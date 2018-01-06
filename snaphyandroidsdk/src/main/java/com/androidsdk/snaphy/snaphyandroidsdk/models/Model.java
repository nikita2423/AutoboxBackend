package com.androidsdk.snaphy.snaphyandroidsdk.models;

/**
 * Created by snaphy on 12/9/16.
 */

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.androidsdk.snaphy.snaphyandroidsdk.list.DataList;
import com.strongloop.android.loopback.callbacks.VoidCallback;
import com.strongloop.android.remoting.Repository;
import com.strongloop.android.remoting.VirtualObject;
import com.strongloop.android.remoting.adapters.Adapter;

/**
 * A local representative of a single model instance on the server. The data is
 * immediately accessible locally, but can be saved, destroyed, etc. from the
 * server easily.
 */
public class Model extends VirtualObject {

    /**
     * @deprecated Use {link VoidCallback} instead.
     */
    public static interface Callback extends VoidCallback {
    }

    private Object id;
    private Map<String, Object> overflow = new HashMap<String, Object>();

    public Model(Repository repository,
                 Map<String, ? extends Object> creationParameters) {
        super(repository, creationParameters);
    }

    public Model() {
        this(null, null);
    }

    /**
     * Gets the model's id field.
     * @return The id.
     */
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    /**
     * Gets the value associated with a given key.
     * @param key The key for which to return the corresponding value.
     * @return The value associated with the key, or <code>null</code> if no
     * value is associated with the key.
     */
    public Object get(String key) {
        return overflow.get(key);
    }

    /**
     * Adds a given key-value pair to the dictionary.
     *
     * @param key The key for value. If the key already exists
     * in the dictionary, the specified value takes its place.
     * @param value The value for the key. The value may be <code>null</code>.
     */
    public void put(String key, Object value) {
        overflow.put(key, value);
    }

    /**
     * Adds all the specified params to the dictionary.
     * @param params The params to add.
     */
    public void putAll(Map<String, ? extends Object> params) {
        overflow.putAll(params);
    }

    /**
     * Converts the Model (and all of its Java Bean properties) into a
     * {@link java.util.Map}.
     */
    public Map<String, ? extends Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(overflow);
        map.put("id", getId());
        map.putAll(super.toMap());
        return map;
    }

    /**
     * Saves the Model to the server.
     * <p>
     * This method calls {@link #toMap()} to determine which fields should be
     * saved.
     * @param callback The callback to be executed when finished.
     */
    public void save(final VoidCallback callback) {
        invokeMethod(id == null ? "create" : "save", toMap(),
                new Adapter.JsonObjectCallback() {

                    @Override
                    public void onError(Throwable t) {
                        callback.onError(t);
                    }

                    @Override
                    public void onSuccess(JSONObject response) {
                        Object id = response.opt("id");
                        if (id != null) {
                            setId(id);
                        }
                        callback.onSuccess();
                    }
                });
    }





    private class ListReferences{
        public DataList<Model> list;
        public int index;

        public ListReferences(DataList<Model> data, int index){
            this.list = data;
            this.index = index;
        }

        public ListReferences(DataList<Model> data){
            this.list = data;
        }
    }

    //Contains the reference of all the models where
    private transient DataList<ListReferences> dataList = new DataList<>();


    /**
     * Store the reference of the list which holds the refrence of the of model.
     * @param modelList
     */
    public void addListSubscriber(DataList<Model> modelList){
        ListReferences listReferences = new ListReferences(modelList);
        dataList.add(listReferences);
    }

    /**
     * Destroys the Model from the server.
     * @param callback The callback to be executed when finished.
     */

    public void destroy(final VoidCallback callback) {
        //remove all the references of this model from list..
        removeReferences();
        invokeMethod("remove", toMap(), new Adapter.Callback() {

            @Override
            public void onError(Throwable t) {
                //Add the references to the list. again
                addReferences();
                callback.onError(t);
            }

            @Override
            public void onSuccess(String response) {
                callback.onSuccess();
            }
        });
    }

    /**
     * Remove all the refrences of this model from all list..
     */
    private void removeReferences(){
        for(ListReferences listReferences : dataList){
            if(listReferences != null){
                if(listReferences.list != null){
                    if(listReferences.list.size() != 0){
                        //get the index of the
                        int index = listReferences.list.indexOf(this);
                        if(index != -1){
                            listReferences.index = index;
                            //Now remove the model..
                            listReferences.list.remove(this);
                        }else{
                            listReferences.index = index;
                        }
                    }
                }
            }
        }
    }


    /**
     * Add again this model to the list..
     */
    private void addReferences(){
        for(ListReferences listReferences : dataList){
            if(listReferences != null){
                if(listReferences.list != null){
                    //get the index of the
                    int index = listReferences.index;
                    if(index != -1){
                        listReferences.list.add(index, this);
                    }
                }
            }
        }
    }

}

