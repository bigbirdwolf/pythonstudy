package com.yltx.oil.partner.data.datasource;

import android.content.Context;
import com.google.gson.Gson;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.network.NetworkApi;
import com.yltx.oil.partner.data.network.SSLContextUtil;
import com.yltx.oil.partner.data.network.adapter.RxJavaCallAdapterFactory;
import com.yltx.oil.partner.data.network.interceptors.CommonParamsInterceptor;
import com.yltx.oil.partner.data.network.interceptors.RewriteCacheControlInterceptor;
import com.yltx.oil.partner.data.response.AppLoginStatusResp;
import com.yltx.oil.partner.data.response.BankInfoResp;
import com.yltx.oil.partner.data.response.BannerResponse;
import com.yltx.oil.partner.data.response.FinanceCardlResp;
import com.yltx.oil.partner.data.response.GiftCardResp;
import com.yltx.oil.partner.data.response.Homebuttonconfiguration_Bean;
import com.yltx.oil.partner.data.response.InviteDetailResp;
import com.yltx.oil.partner.data.response.InviteResp;
import com.yltx.oil.partner.data.response.LnvoicePayResp;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.data.response.PhoneResp;
import com.yltx.oil.partner.data.response.RechargePayTypeResp;
import com.yltx.oil.partner.data.response.SfResp;
import com.yltx.oil.partner.data.response.ShopDetailsResp;
import com.yltx.oil.partner.data.response.ShopResp;
import com.yltx.oil.partner.data.response.StoredValueCardResp;
import com.yltx.oil.partner.data.response.TxHistoryResp;
import com.yltx.oil.partner.data.response.VersionResponse;
import com.yltx.oil.partner.modules.home.response.MessageForDetailsResponse;
import com.yltx.oil.partner.modules.home.response.MessageNotificationResponse;
import com.yltx.oil.partner.modules.home.response.SCResponse;
import com.yltx.oil.partner.modules.home.response.SSLSResponse;
import com.yltx.oil.partner.modules.home.response.SeekResponse;
import com.yltx.oil.partner.modules.mine.response.ComplaintResponse;
import com.yltx.oil.partner.modules.mine.response.MemberResponse;
import com.yltx.oil.partner.modules.mine.response.MyfeedbackResponse;
import com.yltx.oil.partner.modules.oiltrade.response.FuelCardDetailsResponse;
import com.yltx.oil.partner.modules.oiltrade.response.FuelCardListResponse;
import com.yltx.oil.partner.modules.profit.response.AllordersResponse;
import com.yltx.oil.partner.modules.profit.response.CommissionResponse;
import com.yltx.oil.partner.modules.profit.response.FragmentProfit_yjjsjl_Response;
import com.yltx.oil.partner.modules.profit.response.GeneralizeResponse;
import com.yltx.oil.partner.modules.profit.response.JoinResponse;
import com.yltx.oil.partner.modules.profit.response.ManageResponse;
import com.yltx.oil.partner.modules.profit.response.UserAccountConsumeResponse;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

@Singleton
/* loaded from: classes.dex */
public class RestDataSource implements DataSource {
    private Context mContext;
    private Gson mGson = new Gson();
    private final NetworkApi networkApi;

