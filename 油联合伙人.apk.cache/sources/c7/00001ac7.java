package com.yltx.oil.partner.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;

/* loaded from: classes.dex */
public class UpdateConfiguration {
    final int allowedNetworkTypes;
    final String description;
    final String destinationDir;
    final String destinationFileName;
    final int downloadIconBig;
    final int downloadIconSmall;
    final String mimeType;
    final int notificationVisibility;
    final String title;
    final boolean visibleInDownloads;

    private UpdateConfiguration(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.downloadIconBig = builder.downloadIconBig;
        this.downloadIconSmall = builder.downloadIconSmall;
        this.destinationDir = builder.destinationDir;
        this.destinationFileName = builder.destinationFileName;
        this.allowedNetworkTypes = builder.allowedNetworkTypes;
        this.mimeType = builder.mimeType;
        this.notificationVisibility = builder.notificationVisibility;
        this.visibleInDownloads = builder.visibleInDownloads;
    }

    public static UpdateConfiguration createDefault(Context context) {
        return new Builder(context).build();
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String description;
        private String destinationDir;
        private String destinationFileName;
        private Context mContext;
        private String mimeType;
        private String title;
        private int downloadIconBig = -1;
        private int downloadIconSmall = -1;
        private int allowedNetworkTypes = -1;
        private int notificationVisibility = -1;
        private boolean visibleInDownloads = true;

        public Builder(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public Builder setNotificationTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setNotificationDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder setDownloadIconBig(@DrawableRes int i) {
            this.downloadIconBig = i;
            return this;
        }

        public Builder setDownloadIconSmall(@DrawableRes int i) {
            this.downloadIconSmall = i;
            return this;
        }

        public Builder setDestinationInExternalPublicDir(String str, String str2) {
            this.destinationDir = str;
            this.destinationFileName = str2;
            return this;
        }

        public Builder setVisibleInDownloads(boolean z) {
            this.visibleInDownloads = z;
            return this;
        }

        public Builder setNotificationVisibility(int i) {
            this.notificationVisibility = i;
            return this;
        }

        public Builder setMimeType(String str) {
            this.mimeType = str;
            return this;
        }

        public Builder setAllowedNetworkTypes(int i) {
            this.allowedNetworkTypes = i;
            return this;
        }

        private void initEmptyFieldsWithDefaultValues() {
            if (TextUtils.isEmpty(this.title)) {
                this.title = "正在下载";
            }
            if (TextUtils.isEmpty(this.description)) {
                this.description = "正在下载";
            }
            if (TextUtils.isEmpty(this.destinationDir)) {
                this.destinationDir = Environment.DIRECTORY_DOWNLOADS;
            }
            if (TextUtils.isEmpty(this.destinationFileName)) {
                this.destinationFileName = "a.apk";
            }
            if (TextUtils.isEmpty(this.mimeType)) {
                this.mimeType = "application/vnd.android.package-archive";
            }
            if (this.allowedNetworkTypes == -1) {
                this.allowedNetworkTypes = 3;
            }
            if (this.notificationVisibility == -1) {
                this.notificationVisibility = 1;
            }
        }

        public UpdateConfiguration build() {
            initEmptyFieldsWithDefaultValues();
            return new UpdateConfiguration(this);
        }
    }
}