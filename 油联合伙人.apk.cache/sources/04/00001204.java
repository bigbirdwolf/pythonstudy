package com.tencent.mm.opensdk.channel.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.d;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: com.tencent.mm.opensdk.channel.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0017a {
        public String a;
        public String action;
        public long b;
        public Bundle bundle;
        public String content;
    }

    public static boolean a(Context context, C0017a c0017a) {
        String str;
        String str2;
        if (context == null) {
            str = "MicroMsg.SDK.MMessage";
            str2 = "send fail, invalid argument";
        } else if (!d.a(c0017a.action)) {
            String str3 = null;
            if (!d.a(c0017a.a)) {
                str3 = c0017a.a + ".permission.MM_MESSAGE";
            }
            Intent intent = new Intent(c0017a.action);
            if (c0017a.bundle != null) {
                intent.putExtras(c0017a.bundle);
            }
            String packageName = context.getPackageName();
            intent.putExtra(ConstantsAPI.SDK_VERSION, 620823552);
            intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
            intent.putExtra(ConstantsAPI.CONTENT, c0017a.content);
            intent.putExtra(ConstantsAPI.APP_SUPORT_CONTENT_TYPE, c0017a.b);
            intent.putExtra(ConstantsAPI.CHECK_SUM, b.a(c0017a.content, 620823552, packageName));
            context.sendBroadcast(intent, str3);
            Log.d("MicroMsg.SDK.MMessage", "send mm message, intent=" + intent + ", perm=" + str3);
            return true;
        } else {
            str = "MicroMsg.SDK.MMessage";
            str2 = "send fail, action is null";
        }
        Log.e(str, str2);
        return false;
    }
}