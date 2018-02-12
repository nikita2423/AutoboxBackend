package com.androidsdk.snaphy.snaphyandroidsdk.callbacks;

/**
 * Created by snaphy on 12/9/16.
 */
public abstract class VoidCallback implements VoidCallbackInterface{
    public void onBefore(){}
    public void onSuccess(){}
    public void onError(Throwable t){}
    public void onFinally(){}
}
