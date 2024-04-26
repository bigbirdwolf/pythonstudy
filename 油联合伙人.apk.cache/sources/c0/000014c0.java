package com.umeng.socialize.shareboard;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.utils.SLog;
import com.umeng.socialize.utils.UmengText;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class ShareBoardMenuHelper {
    private static String TAG = "ShareBoardMenuHelper";
    private ShareBoardConfig mShareBoardConfig;

    public ShareBoardMenuHelper(ShareBoardConfig shareBoardConfig) {
        this.mShareBoardConfig = shareBoardConfig;
    }

    public List<SnsPlatform[][]> formatPageData(List<SnsPlatform> list) {
        int i;
        int i2 = this.mShareBoardConfig.mMenuColumnNum * 2;
        int size = list.size();
        ArrayList arrayList = new ArrayList();
        if (size < this.mShareBoardConfig.mMenuColumnNum) {
            SnsPlatform[][] snsPlatformArr = (SnsPlatform[][]) Array.newInstance(SnsPlatform.class, 1, size);
            for (int i3 = 0; i3 < list.size(); i3++) {
                snsPlatformArr[0][i3] = list.get(i3);
            }
            arrayList.add(snsPlatformArr);
            return arrayList;
        }
        int i4 = size / i2;
        int i5 = size % i2;
        if (i5 != 0) {
            i = (i5 / this.mShareBoardConfig.mMenuColumnNum) + (i5 % this.mShareBoardConfig.mMenuColumnNum == 0 ? 0 : 1);
            i4++;
        } else {
            i = -1;
        }
        int i6 = 0;
        while (i6 < i4) {
            arrayList.add((SnsPlatform[][]) Array.newInstance(SnsPlatform.class, (i6 != i4 + (-1) || i == -1) ? 2 : i, this.mShareBoardConfig.mMenuColumnNum));
            i6++;
        }
        int i7 = 0;
        int i8 = 0;
        while (i7 < arrayList.size()) {
            SnsPlatform[][] snsPlatformArr2 = (SnsPlatform[][]) arrayList.get(i7);
            int length = snsPlatformArr2.length;
            int i9 = i8;
            int i10 = 0;
            while (i10 < length) {
                SnsPlatform[] snsPlatformArr3 = snsPlatformArr2[i10];
                int i11 = i9;
                for (int i12 = 0; i12 < snsPlatformArr3.length; i12++) {
                    if (i11 < size) {
                        snsPlatformArr3[i12] = list.get(i11);
                    }
                    i11++;
                }
                i10++;
                i9 = i11;
            }
            i7++;
            i8 = i9;
        }
        return arrayList;
    }

    public View createPageLayout(Context context, SnsPlatform[][] snsPlatformArr) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(48);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        int i = 0;
        while (i < snsPlatformArr.length) {
            linearLayout.addView(createRowLayout(context, snsPlatformArr[i], i != 0));
            i++;
        }
        return linearLayout;
    }

    private View createRowLayout(Context context, SnsPlatform[] snsPlatformArr, boolean z) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        if (z) {
            layoutParams.topMargin = dip2px(context, 20.0f);
        }
        linearLayout.setLayoutParams(layoutParams);
        for (SnsPlatform snsPlatform : snsPlatformArr) {
            linearLayout.addView(createBtnView(context, snsPlatform));
        }
        return linearLayout;
    }

    private View createBtnView(Context context, final SnsPlatform snsPlatform) {
        int i;
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(17);
        if (snsPlatform != null) {
            ResContainer resContainer = ResContainer.get(context);
            View inflate = LayoutInflater.from(context).inflate(resContainer.layout("socialize_share_menu_item"), (ViewGroup) null);
            SocializeImageView socializeImageView = (SocializeImageView) inflate.findViewById(resContainer.id("socialize_image_view"));
            TextView textView = (TextView) inflate.findViewById(resContainer.id("socialize_text_view"));
            if (this.mShareBoardConfig.mMenuBgColor != 0 && this.mShareBoardConfig.mMenuBgShape != ShareBoardConfig.BG_SHAPE_NONE) {
                socializeImageView.setBackgroundColor(this.mShareBoardConfig.mMenuBgColor, this.mShareBoardConfig.mMenuBgPressedColor);
                socializeImageView.setBackgroundShape(this.mShareBoardConfig.mMenuBgShape, this.mShareBoardConfig.mMenuBgShapeAngle);
            } else {
                socializeImageView.setPadding(0, 0, 0, 0);
            }
            if (this.mShareBoardConfig.mMenuIconPressedColor != 0) {
                socializeImageView.setPressedColor(this.mShareBoardConfig.mMenuIconPressedColor);
            }
            String str = "";
            try {
                str = snsPlatform.mShowWord;
            } catch (Exception e) {
                SHARE_MEDIA share_media = snsPlatform.mPlatform;
                String share_media2 = share_media == null ? "" : share_media.toString();
                SLog.error(UmengText.SHAREBOARD.NULLNAME + share_media2, e);
            }
            if (!TextUtils.isEmpty(str)) {
                textView.setText(snsPlatform.mShowWord);
            }
            textView.setGravity(17);
            try {
                i = ResContainer.getResourceId(context, "drawable", snsPlatform.mIcon);
            } catch (Exception e2) {
                SHARE_MEDIA share_media3 = snsPlatform.mPlatform;
                String share_media4 = share_media3 == null ? "" : share_media3.toString();
                SLog.error(UmengText.SHAREBOARD.NULLNAME + share_media4, e2);
                i = 0;
            }
            if (i != 0) {
                socializeImageView.setImageResource(i);
            }
            if (this.mShareBoardConfig.mMenuTextColor != 0) {
                textView.setTextColor(this.mShareBoardConfig.mMenuTextColor);
            }
            inflate.setOnClickListener(new View.OnClickListener() { // from class: com.umeng.socialize.shareboard.ShareBoardMenuHelper.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SHARE_MEDIA share_media5 = snsPlatform.mPlatform;
                    if (ShareBoardMenuHelper.this.mShareBoardConfig == null || ShareBoardMenuHelper.this.mShareBoardConfig.getShareBoardlistener() == null) {
                        return;
                    }
                    ShareBoardMenuHelper.this.mShareBoardConfig.getShareBoardlistener().onclick(snsPlatform, share_media5);
                }
            });
            linearLayout.addView(inflate);
        }
        return linearLayout;
    }

    private int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}