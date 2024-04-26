package org.jsoup.nodes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import org.jsoup.SerializationException;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

/* loaded from: classes.dex */
public class Entities {
    private static final Object[][] xhtmlArray = {new Object[]{"quot", 34}, new Object[]{"amp", 38}, new Object[]{"lt", 60}, new Object[]{"gt", 62}};
    private static final Map<Character, String> xhtmlByVal = new HashMap();
    private static final Map<String, Character> base = loadEntities("entities-base.properties");
    private static final Map<Character, String> baseByVal = toCharacterKey(base);
    private static final Map<String, Character> full = loadEntities("entities-full.properties");
    private static final Map<Character, String> fullByVal = toCharacterKey(full);

    /* loaded from: classes.dex */
    public enum EscapeMode {
        xhtml(Entities.xhtmlByVal),
        base(Entities.baseByVal),
        extended(Entities.fullByVal);
        
        private Map<Character, String> map;

        EscapeMode(Map map) {
            this.map = map;
        }

        public Map<Character, String> getMap() {
            return this.map;
        }
    }

    private Entities() {
    }

    public static boolean isNamedEntity(String str) {
        return full.containsKey(str);
    }

    public static boolean isBaseNamedEntity(String str) {
        return base.containsKey(str);
    }

    public static Character getCharacterByName(String str) {
        return full.get(str);
    }

    static String escape(String str, Document.OutputSettings outputSettings) {
        StringBuilder sb = new StringBuilder(str.length() * 2);
        try {
            escape(sb, str, outputSettings, false, false, false);
            return sb.toString();
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void escape(Appendable appendable, String str, Document.OutputSettings outputSettings, boolean z, boolean z2, boolean z3) throws IOException {
        CoreCharset coreCharset;
        EscapeMode escapeMode = outputSettings.escapeMode();
        CharsetEncoder encoder = outputSettings.encoder();
        CoreCharset byName = CoreCharset.byName(encoder.charset().name());
        Map<Character, String> map = escapeMode.getMap();
        int length = str.length();
        int i = 0;
        boolean z4 = false;
        boolean z5 = false;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            boolean z6 = true;
            if (!z2) {
                z6 = z4;
            } else if (StringUtil.isWhitespace(codePointAt)) {
                if ((!z3 || z4) && !z5) {
                    appendable.append(' ');
                    coreCharset = byName;
                    z5 = true;
                } else {
                    coreCharset = byName;
                }
                i += Character.charCount(codePointAt);
                byName = coreCharset;
            } else {
                z5 = false;
            }
            if (codePointAt < 65536) {
                char c = (char) codePointAt;
                if (c == '\"') {
                    coreCharset = byName;
                    if (z) {
                        appendable.append("&quot;");
                    } else {
                        appendable.append(c);
                    }
                } else if (c == '&') {
                    coreCharset = byName;
                    appendable.append("&amp;");
                } else if (c == '<') {
                    coreCharset = byName;
                    if (!z || escapeMode == EscapeMode.xhtml) {
                        appendable.append("&lt;");
                    } else {
                        appendable.append(c);
                    }
                } else if (c == '>') {
                    coreCharset = byName;
                    if (!z) {
                        appendable.append("&gt;");
                    } else {
                        appendable.append(c);
                    }
                } else if (c == 160) {
                    coreCharset = byName;
                    if (escapeMode != EscapeMode.xhtml) {
                        appendable.append("&nbsp;");
                    } else {
                        appendable.append("&#xa0;");
                    }
                } else {
                    if (canEncode(byName, c, encoder)) {
                        appendable.append(c);
                    } else if (map.containsKey(Character.valueOf(c))) {
                        appendable.append('&').append(map.get(Character.valueOf(c))).append(';');
                    } else {
                        coreCharset = byName;
                        appendable.append("&#x").append(Integer.toHexString(codePointAt)).append(';');
                    }
                    coreCharset = byName;
                }
            } else {
                coreCharset = byName;
                String str2 = new String(Character.toChars(codePointAt));
                if (encoder.canEncode(str2)) {
                    appendable.append(str2);
                } else {
                    appendable.append("&#x").append(Integer.toHexString(codePointAt)).append(';');
                }
            }
            z4 = z6;
            i += Character.charCount(codePointAt);
            byName = coreCharset;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String unescape(String str) {
        return unescape(str, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String unescape(String str, boolean z) {
        return Parser.unescapeEntities(str, z);
    }

    private static boolean canEncode(CoreCharset coreCharset, char c, CharsetEncoder charsetEncoder) {
        switch (coreCharset) {
            case ascii:
                return c < 128;
            case utf:
                return true;
            default:
                return charsetEncoder.canEncode(c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum CoreCharset {
        ascii,
        utf,
        fallback;

        /* JADX INFO: Access modifiers changed from: private */
        public static CoreCharset byName(String str) {
            if (str.equals("US-ASCII")) {
                return ascii;
            }
            if (str.startsWith("UTF-")) {
                return utf;
            }
            return fallback;
        }
    }

    static {
        Object[][] objArr;
        for (Object[] objArr2 : xhtmlArray) {
            xhtmlByVal.put(Character.valueOf((char) ((Integer) objArr2[1]).intValue()), (String) objArr2[0]);
        }
    }

    private static Map<String, Character> loadEntities(String str) {
        Properties properties = new Properties();
        HashMap hashMap = new HashMap();
        try {
            InputStream resourceAsStream = Entities.class.getResourceAsStream(str);
            properties.load(resourceAsStream);
            resourceAsStream.close();
            for (Map.Entry entry : properties.entrySet()) {
                hashMap.put((String) entry.getKey(), Character.valueOf((char) Integer.parseInt((String) entry.getValue(), 16)));
            }
            return hashMap;
        } catch (IOException e) {
            throw new MissingResourceException("Error loading entities resource: " + e.getMessage(), "Entities", str);
        }
    }

    private static Map<Character, String> toCharacterKey(Map<String, Character> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Character> entry : map.entrySet()) {
            Character value = entry.getValue();
            String key = entry.getKey();
            if (hashMap.containsKey(value)) {
                if (key.toLowerCase().equals(key)) {
                    hashMap.put(value, key);
                }
            } else {
                hashMap.put(value, key);
            }
        }
        return hashMap;
    }
}