package com.yltx.oil.partner.base;

import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.ErrorView;

/* loaded from: classes.dex */
public class ErrorViewFactory {
    public static void buildEmptyErrorView(ErrorView errorView, ErrorView.Config config, ErrorView.OnRetryListener onRetryListener) {
        errorView.setOnRetryListener(onRetryListener);
        errorView.setImage(R.mipmap.empty_img);
        if (config != null) {
            errorView.setConfig(config);
        }
    }

    public static void buildNoNetworkErrorView(ErrorView errorView, ErrorView.Config config, ErrorView.OnRetryListener onRetryListener) {
        errorView.setTitle("网络异常");
        errorView.setSubtitle("请检查您的手机是否联网");
        errorView.setImage(R.mipmap.error_image_network_unavailable);
        errorView.setOnRetryListener(onRetryListener);
    }

    public static void buildLoadFailErrorView(ErrorView errorView, ErrorView.Config config, ErrorView.OnRetryListener onRetryListener) {
        errorView.setSubtitle("加载失败");
        errorView.setImage(R.mipmap.error_image_load_faild);
        if (config != null) {
            errorView.setConfig(config);
        }
        errorView.setOnRetryListener(onRetryListener);
    }

    public static void buildServerErrorView(ErrorView errorView, ErrorView.Config config, ErrorView.OnRetryListener onRetryListener) {
        errorView.setSubtitle("服务器异常");
        errorView.setImage(R.mipmap.error_image_server);
        if (config != null) {
            errorView.setConfig(config);
        }
        errorView.setOnRetryListener(onRetryListener);
    }
}