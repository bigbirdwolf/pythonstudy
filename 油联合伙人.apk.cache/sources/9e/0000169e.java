package com.yltx.oil.partner.injections.modules;

import com.yltx.oil.partner.injections.components.ActivityScope;
import com.yltx.oil.partner.modules.SplashActivity;
import com.yltx.oil.partner.modules.home.activity.ApplyingPartnerActivity;
import com.yltx.oil.partner.modules.home.activity.ClassificationActivity;
import com.yltx.oil.partner.modules.home.activity.CommoditySharingInforActivity;
import com.yltx.oil.partner.modules.home.activity.MessageBulletinActivity;
import com.yltx.oil.partner.modules.home.activity.MessageDetailsActivity;
import com.yltx.oil.partner.modules.home.activity.MessageHomeActivity;
import com.yltx.oil.partner.modules.home.activity.SearchHomeActivity;
import com.yltx.oil.partner.modules.home.activity.SearchResultActivity;
import com.yltx.oil.partner.modules.home.activity.ShareDetailsActivity;
import com.yltx.oil.partner.modules.home.fragment.FragmentHome;
import com.yltx.oil.partner.modules.login.activity.ForgetPasswordActivity;
import com.yltx.oil.partner.modules.login.activity.LoginActivity;
import com.yltx.oil.partner.modules.login.activity.NonoilLoginActivity;
import com.yltx.oil.partner.modules.login.activity.RegisterActivity;
import com.yltx.oil.partner.modules.login.activity.YLSPLoginActivity;
import com.yltx.oil.partner.modules.login.activity.YLTXLoginActivity;
import com.yltx.oil.partner.modules.login.fragment.FragmentPwdLogin;
import com.yltx.oil.partner.modules.main.MainActivity;
import com.yltx.oil.partner.modules.mine.activity.ClipImageActivity;
import com.yltx.oil.partner.modules.mine.activity.ComplainValetActivity;
import com.yltx.oil.partner.modules.mine.activity.ComplaintActivity;
import com.yltx.oil.partner.modules.mine.activity.FeedbackActivity;
import com.yltx.oil.partner.modules.mine.activity.HelpCenterActivity;
import com.yltx.oil.partner.modules.mine.activity.InvitationDetailsActivity;
import com.yltx.oil.partner.modules.mine.activity.InviteCourtesyActivity;
import com.yltx.oil.partner.modules.mine.activity.MineInfoActivity;
import com.yltx.oil.partner.modules.mine.activity.ModifyNicknameActivity;
import com.yltx.oil.partner.modules.mine.activity.NoviceGuideActivity;
import com.yltx.oil.partner.modules.mine.activity.PhoneActivity;
import com.yltx.oil.partner.modules.mine.activity.RechargeActivity;
import com.yltx.oil.partner.modules.mine.activity.UpdatePwdActivity;
import com.yltx.oil.partner.modules.mine.fragment.FragmentMine;
import com.yltx.oil.partner.modules.oiltrade.fragment.FragmentOilTrade;
import com.yltx.oil.partner.modules.oiltrade.fragment.GiftCardFragment;
import com.yltx.oil.partner.modules.oiltrade.fragment.OilCardFragment;
import com.yltx.oil.partner.modules.oiltrade.fragment.RefuelingCardFragment;
import com.yltx.oil.partner.modules.oiltrade.fragment.ShopFragment;
import com.yltx.oil.partner.modules.oiltrade.fragment.StoredValueCardFragment;
import com.yltx.oil.partner.modules.profit.activity.AccountdetailsActivity;
import com.yltx.oil.partner.modules.profit.activity.BindingbankcardsActivity;
import com.yltx.oil.partner.modules.profit.activity.CollectionRecordActivity;
import com.yltx.oil.partner.modules.profit.activity.DataanalysisActivity;
import com.yltx.oil.partner.modules.profit.activity.ModificationBankCardsActivity;
import com.yltx.oil.partner.modules.profit.activity.OptiondateActivity;
import com.yltx.oil.partner.modules.profit.activity.OrderdetailsActivity;
import com.yltx.oil.partner.modules.profit.activity.SettlementrecordsActivity;
import com.yltx.oil.partner.modules.profit.activity.TxHistoryActivity;
import com.yltx.oil.partner.modules.profit.activity.WithdrawActivity;
import com.yltx.oil.partner.modules.profit.fragment.AllFragment;
import com.yltx.oil.partner.modules.profit.fragment.AllordersFragment;
import com.yltx.oil.partner.modules.profit.fragment.EffectiveorderFragment;
import com.yltx.oil.partner.modules.profit.fragment.FailureoftheorderFragment;
import com.yltx.oil.partner.modules.profit.fragment.FragmentProfit;
import com.yltx.oil.partner.modules.profit.fragment.RefundFragment;
import com.yltx.oil.partner.modules.web.JsBridgeWebActivity;
import com.yltx.oil.partner.modules.web.WebActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
/* loaded from: classes.dex */
public abstract class BuildersModule {
    @ContributesAndroidInjector
    @ActivityScope
    abstract JsBridgeWebActivity InviteFriendsActivityInjector();

