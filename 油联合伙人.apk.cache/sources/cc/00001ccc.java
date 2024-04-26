package org.apache.commons.cli;

/* loaded from: classes.dex */
public class AlreadySelectedException extends ParseException {
    private OptionGroup group;
    private Option option;

    public AlreadySelectedException(String str) {
        super(str);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AlreadySelectedException(org.apache.commons.cli.OptionGroup r3, org.apache.commons.cli.Option r4) {
        /*
            r2 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "The option '"
            r0.append(r1)
            java.lang.String r1 = r4.getKey()
            r0.append(r1)
            java.lang.String r1 = "' was specified but an option from this group "
            r0.append(r1)
            java.lang.String r1 = "has already been selected: '"
            r0.append(r1)
            java.lang.String r1 = r3.getSelected()
            r0.append(r1)
            java.lang.String r1 = "'"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            r2.group = r3
            r2.option = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.AlreadySelectedException.<init>(org.apache.commons.cli.OptionGroup, org.apache.commons.cli.Option):void");
    }

    public OptionGroup getOptionGroup() {
        return this.group;
    }

    public Option getOption() {
        return this.option;
    }
}