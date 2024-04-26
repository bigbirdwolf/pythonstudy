package com.facebook.stetho.inspector.helper;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;

/* loaded from: classes.dex */
public interface PeerRegistrationListener {
    void onPeerRegistered(JsonRpcPeer jsonRpcPeer);

    void onPeerUnregistered(JsonRpcPeer jsonRpcPeer);
}