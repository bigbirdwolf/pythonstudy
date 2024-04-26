package org.jsoup.parser;

import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.BooleanAttribute;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class Token {
    TokenType type;

    /* loaded from: classes.dex */
    enum TokenType {
        Doctype,
        StartTag,
        EndTag,
        Comment,
        Character,
        EOF
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Token reset();

    private Token() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String tokenType() {
        return getClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void reset(StringBuilder sb) {
        if (sb != null) {
            sb.delete(0, sb.length());
        }
    }

    /* loaded from: classes.dex */
    static final class Doctype extends Token {
        boolean forceQuirks;
        final StringBuilder name;
        final StringBuilder publicIdentifier;
        final StringBuilder systemIdentifier;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Doctype() {
            super();
            this.name = new StringBuilder();
            this.publicIdentifier = new StringBuilder();
            this.systemIdentifier = new StringBuilder();
            this.forceQuirks = false;
            this.type = TokenType.Doctype;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jsoup.parser.Token
        public Token reset() {
            reset(this.name);
            reset(this.publicIdentifier);
            reset(this.systemIdentifier);
            this.forceQuirks = false;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getName() {
            return this.name.toString();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getPublicIdentifier() {
            return this.publicIdentifier.toString();
        }

        public String getSystemIdentifier() {
            return this.systemIdentifier.toString();
        }

        public boolean isForceQuirks() {
            return this.forceQuirks;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class Tag extends Token {
        Attributes attributes;
        private boolean hasEmptyAttributeValue;
        private boolean hasPendingAttributeValue;
        private String pendingAttributeName;
        private StringBuilder pendingAttributeValue;
        private String pendingAttributeValueS;
        boolean selfClosing;
        protected String tagName;

        Tag() {
            super();
            this.pendingAttributeValue = new StringBuilder();
            this.hasEmptyAttributeValue = false;
            this.hasPendingAttributeValue = false;
            this.selfClosing = false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jsoup.parser.Token
        public Tag reset() {
            this.tagName = null;
            this.pendingAttributeName = null;
            reset(this.pendingAttributeValue);
            this.pendingAttributeValueS = null;
            this.hasEmptyAttributeValue = false;
            this.hasPendingAttributeValue = false;
            this.selfClosing = false;
            this.attributes = null;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void newAttribute() {
            Attribute booleanAttribute;
            if (this.attributes == null) {
                this.attributes = new Attributes();
            }
            if (this.pendingAttributeName != null) {
                if (this.hasPendingAttributeValue) {
                    booleanAttribute = new Attribute(this.pendingAttributeName, this.pendingAttributeValue.length() > 0 ? this.pendingAttributeValue.toString() : this.pendingAttributeValueS);
                } else if (this.hasEmptyAttributeValue) {
                    booleanAttribute = new Attribute(this.pendingAttributeName, "");
                } else {
                    booleanAttribute = new BooleanAttribute(this.pendingAttributeName);
                }
                this.attributes.put(booleanAttribute);
            }
            this.pendingAttributeName = null;
            this.hasEmptyAttributeValue = false;
            this.hasPendingAttributeValue = false;
            reset(this.pendingAttributeValue);
            this.pendingAttributeValueS = null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void finaliseTag() {
            if (this.pendingAttributeName != null) {
                newAttribute();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String name() {
            Validate.isFalse(this.tagName == null || this.tagName.length() == 0);
            return this.tagName;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final Tag name(String str) {
            this.tagName = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean isSelfClosing() {
            return this.selfClosing;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final Attributes getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendTagName(String str) {
            if (this.tagName != null) {
                str = this.tagName.concat(str);
            }
            this.tagName = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendTagName(char c) {
            appendTagName(String.valueOf(c));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendAttributeName(String str) {
            if (this.pendingAttributeName != null) {
                str = this.pendingAttributeName.concat(str);
            }
            this.pendingAttributeName = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendAttributeName(char c) {
            appendAttributeName(String.valueOf(c));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendAttributeValue(String str) {
            ensureAttributeValue();
            if (this.pendingAttributeValue.length() == 0) {
                this.pendingAttributeValueS = str;
            } else {
                this.pendingAttributeValue.append(str);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendAttributeValue(char c) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(c);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendAttributeValue(char[] cArr) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(cArr);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void setEmptyAttributeValue() {
            this.hasEmptyAttributeValue = true;
        }

        private void ensureAttributeValue() {
            this.hasPendingAttributeValue = true;
            if (this.pendingAttributeValueS != null) {
                this.pendingAttributeValue.append(this.pendingAttributeValueS);
                this.pendingAttributeValueS = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class StartTag extends Tag {
        /* JADX INFO: Access modifiers changed from: package-private */
        public StartTag() {
            this.attributes = new Attributes();
            this.type = TokenType.StartTag;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jsoup.parser.Token.Tag, org.jsoup.parser.Token
        public Tag reset() {
            super.reset();
            this.attributes = new Attributes();
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public StartTag nameAttr(String str, Attributes attributes) {
            this.tagName = str;
            this.attributes = attributes;
            return this;
        }

        public String toString() {
            if (this.attributes != null && this.attributes.size() > 0) {
                return "<" + name() + " " + this.attributes.toString() + ">";
            }
            return "<" + name() + ">";
        }
    }

    /* loaded from: classes.dex */
    static final class EndTag extends Tag {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EndTag() {
            this.type = TokenType.EndTag;
        }

        public String toString() {
            return "</" + name() + ">";
        }
    }

    /* loaded from: classes.dex */
    static final class Comment extends Token {
        boolean bogus;
        final StringBuilder data;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jsoup.parser.Token
        public Token reset() {
            reset(this.data);
            this.bogus = false;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Comment() {
            super();
            this.data = new StringBuilder();
            this.bogus = false;
            this.type = TokenType.Comment;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getData() {
            return this.data.toString();
        }

        public String toString() {
            return "<!--" + getData() + "-->";
        }
    }

    /* loaded from: classes.dex */
    static final class Character extends Token {
        private String data;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Character() {
            super();
            this.type = TokenType.Character;
        }

        @Override // org.jsoup.parser.Token
        Token reset() {
            this.data = null;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Character data(String str) {
            this.data = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getData() {
            return this.data;
        }

        public String toString() {
            return getData();
        }
    }

    /* loaded from: classes.dex */
    static final class EOF extends Token {
        @Override // org.jsoup.parser.Token
        Token reset() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public EOF() {
            super();
            this.type = TokenType.EOF;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isDoctype() {
        return this.type == TokenType.Doctype;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Doctype asDoctype() {
        return (Doctype) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isStartTag() {
        return this.type == TokenType.StartTag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final StartTag asStartTag() {
        return (StartTag) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isEndTag() {
        return this.type == TokenType.EndTag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final EndTag asEndTag() {
        return (EndTag) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isComment() {
        return this.type == TokenType.Comment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Comment asComment() {
        return (Comment) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isCharacter() {
        return this.type == TokenType.Character;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Character asCharacter() {
        return (Character) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isEOF() {
        return this.type == TokenType.EOF;
    }
}