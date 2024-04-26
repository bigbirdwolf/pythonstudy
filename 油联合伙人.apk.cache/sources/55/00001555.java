package com.yltx.oil.partner.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class ToolBarActivity extends StateActivity {
    protected TextView mBxHistory;
    int mHeight;
    private TextView mRegister;
    public Toolbar mToolbar;
    private TextView mToolbarTitle;
    Window window;

    public TextView getmRegister() {
        return this.mRegister;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.window = getWindow();
        initToolBar();
    }

    public TextView getmToolbarTitle() {
        return this.mToolbarTitle;
    }

    public void setmToolbarTitle(TextView textView) {
        this.mToolbarTitle = textView;
    }

    private void initToolBar() {
        View inflate = getLayoutInflater().inflate(R.layout.appbar_layout, getRootView());
        this.mToolbar = (Toolbar) inflate.findViewById(R.id.id_tool_bar);
        this.mToolbarTitle = (TextView) inflate.findViewById(R.id.tool_bar_title);
        this.mBxHistory = (TextView) inflate.findViewById(R.id.tv_bx_history);
        this.mRegister = (TextView) inflate.findViewById(R.id.iv_register);
        this.mToolbar.setTitle("");
        setSupportActionBar(this.mToolbar);
        this.mHeight = getTopHeight();
        if (Build.VERSION.SDK_INT >= 21) {
            this.window.clearFlags(67108864);
            this.window.getDecorView().setSystemUiVisibility(9216);
            this.window.addFlags(Integer.MIN_VALUE);
            this.window.setStatusBarColor(0);
            this.mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.window.addFlags(67108864);
            this.mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        } else {
            this.mHeight = getToolBarHeight();
        }
        this.mToolbar.getLayoutParams().height = this.mHeight;
    }

    public int getTopHeight() {
        return getToolBarHeight() + getStatusBarHeight();
    }

    public int getToolBarHeight() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
        return TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
    }

    public int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public Toolbar getToolbar() {
        return this.mToolbar;
    }

    public void setToolbarTitle(String str) {
        this.mToolbarTitle.setText(str);
    }

    public void setToolbarTitle(@StringRes int i) {
        this.mToolbarTitle.setText(i);
    }

    public void setToolbarTitleColor(@ColorInt int i) {
        this.mToolbarTitle.setTextColor(i);
    }

    public void setToolbarTitleColorRes(@ColorRes int i) {
        this.mToolbarTitle.setTextColor(getResources().getColor(i));
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}