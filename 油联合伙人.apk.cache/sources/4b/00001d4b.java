package org.jsoup.nodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.SerializationException;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

/* loaded from: classes.dex */
public abstract class Node implements Cloneable {
    private static final List<Node> EMPTY_NODES = Collections.emptyList();
    Attributes attributes;
    String baseUri;
    List<Node> childNodes;
    Node parentNode;
    int siblingIndex;

    public boolean equals(Object obj) {
        return this == obj;
    }

    public abstract String nodeName();

    abstract void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException;

    abstract void outerHtmlTail(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public Node(String str, Attributes attributes) {
        Validate.notNull(str);
        Validate.notNull(attributes);
        this.childNodes = EMPTY_NODES;
        this.baseUri = str.trim();
        this.attributes = attributes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Node(String str) {
        this(str, new Attributes());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Node() {
        this.childNodes = EMPTY_NODES;
        this.attributes = null;
    }

    public String attr(String str) {
        Validate.notNull(str);
        if (this.attributes.hasKey(str)) {
            return this.attributes.get(str);
        }
        return str.toLowerCase().startsWith("abs:") ? absUrl(str.substring("abs:".length())) : "";
    }

    public Attributes attributes() {
        return this.attributes;
    }

    public Node attr(String str, String str2) {
        this.attributes.put(str, str2);
        return this;
    }

    public boolean hasAttr(String str) {
        Validate.notNull(str);
        if (str.startsWith("abs:")) {
            String substring = str.substring("abs:".length());
            if (this.attributes.hasKey(substring) && !absUrl(substring).equals("")) {
                return true;
            }
        }
        return this.attributes.hasKey(str);
    }

    public Node removeAttr(String str) {
        Validate.notNull(str);
        this.attributes.remove(str);
        return this;
    }

    public String baseUri() {
        return this.baseUri;
    }

    public void setBaseUri(final String str) {
        Validate.notNull(str);
        traverse(new NodeVisitor() { // from class: org.jsoup.nodes.Node.1
            @Override // org.jsoup.select.NodeVisitor
            public void tail(Node node, int i) {
            }

            @Override // org.jsoup.select.NodeVisitor
            public void head(Node node, int i) {
                node.baseUri = str;
            }
        });
    }

    public String absUrl(String str) {
        Validate.notEmpty(str);
        return !hasAttr(str) ? "" : StringUtil.resolve(this.baseUri, attr(str));
    }

    public Node childNode(int i) {
        return this.childNodes.get(i);
    }

    public List<Node> childNodes() {
        return Collections.unmodifiableList(this.childNodes);
    }

    public List<Node> childNodesCopy() {
        ArrayList arrayList = new ArrayList(this.childNodes.size());
        for (Node node : this.childNodes) {
            arrayList.add(node.clone());
        }
        return arrayList;
    }

    public final int childNodeSize() {
        return this.childNodes.size();
    }

    protected Node[] childNodesAsArray() {
        return (Node[]) this.childNodes.toArray(new Node[childNodeSize()]);
    }

    public Node parent() {
        return this.parentNode;
    }

    public final Node parentNode() {
        return this.parentNode;
    }

    public Document ownerDocument() {
        if (this instanceof Document) {
            return (Document) this;
        }
        if (this.parentNode == null) {
            return null;
        }
        return this.parentNode.ownerDocument();
    }

    public void remove() {
        Validate.notNull(this.parentNode);
        this.parentNode.removeChild(this);
    }

    public Node before(String str) {
        addSiblingHtml(this.siblingIndex, str);
        return this;
    }

    public Node before(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(this.siblingIndex, node);
        return this;
    }

    public Node after(String str) {
        addSiblingHtml(this.siblingIndex + 1, str);
        return this;
    }

    public Node after(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(this.siblingIndex + 1, node);
        return this;
    }

    private void addSiblingHtml(int i, String str) {
        Validate.notNull(str);
        Validate.notNull(this.parentNode);
        List<Node> parseFragment = Parser.parseFragment(str, parent() instanceof Element ? (Element) parent() : null, baseUri());
        this.parentNode.addChildren(i, (Node[]) parseFragment.toArray(new Node[parseFragment.size()]));
    }

    public Node wrap(String str) {
        Validate.notEmpty(str);
        List<Node> parseFragment = Parser.parseFragment(str, parent() instanceof Element ? (Element) parent() : null, baseUri());
        Node node = parseFragment.get(0);
        if (node == null || !(node instanceof Element)) {
            return null;
        }
        Element element = (Element) node;
        Element deepChild = getDeepChild(element);
        this.parentNode.replaceChild(this, element);
        deepChild.addChildren(this);
        if (parseFragment.size() > 0) {
            for (int i = 0; i < parseFragment.size(); i++) {
                Node node2 = parseFragment.get(i);
                node2.parentNode.removeChild(node2);
                element.appendChild(node2);
            }
        }
        return this;
    }

    public Node unwrap() {
        Validate.notNull(this.parentNode);
        Node node = this.childNodes.size() > 0 ? this.childNodes.get(0) : null;
        this.parentNode.addChildren(this.siblingIndex, childNodesAsArray());
        remove();
        return node;
    }

    private Element getDeepChild(Element element) {
        Elements children = element.children();
        return children.size() > 0 ? getDeepChild(children.get(0)) : element;
    }

    public void replaceWith(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.replaceChild(this, node);
    }

    protected void setParentNode(Node node) {
        if (this.parentNode != null) {
            this.parentNode.removeChild(this);
        }
        this.parentNode = node;
    }

    protected void replaceChild(Node node, Node node2) {
        Validate.isTrue(node.parentNode == this);
        Validate.notNull(node2);
        if (node2.parentNode != null) {
            node2.parentNode.removeChild(node2);
        }
        int i = node.siblingIndex;
        this.childNodes.set(i, node2);
        node2.parentNode = this;
        node2.setSiblingIndex(i);
        node.parentNode = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeChild(Node node) {
        Validate.isTrue(node.parentNode == this);
        int i = node.siblingIndex;
        this.childNodes.remove(i);
        reindexChildren(i);
        node.parentNode = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addChildren(Node... nodeArr) {
        for (Node node : nodeArr) {
            reparentChild(node);
            ensureChildNodes();
            this.childNodes.add(node);
            node.setSiblingIndex(this.childNodes.size() - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addChildren(int i, Node... nodeArr) {
        Validate.noNullElements(nodeArr);
        ensureChildNodes();
        for (int length = nodeArr.length - 1; length >= 0; length--) {
            Node node = nodeArr[length];
            reparentChild(node);
            this.childNodes.add(i, node);
            reindexChildren(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void ensureChildNodes() {
        if (this.childNodes == EMPTY_NODES) {
            this.childNodes = new ArrayList(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reparentChild(Node node) {
        if (node.parentNode != null) {
            node.parentNode.removeChild(node);
        }
        node.setParentNode(this);
    }

    private void reindexChildren(int i) {
        while (i < this.childNodes.size()) {
            this.childNodes.get(i).setSiblingIndex(i);
            i++;
        }
    }

    public List<Node> siblingNodes() {
        if (this.parentNode == null) {
            return Collections.emptyList();
        }
        List<Node> list = this.parentNode.childNodes;
        ArrayList arrayList = new ArrayList(list.size() - 1);
        for (Node node : list) {
            if (node != this) {
                arrayList.add(node);
            }
        }
        return arrayList;
    }

    public Node nextSibling() {
        if (this.parentNode == null) {
            return null;
        }
        List<Node> list = this.parentNode.childNodes;
        int i = this.siblingIndex + 1;
        if (list.size() > i) {
            return list.get(i);
        }
        return null;
    }

    public Node previousSibling() {
        if (this.parentNode != null && this.siblingIndex > 0) {
            return this.parentNode.childNodes.get(this.siblingIndex - 1);
        }
        return null;
    }

    public int siblingIndex() {
        return this.siblingIndex;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSiblingIndex(int i) {
        this.siblingIndex = i;
    }

    public Node traverse(NodeVisitor nodeVisitor) {
        Validate.notNull(nodeVisitor);
        new NodeTraversor(nodeVisitor).traverse(this);
        return this;
    }

    public String outerHtml() {
        StringBuilder sb = new StringBuilder(128);
        outerHtml(sb);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void outerHtml(Appendable appendable) {
        new NodeTraversor(new OuterHtmlVisitor(appendable, getOutputSettings())).traverse(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Document.OutputSettings getOutputSettings() {
        return (ownerDocument() != null ? ownerDocument() : new Document("")).outputSettings();
    }

    public <T extends Appendable> T html(T t) {
        outerHtml(t);
        return t;
    }

    public String toString() {
        return outerHtml();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void indent(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        appendable.append("\n").append(StringUtil.padding(i * outputSettings.indentAmount()));
    }

    public boolean hasSameValue(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return outerHtml().equals(((Node) obj).outerHtml());
    }

    @Override // 
    public Node clone() {
        Node doClone = doClone(null);
        LinkedList linkedList = new LinkedList();
        linkedList.add(doClone);
        while (!linkedList.isEmpty()) {
            Node node = (Node) linkedList.remove();
            for (int i = 0; i < node.childNodes.size(); i++) {
                Node doClone2 = node.childNodes.get(i).doClone(node);
                node.childNodes.set(i, doClone2);
                linkedList.add(doClone2);
            }
        }
        return doClone;
    }

    protected Node doClone(Node node) {
        try {
            Node node2 = (Node) super.clone();
            node2.parentNode = node;
            node2.siblingIndex = node == null ? 0 : this.siblingIndex;
            node2.attributes = this.attributes != null ? this.attributes.clone() : null;
            node2.baseUri = this.baseUri;
            node2.childNodes = new ArrayList(this.childNodes.size());
            for (Node node3 : this.childNodes) {
                node2.childNodes.add(node3);
            }
            return node2;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class OuterHtmlVisitor implements NodeVisitor {
        private Appendable accum;
        private Document.OutputSettings out;

        OuterHtmlVisitor(Appendable appendable, Document.OutputSettings outputSettings) {
            this.accum = appendable;
            this.out = outputSettings;
        }

        @Override // org.jsoup.select.NodeVisitor
        public void head(Node node, int i) {
            try {
                node.outerHtmlHead(this.accum, i, this.out);
            } catch (IOException e) {
                throw new SerializationException(e);
            }
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(Node node, int i) {
            if (node.nodeName().equals("#text")) {
                return;
            }
            try {
                node.outerHtmlTail(this.accum, i, this.out);
            } catch (IOException e) {
                throw new SerializationException(e);
            }
        }
    }
}