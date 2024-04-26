package com.alipay.sdk.util;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b {
    public static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject[] jSONObjectArr;
        JSONObject jSONObject3 = new JSONObject();
        try {
            for (JSONObject jSONObject4 : new JSONObject[]{jSONObject, jSONObject2}) {
                if (jSONObject4 != null) {
                    Iterator<String> keys = jSONObject4.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        jSONObject3.put(next, jSONObject4.get(next));
                    }
                }
            }
        } catch (JSONException e) {
            c.a(e);
        }
        return jSONObject3;
    }
}