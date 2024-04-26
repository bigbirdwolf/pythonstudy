package org.jsoup.parser;

import com.alipay.sdk.authjs.a;
import com.alipay.sdk.cons.c;
import com.umeng.commonsdk.proguard.e;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.yltx.oil.partner.oss.OSSFileHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Token;
import org.jsoup.select.Elements;

/* loaded from: classes.dex */
public class HtmlTreeBuilder extends TreeBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Element contextElement;
    private FormElement formElement;
    private Element headElement;
    private HtmlTreeBuilderState originalState;
    private HtmlTreeBuilderState state;
    public static final String[] TagsSearchInScope = {"applet", "caption", "html", "table", "td", "th", "marquee", "object"};
    private static final String[] TagSearchList = {"ol", "ul"};
    private static final String[] TagSearchButton = {"button"};
    private static final String[] TagSearchTableScope = {"html", "table"};
    private static final String[] TagSearchSelectScope = {"optgroup", "option"};
    private static final String[] TagSearchEndTags = {"dd", SocializeProtocolConstants.PROTOCOL_KEY_DT, "li", "option", "optgroup", e.ao, "rp", "rt"};
    private static final String[] TagSearchSpecial = {"address", "applet", "area", "article", "aside", "base", "basefont", "bgsound", "blockquote", "body", "br", "button", "caption", "center", "col", "colgroup", "command", "dd", "details", "dir", "div", "dl", SocializeProtocolConstants.PROTOCOL_KEY_DT, "embed", "fieldset", "figcaption", "figure", "footer", c.c, "frame", "frameset", "h1", "h2", "h3", "h4", "h5", "h6", OSSFileHelper.OSS_HEAD_FILE_NAME, "header", "hgroup", "hr", "html", "iframe", "img", "input", "isindex", "li", "link", "listing", "marquee", "menu", "meta", "nav", "noembed", "noframes", "noscript", "object", "ol", e.ao, a.e, "plaintext", "pre", "script", "section", "select", "style", SocializeProtocolConstants.SUMMARY, "table", "tbody", "td", "textarea", "tfoot", "th", "thead", "title", "tr", "ul", "wbr", "xmp"};
    private boolean baseUriSetFromDoc = false;
    private ArrayList<Element> formattingElements = new ArrayList<>();
    private List<String> pendingTableCharacters = new ArrayList();
    private Token.EndTag emptyEnd = new Token.EndTag();
    private boolean framesetOk = true;
    private boolean fosterInserts = false;
    private boolean fragmentParsing = false;
    private String[] specificScopeTarget = {null};

    @Override // org.jsoup.parser.TreeBuilder
    public /* bridge */ /* synthetic */ boolean processStartTag(String str, Attributes attributes) {
        return super.processStartTag(str, attributes);
    }

    @Override // org.jsoup.parser.TreeBuilder
    Document parse(String str, String str2, ParseErrorList parseErrorList) {
        this.state = HtmlTreeBuilderState.Initial;
        this.baseUriSetFromDoc = false;
        return super.parse(str, str2, parseErrorList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Node> parseFragment(String str, Element element, String str2, ParseErrorList parseErrorList) {
        Element element2;
        this.state = HtmlTreeBuilderState.Initial;
        initialiseParse(str, str2, parseErrorList);
        this.contextElement = element;
        this.fragmentParsing = true;
        if (element != null) {
            if (element.ownerDocument() != null) {
                this.doc.quirksMode(element.ownerDocument().quirksMode());
            }
            String tagName = element.tagName();
            if (StringUtil.in(tagName, "title", "textarea")) {
                this.tokeniser.transition(TokeniserState.Rcdata);
            } else if (StringUtil.in(tagName, "iframe", "noembed", "noframes", "style", "xmp")) {
                this.tokeniser.transition(TokeniserState.Rawtext);
            } else if (tagName.equals("script")) {
                this.tokeniser.transition(TokeniserState.ScriptData);
            } else if (tagName.equals("noscript")) {
                this.tokeniser.transition(TokeniserState.Data);
            } else if (tagName.equals("plaintext")) {
                this.tokeniser.transition(TokeniserState.Data);
            } else {
                this.tokeniser.transition(TokeniserState.Data);
            }
            element2 = new Element(Tag.valueOf("html"), str2);
            this.doc.appendChild(element2);
            this.stack.add(element2);
            resetInsertionMode();
            Elements parents = element.parents();
            parents.add(0, element);
            Iterator<Element> it = parents.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Element next = it.next();
                if (next instanceof FormElement) {
                    this.formElement = (FormElement) next;
                    break;
                }
            }
        } else {
            element2 = null;
        }
        runParser();
        if (element != null && element2 != null) {
            return element2.childNodes();
        }
        return this.doc.childNodes();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.parser.TreeBuilder
    public boolean process(Token token) {
        this.currentToken = token;
        return this.state.process(token, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean process(Token token, HtmlTreeBuilderState htmlTreeBuilderState) {
        this.currentToken = token;
        return htmlTreeBuilderState.process(token, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void transition(HtmlTreeBuilderState htmlTreeBuilderState) {
        this.state = htmlTreeBuilderState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HtmlTreeBuilderState state() {
        return this.state;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void markInsertionMode() {
        this.originalState = this.state;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HtmlTreeBuilderState originalState() {
        return this.originalState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void framesetOk(boolean z) {
        this.framesetOk = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean framesetOk() {
        return this.framesetOk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Document getDocument() {
        return this.doc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getBaseUri() {
        return this.baseUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void maybeSetBaseUri(Element element) {
        if (this.baseUriSetFromDoc) {
            return;
        }
        String absUrl = element.absUrl("href");
        if (absUrl.length() != 0) {
            this.baseUri = absUrl;
            this.baseUriSetFromDoc = true;
            this.doc.setBaseUri(absUrl);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFragmentParsing() {
        return this.fragmentParsing;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void error(HtmlTreeBuilderState htmlTreeBuilderState) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader.pos(), "Unexpected token [%s] when in state [%s]", this.currentToken.tokenType(), htmlTreeBuilderState));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element insert(Token.StartTag startTag) {
        if (startTag.isSelfClosing()) {
            Element insertEmpty = insertEmpty(startTag);
            this.stack.add(insertEmpty);
            this.tokeniser.transition(TokeniserState.Data);
            this.tokeniser.emit(this.emptyEnd.reset().name(insertEmpty.tagName()));
            return insertEmpty;
        }
        Element element = new Element(Tag.valueOf(startTag.name()), this.baseUri, startTag.attributes);
        insert(element);
        return element;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element insertStartTag(String str) {
        Element element = new Element(Tag.valueOf(str), this.baseUri);
        insert(element);
        return element;
    }

    void insert(Element element) {
        insertNode(element);
        this.stack.add(element);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element insertEmpty(Token.StartTag startTag) {
        Tag valueOf = Tag.valueOf(startTag.name());
        Element element = new Element(valueOf, this.baseUri, startTag.attributes);
        insertNode(element);
        if (startTag.isSelfClosing()) {
            if (valueOf.isKnownTag()) {
                if (valueOf.isSelfClosing()) {
                    this.tokeniser.acknowledgeSelfClosingFlag();
                }
            } else {
                valueOf.setSelfClosing();
                this.tokeniser.acknowledgeSelfClosingFlag();
            }
        }
        return element;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FormElement insertForm(Token.StartTag startTag, boolean z) {
        FormElement formElement = new FormElement(Tag.valueOf(startTag.name()), this.baseUri, startTag.attributes);
        setFormElement(formElement);
        insertNode(formElement);
        if (z) {
            this.stack.add(formElement);
        }
        return formElement;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insert(Token.Comment comment) {
        insertNode(new Comment(comment.getData(), this.baseUri));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insert(Token.Character character) {
        Node dataNode;
        String tagName = currentElement().tagName();
        if (tagName.equals("script") || tagName.equals("style")) {
            dataNode = new DataNode(character.getData(), this.baseUri);
        } else {
            dataNode = new TextNode(character.getData(), this.baseUri);
        }
        currentElement().appendChild(dataNode);
    }

    private void insertNode(Node node) {
        if (this.stack.size() == 0) {
            this.doc.appendChild(node);
        } else if (isFosterInserts()) {
            insertInFosterParent(node);
        } else {
            currentElement().appendChild(node);
        }
        if (node instanceof Element) {
            Element element = (Element) node;
            if (!element.tag().isFormListed() || this.formElement == null) {
                return;
            }
            this.formElement.addElement(element);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element pop() {
        return this.stack.remove(this.stack.size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void push(Element element) {
        this.stack.add(element);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList<Element> getStack() {
        return this.stack;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onStack(Element element) {
        return isElementInQueue(this.stack, element);
    }

    private boolean isElementInQueue(ArrayList<Element> arrayList, Element element) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == element) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element getFromStack(String str) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            Element element = this.stack.get(size);
            if (element.nodeName().equals(str)) {
                return element;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean removeFromStack(Element element) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            if (this.stack.get(size) == element) {
                this.stack.remove(size);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void popStackToClose(String str) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            this.stack.remove(size);
            if (this.stack.get(size).nodeName().equals(str)) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void popStackToClose(String... strArr) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            this.stack.remove(size);
            if (StringUtil.in(this.stack.get(size).nodeName(), strArr)) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void popStackToBefore(String str) {
        for (int size = this.stack.size() - 1; size >= 0 && !this.stack.get(size).nodeName().equals(str); size--) {
            this.stack.remove(size);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearStackToTableContext() {
        clearStackToContext("table");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearStackToTableBodyContext() {
        clearStackToContext("tbody", "tfoot", "thead");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearStackToTableRowContext() {
        clearStackToContext("tr");
    }

    private void clearStackToContext(String... strArr) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            Element element = this.stack.get(size);
            if (StringUtil.in(element.nodeName(), strArr) || element.nodeName().equals("html")) {
                return;
            }
            this.stack.remove(size);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element aboveOnStack(Element element) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            if (this.stack.get(size) == element) {
                return this.stack.get(size - 1);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertOnStackAfter(Element element, Element element2) {
        int lastIndexOf = this.stack.lastIndexOf(element);
        Validate.isTrue(lastIndexOf != -1);
        this.stack.add(lastIndexOf + 1, element2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceOnStack(Element element, Element element2) {
        replaceInQueue(this.stack, element, element2);
    }

    private void replaceInQueue(ArrayList<Element> arrayList, Element element, Element element2) {
        int lastIndexOf = arrayList.lastIndexOf(element);
        Validate.isTrue(lastIndexOf != -1);
        arrayList.set(lastIndexOf, element2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetInsertionMode() {
        boolean z = false;
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            Element element = this.stack.get(size);
            if (size == 0) {
                element = this.contextElement;
                z = true;
            }
            String nodeName = element.nodeName();
            if ("select".equals(nodeName)) {
                transition(HtmlTreeBuilderState.InSelect);
                return;
            } else if ("td".equals(nodeName) || ("th".equals(nodeName) && !z)) {
                transition(HtmlTreeBuilderState.InCell);
                return;
            } else if ("tr".equals(nodeName)) {
                transition(HtmlTreeBuilderState.InRow);
                return;
            } else if ("tbody".equals(nodeName) || "thead".equals(nodeName) || "tfoot".equals(nodeName)) {
                transition(HtmlTreeBuilderState.InTableBody);
                return;
            } else if ("caption".equals(nodeName)) {
                transition(HtmlTreeBuilderState.InCaption);
                return;
            } else if ("colgroup".equals(nodeName)) {
                transition(HtmlTreeBuilderState.InColumnGroup);
                return;
            } else if ("table".equals(nodeName)) {
                transition(HtmlTreeBuilderState.InTable);
                return;
            } else if (OSSFileHelper.OSS_HEAD_FILE_NAME.equals(nodeName)) {
                transition(HtmlTreeBuilderState.InBody);
                return;
            } else if ("body".equals(nodeName)) {
                transition(HtmlTreeBuilderState.InBody);
                return;
            } else if ("frameset".equals(nodeName)) {
                transition(HtmlTreeBuilderState.InFrameset);
                return;
            } else if ("html".equals(nodeName)) {
                transition(HtmlTreeBuilderState.BeforeHead);
                return;
            } else if (z) {
                transition(HtmlTreeBuilderState.InBody);
                return;
            }
        }
    }

    private boolean inSpecificScope(String str, String[] strArr, String[] strArr2) {
        this.specificScopeTarget[0] = str;
        return inSpecificScope(this.specificScopeTarget, strArr, strArr2);
    }

    private boolean inSpecificScope(String[] strArr, String[] strArr2, String[] strArr3) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            String nodeName = this.stack.get(size).nodeName();
            if (StringUtil.in(nodeName, strArr)) {
                return true;
            }
            if (StringUtil.in(nodeName, strArr2)) {
                return false;
            }
            if (strArr3 != null && StringUtil.in(nodeName, strArr3)) {
                return false;
            }
        }
        Validate.fail("Should not be reachable");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean inScope(String[] strArr) {
        return inSpecificScope(strArr, TagsSearchInScope, (String[]) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean inScope(String str) {
        return inScope(str, null);
    }

    boolean inScope(String str, String[] strArr) {
        return inSpecificScope(str, TagsSearchInScope, strArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean inListItemScope(String str) {
        return inScope(str, TagSearchList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean inButtonScope(String str) {
        return inScope(str, TagSearchButton);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean inTableScope(String str) {
        return inSpecificScope(str, TagSearchTableScope, (String[]) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean inSelectScope(String str) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            String nodeName = this.stack.get(size).nodeName();
            if (nodeName.equals(str)) {
                return true;
            }
            if (!StringUtil.in(nodeName, TagSearchSelectScope)) {
                return false;
            }
        }
        Validate.fail("Should not be reachable");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHeadElement(Element element) {
        this.headElement = element;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element getHeadElement() {
        return this.headElement;
    }

    boolean isFosterInserts() {
        return this.fosterInserts;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFosterInserts(boolean z) {
        this.fosterInserts = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FormElement getFormElement() {
        return this.formElement;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFormElement(FormElement formElement) {
        this.formElement = formElement;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void newPendingTableCharacters() {
        this.pendingTableCharacters = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> getPendingTableCharacters() {
        return this.pendingTableCharacters;
    }

    void setPendingTableCharacters(List<String> list) {
        this.pendingTableCharacters = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void generateImpliedEndTags(String str) {
        while (str != null && !currentElement().nodeName().equals(str) && StringUtil.in(currentElement().nodeName(), TagSearchEndTags)) {
            pop();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void generateImpliedEndTags() {
        generateImpliedEndTags(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSpecial(Element element) {
        return StringUtil.in(element.nodeName(), TagSearchSpecial);
    }

    Element lastFormattingElement() {
        if (this.formattingElements.size() > 0) {
            return this.formattingElements.get(this.formattingElements.size() - 1);
        }
        return null;
    }

    Element removeLastFormattingElement() {
        int size = this.formattingElements.size();
        if (size > 0) {
            return this.formattingElements.remove(size - 1);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pushActiveFormattingElements(Element element) {
        int size = this.formattingElements.size() - 1;
        int i = 0;
        while (true) {
            if (size >= 0) {
                Element element2 = this.formattingElements.get(size);
                if (element2 == null) {
                    break;
                }
                if (isSameFormattingElement(element, element2)) {
                    i++;
                }
                if (i == 3) {
                    this.formattingElements.remove(size);
                    break;
                }
                size--;
            } else {
                break;
            }
        }
        this.formattingElements.add(element);
    }

    private boolean isSameFormattingElement(Element element, Element element2) {
        return element.nodeName().equals(element2.nodeName()) && element.attributes().equals(element2.attributes());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void reconstructFormattingElements() {
        /*
            r7 = this;
            org.jsoup.nodes.Element r0 = r7.lastFormattingElement()
            if (r0 == 0) goto L59
            boolean r1 = r7.onStack(r0)
            if (r1 == 0) goto Ld
            goto L59
        Ld:
            java.util.ArrayList<org.jsoup.nodes.Element> r1 = r7.formattingElements
            int r1 = r1.size()
            r2 = 1
            int r1 = r1 - r2
            r3 = r0
            r0 = r1
        L17:
            r4 = 0
            if (r0 != 0) goto L1b
            goto L2e
        L1b:
            java.util.ArrayList<org.jsoup.nodes.Element> r3 = r7.formattingElements
            int r0 = r0 + (-1)
            java.lang.Object r3 = r3.get(r0)
            org.jsoup.nodes.Element r3 = (org.jsoup.nodes.Element) r3
            if (r3 == 0) goto L2d
            boolean r5 = r7.onStack(r3)
            if (r5 == 0) goto L17
        L2d:
            r2 = 0
        L2e:
            if (r2 != 0) goto L3b
            java.util.ArrayList<org.jsoup.nodes.Element> r2 = r7.formattingElements
            int r0 = r0 + 1
            java.lang.Object r2 = r2.get(r0)
            org.jsoup.nodes.Element r2 = (org.jsoup.nodes.Element) r2
            r3 = r2
        L3b:
            org.jsoup.helper.Validate.notNull(r3)
            java.lang.String r2 = r3.nodeName()
            org.jsoup.nodes.Element r2 = r7.insertStartTag(r2)
            org.jsoup.nodes.Attributes r5 = r2.attributes()
            org.jsoup.nodes.Attributes r6 = r3.attributes()
            r5.addAll(r6)
            java.util.ArrayList<org.jsoup.nodes.Element> r5 = r7.formattingElements
            r5.set(r0, r2)
            if (r0 != r1) goto L2d
            return
        L59:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilder.reconstructFormattingElements():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearFormattingElementsToLastMarker() {
        while (!this.formattingElements.isEmpty() && removeLastFormattingElement() != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeFromActiveFormattingElements(Element element) {
        for (int size = this.formattingElements.size() - 1; size >= 0; size--) {
            if (this.formattingElements.get(size) == element) {
                this.formattingElements.remove(size);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isInActiveFormattingElements(Element element) {
        return isElementInQueue(this.formattingElements, element);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element getActiveFormattingElement(String str) {
        for (int size = this.formattingElements.size() - 1; size >= 0; size--) {
            Element element = this.formattingElements.get(size);
            if (element == null) {
                return null;
            }
            if (element.nodeName().equals(str)) {
                return element;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceActiveFormattingElement(Element element, Element element2) {
        replaceInQueue(this.formattingElements, element, element2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertMarkerToFormattingElements() {
        this.formattingElements.add(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertInFosterParent(Node node) {
        Element element;
        Element fromStack = getFromStack("table");
        boolean z = false;
        if (fromStack != null) {
            if (fromStack.parent() != null) {
                element = fromStack.parent();
                z = true;
            } else {
                element = aboveOnStack(fromStack);
            }
        } else {
            element = this.stack.get(0);
        }
        if (z) {
            Validate.notNull(fromStack);
            fromStack.before(node);
            return;
        }
        element.appendChild(node);
    }

    public String toString() {
        return "TreeBuilder{currentToken=" + this.currentToken + ", state=" + this.state + ", currentElement=" + currentElement() + '}';
    }
}