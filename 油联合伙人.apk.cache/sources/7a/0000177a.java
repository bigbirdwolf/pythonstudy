package com.yltx.oil.partner.modules.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class MessageDetailsActivity_ViewBinding implements Unbinder {
    private MessageDetailsActivity target;

    @UiThread
    public MessageDetailsActivity_ViewBinding(MessageDetailsActivity messageDetailsActivity) {
        this(messageDetailsActivity, messageDetailsActivity.getWindow().getDecorView());
    }

    @UiThread
    public MessageDetailsActivity_ViewBinding(MessageDetailsActivity messageDetailsActivity, View view) {
        this.target = messageDetailsActivity;
        messageDetailsActivity.detailsTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.details_title, "field 'detailsTitle'", TextView.class);
        messageDetailsActivity.detailsContent = (TextView) Utils.findRequiredViewAsType(view, R.id.details_content, "field 'detailsContent'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        MessageDetailsActivity messageDetailsActivity = this.target;
        if (messageDetailsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        messageDetailsActivity.detailsTitle = null;
        messageDetailsActivity.detailsContent = null;
    }
}