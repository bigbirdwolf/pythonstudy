package com.google.ads.consent;

/* loaded from: classes.dex */
public interface ConsentInfoUpdateListener {
    void onConsentInfoUpdated(ConsentStatus consentStatus);

    void onFailedToUpdateConsentInfo(String str);
}