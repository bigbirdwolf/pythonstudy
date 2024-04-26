package com.yltx.oil.partner.modules.mine.presenter;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.data.response.PhoneResp;
import com.yltx.oil.partner.modules.mine.domain.MemberUseCase;
import com.yltx.oil.partner.modules.mine.domain.PersonalCenterUseCase;
import com.yltx.oil.partner.modules.mine.domain.PhoneUseCase;
import com.yltx.oil.partner.modules.mine.response.MemberResponse;
import com.yltx.oil.partner.modules.mine.view.MemberView;
import com.yltx.oil.partner.mvp.controller.Presenter;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.mvp.views.InterfaceView;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class MemberPresenter implements Presenter {
    private MemberUseCase memberUseCase;
    private PersonalCenterUseCase personalCenterUseCase;
    private PhoneUseCase phoneUseCase;
    private MemberView view;

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onPause() {
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onResume() {
    }

    @Inject
    public MemberPresenter(MemberUseCase memberUseCase, PhoneUseCase phoneUseCase, PersonalCenterUseCase personalCenterUseCase) {
        this.memberUseCase = memberUseCase;
        this.phoneUseCase = phoneUseCase;
        this.personalCenterUseCase = personalCenterUseCase;
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void onDestroy() {
        this.memberUseCase.unSubscribe();
        this.phoneUseCase.unSubscribe();
        this.personalCenterUseCase.unSubscribe();
    }

    @Override // com.yltx.oil.partner.mvp.controller.Presenter
    public void attachView(InterfaceView interfaceView) {
        this.view = (MemberView) interfaceView;
    }

    public void submitMember() {
        this.memberUseCase.execute(new MemberSubscriber(this.view));
    }

    public void PersonalCenter() {
        this.personalCenterUseCase.execute(new PersonalCenterSubscriber(this.view));
    }

    public void getPhone() {
        this.phoneUseCase.execute(new PhoneSubscriber(this.view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MemberSubscriber extends ProgressSubscriber<HttpResult<List<MemberResponse>>> {
        public MemberSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            MemberPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<List<MemberResponse>> httpResult) {
            super.onNext((MemberSubscriber) httpResult);
            MemberPresenter.this.view.onMember(httpResult);
        }
    }

    /* loaded from: classes.dex */
    private class PhoneSubscriber extends ProgressSubscriber<HttpResult<PhoneResp>> {
        public PhoneSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            MemberPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<PhoneResp> httpResult) {
            super.onNext((PhoneSubscriber) httpResult);
            MemberPresenter.this.view.onphone(httpResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class PersonalCenterSubscriber extends ProgressSubscriber<HttpResult<LoginInfo>> {
        public PersonalCenterSubscriber(LoadDataView loadDataView) {
            super(loadDataView);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            MemberPresenter.this.view.onError(th);
        }

        @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
        public void onNext(HttpResult<LoginInfo> httpResult) {
            super.onNext((PersonalCenterSubscriber) httpResult);
            MemberPresenter.this.view.onPersonalCenter(httpResult);
        }
    }
}