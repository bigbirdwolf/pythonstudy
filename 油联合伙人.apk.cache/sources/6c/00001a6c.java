package com.yltx.oil.partner.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.yltx.oil.partner.modules.login.activity.ForgetPasswordActivity;
import com.yltx.oil.partner.modules.login.activity.LoginActivity;
import com.yltx.oil.partner.modules.login.activity.RegisterActivity;
import com.yltx.oil.partner.modules.login.activity.YLSPLoginActivity;
import com.yltx.oil.partner.modules.main.MainActivity;
import com.yltx.oil.partner.modules.mine.activity.ClipImageActivity;
import com.yltx.oil.partner.modules.mine.activity.ComplainValetActivity;
import com.yltx.oil.partner.modules.mine.activity.FeedbackActivity;
import com.yltx.oil.partner.modules.mine.activity.InvitationDetailsActivity;
import com.yltx.oil.partner.modules.mine.activity.InviteCourtesyActivity;
import com.yltx.oil.partner.modules.mine.activity.MineInfoActivity;
import com.yltx.oil.partner.modules.mine.activity.ModifyNicknameActivity;
import com.yltx.oil.partner.modules.mine.activity.PhoneActivity;
import com.yltx.oil.partner.modules.mine.activity.RechargeActivity;
import com.yltx.oil.partner.modules.mine.activity.UpdatePwdActivity;
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
import com.yltx.oil.partner.modules.web.JsBridgeWebActivity;
import com.yltx.oil.partner.wxapi.WXPayEntryActivity;
import javax.inject.Singleton;

@Singleton
/* loaded from: classes.dex */
public class Navigator {
    public void navigateToSplash(Context context) {
        if (context != null) {
            context.startActivity(SplashActivity.getCallingIntent(context));
        }
    }

    public void navigateToMain(Context context, int i) {
        if (context != null) {
            context.startActivity(MainActivity.getCallingIntent(context, i));
        }
    }

    public void navigateToLogin(Context context) {
        if (context != null) {
            context.startActivity(LoginActivity.getCallingIntent(context));
        }
    }

    public void navigateToInfo(Context context, int i, String str) {
        if (context != null) {
            context.startActivity(CommoditySharingInforActivity.getCallingIntent(context, i, str));
        }
    }

    public void navigateToApplyingPartner(Context context) {
        if (context != null) {
            context.startActivity(ApplyingPartnerActivity.getCallingIntent(context));
        }
    }

    public void navigateToRegister(Context context) {
        if (context != null) {
            context.startActivity(RegisterActivity.getCallingIntent(context));
        }
    }

    public void navigateToForgetPassword(Context context) {
        if (context != null) {
            context.startActivity(ForgetPasswordActivity.getCallingIntent(context));
        }
    }

    public void navigateToYLSPLogin(Context context) {
        if (context != null) {
            context.startActivity(YLSPLoginActivity.getCallingIntent(context));
        }
    }

    public void navigateToMineInfo(Context context, String str) {
        if (context != null) {
            context.startActivity(MineInfoActivity.getCallingIntent(context, str));
        }
    }

    public void navigateToFeedback(Context context) {
        if (context != null) {
            context.startActivity(FeedbackActivity.getCallingIntent(context));
        }
    }

    public void navigateToComplainValet(Context context, String str) {
        if (context != null) {
            context.startActivity(ComplainValetActivity.getCallingIntent(context, str));
        }
    }

    public void navigateToClipImage(Context context, int i, Uri uri) {
        if (context != null) {
            Intent callingIntent = ClipImageActivity.getCallingIntent(context);
            callingIntent.putExtra(ClipImageActivity.EXTRA_IMAGE_URI, uri);
            ((Activity) context).startActivityForResult(callingIntent, i);
        }
    }

    public void navigateToModifyNickname(Context context) {
        if (context != null) {
            context.startActivity(ModifyNicknameActivity.getCallingIntent(context));
        }
    }

    public void navigateToInvitationDetails(Context context) {
        if (context != null) {
            context.startActivity(InvitationDetailsActivity.getCallingIntent(context));
        }
    }

