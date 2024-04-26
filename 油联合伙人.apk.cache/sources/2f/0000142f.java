package com.umeng.socialize.bean;

import com.contrarywind.timer.MessageHandler;

/* loaded from: classes.dex */
public enum UmengErrorCode {
    UnKnowCode(MessageHandler.WHAT_SMOOTH_SCROLL),
    AuthorizeFailed(2002),
    ShareFailed(2003),
    RequestForUserProfileFailed(2004),
    ShareDataNil(2004),
    ShareDataTypeIllegal(2004),
    NotInstall(2008);
    
    private final int a;

    UmengErrorCode(int i) {
        this.a = i;
    }

    public String getMessage() {
        if (this == UnKnowCode) {
            return a() + "未知错误----";
        } else if (this == AuthorizeFailed) {
            return a() + "授权失败----";
        } else if (this == ShareFailed) {
            return a() + "分享失败----";
        } else if (this == RequestForUserProfileFailed) {
            return a() + "获取用户资料失败----";
        } else if (this == ShareDataNil) {
            return a() + "分享内容为空";
        } else if (this == ShareDataTypeIllegal) {
            return a() + "分享内容不合法----";
        } else if (this == NotInstall) {
            return a() + "没有安装应用";
        } else {
            return "unkonw";
        }
    }

    private String a() {
        return "错误码：" + this.a + " 错误信息：";
    }
}