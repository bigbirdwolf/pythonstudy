package com.alibaba.sdk.android.oss.common.auth;

/* loaded from: classes.dex */
public abstract class OSSCustomSignerCredentialProvider extends OSSCredentialProvider {
    public abstract String signContent(String str);
}