package com.yltx.oil.partner.modules.mine.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class ModifyNicknameActivity_ViewBinding implements Unbinder {
    private ModifyNicknameActivity target;

    @UiThread
    public ModifyNicknameActivity_ViewBinding(ModifyNicknameActivity modifyNicknameActivity) {
        this(modifyNicknameActivity, modifyNicknameActivity.getWindow().getDecorView());
    }

    @UiThread
    public ModifyNicknameActivity_ViewBinding(ModifyNicknameActivity modifyNicknameActivity, View view) {
        this.target = modifyNicknameActivity;
        modifyNicknameActivity.activityBasicMessageNickname = (EditText) Utils.findRequiredViewAsType(view, R.id.activity_basic_message_nickname, "field 'activityBasicMessageNickname'", EditText.class);
        modifyNicknameActivity.tvSubmit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_submit, "field 'tvSubmit'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ModifyNicknameActivity modifyNicknameActivity = this.target;
        if (modifyNicknameActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        modifyNicknameActivity.activityBasicMessageNickname = null;
        modifyNicknameActivity.tvSubmit = null;
    }
}