package com.yltx.oil.partner.injections.components;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.base.PartnerApplication_MembersInjector;
import com.yltx.oil.partner.data.datasource.RestDataSource;
import com.yltx.oil.partner.data.network.interceptors.HttpLoggingInterceptor;
import com.yltx.oil.partner.data.repository.Repository;
import com.yltx.oil.partner.injections.components.GlobalComponent;
import com.yltx.oil.partner.injections.instrumentation.ApplicationInstrumentation;
import com.yltx.oil.partner.injections.instrumentation.HttpLoggingInstrumentation;
import com.yltx.oil.partner.injections.instrumentation.StethoInstrumentation;
import com.yltx.oil.partner.injections.modules.AppModule;
import com.yltx.oil.partner.injections.modules.AppModule_ProvideContextFactory;
import com.yltx.oil.partner.injections.modules.BuildersModule_AccountdetailsActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_AllFragment;
import com.yltx.oil.partner.injections.modules.BuildersModule_AllordersFragment;
import com.yltx.oil.partner.injections.modules.BuildersModule_ApplyingPartnerActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_BindingbankcardsActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_ClassificationActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_ClipImageActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_CollectionRecordActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_CommoditySharingInforActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_ComplainValetActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_ComplaintActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_DataanalysisActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_EffectiveorderFragment;
import com.yltx.oil.partner.injections.modules.BuildersModule_FailureoftheorderFragment;
import com.yltx.oil.partner.injections.modules.BuildersModule_FeedbackActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_ForgetPasswordActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_FragmentHome;
import com.yltx.oil.partner.injections.modules.BuildersModule_FragmentMine;
import com.yltx.oil.partner.injections.modules.BuildersModule_FragmentOilTrade;
import com.yltx.oil.partner.injections.modules.BuildersModule_FragmentProfit;
import com.yltx.oil.partner.injections.modules.BuildersModule_FragmentPwdLogin;
import com.yltx.oil.partner.injections.modules.BuildersModule_GiftcardFragment;
import com.yltx.oil.partner.injections.modules.BuildersModule_HelpCenterActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_InvitationDetailsActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_InviteCourtesyActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_InviteFriendsActivityInjector;
import com.yltx.oil.partner.injections.modules.BuildersModule_LoginActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_MainActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_MessageBulletinActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_MessageDetailsActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_MessageHomeActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_MineInfoActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_ModificationBankCardsActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_ModifyNicknameActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_NonoilLoginActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_NoviceGuideActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_OilCardFragment;
import com.yltx.oil.partner.injections.modules.BuildersModule_OptiondateActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_OrderdetailsActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_PhoneActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_RechargeActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_RefuelingCardFragment;
import com.yltx.oil.partner.injections.modules.BuildersModule_RefundFragment;
import com.yltx.oil.partner.injections.modules.BuildersModule_RegisterActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_SearchHomeActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_SearchResultActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_SettlementrecordsActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_ShareDetailsActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_ShopFragment;
import com.yltx.oil.partner.injections.modules.BuildersModule_SplashActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_StoredValueCardFragment;
import com.yltx.oil.partner.injections.modules.BuildersModule_TxHistoryActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_UpdatePwdActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_WebActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_WithdrawActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_YlspLoginActivity;
import com.yltx.oil.partner.injections.modules.BuildersModule_YltxLoginActivity;
import com.yltx.oil.partner.injections.modules.DebugInstrumentationModule;
import com.yltx.oil.partner.injections.modules.DebugInstrumentationModule_ProvidesHttpLoggingInstrumentationFactory;
import com.yltx.oil.partner.injections.modules.DebugInstrumentationModule_ProvidesHttpLoggingIntercetorFactory;
import com.yltx.oil.partner.injections.modules.DebugInstrumentationModule_ProvidesInstrumentationFactory;
import com.yltx.oil.partner.injections.modules.DebugInstrumentationModule_ProvidesStethoInstrumentationFactory;
import com.yltx.oil.partner.injections.modules.DebugInstrumentationModule_ProvidesStethoIntercetorFactory;
import com.yltx.oil.partner.injections.modules.GlobalModule;
import com.yltx.oil.partner.injections.modules.GlobalModule_ProvideDataRepositoryFactory;
import com.yltx.oil.partner.injections.modules.GlobalModule_ProvideNavigatorFactory;
import com.yltx.oil.partner.injections.modules.GlobalModule_ProvideRestDataSourceFactory;
import com.yltx.oil.partner.injections.modules.NetworkModule;
import com.yltx.oil.partner.injections.modules.NetworkModule_ProvideOkHttpClientFactory;
import com.yltx.oil.partner.modules.SplashActivity;
import com.yltx.oil.partner.modules.SplashActivity_MembersInjector;
import com.yltx.oil.partner.modules.home.activity.ApplyingPartnerActivity;
import com.yltx.oil.partner.modules.home.activity.ClassificationActivity;
import com.yltx.oil.partner.modules.home.activity.CommoditySharingInforActivity;
import com.yltx.oil.partner.modules.home.activity.CommoditySharingInforActivity_MembersInjector;
import com.yltx.oil.partner.modules.home.activity.MessageBulletinActivity;
import com.yltx.oil.partner.modules.home.activity.MessageDetailsActivity;
import com.yltx.oil.partner.modules.home.activity.MessageDetailsActivity_MembersInjector;
import com.yltx.oil.partner.modules.home.activity.MessageHomeActivity;
import com.yltx.oil.partner.modules.home.activity.MessageHomeActivity_MembersInjector;
import com.yltx.oil.partner.modules.home.activity.SearchHomeActivity;
import com.yltx.oil.partner.modules.home.activity.SearchHomeActivity_MembersInjector;
import com.yltx.oil.partner.modules.home.activity.SearchResultActivity;
import com.yltx.oil.partner.modules.home.activity.SearchResultActivity_MembersInjector;
import com.yltx.oil.partner.modules.home.activity.ShareDetailsActivity;
import com.yltx.oil.partner.modules.home.domain.BannerUseCase_Factory;
import com.yltx.oil.partner.modules.home.domain.HomeButtonconfigurationUseCase_Factory;
import com.yltx.oil.partner.modules.home.domain.MessageForDetailsUseCase;
import com.yltx.oil.partner.modules.home.domain.MessageNotificationUseCase;
import com.yltx.oil.partner.modules.home.domain.SCUseCase;
import com.yltx.oil.partner.modules.home.domain.SSLSUseCase;
import com.yltx.oil.partner.modules.home.domain.SeekUseCase;
import com.yltx.oil.partner.modules.home.domain.ShopRecommendUseCase;
import com.yltx.oil.partner.modules.home.domain.ShopRecommendUseCase_Factory;
import com.yltx.oil.partner.modules.home.domain.ShopRecommendUseCase_MembersInjector;
import com.yltx.oil.partner.modules.home.fragment.FragmentHome;
import com.yltx.oil.partner.modules.home.fragment.FragmentHome_MembersInjector;
import com.yltx.oil.partner.modules.home.presenter.BannerPresenter;
import com.yltx.oil.partner.modules.home.presenter.BannerPresenter_Factory;
import com.yltx.oil.partner.modules.home.presenter.HomeButtonconfigurationPresenter;
import com.yltx.oil.partner.modules.home.presenter.HomeButtonconfigurationPresenter_Factory;
import com.yltx.oil.partner.modules.home.presenter.MessageForDetailsPresenter;
import com.yltx.oil.partner.modules.home.presenter.MessageNotificationPresenter;
import com.yltx.oil.partner.modules.home.presenter.SCPresenter;
import com.yltx.oil.partner.modules.home.presenter.SSLSPresenter;
import com.yltx.oil.partner.modules.home.presenter.SeekPresenter;
import com.yltx.oil.partner.modules.home.presenter.ShopRecommendPresenter;
import com.yltx.oil.partner.modules.login.activity.ForgetPasswordActivity;
import com.yltx.oil.partner.modules.login.activity.ForgetPasswordActivity_MembersInjector;
import com.yltx.oil.partner.modules.login.activity.LoginActivity;
import com.yltx.oil.partner.modules.login.activity.NonoilLoginActivity;
import com.yltx.oil.partner.modules.login.activity.NonoilLoginActivity_MembersInjector;
import com.yltx.oil.partner.modules.login.activity.RegisterActivity;
import com.yltx.oil.partner.modules.login.activity.RegisterActivity_MembersInjector;
import com.yltx.oil.partner.modules.login.activity.YLSPLoginActivity;
import com.yltx.oil.partner.modules.login.activity.YLTXLoginActivity;
import com.yltx.oil.partner.modules.login.activity.YLTXLoginActivity_MembersInjector;
import com.yltx.oil.partner.modules.login.domain.AppLoginStatusUseCase_Factory;
import com.yltx.oil.partner.modules.login.domain.AutoUseCase_Factory;
import com.yltx.oil.partner.modules.login.domain.FindpwUseCase_Factory;
import com.yltx.oil.partner.modules.login.domain.GetImgCodeUseCase_Factory;
import com.yltx.oil.partner.modules.login.domain.GetvalidCodeUseCase;
import com.yltx.oil.partner.modules.login.domain.GetvalidCodeUseCase_Factory;
import com.yltx.oil.partner.modules.login.domain.LoginSmUseCase_Factory;
import com.yltx.oil.partner.modules.login.domain.LoginUseCase_Factory;
import com.yltx.oil.partner.modules.login.domain.RegisterUseCase_Factory;
import com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin;
import com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin_MembersInjector;
import com.yltx.oil.partner.modules.login.presenter.AppLoginStatusPresenter;
import com.yltx.oil.partner.modules.login.presenter.AppLoginStatusPresenter_Factory;
import com.yltx.oil.partner.modules.login.presenter.ForgetPwdPresenter;
import com.yltx.oil.partner.modules.login.presenter.ForgetPwdPresenter_Factory;
import com.yltx.oil.partner.modules.login.presenter.GetValidCodePresenter;
import com.yltx.oil.partner.modules.login.presenter.GetValidCodePresenter_Factory;
import com.yltx.oil.partner.modules.login.presenter.LoginPresenter;
import com.yltx.oil.partner.modules.login.presenter.LoginPresenter_Factory;
import com.yltx.oil.partner.modules.login.presenter.RegisterPresenter;
import com.yltx.oil.partner.modules.login.presenter.RegisterPresenter_Factory;
import com.yltx.oil.partner.modules.main.MainActivity;
import com.yltx.oil.partner.modules.main.domian.CheckVersionUseCase_Factory;
import com.yltx.oil.partner.modules.main.presenter.SplashPresenter;
import com.yltx.oil.partner.modules.main.presenter.SplashPresenter_Factory;
import com.yltx.oil.partner.modules.mine.activity.ClipImageActivity;
import com.yltx.oil.partner.modules.mine.activity.ComplainValetActivity;
import com.yltx.oil.partner.modules.mine.activity.ComplainValetActivity_MembersInjector;
import com.yltx.oil.partner.modules.mine.activity.ComplaintActivity;
import com.yltx.oil.partner.modules.mine.activity.ComplaintActivity_MembersInjector;
import com.yltx.oil.partner.modules.mine.activity.FeedbackActivity;
import com.yltx.oil.partner.modules.mine.activity.FeedbackActivity_MembersInjector;
import com.yltx.oil.partner.modules.mine.activity.HelpCenterActivity;
import com.yltx.oil.partner.modules.mine.activity.InvitationDetailsActivity;
import com.yltx.oil.partner.modules.mine.activity.InvitationDetailsActivity_MembersInjector;
import com.yltx.oil.partner.modules.mine.activity.InviteCourtesyActivity;
import com.yltx.oil.partner.modules.mine.activity.InviteCourtesyActivity_MembersInjector;
import com.yltx.oil.partner.modules.mine.activity.MineInfoActivity;
import com.yltx.oil.partner.modules.mine.activity.MineInfoActivity_MembersInjector;
import com.yltx.oil.partner.modules.mine.activity.ModifyNicknameActivity;
import com.yltx.oil.partner.modules.mine.activity.ModifyNicknameActivity_MembersInjector;
import com.yltx.oil.partner.modules.mine.activity.NoviceGuideActivity;
import com.yltx.oil.partner.modules.mine.activity.PhoneActivity;
import com.yltx.oil.partner.modules.mine.activity.PhoneActivity_MembersInjector;
import com.yltx.oil.partner.modules.mine.activity.RechargeActivity;
import com.yltx.oil.partner.modules.mine.activity.RechargeActivity_MembersInjector;
import com.yltx.oil.partner.modules.mine.activity.UpdatePwdActivity;
import com.yltx.oil.partner.modules.mine.activity.UpdatePwdActivity_MembersInjector;
import com.yltx.oil.partner.modules.mine.domain.ComplaintOrderUseCase;
import com.yltx.oil.partner.modules.mine.domain.ComplaintUseCase;
import com.yltx.oil.partner.modules.mine.domain.DetailUseCase;
import com.yltx.oil.partner.modules.mine.domain.MemberUseCase;
import com.yltx.oil.partner.modules.mine.domain.MineinfoHeadPicUseCase;
import com.yltx.oil.partner.modules.mine.domain.MineinfoOldPhoneUseCase;
import com.yltx.oil.partner.modules.mine.domain.MineinfoUpdatePhoneUseCase;
import com.yltx.oil.partner.modules.mine.domain.ModifUseCase;
import com.yltx.oil.partner.modules.mine.domain.MyfeedbackSubmintUseCase;
import com.yltx.oil.partner.modules.mine.domain.MyfeedbackUseCase;
import com.yltx.oil.partner.modules.mine.domain.PersonalCenterUseCase;
import com.yltx.oil.partner.modules.mine.domain.PhoneUseCase;
import com.yltx.oil.partner.modules.mine.domain.RechargePayOrderCae;
import com.yltx.oil.partner.modules.mine.domain.RechargePayOrderCae_Factory;
import com.yltx.oil.partner.modules.mine.domain.RechargePayTypeOrderCae;
import com.yltx.oil.partner.modules.mine.domain.RechargePayTypeOrderCae_Factory;
import com.yltx.oil.partner.modules.mine.domain.TotalUseCase;
import com.yltx.oil.partner.modules.mine.fragment.FragmentMine;
import com.yltx.oil.partner.modules.mine.fragment.FragmentMine_MembersInjector;
import com.yltx.oil.partner.modules.mine.presenter.ComplaintOrderPresenter;
import com.yltx.oil.partner.modules.mine.presenter.ComplaintPresenter;
import com.yltx.oil.partner.modules.mine.presenter.InviteDetailPresenter;
import com.yltx.oil.partner.modules.mine.presenter.InvitePresenter;
import com.yltx.oil.partner.modules.mine.presenter.MemberPresenter;
import com.yltx.oil.partner.modules.mine.presenter.MineInfoPresenter;
import com.yltx.oil.partner.modules.mine.presenter.MinePhonePresenter;
import com.yltx.oil.partner.modules.mine.presenter.ModifPresenter;
import com.yltx.oil.partner.modules.mine.presenter.MyfeedbackPresenter;
import com.yltx.oil.partner.modules.mine.presenter.RechargePresenter;
import com.yltx.oil.partner.modules.mine.presenter.RechargePresenter_Factory;
import com.yltx.oil.partner.modules.oiltrade.domain.FinanceCardDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.FinanceCardUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.FuelCardDetailsUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.FuelCardListUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.GiftCardCardUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.GiftCardCardUseCase_Factory;
import com.yltx.oil.partner.modules.oiltrade.domain.GiftCardCardUseCase_MembersInjector;
import com.yltx.oil.partner.modules.oiltrade.domain.GiftCardDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.SFDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.ShopDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.ShopUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.ShopUseCase_Factory;
import com.yltx.oil.partner.modules.oiltrade.domain.ShopUseCase_MembersInjector;
import com.yltx.oil.partner.modules.oiltrade.domain.StoredValueCardDetailUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.StoredValueCardUseCase;
import com.yltx.oil.partner.modules.oiltrade.domain.StoredValueCardUseCase_Factory;
import com.yltx.oil.partner.modules.oiltrade.domain.StoredValueCardUseCase_MembersInjector;
import com.yltx.oil.partner.modules.oiltrade.fragment.FragmentOilTrade;
import com.yltx.oil.partner.modules.oiltrade.fragment.GiftCardFragment;
import com.yltx.oil.partner.modules.oiltrade.fragment.GiftCardFragment_MembersInjector;
import com.yltx.oil.partner.modules.oiltrade.fragment.OilCardFragment;
import com.yltx.oil.partner.modules.oiltrade.fragment.OilCardFragment_MembersInjector;
import com.yltx.oil.partner.modules.oiltrade.fragment.RefuelingCardFragment;
import com.yltx.oil.partner.modules.oiltrade.fragment.RefuelingCardFragment_MembersInjector;
import com.yltx.oil.partner.modules.oiltrade.fragment.ShopFragment;
import com.yltx.oil.partner.modules.oiltrade.fragment.ShopFragment_MembersInjector;
import com.yltx.oil.partner.modules.oiltrade.fragment.StoredValueCardFragment;
import com.yltx.oil.partner.modules.oiltrade.fragment.StoredValueCardFragment_MembersInjector;
import com.yltx.oil.partner.modules.oiltrade.presenter.FinanceCardPresenter;
import com.yltx.oil.partner.modules.oiltrade.presenter.FinanceCardetailPresenter;
import com.yltx.oil.partner.modules.oiltrade.presenter.FuelCardListPresenter;
import com.yltx.oil.partner.modules.oiltrade.presenter.GiftCardCardPresenter;
import com.yltx.oil.partner.modules.oiltrade.presenter.ShopPresenter;
import com.yltx.oil.partner.modules.oiltrade.presenter.StoredValueCardPresenter;
import com.yltx.oil.partner.modules.profit.activity.AccountdetailsActivity;
import com.yltx.oil.partner.modules.profit.activity.AccountdetailsActivity_MembersInjector;
import com.yltx.oil.partner.modules.profit.activity.BindingbankcardsActivity;
import com.yltx.oil.partner.modules.profit.activity.BindingbankcardsActivity_MembersInjector;
import com.yltx.oil.partner.modules.profit.activity.CollectionRecordActivity;
import com.yltx.oil.partner.modules.profit.activity.CollectionRecordActivity_MembersInjector;
import com.yltx.oil.partner.modules.profit.activity.DataanalysisActivity;
import com.yltx.oil.partner.modules.profit.activity.DataanalysisActivity_MembersInjector;
import com.yltx.oil.partner.modules.profit.activity.ModificationBankCardsActivity;
import com.yltx.oil.partner.modules.profit.activity.ModificationBankCardsActivity_MembersInjector;
import com.yltx.oil.partner.modules.profit.activity.OptiondateActivity;
import com.yltx.oil.partner.modules.profit.activity.OrderdetailsActivity;
import com.yltx.oil.partner.modules.profit.activity.SettlementrecordsActivity;
import com.yltx.oil.partner.modules.profit.activity.SettlementrecordsActivity_MembersInjector;
import com.yltx.oil.partner.modules.profit.activity.TxHistoryActivity;
import com.yltx.oil.partner.modules.profit.activity.TxHistoryActivity_MembersInjector;
import com.yltx.oil.partner.modules.profit.activity.WithdrawActivity;
import com.yltx.oil.partner.modules.profit.activity.WithdrawActivity_MembersInjector;
import com.yltx.oil.partner.modules.profit.domain.AccountBalanceUseCase;
import com.yltx.oil.partner.modules.profit.domain.AllordersUseCase;
import com.yltx.oil.partner.modules.profit.domain.BindingbankcardsUseCase;
import com.yltx.oil.partner.modules.profit.domain.CommissionUseCase;
import com.yltx.oil.partner.modules.profit.domain.FragmentProfit_yjjsjl_UseCase;
import com.yltx.oil.partner.modules.profit.domain.GeneralizeUseCase;
import com.yltx.oil.partner.modules.profit.domain.GetTxHistoryListUseCase;
import com.yltx.oil.partner.modules.profit.domain.IsAuthUseCase;
import com.yltx.oil.partner.modules.profit.domain.JoinUseCase;
import com.yltx.oil.partner.modules.profit.domain.ManagerYZJYFXUseCase;
import com.yltx.oil.partner.modules.profit.domain.SettlementRecordsUseCase;
import com.yltx.oil.partner.modules.profit.domain.TxApplyUseCase;
import com.yltx.oil.partner.modules.profit.domain.TxUseCase;
import com.yltx.oil.partner.modules.profit.domain.UpBankcardsUseCase;
import com.yltx.oil.partner.modules.profit.domain.UserAccountUseCase;
import com.yltx.oil.partner.modules.profit.domain.UserAccountUseCase_Factory;
import com.yltx.oil.partner.modules.profit.domain.UserAccountUseCase_MembersInjector;
import com.yltx.oil.partner.modules.profit.fragment.AllFragment;
import com.yltx.oil.partner.modules.profit.fragment.AllFragment_MembersInjector;
import com.yltx.oil.partner.modules.profit.fragment.AllordersFragment;
import com.yltx.oil.partner.modules.profit.fragment.AllordersFragment_MembersInjector;
import com.yltx.oil.partner.modules.profit.fragment.EffectiveorderFragment;
import com.yltx.oil.partner.modules.profit.fragment.EffectiveorderFragment_MembersInjector;
import com.yltx.oil.partner.modules.profit.fragment.FailureoftheorderFragment;
import com.yltx.oil.partner.modules.profit.fragment.FailureoftheorderFragment_MembersInjector;
import com.yltx.oil.partner.modules.profit.fragment.FragmentProfit;
import com.yltx.oil.partner.modules.profit.fragment.FragmentProfit_MembersInjector;
import com.yltx.oil.partner.modules.profit.fragment.RefundFragment;
import com.yltx.oil.partner.modules.profit.presenter.AccountBalancePresenter;
import com.yltx.oil.partner.modules.profit.presenter.AllordersPresenter;
import com.yltx.oil.partner.modules.profit.presenter.BindingBankPresenter;
import com.yltx.oil.partner.modules.profit.presenter.CommissionPresenter;
import com.yltx.oil.partner.modules.profit.presenter.FragmentProfit_yjjsjl_Presenter;
import com.yltx.oil.partner.modules.profit.presenter.GeneralizePresenter;
import com.yltx.oil.partner.modules.profit.presenter.JoinPresenter;
import com.yltx.oil.partner.modules.profit.presenter.ModificationPresenter;
import com.yltx.oil.partner.modules.profit.presenter.SettlementRecordsPresenter;
import com.yltx.oil.partner.modules.profit.presenter.TxHistoryPresenter;
import com.yltx.oil.partner.modules.profit.presenter.TxPresenter;
import com.yltx.oil.partner.modules.profit.presenter.UserAccountPresenter;
import com.yltx.oil.partner.modules.web.JsBridgeWebActivity;
import com.yltx.oil.partner.modules.web.WebActivity;
import com.yltx.oil.partner.navigation.Navigator;
import com.yltx.oil.partner.oss.OSSFileHelper;
import com.yltx.oil.partner.oss.OSSFileHelper_Factory;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication_MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.DispatchingAndroidInjector_Factory;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import java.util.Collections;
import java.util.Map;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

