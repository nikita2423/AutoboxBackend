package com.androidsdk.snaphy.snaphyandroidsdk.callbacks;

/**
 * Created by snaphy on 12/9/16.
 */
//import com.strongloop.android.remoting.VirtualObject;

public interface ObjectCallbackInterface<T> {
    public void onBefore();
    public void onSuccess(T object);
    public void onError(Throwable t);
    public void onFinally();
}
