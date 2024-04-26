package com.yltx.oil.partner.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class SpecialCharactersInputFilter implements InputFilter {
    private Pattern pattern;

    public SpecialCharactersInputFilter() {
        this.pattern = Pattern.compile("^[a-zA-Z0-9\\u4e00-\\u9fa5]+");
    }

    public SpecialCharactersInputFilter(String str) {
        this.pattern = Pattern.compile(str);
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        String charSequence2 = charSequence.toString();
        if (!TextUtils.isEmpty(charSequence) && this.pattern.matcher(charSequence).matches()) {
            return ((Object) spanned.subSequence(i3, i4)) + charSequence2;
        }
        return "";
    }
}