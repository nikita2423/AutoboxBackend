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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.ChauffeurRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.VehicleDetailRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Chauffeur extends Model {


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

    private Chauffeur that ;

    public Chauffeur (){
        that = this;
    }

    
        
            

            
                private String chauffeurContact;
                /* Adding Getter and Setter methods */
                public String getChauffeurContact(){
                    return chauffeurContact;
                }

                /* Adding Getter and Setter methods */
                public void setChauffeurContact(String chauffeurContact){
                    this.chauffeurContact = chauffeurContact;
                    //Update hashMap value..
                    hashMap.put("chauffeurContact", chauffeurContact);
                }

            
            
        
    
        
            

            
                private String name;
                /* Adding Getter and Setter methods */
                public String getName(){
                    return name;
                }

                /* Adding Getter and Setter methods */
                public void setName(String name){
                    this.name = name;
                    //Update hashMap value..
                    hashMap.put("name", name);
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

            
            
        
    
        
            

            
                private String message;
                /* Adding Getter and Setter methods */
                public String getMessage(){
                    return message;
                }

                /* Adding Getter and Setter methods */
                public void setMessage(String message){
                    this.message = message;
                    //Update hashMap value..
                    hashMap.put("message", message);
                }

            
            
        
    
        
            

            
                private String driverId;
                /* Adding Getter and Setter methods */
                public String getDriverId(){
                    return driverId;
                }

                /* Adding Getter and Setter methods */
                public void setDriverId(String driverId){
                    this.driverId = driverId;
                    //Update hashMap value..
                    hashMap.put("driverId", driverId);
                }

            
            
        
    
        
            

            
                private String ownerName;
                /* Adding Getter and Setter methods */
                public String getOwnerName(){
                    return ownerName;
                }

                /* Adding Getter and Setter methods */
                public void setOwnerName(String ownerName){
                    this.ownerName = ownerName;
                    //Update hashMap value..
                    hashMap.put("ownerName", ownerName);
                }

            
            
        
    
        
            

            
                private String ownerContact;
                /* Adding Getter and Setter methods */
                public String getOwnerContact(){
                    return ownerContact;
                }

                /* Adding Getter and Setter methods */
                public void setOwnerContact(String ownerContact){
                    this.ownerContact = ownerContact;
                    //Update hashMap value..
                    hashMap.put("ownerContact", ownerContact);
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
      ChauffeurRepository lowercaseFirstLetterRepository = (ChauffeurRepository) getRepository();
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
      ChauffeurRepository lowercaseFirstLetterRepository = (ChauffeurRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      ChauffeurRepository lowercaseFirstLetterRepository = (ChauffeurRepository) getRepository();
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
                                        ChauffeurRepository chauffeurRepository = (ChauffeurRepository) getRepository();

                                        RestAdapter restAdapter = chauffeurRepository.getRestAdapter();
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
                            ChauffeurRepository lowercaseFirstLetterRepository = (ChauffeurRepository) getRepository();
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
                                        final ChauffeurRepository  chauffeurRepo = restAdapter.createRepository(ChauffeurRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        chauffeurRepo.get__customer( (String)that.getId(), refresh,  new ObjectCallback<Customer> (){
                                            

                                            
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
                                        ChauffeurRepository chauffeurRepository = (ChauffeurRepository) getRepository();

                                        RestAdapter restAdapter = chauffeurRepository.getRestAdapter();
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
                            ChauffeurRepository lowercaseFirstLetterRepository = (ChauffeurRepository) getRepository();
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
                                        final ChauffeurRepository  chauffeurRepo = restAdapter.createRepository(ChauffeurRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        chauffeurRepo.get__vehicleDetail( (String)that.getId(), refresh,  new ObjectCallback<VehicleDetail> (){
                                            

                                            
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
