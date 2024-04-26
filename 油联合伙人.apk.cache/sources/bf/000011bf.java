package com.jakewharton.rxbinding.widget;

import android.widget.SearchView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/* loaded from: classes.dex */
final class SearchViewQueryTextChangeEventsOnSubscribe implements Observable.OnSubscribe<SearchViewQueryTextEvent> {
    final SearchView view;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SearchViewQueryTextChangeEventsOnSubscribe(SearchView searchView) {
        this.view = searchView;
    }

    @Override // rx.functions.Action1
    public void call(final Subscriber<? super SearchViewQueryTextEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() { // from class: com.jakewharton.rxbinding.widget.SearchViewQueryTextChangeEventsOnSubscribe.1
            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                if (subscriber.isUnsubscribed()) {
                    return false;
                }
                subscriber.onNext(SearchViewQueryTextEvent.create(SearchViewQueryTextChangeEventsOnSubscribe.this.view, str, false));
                return true;
            }

            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                if (subscriber.isUnsubscribed()) {
                    return false;
                }
                subscriber.onNext(SearchViewQueryTextEvent.create(SearchViewQueryTextChangeEventsOnSubscribe.this.view, SearchViewQueryTextChangeEventsOnSubscribe.this.view.getQuery(), true));
                return true;
            }
        };
        subscriber.add(new MainThreadSubscription() { // from class: com.jakewharton.rxbinding.widget.SearchViewQueryTextChangeEventsOnSubscribe.2
            @Override // rx.android.MainThreadSubscription
            protected void onUnsubscribe() {
                SearchViewQueryTextChangeEventsOnSubscribe.this.view.setOnQueryTextListener(null);
            }
        });
        this.view.setOnQueryTextListener(onQueryTextListener);
        subscriber.onNext(SearchViewQueryTextEvent.create(this.view, this.view.getQuery(), false));
    }
}