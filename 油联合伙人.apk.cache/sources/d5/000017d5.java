package com.yltx.oil.partner.modules.home.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.Homebuttonconfiguration_Bean;
import com.yltx.oil.partner.mvp.views.ProgressView;
import java.util.List;

/* loaded from: classes.dex */
public interface HomeButtonconfigurationView extends ProgressView {
    void onError(Throwable th);

    void onHomeButtonconfiguration(HttpResult<List<Homebuttonconfiguration_Bean>> httpResult);
}