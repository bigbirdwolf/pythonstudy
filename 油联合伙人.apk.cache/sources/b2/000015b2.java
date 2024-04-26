package com.yltx.oil.partner.data.repository;

import android.content.Context;
import com.yltx.oil.partner.data.datasource.DataSourceFactory;
import com.yltx.oil.partner.data.network.HttpResult;
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
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

@Singleton
/* loaded from: classes.dex */
public class DataRepository implements Repository {
    private final Context mContext;
    private final DataSourceFactory mDataSourceFactory;

    @Inject
    public DataRepository(Context context, DataSourceFactory dataSourceFactory) {
        this.mDataSourceFactory = dataSourceFactory;
        this.mContext = context;
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<LoginInfo>> auto(String str, String str2) {
        return this.mDataSourceFactory.createRestDataSource().auto(str, str2);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<LoginInfo>> loginWithToken(String str, String str2) {
        return this.mDataSourceFactory.createRestDataSource().loginWithToken(str, str2);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<AppLoginStatusResp>> AppLoginStatus() {
        return this.mDataSourceFactory.createRestDataSource().AppLoginStatus();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<LoginInfo>> loginValidcode(String str, String str2) {
        return this.mDataSourceFactory.createRestDataSource().loginValidcode(str, str2);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<List<BannerResponse>>> index() {
        return this.mDataSourceFactory.createRestDataSource().index();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<List<Homebuttonconfiguration_Bean>>> getInfoList() {
        return this.mDataSourceFactory.createRestDataSource().getInfoList();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> findpwdUpdate(String str, String str2, String str3) {
        return this.mDataSourceFactory.createRestDataSource().findpwdUpdate(str, str2, str3);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> validCode(String str, String str2, String str3) {
        return this.mDataSourceFactory.createRestDataSource().validCode(str, str2, str3);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<PhoneResp>> getPhone() {
        return this.mDataSourceFactory.createRestDataSource().getPhone();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<List<MemberResponse>>> memberCode() {
        return this.mDataSourceFactory.createRestDataSource().memberCode();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> modifCode(String str) {
        return this.mDataSourceFactory.createRestDataSource().modifCode(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<List<MyfeedbackResponse>>> myfeedbackCode() {
        return this.mDataSourceFactory.createRestDataSource().myfeedbackCode();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> changeHeadPic(String str) {
        return this.mDataSourceFactory.createRestDataSource().changeHeadPic(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<LoginInfo>> regist(String str, String str2, String str3) {
        return this.mDataSourceFactory.createRestDataSource().regist(str, str2, str3);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> Feedbackadd(String str, String str2, String str3) {
        return this.mDataSourceFactory.createRestDataSource().Feedbackadd(str, str2, str3);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> checkValidCode(String str, String str2) {
        return this.mDataSourceFactory.createRestDataSource().checkValidCode(str, str2);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> updatePhone(String str, String str2) {
        return this.mDataSourceFactory.createRestDataSource().updatePhone(str, str2);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<FinanceCardlResp>> financecardList(String str) {
        return this.mDataSourceFactory.createRestDataSource().financecardList(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<StoredValueCardResp>> getSotredCardList(String str) {
        return this.mDataSourceFactory.createRestDataSource().getSotredCardList(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<GiftCardResp>> getRechargeList(String str) {
        return this.mDataSourceFactory.createRestDataSource().getRechargeList(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<FinanceCardlResp>> financecardDetail(String str) {
        return this.mDataSourceFactory.createRestDataSource().financecardDetail(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<StoredValueCardResp>> getSotredCardById(String str) {
        return this.mDataSourceFactory.createRestDataSource().getSotredCardById(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<GiftCardResp>> getRechargeById(String str) {
        return this.mDataSourceFactory.createRestDataSource().getRechargeById(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<LoginInfo>> partnerLogin() {
        return this.mDataSourceFactory.createRestDataSource().partnerLogin();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<List<MessageNotificationResponse>>> MessageList(int i) {
        return this.mDataSourceFactory.createRestDataSource().MessageList(i);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<MessageForDetailsResponse>> DetailsList(int i) {
        return this.mDataSourceFactory.createRestDataSource().DetailsList(i);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> complaint(String str, String str2, String str3, String str4, String str5, String str6) {
        return this.mDataSourceFactory.createRestDataSource().complaint(str, str2, str3, str4, str5, str6);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<ComplaintResponse>> complaintOrder(String str, String str2, String str3, String str4) {
        return this.mDataSourceFactory.createRestDataSource().complaintOrder(str, str2, str3, str4);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<FuelCardListResponse>> FuelList(String str) {
        return this.mDataSourceFactory.createRestDataSource().FuelList(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<FragmentProfit_yjjsjl_Response> yjjsjl(String str) {
        return this.mDataSourceFactory.createRestDataSource().yjjsjl(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<FuelCardDetailsResponse>> FuelDetailsList(String str) {
        return this.mDataSourceFactory.createRestDataSource().FuelDetailsList(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> getUsableBalance() {
        return this.mDataSourceFactory.createRestDataSource().getUsableBalance();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<BankInfoResp>> getBankCard() {
        return this.mDataSourceFactory.createRestDataSource().getBankCard();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> updateBindBankCard(String str, String str2, String str3, String str4, String str5) {
        return this.mDataSourceFactory.createRestDataSource().updateBindBankCard(str, str2, str3, str4, str5);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> getIsAuth() {
        return this.mDataSourceFactory.createRestDataSource().getIsAuth();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> bindBankCard(String str, String str2, String str3, String str4, String str5) {
        return this.mDataSourceFactory.createRestDataSource().bindBankCard(str, str2, str3, str4, str5);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<InviteResp>> getTotal(String str) {
        return this.mDataSourceFactory.createRestDataSource().getTotal(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<InviteDetailResp>> getDetail(String str) {
        return this.mDataSourceFactory.createRestDataSource().getDetail(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<AllordersResponse> AllordersList(String str, String str2, String str3) {
        return this.mDataSourceFactory.createRestDataSource().AllordersList(str, str2, str3);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<CommissionResponse>> CommissionList(String str) {
        return this.mDataSourceFactory.createRestDataSource().CommissionList(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<JoinResponse>> JoinList() {
        return this.mDataSourceFactory.createRestDataSource().JoinList();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<VersionResponse> versionCheck(String str) {
        return this.mDataSourceFactory.createRestDataSource().versionCheck(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<ManageResponse>> yzjyfx(String str) {
        return this.mDataSourceFactory.createRestDataSource().yzjyfx(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<String>> userAccountTxApply(String str, String str2) {
        return this.mDataSourceFactory.createRestDataSource().userAccountTxApply(str, str2);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<SeekResponse>> SSList(String str) {
        return this.mDataSourceFactory.createRestDataSource().SSList(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<List<SSLSResponse>>> SSLSList() {
        return this.mDataSourceFactory.createRestDataSource().SSLSList();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<SCResponse>> SCList() {
        return this.mDataSourceFactory.createRestDataSource().SCList();
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<SfResp> getShareNum(String str, String str2, String str3) {
        return this.mDataSourceFactory.createRestDataSource().getShareNum(str, str2, str3);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<RechargePayTypeResp> getOutPayTypeList(String str) {
        return this.mDataSourceFactory.createRestDataSource().getOutPayTypeList(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<LnvoicePayResp> generateRecord(String str, String str2, String str3, String str4, String str5) {
        return this.mDataSourceFactory.createRestDataSource().generateRecord(str, str2, str3, str4, str5);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<TxHistoryResp> getTxList(String str) {
        return this.mDataSourceFactory.createRestDataSource().getTxList(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<ShopResp>> getShopSelect(String str) {
        return this.mDataSourceFactory.createRestDataSource().getShopSelect(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<HttpResult<ShopDetailsResp>> getShopId(String str) {
        return this.mDataSourceFactory.createRestDataSource().getShopId(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<List<ShopResp>> getShopRecommend(String str) {
        return this.mDataSourceFactory.createRestDataSource().getShopRecommend(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<GeneralizeResponse> GeneralizeList(String str, String str2, String str3) {
        return this.mDataSourceFactory.createRestDataSource().GeneralizeList(str, str2, str3);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<ManageResponse> yzskjlList(String str) {
        return this.mDataSourceFactory.createRestDataSource().yzskjlList(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<UserAccountConsumeResponse> userAccountConsumeList(String str) {
        return this.mDataSourceFactory.createRestDataSource().userAccountConsumeList(str);
    }

    @Override // com.yltx.oil.partner.data.repository.Repository
    public Observable<String> getimgCode() {
        return this.mDataSourceFactory.createRestDataSource().getimgCode();
    }
}