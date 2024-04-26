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
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.PartnerApplication;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.ToolBarActivity;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.ShiftInfo;
import com.yltx.oil.partner.modules.mine.adapter.ShiftBxImageAdapter;
import com.yltx.oil.partner.modules.mine.presenter.ComplaintPresenter;
import com.yltx.oil.partner.modules.mine.view.ComplainView;
import com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber;
import com.yltx.oil.partner.permission.CheckPermission;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.utils.MediaStoreCompat;
import com.yltx.oil.partner.utils.ScrollGridLayoutManager;
import com.yltx.oil.partner.utils.StringUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import com.yltx.oil.partner.utils.XTViewUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* loaded from: classes.dex */
public class ComplainValetActivity extends ToolBarActivity implements ComplainView {
    public static final int REQ_CODE_CROP_PHOTO = 3;
    public static final int REQ_CODE_PICK_PHOTO = 2;
    public static final int REQ_CODE_TAKE_PHOTO = 1;
    @BindView(R.id.bx_img)
    RecyclerView bxImg;
    @Inject
    ComplaintPresenter complaintPresenter;
    private Dialog dialog;
    @BindView(R.id.et_context)
    EditText etContext;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.iv_complain)
    LinearLayout ivComplain;
    private Uri lastTmpFileUri;
    private String mCapturePhotoUriHolder;
    private Dialog mImgLoading;
    private Dialog mProgressDialog;
    private MediaStoreCompat mediaStoreCompat;
    private String path;
    private OptionsPickerView pvOptions;
    private ShiftBxImageAdapter recyclerViewbxImgAdapter;
    private String titleText;
    @BindView(R.id.tv_reson)
    TextView tvReson;
    @BindView(R.id.tv_sub)
    TextView tvSub;
    private String voucherCode;
    private List<ShiftInfo> bxImglist = new ArrayList();
    private String bxpath = "";
    private List<Uri> uris = new ArrayList();
    private ArrayList<String> options1Items = new ArrayList<>();

    @Override // com.yltx.oil.partner.modules.mine.view.ComplainView
    public void onError(Throwable th) {
    }

    public static Intent getCallingIntent(Context context, String str) {
        Intent intent = new Intent(context, ComplainValetActivity.class);
        intent.putExtra("voucherCode", str);
        return intent;
    }

    @Override // com.yltx.oil.partner.base.ToolBarActivity, com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    @RequiresApi(api = 18)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_complain_valet);
        ButterKnife.bind(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        this.complaintPresenter.attachView(this);
        this.mediaStoreCompat = new MediaStoreCompat(this, new Handler(Looper.getMainLooper()));
        if (bundle != null) {
            this.path = bundle.getString("volue");
            Log.i("savedInstanceState", "图片拍摄成功");
            displayImage();
        }
        setupUI();
        bindListener();
    }

    private void setupUI() {
        setToolbarTitle("投诉");
        this.voucherCode = getIntent().getStringExtra("voucherCode");
        this.recyclerViewbxImgAdapter = new ShiftBxImageAdapter(null, this, "图片");
        this.bxImg.setLayoutManager(new ScrollGridLayoutManager(this, 3, false));
        this.bxImg.setAdapter(this.recyclerViewbxImgAdapter);
    }

    private void bindListener() {
        Rx.click(this.ivComplain, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$DIeKLQYY1Qt31fY9rEz_PuuWe-o
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplainValetActivity.lambda$bindListener$0(ComplainValetActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvSub, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$YMQvNrmaPaxBtzfS6xlP4h6Bq-s
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplainValetActivity.lambda$bindListener$1(ComplainValetActivity.this, (Void) obj);
            }
        });
        Rx.click(this.tvReson, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$z9K6PAKjtEjs32TJGyicOsI5rfg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplainValetActivity.lambda$bindListener$2(ComplainValetActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(ComplainValetActivity complainValetActivity, Void r1) {
        Config.IMG_PATH = Config.IMAGE_BX;
        complainValetActivity.setupDialog();
    }

    public static /* synthetic */ void lambda$bindListener$1(ComplainValetActivity complainValetActivity, Void r3) {
        if (TextUtils.isEmpty(complainValetActivity.tvReson.getText().toString()) || complainValetActivity.tvReson.getText().toString().equals("请选择")) {
            ToastUtil.showMiddleScreenToast("请选择原因");
        } else if (TextUtils.isEmpty(complainValetActivity.etName.getText().toString())) {
            ToastUtil.showMiddleScreenToast("请输入名称");
        } else if (!StringUtils.isMobile(complainValetActivity.etPhone.getText().toString())) {
            ToastUtil.showMiddleScreenToast(complainValetActivity.getString(R.string.pls_enter_right_phonenum));
        } else if (TextUtils.isEmpty(complainValetActivity.etContext.getText().toString())) {
            ToastUtil.showMiddleScreenToast("请输入内容");
        } else if (complainValetActivity.bxImglist != null && complainValetActivity.bxImglist.size() > 0) {
            Log.v("httpl==bxImglist", complainValetActivity.bxImglist.size() + "");
            for (int i = 0; i < complainValetActivity.bxImglist.size(); i++) {
                complainValetActivity.sendImage(complainValetActivity.bxImglist.get(i).getImg(), i);
            }
        } else {
            ToastUtil.showMiddleScreenToast("请添加照片");
        }
    }

    public static /* synthetic */ void lambda$bindListener$2(ComplainValetActivity complainValetActivity, Void r2) {
        complainValetActivity.initReasonType();
        complainValetActivity.pvOptions.show(complainValetActivity.tvReson);
    }

    private void sendImage(String str, final int i) {
        PartnerApplication.ossFileHelper.asyncUpload(0, str).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super String>) new ProgressSubscriber<String>(this) { // from class: com.yltx.oil.partner.modules.mine.activity.ComplainValetActivity.1
            {
                ComplainValetActivity.this = this;
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, com.yltx.oil.partner.mvp.subscribers.CommonErrorHandlerSubscriber, rx.Observer
            public void onError(Throwable th) {
                super.onError(th);
                ToastUtil.showMiddleScreenToast(th.toString());
            }

            @Override // com.yltx.oil.partner.mvp.subscribers.ProgressSubscriber, rx.Observer
            public void onNext(String str2) {
                super.onNext((AnonymousClass1) str2);
                ComplainValetActivity complainValetActivity = ComplainValetActivity.this;
                complainValetActivity.bxpath = ComplainValetActivity.this.bxpath + str2 + ",";
                Log.d("httpl===pppp", ComplainValetActivity.this.bxpath);
                if (i == ComplainValetActivity.this.bxImglist.size() - 1) {
                    ComplainValetActivity.this.complaintPresenter.submitComplaint(ComplainValetActivity.this.etPhone.getText().toString(), ComplainValetActivity.this.etContext.getText().toString(), ComplainValetActivity.this.tvReson.getText().toString(), ComplainValetActivity.this.bxpath, ComplainValetActivity.this.voucherCode, ComplainValetActivity.this.etName.getText().toString());
                }
            }
        });
    }

    private void setupDialog() {
        this.dialog = new Dialog(this, R.style.Theme_Light_Dialog);
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_common_simple, (ViewGroup) null);
        Rx.click((TextView) inflate.findViewById(R.id.tv_take_pic), new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$yzK6YAVOn5M8hYNSQLRID_VFDPo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplainValetActivity.lambda$setupDialog$6(ComplainValetActivity.this, (Void) obj);
            }
        });
        Rx.click((TextView) inflate.findViewById(R.id.tv_gallery), new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$xq81ABbEc4EIQkrK_Cb67Zm47UY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplainValetActivity.lambda$setupDialog$7(ComplainValetActivity.this, (Void) obj);
            }
        });
        Rx.click((TextView) inflate.findViewById(R.id.tv_cancel), new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$CyrPQkjd0vJlAaQuNHrC1ftWuQk
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplainValetActivity.lambda$setupDialog$8(ComplainValetActivity.this, (Void) obj);
            }
        });
        Window window = this.dialog.getWindow();
        window.setGravity(80);
        window.setWindowAnimations(R.style.dialogStyle);
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

    public static /* synthetic */ void lambda$setupDialog$6(ComplainValetActivity complainValetActivity, Void r5) {
        complainValetActivity.dialog.dismiss();
        CheckPermission.check(complainValetActivity, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$GhhfFpGoDZhU2I0xy8cRLz18fw4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplainValetActivity.lambda$null$3(ComplainValetActivity.this, (String) obj);
            }
        }, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$J7hZH_iLAIBdb6sXDtX2A-IH6O0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplainValetActivity.lambda$null$5(ComplainValetActivity.this, (String) obj);
            }
        }, "android.permission.CAMERA");
    }

    public static /* synthetic */ void lambda$null$3(ComplainValetActivity complainValetActivity, String str) {
        complainValetActivity.mCapturePhotoUriHolder = complainValetActivity.mediaStoreCompat.invokeCameraCapture(complainValetActivity, 1);
    }

    public static /* synthetic */ void lambda$null$5(ComplainValetActivity complainValetActivity, String str) {
        if (str.equals("android.permission.CAMERA")) {
            MaterialDialog.Builder title = new MaterialDialog.Builder(complainValetActivity.getContext()).title("温馨提示");
            title.content("应用无法使用摄像头权限导致部分功能不可用，" + String.format(CheckPermission.CAMERA_MSG, complainValetActivity.getString(R.string.app_name))).neutralText("确定").onNeutral(new MaterialDialog.SingleButtonCallback() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$Z0X-n1U7WqPlpVmWhmPuL-7UsR8
                @Override // com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback
                public final void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                    ComplainValetActivity.lambda$null$4(materialDialog, dialogAction);
                }
            }).build().show();
        }
    }

    public static /* synthetic */ void lambda$null$4(MaterialDialog materialDialog, DialogAction dialogAction) {
        materialDialog.dismiss();
    }

    public static /* synthetic */ void lambda$setupDialog$7(ComplainValetActivity complainValetActivity, Void r3) {
        complainValetActivity.dialog.dismiss();
        Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        complainValetActivity.startActivityForResult(intent, 2);
    }

    public static /* synthetic */ void lambda$setupDialog$8(ComplainValetActivity complainValetActivity, Void r1) {
        complainValetActivity.dialog.dismiss();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 11) {
            return;
        }
        if (iArr[0] == 0) {
            return;
        }
        MaterialDialog.Builder title = new MaterialDialog.Builder(getContext()).title("温馨提示");
        title.content("应用无法使用摄像头权限导致部分功能不可用，" + String.format(CheckPermission.CAMERA_MSG, getString(R.string.app_name))).neutralText("确定").onNeutral(new MaterialDialog.SingleButtonCallback() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$TPSr44Ye7EkLbhGqm3kJqwUwlVw
            @Override // com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback
            public final void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                ComplainValetActivity.lambda$onRequestPermissionsResult$9(materialDialog, dialogAction);
            }
        }).build().show();
    }

    public static /* synthetic */ void lambda$onRequestPermissionsResult$9(MaterialDialog materialDialog, DialogAction dialogAction) {
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
                    if (intent != null) {
                        this.path = intent.getStringExtra(Config.RESULT_CLIPPED_BITMAP);
                        displayImage();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.uris == null || this.uris.size() <= 0) {
            return;
        }
        for (int i = 0; i < this.uris.size(); i++) {
            deleteLastTmpFile(this.uris.get(i));
        }
    }

    public void displayImage() {
        ShiftInfo shiftInfo = new ShiftInfo();
        shiftInfo.setImg(this.path);
        Log.d("httpl==sise", this.bxImglist.size() + "===" + this.path);
        if (this.bxImglist.size() < 4) {
            this.bxImglist.add(shiftInfo);
            this.ivComplain.setVisibility(0);
        } else if (this.bxImglist.size() == 4) {
            this.bxImglist.add(shiftInfo);
            this.ivComplain.setVisibility(8);
        }
        this.uris.add(Uri.parse(this.path));
        this.recyclerViewbxImgAdapter.setNewData(this.bxImglist);
        this.recyclerViewbxImgAdapter.notifyDataSetChanged();
    }

    private void processPhoto(final Uri uri) {
        Observable.just(uri).subscribeOn(Schedulers.io()).map(new Func1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$IVWB-XXaE5R7MmfaDFgK8LSff-E
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ComplainValetActivity.lambda$processPhoto$10(ComplainValetActivity.this, uri, (Uri) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$jj4Ae_76BbLKppWBfuLiS8peZb8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplainValetActivity.lambda$processPhoto$11(ComplainValetActivity.this, (Uri) obj);
            }
        }, new Action1() { // from class: com.yltx.oil.partner.modules.mine.activity.-$$Lambda$ComplainValetActivity$583CvEg88914bVVkOSpwOtx26t0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ComplainValetActivity.lambda$processPhoto$12(ComplainValetActivity.this, (Throwable) obj);
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
    public static /* synthetic */ android.net.Uri lambda$processPhoto$10(com.yltx.oil.partner.modules.mine.activity.ComplainValetActivity r4, android.net.Uri r5, android.net.Uri r6) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.yltx.oil.partner.modules.mine.activity.ComplainValetActivity.lambda$processPhoto$10(com.yltx.oil.partner.modules.mine.activity.ComplainValetActivity, android.net.Uri, android.net.Uri):android.net.Uri");
    }

    public static /* synthetic */ void lambda$processPhoto$11(ComplainValetActivity complainValetActivity, Uri uri) {
        complainValetActivity.path = uri.getPath();
        complainValetActivity.displayImage();
        complainValetActivity.lastTmpFileUri = uri;
    }

    public static /* synthetic */ void lambda$processPhoto$12(ComplainValetActivity complainValetActivity, Throwable th) {
        Toast.makeText(complainValetActivity, "文件创建失败", 0).show();
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

    private void initReasonType() {
        this.titleText = "请选择投诉原因";
        this.options1Items.clear();
        this.options1Items.add("商品质量问题");
        this.options1Items.add("商品少发漏发");
        this.options1Items.add("其它");
        this.pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() { // from class: com.yltx.oil.partner.modules.mine.activity.ComplainValetActivity.2
            {
                ComplainValetActivity.this = this;
            }

            @Override // com.bigkoo.pickerview.listener.OnOptionsSelectListener
            public void onOptionsSelect(int i, int i2, int i3, View view) {
                if (view.getId() != R.id.tv_reson) {
                    return;
                }
                ComplainValetActivity.this.tvReson.setText((String) ComplainValetActivity.this.options1Items.get(i));
            }
        }).setTitleText(this.titleText).setSubmitColor(-16777216).setCancelColor(-16777216).setDividerColor(-16777216).setTextColorCenter(-16777216).setContentTextSize(14).setSubmitText("确定").setCancelText("取消").setSubCalSize(14).setTitleSize(14).isRestoreItem(true).build();
        this.pvOptions.setPicker(this.options1Items);
    }

    @Override // com.yltx.oil.partner.modules.mine.view.ComplainView
    public void onComplain(HttpResult<String> httpResult) {
        if (httpResult.getData().equals("1")) {
            ToastUtil.showMiddleScreenToast("提交成功");
            finish();
        }
    }
}