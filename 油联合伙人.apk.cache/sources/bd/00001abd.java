package com.yltx.oil.partner.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.IOException;
import java.util.HashMap;

/* loaded from: classes.dex */
public class OfflineResource {
    private static final String SAMPLE_DIR = "baiduTTS";
    public static final String VOICE_DUXY = "X";
    public static final String VOICE_DUYY = "Y";
    public static final String VOICE_FEMALE = "F";
    public static final String VOICE_MALE = "M";
    private static HashMap<String, Boolean> mapInitied = new HashMap<>();
    private AssetManager assets;
    private String destPath;
    private String modelFilename;
    private String textFilename;

    public OfflineResource(Context context, String str) throws IOException {
        Context applicationContext = context.getApplicationContext();
        this.assets = applicationContext.getApplicationContext().getAssets();
        this.destPath = FileUtil.createTmpDir(applicationContext);
        setOfflineVoiceType(str);
    }

    public String getModelFilename() {
        return this.modelFilename;
    }

    public String getTextFilename() {
        return this.textFilename;
    }

    public void setOfflineVoiceType(String str) throws IOException {
        String str2;
        if (VOICE_MALE.equals(str)) {
            str2 = "bd_etts_common_speech_m15_mand_eng_high_am-mix_v3.0.0_20170505.dat";
        } else if (VOICE_FEMALE.equals(str)) {
            str2 = "bd_etts_common_speech_f7_mand_eng_high_am-mix_v3.0.0_20170512.dat";
        } else if (VOICE_DUXY.equals(str)) {
            str2 = "bd_etts_common_speech_yyjw_mand_eng_high_am-mix_v3.0.0_20170512.dat";
        } else if (!VOICE_DUYY.equals(str)) {
            throw new RuntimeException("voice type is not in list");
        } else {
            str2 = "bd_etts_common_speech_as_mand_eng_high_am_v3.0.0_20170516.dat";
        }
        this.textFilename = copyAssetsFile("bd_etts_text.dat");
        this.modelFilename = copyAssetsFile(str2);
    }

    private String copyAssetsFile(String str) throws IOException {
        String str2 = this.destPath + "/" + str;
        Boolean bool = mapInitied.get(str);
        FileUtil.copyFromAssets(this.assets, str, str2, bool == null || !bool.booleanValue());
        Log.i("ContentValues", "文件复制成功：" + str2);
        return str2;
    }
}