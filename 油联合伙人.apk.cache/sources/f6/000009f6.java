package com.alipay.security.mobile.module.http.model;

/* loaded from: classes.dex */
public class c extends a {
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final String f = "APPKEY_ERROR";
    public static final String g = "SUCCESS";
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p = "";

    public void b(String str) {
        this.p = str;
    }

    public String c() {
        return this.p;
    }

    public void c(String str) {
        this.h = str;
    }

    public int d() {
        return this.a ? com.alipay.security.mobile.module.a.a.a(this.h) ? 2 : 1 : f.equals(this.b) ? 3 : 2;
    }

    public void d(String str) {
        this.i = str;
    }

    public void e(String str) {
        this.j = str;
    }

    public boolean e() {
        return "1".equals(this.j);
    }

    public String f() {
        return this.k == null ? "0" : this.k;
    }

    public void f(String str) {
        this.k = str;
    }

    public String g() {
        return this.h;
    }

    public void g(String str) {
        this.l = str;
    }

    public String h() {
        return this.i;
    }

    public void h(String str) {
        this.n = str;
    }

    public String i() {
        return this.j;
    }

    public void i(String str) {
        this.m = str;
    }

    public String j() {
        return this.l;
    }

    public void j(String str) {
        this.o = str;
    }

    public String k() {
        return this.n;
    }

    public String l() {
        return this.m;
    }

    public String m() {
        return this.o;
    }
}