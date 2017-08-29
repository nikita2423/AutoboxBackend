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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.QuoteReplyRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerQuoteRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.DealerRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class QuoteReply extends Model {


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

    private QuoteReply that ;

    public QuoteReply (){
        that = this;
    }

    
        
            

            
                private double exShowroomPrice;
                /* Adding Getter and Setter methods */
                public double getExShowroomPrice(){
                    return exShowroomPrice;
                }

                /* Adding Getter and Setter methods */
                public void setExShowroomPrice(double exShowroomPrice){
                    this.exShowroomPrice = exShowroomPrice;
                    //Update hashMap value..
                    hashMap.put("exShowroomPrice", exShowroomPrice);
                }

            
            
        
    
        
            

            
                private double exchangeBonus;
                /* Adding Getter and Setter methods */
                public double getExchangeBonus(){
                    return exchangeBonus;
                }

                /* Adding Getter and Setter methods */
                public void setExchangeBonus(double exchangeBonus){
                    this.exchangeBonus = exchangeBonus;
                    //Update hashMap value..
                    hashMap.put("exchangeBonus", exchangeBonus);
                }

            
            
        
    
        
            

            
                private double insurance;
                /* Adding Getter and Setter methods */
                public double getInsurance(){
                    return insurance;
                }

                /* Adding Getter and Setter methods */
                public void setInsurance(double insurance){
                    this.insurance = insurance;
                    //Update hashMap value..
                    hashMap.put("insurance", insurance);
                }

            
            
        
    
        
            

            
                private double specialDiscount;
                /* Adding Getter and Setter methods */
                public double getSpecialDiscount(){
                    return specialDiscount;
                }

                /* Adding Getter and Setter methods */
                public void setSpecialDiscount(double specialDiscount){
                    this.specialDiscount = specialDiscount;
                    //Update hashMap value..
                    hashMap.put("specialDiscount", specialDiscount);
                }

            
            
        
    
        
            

            
                private String rtoRegistration;
                /* Adding Getter and Setter methods */
                public String getRtoRegistration(){
                    return rtoRegistration;
                }

                /* Adding Getter and Setter methods */
                public void setRtoRegistration(String rtoRegistration){
                    this.rtoRegistration = rtoRegistration;
                    //Update hashMap value..
                    hashMap.put("rtoRegistration", rtoRegistration);
                }

            
            
        
    
        
            

            
                private double loyaltyBonus;
                /* Adding Getter and Setter methods */
                public double getLoyaltyBonus(){
                    return loyaltyBonus;
                }

                /* Adding Getter and Setter methods */
                public void setLoyaltyBonus(double loyaltyBonus){
                    this.loyaltyBonus = loyaltyBonus;
                    //Update hashMap value..
                    hashMap.put("loyaltyBonus", loyaltyBonus);
                }

            
            
        
    
        
            

            
                private double miscCharges;
                /* Adding Getter and Setter methods */
                public double getMiscCharges(){
                    return miscCharges;
                }

                /* Adding Getter and Setter methods */
                public void setMiscCharges(double miscCharges){
                    this.miscCharges = miscCharges;
                    //Update hashMap value..
                    hashMap.put("miscCharges", miscCharges);
                }

            
            
        
    
        
            

            
                private double gst;
                /* Adding Getter and Setter methods */
                public double getGst(){
                    return gst;
                }

                /* Adding Getter and Setter methods */
                public void setGst(double gst){
                    this.gst = gst;
                    //Update hashMap value..
                    hashMap.put("gst", gst);
                }

            
            
        
    
        
            

            
                private double roadPrice;
                /* Adding Getter and Setter methods */
                public double getRoadPrice(){
                    return roadPrice;
                }

                /* Adding Getter and Setter methods */
                public void setRoadPrice(double roadPrice){
                    this.roadPrice = roadPrice;
                    //Update hashMap value..
                    hashMap.put("roadPrice", roadPrice);
                }

            
            
        
    
        
            

            
                private String replyNumber;
                /* Adding Getter and Setter methods */
                public String getReplyNumber(){
                    return replyNumber;
                }

                /* Adding Getter and Setter methods */
                public void setReplyNumber(String replyNumber){
                    this.replyNumber = replyNumber;
                    //Update hashMap value..
                    hashMap.put("replyNumber", replyNumber);
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
      QuoteReplyRepository lowercaseFirstLetterRepository = (QuoteReplyRepository) getRepository();
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
      QuoteReplyRepository lowercaseFirstLetterRepository = (QuoteReplyRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      QuoteReplyRepository lowercaseFirstLetterRepository = (QuoteReplyRepository) getRepository();
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
                    private transient CustomerQuote  customerQuote ;
                    private String customerQuoteId;

                    public String getCustomerQuoteId(){
                         return customerQuoteId;
                    }

                    public void setCustomerQuoteId(Object customerQuoteId){
                        if(customerQuoteId != null){
                          this.customerQuoteId = customerQuoteId.toString();
                        }
                    }

                    public CustomerQuote getCustomerQuote() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(customerQuote == null){
                                        QuoteReplyRepository quoteReplyRepository = (QuoteReplyRepository) getRepository();

                                        RestAdapter restAdapter = quoteReplyRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          customerQuote = getCustomerQuote__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return customerQuote;
                    }

                    public void setCustomerQuote(CustomerQuote customerQuote) {
                        this.customerQuote = customerQuote;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setCustomerQuote(Map<String, Object> customerQuote) {
                        //First create a dummy Repo class object for customer.
                        CustomerQuoteRepository customerQuoteRepository = new CustomerQuoteRepository();
                        CustomerQuote customerQuote1 = customerQuoteRepository.createObject(customerQuote);
                        setCustomerQuote(customerQuote1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setCustomerQuote(HashMap<String, Object> customerQuote) {
                        //First create a dummy Repo class object for customer.
                        CustomerQuoteRepository customerQuoteRepository = new CustomerQuoteRepository();
                        CustomerQuote customerQuote1 = customerQuoteRepository.createObject(customerQuote);
                        setCustomerQuote(customerQuote1);
                    }

                    //Adding relation method..
                    public void addRelation(CustomerQuote customerQuote) {
                        that.setCustomerQuote(customerQuote);
                    }


                    //Fetch related data from local database if present a customerQuoteId identifier as property for belongsTo
                    public CustomerQuote getCustomerQuote__db(RestAdapter restAdapter){
                      if(customerQuoteId != null){
                        CustomerQuoteRepository customerQuoteRepository = restAdapter.createRepository(CustomerQuoteRepository.class);
                            try{
                            QuoteReplyRepository lowercaseFirstLetterRepository = (QuoteReplyRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(customerQuoteRepository.getDb() == null ){
                                                    customerQuoteRepository.addStorage(context);
                                                }

                                                if(context != null && customerQuoteRepository.getDb() != null){
                                                    customerQuoteRepository.addStorage(context);
                                                    CustomerQuote customerQuote = (CustomerQuote) customerQuoteRepository.getDb().get__db(customerQuoteId);
                                                    return customerQuote;
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
                                    public void get__customerQuote( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<CustomerQuote> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final QuoteReplyRepository  quoteReplyRepo = restAdapter.createRepository(QuoteReplyRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        quoteReplyRepo.get__customerQuote( (String)that.getId(), refresh,  new ObjectCallback<CustomerQuote> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(CustomerQuote object) {
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
                                        QuoteReplyRepository quoteReplyRepository = (QuoteReplyRepository) getRepository();

                                        RestAdapter restAdapter = quoteReplyRepository.getRestAdapter();
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
                            QuoteReplyRepository lowercaseFirstLetterRepository = (QuoteReplyRepository) getRepository();
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
                                        final QuoteReplyRepository  quoteReplyRepo = restAdapter.createRepository(QuoteReplyRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        quoteReplyRepo.get__dealer( (String)that.getId(), refresh,  new ObjectCallback<Dealer> (){
                                            

                                            
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
