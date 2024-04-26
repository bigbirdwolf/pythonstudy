package com.yltx.oil.partner.modules.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.yltx.oil.partner.R;
import com.yltx.oil.partner.base.Rx;
import com.yltx.oil.partner.base.StateActivity;
import com.yltx.oil.partner.modules.oiltrade.adapter.OiltradeTitleAdapter;
import java.util.ArrayList;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes.dex */
public class ClassificationActivity extends StateActivity {
    private List<Fragment> fragmentList;
    @BindView(R.id.head_left_image)
    ImageView headLeftImage;
    @BindView(R.id.head_title)
    TextView headTitle;
    OiltradeTitleAdapter mAdapter;
    @BindView(R.id.oiltrabe_layout_title)
    TabLayout oiltrabeLayoutTitle;
    @BindView(R.id.vp_oiltrade)
    ViewPager vpOiltrade;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ClassificationActivity.class);
    }

    @Override // com.yltx.oil.partner.base.StateActivity, com.yltx.oil.partner.base.BaseActivity, dagger.android.support.DaggerAppCompatActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_classification);
        ButterKnife.bind(this);
        setupUI();
        bindListener();
    }

    private void bindListener() {
        Rx.click(this.headLeftImage, new Action1() { // from class: com.yltx.oil.partner.modules.home.activity.-$$Lambda$ClassificationActivity$lTXGOCDnJzJ4RqYCu-wZBxXYYuc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ClassificationActivity.lambda$bindListener$0(ClassificationActivity.this, (Void) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$bindListener$0(ClassificationActivity classificationActivity, Void r1) {
        classificationActivity.finish();
    }

    private void setupUI() {
        this.headTitle.setText("分类");
        this.fragmentList = getFragments();
        this.mAdapter = new OiltradeTitleAdapter(getSupportFragmentManager(), this.fragmentList);
        this.vpOiltrade.setAdapter(this.mAdapter);
        this.oiltrabeLayoutTitle.setupWithViewPager(this.vpOiltrade);
        this.vpOiltrade.setOffscreenPageLimit(this.fragmentList.size() - 1);
    }

    private ArrayList<Fragment> getFragments() {
        return new ArrayList<>(4);
    }
}