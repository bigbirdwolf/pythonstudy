package com.yltx.oil.partner.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yltx.oil.partner.R;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/* loaded from: classes.dex */
public class AlbumDisplayUtils {
    public static final String CDN_BASE_URL = "http://yltx-x.oss-cn-hangzhou.aliyuncs.com/";
    private static int DEFAULT_CIRCLE_AVATAR_DRAWABLE_RESOURCE = 2131492879;
    public static final String SP_BASE_URL = "http://ylsp-x.oss-cn-hangzhou.aliyuncs.com/";

    public static String getRemoteUrl(String str) {
        return "http://yltx-x.oss-cn-hangzhou.aliyuncs.com/".concat(str);
    }

    public static String getspUrl(String str) {
        return SP_BASE_URL.concat(str);
    }

    public static String displayAlbumFromCDN(String str, int i, int i2) {
        String concat = getRemoteUrl(str).concat(String.format("?x-oss-process=image/resize,m_lfit,w_%s,h_%s,limit_0/auto-orient,0/quality,q_80/format,jpg", Integer.valueOf(i), Integer.valueOf(i2)));
        Log.v("httpl===display", concat);
        return concat;
    }

    public static String displayAlbumFromSp(String str, int i, int i2) {
        String concat = getspUrl(str).concat(String.format("?x-oss-process=image/resize,m_lfit,w_%s,h_%s,limit_0/auto-orient,0/quality,q_80/format,jpg", Integer.valueOf(i), Integer.valueOf(i2)));
        Log.v("httpl===display", concat);
        return concat;
    }

    public static void displayHomeGoodsImg(Context context, ImageView imageView, String str) {
        if (str == null) {
            str = "";
        }
        Glide.with(context).load(Uri.parse(displayAlbumFromCDN(str, 360, 240))).crossFade().placeholder((int) R.mipmap.default_goods_home).error((int) R.mipmap.default_goods_home).into(imageView);
    }

    public static void displayGoodsImg(Context context, ImageView imageView, String str) {
        if (str == null) {
            str = "";
        }
        Glide.with(context).load(Uri.parse(displayAlbumFromSp(str, 360, 240))).crossFade().placeholder((int) R.mipmap.default_goods_home).error((int) R.mipmap.default_goods_home).into(imageView);
    }

    public static void displayStorageBgImg(Context context, ImageView imageView, String str) {
        if (str == null) {
            str = "";
        }
        Glide.with(context).load(Uri.parse(displayAlbumFromCDN(str, 1080, 120))).dontAnimate().placeholder((int) R.mipmap.back_xuyou1).error((int) R.mipmap.back_xuyou1).into(imageView);
    }

    public static void displayStorageBigImg(Context context, ImageView imageView, String str) {
        if (str == null) {
            str = "";
        }
        Glide.with(context).load(Uri.parse(displayAlbumFromCDN(str, 1080, 120))).crossFade().dontAnimate().placeholder((int) R.mipmap.back_xuyou1).error((int) R.mipmap.back_xuyou1).into(imageView);
    }

    public static void displayStorageTagImg(Context context, ImageView imageView, String str) {
        if (str == null) {
            str = "";
        }
        Glide.with(context).load(Uri.parse(displayAlbumFromCDN(str, 120, 81))).placeholder((int) R.mipmap.default_goods_home).error((int) R.mipmap.default_goods_home).into(imageView);
    }

    public static void displayStorageCardTagImg(Context context, ImageView imageView, String str) {
        if (str == null) {
            str = "";
        }
        Glide.with(context).load(Uri.parse(displayAlbumFromCDN(str, 810, 600))).dontAnimate().placeholder((int) R.mipmap.default_station).error((int) R.mipmap.default_station).into(imageView);
    }

    public static void displayStationImg(Context context, ImageView imageView, String str) {
        if (str == null) {
            str = "";
        }
        Glide.with(context).load(Uri.parse(displayAlbumFromCDN(str, 1080, 525))).placeholder((int) R.mipmap.default_station).error((int) R.mipmap.default_station).into(imageView);
    }

    public static void displayStationGoodsImg(Context context, ImageView imageView, String str) {
        if (str == null) {
            str = "";
        }
        Glide.with(context).load(Uri.parse(displayAlbumFromCDN(str, 270, 270))).placeholder((int) R.mipmap.default_goods).error((int) R.mipmap.default_goods).into(imageView);
    }

    public static void displayBannerImg(Context context, ImageView imageView, String str) {
        if (str == null) {
            str = "";
        }
        Glide.with(context).load(Uri.parse(displayAlbumFromCDN(str, 1080, 210))).placeholder((int) R.mipmap.banner_home_def).error((int) R.mipmap.banner_home_def).into(imageView);
    }

    public static void displayHeaderImg(Context context, ImageView imageView, String str) {
        if (str == null) {
            str = "";
        }
        Glide.with(context).load(Uri.parse(displayAlbumFromCDN(str, 206, 206))).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).bitmapTransform(new CropCircleTransformation(context)).placeholder(DEFAULT_CIRCLE_AVATAR_DRAWABLE_RESOURCE).error(DEFAULT_CIRCLE_AVATAR_DRAWABLE_RESOURCE).into(imageView);
    }

    public static void displayEditHeaderImg(Context context, ImageView imageView, String str) {
        if (str == null) {
            str = "";
        }
        Glide.with(context).load(Uri.parse(displayAlbumFromCDN(str, 150, 150))).dontAnimate().bitmapTransform(new CropCircleTransformation(context)).placeholder(DEFAULT_CIRCLE_AVATAR_DRAWABLE_RESOURCE).error(DEFAULT_CIRCLE_AVATAR_DRAWABLE_RESOURCE).into(imageView);
    }
}