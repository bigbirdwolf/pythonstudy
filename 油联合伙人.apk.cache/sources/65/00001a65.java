package com.yltx.oil.partner.mvp.subscribers;

import com.yltx.oil.partner.mvp.views.LoadDataView;

/* loaded from: classes.dex */
public abstract class LoadDataSubscriber<T> extends CommonErrorHandlerSubscriber<T> {
    public LoadDataSubscriber(LoadDataView loadDataView) {
        super(loadDataView);
    }
}