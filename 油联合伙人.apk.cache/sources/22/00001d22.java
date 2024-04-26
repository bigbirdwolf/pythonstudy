package org.jsoup.examples;

import com.umeng.commonsdk.proguard.e;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.IOException;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

/* loaded from: classes.dex */
public class HtmlToPlainText {
    private static final int timeout = 5000;
    private static final String userAgent = "Mozilla/5.0 (jsoup)";

    public static void main(String... strArr) throws IOException {
        Validate.isTrue(strArr.length == 1 || strArr.length == 2, "usage: java -cp jsoup.jar org.jsoup.examples.HtmlToPlainText url [selector]");
        String str = strArr[0];
        String str2 = strArr.length == 2 ? strArr[1] : null;
        Document document = Jsoup.connect(str).userAgent(userAgent).timeout(5000).get();
        HtmlToPlainText htmlToPlainText = new HtmlToPlainText();
        if (str2 != null) {
            Iterator<Element> it = document.select(str2).iterator();
            while (it.hasNext()) {
                System.out.println(htmlToPlainText.getPlainText(it.next()));
            }
            return;
        }
        System.out.println(htmlToPlainText.getPlainText(document));
    }

    public String getPlainText(Element element) {
        FormattingVisitor formattingVisitor = new FormattingVisitor();
        new NodeTraversor(formattingVisitor).traverse(element);
        return formattingVisitor.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class FormattingVisitor implements NodeVisitor {
        private static final int maxWidth = 80;
        private StringBuilder accum;
        private int width;

        private FormattingVisitor() {
            this.width = 0;
            this.accum = new StringBuilder();
        }

        @Override // org.jsoup.select.NodeVisitor
        public void head(Node node, int i) {
            String nodeName = node.nodeName();
            if (node instanceof TextNode) {
                append(((TextNode) node).text());
            } else if (nodeName.equals("li")) {
                append("\n * ");
            } else if (nodeName.equals(SocializeProtocolConstants.PROTOCOL_KEY_DT)) {
                append("  ");
            } else if (StringUtil.in(nodeName, e.ao, "h1", "h2", "h3", "h4", "h5", "tr")) {
                append("\n");
            }
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(Node node, int i) {
            String nodeName = node.nodeName();
            if (StringUtil.in(nodeName, "br", "dd", SocializeProtocolConstants.PROTOCOL_KEY_DT, e.ao, "h1", "h2", "h3", "h4", "h5")) {
                append("\n");
            } else if (nodeName.equals(e.al)) {
                append(String.format(" <%s>", node.absUrl("href")));
            }
        }

        private void append(String str) {
            if (str.startsWith("\n")) {
                this.width = 0;
            }
            if (str.equals(" ") && (this.accum.length() == 0 || StringUtil.in(this.accum.substring(this.accum.length() - 1), " ", "\n"))) {
                return;
            }
            if (str.length() + this.width > 80) {
                String[] split = str.split("\\s+");
                int i = 0;
                while (i < split.length) {
                    String str2 = split[i];
                    if (!(i == split.length - 1)) {
                        str2 = str2 + " ";
                    }
                    if (str2.length() + this.width > 80) {
                        StringBuilder sb = this.accum;
                        sb.append("\n");
                        sb.append(str2);
                        this.width = str2.length();
                    } else {
                        this.accum.append(str2);
                        this.width += str2.length();
                    }
                    i++;
                }
                return;
            }
            this.accum.append(str);
            this.width += str.length();
        }

        public String toString() {
            return this.accum.toString();
        }
    }
}