package org.json.alipay;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes.dex */
public class a {
    private ArrayList a;

    public a() {
        this.a = new ArrayList();
    }

    public a(Object obj) {
        this();
        if (!obj.getClass().isArray()) {
            throw new JSONException("JSONArray initial value should be a string or collection or array.");
        }
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.a.add(Array.get(obj, i));
        }
    }

    public a(String str) {
        this(new c(str));
    }

    public a(Collection collection) {
        this.a = collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public a(c cVar) {
        this();
        char c;
        ArrayList arrayList;
        Object d;
        char c2 = cVar.c();
        if (c2 == '[') {
            c = ']';
        } else if (c2 != '(') {
            throw cVar.a("A JSONArray text must start with '['");
        } else {
            c = ')';
        }
        if (cVar.c() == ']') {
            return;
        }
        do {
            cVar.a();
            if (cVar.c() == ',') {
                cVar.a();
                arrayList = this.a;
                d = null;
            } else {
                cVar.a();
                arrayList = this.a;
                d = cVar.d();
            }
            arrayList.add(d);
            char c3 = cVar.c();
            if (c3 != ')') {
                if (c3 != ',' && c3 != ';') {
                    if (c3 != ']') {
                        throw cVar.a("Expected a ',' or ']'");
                    }
                }
            }
            if (c == c3) {
                return;
            }
            throw cVar.a("Expected a '" + new Character(c) + "'");
        } while (cVar.c() != ']');
    }

    private String a(String str) {
        int size = this.a.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(b.a(this.a.get(i)));
        }
        return stringBuffer.toString();
    }

    public final int a() {
        return this.a.size();
    }

    public final Object a(int i) {
        Object obj = (i < 0 || i >= this.a.size()) ? null : this.a.get(i);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public String toString() {
        try {
            return "[" + a(",") + ']';
        } catch (Exception unused) {
            return null;
        }
    }
}