package com.yltx.oil.partner.modules.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class MessageHomeActivity_ViewBinding implements Unbinder {
    private MessageHomeActivity target;

    @UiThread
    public MessageHomeActivity_ViewBinding(MessageHomeActivity messageHomeActivity) {
        this(messageHomeActivity, messageHomeActivity.getWindow().getDecorView());
    }

    @UiThread
    public MessageHomeActivity_ViewBinding(MessageHomeActivity messageHomeActivity, View view) {
        this.target = messageHomeActivity;
        messageHomeActivity.messageHomeRecyclerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.message_home_recyclerview, "field 'messageHomeRecyclerview'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        MessageHomeActivity messageHomeActivity = this.target;
        if (messageHomeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        messageHomeActivity.messageHomeRecyclerview = null;
    }
}