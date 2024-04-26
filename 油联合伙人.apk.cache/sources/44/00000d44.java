package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.app.Application;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.NodeType;
import com.facebook.stetho.inspector.elements.android.ActivityTracker;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ApplicationDescriptor extends AbstractChainedDescriptor<Application> {
    private final Map<Application, ElementContext> mElementToContextMap = Collections.synchronizedMap(new IdentityHashMap());
    private final ActivityTracker mActivityTracker = ActivityTracker.get();

    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    protected /* bridge */ /* synthetic */ void onGetChildren(Application application, Accumulator accumulator) {
        onGetChildren2(application, (Accumulator<Object>) accumulator);
    }

    private ElementContext getContext(Application application) {
        return this.mElementToContextMap.get(application);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    public void onHook(Application application) {
        ElementContext elementContext = new ElementContext();
        elementContext.hook(application);
        this.mElementToContextMap.put(application, elementContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    public void onUnhook(Application application) {
        this.mElementToContextMap.remove(application).unhook();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
    public NodeType onGetNodeType(Application application) {
        return NodeType.ELEMENT_NODE;
    }

    /* renamed from: onGetChildren  reason: avoid collision after fix types in other method */
    protected void onGetChildren2(Application application, Accumulator<Object> accumulator) {
        List<Activity> activitiesList = getContext(application).getActivitiesList();
        for (int size = activitiesList.size() - 1; size >= 0; size--) {
            accumulator.store(activitiesList.get(size));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ElementContext {
        private Application mElement;
        private final ActivityTracker.Listener mListener = new ActivityTracker.Listener() { // from class: com.facebook.stetho.inspector.elements.android.ApplicationDescriptor.ElementContext.1
            @Override // com.facebook.stetho.inspector.elements.android.ActivityTracker.Listener
            public void onActivityAdded(Activity activity) {
            }

            @Override // com.facebook.stetho.inspector.elements.android.ActivityTracker.Listener
            public void onActivityRemoved(Activity activity) {
            }
        };

        public ElementContext() {
        }

        public void hook(Application application) {
            this.mElement = application;
            ApplicationDescriptor.this.mActivityTracker.registerListener(this.mListener);
        }

        public void unhook() {
            ApplicationDescriptor.this.mActivityTracker.unregisterListener(this.mListener);
            this.mElement = null;
        }

        public List<Activity> getActivitiesList() {
            return ApplicationDescriptor.this.mActivityTracker.getActivitiesView();
        }
    }
}