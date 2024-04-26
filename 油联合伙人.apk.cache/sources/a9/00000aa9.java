package com.bigkoo.pickerview.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.R;
import com.bigkoo.pickerview.configure.PickerOptions;
import java.util.List;

/* loaded from: classes.dex */
public class OptionsPickerView<T> extends BasePickerView implements View.OnClickListener {
    private static final String TAG_CANCEL = "cancel";
    private static final String TAG_SUBMIT = "submit";
    private WheelOptions wheelOptions;

    public OptionsPickerView(PickerOptions pickerOptions) {
        super(pickerOptions.context);
        this.mPickerOptions = pickerOptions;
        initView(pickerOptions.context);
    }

    private void initView(Context context) {
        setDialogOutSideCancelable();
        initViews();
        initAnim();
        initEvents();
        if (this.mPickerOptions.customListener == null) {
            LayoutInflater.from(context).inflate(this.mPickerOptions.layoutRes, this.contentContainer);
            TextView textView = (TextView) findViewById(R.id.tvTitle);
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rv_topbar);
            Button button = (Button) findViewById(R.id.btnSubmit);
            Button button2 = (Button) findViewById(R.id.btnCancel);
            button.setTag(TAG_SUBMIT);
            button2.setTag("cancel");
            button.setOnClickListener(this);
            button2.setOnClickListener(this);
            button.setText(TextUtils.isEmpty(this.mPickerOptions.textContentConfirm) ? context.getResources().getString(R.string.pickerview_submit) : this.mPickerOptions.textContentConfirm);
            button2.setText(TextUtils.isEmpty(this.mPickerOptions.textContentCancel) ? context.getResources().getString(R.string.pickerview_cancel) : this.mPickerOptions.textContentCancel);
            textView.setText(TextUtils.isEmpty(this.mPickerOptions.textContentTitle) ? "" : this.mPickerOptions.textContentTitle);
            button.setTextColor(this.mPickerOptions.textColorConfirm);
            button2.setTextColor(this.mPickerOptions.textColorCancel);
            textView.setTextColor(this.mPickerOptions.textColorTitle);
            relativeLayout.setBackgroundColor(this.mPickerOptions.bgColorTitle);
            button.setTextSize(this.mPickerOptions.textSizeSubmitCancel);
            button2.setTextSize(this.mPickerOptions.textSizeSubmitCancel);
            textView.setTextSize(this.mPickerOptions.textSizeTitle);
        } else {
            this.mPickerOptions.customListener.customLayout(LayoutInflater.from(context).inflate(this.mPickerOptions.layoutRes, this.contentContainer));
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.optionspicker);
        linearLayout.setBackgroundColor(this.mPickerOptions.bgColorWheel);
        this.wheelOptions = new WheelOptions(linearLayout, this.mPickerOptions.isRestoreItem);
        if (this.mPickerOptions.optionsSelectChangeListener != null) {
            this.wheelOptions.setOptionsSelectChangeListener(this.mPickerOptions.optionsSelectChangeListener);
        }
        this.wheelOptions.setTextContentSize(this.mPickerOptions.textSizeContent);
        this.wheelOptions.setLabels(this.mPickerOptions.label1, this.mPickerOptions.label2, this.mPickerOptions.label3);
        this.wheelOptions.setTextXOffset(this.mPickerOptions.x_offset_one, this.mPickerOptions.x_offset_two, this.mPickerOptions.x_offset_three);
        this.wheelOptions.setCyclic(this.mPickerOptions.cyclic1, this.mPickerOptions.cyclic2, this.mPickerOptions.cyclic3);
        this.wheelOptions.setTypeface(this.mPickerOptions.font);
        setOutSideCancelable(this.mPickerOptions.cancelable);
        this.wheelOptions.setDividerColor(this.mPickerOptions.dividerColor);
        this.wheelOptions.setDividerType(this.mPickerOptions.dividerType);
        this.wheelOptions.setLineSpacingMultiplier(this.mPickerOptions.lineSpacingMultiplier);
        this.wheelOptions.setTextColorOut(this.mPickerOptions.textColorOut);
        this.wheelOptions.setTextColorCenter(this.mPickerOptions.textColorCenter);
        this.wheelOptions.isCenterLabel(this.mPickerOptions.isCenterLabel);
    }

    public void setTitleText(String str) {
        TextView textView = (TextView) findViewById(R.id.tvTitle);
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setSelectOptions(int i) {
        this.mPickerOptions.option1 = i;
        reSetCurrentItems();
    }

    public void setSelectOptions(int i, int i2) {
        this.mPickerOptions.option1 = i;
        this.mPickerOptions.option2 = i2;
        reSetCurrentItems();
    }

    public void setSelectOptions(int i, int i2, int i3) {
        this.mPickerOptions.option1 = i;
        this.mPickerOptions.option2 = i2;
        this.mPickerOptions.option3 = i3;
        reSetCurrentItems();
    }

    private void reSetCurrentItems() {
        if (this.wheelOptions != null) {
            this.wheelOptions.setCurrentItems(this.mPickerOptions.option1, this.mPickerOptions.option2, this.mPickerOptions.option3);
        }
    }

    public void setPicker(List<T> list) {
        setPicker(list, null, null);
    }

    public void setPicker(List<T> list, List<List<T>> list2) {
        setPicker(list, list2, null);
    }

    public void setPicker(List<T> list, List<List<T>> list2, List<List<List<T>>> list3) {
        this.wheelOptions.setPicker(list, list2, list3);
        reSetCurrentItems();
    }

    public void setNPicker(List<T> list, List<T> list2, List<T> list3) {
        this.wheelOptions.setLinkage(false);
        this.wheelOptions.setNPicker(list, list2, list3);
        reSetCurrentItems();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (((String) view.getTag()).equals(TAG_SUBMIT)) {
            returnData();
        }
        dismiss();
    }

    public void returnData() {
        if (this.mPickerOptions.optionsSelectListener != null) {
            int[] currentItems = this.wheelOptions.getCurrentItems();
            this.mPickerOptions.optionsSelectListener.onOptionsSelect(currentItems[0], currentItems[1], currentItems[2], this.clickView);
        }
    }

    @Override // com.bigkoo.pickerview.view.BasePickerView
    public boolean isDialog() {
        return this.mPickerOptions.isDialog;
    }
}