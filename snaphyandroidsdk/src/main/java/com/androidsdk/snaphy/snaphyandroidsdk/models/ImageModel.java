package com.androidsdk.snaphy.snaphyandroidsdk.models;

/**
 * Created by snaphy on 14/9/16.
 */
import android.util.Log;

import com.androidsdk.snaphy.snaphyandroidsdk.callbacks.VoidCallback;
import com.androidsdk.snaphy.snaphyandroidsdk.repository.ContainerRepository;
import com.strongloop.android.loopback.Model;
import com.strongloop.android.loopback.RestAdapter;


import java.util.HashMap;

public class ImageModel extends Model {
    private String id;

    String TAG = "Snaphy";


    public HashMap<String, Object> getHashMap() {
        return hashMap;
    }

    private HashMap<String, Object> hashMap = new HashMap<>();
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        hashMap.put("id", id);
        this.id = id;
    }

    public HashMap<String, Object> getUrl() {
        return url;
    }

    public void setUrl(HashMap<String, Object> url) {
        hashMap.put("url", url);
        this.url = url;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        hashMap.put("container", container);
        this.container = container;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        hashMap.put("name", name);
        this.name = name;
    }

    private HashMap<String, Object> url;
    private String container;
    private String name;


    public void destroy(RestAdapter restAdapter){
        if(container != null && getName() != null){
            ContainerRepository containerRepository = restAdapter.createRepository(ContainerRepository.class);
            containerRepository.removeFile(container, getName(), new VoidCallback() {
                @Override
                public void onSuccess() {
                    //Now delete file of medium, small, original,
                    Log.i(TAG, "Successfully deleted file to server..");
                }

                @Override
                public void onError(Throwable t) {
                    //Error
                    Log.e(TAG, "Error delete file to server..");
                }
            });
            containerRepository.removeFile(container, "medium_" + getName(), new VoidCallback() {
                @Override
                public void onSuccess() {
                    //Now delete file of medium, small, original,
                }

                @Override
                public void onError(Throwable t) {
                    //Error
                    Log.i(TAG, "Successfully deleted medium file to server..");
                }
            });
            containerRepository.removeFile(container, "small_" + getName(), new VoidCallback() {
                @Override
                public void onSuccess() {
                    //Now delete file of medium, small, original,
                    Log.i(TAG, "Successfully deleted small file to server..");
                }

                @Override
                public void onError(Throwable t) {
                    //Error
                    Log.e(TAG, "Error delete small file from server..");
                }
            });

            containerRepository.removeFile(container, "original_" + getName(), new VoidCallback() {
                @Override
                public void onSuccess() {
                    //Now delete file of medium, small, original,
                    Log.i(TAG, "Successfully deleted original file to server..");
                }

                @Override
                public void onError(Throwable t) {
                    //Error
                    Log.e(TAG, "Error delete original file from server..");
                }
            });
        }
    }
}

