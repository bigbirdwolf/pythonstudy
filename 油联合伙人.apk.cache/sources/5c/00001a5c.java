package com.yltx.oil.partner.mvp.controller;

import android.util.Log;
import com.yltx.oil.partner.base.ErrorView;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.mvp.domain.UseCase;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.PageLimitView;
import com.yltx.oil.partner.utils.ErrorFormatter;
import rx.Observer;
import rx.Subscriber;

/* loaded from: classes.dex */
public abstract class PageLimitPresenter<T> implements Presenter {
    private PageLimitView<T> mPageView;
    private int pageOffset = 1;
    private int pageSize = 10;
    private int state = 16;

    protected Observer<T> buildObserver() {
        return null;
    }

    protected abstract UseCase<T> buildPageUseCase(int i, int i2);

    static /* synthetic */ int access$008(PageLimitPresenter pageLimitPresenter) {
        int i = pageLimitPresenter.pageOffset;
        pageLimitPresenter.pageOffset = i + 1;
        return i;
    }

    static /* synthetic */ int access$010(PageLimitPresenter pageLimitPresenter) {
        int i = pageLimitPresenter.pageOffset;
        pageLimitPresenter.pageOffset = i - 1;
        return i;
    }

    public void setPageSize(int i) {
        this.pageSize = i;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.mPageView = (PageLimitView) interfaceView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void errorFetch() {
        fetchFirst();
    }

    public void fetchFirst() {
        this.state = 16;
        this.pageOffset = 1;
        buildPageUseCase(this.pageOffset, this.pageSize).execute(new PageLimitSubscriber(this.mPageView, buildObserver()));
    }

    public void fetchTop() {
        this.state = 17;
        this.pageOffset = 1;
        buildPageUseCase(this.pageOffset, this.pageSize).execute(new PageLimitSubscriber(this.mPageView, buildObserver()));
    }

    public void fetchMore() {
        this.state = 18;
        buildPageUseCase(this.pageOffset, this.pageSize).execute(new PageLimitSubscriber(this.mPageView, buildObserver()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class PageLimitSubscriber extends Subscriber<T> {
        private PageLimitView<T> pageLimitView;
        private Observer<T> pageObserver;

        public PageLimitSubscriber(PageLimitView<T> pageLimitView, Observer<T> observer) {
            this.pageLimitView = pageLimitView;
            this.pageObserver = observer;
        }

        @Override // rx.Subscriber
        public void onStart() {
            super.onStart();
            PageLimitPresenter.access$008(PageLimitPresenter.this);
            if (Config.IS_SHOW_LOADING) {
                if (PageLimitPresenter.this.state == 16) {
                    this.pageLimitView.showLoadingView();
                    return;
                }
                return;
            }
            Config.IS_SHOW_LOADING = true;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.pageObserver != null) {
                this.pageObserver.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            Log.d(">>>>", ErrorFormatter.format(th));
            PageLimitPresenter.access$010(PageLimitPresenter.this);
            if (PageLimitPresenter.this.pageOffset <= 1) {
                PageLimitPresenter.this.pageOffset = 1;
            }
            switch (PageLimitPresenter.this.state) {
                case 16:
                    this.pageLimitView.onLoadMoreError(ErrorFormatter.format(th));
                    PageLimitView<T> pageLimitView = this.pageLimitView;
                    final PageLimitPresenter pageLimitPresenter = PageLimitPresenter.this;
                    pageLimitView.showErrorView(th, null, new ErrorView.OnRetryListener() { // from class: com.yltx.oil.partner.mvp.controller.-$$Lambda$FBmehZjWSGxRuiNEcCJBXMFr42k
                        @Override // com.yltx.oil.partner.base.ErrorView.OnRetryListener
                        public final void onRetry() {
                            PageLimitPresenter.this.errorFetch();
                        }
                    });
                    break;
                case 17:
                    this.pageLimitView.onRefreshError(ErrorFormatter.format(th));
                    break;
                case 18:
                    this.pageLimitView.onLoadMoreError(ErrorFormatter.format(th));
                    break;
            }
            if (this.pageObserver != null) {
                this.pageObserver.onError(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            switch (PageLimitPresenter.this.state) {
                case 16:
                    this.pageLimitView.onLoadingComplete();
                    this.pageLimitView.render(t);
                    break;
                case 17:
                    this.pageLimitView.onRefreshComplete(t);
                    break;
                case 18:
                    this.pageLimitView.onLoadMoreComplete(t);
                    break;
            }
            if (this.pageObserver != null) {
                this.pageObserver.onNext(t);
            }
        }
    }
}