package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.SearchView;
import com.jakewharton.rxbinding.view.ViewEvent;

/* loaded from: classes.dex */
public final class SearchViewQueryTextEvent extends ViewEvent<SearchView> {
    private final CharSequence queryText;
    private final boolean submitted;

    @CheckResult
    @NonNull
    public static SearchViewQueryTextEvent create(@NonNull SearchView searchView, @NonNull CharSequence charSequence, boolean z) {
        return new SearchViewQueryTextEvent(searchView, charSequence, z);
    }

    private SearchViewQueryTextEvent(@NonNull SearchView searchView, @NonNull CharSequence charSequence, boolean z) {
        super(searchView);
        this.queryText = charSequence;
        this.submitted = z;
    }

    @NonNull
    public CharSequence queryText() {
        return this.queryText;
    }

    public boolean isSubmitted() {
        return this.submitted;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SearchViewQueryTextEvent) {
            SearchViewQueryTextEvent searchViewQueryTextEvent = (SearchViewQueryTextEvent) obj;
            return searchViewQueryTextEvent.view() == view() && searchViewQueryTextEvent.queryText.equals(this.queryText) && searchViewQueryTextEvent.submitted == this.submitted;
        }
        return false;
    }

    public int hashCode() {
        return ((((629 + view().hashCode()) * 37) + this.queryText.hashCode()) * 37) + (this.submitted ? 1 : 0);
    }

    public String toString() {
        return "SearchViewQueryTextEvent{view=" + view() + ", queryText=" + ((Object) this.queryText) + ", submitted=" + this.submitted + '}';
    }
}