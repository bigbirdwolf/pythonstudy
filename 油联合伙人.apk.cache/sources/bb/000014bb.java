package com.umeng.socialize.shareboard;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.alipay.sdk.util.i;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.umeng.socialize.utils.SocializeSpUtils;
import java.util.List;

/* loaded from: classes.dex */
public class ShareBoard extends PopupWindow {
    private ShareBoardConfig mShareBoardConfig;

    public ShareBoard(Context context, List<SnsPlatform> list) {
        this(context, list, null);
    }

    public ShareBoard(Context context, List<SnsPlatform> list, ShareBoardConfig shareBoardConfig) {
        super(context);
        setWindowLayoutMode(-1, -1);
        boolean z = context.getResources().getConfiguration().orientation == 2;
        shareBoardConfig = shareBoardConfig == null ? new ShareBoardConfig() : shareBoardConfig;
        this.mShareBoardConfig = shareBoardConfig;
        shareBoardConfig.setOrientation(z);
        UMActionFrame uMActionFrame = new UMActionFrame(context);
        uMActionFrame.setSnsPlatformData(list, shareBoardConfig);
        uMActionFrame.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        uMActionFrame.setDismissListener(new PopupWindow.OnDismissListener() { // from class: com.umeng.socialize.shareboard.ShareBoard.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                ShareBoard.this.dismiss();
            }
        });
        setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.umeng.socialize.shareboard.ShareBoard.2
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                PopupWindow.OnDismissListener onDismissListener = ShareBoard.this.mShareBoardConfig != null ? ShareBoard.this.mShareBoardConfig.getOnDismissListener() : null;
                if (onDismissListener != null) {
                    onDismissListener.onDismiss();
                }
            }
        });
        setContentView(uMActionFrame);
        setFocusable(true);
        saveShareboardConfig(context, shareBoardConfig);
    }

    private void saveShareboardConfig(Context context, ShareBoardConfig shareBoardConfig) {
        if (context == null || shareBoardConfig == null) {
            return;
        }
        String str = shareBoardConfig.mShareboardPosition == ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM ? "0" : "1";
        String str2 = null;
        if (shareBoardConfig.mMenuBgShape == ShareBoardConfig.BG_SHAPE_NONE) {
            str2 = "0";
        } else if (shareBoardConfig.mMenuBgShape == ShareBoardConfig.BG_SHAPE_CIRCULAR) {
            str2 = "1";
        } else if (shareBoardConfig.mMenuBgShape == ShareBoardConfig.BG_SHAPE_ROUNDED_SQUARE) {
            str2 = shareBoardConfig.mMenuBgShapeAngle != 0 ? "2" : "3";
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        SocializeSpUtils.putShareBoardConfig(context, str2 + i.b + str);
    }

    public void setShareBoardlistener(final ShareBoardlistener shareBoardlistener) {
        if (this.mShareBoardConfig == null) {
            return;
        }
        this.mShareBoardConfig.setShareBoardlistener(new ShareBoardlistener() { // from class: com.umeng.socialize.shareboard.ShareBoard.3
            @Override // com.umeng.socialize.utils.ShareBoardlistener
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                ShareBoard.this.setOnDismissListener(null);
                ShareBoard.this.dismiss();
                if (shareBoardlistener != null) {
                    shareBoardlistener.onclick(snsPlatform, share_media);
                }
            }
        });
    }
}