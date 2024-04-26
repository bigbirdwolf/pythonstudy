package com.yltx.oil.partner.modules.mine.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.data.response.ShiftInfo;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;
import java.io.IOException;
import java.util.List;

/* loaded from: classes.dex */
public class ShiftBxImageAdapter extends BaseQuickAdapter<ShiftInfo, BaseViewHolder> {
    private Context context;
    private List<ShiftInfo> datas;
    private String type;

    public ShiftBxImageAdapter(@Nullable List<ShiftInfo> list, Context context, String str) {
        super(R.layout.item_shift_image_layout, list);
        this.context = context;
        this.datas = list;
        this.type = str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    @Nullable
    public ShiftInfo getItem(int i) {
        return (ShiftInfo) super.getItem(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, ShiftInfo shiftInfo) {
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.item_bx_img_shift);
        if (this.type.equals("详情图片")) {
            Log.v("httpl===ppic", shiftInfo.getImg());
            AlbumDisplayUtils.displayStationGoodsImg(this.context, imageView, shiftInfo.getImg());
            return;
        }
        Glide.with(this.context).load(shiftInfo.getImg()).dontAnimate().into(imageView);
    }

    public static Bitmap toturn(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static int readPictureDegree(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt != 3) {
                if (attributeInt != 6) {
                    return attributeInt != 8 ? 0 : 270;
                }
                return 90;
            }
            return 180;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}