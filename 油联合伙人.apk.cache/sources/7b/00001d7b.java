package org.jsoup.parser;

import com.alipay.sdk.util.i;
import com.yltx.oil.partner.utils.OfflineResource;
import java.util.Arrays;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.Token;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class Tokeniser {
    private static final char[] notCharRefCharsSorted = {'\t', '\n', '\r', '\f', ' ', '<', '&'};
    static final char replacementChar = 65533;
    private Token emitPending;
    private ParseErrorList errors;
    private String lastStartTag;
    private CharacterReader reader;
    Token.Tag tagPending;
    private TokeniserState state = TokeniserState.Data;
    private boolean isEmitPending = false;
    private String charsString = null;
    private StringBuilder charsBuilder = new StringBuilder(1024);
    StringBuilder dataBuffer = new StringBuilder(1024);
    Token.StartTag startPending = new Token.StartTag();
    Token.EndTag endPending = new Token.EndTag();
    Token.Character charPending = new Token.Character();
    Token.Doctype doctypePending = new Token.Doctype();
    Token.Comment commentPending = new Token.Comment();
    private boolean selfClosingFlagAcknowledged = true;
    private final char[] charRefHolder = new char[1];

    boolean currentNodeInHtmlNS() {
        return true;
    }

    static {
        Arrays.sort(notCharRefCharsSorted);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Tokeniser(CharacterReader characterReader, ParseErrorList parseErrorList) {
        this.reader = characterReader;
        this.errors = parseErrorList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token read() {
        if (!this.selfClosingFlagAcknowledged) {
            error("Self closing flag not acknowledged");
            this.selfClosingFlagAcknowledged = true;
        }
        while (!this.isEmitPending) {
            this.state.read(this, this.reader);
        }
        if (this.charsBuilder.length() > 0) {
            String sb = this.charsBuilder.toString();
            this.charsBuilder.delete(0, this.charsBuilder.length());
            this.charsString = null;
            return this.charPending.data(sb);
        } else if (this.charsString != null) {
            Token.Character data = this.charPending.data(this.charsString);
            this.charsString = null;
            return data;
        } else {
            this.isEmitPending = false;
            return this.emitPending;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void emit(Token token) {
        Validate.isFalse(this.isEmitPending, "There is an unread token pending!");
        this.emitPending = token;
        this.isEmitPending = true;
        if (token.type == Token.TokenType.StartTag) {
            Token.StartTag startTag = (Token.StartTag) token;
            this.lastStartTag = startTag.tagName;
            if (startTag.selfClosing) {
                this.selfClosingFlagAcknowledged = false;
            }
        } else if (token.type != Token.TokenType.EndTag || ((Token.EndTag) token).attributes == null) {
        } else {
            error("Attributes incorrectly present on end tag");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void emit(String str) {
        if (this.charsString == null) {
            this.charsString = str;
            return;
        }
        if (this.charsBuilder.length() == 0) {
            this.charsBuilder.append(this.charsString);
        }
        this.charsBuilder.append(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void emit(char[] cArr) {
        emit(String.valueOf(cArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void emit(char c) {
        emit(String.valueOf(c));
    }

    TokeniserState getState() {
        return this.state;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void transition(TokeniserState tokeniserState) {
        this.state = tokeniserState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void advanceTransition(TokeniserState tokeniserState) {
        this.reader.advance();
        this.state = tokeniserState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void acknowledgeSelfClosingFlag() {
        this.selfClosingFlagAcknowledged = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char[] consumeCharacterReference(Character ch, boolean z) {
        int i;
        if (this.reader.isEmpty()) {
            return null;
        }
        if ((ch == null || ch.charValue() != this.reader.current()) && !this.reader.matchesAnySorted(notCharRefCharsSorted)) {
            char[] cArr = this.charRefHolder;
            this.reader.mark();
            if (this.reader.matchConsume("#")) {
                boolean matchConsumeIgnoreCase = this.reader.matchConsumeIgnoreCase(OfflineResource.VOICE_DUXY);
                String consumeHexSequence = matchConsumeIgnoreCase ? this.reader.consumeHexSequence() : this.reader.consumeDigitSequence();
                if (consumeHexSequence.length() == 0) {
                    characterReferenceError("numeric reference with no numerals");
                    this.reader.rewindToMark();
                    return null;
                }
                if (!this.reader.matchConsume(i.b)) {
                    characterReferenceError("missing semicolon");
                }
                try {
                    i = Integer.valueOf(consumeHexSequence, matchConsumeIgnoreCase ? 16 : 10).intValue();
                } catch (NumberFormatException unused) {
                    i = -1;
                }
                if (i == -1 || ((i >= 55296 && i <= 57343) || i > 1114111)) {
                    characterReferenceError("character outside of valid range");
                    cArr[0] = replacementChar;
                    return cArr;
                } else if (i < 65536) {
                    cArr[0] = (char) i;
                    return cArr;
                } else {
                    return Character.toChars(i);
                }
            }
            String consumeLetterThenDigitSequence = this.reader.consumeLetterThenDigitSequence();
            boolean matches = this.reader.matches(';');
            if (!(Entities.isBaseNamedEntity(consumeLetterThenDigitSequence) || (Entities.isNamedEntity(consumeLetterThenDigitSequence) && matches))) {
                this.reader.rewindToMark();
                if (matches) {
                    characterReferenceError(String.format("invalid named referenece '%s'", consumeLetterThenDigitSequence));
                }
                return null;
            } else if (z && (this.reader.matchesLetter() || this.reader.matchesDigit() || this.reader.matchesAny('=', '-', '_'))) {
                this.reader.rewindToMark();
                return null;
            } else {
                if (!this.reader.matchConsume(i.b)) {
                    characterReferenceError("missing semicolon");
                }
                cArr[0] = Entities.getCharacterByName(consumeLetterThenDigitSequence).charValue();
                return cArr;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token.Tag createTagPending(boolean z) {
        this.tagPending = z ? this.startPending.reset() : this.endPending.reset();
        return this.tagPending;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void emitTagPending() {
        this.tagPending.finaliseTag();
        emit(this.tagPending);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void createCommentPending() {
        this.commentPending.reset();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void emitCommentPending() {
        emit(this.commentPending);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void createDoctypePending() {
        this.doctypePending.reset();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void emitDoctypePending() {
        emit(this.doctypePending);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void createTempBuffer() {
        Token.reset(this.dataBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAppropriateEndTagToken() {
        return this.lastStartTag != null && this.tagPending.tagName.equals(this.lastStartTag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String appropriateEndTagName() {
        if (this.lastStartTag == null) {
            return null;
        }
        return this.lastStartTag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void error(TokeniserState tokeniserState) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader.pos(), "Unexpected character '%s' in input state [%s]", Character.valueOf(this.reader.current()), tokeniserState));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void eofError(TokeniserState tokeniserState) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader.pos(), "Unexpectedly reached end of file (EOF) in input state [%s]", tokeniserState));
        }
    }

    private void characterReferenceError(String str) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader.pos(), "Invalid character reference: %s", str));
        }
    }

    private void error(String str) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader.pos(), str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String unescapeEntities(boolean z) {
        StringBuilder sb = new StringBuilder();
        while (!this.reader.isEmpty()) {
            sb.append(this.reader.consumeTo('&'));
            if (this.reader.matches('&')) {
                this.reader.consume();
                char[] consumeCharacterReference = consumeCharacterReference(null, z);
                if (consumeCharacterReference == null || consumeCharacterReference.length == 0) {
                    sb.append('&');
                } else {
                    sb.append(consumeCharacterReference);
                }
            }
        }
        return sb.toString();
    }
}