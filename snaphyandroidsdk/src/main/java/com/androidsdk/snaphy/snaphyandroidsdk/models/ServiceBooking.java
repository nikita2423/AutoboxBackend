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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.ServiceBookingRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.ServiceTypeRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.WorkshopRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class ServiceBooking extends Model {


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

    private ServiceBooking that ;

    public ServiceBooking (){
        that = this;
    }

    
        
            

            
                private String serviceDate;
                /* Adding Getter and Setter methods */
                public String getServiceDate(){
                    return serviceDate;
                }

                /* Adding Getter and Setter methods */
                public void setServiceDate(String serviceDate){
                    this.serviceDate = serviceDate;
                    //Update hashMap value..
                    hashMap.put("serviceDate", serviceDate);
                }

            
            
        
    
        
            

            
                private String comments;
                /* Adding Getter and Setter methods */
                public String getComments(){
                    return comments;
                }

                /* Adding Getter and Setter methods */
                public void setComments(String comments){
                    this.comments = comments;
                    //Update hashMap value..
                    hashMap.put("comments", comments);
                }

            
            
        
    
        
            

            
                private String vehiclePickup;
                /* Adding Getter and Setter methods */
                public String getVehiclePickup(){
                    return vehiclePickup;
                }

                /* Adding Getter and Setter methods */
                public void setVehiclePickup(String vehiclePickup){
                    this.vehiclePickup = vehiclePickup;
                    //Update hashMap value..
                    hashMap.put("vehiclePickup", vehiclePickup);
                }

            
            
        
    
        
            

            
                private String bookingNumber;
                /* Adding Getter and Setter methods */
                public String getBookingNumber(){
                    return bookingNumber;
                }

                /* Adding Getter and Setter methods */
                public void setBookingNumber(String bookingNumber){
                    this.bookingNumber = bookingNumber;
                    //Update hashMap value..
                    hashMap.put("bookingNumber", bookingNumber);
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
      ServiceBookingRepository lowercaseFirstLetterRepository = (ServiceBookingRepository) getRepository();
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
      ServiceBookingRepository lowercaseFirstLetterRepository = (ServiceBookingRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      ServiceBookingRepository lowercaseFirstLetterRepository = (ServiceBookingRepository) getRepository();
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
                    private transient ServiceType  serviceType ;
                    private String serviceTypeId;

                    public String getServiceTypeId(){
                         return serviceTypeId;
                    }

                    public void setServiceTypeId(Object serviceTypeId){
                        if(serviceTypeId != null){
                          this.serviceTypeId = serviceTypeId.toString();
                        }
                    }

                    public ServiceType getServiceType() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(serviceType == null){
                                        ServiceBookingRepository serviceBookingRepository = (ServiceBookingRepository) getRepository();

                                        RestAdapter restAdapter = serviceBookingRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          serviceType = getServiceType__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return serviceType;
                    }

                    public void setServiceType(ServiceType serviceType) {
                        this.serviceType = serviceType;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setServiceType(Map<String, Object> serviceType) {
                        //First create a dummy Repo class object for customer.
                        ServiceTypeRepository serviceTypeRepository = new ServiceTypeRepository();
                        ServiceType serviceType1 = serviceTypeRepository.createObject(serviceType);
                        setServiceType(serviceType1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setServiceType(HashMap<String, Object> serviceType) {
                        //First create a dummy Repo class object for customer.
                        ServiceTypeRepository serviceTypeRepository = new ServiceTypeRepository();
                        ServiceType serviceType1 = serviceTypeRepository.createObject(serviceType);
                        setServiceType(serviceType1);
                    }

                    //Adding relation method..
                    public void addRelation(ServiceType serviceType) {
                        that.setServiceType(serviceType);
                    }


                    //Fetch related data from local database if present a serviceTypeId identifier as property for belongsTo
                    public ServiceType getServiceType__db(RestAdapter restAdapter){
                      if(serviceTypeId != null){
                        ServiceTypeRepository serviceTypeRepository = restAdapter.createRepository(ServiceTypeRepository.class);
                            try{
                            ServiceBookingRepository lowercaseFirstLetterRepository = (ServiceBookingRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(serviceTypeRepository.getDb() == null ){
                                                    serviceTypeRepository.addStorage(context);
                                                }

                                                if(context != null && serviceTypeRepository.getDb() != null){
                                                    serviceTypeRepository.addStorage(context);
                                                    ServiceType serviceType = (ServiceType) serviceTypeRepository.getDb().get__db(serviceTypeId);
                                                    return serviceType;
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
                                    public void get__serviceType( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<ServiceType> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final ServiceBookingRepository  serviceBookingRepo = restAdapter.createRepository(ServiceBookingRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        serviceBookingRepo.get__serviceType( (String)that.getId(), refresh,  new ObjectCallback<ServiceType> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(ServiceType object) {
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
                    private transient Workshop  workshop ;
                    private String workshopId;

                    public String getWorkshopId(){
                         return workshopId;
                    }

                    public void setWorkshopId(Object workshopId){
                        if(workshopId != null){
                          this.workshopId = workshopId.toString();
                        }
                    }

                    public Workshop getWorkshop() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(workshop == null){
                                        ServiceBookingRepository serviceBookingRepository = (ServiceBookingRepository) getRepository();

                                        RestAdapter restAdapter = serviceBookingRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          workshop = getWorkshop__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return workshop;
                    }

                    public void setWorkshop(Workshop workshop) {
                        this.workshop = workshop;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setWorkshop(Map<String, Object> workshop) {
                        //First create a dummy Repo class object for customer.
                        WorkshopRepository workshopRepository = new WorkshopRepository();
                        Workshop workshop1 = workshopRepository.createObject(workshop);
                        setWorkshop(workshop1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setWorkshop(HashMap<String, Object> workshop) {
                        //First create a dummy Repo class object for customer.
                        WorkshopRepository workshopRepository = new WorkshopRepository();
                        Workshop workshop1 = workshopRepository.createObject(workshop);
                        setWorkshop(workshop1);
                    }

                    //Adding relation method..
                    public void addRelation(Workshop workshop) {
                        that.setWorkshop(workshop);
                    }


                    //Fetch related data from local database if present a workshopId identifier as property for belongsTo
                    public Workshop getWorkshop__db(RestAdapter restAdapter){
                      if(workshopId != null){
                        WorkshopRepository workshopRepository = restAdapter.createRepository(WorkshopRepository.class);
                            try{
                            ServiceBookingRepository lowercaseFirstLetterRepository = (ServiceBookingRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(workshopRepository.getDb() == null ){
                                                    workshopRepository.addStorage(context);
                                                }

                                                if(context != null && workshopRepository.getDb() != null){
                                                    workshopRepository.addStorage(context);
                                                    Workshop workshop = (Workshop) workshopRepository.getDb().get__db(workshopId);
                                                    return workshop;
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
                                    public void get__workshop( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Workshop> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final ServiceBookingRepository  serviceBookingRepo = restAdapter.createRepository(ServiceBookingRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        serviceBookingRepo.get__workshop( (String)that.getId(), refresh,  new ObjectCallback<Workshop> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Workshop object) {
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
                                        ServiceBookingRepository serviceBookingRepository = (ServiceBookingRepository) getRepository();

                                        RestAdapter restAdapter = serviceBookingRepository.getRestAdapter();
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
                            ServiceBookingRepository lowercaseFirstLetterRepository = (ServiceBookingRepository) getRepository();
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
                                        final ServiceBookingRepository  serviceBookingRepo = restAdapter.createRepository(ServiceBookingRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        serviceBookingRepo.get__customer( (String)that.getId(), refresh,  new ObjectCallback<Customer> (){
                                            

                                            
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
