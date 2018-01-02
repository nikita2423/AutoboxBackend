package com.androidsdk.snaphy.snaphyandroidsdk.models;







import org.json.JSONObject;
import org.json.JSONArray;

import java.util.List;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.remoting.adapters.Adapter;
import android.content.Context;

/*
Replacing with custom Snaphy callback methods
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.strongloop.android.loopback.callbacks.ObjectCallback;
import com.strongloop.android.loopback.callbacks.VoidCallback;
*/
import com.androidsdk.snaphy.snaphyandroidsdk.callbacks.ObjectCallback;
import com.androidsdk.snaphy.snaphyandroidsdk.callbacks.DataListCallback;
import com.androidsdk.snaphy.snaphyandroidsdk.callbacks.VoidCallback;
import com.androidsdk.snaphy.snaphyandroidsdk.list.DataList;

//Import self repository..
import com.androidsdk.snaphy.snaphyandroidsdk.repository.GpsPacketDataRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class GpsPacketData extends Model {


    //For converting all model values to hashMap
    private  transient Map<String, Object> hashMap = new HashMap<>();

    public Map<String,  ? extends Object> convertMap(){
        if(that.getId() != null){
            return hashMap;
        }else{
            hashMap.put("id", that.getId());
            return hashMap;
        }
    }

    private GpsPacketData that ;

    public GpsPacketData (){
        that = this;
    }

    
        
            

            
                private String clientId;
                /* Adding Getter and Setter methods */
                public String getClientId(){
                    return clientId;
                }

                /* Adding Getter and Setter methods */
                public void setClientId(String clientId){
                    this.clientId = clientId;
                    //Update hashMap value..
                    hashMap.put("clientId", clientId);
                }

            
            
        
    
        
            

            
                private String deviceIMEI;
                /* Adding Getter and Setter methods */
                public String getDeviceIMEI(){
                    return deviceIMEI;
                }

                /* Adding Getter and Setter methods */
                public void setDeviceIMEI(String deviceIMEI){
                    this.deviceIMEI = deviceIMEI;
                    //Update hashMap value..
                    hashMap.put("deviceIMEI", deviceIMEI);
                }

            
            
        
    
        
            

            
                private double eventCode;
                /* Adding Getter and Setter methods */
                public double getEventCode(){
                    return eventCode;
                }

                /* Adding Getter and Setter methods */
                public void setEventCode(double eventCode){
                    this.eventCode = eventCode;
                    //Update hashMap value..
                    hashMap.put("eventCode", eventCode);
                }

            
            
        
    
        
            

            
                private boolean isStoredPacket;
                /* Adding Getter and Setter methods */
                public boolean getIsStoredPacket(){
                    return isStoredPacket;
                }

                /* Adding Getter and Setter methods */
                public void setIsStoredPacket(boolean isStoredPacket){
                    this.isStoredPacket = isStoredPacket;
                    //Update hashMap value..
                    hashMap.put("isStoredPacket", isStoredPacket);
                }

            
            
        
    
        
            

            
                private String eventType;
                /* Adding Getter and Setter methods */
                public String getEventType(){
                    return eventType;
                }

                /* Adding Getter and Setter methods */
                public void setEventType(String eventType){
                    this.eventType = eventType;
                    //Update hashMap value..
                    hashMap.put("eventType", eventType);
                }

            
            
        
    
        
            

            
                private String latitude;
                /* Adding Getter and Setter methods */
                public String getLatitude(){
                    return latitude;
                }

                /* Adding Getter and Setter methods */
                public void setLatitude(String latitude){
                    this.latitude = latitude;
                    //Update hashMap value..
                    hashMap.put("latitude", latitude);
                }

            
            
        
    
        
            

            
                private String longitude;
                /* Adding Getter and Setter methods */
                public String getLongitude(){
                    return longitude;
                }

                /* Adding Getter and Setter methods */
                public void setLongitude(String longitude){
                    this.longitude = longitude;
                    //Update hashMap value..
                    hashMap.put("longitude", longitude);
                }

            
            
        
    
        
            

            
                private Map<String, Object> latlng = new HashMap();
                /* Adding Getter and Setter methods */
                public Map<String, Object> getLatlng(){
                    return latlng;
                }
                /* Adding Getter and Setter methods */
                public double getLatlngLatitide(){
                    if(latlng != null){
                        return (Double)latlng.get("lat");
                    }else{
                        return 0;
                    }
                }

                /* Adding Getter and Setter methods */
                public double getLatlngLongitude(){
                    if(latlng != null){
                        return (Double)latlng.get("lng");
                    }else{
                        return 0;
                    }

                }

                /* Adding Getter and Setter methods */
                public void setLatlng(Map<String, Object> latlng){
                    this.latlng.putAll(latlng);
                    //Update Map value..
                    hashMap.put("latlng", latlng);
                }

                /* Adding Getter and Setter methods */
                public void setLatlng(double lat, double lng){
                    this.latlng.put("lat", lat);
                    this.latlng.put("lng", lng);
                    //Update Map value..
                    hashMap.put("latlng", latlng);
                }

            
            
        
    
        
            

            
                private String eventDate;
                /* Adding Getter and Setter methods */
                public String getEventDate(){
                    return eventDate;
                }

                /* Adding Getter and Setter methods */
                public void setEventDate(String eventDate){
                    this.eventDate = eventDate;
                    //Update hashMap value..
                    hashMap.put("eventDate", eventDate);
                }

            
            
        
    
        
            

            
                private String gpsStatus;
                /* Adding Getter and Setter methods */
                public String getGpsStatus(){
                    return gpsStatus;
                }

                /* Adding Getter and Setter methods */
                public void setGpsStatus(String gpsStatus){
                    this.gpsStatus = gpsStatus;
                    //Update hashMap value..
                    hashMap.put("gpsStatus", gpsStatus);
                }

            
            
        
    
        
            

            
                private double gmsSignal;
                /* Adding Getter and Setter methods */
                public double getGmsSignal(){
                    return gmsSignal;
                }

                /* Adding Getter and Setter methods */
                public void setGmsSignal(double gmsSignal){
                    this.gmsSignal = gmsSignal;
                    //Update hashMap value..
                    hashMap.put("gmsSignal", gmsSignal);
                }

            
            
        
    
        
            

            
                private double speed;
                /* Adding Getter and Setter methods */
                public double getSpeed(){
                    return speed;
                }

                /* Adding Getter and Setter methods */
                public void setSpeed(double speed){
                    this.speed = speed;
                    //Update hashMap value..
                    hashMap.put("speed", speed);
                }

            
            
        
    
        
            

            
                private String accumulatedDistance;
                /* Adding Getter and Setter methods */
                public String getAccumulatedDistance(){
                    return accumulatedDistance;
                }

                /* Adding Getter and Setter methods */
                public void setAccumulatedDistance(String accumulatedDistance){
                    this.accumulatedDistance = accumulatedDistance;
                    //Update hashMap value..
                    hashMap.put("accumulatedDistance", accumulatedDistance);
                }

            
            
        
    
        
            

            
                private double courseInDegree;
                /* Adding Getter and Setter methods */
                public double getCourseInDegree(){
                    return courseInDegree;
                }

                /* Adding Getter and Setter methods */
                public void setCourseInDegree(double courseInDegree){
                    this.courseInDegree = courseInDegree;
                    //Update hashMap value..
                    hashMap.put("courseInDegree", courseInDegree);
                }

            
            
        
    
        
            

            
                private double satelliteConnected;
                /* Adding Getter and Setter methods */
                public double getSatelliteConnected(){
                    return satelliteConnected;
                }

                /* Adding Getter and Setter methods */
                public void setSatelliteConnected(double satelliteConnected){
                    this.satelliteConnected = satelliteConnected;
                    //Update hashMap value..
                    hashMap.put("satelliteConnected", satelliteConnected);
                }

            
            
        
    
        
            

            
                private double hdop;
                /* Adding Getter and Setter methods */
                public double getHdop(){
                    return hdop;
                }

                /* Adding Getter and Setter methods */
                public void setHdop(double hdop){
                    this.hdop = hdop;
                    //Update hashMap value..
                    hashMap.put("hdop", hdop);
                }

            
            
        
    
        
            

            
                private double voltageEquivalent;
                /* Adding Getter and Setter methods */
                public double getVoltageEquivalent(){
                    return voltageEquivalent;
                }

                /* Adding Getter and Setter methods */
                public void setVoltageEquivalent(double voltageEquivalent){
                    this.voltageEquivalent = voltageEquivalent;
                    //Update hashMap value..
                    hashMap.put("voltageEquivalent", voltageEquivalent);
                }

            
            
        
    
        
            

            
                private String digitalInput1;
                /* Adding Getter and Setter methods */
                public String getDigitalInput1(){
                    return digitalInput1;
                }

                /* Adding Getter and Setter methods */
                public void setDigitalInput1(String digitalInput1){
                    this.digitalInput1 = digitalInput1;
                    //Update hashMap value..
                    hashMap.put("digitalInput1", digitalInput1);
                }

            
            
        
    
        
            

            
                private String caseStatus;
                /* Adding Getter and Setter methods */
                public String getCaseStatus(){
                    return caseStatus;
                }

                /* Adding Getter and Setter methods */
                public void setCaseStatus(String caseStatus){
                    this.caseStatus = caseStatus;
                    //Update hashMap value..
                    hashMap.put("caseStatus", caseStatus);
                }

            
            
        
    
        
            

            
                private boolean isOverSpeedStarted;
                /* Adding Getter and Setter methods */
                public boolean getIsOverSpeedStarted(){
                    return isOverSpeedStarted;
                }

                /* Adding Getter and Setter methods */
                public void setIsOverSpeedStarted(boolean isOverSpeedStarted){
                    this.isOverSpeedStarted = isOverSpeedStarted;
                    //Update hashMap value..
                    hashMap.put("isOverSpeedStarted", isOverSpeedStarted);
                }

            
            
        
    
        
            

            
                private boolean isOverSpeedEnded;
                /* Adding Getter and Setter methods */
                public boolean getIsOverSpeedEnded(){
                    return isOverSpeedEnded;
                }

                /* Adding Getter and Setter methods */
                public void setIsOverSpeedEnded(boolean isOverSpeedEnded){
                    this.isOverSpeedEnded = isOverSpeedEnded;
                    //Update hashMap value..
                    hashMap.put("isOverSpeedEnded", isOverSpeedEnded);
                }

            
            
        
    
        
            

            
                private boolean immobilizerVoilationAlert;
                /* Adding Getter and Setter methods */
                public boolean getImmobilizerVoilationAlert(){
                    return immobilizerVoilationAlert;
                }

                /* Adding Getter and Setter methods */
                public void setImmobilizerVoilationAlert(boolean immobilizerVoilationAlert){
                    this.immobilizerVoilationAlert = immobilizerVoilationAlert;
                    //Update hashMap value..
                    hashMap.put("immobilizerVoilationAlert", immobilizerVoilationAlert);
                }

            
            
        
    
        
            

            
                private String batteryStatus;
                /* Adding Getter and Setter methods */
                public String getBatteryStatus(){
                    return batteryStatus;
                }

                /* Adding Getter and Setter methods */
                public void setBatteryStatus(String batteryStatus){
                    this.batteryStatus = batteryStatus;
                    //Update hashMap value..
                    hashMap.put("batteryStatus", batteryStatus);
                }

            
            
        
    
        
            

            
                private String digitalInput2;
                /* Adding Getter and Setter methods */
                public String getDigitalInput2(){
                    return digitalInput2;
                }

                /* Adding Getter and Setter methods */
                public void setDigitalInput2(String digitalInput2){
                    this.digitalInput2 = digitalInput2;
                    //Update hashMap value..
                    hashMap.put("digitalInput2", digitalInput2);
                }

            
            
        
    
        
            

            
                private String ignitionStatus;
                /* Adding Getter and Setter methods */
                public String getIgnitionStatus(){
                    return ignitionStatus;
                }

                /* Adding Getter and Setter methods */
                public void setIgnitionStatus(String ignitionStatus){
                    this.ignitionStatus = ignitionStatus;
                    //Update hashMap value..
                    hashMap.put("ignitionStatus", ignitionStatus);
                }

            
            
        
    
        
            

            
                private boolean internalBatteryLowAlert;
                /* Adding Getter and Setter methods */
                public boolean getInternalBatteryLowAlert(){
                    return internalBatteryLowAlert;
                }

                /* Adding Getter and Setter methods */
                public void setInternalBatteryLowAlert(boolean internalBatteryLowAlert){
                    this.internalBatteryLowAlert = internalBatteryLowAlert;
                    //Update hashMap value..
                    hashMap.put("internalBatteryLowAlert", internalBatteryLowAlert);
                }

            
            
        
    
        
            

            
                private boolean anglePollingBit;
                /* Adding Getter and Setter methods */
                public boolean getAnglePollingBit(){
                    return anglePollingBit;
                }

                /* Adding Getter and Setter methods */
                public void setAnglePollingBit(boolean anglePollingBit){
                    this.anglePollingBit = anglePollingBit;
                    //Update hashMap value..
                    hashMap.put("anglePollingBit", anglePollingBit);
                }

            
            
        
    
        
            

            
                private String digitalOutput1Status;
                /* Adding Getter and Setter methods */
                public String getDigitalOutput1Status(){
                    return digitalOutput1Status;
                }

                /* Adding Getter and Setter methods */
                public void setDigitalOutput1Status(String digitalOutput1Status){
                    this.digitalOutput1Status = digitalOutput1Status;
                    //Update hashMap value..
                    hashMap.put("digitalOutput1Status", digitalOutput1Status);
                }

            
            
        
    
        
            

            
                private boolean isHarshAccelerationDetected;
                /* Adding Getter and Setter methods */
                public boolean getIsHarshAccelerationDetected(){
                    return isHarshAccelerationDetected;
                }

                /* Adding Getter and Setter methods */
                public void setIsHarshAccelerationDetected(boolean isHarshAccelerationDetected){
                    this.isHarshAccelerationDetected = isHarshAccelerationDetected;
                    //Update hashMap value..
                    hashMap.put("isHarshAccelerationDetected", isHarshAccelerationDetected);
                }

            
            
        
    
        
            

            
                private boolean isHarshBrakingDetected;
                /* Adding Getter and Setter methods */
                public boolean getIsHarshBrakingDetected(){
                    return isHarshBrakingDetected;
                }

                /* Adding Getter and Setter methods */
                public void setIsHarshBrakingDetected(boolean isHarshBrakingDetected){
                    this.isHarshBrakingDetected = isHarshBrakingDetected;
                    //Update hashMap value..
                    hashMap.put("isHarshBrakingDetected", isHarshBrakingDetected);
                }

            
            
        
    
        
            

            
                private String externalBatteryVoltage;
                /* Adding Getter and Setter methods */
                public String getExternalBatteryVoltage(){
                    return externalBatteryVoltage;
                }

                /* Adding Getter and Setter methods */
                public void setExternalBatteryVoltage(String externalBatteryVoltage){
                    this.externalBatteryVoltage = externalBatteryVoltage;
                    //Update hashMap value..
                    hashMap.put("externalBatteryVoltage", externalBatteryVoltage);
                }

            
            
        
    
        
            

            
                private double internalBatteryVoltage;
                /* Adding Getter and Setter methods */
                public double getInternalBatteryVoltage(){
                    return internalBatteryVoltage;
                }

                /* Adding Getter and Setter methods */
                public void setInternalBatteryVoltage(double internalBatteryVoltage){
                    this.internalBatteryVoltage = internalBatteryVoltage;
                    //Update hashMap value..
                    hashMap.put("internalBatteryVoltage", internalBatteryVoltage);
                }

            
            
        
    
        
            

            
                private Map<String, Object> gpsPacketId;
                /* Adding Getter and Setter methods */
                public Map<String, Object> getGpsPacketId(){
                    return gpsPacketId;
                }

                /* Adding Getter and Setter methods */
                public void setGpsPacketId(Map<String, Object> gpsPacketId){
                    this.gpsPacketId = gpsPacketId;
                    //Update Map value..
                    hashMap.put("gpsPacketId", gpsPacketId);
                }

            
            
        
    
        
            

            
                private String added;
                /* Adding Getter and Setter methods */
                public String getAdded(){
                    return added;
                }

                /* Adding Getter and Setter methods */
                public void setAdded(String added){
                    this.added = added;
                    //Update hashMap value..
                    hashMap.put("added", added);
                }

            
            
        
    
        
            

            
                private String updated;
                /* Adding Getter and Setter methods */
                public String getUpdated(){
                    return updated;
                }

                /* Adding Getter and Setter methods */
                public void setUpdated(String updated){
                    this.updated = updated;
                    //Update hashMap value..
                    hashMap.put("updated", updated);
                }

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      GpsPacketDataRepository lowercaseFirstLetterRepository = (GpsPacketDataRepository) getRepository();
      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
          //Delete from database..
          String id = getId().toString();
          if(id != null && lowercaseFirstLetterRepository.getDb() != null){
             lowercaseFirstLetterRepository.getDb().delete__db(id);
          }
      }
      //Also save to database..
      super.destroy(callback);
    }



    public void save__db(String id){
      GpsPacketDataRepository lowercaseFirstLetterRepository = (GpsPacketDataRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      GpsPacketDataRepository lowercaseFirstLetterRepository = (GpsPacketDataRepository) getRepository();
      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){

        if(getId() != null && lowercaseFirstLetterRepository.getDb() != null){
            String id = getId().toString();
          lowercaseFirstLetterRepository.getDb().delete__db(id);
        }
      }
    }


    public void save__db(){
      if(getId() == null){
        return;
      }
      String id = getId().toString();
      save__db(id);
    }



