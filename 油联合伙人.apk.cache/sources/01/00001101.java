package com.jakewharton.rxbinding.support.v7.widget;

import android.support.v7.widget.SearchView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class SearchViewQueryTextChangesOnSubscribe implements Observable.OnSubscribe<CharSequence> {
    final SearchView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SearchViewQueryTextChangesOnSubscribe(SearchView searchView) {
        this.view = searchView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super CharSequence> subscriber) {
        MainThreadSubscription.verifyMainThread();
        SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() { // from class: com.jakewharton.rxbinding.support.v7.widget.SearchViewQueryTextChangesOnSubscribe.1
            @Override // android.support.v7.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                return false;
            }

            @Override // android.support.v7.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                if (subscriber.isUnsubscribed()) {
                    return false;
                }
                subscriber.onNext(str);
                return true;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.support.v7.widget.SearchViewQueryTextChangesOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                SearchViewQueryTextChangesOnSubscribe.this.view.setOnQueryTextListener(null);
            }
        });
        this.view.setOnQueryTextListener(onQueryTextListener);
        subscriber.onNext(this.view.getQuery());
    }
}