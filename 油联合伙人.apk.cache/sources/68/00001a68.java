package com.yltx.oil.partner.mvp.views;

import com.yltx.oil.partner.base.ErrorView;

/* loaded from: classes.dex */
public interface LoadDataView extends InterfaceView {
    void onLoadingComplete();

    void showEmptyView(ErrorView.Config config, ErrorView.OnRetryListener onRetryListener);

    void showError(String str);

    void showErrorView(Throwable th, ErrorView.Config config, ErrorView.OnRetryListener onRetryListener);

    void showLoadingView();
}