package com.facebook.stetho.inspector;

/* loaded from: classes.dex */
public class MessageHandlingException extends Exception {
    public MessageHandlingException(Throwable th) {
        super(th);
    }

    public MessageHandlingException(String str) {
        super(str);
    }
}