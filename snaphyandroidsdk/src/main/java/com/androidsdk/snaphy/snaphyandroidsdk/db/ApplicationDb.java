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

import com.androidsdk.snaphy.snaphyandroidsdk.models.Application;
//Import self repository..
import com.androidsdk.snaphy.snaphyandroidsdk.repository.ApplicationRepository;
import com.strongloop.android.loopback.RestAdapter;

/**
* Created by snaphy on 1/2/2017.
*/

public class ApplicationDb{

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

  public ApplicationDb(Context context, String DATABASE_NAME, RestAdapter restAdapter){
    //super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
    this.restAdapter = restAdapter;
    TABLE = "Application";
    this.DATABASE_NAME = DATABASE_NAME;
    SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
    DbHandler.getInstance(context, DATABASE_NAME).onCreate(db);
  }


    public void insert__db (final String id, final Application _modelData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                // Inserting Row
                ContentValues values = getContentValues(_modelData);
                db.insert("`Application`", null, values);
                //db.close(); // Closing database connection
            }
        }).start();

    }





    public ContentValues getContentValues(Application _modelData){
      ContentValues values = new ContentValues();
                       
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
                        String nameData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getName");
                              if(method.invoke(_modelData) != null){
                                //nameData = _modelData.getName().toString();
                                nameData = (String) method.invoke(_modelData);
                                values.put("`name`", nameData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String descriptionData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getDescription");
                              if(method.invoke(_modelData) != null){
                                //descriptionData = _modelData.getDescription().toString();
                                descriptionData = (String) method.invoke(_modelData);
                                values.put("`description`", descriptionData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String iconData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getIcon");
                              if(method.invoke(_modelData) != null){
                                //iconData = _modelData.getIcon().toString();
                                iconData = (String) method.invoke(_modelData);
                                values.put("`icon`", iconData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String ownerData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getOwner");
                              if(method.invoke(_modelData) != null){
                                //ownerData = _modelData.getOwner().toString();
                                ownerData = (String) method.invoke(_modelData);
                                values.put("`owner`", ownerData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                  String collaboratorsData = "";
                  if(_modelData.getCollaborators() != null){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                    Gson gson = gsonBuilder.create();
                    collaboratorsData = gson.toJson(_modelData.getCollaborators(), DataList.class);
                    values.put("`collaborators`", collaboratorsData);
                  }
              
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String emailData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getEmail");
                              if(method.invoke(_modelData) != null){
                                //emailData = _modelData.getEmail().toString();
                                emailData = (String) method.invoke(_modelData);
                                values.put("`email`", emailData);
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
                        String urlData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getUrl");
                              if(method.invoke(_modelData) != null){
                                //urlData = _modelData.getUrl().toString();
                                urlData = (String) method.invoke(_modelData);
                                values.put("`url`", urlData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                  String callbackUrlsData = "";
                  if(_modelData.getCallbackUrls() != null){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                    Gson gson = gsonBuilder.create();
                    callbackUrlsData = gson.toJson(_modelData.getCallbackUrls(), DataList.class);
                    values.put("`callbackUrls`", callbackUrlsData);
                  }
              
                                
                                  String permissionsData = "";
                  if(_modelData.getPermissions() != null){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                    Gson gson = gsonBuilder.create();
                    permissionsData = gson.toJson(_modelData.getPermissions(), DataList.class);
                    values.put("`permissions`", permissionsData);
                  }
              
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String clientKeyData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getClientKey");
                              if(method.invoke(_modelData) != null){
                                //clientKeyData = _modelData.getClientKey().toString();
                                clientKeyData = (String) method.invoke(_modelData);
                                values.put("`clientKey`", clientKeyData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String javaScriptKeyData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getJavaScriptKey");
                              if(method.invoke(_modelData) != null){
                                //javaScriptKeyData = _modelData.getJavaScriptKey().toString();
                                javaScriptKeyData = (String) method.invoke(_modelData);
                                values.put("`javaScriptKey`", javaScriptKeyData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String restApiKeyData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getRestApiKey");
                              if(method.invoke(_modelData) != null){
                                //restApiKeyData = _modelData.getRestApiKey().toString();
                                restApiKeyData = (String) method.invoke(_modelData);
                                values.put("`restApiKey`", restApiKeyData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String windowsKeyData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getWindowsKey");
                              if(method.invoke(_modelData) != null){
                                //windowsKeyData = _modelData.getWindowsKey().toString();
                                windowsKeyData = (String) method.invoke(_modelData);
                                values.put("`windowsKey`", windowsKeyData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String masterKeyData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getMasterKey");
                              if(method.invoke(_modelData) != null){
                                //masterKeyData = _modelData.getMasterKey().toString();
                                masterKeyData = (String) method.invoke(_modelData);
                                values.put("`masterKey`", masterKeyData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String pushSettingsData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getPushSettings");
                              if(method.invoke(_modelData) != null){
                                //pushSettingsData = _modelData.getPushSettings().toString();
                                pushSettingsData = (String) method.invoke(_modelData);
                                values.put("`pushSettings`", pushSettingsData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String authenticationEnabledData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getAuthenticationEnabled");
                              if(method.invoke(_modelData) != null){
                                //authenticationEnabledData = _modelData.getAuthenticationEnabled().toString();
                                authenticationEnabledData = (String) method.invoke(_modelData);
                                values.put("`authenticationEnabled`", authenticationEnabledData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                                            //http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
                        String anonymousAllowedData = "";
                        try {
                              Method method = _modelData.getClass().getMethod("getAnonymousAllowed");
                              if(method.invoke(_modelData) != null){
                                //anonymousAllowedData = _modelData.getAnonymousAllowed().toString();
                                anonymousAllowedData = (String) method.invoke(_modelData);
                                values.put("`anonymousAllowed`", anonymousAllowedData);
                              }
                        } catch (Exception e) {
                          Log.e("Database Error", e.toString());
                        }

                                  
                                
                                  String authenticationSchemesData = "";
                  if(_modelData.getAuthenticationSchemes() != null){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                    Gson gson = gsonBuilder.create();
                    authenticationSchemesData = gson.toJson(_modelData.getAuthenticationSchemes(), DataList.class);
                    values.put("`authenticationSchemes`", authenticationSchemesData);
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

                                  
                  
        
          
        //Add the updated data property value to be 1
        values.put("`_DATA_UPDATED`", 1);
        return values;
    }



    // Getting single c
    public   Application get__db(String id) {
        if (id != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("Application", null, "id=?", new String[]{id}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);
                    cursor.close();
                    //db.close(); // Closing database connection
                    if (hashMap != null) {
                        ApplicationRepository repo = restAdapter.createRepository(ApplicationRepository.class);
                        repo.addStorage(context);
                        return (Application)repo.createObject(hashMap);
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
    public   Application get__db(String whereKey, String whereKeyValue) {
        if (whereKeyValue != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("`Application`", null, "`" + whereKey + "` =?", new String[]{whereKeyValue}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);

                    cursor.close();
                    //db.close(); // Closing database connection

                    if (hashMap != null) {
                        ApplicationRepository repo = restAdapter.createRepository(ApplicationRepository.class);
                        repo.addStorage(context);
                        return (Application)repo.createObject(hashMap);
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

                      
                                                            String idData = "";
                        if(cursor.getString(0) != null){
                          idData = cursor.getString(0);
                          if(idData != null){
                            idData = idData.toString();
                            hashMap.put("id", idData);
                          }
                        }
                                                
                                
                                                            String realmData = "";
                        if(cursor.getString(1) != null){
                          realmData = cursor.getString(1);
                          if(realmData != null){
                            realmData = realmData.toString();
                            hashMap.put("realm", realmData);
                          }
                        }
                                                
                                
                                                            String nameData = "";
                        if(cursor.getString(2) != null){
                          nameData = cursor.getString(2);
                          if(nameData != null){
                            nameData = nameData.toString();
                            hashMap.put("name", nameData);
                          }
                        }
                                                
                                
                                                            String descriptionData = "";
                        if(cursor.getString(3) != null){
                          descriptionData = cursor.getString(3);
                          if(descriptionData != null){
                            descriptionData = descriptionData.toString();
                            hashMap.put("description", descriptionData);
                          }
                        }
                                                
                                
                                                            String iconData = "";
                        if(cursor.getString(4) != null){
                          iconData = cursor.getString(4);
                          if(iconData != null){
                            iconData = iconData.toString();
                            hashMap.put("icon", iconData);
                          }
                        }
                                                
                                
                                                            String ownerData = "";
                        if(cursor.getString(5) != null){
                          ownerData = cursor.getString(5);
                          if(ownerData != null){
                            ownerData = ownerData.toString();
                            hashMap.put("owner", ownerData);
                          }
                        }
                                                
                                
                                  DataList<Object> collaboratorsData = new DataList<>();
                  if(cursor.getString(6) != null){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                    Gson gson = gsonBuilder.create();
                    collaboratorsData = gson.fromJson(cursor.getString(6), DataList.class);
                    if(collaboratorsData != null){
                      collaboratorsData = (DataList<Object>)collaboratorsData;
                      hashMap.put("collaborators", collaboratorsData);
                    }
                  }
                            
                                
                                                            String emailData = "";
                        if(cursor.getString(7) != null){
                          emailData = cursor.getString(7);
                          if(emailData != null){
                            emailData = emailData.toString();
                            hashMap.put("email", emailData);
                          }
                        }
                                                
                                
                                                            String emailVerifiedData = "";
                        if(cursor.getString(8) != null){
                          emailVerifiedData = cursor.getString(8);
                          if(emailVerifiedData != null){
                            emailVerifiedData = emailVerifiedData.toString();
                            hashMap.put("emailVerified", emailVerifiedData);
                          }
                        }
                                                
                                
                                                            String urlData = "";
                        if(cursor.getString(9) != null){
                          urlData = cursor.getString(9);
                          if(urlData != null){
                            urlData = urlData.toString();
                            hashMap.put("url", urlData);
                          }
                        }
                                                
                                
                                  DataList<Object> callbackUrlsData = new DataList<>();
                  if(cursor.getString(10) != null){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                    Gson gson = gsonBuilder.create();
                    callbackUrlsData = gson.fromJson(cursor.getString(10), DataList.class);
                    if(callbackUrlsData != null){
                      callbackUrlsData = (DataList<Object>)callbackUrlsData;
                      hashMap.put("callbackUrls", callbackUrlsData);
                    }
                  }
                            
                                
                                  DataList<Object> permissionsData = new DataList<>();
                  if(cursor.getString(11) != null){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                    Gson gson = gsonBuilder.create();
                    permissionsData = gson.fromJson(cursor.getString(11), DataList.class);
                    if(permissionsData != null){
                      permissionsData = (DataList<Object>)permissionsData;
                      hashMap.put("permissions", permissionsData);
                    }
                  }
                            
                                
                                                            String clientKeyData = "";
                        if(cursor.getString(12) != null){
                          clientKeyData = cursor.getString(12);
                          if(clientKeyData != null){
                            clientKeyData = clientKeyData.toString();
                            hashMap.put("clientKey", clientKeyData);
                          }
                        }
                                                
                                
                                                            String javaScriptKeyData = "";
                        if(cursor.getString(13) != null){
                          javaScriptKeyData = cursor.getString(13);
                          if(javaScriptKeyData != null){
                            javaScriptKeyData = javaScriptKeyData.toString();
                            hashMap.put("javaScriptKey", javaScriptKeyData);
                          }
                        }
                                                
                                
                                                            String restApiKeyData = "";
                        if(cursor.getString(14) != null){
                          restApiKeyData = cursor.getString(14);
                          if(restApiKeyData != null){
                            restApiKeyData = restApiKeyData.toString();
                            hashMap.put("restApiKey", restApiKeyData);
                          }
                        }
                                                
                                
                                                            String windowsKeyData = "";
                        if(cursor.getString(15) != null){
                          windowsKeyData = cursor.getString(15);
                          if(windowsKeyData != null){
                            windowsKeyData = windowsKeyData.toString();
                            hashMap.put("windowsKey", windowsKeyData);
                          }
                        }
                                                
                                
                                                            String masterKeyData = "";
                        if(cursor.getString(16) != null){
                          masterKeyData = cursor.getString(16);
                          if(masterKeyData != null){
                            masterKeyData = masterKeyData.toString();
                            hashMap.put("masterKey", masterKeyData);
                          }
                        }
                                                
                                
                                                            String pushSettingsData = "";
                        if(cursor.getString(17) != null){
                          pushSettingsData = cursor.getString(17);
                          if(pushSettingsData != null){
                            pushSettingsData = pushSettingsData.toString();
                            hashMap.put("pushSettings", pushSettingsData);
                          }
                        }
                                                
                                
                                                            String authenticationEnabledData = "";
                        if(cursor.getString(18) != null){
                          authenticationEnabledData = cursor.getString(18);
                          if(authenticationEnabledData != null){
                            authenticationEnabledData = authenticationEnabledData.toString();
                            hashMap.put("authenticationEnabled", authenticationEnabledData);
                          }
                        }
                                                
                                
                                                            String anonymousAllowedData = "";
                        if(cursor.getString(19) != null){
                          anonymousAllowedData = cursor.getString(19);
                          if(anonymousAllowedData != null){
                            anonymousAllowedData = anonymousAllowedData.toString();
                            hashMap.put("anonymousAllowed", anonymousAllowedData);
                          }
                        }
                                                
                                
                                  DataList<Object> authenticationSchemesData = new DataList<>();
                  if(cursor.getString(20) != null){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                    Gson gson = gsonBuilder.create();
                    authenticationSchemesData = gson.fromJson(cursor.getString(20), DataList.class);
                    if(authenticationSchemesData != null){
                      authenticationSchemesData = (DataList<Object>)authenticationSchemesData;
                      hashMap.put("authenticationSchemes", authenticationSchemesData);
                    }
                  }
                            
                                
                                                            String statusData = "";
                        if(cursor.getString(21) != null){
                          statusData = cursor.getString(21);
                          if(statusData != null){
                            statusData = statusData.toString();
                            hashMap.put("status", statusData);
                          }
                        }
                                                
                                
                                                            String createdData = "";
                        if(cursor.getString(22) != null){
                          createdData = cursor.getString(22);
                          if(createdData != null){
                            createdData = createdData.toString();
                            hashMap.put("created", createdData);
                          }
                        }
                                                
                                
                                                            String modifiedData = "";
                        if(cursor.getString(23) != null){
                          modifiedData = cursor.getString(23);
                          if(modifiedData != null){
                            modifiedData = modifiedData.toString();
                            hashMap.put("modified", modifiedData);
                          }
                        }
                                                
                  //End for loop
         
          

        return hashMap;
    }//parseCursor



    public void upsert__db(String id, Application model){
        if(count__db(id) != 0){
            update__db(id, model);
        }else{
            insert__db(id, model);
        }
    } //upsert__db



    // Getting All Contacts
    public DataList<Application>  getAll__db() {
        DataList<Application> modelList = new DataList<Application>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `Application`";

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<Application>) modelList;
        }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    ApplicationRepository repo = restAdapter.createRepository(ApplicationRepository.class);
                    repo.addStorage(context);
                    modelList.add((Application)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<Application>) modelList;
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
    public DataList<Application>  getAll__db(HashMap<String, Object> whereKeyValue) {
        return getAll__db(whereKeyValue, null, 0);
    }



    // Getting All Data where and sort column according to date wise..
    public DataList<Application>  getAll__db(HashMap<String, Object> whereKeyValue, String orderBy, int limit) {
        DataList<Application> modelList = new DataList<Application>();
        String whereQuery = getWhereQuery(whereKeyValue);
        String selectQuery;
        if(orderBy != null){
            selectQuery = "SELECT  * FROM `Application` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                selectQuery = selectQuery +  " " + " LIMIT " + limit;
            }
        }else{
            if(limit != 0){
                // Select All Query
                selectQuery = "SELECT  * FROM Application " + whereQuery + " LIMIT " + limit;
            }else{
                selectQuery = "SELECT  * FROM Application " + whereQuery;
            }
        }

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<Application>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    ApplicationRepository repo = restAdapter.createRepository(ApplicationRepository.class);
                    repo.addStorage(context);
                    modelList.add((Application)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<Application>) modelList;
    }


    // Getting All Data where
    public DataList<Application>  getAll__db(HashMap<String, Object> whereKeyValue, int limit) {
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
            countQuery = "SELECT  * FROM `Application` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                countQuery = countQuery +  " " + " LIMIT " + limit;
            }
        }else{
            if(limit != 0){
                // Select All Query
                countQuery = "SELECT  * FROM `Application` " + whereQuery + " LIMIT " + limit;
            }else{
                countQuery = "SELECT  * FROM `Application` " + whereQuery;
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
            countQuery = "SELECT  * FROM `Application` " + whereQuery + " LIMIT " + limit;
        }else{
            countQuery = "SELECT  * FROM `Application` " + whereQuery;
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
                db.update("`Application`", values, "_DATA_UPDATED = 1 AND " + where, null);
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
                db.delete("`Application`", "_DATA_UPDATED = 0 AND " + where , null);
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
                db.delete("`Application`", where , null);
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        }).start();
    }







    // Getting All Data where
    public DataList<Application>  getAll__db(String whereKey, String whereKeyValue) {
        DataList<Application> modelList = new DataList<Application>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `Application` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<Application>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    ApplicationRepository repo = restAdapter.createRepository(ApplicationRepository.class);
                    repo.addStorage(context);
                    modelList.add((Application)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<Application>) modelList;
    }



    /**
     * Check count of database.
     * @param whereKey
     * @param whereKeyValue
     * @return
     */
    public int count__db(String whereKey, String whereKeyValue){
        String countQuery = "SELECT  * FROM `Application` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;
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
                db.update("`Application`", values, "_DATA_UPDATED = 1 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
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
                db.delete("`Application`", "_DATA_UPDATED = 0 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();

    }


    //Update multiple data at once..
    public void updateAll__db(final HashMap<String, Object> whereKeyValue, final Application _modelData ){
      new Thread(new Runnable(){
        @Override
        public void run(){
          SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
          db.beginTransaction();
          ContentValues values = getContentValues(_modelData);
          String where = getWhere(whereKeyValue);
          db.update("`Application`", values, where, null);
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
    public void update__db(final String id,   final Application _modelData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                db.beginTransaction();
                ContentValues values = getContentValues(_modelData);
                // updating row
                db.update("`Application`", values, "id = ?",
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
                db.update("`Application`", values, "_DATA_UPDATED = 1", null);
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
                db.delete("`Application`", "_DATA_UPDATED = 0", null);
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
