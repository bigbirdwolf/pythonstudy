package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.nodes.Document;

/* loaded from: classes.dex */
public class Comment extends Node {
    private static final String COMMENT_KEY = "comment";

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#comment";
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlTail(Appendable appendable, int i, Document.OutputSettings outputSettings) {
    }

    public Comment(String str, String str2) {
        super(str2);
        this.attributes.put(COMMENT_KEY, str);
    }

    public String getData() {
        return this.attributes.get(COMMENT_KEY);
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        if (outputSettings.prettyPrint()) {
            indent(appendable, i, outputSettings);
        }
        appendable.append("<!--").append(getData()).append("-->");
    }

    @Override // org.jsoup.nodes.Node
    public String toString() {
        return outerHtml();
    }
}