/* loaded from: classes.dex */
public final class DaggerGlobalComponent implements GlobalComponent {
    private Provider<BuildersModule_AccountdetailsActivity.AccountdetailsActivitySubcomponent.Builder> accountdetailsActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_AllFragment.AllFragmentSubcomponent.Builder> allFragmentSubcomponentBuilderProvider;
    private Provider<BuildersModule_AllordersFragment.AllordersFragmentSubcomponent.Builder> allordersFragmentSubcomponentBuilderProvider;
    private Provider<BuildersModule_ApplyingPartnerActivity.ApplyingPartnerActivitySubcomponent.Builder> applyingPartnerActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_BindingbankcardsActivity.BindingbankcardsActivitySubcomponent.Builder> bindingbankcardsActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_ClassificationActivity.ClassificationActivitySubcomponent.Builder> classificationActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_ClipImageActivity.ClipImageActivitySubcomponent.Builder> clipImageActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_CollectionRecordActivity.CollectionRecordActivitySubcomponent.Builder> collectionRecordActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_CommoditySharingInforActivity.CommoditySharingInforActivitySubcomponent.Builder> commoditySharingInforActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_ComplainValetActivity.ComplainValetActivitySubcomponent.Builder> complainValetActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_ComplaintActivity.ComplaintActivitySubcomponent.Builder> complaintActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_DataanalysisActivity.DataanalysisActivitySubcomponent.Builder> dataanalysisActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_EffectiveorderFragment.EffectiveorderFragmentSubcomponent.Builder> effectiveorderFragmentSubcomponentBuilderProvider;
    private Provider<BuildersModule_FailureoftheorderFragment.FailureoftheorderFragmentSubcomponent.Builder> failureoftheorderFragmentSubcomponentBuilderProvider;
    private Provider<BuildersModule_FeedbackActivity.FeedbackActivitySubcomponent.Builder> feedbackActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_ForgetPasswordActivity.ForgetPasswordActivitySubcomponent.Builder> forgetPasswordActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_FragmentHome.FragmentHomeSubcomponent.Builder> fragmentHomeSubcomponentBuilderProvider;
    private Provider<BuildersModule_FragmentMine.FragmentMineSubcomponent.Builder> fragmentMineSubcomponentBuilderProvider;
    private Provider<BuildersModule_FragmentOilTrade.FragmentOilTradeSubcomponent.Builder> fragmentOilTradeSubcomponentBuilderProvider;
    private Provider<BuildersModule_FragmentProfit.FragmentProfitSubcomponent.Builder> fragmentProfitSubcomponentBuilderProvider;
    private Provider<BuildersModule_FragmentPwdLogin.FragmentPwdLoginSubcomponent.Builder> fragmentPwdLoginSubcomponentBuilderProvider;
    private Provider<BuildersModule_GiftcardFragment.GiftCardFragmentSubcomponent.Builder> giftCardFragmentSubcomponentBuilderProvider;
    private Provider<BuildersModule_HelpCenterActivity.HelpCenterActivitySubcomponent.Builder> helpCenterActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_InvitationDetailsActivity.InvitationDetailsActivitySubcomponent.Builder> invitationDetailsActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_InviteCourtesyActivity.InviteCourtesyActivitySubcomponent.Builder> inviteCourtesyActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_InviteFriendsActivityInjector.JsBridgeWebActivitySubcomponent.Builder> jsBridgeWebActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_LoginActivity.LoginActivitySubcomponent.Builder> loginActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_MainActivity.MainActivitySubcomponent.Builder> mainActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_MessageBulletinActivity.MessageBulletinActivitySubcomponent.Builder> messageBulletinActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_MessageDetailsActivity.MessageDetailsActivitySubcomponent.Builder> messageDetailsActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_MessageHomeActivity.MessageHomeActivitySubcomponent.Builder> messageHomeActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_MineInfoActivity.MineInfoActivitySubcomponent.Builder> mineInfoActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_ModificationBankCardsActivity.ModificationBankCardsActivitySubcomponent.Builder> modificationBankCardsActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_ModifyNicknameActivity.ModifyNicknameActivitySubcomponent.Builder> modifyNicknameActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_NonoilLoginActivity.NonoilLoginActivitySubcomponent.Builder> nonoilLoginActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_NoviceGuideActivity.NoviceGuideActivitySubcomponent.Builder> noviceGuideActivitySubcomponentBuilderProvider;
    private Provider<OSSFileHelper> oSSFileHelperProvider;
    private Provider<BuildersModule_OilCardFragment.OilCardFragmentSubcomponent.Builder> oilCardFragmentSubcomponentBuilderProvider;
    private Provider<BuildersModule_OptiondateActivity.OptiondateActivitySubcomponent.Builder> optiondateActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_OrderdetailsActivity.OrderdetailsActivitySubcomponent.Builder> orderdetailsActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_PhoneActivity.PhoneActivitySubcomponent.Builder> phoneActivitySubcomponentBuilderProvider;
    private Provider<Context> provideContextProvider;
    private Provider<Repository> provideDataRepositoryProvider;
    private Provider<Navigator> provideNavigatorProvider;
    private Provider<OkHttpClient> provideOkHttpClientProvider;
    private Provider<RestDataSource> provideRestDataSourceProvider;
    private Provider<HttpLoggingInstrumentation> providesHttpLoggingInstrumentationProvider;
    private Provider<HttpLoggingInterceptor> providesHttpLoggingIntercetorProvider;
    private Provider<ApplicationInstrumentation> providesInstrumentationProvider;
    private Provider<StethoInstrumentation> providesStethoInstrumentationProvider;
    private Provider<StethoInterceptor> providesStethoIntercetorProvider;
    private Provider<BuildersModule_RechargeActivity.RechargeActivitySubcomponent.Builder> rechargeActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_RefuelingCardFragment.RefuelingCardFragmentSubcomponent.Builder> refuelingCardFragmentSubcomponentBuilderProvider;
    private Provider<BuildersModule_RefundFragment.RefundFragmentSubcomponent.Builder> refundFragmentSubcomponentBuilderProvider;
    private Provider<BuildersModule_RegisterActivity.RegisterActivitySubcomponent.Builder> registerActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_SearchHomeActivity.SearchHomeActivitySubcomponent.Builder> searchHomeActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_SearchResultActivity.SearchResultActivitySubcomponent.Builder> searchResultActivitySubcomponentBuilderProvider;
    private Provider<PartnerApplication> seedInstanceProvider;
    private Provider<BuildersModule_SettlementrecordsActivity.SettlementrecordsActivitySubcomponent.Builder> settlementrecordsActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_ShareDetailsActivity.ShareDetailsActivitySubcomponent.Builder> shareDetailsActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_ShopFragment.ShopFragmentSubcomponent.Builder> shopFragmentSubcomponentBuilderProvider;
    private Provider<BuildersModule_SplashActivity.SplashActivitySubcomponent.Builder> splashActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_StoredValueCardFragment.StoredValueCardFragmentSubcomponent.Builder> storedValueCardFragmentSubcomponentBuilderProvider;
    private Provider<BuildersModule_TxHistoryActivity.TxHistoryActivitySubcomponent.Builder> txHistoryActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_UpdatePwdActivity.UpdatePwdActivitySubcomponent.Builder> updatePwdActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_WebActivity.WebActivitySubcomponent.Builder> webActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_WithdrawActivity.WithdrawActivitySubcomponent.Builder> withdrawActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_YlspLoginActivity.YLSPLoginActivitySubcomponent.Builder> yLSPLoginActivitySubcomponentBuilderProvider;
    private Provider<BuildersModule_YltxLoginActivity.YLTXLoginActivitySubcomponent.Builder> yLTXLoginActivitySubcomponentBuilderProvider;

    private DaggerGlobalComponent(Builder builder) {
        initialize(builder);
    }

    public static GlobalComponent.Builder builder() {
        return new Builder();
    }

    private Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> getMapOfClassOfAndProviderOfFactoryOf() {
        return MapBuilder.newMapBuilder(42).put(SplashActivity.class, this.splashActivitySubcomponentBuilderProvider).put(LoginActivity.class, this.loginActivitySubcomponentBuilderProvider).put(CommoditySharingInforActivity.class, this.commoditySharingInforActivitySubcomponentBuilderProvider).put(MainActivity.class, this.mainActivitySubcomponentBuilderProvider).put(ApplyingPartnerActivity.class, this.applyingPartnerActivitySubcomponentBuilderProvider).put(RegisterActivity.class, this.registerActivitySubcomponentBuilderProvider).put(ForgetPasswordActivity.class, this.forgetPasswordActivitySubcomponentBuilderProvider).put(YLSPLoginActivity.class, this.yLSPLoginActivitySubcomponentBuilderProvider).put(ComplaintActivity.class, this.complaintActivitySubcomponentBuilderProvider).put(ComplainValetActivity.class, this.complainValetActivitySubcomponentBuilderProvider).put(NoviceGuideActivity.class, this.noviceGuideActivitySubcomponentBuilderProvider).put(HelpCenterActivity.class, this.helpCenterActivitySubcomponentBuilderProvider).put(FeedbackActivity.class, this.feedbackActivitySubcomponentBuilderProvider).put(MineInfoActivity.class, this.mineInfoActivitySubcomponentBuilderProvider).put(ClipImageActivity.class, this.clipImageActivitySubcomponentBuilderProvider).put(ModifyNicknameActivity.class, this.modifyNicknameActivitySubcomponentBuilderProvider).put(InvitationDetailsActivity.class, this.invitationDetailsActivitySubcomponentBuilderProvider).put(MessageHomeActivity.class, this.messageHomeActivitySubcomponentBuilderProvider).put(SearchHomeActivity.class, this.searchHomeActivitySubcomponentBuilderProvider).put(SearchResultActivity.class, this.searchResultActivitySubcomponentBuilderProvider).put(MessageBulletinActivity.class, this.messageBulletinActivitySubcomponentBuilderProvider).put(MessageDetailsActivity.class, this.messageDetailsActivitySubcomponentBuilderProvider).put(InviteCourtesyActivity.class, this.inviteCourtesyActivitySubcomponentBuilderProvider).put(PhoneActivity.class, this.phoneActivitySubcomponentBuilderProvider).put(UpdatePwdActivity.class, this.updatePwdActivitySubcomponentBuilderProvider).put(ClassificationActivity.class, this.classificationActivitySubcomponentBuilderProvider).put(BindingbankcardsActivity.class, this.bindingbankcardsActivitySubcomponentBuilderProvider).put(TxHistoryActivity.class, this.txHistoryActivitySubcomponentBuilderProvider).put(ModificationBankCardsActivity.class, this.modificationBankCardsActivitySubcomponentBuilderProvider).put(CollectionRecordActivity.class, this.collectionRecordActivitySubcomponentBuilderProvider).put(DataanalysisActivity.class, this.dataanalysisActivitySubcomponentBuilderProvider).put(OrderdetailsActivity.class, this.orderdetailsActivitySubcomponentBuilderProvider).put(SettlementrecordsActivity.class, this.settlementrecordsActivitySubcomponentBuilderProvider).put(WithdrawActivity.class, this.withdrawActivitySubcomponentBuilderProvider).put(WebActivity.class, this.webActivitySubcomponentBuilderProvider).put(AccountdetailsActivity.class, this.accountdetailsActivitySubcomponentBuilderProvider).put(OptiondateActivity.class, this.optiondateActivitySubcomponentBuilderProvider).put(ShareDetailsActivity.class, this.shareDetailsActivitySubcomponentBuilderProvider).put(JsBridgeWebActivity.class, this.jsBridgeWebActivitySubcomponentBuilderProvider).put(RechargeActivity.class, this.rechargeActivitySubcomponentBuilderProvider).put(NonoilLoginActivity.class, this.nonoilLoginActivitySubcomponentBuilderProvider).put(YLTXLoginActivity.class, this.yLTXLoginActivitySubcomponentBuilderProvider).build();
    }

    private DispatchingAndroidInjector<Activity> getDispatchingAndroidInjectorOfActivity() {
        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(getMapOfClassOfAndProviderOfFactoryOf(), Collections.emptyMap());
    }

