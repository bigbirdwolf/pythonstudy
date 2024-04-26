package com.facebook.stetho.dumpapp;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ArgsHelper {
    public static String nextOptionalArg(Iterator<String> it, String str) {
        return it.hasNext() ? it.next() : str;
    }

    public static String nextArg(Iterator<String> it, String str) throws DumpUsageException {
        if (!it.hasNext()) {
            throw new DumpUsageException(str);
        }
        return it.next();
    }

    public static String[] drainToArray(Iterator<String> it) {
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}