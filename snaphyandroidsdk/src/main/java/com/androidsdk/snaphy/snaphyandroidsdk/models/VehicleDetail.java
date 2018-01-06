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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.VehicleDetailRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.ShowroomRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.WorkshopRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.VehicleInfoRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.InsuranceRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class VehicleDetail extends Model {


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

    private VehicleDetail that ;

    public VehicleDetail (){
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

            
            
        
    
        
            

            
                private String registeredName;
                /* Adding Getter and Setter methods */
                public String getRegisteredName(){
                    return registeredName;
                }

                /* Adding Getter and Setter methods */
                public void setRegisteredName(String registeredName){
                    this.registeredName = registeredName;
                    //Update hashMap value..
                    hashMap.put("registeredName", registeredName);
                }

            
            
        
    
        
            

            
                private String showroomName;
                /* Adding Getter and Setter methods */
                public String getShowroomName(){
                    return showroomName;
                }

                /* Adding Getter and Setter methods */
                public void setShowroomName(String showroomName){
                    this.showroomName = showroomName;
                    //Update hashMap value..
                    hashMap.put("showroomName", showroomName);
                }

            
            
        
    
        
            

            
                private String workshopName;
                /* Adding Getter and Setter methods */
                public String getWorkshopName(){
                    return workshopName;
                }

                /* Adding Getter and Setter methods */
                public void setWorkshopName(String workshopName){
                    this.workshopName = workshopName;
                    //Update hashMap value..
                    hashMap.put("workshopName", workshopName);
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

            
            
        
    
        
            

            
                private String vehicleType;
                /* Adding Getter and Setter methods */
                public String getVehicleType(){
                    return vehicleType;
                }

                /* Adding Getter and Setter methods */
                public void setVehicleType(String vehicleType){
                    this.vehicleType = vehicleType;
                    //Update hashMap value..
                    hashMap.put("vehicleType", vehicleType);
                }

            
            
        
    
        
            

            
                private Map<String, Object> vehicleInsuranceProof;
                /* Adding Getter and Setter methods */
                public Map<String, Object> getVehicleInsuranceProof(){
                    return vehicleInsuranceProof;
                }

                /* Adding Getter and Setter methods */
                public void setVehicleInsuranceProof(Map<String, Object> vehicleInsuranceProof){
                    this.vehicleInsuranceProof = vehicleInsuranceProof;
                    //Update Map value..
                    hashMap.put("vehicleInsuranceProof", vehicleInsuranceProof);
                }

            
            
        
    
        
            

            
                private Map<String, Object> vehicleRegistrationProof;
                /* Adding Getter and Setter methods */
                public Map<String, Object> getVehicleRegistrationProof(){
                    return vehicleRegistrationProof;
                }

                /* Adding Getter and Setter methods */
                public void setVehicleRegistrationProof(Map<String, Object> vehicleRegistrationProof){
                    this.vehicleRegistrationProof = vehicleRegistrationProof;
                    //Update Map value..
                    hashMap.put("vehicleRegistrationProof", vehicleRegistrationProof);
                }

            
            
        
    
        
            

            
                private Map<String, Object> pucProof;
                /* Adding Getter and Setter methods */
                public Map<String, Object> getPucProof(){
                    return pucProof;
                }

                /* Adding Getter and Setter methods */
                public void setPucProof(Map<String, Object> pucProof){
                    this.pucProof = pucProof;
                    //Update Map value..
                    hashMap.put("pucProof", pucProof);
                }

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      VehicleDetailRepository lowercaseFirstLetterRepository = (VehicleDetailRepository) getRepository();
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
      VehicleDetailRepository lowercaseFirstLetterRepository = (VehicleDetailRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      VehicleDetailRepository lowercaseFirstLetterRepository = (VehicleDetailRepository) getRepository();
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
                    private transient Showroom  showroom ;
                    private String showroomId;

                    public String getShowroomId(){
                         return showroomId;
                    }

                    public void setShowroomId(Object showroomId){
                        if(showroomId != null){
                          this.showroomId = showroomId.toString();
                        }
                    }

                    public Showroom getShowroom() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(showroom == null){
                                        VehicleDetailRepository vehicleDetailRepository = (VehicleDetailRepository) getRepository();

                                        RestAdapter restAdapter = vehicleDetailRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          showroom = getShowroom__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return showroom;
                    }

                    public void setShowroom(Showroom showroom) {
                        this.showroom = showroom;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setShowroom(Map<String, Object> showroom) {
                        //First create a dummy Repo class object for customer.
                        ShowroomRepository showroomRepository = new ShowroomRepository();
                        Showroom showroom1 = showroomRepository.createObject(showroom);
                        setShowroom(showroom1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setShowroom(HashMap<String, Object> showroom) {
                        //First create a dummy Repo class object for customer.
                        ShowroomRepository showroomRepository = new ShowroomRepository();
                        Showroom showroom1 = showroomRepository.createObject(showroom);
                        setShowroom(showroom1);
                    }

                    //Adding relation method..
                    public void addRelation(Showroom showroom) {
                        that.setShowroom(showroom);
                    }


                    //Fetch related data from local database if present a showroomId identifier as property for belongsTo
                    public Showroom getShowroom__db(RestAdapter restAdapter){
                      if(showroomId != null){
                        ShowroomRepository showroomRepository = restAdapter.createRepository(ShowroomRepository.class);
                            try{
                            VehicleDetailRepository lowercaseFirstLetterRepository = (VehicleDetailRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(showroomRepository.getDb() == null ){
                                                    showroomRepository.addStorage(context);
                                                }

                                                if(context != null && showroomRepository.getDb() != null){
                                                    showroomRepository.addStorage(context);
                                                    Showroom showroom = (Showroom) showroomRepository.getDb().get__db(showroomId);
                                                    return showroom;
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
                                    public void get__showroom( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Showroom> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final VehicleDetailRepository  vehicleDetailRepo = restAdapter.createRepository(VehicleDetailRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        vehicleDetailRepo.get__showroom( (String)that.getId(), refresh,  new ObjectCallback<Showroom> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Showroom object) {
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
                                        VehicleDetailRepository vehicleDetailRepository = (VehicleDetailRepository) getRepository();

                                        RestAdapter restAdapter = vehicleDetailRepository.getRestAdapter();
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
                            VehicleDetailRepository lowercaseFirstLetterRepository = (VehicleDetailRepository) getRepository();
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
                                        final VehicleDetailRepository  vehicleDetailRepo = restAdapter.createRepository(VehicleDetailRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        vehicleDetailRepo.get__workshop( (String)that.getId(), refresh,  new ObjectCallback<Workshop> (){
                                            

                                            
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
                                        VehicleDetailRepository vehicleDetailRepository = (VehicleDetailRepository) getRepository();

                                        RestAdapter restAdapter = vehicleDetailRepository.getRestAdapter();
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
                            VehicleDetailRepository lowercaseFirstLetterRepository = (VehicleDetailRepository) getRepository();
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
                                        final VehicleDetailRepository  vehicleDetailRepo = restAdapter.createRepository(VehicleDetailRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        vehicleDetailRepo.get__customer( (String)that.getId(), refresh,  new ObjectCallback<Customer> (){
                                            

                                            
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
                                        VehicleDetailRepository vehicleDetailRepository = (VehicleDetailRepository) getRepository();

                                        RestAdapter restAdapter = vehicleDetailRepository.getRestAdapter();
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
                            VehicleDetailRepository lowercaseFirstLetterRepository = (VehicleDetailRepository) getRepository();
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
                                        final VehicleDetailRepository  vehicleDetailRepo = restAdapter.createRepository(VehicleDetailRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        vehicleDetailRepo.get__vehicleInfo( (String)that.getId(), refresh,  new ObjectCallback<VehicleInfo> (){
                                            

                                            
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
                                 
                            
                         
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                 
                 
             
          
    
        
        
                
                    //Define belongsTo relation method here..
                    private transient Insurance  insurance ;
                    private String insuranceId;

                    public String getInsuranceId(){
                         return insuranceId;
                    }

                    public void setInsuranceId(Object insuranceId){
                        if(insuranceId != null){
                          this.insuranceId = insuranceId.toString();
                        }
                    }

                    public Insurance getInsurance() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(insurance == null){
                                        VehicleDetailRepository vehicleDetailRepository = (VehicleDetailRepository) getRepository();

                                        RestAdapter restAdapter = vehicleDetailRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          insurance = getInsurance__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return insurance;
                    }

                    public void setInsurance(Insurance insurance) {
                        this.insurance = insurance;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setInsurance(Map<String, Object> insurance) {
                        //First create a dummy Repo class object for customer.
                        InsuranceRepository insuranceRepository = new InsuranceRepository();
                        Insurance insurance1 = insuranceRepository.createObject(insurance);
                        setInsurance(insurance1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setInsurance(HashMap<String, Object> insurance) {
                        //First create a dummy Repo class object for customer.
                        InsuranceRepository insuranceRepository = new InsuranceRepository();
                        Insurance insurance1 = insuranceRepository.createObject(insurance);
                        setInsurance(insurance1);
                    }

                    //Adding relation method..
                    public void addRelation(Insurance insurance) {
                        that.setInsurance(insurance);
                    }


                    //Fetch related data from local database if present a insuranceId identifier as property for belongsTo
                    public Insurance getInsurance__db(RestAdapter restAdapter){
                      if(insuranceId != null){
                        InsuranceRepository insuranceRepository = restAdapter.createRepository(InsuranceRepository.class);
                            try{
                            VehicleDetailRepository lowercaseFirstLetterRepository = (VehicleDetailRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(insuranceRepository.getDb() == null ){
                                                    insuranceRepository.addStorage(context);
                                                }

                                                if(context != null && insuranceRepository.getDb() != null){
                                                    insuranceRepository.addStorage(context);
                                                    Insurance insurance = (Insurance) insuranceRepository.getDb().get__db(insuranceId);
                                                    return insurance;
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
                                    public void get__insurance( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Insurance> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final VehicleDetailRepository  vehicleDetailRepo = restAdapter.createRepository(VehicleDetailRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        vehicleDetailRepo.get__insurance( (String)that.getId(), refresh,  new ObjectCallback<Insurance> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Insurance object) {
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
