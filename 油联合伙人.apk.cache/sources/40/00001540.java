package com.yltx.oil.partner.base;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yltx.oil.partner.R;
import java.util.Map;

/* loaded from: classes.dex */
public class ErrorView extends LinearLayout {
    public static final int ALIGNMENT_CENTER = 1;
    public static final int ALIGNMENT_LEFT = 0;
    public static final int ALIGNMENT_RIGHT = 2;
    private ImageView mErrorImageView;
    private OnRetryListener mListener;
    private TextView mRetryButton;
    private TextView mSubtitleTextView;
    private TextView mTitleTextView;

    /* loaded from: classes.dex */
    public interface OnRetryListener {
        void onRetry();
    }

    public ErrorView(Context context) {
        this(context, null);
    }

    public ErrorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.errorViewStyle);
    }

    public ErrorView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ErrorView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet);
        init(attributeSet, i, i2);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.ErrorView, i, i2);
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.error_view_layout, (ViewGroup) this, true);
        setOrientation(1);
        setGravity(17);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayoutTransition(new LayoutTransition());
        }
        this.mErrorImageView = (ImageView) findViewById(R.id.error_image);
        this.mTitleTextView = (TextView) findViewById(R.id.error_title);
        this.mSubtitleTextView = (TextView) findViewById(R.id.error_subtitle);
        this.mRetryButton = (TextView) findViewById(R.id.error_retry);
        try {
            int resourceId = obtainStyledAttributes.getResourceId(0, R.drawable.error_image);
            String string = obtainStyledAttributes.getString(10);
            int color = obtainStyledAttributes.getColor(11, getResources().getColor(R.color.black));
            String string2 = obtainStyledAttributes.getString(7);
            int color2 = obtainStyledAttributes.getColor(9, getResources().getColor(R.color.grey_858585));
            boolean z = obtainStyledAttributes.getBoolean(6, true);
            boolean z2 = obtainStyledAttributes.getBoolean(5, true);
            boolean z3 = obtainStyledAttributes.getBoolean(4, false);
            String string3 = obtainStyledAttributes.getString(2);
            int resourceId2 = obtainStyledAttributes.getResourceId(1, R.drawable.error_view_retry_button_background);
            int color3 = obtainStyledAttributes.getColor(3, getResources().getColor(R.color.red));
            int i3 = obtainStyledAttributes.getInt(8, 1);
            if (resourceId != 0) {
                setImage(resourceId);
            }
            if (string != null) {
                setTitle(string);
            }
            if (string2 != null) {
                setSubtitle(string2);
            }
            if (string3 != null) {
                this.mRetryButton.setText(string3);
            }
            if (!z) {
                this.mTitleTextView.setVisibility(8);
            }
            if (!z2) {
                this.mSubtitleTextView.setVisibility(8);
            }
            if (!z3) {
                this.mRetryButton.setVisibility(8);
            }
            this.mTitleTextView.setTextColor(color);
            this.mSubtitleTextView.setTextColor(color2);
            this.mRetryButton.setTextColor(color3);
            this.mRetryButton.setBackgroundResource(resourceId2);
            setSubtitleAlignment(i3);
            obtainStyledAttributes.recycle();
            this.mRetryButton.setOnClickListener(new View.OnClickListener() { // from class: com.yltx.oil.partner.base.ErrorView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ErrorView.this.mListener != null) {
                        ErrorView.this.mListener.onRetry();
                    }
                }
            });
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void setOnRetryListener(OnRetryListener onRetryListener) {
        this.mListener = onRetryListener;
        showRetryButton(onRetryListener != null);
    }

    public void setError(int i) {
        Map<Integer, String> codesMap = HttpStatusCodes.getCodesMap();
        if (codesMap.containsKey(Integer.valueOf(i))) {
            setSubtitle(i + " " + codesMap.get(Integer.valueOf(i)));
        }
    }

    public void setConfig(Config config) {
        if (config.getImage() != null) {
            Object image = config.getImage();
            if (image instanceof Integer) {
                setImage(((Integer) image).intValue());
            } else if (image instanceof Drawable) {
                setImage((Drawable) image);
            } else if (image instanceof Bitmap) {
                setImage((Bitmap) image);
            }
        }
        if (config.getTitle() != null) {
            setTitle(config.getTitle());
        }
        if (config.getTitleColor() != 0) {
            setTitleColor(config.getTitleColor());
        }
        if (config.getSubtitle() != null) {
            setSubtitle(config.getSubtitle());
        }
        if (config.getSubtitleColor() != 0) {
            setSubtitleColor(config.getSubtitleColor());
        }
        showRetryButton(config.shouldShowRetryButton());
        if (config.getRetryButtonText() != null) {
            setRetryButtonText(config.getRetryButtonText());
        }
        if (config.getRetryButtonTextColor() != 0) {
            setRetryButtonTextColor(config.getRetryButtonTextColor());
        }
    }

    public Config getConfig() {
        return Config.create().image(getImage()).title(getTitle()).titleColor(getTitleColor()).subtitle(getSubtitle()).subtitleColor(getSubtitleColor()).retryVisible(isRetryButtonVisible()).retryText(getRetryButtonText()).retryTextColor(getRetryButtonTextColor()).build();
    }

    public void setImage(@DrawableRes int i) {
        this.mErrorImageView.setImageResource(i);
    }

    public void setImage(Drawable drawable) {
        this.mErrorImageView.setImageDrawable(drawable);
    }

    public void setImage(Bitmap bitmap) {
        this.mErrorImageView.setImageBitmap(bitmap);
    }

    public Drawable getImage() {
        return this.mErrorImageView.getDrawable();
    }

    public void setTitle(String str) {
        this.mTitleTextView.setText(str);
    }

    public void setTitle(@StringRes int i) {
        this.mTitleTextView.setText(i);
    }

    public String getTitle() {
        return this.mTitleTextView.getText().toString();
    }

    public void setTitleColor(@ColorInt int i) {
        this.mTitleTextView.setTextColor(i);
    }

    public int getTitleColor() {
        return this.mTitleTextView.getCurrentTextColor();
    }

    public void setSubtitle(String str) {
        this.mSubtitleTextView.setText(str);
    }

    public void setSubtitle(@StringRes int i) {
        this.mSubtitleTextView.setText(i);
    }

    public String getSubtitle() {
        return this.mSubtitleTextView.getText().toString();
    }

    public void setSubtitleColor(@ColorInt int i) {
        this.mSubtitleTextView.setTextColor(i);
    }

    public int getSubtitleColor() {
        return this.mSubtitleTextView.getCurrentTextColor();
    }

    public void setRetryButtonText(String str) {
        this.mRetryButton.setText(str);
    }

    public void setRetryButtonText(@StringRes int i) {
        this.mRetryButton.setText(i);
    }

    public String getRetryButtonText() {
        return this.mRetryButton.getText().toString();
    }

    public void setRetryButtonTextColor(@ColorInt int i) {
        this.mRetryButton.setTextColor(i);
    }

    public int getRetryButtonTextColor() {
        return this.mRetryButton.getCurrentTextColor();
    }

    public void showTitle(boolean z) {
        this.mTitleTextView.setVisibility(z ? 0 : 8);
    }

    public boolean isTitleVisible() {
        return this.mTitleTextView.getVisibility() == 0;
    }

    public void showSubtitle(boolean z) {
        this.mSubtitleTextView.setVisibility(z ? 0 : 8);
    }

    public boolean isSubtitleVisible() {
        return this.mSubtitleTextView.getVisibility() == 0;
    }

    public void showRetryButton(boolean z) {
        this.mRetryButton.setVisibility(z ? 0 : 8);
    }

    public boolean isRetryButtonVisible() {
        return this.mRetryButton.getVisibility() == 0;
    }

    public void setSubtitleAlignment(int i) {
        if (i == 0) {
            this.mSubtitleTextView.setGravity(3);
        } else if (i == 1) {
            this.mSubtitleTextView.setGravity(1);
        } else {
            this.mSubtitleTextView.setGravity(5);
        }
    }

    public int getSubtitleAlignment() {
        int gravity = this.mSubtitleTextView.getGravity();
        if (gravity == 3) {
            return 0;
        }
        return gravity == 1 ? 1 : 2;
    }

    /* loaded from: classes.dex */
    public static class Config implements Parcelable {
        public static final Parcelable.Creator<Config> CREATOR = new Parcelable.Creator<Config>() { // from class: com.yltx.oil.partner.base.ErrorView.Config.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Config createFromParcel(Parcel parcel) {
                return new Config(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Config[] newArray(int i) {
                return new Config[i];
            }
        };
        private Object mImage;
        private String mRetryButtonText;
        private int mRetryButtonTextColor;
        private boolean mShowRetryButton;
        private String mSubtitle;
        private int mSubtitleColor;
        private String mTitle;
        private int mTitleColor;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public static Builder create() {
            return new Builder();
        }

        protected Config(Parcel parcel) {
            this.mShowRetryButton = true;
            this.mImage = parcel.readValue(getClass().getClassLoader());
            this.mTitle = parcel.readString();
            this.mTitleColor = parcel.readInt();
            this.mSubtitle = parcel.readString();
            this.mSubtitleColor = parcel.readInt();
            this.mShowRetryButton = parcel.readByte() != 0;
            this.mRetryButtonText = parcel.readString();
            this.mRetryButtonTextColor = parcel.readInt();
        }

        private Config() {
            this.mShowRetryButton = true;
        }

        public Object getImage() {
            return this.mImage;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public int getTitleColor() {
            return this.mTitleColor;
        }

        public String getSubtitle() {
            return this.mSubtitle;
        }

        public int getSubtitleColor() {
            return this.mSubtitleColor;
        }

        public boolean shouldShowRetryButton() {
            return this.mShowRetryButton;
        }

        public String getRetryButtonText() {
            return this.mRetryButtonText;
        }

        public int getRetryButtonTextColor() {
            return this.mRetryButtonTextColor;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeValue(this.mImage);
            parcel.writeString(this.mTitle);
            parcel.writeInt(this.mTitleColor);
            parcel.writeString(this.mSubtitle);
            parcel.writeInt(this.mSubtitleColor);
            parcel.writeByte(this.mShowRetryButton ? (byte) 1 : (byte) 0);
            parcel.writeString(this.mRetryButtonText);
            parcel.writeInt(this.mRetryButtonTextColor);
        }

        /* loaded from: classes.dex */
        public static class Builder {
            private Config config;

            private Builder() {
                this.config = new Config();
            }

            public Builder image(int i) {
                this.config.mImage = Integer.valueOf(i);
                return this;
            }

            public Builder image(Drawable drawable) {
                this.config.mImage = drawable;
                return this;
            }

            public Builder image(Bitmap bitmap) {
                this.config.mImage = bitmap;
                return this;
            }

            public Builder title(String str) {
                this.config.mTitle = str;
                return this;
            }

            public Builder titleColor(int i) {
                this.config.mTitleColor = i;
                return this;
            }

            public Builder subtitle(String str) {
                this.config.mSubtitle = str;
                return this;
            }

            public Builder subtitleColor(int i) {
                this.config.mSubtitleColor = i;
                return this;
            }

            public Builder retryVisible(boolean z) {
                this.config.mShowRetryButton = z;
                return this;
            }

            public Builder retryText(String str) {
                this.config.mRetryButtonText = str;
                return this;
            }

            public Builder retryTextColor(int i) {
                this.config.mRetryButtonTextColor = i;
                return this;
            }

            public Config build() {
                return this.config;
            }
        }
    }
}