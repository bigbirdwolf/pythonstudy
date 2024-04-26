package com.yltx.oil.partner.modules.profit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.BaseFragment;

/* loaded from: classes.dex */
public class RefundFragment extends BaseFragment {
    Unbinder unbinder;

    private void bindListener() {
    }

    private void getComplain() {
    }

    @Override // com.yltx.oil.partner.base.BaseFragment
    protected int setupContentView() {
        return R.layout.fragment_refund;
    }

    protected void setupUI() {
    }

    public static RefundFragment newInstance() {
        Bundle bundle = new Bundle();
        RefundFragment refundFragment = new RefundFragment();
        refundFragment.setArguments(bundle);
        return refundFragment;
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.unbinder = ButterKnife.bind(this, onCreateView);
        return onCreateView;
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // com.yltx.oil.partner.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }
}