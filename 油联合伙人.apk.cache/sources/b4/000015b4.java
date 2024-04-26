package com.yltx.oil.partner.data.repository;

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
import rx.Observable;

/* loaded from: classes.dex */
public interface Repository {
    Observable<AllordersResponse> AllordersList(String str, String str2, String str3);

    Observable<HttpResult<AppLoginStatusResp>> AppLoginStatus();

    Observable<HttpResult<CommissionResponse>> CommissionList(String str);

    Observable<HttpResult<MessageForDetailsResponse>> DetailsList(int i);

    Observable<HttpResult<String>> Feedbackadd(String str, String str2, String str3);

    Observable<HttpResult<FuelCardDetailsResponse>> FuelDetailsList(String str);

    Observable<List<FuelCardListResponse>> FuelList(String str);

    Observable<GeneralizeResponse> GeneralizeList(String str, String str2, String str3);

    Observable<HttpResult<JoinResponse>> JoinList();

    Observable<HttpResult<List<MessageNotificationResponse>>> MessageList(int i);

    Observable<HttpResult<SCResponse>> SCList();

    Observable<HttpResult<List<SSLSResponse>>> SSLSList();

    Observable<List<SeekResponse>> SSList(String str);

    Observable<HttpResult<LoginInfo>> auto(String str, String str2);

    Observable<HttpResult<String>> bindBankCard(String str, String str2, String str3, String str4, String str5);

    Observable<HttpResult<String>> changeHeadPic(String str);

    Observable<HttpResult<String>> checkValidCode(String str, String str2);

    Observable<HttpResult<String>> complaint(String str, String str2, String str3, String str4, String str5, String str6);

    Observable<List<ComplaintResponse>> complaintOrder(String str, String str2, String str3, String str4);

    Observable<HttpResult<FinanceCardlResp>> financecardDetail(String str);

    Observable<List<FinanceCardlResp>> financecardList(String str);

    Observable<HttpResult<String>> findpwdUpdate(String str, String str2, String str3);

    Observable<LnvoicePayResp> generateRecord(String str, String str2, String str3, String str4, String str5);

    Observable<HttpResult<BankInfoResp>> getBankCard();

    Observable<HttpResult<InviteDetailResp>> getDetail(String str);

    Observable<HttpResult<List<Homebuttonconfiguration_Bean>>> getInfoList();

    Observable<HttpResult<String>> getIsAuth();

    Observable<RechargePayTypeResp> getOutPayTypeList(String str);

    Observable<HttpResult<PhoneResp>> getPhone();

    Observable<HttpResult<GiftCardResp>> getRechargeById(String str);

    Observable<List<GiftCardResp>> getRechargeList(String str);

    Observable<SfResp> getShareNum(String str, String str2, String str3);

    Observable<HttpResult<ShopDetailsResp>> getShopId(String str);

    Observable<List<ShopResp>> getShopRecommend(String str);

    Observable<List<ShopResp>> getShopSelect(String str);

    Observable<HttpResult<StoredValueCardResp>> getSotredCardById(String str);

    Observable<List<StoredValueCardResp>> getSotredCardList(String str);

    Observable<HttpResult<InviteResp>> getTotal(String str);

    Observable<TxHistoryResp> getTxList(String str);

    Observable<HttpResult<String>> getUsableBalance();

    Observable<String> getimgCode();

    Observable<HttpResult<List<BannerResponse>>> index();

    Observable<HttpResult<LoginInfo>> loginValidcode(String str, String str2);

    Observable<HttpResult<LoginInfo>> loginWithToken(String str, String str2);

    Observable<HttpResult<List<MemberResponse>>> memberCode();

    Observable<HttpResult<String>> modifCode(String str);

    Observable<HttpResult<List<MyfeedbackResponse>>> myfeedbackCode();

    Observable<HttpResult<LoginInfo>> partnerLogin();

    Observable<HttpResult<LoginInfo>> regist(String str, String str2, String str3);

    Observable<HttpResult<String>> updateBindBankCard(String str, String str2, String str3, String str4, String str5);

    Observable<HttpResult<String>> updatePhone(String str, String str2);

    Observable<UserAccountConsumeResponse> userAccountConsumeList(String str);

    Observable<HttpResult<String>> userAccountTxApply(String str, String str2);

    Observable<HttpResult<String>> validCode(String str, String str2, String str3);

    Observable<VersionResponse> versionCheck(String str);

    Observable<FragmentProfit_yjjsjl_Response> yjjsjl(String str);

    Observable<HttpResult<ManageResponse>> yzjyfx(String str);

    Observable<ManageResponse> yzskjlList(String str);
}