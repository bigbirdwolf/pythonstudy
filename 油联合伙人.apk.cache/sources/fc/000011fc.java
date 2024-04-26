package com.tbruyelle.rxpermissions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/* loaded from: classes.dex */
public class RxPermissions {
    static final String TAG = "RxPermissions";
    RxPermissionsFragment mRxPermissionsFragment;

    public RxPermissions(@NonNull Activity activity) {
        this.mRxPermissionsFragment = getRxPermissionsFragment(activity);
    }

    private RxPermissionsFragment getRxPermissionsFragment(Activity activity) {
        RxPermissionsFragment findRxPermissionsFragment = findRxPermissionsFragment(activity);
        if (findRxPermissionsFragment == null) {
            RxPermissionsFragment rxPermissionsFragment = new RxPermissionsFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager.beginTransaction().add(rxPermissionsFragment, TAG).commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
            return rxPermissionsFragment;
        }
        return findRxPermissionsFragment;
    }

    private RxPermissionsFragment findRxPermissionsFragment(Activity activity) {
        return (RxPermissionsFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }

    public void setLogging(boolean z) {
        this.mRxPermissionsFragment.setLogging(z);
    }

    public Observable.Transformer<Object, Boolean> ensure(final String... strArr) {
        return new Observable.Transformer<Object, Boolean>() { // from class: com.tbruyelle.rxpermissions.RxPermissions.1
            @Override // rx.functions.Func1
            public Observable<Boolean> call(Observable<Object> observable) {
                return RxPermissions.this.request(observable, strArr).buffer(strArr.length).flatMap(new Func1<List<Permission>, Observable<Boolean>>() { // from class: com.tbruyelle.rxpermissions.RxPermissions.1.1
                    @Override // rx.functions.Func1
                    public Observable<Boolean> call(List<Permission> list) {
                        if (list.isEmpty()) {
                            return Observable.empty();
                        }
                        for (Permission permission : list) {
                            if (!permission.granted) {
                                return Observable.just(false);
                            }
                        }
                        return Observable.just(true);
                    }
                });
            }
        };
    }

    public Observable.Transformer<Object, Permission> ensureEach(final String... strArr) {
        return new Observable.Transformer<Object, Permission>() { // from class: com.tbruyelle.rxpermissions.RxPermissions.2
            @Override // rx.functions.Func1
            public Observable<Permission> call(Observable<Object> observable) {
                return RxPermissions.this.request(observable, strArr);
            }
        };
    }

    public Observable<Boolean> request(String... strArr) {
        return Observable.just(null).compose(ensure(strArr));
    }

    public Observable<Permission> requestEach(String... strArr) {
        return Observable.just(null).compose(ensureEach(strArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<Permission> request(Observable<?> observable, final String... strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("RxPermissions.request/requestEach requires at least one input permission");
        }
        return oneOf(observable, pending(strArr)).flatMap(new Func1<Object, Observable<Permission>>() { // from class: com.tbruyelle.rxpermissions.RxPermissions.3
            @Override // rx.functions.Func1
            public Observable<Permission> call(Object obj) {
                return RxPermissions.this.requestImplementation(strArr);
            }
        });
    }

    private Observable<?> pending(String... strArr) {
        for (String str : strArr) {
            if (!this.mRxPermissionsFragment.containsByPermission(str)) {
                return Observable.empty();
            }
        }
        return Observable.just(null);
    }

    private Observable<?> oneOf(Observable<?> observable, Observable<?> observable2) {
        if (observable == null) {
            return Observable.just(null);
        }
        return Observable.merge(observable, observable2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(23)
    public Observable<Permission> requestImplementation(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        ArrayList arrayList2 = new ArrayList();
        for (String str : strArr) {
            this.mRxPermissionsFragment.log("Requesting permission " + str);
            if (isGranted(str)) {
                arrayList.add(Observable.just(new Permission(str, true, false)));
            } else if (isRevoked(str)) {
                arrayList.add(Observable.just(new Permission(str, false, false)));
            } else {
                PublishSubject<Permission> subjectByPermission = this.mRxPermissionsFragment.getSubjectByPermission(str);
                if (subjectByPermission == null) {
                    arrayList2.add(str);
                    subjectByPermission = PublishSubject.create();
                    this.mRxPermissionsFragment.setSubjectForPermission(str, subjectByPermission);
                }
                arrayList.add(subjectByPermission);
            }
        }
        if (!arrayList2.isEmpty()) {
            requestPermissionsFromFragment((String[]) arrayList2.toArray(new String[arrayList2.size()]));
        }
        return Observable.concat(Observable.from(arrayList));
    }

    public Observable<Boolean> shouldShowRequestPermissionRationale(Activity activity, String... strArr) {
        if (!isMarshmallow()) {
            return Observable.just(false);
        }
        return Observable.just(Boolean.valueOf(shouldShowRequestPermissionRationaleImplementation(activity, strArr)));
    }

    @TargetApi(23)
    private boolean shouldShowRequestPermissionRationaleImplementation(Activity activity, String... strArr) {
        for (String str : strArr) {
            if (!isGranted(str) && !activity.shouldShowRequestPermissionRationale(str)) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    void requestPermissionsFromFragment(String[] strArr) {
        RxPermissionsFragment rxPermissionsFragment = this.mRxPermissionsFragment;
        rxPermissionsFragment.log("requestPermissionsFromFragment " + TextUtils.join(", ", strArr));
        this.mRxPermissionsFragment.requestPermissions(strArr);
    }

    public boolean isGranted(String str) {
        return !isMarshmallow() || this.mRxPermissionsFragment.isGranted(str);
    }

    public boolean isRevoked(String str) {
        return isMarshmallow() && this.mRxPermissionsFragment.isRevoked(str);
    }

    boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= 23;
    }

    void onRequestPermissionsResult(String[] strArr, int[] iArr) {
        this.mRxPermissionsFragment.onRequestPermissionsResult(strArr, iArr, new boolean[strArr.length]);
    }
}