package com.yltx.oil.partner.mvp.domain;

/* loaded from: classes.dex */
public abstract class PageLimitUseCase<T> extends UseCase<T> {
    private int pageOffset = 1;
    private int pageSize = 10;

    public void setPageOffset(int i) {
        this.pageOffset = i;
    }

    public void setPageSize(int i) {
        this.pageSize = i;
    }

    public String pageOffset() {
        return String.valueOf(this.pageOffset);
    }

    public String pageSize() {
        return String.valueOf(this.pageSize);
    }
}