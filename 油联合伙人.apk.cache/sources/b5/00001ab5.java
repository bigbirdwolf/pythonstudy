package com.yltx.oil.partner.utils;

import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes.dex */
public final class ExifInterfaceCompat {
    public static final int EXIF_DEGREE_FALLBACK_VALUE = -1;
    public static final String TAG = "ExifInterfaceCompat";

    private ExifInterfaceCompat() {
    }

    public static final ExifInterface newInstance(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("filename should not be null");
        }
        return new ExifInterface(str);
    }

    public static final Date getExifDateTime(String str) {
        try {
            String attribute = newInstance(str).getAttribute("DateTime");
            if (TextUtils.isEmpty(attribute)) {
                return null;
            }
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.getDefault());
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return simpleDateFormat.parse(attribute);
            } catch (ParseException e) {
                Log.d(TAG, "onFailed to parse date taken", e);
                return null;
            }
        } catch (IOException e2) {
            Log.e(TAG, "cannot read exif", e2);
            return null;
        }
    }

    public static final long getExifDateTimeInMillis(String str) {
        Date exifDateTime = getExifDateTime(str);
        if (exifDateTime == null) {
            return -1L;
        }
        return exifDateTime.getTime();
    }

    public static final int getExifOrientation(String str) {
        try {
            int attributeInt = newInstance(str).getAttributeInt("Orientation", -1);
            if (attributeInt == -1) {
                return 0;
            }
            if (attributeInt != 3) {
                if (attributeInt != 6) {
                    return attributeInt != 8 ? 0 : 270;
                }
                return 90;
            }
            return 180;
        } catch (IOException e) {
            Log.e(TAG, "cannot read exif", e);
            return -1;
        }
    }
}