package com.facebook.stetho.inspector.protocol.module;

import android.content.Context;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.network.AsyncPrettyPrinterInitializer;
import com.facebook.stetho.inspector.network.NetworkPeerManager;
import com.facebook.stetho.inspector.network.ResponseBodyData;
import com.facebook.stetho.inspector.network.ResponseBodyFileManager;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.inspector.protocol.module.Console;
import com.facebook.stetho.inspector.protocol.module.Page;
import com.facebook.stetho.json.annotation.JsonProperty;
import com.facebook.stetho.json.annotation.JsonValue;
import com.yltx.oil.partner.oss.OSSFileHelper;
import java.io.IOException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Network implements ChromeDevtoolsDomain {
    private final NetworkPeerManager mNetworkPeerManager;
    private final ResponseBodyFileManager mResponseBodyFileManager;

    /* loaded from: classes.dex */
    public static class DataReceivedParams {
        @JsonProperty(required = true)
        public int dataLength;
        @JsonProperty(required = true)
        public int encodedDataLength;
        @JsonProperty(required = true)
        public String requestId;
        @JsonProperty(required = true)
        public double timestamp;
    }

    /* loaded from: classes.dex */
    public static class Initiator {
        @JsonProperty
        public List<Console.CallFrame> stackTrace;
        @JsonProperty(required = true)
        public InitiatorType type;
    }

    /* loaded from: classes.dex */
    public static class LoadingFailedParams {
        @JsonProperty(required = true)
        public String errorText;
        @JsonProperty(required = true)
        public String requestId;
        @JsonProperty(required = true)
        public double timestamp;
        @JsonProperty
        public Page.ResourceType type;
    }

    /* loaded from: classes.dex */
    public static class LoadingFinishedParams {
        @JsonProperty(required = true)
        public String requestId;
        @JsonProperty(required = true)
        public double timestamp;
    }

    /* loaded from: classes.dex */
    public static class Request {
        @JsonProperty(required = true)
        public JSONObject headers;
        @JsonProperty(required = true)
        public String method;
        @JsonProperty
        public String postData;
        @JsonProperty(required = true)
        public String url;
    }

    /* loaded from: classes.dex */
    public static class RequestWillBeSentParams {
        @JsonProperty(required = true)
        public String documentURL;
        @JsonProperty(required = true)
        public String frameId;
        @JsonProperty(required = true)
        public Initiator initiator;
        @JsonProperty(required = true)
        public String loaderId;
        @JsonProperty
        public Response redirectResponse;
        @JsonProperty(required = true)
        public Request request;
        @JsonProperty(required = true)
        public String requestId;
        @JsonProperty(required = true)
        public double timestamp;
        @JsonProperty
        public Page.ResourceType type;
    }

    /* loaded from: classes.dex */
    public static class ResourceTiming {
        @JsonProperty(required = true)
        public double connectionEnd;
        @JsonProperty(required = true)
        public double connectionStart;
        @JsonProperty(required = true)
        public double dnsEnd;
        @JsonProperty(required = true)
        public double dnsStart;
        @JsonProperty(required = true)
        public double proxyEnd;
        @JsonProperty(required = true)
        public double proxyStart;
        @JsonProperty(required = true)
        public double receivedHeadersEnd;
        @JsonProperty(required = true)
        public double requestTime;
        @JsonProperty(required = true)
        public double sendEnd;
        @JsonProperty(required = true)
        public double sendStart;
        @JsonProperty(required = true)
        public double sslEnd;
        @JsonProperty(required = true)
        public double sslStart;
    }

    /* loaded from: classes.dex */
    public static class Response {
        @JsonProperty(required = true)
        public int connectionId;
        @JsonProperty(required = true)
        public boolean connectionReused;
        @JsonProperty(required = true)
        public Boolean fromDiskCache;
        @JsonProperty(required = true)
        public JSONObject headers;
        @JsonProperty
        public String headersText;
        @JsonProperty(required = true)
        public String mimeType;
        @JsonProperty
        public JSONObject requestHeaders;
        @JsonProperty
        public String requestHeadersTest;
        @JsonProperty(required = true)
        public int status;
        @JsonProperty(required = true)
        public String statusText;
        @JsonProperty
        public ResourceTiming timing;
        @JsonProperty(required = true)
        public String url;
    }

    /* loaded from: classes.dex */
    public static class ResponseReceivedParams {
        @JsonProperty(required = true)
        public String frameId;
        @JsonProperty(required = true)
        public String loaderId;
        @JsonProperty(required = true)
        public String requestId;
        @JsonProperty(required = true)
        public Response response;
        @JsonProperty(required = true)
        public double timestamp;
        @JsonProperty(required = true)
        public Page.ResourceType type;
    }

    @ChromeDevtoolsMethod
    public void setUserAgentOverride(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
    }

    public Network(Context context) {
        this.mNetworkPeerManager = NetworkPeerManager.getOrCreateInstance(context);
        this.mResponseBodyFileManager = this.mNetworkPeerManager.getResponseBodyFileManager();
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        this.mNetworkPeerManager.addPeer(jsonRpcPeer);
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        this.mNetworkPeerManager.removePeer(jsonRpcPeer);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getResponseBody(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) throws JsonRpcException {
        try {
            return readResponseBody(jSONObject.getString("requestId"));
        } catch (IOException e) {
            throw new JsonRpcException(new JsonRpcError(JsonRpcError.ErrorCode.INTERNAL_ERROR, e.toString(), null));
        } catch (JSONException e2) {
            throw new JsonRpcException(new JsonRpcError(JsonRpcError.ErrorCode.INTERNAL_ERROR, e2.toString(), null));
        }
    }

    private GetResponseBodyResponse readResponseBody(String str) throws IOException, JsonRpcException {
        GetResponseBodyResponse getResponseBodyResponse = new GetResponseBodyResponse();
        try {
            ResponseBodyData readFile = this.mResponseBodyFileManager.readFile(str);
            getResponseBodyResponse.body = readFile.data;
            getResponseBodyResponse.base64Encoded = readFile.base64Encoded;
            return getResponseBodyResponse;
        } catch (OutOfMemoryError e) {
            throw new JsonRpcException(new JsonRpcError(JsonRpcError.ErrorCode.INTERNAL_ERROR, e.toString(), null));
        }
    }

    public void setPrettyPrinterInitializer(AsyncPrettyPrinterInitializer asyncPrettyPrinterInitializer) {
        Util.throwIfNull(asyncPrettyPrinterInitializer);
        this.mNetworkPeerManager.setPrettyPrinterInitializer(asyncPrettyPrinterInitializer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class GetResponseBodyResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public boolean base64Encoded;
        @JsonProperty(required = true)
        public String body;

        private GetResponseBodyResponse() {
        }
    }

    /* loaded from: classes.dex */
    public enum InitiatorType {
        PARSER("parser"),
        SCRIPT("script"),
        OTHER(OSSFileHelper.OSS_OTHER_FILE_NAME);
        
        private final String mProtocolValue;

        InitiatorType(String str) {
            this.mProtocolValue = str;
        }

        @JsonValue
        public String getProtocolValue() {
            return this.mProtocolValue;
        }
    }
}