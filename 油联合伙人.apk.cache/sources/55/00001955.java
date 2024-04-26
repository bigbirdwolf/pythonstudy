package com.yltx.oil.partner.modules.oiltrade.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class StoredValueCardFragment_ViewBinding implements Unbinder {
    private StoredValueCardFragment target;

    @UiThread
    public StoredValueCardFragment_ViewBinding(StoredValueCardFragment storedValueCardFragment, View view) {
        this.target = storedValueCardFragment;
        storedValueCardFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.fragment_oilcard_list, "field 'recyclerView'", RecyclerView.class);
        storedValueCardFragment.mRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.SR_list, "field 'mRefreshLayout'", SwipeRefreshLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        StoredValueCardFragment storedValueCardFragment = this.target;
        if (storedValueCardFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        storedValueCardFragment.recyclerView = null;
        storedValueCardFragment.mRefreshLayout = null;
    }
}