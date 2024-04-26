package org.jsoup.parser;

import java.util.ArrayList;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Token;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class TreeBuilder {
    protected String baseUri;
    protected Token currentToken;
    protected Document doc;
    protected ParseErrorList errors;
    CharacterReader reader;
    protected ArrayList<Element> stack;
    Tokeniser tokeniser;
    private Token.StartTag start = new Token.StartTag();
    private Token.EndTag end = new Token.EndTag();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean process(Token token);

    /* JADX INFO: Access modifiers changed from: protected */
    public void initialiseParse(String str, String str2, ParseErrorList parseErrorList) {
        Validate.notNull(str, "String input must not be null");
        Validate.notNull(str2, "BaseURI must not be null");
        this.doc = new Document(str2);
        this.reader = new CharacterReader(str);
        this.errors = parseErrorList;
        this.tokeniser = new Tokeniser(this.reader, parseErrorList);
        this.stack = new ArrayList<>(32);
        this.baseUri = str2;
    }

    Document parse(String str, String str2) {
        return parse(str, str2, ParseErrorList.noTracking());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Document parse(String str, String str2, ParseErrorList parseErrorList) {
        initialiseParse(str, str2, parseErrorList);
        runParser();
        return this.doc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void runParser() {
        Token read;
        do {
            read = this.tokeniser.read();
            process(read);
            read.reset();
        } while (read.type != Token.TokenType.EOF);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean processStartTag(String str) {
        if (this.currentToken == this.start) {
            return process(new Token.StartTag().name(str));
        }
        return process(this.start.reset().name(str));
    }

    public boolean processStartTag(String str, Attributes attributes) {
        if (this.currentToken == this.start) {
            return process(new Token.StartTag().nameAttr(str, attributes));
        }
        this.start.reset();
        this.start.nameAttr(str, attributes);
        return process(this.start);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean processEndTag(String str) {
        if (this.currentToken == this.end) {
            return process(new Token.EndTag().name(str));
        }
        return process(this.end.reset().name(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Element currentElement() {
        int size = this.stack.size();
        if (size > 0) {
            return this.stack.get(size - 1);
        }
        return null;
    }
}