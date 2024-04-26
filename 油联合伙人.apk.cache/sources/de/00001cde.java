package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class PosixParser extends Parser {
    private Option currentOption;
    private boolean eatTheRest;
    private Options options;
    private List tokens = new ArrayList();

    private void init() {
        this.eatTheRest = false;
        this.tokens.clear();
    }

    @Override // org.apache.commons.cli.Parser
    protected String[] flatten(Options options, String[] strArr, boolean z) {
        init();
        this.options = options;
        Iterator it = Arrays.asList(strArr).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
                int indexOf = str.indexOf(61);
                String substring = indexOf == -1 ? str : str.substring(0, indexOf);
                if (!options.hasOption(substring)) {
                    processNonOptionToken(str, z);
                } else {
                    this.currentOption = options.getOption(substring);
                    this.tokens.add(substring);
                    if (indexOf != -1) {
                        this.tokens.add(str.substring(indexOf + 1));
                    }
                }
            } else if (HelpFormatter.DEFAULT_OPT_PREFIX.equals(str)) {
                this.tokens.add(str);
            } else if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                if (str.length() == 2 || options.hasOption(str)) {
                    processOptionToken(str, z);
                } else {
                    burstToken(str, z);
                }
            } else {
                processNonOptionToken(str, z);
            }
            gobble(it);
        }
        return (String[]) this.tokens.toArray(new String[this.tokens.size()]);
    }

    private void gobble(Iterator it) {
        if (this.eatTheRest) {
            while (it.hasNext()) {
                this.tokens.add(it.next());
            }
        }
    }

    private void processNonOptionToken(String str, boolean z) {
        if (z && (this.currentOption == null || !this.currentOption.hasArg())) {
            this.eatTheRest = true;
            this.tokens.add(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        }
        this.tokens.add(str);
    }

    private void processOptionToken(String str, boolean z) {
        if (z && !this.options.hasOption(str)) {
            this.eatTheRest = true;
        }
        if (this.options.hasOption(str)) {
            this.currentOption = this.options.getOption(str);
        }
        this.tokens.add(str);
    }

    protected void burstToken(String str, boolean z) {
        int i;
        for (int i2 = 1; i2 < str.length(); i2++) {
            String valueOf = String.valueOf(str.charAt(i2));
            if (!this.options.hasOption(valueOf)) {
                if (z) {
                    processNonOptionToken(str.substring(i2), true);
                    return;
                } else {
                    this.tokens.add(str);
                    return;
                }
            }
            List list = this.tokens;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(HelpFormatter.DEFAULT_OPT_PREFIX);
            stringBuffer.append(valueOf);
            list.add(stringBuffer.toString());
            this.currentOption = this.options.getOption(valueOf);
            if (this.currentOption.hasArg() && str.length() != (i = i2 + 1)) {
                this.tokens.add(str.substring(i));
                return;
            }
        }
    }
}