package com.androidsdk.snaphy.snaphyandroidsdk.callbacks;

/**
 * Created by snaphy on 12/9/16.
 */
public interface VoidCallbackInterface {
    public void onBefore();
    public void onSuccess();
    public void onError(Throwable t);
    public void onFinally();
}
