package com.umeng.socialize.shareboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.umeng.socialize.shareboard.widgets.SocializePagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class SocializeMenuAdapter extends SocializePagerAdapter {
    private Context mContext;
    private ShareBoardMenuHelper mMenuHelper;
    private List<SnsPlatform[][]> mPageData;

    @Override // com.umeng.socialize.shareboard.widgets.SocializePagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public SocializeMenuAdapter(Context context, ShareBoardConfig shareBoardConfig) {
        this(context, shareBoardConfig, null);
    }

    public SocializeMenuAdapter(Context context, ShareBoardConfig shareBoardConfig, List<SnsPlatform> list) {
        this.mPageData = new ArrayList();
        this.mContext = context;
        this.mMenuHelper = new ShareBoardMenuHelper(shareBoardConfig);
        setData(list);
    }

    public void setData(List<SnsPlatform> list) {
        this.mPageData.clear();
        if (list != null) {
            this.mPageData.addAll(this.mMenuHelper.formatPageData(list));
        }
        notifyDataSetChanged();
    }

    @Override // com.umeng.socialize.shareboard.widgets.SocializePagerAdapter
    public int getCount() {
        if (this.mPageData == null) {
            return 0;
        }
        return this.mPageData.size();
    }

    @Override // com.umeng.socialize.shareboard.widgets.SocializePagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View createPageLayout = this.mMenuHelper.createPageLayout(this.mContext, this.mPageData.get(i));
        viewGroup.addView(createPageLayout);
        return createPageLayout;
    }

    @Override // com.umeng.socialize.shareboard.widgets.SocializePagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}