//-----------------------------------END Database Methods------------------------------------------------


    




    //Now adding relations between related models
    
        
        
                
                    //Define belongsTo relation method here..
                    private transient Customer  customer ;
                    private String customerId;

                    public String getCustomerId(){
                         return customerId;
                    }

                    public void setCustomerId(Object customerId){
                        if(customerId != null){
                          this.customerId = customerId.toString();
                        }
                    }

                    public Customer getCustomer() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(customer == null){
                                        GpsPacketDataRepository gpsPacketDataRepository = (GpsPacketDataRepository) getRepository();

                                        RestAdapter restAdapter = gpsPacketDataRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          customer = getCustomer__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return customer;
                    }

                    public void setCustomer(Customer customer) {
                        this.customer = customer;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setCustomer(Map<String, Object> customer) {
                        //First create a dummy Repo class object for customer.
                        CustomerRepository customerRepository = new CustomerRepository();
                        Customer customer1 = customerRepository.createObject(customer);
                        setCustomer(customer1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setCustomer(HashMap<String, Object> customer) {
                        //First create a dummy Repo class object for customer.
                        CustomerRepository customerRepository = new CustomerRepository();
                        Customer customer1 = customerRepository.createObject(customer);
                        setCustomer(customer1);
                    }

                    //Adding relation method..
                    public void addRelation(Customer customer) {
                        that.setCustomer(customer);
                    }


                    //Fetch related data from local database if present a customerId identifier as property for belongsTo
                    public Customer getCustomer__db(RestAdapter restAdapter){
                      if(customerId != null){
                        CustomerRepository customerRepository = restAdapter.createRepository(CustomerRepository.class);
                            try{
                            GpsPacketDataRepository lowercaseFirstLetterRepository = (GpsPacketDataRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(customerRepository.getDb() == null ){
                                                    customerRepository.addStorage(context);
                                                }

                                                if(context != null && customerRepository.getDb() != null){
                                                    customerRepository.addStorage(context);
                                                    Customer customer = (Customer) customerRepository.getDb().get__db(customerId);
                                                    return customer;
                                                }else{
                                                    return null;
                                                }
                                          }else{
                                            return null;
                                          }
                            }catch(Exception e){
                            //Ignore exception..
                            return null;
                            }

                        }else{
                          return null;
                      }
                    }
                

                
                







                    //Now add instance methods to fetch the related belongsTo Model..
                    

                    

                                    //Write the method here..
                                    public void get__customer( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Customer> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final GpsPacketDataRepository  gpsPacketDataRepo = restAdapter.createRepository(GpsPacketDataRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        gpsPacketDataRepo.get__customer( (String)that.getId(), refresh,  new ObjectCallback<Customer> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Customer object) {
                                                        if(object != null){
                                                            //now add relation to this recipe.
                                                            addRelation(object);
                                                            //Also add relation to child type for two way communication..Removing two way communication for cyclic error
                                                            //object.addRelation(that);
                                                            callback.onSuccess(object);
                                                            //Calling the finally..callback
                                                            callback.onFinally();
                                                        }else{
                                                            callback.onSuccess(null);
                                                            //Calling the finally..callback
                                                            callback.onFinally();
                                                        }

                                                    }
                                                
                                            


                                            

                                            @Override
                                            public void onError(Throwable t) {
                                                //Now calling the callback
                                                callback.onError(t);
                                                //Calling the finally..callback
                                                callback.onFinally();
                                            }

                                        });
                                    } //method def ends here.
                                 
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                 
                 
             
          
      

}
