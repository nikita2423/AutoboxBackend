package com.androidsdk.snaphy.snaphyandroidsdk.callbacks;

/**
 * Created by snaphy on 14/9/16.
 */
import com.androidsdk.snaphy.snaphyandroidsdk.list.DataList;
import com.strongloop.android.remoting.JsonUtil;
import com.strongloop.android.remoting.Repository;
import com.strongloop.android.remoting.VirtualObject;
import com.strongloop.android.remoting.adapters.Adapter;

import org.json.JSONArray;


public class JsonArrayParser<T extends VirtualObject> extends Adapter.JsonArrayCallback {
    private final Repository<T> repository;
    private final DataListCallback<T> callback;

    public JsonArrayParser(Repository<T> repository, DataListCallback<T> callback) {
        this.repository = repository;
        this.callback = callback;
    }

    @Override
    public void onSuccess(JSONArray response) {
        DataList<T> list = new DataList<T>();
        if (response != null) {
            for (int i = 0; i < response.length(); i++) {
                list.add(repository.createObject(JsonUtil.fromJson(
                        response.optJSONObject(i))));
            }
        }
        callback.onSuccess(list);
    }

    @Override
    public void onError(Throwable throwable) {
        callback.onError(throwable);
    }
}
