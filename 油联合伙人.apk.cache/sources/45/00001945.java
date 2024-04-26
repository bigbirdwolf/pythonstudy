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
public class GiftCardFragment_ViewBinding implements Unbinder {
    private GiftCardFragment target;

    @UiThread
    public GiftCardFragment_ViewBinding(GiftCardFragment giftCardFragment, View view) {
        this.target = giftCardFragment;
        giftCardFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.fragment_gift_list, "field 'recyclerView'", RecyclerView.class);
        giftCardFragment.mRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.sr_list, "field 'mRefreshLayout'", SwipeRefreshLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        GiftCardFragment giftCardFragment = this.target;
        if (giftCardFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        giftCardFragment.recyclerView = null;
        giftCardFragment.mRefreshLayout = null;
    }
}