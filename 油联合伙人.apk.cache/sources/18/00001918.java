package com.yltx.oil.partner.modules.mine.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.modules.mine.response.MyfeedbackResponse;
import com.yltx.oil.partner.mvp.views.ProgressView;
import java.util.List;

/* loaded from: classes.dex */
public interface MyfeedbackView extends ProgressView {
    void onError(Throwable th);

    void onMember(HttpResult<List<MyfeedbackResponse>> httpResult);

    void onfeedbacksubmint(HttpResult<String> httpResult);
}