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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.SellVehicleRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.VehicleInfoRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class SellVehicle extends Model {


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

    private SellVehicle that ;

    public SellVehicle (){
        that = this;
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

            
            
        
    
        
            

            
                private double kilometers;
                /* Adding Getter and Setter methods */
                public double getKilometers(){
                    return kilometers;
                }

                /* Adding Getter and Setter methods */
                public void setKilometers(double kilometers){
                    this.kilometers = kilometers;
                    //Update hashMap value..
                    hashMap.put("kilometers", kilometers);
                }

            
            
        
    
        
            

            
                private double yearOfPurchase;
                /* Adding Getter and Setter methods */
                public double getYearOfPurchase(){
                    return yearOfPurchase;
                }

                /* Adding Getter and Setter methods */
                public void setYearOfPurchase(double yearOfPurchase){
                    this.yearOfPurchase = yearOfPurchase;
                    //Update hashMap value..
                    hashMap.put("yearOfPurchase", yearOfPurchase);
                }

            
            
        
    
        
            

            
                private String trimData;
                /* Adding Getter and Setter methods */
                public String getTrimData(){
                    return trimData;
                }

                /* Adding Getter and Setter methods */
                public void setTrimData(String trimData){
                    this.trimData = trimData;
                    //Update hashMap value..
                    hashMap.put("trimData", trimData);
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
      SellVehicleRepository lowercaseFirstLetterRepository = (SellVehicleRepository) getRepository();
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
      SellVehicleRepository lowercaseFirstLetterRepository = (SellVehicleRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      SellVehicleRepository lowercaseFirstLetterRepository = (SellVehicleRepository) getRepository();
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
                                        SellVehicleRepository sellVehicleRepository = (SellVehicleRepository) getRepository();

                                        RestAdapter restAdapter = sellVehicleRepository.getRestAdapter();
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
                            SellVehicleRepository lowercaseFirstLetterRepository = (SellVehicleRepository) getRepository();
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
                                        final SellVehicleRepository  sellVehicleRepo = restAdapter.createRepository(SellVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        sellVehicleRepo.get__customer( (String)that.getId(), refresh,  new ObjectCallback<Customer> (){
                                            

                                            
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
                    private transient VehicleInfo  vehicleInfo ;
                    private String vehicleInfoId;

                    public String getVehicleInfoId(){
                         return vehicleInfoId;
                    }

                    public void setVehicleInfoId(Object vehicleInfoId){
                        if(vehicleInfoId != null){
                          this.vehicleInfoId = vehicleInfoId.toString();
                        }
                    }

                    public VehicleInfo getVehicleInfo() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(vehicleInfo == null){
                                        SellVehicleRepository sellVehicleRepository = (SellVehicleRepository) getRepository();

                                        RestAdapter restAdapter = sellVehicleRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          vehicleInfo = getVehicleInfo__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return vehicleInfo;
                    }

                    public void setVehicleInfo(VehicleInfo vehicleInfo) {
                        this.vehicleInfo = vehicleInfo;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setVehicleInfo(Map<String, Object> vehicleInfo) {
                        //First create a dummy Repo class object for customer.
                        VehicleInfoRepository vehicleInfoRepository = new VehicleInfoRepository();
                        VehicleInfo vehicleInfo1 = vehicleInfoRepository.createObject(vehicleInfo);
                        setVehicleInfo(vehicleInfo1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setVehicleInfo(HashMap<String, Object> vehicleInfo) {
                        //First create a dummy Repo class object for customer.
                        VehicleInfoRepository vehicleInfoRepository = new VehicleInfoRepository();
                        VehicleInfo vehicleInfo1 = vehicleInfoRepository.createObject(vehicleInfo);
                        setVehicleInfo(vehicleInfo1);
                    }

                    //Adding relation method..
                    public void addRelation(VehicleInfo vehicleInfo) {
                        that.setVehicleInfo(vehicleInfo);
                    }


                    //Fetch related data from local database if present a vehicleInfoId identifier as property for belongsTo
                    public VehicleInfo getVehicleInfo__db(RestAdapter restAdapter){
                      if(vehicleInfoId != null){
                        VehicleInfoRepository vehicleInfoRepository = restAdapter.createRepository(VehicleInfoRepository.class);
                            try{
                            SellVehicleRepository lowercaseFirstLetterRepository = (SellVehicleRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(vehicleInfoRepository.getDb() == null ){
                                                    vehicleInfoRepository.addStorage(context);
                                                }

                                                if(context != null && vehicleInfoRepository.getDb() != null){
                                                    vehicleInfoRepository.addStorage(context);
                                                    VehicleInfo vehicleInfo = (VehicleInfo) vehicleInfoRepository.getDb().get__db(vehicleInfoId);
                                                    return vehicleInfo;
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
                                    public void get__vehicleInfo( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<VehicleInfo> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SellVehicleRepository  sellVehicleRepo = restAdapter.createRepository(SellVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        sellVehicleRepo.get__vehicleInfo( (String)that.getId(), refresh,  new ObjectCallback<VehicleInfo> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(VehicleInfo object) {
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