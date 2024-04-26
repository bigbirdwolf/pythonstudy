package org.apache.commons.cli;

import com.umeng.commonsdk.proguard.e;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class MissingOptionException extends ParseException {
    private List missingOptions;

    public MissingOptionException(String str) {
        super(str);
    }

    public MissingOptionException(List list) {
        this(createMessage(list));
        this.missingOptions = list;
    }

    public List getMissingOptions() {
        return this.missingOptions;
    }

    private static String createMessage(List list) {
        StringBuffer stringBuffer = new StringBuffer("Missing required option");
        stringBuffer.append(list.size() == 1 ? "" : e.ap);
        stringBuffer.append(": ");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
            if (it.hasNext()) {
                stringBuffer.append(", ");
            }
        }
        return stringBuffer.toString();
    }
}