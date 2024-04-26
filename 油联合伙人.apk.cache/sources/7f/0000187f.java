package com.yltx.oil.partner.modules.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.StateActivity;
import com.yltx.oil.partner.data.network.Config;
import com.yltx.oil.partner.injections.Preconditions;
import com.yltx.oil.partner.utils.BitmapUtils;
import com.yltx.oil.partner.utils.CommonUtils;
import com.yltx.oil.partner.widget.ClipImageLayout;
import java.io.File;

/* loaded from: classes.dex */
public class ClipImageActivity extends StateActivity {
    public static final String EXTRA_IMAGE_URI = "extra.image.uri";
    private static final String TAG = "ClipImageActivity";
    @BindView(R.id.clip_image_layout)
    ClipImageLayout clipImageLayout;
    private Uri clipImageUri;
    private String fileLocalPath;
    @BindView(R.id.head_left_image)
    ImageView headLeftImage;
    @BindView(R.id.head_rigt)
    TextView headRigt;
    @BindView(R.id.head_title)
    TextView headTitle;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ClipImageActivity.class);
    }

    private void setupUI() {
        Throwable th;
        Bitmap bitmap;
        Exception e;
        this.headTitle.setText("合伙人");
        this.headRigt.setText("使用");
        this.headRigt.setVisibility(0);
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(this.clipImageUri));
        } catch (Exception e2) {
            e = e2;
            bitmap = null;
        } catch (Throwable th2) {
            th = th2;
            bitmap = null;
            if (bitmap != null) {
                bitmap.recycle();
            }
            throw th;
        }
        try {
            try {
                this.clipImageLayout.getZoomImageView().setImageBitmap(BitmapUtils.compBitmapByScale(bitmap, 720.0f, 1280.0f));
                if (bitmap == null || !bitmap.isRecycled()) {
                    return;
                }
            } catch (Throwable th3) {
                th = th3;
                if (bitmap != null && bitmap.isRecycled()) {
                    bitmap.recycle();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            if (bitmap == null || !bitmap.isRecycled()) {
                return;
            }
            bitmap.recycle();
        }
        bitmap.recycle();
    }

    private void crop() {
        Bitmap clip = this.clipImageLayout.clip();
        try {
            File appTmpFile = CommonUtils.getAppTmpFile(CommonUtils.generateFileName(CommonUtils.FileType.FILE_TYPE_TMP));
            if (appTmpFile == null) {
                Toast.makeText(this, "文件创建失败", 0).show();
                throw new Exception("file cannot be created.");
            }
            BitmapUtils.saveBitmap(clip, appTmpFile.getAbsolutePath(), CommonUtils.dip2px(this, 60.0f), CommonUtils.dip2px(this, 60.0f));
            this.fileLocalPath = appTmpFile.getPath();
            Intent intent = new Intent();
            intent.putExtra(Config.RESULT_CLIPPED_BITMAP, this.fileLocalPath);
            setResult(-1, intent);
            finish();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(this, "未检测到sdcard，请先插入sdcard", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_clip_image);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bundle = extras;
        }
        this.clipImageUri = (Uri) bundle.getParcelable(EXTRA_IMAGE_URI);
        Preconditions.checkNotNull(this.clipImageUri, "EXTRA_IMAGE_URI argument must be passed");
        ButterKnife.bind(this);
        setupUI();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(EXTRA_IMAGE_URI, this.clipImageUri);
    }

    @OnClick({R.id.head_left_image, R.id.head_rigt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_left_image /* 2131296481 */:
                finish();
                return;
            case R.id.head_rigt /* 2131296482 */:
                crop();
                return;
            default:
                return;
        }
    }
}