package org.jsoup.parser;

import java.util.Arrays;
import java.util.Locale;
import org.jsoup.helper.Validate;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class CharacterReader {
    static final char EOF = 65535;
    private static final int maxCacheLen = 12;
    private final char[] input;
    private final int length;
    private int pos = 0;
    private int mark = 0;
    private final String[] stringCache = new String[512];

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharacterReader(String str) {
        Validate.notNull(str);
        this.input = str.toCharArray();
        this.length = this.input.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int pos() {
        return this.pos;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEmpty() {
        return this.pos >= this.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char current() {
        return this.pos >= this.length ? EOF : this.input[this.pos];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char consume() {
        char c = this.pos >= this.length ? EOF : this.input[this.pos];
        this.pos++;
        return c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unconsume() {
        this.pos--;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void advance() {
        this.pos++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void mark() {
        this.mark = this.pos;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void rewindToMark() {
        this.pos = this.mark;
    }

    String consumeAsString() {
        char[] cArr = this.input;
        int i = this.pos;
        this.pos = i + 1;
        return new String(cArr, i, 1);
    }

    int nextIndexOf(char c) {
        for (int i = this.pos; i < this.length; i++) {
            if (c == this.input[i]) {
                return i - this.pos;
            }
        }
        return -1;
    }

    int nextIndexOf(CharSequence charSequence) {
        char charAt = charSequence.charAt(0);
        int i = this.pos;
        while (i < this.length) {
            if (charAt != this.input[i]) {
                do {
                    i++;
                    if (i >= this.length) {
                        break;
                    }
                } while (charAt != this.input[i]);
            }
            int i2 = i + 1;
            int length = (charSequence.length() + i2) - 1;
            if (i < this.length && length <= this.length) {
                int i3 = i2;
                for (int i4 = 1; i3 < length && charSequence.charAt(i4) == this.input[i3]; i4++) {
                    i3++;
                }
                if (i3 == length) {
                    return i - this.pos;
                }
            }
            i = i2;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeTo(char c) {
        int nextIndexOf = nextIndexOf(c);
        if (nextIndexOf != -1) {
            String cacheString = cacheString(this.pos, nextIndexOf);
            this.pos += nextIndexOf;
            return cacheString;
        }
        return consumeToEnd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeTo(String str) {
        int nextIndexOf = nextIndexOf(str);
        if (nextIndexOf != -1) {
            String cacheString = cacheString(this.pos, nextIndexOf);
            this.pos += nextIndexOf;
            return cacheString;
        }
        return consumeToEnd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeToAny(char... cArr) {
        int i = this.pos;
        int i2 = this.length;
        char[] cArr2 = this.input;
        loop0: while (this.pos < i2) {
            for (char c : cArr) {
                if (cArr2[this.pos] == c) {
                    break loop0;
                }
            }
            this.pos++;
        }
        return this.pos > i ? cacheString(i, this.pos - i) : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeToAnySorted(char... cArr) {
        int i = this.pos;
        int i2 = this.length;
        char[] cArr2 = this.input;
        while (this.pos < i2 && Arrays.binarySearch(cArr, cArr2[this.pos]) < 0) {
            this.pos++;
        }
        return this.pos > i ? cacheString(i, this.pos - i) : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeData() {
        char c;
        int i = this.pos;
        int i2 = this.length;
        char[] cArr = this.input;
        while (this.pos < i2 && (c = cArr[this.pos]) != '&' && c != '<' && c != 0) {
            this.pos++;
        }
        return this.pos > i ? cacheString(i, this.pos - i) : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeTagName() {
        char c;
        int i = this.pos;
        int i2 = this.length;
        char[] cArr = this.input;
        while (this.pos < i2 && (c = cArr[this.pos]) != '\t' && c != '\n' && c != '\r' && c != '\f' && c != ' ' && c != '/' && c != '>' && c != 0) {
            this.pos++;
        }
        return this.pos > i ? cacheString(i, this.pos - i) : "";
    }

    String consumeToEnd() {
        String cacheString = cacheString(this.pos, this.length - this.pos);
        this.pos = this.length;
        return cacheString;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeLetterSequence() {
        char c;
        int i = this.pos;
        while (this.pos < this.length && (((c = this.input[this.pos]) >= 'A' && c <= 'Z') || ((c >= 'a' && c <= 'z') || Character.isLetter(c)))) {
            this.pos++;
        }
        return cacheString(i, this.pos - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeLetterThenDigitSequence() {
        char c;
        char c2;
        int i = this.pos;
        while (this.pos < this.length && (((c2 = this.input[this.pos]) >= 'A' && c2 <= 'Z') || ((c2 >= 'a' && c2 <= 'z') || Character.isLetter(c2)))) {
            this.pos++;
        }
        while (!isEmpty() && (c = this.input[this.pos]) >= '0' && c <= '9') {
            this.pos++;
        }
        return cacheString(i, this.pos - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeHexSequence() {
        char c;
        int i = this.pos;
        while (this.pos < this.length && (((c = this.input[this.pos]) >= '0' && c <= '9') || ((c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f')))) {
            this.pos++;
        }
        return cacheString(i, this.pos - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String consumeDigitSequence() {
        char c;
        int i = this.pos;
        while (this.pos < this.length && (c = this.input[this.pos]) >= '0' && c <= '9') {
            this.pos++;
        }
        return cacheString(i, this.pos - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matches(char c) {
        return !isEmpty() && this.input[this.pos] == c;
    }

    boolean matches(String str) {
        int length = str.length();
        if (length > this.length - this.pos) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) != this.input[this.pos + i]) {
                return false;
            }
        }
        return true;
    }

    boolean matchesIgnoreCase(String str) {
        int length = str.length();
        if (length > this.length - this.pos) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (Character.toUpperCase(str.charAt(i)) != Character.toUpperCase(this.input[this.pos + i])) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchesAny(char... cArr) {
        if (isEmpty()) {
            return false;
        }
        char c = this.input[this.pos];
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchesAnySorted(char[] cArr) {
        return !isEmpty() && Arrays.binarySearch(cArr, this.input[this.pos]) >= 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchesLetter() {
        if (isEmpty()) {
            return false;
        }
        char c = this.input[this.pos];
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || Character.isLetter(c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchesDigit() {
        char c;
        return !isEmpty() && (c = this.input[this.pos]) >= '0' && c <= '9';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchConsume(String str) {
        if (matches(str)) {
            this.pos += str.length();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchConsumeIgnoreCase(String str) {
        if (matchesIgnoreCase(str)) {
            this.pos += str.length();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean containsIgnoreCase(String str) {
        return nextIndexOf(str.toLowerCase(Locale.ENGLISH)) > -1 || nextIndexOf(str.toUpperCase(Locale.ENGLISH)) > -1;
    }

    public String toString() {
        return new String(this.input, this.pos, this.length - this.pos);
    }

    private String cacheString(int i, int i2) {
        char[] cArr = this.input;
        String[] strArr = this.stringCache;
        if (i2 > 12) {
            return new String(cArr, i, i2);
        }
        int i3 = 0;
        int i4 = i;
        int i5 = 0;
        while (i3 < i2) {
            i5 = (i5 * 31) + cArr[i4];
            i3++;
            i4++;
        }
        int length = (strArr.length - 1) & i5;
        String str = strArr[length];
        if (str == null) {
            String str2 = new String(cArr, i, i2);
            strArr[length] = str2;
            return str2;
        } else if (rangeEquals(i, i2, str)) {
            return str;
        } else {
            String str3 = new String(cArr, i, i2);
            strArr[length] = str3;
            return str3;
        }
    }

    boolean rangeEquals(int i, int i2, String str) {
        if (i2 != str.length()) {
            return false;
        }
        char[] cArr = this.input;
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i2 == 0) {
                return true;
            }
            int i5 = i + 1;
            int i6 = i3 + 1;
            if (cArr[i] != str.charAt(i3)) {
                return false;
            }
            i = i5;
            i2 = i4;
            i3 = i6;
        }
    }
}