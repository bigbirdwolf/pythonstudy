package com.alibaba.sdk.android.oss.model;

import org.apache.commons.cli.HelpFormatter;

/* loaded from: classes.dex */
public class Range {
    public static final long INFINITE = -1;
    private long begin;
    private long end;

    public Range(long j, long j2) {
        this.begin = j;
        this.end = j2;
    }

    public long getEnd() {
        return this.end;
    }

    public void setEnd(long j) {
        this.end = j;
    }

    public long getBegin() {
        return this.begin;
    }

    public void setBegin(long j) {
        this.begin = j;
    }

    public boolean checkIsValid() {
        if (this.begin < -1 || this.end < -1) {
            return false;
        }
        return this.begin < 0 || this.end < 0 || this.begin <= this.end;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("bytes=");
        sb.append(this.begin == -1 ? "" : String.valueOf(this.begin));
        sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
        sb.append(this.end == -1 ? "" : String.valueOf(this.end));
        return sb.toString();
    }
}