    public void navigateToClassification(Context context) {
        if (context != null) {
            context.startActivity(ClassificationActivity.getCallingIntent(context));
        }
    }

    public void navigateToSearchHome(Context context) {
        if (context != null) {
            context.startActivity(SearchHomeActivity.getCallingIntent(context));
        }
    }

    public void navigateToShareDetails(Context context, Bundle bundle) {
        if (context != null) {
            context.startActivity(ShareDetailsActivity.getCallingIntent(context, bundle));
        }
    }

    public void navigateToMessageHome(Context context) {
        if (context != null) {
            context.startActivity(MessageHomeActivity.getCallingIntent(context));
        }
    }

    public void navigateToMessageBulletin(Context context) {
        if (context != null) {
            context.startActivity(MessageBulletinActivity.getCallingIntent(context));
        }
    }

    public void navigateToMessageDetails(Context context, String str) {
        if (context != null) {
            context.startActivity(MessageDetailsActivity.getCallingIntent(context, str));
        }
    }

    public void navigateToSearchResult(Context context, String str) {
        if (context != null) {
            context.startActivity(SearchResultActivity.getCallingIntent(context, str));
        }
    }

    public void navigateToPhone(Context context, String str) {
        if (context != null) {
            context.startActivity(PhoneActivity.getCallingIntent(context, str));
        }
    }

    public void navigateToUpdatePwd(Context context) {
        if (context != null) {
            context.startActivity(UpdatePwdActivity.getCallingIntent(context));
        }
    }

    public void navigateToInviteCourtesy(Context context) {
        if (context != null) {
            context.startActivity(InviteCourtesyActivity.getCallingIntent(context));
        }
    }

    public void navigateToBindingbankcards(Context context, int i, String str, String str2) {
        if (context != null) {
            context.startActivity(BindingbankcardsActivity.getCallingIntent(context, i, str, str2));
        }
    }

    public void navigateToModificationBankCards(Context context) {
        if (context != null) {
            context.startActivity(ModificationBankCardsActivity.getCallingIntent(context));
        }
    }

    public void navigateToTxHistoryActivity(Context context) {
        if (context != null) {
            context.startActivity(TxHistoryActivity.getCallingIntent(context));
        }
    }

    public void orderdetailsActivity(Context context) {
        if (context != null) {
            context.startActivity(OrderdetailsActivity.getCallingIntent(context));
        }
    }

    public void dataanalysisActivity(Context context) {
        if (context != null) {
            context.startActivity(DataanalysisActivity.getCallingIntent(context));
        }
    }

    public void collectionrecordActivity(Context context) {
        if (context != null) {
            context.startActivity(CollectionRecordActivity.getCallingIntent(context));
        }
    }

    public void settlementrecordsActivity(Context context) {
        if (context != null) {
            context.startActivity(SettlementrecordsActivity.getCallingIntent(context));
        }
    }

    public void navigateToWithdraw(Context context, String str) {
        if (context != null) {
            context.startActivity(WithdrawActivity.getCallingIntent(context, str));
        }
    }

    public void navigateToAccountdetails(Context context) {
        if (context != null) {
            context.startActivity(AccountdetailsActivity.getCallingIntent(context));
        }
    }

    public void optiondateActivity(Context context) {
        if (context != null) {
            context.startActivity(OptiondateActivity.getCallingIntent(context));
        }
    }

    public void navigateToJsBridgeWebActivity(Context context, String str, String str2) {
        if (context != null) {
            Log.v("http====", str2);
            context.startActivity(JsBridgeWebActivity.getCallingIntent(context, str, str2));
        }
    }

    public void navigateToRechargeActivity(Context context) {
        if (context != null) {
            context.startActivity(RechargeActivity.getCallingIntent(context));
        }
    }

    public void navigateToWechatPay(Context context, Bundle bundle) {
        if (context != null) {
            context.startActivity(WXPayEntryActivity.getCallingIntent(context, bundle));
        }
    }
}