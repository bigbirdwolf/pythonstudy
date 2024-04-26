package com.yltx.oil.partner.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.yltx.oil.partner.R;

/* loaded from: classes.dex */
public class PwdInputMethodView extends LinearLayout implements View.OnClickListener {
    private InputReceiver inputReceiver;

    /* loaded from: classes.dex */
    public interface InputReceiver {
        void receive(String str);
    }

    public PwdInputMethodView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_password_input, this);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
        findViewById(R.id.layout_hide).setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.widget.PwdInputMethodView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PwdInputMethodView.this.setVisibility(8);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.inputReceiver.receive((String) view.getTag());
    }

    public void setInputReceiver(InputReceiver inputReceiver) {
        this.inputReceiver = inputReceiver;
    }
}