package com.yltx.oil.partner.modules.mine.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.network.UserToken;
import com.yltx.oil.partner.modules.mine.presenter.MineInfoPresenter;
import com.yltx.oil.partner.modules.mine.view.MineInfoView;
import com.yltx.oil.partner.permission.CheckPermission;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.DataCache;
import com.yltx.oil.partner.utils.MediaStoreCompat;
import com.yltx.oil.partner.utils.StringUtils;
import com.yltx.oil.partner.utils.XTViewUtils;
import com.yltx.oil.partner.widget.CommonDialog;
import com.yltx.oil.partner.widget.SelectableRoundedImageView;
import java.io.File;
import javax.inject.Inject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* loaded from: classes.dex */
public class MineInfoActivity extends ToolBarActivity implements MineInfoView {
    public static final int REQ_CODE_CROP_PHOTO = 3;
    public static final int REQ_CODE_PICK_PHOTO = 2;
    public static final int REQ_CODE_TAKE_PHOTO = 1;
    @BindView(R.id.activity_userinnfos_basic_message)
    TextView activityUserinnfosBasicMessage;
    @BindView(R.id.activity_userinnfos_head_iv)
    SelectableRoundedImageView activityUserinnfosHeadIv;
    @BindView(R.id.activity_userinnfos_login_password)
    TextView activityUserinnfosLoginPassword;
    @BindView(R.id.activity_userinnfos_out)
    TextView activityUserinnfosOut;
    @BindView(R.id.activity_userinnfos_phonenum_binding)
    TextView activityUserinnfosPhonenumBinding;
    private Dialog dialog;
    String headpath = "";
    private Uri lastTmpFileUri;
    private String mCapturePhotoUriHolder;
    private Dialog mImgLoading;
    @Inject
    public MineInfoPresenter mPresenter;
    private MediaStoreCompat mediaStoreCompat;
    private String path;
    String phone;

    @Override // com.yltx.oil.partner.modules.mine.view.MineInfoView
    public void onError(Throwable th) {
    }

    @Override // com.yltx.oil.partner.modules.mine.view.MineInfoView
    public void onSuccess(HttpResult<String> httpResult) {
    }

    public static Intent getCallingIntent(Context context, String str) {
        Intent intent = new Intent(context, MineInfoActivity.class);
        intent.putExtra("headpath", str);
        return intent;
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    @RequiresApi(api = 18)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mine_info);
        ButterKnife.bind(this);
        this.mPresenter.attachView(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        this.mediaStoreCompat = new MediaStoreCompat(this, new Handler(Looper.getMainLooper()));
        if (bundle != null) {
            this.path = bundle.getString("volue");
            Log.i("savedInstanceState", "图片拍摄成功");
            displayImage();
        }
        setupUI();
        bindListener();
    }

    protected void setupUI() {
        setToolbarTitle("账户信息");
        this.headpath = getIntent().getStringExtra("headpath");
        if (PartnerApplication.instance.isLoading) {
            this.phone = PartnerApplication.instance.getUserInfos().getUserInfo().getPhone();
        }
        if (StringUtils.isEmpty(this.headpath)) {
            return;
        }
        AlbumDisplayUtils.displayHeaderImg(getContext(), this.activityUserinnfosHeadIv, this.headpath);
    }

