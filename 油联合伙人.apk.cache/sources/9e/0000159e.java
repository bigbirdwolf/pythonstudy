package com.yltx.oil.partner.data.network.adapter;

import android.util.Log;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.network.ResponseCode;
import com.yltx.oil.partner.data.network.UserToken;
import com.yltx.oil.partner.mvp.exception.BizException;
import com.yltx.oil.partner.mvp.exception.ServerError;
import com.yltx.oil.partner.utils.ContextHolder;
import com.yltx.oil.partner.utils.DataCache;
import okhttp3.Headers;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.plugins.RxJavaPlugins;

/* loaded from: classes.dex */
final class BodyOnSubscribe<T> implements Observable.OnSubscribe<T> {
    private final Observable.OnSubscribe<Response<T>> upstream;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BodyOnSubscribe(Observable.OnSubscribe<Response<T>> onSubscribe) {
        this.upstream = onSubscribe;
    }

    public void call(Subscriber<? super T> subscriber) {
        this.upstream.call(new BodySubscriber(subscriber));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class BodySubscriber<R> extends Subscriber<Response<R>> {
        private final Subscriber<? super R> subscriber;
        private boolean subscriberTerminated;

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Response) ((Response) obj));
        }

        BodySubscriber(Subscriber<? super R> subscriber) {
            super(subscriber);
            this.subscriber = subscriber;
        }

        public void onNext(Response<R> response) {
            if (response.isSuccessful()) {
                Headers headers = response.headers();
                String str = headers.get("X-Resp-Code");
                headers.get("X-Point");
                headers.get("X-Resp-Time");
                if (str == null || str.length() == 0) {
                    this.subscriber.onError(new ServerError("", "resp code is null."));
                    return;
                }
                try {
                    HttpResult httpResult = (HttpResult) response.body();
                    if ("0".equals(str)) {
                        this.subscriber.onNext(response.body());
                    } else if (ResponseCode.RESP_CODE_TOKEN_FAILURE.equals(str)) {
                        ContextHolder.getContext();
                        this.subscriber.onError(new BizException(str, httpResult.getMsg()));
                    } else if (ResponseCode.RESP_CODE_TOKEN_FAILURE_1025.equals(str)) {
                        this.subscriber.onError(new ServerError(str, "该号码未注册"));
                        return;
                    } else if (str.length() == 4) {
                        Log.d("debug", "4位验证码，业务验证错误  " + httpResult.getMsg());
                        if (httpResult.getMsg().equals("请先登录")) {
                            PartnerApplication.getInstance().getSharedPreferences("userId", 0).edit().clear().commit();
                            PartnerApplication.instance.isLoading = false;
                            DataCache.setToken(PartnerApplication.instance, null);
                            UserToken.getInstance().setToken(null);
                            UserToken.getInstance().setPhone(null);
                            DataCache.setPartnerInfo(PartnerApplication.instance, null);
                            DataCache.setUserToken(PartnerApplication.instance, null);
                        }
                        this.subscriber.onError(new BizException(str, httpResult.getMsg()));
                    } else if (str.length() == 5) {
                        Log.d("debug", "5位验证码，系统级错误  " + httpResult.getMsg());
                        this.subscriber.onError(new ServerError(str, httpResult.getMsg()));
                    }
                    this.subscriber.onNext(response.body());
                    return;
                } catch (Exception e) {
                    this.subscriber.onError(e);
                    return;
                }
            }
            this.subscriberTerminated = true;
            HttpException httpException = new HttpException(response);
            try {
                this.subscriber.onError(httpException);
            } catch (OnCompletedFailedException | OnErrorFailedException | OnErrorNotImplementedException e2) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e2);
            } catch (Throwable th) {
                Log.v("subscriberonErrorinner", httpException.getMessage());
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(httpException, th));
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (!this.subscriberTerminated) {
                this.subscriber.onError(th);
                Log.v("subscriberonError", th.getMessage());
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a Retrofit bug with the full stacktrace.");
            assertionError.initCause(th);
            RxJavaPlugins.getInstance().getErrorHandler().handleError(assertionError);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.subscriberTerminated) {
                return;
            }
            this.subscriber.onCompleted();
        }
    }
}