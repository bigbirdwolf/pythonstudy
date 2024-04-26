package com.alipay.sdk.authjs;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import com.alipay.sdk.authjs.a;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d {
    private c a;
    private Context b;

    public d(Context context, c cVar) {
        this.b = context;
        this.a = cVar;
    }

    public void a(String str) {
        String str2;
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
            str2 = jSONObject.getString(a.d);
        } catch (Exception unused) {
            str2 = null;
        }
        try {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(a.e);
            JSONObject jSONObject3 = jSONObject2 instanceof JSONObject ? jSONObject2 : null;
            String string = jSONObject.getString(a.f);
            String string2 = jSONObject.getString(a.c);
            a aVar = new a("call");
            aVar.b(string2);
            aVar.c(string);
            aVar.a(jSONObject3);
            aVar.a(str2);
            a(aVar);
        } catch (Exception unused2) {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            try {
                a(str2, a.EnumC0005a.RUNTIME_ERROR, true);
            } catch (JSONException unused3) {
            }
        }
    }

    public void a(a aVar) throws JSONException {
        if (aVar == null) {
            return;
        }
        if (TextUtils.isEmpty(aVar.d())) {
            a(aVar.b(), a.EnumC0005a.INVALID_PARAMETER, true);
        } else {
            a(new e(this, aVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, a.EnumC0005a enumC0005a, boolean z) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(com.umeng.analytics.pro.b.N, enumC0005a.ordinal());
        a aVar = new a(a.b);
        aVar.a(jSONObject);
        aVar.a(str);
        if (z) {
            this.a.a(aVar);
        } else {
            a(aVar);
        }
    }

    private static void a(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public a.EnumC0005a b(a aVar) {
        if (aVar != null && "toast".equals(aVar.d())) {
            c(aVar);
        }
        return a.EnumC0005a.NONE_ERROR;
    }

    private void c(a aVar) {
        JSONObject f = aVar.f();
        String optString = f.optString("content");
        int i = f.optInt("duration") < 2500 ? 0 : 1;
        Toast.makeText(this.b, optString, i).show();
        new Timer().schedule(new f(this, aVar), i);
    }
}