package com.umeng.socialize.net.dplus.cache;

import android.text.TextUtils;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.UmengText;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DplueCache {
    public static String getFileName() {
        return "dpluscache";
    }

    public static boolean save(JSONObject jSONObject, File file) throws JSONException, IOException {
        if (file.exists()) {
            String readFile = readFile(file);
            return a(readFile + "," + jSONObject, file);
        }
        file.createNewFile();
        return a(jSONObject.toString(), file);
    }

    private static JSONObject a(JSONObject jSONObject) {
        JSONObject optJSONObject;
        if (jSONObject != null && (optJSONObject = jSONObject.optJSONObject("content")) != null) {
            return optJSONObject.optJSONObject("share");
        }
        return new JSONObject();
    }

    private static boolean a(String str, File file) {
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(ContextUtil.getContext().openFileOutput(file.getName(), 0)));
                try {
                    bufferedWriter2.write(str);
                    try {
                        bufferedWriter2.close();
                    } catch (Exception e) {
                        SLog.error(UmengText.CACHE.CLOSE, e);
                    }
                    return true;
                } catch (Exception e2) {
                    e = e2;
                    bufferedWriter = bufferedWriter2;
                    SLog.error(UmengText.CACHE.CACHEFILE, e);
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Exception e3) {
                            SLog.error(UmengText.CACHE.CLOSE, e3);
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Exception e4) {
                            SLog.error(UmengText.CACHE.CLOSE, e4);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }

    public static String readFile(File file) {
        if (!file.exists()) {
            return "";
        }
        BufferedReader bufferedReader = null;
        try {
            try {
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(ContextUtil.getContext().openFileInput(file.getName())));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } catch (Exception e) {
                        bufferedReader = bufferedReader2;
                        e = e;
                        SLog.error(UmengText.CACHE.CACHEFILE, e);
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                                return "";
                            } catch (Exception e2) {
                                SLog.error(UmengText.CACHE.CACHEFILE, e2);
                                return "";
                            }
                        }
                        return "";
                    } catch (Throwable th) {
                        bufferedReader = bufferedReader2;
                        th = th;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e3) {
                                SLog.error(UmengText.CACHE.CACHEFILE, e3);
                            }
                        }
                        throw th;
                    }
                }
                String sb2 = sb.toString();
                try {
                    bufferedReader2.close();
                } catch (Exception e4) {
                    SLog.error(UmengText.CACHE.CACHEFILE, e4);
                }
                return sb2;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }

    public static boolean deleteFile(File file) {
        return file.delete();
    }

    public static File getFilePath(String str) {
        if (ContextUtil.getContext() == null) {
            return null;
        }
        String packageName = ContextUtil.getContext().getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        String str2 = File.separator + "data" + File.separator + "data" + File.separator + packageName + File.separator + "files" + File.separator + str;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        File file = new File(str2);
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        return null;
    }
}