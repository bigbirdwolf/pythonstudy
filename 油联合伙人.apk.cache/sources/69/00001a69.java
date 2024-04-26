package com.yltx.oil.partner.mvp.views;

/* loaded from: classes.dex */
public interface PageLimitView<T> extends LoadDataView {
    void onLoadMoreComplete(T t);

    void onLoadMoreError(String str);

    void onRefreshComplete(T t);

    void onRefreshError(String str);

    void render(T t);
}