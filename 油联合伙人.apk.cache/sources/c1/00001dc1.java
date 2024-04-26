package org.jsoup.parser;

import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Token;

/* loaded from: classes.dex */
public class XmlTreeBuilder extends TreeBuilder {
    @Override // org.jsoup.parser.TreeBuilder
    public /* bridge */ /* synthetic */ boolean processStartTag(String str, Attributes attributes) {
        return super.processStartTag(str, attributes);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.parser.TreeBuilder
    public void initialiseParse(String str, String str2, ParseErrorList parseErrorList) {
        super.initialiseParse(str, str2, parseErrorList);
        this.stack.add(this.doc);
        this.doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.parser.TreeBuilder
    public boolean process(Token token) {
        switch (token.type) {
            case StartTag:
                insert(token.asStartTag());
                return true;
            case EndTag:
                popStackToClose(token.asEndTag());
                return true;
            case Comment:
                insert(token.asComment());
                return true;
            case Character:
                insert(token.asCharacter());
                return true;
            case Doctype:
                insert(token.asDoctype());
                return true;
            case EOF:
                return true;
            default:
                Validate.fail("Unexpected token type: " + token.type);
                return true;
        }
    }

    private void insertNode(Node node) {
        currentElement().appendChild(node);
    }

    Element insert(Token.StartTag startTag) {
        Tag valueOf = Tag.valueOf(startTag.name());
        Element element = new Element(valueOf, this.baseUri, startTag.attributes);
        insertNode(element);
        if (startTag.isSelfClosing()) {
            this.tokeniser.acknowledgeSelfClosingFlag();
            if (!valueOf.isKnownTag()) {
                valueOf.setSelfClosing();
            }
        } else {
            this.stack.add(element);
        }
        return element;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v6, types: [org.jsoup.nodes.Node, org.jsoup.nodes.XmlDeclaration] */
    /* JADX WARN: Type inference failed for: r5v0, types: [org.jsoup.parser.XmlTreeBuilder] */
    void insert(Token.Comment comment) {
        Comment comment2 = new Comment(comment.getData(), this.baseUri);
        if (comment.bogus) {
            String data = comment2.getData();
            if (data.length() > 1 && (data.startsWith("!") || data.startsWith("?"))) {
                Element child = Jsoup.parse("<" + data.substring(1, data.length() - 1) + ">", this.baseUri, Parser.xmlParser()).child(0);
                ?? xmlDeclaration = new XmlDeclaration(child.tagName(), comment2.baseUri(), data.startsWith("!"));
                xmlDeclaration.attributes().addAll(child.attributes());
                comment2 = xmlDeclaration;
            }
        }
        insertNode(comment2);
    }

    void insert(Token.Character character) {
        insertNode(new TextNode(character.getData(), this.baseUri));
    }

    void insert(Token.Doctype doctype) {
        insertNode(new DocumentType(doctype.getName(), doctype.getPublicIdentifier(), doctype.getSystemIdentifier(), this.baseUri));
    }

    private void popStackToClose(Token.EndTag endTag) {
        Element element;
        String name = endTag.name();
        int size = this.stack.size() - 1;
        while (true) {
            if (size < 0) {
                element = null;
                break;
            }
            element = this.stack.get(size);
            if (element.nodeName().equals(name)) {
                break;
            }
            size--;
        }
        if (element == null) {
            return;
        }
        for (int size2 = this.stack.size() - 1; size2 >= 0; size2--) {
            Element element2 = this.stack.get(size2);
            this.stack.remove(size2);
            if (element2 == element) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Node> parseFragment(String str, String str2, ParseErrorList parseErrorList) {
        initialiseParse(str, str2, parseErrorList);
        runParser();
        return this.doc.childNodes();
    }
}