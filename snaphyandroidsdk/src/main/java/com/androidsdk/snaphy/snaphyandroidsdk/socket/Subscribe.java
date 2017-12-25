package com.androidsdk.snaphy.snaphyandroidsdk.socket;

import com.androidsdk.snaphy.snaphyandroidsdk.models.Model;

/**
 * Created by snaphy on 30/1/17.
 */

public abstract class Subscribe<T extends Model> implements SnaphySocket.Listeners<T>{
    //On Initialization of the Constructors..
    public void onDataAdded(T data){}
    // When any Change appears in the list..
    public void onDataUpdated(T data){}
    // On Clearing the list..
    public void onDataDeleted(T data){}

}
