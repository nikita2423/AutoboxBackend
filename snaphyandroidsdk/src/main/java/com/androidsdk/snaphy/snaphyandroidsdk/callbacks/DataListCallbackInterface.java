package com.androidsdk.snaphy.snaphyandroidsdk.callbacks;

import com.androidsdk.snaphy.snaphyandroidsdk.list.DataList;


/**
 * Created by snaphy on 12/9/16.
 */
public interface DataListCallbackInterface<T> {
    public void onBefore();
    public void onSuccess(DataList<T> objects);
    public void onError(Throwable t);
    public void onFinally();
}


