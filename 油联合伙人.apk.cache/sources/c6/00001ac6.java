package com.yltx.oil.partner.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.PartnerApplication;

/* loaded from: classes.dex */
public class ToastUtil {
    private static Toast mLongToast;
    private static TextView mTextView;
    private static Toast mToast;
    private static View mToastView;

    public static void showMiddleScreenToast(Context context, String str) {
        try {
            if (mToast == null) {
                mToastView = LayoutInflater.from(context).inflate(R.layout.toast, (ViewGroup) null);
                mToast = new Toast(context);
                mToast.setView(mToastView);
                mToast.setDuration(0);
                mToast.setGravity(17, 0, 0);
                mTextView = (TextView) mToastView.findViewById(R.id.message);
            }
            mTextView.setText(str);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showMiddleScreenToast(String str) {
        if (mToast == null) {
            mToastView = LayoutInflater.from(PartnerApplication.getInstance().getApplicationContext()).inflate(R.layout.toast, (ViewGroup) null);
            mToast = new Toast(PartnerApplication.getInstance().getApplicationContext());
            mToast.setView(mToastView);
            mToast.setDuration(0);
            mToast.setGravity(17, 0, 0);
            mTextView = (TextView) mToastView.findViewById(R.id.message);
        }
        mTextView.setText(str);
        mToast.show();
    }

    public static void showMiddleScreenLongToast(String str) {
        if (mLongToast == null) {
            mToastView = LayoutInflater.from(PartnerApplication.getInstance().getApplicationContext()).inflate(R.layout.toast, (ViewGroup) null);
            mLongToast = new Toast(PartnerApplication.getInstance().getApplicationContext());
            mLongToast.setView(mToastView);
            mLongToast.setDuration(1);
            mLongToast.setGravity(17, 0, 0);
            mTextView = (TextView) mToastView.findViewById(R.id.message);
        }
        mTextView.setText(str);
        mLongToast.show();
    }
}