package com.facebook.stetho.inspector.protocol.module;

import android.annotation.SuppressLint;
import com.facebook.stetho.inspector.console.ConsolePeerManager;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.annotation.JsonProperty;
import com.facebook.stetho.json.annotation.JsonValue;
import com.umeng.analytics.pro.b;
import com.yltx.oil.partner.oss.OSSFileHelper;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Console implements ChromeDevtoolsDomain {

    @SuppressLint({"UsingDefaultJsonDeserializer", "EmptyJsonPropertyUse"})
    /* loaded from: classes.dex */
    public static class ConsoleMessage {
        @JsonProperty(required = true)
        public MessageLevel level;
        @JsonProperty(required = true)
        public MessageSource source;
        @JsonProperty(required = true)
        public String text;
    }

    @SuppressLint({"UsingDefaultJsonDeserializer", "EmptyJsonPropertyUse"})
    /* loaded from: classes.dex */
    public static class MessageAddedRequest {
        @JsonProperty(required = true)
        public ConsoleMessage message;
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        ConsolePeerManager.getOrCreateInstance().addPeer(jsonRpcPeer);
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        ConsolePeerManager.getOrCreateInstance().removePeer(jsonRpcPeer);
    }

    /* loaded from: classes.dex */
    public enum MessageSource {
        XML("xml"),
        JAVASCRIPT("javascript"),
        NETWORK("network"),
        CONSOLE_API("console-api"),
        STORAGE("storage"),
        APPCACHE("appcache"),
        RENDERING("rendering"),
        CSS("css"),
        SECURITY("security"),
        OTHER(OSSFileHelper.OSS_OTHER_FILE_NAME);
        
        private final String mProtocolValue;

        MessageSource(String str) {
            this.mProtocolValue = str;
        }

        @JsonValue
        public String getProtocolValue() {
            return this.mProtocolValue;
        }
    }

    /* loaded from: classes.dex */
    public enum MessageLevel {
        LOG("log"),
        WARNING("warning"),
        ERROR(b.N),
        DEBUG("debug");
        
        private final String mProtocolValue;

        MessageLevel(String str) {
            this.mProtocolValue = str;
        }

        @JsonValue
        public String getProtocolValue() {
            return this.mProtocolValue;
        }
    }

    @SuppressLint({"UsingDefaultJsonDeserializer", "EmptyJsonPropertyUse"})
    /* loaded from: classes.dex */
    public static class CallFrame {
        @JsonProperty(required = true)
        public int columnNumber;
        @JsonProperty(required = true)
        public String functionName;
        @JsonProperty(required = true)
        public int lineNumber;
        @JsonProperty(required = true)
        public String url;

        public CallFrame() {
        }

        public CallFrame(String str, String str2, int i, int i2) {
            this.functionName = str;
            this.url = str2;
            this.lineNumber = i;
            this.columnNumber = i2;
        }
    }
}