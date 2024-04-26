package com.yltx.oil.partner.data.network;

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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/* loaded from: classes.dex */
public interface NetworkApi {
    @FormUrlEncoded
    @POST("api/v1/partnerOrder/orderInfo")
    Observable<HttpResult<AllordersResponse>> AllordersList(@Field("type") String str, @Field("finalStatus") String str2, @Field("pageNo") String str3);

    @GET("api/v1/user/detail")
    Observable<HttpResult<AppLoginStatusResp>> AppLoginStatus();

    @FormUrlEncoded
    @POST("api/v1/partnerIncome/commissionAnalysis")
    Observable<HttpResult<CommissionResponse>> CommissionList(@Field("button") String str);

    @FormUrlEncoded
    @POST("api/v1/partnerMsg/getMsgDetail")
    Observable<HttpResult<MessageForDetailsResponse>> DetailsList(@Field("rowid") int i);

    @FormUrlEncoded
    @POST("api/v1/partnerFeedback/add")
    Observable<HttpResult<String>> Feedbackadd(@Field("context") String str, @Field("phone") String str2, @Field("name") String str3);

    @FormUrlEncoded
    @POST("api/v1/fuelCardGoods/getFueCardDetail")
    Observable<HttpResult<FuelCardDetailsResponse>> FuelDetailsList(@Field("rowId") String str);

    @FormUrlEncoded
    @POST("api/v1/fuelCardGoods/getFuelCard")
    Observable<HttpResult<List<FuelCardListResponse>>> FuelList(@Field("pageNo") String str);

    @FormUrlEncoded
    @POST("api/v1/partnerShareData/showPartnerShareData")
    Observable<HttpResult<GeneralizeResponse>> GeneralizeList(@Field("beginTime") String str, @Field("endTime") String str2, @Field("pageNo") String str3);

    @GET("api/v1/partnerIncome/isStationManager")
    Observable<HttpResult<JoinResponse>> JoinList();

    @FormUrlEncoded
    @POST("api/v1/partnerMsg/getMsg")
    Observable<HttpResult<List<MessageNotificationResponse>>> MessageList(@Field("pageNo") int i);

    @GET("api/v1/getShop/deleteSearchList")
    Observable<HttpResult<SCResponse>> SCList();

    @GET("api/v1/getShop/shopSearchList")
    Observable<HttpResult<List<SSLSResponse>>> SSLSList();

    @FormUrlEncoded
    @POST("api/v1/getShop/shopSearch")
    Observable<HttpResult<List<SeekResponse>>> SSList(@Field("goodsName") String str);

    @FormUrlEncoded
    @POST("api/v1/partnerLogin/login/auto")
    Observable<HttpResult<LoginInfo>> auto(@Field("token") String str, @Field("userId") String str2);

    @FormUrlEncoded
    @POST("api/v1/minInfo/bindBankCard")
    Observable<HttpResult<String>> bindBankCard(@Field("realname") String str, @Field("idcard") String str2, @Field("bankNo") String str3, @Field("bankPhone") String str4, @Field("validCode") String str5);

    @FormUrlEncoded
    @POST("api/v1/minInfo/changeHeadPic")
    Observable<HttpResult<String>> changeHeadPic(@Field("picUrl") String str);

    @FormUrlEncoded
    @POST("api/v1/minInfo/checkValidCode")
    Observable<HttpResult<String>> checkValidCode(@Field("phone") String str, @Field("validCode") String str2);

    @FormUrlEncoded
    @POST("api/v1/partner/complaint")
    Observable<HttpResult<String>> complaint(@Field("phone") String str, @Field("context") String str2, @Field("reason") String str3, @Field("url") String str4, @Field("voucherCode") String str5, @Field("name") String str6);

    @FormUrlEncoded
    @POST("api/v1/partner/complaintOrder")
    Observable<HttpResult<List<ComplaintResponse>>> complaintOrder(@Field("type") String str, @Field("status") String str2, @Field("voucherCode") String str3, @Field("pageNo") String str4);

    @FormUrlEncoded
    @POST("api/v1/partnerAppfinancecard/financecardDetail")
    Observable<HttpResult<FinanceCardlResp>> financecardDetail(@Field("rowId") String str);

    @FormUrlEncoded
    @POST("api/v1/partnerAppfinancecard/financecardList")
    Observable<HttpResult<List<FinanceCardlResp>>> financecardList(@Field("pageNumber") String str);

    @FormUrlEncoded
    @POST("api/v1/user/findpwdUpdate")
    Observable<HttpResult<String>> findpwdUpdate(@Field("phone") String str, @Field("validCode") String str2, @Field("password") String str3);

    @FormUrlEncoded
    @POST("api/v1/ylRechargeRecord/generatePartnerRecord")
    Observable<HttpResult<LnvoicePayResp>> generateRecord(@Field("orderAmount") String str, @Field("caleTotalAmount") String str2, @Field("outUsePay") String str3, @Field("bankCode") String str4, @Field("outPayAmount") String str5);

    @GET("api/v1/pc/user/bankCard")
    Observable<HttpResult<BankInfoResp>> getBankCard();

    @GET("api/v1/PartnerInvitation/getDetail")
    Observable<HttpResult<InviteDetailResp>> getDetail(@Query("userId") String str);

    @GET("api/partnerIcon/getInfoList")
    Observable<HttpResult<List<Homebuttonconfiguration_Bean>>> getInfoList();

    @GET("api/v1/userAccount/getIsAuth")
    Observable<HttpResult<String>> getIsAuth();

    @GET("api/v1/partnerSetting/getLevelInfo")
    Observable<HttpResult<List<MemberResponse>>> getMember();

