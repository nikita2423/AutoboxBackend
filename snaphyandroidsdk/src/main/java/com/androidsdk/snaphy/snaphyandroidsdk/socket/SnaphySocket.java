package com.androidsdk.snaphy.snaphyandroidsdk.socket;

import android.util.Log;

import com.androidsdk.snaphy.snaphyandroidsdk.list.Util;
import com.androidsdk.snaphy.snaphyandroidsdk.models.Model;
import com.androidsdk.snaphy.snaphyandroidsdk.repository.ModelRepository;
import com.androidsdk.snaphy.snaphyandroidsdk.callbacks.VoidCallback;

import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by snaphy on 30/1/17.
 */

public class SnaphySocket<M extends Model, R extends ModelRepository<M>> {
    private R dataRepository;
    private HashMap<String, String> where;
    private Socket socket;
    private String room = "";
    private String namespace = "";
    private String TAG = "SnaphySocket";
    //private MainActivity mainActivity;


    public SnaphySocket(R dataRepository, HashMap<String, String> where, String baseUrl){
        //this.mainActivity = mainActivity;
        this.where = where;
        this.dataRepository = dataRepository;
        final Socket mainSocket;
        try {
            mainSocket = IO.socket(new URI(baseUrl));
            getDetails();
            socket = mainSocket.io().socket(namespace);
            socket.connect();
            joinRoom(socket, room);
        } catch (URISyntaxException e) {
            Log.e("SnaphySocket Error", e.toString());
        }
    }



    private void joinRoom(Socket socket, String room){
        socket.emit("create", room);
    }


    private void getDetails(){
        namespace = "/" + dataRepository.getClassName();
        Log.i("SnaphySocket", namespace);
        room = "/";
        // Iterating over keys only
        for (String key : where.keySet()) {
            //System.out.println("Key = " + key);
            namespace =  namespace + "/" + key;
            room = room + where.get(key) + "/";
        }
    }


    //Implementing the interface for events..
    public interface Listeners<M> {
        //On Initialization of the Constructors..
        public void onDataAdded(M data);
        // When any Change appears in the list..
        public void onDataUpdated(M data);
        public void onDataDeleted(M data);
    }



    /**
     * Subscriber fires when a new data is to be added.
     * @param onDataReceived
     * @return {SnaphyRealTime} Return self to maintain chaining.
     */
    public SnaphySocket onDataAdded(final OnData<M> onDataReceived){
        Emitter.Listener onDataAdded = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject data = (JSONObject) args[0];
                Map<String, Object> Data = Util.fromJson(data);
                try{
                    M newData = dataRepository.createObject(Data);
                    Log.i(TAG, "NEW DATA ADDED" + data.toString());
                    onDataReceived.onData(newData);
                }catch (Exception e){
                    Log.e("SnaphySocket", e.toString());
                }
            }
        };


        //On Data added
        socket.on("POST", onDataAdded);
        return this;
    }


    public void leave(){
        //socket.emit('leave', this.room);
        socket.emit("leave", room);
    }



    /**
     * Subscriber fires when a new data is to be Updated.
     * @param onDataReceived
     * @return {SnaphyRealTime} Return self to maintain chaining.
     */
    public SnaphySocket onDataUpdated(final OnData<M> onDataReceived){
        Emitter.Listener onDataUpdated = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject data = (JSONObject) args[0];
                Map<String, Object> Data = Util.fromJson(data);
                try{
                    M updatedData = dataRepository.createObject(Data);
                    Log.i(TAG, "DATA UPDATED" + data.toString());
                    onDataReceived.onData(updatedData);
                }
                catch (Exception e){
                    Log.e("SnaphySocket", e.toString());
                }

            }
        };

        //On Data added
        socket.on("PUT", onDataUpdated);
        return this;
    }



    /**
     * Subscriber fires when a new data is deleted.
     * @param onDataReceived
     * @return {SnaphyRealTime} Return self to maintain chaining.
     */
    public SnaphySocket onDataDeleted(final OnData<M> onDataReceived){
        Emitter.Listener onDataDeleted = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject data = (JSONObject) args[0];
                Map<String, Object> Data = Util.fromJson(data);
                try{
                    M deletedData = (M)dataRepository.createObject(Data);
                    Log.i(TAG, "DATA Deleted" + data.toString());
                    onDataReceived.onData(deletedData);
                }
                catch(Exception e){
                    Log.e("SnaphySocket", e.toString());
                }

            }
        };

        //On Data added
        socket.on("PUT", onDataDeleted);
        return this;
    }


    /**
     * Subscriber fires when socket is disconnected.
     * @param callback
     * @return {SnaphyRealTime} Return self to maintain chaining.
     */
    public SnaphySocket onDisconnected(final VoidCallback callback){
        callback.onBefore();
        Emitter.Listener onDataAdded = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                callback.onSuccess();
                callback.onFinally();
            }
        };


        //On Data added
        socket.on(Socket.EVENT_DISCONNECT, onDataAdded);
        return this;
    }



    /**
     * Listen for real time data change for onNewData added, deleted and updated..
     * @param onDataReceived
     */
    public void subscribe(final Subscribe<M> onDataReceived) {
        Emitter.Listener onDataAdded = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            Map<String, Object> Data = Util.fromJson(data);
            try{
                M newData = (M)dataRepository.createObject(Data);
                Log.i(TAG, "NEW DATA ADDED" + data.toString());
                onDataReceived.onDataAdded(newData);
            }
            catch(Exception e){
                Log.e("SnaphySocket", e.toString());
            }
            }
        };

        //On Data added
        socket.on("POST", onDataAdded);

        /*On Data Updated*/
        Emitter.Listener onDataUpdated = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject data = (JSONObject) args[0];
                Map<String, Object> Data = Util.fromJson(data);
                try{
                    M updatedData = (M)dataRepository.createObject(Data);
                    Log.i(TAG, "DATA UPDATED" + data.toString());
                    onDataReceived.onDataUpdated(updatedData);
                }
                catch(Exception e){
                    Log.e("SnaphySocket", e.toString());
                }

            }
        };
        //On Data added
        socket.on("PUT", onDataUpdated);


        /*On Data DELETED*/
        Emitter.Listener onDataDeleted = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            Map<String, Object> deletedDataObj = Util.fromJson(data);
            try{
                M deletedData = (M)dataRepository.createObject(deletedDataObj);
                Log.i(TAG, "DATA DELETED" + data.toString());
                onDataReceived.onDataDeleted(deletedData);
            }
            catch (Exception e){
                Log.e("SnaphySocket", e.toString());
            }

            }
        };
        //On Data added
        socket.on("DELETE", onDataDeleted);
    }
}