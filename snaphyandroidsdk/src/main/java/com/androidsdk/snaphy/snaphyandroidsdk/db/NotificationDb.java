package com.androidsdk.snaphy.snaphyandroidsdk.db;





import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.GsonBuilder;
import android.database.Cursor;
import java.lang.reflect.Method;
import android.util.Log;
import java.util.Map;
import com.androidsdk.snaphy.snaphyandroidsdk.list.DataList;

import com.androidsdk.snaphy.snaphyandroidsdk.models.Notification;
//Import self repository..
import com.androidsdk.snaphy.snaphyandroidsdk.repository.NotificationRepository;
import com.strongloop.android.loopback.RestAdapter;

/**
* Created by snaphy on 1/2/2017.
*/

public class NotificationDb{

    // All Static variables
    RestAdapter restAdapter;

    private String TAG = "snaphy";
    private String KEY_ID = "ID";
    private String KEY_OBJECT = "OBJECT";
    private Context context;
    // Database Name
    private static String DATABASE_NAME;

    // Contacts table name
    private static String TABLE;

  public NotificationDb(Context context, String DATABASE_NAME, RestAdapter restAdapter){
    //super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
    this.restAdapter = restAdapter;
    TABLE = "Notification";
    this.DATABASE_NAME = DATABASE_NAME;
    SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
    DbHandler.getInstance(context, DATABASE_NAME).onCreate(db);
  }


