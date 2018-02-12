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

import com.androidsdk.snaphy.snaphyandroidsdk.models.VehicleDetail;
//Import self repository..
import com.androidsdk.snaphy.snaphyandroidsdk.repository.VehicleDetailRepository;
import com.strongloop.android.loopback.RestAdapter;

/**
* Created by snaphy on 1/2/2017.
*/

public class VehicleDetailDb{

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

  public VehicleDetailDb(Context context, String DATABASE_NAME, RestAdapter restAdapter){
    //super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
    this.restAdapter = restAdapter;
    TABLE = "VehicleDetail";
    this.DATABASE_NAME = DATABASE_NAME;
    SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
    DbHandler.getInstance(context, DATABASE_NAME).onCreate(db);
  }


    public void insert__db (final String id, final VehicleDetail _modelData) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      // Inserting Row
      ContentValues values = getContentValues(_modelData);
      db.insert("`VehicleDetail`", null, values);
    }





    public ContentValues getContentValues(VehicleDetail _modelData){
      ContentValues values = new ContentValues();
                       
                                                            String registrationNumberData = "";
                        if(_modelData.getRegistrationNumber() != null){
                          registrationNumberData = _modelData.getRegistrationNumber().toString();
                          values.put("`registrationNumber`", registrationNumberData);
                        }
                                  
                                
                                                            String registeredNameData = "";
                        if(_modelData.getRegisteredName() != null){
                          registeredNameData = _modelData.getRegisteredName().toString();
                          values.put("`registeredName`", registeredNameData);
                        }
                                  
                                
                                                            String showroomNameData = "";
                        if(_modelData.getShowroomName() != null){
                          showroomNameData = _modelData.getShowroomName().toString();
                          values.put("`showroomName`", showroomNameData);
                        }
                                  
                                
                                                            String workshopNameData = "";
                        if(_modelData.getWorkshopName() != null){
                          workshopNameData = _modelData.getWorkshopName().toString();
                          values.put("`workshopName`", workshopNameData);
                        }
                                  
                                
                                                            String statusData = "";
                        if(_modelData.getStatus() != null){
                          statusData = _modelData.getStatus().toString();
                          values.put("`status`", statusData);
                        }
                                  
                                
                                                            String addedData = "";
                        if(_modelData.getAdded() != null){
                          addedData = _modelData.getAdded().toString();
                          values.put("`added`", addedData);
                        }
                                  
                                
                                                            String updatedData = "";
                        if(_modelData.getUpdated() != null){
                          updatedData = _modelData.getUpdated().toString();
                          values.put("`updated`", updatedData);
                        }
                                  
                                
                                                            String vehicleTypeData = "";
                        if(_modelData.getVehicleType() != null){
                          vehicleTypeData = _modelData.getVehicleType().toString();
                          values.put("`vehicleType`", vehicleTypeData);
                        }
                                  
                                
                                                            String vehicleInsuranceProofData = "";
                        if(_modelData.getVehicleInsuranceProof() != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                          vehicleInsuranceProofData = gson.toJson(_modelData.getVehicleInsuranceProof(), HashMap.class);
                          values.put("`vehicleInsuranceProof`", vehicleInsuranceProofData);
                        }
                                  
                                
                                                            String vehicleRegistrationProofData = "";
                        if(_modelData.getVehicleRegistrationProof() != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                          vehicleRegistrationProofData = gson.toJson(_modelData.getVehicleRegistrationProof(), HashMap.class);
                          values.put("`vehicleRegistrationProof`", vehicleRegistrationProofData);
                        }
                                  
                                
                                                            String pucProofData = "";
                        if(_modelData.getPucProof() != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                          pucProofData = gson.toJson(_modelData.getPucProof(), HashMap.class);
                          values.put("`pucProof`", pucProofData);
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

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String customerIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getCustomerId");
                              if(method.invoke(_modelData) != null){
                                //customerIdData = _modelData.getCustomerId().toString();
                                customerIdData = (String) method.invoke(_modelData);
                                values.put("`customerId`", customerIdData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String workshopIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getWorkshopId");
                              if(method.invoke(_modelData) != null){
                                //workshopIdData = _modelData.getWorkshopId().toString();
                                workshopIdData = (String) method.invoke(_modelData);
                                values.put("`workshopId`", workshopIdData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String showroomIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getShowroomId");
                              if(method.invoke(_modelData) != null){
                                //showroomIdData = _modelData.getShowroomId().toString();
                                showroomIdData = (String) method.invoke(_modelData);
                                values.put("`showroomId`", showroomIdData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String vehicleInfoIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getVehicleInfoId");
                              if(method.invoke(_modelData) != null){
                                //vehicleInfoIdData = _modelData.getVehicleInfoId().toString();
                                vehicleInfoIdData = (String) method.invoke(_modelData);
                                values.put("`vehicleInfoId`", vehicleInfoIdData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String insuranceIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getInsuranceId");
                              if(method.invoke(_modelData) != null){
                                //insuranceIdData = _modelData.getInsuranceId().toString();
                                insuranceIdData = (String) method.invoke(_modelData);
                                values.put("`insuranceId`", insuranceIdData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                  
        
          
        //Add the updated data property value to be 1
        values.put("`_DATA_UPDATED`", 1);
        return values;
    }



    // Getting single c
    public   VehicleDetail get__db(String id) {
        if (id != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("VehicleDetail", null, "id=?", new String[]{id}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);
                    cursor.close();
                    //db.close(); // Closing database connection
                    if (hashMap != null) {
                        VehicleDetailRepository repo = restAdapter.createRepository(VehicleDetailRepository.class);
                        repo.addStorage(context);
                        return (VehicleDetail)repo.createObject(hashMap);
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
    public   VehicleDetail get__db(String whereKey, String whereKeyValue) {
        if (whereKeyValue != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("`VehicleDetail`", null, "`" + whereKey + "` =?", new String[]{whereKeyValue}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);

                    cursor.close();
                    //db.close(); // Closing database connection

                    if (hashMap != null) {
                        VehicleDetailRepository repo = restAdapter.createRepository(VehicleDetailRepository.class);
                        repo.addStorage(context);
                        return (VehicleDetail)repo.createObject(hashMap);
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

                      
                                                            String registrationNumberData = "";
                        if(cursor.getString(0) != null){
                          registrationNumberData = cursor.getString(0);
                          if(registrationNumberData != null){
                            registrationNumberData = (String)registrationNumberData;
                            hashMap.put("registrationNumber", registrationNumberData);
                          }
                        }
                                                
                                
                                                            String registeredNameData = "";
                        if(cursor.getString(1) != null){
                          registeredNameData = cursor.getString(1);
                          if(registeredNameData != null){
                            registeredNameData = (String)registeredNameData;
                            hashMap.put("registeredName", registeredNameData);
                          }
                        }
                                                
                                
                                                            String showroomNameData = "";
                        if(cursor.getString(2) != null){
                          showroomNameData = cursor.getString(2);
                          if(showroomNameData != null){
                            showroomNameData = (String)showroomNameData;
                            hashMap.put("showroomName", showroomNameData);
                          }
                        }
                                                
                                
                                                            String workshopNameData = "";
                        if(cursor.getString(3) != null){
                          workshopNameData = cursor.getString(3);
                          if(workshopNameData != null){
                            workshopNameData = (String)workshopNameData;
                            hashMap.put("workshopName", workshopNameData);
                          }
                        }
                                                
                                
                                                            String statusData = "";
                        if(cursor.getString(4) != null){
                          statusData = cursor.getString(4);
                          if(statusData != null){
                            statusData = (String)statusData;
                            hashMap.put("status", statusData);
                          }
                        }
                                                
                                
                                                            String addedData = "";
                        if(cursor.getString(5) != null){
                          addedData = cursor.getString(5);
                          if(addedData != null){
                            addedData = (String)addedData;
                            hashMap.put("added", addedData);
                          }
                        }
                                                
                                
                                                            String updatedData = "";
                        if(cursor.getString(6) != null){
                          updatedData = cursor.getString(6);
                          if(updatedData != null){
                            updatedData = (String)updatedData;
                            hashMap.put("updated", updatedData);
                          }
                        }
                                                
                                
                                                            String vehicleTypeData = "";
                        if(cursor.getString(7) != null){
                          vehicleTypeData = cursor.getString(7);
                          if(vehicleTypeData != null){
                            vehicleTypeData = (String)vehicleTypeData;
                            hashMap.put("vehicleType", vehicleTypeData);
                          }
                        }
                                                
                                
                                                            Map<String, Object> vehicleInsuranceProofData = new HashMap<>();
                        if(cursor.getString(8) != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                           vehicleInsuranceProofData = gson.fromJson(cursor.getString(8), Map.class);
                          if(vehicleInsuranceProofData != null){
                            vehicleInsuranceProofData = (Map<String, Object>)vehicleInsuranceProofData;
                            hashMap.put("vehicleInsuranceProof", vehicleInsuranceProofData);
                          }
                        }
                                                
                                
                                                            Map<String, Object> vehicleRegistrationProofData = new HashMap<>();
                        if(cursor.getString(9) != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                           vehicleRegistrationProofData = gson.fromJson(cursor.getString(9), Map.class);
                          if(vehicleRegistrationProofData != null){
                            vehicleRegistrationProofData = (Map<String, Object>)vehicleRegistrationProofData;
                            hashMap.put("vehicleRegistrationProof", vehicleRegistrationProofData);
                          }
                        }
                                                
                                
                                                            Map<String, Object> pucProofData = new HashMap<>();
                        if(cursor.getString(10) != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                           pucProofData = gson.fromJson(cursor.getString(10), Map.class);
                          if(pucProofData != null){
                            pucProofData = (Map<String, Object>)pucProofData;
                            hashMap.put("pucProof", pucProofData);
                          }
                        }
                                                
                                
                                                            String idData = "";
                        if(cursor.getString(11) != null){
                          idData = cursor.getString(11);
                          if(idData != null){
                            idData = idData.toString();
                            hashMap.put("id", idData);
                          }
                        }
                                                
                                
                                                            String customerIdData = "";
                        if(cursor.getString(12) != null){
                          customerIdData = cursor.getString(12);
                          if(customerIdData != null){
                            customerIdData = customerIdData.toString();
                            hashMap.put("customerId", customerIdData);
                          }
                        }
                                                
                                
                                                            String workshopIdData = "";
                        if(cursor.getString(13) != null){
                          workshopIdData = cursor.getString(13);
                          if(workshopIdData != null){
                            workshopIdData = workshopIdData.toString();
                            hashMap.put("workshopId", workshopIdData);
                          }
                        }
                                                
                                
                                                            String showroomIdData = "";
                        if(cursor.getString(14) != null){
                          showroomIdData = cursor.getString(14);
                          if(showroomIdData != null){
                            showroomIdData = showroomIdData.toString();
                            hashMap.put("showroomId", showroomIdData);
                          }
                        }
                                                
                                
                                                            String vehicleInfoIdData = "";
                        if(cursor.getString(15) != null){
                          vehicleInfoIdData = cursor.getString(15);
                          if(vehicleInfoIdData != null){
                            vehicleInfoIdData = vehicleInfoIdData.toString();
                            hashMap.put("vehicleInfoId", vehicleInfoIdData);
                          }
                        }
                                                
                                
                                                            String insuranceIdData = "";
                        if(cursor.getString(16) != null){
                          insuranceIdData = cursor.getString(16);
                          if(insuranceIdData != null){
                            insuranceIdData = insuranceIdData.toString();
                            hashMap.put("insuranceId", insuranceIdData);
                          }
                        }
                                                
                  //End for loop
         
          

        return hashMap;
    }//parseCursor



    public void upsert__db(String id, VehicleDetail model){
        if(count__db(id) != 0){
            update__db(id, model);
        }else{
            insert__db(id, model);
        }
    } //upsert__db



    // Getting All Contacts
    public DataList<VehicleDetail>  getAll__db() {
        DataList<VehicleDetail> modelList = new DataList<VehicleDetail>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `VehicleDetail`";

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<VehicleDetail>) modelList;
        }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    VehicleDetailRepository repo = restAdapter.createRepository(VehicleDetailRepository.class);
                    repo.addStorage(context);
                    modelList.add((VehicleDetail)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<VehicleDetail>) modelList;
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
    public DataList<VehicleDetail>  getAll__db(HashMap<String, Object> whereKeyValue) {
        return getAll__db(whereKeyValue, null, 0, 0);
    }



    // Getting All Data where and sort column according to date wise..
    public DataList<VehicleDetail>  getAll__db(HashMap<String, Object> whereKeyValue, String orderBy, int limit, int skip) {
        DataList<VehicleDetail> modelList = new DataList<VehicleDetail>();
        String whereQuery = getWhereQuery(whereKeyValue);
        String selectQuery;
        if(orderBy != null){
            selectQuery = "SELECT  * FROM `VehicleDetail` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                selectQuery = selectQuery +  " " + " LIMIT " + limit + " OFFSET " + skip;
            }else{
                selectQuery = selectQuery +  " " + " LIMIT -1 OFFSET " + skip;
            }
        }else{
            if(limit != 0){
                // Select All Query
                selectQuery = "SELECT  * FROM VehicleDetail " + whereQuery + " LIMIT " + limit + " OFFSET " + skip;
            }else{
                selectQuery = "SELECT  * FROM VehicleDetail " + whereQuery  + " LIMIT -1 OFFSET " + skip;
            }
        }

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<VehicleDetail>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    VehicleDetailRepository repo = restAdapter.createRepository(VehicleDetailRepository.class);
                    repo.addStorage(context);
                    modelList.add((VehicleDetail)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }
        cursor.close();
        // return contact list
        return (DataList<VehicleDetail>) modelList;
    }



    // Getting All Data where
    public DataList<VehicleDetail>  getAll__db(HashMap<String, Object> whereKeyValue, int limit, int skip) {
        return getAll__db(whereKeyValue, null,  limit, skip);
    }





    /**
     * Check count of database.
     * @param whereKeyValue
     * @param orderBy
     * @param limit
     * @return
     */
    public int count__db(HashMap<String, Object> whereKeyValue, String orderBy, int limit, int skip){
        String whereQuery = getWhereQuery(whereKeyValue);
        String countQuery;
        if(orderBy != null){
            countQuery = "SELECT  * FROM `VehicleDetail` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                countQuery = countQuery +  " " + " LIMIT " + limit + " OFFSET " + skip;
            }else{
                countQuery = countQuery + " LIMIT -1  OFFSET " + skip;
            }
        }else{
            if(limit != 0){
                // Select All Query
                countQuery = "SELECT  * FROM `VehicleDetail` " + whereQuery + " LIMIT " + limit + " OFFSET " + skip;
            }else{
                countQuery = "SELECT  * FROM `VehicleDetail` " + whereQuery + " LIMIT -1 OFFSET " + skip;
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
    public int count__db(HashMap<String, Object> whereKeyValue, int limit, int skip){
        String whereQuery = getWhereQuery(whereKeyValue);
        String countQuery;
        if(limit != 0){
            countQuery = "SELECT  * FROM `VehicleDetail` " + whereQuery + " LIMIT " + limit + " OFFSET " + skip;
        }else{
            countQuery = "SELECT  * FROM `VehicleDetail` " + whereQuery + " LIMIT -1 OFFSET " + skip;
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
            return count__db(whereKeyValue, 0, 0);
    }



    // Updating updated data property to new contact with where clause..
    public void checkOldData__db(final HashMap<String, Object> whereKeyValue) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
            //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
            ContentValues values = new ContentValues();
            values.put("_DATA_UPDATED", 0);
            String where = getWhere(whereKeyValue);
            // updating row
            db.update("`VehicleDetail`", values, "_DATA_UPDATED = 1 AND " + where, null);
    }


    // Delete Old data with where clause
    public void deleteOldData__db(final HashMap<String, Object> whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      String where = getWhere(whereKeyValue);
      db.delete("`VehicleDetail`", "_DATA_UPDATED = 0 AND " + where , null);
    }





    // Deleting by whereKeyValue filter data present..
    public void delete__db(final HashMap<String, Object> whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      String where = getWhere(whereKeyValue);
      db.delete("`VehicleDetail`", where , null);
    }







    // Getting All Data where
    public DataList<VehicleDetail>  getAll__db(String whereKey, String whereKeyValue) {
        DataList<VehicleDetail> modelList = new DataList<VehicleDetail>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `VehicleDetail` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<VehicleDetail>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    VehicleDetailRepository repo = restAdapter.createRepository(VehicleDetailRepository.class);
                    repo.addStorage(context);
                    modelList.add((VehicleDetail)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }
        cursor.close();
        return (DataList<VehicleDetail>) modelList;
    }



    /**
     * Check count of database.
     * @param whereKey
     * @param whereKeyValue
     * @return
     */
    public int count__db(String whereKey, String whereKeyValue){
      String countQuery = "SELECT  * FROM `VehicleDetail` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
      Cursor cursor = db.rawQuery(countQuery, null);
      int count = cursor.getCount();
      cursor.close();
      return count;
    }


    // Updating updated data property to new contact with where clause..
    public void checkOldData__db(final String whereKey, final String whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put("_DATA_UPDATED", 0);
      // updating row
      db.update("`VehicleDetail`", values, "_DATA_UPDATED = 1 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
    }


    // Delete Old data with where clause
    public void deleteOldData__db(final String whereKey, final String whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      db.delete("`VehicleDetail`", "_DATA_UPDATED = 0 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
    }


    //Update multiple data at once..
    public void updateAll__db(final HashMap<String, Object> whereKeyValue, final VehicleDetail _modelData ){
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      ContentValues values = getContentValues(_modelData);
      String where = getWhere(whereKeyValue);
      db.update("`VehicleDetail`", values, where, null);
    }




    // Deleting by whereKey and whereKeyValue
    public void delete__db(final String whereKey, final String whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      db.delete(TABLE, whereKey + " = ?", new String[]{whereKeyValue});
    }



    // Updating single contact
    public void update__db(final String id,   final VehicleDetail _modelData) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      ContentValues values = getContentValues(_modelData);
      // updating row
      db.update("`VehicleDetail`", values, "id = ?", new String[] { id });
    }


    // Updating updated data property to new contact
    public void checkOldData__db() {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put("_DATA_UPDATED", 0);
      // updating row
      db.update("`VehicleDetail`", values, "_DATA_UPDATED = 1", null);
    }


    // Delete Old data
    public void deleteOldData__db() {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      db.delete("`VehicleDetail`", "_DATA_UPDATED = 0", null);
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
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      db.delete(TABLE, KEY_ID + " = ?",
      new String[] { id });
    }

    public void reset__db(){
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      db.delete(TABLE,null,null);
    }

}