    @Inject
    public RestDataSource(Context context, OkHttpClient okHttpClient) {
        this.mContext = context;
        OkHttpClient.Builder addNetworkInterceptor = okHttpClient.newBuilder().cache(new Cache(new File(context.getCacheDir(), "httpCache"), 104857600L)).addInterceptor(new RewriteCacheControlInterceptor(context)).addNetworkInterceptor(new RewriteCacheControlInterceptor(context));
        addNetworkInterceptor.sslSocketFactory(SSLContextUtil.getDefaultSLLContext().getSocketFactory());
        addNetworkInterceptor.interceptors().add(0, new CommonParamsInterceptor(context));
        this.networkApi = (NetworkApi) new Retrofit.Builder().baseUrl(Config.getAppApiUrl()).addConverterFactory(GsonConverterFactory.create(new Gson())).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(addNetworkInterceptor.build()).build().create(NetworkApi.class);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<LoginInfo>> auto(String str, String str2) {
        return this.networkApi.auto(str, str2).flatMap(new Func1<HttpResult<LoginInfo>, Observable<HttpResult<LoginInfo>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.1
            @Override // rx.functions.Func1
            public Observable<HttpResult<LoginInfo>> call(HttpResult<LoginInfo> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<AppLoginStatusResp>> AppLoginStatus() {
        return this.networkApi.AppLoginStatus().flatMap(new Func1<HttpResult<AppLoginStatusResp>, Observable<HttpResult<AppLoginStatusResp>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.2
            @Override // rx.functions.Func1
            public Observable<HttpResult<AppLoginStatusResp>> call(HttpResult<AppLoginStatusResp> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<LoginInfo>> loginWithToken(String str, String str2) {
        return this.networkApi.submitLogin(str, str2).flatMap(new Func1<HttpResult<LoginInfo>, Observable<HttpResult<LoginInfo>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.3
            @Override // rx.functions.Func1
            public Observable<HttpResult<LoginInfo>> call(HttpResult<LoginInfo> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<LoginInfo>> loginValidcode(String str, String str2) {
        return this.networkApi.loginValidcode(str, str2).flatMap(new Func1<HttpResult<LoginInfo>, Observable<HttpResult<LoginInfo>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.4
            @Override // rx.functions.Func1
            public Observable<HttpResult<LoginInfo>> call(HttpResult<LoginInfo> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<List<BannerResponse>>> index() {
        return this.networkApi.index().flatMap(new Func1<HttpResult<List<BannerResponse>>, Observable<HttpResult<List<BannerResponse>>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.5
            @Override // rx.functions.Func1
            public Observable<HttpResult<List<BannerResponse>>> call(HttpResult<List<BannerResponse>> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<List<Homebuttonconfiguration_Bean>>> getInfoList() {
        return this.networkApi.getInfoList().flatMap(new Func1<HttpResult<List<Homebuttonconfiguration_Bean>>, Observable<HttpResult<List<Homebuttonconfiguration_Bean>>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.6
            @Override // rx.functions.Func1
            public Observable<HttpResult<List<Homebuttonconfiguration_Bean>>> call(HttpResult<List<Homebuttonconfiguration_Bean>> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> findpwdUpdate(String str, String str2, String str3) {
        return this.networkApi.findpwdUpdate(str, str2, str3).flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.7
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> validCode(String str, String str2, String str3) {
        return this.networkApi.validCode(str, str2, str3).flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.8
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<PhoneResp>> getPhone() {
        return this.networkApi.getPhone().flatMap(new Func1<HttpResult<PhoneResp>, Observable<HttpResult<PhoneResp>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.9
            @Override // rx.functions.Func1
            public Observable<HttpResult<PhoneResp>> call(HttpResult<PhoneResp> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<List<MemberResponse>>> memberCode() {
        return this.networkApi.getMember().flatMap(new Func1<HttpResult<List<MemberResponse>>, Observable<HttpResult<List<MemberResponse>>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.10
            @Override // rx.functions.Func1
            public Observable<HttpResult<List<MemberResponse>>> call(HttpResult<List<MemberResponse>> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> modifCode(String str) {
        return this.networkApi.getModif(str).flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.11
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<List<MyfeedbackResponse>>> myfeedbackCode() {
        return this.networkApi.myfeedbackPic().flatMap(new Func1<HttpResult<List<MyfeedbackResponse>>, Observable<HttpResult<List<MyfeedbackResponse>>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.12
            @Override // rx.functions.Func1
            public Observable<HttpResult<List<MyfeedbackResponse>>> call(HttpResult<List<MyfeedbackResponse>> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> changeHeadPic(String str) {
        return this.networkApi.changeHeadPic(str).flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.13
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<LoginInfo>> regist(String str, String str2, String str3) {
        return this.networkApi.regist(str, str2, str3).flatMap(new Func1<HttpResult<LoginInfo>, Observable<HttpResult<LoginInfo>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.14
            @Override // rx.functions.Func1
            public Observable<HttpResult<LoginInfo>> call(HttpResult<LoginInfo> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> Feedbackadd(String str, String str2, String str3) {
        return this.networkApi.Feedbackadd(str, str2, str3).flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.15
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> checkValidCode(String str, String str2) {
        return this.networkApi.checkValidCode(str, str2).flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.16
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> updatePhone(String str, String str2) {
        return this.networkApi.updatePhone(str, str2).flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.17
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<FinanceCardlResp>> financecardList(String str) {
        return this.networkApi.financecardList(str).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$kJxlkD50pIWWsB3Uj05CecPstVo
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<StoredValueCardResp>> getSotredCardList(String str) {
        return this.networkApi.getSotredCardList(str).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$vZd_0pOeQ1jTuf0nwNU5lmGogRA
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<GiftCardResp>> getRechargeList(String str) {
        return this.networkApi.getRechargeList(str).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$jkV6eo6QKoTAfO6WHwP-6kwU_Jw
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<FinanceCardlResp>> financecardDetail(String str) {
        return this.networkApi.financecardDetail(str).flatMap(new Func1<HttpResult<FinanceCardlResp>, Observable<HttpResult<FinanceCardlResp>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.18
            @Override // rx.functions.Func1
            public Observable<HttpResult<FinanceCardlResp>> call(HttpResult<FinanceCardlResp> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<StoredValueCardResp>> getSotredCardById(String str) {
        return this.networkApi.getSotredCardById(str).flatMap(new Func1<HttpResult<StoredValueCardResp>, Observable<HttpResult<StoredValueCardResp>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.19
            @Override // rx.functions.Func1
            public Observable<HttpResult<StoredValueCardResp>> call(HttpResult<StoredValueCardResp> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<GiftCardResp>> getRechargeById(String str) {
        return this.networkApi.getRechargeById(str).flatMap(new Func1<HttpResult<GiftCardResp>, Observable<HttpResult<GiftCardResp>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.20
            @Override // rx.functions.Func1
            public Observable<HttpResult<GiftCardResp>> call(HttpResult<GiftCardResp> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<LoginInfo>> partnerLogin() {
        return this.networkApi.partnerLogin().flatMap(new Func1<HttpResult<LoginInfo>, Observable<HttpResult<LoginInfo>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.21
            @Override // rx.functions.Func1
            public Observable<HttpResult<LoginInfo>> call(HttpResult<LoginInfo> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<List<MessageNotificationResponse>>> MessageList(int i) {
        return this.networkApi.MessageList(i).flatMap(new Func1<HttpResult<List<MessageNotificationResponse>>, Observable<HttpResult<List<MessageNotificationResponse>>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.22
            @Override // rx.functions.Func1
            public Observable<HttpResult<List<MessageNotificationResponse>>> call(HttpResult<List<MessageNotificationResponse>> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<MessageForDetailsResponse>> DetailsList(int i) {
        return this.networkApi.DetailsList(i).flatMap(new Func1<HttpResult<MessageForDetailsResponse>, Observable<HttpResult<MessageForDetailsResponse>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.23
            @Override // rx.functions.Func1
            public Observable<HttpResult<MessageForDetailsResponse>> call(HttpResult<MessageForDetailsResponse> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> complaint(String str, String str2, String str3, String str4, String str5, String str6) {
        return this.networkApi.complaint(str, str2, str3, str4, str5, str6).flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.24
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<InviteResp>> getTotal(String str) {
        return this.networkApi.getTotal(str).flatMap(new Func1<HttpResult<InviteResp>, Observable<HttpResult<InviteResp>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.25
            @Override // rx.functions.Func1
            public Observable<HttpResult<InviteResp>> call(HttpResult<InviteResp> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<InviteDetailResp>> getDetail(String str) {
        return this.networkApi.getDetail(str).flatMap(new Func1<HttpResult<InviteDetailResp>, Observable<HttpResult<InviteDetailResp>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.26
            @Override // rx.functions.Func1
            public Observable<HttpResult<InviteDetailResp>> call(HttpResult<InviteDetailResp> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<ComplaintResponse>> complaintOrder(String str, String str2, String str3, String str4) {
        return this.networkApi.complaintOrder(str, str2, str3, str4).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$jjdoMahI-tUezm4zsJ_YNwFtZOs
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<FuelCardListResponse>> FuelList(String str) {
        return this.networkApi.FuelList(str).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$oXNIUPEwuctztKnj497oClnIq9E
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<FuelCardDetailsResponse>> FuelDetailsList(String str) {
        return this.networkApi.FuelDetailsList(str).flatMap(new Func1<HttpResult<FuelCardDetailsResponse>, Observable<HttpResult<FuelCardDetailsResponse>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.27
            @Override // rx.functions.Func1
            public Observable<HttpResult<FuelCardDetailsResponse>> call(HttpResult<FuelCardDetailsResponse> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> getUsableBalance() {
        return this.networkApi.getUsableBalance().flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.28
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<BankInfoResp>> getBankCard() {
        return this.networkApi.getBankCard().flatMap(new Func1<HttpResult<BankInfoResp>, Observable<HttpResult<BankInfoResp>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.29
            @Override // rx.functions.Func1
            public Observable<HttpResult<BankInfoResp>> call(HttpResult<BankInfoResp> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<TxHistoryResp> getTxList(String str) {
        return this.networkApi.getTxList(str).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$MWhFmpe0wuvdfRwPjqVnf4z4FDc
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> updateBindBankCard(String str, String str2, String str3, String str4, String str5) {
        return this.networkApi.updateBindBankCard(str, str2, str3, str4, str5).flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.30
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> userAccountTxApply(String str, String str2) {
        return this.networkApi.userAccountTxApply(str, str2).flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.31
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<AllordersResponse> AllordersList(String str, String str2, String str3) {
        return this.networkApi.AllordersList(str, str2, str3).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$rt_U28qfDh99HxLL1Gvt1eA5JdI
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<CommissionResponse>> CommissionList(String str) {
        return this.networkApi.CommissionList(str).flatMap(new Func1<HttpResult<CommissionResponse>, Observable<HttpResult<CommissionResponse>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.32
            @Override // rx.functions.Func1
            public Observable<HttpResult<CommissionResponse>> call(HttpResult<CommissionResponse> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<JoinResponse>> JoinList() {
        return this.networkApi.JoinList().flatMap(new Func1<HttpResult<JoinResponse>, Observable<HttpResult<JoinResponse>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.33
            @Override // rx.functions.Func1
            public Observable<HttpResult<JoinResponse>> call(HttpResult<JoinResponse> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<ManageResponse>> yzjyfx(String str) {
        return this.networkApi.yzjyfx(str).flatMap(new Func1<HttpResult<ManageResponse>, Observable<HttpResult<ManageResponse>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.34
            @Override // rx.functions.Func1
            public Observable<HttpResult<ManageResponse>> call(HttpResult<ManageResponse> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<GeneralizeResponse> GeneralizeList(String str, String str2, String str3) {
        return this.networkApi.GeneralizeList(str, str2, str3).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$CoNL9YNCIkvP1x4isHKr8-734bA
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<ManageResponse> yzskjlList(String str) {
        return this.networkApi.yzjsjlList(str).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$wXX-p9RwpSSnONckL-HUjFAjUe4
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> getIsAuth() {
        return this.networkApi.getIsAuth().flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.35
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> bindBankCard(String str, String str2, String str3, String str4, String str5) {
        return this.networkApi.bindBankCard(str, str2, str3, str4, str5).flatMap(new Func1<HttpResult<String>, Observable<HttpResult<String>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.36
            @Override // rx.functions.Func1
            public Observable<HttpResult<String>> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<FragmentProfit_yjjsjl_Response> yjjsjl(String str) {
        return this.networkApi.yjjsjl(str).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$l5VOuwyxuE4_wd4zCK0b-CruBFw
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<VersionResponse> versionCheck(String str) {
        return this.networkApi.versionCheck(str).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$bfpncjQQqobShAahBGq5fgxxIog
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<ShopResp>> getShopSelect(String str) {
        return this.networkApi.getShopSelect(str).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$pT59U_sqVW8skzrotFbnIJ8Lm_s
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<ShopDetailsResp>> getShopId(String str) {
        return this.networkApi.getShopId(str).flatMap(new Func1<HttpResult<ShopDetailsResp>, Observable<HttpResult<ShopDetailsResp>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.37
            @Override // rx.functions.Func1
            public Observable<HttpResult<ShopDetailsResp>> call(HttpResult<ShopDetailsResp> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<SeekResponse>> SSList(String str) {
        return this.networkApi.SSList(str).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$ZtN1EFf5KfwOK6MKUm_Pa1Ajv0U
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<List<SSLSResponse>>> SSLSList() {
        return this.networkApi.SSLSList().flatMap(new Func1<HttpResult<List<SSLSResponse>>, Observable<HttpResult<List<SSLSResponse>>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.38
            @Override // rx.functions.Func1
            public Observable<HttpResult<List<SSLSResponse>>> call(HttpResult<List<SSLSResponse>> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<SCResponse>> SCList() {
        return this.networkApi.SCList().flatMap(new Func1<HttpResult<SCResponse>, Observable<HttpResult<SCResponse>>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.39
            @Override // rx.functions.Func1
            public Observable<HttpResult<SCResponse>> call(HttpResult<SCResponse> httpResult) {
                return Observable.just(httpResult);
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<SfResp> getShareNum(String str, String str2, String str3) {
        return this.networkApi.getShareNum(str, str2, str3).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$TsJmEWl5UhidCmy0kICg29Z4KMk
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<ShopResp>> getShopRecommend(String str) {
        return this.networkApi.getShopRecommend(str).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$qbx1rzJBaBY57jCYDEJut_10z3Y
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<UserAccountConsumeResponse> userAccountConsumeList(String str) {
        return this.networkApi.userAccountConsumeList(str).flatMap(new Func1() { // from class: com.yltx.oil.partner.data.datasource.-$$Lambda$RestDataSource$3ThIKgR-foxulKDVeN9i5sIXzCY
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Observable just;
                just = Observable.just(((HttpResult) obj).getData());
                return just;
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<RechargePayTypeResp> getOutPayTypeList(String str) {
        return this.networkApi.getOutPayTypeList(str).flatMap(new Func1<HttpResult<RechargePayTypeResp>, Observable<RechargePayTypeResp>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.40
            @Override // rx.functions.Func1
            public Observable<RechargePayTypeResp> call(HttpResult<RechargePayTypeResp> httpResult) {
                return Observable.just(httpResult.getData());
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<LnvoicePayResp> generateRecord(String str, String str2, String str3, String str4, String str5) {
        return this.networkApi.generateRecord(str, str2, str3, str4, str5).flatMap(new Func1<HttpResult<LnvoicePayResp>, Observable<LnvoicePayResp>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.41
            @Override // rx.functions.Func1
            public Observable<LnvoicePayResp> call(HttpResult<LnvoicePayResp> httpResult) {
                return Observable.just(httpResult.getData());
            }
        });
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<String> getimgCode() {
        return this.networkApi.getimgCode().flatMap(new Func1<HttpResult<String>, Observable<String>>() { // from class: com.yltx.oil.partner.data.datasource.RestDataSource.42
            @Override // rx.functions.Func1
            public Observable<String> call(HttpResult<String> httpResult) {
                return Observable.just(httpResult.getData());
            }
        });
    }
}