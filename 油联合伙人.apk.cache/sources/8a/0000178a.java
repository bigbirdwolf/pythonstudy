package com.yltx.oil.partner.modules.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.bigkoo.convenientbanner.holder.Holder;
import com.yltx.oil.partner.data.response.BannerResponse;
import com.yltx.oil.partner.utils.AlbumDisplayUtils;

/* loaded from: classes.dex */
public class BannerHolder implements Holder<BannerResponse> {
    private ImageView imageView;

    @Override // com.bigkoo.convenientbanner.holder.Holder
    public View createView(Context context) {
        this.imageView = new ImageView(context);
        this.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return this.imageView;
    }

    @Override // com.bigkoo.convenientbanner.holder.Holder
    public void UpdateUI(Context context, int i, BannerResponse bannerResponse) {
        AlbumDisplayUtils.displayBannerImg(context, this.imageView, bannerResponse.getFileName());
    }
}