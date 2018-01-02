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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.TrackBusVehicleRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.BusModelRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class TrackBusVehicle extends Model {


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

    private TrackBusVehicle that ;

    public TrackBusVehicle (){
        that = this;
    }

    
        
            

            
                private String gpsCode;
                /* Adding Getter and Setter methods */
                public String getGpsCode(){
                    return gpsCode;
                }

                /* Adding Getter and Setter methods */
                public void setGpsCode(String gpsCode){
                    this.gpsCode = gpsCode;
                    //Update hashMap value..
                    hashMap.put("gpsCode", gpsCode);
                }

            
            
        
    
        
            

            
                private Map<String, Object> homeLocation = new HashMap();
                /* Adding Getter and Setter methods */
                public Map<String, Object> getHomeLocation(){
                    return homeLocation;
                }
                /* Adding Getter and Setter methods */
                public double getHomeLocationLatitide(){
                    if(homeLocation != null){
                        return (Double)homeLocation.get("lat");
                    }else{
                        return 0;
                    }
                }

                /* Adding Getter and Setter methods */
                public double getHomeLocationLongitude(){
                    if(homeLocation != null){
                        return (Double)homeLocation.get("lng");
                    }else{
                        return 0;
                    }

                }

                /* Adding Getter and Setter methods */
                public void setHomeLocation(Map<String, Object> homeLocation){
                    this.homeLocation.putAll(homeLocation);
                    //Update Map value..
                    hashMap.put("homeLocation", homeLocation);
                }

                /* Adding Getter and Setter methods */
                public void setHomeLocation(double lat, double lng){
                    this.homeLocation.put("lat", lat);
                    this.homeLocation.put("lng", lng);
                    //Update Map value..
                    hashMap.put("homeLocation", homeLocation);
                }

            
            
        
    
        
            

            
                private String homeAddress;
                /* Adding Getter and Setter methods */
                public String getHomeAddress(){
                    return homeAddress;
                }

                /* Adding Getter and Setter methods */
                public void setHomeAddress(String homeAddress){
                    this.homeAddress = homeAddress;
                    //Update hashMap value..
                    hashMap.put("homeAddress", homeAddress);
                }

            
            
        
    
        
            

            
                private Map<String, Object> gpsBusNotification;
                /* Adding Getter and Setter methods */
                public Map<String, Object> getGpsBusNotification(){
                    return gpsBusNotification;
                }

                /* Adding Getter and Setter methods */
                public void setGpsBusNotification(Map<String, Object> gpsBusNotification){
                    this.gpsBusNotification = gpsBusNotification;
                    //Update Map value..
                    hashMap.put("gpsBusNotification", gpsBusNotification);
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

            
            
        
    
        
            

            
                private double vicinity;
                /* Adding Getter and Setter methods */
                public double getVicinity(){
                    return vicinity;
                }

                /* Adding Getter and Setter methods */
                public void setVicinity(double vicinity){
                    this.vicinity = vicinity;
                    //Update hashMap value..
                    hashMap.put("vicinity", vicinity);
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

            
            
        
    
        
            

            
                private String busNotification;
                /* Adding Getter and Setter methods */
                public String getBusNotification(){
                    return busNotification;
                }

                /* Adding Getter and Setter methods */
                public void setBusNotification(String busNotification){
                    this.busNotification = busNotification;
                    //Update hashMap value..
                    hashMap.put("busNotification", busNotification);
                }

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      TrackBusVehicleRepository lowercaseFirstLetterRepository = (TrackBusVehicleRepository) getRepository();
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
      TrackBusVehicleRepository lowercaseFirstLetterRepository = (TrackBusVehicleRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      TrackBusVehicleRepository lowercaseFirstLetterRepository = (TrackBusVehicleRepository) getRepository();
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
                                        TrackBusVehicleRepository trackBusVehicleRepository = (TrackBusVehicleRepository) getRepository();

                                        RestAdapter restAdapter = trackBusVehicleRepository.getRestAdapter();
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
                            TrackBusVehicleRepository lowercaseFirstLetterRepository = (TrackBusVehicleRepository) getRepository();
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
                                        final TrackBusVehicleRepository  trackBusVehicleRepo = restAdapter.createRepository(TrackBusVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        trackBusVehicleRepo.get__customer( (String)that.getId(), refresh,  new ObjectCallback<Customer> (){
                                            

                                            
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
                    private transient BusModel  busModel ;
                    private String busModelId;

                    public String getBusModelId(){
                         return busModelId;
                    }

                    public void setBusModelId(Object busModelId){
                        if(busModelId != null){
                          this.busModelId = busModelId.toString();
                        }
                    }

                    public BusModel getBusModel() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(busModel == null){
                                        TrackBusVehicleRepository trackBusVehicleRepository = (TrackBusVehicleRepository) getRepository();

                                        RestAdapter restAdapter = trackBusVehicleRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          busModel = getBusModel__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return busModel;
                    }

                    public void setBusModel(BusModel busModel) {
                        this.busModel = busModel;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setBusModel(Map<String, Object> busModel) {
                        //First create a dummy Repo class object for customer.
                        BusModelRepository busModelRepository = new BusModelRepository();
                        BusModel busModel1 = busModelRepository.createObject(busModel);
                        setBusModel(busModel1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setBusModel(HashMap<String, Object> busModel) {
                        //First create a dummy Repo class object for customer.
                        BusModelRepository busModelRepository = new BusModelRepository();
                        BusModel busModel1 = busModelRepository.createObject(busModel);
                        setBusModel(busModel1);
                    }

                    //Adding relation method..
                    public void addRelation(BusModel busModel) {
                        that.setBusModel(busModel);
                    }


                    //Fetch related data from local database if present a busModelId identifier as property for belongsTo
                    public BusModel getBusModel__db(RestAdapter restAdapter){
                      if(busModelId != null){
                        BusModelRepository busModelRepository = restAdapter.createRepository(BusModelRepository.class);
                            try{
                            TrackBusVehicleRepository lowercaseFirstLetterRepository = (TrackBusVehicleRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(busModelRepository.getDb() == null ){
                                                    busModelRepository.addStorage(context);
                                                }

                                                if(context != null && busModelRepository.getDb() != null){
                                                    busModelRepository.addStorage(context);
                                                    BusModel busModel = (BusModel) busModelRepository.getDb().get__db(busModelId);
                                                    return busModel;
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
                                    public void get__busModel( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<BusModel> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final TrackBusVehicleRepository  trackBusVehicleRepo = restAdapter.createRepository(TrackBusVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        trackBusVehicleRepo.get__busModel( (String)that.getId(), refresh,  new ObjectCallback<BusModel> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(BusModel object) {
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
