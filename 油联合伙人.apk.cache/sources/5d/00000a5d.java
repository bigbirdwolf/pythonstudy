package com.allenliu.versionchecklib.v2.ui;

import android.content.Context;
import com.allenliu.versionchecklib.R;
import com.allenliu.versionchecklib.core.DownloadManager;
import com.allenliu.versionchecklib.utils.ALog;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import java.io.File;

/* loaded from: classes.dex */
public class BuilderHelper {
    private DownloadBuilder builder;
    private Context context;

    public BuilderHelper(Context context, DownloadBuilder downloadBuilder) {
        this.context = context;
        this.builder = downloadBuilder;
    }

    public void checkAndDeleteAPK() {
        try {
            String str = this.builder.getDownloadAPKPath() + this.context.getString(R.string.versionchecklib_download_apkname, this.context.getPackageName());
            if (DownloadManager.checkAPKIsExists(this.context, str)) {
                return;
            }
            ALog.e("删除本地apk");
            new File(str).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkForceUpdate() {
        if (this.builder.getForceUpdateListener() != null) {
            this.builder.getForceUpdateListener().onShouldForceUpdate();
            AllenVersionChecker.getInstance().cancelAllMission(this.context);
        }
    }
}