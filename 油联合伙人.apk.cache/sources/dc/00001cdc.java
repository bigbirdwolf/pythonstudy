package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

/* loaded from: classes.dex */
public abstract class Parser implements CommandLineParser {
    protected CommandLine cmd;
    private Options options;
    private List requiredOptions;

    protected abstract String[] flatten(Options options, String[] strArr, boolean z);

    protected void setOptions(Options options) {
        this.options = options;
        this.requiredOptions = new ArrayList(options.getRequiredOptions());
    }

    protected Options getOptions() {
        return this.options;
    }

    protected List getRequiredOptions() {
        return this.requiredOptions;
    }

    @Override // org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options, String[] strArr) throws ParseException {
        return parse(options, strArr, null, false);
    }

    public CommandLine parse(Options options, String[] strArr, Properties properties) throws ParseException {
        return parse(options, strArr, properties, false);
    }

    @Override // org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options, String[] strArr, boolean z) throws ParseException {
        return parse(options, strArr, null, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0082, code lost:
        if (r7 != false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0087 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0037 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.commons.cli.CommandLine parse(org.apache.commons.cli.Options r4, java.lang.String[] r5, java.util.Properties r6, boolean r7) throws org.apache.commons.cli.ParseException {
        /*
            r3 = this;
            java.util.List r0 = r4.helpOptions()
            java.util.Iterator r0 = r0.iterator()
        L8:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L18
            java.lang.Object r1 = r0.next()
            org.apache.commons.cli.Option r1 = (org.apache.commons.cli.Option) r1
            r1.clearValues()
            goto L8
        L18:
            r3.setOptions(r4)
            org.apache.commons.cli.CommandLine r4 = new org.apache.commons.cli.CommandLine
            r4.<init>()
            r3.cmd = r4
            r4 = 0
            if (r5 != 0) goto L27
            java.lang.String[] r5 = new java.lang.String[r4]
        L27:
            org.apache.commons.cli.Options r0 = r3.getOptions()
            java.lang.String[] r5 = r3.flatten(r0, r5, r7)
            java.util.List r5 = java.util.Arrays.asList(r5)
            java.util.ListIterator r5 = r5.listIterator()
        L37:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto La1
            java.lang.Object r0 = r5.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "--"
            boolean r1 = r1.equals(r0)
            r2 = 1
            if (r1 == 0) goto L4e
        L4c:
            r4 = 1
            goto L85
        L4e:
            java.lang.String r1 = "-"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L5f
            if (r7 == 0) goto L59
            goto L4c
        L59:
            org.apache.commons.cli.CommandLine r1 = r3.cmd
            r1.addArg(r0)
            goto L85
        L5f:
            java.lang.String r1 = "-"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L7d
            if (r7 == 0) goto L79
            org.apache.commons.cli.Options r1 = r3.getOptions()
            boolean r1 = r1.hasOption(r0)
            if (r1 != 0) goto L79
            org.apache.commons.cli.CommandLine r4 = r3.cmd
            r4.addArg(r0)
            goto L4c
        L79:
            r3.processOption(r0, r5)
            goto L85
        L7d:
            org.apache.commons.cli.CommandLine r1 = r3.cmd
            r1.addArg(r0)
            if (r7 == 0) goto L85
            goto L4c
        L85:
            if (r4 == 0) goto L37
        L87:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L37
            java.lang.Object r0 = r5.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "--"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L87
            org.apache.commons.cli.CommandLine r1 = r3.cmd
            r1.addArg(r0)
            goto L87
        La1:
            r3.processProperties(r6)
            r3.checkRequiredOptions()
            org.apache.commons.cli.CommandLine r4 = r3.cmd
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.Parser.parse(org.apache.commons.cli.Options, java.lang.String[], java.util.Properties, boolean):org.apache.commons.cli.CommandLine");
    }

    protected void processProperties(Properties properties) {
        if (properties == null) {
            return;
        }
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String obj = propertyNames.nextElement().toString();
            if (!this.cmd.hasOption(obj)) {
                Option option = getOptions().getOption(obj);
                String property = properties.getProperty(obj);
                if (option.hasArg()) {
                    if (option.getValues() == null || option.getValues().length == 0) {
                        try {
                            option.addValueForProcessing(property);
                        } catch (RuntimeException unused) {
                        }
                    }
                } else if (!"yes".equalsIgnoreCase(property) && !"true".equalsIgnoreCase(property) && !"1".equalsIgnoreCase(property)) {
                    return;
                }
                this.cmd.addOption(option);
            }
        }
    }

    protected void checkRequiredOptions() throws MissingOptionException {
        if (!getRequiredOptions().isEmpty()) {
            throw new MissingOptionException(getRequiredOptions());
        }
    }

    public void processArgs(Option option, ListIterator listIterator) throws ParseException {
        while (true) {
            if (!listIterator.hasNext()) {
                break;
            }
            String str = (String) listIterator.next();
            if (getOptions().hasOption(str) && str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                listIterator.previous();
                break;
            } else {
                try {
                    option.addValueForProcessing(Util.stripLeadingAndTrailingQuotes(str));
                } catch (RuntimeException unused) {
                    listIterator.previous();
                }
            }
        }
        if (option.getValues() == null && !option.hasOptionalArg()) {
            throw new MissingArgumentException(option);
        }
    }

    protected void processOption(String str, ListIterator listIterator) throws ParseException {
        if (!getOptions().hasOption(str)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unrecognized option: ");
            stringBuffer.append(str);
            throw new UnrecognizedOptionException(stringBuffer.toString(), str);
        }
        Option option = (Option) getOptions().getOption(str).clone();
        if (option.isRequired()) {
            getRequiredOptions().remove(option.getKey());
        }
        if (getOptions().getOptionGroup(option) != null) {
            OptionGroup optionGroup = getOptions().getOptionGroup(option);
            if (optionGroup.isRequired()) {
                getRequiredOptions().remove(optionGroup);
            }
            optionGroup.setSelected(option);
        }
        if (option.hasArg()) {
            processArgs(option, listIterator);
        }
        this.cmd.addOption(option);
    }
}