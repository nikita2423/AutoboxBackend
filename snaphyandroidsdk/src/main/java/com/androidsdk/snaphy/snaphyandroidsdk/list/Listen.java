package com.androidsdk.snaphy.snaphyandroidsdk.list;

/**
 * Created by snaphy on 12/9/16.
 */
public abstract class Listen<T> implements DataList.Listeners<T> {

    //On Initialization of the Constructors..
    public void onInit(DataList<T> dataList){}
    // When any Change appears in the list..
    public void onChange(DataList<T> dataList){}
    // On Clearing the list..
    public void onClear(){}
    //On removing of an element from datalist..
    public void onRemove(T element, int index, DataList<T> dataList){

    }
}
