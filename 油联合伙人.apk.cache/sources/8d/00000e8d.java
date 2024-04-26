package com.google.ads.consent;

import com.google.gson.annotations.SerializedName;
import java.util.HashSet;

/* loaded from: classes.dex */
class ConsentData {
    private static final String SDK_PLATFORM = "android";
    private static final String SDK_VERSION = "1.0.6";
    @SerializedName("consent_source")
    private String consentSource;
    @SerializedName("providers")
    private HashSet<AdProvider> adProviders = new HashSet<>();
    @SerializedName("consented_providers")
    private HashSet<AdProvider> consentedAdProviders = new HashSet<>();
    @SerializedName("pub_ids")
    private HashSet<String> publisherIds = new HashSet<>();
    @SerializedName("tag_for_under_age_of_consent")
    private Boolean underAgeOfConsent = false;
    @SerializedName("consent_state")
    private ConsentStatus consentStatus = ConsentStatus.UNKNOWN;
    @SerializedName("is_request_in_eea_or_unknown")
    private boolean isRequestLocationInEeaOrUnknown = false;
    @SerializedName("has_any_npa_pub_id")
    private boolean hasNonPersonalizedPublisherId = false;
    @SerializedName("version")
    private final String sdkVersionString = SDK_VERSION;
    @SerializedName("plat")
    private final String sdkPlatformString = SDK_PLATFORM;
    @SerializedName("raw_response")
    private String rawResponse = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isTaggedForUnderAgeOfConsent() {
        return this.underAgeOfConsent.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void tagForUnderAgeOfConsent(boolean z) {
        this.underAgeOfConsent = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashSet<AdProvider> getAdProviders() {
        return this.adProviders;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAdProviders(HashSet<AdProvider> hashSet) {
        this.adProviders = hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConsentStatus getConsentStatus() {
        return this.consentStatus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setConsentStatus(ConsentStatus consentStatus) {
        this.consentStatus = consentStatus;
    }

    HashSet<String> getPublisherIds() {
        return this.publisherIds;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPublisherIds(HashSet<String> hashSet) {
        this.publisherIds = hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isRequestLocationInEeaOrUnknown() {
        return this.isRequestLocationInEeaOrUnknown;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRequestLocationInEeaOrUnknown(boolean z) {
        this.isRequestLocationInEeaOrUnknown = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashSet<AdProvider> getConsentedAdProviders() {
        return this.consentedAdProviders;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setConsentedAdProviders(HashSet<AdProvider> hashSet) {
        this.consentedAdProviders = hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasNonPersonalizedPublisherId() {
        return this.hasNonPersonalizedPublisherId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHasNonPersonalizedPublisherId(boolean z) {
        this.hasNonPersonalizedPublisherId = z;
    }

    public String getSDKVersionString() {
        return this.sdkVersionString;
    }

    public String getSDKPlatformString() {
        return this.sdkPlatformString;
    }

    public String getConsentSource() {
        return this.consentSource;
    }

    public void setConsentSource(String str) {
        this.consentSource = str;
    }

    String getRawResponse() {
        return this.rawResponse;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRawResponse(String str) {
        this.rawResponse = str;
    }
}