    private DispatchingAndroidInjector<BroadcastReceiver> getDispatchingAndroidInjectorOfBroadcastReceiver() {
        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(Collections.emptyMap(), Collections.emptyMap());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DispatchingAndroidInjector<Fragment> getDispatchingAndroidInjectorOfFragment() {
        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(Collections.emptyMap(), Collections.emptyMap());
    }

    private DispatchingAndroidInjector<Service> getDispatchingAndroidInjectorOfService() {
        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(Collections.emptyMap(), Collections.emptyMap());
    }

    private DispatchingAndroidInjector<ContentProvider> getDispatchingAndroidInjectorOfContentProvider() {
        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(Collections.emptyMap(), Collections.emptyMap());
    }

    private Map<Class<? extends android.support.v4.app.Fragment>, Provider<AndroidInjector.Factory<? extends android.support.v4.app.Fragment>>> getMapOfClassOfAndProviderOfFactoryOf2() {
        return MapBuilder.newMapBuilder(15).put(FragmentHome.class, this.fragmentHomeSubcomponentBuilderProvider).put(FragmentPwdLogin.class, this.fragmentPwdLoginSubcomponentBuilderProvider).put(FragmentMine.class, this.fragmentMineSubcomponentBuilderProvider).put(FragmentOilTrade.class, this.fragmentOilTradeSubcomponentBuilderProvider).put(OilCardFragment.class, this.oilCardFragmentSubcomponentBuilderProvider).put(StoredValueCardFragment.class, this.storedValueCardFragmentSubcomponentBuilderProvider).put(ShopFragment.class, this.shopFragmentSubcomponentBuilderProvider).put(GiftCardFragment.class, this.giftCardFragmentSubcomponentBuilderProvider).put(RefuelingCardFragment.class, this.refuelingCardFragmentSubcomponentBuilderProvider).put(FragmentProfit.class, this.fragmentProfitSubcomponentBuilderProvider).put(AllordersFragment.class, this.allordersFragmentSubcomponentBuilderProvider).put(AllFragment.class, this.allFragmentSubcomponentBuilderProvider).put(RefundFragment.class, this.refundFragmentSubcomponentBuilderProvider).put(EffectiveorderFragment.class, this.effectiveorderFragmentSubcomponentBuilderProvider).put(FailureoftheorderFragment.class, this.failureoftheorderFragmentSubcomponentBuilderProvider).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DispatchingAndroidInjector<android.support.v4.app.Fragment> getDispatchingAndroidInjectorOfFragment2() {
        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(getMapOfClassOfAndProviderOfFactoryOf2(), Collections.emptyMap());
    }

    private void initialize(Builder builder) {
        this.splashActivitySubcomponentBuilderProvider = new Provider<BuildersModule_SplashActivity.SplashActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_SplashActivity.SplashActivitySubcomponent.Builder get() {
                return new SplashActivitySubcomponentBuilder();
            }
        };
        this.loginActivitySubcomponentBuilderProvider = new Provider<BuildersModule_LoginActivity.LoginActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_LoginActivity.LoginActivitySubcomponent.Builder get() {
                return new LoginActivitySubcomponentBuilder();
            }
        };
        this.commoditySharingInforActivitySubcomponentBuilderProvider = new Provider<BuildersModule_CommoditySharingInforActivity.CommoditySharingInforActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_CommoditySharingInforActivity.CommoditySharingInforActivitySubcomponent.Builder get() {
                return new CommoditySharingInforActivitySubcomponentBuilder();
            }
        };
        this.mainActivitySubcomponentBuilderProvider = new Provider<BuildersModule_MainActivity.MainActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_MainActivity.MainActivitySubcomponent.Builder get() {
                return new MainActivitySubcomponentBuilder();
            }
        };
        this.applyingPartnerActivitySubcomponentBuilderProvider = new Provider<BuildersModule_ApplyingPartnerActivity.ApplyingPartnerActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_ApplyingPartnerActivity.ApplyingPartnerActivitySubcomponent.Builder get() {
                return new ApplyingPartnerActivitySubcomponentBuilder();
            }
        };
        this.registerActivitySubcomponentBuilderProvider = new Provider<BuildersModule_RegisterActivity.RegisterActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_RegisterActivity.RegisterActivitySubcomponent.Builder get() {
                return new RegisterActivitySubcomponentBuilder();
            }
        };
        this.forgetPasswordActivitySubcomponentBuilderProvider = new Provider<BuildersModule_ForgetPasswordActivity.ForgetPasswordActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_ForgetPasswordActivity.ForgetPasswordActivitySubcomponent.Builder get() {
                return new ForgetPasswordActivitySubcomponentBuilder();
            }
        };
        this.yLSPLoginActivitySubcomponentBuilderProvider = new Provider<BuildersModule_YlspLoginActivity.YLSPLoginActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_YlspLoginActivity.YLSPLoginActivitySubcomponent.Builder get() {
                return new YLSPLoginActivitySubcomponentBuilder();
            }
        };
        this.complaintActivitySubcomponentBuilderProvider = new Provider<BuildersModule_ComplaintActivity.ComplaintActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.9
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_ComplaintActivity.ComplaintActivitySubcomponent.Builder get() {
                return new ComplaintActivitySubcomponentBuilder();
            }
        };
        this.complainValetActivitySubcomponentBuilderProvider = new Provider<BuildersModule_ComplainValetActivity.ComplainValetActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_ComplainValetActivity.ComplainValetActivitySubcomponent.Builder get() {
                return new ComplainValetActivitySubcomponentBuilder();
            }
        };
        this.noviceGuideActivitySubcomponentBuilderProvider = new Provider<BuildersModule_NoviceGuideActivity.NoviceGuideActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.11
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_NoviceGuideActivity.NoviceGuideActivitySubcomponent.Builder get() {
                return new NoviceGuideActivitySubcomponentBuilder();
            }
        };
        this.helpCenterActivitySubcomponentBuilderProvider = new Provider<BuildersModule_HelpCenterActivity.HelpCenterActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.12
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_HelpCenterActivity.HelpCenterActivitySubcomponent.Builder get() {
                return new HelpCenterActivitySubcomponentBuilder();
            }
        };
        this.feedbackActivitySubcomponentBuilderProvider = new Provider<BuildersModule_FeedbackActivity.FeedbackActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.13
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_FeedbackActivity.FeedbackActivitySubcomponent.Builder get() {
                return new FeedbackActivitySubcomponentBuilder();
            }
        };
        this.mineInfoActivitySubcomponentBuilderProvider = new Provider<BuildersModule_MineInfoActivity.MineInfoActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.14
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_MineInfoActivity.MineInfoActivitySubcomponent.Builder get() {
                return new MineInfoActivitySubcomponentBuilder();
            }
        };
        this.clipImageActivitySubcomponentBuilderProvider = new Provider<BuildersModule_ClipImageActivity.ClipImageActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.15
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_ClipImageActivity.ClipImageActivitySubcomponent.Builder get() {
                return new ClipImageActivitySubcomponentBuilder();
            }
        };
        this.modifyNicknameActivitySubcomponentBuilderProvider = new Provider<BuildersModule_ModifyNicknameActivity.ModifyNicknameActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.16
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_ModifyNicknameActivity.ModifyNicknameActivitySubcomponent.Builder get() {
                return new ModifyNicknameActivitySubcomponentBuilder();
            }
        };
        this.invitationDetailsActivitySubcomponentBuilderProvider = new Provider<BuildersModule_InvitationDetailsActivity.InvitationDetailsActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.17
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_InvitationDetailsActivity.InvitationDetailsActivitySubcomponent.Builder get() {
                return new InvitationDetailsActivitySubcomponentBuilder();
            }
        };
        this.messageHomeActivitySubcomponentBuilderProvider = new Provider<BuildersModule_MessageHomeActivity.MessageHomeActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.18
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_MessageHomeActivity.MessageHomeActivitySubcomponent.Builder get() {
                return new MessageHomeActivitySubcomponentBuilder();
            }
        };
        this.searchHomeActivitySubcomponentBuilderProvider = new Provider<BuildersModule_SearchHomeActivity.SearchHomeActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.19
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_SearchHomeActivity.SearchHomeActivitySubcomponent.Builder get() {
                return new SearchHomeActivitySubcomponentBuilder();
            }
        };
        this.searchResultActivitySubcomponentBuilderProvider = new Provider<BuildersModule_SearchResultActivity.SearchResultActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.20
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_SearchResultActivity.SearchResultActivitySubcomponent.Builder get() {
                return new SearchResultActivitySubcomponentBuilder();
            }
        };
        this.messageBulletinActivitySubcomponentBuilderProvider = new Provider<BuildersModule_MessageBulletinActivity.MessageBulletinActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.21
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_MessageBulletinActivity.MessageBulletinActivitySubcomponent.Builder get() {
                return new MessageBulletinActivitySubcomponentBuilder();
            }
        };
        this.messageDetailsActivitySubcomponentBuilderProvider = new Provider<BuildersModule_MessageDetailsActivity.MessageDetailsActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.22
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_MessageDetailsActivity.MessageDetailsActivitySubcomponent.Builder get() {
                return new MessageDetailsActivitySubcomponentBuilder();
            }
        };
        this.inviteCourtesyActivitySubcomponentBuilderProvider = new Provider<BuildersModule_InviteCourtesyActivity.InviteCourtesyActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.23
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_InviteCourtesyActivity.InviteCourtesyActivitySubcomponent.Builder get() {
                return new InviteCourtesyActivitySubcomponentBuilder();
            }
        };
        this.phoneActivitySubcomponentBuilderProvider = new Provider<BuildersModule_PhoneActivity.PhoneActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.24
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_PhoneActivity.PhoneActivitySubcomponent.Builder get() {
                return new PhoneActivitySubcomponentBuilder();
            }
        };
        this.updatePwdActivitySubcomponentBuilderProvider = new Provider<BuildersModule_UpdatePwdActivity.UpdatePwdActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.25
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_UpdatePwdActivity.UpdatePwdActivitySubcomponent.Builder get() {
                return new UpdatePwdActivitySubcomponentBuilder();
            }
        };
        this.classificationActivitySubcomponentBuilderProvider = new Provider<BuildersModule_ClassificationActivity.ClassificationActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.26
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_ClassificationActivity.ClassificationActivitySubcomponent.Builder get() {
                return new ClassificationActivitySubcomponentBuilder();
            }
        };
        this.bindingbankcardsActivitySubcomponentBuilderProvider = new Provider<BuildersModule_BindingbankcardsActivity.BindingbankcardsActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.27
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_BindingbankcardsActivity.BindingbankcardsActivitySubcomponent.Builder get() {
                return new BindingbankcardsActivitySubcomponentBuilder();
            }
        };
        this.txHistoryActivitySubcomponentBuilderProvider = new Provider<BuildersModule_TxHistoryActivity.TxHistoryActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.28
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_TxHistoryActivity.TxHistoryActivitySubcomponent.Builder get() {
                return new TxHistoryActivitySubcomponentBuilder();
            }
        };
        this.modificationBankCardsActivitySubcomponentBuilderProvider = new Provider<BuildersModule_ModificationBankCardsActivity.ModificationBankCardsActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.29
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_ModificationBankCardsActivity.ModificationBankCardsActivitySubcomponent.Builder get() {
                return new ModificationBankCardsActivitySubcomponentBuilder();
            }
        };
        this.collectionRecordActivitySubcomponentBuilderProvider = new Provider<BuildersModule_CollectionRecordActivity.CollectionRecordActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.30
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_CollectionRecordActivity.CollectionRecordActivitySubcomponent.Builder get() {
                return new CollectionRecordActivitySubcomponentBuilder();
            }
        };
        this.dataanalysisActivitySubcomponentBuilderProvider = new Provider<BuildersModule_DataanalysisActivity.DataanalysisActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.31
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_DataanalysisActivity.DataanalysisActivitySubcomponent.Builder get() {
                return new DataanalysisActivitySubcomponentBuilder();
            }
        };
        this.orderdetailsActivitySubcomponentBuilderProvider = new Provider<BuildersModule_OrderdetailsActivity.OrderdetailsActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.32
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_OrderdetailsActivity.OrderdetailsActivitySubcomponent.Builder get() {
                return new OrderdetailsActivitySubcomponentBuilder();
            }
        };
        this.settlementrecordsActivitySubcomponentBuilderProvider = new Provider<BuildersModule_SettlementrecordsActivity.SettlementrecordsActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.33
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_SettlementrecordsActivity.SettlementrecordsActivitySubcomponent.Builder get() {
                return new SettlementrecordsActivitySubcomponentBuilder();
            }
        };
        this.withdrawActivitySubcomponentBuilderProvider = new Provider<BuildersModule_WithdrawActivity.WithdrawActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.34
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_WithdrawActivity.WithdrawActivitySubcomponent.Builder get() {
                return new WithdrawActivitySubcomponentBuilder();
            }
        };
        this.webActivitySubcomponentBuilderProvider = new Provider<BuildersModule_WebActivity.WebActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.35
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_WebActivity.WebActivitySubcomponent.Builder get() {
                return new WebActivitySubcomponentBuilder();
            }
        };
        this.accountdetailsActivitySubcomponentBuilderProvider = new Provider<BuildersModule_AccountdetailsActivity.AccountdetailsActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.36
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_AccountdetailsActivity.AccountdetailsActivitySubcomponent.Builder get() {
                return new AccountdetailsActivitySubcomponentBuilder();
            }
        };
        this.optiondateActivitySubcomponentBuilderProvider = new Provider<BuildersModule_OptiondateActivity.OptiondateActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.37
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_OptiondateActivity.OptiondateActivitySubcomponent.Builder get() {
                return new OptiondateActivitySubcomponentBuilder();
            }
        };
        this.shareDetailsActivitySubcomponentBuilderProvider = new Provider<BuildersModule_ShareDetailsActivity.ShareDetailsActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.38
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_ShareDetailsActivity.ShareDetailsActivitySubcomponent.Builder get() {
                return new ShareDetailsActivitySubcomponentBuilder();
            }
        };
        this.jsBridgeWebActivitySubcomponentBuilderProvider = new Provider<BuildersModule_InviteFriendsActivityInjector.JsBridgeWebActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.39
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_InviteFriendsActivityInjector.JsBridgeWebActivitySubcomponent.Builder get() {
                return new JsBridgeWebActivitySubcomponentBuilder();
            }
        };
        this.rechargeActivitySubcomponentBuilderProvider = new Provider<BuildersModule_RechargeActivity.RechargeActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.40
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_RechargeActivity.RechargeActivitySubcomponent.Builder get() {
                return new RechargeActivitySubcomponentBuilder();
            }
        };
        this.nonoilLoginActivitySubcomponentBuilderProvider = new Provider<BuildersModule_NonoilLoginActivity.NonoilLoginActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.41
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_NonoilLoginActivity.NonoilLoginActivitySubcomponent.Builder get() {
                return new NonoilLoginActivitySubcomponentBuilder();
            }
        };
        this.yLTXLoginActivitySubcomponentBuilderProvider = new Provider<BuildersModule_YltxLoginActivity.YLTXLoginActivitySubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.42
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_YltxLoginActivity.YLTXLoginActivitySubcomponent.Builder get() {
                return new YLTXLoginActivitySubcomponentBuilder();
            }
        };
        this.fragmentHomeSubcomponentBuilderProvider = new Provider<BuildersModule_FragmentHome.FragmentHomeSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.43
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_FragmentHome.FragmentHomeSubcomponent.Builder get() {
                return new FragmentHomeSubcomponentBuilder();
            }
        };
        this.fragmentPwdLoginSubcomponentBuilderProvider = new Provider<BuildersModule_FragmentPwdLogin.FragmentPwdLoginSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.44
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_FragmentPwdLogin.FragmentPwdLoginSubcomponent.Builder get() {
                return new FragmentPwdLoginSubcomponentBuilder();
            }
        };
        this.fragmentMineSubcomponentBuilderProvider = new Provider<BuildersModule_FragmentMine.FragmentMineSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.45
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_FragmentMine.FragmentMineSubcomponent.Builder get() {
                return new FragmentMineSubcomponentBuilder();
            }
        };
        this.fragmentOilTradeSubcomponentBuilderProvider = new Provider<BuildersModule_FragmentOilTrade.FragmentOilTradeSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.46
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_FragmentOilTrade.FragmentOilTradeSubcomponent.Builder get() {
                return new FragmentOilTradeSubcomponentBuilder();
            }
        };
        this.oilCardFragmentSubcomponentBuilderProvider = new Provider<BuildersModule_OilCardFragment.OilCardFragmentSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.47
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_OilCardFragment.OilCardFragmentSubcomponent.Builder get() {
                return new OilCardFragmentSubcomponentBuilder();
            }
        };
        this.storedValueCardFragmentSubcomponentBuilderProvider = new Provider<BuildersModule_StoredValueCardFragment.StoredValueCardFragmentSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.48
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_StoredValueCardFragment.StoredValueCardFragmentSubcomponent.Builder get() {
                return new StoredValueCardFragmentSubcomponentBuilder();
            }
        };
        this.shopFragmentSubcomponentBuilderProvider = new Provider<BuildersModule_ShopFragment.ShopFragmentSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.49
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_ShopFragment.ShopFragmentSubcomponent.Builder get() {
                return new ShopFragmentSubcomponentBuilder();
            }
        };
        this.giftCardFragmentSubcomponentBuilderProvider = new Provider<BuildersModule_GiftcardFragment.GiftCardFragmentSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.50
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_GiftcardFragment.GiftCardFragmentSubcomponent.Builder get() {
                return new GiftCardFragmentSubcomponentBuilder();
            }
        };
        this.refuelingCardFragmentSubcomponentBuilderProvider = new Provider<BuildersModule_RefuelingCardFragment.RefuelingCardFragmentSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.51
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_RefuelingCardFragment.RefuelingCardFragmentSubcomponent.Builder get() {
                return new RefuelingCardFragmentSubcomponentBuilder();
            }
        };
        this.fragmentProfitSubcomponentBuilderProvider = new Provider<BuildersModule_FragmentProfit.FragmentProfitSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.52
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_FragmentProfit.FragmentProfitSubcomponent.Builder get() {
                return new FragmentProfitSubcomponentBuilder();
            }
        };
        this.allordersFragmentSubcomponentBuilderProvider = new Provider<BuildersModule_AllordersFragment.AllordersFragmentSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.53
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_AllordersFragment.AllordersFragmentSubcomponent.Builder get() {
                return new AllordersFragmentSubcomponentBuilder();
            }
        };
        this.allFragmentSubcomponentBuilderProvider = new Provider<BuildersModule_AllFragment.AllFragmentSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.54
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_AllFragment.AllFragmentSubcomponent.Builder get() {
                return new AllFragmentSubcomponentBuilder();
            }
        };
        this.refundFragmentSubcomponentBuilderProvider = new Provider<BuildersModule_RefundFragment.RefundFragmentSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.55
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_RefundFragment.RefundFragmentSubcomponent.Builder get() {
                return new RefundFragmentSubcomponentBuilder();
            }
        };
        this.effectiveorderFragmentSubcomponentBuilderProvider = new Provider<BuildersModule_EffectiveorderFragment.EffectiveorderFragmentSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.56
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_EffectiveorderFragment.EffectiveorderFragmentSubcomponent.Builder get() {
                return new EffectiveorderFragmentSubcomponentBuilder();
            }
        };
        this.failureoftheorderFragmentSubcomponentBuilderProvider = new Provider<BuildersModule_FailureoftheorderFragment.FailureoftheorderFragmentSubcomponent.Builder>() { // from class: com.yltx.oil.partner.injections.components.DaggerGlobalComponent.57
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public BuildersModule_FailureoftheorderFragment.FailureoftheorderFragmentSubcomponent.Builder get() {
                return new FailureoftheorderFragmentSubcomponentBuilder();
            }
        };
        this.seedInstanceProvider = InstanceFactory.create(builder.seedInstance);
        this.provideContextProvider = DoubleCheck.provider(AppModule_ProvideContextFactory.create(builder.appModule, this.seedInstanceProvider));
        this.providesStethoIntercetorProvider = DoubleCheck.provider(DebugInstrumentationModule_ProvidesStethoIntercetorFactory.create(builder.debugInstrumentationModule));
        this.providesStethoInstrumentationProvider = DoubleCheck.provider(DebugInstrumentationModule_ProvidesStethoInstrumentationFactory.create(builder.debugInstrumentationModule, this.provideContextProvider, this.providesStethoIntercetorProvider));
        this.providesHttpLoggingIntercetorProvider = DoubleCheck.provider(DebugInstrumentationModule_ProvidesHttpLoggingIntercetorFactory.create(builder.debugInstrumentationModule));
        this.providesHttpLoggingInstrumentationProvider = DoubleCheck.provider(DebugInstrumentationModule_ProvidesHttpLoggingInstrumentationFactory.create(builder.debugInstrumentationModule, this.provideContextProvider, this.providesHttpLoggingIntercetorProvider));
        this.providesInstrumentationProvider = DoubleCheck.provider(DebugInstrumentationModule_ProvidesInstrumentationFactory.create(builder.debugInstrumentationModule, this.providesStethoInstrumentationProvider, this.providesHttpLoggingInstrumentationProvider));
        this.provideNavigatorProvider = DoubleCheck.provider(GlobalModule_ProvideNavigatorFactory.create(builder.globalModule));
        this.provideOkHttpClientProvider = DoubleCheck.provider(NetworkModule_ProvideOkHttpClientFactory.create(builder.networkModule, this.providesStethoInstrumentationProvider, this.providesHttpLoggingInstrumentationProvider));
        this.provideRestDataSourceProvider = DoubleCheck.provider(GlobalModule_ProvideRestDataSourceFactory.create(builder.globalModule, this.provideContextProvider, this.provideOkHttpClientProvider));
        this.provideDataRepositoryProvider = DoubleCheck.provider(GlobalModule_ProvideDataRepositoryFactory.create(builder.globalModule, this.provideContextProvider, this.provideRestDataSourceProvider));
        this.oSSFileHelperProvider = DoubleCheck.provider(OSSFileHelper_Factory.create(this.provideContextProvider));
    }

    @Override // dagger.android.AndroidInjector
    public void inject(PartnerApplication partnerApplication) {
        injectPartnerApplication(partnerApplication);
    }

    private PartnerApplication injectPartnerApplication(PartnerApplication partnerApplication) {
        DaggerApplication_MembersInjector.injectActivityInjector(partnerApplication, getDispatchingAndroidInjectorOfActivity());
        DaggerApplication_MembersInjector.injectBroadcastReceiverInjector(partnerApplication, getDispatchingAndroidInjectorOfBroadcastReceiver());
        DaggerApplication_MembersInjector.injectFragmentInjector(partnerApplication, getDispatchingAndroidInjectorOfFragment());
        DaggerApplication_MembersInjector.injectServiceInjector(partnerApplication, getDispatchingAndroidInjectorOfService());
        DaggerApplication_MembersInjector.injectContentProviderInjector(partnerApplication, getDispatchingAndroidInjectorOfContentProvider());
        DaggerApplication_MembersInjector.injectSetInjected(partnerApplication);
        dagger.android.support.DaggerApplication_MembersInjector.injectSupportFragmentInjector(partnerApplication, getDispatchingAndroidInjectorOfFragment2());
        PartnerApplication_MembersInjector.injectMInstrumentation(partnerApplication, this.providesInstrumentationProvider.get());
        PartnerApplication_MembersInjector.injectMNavigator(partnerApplication, this.provideNavigatorProvider.get());
        return partnerApplication;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Builder extends GlobalComponent.Builder {
        private AppModule appModule;
        private DebugInstrumentationModule debugInstrumentationModule;
        private GlobalModule globalModule;
        private NetworkModule networkModule;
        private PartnerApplication seedInstance;

        private Builder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<PartnerApplication> build2() {
            if (this.debugInstrumentationModule == null) {
                this.debugInstrumentationModule = new DebugInstrumentationModule();
            }
            if (this.appModule == null) {
                this.appModule = new AppModule();
            }
            if (this.globalModule == null) {
                this.globalModule = new GlobalModule();
            }
            if (this.networkModule == null) {
                this.networkModule = new NetworkModule();
            }
            if (this.seedInstance == null) {
                throw new IllegalStateException(PartnerApplication.class.getCanonicalName() + " must be set");
            }
            return new DaggerGlobalComponent(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(PartnerApplication partnerApplication) {
            this.seedInstance = (PartnerApplication) Preconditions.checkNotNull(partnerApplication);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SplashActivitySubcomponentBuilder extends BuildersModule_SplashActivity.SplashActivitySubcomponent.Builder {
        private SplashActivity seedInstance;

        private SplashActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<SplashActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(SplashActivity.class.getCanonicalName() + " must be set");
            }
            return new SplashActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(SplashActivity splashActivity) {
            this.seedInstance = (SplashActivity) Preconditions.checkNotNull(splashActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SplashActivitySubcomponentImpl implements BuildersModule_SplashActivity.SplashActivitySubcomponent {
        private AutoUseCase_Factory autoUseCaseProvider;
        private CheckVersionUseCase_Factory checkVersionUseCaseProvider;
        private Provider<LoginPresenter> loginPresenterProvider;
        private LoginSmUseCase_Factory loginSmUseCaseProvider;
        private LoginUseCase_Factory loginUseCaseProvider;
        private Provider<SplashPresenter> splashPresenterProvider;

        private SplashActivitySubcomponentImpl(SplashActivitySubcomponentBuilder splashActivitySubcomponentBuilder) {
            initialize(splashActivitySubcomponentBuilder);
        }

        private void initialize(SplashActivitySubcomponentBuilder splashActivitySubcomponentBuilder) {
            this.loginUseCaseProvider = LoginUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.loginSmUseCaseProvider = LoginSmUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.autoUseCaseProvider = AutoUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.loginPresenterProvider = DoubleCheck.provider(LoginPresenter_Factory.create(this.loginUseCaseProvider, this.loginSmUseCaseProvider, this.autoUseCaseProvider));
            this.checkVersionUseCaseProvider = CheckVersionUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.splashPresenterProvider = DoubleCheck.provider(SplashPresenter_Factory.create(this.checkVersionUseCaseProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(SplashActivity splashActivity) {
            injectSplashActivity(splashActivity);
        }

        private SplashActivity injectSplashActivity(SplashActivity splashActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(splashActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(splashActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            SplashActivity_MembersInjector.injectMPresenter(splashActivity, this.loginPresenterProvider.get());
            SplashActivity_MembersInjector.injectPresenter(splashActivity, this.splashPresenterProvider.get());
            return splashActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class LoginActivitySubcomponentBuilder extends BuildersModule_LoginActivity.LoginActivitySubcomponent.Builder {
        private LoginActivity seedInstance;

        private LoginActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<LoginActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(LoginActivity.class.getCanonicalName() + " must be set");
            }
            return new LoginActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(LoginActivity loginActivity) {
            this.seedInstance = (LoginActivity) Preconditions.checkNotNull(loginActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class LoginActivitySubcomponentImpl implements BuildersModule_LoginActivity.LoginActivitySubcomponent {
        private LoginActivitySubcomponentImpl(LoginActivitySubcomponentBuilder loginActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(LoginActivity loginActivity) {
            injectLoginActivity(loginActivity);
        }

        private LoginActivity injectLoginActivity(LoginActivity loginActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(loginActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(loginActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return loginActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class CommoditySharingInforActivitySubcomponentBuilder extends BuildersModule_CommoditySharingInforActivity.CommoditySharingInforActivitySubcomponent.Builder {
        private CommoditySharingInforActivity seedInstance;

        private CommoditySharingInforActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<CommoditySharingInforActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(CommoditySharingInforActivity.class.getCanonicalName() + " must be set");
            }
            return new CommoditySharingInforActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(CommoditySharingInforActivity commoditySharingInforActivity) {
            this.seedInstance = (CommoditySharingInforActivity) Preconditions.checkNotNull(commoditySharingInforActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class CommoditySharingInforActivitySubcomponentImpl implements BuildersModule_CommoditySharingInforActivity.CommoditySharingInforActivitySubcomponent {
        private CommoditySharingInforActivitySubcomponentImpl(CommoditySharingInforActivitySubcomponentBuilder commoditySharingInforActivitySubcomponentBuilder) {
        }

        private FuelCardDetailsUseCase getFuelCardDetailsUseCase() {
            return new FuelCardDetailsUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private FinanceCardDetailUseCase getFinanceCardDetailUseCase() {
            return new FinanceCardDetailUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private StoredValueCardDetailUseCase getStoredValueCardDetailUseCase() {
            return new StoredValueCardDetailUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private ShopDetailUseCase getShopDetailUseCase() {
            return new ShopDetailUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private SFDetailUseCase getSFDetailUseCase() {
            return new SFDetailUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private GiftCardDetailUseCase getGiftCardDetailUseCase() {
            return new GiftCardDetailUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private FinanceCardetailPresenter getFinanceCardetailPresenter() {
            return new FinanceCardetailPresenter(getFuelCardDetailsUseCase(), getFinanceCardDetailUseCase(), getStoredValueCardDetailUseCase(), getShopDetailUseCase(), getSFDetailUseCase(), getGiftCardDetailUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(CommoditySharingInforActivity commoditySharingInforActivity) {
            injectCommoditySharingInforActivity(commoditySharingInforActivity);
        }

        private CommoditySharingInforActivity injectCommoditySharingInforActivity(CommoditySharingInforActivity commoditySharingInforActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(commoditySharingInforActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(commoditySharingInforActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            CommoditySharingInforActivity_MembersInjector.injectFinanceCardetailPresenter(commoditySharingInforActivity, getFinanceCardetailPresenter());
            return commoditySharingInforActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class MainActivitySubcomponentBuilder extends BuildersModule_MainActivity.MainActivitySubcomponent.Builder {
        private MainActivity seedInstance;

        private MainActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<MainActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(MainActivity.class.getCanonicalName() + " must be set");
            }
            return new MainActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(MainActivity mainActivity) {
            this.seedInstance = (MainActivity) Preconditions.checkNotNull(mainActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class MainActivitySubcomponentImpl implements BuildersModule_MainActivity.MainActivitySubcomponent {
        private MainActivitySubcomponentImpl(MainActivitySubcomponentBuilder mainActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(MainActivity mainActivity) {
            injectMainActivity(mainActivity);
        }

        private MainActivity injectMainActivity(MainActivity mainActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(mainActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(mainActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return mainActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ApplyingPartnerActivitySubcomponentBuilder extends BuildersModule_ApplyingPartnerActivity.ApplyingPartnerActivitySubcomponent.Builder {
        private ApplyingPartnerActivity seedInstance;

        private ApplyingPartnerActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<ApplyingPartnerActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(ApplyingPartnerActivity.class.getCanonicalName() + " must be set");
            }
            return new ApplyingPartnerActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(ApplyingPartnerActivity applyingPartnerActivity) {
            this.seedInstance = (ApplyingPartnerActivity) Preconditions.checkNotNull(applyingPartnerActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ApplyingPartnerActivitySubcomponentImpl implements BuildersModule_ApplyingPartnerActivity.ApplyingPartnerActivitySubcomponent {
        private ApplyingPartnerActivitySubcomponentImpl(ApplyingPartnerActivitySubcomponentBuilder applyingPartnerActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(ApplyingPartnerActivity applyingPartnerActivity) {
            injectApplyingPartnerActivity(applyingPartnerActivity);
        }

        private ApplyingPartnerActivity injectApplyingPartnerActivity(ApplyingPartnerActivity applyingPartnerActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(applyingPartnerActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(applyingPartnerActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return applyingPartnerActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class RegisterActivitySubcomponentBuilder extends BuildersModule_RegisterActivity.RegisterActivitySubcomponent.Builder {
        private RegisterActivity seedInstance;

        private RegisterActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<RegisterActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(RegisterActivity.class.getCanonicalName() + " must be set");
            }
            return new RegisterActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(RegisterActivity registerActivity) {
            this.seedInstance = (RegisterActivity) Preconditions.checkNotNull(registerActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class RegisterActivitySubcomponentImpl implements BuildersModule_RegisterActivity.RegisterActivitySubcomponent {
        private AutoUseCase_Factory autoUseCaseProvider;
        private GetImgCodeUseCase_Factory getImgCodeUseCaseProvider;
        private Provider<GetValidCodePresenter> getValidCodePresenterProvider;
        private GetvalidCodeUseCase_Factory getvalidCodeUseCaseProvider;
        private Provider<LoginPresenter> loginPresenterProvider;
        private LoginSmUseCase_Factory loginSmUseCaseProvider;
        private LoginUseCase_Factory loginUseCaseProvider;
        private Provider<RegisterPresenter> registerPresenterProvider;
        private RegisterUseCase_Factory registerUseCaseProvider;

        private RegisterActivitySubcomponentImpl(RegisterActivitySubcomponentBuilder registerActivitySubcomponentBuilder) {
            initialize(registerActivitySubcomponentBuilder);
        }

        private void initialize(RegisterActivitySubcomponentBuilder registerActivitySubcomponentBuilder) {
            this.getvalidCodeUseCaseProvider = GetvalidCodeUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.getValidCodePresenterProvider = DoubleCheck.provider(GetValidCodePresenter_Factory.create(this.getvalidCodeUseCaseProvider));
            this.registerUseCaseProvider = RegisterUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.getImgCodeUseCaseProvider = GetImgCodeUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.registerPresenterProvider = DoubleCheck.provider(RegisterPresenter_Factory.create(this.registerUseCaseProvider, this.getImgCodeUseCaseProvider));
            this.loginUseCaseProvider = LoginUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.loginSmUseCaseProvider = LoginSmUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.autoUseCaseProvider = AutoUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.loginPresenterProvider = DoubleCheck.provider(LoginPresenter_Factory.create(this.loginUseCaseProvider, this.loginSmUseCaseProvider, this.autoUseCaseProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(RegisterActivity registerActivity) {
            injectRegisterActivity(registerActivity);
        }

        private RegisterActivity injectRegisterActivity(RegisterActivity registerActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(registerActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(registerActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            RegisterActivity_MembersInjector.injectGetValidCodePresenter(registerActivity, this.getValidCodePresenterProvider.get());
            RegisterActivity_MembersInjector.injectRegisterPresenter(registerActivity, this.registerPresenterProvider.get());
            RegisterActivity_MembersInjector.injectMPresenter(registerActivity, this.loginPresenterProvider.get());
            return registerActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ForgetPasswordActivitySubcomponentBuilder extends BuildersModule_ForgetPasswordActivity.ForgetPasswordActivitySubcomponent.Builder {
        private ForgetPasswordActivity seedInstance;

        private ForgetPasswordActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<ForgetPasswordActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(ForgetPasswordActivity.class.getCanonicalName() + " must be set");
            }
            return new ForgetPasswordActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(ForgetPasswordActivity forgetPasswordActivity) {
            this.seedInstance = (ForgetPasswordActivity) Preconditions.checkNotNull(forgetPasswordActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ForgetPasswordActivitySubcomponentImpl implements BuildersModule_ForgetPasswordActivity.ForgetPasswordActivitySubcomponent {
        private FindpwUseCase_Factory findpwUseCaseProvider;
        private Provider<ForgetPwdPresenter> forgetPwdPresenterProvider;
        private GetvalidCodeUseCase_Factory getvalidCodeUseCaseProvider;

        private ForgetPasswordActivitySubcomponentImpl(ForgetPasswordActivitySubcomponentBuilder forgetPasswordActivitySubcomponentBuilder) {
            initialize(forgetPasswordActivitySubcomponentBuilder);
        }

        private void initialize(ForgetPasswordActivitySubcomponentBuilder forgetPasswordActivitySubcomponentBuilder) {
            this.findpwUseCaseProvider = FindpwUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.getvalidCodeUseCaseProvider = GetvalidCodeUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.forgetPwdPresenterProvider = DoubleCheck.provider(ForgetPwdPresenter_Factory.create(this.findpwUseCaseProvider, this.getvalidCodeUseCaseProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(ForgetPasswordActivity forgetPasswordActivity) {
            injectForgetPasswordActivity(forgetPasswordActivity);
        }

        private ForgetPasswordActivity injectForgetPasswordActivity(ForgetPasswordActivity forgetPasswordActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(forgetPasswordActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(forgetPasswordActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            ForgetPasswordActivity_MembersInjector.injectMPresenter(forgetPasswordActivity, this.forgetPwdPresenterProvider.get());
            return forgetPasswordActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class YLSPLoginActivitySubcomponentBuilder extends BuildersModule_YlspLoginActivity.YLSPLoginActivitySubcomponent.Builder {
        private YLSPLoginActivity seedInstance;

        private YLSPLoginActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<YLSPLoginActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(YLSPLoginActivity.class.getCanonicalName() + " must be set");
            }
            return new YLSPLoginActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(YLSPLoginActivity yLSPLoginActivity) {
            this.seedInstance = (YLSPLoginActivity) Preconditions.checkNotNull(yLSPLoginActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class YLSPLoginActivitySubcomponentImpl implements BuildersModule_YlspLoginActivity.YLSPLoginActivitySubcomponent {
        private YLSPLoginActivitySubcomponentImpl(YLSPLoginActivitySubcomponentBuilder yLSPLoginActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(YLSPLoginActivity yLSPLoginActivity) {
            injectYLSPLoginActivity(yLSPLoginActivity);
        }

        private YLSPLoginActivity injectYLSPLoginActivity(YLSPLoginActivity yLSPLoginActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(yLSPLoginActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(yLSPLoginActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return yLSPLoginActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ComplaintActivitySubcomponentBuilder extends BuildersModule_ComplaintActivity.ComplaintActivitySubcomponent.Builder {
        private ComplaintActivity seedInstance;

        private ComplaintActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<ComplaintActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(ComplaintActivity.class.getCanonicalName() + " must be set");
            }
            return new ComplaintActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(ComplaintActivity complaintActivity) {
            this.seedInstance = (ComplaintActivity) Preconditions.checkNotNull(complaintActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ComplaintActivitySubcomponentImpl implements BuildersModule_ComplaintActivity.ComplaintActivitySubcomponent {
        private ComplaintActivitySubcomponentImpl(ComplaintActivitySubcomponentBuilder complaintActivitySubcomponentBuilder) {
        }

        private ComplaintOrderUseCase getComplaintOrderUseCase() {
            return new ComplaintOrderUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private ComplaintOrderPresenter getComplaintOrderPresenter() {
            return new ComplaintOrderPresenter(getComplaintOrderUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(ComplaintActivity complaintActivity) {
            injectComplaintActivity(complaintActivity);
        }

        private ComplaintActivity injectComplaintActivity(ComplaintActivity complaintActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(complaintActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(complaintActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            ComplaintActivity_MembersInjector.injectComplaintPresenter(complaintActivity, getComplaintOrderPresenter());
            return complaintActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ComplainValetActivitySubcomponentBuilder extends BuildersModule_ComplainValetActivity.ComplainValetActivitySubcomponent.Builder {
        private ComplainValetActivity seedInstance;

        private ComplainValetActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<ComplainValetActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(ComplainValetActivity.class.getCanonicalName() + " must be set");
            }
            return new ComplainValetActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(ComplainValetActivity complainValetActivity) {
            this.seedInstance = (ComplainValetActivity) Preconditions.checkNotNull(complainValetActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ComplainValetActivitySubcomponentImpl implements BuildersModule_ComplainValetActivity.ComplainValetActivitySubcomponent {
        private ComplainValetActivitySubcomponentImpl(ComplainValetActivitySubcomponentBuilder complainValetActivitySubcomponentBuilder) {
        }

        private ComplaintUseCase getComplaintUseCase() {
            return new ComplaintUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private ComplaintPresenter getComplaintPresenter() {
            return new ComplaintPresenter(getComplaintUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(ComplainValetActivity complainValetActivity) {
            injectComplainValetActivity(complainValetActivity);
        }

        private ComplainValetActivity injectComplainValetActivity(ComplainValetActivity complainValetActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(complainValetActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(complainValetActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            ComplainValetActivity_MembersInjector.injectComplaintPresenter(complainValetActivity, getComplaintPresenter());
            return complainValetActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class NoviceGuideActivitySubcomponentBuilder extends BuildersModule_NoviceGuideActivity.NoviceGuideActivitySubcomponent.Builder {
        private NoviceGuideActivity seedInstance;

        private NoviceGuideActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<NoviceGuideActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(NoviceGuideActivity.class.getCanonicalName() + " must be set");
            }
            return new NoviceGuideActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(NoviceGuideActivity noviceGuideActivity) {
            this.seedInstance = (NoviceGuideActivity) Preconditions.checkNotNull(noviceGuideActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class NoviceGuideActivitySubcomponentImpl implements BuildersModule_NoviceGuideActivity.NoviceGuideActivitySubcomponent {
        private NoviceGuideActivitySubcomponentImpl(NoviceGuideActivitySubcomponentBuilder noviceGuideActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(NoviceGuideActivity noviceGuideActivity) {
            injectNoviceGuideActivity(noviceGuideActivity);
        }

        private NoviceGuideActivity injectNoviceGuideActivity(NoviceGuideActivity noviceGuideActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(noviceGuideActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(noviceGuideActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return noviceGuideActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class HelpCenterActivitySubcomponentBuilder extends BuildersModule_HelpCenterActivity.HelpCenterActivitySubcomponent.Builder {
        private HelpCenterActivity seedInstance;

        private HelpCenterActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<HelpCenterActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(HelpCenterActivity.class.getCanonicalName() + " must be set");
            }
            return new HelpCenterActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(HelpCenterActivity helpCenterActivity) {
            this.seedInstance = (HelpCenterActivity) Preconditions.checkNotNull(helpCenterActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class HelpCenterActivitySubcomponentImpl implements BuildersModule_HelpCenterActivity.HelpCenterActivitySubcomponent {
        private HelpCenterActivitySubcomponentImpl(HelpCenterActivitySubcomponentBuilder helpCenterActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(HelpCenterActivity helpCenterActivity) {
            injectHelpCenterActivity(helpCenterActivity);
        }

        private HelpCenterActivity injectHelpCenterActivity(HelpCenterActivity helpCenterActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(helpCenterActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(helpCenterActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return helpCenterActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FeedbackActivitySubcomponentBuilder extends BuildersModule_FeedbackActivity.FeedbackActivitySubcomponent.Builder {
        private FeedbackActivity seedInstance;

        private FeedbackActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<FeedbackActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(FeedbackActivity.class.getCanonicalName() + " must be set");
            }
            return new FeedbackActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(FeedbackActivity feedbackActivity) {
            this.seedInstance = (FeedbackActivity) Preconditions.checkNotNull(feedbackActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FeedbackActivitySubcomponentImpl implements BuildersModule_FeedbackActivity.FeedbackActivitySubcomponent {
        private FeedbackActivitySubcomponentImpl(FeedbackActivitySubcomponentBuilder feedbackActivitySubcomponentBuilder) {
        }

        private MyfeedbackUseCase getMyfeedbackUseCase() {
            return new MyfeedbackUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private MyfeedbackSubmintUseCase getMyfeedbackSubmintUseCase() {
            return new MyfeedbackSubmintUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private MyfeedbackPresenter getMyfeedbackPresenter() {
            return new MyfeedbackPresenter(getMyfeedbackUseCase(), getMyfeedbackSubmintUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(FeedbackActivity feedbackActivity) {
            injectFeedbackActivity(feedbackActivity);
        }

        private FeedbackActivity injectFeedbackActivity(FeedbackActivity feedbackActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(feedbackActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(feedbackActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            FeedbackActivity_MembersInjector.injectMyfeedbackPresenter(feedbackActivity, getMyfeedbackPresenter());
            return feedbackActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class MineInfoActivitySubcomponentBuilder extends BuildersModule_MineInfoActivity.MineInfoActivitySubcomponent.Builder {
        private MineInfoActivity seedInstance;

        private MineInfoActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<MineInfoActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(MineInfoActivity.class.getCanonicalName() + " must be set");
            }
            return new MineInfoActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(MineInfoActivity mineInfoActivity) {
            this.seedInstance = (MineInfoActivity) Preconditions.checkNotNull(mineInfoActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class MineInfoActivitySubcomponentImpl implements BuildersModule_MineInfoActivity.MineInfoActivitySubcomponent {
        private MineInfoActivitySubcomponentImpl(MineInfoActivitySubcomponentBuilder mineInfoActivitySubcomponentBuilder) {
        }

        private MineinfoHeadPicUseCase getMineinfoHeadPicUseCase() {
            return new MineinfoHeadPicUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get(), (OSSFileHelper) DaggerGlobalComponent.this.oSSFileHelperProvider.get());
        }

        private MineInfoPresenter getMineInfoPresenter() {
            return new MineInfoPresenter(getMineinfoHeadPicUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(MineInfoActivity mineInfoActivity) {
            injectMineInfoActivity(mineInfoActivity);
        }

        private MineInfoActivity injectMineInfoActivity(MineInfoActivity mineInfoActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(mineInfoActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(mineInfoActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            MineInfoActivity_MembersInjector.injectMPresenter(mineInfoActivity, getMineInfoPresenter());
            return mineInfoActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ClipImageActivitySubcomponentBuilder extends BuildersModule_ClipImageActivity.ClipImageActivitySubcomponent.Builder {
        private ClipImageActivity seedInstance;

        private ClipImageActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<ClipImageActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(ClipImageActivity.class.getCanonicalName() + " must be set");
            }
            return new ClipImageActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(ClipImageActivity clipImageActivity) {
            this.seedInstance = (ClipImageActivity) Preconditions.checkNotNull(clipImageActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ClipImageActivitySubcomponentImpl implements BuildersModule_ClipImageActivity.ClipImageActivitySubcomponent {
        private ClipImageActivitySubcomponentImpl(ClipImageActivitySubcomponentBuilder clipImageActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(ClipImageActivity clipImageActivity) {
            injectClipImageActivity(clipImageActivity);
        }

        private ClipImageActivity injectClipImageActivity(ClipImageActivity clipImageActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(clipImageActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(clipImageActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return clipImageActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ModifyNicknameActivitySubcomponentBuilder extends BuildersModule_ModifyNicknameActivity.ModifyNicknameActivitySubcomponent.Builder {
        private ModifyNicknameActivity seedInstance;

        private ModifyNicknameActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<ModifyNicknameActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(ModifyNicknameActivity.class.getCanonicalName() + " must be set");
            }
            return new ModifyNicknameActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(ModifyNicknameActivity modifyNicknameActivity) {
            this.seedInstance = (ModifyNicknameActivity) Preconditions.checkNotNull(modifyNicknameActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ModifyNicknameActivitySubcomponentImpl implements BuildersModule_ModifyNicknameActivity.ModifyNicknameActivitySubcomponent {
        private ModifyNicknameActivitySubcomponentImpl(ModifyNicknameActivitySubcomponentBuilder modifyNicknameActivitySubcomponentBuilder) {
        }

        private ModifUseCase getModifUseCase() {
            return new ModifUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private ModifPresenter getModifPresenter() {
            return new ModifPresenter(getModifUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(ModifyNicknameActivity modifyNicknameActivity) {
            injectModifyNicknameActivity(modifyNicknameActivity);
        }

        private ModifyNicknameActivity injectModifyNicknameActivity(ModifyNicknameActivity modifyNicknameActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(modifyNicknameActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(modifyNicknameActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            ModifyNicknameActivity_MembersInjector.injectModifPresenter(modifyNicknameActivity, getModifPresenter());
            return modifyNicknameActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class InvitationDetailsActivitySubcomponentBuilder extends BuildersModule_InvitationDetailsActivity.InvitationDetailsActivitySubcomponent.Builder {
        private InvitationDetailsActivity seedInstance;

        private InvitationDetailsActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<InvitationDetailsActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(InvitationDetailsActivity.class.getCanonicalName() + " must be set");
            }
            return new InvitationDetailsActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(InvitationDetailsActivity invitationDetailsActivity) {
            this.seedInstance = (InvitationDetailsActivity) Preconditions.checkNotNull(invitationDetailsActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class InvitationDetailsActivitySubcomponentImpl implements BuildersModule_InvitationDetailsActivity.InvitationDetailsActivitySubcomponent {
        private InvitationDetailsActivitySubcomponentImpl(InvitationDetailsActivitySubcomponentBuilder invitationDetailsActivitySubcomponentBuilder) {
        }

        private DetailUseCase getDetailUseCase() {
            return new DetailUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private InviteDetailPresenter getInviteDetailPresenter() {
            return new InviteDetailPresenter(getDetailUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(InvitationDetailsActivity invitationDetailsActivity) {
            injectInvitationDetailsActivity(invitationDetailsActivity);
        }

        private InvitationDetailsActivity injectInvitationDetailsActivity(InvitationDetailsActivity invitationDetailsActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(invitationDetailsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(invitationDetailsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            InvitationDetailsActivity_MembersInjector.injectPresenter(invitationDetailsActivity, getInviteDetailPresenter());
            return invitationDetailsActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class MessageHomeActivitySubcomponentBuilder extends BuildersModule_MessageHomeActivity.MessageHomeActivitySubcomponent.Builder {
        private MessageHomeActivity seedInstance;

        private MessageHomeActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<MessageHomeActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(MessageHomeActivity.class.getCanonicalName() + " must be set");
            }
            return new MessageHomeActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(MessageHomeActivity messageHomeActivity) {
            this.seedInstance = (MessageHomeActivity) Preconditions.checkNotNull(messageHomeActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class MessageHomeActivitySubcomponentImpl implements BuildersModule_MessageHomeActivity.MessageHomeActivitySubcomponent {
        private MessageHomeActivitySubcomponentImpl(MessageHomeActivitySubcomponentBuilder messageHomeActivitySubcomponentBuilder) {
        }

        private MessageNotificationUseCase getMessageNotificationUseCase() {
            return new MessageNotificationUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private MessageNotificationPresenter getMessageNotificationPresenter() {
            return new MessageNotificationPresenter(getMessageNotificationUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(MessageHomeActivity messageHomeActivity) {
            injectMessageHomeActivity(messageHomeActivity);
        }

        private MessageHomeActivity injectMessageHomeActivity(MessageHomeActivity messageHomeActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(messageHomeActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(messageHomeActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            MessageHomeActivity_MembersInjector.injectMessageNotificationPresenter(messageHomeActivity, getMessageNotificationPresenter());
            return messageHomeActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SearchHomeActivitySubcomponentBuilder extends BuildersModule_SearchHomeActivity.SearchHomeActivitySubcomponent.Builder {
        private SearchHomeActivity seedInstance;

        private SearchHomeActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<SearchHomeActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(SearchHomeActivity.class.getCanonicalName() + " must be set");
            }
            return new SearchHomeActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(SearchHomeActivity searchHomeActivity) {
            this.seedInstance = (SearchHomeActivity) Preconditions.checkNotNull(searchHomeActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SearchHomeActivitySubcomponentImpl implements BuildersModule_SearchHomeActivity.SearchHomeActivitySubcomponent {
        private SearchHomeActivitySubcomponentImpl(SearchHomeActivitySubcomponentBuilder searchHomeActivitySubcomponentBuilder) {
        }

        private SSLSUseCase getSSLSUseCase() {
            return new SSLSUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private SSLSPresenter getSSLSPresenter() {
            return new SSLSPresenter(getSSLSUseCase());
        }

        private SCUseCase getSCUseCase() {
            return new SCUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private SCPresenter getSCPresenter() {
            return new SCPresenter(getSCUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(SearchHomeActivity searchHomeActivity) {
            injectSearchHomeActivity(searchHomeActivity);
        }

        private SearchHomeActivity injectSearchHomeActivity(SearchHomeActivity searchHomeActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(searchHomeActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(searchHomeActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            SearchHomeActivity_MembersInjector.injectSslsPresenter(searchHomeActivity, getSSLSPresenter());
            SearchHomeActivity_MembersInjector.injectScPresenter(searchHomeActivity, getSCPresenter());
            return searchHomeActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SearchResultActivitySubcomponentBuilder extends BuildersModule_SearchResultActivity.SearchResultActivitySubcomponent.Builder {
        private SearchResultActivity seedInstance;

        private SearchResultActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<SearchResultActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(SearchResultActivity.class.getCanonicalName() + " must be set");
            }
            return new SearchResultActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(SearchResultActivity searchResultActivity) {
            this.seedInstance = (SearchResultActivity) Preconditions.checkNotNull(searchResultActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SearchResultActivitySubcomponentImpl implements BuildersModule_SearchResultActivity.SearchResultActivitySubcomponent {
        private SearchResultActivitySubcomponentImpl(SearchResultActivitySubcomponentBuilder searchResultActivitySubcomponentBuilder) {
        }

        private SeekUseCase getSeekUseCase() {
            return new SeekUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private SeekPresenter getSeekPresenter() {
            return new SeekPresenter(getSeekUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(SearchResultActivity searchResultActivity) {
            injectSearchResultActivity(searchResultActivity);
        }

        private SearchResultActivity injectSearchResultActivity(SearchResultActivity searchResultActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(searchResultActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(searchResultActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            SearchResultActivity_MembersInjector.injectSeekPresenter(searchResultActivity, getSeekPresenter());
            return searchResultActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class MessageBulletinActivitySubcomponentBuilder extends BuildersModule_MessageBulletinActivity.MessageBulletinActivitySubcomponent.Builder {
        private MessageBulletinActivity seedInstance;

        private MessageBulletinActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<MessageBulletinActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(MessageBulletinActivity.class.getCanonicalName() + " must be set");
            }
            return new MessageBulletinActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(MessageBulletinActivity messageBulletinActivity) {
            this.seedInstance = (MessageBulletinActivity) Preconditions.checkNotNull(messageBulletinActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class MessageBulletinActivitySubcomponentImpl implements BuildersModule_MessageBulletinActivity.MessageBulletinActivitySubcomponent {
        private MessageBulletinActivitySubcomponentImpl(MessageBulletinActivitySubcomponentBuilder messageBulletinActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(MessageBulletinActivity messageBulletinActivity) {
            injectMessageBulletinActivity(messageBulletinActivity);
        }

        private MessageBulletinActivity injectMessageBulletinActivity(MessageBulletinActivity messageBulletinActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(messageBulletinActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(messageBulletinActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return messageBulletinActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class MessageDetailsActivitySubcomponentBuilder extends BuildersModule_MessageDetailsActivity.MessageDetailsActivitySubcomponent.Builder {
        private MessageDetailsActivity seedInstance;

        private MessageDetailsActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<MessageDetailsActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(MessageDetailsActivity.class.getCanonicalName() + " must be set");
            }
            return new MessageDetailsActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(MessageDetailsActivity messageDetailsActivity) {
            this.seedInstance = (MessageDetailsActivity) Preconditions.checkNotNull(messageDetailsActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class MessageDetailsActivitySubcomponentImpl implements BuildersModule_MessageDetailsActivity.MessageDetailsActivitySubcomponent {
        private MessageDetailsActivitySubcomponentImpl(MessageDetailsActivitySubcomponentBuilder messageDetailsActivitySubcomponentBuilder) {
        }

        private MessageForDetailsUseCase getMessageForDetailsUseCase() {
            return new MessageForDetailsUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private MessageForDetailsPresenter getMessageForDetailsPresenter() {
            return new MessageForDetailsPresenter(getMessageForDetailsUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(MessageDetailsActivity messageDetailsActivity) {
            injectMessageDetailsActivity(messageDetailsActivity);
        }

        private MessageDetailsActivity injectMessageDetailsActivity(MessageDetailsActivity messageDetailsActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(messageDetailsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(messageDetailsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            MessageDetailsActivity_MembersInjector.injectMessageForDetailsPresenter(messageDetailsActivity, getMessageForDetailsPresenter());
            return messageDetailsActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class InviteCourtesyActivitySubcomponentBuilder extends BuildersModule_InviteCourtesyActivity.InviteCourtesyActivitySubcomponent.Builder {
        private InviteCourtesyActivity seedInstance;

        private InviteCourtesyActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<InviteCourtesyActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(InviteCourtesyActivity.class.getCanonicalName() + " must be set");
            }
            return new InviteCourtesyActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(InviteCourtesyActivity inviteCourtesyActivity) {
            this.seedInstance = (InviteCourtesyActivity) Preconditions.checkNotNull(inviteCourtesyActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class InviteCourtesyActivitySubcomponentImpl implements BuildersModule_InviteCourtesyActivity.InviteCourtesyActivitySubcomponent {
        private InviteCourtesyActivitySubcomponentImpl(InviteCourtesyActivitySubcomponentBuilder inviteCourtesyActivitySubcomponentBuilder) {
        }

        private TotalUseCase getTotalUseCase() {
            return new TotalUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private InvitePresenter getInvitePresenter() {
            return new InvitePresenter(getTotalUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(InviteCourtesyActivity inviteCourtesyActivity) {
            injectInviteCourtesyActivity(inviteCourtesyActivity);
        }

        private InviteCourtesyActivity injectInviteCourtesyActivity(InviteCourtesyActivity inviteCourtesyActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(inviteCourtesyActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(inviteCourtesyActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            InviteCourtesyActivity_MembersInjector.injectPresenter(inviteCourtesyActivity, getInvitePresenter());
            return inviteCourtesyActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class PhoneActivitySubcomponentBuilder extends BuildersModule_PhoneActivity.PhoneActivitySubcomponent.Builder {
        private PhoneActivity seedInstance;

        private PhoneActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<PhoneActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(PhoneActivity.class.getCanonicalName() + " must be set");
            }
            return new PhoneActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(PhoneActivity phoneActivity) {
            this.seedInstance = (PhoneActivity) Preconditions.checkNotNull(phoneActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class PhoneActivitySubcomponentImpl implements BuildersModule_PhoneActivity.PhoneActivitySubcomponent {
        private PhoneActivitySubcomponentImpl(PhoneActivitySubcomponentBuilder phoneActivitySubcomponentBuilder) {
        }

        private MineinfoOldPhoneUseCase getMineinfoOldPhoneUseCase() {
            return new MineinfoOldPhoneUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private MineinfoUpdatePhoneUseCase getMineinfoUpdatePhoneUseCase() {
            return new MineinfoUpdatePhoneUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private GetvalidCodeUseCase getGetvalidCodeUseCase() {
            return new GetvalidCodeUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private MinePhonePresenter getMinePhonePresenter() {
            return new MinePhonePresenter(getMineinfoOldPhoneUseCase(), getMineinfoUpdatePhoneUseCase(), getGetvalidCodeUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(PhoneActivity phoneActivity) {
            injectPhoneActivity(phoneActivity);
        }

        private PhoneActivity injectPhoneActivity(PhoneActivity phoneActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(phoneActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(phoneActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            PhoneActivity_MembersInjector.injectMPresenter(phoneActivity, getMinePhonePresenter());
            return phoneActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class UpdatePwdActivitySubcomponentBuilder extends BuildersModule_UpdatePwdActivity.UpdatePwdActivitySubcomponent.Builder {
        private UpdatePwdActivity seedInstance;

        private UpdatePwdActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<UpdatePwdActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(UpdatePwdActivity.class.getCanonicalName() + " must be set");
            }
            return new UpdatePwdActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(UpdatePwdActivity updatePwdActivity) {
            this.seedInstance = (UpdatePwdActivity) Preconditions.checkNotNull(updatePwdActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class UpdatePwdActivitySubcomponentImpl implements BuildersModule_UpdatePwdActivity.UpdatePwdActivitySubcomponent {
        private FindpwUseCase_Factory findpwUseCaseProvider;
        private Provider<ForgetPwdPresenter> forgetPwdPresenterProvider;
        private GetvalidCodeUseCase_Factory getvalidCodeUseCaseProvider;

        private UpdatePwdActivitySubcomponentImpl(UpdatePwdActivitySubcomponentBuilder updatePwdActivitySubcomponentBuilder) {
            initialize(updatePwdActivitySubcomponentBuilder);
        }

        private MineinfoOldPhoneUseCase getMineinfoOldPhoneUseCase() {
            return new MineinfoOldPhoneUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private MineinfoUpdatePhoneUseCase getMineinfoUpdatePhoneUseCase() {
            return new MineinfoUpdatePhoneUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private GetvalidCodeUseCase getGetvalidCodeUseCase() {
            return new GetvalidCodeUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private MinePhonePresenter getMinePhonePresenter() {
            return new MinePhonePresenter(getMineinfoOldPhoneUseCase(), getMineinfoUpdatePhoneUseCase(), getGetvalidCodeUseCase());
        }

        private void initialize(UpdatePwdActivitySubcomponentBuilder updatePwdActivitySubcomponentBuilder) {
            this.findpwUseCaseProvider = FindpwUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.getvalidCodeUseCaseProvider = GetvalidCodeUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.forgetPwdPresenterProvider = DoubleCheck.provider(ForgetPwdPresenter_Factory.create(this.findpwUseCaseProvider, this.getvalidCodeUseCaseProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(UpdatePwdActivity updatePwdActivity) {
            injectUpdatePwdActivity(updatePwdActivity);
        }

        private UpdatePwdActivity injectUpdatePwdActivity(UpdatePwdActivity updatePwdActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(updatePwdActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(updatePwdActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            UpdatePwdActivity_MembersInjector.injectForgetPwdPresenter(updatePwdActivity, this.forgetPwdPresenterProvider.get());
            UpdatePwdActivity_MembersInjector.injectMPresenter(updatePwdActivity, getMinePhonePresenter());
            return updatePwdActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ClassificationActivitySubcomponentBuilder extends BuildersModule_ClassificationActivity.ClassificationActivitySubcomponent.Builder {
        private ClassificationActivity seedInstance;

        private ClassificationActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<ClassificationActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(ClassificationActivity.class.getCanonicalName() + " must be set");
            }
            return new ClassificationActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(ClassificationActivity classificationActivity) {
            this.seedInstance = (ClassificationActivity) Preconditions.checkNotNull(classificationActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ClassificationActivitySubcomponentImpl implements BuildersModule_ClassificationActivity.ClassificationActivitySubcomponent {
        private ClassificationActivitySubcomponentImpl(ClassificationActivitySubcomponentBuilder classificationActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(ClassificationActivity classificationActivity) {
            injectClassificationActivity(classificationActivity);
        }

        private ClassificationActivity injectClassificationActivity(ClassificationActivity classificationActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(classificationActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(classificationActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return classificationActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class BindingbankcardsActivitySubcomponentBuilder extends BuildersModule_BindingbankcardsActivity.BindingbankcardsActivitySubcomponent.Builder {
        private BindingbankcardsActivity seedInstance;

        private BindingbankcardsActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<BindingbankcardsActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(BindingbankcardsActivity.class.getCanonicalName() + " must be set");
            }
            return new BindingbankcardsActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(BindingbankcardsActivity bindingbankcardsActivity) {
            this.seedInstance = (BindingbankcardsActivity) Preconditions.checkNotNull(bindingbankcardsActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class BindingbankcardsActivitySubcomponentImpl implements BuildersModule_BindingbankcardsActivity.BindingbankcardsActivitySubcomponent {
        private Provider<GetValidCodePresenter> getValidCodePresenterProvider;
        private GetvalidCodeUseCase_Factory getvalidCodeUseCaseProvider;

        private BindingbankcardsActivitySubcomponentImpl(BindingbankcardsActivitySubcomponentBuilder bindingbankcardsActivitySubcomponentBuilder) {
            initialize(bindingbankcardsActivitySubcomponentBuilder);
        }

        private BindingbankcardsUseCase getBindingbankcardsUseCase() {
            return new BindingbankcardsUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private UpBankcardsUseCase getUpBankcardsUseCase() {
            return new UpBankcardsUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private BindingBankPresenter getBindingBankPresenter() {
            return new BindingBankPresenter(getBindingbankcardsUseCase(), getUpBankcardsUseCase());
        }

        private void initialize(BindingbankcardsActivitySubcomponentBuilder bindingbankcardsActivitySubcomponentBuilder) {
            this.getvalidCodeUseCaseProvider = GetvalidCodeUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.getValidCodePresenterProvider = DoubleCheck.provider(GetValidCodePresenter_Factory.create(this.getvalidCodeUseCaseProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(BindingbankcardsActivity bindingbankcardsActivity) {
            injectBindingbankcardsActivity(bindingbankcardsActivity);
        }

        private BindingbankcardsActivity injectBindingbankcardsActivity(BindingbankcardsActivity bindingbankcardsActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(bindingbankcardsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(bindingbankcardsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            BindingbankcardsActivity_MembersInjector.injectGetValidCodePresenter(bindingbankcardsActivity, this.getValidCodePresenterProvider.get());
            BindingbankcardsActivity_MembersInjector.injectBindingBankPresenter(bindingbankcardsActivity, getBindingBankPresenter());
            return bindingbankcardsActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class TxHistoryActivitySubcomponentBuilder extends BuildersModule_TxHistoryActivity.TxHistoryActivitySubcomponent.Builder {
        private TxHistoryActivity seedInstance;

        private TxHistoryActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<TxHistoryActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(TxHistoryActivity.class.getCanonicalName() + " must be set");
            }
            return new TxHistoryActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(TxHistoryActivity txHistoryActivity) {
            this.seedInstance = (TxHistoryActivity) Preconditions.checkNotNull(txHistoryActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class TxHistoryActivitySubcomponentImpl implements BuildersModule_TxHistoryActivity.TxHistoryActivitySubcomponent {
        private TxHistoryActivitySubcomponentImpl(TxHistoryActivitySubcomponentBuilder txHistoryActivitySubcomponentBuilder) {
        }

        private GetTxHistoryListUseCase getGetTxHistoryListUseCase() {
            return new GetTxHistoryListUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private TxHistoryPresenter getTxHistoryPresenter() {
            return new TxHistoryPresenter(getGetTxHistoryListUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(TxHistoryActivity txHistoryActivity) {
            injectTxHistoryActivity(txHistoryActivity);
        }

        private TxHistoryActivity injectTxHistoryActivity(TxHistoryActivity txHistoryActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(txHistoryActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(txHistoryActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            TxHistoryActivity_MembersInjector.injectMPresenter(txHistoryActivity, getTxHistoryPresenter());
            return txHistoryActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ModificationBankCardsActivitySubcomponentBuilder extends BuildersModule_ModificationBankCardsActivity.ModificationBankCardsActivitySubcomponent.Builder {
        private ModificationBankCardsActivity seedInstance;

        private ModificationBankCardsActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<ModificationBankCardsActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(ModificationBankCardsActivity.class.getCanonicalName() + " must be set");
            }
            return new ModificationBankCardsActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(ModificationBankCardsActivity modificationBankCardsActivity) {
            this.seedInstance = (ModificationBankCardsActivity) Preconditions.checkNotNull(modificationBankCardsActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ModificationBankCardsActivitySubcomponentImpl implements BuildersModule_ModificationBankCardsActivity.ModificationBankCardsActivitySubcomponent {
        private ModificationBankCardsActivitySubcomponentImpl(ModificationBankCardsActivitySubcomponentBuilder modificationBankCardsActivitySubcomponentBuilder) {
        }

        private TxUseCase getTxUseCase() {
            return new TxUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private ModificationPresenter getModificationPresenter() {
            return new ModificationPresenter(getTxUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(ModificationBankCardsActivity modificationBankCardsActivity) {
            injectModificationBankCardsActivity(modificationBankCardsActivity);
        }

        private ModificationBankCardsActivity injectModificationBankCardsActivity(ModificationBankCardsActivity modificationBankCardsActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(modificationBankCardsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(modificationBankCardsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            ModificationBankCardsActivity_MembersInjector.injectPresenter(modificationBankCardsActivity, getModificationPresenter());
            return modificationBankCardsActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class CollectionRecordActivitySubcomponentBuilder extends BuildersModule_CollectionRecordActivity.CollectionRecordActivitySubcomponent.Builder {
        private CollectionRecordActivity seedInstance;

        private CollectionRecordActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<CollectionRecordActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(CollectionRecordActivity.class.getCanonicalName() + " must be set");
            }
            return new CollectionRecordActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(CollectionRecordActivity collectionRecordActivity) {
            this.seedInstance = (CollectionRecordActivity) Preconditions.checkNotNull(collectionRecordActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class CollectionRecordActivitySubcomponentImpl implements BuildersModule_CollectionRecordActivity.CollectionRecordActivitySubcomponent {
        private CollectionRecordActivitySubcomponentImpl(CollectionRecordActivitySubcomponentBuilder collectionRecordActivitySubcomponentBuilder) {
        }

        private SettlementRecordsUseCase getSettlementRecordsUseCase() {
            return new SettlementRecordsUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private SettlementRecordsPresenter getSettlementRecordsPresenter() {
            return new SettlementRecordsPresenter(getSettlementRecordsUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(CollectionRecordActivity collectionRecordActivity) {
            injectCollectionRecordActivity(collectionRecordActivity);
        }

        private CollectionRecordActivity injectCollectionRecordActivity(CollectionRecordActivity collectionRecordActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(collectionRecordActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(collectionRecordActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            CollectionRecordActivity_MembersInjector.injectSettlementRecordsPresenter(collectionRecordActivity, getSettlementRecordsPresenter());
            return collectionRecordActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class DataanalysisActivitySubcomponentBuilder extends BuildersModule_DataanalysisActivity.DataanalysisActivitySubcomponent.Builder {
        private DataanalysisActivity seedInstance;

        private DataanalysisActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<DataanalysisActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(DataanalysisActivity.class.getCanonicalName() + " must be set");
            }
            return new DataanalysisActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(DataanalysisActivity dataanalysisActivity) {
            this.seedInstance = (DataanalysisActivity) Preconditions.checkNotNull(dataanalysisActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class DataanalysisActivitySubcomponentImpl implements BuildersModule_DataanalysisActivity.DataanalysisActivitySubcomponent {
        private DataanalysisActivitySubcomponentImpl(DataanalysisActivitySubcomponentBuilder dataanalysisActivitySubcomponentBuilder) {
        }

        private GeneralizeUseCase getGeneralizeUseCase() {
            return new GeneralizeUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private GeneralizePresenter getGeneralizePresenter() {
            return new GeneralizePresenter(getGeneralizeUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(DataanalysisActivity dataanalysisActivity) {
            injectDataanalysisActivity(dataanalysisActivity);
        }

        private DataanalysisActivity injectDataanalysisActivity(DataanalysisActivity dataanalysisActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(dataanalysisActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(dataanalysisActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            DataanalysisActivity_MembersInjector.injectGeneralizePresenter(dataanalysisActivity, getGeneralizePresenter());
            return dataanalysisActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class OrderdetailsActivitySubcomponentBuilder extends BuildersModule_OrderdetailsActivity.OrderdetailsActivitySubcomponent.Builder {
        private OrderdetailsActivity seedInstance;

        private OrderdetailsActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<OrderdetailsActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(OrderdetailsActivity.class.getCanonicalName() + " must be set");
            }
            return new OrderdetailsActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(OrderdetailsActivity orderdetailsActivity) {
            this.seedInstance = (OrderdetailsActivity) Preconditions.checkNotNull(orderdetailsActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class OrderdetailsActivitySubcomponentImpl implements BuildersModule_OrderdetailsActivity.OrderdetailsActivitySubcomponent {
        private OrderdetailsActivitySubcomponentImpl(OrderdetailsActivitySubcomponentBuilder orderdetailsActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(OrderdetailsActivity orderdetailsActivity) {
            injectOrderdetailsActivity(orderdetailsActivity);
        }

        private OrderdetailsActivity injectOrderdetailsActivity(OrderdetailsActivity orderdetailsActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(orderdetailsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(orderdetailsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return orderdetailsActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SettlementrecordsActivitySubcomponentBuilder extends BuildersModule_SettlementrecordsActivity.SettlementrecordsActivitySubcomponent.Builder {
        private SettlementrecordsActivity seedInstance;

        private SettlementrecordsActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<SettlementrecordsActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(SettlementrecordsActivity.class.getCanonicalName() + " must be set");
            }
            return new SettlementrecordsActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(SettlementrecordsActivity settlementrecordsActivity) {
            this.seedInstance = (SettlementrecordsActivity) Preconditions.checkNotNull(settlementrecordsActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SettlementrecordsActivitySubcomponentImpl implements BuildersModule_SettlementrecordsActivity.SettlementrecordsActivitySubcomponent {
        private SettlementrecordsActivitySubcomponentImpl(SettlementrecordsActivitySubcomponentBuilder settlementrecordsActivitySubcomponentBuilder) {
        }

        private FragmentProfit_yjjsjl_UseCase getFragmentProfit_yjjsjl_UseCase() {
            return new FragmentProfit_yjjsjl_UseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private FragmentProfit_yjjsjl_Presenter getFragmentProfit_yjjsjl_Presenter() {
            return new FragmentProfit_yjjsjl_Presenter(getFragmentProfit_yjjsjl_UseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(SettlementrecordsActivity settlementrecordsActivity) {
            injectSettlementrecordsActivity(settlementrecordsActivity);
        }

        private SettlementrecordsActivity injectSettlementrecordsActivity(SettlementrecordsActivity settlementrecordsActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(settlementrecordsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(settlementrecordsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            SettlementrecordsActivity_MembersInjector.injectProfit_yjjsjl_presenter(settlementrecordsActivity, getFragmentProfit_yjjsjl_Presenter());
            return settlementrecordsActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class WithdrawActivitySubcomponentBuilder extends BuildersModule_WithdrawActivity.WithdrawActivitySubcomponent.Builder {
        private WithdrawActivity seedInstance;

        private WithdrawActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<WithdrawActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(WithdrawActivity.class.getCanonicalName() + " must be set");
            }
            return new WithdrawActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(WithdrawActivity withdrawActivity) {
            this.seedInstance = (WithdrawActivity) Preconditions.checkNotNull(withdrawActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class WithdrawActivitySubcomponentImpl implements BuildersModule_WithdrawActivity.WithdrawActivitySubcomponent {
        private WithdrawActivitySubcomponentImpl(WithdrawActivitySubcomponentBuilder withdrawActivitySubcomponentBuilder) {
        }

        private TxUseCase getTxUseCase() {
            return new TxUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private TxApplyUseCase getTxApplyUseCase() {
            return new TxApplyUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private TxPresenter getTxPresenter() {
            return new TxPresenter(getTxUseCase(), getTxApplyUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(WithdrawActivity withdrawActivity) {
            injectWithdrawActivity(withdrawActivity);
        }

        private WithdrawActivity injectWithdrawActivity(WithdrawActivity withdrawActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(withdrawActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(withdrawActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            WithdrawActivity_MembersInjector.injectPresenter(withdrawActivity, getTxPresenter());
            return withdrawActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class WebActivitySubcomponentBuilder extends BuildersModule_WebActivity.WebActivitySubcomponent.Builder {
        private WebActivity seedInstance;

        private WebActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<WebActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(WebActivity.class.getCanonicalName() + " must be set");
            }
            return new WebActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(WebActivity webActivity) {
            this.seedInstance = (WebActivity) Preconditions.checkNotNull(webActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class WebActivitySubcomponentImpl implements BuildersModule_WebActivity.WebActivitySubcomponent {
        private WebActivitySubcomponentImpl(WebActivitySubcomponentBuilder webActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(WebActivity webActivity) {
            injectWebActivity(webActivity);
        }

        private WebActivity injectWebActivity(WebActivity webActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(webActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(webActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return webActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class AccountdetailsActivitySubcomponentBuilder extends BuildersModule_AccountdetailsActivity.AccountdetailsActivitySubcomponent.Builder {
        private AccountdetailsActivity seedInstance;

        private AccountdetailsActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<AccountdetailsActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(AccountdetailsActivity.class.getCanonicalName() + " must be set");
            }
            return new AccountdetailsActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(AccountdetailsActivity accountdetailsActivity) {
            this.seedInstance = (AccountdetailsActivity) Preconditions.checkNotNull(accountdetailsActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class AccountdetailsActivitySubcomponentImpl implements BuildersModule_AccountdetailsActivity.AccountdetailsActivitySubcomponent {
        private AccountdetailsActivitySubcomponentImpl(AccountdetailsActivitySubcomponentBuilder accountdetailsActivitySubcomponentBuilder) {
        }

        private UserAccountUseCase getUserAccountUseCase() {
            return injectUserAccountUseCase(UserAccountUseCase_Factory.newUserAccountUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get()));
        }

        private UserAccountPresenter getUserAccountPresenter() {
            return new UserAccountPresenter(getUserAccountUseCase());
        }

        private AccountBalanceUseCase getAccountBalanceUseCase() {
            return new AccountBalanceUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private IsAuthUseCase getIsAuthUseCase() {
            return new IsAuthUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private ManagerYZJYFXUseCase getManagerYZJYFXUseCase() {
            return new ManagerYZJYFXUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private AccountBalancePresenter getAccountBalancePresenter() {
            return new AccountBalancePresenter(getAccountBalanceUseCase(), getIsAuthUseCase(), getManagerYZJYFXUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(AccountdetailsActivity accountdetailsActivity) {
            injectAccountdetailsActivity(accountdetailsActivity);
        }

        private UserAccountUseCase injectUserAccountUseCase(UserAccountUseCase userAccountUseCase) {
            UserAccountUseCase_MembersInjector.injectBuildObservable(userAccountUseCase);
            return userAccountUseCase;
        }

        private AccountdetailsActivity injectAccountdetailsActivity(AccountdetailsActivity accountdetailsActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(accountdetailsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(accountdetailsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            AccountdetailsActivity_MembersInjector.injectUserAccountPresenter(accountdetailsActivity, getUserAccountPresenter());
            AccountdetailsActivity_MembersInjector.injectMPresenter(accountdetailsActivity, getAccountBalancePresenter());
            return accountdetailsActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class OptiondateActivitySubcomponentBuilder extends BuildersModule_OptiondateActivity.OptiondateActivitySubcomponent.Builder {
        private OptiondateActivity seedInstance;

        private OptiondateActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<OptiondateActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(OptiondateActivity.class.getCanonicalName() + " must be set");
            }
            return new OptiondateActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(OptiondateActivity optiondateActivity) {
            this.seedInstance = (OptiondateActivity) Preconditions.checkNotNull(optiondateActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class OptiondateActivitySubcomponentImpl implements BuildersModule_OptiondateActivity.OptiondateActivitySubcomponent {
        private OptiondateActivitySubcomponentImpl(OptiondateActivitySubcomponentBuilder optiondateActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(OptiondateActivity optiondateActivity) {
            injectOptiondateActivity(optiondateActivity);
        }

        private OptiondateActivity injectOptiondateActivity(OptiondateActivity optiondateActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(optiondateActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(optiondateActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return optiondateActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ShareDetailsActivitySubcomponentBuilder extends BuildersModule_ShareDetailsActivity.ShareDetailsActivitySubcomponent.Builder {
        private ShareDetailsActivity seedInstance;

        private ShareDetailsActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<ShareDetailsActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(ShareDetailsActivity.class.getCanonicalName() + " must be set");
            }
            return new ShareDetailsActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(ShareDetailsActivity shareDetailsActivity) {
            this.seedInstance = (ShareDetailsActivity) Preconditions.checkNotNull(shareDetailsActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ShareDetailsActivitySubcomponentImpl implements BuildersModule_ShareDetailsActivity.ShareDetailsActivitySubcomponent {
        private ShareDetailsActivitySubcomponentImpl(ShareDetailsActivitySubcomponentBuilder shareDetailsActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(ShareDetailsActivity shareDetailsActivity) {
            injectShareDetailsActivity(shareDetailsActivity);
        }

        private ShareDetailsActivity injectShareDetailsActivity(ShareDetailsActivity shareDetailsActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(shareDetailsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(shareDetailsActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return shareDetailsActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class JsBridgeWebActivitySubcomponentBuilder extends BuildersModule_InviteFriendsActivityInjector.JsBridgeWebActivitySubcomponent.Builder {
        private JsBridgeWebActivity seedInstance;

        private JsBridgeWebActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<JsBridgeWebActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(JsBridgeWebActivity.class.getCanonicalName() + " must be set");
            }
            return new JsBridgeWebActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(JsBridgeWebActivity jsBridgeWebActivity) {
            this.seedInstance = (JsBridgeWebActivity) Preconditions.checkNotNull(jsBridgeWebActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class JsBridgeWebActivitySubcomponentImpl implements BuildersModule_InviteFriendsActivityInjector.JsBridgeWebActivitySubcomponent {
        private JsBridgeWebActivitySubcomponentImpl(JsBridgeWebActivitySubcomponentBuilder jsBridgeWebActivitySubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(JsBridgeWebActivity jsBridgeWebActivity) {
            injectJsBridgeWebActivity(jsBridgeWebActivity);
        }

        private JsBridgeWebActivity injectJsBridgeWebActivity(JsBridgeWebActivity jsBridgeWebActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(jsBridgeWebActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(jsBridgeWebActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            return jsBridgeWebActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class RechargeActivitySubcomponentBuilder extends BuildersModule_RechargeActivity.RechargeActivitySubcomponent.Builder {
        private RechargeActivity seedInstance;

        private RechargeActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<RechargeActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(RechargeActivity.class.getCanonicalName() + " must be set");
            }
            return new RechargeActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(RechargeActivity rechargeActivity) {
            this.seedInstance = (RechargeActivity) Preconditions.checkNotNull(rechargeActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class RechargeActivitySubcomponentImpl implements BuildersModule_RechargeActivity.RechargeActivitySubcomponent {
        private RechargeActivitySubcomponentImpl(RechargeActivitySubcomponentBuilder rechargeActivitySubcomponentBuilder) {
        }

        private RechargePayTypeOrderCae getRechargePayTypeOrderCae() {
            return RechargePayTypeOrderCae_Factory.newRechargePayTypeOrderCae((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private RechargePayOrderCae getRechargePayOrderCae() {
            return RechargePayOrderCae_Factory.newRechargePayOrderCae((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private RechargePresenter getRechargePresenter() {
            return RechargePresenter_Factory.newRechargePresenter(getRechargePayTypeOrderCae(), getRechargePayOrderCae());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(RechargeActivity rechargeActivity) {
            injectRechargeActivity(rechargeActivity);
        }

        private RechargeActivity injectRechargeActivity(RechargeActivity rechargeActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(rechargeActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(rechargeActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            RechargeActivity_MembersInjector.injectMPresenter(rechargeActivity, getRechargePresenter());
            return rechargeActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class NonoilLoginActivitySubcomponentBuilder extends BuildersModule_NonoilLoginActivity.NonoilLoginActivitySubcomponent.Builder {
        private NonoilLoginActivity seedInstance;

        private NonoilLoginActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<NonoilLoginActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(NonoilLoginActivity.class.getCanonicalName() + " must be set");
            }
            return new NonoilLoginActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(NonoilLoginActivity nonoilLoginActivity) {
            this.seedInstance = (NonoilLoginActivity) Preconditions.checkNotNull(nonoilLoginActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class NonoilLoginActivitySubcomponentImpl implements BuildersModule_NonoilLoginActivity.NonoilLoginActivitySubcomponent {
        private Provider<AppLoginStatusPresenter> appLoginStatusPresenterProvider;
        private AppLoginStatusUseCase_Factory appLoginStatusUseCaseProvider;

        private NonoilLoginActivitySubcomponentImpl(NonoilLoginActivitySubcomponentBuilder nonoilLoginActivitySubcomponentBuilder) {
            initialize(nonoilLoginActivitySubcomponentBuilder);
        }

        private void initialize(NonoilLoginActivitySubcomponentBuilder nonoilLoginActivitySubcomponentBuilder) {
            this.appLoginStatusUseCaseProvider = AppLoginStatusUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.appLoginStatusPresenterProvider = DoubleCheck.provider(AppLoginStatusPresenter_Factory.create(this.appLoginStatusUseCaseProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(NonoilLoginActivity nonoilLoginActivity) {
            injectNonoilLoginActivity(nonoilLoginActivity);
        }

        private NonoilLoginActivity injectNonoilLoginActivity(NonoilLoginActivity nonoilLoginActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(nonoilLoginActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(nonoilLoginActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            NonoilLoginActivity_MembersInjector.injectPresenter(nonoilLoginActivity, this.appLoginStatusPresenterProvider.get());
            return nonoilLoginActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class YLTXLoginActivitySubcomponentBuilder extends BuildersModule_YltxLoginActivity.YLTXLoginActivitySubcomponent.Builder {
        private YLTXLoginActivity seedInstance;

        private YLTXLoginActivitySubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<YLTXLoginActivity> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(YLTXLoginActivity.class.getCanonicalName() + " must be set");
            }
            return new YLTXLoginActivitySubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(YLTXLoginActivity yLTXLoginActivity) {
            this.seedInstance = (YLTXLoginActivity) Preconditions.checkNotNull(yLTXLoginActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class YLTXLoginActivitySubcomponentImpl implements BuildersModule_YltxLoginActivity.YLTXLoginActivitySubcomponent {
        private Provider<AppLoginStatusPresenter> appLoginStatusPresenterProvider;
        private AppLoginStatusUseCase_Factory appLoginStatusUseCaseProvider;

        private YLTXLoginActivitySubcomponentImpl(YLTXLoginActivitySubcomponentBuilder yLTXLoginActivitySubcomponentBuilder) {
            initialize(yLTXLoginActivitySubcomponentBuilder);
        }

        private void initialize(YLTXLoginActivitySubcomponentBuilder yLTXLoginActivitySubcomponentBuilder) {
            this.appLoginStatusUseCaseProvider = AppLoginStatusUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.appLoginStatusPresenterProvider = DoubleCheck.provider(AppLoginStatusPresenter_Factory.create(this.appLoginStatusUseCaseProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(YLTXLoginActivity yLTXLoginActivity) {
            injectYLTXLoginActivity(yLTXLoginActivity);
        }

        private YLTXLoginActivity injectYLTXLoginActivity(YLTXLoginActivity yLTXLoginActivity) {
            DaggerAppCompatActivity_MembersInjector.injectSupportFragmentInjector(yLTXLoginActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            DaggerAppCompatActivity_MembersInjector.injectFrameworkFragmentInjector(yLTXLoginActivity, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment());
            YLTXLoginActivity_MembersInjector.injectPresenter(yLTXLoginActivity, this.appLoginStatusPresenterProvider.get());
            return yLTXLoginActivity;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FragmentHomeSubcomponentBuilder extends BuildersModule_FragmentHome.FragmentHomeSubcomponent.Builder {
        private FragmentHome seedInstance;

        private FragmentHomeSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<FragmentHome> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(FragmentHome.class.getCanonicalName() + " must be set");
            }
            return new FragmentHomeSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(FragmentHome fragmentHome) {
            this.seedInstance = (FragmentHome) Preconditions.checkNotNull(fragmentHome);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FragmentHomeSubcomponentImpl implements BuildersModule_FragmentHome.FragmentHomeSubcomponent {
        private Provider<BannerPresenter> bannerPresenterProvider;
        private BannerUseCase_Factory bannerUseCaseProvider;
        private Provider<HomeButtonconfigurationPresenter> homeButtonconfigurationPresenterProvider;
        private HomeButtonconfigurationUseCase_Factory homeButtonconfigurationUseCaseProvider;

        private FragmentHomeSubcomponentImpl(FragmentHomeSubcomponentBuilder fragmentHomeSubcomponentBuilder) {
            initialize(fragmentHomeSubcomponentBuilder);
        }

        private ShopRecommendUseCase getShopRecommendUseCase() {
            return injectShopRecommendUseCase(ShopRecommendUseCase_Factory.newShopRecommendUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get()));
        }

        private ShopRecommendPresenter getShopRecommendPresenter() {
            return new ShopRecommendPresenter(getShopRecommendUseCase());
        }

        private void initialize(FragmentHomeSubcomponentBuilder fragmentHomeSubcomponentBuilder) {
            this.bannerUseCaseProvider = BannerUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.bannerPresenterProvider = DoubleCheck.provider(BannerPresenter_Factory.create(this.bannerUseCaseProvider));
            this.homeButtonconfigurationUseCaseProvider = HomeButtonconfigurationUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.homeButtonconfigurationPresenterProvider = DoubleCheck.provider(HomeButtonconfigurationPresenter_Factory.create(this.homeButtonconfigurationUseCaseProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(FragmentHome fragmentHome) {
            injectFragmentHome(fragmentHome);
        }

        private ShopRecommendUseCase injectShopRecommendUseCase(ShopRecommendUseCase shopRecommendUseCase) {
            ShopRecommendUseCase_MembersInjector.injectBuildObservable(shopRecommendUseCase);
            return shopRecommendUseCase;
        }

        private FragmentHome injectFragmentHome(FragmentHome fragmentHome) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(fragmentHome, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            FragmentHome_MembersInjector.injectMPresenter(fragmentHome, getShopRecommendPresenter());
            FragmentHome_MembersInjector.injectBannerPresenter(fragmentHome, this.bannerPresenterProvider.get());
            FragmentHome_MembersInjector.injectHomeButtonconfigurationPresenter(fragmentHome, this.homeButtonconfigurationPresenterProvider.get());
            return fragmentHome;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FragmentPwdLoginSubcomponentBuilder extends BuildersModule_FragmentPwdLogin.FragmentPwdLoginSubcomponent.Builder {
        private FragmentPwdLogin seedInstance;

        private FragmentPwdLoginSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<FragmentPwdLogin> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(FragmentPwdLogin.class.getCanonicalName() + " must be set");
            }
            return new FragmentPwdLoginSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(FragmentPwdLogin fragmentPwdLogin) {
            this.seedInstance = (FragmentPwdLogin) Preconditions.checkNotNull(fragmentPwdLogin);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FragmentPwdLoginSubcomponentImpl implements BuildersModule_FragmentPwdLogin.FragmentPwdLoginSubcomponent {
        private AutoUseCase_Factory autoUseCaseProvider;
        private Provider<GetValidCodePresenter> getValidCodePresenterProvider;
        private GetvalidCodeUseCase_Factory getvalidCodeUseCaseProvider;
        private Provider<LoginPresenter> loginPresenterProvider;
        private LoginSmUseCase_Factory loginSmUseCaseProvider;
        private LoginUseCase_Factory loginUseCaseProvider;

        private FragmentPwdLoginSubcomponentImpl(FragmentPwdLoginSubcomponentBuilder fragmentPwdLoginSubcomponentBuilder) {
            initialize(fragmentPwdLoginSubcomponentBuilder);
        }

        private void initialize(FragmentPwdLoginSubcomponentBuilder fragmentPwdLoginSubcomponentBuilder) {
            this.loginUseCaseProvider = LoginUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.loginSmUseCaseProvider = LoginSmUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.autoUseCaseProvider = AutoUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.loginPresenterProvider = DoubleCheck.provider(LoginPresenter_Factory.create(this.loginUseCaseProvider, this.loginSmUseCaseProvider, this.autoUseCaseProvider));
            this.getvalidCodeUseCaseProvider = GetvalidCodeUseCase_Factory.create(DaggerGlobalComponent.this.provideDataRepositoryProvider);
            this.getValidCodePresenterProvider = DoubleCheck.provider(GetValidCodePresenter_Factory.create(this.getvalidCodeUseCaseProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(FragmentPwdLogin fragmentPwdLogin) {
            injectFragmentPwdLogin(fragmentPwdLogin);
        }

        private FragmentPwdLogin injectFragmentPwdLogin(FragmentPwdLogin fragmentPwdLogin) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(fragmentPwdLogin, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            FragmentPwdLogin_MembersInjector.injectMPresenter(fragmentPwdLogin, this.loginPresenterProvider.get());
            FragmentPwdLogin_MembersInjector.injectGetValidCodePresenter(fragmentPwdLogin, this.getValidCodePresenterProvider.get());
            return fragmentPwdLogin;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FragmentMineSubcomponentBuilder extends BuildersModule_FragmentMine.FragmentMineSubcomponent.Builder {
        private FragmentMine seedInstance;

        private FragmentMineSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<FragmentMine> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(FragmentMine.class.getCanonicalName() + " must be set");
            }
            return new FragmentMineSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(FragmentMine fragmentMine) {
            this.seedInstance = (FragmentMine) Preconditions.checkNotNull(fragmentMine);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FragmentMineSubcomponentImpl implements BuildersModule_FragmentMine.FragmentMineSubcomponent {
        private FragmentMineSubcomponentImpl(FragmentMineSubcomponentBuilder fragmentMineSubcomponentBuilder) {
        }

        private MemberUseCase getMemberUseCase() {
            return new MemberUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private PhoneUseCase getPhoneUseCase() {
            return new PhoneUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private PersonalCenterUseCase getPersonalCenterUseCase() {
            return new PersonalCenterUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private MemberPresenter getMemberPresenter() {
            return new MemberPresenter(getMemberUseCase(), getPhoneUseCase(), getPersonalCenterUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(FragmentMine fragmentMine) {
            injectFragmentMine(fragmentMine);
        }

        private FragmentMine injectFragmentMine(FragmentMine fragmentMine) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(fragmentMine, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            FragmentMine_MembersInjector.injectMemberPresenter(fragmentMine, getMemberPresenter());
            return fragmentMine;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FragmentOilTradeSubcomponentBuilder extends BuildersModule_FragmentOilTrade.FragmentOilTradeSubcomponent.Builder {
        private FragmentOilTrade seedInstance;

        private FragmentOilTradeSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<FragmentOilTrade> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(FragmentOilTrade.class.getCanonicalName() + " must be set");
            }
            return new FragmentOilTradeSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(FragmentOilTrade fragmentOilTrade) {
            this.seedInstance = (FragmentOilTrade) Preconditions.checkNotNull(fragmentOilTrade);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FragmentOilTradeSubcomponentImpl implements BuildersModule_FragmentOilTrade.FragmentOilTradeSubcomponent {
        private FragmentOilTradeSubcomponentImpl(FragmentOilTradeSubcomponentBuilder fragmentOilTradeSubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(FragmentOilTrade fragmentOilTrade) {
            injectFragmentOilTrade(fragmentOilTrade);
        }

        private FragmentOilTrade injectFragmentOilTrade(FragmentOilTrade fragmentOilTrade) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(fragmentOilTrade, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            return fragmentOilTrade;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class OilCardFragmentSubcomponentBuilder extends BuildersModule_OilCardFragment.OilCardFragmentSubcomponent.Builder {
        private OilCardFragment seedInstance;

        private OilCardFragmentSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<OilCardFragment> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(OilCardFragment.class.getCanonicalName() + " must be set");
            }
            return new OilCardFragmentSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(OilCardFragment oilCardFragment) {
            this.seedInstance = (OilCardFragment) Preconditions.checkNotNull(oilCardFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class OilCardFragmentSubcomponentImpl implements BuildersModule_OilCardFragment.OilCardFragmentSubcomponent {
        private OilCardFragmentSubcomponentImpl(OilCardFragmentSubcomponentBuilder oilCardFragmentSubcomponentBuilder) {
        }

        private FinanceCardUseCase getFinanceCardUseCase() {
            return new FinanceCardUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private FinanceCardPresenter getFinanceCardPresenter() {
            return new FinanceCardPresenter(getFinanceCardUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(OilCardFragment oilCardFragment) {
            injectOilCardFragment(oilCardFragment);
        }

        private OilCardFragment injectOilCardFragment(OilCardFragment oilCardFragment) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(oilCardFragment, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            OilCardFragment_MembersInjector.injectMPresenter(oilCardFragment, getFinanceCardPresenter());
            return oilCardFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class StoredValueCardFragmentSubcomponentBuilder extends BuildersModule_StoredValueCardFragment.StoredValueCardFragmentSubcomponent.Builder {
        private StoredValueCardFragment seedInstance;

        private StoredValueCardFragmentSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<StoredValueCardFragment> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(StoredValueCardFragment.class.getCanonicalName() + " must be set");
            }
            return new StoredValueCardFragmentSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(StoredValueCardFragment storedValueCardFragment) {
            this.seedInstance = (StoredValueCardFragment) Preconditions.checkNotNull(storedValueCardFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class StoredValueCardFragmentSubcomponentImpl implements BuildersModule_StoredValueCardFragment.StoredValueCardFragmentSubcomponent {
        private StoredValueCardFragmentSubcomponentImpl(StoredValueCardFragmentSubcomponentBuilder storedValueCardFragmentSubcomponentBuilder) {
        }

        private StoredValueCardUseCase getStoredValueCardUseCase() {
            return injectStoredValueCardUseCase(StoredValueCardUseCase_Factory.newStoredValueCardUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get()));
        }

        private StoredValueCardPresenter getStoredValueCardPresenter() {
            return new StoredValueCardPresenter(getStoredValueCardUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(StoredValueCardFragment storedValueCardFragment) {
            injectStoredValueCardFragment(storedValueCardFragment);
        }

        private StoredValueCardUseCase injectStoredValueCardUseCase(StoredValueCardUseCase storedValueCardUseCase) {
            StoredValueCardUseCase_MembersInjector.injectBuildObservable(storedValueCardUseCase);
            return storedValueCardUseCase;
        }

        private StoredValueCardFragment injectStoredValueCardFragment(StoredValueCardFragment storedValueCardFragment) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(storedValueCardFragment, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            StoredValueCardFragment_MembersInjector.injectMPresenter(storedValueCardFragment, getStoredValueCardPresenter());
            return storedValueCardFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ShopFragmentSubcomponentBuilder extends BuildersModule_ShopFragment.ShopFragmentSubcomponent.Builder {
        private ShopFragment seedInstance;

        private ShopFragmentSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<ShopFragment> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(ShopFragment.class.getCanonicalName() + " must be set");
            }
            return new ShopFragmentSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(ShopFragment shopFragment) {
            this.seedInstance = (ShopFragment) Preconditions.checkNotNull(shopFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ShopFragmentSubcomponentImpl implements BuildersModule_ShopFragment.ShopFragmentSubcomponent {
        private ShopFragmentSubcomponentImpl(ShopFragmentSubcomponentBuilder shopFragmentSubcomponentBuilder) {
        }

        private ShopUseCase getShopUseCase() {
            return injectShopUseCase(ShopUseCase_Factory.newShopUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get()));
        }

        private ShopPresenter getShopPresenter() {
            return new ShopPresenter(getShopUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(ShopFragment shopFragment) {
            injectShopFragment(shopFragment);
        }

        private ShopUseCase injectShopUseCase(ShopUseCase shopUseCase) {
            ShopUseCase_MembersInjector.injectBuildObservable(shopUseCase);
            return shopUseCase;
        }

        private ShopFragment injectShopFragment(ShopFragment shopFragment) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(shopFragment, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            ShopFragment_MembersInjector.injectMPresenter(shopFragment, getShopPresenter());
            return shopFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class GiftCardFragmentSubcomponentBuilder extends BuildersModule_GiftcardFragment.GiftCardFragmentSubcomponent.Builder {
        private GiftCardFragment seedInstance;

        private GiftCardFragmentSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<GiftCardFragment> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(GiftCardFragment.class.getCanonicalName() + " must be set");
            }
            return new GiftCardFragmentSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(GiftCardFragment giftCardFragment) {
            this.seedInstance = (GiftCardFragment) Preconditions.checkNotNull(giftCardFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class GiftCardFragmentSubcomponentImpl implements BuildersModule_GiftcardFragment.GiftCardFragmentSubcomponent {
        private GiftCardFragmentSubcomponentImpl(GiftCardFragmentSubcomponentBuilder giftCardFragmentSubcomponentBuilder) {
        }

        private GiftCardCardUseCase getGiftCardCardUseCase() {
            return injectGiftCardCardUseCase(GiftCardCardUseCase_Factory.newGiftCardCardUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get()));
        }

        private GiftCardCardPresenter getGiftCardCardPresenter() {
            return new GiftCardCardPresenter(getGiftCardCardUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(GiftCardFragment giftCardFragment) {
            injectGiftCardFragment(giftCardFragment);
        }

        private GiftCardCardUseCase injectGiftCardCardUseCase(GiftCardCardUseCase giftCardCardUseCase) {
            GiftCardCardUseCase_MembersInjector.injectBuildObservable(giftCardCardUseCase);
            return giftCardCardUseCase;
        }

        private GiftCardFragment injectGiftCardFragment(GiftCardFragment giftCardFragment) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(giftCardFragment, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            GiftCardFragment_MembersInjector.injectMPresenter(giftCardFragment, getGiftCardCardPresenter());
            return giftCardFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class RefuelingCardFragmentSubcomponentBuilder extends BuildersModule_RefuelingCardFragment.RefuelingCardFragmentSubcomponent.Builder {
        private RefuelingCardFragment seedInstance;

        private RefuelingCardFragmentSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<RefuelingCardFragment> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(RefuelingCardFragment.class.getCanonicalName() + " must be set");
            }
            return new RefuelingCardFragmentSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(RefuelingCardFragment refuelingCardFragment) {
            this.seedInstance = (RefuelingCardFragment) Preconditions.checkNotNull(refuelingCardFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class RefuelingCardFragmentSubcomponentImpl implements BuildersModule_RefuelingCardFragment.RefuelingCardFragmentSubcomponent {
        private RefuelingCardFragmentSubcomponentImpl(RefuelingCardFragmentSubcomponentBuilder refuelingCardFragmentSubcomponentBuilder) {
        }

        private FuelCardListUseCase getFuelCardListUseCase() {
            return new FuelCardListUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private FuelCardListPresenter getFuelCardListPresenter() {
            return new FuelCardListPresenter(getFuelCardListUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(RefuelingCardFragment refuelingCardFragment) {
            injectRefuelingCardFragment(refuelingCardFragment);
        }

        private RefuelingCardFragment injectRefuelingCardFragment(RefuelingCardFragment refuelingCardFragment) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(refuelingCardFragment, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            RefuelingCardFragment_MembersInjector.injectMPresenter(refuelingCardFragment, getFuelCardListPresenter());
            return refuelingCardFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FragmentProfitSubcomponentBuilder extends BuildersModule_FragmentProfit.FragmentProfitSubcomponent.Builder {
        private FragmentProfit seedInstance;

        private FragmentProfitSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<FragmentProfit> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(FragmentProfit.class.getCanonicalName() + " must be set");
            }
            return new FragmentProfitSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(FragmentProfit fragmentProfit) {
            this.seedInstance = (FragmentProfit) Preconditions.checkNotNull(fragmentProfit);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FragmentProfitSubcomponentImpl implements BuildersModule_FragmentProfit.FragmentProfitSubcomponent {
        private FragmentProfitSubcomponentImpl(FragmentProfitSubcomponentBuilder fragmentProfitSubcomponentBuilder) {
        }

        private AccountBalanceUseCase getAccountBalanceUseCase() {
            return new AccountBalanceUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private IsAuthUseCase getIsAuthUseCase() {
            return new IsAuthUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private ManagerYZJYFXUseCase getManagerYZJYFXUseCase() {
            return new ManagerYZJYFXUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private AccountBalancePresenter getAccountBalancePresenter() {
            return new AccountBalancePresenter(getAccountBalanceUseCase(), getIsAuthUseCase(), getManagerYZJYFXUseCase());
        }

        private FragmentProfit_yjjsjl_UseCase getFragmentProfit_yjjsjl_UseCase() {
            return new FragmentProfit_yjjsjl_UseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private FragmentProfit_yjjsjl_Presenter getFragmentProfit_yjjsjl_Presenter() {
            return new FragmentProfit_yjjsjl_Presenter(getFragmentProfit_yjjsjl_UseCase());
        }

        private CommissionUseCase getCommissionUseCase() {
            return new CommissionUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private CommissionPresenter getCommissionPresenter() {
            return new CommissionPresenter(getCommissionUseCase());
        }

        private JoinUseCase getJoinUseCase() {
            return new JoinUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private JoinPresenter getJoinPresenter() {
            return new JoinPresenter(getJoinUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(FragmentProfit fragmentProfit) {
            injectFragmentProfit(fragmentProfit);
        }

        private FragmentProfit injectFragmentProfit(FragmentProfit fragmentProfit) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(fragmentProfit, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            FragmentProfit_MembersInjector.injectMPresenter(fragmentProfit, getAccountBalancePresenter());
            FragmentProfit_MembersInjector.injectProfit_yjjsjl_presenter(fragmentProfit, getFragmentProfit_yjjsjl_Presenter());
            FragmentProfit_MembersInjector.injectCommissionPresenter(fragmentProfit, getCommissionPresenter());
            FragmentProfit_MembersInjector.injectJoinPresenter(fragmentProfit, getJoinPresenter());
            return fragmentProfit;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class AllordersFragmentSubcomponentBuilder extends BuildersModule_AllordersFragment.AllordersFragmentSubcomponent.Builder {
        private AllordersFragment seedInstance;

        private AllordersFragmentSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<AllordersFragment> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(AllordersFragment.class.getCanonicalName() + " must be set");
            }
            return new AllordersFragmentSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(AllordersFragment allordersFragment) {
            this.seedInstance = (AllordersFragment) Preconditions.checkNotNull(allordersFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class AllordersFragmentSubcomponentImpl implements BuildersModule_AllordersFragment.AllordersFragmentSubcomponent {
        private AllordersFragmentSubcomponentImpl(AllordersFragmentSubcomponentBuilder allordersFragmentSubcomponentBuilder) {
        }

        private AllordersUseCase getAllordersUseCase() {
            return new AllordersUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private AllordersPresenter getAllordersPresenter() {
            return new AllordersPresenter(getAllordersUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(AllordersFragment allordersFragment) {
            injectAllordersFragment(allordersFragment);
        }

        private AllordersFragment injectAllordersFragment(AllordersFragment allordersFragment) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(allordersFragment, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            AllordersFragment_MembersInjector.injectAllordersPresenter(allordersFragment, getAllordersPresenter());
            return allordersFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class AllFragmentSubcomponentBuilder extends BuildersModule_AllFragment.AllFragmentSubcomponent.Builder {
        private AllFragment seedInstance;

        private AllFragmentSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<AllFragment> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(AllFragment.class.getCanonicalName() + " must be set");
            }
            return new AllFragmentSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(AllFragment allFragment) {
            this.seedInstance = (AllFragment) Preconditions.checkNotNull(allFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class AllFragmentSubcomponentImpl implements BuildersModule_AllFragment.AllFragmentSubcomponent {
        private AllFragmentSubcomponentImpl(AllFragmentSubcomponentBuilder allFragmentSubcomponentBuilder) {
        }

        private SettlementRecordsUseCase getSettlementRecordsUseCase() {
            return new SettlementRecordsUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private SettlementRecordsPresenter getSettlementRecordsPresenter() {
            return new SettlementRecordsPresenter(getSettlementRecordsUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(AllFragment allFragment) {
            injectAllFragment(allFragment);
        }

        private AllFragment injectAllFragment(AllFragment allFragment) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(allFragment, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            AllFragment_MembersInjector.injectSettlementRecordsPresenter(allFragment, getSettlementRecordsPresenter());
            return allFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class RefundFragmentSubcomponentBuilder extends BuildersModule_RefundFragment.RefundFragmentSubcomponent.Builder {
        private RefundFragment seedInstance;

        private RefundFragmentSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<RefundFragment> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(RefundFragment.class.getCanonicalName() + " must be set");
            }
            return new RefundFragmentSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(RefundFragment refundFragment) {
            this.seedInstance = (RefundFragment) Preconditions.checkNotNull(refundFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class RefundFragmentSubcomponentImpl implements BuildersModule_RefundFragment.RefundFragmentSubcomponent {
        private RefundFragmentSubcomponentImpl(RefundFragmentSubcomponentBuilder refundFragmentSubcomponentBuilder) {
        }

        @Override // dagger.android.AndroidInjector
        public void inject(RefundFragment refundFragment) {
            injectRefundFragment(refundFragment);
        }

        private RefundFragment injectRefundFragment(RefundFragment refundFragment) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(refundFragment, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            return refundFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class EffectiveorderFragmentSubcomponentBuilder extends BuildersModule_EffectiveorderFragment.EffectiveorderFragmentSubcomponent.Builder {
        private EffectiveorderFragment seedInstance;

        private EffectiveorderFragmentSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<EffectiveorderFragment> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(EffectiveorderFragment.class.getCanonicalName() + " must be set");
            }
            return new EffectiveorderFragmentSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(EffectiveorderFragment effectiveorderFragment) {
            this.seedInstance = (EffectiveorderFragment) Preconditions.checkNotNull(effectiveorderFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class EffectiveorderFragmentSubcomponentImpl implements BuildersModule_EffectiveorderFragment.EffectiveorderFragmentSubcomponent {
        private EffectiveorderFragmentSubcomponentImpl(EffectiveorderFragmentSubcomponentBuilder effectiveorderFragmentSubcomponentBuilder) {
        }

        private AllordersUseCase getAllordersUseCase() {
            return new AllordersUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private AllordersPresenter getAllordersPresenter() {
            return new AllordersPresenter(getAllordersUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(EffectiveorderFragment effectiveorderFragment) {
            injectEffectiveorderFragment(effectiveorderFragment);
        }

        private EffectiveorderFragment injectEffectiveorderFragment(EffectiveorderFragment effectiveorderFragment) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(effectiveorderFragment, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            EffectiveorderFragment_MembersInjector.injectAllordersPresenter(effectiveorderFragment, getAllordersPresenter());
            return effectiveorderFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FailureoftheorderFragmentSubcomponentBuilder extends BuildersModule_FailureoftheorderFragment.FailureoftheorderFragmentSubcomponent.Builder {
        private FailureoftheorderFragment seedInstance;

        private FailureoftheorderFragmentSubcomponentBuilder() {
        }

        @Override // dagger.android.AndroidInjector.Builder
        /* renamed from: build */
        public AndroidInjector<FailureoftheorderFragment> build2() {
            if (this.seedInstance == null) {
                throw new IllegalStateException(FailureoftheorderFragment.class.getCanonicalName() + " must be set");
            }
            return new FailureoftheorderFragmentSubcomponentImpl(this);
        }

        @Override // dagger.android.AndroidInjector.Builder
        public void seedInstance(FailureoftheorderFragment failureoftheorderFragment) {
            this.seedInstance = (FailureoftheorderFragment) Preconditions.checkNotNull(failureoftheorderFragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class FailureoftheorderFragmentSubcomponentImpl implements BuildersModule_FailureoftheorderFragment.FailureoftheorderFragmentSubcomponent {
        private FailureoftheorderFragmentSubcomponentImpl(FailureoftheorderFragmentSubcomponentBuilder failureoftheorderFragmentSubcomponentBuilder) {
        }

        private AllordersUseCase getAllordersUseCase() {
            return new AllordersUseCase((Repository) DaggerGlobalComponent.this.provideDataRepositoryProvider.get());
        }

        private AllordersPresenter getAllordersPresenter() {
            return new AllordersPresenter(getAllordersUseCase());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(FailureoftheorderFragment failureoftheorderFragment) {
            injectFailureoftheorderFragment(failureoftheorderFragment);
        }

        private FailureoftheorderFragment injectFailureoftheorderFragment(FailureoftheorderFragment failureoftheorderFragment) {
            DaggerFragment_MembersInjector.injectChildFragmentInjector(failureoftheorderFragment, DaggerGlobalComponent.this.getDispatchingAndroidInjectorOfFragment2());
            FailureoftheorderFragment_MembersInjector.injectAllordersPresenter(failureoftheorderFragment, getAllordersPresenter());
            return failureoftheorderFragment;
        }
    }
}