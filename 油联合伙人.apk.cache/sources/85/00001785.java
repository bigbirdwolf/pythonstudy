package com.yltx.oil.partner.modules.home.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.stetho.common.LogUtil;
import com.google.zxing.WriterException;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.permission.CheckPermission;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.EncodeUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.widget.CommonDialog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Calendar;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class ShareDetailsActivity extends ToolBarActivity {
    static final int SHAREPYQ = 1;
    static final int SHAREWX = 0;
    Bitmap bitmap;
    @BindView(R.id.btn_copy)
    Button btnCopy;
    private double commission;
    private String commissionRate;
    @BindView(R.id.fxz)
    TextView fxz;
    @BindView(R.id.im_cesi)
    ImageView imCesi;
    @BindView(R.id.iv_encode)
    ImageView ivEncode;
    @BindView(R.id.iv_ico)
    ImageView ivIco;
    @BindView(R.id.ll_fx_view)
    LinearLayout llFxView;
    @BindView(R.id.ll_fximg_view)
    LinearLayout llFximgView;
    private String name;
    private String photo;
    double pkgDiscount;
    private String price;
    @BindView(R.id.tv_dazhe)
    TextView tvDazhe;
    @BindView(R.id.tv_fx_wx)
    TextView tvFxWx;
    @BindView(R.id.tv_fx_wx_pyq)
    TextView tvFxWxPyq;
    @BindView(R.id.tv_fxname)
    TextView tvFxname;
    @BindView(R.id.tv_guize)
    TextView tvGuize;
    @BindView(R.id.tv_share_con)
    TextView tvShareCon;
    @BindView(R.id.tv_share_img)
    TextView tvShareImg;
    @BindView(R.id.tv_share_text)
    TextView tvShareText;
    @BindView(R.id.tv_yuanjia)
    TextView tvYuanjia;
    private int type;
    private String atThePrice = "\n\n【在售价】 %s 元\n\n";
    private String current = "【现价】 %s 元\n\n";
    private String orderLink = "【下单链接】 %s \n\n";
    private String description = "--------------------------------\n\n复制这条信息到【微信】即可购买\n\n";
    String ypmy = "http://wx.chinayltx.com/#/financecardOrderNew?rowId=%1$s&count=%2$d&recommenderId=%3$s&psgId=%4$d";
    String czk = "http://wx.chinayltx.com/#/rechargeValueCardPartner?rowId=%1$s&flag=%2$d&type=%3$s&recommenderId=%4$s&money=%5$.2f&cardLisId=%6$d&psgId=%7$d";
    String jyk = "http://wx.chinayltx.com/#/pay/filling?cardId=%1$d&cardAmt=%2$.2f&pkgId=%3$d&pkgDiscount=%4$.2f&pkgName=%5$s&pkgNum=%6$d&preferentialAmt=%7$.2f&recommenderId=%8$s&psgId=%9$d";
    String sp = "http://weixin.ylsp188.com/?wechat#/qualitygoods/goodsinfo?prodId=%1$d&recommenderId=%2$s&parternerId=%3$s";
    String lpk = "http://wx.chinayltx.com/#/pay/rechargecardorder?rowId=%1$d&count=1&current=%2$s&recommenderId=%3$s&psgId=%4$d";
    boolean isImg = false;
    private UMShareListener umShareListener = new UMShareListener() { // from class: com.yltx.oil.partner.modules.home.activity.ShareDetailsActivity.2
        @Override // com.umeng.socialize.UMShareListener
        public void onCancel(SHARE_MEDIA share_media) {
        }

        @Override // com.umeng.socialize.UMShareListener
        public void onStart(SHARE_MEDIA share_media) {
        }

        {
            ShareDetailsActivity.this = this;
        }

        @Override // com.umeng.socialize.UMShareListener
        public void onResult(SHARE_MEDIA share_media) {
            ToastUtil.showMiddleScreenToast("分享成功");
        }

        @Override // com.umeng.socialize.UMShareListener
        public void onError(SHARE_MEDIA share_media, Throwable th) {
            ToastUtil.showMiddleScreenToast("分享失败");
        }
    };

    public static Intent getCallingIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ShareDetailsActivity.class);
        intent.putExtra("bundle", bundle);
        return intent;
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_share_details);
        ButterKnife.bind(this);
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        String str;
        String format;
        setToolbarTitle("分享");
        Bundle bundleExtra = getIntent().getBundleExtra("bundle");
        this.name = bundleExtra.getString("name");
        this.photo = bundleExtra.getString("photo");
        this.price = bundleExtra.getString("price");
        this.type = bundleExtra.getInt("shareType");
        this.commission = bundleExtra.getDouble("commission");
        this.commissionRate = bundleExtra.getString("commissionRate");
        if (this.type == 3) {
            AlbumDisplayUtils.displayHomeGoodsImg(this, this.ivIco, this.photo);
        } else {
            AlbumDisplayUtils.displayHomeGoodsImg(this, this.ivIco, this.photo);
        }
        BigDecimal scale = new BigDecimal(this.commission).setScale(2, 4);
        TextView textView = this.fxz;
        textView.setText("您的佣金预计为" + this.commissionRate + "%（预计¥" + scale.toString() + "）");
        this.tvFxname.setText(this.name);
        TextView textView2 = this.tvYuanjia;
        StringBuilder sb = new StringBuilder();
        sb.append("现价 ¥");
        sb.append(this.price);
        textView2.setText(sb.toString());
        switch (this.type) {
            case 0:
                int i = bundleExtra.getInt("cardId");
                double d = bundleExtra.getDouble("cardAmt");
                int i2 = bundleExtra.getInt("pkgId");
                int i3 = bundleExtra.getInt("psgId");
                this.pkgDiscount = bundleExtra.getDouble("pkgDiscount");
                String string = bundleExtra.getString("pkgName");
                int i4 = bundleExtra.getInt("pkgNum");
                double d2 = bundleExtra.getDouble("preferentialAmt");
                String string2 = bundleExtra.getString("recommenderId");
                try {
                    str = URLEncoder.encode(string, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    str = string;
                }
                TextView textView3 = this.tvYuanjia;
                textView3.setText("现价 ¥" + this.pkgDiscount);
                format = String.format(this.jyk, Integer.valueOf(i2), Double.valueOf(d), Integer.valueOf(i), Double.valueOf(this.pkgDiscount), str, Integer.valueOf(i4), Double.valueOf(d2), string2, Integer.valueOf(i3));
                break;
            case 1:
                this.atThePrice = "\n\n【价格】 %s 元\n\n";
                this.current = "【利润】 %s 元\n\n";
                int i5 = bundleExtra.getInt("rowId");
                int i6 = bundleExtra.getInt("count");
                int i7 = bundleExtra.getInt("psgId");
                this.pkgDiscount = bundleExtra.getDouble("pkgDiscount");
                format = String.format(this.ypmy, Integer.valueOf(i5), Integer.valueOf(i6), bundleExtra.getString("recommenderId"), Integer.valueOf(i7));
                break;
            case 2:
                int i8 = bundleExtra.getInt("rowId");
                int i9 = bundleExtra.getInt("cardLisId");
                String string3 = bundleExtra.getString("type");
                int i10 = bundleExtra.getInt("flag");
                this.pkgDiscount = bundleExtra.getDouble("pkgDiscount");
                double d3 = bundleExtra.getDouble("money");
                format = String.format(this.czk, Integer.valueOf(i8), Integer.valueOf(i10), string3, bundleExtra.getString("recommenderId"), Double.valueOf(d3), Integer.valueOf(i9), Integer.valueOf(bundleExtra.getInt("psgId")));
                break;
            case 3:
                int i11 = bundleExtra.getInt("goodsshopId");
                String string4 = bundleExtra.getString("recommenderId");
                String string5 = bundleExtra.getString("parternerId");
                this.pkgDiscount = bundleExtra.getDouble("pkgDiscount");
                BigDecimal scale2 = new BigDecimal(bundleExtra.getString("predictRebatePrice")).setScale(2, 4);
                BigDecimal scale3 = new BigDecimal(bundleExtra.getDouble("predictExtraPrice")).setScale(2, 4);
                BigDecimal scale4 = new BigDecimal(this.commission).setScale(2, 4);
                TextView textView4 = this.fxz;
                textView4.setText("您的佣金预计为" + scale2.toString() + "元（佣金收入¥" + scale4 + "+等级奖励¥" + scale3.toString() + "）");
                bundleExtra.getInt("psgId");
                format = String.format(this.sp, Integer.valueOf(i11), string4, string5);
                TextView textView5 = this.tvYuanjia;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("现价 ¥");
                sb2.append(this.pkgDiscount);
                textView5.setText(sb2.toString());
                break;
            case 4:
                int i12 = bundleExtra.getInt("rowId");
                String string6 = bundleExtra.getString("price");
                int i13 = bundleExtra.getInt("psgId");
                this.pkgDiscount = bundleExtra.getDouble("pkgDiscount");
                format = String.format(this.lpk, Integer.valueOf(i12), string6, bundleExtra.getString("recommenderId"), Integer.valueOf(i13));
                break;
            default:
                format = "请联系客服";
                break;
        }
        if (this.type == 4) {
            TextView textView6 = this.tvShareCon;
            textView6.setText(this.name + "\n\n" + String.format(this.current, String.valueOf(this.pkgDiscount)) + String.format(this.orderLink, format) + this.description);
        } else {
            TextView textView7 = this.tvShareCon;
            textView7.setText(this.name + String.format(this.atThePrice, this.price) + String.format(this.current, String.valueOf(this.pkgDiscount)) + String.format(this.orderLink, format) + this.description);
        }
        try {
            this.ivEncode.setImageBitmap(EncodeUtils.createCode(getContext(), format));
        } catch (WriterException e2) {
            e2.printStackTrace();
            Log.d(">>>>", "生成二维码出错");
        }
        this.tvShareText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        this.tvShareText.setBackground(getResources().getDrawable(R.drawable.lightyellow_left));
        this.tvShareImg.setTextColor(getResources().getColor(R.color.white));
        this.tvShareImg.setBackground(getResources().getDrawable(R.drawable.light_yellow_right));
        this.llFximgView.setVisibility(0);
        this.llFxView.setVisibility(8);
        this.isImg = true;
        this.btnCopy.setText("保存图片");
    }

    protected void bindListener() {
        Rx.click(this.btnCopy, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$ShareDetailsActivity$hLynXCoZADcpS69LmBl5dBiC8II
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ShareDetailsActivity.lambda$bindListener$2(ShareDetailsActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvShareText, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$ShareDetailsActivity$SBMPlaCnpOp0gRaHuGs-GyVZRy8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ShareDetailsActivity.lambda$bindListener$3(ShareDetailsActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvShareImg, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$ShareDetailsActivity$8RHNrYlNlw0LJFptu9yvFPGT2J0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ShareDetailsActivity.lambda$bindListener$4(ShareDetailsActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvFxWx, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$ShareDetailsActivity$6adK0B0i6jhA832XURukzdxy88Q
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ShareDetailsActivity.lambda$bindListener$5(ShareDetailsActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvFxWxPyq, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$ShareDetailsActivity$txNLfP6tvD5I8JKe_yzOu102o6I
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ShareDetailsActivity.lambda$bindListener$6(ShareDetailsActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvGuize, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$ShareDetailsActivity$8b4KZd4Pf8zDQOd4MaKs1raw3OQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ShareDetailsActivity.lambda$bindListener$7(ShareDetailsActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$2(ShareDetailsActivity shareDetailsActivity, Void r5) {
        if (!shareDetailsActivity.isImg) {
            ((ClipboardManager) shareDetailsActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Label", shareDetailsActivity.tvShareCon.getText().toString()));
            ToastUtil.showMiddleScreenToast("已复制");
            return;
        }
        CheckPermission.check(shareDetailsActivity, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$ShareDetailsActivity$iOgjROQJX6CC5pLppc7GQMNqq7Y
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ShareDetailsActivity.lambda$null$0(ShareDetailsActivity.this, (String) obj);
            }
        }, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$ShareDetailsActivity$67IJCw5qZb_J_kfmYWBGzsc-yXU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ShareDetailsActivity.lambda$null$1((String) obj);
            }
        }, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public static /* synthetic */ void lambda$null$0(ShareDetailsActivity shareDetailsActivity, String str) {
        shareDetailsActivity.bitmap = shareDetailsActivity.viewSaveToImage(shareDetailsActivity.llFximgView);
    }

    public static /* synthetic */ void lambda$null$1(String str) {
        ToastUtil.showMiddleScreenToast("应用未授权相机权限");
    }

    public static /* synthetic */ void lambda$bindListener$3(ShareDetailsActivity shareDetailsActivity, Void r3) {
        shareDetailsActivity.tvShareText.setTextColor(shareDetailsActivity.getResources().getColor(R.color.white));
        shareDetailsActivity.tvShareText.setBackground(shareDetailsActivity.getResources().getDrawable(R.drawable.light_yellow_left));
        shareDetailsActivity.tvShareImg.setTextColor(shareDetailsActivity.getResources().getColor(R.color.colorPrimaryDark));
        shareDetailsActivity.tvShareImg.setBackground(shareDetailsActivity.getResources().getDrawable(R.drawable.lightyellow_right));
        shareDetailsActivity.llFximgView.setVisibility(8);
        shareDetailsActivity.llFxView.setVisibility(0);
        shareDetailsActivity.isImg = false;
        shareDetailsActivity.btnCopy.setText("复制文案");
    }

    public static /* synthetic */ void lambda$bindListener$4(ShareDetailsActivity shareDetailsActivity, Void r3) {
        shareDetailsActivity.tvShareText.setTextColor(shareDetailsActivity.getResources().getColor(R.color.colorPrimaryDark));
        shareDetailsActivity.tvShareText.setBackground(shareDetailsActivity.getResources().getDrawable(R.drawable.lightyellow_left));
        shareDetailsActivity.tvShareImg.setTextColor(shareDetailsActivity.getResources().getColor(R.color.white));
        shareDetailsActivity.tvShareImg.setBackground(shareDetailsActivity.getResources().getDrawable(R.drawable.light_yellow_right));
        shareDetailsActivity.llFximgView.setVisibility(0);
        shareDetailsActivity.llFxView.setVisibility(8);
        shareDetailsActivity.isImg = true;
        shareDetailsActivity.btnCopy.setText("保存图片");
    }

    public static /* synthetic */ void lambda$bindListener$5(ShareDetailsActivity shareDetailsActivity, Void r3) {
        shareDetailsActivity.shareByWechat(0, shareDetailsActivity.isImg, shareDetailsActivity.bitmap);
    }

    public static /* synthetic */ void lambda$bindListener$6(ShareDetailsActivity shareDetailsActivity, Void r3) {
        shareDetailsActivity.shareByWechat(1, shareDetailsActivity.isImg, shareDetailsActivity.bitmap);
    }

    public static /* synthetic */ void lambda$bindListener$7(ShareDetailsActivity shareDetailsActivity, Void r2) {
        final CommonDialog showGuizeDialog = CommonUtils.showGuizeDialog(shareDetailsActivity, "佣金计算规则");
        CommonUtils.setOnSureClickListener(new CommonUtils.OnSureClickListener() { // from class: com.yltx.oil.partner.modules.home.activity.ShareDetailsActivity.1
            {
                ShareDetailsActivity.this = shareDetailsActivity;
            }

            @Override // com.yltx.oil.partner.utils.CommonUtils.OnSureClickListener
            public void onSureClick(View view) {
                showGuizeDialog.dismiss();
            }
        });
    }

    public void shareByWechat(int i, boolean z, Bitmap bitmap) {
        if (!UMShareAPI.get(getContext()).isInstall(this, SHARE_MEDIA.WEIXIN)) {
            ToastUtil.showMiddleScreenToast("请先安装微信APP哦");
            return;
        }
        switch (i) {
            case 0:
                if (!z) {
                    new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN).withText(this.tvShareCon.getText().toString()).setCallback(this.umShareListener).share();
                    return;
                } else if (bitmap != null) {
                    UMImage uMImage = new UMImage(getContext(), bitmap);
                    uMImage.setThumb(new UMImage(getContext(), (int) R.mipmap.logo));
                    new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN).withMedia(uMImage).setCallback(this.umShareListener).share();
                    return;
                } else {
                    ToastUtil.showMiddleScreenToast("请保存图片后再分享哦");
                    return;
                }
            case 1:
                if (!z) {
                    new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withText(this.tvShareCon.getText().toString()).setCallback(this.umShareListener).share();
                    return;
                } else if (bitmap != null) {
                    UMImage uMImage2 = new UMImage(getContext(), bitmap);
                    uMImage2.setThumb(new UMImage(getContext(), (int) R.mipmap.logo));
                    new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(uMImage2).setCallback(this.umShareListener).share();
                    return;
                } else {
                    ToastUtil.showMiddleScreenToast("请保存图片后再分享哦");
                    return;
                }
            default:
                return;
        }
    }

    private Bitmap viewSaveToImage(View view) {
        String str;
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(1048576);
        view.setDrawingCacheBackgroundColor(-1);
        Bitmap loadBitmapFromView = loadBitmapFromView(view);
        try {
        } catch (Exception e) {
            e = e;
            str = "";
        }
        if (Environment.getExternalStorageState().equals("mounted")) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File file = new File(externalStorageDirectory, Calendar.getInstance().getTimeInMillis() + ".png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            str = file.getAbsolutePath();
            try {
                loadBitmapFromView.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + file)));
                ToastUtil.showMiddleScreenToast("保存成功");
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                LogUtil.e("imagePath=" + str);
                view.destroyDrawingCache();
                return loadBitmapFromView;
            }
            LogUtil.e("imagePath=" + str);
            view.destroyDrawingCache();
            return loadBitmapFromView;
        }
        throw new Exception("创建文件失败!");
    }

    private Bitmap loadBitmapFromView(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1);
        view.layout(0, 0, width, height);
        view.draw(canvas);
        return createBitmap;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        UMShareAPI.get(this).onActivityResult(i, i2, intent);
    }
}