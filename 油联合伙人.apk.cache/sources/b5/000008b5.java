package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.HttpUtil;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import java.net.URI;

/* loaded from: classes.dex */
public class ObjectURLPresigner {
    private OSSCredentialProvider credentialProvider;
    private URI endpoint;

    public ObjectURLPresigner(URI uri, OSSCredentialProvider oSSCredentialProvider) {
        this.endpoint = uri;
        this.credentialProvider = oSSCredentialProvider;
    }

    public String presignConstrainedURL(String str, String str2, long j) throws ClientException {
        OSSFederationToken oSSFederationToken;
        String sign;
        String str3 = "/" + str + "/" + str2;
        String valueOf = String.valueOf((DateUtil.getFixedSkewedTimeMillis() / 1000) + j);
        if (this.credentialProvider instanceof OSSFederationCredentialProvider) {
            oSSFederationToken = ((OSSFederationCredentialProvider) this.credentialProvider).getValidFederationToken();
            if (oSSFederationToken == null) {
                throw new ClientException("Can not get a federation token!");
            }
            str3 = str3 + "?security-token=" + oSSFederationToken.getSecurityToken();
        } else if (this.credentialProvider instanceof OSSStsTokenCredentialProvider) {
            oSSFederationToken = ((OSSStsTokenCredentialProvider) this.credentialProvider).getFederationToken();
            str3 = str3 + "?security-token=" + oSSFederationToken.getSecurityToken();
        } else {
            oSSFederationToken = null;
        }
        String str4 = "GET\n\n\n" + valueOf + "\n" + str3;
        if ((this.credentialProvider instanceof OSSFederationCredentialProvider) || (this.credentialProvider instanceof OSSStsTokenCredentialProvider)) {
            sign = OSSUtils.sign(oSSFederationToken.getTempAK(), oSSFederationToken.getTempSK(), str4);
        } else if (this.credentialProvider instanceof OSSPlainTextAKSKCredentialProvider) {
            sign = OSSUtils.sign(((OSSPlainTextAKSKCredentialProvider) this.credentialProvider).getAccessKeyId(), ((OSSPlainTextAKSKCredentialProvider) this.credentialProvider).getAccessKeySecret(), str4);
        } else if (this.credentialProvider instanceof OSSCustomSignerCredentialProvider) {
            sign = ((OSSCustomSignerCredentialProvider) this.credentialProvider).signContent(str4);
        } else {
            throw new ClientException("Unknown credentialProvider!");
        }
        String substring = sign.split(":")[0].substring(4);
        String str5 = sign.split(":")[1];
        String host = this.endpoint.getHost();
        if (!OSSUtils.isCname(host)) {
            host = str + "." + host;
        }
        String str6 = this.endpoint.getScheme() + "://" + host + "/" + HttpUtil.urlEncode(str2, "utf-8") + "?OSSAccessKeyId=" + HttpUtil.urlEncode(substring, "utf-8") + "&Expires=" + valueOf + "&Signature=" + HttpUtil.urlEncode(str5, "utf-8");
        if ((this.credentialProvider instanceof OSSFederationCredentialProvider) || (this.credentialProvider instanceof OSSStsTokenCredentialProvider)) {
            return str6 + "&security-token=" + HttpUtil.urlEncode(oSSFederationToken.getSecurityToken(), "utf-8");
        }
        return str6;
    }

    public String presignPublicURL(String str, String str2) {
        String host = this.endpoint.getHost();
        if (!OSSUtils.isCname(host)) {
            host = str + "." + host;
        }
        return this.endpoint.getScheme() + "://" + host + "/" + HttpUtil.urlEncode(str2, "utf-8");
    }
}