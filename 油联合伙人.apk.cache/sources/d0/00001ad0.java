package com.yltx.oil.partner.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes.dex */
public class XTViewUtils {
    public static ViewGroup.LayoutParams setViewMargin(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (view == null) {
            return null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        } else {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
        }
        marginLayoutParams.setMargins(i, i3, i2, i4);
        view.setLayoutParams(marginLayoutParams);
        return marginLayoutParams;
    }

    public static int dp2pix(Context context, int i) {
        return (int) ((i * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int pix2dp(Context context, int i) {
        return (int) ((i / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void setCurrentDate(TextView textView) {
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.format(calendar.getTime());
        textView.setText(simpleDateFormat.format(time));
    }

    public static void setLastWeekDate(TextView textView) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -7);
        textView.setText(simpleDateFormat.format(calendar.getTime()));
    }

    public static void setHalfMonthDate(TextView textView) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -15);
        textView.setText(simpleDateFormat.format(calendar.getTime()));
    }

    public static void setLastMonthDate(TextView textView) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, -1);
        textView.setText(simpleDateFormat.format(calendar.getTime()));
    }

    public static void setStart_LastMonthDate(TextView textView) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, -2);
        textView.setText(simpleDateFormat.format(calendar.getTime()));
    }

    public static void setEnd_LastMonthDate(TextView textView) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, -1);
        textView.setText(simpleDateFormat.format(calendar.getTime()));
    }
}