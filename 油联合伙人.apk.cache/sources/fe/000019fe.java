package com.yltx.oil.partner.modules.profit.fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.widget.PayPwdView;
import com.yltx.oil.partner.widget.PwdInputMethodView;

/* loaded from: classes.dex */
public class PayFragment extends DialogFragment implements View.OnClickListener {
    public static final String EXTRA_CONTENT = "extra_content";
    private PayPwdView.InputCallBack inputCallBack;
    private PayPwdView psw_input;

    @Override // android.support.v4.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.fragment_pay);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.gravity = 48;
        window.setAttributes(attributes);
        initView(dialog);
        return dialog;
    }

    private void initView(Dialog dialog) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            ((TextView) dialog.findViewById(R.id.tv_content)).setText(arguments.getString(EXTRA_CONTENT));
        }
        this.psw_input = (PayPwdView) dialog.findViewById(R.id.payPwdView);
        this.psw_input.setInputMethodView((PwdInputMethodView) dialog.findViewById(R.id.inputMethodView));
        this.psw_input.setInputCallBack(this.inputCallBack);
        dialog.findViewById(R.id.iv_close).setOnClickListener(this);
        dialog.findViewById(R.id.tv_forget_pay_pwd).setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.iv_close) {
            dismiss();
        }
        view.getId();
    }

    public void setPaySuccessCallBack(PayPwdView.InputCallBack inputCallBack) {
        this.inputCallBack = inputCallBack;
    }
}