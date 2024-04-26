package org.apache.commons.cli;

/* loaded from: classes.dex */
public class MissingArgumentException extends ParseException {
    private Option option;

    public MissingArgumentException(String str) {
        super(str);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MissingArgumentException(org.apache.commons.cli.Option r3) {
        /*
            r2 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "Missing argument for option: "
            r0.append(r1)
            java.lang.String r1 = r3.getKey()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            r2.option = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.MissingArgumentException.<init>(org.apache.commons.cli.Option):void");
    }

    public Option getOption() {
        return this.option;
    }
}