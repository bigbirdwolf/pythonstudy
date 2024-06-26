package com.alibaba.sdk.android.oss;

/* loaded from: classes.dex */
public class ClientException extends Exception {
    public ClientException() {
    }

    public ClientException(String str) {
        super("[ErrorMessage]: " + str);
    }

    public ClientException(Throwable th) {
        super(th);
    }

    public ClientException(String str, Throwable th) {
        super("[ErrorMessage]: " + str, th);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (getCause() == null) {
            return message;
        }
        return getCause().getMessage() + "\n" + message;
    }
}