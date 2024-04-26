package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;

/* loaded from: classes.dex */
public class DocumentType extends Node {
    private static final String NAME = "name";
    private static final String PUBLIC_ID = "publicId";
    private static final String SYSTEM_ID = "systemId";

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#doctype";
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlTail(Appendable appendable, int i, Document.OutputSettings outputSettings) {
    }

    public DocumentType(String str, String str2, String str3, String str4) {
        super(str4);
        attr("name", str);
        attr(PUBLIC_ID, str2);
        attr(SYSTEM_ID, str3);
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        if (outputSettings.syntax() == Document.OutputSettings.Syntax.html && !has(PUBLIC_ID) && !has(SYSTEM_ID)) {
            appendable.append("<!doctype");
        } else {
            appendable.append("<!DOCTYPE");
        }
        if (has("name")) {
            appendable.append(" ").append(attr("name"));
        }
        if (has(PUBLIC_ID)) {
            appendable.append(" PUBLIC \"").append(attr(PUBLIC_ID)).append('\"');
        }
        if (has(SYSTEM_ID)) {
            appendable.append(" \"").append(attr(SYSTEM_ID)).append('\"');
        }
        appendable.append('>');
    }

    private boolean has(String str) {
        return !StringUtil.isBlank(attr(str));
    }
}