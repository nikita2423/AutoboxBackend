package com.androidsdk.snaphy.snaphyandroidsdk.repository;

/**
 * Created by snaphy on 14/9/16.
 */

//import com.androidsdk.snaphy.snaphyandroidsdk.callbacks.DataListCallback;
//import com.androidsdk.snaphy.snaphyandroidsdk.callbacks.JsonArrayParser;
//import com.androidsdk.snaphy.snaphyandroidsdk.callbacks.JsonObjectParser;
//import com.androidsdk.snaphy.snaphyandroidsdk.callbacks.ObjectCallback;
import com.androidsdk.snaphy.snaphyandroidsdk.models.CustomContainer;
import com.androidsdk.snaphy.snaphyandroidsdk.models.ImageModel;
import com.google.common.collect.ImmutableMap;
import com.strongloop.android.loopback.Container;
import com.strongloop.android.loopback.File;
import com.strongloop.android.loopback.FileRepository;


import com.strongloop.android.loopback.callbacks.JsonArrayParser;
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.strongloop.android.loopback.callbacks.JsonObjectParser;
import com.strongloop.android.loopback.callbacks.ObjectCallback;
import com.strongloop.android.remoting.JsonUtil;
import com.strongloop.android.remoting.adapters.Adapter;
import com.strongloop.android.remoting.adapters.RestContract;
import com.strongloop.android.remoting.adapters.RestContractItem;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Ravi-Gupta on 2/16/2016.
 */
public class CustomFileRepository extends FileRepository {
    private CustomContainer container;
    public CustomFileRepository() {

    }

    public Container getContainer() {
        return container;
    }
    public String getContainerName() {
        return getContainer().getName();
    }

    public void setContainer(CustomContainer value) {
        container = value;
    }



    public RestContract createContract() {
        RestContract contract = super.createContract();
        String basePath = "/containers/:container";
        String className = getClassName();
        contract.addItem(
                RestContractItem.createMultipart(basePath + "/upload", "POST"),
                className + ".upload");
        return contract;
    }

    @Override
    public File createObject(Map<String, ? extends Object> parameters) {
        File file = super.createObject(parameters);
        file.setContainerRef(container);
        return file;
    }



    /**
     * Upload a new file
     * @param localFile The local file to upload.
     * @param callback The callback to be executed when finished.
     */
    public void uploadAmazon(java.io.File localFile, final ObjectCallback<ImageModel> callback) {
        invokeStaticMethod("upload",
                ImmutableMap.of("container", getContainerName(), "file", localFile),
                new CustomUploadResponseParser(this, callback));
    }

    /**
     * Get file by name
     * @param name The name of the file to get.
     * @param callback The callback to be executed when finished.
     */
    public void get(String name, final ObjectCallback<File> callback) {
        final HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("container", getContainerName());
        params.put("name", name);
        invokeStaticMethod("get", params,
                new JsonObjectParser<File>(this, callback));
    }

    /**
     * List all files in the container.
     * @param callback The callback to be executed when finished.
     */
    public void getAll(ListCallback<File> callback) {
        invokeStaticMethod("getAll",
                ImmutableMap.of("container", getContainerName()),
                new JsonArrayParser<File>(this, callback));
    }


    private class CustomUploadResponseParser extends Adapter.JsonObjectCallback {
        private final FileRepository repository;
        private final ObjectCallback<ImageModel> callback;

        private CustomUploadResponseParser(FileRepository repository, ObjectCallback<ImageModel> callback) {
            this.repository = repository;
            this.callback = callback;
        }

        @Override
        public void onSuccess(JSONObject response) {
            try {
                HashMap<String, Object> imageModel = new HashMap<>();
                imageModel = (HashMap)JsonUtil.fromJson(response);
                ImageModel myData = new ImageModel();
                if(imageModel.get("name") != null){
                    myData.setName((String)imageModel.get("name"));
                }

                if(imageModel.get("container") != null){
                    myData.setContainer((String)imageModel.get("container"));
                }

                if(imageModel.get("id") != null){
                    myData.setId((String)imageModel.get("id"));
                }


                if(imageModel.get("url") != null){
                    HashMap<String, Object> objectHashMap = new HashMap<>();
                    objectHashMap = (HashMap<String, Object>)imageModel.get("url");
                    myData.setUrl(objectHashMap);
                }
                callback.onSuccess(myData);
            } catch (Exception e) {
                callback.onError(e);
            }
        }

        @Override
        public void onError(Throwable t) {
            callback.onError(t);
        }
    }





}

