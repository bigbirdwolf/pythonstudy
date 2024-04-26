package com.alipay.sdk.widget;

import java.util.Iterator;
import java.util.Stack;

/* loaded from: classes.dex */
public class u {
    private Stack<WebViewWindow> a = new Stack<>();

    public WebViewWindow a() {
        return this.a.pop();
    }

    public void a(WebViewWindow webViewWindow) {
        this.a.push(webViewWindow);
    }

    public boolean b() {
        return this.a.isEmpty();
    }

    public void c() {
        if (b()) {
            return;
        }
        Iterator<WebViewWindow> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.a.clear();
    }
}