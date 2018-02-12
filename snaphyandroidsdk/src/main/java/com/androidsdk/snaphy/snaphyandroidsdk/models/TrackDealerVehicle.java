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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.TrackDealerVehicleRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.DealerRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.DealerVehicleRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class TrackDealerVehicle extends Model {


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

    private TrackDealerVehicle that ;

    public TrackDealerVehicle (){
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

            
            
        
    
        
            

            
                private double latitude;
                /* Adding Getter and Setter methods */
                public double getLatitude(){
                    return latitude;
                }

                /* Adding Getter and Setter methods */
                public void setLatitude(double latitude){
                    this.latitude = latitude;
                    //Update hashMap value..
                    hashMap.put("latitude", latitude);
                }

            
            
        
    
        
            

            
                private double longitude;
                /* Adding Getter and Setter methods */
                public double getLongitude(){
                    return longitude;
                }

                /* Adding Getter and Setter methods */
                public void setLongitude(double longitude){
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

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      TrackDealerVehicleRepository lowercaseFirstLetterRepository = (TrackDealerVehicleRepository) getRepository();
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
      TrackDealerVehicleRepository lowercaseFirstLetterRepository = (TrackDealerVehicleRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      TrackDealerVehicleRepository lowercaseFirstLetterRepository = (TrackDealerVehicleRepository) getRepository();
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
                                        TrackDealerVehicleRepository trackDealerVehicleRepository = (TrackDealerVehicleRepository) getRepository();

                                        RestAdapter restAdapter = trackDealerVehicleRepository.getRestAdapter();
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
                            TrackDealerVehicleRepository lowercaseFirstLetterRepository = (TrackDealerVehicleRepository) getRepository();
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
                                        final TrackDealerVehicleRepository  trackDealerVehicleRepo = restAdapter.createRepository(TrackDealerVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        trackDealerVehicleRepo.get__dealer( (String)that.getId(), refresh,  new ObjectCallback<Dealer> (){
                                            

                                            
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
                                 
                            
                         
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                 
                 
             
          
    
        
        
                
                    //Define belongsTo relation method here..
                    private transient DealerVehicle  dealerVehicle ;
                    private String dealerVehicleId;

                    public String getDealerVehicleId(){
                         return dealerVehicleId;
                    }

                    public void setDealerVehicleId(Object dealerVehicleId){
                        if(dealerVehicleId != null){
                          this.dealerVehicleId = dealerVehicleId.toString();
                        }
                    }

                    public DealerVehicle getDealerVehicle() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(dealerVehicle == null){
                                        TrackDealerVehicleRepository trackDealerVehicleRepository = (TrackDealerVehicleRepository) getRepository();

                                        RestAdapter restAdapter = trackDealerVehicleRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          dealerVehicle = getDealerVehicle__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return dealerVehicle;
                    }

                    public void setDealerVehicle(DealerVehicle dealerVehicle) {
                        this.dealerVehicle = dealerVehicle;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setDealerVehicle(Map<String, Object> dealerVehicle) {
                        //First create a dummy Repo class object for customer.
                        DealerVehicleRepository dealerVehicleRepository = new DealerVehicleRepository();
                        DealerVehicle dealerVehicle1 = dealerVehicleRepository.createObject(dealerVehicle);
                        setDealerVehicle(dealerVehicle1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setDealerVehicle(HashMap<String, Object> dealerVehicle) {
                        //First create a dummy Repo class object for customer.
                        DealerVehicleRepository dealerVehicleRepository = new DealerVehicleRepository();
                        DealerVehicle dealerVehicle1 = dealerVehicleRepository.createObject(dealerVehicle);
                        setDealerVehicle(dealerVehicle1);
                    }

                    //Adding relation method..
                    public void addRelation(DealerVehicle dealerVehicle) {
                        that.setDealerVehicle(dealerVehicle);
                    }


                    //Fetch related data from local database if present a dealerVehicleId identifier as property for belongsTo
                    public DealerVehicle getDealerVehicle__db(RestAdapter restAdapter){
                      if(dealerVehicleId != null){
                        DealerVehicleRepository dealerVehicleRepository = restAdapter.createRepository(DealerVehicleRepository.class);
                            try{
                            TrackDealerVehicleRepository lowercaseFirstLetterRepository = (TrackDealerVehicleRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(dealerVehicleRepository.getDb() == null ){
                                                    dealerVehicleRepository.addStorage(context);
                                                }

                                                if(context != null && dealerVehicleRepository.getDb() != null){
                                                    dealerVehicleRepository.addStorage(context);
                                                    DealerVehicle dealerVehicle = (DealerVehicle) dealerVehicleRepository.getDb().get__db(dealerVehicleId);
                                                    return dealerVehicle;
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
                                    public void get__dealerVehicle( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<DealerVehicle> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final TrackDealerVehicleRepository  trackDealerVehicleRepo = restAdapter.createRepository(TrackDealerVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        trackDealerVehicleRepo.get__dealerVehicle( (String)that.getId(), refresh,  new ObjectCallback<DealerVehicle> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(DealerVehicle object) {
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
