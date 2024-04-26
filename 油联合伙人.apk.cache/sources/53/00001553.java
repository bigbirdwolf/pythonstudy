package com.yltx.oil.partner.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.ErrorView;
import com.yltx.oil.partner.data.network.adapter.HttpException;
import com.yltx.oil.partner.mvp.exception.BizException;
import com.yltx.oil.partner.mvp.exception.ServerError;
import com.yltx.oil.partner.mvp.views.ProgressView;
import com.yltx.oil.partner.utils.DialogUtils;
import com.yltx.oil.partner.utils.ToastUtil;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/* loaded from: classes.dex */
public class StateActivity extends BaseActivity implements ProgressView {
    private static int[] ATTRS = {R.attr.windowActionBarOverlay, R.attr.actionBarSize};
    private static final String TAG = "StateActivity";
    private DialogUtils dialogUtils = new DialogUtils();
    public ImageView loadingView;
    private CoordinatorLayout mBaseView;
    private FrameLayout mContentView;
    private ErrorView mErrorView;
    private BetterViewAnimator mViewAnimator;

    @Override // com.yltx.oil.partner.mvp.views.InterfaceView
    public Context getContext() {
        return this;
    }

    private void initContentView() {
        this.mBaseView = (CoordinatorLayout) getLayoutInflater().inflate(R.layout.activity_base, (ViewGroup) null);
        this.mViewAnimator = (BetterViewAnimator) this.mBaseView.findViewById(R.id.view_animator);
        this.mContentView = (FrameLayout) this.mBaseView.findViewById(R.id.content_view);
        this.mErrorView = (ErrorView) this.mBaseView.findViewById(R.id.error_view);
        this.loadingView = (ImageView) this.mBaseView.findViewById(R.id.loading_view);
    }

    private void initChildView(int i) {
        View inflate = getLayoutInflater().inflate(i, (ViewGroup) null);
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(-1, -1);
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(ATTRS);
        obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.getDimension(1, (int) getContext().getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
        obtainStyledAttributes.recycle();
        this.mContentView.addView(inflate);
        this.mViewAnimator.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ViewGroup getRootView() {
        return this.mBaseView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initContentView();
    }

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void showError(String str) {
        ToastUtil.showMiddleScreenToast(getContext(), str);
    }

    @Override // android.support.v7.app.AppCompatActivity, android.app.Activity
    public void setContentView(int i) {
        initChildView(i);
        setContentView(this.mBaseView);
    }

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void showLoadingView() {
        this.loadingView.setVisibility(0);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.mipmap.loading)).asGif().into(this.loadingView);
        this.mViewAnimator.setDisplayedChildId(R.id.layout_loading);
    }

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void showEmptyView(ErrorView.Config config, ErrorView.OnRetryListener onRetryListener) {
        this.loadingView.setVisibility(8);
        ErrorViewFactory.buildEmptyErrorView(this.mErrorView, config, onRetryListener);
        this.mViewAnimator.setDisplayedChildId(R.id.error_view);
    }

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void showErrorView(Throwable th, ErrorView.Config config, ErrorView.OnRetryListener onRetryListener) {
        this.loadingView.setVisibility(8);
        if (th != null) {
            String str = TAG;
            Log.e(str, "showErrorView: " + th.getMessage(), th);
        }
        if (th != null && (th instanceof BizException)) {
            ErrorViewFactory.buildLoadFailErrorView(this.mErrorView, config, onRetryListener);
        } else if (th != null && (th instanceof UnknownHostException)) {
            ErrorViewFactory.buildNoNetworkErrorView(this.mErrorView, config, onRetryListener);
        } else if (th != null && (th instanceof HttpException)) {
            ErrorViewFactory.buildLoadFailErrorView(this.mErrorView, config, onRetryListener);
        } else if (th != null && (th instanceof ConnectException)) {
            ErrorViewFactory.buildLoadFailErrorView(this.mErrorView, config, onRetryListener);
        } else if (th != null && (th instanceof SocketTimeoutException)) {
            ErrorViewFactory.buildLoadFailErrorView(this.mErrorView, config, onRetryListener);
        } else if (th != null && (th instanceof ServerError)) {
            ErrorViewFactory.buildServerErrorView(this.mErrorView, config, onRetryListener);
        } else {
            ErrorViewFactory.buildLoadFailErrorView(this.mErrorView, config, onRetryListener);
        }
        this.mViewAnimator.setDisplayedChildId(R.id.error_view);
    }

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void onLoadingComplete() {
        this.mViewAnimator.setDisplayedChildId(R.id.content_view);
    }

    @Override // com.yltx.oil.partner.mvp.views.ProgressView
    public void showProgress() {
        this.dialogUtils.showProgress(getContext());
    }

    @Override // com.yltx.oil.partner.mvp.views.ProgressView
    public void hideProgress() {
        this.dialogUtils.hideProgress();
    }
}