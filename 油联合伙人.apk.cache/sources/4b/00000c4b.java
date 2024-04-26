package com.chad.library.adapter.base;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.AlphaInAnimation;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.chad.library.adapter.base.animation.SlideInBottomAnimation;
import com.chad.library.adapter.base.animation.SlideInLeftAnimation;
import com.chad.library.adapter.base.animation.SlideInRightAnimation;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseQuickAdapter<T, K extends BaseViewHolder> extends RecyclerView.Adapter<K> {
    public static final int ALPHAIN = 1;
    public static final int EMPTY_VIEW = 1365;
    public static final int FOOTER_VIEW = 819;
    public static final int HEADER_VIEW = 273;
    public static final int LOADING_VIEW = 546;
    public static final int SCALEIN = 2;
    public static final int SLIDEIN_BOTTOM = 3;
    public static final int SLIDEIN_LEFT = 4;
    public static final int SLIDEIN_RIGHT = 5;
    protected static final String TAG = "BaseQuickAdapter";
    private boolean footerViewAsFlow;
    private boolean headerViewAsFlow;
    private int mAutoLoadMoreSize;
    protected Context mContext;
    private BaseAnimation mCustomAnimation;
    protected List<T> mData;
    private int mDuration;
    private FrameLayout mEmptyLayout;
    private boolean mFirstOnlyEnable;
    private boolean mFootAndEmptyEnable;
    private LinearLayout mFooterLayout;
    private boolean mHeadAndEmptyEnable;
    private LinearLayout mHeaderLayout;
    private Interpolator mInterpolator;
    private boolean mIsUseEmpty;
    private int mLastPosition;
    protected LayoutInflater mLayoutInflater;
    protected int mLayoutResId;
    private boolean mLoadMoreEnable;
    private LoadMoreView mLoadMoreView;
    private boolean mLoading;
    private MultiTypeDelegate<T> mMultiTypeDelegate;
    private boolean mNextLoadEnable;
    private OnItemChildClickListener mOnItemChildClickListener;
    private OnItemChildLongClickListener mOnItemChildLongClickListener;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private boolean mOpenAnimationEnable;
    private RecyclerView mRecyclerView;
    private RequestLoadMoreListener mRequestLoadMoreListener;
    private BaseAnimation mSelectAnimation;
    private SpanSizeLookup mSpanSizeLookup;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AnimationType {
    }

    /* loaded from: classes.dex */
    public interface OnItemChildClickListener {
        void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    /* loaded from: classes.dex */
    public interface OnItemChildLongClickListener {
        boolean onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    /* loaded from: classes.dex */
    public interface OnItemLongClickListener {
        boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    /* loaded from: classes.dex */
    public interface RequestLoadMoreListener {
        void onLoadMoreRequested();
    }

    /* loaded from: classes.dex */
    public interface SpanSizeLookup {
        int getSpanSize(GridLayoutManager gridLayoutManager, int i);
    }

    protected abstract void convert(K k, T t);

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isFixedViewType(int i) {
        return i == 1365 || i == 273 || i == 819 || i == 546;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v7.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        onBindViewHolder((BaseQuickAdapter<T, K>) ((BaseViewHolder) viewHolder), i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v7.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        onViewAttachedToWindow((BaseQuickAdapter<T, K>) ((BaseViewHolder) viewHolder));
    }

    protected RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    private void checkNotNull() {
        if (getRecyclerView() == null) {
            throw new RuntimeException("please bind recyclerView first!");
        }
    }

    public void bindToRecyclerView(RecyclerView recyclerView) {
        if (getRecyclerView() != null) {
            throw new RuntimeException("Don't bind twice");
        }
        setRecyclerView(recyclerView);
        getRecyclerView().setAdapter(this);
    }

    @Deprecated
    public void setOnLoadMoreListener(RequestLoadMoreListener requestLoadMoreListener) {
        openLoadMore(requestLoadMoreListener);
    }

    private void openLoadMore(RequestLoadMoreListener requestLoadMoreListener) {
        this.mRequestLoadMoreListener = requestLoadMoreListener;
        this.mNextLoadEnable = true;
        this.mLoadMoreEnable = true;
        this.mLoading = false;
    }

    public void setOnLoadMoreListener(RequestLoadMoreListener requestLoadMoreListener, RecyclerView recyclerView) {
        openLoadMore(requestLoadMoreListener);
        if (getRecyclerView() == null) {
            setRecyclerView(recyclerView);
        }
    }

    public void disableLoadMoreIfNotFullPage() {
        checkNotNull();
        disableLoadMoreIfNotFullPage(getRecyclerView());
    }

    public void disableLoadMoreIfNotFullPage(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager;
        setEnableLoadMore(false);
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null) {
            return;
        }
        if (layoutManager instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            recyclerView.postDelayed(new Runnable() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.1
                @Override // java.lang.Runnable
                public void run() {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1 != BaseQuickAdapter.this.getItemCount()) {
                        BaseQuickAdapter.this.setEnableLoadMore(true);
                    }
                }
            }, 50L);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            final StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            recyclerView.postDelayed(new Runnable() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    int[] iArr = new int[staggeredGridLayoutManager.getSpanCount()];
                    staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(iArr);
                    if (BaseQuickAdapter.this.getTheBiggestNumber(iArr) + 1 != BaseQuickAdapter.this.getItemCount()) {
                        BaseQuickAdapter.this.setEnableLoadMore(true);
                    }
                }
            }, 50L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTheBiggestNumber(int[] iArr) {
        int i = -1;
        if (iArr == null || iArr.length == 0) {
            return -1;
        }
        for (int i2 : iArr) {
            if (i2 > i) {
                i = i2;
            }
        }
        return i;
    }

    public void setNotDoAnimationCount(int i) {
        this.mLastPosition = i;
    }

    public void setLoadMoreView(LoadMoreView loadMoreView) {
        this.mLoadMoreView = loadMoreView;
    }

    public int getLoadMoreViewCount() {
        if (this.mRequestLoadMoreListener == null || !this.mLoadMoreEnable) {
            return 0;
        }
        return ((this.mNextLoadEnable || !this.mLoadMoreView.isLoadEndMoreGone()) && this.mData.size() != 0) ? 1 : 0;
    }

    public boolean isLoading() {
        return this.mLoading;
    }

    public void loadMoreEnd() {
        loadMoreEnd(false);
    }

    public void loadMoreEnd(boolean z) {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        this.mLoading = false;
        this.mNextLoadEnable = false;
        this.mLoadMoreView.setLoadMoreEndGone(z);
        if (z) {
            notifyItemRemoved(getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount());
            return;
        }
        this.mLoadMoreView.setLoadMoreStatus(4);
        notifyItemChanged(getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount());
    }

    public void loadMoreComplete() {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        this.mLoading = false;
        this.mLoadMoreView.setLoadMoreStatus(1);
        notifyItemChanged(getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount());
    }

    public void loadMoreFail() {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        this.mLoading = false;
        this.mLoadMoreView.setLoadMoreStatus(3);
        notifyItemChanged(getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount());
    }

    public void setEnableLoadMore(boolean z) {
        int loadMoreViewCount = getLoadMoreViewCount();
        this.mLoadMoreEnable = z;
        int loadMoreViewCount2 = getLoadMoreViewCount();
        if (loadMoreViewCount == 1) {
            if (loadMoreViewCount2 == 0) {
                notifyItemRemoved(getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount());
            }
        } else if (loadMoreViewCount2 == 1) {
            this.mLoadMoreView.setLoadMoreStatus(1);
            notifyItemInserted(getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount());
        }
    }

    public boolean isLoadMoreEnable() {
        return this.mLoadMoreEnable;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public BaseQuickAdapter(@LayoutRes int i, @Nullable List<T> list) {
        this.mNextLoadEnable = false;
        this.mLoadMoreEnable = false;
        this.mLoading = false;
        this.mLoadMoreView = new SimpleLoadMoreView();
        this.mFirstOnlyEnable = true;
        this.mOpenAnimationEnable = false;
        this.mInterpolator = new LinearInterpolator();
        this.mDuration = 300;
        this.mLastPosition = -1;
        this.mSelectAnimation = new AlphaInAnimation();
        this.mIsUseEmpty = true;
        this.mAutoLoadMoreSize = 1;
        this.mData = list == null ? new ArrayList<>() : list;
        if (i != 0) {
            this.mLayoutResId = i;
        }
    }

    public BaseQuickAdapter(@Nullable List<T> list) {
        this(0, list);
    }

    public BaseQuickAdapter(@LayoutRes int i) {
        this(i, null);
    }

    public void setNewData(@Nullable List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.mData = list;
        if (this.mRequestLoadMoreListener != null) {
            this.mNextLoadEnable = true;
            this.mLoadMoreEnable = true;
            this.mLoading = false;
            this.mLoadMoreView.setLoadMoreStatus(1);
        }
        this.mLastPosition = -1;
        notifyDataSetChanged();
    }

    @Deprecated
    public void add(@IntRange(from = 0) int i, @NonNull T t) {
        addData(i, (int) t);
    }

    public void addData(@IntRange(from = 0) int i, @NonNull T t) {
        this.mData.add(i, t);
        notifyItemInserted(i + getHeaderLayoutCount());
        compatibilityDataSizeChanged(1);
    }

    public void addData(@NonNull T t) {
        this.mData.add(t);
        notifyItemInserted(this.mData.size() + getHeaderLayoutCount());
        compatibilityDataSizeChanged(1);
    }

    public void remove(@IntRange(from = 0) int i) {
        this.mData.remove(i);
        int headerLayoutCount = i + getHeaderLayoutCount();
        notifyItemRemoved(headerLayoutCount);
        compatibilityDataSizeChanged(0);
        notifyItemRangeChanged(headerLayoutCount, this.mData.size() - headerLayoutCount);
    }

    public void setData(@IntRange(from = 0) int i, @NonNull T t) {
        this.mData.set(i, t);
        notifyItemChanged(i + getHeaderLayoutCount());
    }

    public void addData(@IntRange(from = 0) int i, @NonNull List<T> list) {
        this.mData.addAll(i, list);
        notifyItemRangeInserted(i + getHeaderLayoutCount(), list.size());
        compatibilityDataSizeChanged(list.size());
    }

    public void addData(@NonNull List<T> list) {
        this.mData.addAll(list);
        notifyItemRangeInserted((this.mData.size() - list.size()) + getHeaderLayoutCount(), list.size());
        compatibilityDataSizeChanged(list.size());
    }

    private void compatibilityDataSizeChanged(int i) {
        if ((this.mData == null ? 0 : this.mData.size()) == i) {
            notifyDataSetChanged();
        }
    }

    @NonNull
    public List<T> getData() {
        return this.mData;
    }

    @Nullable
    public T getItem(@IntRange(from = 0) int i) {
        if (i < this.mData.size()) {
            return this.mData.get(i);
        }
        return null;
    }

    @Deprecated
    public int getHeaderViewsCount() {
        return getHeaderLayoutCount();
    }

    @Deprecated
    public int getFooterViewsCount() {
        return getFooterLayoutCount();
    }

    public int getHeaderLayoutCount() {
        return (this.mHeaderLayout == null || this.mHeaderLayout.getChildCount() == 0) ? 0 : 1;
    }

    public int getFooterLayoutCount() {
        return (this.mFooterLayout == null || this.mFooterLayout.getChildCount() == 0) ? 0 : 1;
    }

    public int getEmptyViewCount() {
        return (this.mEmptyLayout == null || this.mEmptyLayout.getChildCount() == 0 || !this.mIsUseEmpty || this.mData.size() != 0) ? 0 : 1;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 1;
        if (getEmptyViewCount() != 1) {
            return getLoadMoreViewCount() + getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount();
        }
        if (this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
            i = 2;
        }
        return (!this.mFootAndEmptyEnable || getFooterLayoutCount() == 0) ? i : i + 1;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        boolean z = true;
        if (getEmptyViewCount() == 1) {
            z = (!this.mHeadAndEmptyEnable || getHeaderLayoutCount() == 0) ? false : false;
            switch (i) {
                case 0:
                    if (z) {
                        return 273;
                    }
                    return EMPTY_VIEW;
                case 1:
                    return z ? EMPTY_VIEW : FOOTER_VIEW;
                case 2:
                    return FOOTER_VIEW;
                default:
                    return EMPTY_VIEW;
            }
        }
        int headerLayoutCount = getHeaderLayoutCount();
        if (i < headerLayoutCount) {
            return 273;
        }
        int i2 = i - headerLayoutCount;
        int size = this.mData.size();
        if (i2 < size) {
            return getDefItemViewType(i2);
        }
        return i2 - size < getFooterLayoutCount() ? FOOTER_VIEW : LOADING_VIEW;
    }

    protected int getDefItemViewType(int i) {
        if (this.mMultiTypeDelegate != null) {
            return this.mMultiTypeDelegate.getDefItemViewType(this.mData, i);
        }
        return super.getItemViewType(i);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public K onCreateViewHolder(ViewGroup viewGroup, int i) {
        K createBaseViewHolder;
        this.mContext = viewGroup.getContext();
        this.mLayoutInflater = LayoutInflater.from(this.mContext);
        if (i == 273) {
            createBaseViewHolder = createBaseViewHolder(this.mHeaderLayout);
        } else if (i == 546) {
            createBaseViewHolder = getLoadingView(viewGroup);
        } else if (i == 819) {
            createBaseViewHolder = createBaseViewHolder(this.mFooterLayout);
        } else if (i == 1365) {
            createBaseViewHolder = createBaseViewHolder(this.mEmptyLayout);
        } else {
            createBaseViewHolder = onCreateDefViewHolder(viewGroup, i);
            bindViewClickListener(createBaseViewHolder);
        }
        createBaseViewHolder.setAdapter(this);
        return createBaseViewHolder;
    }

    private K getLoadingView(ViewGroup viewGroup) {
        K createBaseViewHolder = createBaseViewHolder(getItemView(this.mLoadMoreView.getLayoutId(), viewGroup));
        createBaseViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BaseQuickAdapter.this.mLoadMoreView.getLoadMoreStatus() == 3) {
                    BaseQuickAdapter.this.mLoadMoreView.setLoadMoreStatus(1);
                    BaseQuickAdapter.this.notifyItemChanged(BaseQuickAdapter.this.getHeaderLayoutCount() + BaseQuickAdapter.this.mData.size() + BaseQuickAdapter.this.getFooterLayoutCount());
                }
            }
        });
        return createBaseViewHolder;
    }

    public void onViewAttachedToWindow(K k) {
        super.onViewAttachedToWindow((BaseQuickAdapter<T, K>) k);
        int itemViewType = k.getItemViewType();
        if (itemViewType == 1365 || itemViewType == 273 || itemViewType == 819 || itemViewType == 546) {
            setFullSpan(k);
        } else {
            addAnimation(k);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFullSpan(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams()).setFullSpan(true);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.4
                @Override // android.support.v7.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i) {
                    int itemViewType = BaseQuickAdapter.this.getItemViewType(i);
                    if (itemViewType == 273 && BaseQuickAdapter.this.isHeaderViewAsFlow()) {
                        return 1;
                    }
                    if (itemViewType == 819 && BaseQuickAdapter.this.isFooterViewAsFlow()) {
                        return 1;
                    }
                    if (BaseQuickAdapter.this.mSpanSizeLookup != null) {
                        return BaseQuickAdapter.this.isFixedViewType(itemViewType) ? gridLayoutManager.getSpanCount() : BaseQuickAdapter.this.mSpanSizeLookup.getSpanSize(gridLayoutManager, i - BaseQuickAdapter.this.getHeaderLayoutCount());
                    } else if (BaseQuickAdapter.this.isFixedViewType(itemViewType)) {
                        return gridLayoutManager.getSpanCount();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }

    public void setHeaderViewAsFlow(boolean z) {
        this.headerViewAsFlow = z;
    }

    public boolean isHeaderViewAsFlow() {
        return this.headerViewAsFlow;
    }

    public void setFooterViewAsFlow(boolean z) {
        this.footerViewAsFlow = z;
    }

    public boolean isFooterViewAsFlow() {
        return this.footerViewAsFlow;
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    public void onBindViewHolder(K k, int i) {
        autoLoadMore(i);
        int itemViewType = k.getItemViewType();
        if (itemViewType == 0) {
            convert(k, this.mData.get(k.getLayoutPosition() - getHeaderLayoutCount()));
        } else if (itemViewType != 273) {
            if (itemViewType == 546) {
                this.mLoadMoreView.convert(k);
            } else if (itemViewType == 819 || itemViewType == 1365) {
            } else {
                convert(k, this.mData.get(k.getLayoutPosition() - getHeaderLayoutCount()));
            }
        }
    }

    private void bindViewClickListener(final BaseViewHolder baseViewHolder) {
        View view;
        if (baseViewHolder == null || (view = baseViewHolder.itemView) == null) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (BaseQuickAdapter.this.getOnItemClickListener() != null) {
                    BaseQuickAdapter.this.getOnItemClickListener().onItemClick(BaseQuickAdapter.this, view2, baseViewHolder.getLayoutPosition() - BaseQuickAdapter.this.getHeaderLayoutCount());
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.6
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view2) {
                return BaseQuickAdapter.this.getOnItemLongClickListener() != null && BaseQuickAdapter.this.getOnItemLongClickListener().onItemLongClick(BaseQuickAdapter.this, view2, baseViewHolder.getLayoutPosition() - BaseQuickAdapter.this.getHeaderLayoutCount());
            }
        });
    }

    public void setMultiTypeDelegate(MultiTypeDelegate<T> multiTypeDelegate) {
        this.mMultiTypeDelegate = multiTypeDelegate;
    }

    public MultiTypeDelegate<T> getMultiTypeDelegate() {
        return this.mMultiTypeDelegate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public K onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        int i2 = this.mLayoutResId;
        if (this.mMultiTypeDelegate != null) {
            i2 = this.mMultiTypeDelegate.getLayoutId(i);
        }
        return createBaseViewHolder(viewGroup, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public K createBaseViewHolder(ViewGroup viewGroup, int i) {
        return createBaseViewHolder(getItemView(i, viewGroup));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public K createBaseViewHolder(View view) {
        Class cls = null;
        for (Class<?> cls2 = getClass(); cls == null && cls2 != null; cls2 = cls2.getSuperclass()) {
            cls = getInstancedGenericKClass(cls2);
        }
        K createGenericKInstance = createGenericKInstance(cls, view);
        return createGenericKInstance != null ? createGenericKInstance : (K) new BaseViewHolder(view);
    }

    private K createGenericKInstance(Class cls, View view) {
        try {
            return (!cls.getName().contains("$") || Modifier.toString(cls.getModifiers()).contains("static")) ? (K) cls.getDeclaredConstructor(View.class).newInstance(view) : (K) cls.getDeclaredConstructor(getClass(), View.class).newInstance(this, view);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    private Class getInstancedGenericKClass(Class cls) {
        Type[] actualTypeArguments;
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            for (Type type : ((ParameterizedType) genericSuperclass).getActualTypeArguments()) {
                if (type instanceof Class) {
                    Class cls2 = (Class) type;
                    if (BaseViewHolder.class.isAssignableFrom(cls2)) {
                        return cls2;
                    }
                }
            }
            return null;
        }
        return null;
    }

    public LinearLayout getHeaderLayout() {
        return this.mHeaderLayout;
    }

    public LinearLayout getFooterLayout() {
        return this.mFooterLayout;
    }

    public int addHeaderView(View view) {
        return addHeaderView(view, -1);
    }

    public int addHeaderView(View view, int i) {
        return addHeaderView(view, i, 1);
    }

    public int addHeaderView(View view, int i, int i2) {
        int headerViewPosition;
        if (this.mHeaderLayout == null) {
            this.mHeaderLayout = new LinearLayout(view.getContext());
            if (i2 == 1) {
                this.mHeaderLayout.setOrientation(1);
                this.mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                this.mHeaderLayout.setOrientation(0);
                this.mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.mHeaderLayout.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        this.mHeaderLayout.addView(view, i);
        if (this.mHeaderLayout.getChildCount() == 1 && (headerViewPosition = getHeaderViewPosition()) != -1) {
            notifyItemInserted(headerViewPosition);
        }
        return i;
    }

    public int setHeaderView(View view) {
        return setHeaderView(view, 0, 1);
    }

    public int setHeaderView(View view, int i) {
        return setHeaderView(view, i, 1);
    }

    public int setHeaderView(View view, int i, int i2) {
        if (this.mHeaderLayout == null || this.mHeaderLayout.getChildCount() <= i) {
            return addHeaderView(view, i, i2);
        }
        this.mHeaderLayout.removeViewAt(i);
        this.mHeaderLayout.addView(view, i);
        return i;
    }

    public int addFooterView(View view) {
        return addFooterView(view, -1, 1);
    }

    public int addFooterView(View view, int i) {
        return addFooterView(view, i, 1);
    }

    public int addFooterView(View view, int i, int i2) {
        int footerViewPosition;
        if (this.mFooterLayout == null) {
            this.mFooterLayout = new LinearLayout(view.getContext());
            if (i2 == 1) {
                this.mFooterLayout.setOrientation(1);
                this.mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                this.mFooterLayout.setOrientation(0);
                this.mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.mFooterLayout.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        this.mFooterLayout.addView(view, i);
        if (this.mFooterLayout.getChildCount() == 1 && (footerViewPosition = getFooterViewPosition()) != -1) {
            notifyItemInserted(footerViewPosition);
        }
        return i;
    }

    public int setFooterView(View view) {
        return setFooterView(view, 0, 1);
    }

    public int setFooterView(View view, int i) {
        return setFooterView(view, i, 1);
    }

    public int setFooterView(View view, int i, int i2) {
        if (this.mFooterLayout == null || this.mFooterLayout.getChildCount() <= i) {
            return addFooterView(view, i, i2);
        }
        this.mFooterLayout.removeViewAt(i);
        this.mFooterLayout.addView(view, i);
        return i;
    }

    public void removeHeaderView(View view) {
        int headerViewPosition;
        if (getHeaderLayoutCount() == 0) {
            return;
        }
        this.mHeaderLayout.removeView(view);
        if (this.mHeaderLayout.getChildCount() != 0 || (headerViewPosition = getHeaderViewPosition()) == -1) {
            return;
        }
        notifyItemRemoved(headerViewPosition);
    }

    public void removeFooterView(View view) {
        int footerViewPosition;
        if (getFooterLayoutCount() == 0) {
            return;
        }
        this.mFooterLayout.removeView(view);
        if (this.mFooterLayout.getChildCount() != 0 || (footerViewPosition = getFooterViewPosition()) == -1) {
            return;
        }
        notifyItemRemoved(footerViewPosition);
    }

    public void removeAllHeaderView() {
        if (getHeaderLayoutCount() == 0) {
            return;
        }
        this.mHeaderLayout.removeAllViews();
        int headerViewPosition = getHeaderViewPosition();
        if (headerViewPosition != -1) {
            notifyItemRemoved(headerViewPosition);
        }
    }

    public void removeAllFooterView() {
        if (getFooterLayoutCount() == 0) {
            return;
        }
        this.mFooterLayout.removeAllViews();
        int footerViewPosition = getFooterViewPosition();
        if (footerViewPosition != -1) {
            notifyItemRemoved(footerViewPosition);
        }
    }

    private int getHeaderViewPosition() {
        return (getEmptyViewCount() != 1 || this.mHeadAndEmptyEnable) ? 0 : -1;
    }

    private int getFooterViewPosition() {
        int i = 1;
        if (getEmptyViewCount() == 1) {
            if (this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
                i = 2;
            }
            if (this.mFootAndEmptyEnable) {
                return i;
            }
            return -1;
        }
        return getHeaderLayoutCount() + this.mData.size();
    }

    public void setEmptyView(int i, ViewGroup viewGroup) {
        setEmptyView(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
    }

    public void setEmptyView(int i) {
        checkNotNull();
        setEmptyView(i, getRecyclerView());
    }

    public void setEmptyView(View view) {
        boolean z;
        int i = 0;
        if (this.mEmptyLayout == null) {
            this.mEmptyLayout = new FrameLayout(view.getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-1, -1);
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams.width = layoutParams2.width;
                layoutParams.height = layoutParams2.height;
            }
            this.mEmptyLayout.setLayoutParams(layoutParams);
            z = true;
        } else {
            z = false;
        }
        this.mEmptyLayout.removeAllViews();
        this.mEmptyLayout.addView(view);
        this.mIsUseEmpty = true;
        if (z && getEmptyViewCount() == 1) {
            if (this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
                i = 1;
            }
            notifyItemInserted(i);
        }
    }

    public void setHeaderAndEmpty(boolean z) {
        setHeaderFooterEmpty(z, false);
    }

    public void setHeaderFooterEmpty(boolean z, boolean z2) {
        this.mHeadAndEmptyEnable = z;
        this.mFootAndEmptyEnable = z2;
    }

    public void isUseEmpty(boolean z) {
        this.mIsUseEmpty = z;
    }

    public View getEmptyView() {
        return this.mEmptyLayout;
    }

    public void setAutoLoadMoreSize(int i) {
        if (i > 1) {
            this.mAutoLoadMoreSize = i;
        }
    }

    private void autoLoadMore(int i) {
        if (getLoadMoreViewCount() != 0 && i >= getItemCount() - this.mAutoLoadMoreSize && this.mLoadMoreView.getLoadMoreStatus() == 1) {
            this.mLoadMoreView.setLoadMoreStatus(2);
            if (this.mLoading) {
                return;
            }
            this.mLoading = true;
            if (getRecyclerView() != null) {
                getRecyclerView().post(new Runnable() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.7
                    @Override // java.lang.Runnable
                    public void run() {
                        BaseQuickAdapter.this.mRequestLoadMoreListener.onLoadMoreRequested();
                    }
                });
            } else {
                this.mRequestLoadMoreListener.onLoadMoreRequested();
            }
        }
    }

    private void addAnimation(RecyclerView.ViewHolder viewHolder) {
        BaseAnimation baseAnimation;
        if (this.mOpenAnimationEnable) {
            if (!this.mFirstOnlyEnable || viewHolder.getLayoutPosition() > this.mLastPosition) {
                if (this.mCustomAnimation != null) {
                    baseAnimation = this.mCustomAnimation;
                } else {
                    baseAnimation = this.mSelectAnimation;
                }
                for (Animator animator : baseAnimation.getAnimators(viewHolder.itemView)) {
                    startAnim(animator, viewHolder.getLayoutPosition());
                }
                this.mLastPosition = viewHolder.getLayoutPosition();
            }
        }
    }

    protected void startAnim(Animator animator, int i) {
        animator.setDuration(this.mDuration).start();
        animator.setInterpolator(this.mInterpolator);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View getItemView(@LayoutRes int i, ViewGroup viewGroup) {
        return this.mLayoutInflater.inflate(i, viewGroup, false);
    }

    public void openLoadAnimation(int i) {
        this.mOpenAnimationEnable = true;
        this.mCustomAnimation = null;
        switch (i) {
            case 1:
                this.mSelectAnimation = new AlphaInAnimation();
                return;
            case 2:
                this.mSelectAnimation = new ScaleInAnimation();
                return;
            case 3:
                this.mSelectAnimation = new SlideInBottomAnimation();
                return;
            case 4:
                this.mSelectAnimation = new SlideInLeftAnimation();
                return;
            case 5:
                this.mSelectAnimation = new SlideInRightAnimation();
                return;
            default:
                return;
        }
    }

    public void openLoadAnimation(BaseAnimation baseAnimation) {
        this.mOpenAnimationEnable = true;
        this.mCustomAnimation = baseAnimation;
    }

    public void openLoadAnimation() {
        this.mOpenAnimationEnable = true;
    }

    public void isFirstOnly(boolean z) {
        this.mFirstOnlyEnable = z;
    }

    public View getViewByPosition(int i, @IdRes int i2) {
        checkNotNull();
        return getViewByPosition(getRecyclerView(), i, i2);
    }

    public View getViewByPosition(RecyclerView recyclerView, int i, @IdRes int i2) {
        BaseViewHolder baseViewHolder;
        if (recyclerView == null || (baseViewHolder = (BaseViewHolder) recyclerView.findViewHolderForLayoutPosition(i)) == null) {
            return null;
        }
        return baseViewHolder.getView(i2);
    }

    private int recursiveExpand(int i, @NonNull List list) {
        int size = (i + list.size()) - 1;
        int size2 = list.size() - 1;
        int i2 = 0;
        while (size2 >= 0) {
            if (list.get(size2) instanceof IExpandable) {
                IExpandable iExpandable = (IExpandable) list.get(size2);
                if (iExpandable.isExpanded() && hasSubItems(iExpandable)) {
                    List<T> subItems = iExpandable.getSubItems();
                    int i3 = size + 1;
                    this.mData.addAll(i3, subItems);
                    i2 += recursiveExpand(i3, subItems);
                }
            }
            size2--;
            size--;
        }
        return i2;
    }

    public int expand(@IntRange(from = 0) int i, boolean z, boolean z2) {
        int headerLayoutCount = i - getHeaderLayoutCount();
        IExpandable expandableItem = getExpandableItem(headerLayoutCount);
        int i2 = 0;
        if (expandableItem == null) {
            return 0;
        }
        if (!hasSubItems(expandableItem)) {
            expandableItem.setExpanded(false);
            return 0;
        }
        if (!expandableItem.isExpanded()) {
            List<T> subItems = expandableItem.getSubItems();
            int i3 = headerLayoutCount + 1;
            this.mData.addAll(i3, subItems);
            expandableItem.setExpanded(true);
            i2 = recursiveExpand(i3, subItems) + 0 + subItems.size();
        }
        int headerLayoutCount2 = headerLayoutCount + getHeaderLayoutCount();
        if (z2) {
            if (z) {
                notifyItemChanged(headerLayoutCount2);
                notifyItemRangeInserted(headerLayoutCount2 + 1, i2);
            } else {
                notifyDataSetChanged();
            }
        }
        return i2;
    }

    public int expand(@IntRange(from = 0) int i, boolean z) {
        return expand(i, z, true);
    }

    public int expand(@IntRange(from = 0) int i) {
        return expand(i, true, true);
    }

    public int expandAll(int i, boolean z, boolean z2) {
        T item;
        int headerLayoutCount = i - getHeaderLayoutCount();
        int i2 = headerLayoutCount + 1;
        T item2 = i2 < this.mData.size() ? getItem(i2) : null;
        IExpandable expandableItem = getExpandableItem(headerLayoutCount);
        if (expandableItem == null || !hasSubItems(expandableItem)) {
            return 0;
        }
        int expand = expand(getHeaderLayoutCount() + headerLayoutCount, false, false);
        while (i2 < this.mData.size() && (item = getItem(i2)) != item2) {
            if (isExpandable(item)) {
                expand += expand(getHeaderLayoutCount() + i2, false, false);
            }
            i2++;
        }
        if (z2) {
            if (z) {
                notifyItemRangeInserted(headerLayoutCount + getHeaderLayoutCount() + 1, expand);
            } else {
                notifyDataSetChanged();
            }
        }
        return expand;
    }

    public int expandAll(int i, boolean z) {
        return expandAll(i, true, !z);
    }

    public void expandAll() {
        for (int size = this.mData.size() - 1; size >= 0; size--) {
            expandAll(size, false, false);
        }
    }

    private int recursiveCollapse(@IntRange(from = 0) int i) {
        T item = getItem(i);
        int i2 = 0;
        if (isExpandable(item)) {
            IExpandable iExpandable = (IExpandable) item;
            if (iExpandable.isExpanded()) {
                List<T> subItems = iExpandable.getSubItems();
                for (int size = subItems.size() - 1; size >= 0; size--) {
                    T t = subItems.get(size);
                    int itemPosition = getItemPosition(t);
                    if (itemPosition >= 0) {
                        if (t instanceof IExpandable) {
                            i2 += recursiveCollapse(itemPosition);
                        }
                        this.mData.remove(itemPosition);
                        i2++;
                    }
                }
            }
            return i2;
        }
        return 0;
    }

    public int collapse(@IntRange(from = 0) int i, boolean z, boolean z2) {
        int headerLayoutCount = i - getHeaderLayoutCount();
        IExpandable expandableItem = getExpandableItem(headerLayoutCount);
        if (expandableItem == null) {
            return 0;
        }
        int recursiveCollapse = recursiveCollapse(headerLayoutCount);
        expandableItem.setExpanded(false);
        int headerLayoutCount2 = headerLayoutCount + getHeaderLayoutCount();
        if (z2) {
            if (z) {
                notifyItemChanged(headerLayoutCount2);
                notifyItemRangeRemoved(headerLayoutCount2 + 1, recursiveCollapse);
            } else {
                notifyDataSetChanged();
            }
        }
        return recursiveCollapse;
    }

    public int collapse(@IntRange(from = 0) int i) {
        return collapse(i, true, true);
    }

    public int collapse(@IntRange(from = 0) int i, boolean z) {
        return collapse(i, z, true);
    }

    private int getItemPosition(T t) {
        if (t == null || this.mData == null || this.mData.isEmpty()) {
            return -1;
        }
        return this.mData.indexOf(t);
    }

    private boolean hasSubItems(IExpandable iExpandable) {
        List<T> subItems;
        return (iExpandable == null || (subItems = iExpandable.getSubItems()) == null || subItems.size() <= 0) ? false : true;
    }

    public boolean isExpandable(T t) {
        return t != null && (t instanceof IExpandable);
    }

    private IExpandable getExpandableItem(int i) {
        T item = getItem(i);
        if (isExpandable(item)) {
            return (IExpandable) item;
        }
        return null;
    }

    public int getParentPosition(@NonNull T t) {
        int itemPosition = getItemPosition(t);
        if (itemPosition == -1) {
            return -1;
        }
        int level = t instanceof IExpandable ? ((IExpandable) t).getLevel() : ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        if (level == 0) {
            return itemPosition;
        }
        if (level == -1) {
            return -1;
        }
        while (itemPosition >= 0) {
            T t2 = this.mData.get(itemPosition);
            if (t2 instanceof IExpandable) {
                IExpandable iExpandable = (IExpandable) t2;
                if (iExpandable.getLevel() >= 0 && iExpandable.getLevel() < level) {
                    return itemPosition;
                }
            }
            itemPosition--;
        }
        return -1;
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        this.mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemChildLongClickListener(OnItemChildLongClickListener onItemChildLongClickListener) {
        this.mOnItemChildLongClickListener = onItemChildLongClickListener;
    }

    public final OnItemLongClickListener getOnItemLongClickListener() {
        return this.mOnItemLongClickListener;
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    @Nullable
    public final OnItemChildClickListener getOnItemChildClickListener() {
        return this.mOnItemChildClickListener;
    }

    @Nullable
    public final OnItemChildLongClickListener getOnItemChildLongClickListener() {
        return this.mOnItemChildLongClickListener;
    }
}