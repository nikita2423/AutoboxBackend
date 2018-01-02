package com.androidsdk.snaphy.snaphyandroidsdk.socket;

import com.androidsdk.snaphy.snaphyandroidsdk.models.Model;

/**
 * Created by snaphy on 30/1/17.
 */

public abstract class OnData<T extends Model>{
    //On Initialization of the Constructors..
    public abstract void onData(T data);
}
