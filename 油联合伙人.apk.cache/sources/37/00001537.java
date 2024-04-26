package com.yltx.oil.partner.base;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.bumptech.glide.Glide;
import com.umeng.analytics.MobclickAgent;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.navigation.Navigator;
import com.yltx.oil.partner.utils.ToastUtil;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

/* loaded from: classes.dex */
public abstract class BaseActivity extends DaggerAppCompatActivity {
    private Handler mHandler;
    private InputMethodManager inputMethodManager = null;
    private FragmentManager mFragmentManager = null;
    private boolean isBackExit = false;
    private long exitTime = 0;

    @Override // dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        AndroidInjection.inject(this);
        super.onCreate(bundle);
        ActivityStackManager.getDefault().addActivity(this);
        this.inputMethodManager = (InputMethodManager) getSystemService("input_method");
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.mHandler.post(new Runnable() { // from class: com.yltx.oil.partner.base.-$$Lambda$BaseActivity$VxNCPHVBabkE-R59ZysnsXLxVgQ
            @Override // java.lang.Runnable
            public final void run() {
                BaseActivity.lambda$onStop$0(BaseActivity.this);
            }
        });
    }

    public static /* synthetic */ void lambda$onStop$0(BaseActivity baseActivity) {
        Glide.get(baseActivity).clearMemory();
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getDefault().finishActivity(this);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        hideSoftKeyboard();
    }

    protected void requestSoftKeyboard(final EditText editText) {
        editText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.yltx.oil.partner.base.BaseActivity.1
            {
                BaseActivity.this = this;
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (editText.requestFocus() && BaseActivity.this.inputMethodManager.showSoftInput(editText, 0)) {
                    editText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode == 2 || getCurrentFocus() == null) {
            return;
        }
        this.inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
    }

    protected void requestFeatureFullScreen() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
    }

    protected void requestFeatureNoTitleWithSystemBarTransparent() {
        getWindow().requestFeature(1);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(201326592);
            window.getDecorView().setSystemUiVisibility(1792);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
            window.setNavigationBarColor(0);
        }
    }

    protected void requestFeatureNoTitleWithStatusBarTransparent() {
        getWindow().requestFeature(1);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(201326592);
            window.getDecorView().setSystemUiVisibility(1280);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        }
    }

    protected void setStatusBarColor(@ColorInt int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(i);
        }
    }

    protected void addFragment(Fragment fragment, @IdRes int i) {
        if (this.mFragmentManager == null) {
            this.mFragmentManager = getSupportFragmentManager();
        }
        this.mFragmentManager.beginTransaction().add(i, fragment).commit();
    }

    protected void addFragment(Fragment fragment, @IdRes int i, String str) {
        if (this.mFragmentManager == null) {
            this.mFragmentManager = getSupportFragmentManager();
        }
        this.mFragmentManager.beginTransaction().add(i, fragment, str).commit();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (isShouldHideInput(currentFocus, motionEvent)) {
                hideSoftInput(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean isShouldHideInput(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }

    private void hideSoftInput(IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }

    protected void replaceFragment(Fragment fragment, @IdRes int i) {
        if (this.mFragmentManager == null) {
            this.mFragmentManager = getSupportFragmentManager();
        }
        this.mFragmentManager.beginTransaction().replace(i, fragment).commit();
    }

    protected void replaceFragment(Fragment fragment, @IdRes int i, String str) {
        if (this.mFragmentManager == null) {
            this.mFragmentManager = getSupportFragmentManager();
        }
        this.mFragmentManager.beginTransaction().replace(i, fragment, str).commit();
    }

    protected void hideFragment(Fragment fragment) {
        if (this.mFragmentManager == null) {
            this.mFragmentManager = getSupportFragmentManager();
        }
        this.mFragmentManager.beginTransaction().hide(fragment).commit();
    }

    protected void showFragment(Fragment fragment) {
        if (this.mFragmentManager == null) {
            this.mFragmentManager = getSupportFragmentManager();
        }
        this.mFragmentManager.beginTransaction().show(fragment).commit();
    }

    protected void removeFragment(Fragment fragment) {
        if (this.mFragmentManager == null) {
            this.mFragmentManager = getSupportFragmentManager();
        }
        this.mFragmentManager.beginTransaction().remove(fragment).commit();
    }

    protected void attachFragment(Fragment fragment) {
        if (this.mFragmentManager == null) {
            this.mFragmentManager = getSupportFragmentManager();
        }
        this.mFragmentManager.beginTransaction().attach(fragment).commit();
    }

    protected void detachFragment(Fragment fragment) {
        if (this.mFragmentManager == null) {
            this.mFragmentManager = getSupportFragmentManager();
        }
        this.mFragmentManager.beginTransaction().detach(fragment).commit();
    }

    public Navigator getNavigator() {
        return ((PartnerApplication) getApplication()).getNavigator();
    }

    public void setBackExit(boolean z) {
        this.isBackExit = z;
    }

    @Override // android.support.v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 0 && this.isBackExit) {
            if (System.currentTimeMillis() - this.exitTime > 2000) {
                ToastUtil.showMiddleScreenToast(getApplicationContext().getResources().getString(R.string.exit_prompt));
                this.exitTime = System.currentTimeMillis();
                return true;
            }
            System.exit(0);
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}