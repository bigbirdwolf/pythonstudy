package com.umeng.socialize.b.b;

import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.UmengText;
import com.yltx.oil.partner.utils.CommonUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/* compiled from: ImageFormat.java */
/* loaded from: classes.dex */
public class d {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final int k = 10;
    public static final int l = 11;
    public static final String[] m = {"jpeg", "gif", CommonUtils.SUFFIX_IMAGE_FILE, "bmp", "pcx", "iff", "ras", "pbm", "pgm", "ppm", "psd", "swf"};

    public static String a(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    int read = byteArrayInputStream.read();
                    int read2 = byteArrayInputStream.read();
                    if (read == 71 && read2 == 73) {
                        String str = m[1];
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e2) {
                            SLog.error(UmengText.IMAGE.CLOSE, e2);
                        }
                        return str;
                    } else if (read == 137 && read2 == 80) {
                        String str2 = m[2];
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e3) {
                            SLog.error(UmengText.IMAGE.CLOSE, e3);
                        }
                        return str2;
                    } else if (read == 255 && read2 == 216) {
                        String str3 = m[0];
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e4) {
                            SLog.error(UmengText.IMAGE.CLOSE, e4);
                        }
                        return str3;
                    } else if (read == 66 && read2 == 77) {
                        String str4 = m[3];
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e5) {
                            SLog.error(UmengText.IMAGE.CLOSE, e5);
                        }
                        return str4;
                    } else if (read == 10 && read2 < 6) {
                        String str5 = m[4];
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e6) {
                            SLog.error(UmengText.IMAGE.CLOSE, e6);
                        }
                        return str5;
                    } else if (read == 70 && read2 == 79) {
                        String str6 = m[5];
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e7) {
                            SLog.error(UmengText.IMAGE.CLOSE, e7);
                        }
                        return str6;
                    } else if (read == 89 && read2 == 166) {
                        String str7 = m[6];
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e8) {
                            SLog.error(UmengText.IMAGE.CLOSE, e8);
                        }
                        return str7;
                    } else if (read == 80 && read2 >= 49 && read2 <= 54) {
                        int i2 = read2 - 48;
                        if (i2 < 1 || i2 > 6) {
                            try {
                                byteArrayInputStream.close();
                            } catch (IOException e9) {
                                SLog.error(UmengText.IMAGE.CLOSE, e9);
                            }
                            return "";
                        }
                        String str8 = m[new int[]{7, 8, 9}[(i2 - 1) % 3]];
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e10) {
                            SLog.error(UmengText.IMAGE.CLOSE, e10);
                        }
                        return str8;
                    } else if (read == 56 && read2 == 66) {
                        String str9 = m[10];
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e11) {
                            SLog.error(UmengText.IMAGE.CLOSE, e11);
                        }
                        return str9;
                    } else if (read != 70 || read2 != 87) {
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e12) {
                            SLog.error(UmengText.IMAGE.CLOSE, e12);
                        }
                        return "";
                    } else {
                        String str10 = m[11];
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e13) {
                            SLog.error(UmengText.IMAGE.CLOSE, e13);
                        }
                        return str10;
                    }
                } catch (Exception e14) {
                    e = e14;
                    byteArrayInputStream2 = byteArrayInputStream;
                    SLog.error(UmengText.IMAGE.CHECK_FORMAT_ERROR, e);
                    if (byteArrayInputStream2 != null) {
                        try {
                            byteArrayInputStream2.close();
                        } catch (IOException e15) {
                            SLog.error(UmengText.IMAGE.CLOSE, e15);
                        }
                    }
                    return "";
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e16) {
                            SLog.error(UmengText.IMAGE.CLOSE, e16);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                byteArrayInputStream = byteArrayInputStream2;
            }
        } catch (Exception e17) {
            e = e17;
        }
    }
}