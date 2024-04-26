package com.jakewharton.rxbinding.support.v7.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes.dex */
public final class RxSearchView {
    @CheckResult
    @NonNull
    public static Observable<SearchViewQueryTextEvent> queryTextChangeEvents(@NonNull SearchView searchView) {
        Preconditions.checkNotNull(searchView, "view == null");
        return Observable.create(new SearchViewQueryTextChangeEventsOnSubscribe(searchView));
    }

    @CheckResult
    @NonNull
    public static Observable<CharSequence> queryTextChanges(@NonNull SearchView searchView) {
        Preconditions.checkNotNull(searchView, "view == null");
        return Observable.create(new SearchViewQueryTextChangesOnSubscribe(searchView));
    }

    @CheckResult
    @NonNull
    public static Action1<? super CharSequence> query(@NonNull final SearchView searchView, final boolean z) {
        Preconditions.checkNotNull(searchView, "view == null");
        return new Action1<CharSequence>() { // from class: com.jakewharton.rxbinding.support.v7.widget.RxSearchView.1
            @Override // rx.functions.Action1
            public void call(CharSequence charSequence) {
                SearchView.this.setQuery(charSequence, z);
            }
        };
    }

    private RxSearchView() {
        throw new AssertionError("No instances.");
    }
}