package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;

/* loaded from: classes.dex */
public class XmlDeclaration extends Node {
    private final boolean isProcessingInstruction;
    private final String name;

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#declaration";
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlTail(Appendable appendable, int i, Document.OutputSettings outputSettings) {
    }

    public XmlDeclaration(String str, String str2, boolean z) {
        super(str2);
        Validate.notNull(str);
        this.name = str;
        this.isProcessingInstruction = z;
    }

    public String name() {
        return this.name;
    }

    public String getWholeDeclaration() {
        return this.attributes.html().trim();
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        appendable.append("<").append(this.isProcessingInstruction ? "!" : "?").append(this.name);
        this.attributes.html(appendable, outputSettings);
        appendable.append(this.isProcessingInstruction ? "!" : "?").append(">");
    }

    @Override // org.jsoup.nodes.Node
    public String toString() {
        return outerHtml();
    }
}