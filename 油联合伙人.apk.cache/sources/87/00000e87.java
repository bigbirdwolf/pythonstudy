package com.github.lzyzsd.jsbridge;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Message {
    private static final String CALLBACK_ID_STR = "callbackId";
    private static final String DATA_STR = "data";
    private static final String HANDLER_NAME_STR = "handlerName";
    private static final String RESPONSE_DATA_STR = "responseData";
    private static final String RESPONSE_ID_STR = "responseId";
    private String callbackId;
    private String data;
    private String handlerName;
    private String responseData;
    private String responseId;

    public String getResponseId() {
        return this.responseId;
    }

    public void setResponseId(String str) {
        this.responseId = str;
    }

    public String getResponseData() {
        return this.responseData;
    }

    public void setResponseData(String str) {
        this.responseData = str;
    }

    public String getCallbackId() {
        return this.callbackId;
    }

    public void setCallbackId(String str) {
        this.callbackId = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public String getHandlerName() {
        return this.handlerName;
    }

    public void setHandlerName(String str) {
        this.handlerName = str;
    }

    public String toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CALLBACK_ID_STR, getCallbackId());
            jSONObject.put("data", getData());
            jSONObject.put(HANDLER_NAME_STR, getHandlerName());
            jSONObject.put(RESPONSE_DATA_STR, getResponseData());
            jSONObject.put(RESPONSE_ID_STR, getResponseId());
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Message toObject(String str) {
        Message message = new Message();
        try {
            JSONObject jSONObject = new JSONObject(str);
            message.setHandlerName(jSONObject.has(HANDLER_NAME_STR) ? jSONObject.getString(HANDLER_NAME_STR) : null);
            message.setCallbackId(jSONObject.has(CALLBACK_ID_STR) ? jSONObject.getString(CALLBACK_ID_STR) : null);
            message.setResponseData(jSONObject.has(RESPONSE_DATA_STR) ? jSONObject.getString(RESPONSE_DATA_STR) : null);
            message.setResponseId(jSONObject.has(RESPONSE_ID_STR) ? jSONObject.getString(RESPONSE_ID_STR) : null);
            message.setData(jSONObject.has("data") ? jSONObject.getString("data") : null);
            return message;
        } catch (JSONException e) {
            e.printStackTrace();
            return message;
        }
    }

    public static List<Message> toArrayList(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                Message message = new Message();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                message.setHandlerName(jSONObject.has(HANDLER_NAME_STR) ? jSONObject.getString(HANDLER_NAME_STR) : null);
                message.setCallbackId(jSONObject.has(CALLBACK_ID_STR) ? jSONObject.getString(CALLBACK_ID_STR) : null);
                message.setResponseData(jSONObject.has(RESPONSE_DATA_STR) ? jSONObject.getString(RESPONSE_DATA_STR) : null);
                message.setResponseId(jSONObject.has(RESPONSE_ID_STR) ? jSONObject.getString(RESPONSE_ID_STR) : null);
                message.setData(jSONObject.has("data") ? jSONObject.getString("data") : null);
                arrayList.add(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}