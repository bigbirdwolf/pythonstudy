package com.yltx.oil.partner.modules.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.network.UserToken;
import com.yltx.oil.partner.data.response.FinanceCardlResp;
import com.yltx.oil.partner.data.response.GiftCardResp;
import com.yltx.oil.partner.data.response.ShopDetailsResp;
import com.yltx.oil.partner.data.response.StoredValueCardResp;
import com.yltx.oil.partner.modules.oiltrade.presenter.FinanceCardetailPresenter;
import com.yltx.oil.partner.modules.oiltrade.response.FuelCardDetailsResponse;
import com.yltx.oil.partner.modules.oiltrade.view.FinanCarddetailView;
import com.yltx.oil.partner.navigation.Navigator;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import com.yltx.oil.partner.utils.DataCache;
import java.math.BigDecimal;
import javax.inject.Inject;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class CommoditySharingInforActivity extends ToolBarActivity implements FinanCarddetailView {
    public static final int CZK = 2;
    public static final int JYK = 0;
    public static final int LPK = 4;
    public static final int SPL = 3;
    public static final int YPMY = 1;
    @Inject
    FinanceCardetailPresenter financeCardetailPresenter;
    @BindView(R.id.iv_ico)
    ImageView ivIco;
    private String rowid;
    @BindView(R.id.tv_buycount)
    TextView tvBuycount;
    @BindView(R.id.tv_czksy)
    TextView tvCzksy;
    @BindView(R.id.tv_fxxq)
    TextView tvFxxq;
    @BindView(R.id.tv_fxz)
    TextView tvFxz;
    @BindView(R.id.tv_lirun)
    TextView tvLirun;
    @BindView(R.id.tv_lr)
    TextView tvLr;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_yjbl)
    TextView tvYjbl;
    @BindView(R.id.tv_yuanjia)
    TextView tvYuanjia;
    @BindView(R.id.tv_zhouqi)
    TextView tvZhouqi;
    @BindView(R.id.tv_hy)
    TextView tv_hy;
    private int type;
    private String userId;
    private FinanceCardlResp financeCardlResp = null;
    private StoredValueCardResp storedValueCardResp = null;
    private FuelCardDetailsResponse fuelCardDetailsResponse = null;
    private ShopDetailsResp shopResp = null;
    private GiftCardResp giftCardResp = null;

    @Override // com.yltx.oil.partner.modules.oiltrade.view.FinanCarddetailView
    public void onError(Throwable th) {
    }

    public static Intent getCallingIntent(Context context, int i, String str) {
        Intent intent = new Intent(context, CommoditySharingInforActivity.class);
        intent.putExtra("type", i);
        intent.putExtra("rowid", str);
        return intent;
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_commodity_sharing_infor);
        ButterKnife.bind(this);
        this.financeCardetailPresenter.attachView(this);
        this.type = getIntent().getIntExtra("type", 0);
        this.rowid = getIntent().getStringExtra("rowid");
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        setToolbarTitle("分享详情");
        switch (this.type) {
            case 0:
                this.tvTitle.setText("全国加油卡");
                this.ivIco.setImageResource(R.mipmap.qgjykxq);
                this.tvYuanjia.getPaint().setFlags(16);
                this.tvYuanjia.setText("原价：0.00");
                this.tvLirun.setText("折后价：");
                this.tvZhouqi.setVisibility(4);
                this.tvCzksy.setVisibility(8);
                this.financeCardetailPresenter.getFueldetail(this.rowid);
                return;
            case 1:
                this.tvTitle.setText("油品贸易");
                this.ivIco.setImageResource(R.mipmap.ypmyxq);
                this.tvYuanjia.setText("批发价：0.00");
                this.tvLirun.setText("利润：");
                this.tvZhouqi.setText("周期：365天");
                this.tvCzksy.setVisibility(8);
                this.financeCardetailPresenter.getFinanceCardetail(this.rowid);
                return;
            case 2:
                this.tvTitle.setText("储值卡");
                this.ivIco.setImageResource(R.mipmap.czkxq);
                this.tvYuanjia.setVisibility(4);
                this.tvLirun.setText("价格：");
                this.tvZhouqi.setVisibility(4);
                this.tvCzksy.setVisibility(0);
                this.financeCardetailPresenter.getStoredValueCardDetailUseCase(this.rowid);
                return;
            case 3:
                this.tvTitle.setText("商品");
                this.ivIco.setImageResource(R.mipmap.qgjykxq);
                this.tvYuanjia.getPaint().setFlags(16);
                this.tvYuanjia.setText("原价：0.00");
                this.tvLirun.setText("折后价：");
                this.tvZhouqi.setVisibility(4);
                this.tvCzksy.setVisibility(8);
                this.financeCardetailPresenter.getshopdetail(this.rowid);
                return;
            case 4:
                this.tvTitle.setText("礼品卡");
                this.ivIco.setImageResource(R.mipmap.qgjykxq);
                this.tvYuanjia.setText("原价：0.00");
                this.tvLirun.setText("折后价：");
                this.tvLr.setVisibility(4);
                this.tvLirun.setVisibility(4);
                this.tvZhouqi.setVisibility(4);
                this.tvCzksy.setVisibility(8);
                this.financeCardetailPresenter.getRechargeById(this.rowid);
                return;
            default:
                return;
        }
    }

    protected void bindListener() {
        Rx.click(this.tvShare, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$CommoditySharingInforActivity$ILgkuWG1S6W2kUj6cj_nap2P1lY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CommoditySharingInforActivity.lambda$bindListener$0(CommoditySharingInforActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvFxxq, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$CommoditySharingInforActivity$hlAaW5vG2kugU_MHHkaY7LvhsZw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CommoditySharingInforActivity.lambda$bindListener$1(CommoditySharingInforActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(CommoditySharingInforActivity commoditySharingInforActivity, Void r4) {
        String str;
        String rowId = PartnerApplication.getInstance().getUserInfos().getUserInfo().getRowId();
        switch (commoditySharingInforActivity.type) {
            case 0:
                str = "2";
                break;
            case 1:
                str = "1";
                break;
            case 2:
                str = "3";
                break;
            case 3:
                str = "0";
                break;
            case 4:
                str = "4";
                break;
            default:
                str = null;
                break;
        }
        if (commoditySharingInforActivity.type == 1) {
            if (commoditySharingInforActivity.financeCardlResp != null) {
                commoditySharingInforActivity.financeCardetailPresenter.getShareNum(rowId, String.valueOf(commoditySharingInforActivity.financeCardlResp.getfId()), str);
                return;
            }
            return;
        }
        commoditySharingInforActivity.financeCardetailPresenter.getShareNum(rowId, commoditySharingInforActivity.rowid, str);
    }

    public static /* synthetic */ void lambda$bindListener$1(CommoditySharingInforActivity commoditySharingInforActivity, Void r7) {
        switch (commoditySharingInforActivity.type) {
            case 0:
                if (commoditySharingInforActivity.fuelCardDetailsResponse != null) {
                    commoditySharingInforActivity.getNavigator().navigateToJsBridgeWebActivity(commoditySharingInforActivity.getContext(), "加油卡", Config.API_WeiXin_URL.concat("#/partner/fillingcard"));
                    return;
                }
                return;
            case 1:
                if (commoditySharingInforActivity.financeCardlResp != null) {
                    Navigator navigator = commoditySharingInforActivity.getNavigator();
                    Context context = commoditySharingInforActivity.getContext();
                    navigator.navigateToJsBridgeWebActivity(context, "油品贸易", Config.API_WeiXin_URL.concat("#/partner/financecardDetai?rowId=") + commoditySharingInforActivity.financeCardlResp.getfId());
                    return;
                }
                return;
            case 2:
                if (commoditySharingInforActivity.storedValueCardResp != null) {
                    String str = "stored";
                    String type = commoditySharingInforActivity.storedValueCardResp.getType();
                    char c = 65535;
                    switch (type.hashCode()) {
                        case 49:
                            if (type.equals("1")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 50:
                            if (type.equals("2")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 51:
                            if (type.equals("3")) {
                                c = 2;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            str = "stored";
                            break;
                        case 1:
                            str = "family";
                            break;
                        case 2:
                            str = "motorcade";
                            break;
                    }
                    Navigator navigator2 = commoditySharingInforActivity.getNavigator();
                    Context context2 = commoditySharingInforActivity.getContext();
                    navigator2.navigateToJsBridgeWebActivity(context2, "储值卡", Config.API_WeiXin_URL.concat("#/partner/cardInfo?name=") + commoditySharingInforActivity.storedValueCardResp.getCardName() + "&money=" + commoditySharingInforActivity.storedValueCardResp.getAmt() + "&type=" + str);
                    return;
                }
                return;
            case 3:
                if (commoditySharingInforActivity.shopResp != null) {
                    UserToken userToken = UserToken.getInstance();
                    if (TextUtils.isEmpty(userToken.getToken())) {
                        commoditySharingInforActivity.userId = DataCache.getUserid(commoditySharingInforActivity.getContext());
                    } else {
                        commoditySharingInforActivity.userId = TextUtils.isEmpty(userToken.getUserID()) ? "" : userToken.getUserID();
                    }
                    Navigator navigator3 = commoditySharingInforActivity.getNavigator();
                    Context context3 = commoditySharingInforActivity.getContext();
                    navigator3.navigateToJsBridgeWebActivity(context3, "商品", Config.API_SP_URL.concat("goodsDetail?prodId=") + commoditySharingInforActivity.shopResp.getGoodsShareDetail().getGoodsId() + "&userId=" + commoditySharingInforActivity.userId);
                    return;
                }
                return;
            case 4:
                if (commoditySharingInforActivity.giftCardResp != null) {
                    Navigator navigator4 = commoditySharingInforActivity.getNavigator();
                    Context context4 = commoditySharingInforActivity.getContext();
                    navigator4.navigateToJsBridgeWebActivity(context4, "礼品卡", Config.API_WeiXin_URL.concat("#/partner/rechargeCard?rowId=") + commoditySharingInforActivity.giftCardResp.getRowId());
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.yltx.oil.partner.modules.oiltrade.view.FinanCarddetailView
    public void onfuelDetails(HttpResult<FuelCardDetailsResponse> httpResult) {
        this.fuelCardDetailsResponse = httpResult.getData();
        TextView textView = this.tvTitle;
        textView.setText(httpResult.getData().getName() + "");
        TextView textView2 = this.tvYuanjia;
        textView2.setText("原价:" + httpResult.getData().getAmt() + "");
        TextView textView3 = this.tvLr;
        textView3.setText(httpResult.getData().getSpecialPrice() + "");
        TextView textView4 = this.tvFxz;
        textView4.setText(httpResult.getData().getCommission() + "元");
        TextView textView5 = this.tvBuycount;
        textView5.setText("已售" + httpResult.getData().getBuyCount() + "件");
        if (this.fuelCardDetailsResponse.getCommissionRate() != null) {
            BigDecimal scale = new BigDecimal(this.fuelCardDetailsResponse.getCommissionRate()).multiply(new BigDecimal(100)).setScale(2, 4);
            TextView textView6 = this.tvYjbl;
            textView6.setText(scale.toString() + "%");
        } else {
            this.tvYjbl.setText("0%");
        }
        AlbumDisplayUtils.displayHomeGoodsImg(this, this.ivIco, httpResult.getData().getPhoto());
    }

    @Override // com.yltx.oil.partner.modules.oiltrade.view.FinanCarddetailView
    public void onshopDetails(HttpResult<ShopDetailsResp> httpResult) {
        this.shopResp = httpResult.getData();
        this.tvTitle.setText(httpResult.getData().getGoodsShareDetail().getGoodsName());
        TextView textView = this.tvYuanjia;
        textView.setText("原价：" + httpResult.getData().getGoodsShareDetail().getMarketPrice() + "元");
        TextView textView2 = this.tvLr;
        textView2.setText(httpResult.getData().getGoodsShareDetail().getSalePrice() + "元");
        BigDecimal scale = new BigDecimal(httpResult.getData().getGoodsShareDetail().getCommission()).setScale(2, 4);
        TextView textView3 = this.tvFxz;
        textView3.setText(scale.toString() + "元");
        BigDecimal scale2 = new BigDecimal(httpResult.getData().getGoodsShareDetail().getCommissionRate()).multiply(new BigDecimal(100)).setScale(2, 4);
        TextView textView4 = this.tvYjbl;
        textView4.setText(scale2.toString() + "%");
        TextView textView5 = this.tvBuycount;
        textView5.setText("已售" + httpResult.getData().getGoodsShareDetail().getBuyCount() + "件");
        AlbumDisplayUtils.displayHomeGoodsImg(this, this.ivIco, httpResult.getData().getGoodsShareDetail().getGoodsImage().split(",")[0]);
        if (httpResult.getData().getShowStatus().equals("1")) {
            BigDecimal scale3 = new BigDecimal(httpResult.getData().getNextRebatePrice()).setScale(2, 4);
            this.tv_hy.setVisibility(0);
            TextView textView6 = this.tv_hy;
            textView6.setText("晋升到" + httpResult.getData().getUserNextLevel() + ",您能获得的佣金预计为" + scale3 + "元，加油喔！");
            return;
        }
        this.tv_hy.setVisibility(8);
    }

    @Override // com.yltx.oil.partner.modules.oiltrade.view.FinanCarddetailView
    public void onGiftCardDetails(HttpResult<GiftCardResp> httpResult) {
        this.giftCardResp = httpResult.getData();
        this.tvTitle.setText(httpResult.getData().getName());
        TextView textView = this.tvYuanjia;
        textView.setText("原价：" + httpResult.getData().getSaleprice() + "元");
        TextView textView2 = this.tvFxz;
        textView2.setText(httpResult.getData().getCommission() + "元");
        BigDecimal scale = new BigDecimal(httpResult.getData().getCommissionRate()).setScale(2, 4).multiply(new BigDecimal(100)).setScale(2, 4);
        TextView textView3 = this.tvYjbl;
        textView3.setText(scale.toString() + "%");
        TextView textView4 = this.tvBuycount;
        textView4.setText("已售" + httpResult.getData().getBuyCount() + "件");
        AlbumDisplayUtils.displayHomeGoodsImg(this, this.ivIco, httpResult.getData().getPhoto());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0278, code lost:
        if (r0.equals("2") != false) goto L16;
     */
    @Override // com.yltx.oil.partner.modules.oiltrade.view.FinanCarddetailView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onDetails(com.yltx.oil.partner.data.response.SfResp r11) {
        /*
            Method dump skipped, instructions count: 1212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yltx.oil.partner.modules.home.activity.CommoditySharingInforActivity.onDetails(com.yltx.oil.partner.data.response.SfResp):void");
    }

    @Override // com.yltx.oil.partner.modules.oiltrade.view.FinanCarddetailView
    public void onfinanceCarddetail(HttpResult<FinanceCardlResp> httpResult) {
        this.financeCardlResp = httpResult.getData();
        this.tvTitle.setText(httpResult.getData().getName());
        AlbumDisplayUtils.displayHomeGoodsImg(this, this.ivIco, httpResult.getData().getMainPhoto());
        TextView textView = this.tvYuanjia;
        textView.setText("批发价：" + httpResult.getData().getTonPrice());
        BigDecimal scale = new BigDecimal(Double.parseDouble(httpResult.getData().getShraeMoney())).setScale(2, 4);
        TextView textView2 = this.tvLr;
        textView2.setText(httpResult.getData().getSummary() + "元");
        TextView textView3 = this.tvZhouqi;
        textView3.setText("周期：" + httpResult.getData().getDays() + "天");
        TextView textView4 = this.tvFxz;
        textView4.setText(scale.toString() + "元");
        BigDecimal scale2 = new BigDecimal(httpResult.getData().getCommissionRate()).multiply(new BigDecimal(100)).setScale(2, 4);
        TextView textView5 = this.tvYjbl;
        textView5.setText(scale2.toString() + "%");
        TextView textView6 = this.tvBuycount;
        textView6.setText("已售" + httpResult.getData().getBuyCount() + "件");
    }

    @Override // com.yltx.oil.partner.modules.oiltrade.view.FinanCarddetailView
    public void onStoredValueDetail(HttpResult<StoredValueCardResp> httpResult) {
        this.storedValueCardResp = httpResult.getData();
        this.tvTitle.setText(httpResult.getData().getCardName());
        AlbumDisplayUtils.displayHomeGoodsImg(this, this.ivIco, httpResult.getData().getPhoto());
        BigDecimal scale = new BigDecimal(httpResult.getData().getAmt()).setScale(2, 4);
        TextView textView = this.tvLr;
        textView.setText(scale + "元");
        BigDecimal scale2 = new BigDecimal(httpResult.getData().getCommission()).setScale(2, 4);
        TextView textView2 = this.tvFxz;
        textView2.setText(scale2.toString() + "元");
        BigDecimal scale3 = new BigDecimal(httpResult.getData().getCommissionRate()).multiply(new BigDecimal(100)).setScale(2, 4);
        TextView textView3 = this.tvYjbl;
        textView3.setText(scale3.toString() + "%");
        this.tvCzksy.setText(httpResult.getData().getUseRule());
        TextView textView4 = this.tvBuycount;
        textView4.setText("已售" + httpResult.getData().getBuyCount() + "件");
    }
}