package org.jsoup.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Parser;

/* loaded from: classes.dex */
public final class DataUtil {
    private static final int UNICODE_BOM = 65279;
    static final int boundaryLength = 32;
    private static final int bufferSize = 131072;
    static final String defaultCharset = "UTF-8";
    private static final Pattern charsetPattern = Pattern.compile("(?i)\\bcharset=\\s*(?:\"|')?([^\\s,;\"']*)");
    private static final char[] mimeBoundaryChars = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private DataUtil() {
    }

    public static Document load(File file, String str, String str2) throws IOException {
        return parseByteData(readFileToByteBuffer(file), str, str2, Parser.htmlParser());
    }

    public static Document load(InputStream inputStream, String str, String str2) throws IOException {
        return parseByteData(readToByteBuffer(inputStream), str, str2, Parser.htmlParser());
    }

    public static Document load(InputStream inputStream, String str, String str2, Parser parser) throws IOException {
        return parseByteData(readToByteBuffer(inputStream), str, str2, parser);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void crossStreams(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[131072];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Document parseByteData(ByteBuffer byteBuffer, String str, String str2, Parser parser) {
        String charBuffer;
        String str3;
        String detectCharsetFromBom = detectCharsetFromBom(byteBuffer, str);
        Document document = null;
        if (detectCharsetFromBom == null) {
            charBuffer = Charset.forName("UTF-8").decode(byteBuffer).toString();
            Document parseInput = parser.parseInput(charBuffer, str2);
            Element first = parseInput.select("meta[http-equiv=content-type], meta[charset]").first();
            if (first != null) {
                String charsetFromContentType = first.hasAttr("http-equiv") ? getCharsetFromContentType(first.attr("content")) : null;
                str3 = (charsetFromContentType == null && first.hasAttr("charset")) ? first.attr("charset") : charsetFromContentType;
            } else {
                str3 = null;
            }
            if (str3 == null && (parseInput.childNode(0) instanceof XmlDeclaration)) {
                XmlDeclaration xmlDeclaration = (XmlDeclaration) parseInput.childNode(0);
                if (xmlDeclaration.name().equals("xml")) {
                    str3 = xmlDeclaration.attr("encoding");
                }
            }
            String validateCharset = validateCharset(str3);
            if (validateCharset == null || validateCharset.equals("UTF-8")) {
                document = parseInput;
            } else {
                detectCharsetFromBom = validateCharset.trim().replaceAll("[\"']", "");
                byteBuffer.rewind();
                charBuffer = Charset.forName(detectCharsetFromBom).decode(byteBuffer).toString();
            }
        } else {
            Validate.notEmpty(detectCharsetFromBom, "Must set charset arg to character set of file to parse. Set to null to attempt to detect from HTML");
            charBuffer = Charset.forName(detectCharsetFromBom).decode(byteBuffer).toString();
        }
        if (document == null) {
            Document parseInput2 = parser.parseInput(charBuffer, str2);
            parseInput2.outputSettings().charset(detectCharsetFromBom);
            return parseInput2;
        }
        return document;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteBuffer readToByteBuffer(InputStream inputStream, int i) throws IOException {
        Validate.isTrue(i >= 0, "maxSize must be 0 (unlimited) or larger");
        boolean z = i > 0;
        byte[] bArr = new byte[131072];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(131072);
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            if (z) {
                if (read > i) {
                    byteArrayOutputStream.write(bArr, 0, i);
                    break;
                }
                i -= read;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }

    static ByteBuffer readToByteBuffer(InputStream inputStream) throws IOException {
        return readToByteBuffer(inputStream, 0);
    }

    static ByteBuffer readFileToByteBuffer(File file) throws IOException {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (Throwable th) {
            th = th;
            randomAccessFile = null;
        }
        try {
            byte[] bArr = new byte[(int) randomAccessFile.length()];
            randomAccessFile.readFully(bArr);
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            randomAccessFile.close();
            return wrap;
        } catch (Throwable th2) {
            th = th2;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteBuffer emptyByteBuffer() {
        return ByteBuffer.allocate(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getCharsetFromContentType(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = charsetPattern.matcher(str);
        if (matcher.find()) {
            return validateCharset(matcher.group(1).trim().replace("charset=", ""));
        }
        return null;
    }

    private static String validateCharset(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String replaceAll = str.trim().replaceAll("[\"']", "");
        if (Charset.isSupported(replaceAll)) {
            return replaceAll;
        }
        String upperCase = replaceAll.toUpperCase(Locale.ENGLISH);
        if (Charset.isSupported(upperCase)) {
            return upperCase;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String mimeBoundary() {
        StringBuilder sb = new StringBuilder(32);
        Random random = new Random();
        for (int i = 0; i < 32; i++) {
            sb.append(mimeBoundaryChars[random.nextInt(mimeBoundaryChars.length)]);
        }
        return sb.toString();
    }

    private static String detectCharsetFromBom(ByteBuffer byteBuffer, String str) {
        byteBuffer.mark();
        byte[] bArr = new byte[4];
        if (byteBuffer.remaining() >= bArr.length) {
            byteBuffer.get(bArr);
            byteBuffer.rewind();
        }
        if ((bArr[0] == 0 && bArr[1] == 0 && bArr[2] == -2 && bArr[3] == -1) || (bArr[0] == -1 && bArr[1] == -2 && bArr[2] == 0 && bArr[3] == 0)) {
            return "UTF-32";
        }
        if ((bArr[0] == -2 && bArr[1] == -1) || (bArr[0] == -1 && bArr[1] == -2)) {
            return "UTF-16";
        }
        if (bArr[0] == -17 && bArr[1] == -69 && bArr[2] == -65) {
            byteBuffer.position(3);
            return "UTF-8";
        }
        return str;
    }
}