package com.androidsdk.snaphy.snaphyandroidsdk.callbacks;

//import com.strongloop.android.remoting.VirtualObject;

/**
 * Created by snaphy on 12/9/16.
 */
public abstract class ObjectCallback<T> implements ObjectCallbackInterface<T> {
    public void onBefore(){}
    public void onSuccess(T object){}
    public void onError(Throwable t){}
    public void onFinally(){}
}
