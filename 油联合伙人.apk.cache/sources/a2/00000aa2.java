package com.bigkoo.pickerview.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.bigkoo.pickerview.R;
import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.utils.PickerViewAnimateUtil;

/* loaded from: classes.dex */
public class BasePickerView {
    protected View clickView;
    protected ViewGroup contentContainer;
    private Context context;
    private ViewGroup dialogView;
    private boolean dismissing;
    private Animation inAnim;
    private boolean isShowing;
    private Dialog mDialog;
    protected PickerOptions mPickerOptions;
    private OnDismissListener onDismissListener;
    private Animation outAnim;
    private ViewGroup rootView;
    protected int animGravity = 80;
    private boolean isAnim = true;
    private View.OnKeyListener onKeyBackListener = new View.OnKeyListener() { // from class: com.bigkoo.pickerview.view.BasePickerView.4
        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i == 4 && keyEvent.getAction() == 0 && BasePickerView.this.isShowing()) {
                BasePickerView.this.dismiss();
                return true;
            }
            return false;
        }
    };
    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() { // from class: com.bigkoo.pickerview.view.BasePickerView.5
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                BasePickerView.this.dismiss();
                return false;
            }
            return false;
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    public void initEvents() {
    }

    public boolean isDialog() {
        return false;
    }

    public BasePickerView(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initViews() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
        LayoutInflater from = LayoutInflater.from(this.context);
        if (isDialog()) {
            this.dialogView = (ViewGroup) from.inflate(R.layout.layout_basepickerview, (ViewGroup) null, false);
            this.dialogView.setBackgroundColor(0);
            this.contentContainer = (ViewGroup) this.dialogView.findViewById(R.id.content_container);
            layoutParams.leftMargin = 30;
            layoutParams.rightMargin = 30;
            this.contentContainer.setLayoutParams(layoutParams);
            createDialog();
            this.dialogView.setOnClickListener(new View.OnClickListener() { // from class: com.bigkoo.pickerview.view.BasePickerView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BasePickerView.this.dismiss();
                }
            });
        } else {
            if (this.mPickerOptions.decorView == null) {
                this.mPickerOptions.decorView = (ViewGroup) ((Activity) this.context).getWindow().getDecorView();
            }
            this.rootView = (ViewGroup) from.inflate(R.layout.layout_basepickerview, this.mPickerOptions.decorView, false);
            this.rootView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            if (this.mPickerOptions.backgroundId != -1) {
                this.rootView.setBackgroundColor(this.mPickerOptions.backgroundId);
            }
            this.contentContainer = (ViewGroup) this.rootView.findViewById(R.id.content_container);
            this.contentContainer.setLayoutParams(layoutParams);
        }
        setKeyBackCancelable(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initAnim() {
        this.inAnim = getInAnimation();
        this.outAnim = getOutAnimation();
    }

    public void show(View view, boolean z) {
        this.clickView = view;
        this.isAnim = z;
        show();
    }

    public void show(boolean z) {
        this.isAnim = z;
        show();
    }

    public void show(View view) {
        this.clickView = view;
        show();
    }

    public void show() {
        if (isDialog()) {
            showDialog();
        } else if (isShowing()) {
        } else {
            this.isShowing = true;
            onAttached(this.rootView);
            this.rootView.requestFocus();
        }
    }

    private void onAttached(View view) {
        this.mPickerOptions.decorView.addView(view);
        if (this.isAnim) {
            this.contentContainer.startAnimation(this.inAnim);
        }
    }

    public boolean isShowing() {
        if (isDialog()) {
            return false;
        }
        return this.rootView.getParent() != null || this.isShowing;
    }

    public void dismiss() {
        if (isDialog()) {
            dismissDialog();
        } else if (this.dismissing) {
        } else {
            if (this.isAnim) {
                this.outAnim.setAnimationListener(new Animation.AnimationListener() { // from class: com.bigkoo.pickerview.view.BasePickerView.2
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        BasePickerView.this.dismissImmediately();
                    }
                });
                this.contentContainer.startAnimation(this.outAnim);
            } else {
                dismissImmediately();
            }
            this.dismissing = true;
        }
    }

    public void dismissImmediately() {
        this.mPickerOptions.decorView.post(new Runnable() { // from class: com.bigkoo.pickerview.view.BasePickerView.3
            @Override // java.lang.Runnable
            public void run() {
                BasePickerView.this.mPickerOptions.decorView.removeView(BasePickerView.this.rootView);
                BasePickerView.this.isShowing = false;
                BasePickerView.this.dismissing = false;
                if (BasePickerView.this.onDismissListener != null) {
                    BasePickerView.this.onDismissListener.onDismiss(BasePickerView.this);
                }
            }
        });
    }

    public Animation getInAnimation() {
        return AnimationUtils.loadAnimation(this.context, PickerViewAnimateUtil.getAnimationResource(this.animGravity, true));
    }

    public Animation getOutAnimation() {
        return AnimationUtils.loadAnimation(this.context, PickerViewAnimateUtil.getAnimationResource(this.animGravity, false));
    }

    public BasePickerView setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public void setKeyBackCancelable(boolean z) {
        ViewGroup viewGroup;
        if (isDialog()) {
            viewGroup = this.dialogView;
        } else {
            viewGroup = this.rootView;
        }
        viewGroup.setFocusable(z);
        viewGroup.setFocusableInTouchMode(z);
        if (z) {
            viewGroup.setOnKeyListener(this.onKeyBackListener);
        } else {
            viewGroup.setOnKeyListener(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BasePickerView setOutSideCancelable(boolean z) {
        if (this.rootView != null) {
            View findViewById = this.rootView.findViewById(R.id.outmost_container);
            if (z) {
                findViewById.setOnTouchListener(this.onCancelableTouchListener);
            } else {
                findViewById.setOnTouchListener(null);
            }
        }
        return this;
    }

    public void setDialogOutSideCancelable() {
        if (this.mDialog != null) {
            this.mDialog.setCancelable(this.mPickerOptions.cancelable);
        }
    }

    public View findViewById(int i) {
        return this.contentContainer.findViewById(i);
    }

    public void createDialog() {
        if (this.dialogView != null) {
            this.mDialog = new Dialog(this.context, R.style.custom_dialog2);
            this.mDialog.setCancelable(this.mPickerOptions.cancelable);
            this.mDialog.setContentView(this.dialogView);
            Window window = this.mDialog.getWindow();
            if (window != null) {
                window.setWindowAnimations(R.style.pickerview_dialogAnim);
                window.setGravity(17);
            }
            this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.bigkoo.pickerview.view.BasePickerView.6
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    if (BasePickerView.this.onDismissListener != null) {
                        BasePickerView.this.onDismissListener.onDismiss(BasePickerView.this);
                    }
                }
            });
        }
    }

    public void showDialog() {
        if (this.mDialog != null) {
            this.mDialog.show();
        }
    }

    public void dismissDialog() {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
    }
}