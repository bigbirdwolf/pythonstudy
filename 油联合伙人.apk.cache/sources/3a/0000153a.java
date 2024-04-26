package com.yltx.oil.partner.base;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.umeng.analytics.MobclickAgent;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.ErrorView;
import com.yltx.oil.partner.data.network.adapter.HttpException;
import com.yltx.oil.partner.injections.instrumentation.HasComponent;
import com.yltx.oil.partner.mvp.exception.BizException;
import com.yltx.oil.partner.mvp.exception.ServerError;
import com.yltx.oil.partner.mvp.views.LoadDataView;
import com.yltx.oil.partner.navigation.Navigator;
import com.yltx.oil.partner.utils.ToastUtil;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseFragment extends DaggerFragment implements LoadDataView {
    private static final String TAG = "BaseFragment";
    public ImageView loadingView;
    public AnimationDrawable loadingViewDrawable;
    FrameLayout mContentView;
    ErrorView mErrorView;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    BetterViewAnimator mViewAnimator;
    private Unbinder unbinder;

    @LayoutRes
    protected abstract int setupContentView();

    @Deprecated
    protected <C> C getComponent(Class<C> cls) {
        return cls.cast(((HasComponent) getActivity()).getComponent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Navigator getNavigator() {
        if (getActivity() instanceof BaseActivity) {
            return ((BaseActivity) getActivity()).getNavigator();
        }
        return null;
    }

    @Nullable
    protected final <V extends View> V $(@NonNull View view, @IdRes int i) {
        return (V) view.findViewById(i);
    }

    public boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        AndroidSupportInjection.inject(this);
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        this.mHandler.post(new Runnable() { // from class: com.yltx.oil.partner.base.-$$Lambda$BaseFragment$ycg2P04x0B3m3MWKG84rm265tuo
            @Override // java.lang.Runnable
            public final void run() {
                Glide.get(PartnerApplication.getInstance()).clearMemory();
            }
        });
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_base_layout, viewGroup, false);
        View inflate2 = layoutInflater.inflate(setupContentView(), (ViewGroup) null);
        this.mContentView = (FrameLayout) $(inflate, R.id.content_view);
        this.mErrorView = (ErrorView) $(inflate, R.id.error_view);
        this.mViewAnimator = (BetterViewAnimator) $(inflate, R.id.view_animator);
        this.loadingView = (ImageView) $(inflate, R.id.loading_view);
        this.mContentView.addView(inflate2);
        this.unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.unbinder != null) {
            this.unbinder.unbind();
        }
    }

    protected void addView(View view) {
        addView(view, this.mContentView.getChildCount());
    }

    protected void addView(View view, int i) {
        this.mContentView.addView(view, i);
    }

    protected void removeView(View view) {
        this.mContentView.removeView(view);
    }

    protected void setFitsSystemWindows(boolean z) {
        this.mViewAnimator.setFitsSystemWindows(z);
    }

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void showLoadingView() {
        this.loadingView.setVisibility(0);
        Glide.with(getActivity()).load(Integer.valueOf((int) R.mipmap.loading)).asGif().into(this.loadingView);
        this.mViewAnimator.setDisplayedChildId(R.id.layout_loading);
    }

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void showEmptyView(ErrorView.Config config, ErrorView.OnRetryListener onRetryListener) {
        this.loadingView.setVisibility(0);
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

    @Override // com.yltx.oil.partner.mvp.views.LoadDataView
    public void showError(String str) {
        ToastUtil.showMiddleScreenToast(getContext(), str);
    }

    protected void setEnableLoadMore(RecyclerView recyclerView, boolean z) {
        if (recyclerView.getAdapter() == null || !(recyclerView.getAdapter() instanceof BaseQuickAdapter)) {
            return;
        }
        ((BaseQuickAdapter) recyclerView.getAdapter()).setEnableLoadMore(z);
    }

    protected void showNoMoreData(RecyclerView recyclerView) {
        if (recyclerView.getAdapter() == null || !(recyclerView.getAdapter() instanceof BaseQuickAdapter)) {
            return;
        }
        ((BaseQuickAdapter) recyclerView.getAdapter()).loadMoreEnd();
    }

    protected void showLoadMoreFail(RecyclerView recyclerView) {
        if (recyclerView.getAdapter() == null || !(recyclerView.getAdapter() instanceof BaseQuickAdapter)) {
            return;
        }
        ((BaseQuickAdapter) recyclerView.getAdapter()).loadMoreFail();
    }

    protected void loadMoreComplete(RecyclerView recyclerView) {
        if (recyclerView.getAdapter() == null || !(recyclerView.getAdapter() instanceof BaseQuickAdapter)) {
            return;
        }
        ((BaseQuickAdapter) recyclerView.getAdapter()).loadMoreComplete();
    }

    public int getToolBarHeight() {
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
        return TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
    }

    public int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }
}