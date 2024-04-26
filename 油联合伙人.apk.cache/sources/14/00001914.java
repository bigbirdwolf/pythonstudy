package com.yltx.oil.partner.modules.mine.view;

import com.yltx.oil.partner.data.network.HttpResult;
import com.yltx.oil.partner.data.response.LoginInfo;
import com.yltx.oil.partner.data.response.PhoneResp;
import com.yltx.oil.partner.modules.mine.response.MemberResponse;
import com.yltx.oil.partner.mvp.views.ProgressView;
import java.util.List;

/* loaded from: classes.dex */
public interface MemberView extends ProgressView {
    void onError(Throwable th);

    void onMember(HttpResult<List<MemberResponse>> httpResult);

    void onPersonalCenter(HttpResult<LoginInfo> httpResult);

    void onphone(HttpResult<PhoneResp> httpResult);
}