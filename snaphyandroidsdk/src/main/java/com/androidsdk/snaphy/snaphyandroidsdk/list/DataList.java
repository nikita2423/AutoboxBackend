package com.androidsdk.snaphy.snaphyandroidsdk.list;

import android.util.Log;
import com.androidsdk.snaphy.snaphyandroidsdk.models.Model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


/**
 * Created by snaphy on 12/9/16.
 */
public class DataList<T> extends ArrayList<T> {
    //Variable holding all the subscriber object respect to object..
    HashMap<Object, Listen> listenersMap = new HashMap();


    public DataList(int capacity){
        super(capacity);
        publishOnInitialize();
    }

    public DataList(){
        super();
        publishOnInitialize();
    }

    public DataList(Collection<? extends T> collection){
        super(collection);
        publishOnInitialize();
    }



    public void add(int index, T element){
        synchronized (this){
            super.add(index, element);
        }
        publishOnChange();
        addBackRefrences(element);
    }


    /**
     * Add backrefrences to model of snaphy loopback type only.
     * @param element
     */
    private void addBackRefrences(T element){
        //Add references of SnaphyModel to the list..
        try{
            if(element instanceof Model){
                ((Model) element).addListSubscriber((DataList<Model>)this);
            }
        }catch (Exception e){
            Log.d("Snaphy", "Model is not the instance of SnaphyModel type");
        }
    }



    public boolean add(T element){
        boolean returnValue;
        synchronized (this) {
            returnValue = super.add(element);
        }
        publishOnChange();
        addBackRefrences(element);
        return returnValue;
    }



    public boolean addAll(Collection<? extends T> collection){
        boolean returnValue;
        synchronized (this) {
            returnValue = super.addAll(collection);
        }
        publishOnChange();
        return returnValue;
    }


    public boolean addAll(int index, Collection<? extends T> collection){
        boolean returnValue;
        synchronized (this) {
            returnValue = super.addAll(index, collection);
        }
        publishOnChange();
        return returnValue;
    }

    /**
     * Removes all elements from this {@code ArrayList}, leaving it empty.
     *
     * @see #isEmpty
     * @see #size
     */
    public void clear() {
        synchronized (this) {
            super.clear();
        }
        publishOnChange();
        publishOnClear();
    }

    /**
     * Removes the object at the specified location from this list.
     *
     * @param index
     *            the index of the object to remove.
     * @return the removed object.
     * @throws IndexOutOfBoundsException
     *             when {@code location < 0 || location >= size()}
     */
    public T remove(int index) {
        T returnValue;
        synchronized (this) {
            returnValue = super.remove(index);
        }
        publishOnChange();
        publishOnRemove(returnValue, index);
        return returnValue;
    }

    public boolean remove(Object object) {
        boolean returnValue;
        int index = this.indexOf(object);

        synchronized (this) {
            returnValue = super.remove(object);
        }
        publishOnChange();
        publishOnRemove((T)object, index);
        return returnValue;

    }


    /**
     * Replaces the element at the specified location in this {@code ArrayList}
     * with the specified object.
     *
     * @param index
     *            the index at which to put the specified object.
     * @param object
     *            the object to add.
     * @return the previous element at the index.
     * @throws IndexOutOfBoundsException
     *             when {@code location < 0 || location >= size()}
     */
    public T set(int index, T object){
        T returnValue;
        synchronized (this) {
            returnValue = super.set(index, object);
        }
        publishOnChange();
        return returnValue;
    }





    //Variable of all the holders..

    //Implementing the interface for events..
    public interface Listeners<T> {
        //On Initialization of the Constructors..
        public void onInit(DataList<T> dataList);
        // When any Change appears in the list..
        public void onChange(DataList<T> dataList);
        // On Clearing the list..
        public void onClear();
        //On removing of an element from datalist..

        /**
         * onRemove event
         * @param element Element which is removed.
         * @param dataList Collection from which the element is removed.
         */
        public void onRemove(T element, int index, DataList<T> dataList);
    }


    /**
     * Assign the listener implementing events interface that will receive the events
     * @param context Context to which this listener is binded.
     * @param listener Listener object
     */
    public void subscribe(Object context, Listen<T> listener) {
        listenersMap.put(context, listener);
        //Fire the Initialize event here..for first time.
        listener.onInit(this);
    }

    /**
     * Remove all the listener subscribed to the list
     */
    public void unsubscribe(){
        listenersMap.clear();
    }

    /**
     * Remove listeners of the current context only.
     * */
    public void unsubscribe(Object context){
        listenersMap.clear();
    }

    /**
     * Publish all the onChange event subscribed to datalist
     */
    public void publishOnChange(){
        for(Object key : listenersMap.keySet()){
            if(key == null){
                return;
            }
            Listeners<T> listener = listenersMap.get(key);
            //Now publish the onChange event..
            if(listener != null){
                listener.onChange(this);
            }
        }
    }

    /**
     * Publish all the onInitialize event subscribed to datalist
     */
    private void publishOnInitialize(){
        for(Object key : listenersMap.keySet()){
            if(key == null){
                return;
            }
            Listeners<T> listener = listenersMap.get(key);
            //Now publish the onChange event..
            if(listener != null){
                listener.onInit(this);
            }
        }
    }

    /**
     * Publish all the onClear event subscribed to datalist
     */
    private void publishOnClear(){
        for(Object key : listenersMap.keySet()){
            if(key == null){
                return;
            }
            Listeners<T> listener = listenersMap.get(key);
            //Now publish the onChange event..
            if(listener != null){
                listener.onClear();
            }
        }
    }


    /**
     * Publish all the onRemove event subscribed to datalist
     * @param element Remove element
     */
    private void publishOnRemove(T element, int index){
        for(Object key : listenersMap.keySet()){
            if(key == null){
                return;
            }
            Listeners<T> listener = listenersMap.get(key);
            //Now publish the onChange event..
            if(listener != null){
                listener.onRemove(element, index, this);
            }
        }
    }
}