    public void insert__db (final String id, final Notification _modelData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                // Inserting Row
                ContentValues values = getContentValues(_modelData);
                db.insert("`Notification`", null, values);
                //db.close(); // Closing database connection
            }
        }).start();

    }





    public ContentValues getContentValues(Notification _modelData){
      ContentValues values = new ContentValues();
                       
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String alertData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getAlert");
                              if(method.invoke(_modelData) != null){
                                //alertData = _modelData.getAlert().toString();
                                alertData = (String) method.invoke(_modelData);
                                values.put("`alert`", alertData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String badgeData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getBadge");
                              if(method.invoke(_modelData) != null){
                                //badgeData = _modelData.getBadge().toString();
                                badgeData = (String) method.invoke(_modelData);
                                values.put("`badge`", badgeData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String categoryData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getCategory");
                              if(method.invoke(_modelData) != null){
                                //categoryData = _modelData.getCategory().toString();
                                categoryData = (String) method.invoke(_modelData);
                                values.put("`category`", categoryData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String collapseKeyData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getCollapseKey");
                              if(method.invoke(_modelData) != null){
                                //collapseKeyData = _modelData.getCollapseKey().toString();
                                collapseKeyData = (String) method.invoke(_modelData);
                                values.put("`collapseKey`", collapseKeyData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String contentAvailableData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getContentAvailable");
                              if(method.invoke(_modelData) != null){
                                //contentAvailableData = _modelData.getContentAvailable().toString();
                                contentAvailableData = (String) method.invoke(_modelData);
                                values.put("`contentAvailable`", contentAvailableData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String createdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getCreated");
                              if(method.invoke(_modelData) != null){
                                //createdData = _modelData.getCreated().toString();
                                createdData = (String) method.invoke(_modelData);
                                values.put("`created`", createdData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String delayWhileIdleData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getDelayWhileIdle");
                              if(method.invoke(_modelData) != null){
                                //delayWhileIdleData = _modelData.getDelayWhileIdle().toString();
                                delayWhileIdleData = (String) method.invoke(_modelData);
                                values.put("`delayWhileIdle`", delayWhileIdleData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String deviceTokenData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getDeviceToken");
                              if(method.invoke(_modelData) != null){
                                //deviceTokenData = _modelData.getDeviceToken().toString();
                                deviceTokenData = (String) method.invoke(_modelData);
                                values.put("`deviceToken`", deviceTokenData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String deviceTypeData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getDeviceType");
                              if(method.invoke(_modelData) != null){
                                //deviceTypeData = _modelData.getDeviceType().toString();
                                deviceTypeData = (String) method.invoke(_modelData);
                                values.put("`deviceType`", deviceTypeData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String expirationIntervalData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getExpirationInterval");
                              if(method.invoke(_modelData) != null){
                                //expirationIntervalData = _modelData.getExpirationInterval().toString();
                                expirationIntervalData = (String) method.invoke(_modelData);
                                values.put("`expirationInterval`", expirationIntervalData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String expirationTimeData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getExpirationTime");
                              if(method.invoke(_modelData) != null){
                                //expirationTimeData = _modelData.getExpirationTime().toString();
                                expirationTimeData = (String) method.invoke(_modelData);
                                values.put("`expirationTime`", expirationTimeData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String modifiedData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getModified");
                              if(method.invoke(_modelData) != null){
                                //modifiedData = _modelData.getModified().toString();
                                modifiedData = (String) method.invoke(_modelData);
                                values.put("`modified`", modifiedData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String scheduledTimeData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getScheduledTime");
                              if(method.invoke(_modelData) != null){
                                //scheduledTimeData = _modelData.getScheduledTime().toString();
                                scheduledTimeData = (String) method.invoke(_modelData);
                                values.put("`scheduledTime`", scheduledTimeData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String soundData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getSound");
                              if(method.invoke(_modelData) != null){
                                //soundData = _modelData.getSound().toString();
                                soundData = (String) method.invoke(_modelData);
                                values.put("`sound`", soundData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String statusData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getStatus");
                              if(method.invoke(_modelData) != null){
                                //statusData = _modelData.getStatus().toString();
                                statusData = (String) method.invoke(_modelData);
                                values.put("`status`", statusData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                  String urlArgsData = "";
                  if(_modelData.getUrlArgs() != null){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                    Gson gson = gsonBuilder.create();
                    urlArgsData = gson.toJson(_modelData.getUrlArgs(), DataList.class);
                    values.put("`urlArgs`", urlArgsData);
                  }
              
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String idData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getId");
                              if(method.invoke(_modelData) != null){
                                //idData = _modelData.getId().toString();
                                idData = (String) method.invoke(_modelData);
                                values.put("`id`", idData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                  
        
          
        //Add the updated data property value to be 1
        values.put("`_DATA_UPDATED`", 1);
        return values;
    }



    // Getting single c
    public   Notification get__db(String id) {
        if (id != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("Notification", null, "id=?", new String[]{id}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);
                    cursor.close();
                    //db.close(); // Closing database connection
                    if (hashMap != null) {
                        NotificationRepository repo = restAdapter.createRepository(NotificationRepository.class);
                        repo.addStorage(context);
                        return (Notification)repo.createObject(hashMap);
                    } else {
                        return null;
                    }
                }

            } else {
                return null;
            }
        } else {
            return null;
        }

    } //get__db




    // Getting single cont
    public   Notification get__db(String whereKey, String whereKeyValue) {
        if (whereKeyValue != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("`Notification`", null, "`" + whereKey + "` =?", new String[]{whereKeyValue}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);

                    cursor.close();
                    //db.close(); // Closing database connection

                    if (hashMap != null) {
                        NotificationRepository repo = restAdapter.createRepository(NotificationRepository.class);
                        repo.addStorage(context);
                        return (Notification)repo.createObject(hashMap);
                    } else {
                        return null;
                    }
                }

            } else {
                return null;
            }
        } else {
            return null;
        }

    } //get__db




    private HashMap<String, Object> parseCursor(Cursor cursor ){
      HashMap<String, Object> hashMap = new HashMap<>();

                      
                                                            String alertData = "";
                        if(cursor.getString(0) != null){
                          alertData = cursor.getString(0);
                          if(alertData != null){
                            alertData = alertData.toString();
                            hashMap.put("alert", alertData);
                          }
                        }
                                                
                                
                                                            String badgeData = "";
                        if(cursor.getString(1) != null){
                          badgeData = cursor.getString(1);
                          if(badgeData != null){
                            badgeData = badgeData.toString();
                            hashMap.put("badge", badgeData);
                          }
                        }
                                                
                                
                                                            String categoryData = "";
                        if(cursor.getString(2) != null){
                          categoryData = cursor.getString(2);
                          if(categoryData != null){
                            categoryData = categoryData.toString();
                            hashMap.put("category", categoryData);
                          }
                        }
                                                
                                
                                                            String collapseKeyData = "";
                        if(cursor.getString(3) != null){
                          collapseKeyData = cursor.getString(3);
                          if(collapseKeyData != null){
                            collapseKeyData = collapseKeyData.toString();
                            hashMap.put("collapseKey", collapseKeyData);
                          }
                        }
                                                
                                
                                                            String contentAvailableData = "";
                        if(cursor.getString(4) != null){
                          contentAvailableData = cursor.getString(4);
                          if(contentAvailableData != null){
                            contentAvailableData = contentAvailableData.toString();
                            hashMap.put("contentAvailable", contentAvailableData);
                          }
                        }
                                                
                                
                                                            String createdData = "";
                        if(cursor.getString(5) != null){
                          createdData = cursor.getString(5);
                          if(createdData != null){
                            createdData = createdData.toString();
                            hashMap.put("created", createdData);
                          }
                        }
                                                
                                
                                                            String delayWhileIdleData = "";
                        if(cursor.getString(6) != null){
                          delayWhileIdleData = cursor.getString(6);
                          if(delayWhileIdleData != null){
                            delayWhileIdleData = delayWhileIdleData.toString();
                            hashMap.put("delayWhileIdle", delayWhileIdleData);
                          }
                        }
                                                
                                
                                                            String deviceTokenData = "";
                        if(cursor.getString(7) != null){
                          deviceTokenData = cursor.getString(7);
                          if(deviceTokenData != null){
                            deviceTokenData = deviceTokenData.toString();
                            hashMap.put("deviceToken", deviceTokenData);
                          }
                        }
                                                
                                
                                                            String deviceTypeData = "";
                        if(cursor.getString(8) != null){
                          deviceTypeData = cursor.getString(8);
                          if(deviceTypeData != null){
                            deviceTypeData = deviceTypeData.toString();
                            hashMap.put("deviceType", deviceTypeData);
                          }
                        }
                                                
                                
                                                            String expirationIntervalData = "";
                        if(cursor.getString(9) != null){
                          expirationIntervalData = cursor.getString(9);
                          if(expirationIntervalData != null){
                            expirationIntervalData = expirationIntervalData.toString();
                            hashMap.put("expirationInterval", expirationIntervalData);
                          }
                        }
                                                
                                
                                                            String expirationTimeData = "";
                        if(cursor.getString(10) != null){
                          expirationTimeData = cursor.getString(10);
                          if(expirationTimeData != null){
                            expirationTimeData = expirationTimeData.toString();
                            hashMap.put("expirationTime", expirationTimeData);
                          }
                        }
                                                
                                
                                                            String modifiedData = "";
                        if(cursor.getString(11) != null){
                          modifiedData = cursor.getString(11);
                          if(modifiedData != null){
                            modifiedData = modifiedData.toString();
                            hashMap.put("modified", modifiedData);
                          }
                        }
                                                
                                
                                                            String scheduledTimeData = "";
                        if(cursor.getString(12) != null){
                          scheduledTimeData = cursor.getString(12);
                          if(scheduledTimeData != null){
                            scheduledTimeData = scheduledTimeData.toString();
                            hashMap.put("scheduledTime", scheduledTimeData);
                          }
                        }
                                                
                                
                                                            String soundData = "";
                        if(cursor.getString(13) != null){
                          soundData = cursor.getString(13);
                          if(soundData != null){
                            soundData = soundData.toString();
                            hashMap.put("sound", soundData);
                          }
                        }
                                                
                                
                                                            String statusData = "";
                        if(cursor.getString(14) != null){
                          statusData = cursor.getString(14);
                          if(statusData != null){
                            statusData = statusData.toString();
                            hashMap.put("status", statusData);
                          }
                        }
                                                
                                
                                  DataList<Object> urlArgsData = new DataList<>();
                  if(cursor.getString(15) != null){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                    Gson gson = gsonBuilder.create();
                    urlArgsData = gson.fromJson(cursor.getString(15), DataList.class);
                    if(urlArgsData != null){
                      urlArgsData = (DataList<Object>)urlArgsData;
                      hashMap.put("urlArgs", urlArgsData);
                    }
                  }
                            
                                
                                                            String idData = "";
                        if(cursor.getString(16) != null){
                          idData = cursor.getString(16);
                          if(idData != null){
                            idData = idData.toString();
                            hashMap.put("id", idData);
                          }
                        }
                                                
                  //End for loop
         
          

        return hashMap;
    }//parseCursor



    public void upsert__db(String id, Notification model){
        if(count__db(id) != 0){
            update__db(id, model);
        }else{
            insert__db(id, model);
        }
    } //upsert__db



    // Getting All Contacts
    public DataList<Notification>  getAll__db() {
        DataList<Notification> modelList = new DataList<Notification>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `Notification`";

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<Notification>) modelList;
        }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    NotificationRepository repo = restAdapter.createRepository(NotificationRepository.class);
                    repo.addStorage(context);
                    modelList.add((Notification)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<Notification>) modelList;
    }



    public String getWhereQuery(HashMap<String, Object> whereKeyValue){
        //Prepare where key value
        String where = "";
        if(whereKeyValue.size() > 0){
            where = where +  " WHERE ";
        }
        where = where + getWhere(whereKeyValue);
        return where;
    }


     //Now also accept gt and lt values
     public String getWhere(HashMap<String, Object> whereKeyValue){
        String where = "";
        int i=0;
        for(String key : whereKeyValue.keySet()){
            Object o = whereKeyValue.get(key);
            DataList<String> keyValue = getKeyValue(key, o);
            if(keyValue != null){
                if(keyValue.size() != 0){
                    String returnedKey = keyValue.get(0);
                    try{
                        int value = Integer.parseInt(keyValue.get(1));
                        if(i==0){
                            if(returnedKey.equals("gt")){
                                where = where + " `" + key + "` > "+ value + "";
                            }else if(returnedKey.equals("lt")){
                                where = where + " `" + key + "` < "+ value + "";
                            }else{
                                where = where + " `" + key + "` = "+ value + "";
                            }
                        }else{
                            if(returnedKey.equals("gt")){
                                where = where + " AND `" + key + "` > "+ value + "";
                            }else if(returnedKey.equals("lt")){
                                where = where + " AND `" + key + "` < "+ value + "";
                            }else{
                                where = where + " AND `" + key + "` = "+ value + "";
                            }
                        }

                    }catch(Exception e){
                      String value = keyValue.get(1);
                      if(i==0){
                          if(returnedKey.equals("gt")){
                              where = where + " `" + key + "` > '"+ value + "'";
                          }else if(returnedKey.equals("lt")){
                              where = where + " `" + key + "` < '"+ value + "'";
                          }else{
                              where = where + " `" + key + "` = '"+ value + "'";
                          }
                      }else{
                          if(returnedKey.equals("gt")){
                              where = where + " AND `" + key + "` > '"+ value + "'";
                          }else if(returnedKey.equals("lt")){
                              where = where + " AND `" + key + "` < '"+ value + "'";
                          }else{
                              where = where + " AND `" + key + "` = '"+ value + "'";
                          }
                      }

                    }

                    i++;
                }
            }
        }
        return where;
     }




    //first argument is key and second is value
    public DataList<String> getKeyValue(String key, Object keyValue){
        DataList<String> returnVal = new DataList<>();
        try{
            //Converting to nested hashmap
            HashMap<String, Object> value = (HashMap<String, Object>)keyValue;
            for(String key_ : value.keySet()){
                Object o = value.get(key_);
                returnVal.add(key_);
                returnVal.add(o.toString());
                return returnVal;
            }
        }catch(Exception e){
            returnVal.add(key);
            returnVal.add(keyValue.toString());
            return returnVal;
        }
        return null;
    }



    // Getting All Data where
    public DataList<Notification>  getAll__db(HashMap<String, Object> whereKeyValue) {
        return getAll__db(whereKeyValue, null, 0);
    }



    // Getting All Data where and sort column according to date wise..
    public DataList<Notification>  getAll__db(HashMap<String, Object> whereKeyValue, String orderBy, int limit) {
        DataList<Notification> modelList = new DataList<Notification>();
        String whereQuery = getWhereQuery(whereKeyValue);
        String selectQuery;
        if(orderBy != null){
            selectQuery = "SELECT  * FROM `Notification` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                selectQuery = selectQuery +  " " + " LIMIT " + limit;
            }
        }else{
            if(limit != 0){
                // Select All Query
                selectQuery = "SELECT  * FROM Notification " + whereQuery + " LIMIT " + limit;
            }else{
                selectQuery = "SELECT  * FROM Notification " + whereQuery;
            }
        }

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<Notification>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    NotificationRepository repo = restAdapter.createRepository(NotificationRepository.class);
                    repo.addStorage(context);
                    modelList.add((Notification)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<Notification>) modelList;
    }


    // Getting All Data where
    public DataList<Notification>  getAll__db(HashMap<String, Object> whereKeyValue, int limit) {
        return getAll__db(whereKeyValue, null,  limit);
    }





    /**
     * Check count of database.
     * @param whereKeyValue
     * @param orderBy
     * @param limit
     * @return
     */
    public int count__db(HashMap<String, Object> whereKeyValue, String orderBy, int limit){
        String whereQuery = getWhereQuery(whereKeyValue);
        String countQuery;
        if(orderBy != null){
            countQuery = "SELECT  * FROM `Notification` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                countQuery = countQuery +  " " + " LIMIT " + limit;
            }
        }else{
            if(limit != 0){
                // Select All Query
                countQuery = "SELECT  * FROM `Notification` " + whereQuery + " LIMIT " + limit;
            }else{
                countQuery = "SELECT  * FROM `Notification` " + whereQuery;
            }
        }


        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    /**
     * Check count of database.
     * @param whereKeyValue
     * @param limit
     * @return
     */
    public int count__db(HashMap<String, Object> whereKeyValue, int limit){
        String whereQuery = getWhereQuery(whereKeyValue);
        String countQuery;
        if(limit != 0){
            countQuery = "SELECT  * FROM `Notification` " + whereQuery + " LIMIT " + limit;
        }else{
            countQuery = "SELECT  * FROM `Notification` " + whereQuery;
        }

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    /**
     * Check count of database.
     * @param whereKeyValue
     * @return
     */
    public int count__db(HashMap<String, Object> whereKeyValue){
            return count__db(whereKeyValue, 0);
    }


    // Updating updated data property to new contact with where clause..
    public void checkOldData__db(final HashMap<String, Object> whereKeyValue) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
                db.beginTransaction();
                ContentValues values = new ContentValues();
                values.put("_DATA_UPDATED", 0);
                String where = getWhere(whereKeyValue);
                // updating row
                db.update("`Notification`", values, "_DATA_UPDATED = 1 AND " + where, null);
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();

    }


    // Delete Old data with where clause
    public void deleteOldData__db(final HashMap<String, Object> whereKeyValue) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                db.beginTransaction();
                String where = getWhere(whereKeyValue);
                db.delete("`Notification`", "_DATA_UPDATED = 0 AND " + where , null);
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();

    }





    // Deleting by whereKeyValue filter data present..
    public void delete__db(final HashMap<String, Object> whereKeyValue) {
      new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                db.beginTransaction();
                String where = getWhere(whereKeyValue);
                db.delete("`Notification`", where , null);
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        }).start();
    }







    // Getting All Data where
    public DataList<Notification>  getAll__db(String whereKey, String whereKeyValue) {
        DataList<Notification> modelList = new DataList<Notification>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `Notification` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<Notification>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    NotificationRepository repo = restAdapter.createRepository(NotificationRepository.class);
                    repo.addStorage(context);
                    modelList.add((Notification)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<Notification>) modelList;
    }



    /**
     * Check count of database.
     * @param whereKey
     * @param whereKeyValue
     * @return
     */
    public int count__db(String whereKey, String whereKeyValue){
        String countQuery = "SELECT  * FROM `Notification` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;
        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    // Updating updated data property to new contact with where clause..
    public void checkOldData__db(final String whereKey, final String whereKeyValue) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
                db.beginTransaction();
                ContentValues values = new ContentValues();
                values.put("_DATA_UPDATED", 0);
                // updating row
                db.update("`Notification`", values, "_DATA_UPDATED = 1 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();
    }


    // Delete Old data with where clause
    public void deleteOldData__db(final String whereKey, final String whereKeyValue) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                db.beginTransaction();
                db.delete("`Notification`", "_DATA_UPDATED = 0 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();

    }


    //Update multiple data at once..
    public void updateAll__db(final HashMap<String, Object> whereKeyValue, final Notification _modelData ){
      new Thread(new Runnable(){
        @Override
        public void run(){
          SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
          db.beginTransaction();
          ContentValues values = getContentValues(_modelData);
          String where = getWhere(whereKeyValue);
          db.update("`Notification`", values, where, null);
          db.setTransactionSuccessful();
          db.endTransaction();
          //db.close();
        }

      }).start();
    }




    // Deleting by whereKey and whereKeyValue
    public void delete__db(final String whereKey, final String whereKeyValue) {
      new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                db.beginTransaction();
                db.delete(TABLE, whereKey + " = ?", new String[]{whereKeyValue});
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();
    }



    // Updating single contact
    public void update__db(final String id,   final Notification _modelData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                db.beginTransaction();
                ContentValues values = getContentValues(_modelData);
                // updating row
                db.update("`Notification`", values, "id = ?",
                        new String[] { id });
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();

    }


    // Updating updated data property to new contact
    public void checkOldData__db() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                db.beginTransaction();
                ContentValues values = new ContentValues();
                values.put("_DATA_UPDATED", 0);
                // updating row
                db.update("`Notification`", values, "_DATA_UPDATED = 1", null);
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();

    }


    // Delete Old data
    public void deleteOldData__db() {
      new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                db.beginTransaction();
                db.delete("`Notification`", "_DATA_UPDATED = 0", null);
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();

    }


    // Getting contacts Count
    public int count__db() {
        String countQuery = "SELECT  * FROM `" + TABLE + "`";
        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }


    /**
     * Get count by Id..
     * @param id
     * @return
     */
    public int count__db(String id){
        String countQuery = "SELECT  * FROM `" + TABLE  + "` WHERE ID='" + id+"'";
        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }



    // Deleting by id
    public void delete__db(final String id) {
      new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                db.beginTransaction();
                db.delete(TABLE, KEY_ID + " = ?",
                new String[] { id });
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();
    }

    public void reset__db(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                db.beginTransaction();
                db.delete(TABLE,null,null);
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();

    }

}
