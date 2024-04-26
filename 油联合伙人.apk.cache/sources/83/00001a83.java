package com.yltx.oil.partner.permission;

import android.app.Activity;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.utils.ToastUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class CheckPermission {
    public static final String CALENDAR_MSG = "请在设置-应用-%s-权限管理中开启日历权限";
    public static final String CAMERA_MSG = "请在设置-应用-%s-权限管理中开启摄像头权限";
    public static final String CONTACTS_MSG = "请在设置-应用-%s-权限管理中开启通讯录权限";
    public static final String LOCATION_MSG = "请在设置-应用-%s-权限管理中开启定位功能权限";
    public static final String MICROPHONE_MSG = "请在设置-应用-%s-权限管理中开启录音权限";
    public static final String PHONE_MSG = "请在设置-应用-%s-权限管理中开启找打电话相关功能权限";
    public static final String SENSORS_MSG = "请在设置-应用-%s-权限管理中开启手机传感器功能权限";
    public static final String SMS_MSG = "请在设置-应用-%s-权限管理中开启发送短信权限";
    public static final String STORAGE_MSG = "请在设置-应用-%s-权限管理中开启存储空间信息权限";
    private static Map<String, String> permissionMap = new HashMap();

    static {
        permissionMap.put("android.permission.WRITE_CONTACTS", CONTACTS_MSG);
        permissionMap.put("android.permission.GET_ACCOUNTS", CONTACTS_MSG);
        permissionMap.put("android.permission.READ_CONTACTS", CONTACTS_MSG);
        permissionMap.put("android.permission.READ_CALL_LOG", PHONE_MSG);
        permissionMap.put("android.permission.READ_PHONE_STATE", PHONE_MSG);
        permissionMap.put("android.permission.CALL_PHONE", PHONE_MSG);
        permissionMap.put("android.permission.WRITE_CALL_LOG", PHONE_MSG);
        permissionMap.put("android.permission.USE_SIP", PHONE_MSG);
        permissionMap.put("android.permission.PROCESS_OUTGOING_CALLS", PHONE_MSG);
        permissionMap.put("com.android.voicemail.permission.ADD_VOICEMAIL", PHONE_MSG);
        permissionMap.put("android.permission.READ_CALENDAR", CALENDAR_MSG);
        permissionMap.put("android.permission.WRITE_CALENDAR", CALENDAR_MSG);
        permissionMap.put("android.permission.CAMERA", CAMERA_MSG);
        permissionMap.put("android.permission.BODY_SENSORS", SENSORS_MSG);
        permissionMap.put("android.permission.ACCESS_FINE_LOCATION", LOCATION_MSG);
        permissionMap.put("android.permission.ACCESS_COARSE_LOCATION", LOCATION_MSG);
        permissionMap.put("android.permission.READ_EXTERNAL_STORAGE", STORAGE_MSG);
        permissionMap.put("android.permission.WRITE_EXTERNAL_STORAGE", STORAGE_MSG);
        permissionMap.put("android.permission.RECORD_AUDIO", MICROPHONE_MSG);
        permissionMap.put("android.permission.READ_SMS", SMS_MSG);
        permissionMap.put("android.permission.RECEIVE_WAP_PUSH", SMS_MSG);
        permissionMap.put("android.permission.RECEIVE_MMS", SMS_MSG);
        permissionMap.put("android.permission.RECEIVE_SMS", SMS_MSG);
        permissionMap.put("android.permission.SEND_SMS", SMS_MSG);
        permissionMap.put("android.permission.READ_CELL_BROADCASTS", SMS_MSG);
    }

    public static void check(Activity activity, final Action1<Void> action1, String... strArr) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.setLogging(Config.DEBUG);
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        activity.getString(R.string.app_name);
        final int length = strArr.length;
        rxPermissions.requestEach(strArr).subscribe(new Action1() { // from class: com.yltx.oil.partner.permission.-$$Lambda$CheckPermission$XJjITxVukRozoarmWS5O9fXpLiQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CheckPermission.lambda$check$0(atomicInteger, length, action1, (Permission) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$check$0(AtomicInteger atomicInteger, int i, Action1 action1, Permission permission) {
        int addAndGet = atomicInteger.addAndGet(1);
        if (!permission.granted && permissionMap.containsKey(permission.name)) {
            permissionMap.get(permission.name);
            ToastUtil.showMiddleScreenToast("部分权限禁止，可能导致应用无法正常使用");
        }
        if (addAndGet == i) {
            action1.call(null);
        }
    }

    public static void checkAllGranted(final Activity activity, final Action1<Void> action1, String... strArr) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.setLogging(Config.DEBUG);
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final String string = activity.getString(R.string.app_name);
        final int length = strArr.length;
        rxPermissions.requestEach(strArr).subscribe(new Action1<Permission>() { // from class: com.yltx.oil.partner.permission.CheckPermission.1
            @Override // rx.functions.Action1
            public void call(Permission permission) {
                int i = 0;
                if (!permission.granted) {
                    if (CheckPermission.permissionMap.containsKey(permission.name)) {
                        ToastUtil.showMiddleScreenToast(activity, String.format((String) CheckPermission.permissionMap.get(permission.name), string));
                    }
                } else {
                    i = atomicInteger.addAndGet(1);
                }
                if (i == length) {
                    action1.call(null);
                }
            }
        });
    }

    public static void check(Activity activity, final Action1<String> action1, final Action1<String> action12, String... strArr) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.setLogging(Config.DEBUG);
        rxPermissions.requestEach(strArr).subscribe(new Action1() { // from class: com.yltx.oil.partner.permission.-$$Lambda$CheckPermission$P24uvqROltapYAMjgtBLXpZjcS4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CheckPermission.lambda$check$1(Action1.this, action12, (Permission) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$check$1(Action1 action1, Action1 action12, Permission permission) {
        if (permission.granted) {
            if (action1 != null) {
                action1.call(permission.name);
            }
        } else if (action12 != null) {
            action12.call(permission.name);
        }
    }
}