package com.chad.library.adapter.base.entity;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class AbstractExpandableItem<T> implements IExpandable<T> {
    protected boolean mExpandable = false;
    protected List<T> mSubItems;

    @Override // com.chad.library.adapter.base.entity.IExpandable
    public boolean isExpanded() {
        return this.mExpandable;
    }

    @Override // com.chad.library.adapter.base.entity.IExpandable
    public void setExpanded(boolean z) {
        this.mExpandable = z;
    }

    @Override // com.chad.library.adapter.base.entity.IExpandable
    public List<T> getSubItems() {
        return this.mSubItems;
    }

    public boolean hasSubItem() {
        return this.mSubItems != null && this.mSubItems.size() > 0;
    }

    public void setSubItems(List<T> list) {
        this.mSubItems = list;
    }

    public T getSubItem(int i) {
        if (!hasSubItem() || i >= this.mSubItems.size()) {
            return null;
        }
        return this.mSubItems.get(i);
    }

    public int getSubItemPosition(T t) {
        if (this.mSubItems != null) {
            return this.mSubItems.indexOf(t);
        }
        return -1;
    }

    public void addSubItem(T t) {
        if (this.mSubItems == null) {
            this.mSubItems = new ArrayList();
        }
        this.mSubItems.add(t);
    }

    public void addSubItem(int i, T t) {
        if (this.mSubItems != null && i >= 0 && i < this.mSubItems.size()) {
            this.mSubItems.add(i, t);
        } else {
            addSubItem(t);
        }
    }

    public boolean contains(T t) {
        return this.mSubItems != null && this.mSubItems.contains(t);
    }

    public boolean removeSubItem(T t) {
        return this.mSubItems != null && this.mSubItems.remove(t);
    }

    public boolean removeSubItem(int i) {
        if (this.mSubItems == null || i < 0 || i >= this.mSubItems.size()) {
            return false;
        }
        this.mSubItems.remove(i);
        return true;
    }
}