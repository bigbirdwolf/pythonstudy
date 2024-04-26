package com.yltx.oil.partner.mvp.subscribers;

import android.text.TextUtils;
import android.util.Log;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import com.yltx.oil.partner.utils.ErrorFormatter;
import rx.Subscriber;

/* loaded from: classes.dex */
public abstract class CommonErrorHandlerSubscriber<T> extends Subscriber<T> {
    protected LoadDataView mLoadDataView;

    public CommonErrorHandlerSubscriber(LoadDataView loadDataView) {
        this.mLoadDataView = loadDataView;
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        String format = ErrorFormatter.format(th);
        Log.d(">>>", format);
        if (!TextUtils.isEmpty(format) && format.equals("请先登录") && !Config.LOGIN_CONFLICT) {
            Config.LOGIN_CONFLICT = true;
            return;
        }
        if (Config.LOGIN_CONFLICT && !TextUtils.isEmpty(format) && format.equals("请先登录")) {
            this.mLoadDataView.showError(format);
        }
        this.mLoadDataView.showError(format);
    }
}