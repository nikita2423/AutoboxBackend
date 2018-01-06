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

import com.androidsdk.snaphy.snaphyandroidsdk.models.GpsPacketData;
//Import self repository..
import com.androidsdk.snaphy.snaphyandroidsdk.repository.GpsPacketDataRepository;
import com.strongloop.android.loopback.RestAdapter;

/**
* Created by snaphy on 1/2/2017.
*/

public class GpsPacketDataDb{

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

  public GpsPacketDataDb(Context context, String DATABASE_NAME, RestAdapter restAdapter){
    //super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
    this.restAdapter = restAdapter;
    TABLE = "GpsPacketData";
    this.DATABASE_NAME = DATABASE_NAME;
    SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
    DbHandler.getInstance(context, DATABASE_NAME).onCreate(db);
  }


    public void insert__db (final String id, final GpsPacketData _modelData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                // Inserting Row
                ContentValues values = getContentValues(_modelData);
                db.insert("`GpsPacketData`", null, values);
                //db.close(); // Closing database connection
            }
        }).start();

    }





    public ContentValues getContentValues(GpsPacketData _modelData){
      ContentValues values = new ContentValues();
                       
                                                            String clientIdData = "";
                        if(_modelData.getClientId() != null){
                          clientIdData = _modelData.getClientId().toString();
                          values.put("`clientId`", clientIdData);
                        }
                                  
                                
                                                            String deviceIMEIData = "";
                        if(_modelData.getDeviceIMEI() != null){
                          deviceIMEIData = _modelData.getDeviceIMEI().toString();
                          values.put("`deviceIMEI`", deviceIMEIData);
                        }
                                  
                                
                                                            double eventCodeData;
                        eventCodeData = (double)_modelData.getEventCode();
                        values.put("`eventCode`", eventCodeData);
                                  
                                
                                                            int isStoredPacketData = 0;
                        if(_modelData.getIsStoredPacket()){
                          isStoredPacketData = 1;
                        }else{
                          isStoredPacketData = 0;
                        }
                        values.put("`isStoredPacket`", isStoredPacketData);
                                  
                                
                                                            String eventTypeData = "";
                        if(_modelData.getEventType() != null){
                          eventTypeData = _modelData.getEventType().toString();
                          values.put("`eventType`", eventTypeData);
                        }
                                  
                                
                                                            String latitudeData = "";
                        if(_modelData.getLatitude() != null){
                          latitudeData = _modelData.getLatitude().toString();
                          values.put("`latitude`", latitudeData);
                        }
                                  
                                
                                                            String longitudeData = "";
                        if(_modelData.getLongitude() != null){
                          longitudeData = _modelData.getLongitude().toString();
                          values.put("`longitude`", longitudeData);
                        }
                                  
                                
                                                            String latlngData = "";
                        if(_modelData.getLatlng() != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                          latlngData = gson.toJson(_modelData.getLatlng(), HashMap.class);
                          values.put("`latlng`", latlngData);
                        }
                                  
                                
                                                            String eventDateData = "";
                        if(_modelData.getEventDate() != null){
                          eventDateData = _modelData.getEventDate().toString();
                          values.put("`eventDate`", eventDateData);
                        }
                                  
                                
                                                            String gpsStatusData = "";
                        if(_modelData.getGpsStatus() != null){
                          gpsStatusData = _modelData.getGpsStatus().toString();
                          values.put("`gpsStatus`", gpsStatusData);
                        }
                                  
                                
                                                            double gmsSignalData;
                        gmsSignalData = (double)_modelData.getGmsSignal();
                        values.put("`gmsSignal`", gmsSignalData);
                                  
                                
                                                            double speedData;
                        speedData = (double)_modelData.getSpeed();
                        values.put("`speed`", speedData);
                                  
                                
                                                            String accumulatedDistanceData = "";
                        if(_modelData.getAccumulatedDistance() != null){
                          accumulatedDistanceData = _modelData.getAccumulatedDistance().toString();
                          values.put("`accumulatedDistance`", accumulatedDistanceData);
                        }
                                  
                                
                                                            double courseInDegreeData;
                        courseInDegreeData = (double)_modelData.getCourseInDegree();
                        values.put("`courseInDegree`", courseInDegreeData);
                                  
                                
                                                            double satelliteConnectedData;
                        satelliteConnectedData = (double)_modelData.getSatelliteConnected();
                        values.put("`satelliteConnected`", satelliteConnectedData);
                                  
                                
                                                            double hdopData;
                        hdopData = (double)_modelData.getHdop();
                        values.put("`hdop`", hdopData);
                                  
                                
                                                            double voltageEquivalentData;
                        voltageEquivalentData = (double)_modelData.getVoltageEquivalent();
                        values.put("`voltageEquivalent`", voltageEquivalentData);
                                  
                                
                                                            String digitalInput1Data = "";
                        if(_modelData.getDigitalInput1() != null){
                          digitalInput1Data = _modelData.getDigitalInput1().toString();
                          values.put("`digitalInput1`", digitalInput1Data);
                        }
                                  
                                
                                                            String caseStatusData = "";
                        if(_modelData.getCaseStatus() != null){
                          caseStatusData = _modelData.getCaseStatus().toString();
                          values.put("`caseStatus`", caseStatusData);
                        }
                                  
                                
                                                            int isOverSpeedStartedData = 0;
                        if(_modelData.getIsOverSpeedStarted()){
                          isOverSpeedStartedData = 1;
                        }else{
                          isOverSpeedStartedData = 0;
                        }
                        values.put("`isOverSpeedStarted`", isOverSpeedStartedData);
                                  
                                
                                                            int isOverSpeedEndedData = 0;
                        if(_modelData.getIsOverSpeedEnded()){
                          isOverSpeedEndedData = 1;
                        }else{
                          isOverSpeedEndedData = 0;
                        }
                        values.put("`isOverSpeedEnded`", isOverSpeedEndedData);
                                  
                                
                                                            int immobilizerVoilationAlertData = 0;
                        if(_modelData.getImmobilizerVoilationAlert()){
                          immobilizerVoilationAlertData = 1;
                        }else{
                          immobilizerVoilationAlertData = 0;
                        }
                        values.put("`immobilizerVoilationAlert`", immobilizerVoilationAlertData);
                                  
                                
                                                            String batteryStatusData = "";
                        if(_modelData.getBatteryStatus() != null){
                          batteryStatusData = _modelData.getBatteryStatus().toString();
                          values.put("`batteryStatus`", batteryStatusData);
                        }
                                  
                                
                                                            String digitalInput2Data = "";
                        if(_modelData.getDigitalInput2() != null){
                          digitalInput2Data = _modelData.getDigitalInput2().toString();
                          values.put("`digitalInput2`", digitalInput2Data);
                        }
                                  
                                
                                                            String ignitionStatusData = "";
                        if(_modelData.getIgnitionStatus() != null){
                          ignitionStatusData = _modelData.getIgnitionStatus().toString();
                          values.put("`ignitionStatus`", ignitionStatusData);
                        }
                                  
                                
                                                            int internalBatteryLowAlertData = 0;
                        if(_modelData.getInternalBatteryLowAlert()){
                          internalBatteryLowAlertData = 1;
                        }else{
                          internalBatteryLowAlertData = 0;
                        }
                        values.put("`internalBatteryLowAlert`", internalBatteryLowAlertData);
                                  
                                
                                                            int anglePollingBitData = 0;
                        if(_modelData.getAnglePollingBit()){
                          anglePollingBitData = 1;
                        }else{
                          anglePollingBitData = 0;
                        }
                        values.put("`anglePollingBit`", anglePollingBitData);
                                  
                                
                                                            String digitalOutput1StatusData = "";
                        if(_modelData.getDigitalOutput1Status() != null){
                          digitalOutput1StatusData = _modelData.getDigitalOutput1Status().toString();
                          values.put("`digitalOutput1Status`", digitalOutput1StatusData);
                        }
                                  
                                
                                                            int isHarshAccelerationDetectedData = 0;
                        if(_modelData.getIsHarshAccelerationDetected()){
                          isHarshAccelerationDetectedData = 1;
                        }else{
                          isHarshAccelerationDetectedData = 0;
                        }
                        values.put("`isHarshAccelerationDetected`", isHarshAccelerationDetectedData);
                                  
                                
                                                            int isHarshBrakingDetectedData = 0;
                        if(_modelData.getIsHarshBrakingDetected()){
                          isHarshBrakingDetectedData = 1;
                        }else{
                          isHarshBrakingDetectedData = 0;
                        }
                        values.put("`isHarshBrakingDetected`", isHarshBrakingDetectedData);
                                  
                                
                                                            String externalBatteryVoltageData = "";
                        if(_modelData.getExternalBatteryVoltage() != null){
                          externalBatteryVoltageData = _modelData.getExternalBatteryVoltage().toString();
                          values.put("`externalBatteryVoltage`", externalBatteryVoltageData);
                        }
                                  
                                
                                                            double internalBatteryVoltageData;
                        internalBatteryVoltageData = (double)_modelData.getInternalBatteryVoltage();
                        values.put("`internalBatteryVoltage`", internalBatteryVoltageData);
                                  
                                
                                                            String gpsPacketIdData = "";
                        if(_modelData.getGpsPacketId() != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                          gpsPacketIdData = gson.toJson(_modelData.getGpsPacketId(), HashMap.class);
                          values.put("`gpsPacketId`", gpsPacketIdData);
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

                                  
                  
        
          
        //Add the updated data property value to be 1
        values.put("`_DATA_UPDATED`", 1);
        return values;
    }



    // Getting single c
    public   GpsPacketData get__db(String id) {
        if (id != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("GpsPacketData", null, "id=?", new String[]{id}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);
                    cursor.close();
                    //db.close(); // Closing database connection
                    if (hashMap != null) {
                        GpsPacketDataRepository repo = restAdapter.createRepository(GpsPacketDataRepository.class);
                        repo.addStorage(context);
                        return (GpsPacketData)repo.createObject(hashMap);
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
    public   GpsPacketData get__db(String whereKey, String whereKeyValue) {
        if (whereKeyValue != null) {
            SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
            Cursor cursor = db.query("`GpsPacketData`", null, "`" + whereKey + "` =?", new String[]{whereKeyValue}, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst() || cursor.getCount() == 0){
                    return null;
                }else{
                    HashMap<String, Object> hashMap = parseCursor(cursor);

                    cursor.close();
                    //db.close(); // Closing database connection

                    if (hashMap != null) {
                        GpsPacketDataRepository repo = restAdapter.createRepository(GpsPacketDataRepository.class);
                        repo.addStorage(context);
                        return (GpsPacketData)repo.createObject(hashMap);
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

                      
                                                            String clientIdData = "";
                        if(cursor.getString(0) != null){
                          clientIdData = cursor.getString(0);
                          if(clientIdData != null){
                            clientIdData = (String)clientIdData;
                            hashMap.put("clientId", clientIdData);
                          }
                        }
                                                
                                
                                                            String deviceIMEIData = "";
                        if(cursor.getString(1) != null){
                          deviceIMEIData = cursor.getString(1);
                          if(deviceIMEIData != null){
                            deviceIMEIData = (String)deviceIMEIData;
                            hashMap.put("deviceIMEI", deviceIMEIData);
                          }
                        }
                                                
                                
                                                            double eventCodeData = (double)0;
                          eventCodeData = cursor.getInt(2);
                          eventCodeData = (double)eventCodeData;
                          hashMap.put("eventCode", eventCodeData);


                                                
                                
                                                            boolean isStoredPacketData = false;
                        int tempisStoredPacketData = cursor.getInt(3);
                        if( tempisStoredPacketData > 0){
                          isStoredPacketData = true;
                        }else{
                          isStoredPacketData = false;
                        }
                        hashMap.put("isStoredPacket", isStoredPacketData);
                                                
                                
                                                            String eventTypeData = "";
                        if(cursor.getString(4) != null){
                          eventTypeData = cursor.getString(4);
                          if(eventTypeData != null){
                            eventTypeData = (String)eventTypeData;
                            hashMap.put("eventType", eventTypeData);
                          }
                        }
                                                
                                
                                                            String latitudeData = "";
                        if(cursor.getString(5) != null){
                          latitudeData = cursor.getString(5);
                          if(latitudeData != null){
                            latitudeData = (String)latitudeData;
                            hashMap.put("latitude", latitudeData);
                          }
                        }
                                                
                                
                                                            String longitudeData = "";
                        if(cursor.getString(6) != null){
                          longitudeData = cursor.getString(6);
                          if(longitudeData != null){
                            longitudeData = (String)longitudeData;
                            hashMap.put("longitude", longitudeData);
                          }
                        }
                                                
                                
                                                            Map<String, Object> latlngData = new HashMap<>();
                        if(cursor.getString(7) != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                           latlngData = gson.fromJson(cursor.getString(7), Map.class);
                          if(latlngData != null){
                            latlngData = (Map<String, Object>)latlngData;
                            hashMap.put("latlng", latlngData);
                          }
                        }
                                                
                                
                                                            String eventDateData = "";
                        if(cursor.getString(8) != null){
                          eventDateData = cursor.getString(8);
                          if(eventDateData != null){
                            eventDateData = (String)eventDateData;
                            hashMap.put("eventDate", eventDateData);
                          }
                        }
                                                
                                
                                                            String gpsStatusData = "";
                        if(cursor.getString(9) != null){
                          gpsStatusData = cursor.getString(9);
                          if(gpsStatusData != null){
                            gpsStatusData = (String)gpsStatusData;
                            hashMap.put("gpsStatus", gpsStatusData);
                          }
                        }
                                                
                                
                                                            double gmsSignalData = (double)0;
                          gmsSignalData = cursor.getInt(10);
                          gmsSignalData = (double)gmsSignalData;
                          hashMap.put("gmsSignal", gmsSignalData);


                                                
                                
                                                            double speedData = (double)0;
                          speedData = cursor.getInt(11);
                          speedData = (double)speedData;
                          hashMap.put("speed", speedData);


                                                
                                
                                                            String accumulatedDistanceData = "";
                        if(cursor.getString(12) != null){
                          accumulatedDistanceData = cursor.getString(12);
                          if(accumulatedDistanceData != null){
                            accumulatedDistanceData = (String)accumulatedDistanceData;
                            hashMap.put("accumulatedDistance", accumulatedDistanceData);
                          }
                        }
                                                
                                
                                                            double courseInDegreeData = (double)0;
                          courseInDegreeData = cursor.getInt(13);
                          courseInDegreeData = (double)courseInDegreeData;
                          hashMap.put("courseInDegree", courseInDegreeData);


                                                
                                
                                                            double satelliteConnectedData = (double)0;
                          satelliteConnectedData = cursor.getInt(14);
                          satelliteConnectedData = (double)satelliteConnectedData;
                          hashMap.put("satelliteConnected", satelliteConnectedData);


                                                
                                
                                                            double hdopData = (double)0;
                          hdopData = cursor.getInt(15);
                          hdopData = (double)hdopData;
                          hashMap.put("hdop", hdopData);


                                                
                                
                                                            double voltageEquivalentData = (double)0;
                          voltageEquivalentData = cursor.getInt(16);
                          voltageEquivalentData = (double)voltageEquivalentData;
                          hashMap.put("voltageEquivalent", voltageEquivalentData);


                                                
                                
                                                            String digitalInput1Data = "";
                        if(cursor.getString(17) != null){
                          digitalInput1Data = cursor.getString(17);
                          if(digitalInput1Data != null){
                            digitalInput1Data = (String)digitalInput1Data;
                            hashMap.put("digitalInput1", digitalInput1Data);
                          }
                        }
                                                
                                
                                                            String caseStatusData = "";
                        if(cursor.getString(18) != null){
                          caseStatusData = cursor.getString(18);
                          if(caseStatusData != null){
                            caseStatusData = (String)caseStatusData;
                            hashMap.put("caseStatus", caseStatusData);
                          }
                        }
                                                
                                
                                                            boolean isOverSpeedStartedData = false;
                        int tempisOverSpeedStartedData = cursor.getInt(19);
                        if( tempisOverSpeedStartedData > 0){
                          isOverSpeedStartedData = true;
                        }else{
                          isOverSpeedStartedData = false;
                        }
                        hashMap.put("isOverSpeedStarted", isOverSpeedStartedData);
                                                
                                
                                                            boolean isOverSpeedEndedData = false;
                        int tempisOverSpeedEndedData = cursor.getInt(20);
                        if( tempisOverSpeedEndedData > 0){
                          isOverSpeedEndedData = true;
                        }else{
                          isOverSpeedEndedData = false;
                        }
                        hashMap.put("isOverSpeedEnded", isOverSpeedEndedData);
                                                
                                
                                                            boolean immobilizerVoilationAlertData = false;
                        int tempimmobilizerVoilationAlertData = cursor.getInt(21);
                        if( tempimmobilizerVoilationAlertData > 0){
                          immobilizerVoilationAlertData = true;
                        }else{
                          immobilizerVoilationAlertData = false;
                        }
                        hashMap.put("immobilizerVoilationAlert", immobilizerVoilationAlertData);
                                                
                                
                                                            String batteryStatusData = "";
                        if(cursor.getString(22) != null){
                          batteryStatusData = cursor.getString(22);
                          if(batteryStatusData != null){
                            batteryStatusData = (String)batteryStatusData;
                            hashMap.put("batteryStatus", batteryStatusData);
                          }
                        }
                                                
                                
                                                            String digitalInput2Data = "";
                        if(cursor.getString(23) != null){
                          digitalInput2Data = cursor.getString(23);
                          if(digitalInput2Data != null){
                            digitalInput2Data = (String)digitalInput2Data;
                            hashMap.put("digitalInput2", digitalInput2Data);
                          }
                        }
                                                
                                
                                                            String ignitionStatusData = "";
                        if(cursor.getString(24) != null){
                          ignitionStatusData = cursor.getString(24);
                          if(ignitionStatusData != null){
                            ignitionStatusData = (String)ignitionStatusData;
                            hashMap.put("ignitionStatus", ignitionStatusData);
                          }
                        }
                                                
                                
                                                            boolean internalBatteryLowAlertData = false;
                        int tempinternalBatteryLowAlertData = cursor.getInt(25);
                        if( tempinternalBatteryLowAlertData > 0){
                          internalBatteryLowAlertData = true;
                        }else{
                          internalBatteryLowAlertData = false;
                        }
                        hashMap.put("internalBatteryLowAlert", internalBatteryLowAlertData);
                                                
                                
                                                            boolean anglePollingBitData = false;
                        int tempanglePollingBitData = cursor.getInt(26);
                        if( tempanglePollingBitData > 0){
                          anglePollingBitData = true;
                        }else{
                          anglePollingBitData = false;
                        }
                        hashMap.put("anglePollingBit", anglePollingBitData);
                                                
                                
                                                            String digitalOutput1StatusData = "";
                        if(cursor.getString(27) != null){
                          digitalOutput1StatusData = cursor.getString(27);
                          if(digitalOutput1StatusData != null){
                            digitalOutput1StatusData = (String)digitalOutput1StatusData;
                            hashMap.put("digitalOutput1Status", digitalOutput1StatusData);
                          }
                        }
                                                
                                
                                                            boolean isHarshAccelerationDetectedData = false;
                        int tempisHarshAccelerationDetectedData = cursor.getInt(28);
                        if( tempisHarshAccelerationDetectedData > 0){
                          isHarshAccelerationDetectedData = true;
                        }else{
                          isHarshAccelerationDetectedData = false;
                        }
                        hashMap.put("isHarshAccelerationDetected", isHarshAccelerationDetectedData);
                                                
                                
                                                            boolean isHarshBrakingDetectedData = false;
                        int tempisHarshBrakingDetectedData = cursor.getInt(29);
                        if( tempisHarshBrakingDetectedData > 0){
                          isHarshBrakingDetectedData = true;
                        }else{
                          isHarshBrakingDetectedData = false;
                        }
                        hashMap.put("isHarshBrakingDetected", isHarshBrakingDetectedData);
                                                
                                
                                                            String externalBatteryVoltageData = "";
                        if(cursor.getString(30) != null){
                          externalBatteryVoltageData = cursor.getString(30);
                          if(externalBatteryVoltageData != null){
                            externalBatteryVoltageData = (String)externalBatteryVoltageData;
                            hashMap.put("externalBatteryVoltage", externalBatteryVoltageData);
                          }
                        }
                                                
                                
                                                            double internalBatteryVoltageData = (double)0;
                          internalBatteryVoltageData = cursor.getInt(31);
                          internalBatteryVoltageData = (double)internalBatteryVoltageData;
                          hashMap.put("internalBatteryVoltage", internalBatteryVoltageData);


                                                
                                
                                                            Map<String, Object> gpsPacketIdData = new HashMap<>();
                        if(cursor.getString(32) != null){
                          GsonBuilder gsonBuilder = new GsonBuilder();
                          gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
                          Gson gson = gsonBuilder.create();
                           gpsPacketIdData = gson.fromJson(cursor.getString(32), Map.class);
                          if(gpsPacketIdData != null){
                            gpsPacketIdData = (Map<String, Object>)gpsPacketIdData;
                            hashMap.put("gpsPacketId", gpsPacketIdData);
                          }
                        }
                                                
                                
                                                            String addedData = "";
                        if(cursor.getString(33) != null){
                          addedData = cursor.getString(33);
                          if(addedData != null){
                            addedData = (String)addedData;
                            hashMap.put("added", addedData);
                          }
                        }
                                                
                                
                                                            String updatedData = "";
                        if(cursor.getString(34) != null){
                          updatedData = cursor.getString(34);
                          if(updatedData != null){
                            updatedData = (String)updatedData;
                            hashMap.put("updated", updatedData);
                          }
                        }
                                                
                                
                                                            String idData = "";
                        if(cursor.getString(35) != null){
                          idData = cursor.getString(35);
                          if(idData != null){
                            idData = idData.toString();
                            hashMap.put("id", idData);
                          }
                        }
                                                
                                
                                                            String customerIdData = "";
                        if(cursor.getString(36) != null){
                          customerIdData = cursor.getString(36);
                          if(customerIdData != null){
                            customerIdData = customerIdData.toString();
                            hashMap.put("customerId", customerIdData);
                          }
                        }
                                                
                  //End for loop
         
          

        return hashMap;
    }//parseCursor



    public void upsert__db(String id, GpsPacketData model){
        if(count__db(id) != 0){
            update__db(id, model);
        }else{
            insert__db(id, model);
        }
    } //upsert__db



    // Getting All Contacts
    public DataList<GpsPacketData>  getAll__db() {
        DataList<GpsPacketData> modelList = new DataList<GpsPacketData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `GpsPacketData`";

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<GpsPacketData>) modelList;
        }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    GpsPacketDataRepository repo = restAdapter.createRepository(GpsPacketDataRepository.class);
                    repo.addStorage(context);
                    modelList.add((GpsPacketData)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<GpsPacketData>) modelList;
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
    public DataList<GpsPacketData>  getAll__db(HashMap<String, Object> whereKeyValue) {
        return getAll__db(whereKeyValue, null, 0);
    }



    // Getting All Data where and sort column according to date wise..
    public DataList<GpsPacketData>  getAll__db(HashMap<String, Object> whereKeyValue, String orderBy, int limit) {
        DataList<GpsPacketData> modelList = new DataList<GpsPacketData>();
        String whereQuery = getWhereQuery(whereKeyValue);
        String selectQuery;
        if(orderBy != null){
            selectQuery = "SELECT  * FROM `GpsPacketData` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                selectQuery = selectQuery +  " " + " LIMIT " + limit;
            }
        }else{
            if(limit != 0){
                // Select All Query
                selectQuery = "SELECT  * FROM GpsPacketData " + whereQuery + " LIMIT " + limit;
            }else{
                selectQuery = "SELECT  * FROM GpsPacketData " + whereQuery;
            }
        }

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<GpsPacketData>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    GpsPacketDataRepository repo = restAdapter.createRepository(GpsPacketDataRepository.class);
                    repo.addStorage(context);
                    modelList.add((GpsPacketData)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<GpsPacketData>) modelList;
    }


    // Getting All Data where
    public DataList<GpsPacketData>  getAll__db(HashMap<String, Object> whereKeyValue, int limit) {
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
            countQuery = "SELECT  * FROM `GpsPacketData` " + whereQuery  + " ORDER BY " + orderBy ;
            if(limit != 0){
                // Select All Query
                countQuery = countQuery +  " " + " LIMIT " + limit;
            }
        }else{
            if(limit != 0){
                // Select All Query
                countQuery = "SELECT  * FROM `GpsPacketData` " + whereQuery + " LIMIT " + limit;
            }else{
                countQuery = "SELECT  * FROM `GpsPacketData` " + whereQuery;
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
            countQuery = "SELECT  * FROM `GpsPacketData` " + whereQuery + " LIMIT " + limit;
        }else{
            countQuery = "SELECT  * FROM `GpsPacketData` " + whereQuery;
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
                db.update("`GpsPacketData`", values, "_DATA_UPDATED = 1 AND " + where, null);
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
                db.delete("`GpsPacketData`", "_DATA_UPDATED = 0 AND " + where , null);
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
                db.delete("`GpsPacketData`", where , null);
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        }).start();
    }







    // Getting All Data where
    public DataList<GpsPacketData>  getAll__db(String whereKey, String whereKeyValue) {
        DataList<GpsPacketData> modelList = new DataList<GpsPacketData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM `GpsPacketData` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;

        SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getReadableDatabase();
        //http://www.tothenew.com/blog/sqlite-locking-and-transaction-handling-in-android/
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
         if (!cursor.moveToFirst() || cursor.getCount() == 0){
            return (DataList<GpsPacketData>) modelList;
         }else{
            do {

                HashMap<String, Object> hashMap = parseCursor(cursor);
                if(hashMap != null){
                    GpsPacketDataRepository repo = restAdapter.createRepository(GpsPacketDataRepository.class);
                    repo.addStorage(context);
                    modelList.add((GpsPacketData)repo.createObject(hashMap));
                }
            } while (cursor.moveToNext());
         }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        //db.close();
        // return contact list
        return (DataList<GpsPacketData>) modelList;
    }



    /**
     * Check count of database.
     * @param whereKey
     * @param whereKeyValue
     * @return
     */
    public int count__db(String whereKey, String whereKeyValue){
        String countQuery = "SELECT  * FROM `GpsPacketData` WHERE `" + whereKey +"` ='"+ whereKeyValue + "'" ;
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
                db.update("`GpsPacketData`", values, "_DATA_UPDATED = 1 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
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
                db.delete("`GpsPacketData`", "_DATA_UPDATED = 0 AND `" + whereKey + "` = ?", new String[]{whereKeyValue});
                db.setTransactionSuccessful();
                db.endTransaction();
                //db.close();
            }
        }).start();

    }


    //Update multiple data at once..
    public void updateAll__db(final HashMap<String, Object> whereKeyValue, final GpsPacketData _modelData ){
      new Thread(new Runnable(){
        @Override
        public void run(){
          SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
          db.beginTransaction();
          ContentValues values = getContentValues(_modelData);
          String where = getWhere(whereKeyValue);
          db.update("`GpsPacketData`", values, where, null);
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
    public void update__db(final String id,   final GpsPacketData _modelData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DbHandler.getInstance(context, DATABASE_NAME).getWritableDatabase();
                db.beginTransaction();
                ContentValues values = getContentValues(_modelData);
                // updating row
                db.update("`GpsPacketData`", values, "id = ?",
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
                db.update("`GpsPacketData`", values, "_DATA_UPDATED = 1", null);
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
                db.delete("`GpsPacketData`", "_DATA_UPDATED = 0", null);
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
