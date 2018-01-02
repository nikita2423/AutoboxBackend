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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerOfferRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.OfferRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class CustomerOffer extends Model {


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

    private CustomerOffer that ;

    public CustomerOffer (){
        that = this;
    }

    
        
            

            
                private boolean readStatus;
                /* Adding Getter and Setter methods */
                public boolean getReadStatus(){
                    return readStatus;
                }

                /* Adding Getter and Setter methods */
                public void setReadStatus(boolean readStatus){
                    this.readStatus = readStatus;
                    //Update hashMap value..
                    hashMap.put("readStatus", readStatus);
                }

            
            
        
    
        
            

            
                private boolean removeStatus;
                /* Adding Getter and Setter methods */
                public boolean getRemoveStatus(){
                    return removeStatus;
                }

                /* Adding Getter and Setter methods */
                public void setRemoveStatus(boolean removeStatus){
                    this.removeStatus = removeStatus;
                    //Update hashMap value..
                    hashMap.put("removeStatus", removeStatus);
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

            
            
        
    
        
            

            
                private String expiredOn;
                /* Adding Getter and Setter methods */
                public String getExpiredOn(){
                    return expiredOn;
                }

                /* Adding Getter and Setter methods */
                public void setExpiredOn(String expiredOn){
                    this.expiredOn = expiredOn;
                    //Update hashMap value..
                    hashMap.put("expiredOn", expiredOn);
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
      CustomerOfferRepository lowercaseFirstLetterRepository = (CustomerOfferRepository) getRepository();
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
      CustomerOfferRepository lowercaseFirstLetterRepository = (CustomerOfferRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      CustomerOfferRepository lowercaseFirstLetterRepository = (CustomerOfferRepository) getRepository();
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
                    private transient Offer  offer ;
                    private String offerId;

                    public String getOfferId(){
                         return offerId;
                    }

                    public void setOfferId(Object offerId){
                        if(offerId != null){
                          this.offerId = offerId.toString();
                        }
                    }

                    public Offer getOffer() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(offer == null){
                                        CustomerOfferRepository customerOfferRepository = (CustomerOfferRepository) getRepository();

                                        RestAdapter restAdapter = customerOfferRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          offer = getOffer__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return offer;
                    }

                    public void setOffer(Offer offer) {
                        this.offer = offer;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setOffer(Map<String, Object> offer) {
                        //First create a dummy Repo class object for customer.
                        OfferRepository offerRepository = new OfferRepository();
                        Offer offer1 = offerRepository.createObject(offer);
                        setOffer(offer1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setOffer(HashMap<String, Object> offer) {
                        //First create a dummy Repo class object for customer.
                        OfferRepository offerRepository = new OfferRepository();
                        Offer offer1 = offerRepository.createObject(offer);
                        setOffer(offer1);
                    }

                    //Adding relation method..
                    public void addRelation(Offer offer) {
                        that.setOffer(offer);
                    }


                    //Fetch related data from local database if present a offerId identifier as property for belongsTo
                    public Offer getOffer__db(RestAdapter restAdapter){
                      if(offerId != null){
                        OfferRepository offerRepository = restAdapter.createRepository(OfferRepository.class);
                            try{
                            CustomerOfferRepository lowercaseFirstLetterRepository = (CustomerOfferRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(offerRepository.getDb() == null ){
                                                    offerRepository.addStorage(context);
                                                }

                                                if(context != null && offerRepository.getDb() != null){
                                                    offerRepository.addStorage(context);
                                                    Offer offer = (Offer) offerRepository.getDb().get__db(offerId);
                                                    return offer;
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
                                    public void get__offer( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Offer> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CustomerOfferRepository  customerOfferRepo = restAdapter.createRepository(CustomerOfferRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        customerOfferRepo.get__offer( (String)that.getId(), refresh,  new ObjectCallback<Offer> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Offer object) {
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
                                        CustomerOfferRepository customerOfferRepository = (CustomerOfferRepository) getRepository();

                                        RestAdapter restAdapter = customerOfferRepository.getRestAdapter();
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
                            CustomerOfferRepository lowercaseFirstLetterRepository = (CustomerOfferRepository) getRepository();
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
                                        final CustomerOfferRepository  customerOfferRepo = restAdapter.createRepository(CustomerOfferRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        customerOfferRepo.get__customer( (String)that.getId(), refresh,  new ObjectCallback<Customer> (){
                                            

                                            
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