    @ContributesAndroidInjector
    @ActivityScope
    abstract MessageDetailsActivity MessageDetailsActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract NonoilLoginActivity NonoilLoginActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract AccountdetailsActivity accountdetailsActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract AllFragment allFragment();

    @ContributesAndroidInjector
    @ActivityScope
    abstract AllordersFragment allordersFragment();

    @ContributesAndroidInjector
    @ActivityScope
    abstract ApplyingPartnerActivity applyingPartnerActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract BindingbankcardsActivity bindingbankcardsActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract ClassificationActivity classificationActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract ClipImageActivity clipImageActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract CollectionRecordActivity collectionRecordActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract CommoditySharingInforActivity commoditySharingInforActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract ComplainValetActivity complainValetActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract ComplaintActivity complaintActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract DataanalysisActivity dataanalysisActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract EffectiveorderFragment effectiveorderFragment();

    @ContributesAndroidInjector
    @ActivityScope
    abstract FailureoftheorderFragment failureoftheorderFragment();

    @ContributesAndroidInjector
    @ActivityScope
    abstract FeedbackActivity feedbackActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract ForgetPasswordActivity forgetPasswordActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract FragmentHome fragmentHome();

    @ContributesAndroidInjector
    @ActivityScope
    abstract FragmentMine fragmentMine();

    @ContributesAndroidInjector
    @ActivityScope
    abstract FragmentOilTrade fragmentOilTrade();

    @ContributesAndroidInjector
    @ActivityScope
    abstract FragmentProfit fragmentProfit();

    @ContributesAndroidInjector
    @ActivityScope
    abstract FragmentPwdLogin fragmentPwdLogin();

    @ContributesAndroidInjector
    @ActivityScope
    abstract GiftCardFragment giftcardFragment();

    @ContributesAndroidInjector
    @ActivityScope
    abstract HelpCenterActivity helpCenterActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract InvitationDetailsActivity invitationDetailsActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract InviteCourtesyActivity inviteCourtesyActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract LoginActivity loginActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract MessageBulletinActivity messageBulletinActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract MessageHomeActivity messageHomeActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract MineInfoActivity mineInfoActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract ModificationBankCardsActivity modificationBankCardsActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract ModifyNicknameActivity modifyNicknameActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract NoviceGuideActivity noviceGuideActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract OilCardFragment oilCardFragment();

    @ContributesAndroidInjector
    @ActivityScope
    abstract OptiondateActivity optiondateActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract OrderdetailsActivity orderdetailsActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract PhoneActivity phoneActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract RechargeActivity rechargeActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract RefuelingCardFragment refuelingCardFragment();

    @ContributesAndroidInjector
    @ActivityScope
    abstract RefundFragment refundFragment();

    @ContributesAndroidInjector
    @ActivityScope
    abstract RegisterActivity registerActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract SearchHomeActivity searchHomeActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract SearchResultActivity searchResultActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract SettlementrecordsActivity settlementrecordsActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract ShareDetailsActivity shareDetailsActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract ShopFragment shopFragment();

    @ContributesAndroidInjector
    @ActivityScope
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract StoredValueCardFragment storedValueCardFragment();

    @ContributesAndroidInjector
    @ActivityScope
    abstract TxHistoryActivity txHistoryActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract UpdatePwdActivity updatePwdActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract WebActivity webActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract WithdrawActivity withdrawActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract YLSPLoginActivity ylspLoginActivity();

    @ContributesAndroidInjector
    @ActivityScope
    abstract YLTXLoginActivity yltxLoginActivity();
}