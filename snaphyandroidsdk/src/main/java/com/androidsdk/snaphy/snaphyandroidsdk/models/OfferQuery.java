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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.OfferQueryRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.DealerRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class OfferQuery extends Model {


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

    private OfferQuery that ;

    public OfferQuery (){
        that = this;
    }

    
        
            

            
                private String subject;
                /* Adding Getter and Setter methods */
                public String getSubject(){
                    return subject;
                }

                /* Adding Getter and Setter methods */
                public void setSubject(String subject){
                    this.subject = subject;
                    //Update hashMap value..
                    hashMap.put("subject", subject);
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

            
            
        
    
        
            

            
                private String queryType;
                /* Adding Getter and Setter methods */
                public String getQueryType(){
                    return queryType;
                }

                /* Adding Getter and Setter methods */
                public void setQueryType(String queryType){
                    this.queryType = queryType;
                    //Update hashMap value..
                    hashMap.put("queryType", queryType);
                }

            
            
        
    
        
            

            
                private String customerContact;
                /* Adding Getter and Setter methods */
                public String getCustomerContact(){
                    return customerContact;
                }

                /* Adding Getter and Setter methods */
                public void setCustomerContact(String customerContact){
                    this.customerContact = customerContact;
                    //Update hashMap value..
                    hashMap.put("customerContact", customerContact);
                }

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      OfferQueryRepository lowercaseFirstLetterRepository = (OfferQueryRepository) getRepository();
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
      OfferQueryRepository lowercaseFirstLetterRepository = (OfferQueryRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      OfferQueryRepository lowercaseFirstLetterRepository = (OfferQueryRepository) getRepository();
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
                                        OfferQueryRepository offerQueryRepository = (OfferQueryRepository) getRepository();

                                        RestAdapter restAdapter = offerQueryRepository.getRestAdapter();
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
                            OfferQueryRepository lowercaseFirstLetterRepository = (OfferQueryRepository) getRepository();
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
                                        final OfferQueryRepository  offerQueryRepo = restAdapter.createRepository(OfferQueryRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        offerQueryRepo.get__customer( (String)that.getId(), refresh,  new ObjectCallback<Customer> (){
                                            

                                            
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
                    private transient Dealer  dealer ;
                    private String dealerId;

                    public String getDealerId(){
                         return dealerId;
                    }

                    public void setDealerId(Object dealerId){
                        if(dealerId != null){
                          this.dealerId = dealerId.toString();
                        }
                    }

                    public Dealer getDealer() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(dealer == null){
                                        OfferQueryRepository offerQueryRepository = (OfferQueryRepository) getRepository();

                                        RestAdapter restAdapter = offerQueryRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          dealer = getDealer__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return dealer;
                    }

                    public void setDealer(Dealer dealer) {
                        this.dealer = dealer;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setDealer(Map<String, Object> dealer) {
                        //First create a dummy Repo class object for customer.
                        DealerRepository dealerRepository = new DealerRepository();
                        Dealer dealer1 = dealerRepository.createObject(dealer);
                        setDealer(dealer1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setDealer(HashMap<String, Object> dealer) {
                        //First create a dummy Repo class object for customer.
                        DealerRepository dealerRepository = new DealerRepository();
                        Dealer dealer1 = dealerRepository.createObject(dealer);
                        setDealer(dealer1);
                    }

                    //Adding relation method..
                    public void addRelation(Dealer dealer) {
                        that.setDealer(dealer);
                    }


                    //Fetch related data from local database if present a dealerId identifier as property for belongsTo
                    public Dealer getDealer__db(RestAdapter restAdapter){
                      if(dealerId != null){
                        DealerRepository dealerRepository = restAdapter.createRepository(DealerRepository.class);
                            try{
                            OfferQueryRepository lowercaseFirstLetterRepository = (OfferQueryRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(dealerRepository.getDb() == null ){
                                                    dealerRepository.addStorage(context);
                                                }

                                                if(context != null && dealerRepository.getDb() != null){
                                                    dealerRepository.addStorage(context);
                                                    Dealer dealer = (Dealer) dealerRepository.getDb().get__db(dealerId);
                                                    return dealer;
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
                                    public void get__dealer( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Dealer> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final OfferQueryRepository  offerQueryRepo = restAdapter.createRepository(OfferQueryRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        offerQueryRepo.get__dealer( (String)that.getId(), refresh,  new ObjectCallback<Dealer> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Dealer object) {
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
