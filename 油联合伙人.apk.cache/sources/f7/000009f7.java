package com.alipay.security.mobile.module.http.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class d {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private Map<String, String> f;
    private String g;
    private String h;
    private String i;
    private String j;

    public String a() {
        return this.j;
    }

    public void a(String str) {
        this.j = str;
    }

    public void a(Map<String, String> map) {
        this.f = map;
    }

    public String b() {
        return com.alipay.security.mobile.module.a.a.d(this.a);
    }

    public void b(String str) {
        this.a = str;
    }

    public String c() {
        return com.alipay.security.mobile.module.a.a.d(this.b);
    }

    public void c(String str) {
        this.b = str;
    }

    public String d() {
        return com.alipay.security.mobile.module.a.a.d(this.c);
    }

    public void d(String str) {
        this.c = str;
    }

    public String e() {
        return com.alipay.security.mobile.module.a.a.d(this.d);
    }

    public void e(String str) {
        this.d = str;
    }

    public Map<String, String> f() {
        return this.f == null ? new HashMap() : this.f;
    }

    public void f(String str) {
        this.e = str;
    }

    public String g() {
        return this.e;
    }

    public void g(String str) {
        this.g = str;
    }

    public String h() {
        return this.g;
    }

    public void h(String str) {
        this.h = str;
    }

    public String i() {
        return this.h;
    }

    public void i(String str) {
        this.i = str;
    }

    public String j() {
        return this.i;
    }
}