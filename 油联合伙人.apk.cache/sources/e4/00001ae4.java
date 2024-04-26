package com.yltx.oil.partner.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yltx.oil.partner.base.RxBus;
import com.yltx.oil.partner.data.response.WeChatPayResultEvent;
import com.yltx.oil.partner.modules.mine.activity.RechargeActivity;
import com.yltx.oil.partner.utils.ToastUtil;

/* loaded from: classes.dex */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;
    String appId;
    String nonceStr;
    String orderId;
    String orderType;
    String packageValue = "Sign=WXPay";
    String partnerId;
    PayReq payReq;
    String prepayId;
    String sign;
    String timeStamp;

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
    }

    public static Intent getCallingIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, WXPayEntryActivity.class);
        intent.putExtra("bundle", bundle);
        return intent;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle bundleExtra = getIntent().getBundleExtra("bundle");
        this.orderId = bundleExtra.getString("orderId");
        this.appId = bundleExtra.getString("appId");
        this.partnerId = bundleExtra.getString("partnerId");
        this.prepayId = bundleExtra.getString("prepayId");
        this.nonceStr = bundleExtra.getString("nonceStr");
        this.timeStamp = bundleExtra.getString("timeStamp");
        this.sign = bundleExtra.getString("sign");
        this.orderType = bundleExtra.getString("orderType");
        if (!TextUtils.isEmpty(this.appId)) {
            this.api = WXAPIFactory.createWXAPI(this, this.appId);
            if (isWXAppInstalledAndSupported(this.appId)) {
                this.payReq = wxpay();
            } else {
                ToastUtil.showMiddleScreenToast(this, "请安装微信APP");
                finish();
            }
        }
        if (this.api != null) {
            this.api.handleIntent(getIntent(), this);
            if (this.payReq != null) {
                this.api.sendReq(this.payReq);
            }
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.api.handleIntent(intent, this);
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == 5) {
            char c = 65535;
            if (baseResp.errCode == 0) {
                ToastUtil.showMiddleScreenToast(this, "微信支付成功");
                WeChatPayResultEvent weChatPayResultEvent = new WeChatPayResultEvent();
                weChatPayResultEvent.setWhat(WeChatPayResultEvent.wechat_success_pay);
                RxBus.getDefault().post(weChatPayResultEvent);
                setResult(2003, new Intent());
                String str = this.orderType;
                if (str.hashCode() == 1570 && str.equals(RechargeActivity.ORDER_RECHARGE_OIL)) {
                    c = 0;
                }
                if (c == 0) {
                    RechargeActivity.RechargeActivity.finish();
                }
                finish();
                return;
            } else if (baseResp.errCode == -2) {
                ToastUtil.showMiddleScreenToast(this, "微信支付已取消");
                finish();
                return;
            } else if (baseResp.errCode == -1) {
                ToastUtil.showMiddleScreenToast(this, "微信支付失败");
                finish();
                return;
            } else {
                return;
            }
        }
        finish();
    }

    public PayReq wxpay() {
        PayReq payReq = new PayReq();
        payReq.appId = this.appId;
        payReq.partnerId = this.partnerId;
        payReq.prepayId = this.prepayId;
        payReq.packageValue = this.packageValue;
        payReq.nonceStr = this.nonceStr;
        payReq.timeStamp = this.timeStamp;
        payReq.sign = this.sign;
        this.api.registerApp(this.appId);
        return payReq;
    }

    private boolean isWXAppInstalledAndSupported(String str) {
        if (str != null) {
            this.api.registerApp(str);
        }
        return this.api.isWXAppInstalled() && this.api.isWXAppSupportAPI();
    }
}