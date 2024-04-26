package com.yltx.oil.partner.data.datasource;

import android.content.Context;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* loaded from: classes.dex */
public class DataSourceFactory {
    private final Context mContext;
    private RestDataSource restDataSource;

    @Inject
    public DataSourceFactory(Context context, RestDataSource restDataSource) {
        if (context == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.mContext = context;
        this.restDataSource = restDataSource;
    }

    public DataSource createRestDataSource() {
        return this.restDataSource;
    }
}