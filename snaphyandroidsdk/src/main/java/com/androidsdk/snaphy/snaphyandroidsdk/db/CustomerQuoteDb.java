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

import com.androidsdk.snaphy.snaphyandroidsdk.models.CustomerQuote;
//Import self repository..
import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerQuoteRepository;
import com.strongloop.android.loopback.RestAdapter;

/**
* Created by snaphy on 1/2/2017.
*/

public class CustomerQuoteDb{

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

  public CustomerQuoteDb(Context context, String DATABASE_NAME, RestAdapter restAdapter){
    //super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
    this.restAdapter = restAdapter;
    TABLE = "CustomerQuote";
    this.DATABASE_NAME = DATABASE_NAME;
    SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
    DbHandler.getInstance(context, DATABASE_NAME).onCreate(db);
  }


    public void insert__db (final String id, final CustomerQuote _modelData) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      // Inserting Row
      ContentValues values = getContentValues(_modelData);
      db.insert("`CustomerQuote`", null, values);
    }





    public ContentValues getContentValues(CustomerQuote _modelData){
      ContentValues values = new ContentValues();
                       
                                                            String quoteTypeData = "";
                        if(_modelData.getQuoteType() != null){
                          quoteTypeData = _modelData.getQuoteType().toString();
                          values.put("`quoteType`", quoteTypeData);
                        }
                                  
                                
                                                            String ownershipTypeData = "";
                        if(_modelData.getOwnershipType() != null){
                          ownershipTypeData = _modelData.getOwnershipType().toString();
                          values.put("`ownershipType`", ownershipTypeData);
                        }
                                  
                                
                                                            String isFinanceData = "";
                        if(_modelData.getIsFinance() != null){
                          isFinanceData = _modelData.getIsFinance().toString();
                          values.put("`isFinance`", isFinanceData);
                        }
                                  
                                
                                                            String isInsuranceData = "";
                        if(_modelData.getIsInsurance() != null){
                          isInsuranceData = _modelData.getIsInsurance().toString();
                          values.put("`isInsurance`", isInsuranceData);
                        }
                                  
                                
                                                            String isOldVehicleTradeData = "";
                        if(_modelData.getIsOldVehicleTrade() != null){
                          isOldVehicleTradeData = _modelData.getIsOldVehicleTrade().toString();
                          values.put("`isOldVehicleTrade`", isOldVehicleTradeData);
                        }
                                  
                                
                                                            String soldViaAutoboxData = "";
                        if(_modelData.getSoldViaAutobox() != null){
                          soldViaAutoboxData = _modelData.getSoldViaAutobox().toString();
                          values.put("`soldViaAutobox`", soldViaAutoboxData);
                        }
                                  
                                
                                                            int isSoldViaAutoboxData = 0;
                        if(_modelData.getIsSoldViaAutobox()){
                          isSoldViaAutoboxData = 1;
                        }else{
                          isSoldViaAutoboxData = 0;
                        }
                        values.put("`isSoldViaAutobox`", isSoldViaAutoboxData);
                                  
                                
                                                            String onRoadPriceData = "";
                        if(_modelData.getOnRoadPrice() != null){
                          onRoadPriceData = _modelData.getOnRoadPrice().toString();
                          values.put("`onRoadPrice`", onRoadPriceData);
                        }
                                  
                                
                                                            String purchaseStatusData = "";
                        if(_modelData.getPurchaseStatus() != null){
                          purchaseStatusData = _modelData.getPurchaseStatus().toString();
                          values.put("`purchaseStatus`", purchaseStatusData);
                        }
                                  
                                
                                                            String purchaseDateData = "";
                        if(_modelData.getPurchaseDate() != null){
                          purchaseDateData = _modelData.getPurchaseDate().toString();
                          values.put("`purchaseDate`", purchaseDateData);
                        }
                                  
                                
                                                            String gpsTrackerData = "";
                        if(_modelData.getGpsTracker() != null){
                          gpsTrackerData = _modelData.getGpsTracker().toString();
                          values.put("`gpsTracker`", gpsTrackerData);
                        }
                                  
                                
                                                            String dashCameraData = "";
                        if(_modelData.getDashCamera() != null){
                          dashCameraData = _modelData.getDashCamera().toString();
                          values.put("`dashCamera`", dashCameraData);
                        }
                                  
                                
                                                            String testDriveData = "";
                        if(_modelData.getTestDrive() != null){
                          testDriveData = _modelData.getTestDrive().toString();
                          values.put("`testDrive`", testDriveData);
                        }
                                  
                                
                                                            String quoteNumberData = "";
                        if(_modelData.getQuoteNumber() != null){
                          quoteNumberData = _modelData.getQuoteNumber().toString();
                          values.put("`quoteNumber`", quoteNumberData);
                        }
                                  
                                
                                                            String currentBrandIdData = "";
                        if(_modelData.getCurrentBrandId() != null){
                          currentBrandIdData = _modelData.getCurrentBrandId().toString();
                          values.put("`currentBrandId`", currentBrandIdData);
                        }
                                  
                                
                                                            double milesData;
                        milesData = (double)_modelData.getMiles();
                        values.put("`miles`", milesData);
                                  
                                
                                                            String oldTradeVariantNameData = "";
                        if(_modelData.getOldTradeVariantName() != null){
                          oldTradeVariantNameData = _modelData.getOldTradeVariantName().toString();
                          values.put("`oldTradeVariantName`", oldTradeVariantNameData);
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
                                  
                                
                                                            String statusData = "";
                        if(_modelData.getStatus() != null){
                          statusData = _modelData.getStatus().toString();
                          values.put("`status`", statusData);
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
                        String brandIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getBrandId");
                              if(method.invoke(_modelData) != null){
                                //brandIdData = _modelData.getBrandId().toString();
                                brandIdData = (String) method.invoke(_modelData);
                                values.put("`brandId`", brandIdData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String carModelIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getCarModelId");
                              if(method.invoke(_modelData) != null){
                                //carModelIdData = _modelData.getCarModelId().toString();
                                carModelIdData = (String) method.invoke(_modelData);
                                values.put("`carModelId`", carModelIdData);
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

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String cityIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getCityId");
                              if(method.invoke(_modelData) != null){
                                //cityIdData = _modelData.getCityId().toString();
                                cityIdData = (String) method.invoke(_modelData);
                                values.put("`cityId`", cityIdData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String trimIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getTrimId");
                              if(method.invoke(_modelData) != null){
                                //trimIdData = _modelData.getTrimId().toString();
                                trimIdData = (String) method.invoke(_modelData);
                                values.put("`trimId`", trimIdData);
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

                                  
                  
        
          
        //Add the updated data property value to be 1
        values.put("`_DATA_UPDATED`", 1);
        return values;
    }



    // Getting single c
    public   CustomerQuote get__db(String id) {
        if (id != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("CustomerQuote", null, "id=?", new String[]{id}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);
                    cursor.close();
                    //db.close(); // Closing database connection
                    if (hashMap != null) {
                        CustomerQuoteRepository repo = restAdapter.createRepository(CustomerQuoteRepository.class);
                        repo.addStorage(context);
                        return (CustomerQuote)repo.createObject(hashMap);
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
    public   CustomerQuote get__db(String whereKey, String whereKeyValue) {
        if (whereKeyValue != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("`CustomerQuote`", null, "`" + whereKey + "` =?", new String[]{whereKeyValue}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);

                    cursor.close();
                    //db.close(); // Closing database connection

                    if (hashMap != null) {
                        CustomerQuoteRepository repo = restAdapter.createRepository(CustomerQuoteRepository.class);
                        repo.addStorage(context);
                        return (CustomerQuote)repo.createObject(hashMap);
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

                      
                                                            String quoteTypeData = "";
                        if(cursor.getString(0) != null){
                          quoteTypeData = cursor.getString(0);
                          if(quoteTypeData != null){
                            quoteTypeData = (String)quoteTypeData;
                            hashMap.put("quoteType", quoteTypeData);
                          }
                        }
                                                
                                
                                                            String ownershipTypeData = "";
                        if(cursor.getString(1) != null){
                          ownershipTypeData = cursor.getString(1);
                          if(ownershipTypeData != null){
                            ownershipTypeData = (String)ownershipTypeData;
                            hashMap.put("ownershipType", ownershipTypeData);
                          }
                        }
                                                
                                
                                                            String isFinanceData = "";
                        if(cursor.getString(2) != null){
                          isFinanceData = cursor.getString(2);
                          if(isFinanceData != null){
                            isFinanceData = (String)isFinanceData;
                            hashMap.put("isFinance", isFinanceData);
                          }
                        }
                                                
                                
                                                            String isInsuranceData = "";
                        if(cursor.getString(3) != null){
                          isInsuranceData = cursor.getString(3);
                          if(isInsuranceData != null){
                            isInsuranceData = (String)isInsuranceData;
                            hashMap.put("isInsurance", isInsuranceData);
                          }
                        }
                                                
                                
                                                            String isOldVehicleTradeData = "";
                        if(cursor.getString(4) != null){
                          isOldVehicleTradeData = cursor.getString(4);
                          if(isOldVehicleTradeData != null){
                            isOldVehicleTradeData = (String)isOldVehicleTradeData;
                            hashMap.put("isOldVehicleTrade", isOldVehicleTradeData);
                          }
                        }
                                                
                                
                                                            String soldViaAutoboxData = "";
                        if(cursor.getString(5) != null){
                          soldViaAutoboxData = cursor.getString(5);
                          if(soldViaAutoboxData != null){
                            soldViaAutoboxData = (String)soldViaAutoboxData;
                            hashMap.put("soldViaAutobox", soldViaAutoboxData);
                          }
                        }
                                                
                                
                                                            boolean isSoldViaAutoboxData = false;
                        int tempisSoldViaAutoboxData = cursor.getInt(6);
                        if( tempisSoldViaAutoboxData > 0){
                          isSoldViaAutoboxData = true;
                        }else{
                          isSoldViaAutoboxData = false;
                        }
                        hashMap.put("isSoldViaAutobox", isSoldViaAutoboxData);
                                                
                                
                                                            String onRoadPriceData = "";
                        if(cursor.getString(7) != null){
                          onRoadPriceData = cursor.getString(7);
                          if(onRoadPriceData != null){
                            onRoadPriceData = (String)onRoadPriceData;
                            hashMap.put("onRoadPrice", onRoadPriceData);
                          }
                        }
                                                
                                
                                                            String purchaseStatusData = "";
                        if(cursor.getString(8) != null){
                          purchaseStatusData = cursor.getString(8);
                          if(purchaseStatusData != null){
                            purchaseStatusData = (String)purchaseStatusData;
                            hashMap.put("purchaseStatus", purchaseStatusData);
                          }
                        }
                                                
                                
                                                            String purchaseDateData = "";
                        if(cursor.getString(9) != null){
                          purchaseDateData = cursor.getString(9);
                          if(purchaseDateData != null){
                            purchaseDateData = (String)purchaseDateData;
                            hashMap.put("purchaseDate", purchaseDateData);
                          }
                        }
                                                
                                
                                                            String gpsTrackerData = "";
                        if(cursor.getString(10) != null){
                          gpsTrackerData = cursor.getString(10);
                          if(gpsTrackerData != null){
                            gpsTrackerData = (String)gpsTrackerData;
                            hashMap.put("gpsTracker", gpsTrackerData);
                          }
                        }
                                                
                                
                                                            String dashCameraData = "";
                        if(cursor.getString(11) != null){
                          dashCameraData = cursor.getString(11);
                          if(dashCameraData != null){
                            dashCameraData = (String)dashCameraData;
                            hashMap.put("dashCamera", dashCameraData);
                          }
                        }
                                                
                                
                                                            String testDriveData = "";
                        if(cursor.getString(12) != null){
                          testDriveData = cursor.getString(12);
                          if(testDriveData != null){
                            testDriveData = (String)testDriveData;
                            hashMap.put("testDrive", testDriveData);
                          }
                        }
                                                
                                
                                                            String quoteNumberData = "";
                        if(cursor.getString(13) != null){
                          quoteNumberData = cursor.getString(13);
                          if(quoteNumberData != null){
                            quoteNumberData = (String)quoteNumberData;
                            hashMap.put("quoteNumber", quoteNumberData);
                          }
                        }
                                                
                                
                                                            String currentBrandIdData = "";
                        if(cursor.getString(14) != null){
                          currentBrandIdData = cursor.getString(14);
                          if(currentBrandIdData != null){
                            currentBrandIdData = (String)currentBrandIdData;
                            hashMap.put("currentBrandId", currentBrandIdData);
                          }
                        }
                                                
                                
                                                            double milesData = (double)0;
                          milesData = cursor.getInt(15);
                          milesData = (double)milesData;
                          hashMap.put("miles", milesData);


                                                
                                
                                                            String oldTradeVariantNameData = "";
                        if(cursor.getString(16) != null){
                          oldTradeVariantNameData = cursor.getString(16);
                          if(oldTradeVariantNameData != null){
                            oldTradeVariantNameData = (String)oldTradeVariantNameData;
                            hashMap.put("oldTradeVariantName", oldTradeVariantNameData);
                          }
                        }
                                                
                                
                                                            String addedData = "";
                        if(cursor.getString(17) != null){
                          addedData = cursor.getString(17);
                          if(addedData != null){
                            addedData = (String)addedData;
                            hashMap.put("added", addedData);
                          }
                        }
                                                
                                
                                                            String updatedData = "";
                        if(cursor.getString(18) != null){
                          updatedData = cursor.getString(18);
                          if(updatedData != null){
                            updatedData = (String)updatedData;
                            hashMap.put("updated", updatedData);
                          }
                        }
                                                
                                
                                                            String statusData = "";
                        if(cursor.getString(19) != null){
                          statusData = cursor.getString(19);
                          if(statusData != null){
                            statusData = (String)statusData;
                            hashMap.put("status", statusData);
                          }
                        }
                                                
                                
                                                            String idData = "";
                        if(cursor.getString(20) != null){
                          idData = cursor.getString(20);
                          if(idData != null){
                            idData = idData.toString();
                            hashMap.put("id", idData);
                          }
                        }
                                                
                                
                                                            String brandIdData = "";
                        if(cursor.getString(21) != null){
                          brandIdData = cursor.getString(21);
                          if(brandIdData != null){
                            brandIdData = brandIdData.toString();
                            hashMap.put("brandId", brandIdData);
                          }
                        }
                                                
                                
                                                            String carModelIdData = "";
                        if(cursor.getString(22) != null){
                          carModelIdData = cursor.getString(22);
                          if(carModelIdData != null){
                            carModelIdData = carModelIdData.toString();
                            hashMap.put("carModelId", carModelIdData);
                          }
                        }
                                                
                                
                                                            String customerIdData = "";
                        if(cursor.getString(23) != null){
                          customerIdData = cursor.getString(23);
                          if(customerIdData != null){
                            customerIdData = customerIdData.toString();
                            hashMap.put("customerId", customerIdData);
                          }
                        }
                                                
                                
                                                            String dealerIdData = "";
                        if(cursor.getString(24) != null){
                          dealerIdData = cursor.getString(24);
                          if(dealerIdData != null){
                            dealerIdData = dealerIdData.toString();
                            hashMap.put("dealerId", dealerIdData);
                          }
                        }
                                                
                                
                                                            String cityIdData = "";
                        if(cursor.getString(25) != null){
                          cityIdData = cursor.getString(25);
                          if(cityIdData != null){
                            cityIdData = cityIdData.toString();
                            hashMap.put("cityId", cityIdData);
                          }
                        }
                                                
                                
                                                            String trimIdData = "";
                        if(cursor.getString(26) != null){
                          trimIdData = cursor.getString(26);
                          if(trimIdData != null){
                            trimIdData = trimIdData.toString();
                            hashMap.put("trimId", trimIdData);
                          }
                        }
                                                
                                
                                                            String vehicleInfoIdData = "";
                        if(cursor.getString(27) != null){
                          vehicleInfoIdData = cursor.getString(27);
                          if(vehicleInfoIdData != null){
                            vehicleInfoIdData = vehicleInfoIdData.toString();
                            hashMap.put("vehicleInfoId", vehicleInfoIdData);
                          }
                        }
                                                
                  //End for loop
         
          

        return hashMap;
    }//parseCursor



    public void upsert__db(String id, CustomerQuote model){
        if(count__db(id) != 0){
            update__db(id, model);
        }else{
            insert__db(id, model);
        }
    } //upsert__db



    // Getting All Contacts
    public DataList<CustomerQuote>  getAll__db() {
        DataList<CustomerQuote> modelList = new DataList<CustomerQuote>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `CustomerQuote`";

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<CustomerQuote>) modelList;
        }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    CustomerQuoteRepository repo = restAdapter.createRepository(CustomerQuoteRepository.class);
                    repo.addStorage(context);
                    modelList.add((CustomerQuote)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<CustomerQuote>) modelList;
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
    public DataList<CustomerQuote>  getAll__db(HashMap<String, Object> whereKeyValue) {
        return getAll__db(whereKeyValue, null, 0, 0);
    }



    // Getting All Data where and sort column according to date wise..
    public DataList<CustomerQuote>  getAll__db(HashMap<String, Object> whereKeyValue, String orderBy, int limit, int skip) {
        DataList<CustomerQuote> modelList = new DataList<CustomerQuote>();
        String whereQuery = getWhereQuery(whereKeyValue);
        String selectQuery;
        if(orderBy != null){
            selectQuery = "SELECT  * FROM `CustomerQuote` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                selectQuery = selectQuery +  " " + " LIMIT " + limit + " OFFSET " + skip;
            }else{
                selectQuery = selectQuery +  " " + " LIMIT -1 OFFSET " + skip;
            }
        }else{
            if(limit != 0){
                // Select All Query
                selectQuery = "SELECT  * FROM CustomerQuote " + whereQuery + " LIMIT " + limit + " OFFSET " + skip;
            }else{
                selectQuery = "SELECT  * FROM CustomerQuote " + whereQuery  + " LIMIT -1 OFFSET " + skip;
            }
        }

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<CustomerQuote>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    CustomerQuoteRepository repo = restAdapter.createRepository(CustomerQuoteRepository.class);
                    repo.addStorage(context);
                    modelList.add((CustomerQuote)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }
        cursor.close();
        // return contact list
        return (DataList<CustomerQuote>) modelList;
    }



    // Getting All Data where
    public DataList<CustomerQuote>  getAll__db(HashMap<String, Object> whereKeyValue, int limit, int skip) {
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
            countQuery = "SELECT  * FROM `CustomerQuote` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                countQuery = countQuery +  " " + " LIMIT " + limit + " OFFSET " + skip;
            }else{
                countQuery = countQuery + " LIMIT -1  OFFSET " + skip;
            }
        }else{
            if(limit != 0){
                // Select All Query
                countQuery = "SELECT  * FROM `CustomerQuote` " + whereQuery + " LIMIT " + limit + " OFFSET " + skip;
            }else{
                countQuery = "SELECT  * FROM `CustomerQuote` " + whereQuery + " LIMIT -1 OFFSET " + skip;
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
            countQuery = "SELECT  * FROM `CustomerQuote` " + whereQuery + " LIMIT " + limit + " OFFSET " + skip;
        }else{
            countQuery = "SELECT  * FROM `CustomerQuote` " + whereQuery + " LIMIT -1 OFFSET " + skip;
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
            db.update("`CustomerQuote`", values, "_DATA_UPDATED = 1 AND " + where, null);
    }


    // Delete Old data with where clause
    public void deleteOldData__db(final HashMap<String, Object> whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      String where = getWhere(whereKeyValue);
      db.delete("`CustomerQuote`", "_DATA_UPDATED = 0 AND " + where , null);
    }





    // Deleting by whereKeyValue filter data present..
    public void delete__db(final HashMap<String, Object> whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      String where = getWhere(whereKeyValue);
      db.delete("`CustomerQuote`", where , null);
    }







    // Getting All Data where
    public DataList<CustomerQuote>  getAll__db(String whereKey, String whereKeyValue) {
        DataList<CustomerQuote> modelList = new DataList<CustomerQuote>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `CustomerQuote` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<CustomerQuote>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    CustomerQuoteRepository repo = restAdapter.createRepository(CustomerQuoteRepository.class);
                    repo.addStorage(context);
                    modelList.add((CustomerQuote)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }
        cursor.close();
        return (DataList<CustomerQuote>) modelList;
    }



    /**
     * Check count of database.
     * @param whereKey
     * @param whereKeyValue
     * @return
     */
    public int count__db(String whereKey, String whereKeyValue){
      String countQuery = "SELECT  * FROM `CustomerQuote` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;
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
      db.update("`CustomerQuote`", values, "_DATA_UPDATED = 1 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
    }


    // Delete Old data with where clause
    public void deleteOldData__db(final String whereKey, final String whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      db.delete("`CustomerQuote`", "_DATA_UPDATED = 0 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
    }


    //Update multiple data at once..
    public void updateAll__db(final HashMap<String, Object> whereKeyValue, final CustomerQuote _modelData ){
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      ContentValues values = getContentValues(_modelData);
      String where = getWhere(whereKeyValue);
      db.update("`CustomerQuote`", values, where, null);
    }




    // Deleting by whereKey and whereKeyValue
    public void delete__db(final String whereKey, final String whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      db.delete(TABLE, whereKey + " = ?", new String[]{whereKeyValue});
    }



    // Updating single contact
    public void update__db(final String id,   final CustomerQuote _modelData) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      ContentValues values = getContentValues(_modelData);
      // updating row
      db.update("`CustomerQuote`", values, "id = ?", new String[] { id });
    }


    // Updating updated data property to new contact
    public void checkOldData__db() {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put("_DATA_UPDATED", 0);
      // updating row
      db.update("`CustomerQuote`", values, "_DATA_UPDATED = 1", null);
    }


    // Delete Old data
    public void deleteOldData__db() {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      db.delete("`CustomerQuote`", "_DATA_UPDATED = 0", null);
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