    @FormUrlEncoded
    @POST("api/v1/minInfo/updateNickName")
    Observable<HttpResult<String>> getModif(@Field("nickName") String str);

    @FormUrlEncoded
    @POST("api/v1/ylRechargeRecord/getPayTypeList")
    Observable<HttpResult<RechargePayTypeResp>> getOutPayTypeList(@Field("clientType") String str);

    @GET("api/v1/partnerSetting/getPhone")
    Observable<HttpResult<PhoneResp>> getPhone();

    @FormUrlEncoded
    @POST("api/v1/partnerCommissionRebate/getRechargeById")
    Observable<HttpResult<GiftCardResp>> getRechargeById(@Field("rowId") String str);

    @FormUrlEncoded
    @POST("api/v1/partnerCommissionRebate/getRechargeList")
    Observable<HttpResult<List<GiftCardResp>>> getRechargeList(@Field("pageNo") String str);

    @FormUrlEncoded
    @POST("api/v1/calculateWatchCount/getShareNum")
    Observable<HttpResult<SfResp>> getShareNum(@Field("recommenderId") String str, @Field("goodsId") String str2, @Field("type") String str3);

    @FormUrlEncoded
    @POST("api/v1/getShop/getShopId")
    Observable<HttpResult<ShopDetailsResp>> getShopId(@Field("specsId") String str);

    @FormUrlEncoded
    @POST("api/v1/getShop/getShopRecommend")
    Observable<HttpResult<List<ShopResp>>> getShopRecommend(@Field("pageNo") String str);

    @FormUrlEncoded
    @POST("api/v1/getShop/getShopSelect")
    Observable<HttpResult<List<ShopResp>>> getShopSelect(@Field("pageNo") String str);

    @FormUrlEncoded
    @POST("api/v1/partnerCommissionRebate/getSotredCardById")
    Observable<HttpResult<StoredValueCardResp>> getSotredCardById(@Field("rowId") String str);

    @FormUrlEncoded
    @POST("api/v1/partnerCommissionRebate/getSotredCardList")
    Observable<HttpResult<List<StoredValueCardResp>>> getSotredCardList(@Field("pageNo") String str);

    @GET("api/v1/PartnerInvitation/getTotal")
    Observable<HttpResult<InviteResp>> getTotal(@Query("userId") String str);

    @FormUrlEncoded
    @POST("api/v1/userAccount/userAccountTxList")
    Observable<HttpResult<TxHistoryResp>> getTxList(@Field("pageNo") String str);

    @GET("api/v1/userAccount/getUsableBalance")
    Observable<HttpResult<String>> getUsableBalance();

    @GET("api/v1/verify/imgCode")
    Observable<HttpResult<String>> getimgCode();

    @GET("api/partnerBanner/index")
    Observable<HttpResult<List<BannerResponse>>> index();

    @FormUrlEncoded
    @POST("api/v1/partnerLogin/loginValidcode")
    Observable<HttpResult<LoginInfo>> loginValidcode(@Field("phone") String str, @Field("validCode") String str2);

    @GET("api/v1/partnerFeedback/list")
    Observable<HttpResult<List<MyfeedbackResponse>>> myfeedbackPic();

    @GET("api/v1/partnerLogin")
    Observable<HttpResult<LoginInfo>> partnerLogin();

    @FormUrlEncoded
    @POST("api/v1/partnerLogin/regist")
    Observable<HttpResult<LoginInfo>> regist(@Field("phone") String str, @Field("validCode") String str2, @Field("password") String str3);

    @FormUrlEncoded
    @POST("api/v1/partnerLogin/login")
    Observable<HttpResult<LoginInfo>> submitLogin(@Field("phone") String str, @Field("password") String str2);

    @FormUrlEncoded
    @POST("api/v1/minInfo/updateBindBankCard")
    Observable<HttpResult<String>> updateBindBankCard(@Field("realname") String str, @Field("idcard") String str2, @Field("bankNo") String str3, @Field("bankPhone") String str4, @Field("validCode") String str5);

    @FormUrlEncoded
    @POST("api/v1/minInfo/updatePhone")
    Observable<HttpResult<String>> updatePhone(@Field("phone") String str, @Field("validCode") String str2);

    @FormUrlEncoded
    @POST("api/v1/userAccount/userAccountConsumeList")
    Observable<HttpResult<UserAccountConsumeResponse>> userAccountConsumeList(@Field("pageNo") String str);

    @FormUrlEncoded
    @POST("api/v1/userAccount/userAccountTxApply")
    Observable<HttpResult<String>> userAccountTxApply(@Field("txMoney") String str, @Field("accountPwd") String str2);

    @FormUrlEncoded
    @POST("api/v1/validCode")
    Observable<HttpResult<String>> validCode(@Field("phone") String str, @Field("type") String str2, @Field("imgCode") String str3);

    @FormUrlEncoded
    @POST("api/v1/partnerSetting/version")
    Observable<HttpResult<VersionResponse>> versionCheck(@Field("platform") String str);

    @FormUrlEncoded
    @POST("api/v1/partnerIncome/commissionRecord")
    Observable<HttpResult<FragmentProfit_yjjsjl_Response>> yjjsjl(@Field("pageNo") String str);

    @FormUrlEncoded
    @POST("api/v1/partnerIncome/stationData")
    Observable<HttpResult<ManageResponse>> yzjsjlList(@Field("pageNo") String str);

    @FormUrlEncoded
    @POST("api/v1/partnerIncome/stationData")
    Observable<HttpResult<ManageResponse>> yzjyfx(@Field("pageNo") String str);
}