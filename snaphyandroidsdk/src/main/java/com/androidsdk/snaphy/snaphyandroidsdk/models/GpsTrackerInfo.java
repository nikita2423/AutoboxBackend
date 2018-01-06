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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.GpsTrackerInfoRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.VehicleDetailRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class GpsTrackerInfo extends Model {


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

    private GpsTrackerInfo that ;

    public GpsTrackerInfo (){
        that = this;
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

            
            
        
    
        
            

            
                private String gpsTrackerSimNumber;
                /* Adding Getter and Setter methods */
                public String getGpsTrackerSimNumber(){
                    return gpsTrackerSimNumber;
                }

                /* Adding Getter and Setter methods */
                public void setGpsTrackerSimNumber(String gpsTrackerSimNumber){
                    this.gpsTrackerSimNumber = gpsTrackerSimNumber;
                    //Update hashMap value..
                    hashMap.put("gpsTrackerSimNumber", gpsTrackerSimNumber);
                }

            
            
        
    
        
            

            
                private String gpsPassword;
                /* Adding Getter and Setter methods */
                public String getGpsPassword(){
                    return gpsPassword;
                }

                /* Adding Getter and Setter methods */
                public void setGpsPassword(String gpsPassword){
                    this.gpsPassword = gpsPassword;
                    //Update hashMap value..
                    hashMap.put("gpsPassword", gpsPassword);
                }

            
            
        
    
        
            

            
                private String registrationNumber;
                /* Adding Getter and Setter methods */
                public String getRegistrationNumber(){
                    return registrationNumber;
                }

                /* Adding Getter and Setter methods */
                public void setRegistrationNumber(String registrationNumber){
                    this.registrationNumber = registrationNumber;
                    //Update hashMap value..
                    hashMap.put("registrationNumber", registrationNumber);
                }

            
            
        
    
        
            

            
                private String serialNumber;
                /* Adding Getter and Setter methods */
                public String getSerialNumber(){
                    return serialNumber;
                }

                /* Adding Getter and Setter methods */
                public void setSerialNumber(String serialNumber){
                    this.serialNumber = serialNumber;
                    //Update hashMap value..
                    hashMap.put("serialNumber", serialNumber);
                }

            
            
        
    
        
            

            
                private String modelName;
                /* Adding Getter and Setter methods */
                public String getModelName(){
                    return modelName;
                }

                /* Adding Getter and Setter methods */
                public void setModelName(String modelName){
                    this.modelName = modelName;
                    //Update hashMap value..
                    hashMap.put("modelName", modelName);
                }

            
            
        
    
        
            

            
                private String status;
                /* Adding Getter and Setter methods */
                public String getStatus(){
                    return status;
                }

                /* Adding Getter and Setter methods */
                public void setStatus(String status){
                    this.status = status;
                    //Update hashMap value..
                    hashMap.put("status", status);
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

            
            
        
    
        
            

            
                private Map<String, Object> gpsTrackerNotification;
                /* Adding Getter and Setter methods */
                public Map<String, Object> getGpsTrackerNotification(){
                    return gpsTrackerNotification;
                }

                /* Adding Getter and Setter methods */
                public void setGpsTrackerNotification(Map<String, Object> gpsTrackerNotification){
                    this.gpsTrackerNotification = gpsTrackerNotification;
                    //Update Map value..
                    hashMap.put("gpsTrackerNotification", gpsTrackerNotification);
                }

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      GpsTrackerInfoRepository lowercaseFirstLetterRepository = (GpsTrackerInfoRepository) getRepository();
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
      GpsTrackerInfoRepository lowercaseFirstLetterRepository = (GpsTrackerInfoRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      GpsTrackerInfoRepository lowercaseFirstLetterRepository = (GpsTrackerInfoRepository) getRepository();
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
                                        GpsTrackerInfoRepository gpsTrackerInfoRepository = (GpsTrackerInfoRepository) getRepository();

                                        RestAdapter restAdapter = gpsTrackerInfoRepository.getRestAdapter();
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
                            GpsTrackerInfoRepository lowercaseFirstLetterRepository = (GpsTrackerInfoRepository) getRepository();
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
                                        final GpsTrackerInfoRepository  gpsTrackerInfoRepo = restAdapter.createRepository(GpsTrackerInfoRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        gpsTrackerInfoRepo.get__customer( (String)that.getId(), refresh,  new ObjectCallback<Customer> (){
                                            

                                            
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
                                 
                            
                         
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                 
                 
             
          
    
        
        
                
                    //Define belongsTo relation method here..
                    private transient VehicleDetail  vehicleDetail ;
                    private String vehicleDetailId;

                    public String getVehicleDetailId(){
                         return vehicleDetailId;
                    }

                    public void setVehicleDetailId(Object vehicleDetailId){
                        if(vehicleDetailId != null){
                          this.vehicleDetailId = vehicleDetailId.toString();
                        }
                    }

                    public VehicleDetail getVehicleDetail() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(vehicleDetail == null){
                                        GpsTrackerInfoRepository gpsTrackerInfoRepository = (GpsTrackerInfoRepository) getRepository();

                                        RestAdapter restAdapter = gpsTrackerInfoRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          vehicleDetail = getVehicleDetail__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return vehicleDetail;
                    }

                    public void setVehicleDetail(VehicleDetail vehicleDetail) {
                        this.vehicleDetail = vehicleDetail;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setVehicleDetail(Map<String, Object> vehicleDetail) {
                        //First create a dummy Repo class object for customer.
                        VehicleDetailRepository vehicleDetailRepository = new VehicleDetailRepository();
                        VehicleDetail vehicleDetail1 = vehicleDetailRepository.createObject(vehicleDetail);
                        setVehicleDetail(vehicleDetail1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setVehicleDetail(HashMap<String, Object> vehicleDetail) {
                        //First create a dummy Repo class object for customer.
                        VehicleDetailRepository vehicleDetailRepository = new VehicleDetailRepository();
                        VehicleDetail vehicleDetail1 = vehicleDetailRepository.createObject(vehicleDetail);
                        setVehicleDetail(vehicleDetail1);
                    }

                    //Adding relation method..
                    public void addRelation(VehicleDetail vehicleDetail) {
                        that.setVehicleDetail(vehicleDetail);
                    }


                    //Fetch related data from local database if present a vehicleDetailId identifier as property for belongsTo
                    public VehicleDetail getVehicleDetail__db(RestAdapter restAdapter){
                      if(vehicleDetailId != null){
                        VehicleDetailRepository vehicleDetailRepository = restAdapter.createRepository(VehicleDetailRepository.class);
                            try{
                            GpsTrackerInfoRepository lowercaseFirstLetterRepository = (GpsTrackerInfoRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(vehicleDetailRepository.getDb() == null ){
                                                    vehicleDetailRepository.addStorage(context);
                                                }

                                                if(context != null && vehicleDetailRepository.getDb() != null){
                                                    vehicleDetailRepository.addStorage(context);
                                                    VehicleDetail vehicleDetail = (VehicleDetail) vehicleDetailRepository.getDb().get__db(vehicleDetailId);
                                                    return vehicleDetail;
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
                                    public void get__vehicleDetail( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<VehicleDetail> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final GpsTrackerInfoRepository  gpsTrackerInfoRepo = restAdapter.createRepository(GpsTrackerInfoRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        gpsTrackerInfoRepo.get__vehicleDetail( (String)that.getId(), refresh,  new ObjectCallback<VehicleDetail> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(VehicleDetail object) {
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