package com.yltx.oil.partner.mvp.controller;

import com.yltx.oil.partner.mvp.views.InterfaceView;

/* loaded from: classes.dex */
public interface Presenter {
    void attachView(InterfaceView interfaceView);

    void onDestroy();

    void onPause();

    void onResume();
}