package com.facebook.stetho.websocket;

import java.io.IOException;

/* loaded from: classes.dex */
interface WriteCallback {
    void onFailure(IOException iOException);

    void onSuccess();
}