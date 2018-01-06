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

import com.androidsdk.snaphy.snaphyandroidsdk.models.QuoteReply;
//Import self repository..
import com.androidsdk.snaphy.snaphyandroidsdk.repository.QuoteReplyRepository;
import com.strongloop.android.loopback.RestAdapter;

/**
* Created by snaphy on 1/2/2017.
*/

public class QuoteReplyDb{

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

  public QuoteReplyDb(Context context, String DATABASE_NAME, RestAdapter restAdapter){
    //super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
    this.restAdapter = restAdapter;
    TABLE = "QuoteReply";
    this.DATABASE_NAME = DATABASE_NAME;
    SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
    DbHandler.getInstance(context, DATABASE_NAME).onCreate(db);
  }


    public void insert__db (final String id, final QuoteReply _modelData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                // Inserting Row
                ContentValues values = getContentValues(_modelData);
                db.insert("`QuoteReply`", null, values);
                //db.close(); // Closing database connection
            }
        }).start();

    }





    public ContentValues getContentValues(QuoteReply _modelData){
      ContentValues values = new ContentValues();
                       
                                                            double exShowroomPriceData;
                        exShowroomPriceData = (double)_modelData.getExShowroomPrice();
                        values.put("`exShowroomPrice`", exShowroomPriceData);
                                  
                                
                                                            double exchangeBonusData;
                        exchangeBonusData = (double)_modelData.getExchangeBonus();
                        values.put("`exchangeBonus`", exchangeBonusData);
                                  
                                
                                                            double insuranceData;
                        insuranceData = (double)_modelData.getInsurance();
                        values.put("`insurance`", insuranceData);
                                  
                                
                                                            double specialDiscountData;
                        specialDiscountData = (double)_modelData.getSpecialDiscount();
                        values.put("`specialDiscount`", specialDiscountData);
                                  
                                
                                                            String rtoRegistrationData = "";
                        if(_modelData.getRtoRegistration() != null){
                          rtoRegistrationData = _modelData.getRtoRegistration().toString();
                          values.put("`rtoRegistration`", rtoRegistrationData);
                        }
                                  
                                
                                                            double loyaltyBonusData;
                        loyaltyBonusData = (double)_modelData.getLoyaltyBonus();
                        values.put("`loyaltyBonus`", loyaltyBonusData);
                                  
                                
                                                            double miscChargesData;
                        miscChargesData = (double)_modelData.getMiscCharges();
                        values.put("`miscCharges`", miscChargesData);
                                  
                                
                                                            double gstData;
                        gstData = (double)_modelData.getGst();
                        values.put("`gst`", gstData);
                                  
                                
                                                            double roadPriceData;
                        roadPriceData = (double)_modelData.getRoadPrice();
                        values.put("`roadPrice`", roadPriceData);
                                  
                                
                                                            double distanceData;
                        distanceData = (double)_modelData.getDistance();
                        values.put("`distance`", distanceData);
                                  
                                
                                                            int isReadData = 0;
                        if(_modelData.getIsRead()){
                          isReadData = 1;
                        }else{
                          isReadData = 0;
                        }
                        values.put("`isRead`", isReadData);
                                  
                                
                                                            String replyNumberData = "";
                        if(_modelData.getReplyNumber() != null){
                          replyNumberData = _modelData.getReplyNumber().toString();
                          values.put("`replyNumber`", replyNumberData);
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
                        String customerQuoteIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getCustomerQuoteId");
                              if(method.invoke(_modelData) != null){
                                //customerQuoteIdData = _modelData.getCustomerQuoteId().toString();
                                customerQuoteIdData = (String) method.invoke(_modelData);
                                values.put("`customerQuoteId`", customerQuoteIdData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String dealerIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getDealerId");
                              if(method.invoke(_modelData) != null){
                                //dealerIdData = _modelData.getDealerId().toString();
                                dealerIdData = (String) method.invoke(_modelData);
                                values.put("`dealerId`", dealerIdData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                  
        
          
        //Add the updated data property value to be 1
        values.put("`_DATA_UPDATED`", 1);
        return values;
    }



    // Getting single c
    public   QuoteReply get__db(String id) {
        if (id != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("QuoteReply", null, "id=?", new String[]{id}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);
                    cursor.close();
                    //db.close(); // Closing database connection
                    if (hashMap != null) {
                        QuoteReplyRepository repo = restAdapter.createRepository(QuoteReplyRepository.class);
                        repo.addStorage(context);
                        return (QuoteReply)repo.createObject(hashMap);
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
    public   QuoteReply get__db(String whereKey, String whereKeyValue) {
        if (whereKeyValue != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("`QuoteReply`", null, "`" + whereKey + "` =?", new String[]{whereKeyValue}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);

                    cursor.close();
                    //db.close(); // Closing database connection

                    if (hashMap != null) {
                        QuoteReplyRepository repo = restAdapter.createRepository(QuoteReplyRepository.class);
                        repo.addStorage(context);
                        return (QuoteReply)repo.createObject(hashMap);
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

                      
                                                            double exShowroomPriceData = (double)0;
                          exShowroomPriceData = cursor.getInt(0);
                          exShowroomPriceData = (double)exShowroomPriceData;
                          hashMap.put("exShowroomPrice", exShowroomPriceData);


                                                
                                
                                                            double exchangeBonusData = (double)0;
                          exchangeBonusData = cursor.getInt(1);
                          exchangeBonusData = (double)exchangeBonusData;
                          hashMap.put("exchangeBonus", exchangeBonusData);


                                                
                                
                                                            double insuranceData = (double)0;
                          insuranceData = cursor.getInt(2);
                          insuranceData = (double)insuranceData;
                          hashMap.put("insurance", insuranceData);


                                                
                                
                                                            double specialDiscountData = (double)0;
                          specialDiscountData = cursor.getInt(3);
                          specialDiscountData = (double)specialDiscountData;
                          hashMap.put("specialDiscount", specialDiscountData);


                                                
                                
                                                            String rtoRegistrationData = "";
                        if(cursor.getString(4) != null){
                          rtoRegistrationData = cursor.getString(4);
                          if(rtoRegistrationData != null){
                            rtoRegistrationData = (String)rtoRegistrationData;
                            hashMap.put("rtoRegistration", rtoRegistrationData);
                          }
                        }
                                                
                                
                                                            double loyaltyBonusData = (double)0;
                          loyaltyBonusData = cursor.getInt(5);
                          loyaltyBonusData = (double)loyaltyBonusData;
                          hashMap.put("loyaltyBonus", loyaltyBonusData);


                                                
                                
                                                            double miscChargesData = (double)0;
                          miscChargesData = cursor.getInt(6);
                          miscChargesData = (double)miscChargesData;
                          hashMap.put("miscCharges", miscChargesData);


                                                
                                
                                                            double gstData = (double)0;
                          gstData = cursor.getInt(7);
                          gstData = (double)gstData;
                          hashMap.put("gst", gstData);


                                                
                                
                                                            double roadPriceData = (double)0;
                          roadPriceData = cursor.getInt(8);
                          roadPriceData = (double)roadPriceData;
                          hashMap.put("roadPrice", roadPriceData);


                                                
                                
                                                            double distanceData = (double)0;
                          distanceData = cursor.getInt(9);
                          distanceData = (double)distanceData;
                          hashMap.put("distance", distanceData);


                                                
                                
                                                            boolean isReadData = false;
                        int tempisReadData = cursor.getInt(10);
                        if( tempisReadData > 0){
                          isReadData = true;
                        }else{
                          isReadData = false;
                        }
                        hashMap.put("isRead", isReadData);
                                                
                                
                                                            String replyNumberData = "";
                        if(cursor.getString(11) != null){
                          replyNumberData = cursor.getString(11);
                          if(replyNumberData != null){
                            replyNumberData = (String)replyNumberData;
                            hashMap.put("replyNumber", replyNumberData);
                          }
                        }
                                                
                                
                                                            String addedData = "";
                        if(cursor.getString(12) != null){
                          addedData = cursor.getString(12);
                          if(addedData != null){
                            addedData = (String)addedData;
                            hashMap.put("added", addedData);
                          }
                        }
                                                
                                
                                                            String updatedData = "";
                        if(cursor.getString(13) != null){
                          updatedData = cursor.getString(13);
                          if(updatedData != null){
                            updatedData = (String)updatedData;
                            hashMap.put("updated", updatedData);
                          }
                        }
                                                
                                
                                                            String idData = "";
                        if(cursor.getString(14) != null){
                          idData = cursor.getString(14);
                          if(idData != null){
                            idData = idData.toString();
                            hashMap.put("id", idData);
                          }
                        }
                                                
                                
                                                            String customerQuoteIdData = "";
                        if(cursor.getString(15) != null){
                          customerQuoteIdData = cursor.getString(15);
                          if(customerQuoteIdData != null){
                            customerQuoteIdData = customerQuoteIdData.toString();
                            hashMap.put("customerQuoteId", customerQuoteIdData);
                          }
                        }
                                                
                                
                                                            String dealerIdData = "";
                        if(cursor.getString(16) != null){
                          dealerIdData = cursor.getString(16);
                          if(dealerIdData != null){
                            dealerIdData = dealerIdData.toString();
                            hashMap.put("dealerId", dealerIdData);
                          }
                        }
                                                
                  //End for loop
         
          

        return hashMap;
    }//parseCursor



    public void upsert__db(String id, QuoteReply model){
        if(count__db(id) != 0){
            update__db(id, model);
        }else{
            insert__db(id, model);
        }
    } //upsert__db



    // Getting All Contacts
    public DataList<QuoteReply>  getAll__db() {
        DataList<QuoteReply> modelList = new DataList<QuoteReply>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `QuoteReply`";

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<QuoteReply>) modelList;
        }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    QuoteReplyRepository repo = restAdapter.createRepository(QuoteReplyRepository.class);
                    repo.addStorage(context);
                    modelList.add((QuoteReply)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<QuoteReply>) modelList;
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
    public DataList<QuoteReply>  getAll__db(HashMap<String, Object> whereKeyValue) {
        return getAll__db(whereKeyValue, null, 0);
    }



    // Getting All Data where and sort column according to date wise..
    public DataList<QuoteReply>  getAll__db(HashMap<String, Object> whereKeyValue, String orderBy, int limit) {
        DataList<QuoteReply> modelList = new DataList<QuoteReply>();
        String whereQuery = getWhereQuery(whereKeyValue);
        String selectQuery;
        if(orderBy != null){
            selectQuery = "SELECT  * FROM `QuoteReply` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                selectQuery = selectQuery +  " " + " LIMIT " + limit;
            }
        }else{
            if(limit != 0){
                // Select All Query
                selectQuery = "SELECT  * FROM QuoteReply " + whereQuery + " LIMIT " + limit;
            }else{
                selectQuery = "SELECT  * FROM QuoteReply " + whereQuery;
            }
        }

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<QuoteReply>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    QuoteReplyRepository repo = restAdapter.createRepository(QuoteReplyRepository.class);
                    repo.addStorage(context);
                    modelList.add((QuoteReply)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<QuoteReply>) modelList;
    }


    // Getting All Data where
    public DataList<QuoteReply>  getAll__db(HashMap<String, Object> whereKeyValue, int limit) {
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
            countQuery = "SELECT  * FROM `QuoteReply` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                countQuery = countQuery +  " " + " LIMIT " + limit;
            }
        }else{
            if(limit != 0){
                // Select All Query
                countQuery = "SELECT  * FROM `QuoteReply` " + whereQuery + " LIMIT " + limit;
            }else{
                countQuery = "SELECT  * FROM `QuoteReply` " + whereQuery;
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
            countQuery = "SELECT  * FROM `QuoteReply` " + whereQuery + " LIMIT " + limit;
        }else{
            countQuery = "SELECT  * FROM `QuoteReply` " + whereQuery;
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
                db.update("`QuoteReply`", values, "_DATA_UPDATED = 1 AND " + where, null);
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
                db.delete("`QuoteReply`", "_DATA_UPDATED = 0 AND " + where , null);
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
                db.delete("`QuoteReply`", where , null);
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        }).start();
    }







    // Getting All Data where
    public DataList<QuoteReply>  getAll__db(String whereKey, String whereKeyValue) {
        DataList<QuoteReply> modelList = new DataList<QuoteReply>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `QuoteReply` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<QuoteReply>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    QuoteReplyRepository repo = restAdapter.createRepository(QuoteReplyRepository.class);
                    repo.addStorage(context);
                    modelList.add((QuoteReply)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<QuoteReply>) modelList;
    }



    /**
     * Check count of database.
     * @param whereKey
     * @param whereKeyValue
     * @return
     */
    public int count__db(String whereKey, String whereKeyValue){
        String countQuery = "SELECT  * FROM `QuoteReply` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;
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
                db.update("`QuoteReply`", values, "_DATA_UPDATED = 1 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
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
                db.delete("`QuoteReply`", "_DATA_UPDATED = 0 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();

    }


    //Update multiple data at once..
    public void updateAll__db(final HashMap<String, Object> whereKeyValue, final QuoteReply _modelData ){
      new Thread(new Runnable(){
        @Override
        public void run(){
          SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
          db.beginTransaction();
          ContentValues values = getContentValues(_modelData);
          String where = getWhere(whereKeyValue);
          db.update("`QuoteReply`", values, where, null);
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
    public void update__db(final String id,   final QuoteReply _modelData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                db.beginTransaction();
                ContentValues values = getContentValues(_modelData);
                // updating row
                db.update("`QuoteReply`", values, "id = ?",
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
                db.update("`QuoteReply`", values, "_DATA_UPDATED = 1", null);
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
                db.delete("`QuoteReply`", "_DATA_UPDATED = 0", null);
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
