package retrofit2.adapter.rxjava;

import java.lang.reflect.Type;
import javax.annotation.Nullable;
import retrofit2.Call;
import retrofit2.CallAdapter;
import rx.Observable;
import rx.Scheduler;

/* loaded from: classes.dex */
final class RxJavaCallAdapter<R> implements CallAdapter<R, Object> {
    private final boolean isAsync;
    private final boolean isBody;
    private final boolean isCompletable;
    private final boolean isResult;
    private final boolean isSingle;
    private final Type responseType;
    @Nullable
    private final Scheduler scheduler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RxJavaCallAdapter(Type type, @Nullable Scheduler scheduler, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.responseType = type;
        this.scheduler = scheduler;
        this.isAsync = z;
        this.isResult = z2;
        this.isBody = z3;
        this.isSingle = z4;
        this.isCompletable = z5;
    }

    @Override // retrofit2.CallAdapter
    public Type responseType() {
        return this.responseType;
    }

    @Override // retrofit2.CallAdapter
    public Object adapt(Call<R> call) {
        Observable.OnSubscribe callExecuteOnSubscribe;
        Observable.OnSubscribe bodyOnSubscribe;
        if (this.isAsync) {
            callExecuteOnSubscribe = new CallEnqueueOnSubscribe(call);
        } else {
            callExecuteOnSubscribe = new CallExecuteOnSubscribe(call);
        }
        if (this.isResult) {
            bodyOnSubscribe = new ResultOnSubscribe(callExecuteOnSubscribe);
        } else {
            bodyOnSubscribe = this.isBody ? new BodyOnSubscribe(callExecuteOnSubscribe) : callExecuteOnSubscribe;
        }
        Observable create = Observable.create(bodyOnSubscribe);
        if (this.scheduler != null) {
            create = create.subscribeOn(this.scheduler);
        }
        if (this.isSingle) {
            return create.toSingle();
        }
        return this.isCompletable ? create.toCompletable() : create;
    }
}