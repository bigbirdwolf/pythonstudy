package com.yltx.oil.partner.mvp.views;

/* loaded from: classes.dex */
public interface TestView extends LoadDataView {
    void onRenderError(Throwable th);

    void renderSuccess(String str);
}