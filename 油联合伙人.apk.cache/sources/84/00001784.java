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
public class SearchResultActivity_ViewBinding implements Unbinder {
    private SearchResultActivity target;

    @UiThread
    public SearchResultActivity_ViewBinding(SearchResultActivity searchResultActivity) {
        this(searchResultActivity, searchResultActivity.getWindow().getDecorView());
    }

    @UiThread
    public SearchResultActivity_ViewBinding(SearchResultActivity searchResultActivity, View view) {
        this.target = searchResultActivity;
        searchResultActivity.headLeftImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.head_left_image, "field 'headLeftImage'", ImageView.class);
        searchResultActivity.ssEdittext = (EditText) Utils.findRequiredViewAsType(view, R.id.ss_edittext, "field 'ssEdittext'", EditText.class);
        searchResultActivity.ssBtnSearch = (Button) Utils.findRequiredViewAsType(view, R.id.ss_btn_search, "field 'ssBtnSearch'", Button.class);
        searchResultActivity.ssList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.ss_list, "field 'ssList'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        SearchResultActivity searchResultActivity = this.target;
        if (searchResultActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        searchResultActivity.headLeftImage = null;
        searchResultActivity.ssEdittext = null;
        searchResultActivity.ssBtnSearch = null;
        searchResultActivity.ssList = null;
    }
}