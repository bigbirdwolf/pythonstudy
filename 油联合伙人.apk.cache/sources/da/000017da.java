package com.yltx.oil.partner.modules.home.view;

import com.yltx.oil.partner.modules.home.response.SeekResponse;
import com.yltx.oil.partner.mvp.views.ProgressView;
import java.util.List;

/* loaded from: classes.dex */
public interface SeekView extends ProgressView {
    void onError(Throwable th);

    void onSeek(List<SeekResponse> list);
}