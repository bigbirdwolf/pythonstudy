package com.yltx.oil.partner.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import java.util.Map;

/* loaded from: classes.dex */
public class AliPay {
    private static final int SDK_PAY_FLAG = 1;
    private Activity activity;

    public AliPay(Activity activity) {
        this.activity = activity;
    }

    public static void payV2(final Activity activity, final String str, final Handler handler) {
        new Thread(new Runnable() { // from class: com.yltx.oil.partner.utils.AliPay.1
            @Override // java.lang.Runnable
            public void run() {
                Map<String, String> payV2 = new PayTask(activity).payV2(str, true);
                Log.i("msp", payV2.toString());
                Message message = new Message();
                message.what = 1;
                message.obj = payV2;
                handler.sendMessage(message);
            }
        }).start();
    }

    public void getSDKVersion() {
        Toast.makeText(this.activity, new PayTask(this.activity).getVersion(), 0).show();
    }
}