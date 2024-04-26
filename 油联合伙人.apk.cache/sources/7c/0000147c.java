package com.umeng.socialize.net;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.net.utils.URequest;
import com.umeng.socialize.utils.SocializeUtils;

/* loaded from: classes.dex */
public class ActionBarRequest extends SocializeRequest {
    private static final String a = "/bar/get/";
    private static final int b = 1;
    private int c;

    public ActionBarRequest(Context context, boolean z) {
        super(context, "", ActionBarResponse.class, 1, URequest.RequestMethod.GET);
        this.c = 0;
        this.mContext = context;
        this.c = z ? 1 : 0;
        this.mMethod = URequest.RequestMethod.GET;
    }

    @Override // com.umeng.socialize.net.base.SocializeRequest, com.umeng.socialize.net.utils.URequest
    public void onPrepareRequest() {
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_DESCRIPTOR, Config.Descriptor);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_NEW_INSTALL, String.valueOf(this.c));
        if (TextUtils.isEmpty(Config.EntityName)) {
            return;
        }
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_ENTITY_NAME, Config.EntityName);
    }

    @Override // com.umeng.socialize.net.base.SocializeRequest
    protected String getPath() {
        return a + SocializeUtils.getAppkey(this.mContext) + "/";
    }
}