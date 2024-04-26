package dagger.android;

import android.app.IntentService;

/* loaded from: classes.dex */
public abstract class DaggerIntentService extends IntentService {
    public DaggerIntentService(String str) {
        super(str);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }
}