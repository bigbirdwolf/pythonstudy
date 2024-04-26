package com.bigkoo.pickerview.view;

import android.graphics.Typeface;
import android.view.View;
import com.bigkoo.pickerview.R;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import java.util.List;

/* loaded from: classes.dex */
public class WheelOptions<T> {
    private int dividerColor;
    private WheelView.DividerType dividerType;
    private boolean isRestoreItem;
    private float lineSpacingMultiplier;
    private boolean linkage = true;
    private List<T> mOptions1Items;
    private List<List<T>> mOptions2Items;
    private List<List<List<T>>> mOptions3Items;
    private OnOptionsSelectChangeListener optionsSelectChangeListener;
    private int textColorCenter;
    private int textColorOut;
    private View view;
    private OnItemSelectedListener wheelListener_option1;
    private OnItemSelectedListener wheelListener_option2;
    private WheelView wv_option1;
    private WheelView wv_option2;
    private WheelView wv_option3;

    public View getView() {
        return this.view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public WheelOptions(View view, boolean z) {
        this.isRestoreItem = z;
        this.view = view;
        this.wv_option1 = (WheelView) view.findViewById(R.id.options1);
        this.wv_option2 = (WheelView) view.findViewById(R.id.options2);
        this.wv_option3 = (WheelView) view.findViewById(R.id.options3);
    }

    public void setPicker(List<T> list, List<List<T>> list2, List<List<List<T>>> list3) {
        this.mOptions1Items = list;
        this.mOptions2Items = list2;
        this.mOptions3Items = list3;
        this.wv_option1.setAdapter(new ArrayWheelAdapter(this.mOptions1Items));
        this.wv_option1.setCurrentItem(0);
        if (this.mOptions2Items != null) {
            this.wv_option2.setAdapter(new ArrayWheelAdapter(this.mOptions2Items.get(0)));
        }
        this.wv_option2.setCurrentItem(this.wv_option2.getCurrentItem());
        if (this.mOptions3Items != null) {
            this.wv_option3.setAdapter(new ArrayWheelAdapter(this.mOptions3Items.get(0).get(0)));
        }
        this.wv_option3.setCurrentItem(this.wv_option3.getCurrentItem());
        this.wv_option1.setIsOptions(true);
        this.wv_option2.setIsOptions(true);
        this.wv_option3.setIsOptions(true);
        if (this.mOptions2Items == null) {
            this.wv_option2.setVisibility(8);
        } else {
            this.wv_option2.setVisibility(0);
        }
        if (this.mOptions3Items == null) {
            this.wv_option3.setVisibility(8);
        } else {
            this.wv_option3.setVisibility(0);
        }
        this.wheelListener_option1 = new OnItemSelectedListener() { // from class: com.bigkoo.pickerview.view.WheelOptions.1
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int i) {
                int i2;
                if (WheelOptions.this.mOptions2Items == null) {
                    if (WheelOptions.this.optionsSelectChangeListener != null) {
                        WheelOptions.this.optionsSelectChangeListener.onOptionsSelectChanged(WheelOptions.this.wv_option1.getCurrentItem(), 0, 0);
                        return;
                    }
                    return;
                }
                if (WheelOptions.this.isRestoreItem) {
                    i2 = 0;
                } else {
                    i2 = WheelOptions.this.wv_option2.getCurrentItem();
                    if (i2 >= ((List) WheelOptions.this.mOptions2Items.get(i)).size() - 1) {
                        i2 = ((List) WheelOptions.this.mOptions2Items.get(i)).size() - 1;
                    }
                }
                WheelOptions.this.wv_option2.setAdapter(new ArrayWheelAdapter((List) WheelOptions.this.mOptions2Items.get(i)));
                WheelOptions.this.wv_option2.setCurrentItem(i2);
                if (WheelOptions.this.mOptions3Items != null) {
                    WheelOptions.this.wheelListener_option2.onItemSelected(i2);
                } else if (WheelOptions.this.optionsSelectChangeListener != null) {
                    WheelOptions.this.optionsSelectChangeListener.onOptionsSelectChanged(i, i2, 0);
                }
            }
        };
        this.wheelListener_option2 = new OnItemSelectedListener() { // from class: com.bigkoo.pickerview.view.WheelOptions.2
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int i) {
                int i2 = 0;
                if (WheelOptions.this.mOptions3Items != null) {
                    int currentItem = WheelOptions.this.wv_option1.getCurrentItem();
                    if (currentItem >= WheelOptions.this.mOptions3Items.size() - 1) {
                        currentItem = WheelOptions.this.mOptions3Items.size() - 1;
                    }
                    if (i >= ((List) WheelOptions.this.mOptions2Items.get(currentItem)).size() - 1) {
                        i = ((List) WheelOptions.this.mOptions2Items.get(currentItem)).size() - 1;
                    }
                    if (!WheelOptions.this.isRestoreItem) {
                        i2 = WheelOptions.this.wv_option3.getCurrentItem() >= ((List) ((List) WheelOptions.this.mOptions3Items.get(currentItem)).get(i)).size() + (-1) ? ((List) ((List) WheelOptions.this.mOptions3Items.get(currentItem)).get(i)).size() - 1 : WheelOptions.this.wv_option3.getCurrentItem();
                    }
                    WheelOptions.this.wv_option3.setAdapter(new ArrayWheelAdapter((List) ((List) WheelOptions.this.mOptions3Items.get(WheelOptions.this.wv_option1.getCurrentItem())).get(i)));
                    WheelOptions.this.wv_option3.setCurrentItem(i2);
                    if (WheelOptions.this.optionsSelectChangeListener != null) {
                        WheelOptions.this.optionsSelectChangeListener.onOptionsSelectChanged(WheelOptions.this.wv_option1.getCurrentItem(), i, i2);
                    }
                } else if (WheelOptions.this.optionsSelectChangeListener != null) {
                    WheelOptions.this.optionsSelectChangeListener.onOptionsSelectChanged(WheelOptions.this.wv_option1.getCurrentItem(), i, 0);
                }
            }
        };
        if (list != null && this.linkage) {
            this.wv_option1.setOnItemSelectedListener(this.wheelListener_option1);
        }
        if (list2 != null && this.linkage) {
            this.wv_option2.setOnItemSelectedListener(this.wheelListener_option2);
        }
        if (list3 == null || !this.linkage || this.optionsSelectChangeListener == null) {
            return;
        }
        this.wv_option3.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.bigkoo.pickerview.view.WheelOptions.3
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int i) {
                WheelOptions.this.optionsSelectChangeListener.onOptionsSelectChanged(WheelOptions.this.wv_option1.getCurrentItem(), WheelOptions.this.wv_option2.getCurrentItem(), i);
            }
        });
    }

    public void setNPicker(List<T> list, List<T> list2, List<T> list3) {
        this.wv_option1.setAdapter(new ArrayWheelAdapter(list));
        this.wv_option1.setCurrentItem(0);
        if (list2 != null) {
            this.wv_option2.setAdapter(new ArrayWheelAdapter(list2));
        }
        this.wv_option2.setCurrentItem(this.wv_option2.getCurrentItem());
        if (list3 != null) {
            this.wv_option3.setAdapter(new ArrayWheelAdapter(list3));
        }
        this.wv_option3.setCurrentItem(this.wv_option3.getCurrentItem());
        this.wv_option1.setIsOptions(true);
        this.wv_option2.setIsOptions(true);
        this.wv_option3.setIsOptions(true);
        if (this.optionsSelectChangeListener != null) {
            this.wv_option1.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.bigkoo.pickerview.view.WheelOptions.4
                @Override // com.contrarywind.listener.OnItemSelectedListener
                public void onItemSelected(int i) {
                    WheelOptions.this.optionsSelectChangeListener.onOptionsSelectChanged(i, WheelOptions.this.wv_option2.getCurrentItem(), WheelOptions.this.wv_option3.getCurrentItem());
                }
            });
        }
        if (list2 == null) {
            this.wv_option2.setVisibility(8);
        } else {
            this.wv_option2.setVisibility(0);
            if (this.optionsSelectChangeListener != null) {
                this.wv_option2.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.bigkoo.pickerview.view.WheelOptions.5
                    @Override // com.contrarywind.listener.OnItemSelectedListener
                    public void onItemSelected(int i) {
                        WheelOptions.this.optionsSelectChangeListener.onOptionsSelectChanged(WheelOptions.this.wv_option1.getCurrentItem(), i, WheelOptions.this.wv_option3.getCurrentItem());
                    }
                });
            }
        }
        if (list3 == null) {
            this.wv_option3.setVisibility(8);
            return;
        }
        this.wv_option3.setVisibility(0);
        if (this.optionsSelectChangeListener != null) {
            this.wv_option3.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.bigkoo.pickerview.view.WheelOptions.6
                @Override // com.contrarywind.listener.OnItemSelectedListener
                public void onItemSelected(int i) {
                    WheelOptions.this.optionsSelectChangeListener.onOptionsSelectChanged(WheelOptions.this.wv_option1.getCurrentItem(), WheelOptions.this.wv_option2.getCurrentItem(), i);
                }
            });
        }
    }

    public void setTextContentSize(int i) {
        float f = i;
        this.wv_option1.setTextSize(f);
        this.wv_option2.setTextSize(f);
        this.wv_option3.setTextSize(f);
    }

    private void setTextColorOut() {
        this.wv_option1.setTextColorOut(this.textColorOut);
        this.wv_option2.setTextColorOut(this.textColorOut);
        this.wv_option3.setTextColorOut(this.textColorOut);
    }

    private void setTextColorCenter() {
        this.wv_option1.setTextColorCenter(this.textColorCenter);
        this.wv_option2.setTextColorCenter(this.textColorCenter);
        this.wv_option3.setTextColorCenter(this.textColorCenter);
    }

    private void setDividerColor() {
        this.wv_option1.setDividerColor(this.dividerColor);
        this.wv_option2.setDividerColor(this.dividerColor);
        this.wv_option3.setDividerColor(this.dividerColor);
    }

    private void setDividerType() {
        this.wv_option1.setDividerType(this.dividerType);
        this.wv_option2.setDividerType(this.dividerType);
        this.wv_option3.setDividerType(this.dividerType);
    }

    private void setLineSpacingMultiplier() {
        this.wv_option1.setLineSpacingMultiplier(this.lineSpacingMultiplier);
        this.wv_option2.setLineSpacingMultiplier(this.lineSpacingMultiplier);
        this.wv_option3.setLineSpacingMultiplier(this.lineSpacingMultiplier);
    }

    public void setLabels(String str, String str2, String str3) {
        if (str != null) {
            this.wv_option1.setLabel(str);
        }
        if (str2 != null) {
            this.wv_option2.setLabel(str2);
        }
        if (str3 != null) {
            this.wv_option3.setLabel(str3);
        }
    }

    public void setTextXOffset(int i, int i2, int i3) {
        this.wv_option1.setTextXOffset(i);
        this.wv_option2.setTextXOffset(i2);
        this.wv_option3.setTextXOffset(i3);
    }

    public void setCyclic(boolean z) {
        this.wv_option1.setCyclic(z);
        this.wv_option2.setCyclic(z);
        this.wv_option3.setCyclic(z);
    }

    public void setTypeface(Typeface typeface) {
        this.wv_option1.setTypeface(typeface);
        this.wv_option2.setTypeface(typeface);
        this.wv_option3.setTypeface(typeface);
    }

    public void setCyclic(boolean z, boolean z2, boolean z3) {
        this.wv_option1.setCyclic(z);
        this.wv_option2.setCyclic(z2);
        this.wv_option3.setCyclic(z3);
    }

    public int[] getCurrentItems() {
        int[] iArr = new int[3];
        iArr[0] = this.wv_option1.getCurrentItem();
        if (this.mOptions2Items != null && this.mOptions2Items.size() > 0) {
            iArr[1] = this.wv_option2.getCurrentItem() > this.mOptions2Items.get(iArr[0]).size() - 1 ? 0 : this.wv_option2.getCurrentItem();
        } else {
            iArr[1] = this.wv_option2.getCurrentItem();
        }
        if (this.mOptions3Items != null && this.mOptions3Items.size() > 0) {
            iArr[2] = this.wv_option3.getCurrentItem() <= this.mOptions3Items.get(iArr[0]).get(iArr[1]).size() - 1 ? this.wv_option3.getCurrentItem() : 0;
        } else {
            iArr[2] = this.wv_option3.getCurrentItem();
        }
        return iArr;
    }

    public void setCurrentItems(int i, int i2, int i3) {
        if (this.linkage) {
            itemSelected(i, i2, i3);
            return;
        }
        this.wv_option1.setCurrentItem(i);
        this.wv_option2.setCurrentItem(i2);
        this.wv_option3.setCurrentItem(i3);
    }

    private void itemSelected(int i, int i2, int i3) {
        if (this.mOptions1Items != null) {
            this.wv_option1.setCurrentItem(i);
        }
        if (this.mOptions2Items != null) {
            this.wv_option2.setAdapter(new ArrayWheelAdapter(this.mOptions2Items.get(i)));
            this.wv_option2.setCurrentItem(i2);
        }
        if (this.mOptions3Items != null) {
            this.wv_option3.setAdapter(new ArrayWheelAdapter(this.mOptions3Items.get(i).get(i2)));
            this.wv_option3.setCurrentItem(i3);
        }
    }

    public void setLineSpacingMultiplier(float f) {
        this.lineSpacingMultiplier = f;
        setLineSpacingMultiplier();
    }

    public void setDividerColor(int i) {
        this.dividerColor = i;
        setDividerColor();
    }

    public void setDividerType(WheelView.DividerType dividerType) {
        this.dividerType = dividerType;
        setDividerType();
    }

    public void setTextColorCenter(int i) {
        this.textColorCenter = i;
        setTextColorCenter();
    }

    public void setTextColorOut(int i) {
        this.textColorOut = i;
        setTextColorOut();
    }

    public void isCenterLabel(boolean z) {
        this.wv_option1.isCenterLabel(z);
        this.wv_option2.isCenterLabel(z);
        this.wv_option3.isCenterLabel(z);
    }

    public void setOptionsSelectChangeListener(OnOptionsSelectChangeListener onOptionsSelectChangeListener) {
        this.optionsSelectChangeListener = onOptionsSelectChangeListener;
    }

    public void setLinkage(boolean z) {
        this.linkage = z;
    }
}