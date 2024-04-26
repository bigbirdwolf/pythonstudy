package com.yltx.oil.partner.modules.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class SearchHomeActivity_ViewBinding implements Unbinder {
    private SearchHomeActivity target;

    @UiThread
    public SearchHomeActivity_ViewBinding(SearchHomeActivity searchHomeActivity) {
        this(searchHomeActivity, searchHomeActivity.getWindow().getDecorView());
    }

    @UiThread
    public SearchHomeActivity_ViewBinding(SearchHomeActivity searchHomeActivity, View view) {
        this.target = searchHomeActivity;
        searchHomeActivity.btnSearch = (Button) Utils.findRequiredViewAsType(view, R.id.btn_search, "field 'btnSearch'", Button.class);
        searchHomeActivity.sslsRecyclerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.ssls_recyclerview, "field 'sslsRecyclerview'", RecyclerView.class);
        searchHomeActivity.SeekEdittext = (EditText) Utils.findRequiredViewAsType(view, R.id.Seek_edittext, "field 'SeekEdittext'", EditText.class);
        searchHomeActivity.sslsSc = (ImageView) Utils.findRequiredViewAsType(view, R.id.ssls_sc, "field 'sslsSc'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        SearchHomeActivity searchHomeActivity = this.target;
        if (searchHomeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        searchHomeActivity.btnSearch = null;
        searchHomeActivity.sslsRecyclerview = null;
        searchHomeActivity.SeekEdittext = null;
        searchHomeActivity.sslsSc = null;
    }
}