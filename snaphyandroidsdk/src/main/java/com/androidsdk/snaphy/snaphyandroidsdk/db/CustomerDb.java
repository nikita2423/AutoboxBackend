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

import com.androidsdk.snaphy.snaphyandroidsdk.models.Customer;
//Import self repository..
import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerRepository;
import com.strongloop.android.loopback.RestAdapter;

/**
* Created by snaphy on 1/2/2017.
*/

public class CustomerDb{

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

  public CustomerDb(Context context, String DATABASE_NAME, RestAdapter restAdapter){
    //super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
    this.restAdapter = restAdapter;
    TABLE = "Customer";
    this.DATABASE_NAME = DATABASE_NAME;
    SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
    DbHandler.getInstance(context, DATABASE_NAME).onCreate(db);
  }


    public void insert__db (final String id, final Customer _modelData) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      // Inserting Row
      ContentValues values = getContentValues(_modelData);
      db.insert("`Customer`", null, values);
    }





    public ContentValues getContentValues(Customer _modelData){
      ContentValues values = new ContentValues();
                       
                                                            String firstNameData = "";
                        if(_modelData.getFirstName() != null){
                          firstNameData = _modelData.getFirstName().toString();
                          values.put("`firstName`", firstNameData);
                        }
                                  
                                
                                                            String lastNameData = "";
                        if(_modelData.getLastName() != null){
                          lastNameData = _modelData.getLastName().toString();
                          values.put("`lastName`", lastNameData);
                        }
                                  
                                
                                                            String registerStatusData = "";
                        if(_modelData.getRegisterStatus() != null){
                          registerStatusData = _modelData.getRegisterStatus().toString();
                          values.put("`registerStatus`", registerStatusData);
                        }
                                  
                                
                                                            int isInstalledData = 0;
                        if(_modelData.getIsInstalled()){
                          isInstalledData = 1;
                        }else{
                          isInstalledData = 0;
                        }
                        values.put("`isInstalled`", isInstalledData);
                                  
                                
                                                            int isContactSyncedData = 0;
                        if(_modelData.getIsContactSynced()){
                          isContactSyncedData = 1;
                        }else{
                          isContactSyncedData = 0;
                        }
                        values.put("`isContactSynced`", isContactSyncedData);
                                  
                                
                                                            double shareAppCountData;
                        shareAppCountData = (double)_modelData.getShareAppCount();
                        values.put("`shareAppCount`", shareAppCountData);
                                  
                                
                                                            double earnedPointsData;
                        earnedPointsData = (double)_modelData.getEarnedPoints();
                        values.put("`earnedPoints`", earnedPointsData);
                                  
                                
                                                            String cityNameData = "";
                        if(_modelData.getCityName() != null){
                          cityNameData = _modelData.getCityName().toString();
                          values.put("`cityName`", cityNameData);
                        }
                                  
                                
                                                            String countryNameData = "";
                        if(_modelData.getCountryName() != null){
                          countryNameData = _modelData.getCountryName().toString();
                          values.put("`countryName`", countryNameData);
                        }
                                  
                                
                                                            String serviceCenterData = "";
                        if(_modelData.getServiceCenter() != null){
                          serviceCenterData = _modelData.getServiceCenter().toString();
                          values.put("`serviceCenter`", serviceCenterData);
                        }
                                  
                                
                                                            String statusData = "";
                        if(_modelData.getStatus() != null){
                          statusData = _modelData.getStatus().toString();
                          values.put("`status`", statusData);
                        }
                                  
                                
                                                            String phoneNumberData = "";
                        if(_modelData.getPhoneNumber() != null){
                          phoneNumberData = _modelData.getPhoneNumber().toString();
                          values.put("`phoneNumber`", phoneNumberData);
                        }
                                  
                                
                                                            String emailData = "";
                        if(_modelData.getEmail() != null){
                          emailData = _modelData.getEmail().toString();
                          values.put("`email`", emailData);
                        }
                                  
                                
                                                            String usernameData = "";
                        if(_modelData.getUsername() != null){
                          usernameData = _modelData.getUsername().toString();
                          values.put("`username`", usernameData);
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
                                  
                                
                                                            String registrationIdData = "";
                        if(_modelData.getRegistrationId() != null){
                          registrationIdData = _modelData.getRegistrationId().toString();
                          values.put("`registrationId`", registrationIdData);
                        }
                                  
                                
                                                            String profilePicData = "";
                        if(_modelData.getProfilePic() != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                          profilePicData = gson.toJson(_modelData.getProfilePic(), HashMap.class);
                          values.put("`profilePic`", profilePicData);
                        }
                                  
                                
                                                            String referralCodeData = "";
                        if(_modelData.getReferralCode() != null){
                          referralCodeData = _modelData.getReferralCode().toString();
                          values.put("`referralCode`", referralCodeData);
                        }
                                  
                                
                                                            String locationUrlData = "";
                        if(_modelData.getLocationUrl() != null){
                          locationUrlData = _modelData.getLocationUrl().toString();
                          values.put("`locationUrl`", locationUrlData);
                        }
                                  
                                
                                                            double referralCountData;
                        referralCountData = (double)_modelData.getReferralCount();
                        values.put("`referralCount`", referralCountData);
                                  
                                
                                                            String sosStatusData = "";
                        if(_modelData.getSosStatus() != null){
                          sosStatusData = _modelData.getSosStatus().toString();
                          values.put("`sosStatus`", sosStatusData);
                        }
                                  
                                
                                                            double vehicleAddedData;
                        vehicleAddedData = (double)_modelData.getVehicleAdded();
                        values.put("`vehicleAdded`", vehicleAddedData);
                                  
                                
                                                            String notificationSettingsData = "";
                        if(_modelData.getNotificationSettings() != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                          notificationSettingsData = gson.toJson(_modelData.getNotificationSettings(), HashMap.class);
                          values.put("`notificationSettings`", notificationSettingsData);
                        }
                                  
                                
                                                            String gpsTrackerNotificationData = "";
                        if(_modelData.getGpsTrackerNotification() != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                          gpsTrackerNotificationData = gson.toJson(_modelData.getGpsTrackerNotification(), HashMap.class);
                          values.put("`gpsTrackerNotification`", gpsTrackerNotificationData);
                        }
                                  
                                
                                                            String driverLicenceProofData = "";
                        if(_modelData.getDriverLicenceProof() != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                          driverLicenceProofData = gson.toJson(_modelData.getDriverLicenceProof(), HashMap.class);
                          values.put("`driverLicenceProof`", driverLicenceProofData);
                        }
                                  
                                
                                                            String bloodGroupData = "";
                        if(_modelData.getBloodGroup() != null){
                          bloodGroupData = _modelData.getBloodGroup().toString();
                          values.put("`bloodGroup`", bloodGroupData);
                        }
                                  
                                
                                                            String googleRefreshTokenData = "";
                        if(_modelData.getGoogleRefreshToken() != null){
                          googleRefreshTokenData = _modelData.getGoogleRefreshToken().toString();
                          values.put("`googleRefreshToken`", googleRefreshTokenData);
                        }
                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String realmData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getRealm");
                              if(method.invoke(_modelData) != null){
                                //realmData = _modelData.getRealm().toString();
                                realmData = (String) method.invoke(_modelData);
                                values.put("`realm`", realmData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String passwordData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getPassword");
                              if(method.invoke(_modelData) != null){
                                //passwordData = _modelData.getPassword().toString();
                                passwordData = (String) method.invoke(_modelData);
                                values.put("`password`", passwordData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String credentialsData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getCredentials");
                              if(method.invoke(_modelData) != null){
                                //credentialsData = _modelData.getCredentials().toString();
                                credentialsData = (String) method.invoke(_modelData);
                                values.put("`credentials`", credentialsData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String challengesData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getChallenges");
                              if(method.invoke(_modelData) != null){
                                //challengesData = _modelData.getChallenges().toString();
                                challengesData = (String) method.invoke(_modelData);
                                values.put("`challenges`", challengesData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String emailVerifiedData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getEmailVerified");
                              if(method.invoke(_modelData) != null){
                                //emailVerifiedData = _modelData.getEmailVerified().toString();
                                emailVerifiedData = (String) method.invoke(_modelData);
                                values.put("`emailVerified`", emailVerifiedData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String verificationTokenData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getVerificationToken");
                              if(method.invoke(_modelData) != null){
                                //verificationTokenData = _modelData.getVerificationToken().toString();
                                verificationTokenData = (String) method.invoke(_modelData);
                                values.put("`verificationToken`", verificationTokenData);
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
                        String lastUpdatedData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getLastUpdated");
                              if(method.invoke(_modelData) != null){
                                //lastUpdatedData = _modelData.getLastUpdated().toString();
                                lastUpdatedData = (String) method.invoke(_modelData);
                                values.put("`lastUpdated`", lastUpdatedData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
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
                        String countryIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getCountryId");
                              if(method.invoke(_modelData) != null){
                                //countryIdData = _modelData.getCountryId().toString();
                                countryIdData = (String) method.invoke(_modelData);
                                values.put("`countryId`", countryIdData);
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
                        String busIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getBusId");
                              if(method.invoke(_modelData) != null){
                                //busIdData = _modelData.getBusId().toString();
                                busIdData = (String) method.invoke(_modelData);
                                values.put("`busId`", busIdData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String planTypeIdData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getPlanTypeId");
                              if(method.invoke(_modelData) != null){
                                //planTypeIdData = _modelData.getPlanTypeId().toString();
                                planTypeIdData = (String) method.invoke(_modelData);
                                values.put("`planTypeId`", planTypeIdData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                  
        
          
        //Add the updated data property value to be 1
        values.put("`_DATA_UPDATED`", 1);
        return values;
    }



    // Getting single c
    public   Customer get__db(String id) {
        if (id != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("Customer", null, "id=?", new String[]{id}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);
                    cursor.close();
                    //db.close(); // Closing database connection
                    if (hashMap != null) {
                        CustomerRepository repo = restAdapter.createRepository(CustomerRepository.class);
                        repo.addStorage(context);
                        return (Customer)repo.createObject(hashMap);
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
    public   Customer get__db(String whereKey, String whereKeyValue) {
        if (whereKeyValue != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("`Customer`", null, "`" + whereKey + "` =?", new String[]{whereKeyValue}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);

                    cursor.close();
                    //db.close(); // Closing database connection

                    if (hashMap != null) {
                        CustomerRepository repo = restAdapter.createRepository(CustomerRepository.class);
                        repo.addStorage(context);
                        return (Customer)repo.createObject(hashMap);
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

                      
                                                            String firstNameData = "";
                        if(cursor.getString(0) != null){
                          firstNameData = cursor.getString(0);
                          if(firstNameData != null){
                            firstNameData = (String)firstNameData;
                            hashMap.put("firstName", firstNameData);
                          }
                        }
                                                
                                
                                                            String lastNameData = "";
                        if(cursor.getString(1) != null){
                          lastNameData = cursor.getString(1);
                          if(lastNameData != null){
                            lastNameData = (String)lastNameData;
                            hashMap.put("lastName", lastNameData);
                          }
                        }
                                                
                                
                                                            String registerStatusData = "";
                        if(cursor.getString(2) != null){
                          registerStatusData = cursor.getString(2);
                          if(registerStatusData != null){
                            registerStatusData = (String)registerStatusData;
                            hashMap.put("registerStatus", registerStatusData);
                          }
                        }
                                                
                                
                                                            boolean isInstalledData = false;
                        int tempisInstalledData = cursor.getInt(3);
                        if( tempisInstalledData > 0){
                          isInstalledData = true;
                        }else{
                          isInstalledData = false;
                        }
                        hashMap.put("isInstalled", isInstalledData);
                                                
                                
                                                            boolean isContactSyncedData = false;
                        int tempisContactSyncedData = cursor.getInt(4);
                        if( tempisContactSyncedData > 0){
                          isContactSyncedData = true;
                        }else{
                          isContactSyncedData = false;
                        }
                        hashMap.put("isContactSynced", isContactSyncedData);
                                                
                                
                                                            double shareAppCountData = (double)0;
                          shareAppCountData = cursor.getInt(5);
                          shareAppCountData = (double)shareAppCountData;
                          hashMap.put("shareAppCount", shareAppCountData);


                                                
                                
                                                            double earnedPointsData = (double)0;
                          earnedPointsData = cursor.getInt(6);
                          earnedPointsData = (double)earnedPointsData;
                          hashMap.put("earnedPoints", earnedPointsData);


                                                
                                
                                                            String cityNameData = "";
                        if(cursor.getString(7) != null){
                          cityNameData = cursor.getString(7);
                          if(cityNameData != null){
                            cityNameData = (String)cityNameData;
                            hashMap.put("cityName", cityNameData);
                          }
                        }
                                                
                                
                                                            String countryNameData = "";
                        if(cursor.getString(8) != null){
                          countryNameData = cursor.getString(8);
                          if(countryNameData != null){
                            countryNameData = (String)countryNameData;
                            hashMap.put("countryName", countryNameData);
                          }
                        }
                                                
                                
                                                            String serviceCenterData = "";
                        if(cursor.getString(9) != null){
                          serviceCenterData = cursor.getString(9);
                          if(serviceCenterData != null){
                            serviceCenterData = (String)serviceCenterData;
                            hashMap.put("serviceCenter", serviceCenterData);
                          }
                        }
                                                
                                
                                                            String statusData = "";
                        if(cursor.getString(10) != null){
                          statusData = cursor.getString(10);
                          if(statusData != null){
                            statusData = (String)statusData;
                            hashMap.put("status", statusData);
                          }
                        }
                                                
                                
                                                            String phoneNumberData = "";
                        if(cursor.getString(11) != null){
                          phoneNumberData = cursor.getString(11);
                          if(phoneNumberData != null){
                            phoneNumberData = (String)phoneNumberData;
                            hashMap.put("phoneNumber", phoneNumberData);
                          }
                        }
                                                
                                
                                                            String emailData = "";
                        if(cursor.getString(12) != null){
                          emailData = cursor.getString(12);
                          if(emailData != null){
                            emailData = (String)emailData;
                            hashMap.put("email", emailData);
                          }
                        }
                                                
                                
                                                            String usernameData = "";
                        if(cursor.getString(13) != null){
                          usernameData = cursor.getString(13);
                          if(usernameData != null){
                            usernameData = (String)usernameData;
                            hashMap.put("username", usernameData);
                          }
                        }
                                                
                                
                                                            String addedData = "";
                        if(cursor.getString(14) != null){
                          addedData = cursor.getString(14);
                          if(addedData != null){
                            addedData = (String)addedData;
                            hashMap.put("added", addedData);
                          }
                        }
                                                
                                
                                                            String updatedData = "";
                        if(cursor.getString(15) != null){
                          updatedData = cursor.getString(15);
                          if(updatedData != null){
                            updatedData = (String)updatedData;
                            hashMap.put("updated", updatedData);
                          }
                        }
                                                
                                
                                                            String registrationIdData = "";
                        if(cursor.getString(16) != null){
                          registrationIdData = cursor.getString(16);
                          if(registrationIdData != null){
                            registrationIdData = (String)registrationIdData;
                            hashMap.put("registrationId", registrationIdData);
                          }
                        }
                                                
                                
                                                            Map<String, Object> profilePicData = new HashMap<>();
                        if(cursor.getString(17) != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                           profilePicData = gson.fromJson(cursor.getString(17), Map.class);
                          if(profilePicData != null){
                            profilePicData = (Map<String, Object>)profilePicData;
                            hashMap.put("profilePic", profilePicData);
                          }
                        }
                                                
                                
                                                            String referralCodeData = "";
                        if(cursor.getString(18) != null){
                          referralCodeData = cursor.getString(18);
                          if(referralCodeData != null){
                            referralCodeData = (String)referralCodeData;
                            hashMap.put("referralCode", referralCodeData);
                          }
                        }
                                                
                                
                                                            String locationUrlData = "";
                        if(cursor.getString(19) != null){
                          locationUrlData = cursor.getString(19);
                          if(locationUrlData != null){
                            locationUrlData = (String)locationUrlData;
                            hashMap.put("locationUrl", locationUrlData);
                          }
                        }
                                                
                                
                                                            double referralCountData = (double)0;
                          referralCountData = cursor.getInt(20);
                          referralCountData = (double)referralCountData;
                          hashMap.put("referralCount", referralCountData);


                                                
                                
                                                            String sosStatusData = "";
                        if(cursor.getString(21) != null){
                          sosStatusData = cursor.getString(21);
                          if(sosStatusData != null){
                            sosStatusData = (String)sosStatusData;
                            hashMap.put("sosStatus", sosStatusData);
                          }
                        }
                                                
                                
                                                            double vehicleAddedData = (double)0;
                          vehicleAddedData = cursor.getInt(22);
                          vehicleAddedData = (double)vehicleAddedData;
                          hashMap.put("vehicleAdded", vehicleAddedData);


                                                
                                
                                                            Map<String, Object> notificationSettingsData = new HashMap<>();
                        if(cursor.getString(23) != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                           notificationSettingsData = gson.fromJson(cursor.getString(23), Map.class);
                          if(notificationSettingsData != null){
                            notificationSettingsData = (Map<String, Object>)notificationSettingsData;
                            hashMap.put("notificationSettings", notificationSettingsData);
                          }
                        }
                                                
                                
                                                            Map<String, Object> gpsTrackerNotificationData = new HashMap<>();
                        if(cursor.getString(24) != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                           gpsTrackerNotificationData = gson.fromJson(cursor.getString(24), Map.class);
                          if(gpsTrackerNotificationData != null){
                            gpsTrackerNotificationData = (Map<String, Object>)gpsTrackerNotificationData;
                            hashMap.put("gpsTrackerNotification", gpsTrackerNotificationData);
                          }
                        }
                                                
                                
                                                            Map<String, Object> driverLicenceProofData = new HashMap<>();
                        if(cursor.getString(25) != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                           driverLicenceProofData = gson.fromJson(cursor.getString(25), Map.class);
                          if(driverLicenceProofData != null){
                            driverLicenceProofData = (Map<String, Object>)driverLicenceProofData;
                            hashMap.put("driverLicenceProof", driverLicenceProofData);
                          }
                        }
                                                
                                
                                                            String bloodGroupData = "";
                        if(cursor.getString(26) != null){
                          bloodGroupData = cursor.getString(26);
                          if(bloodGroupData != null){
                            bloodGroupData = (String)bloodGroupData;
                            hashMap.put("bloodGroup", bloodGroupData);
                          }
                        }
                                                
                                
                                                            String googleRefreshTokenData = "";
                        if(cursor.getString(27) != null){
                          googleRefreshTokenData = cursor.getString(27);
                          if(googleRefreshTokenData != null){
                            googleRefreshTokenData = (String)googleRefreshTokenData;
                            hashMap.put("googleRefreshToken", googleRefreshTokenData);
                          }
                        }
                                                
                                
                                                            String realmData = "";
                        if(cursor.getString(28) != null){
                          realmData = cursor.getString(28);
                          if(realmData != null){
                            realmData = realmData.toString();
                            hashMap.put("realm", realmData);
                          }
                        }
                                                
                                
                                                            String passwordData = "";
                        if(cursor.getString(29) != null){
                          passwordData = cursor.getString(29);
                          if(passwordData != null){
                            passwordData = passwordData.toString();
                            hashMap.put("password", passwordData);
                          }
                        }
                                                
                                
                                                            String credentialsData = "";
                        if(cursor.getString(30) != null){
                          credentialsData = cursor.getString(30);
                          if(credentialsData != null){
                            credentialsData = credentialsData.toString();
                            hashMap.put("credentials", credentialsData);
                          }
                        }
                                                
                                
                                                            String challengesData = "";
                        if(cursor.getString(31) != null){
                          challengesData = cursor.getString(31);
                          if(challengesData != null){
                            challengesData = challengesData.toString();
                            hashMap.put("challenges", challengesData);
                          }
                        }
                                                
                                
                                                            String emailVerifiedData = "";
                        if(cursor.getString(32) != null){
                          emailVerifiedData = cursor.getString(32);
                          if(emailVerifiedData != null){
                            emailVerifiedData = emailVerifiedData.toString();
                            hashMap.put("emailVerified", emailVerifiedData);
                          }
                        }
                                                
                                
                                                            String verificationTokenData = "";
                        if(cursor.getString(33) != null){
                          verificationTokenData = cursor.getString(33);
                          if(verificationTokenData != null){
                            verificationTokenData = verificationTokenData.toString();
                            hashMap.put("verificationToken", verificationTokenData);
                          }
                        }
                                                
                                
                                                            String createdData = "";
                        if(cursor.getString(34) != null){
                          createdData = cursor.getString(34);
                          if(createdData != null){
                            createdData = createdData.toString();
                            hashMap.put("created", createdData);
                          }
                        }
                                                
                                
                                                            String lastUpdatedData = "";
                        if(cursor.getString(35) != null){
                          lastUpdatedData = cursor.getString(35);
                          if(lastUpdatedData != null){
                            lastUpdatedData = lastUpdatedData.toString();
                            hashMap.put("lastUpdated", lastUpdatedData);
                          }
                        }
                                                
                                
                                                            String idData = "";
                        if(cursor.getString(36) != null){
                          idData = cursor.getString(36);
                          if(idData != null){
                            idData = idData.toString();
                            hashMap.put("id", idData);
                          }
                        }
                                                
                                
                                                            String countryIdData = "";
                        if(cursor.getString(37) != null){
                          countryIdData = cursor.getString(37);
                          if(countryIdData != null){
                            countryIdData = countryIdData.toString();
                            hashMap.put("countryId", countryIdData);
                          }
                        }
                                                
                                
                                                            String cityIdData = "";
                        if(cursor.getString(38) != null){
                          cityIdData = cursor.getString(38);
                          if(cityIdData != null){
                            cityIdData = cityIdData.toString();
                            hashMap.put("cityId", cityIdData);
                          }
                        }
                                                
                                
                                                            String workshopIdData = "";
                        if(cursor.getString(39) != null){
                          workshopIdData = cursor.getString(39);
                          if(workshopIdData != null){
                            workshopIdData = workshopIdData.toString();
                            hashMap.put("workshopId", workshopIdData);
                          }
                        }
                                                
                                
                                                            String busIdData = "";
                        if(cursor.getString(40) != null){
                          busIdData = cursor.getString(40);
                          if(busIdData != null){
                            busIdData = busIdData.toString();
                            hashMap.put("busId", busIdData);
                          }
                        }
                                                
                                
                                                            String planTypeIdData = "";
                        if(cursor.getString(41) != null){
                          planTypeIdData = cursor.getString(41);
                          if(planTypeIdData != null){
                            planTypeIdData = planTypeIdData.toString();
                            hashMap.put("planTypeId", planTypeIdData);
                          }
                        }
                                                
                  //End for loop
         
          

        return hashMap;
    }//parseCursor



    public void upsert__db(String id, Customer model){
        if(count__db(id) != 0){
            update__db(id, model);
        }else{
            insert__db(id, model);
        }
    } //upsert__db



    // Getting All Contacts
    public DataList<Customer>  getAll__db() {
        DataList<Customer> modelList = new DataList<Customer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `Customer`";

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<Customer>) modelList;
        }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    CustomerRepository repo = restAdapter.createRepository(CustomerRepository.class);
                    repo.addStorage(context);
                    modelList.add((Customer)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<Customer>) modelList;
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
    public DataList<Customer>  getAll__db(HashMap<String, Object> whereKeyValue) {
        return getAll__db(whereKeyValue, null, 0, 0);
    }



    // Getting All Data where and sort column according to date wise..
    public DataList<Customer>  getAll__db(HashMap<String, Object> whereKeyValue, String orderBy, int limit, int skip) {
        DataList<Customer> modelList = new DataList<Customer>();
        String whereQuery = getWhereQuery(whereKeyValue);
        String selectQuery;
        if(orderBy != null){
            selectQuery = "SELECT  * FROM `Customer` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                selectQuery = selectQuery +  " " + " LIMIT " + limit + " OFFSET " + skip;
            }else{
                selectQuery = selectQuery +  " " + " LIMIT -1 OFFSET " + skip;
            }
        }else{
            if(limit != 0){
                // Select All Query
                selectQuery = "SELECT  * FROM Customer " + whereQuery + " LIMIT " + limit + " OFFSET " + skip;
            }else{
                selectQuery = "SELECT  * FROM Customer " + whereQuery  + " LIMIT -1 OFFSET " + skip;
            }
        }

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<Customer>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    CustomerRepository repo = restAdapter.createRepository(CustomerRepository.class);
                    repo.addStorage(context);
                    modelList.add((Customer)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }
        cursor.close();
        // return contact list
        return (DataList<Customer>) modelList;
    }



    // Getting All Data where
    public DataList<Customer>  getAll__db(HashMap<String, Object> whereKeyValue, int limit, int skip) {
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
            countQuery = "SELECT  * FROM `Customer` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                countQuery = countQuery +  " " + " LIMIT " + limit + " OFFSET " + skip;
            }else{
                countQuery = countQuery + " LIMIT -1  OFFSET " + skip;
            }
        }else{
            if(limit != 0){
                // Select All Query
                countQuery = "SELECT  * FROM `Customer` " + whereQuery + " LIMIT " + limit + " OFFSET " + skip;
            }else{
                countQuery = "SELECT  * FROM `Customer` " + whereQuery + " LIMIT -1 OFFSET " + skip;
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
            countQuery = "SELECT  * FROM `Customer` " + whereQuery + " LIMIT " + limit + " OFFSET " + skip;
        }else{
            countQuery = "SELECT  * FROM `Customer` " + whereQuery + " LIMIT -1 OFFSET " + skip;
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
            db.update("`Customer`", values, "_DATA_UPDATED = 1 AND " + where, null);
    }


    // Delete Old data with where clause
    public void deleteOldData__db(final HashMap<String, Object> whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      String where = getWhere(whereKeyValue);
      db.delete("`Customer`", "_DATA_UPDATED = 0 AND " + where , null);
    }





    // Deleting by whereKeyValue filter data present..
    public void delete__db(final HashMap<String, Object> whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      String where = getWhere(whereKeyValue);
      db.delete("`Customer`", where , null);
    }







    // Getting All Data where
    public DataList<Customer>  getAll__db(String whereKey, String whereKeyValue) {
        DataList<Customer> modelList = new DataList<Customer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `Customer` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<Customer>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    CustomerRepository repo = restAdapter.createRepository(CustomerRepository.class);
                    repo.addStorage(context);
                    modelList.add((Customer)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }
        cursor.close();
        return (DataList<Customer>) modelList;
    }



    /**
     * Check count of database.
     * @param whereKey
     * @param whereKeyValue
     * @return
     */
    public int count__db(String whereKey, String whereKeyValue){
      String countQuery = "SELECT  * FROM `Customer` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;
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
      db.update("`Customer`", values, "_DATA_UPDATED = 1 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
    }


    // Delete Old data with where clause
    public void deleteOldData__db(final String whereKey, final String whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      db.delete("`Customer`", "_DATA_UPDATED = 0 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
    }


    //Update multiple data at once..
    public void updateAll__db(final HashMap<String, Object> whereKeyValue, final Customer _modelData ){
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      ContentValues values = getContentValues(_modelData);
      String where = getWhere(whereKeyValue);
      db.update("`Customer`", values, where, null);
    }




    // Deleting by whereKey and whereKeyValue
    public void delete__db(final String whereKey, final String whereKeyValue) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      db.delete(TABLE, whereKey + " = ?", new String[]{whereKeyValue});
    }



    // Updating single contact
    public void update__db(final String id,   final Customer _modelData) {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      ContentValues values = getContentValues(_modelData);
      // updating row
      db.update("`Customer`", values, "id = ?", new String[] { id });
    }


    // Updating updated data property to new contact
    public void checkOldData__db() {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put("_DATA_UPDATED", 0);
      // updating row
      db.update("`Customer`", values, "_DATA_UPDATED = 1", null);
    }


    // Delete Old data
    public void deleteOldData__db() {
      SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
      db.delete("`Customer`", "_DATA_UPDATED = 0", null);
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
