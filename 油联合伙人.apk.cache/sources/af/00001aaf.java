package com.yltx.oil.partner.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.yltx.oil.partner.data.response.LoginInfo;

/* loaded from: classes.dex */
public class DataCache {
    private static final String IS_LOGIN = "is_login";
    private static String Info = "Info";
    private static final String LOCATION = "location";
    private static final String PASSWORD = "password";
    private static String PartnerInfo = "PartnerInfo";
    private static String SPNAME = "sp_data";
    private static final String Token = "token";
    private static final String USERID = "USERID";
    private static final String USERINFOS = "userinfo";
    private static final String USERNAME = "username";
    private static String User = "User";
    private static String UserInfo = "UserInfoInfo";

    public static void setToken(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SPNAME, 0).edit();
        edit.putString(Token, str);
        edit.apply();
    }

    public static void setUsername(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SPNAME, 0).edit();
        edit.putString(USERNAME, str);
        edit.apply();
    }

    public static void setPassword(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SPNAME, 0).edit();
        edit.putString(PASSWORD, str);
        edit.apply();
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences(SPNAME, 0).getString(Token, "");
    }

    public static void setUserid(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SPNAME, 0).edit();
        edit.putString(USERID, str);
        edit.apply();
    }

    public static String getUserid(Context context) {
        return context.getSharedPreferences(SPNAME, 0).getString(USERID, "");
    }

    public static String getUsername(Context context) {
        return context.getSharedPreferences(SPNAME, 0).getString(USERNAME, "");
    }

    public static String getPassword(Context context) {
        return context.getSharedPreferences(SPNAME, 0).getString(PASSWORD, "");
    }

    public static void setPartnerInfo(Context context, LoginInfo.PartnerInfo partnerInfo) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PartnerInfo, 0).edit();
        edit.putString(Info, new Gson().toJson(partnerInfo));
        edit.apply();
    }

    public static String getPartnerInfo(Context context) {
        return context.getSharedPreferences(PartnerInfo, 0).getString(Info, "");
    }

    public static void setUserToken(Context context, LoginInfo loginInfo) {
        SharedPreferences.Editor edit = context.getSharedPreferences(User, 0).edit();
        edit.putString(UserInfo, new Gson().toJson(loginInfo));
        edit.apply();
    }

    public static String getUserToken(Context context) {
        return context.getSharedPreferences(User, 0).getString(UserInfo, "");
    }
}