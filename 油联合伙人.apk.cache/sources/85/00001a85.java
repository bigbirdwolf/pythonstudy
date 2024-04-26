package com.yltx.oil.partner.permission;

import java.util.List;

/* loaded from: classes.dex */
public interface PermissionListener {
    void onDenied(List<String> list);

    void onGranted();
}