    private void initImgLoading() {
        if (this.mImgLoading == null) {
            this.mImgLoading = new Dialog(this, R.style.AppTheme_Dialogstyle);
            this.mImgLoading.setCancelable(false);
            this.mImgLoading.setCanceledOnTouchOutside(false);
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.custom_progressbar, (ViewGroup) null);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.mipmap.loading)).asGif().into((ImageView) inflate.findViewById(R.id.loading_view));
        this.mImgLoading.setContentView(inflate);
    }

    protected void bindListener() {
        Rx.click(this.activityUserinnfosHeadIv, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$uG5cpVf7fb5hQCAGPvOqr3EbyMs
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MineInfoActivity.lambda$bindListener$0(MineInfoActivity.this, (Void) obj);
            }
        });
        Rx.click(this.activityUserinnfosOut, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$yWkBbCIp0IE-SRHou2SSvwGLgA0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MineInfoActivity.lambda$bindListener$1(MineInfoActivity.this, (Void) obj);
            }
        });
        Rx.click(this.activityUserinnfosBasicMessage, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$k817FD_PDDBcSG_15V93iCK9ZLI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MineInfoActivity.lambda$bindListener$2(MineInfoActivity.this, (Void) obj);
            }
        });
        Rx.click(this.activityUserinnfosPhonenumBinding, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$6_yvYKWRZp3_krQHcdnrESp4JaI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MineInfoActivity.lambda$bindListener$3(MineInfoActivity.this, (Void) obj);
            }
        });
        Rx.click(this.activityUserinnfosLoginPassword, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$n1n8rX_WNYz5rQpODaX0ulzwrqE
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MineInfoActivity.lambda$bindListener$4(MineInfoActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(MineInfoActivity mineInfoActivity, Void r1) {
        Config.IMG_PATH = Config.IMAGE_BX;
        mineInfoActivity.setupDialog();
    }

    public static /* synthetic */ void lambda$bindListener$1(MineInfoActivity mineInfoActivity, Void r2) {
        final CommonDialog showPhoneDialog = CommonUtils.showPhoneDialog(mineInfoActivity, "退出该账户吗？");
        CommonUtils.setOnSureClickListener(new CommonUtils.OnSureClickListener() { // from class: com.yltx.oil.partner.modules.mine.activity.MineInfoActivity.1
            {
                MineInfoActivity.this = mineInfoActivity;
            }

            @Override // com.yltx.oil.partner.utils.CommonUtils.OnSureClickListener
            public void onSureClick(View view) {
                MineInfoActivity.this.getSharedPreferences("userId", 0).edit().clear().commit();
                PartnerApplication.instance.isLoading = false;
                DataCache.setToken(MineInfoActivity.this, null);
                UserToken.getInstance().setToken(null);
                UserToken.getInstance().setPhone(null);
                DataCache.setPartnerInfo(MineInfoActivity.this, null);
                DataCache.setUserToken(PartnerApplication.instance, null);
                showPhoneDialog.dismiss();
                MineInfoActivity.this.finish();
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$2(MineInfoActivity mineInfoActivity, Void r1) {
        mineInfoActivity.getNavigator().navigateToModifyNickname(mineInfoActivity);
    }

    public static /* synthetic */ void lambda$bindListener$3(MineInfoActivity mineInfoActivity, Void r2) {
        mineInfoActivity.getNavigator().navigateToPhone(mineInfoActivity, mineInfoActivity.phone);
    }

    public static /* synthetic */ void lambda$bindListener$4(MineInfoActivity mineInfoActivity, Void r1) {
        mineInfoActivity.getNavigator().navigateToUpdatePwd(mineInfoActivity);
    }

    private void setupDialog() {
        this.dialog = new Dialog(this, R.style.Theme_Light_Dialog);
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_common_simple, (ViewGroup) null);
        Rx.click((TextView) inflate.findViewById(R.id.tv_take_pic), new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$rU9Fufsd63A56SG1uHbGWmFa3wo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MineInfoActivity.lambda$setupDialog$8(MineInfoActivity.this, (Void) obj);
            }
        });
        Rx.click((TextView) inflate.findViewById(R.id.tv_gallery), new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$_qjRGvoBgxa_VyFyzN9XOzZzQ0A
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MineInfoActivity.lambda$setupDialog$9(MineInfoActivity.this, (Void) obj);
            }
        });
        Rx.click((TextView) inflate.findViewById(R.id.tv_cancel), new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$He6dEwmAPKDhnwG2Tsfl3dXPzGQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MineInfoActivity.lambda$setupDialog$10(MineInfoActivity.this, (Void) obj);
            }
        });
        Window window = this.dialog.getWindow();
        window.setGravity(80);
        window.setWindowAnimations(R.style.commondialog);
        window.getDecorView().setPadding(XTViewUtils.dp2pix(this, 0), 0, XTViewUtils.dp2pix(this, 0), 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        this.dialog.setContentView(inflate);
        this.dialog.setCancelable(true);
        this.dialog.setCanceledOnTouchOutside(true);
        this.dialog.show();
    }

    public static /* synthetic */ void lambda$setupDialog$8(MineInfoActivity mineInfoActivity, Void r5) {
        mineInfoActivity.dialog.dismiss();
        CheckPermission.check(mineInfoActivity, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$KGq_hpq2_M_k9UHEIokMzmUxpm8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MineInfoActivity.lambda$null$5(MineInfoActivity.this, (String) obj);
            }
        }, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$W9jje3OgYRDZApE7ayaPxgDMNns
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MineInfoActivity.lambda$null$7(MineInfoActivity.this, (String) obj);
            }
        }, "android.permission.CAMERA");
    }

    public static /* synthetic */ void lambda$null$5(MineInfoActivity mineInfoActivity, String str) {
        mineInfoActivity.mCapturePhotoUriHolder = mineInfoActivity.mediaStoreCompat.invokeCameraCapture(mineInfoActivity, 1);
    }

    public static /* synthetic */ void lambda$null$7(MineInfoActivity mineInfoActivity, String str) {
        if (str.equals("android.permission.CAMERA")) {
            MaterialDialog.Builder title = new MaterialDialog.Builder(mineInfoActivity.getContext()).title("温馨提示");
            title.content("应用无法使用摄像头权限导致部分功能不可用，" + String.format(CheckPermission.CAMERA_MSG, mineInfoActivity.getString(R.string.app_name))).neutralText("确定").onNeutral(new MaterialDialog.SingleButtonCallback() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$Ed2xYbzSb48lOX02mgLm8EMvYrw
                @Override // com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback
                public final void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                    MineInfoActivity.lambda$null$6(materialDialog, dialogAction);
                }
            }).build().show();
        }
    }

    public static /* synthetic */ void lambda$null$6(MaterialDialog materialDialog, DialogAction dialogAction) {
        materialDialog.dismiss();
    }

    public static /* synthetic */ void lambda$setupDialog$9(MineInfoActivity mineInfoActivity, Void r3) {
        mineInfoActivity.dialog.dismiss();
        Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        mineInfoActivity.startActivityForResult(intent, 2);
    }

    public static /* synthetic */ void lambda$setupDialog$10(MineInfoActivity mineInfoActivity, Void r1) {
        mineInfoActivity.dialog.dismiss();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 11) {
            return;
        }
        if (iArr[0] == 0) {
            this.mCapturePhotoUriHolder = this.mediaStoreCompat.invokeCameraCapture(this, 1);
            return;
        }
        MaterialDialog.Builder title = new MaterialDialog.Builder(getContext()).title("温馨提示");
        title.content("应用无法使用摄像头权限导致部分功能不可用，" + String.format(CheckPermission.CAMERA_MSG, getString(R.string.app_name))).neutralText("确定").onNeutral(new MaterialDialog.SingleButtonCallback() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$Ae-mK2J0rj_d8IBPg5kFULaH65Q
            @Override // com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback
            public final void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                MineInfoActivity.lambda$onRequestPermissionsResult$11(materialDialog, dialogAction);
            }
        }).build().show();
    }

    public static /* synthetic */ void lambda$onRequestPermissionsResult$11(MaterialDialog materialDialog, DialogAction dialogAction) {
        materialDialog.dismiss();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 1:
                    processPhoto(this.mediaStoreCompat.getCapturedPhotoUri(intent, this.mCapturePhotoUriHolder));
                    if (this.mImgLoading != null) {
                        this.mImgLoading.show();
                        return;
                    }
                    return;
                case 2:
                    if (intent != null) {
                        processPhoto(intent.getData());
                        if (this.mImgLoading != null) {
                            this.mImgLoading.show();
                            return;
                        }
                        return;
                    }
                    return;
                case 3:
                    if (intent.getExtras() != null) {
                        this.path = intent.getStringExtra(Config.RESULT_CLIPPED_BITMAP);
                        displayImage();
                        this.mPresenter.submitHeadPic(this.path);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private void processPhoto(final Uri uri) {
        Observable.just(uri).subscribeOn(Schedulers.io()).map(new Func1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$BxHFvpCp0xpqaxZUV7OYIvkE-20
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return MineInfoActivity.lambda$processPhoto$12(MineInfoActivity.this, uri, (Uri) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$E_HxGn8zadyaxaosQmRdhrrsaMc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MineInfoActivity.lambda$processPhoto$13(MineInfoActivity.this, (Uri) obj);
            }
        }, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$MineInfoActivity$Vwj-U46fegtOCxHtdroIIcLRBBM
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MineInfoActivity.lambda$processPhoto$14(MineInfoActivity.this, (Throwable) obj);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0024, code lost:
        if (r1 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0026, code lost:
        r1.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x004c, code lost:
        if (r1 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0053, code lost:
        return android.net.Uri.fromFile(r6);
     */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ android.net.Uri lambda$processPhoto$12(com.yltx.oil.partner.modules.mine.activity.MineInfoActivity r4, android.net.Uri r5, android.net.Uri r6) {
        /*
            com.yltx.oil.partner.utils.CommonUtils$FileType r6 = com.yltx.oil.partner.utils.CommonUtils.FileType.FILE_TYPE_TMP
            java.lang.String r6 = com.yltx.oil.partner.utils.CommonUtils.generateFileName(r6)
            r0 = 0
            java.io.File r6 = r4.providerFile(r6)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L41
            android.content.ContentResolver r1 = r4.getContentResolver()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L3e
            r2 = 960(0x3c0, float:1.345E-42)
            android.graphics.Bitmap r5 = com.yltx.oil.partner.utils.ImageUtils.decodeStreamByScale(r1, r5, r2)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L3e
            r1 = 102400(0x19000, float:1.43493E-40)
            android.graphics.Bitmap r1 = com.yltx.oil.partner.utils.ImageUtils.compBitmapByQuality(r5, r1)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L35
            com.yltx.oil.partner.utils.ImageUtils.writeBitmapToFile(r1, r6)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            if (r5 == 0) goto L24
            r5.recycle()
        L24:
            if (r1 == 0) goto L4f
        L26:
            r1.recycle()
            goto L4f
        L2a:
            r6 = move-exception
            goto L33
        L2c:
            r0 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L44
        L31:
            r6 = move-exception
            r1 = r0
        L33:
            r0 = r5
            goto L55
        L35:
            r1 = move-exception
            r3 = r0
            r0 = r5
            r5 = r1
            r1 = r3
            goto L44
        L3b:
            r5 = move-exception
            r1 = r0
            goto L44
        L3e:
            r6 = move-exception
            r1 = r0
            goto L55
        L41:
            r5 = move-exception
            r6 = r0
            r1 = r6
        L44:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L54
            if (r0 == 0) goto L4c
            r0.recycle()
        L4c:
            if (r1 == 0) goto L4f
            goto L26
        L4f:
            android.net.Uri r5 = android.net.Uri.fromFile(r6)
            return r5
        L54:
            r6 = move-exception
        L55:
            if (r0 == 0) goto L5a
            r0.recycle()
        L5a:
            if (r1 == 0) goto L5f
            r1.recycle()
        L5f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yltx.oil.partner.modules.mine.activity.MineInfoActivity.lambda$processPhoto$12(com.yltx.oil.partner.modules.mine.activity.MineInfoActivity, android.net.Uri, android.net.Uri):android.net.Uri");
    }

    public static /* synthetic */ void lambda$processPhoto$13(MineInfoActivity mineInfoActivity, Uri uri) {
        mineInfoActivity.path = uri.getPath();
        mineInfoActivity.getNavigator().navigateToClipImage(mineInfoActivity.getContext(), 3, uri);
        mineInfoActivity.lastTmpFileUri = uri;
        if (mineInfoActivity.mImgLoading != null) {
            mineInfoActivity.mImgLoading.dismiss();
        }
    }

    public static /* synthetic */ void lambda$processPhoto$14(MineInfoActivity mineInfoActivity, Throwable th) {
        if (mineInfoActivity.mImgLoading != null) {
            mineInfoActivity.mImgLoading.dismiss();
        }
        Toast.makeText(mineInfoActivity, "文件创建失败", 0).show();
    }

    private File providerFile(String str) throws Exception {
        return CommonUtils.getAppTmpFile(str);
    }

    private void deleteLastTmpFile(Uri uri) {
        if (uri != null) {
            File file = new File(uri.getPath());
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("volue", this.path);
    }

    public void displayImage() {
        Glide.with((FragmentActivity) this).load(this.path).dontAnimate().into(this.activityUserinnfosHeadIv);
    }
}