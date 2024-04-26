package com.yltx.oil.partner.modules.mine.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.data.response.InviteDetailResp;
import java.util.List;

/* loaded from: classes.dex */
public class MyExpandableListView extends BaseExpandableListAdapter {
    private Activity context;
    List<InviteDetailResp.ListInfoBeanX> listInfo;

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public MyExpandableListView(Activity activity, List<InviteDetailResp.ListInfoBeanX> list) {
        this.context = activity;
        this.listInfo = list;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.listInfo.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        return this.listInfo.get(i).getListInfo().size();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        return this.listInfo.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        return this.listInfo.get(i).getListInfo().get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.context.getLayoutInflater().inflate(R.layout.list_item_expandablelistview, (ViewGroup) null);
        }
        ((TextView) view.findViewById(R.id.tv_title)).setText(this.listInfo.get(i).getDate());
        ((TextView) view.findViewById(R.id.tv_num)).setText(this.listInfo.get(i).getListInfo().size() + "人");
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.context.getLayoutInflater().inflate(R.layout.list_item_expandablelistview_child, (ViewGroup) null);
        }
        ((TextView) view.findViewById(R.id.tv_phone)).setText(this.listInfo.get(i).getListInfo().get(i2).getPhone());
        ((TextView) view.findViewById(R.id.tv_time)).setText(this.listInfo.get(i).getListInfo().get(i2).getCreateTime());
        ((TextView) view.findViewById(R.id.tv_state)).setText(this.listInfo.get(i).getListInfo().get(i2).getType());
        return view;
    }
}