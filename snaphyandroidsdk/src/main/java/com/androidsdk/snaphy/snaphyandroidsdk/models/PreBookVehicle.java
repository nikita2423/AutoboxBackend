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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.PreBookVehicleRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.PreBookVehicleBrandRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.PreBookVehicleModelRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class PreBookVehicle extends Model {


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

    private PreBookVehicle that ;

    public PreBookVehicle (){
        that = this;
    }

    
        
            

            
                private String brandName;
                /* Adding Getter and Setter methods */
                public String getBrandName(){
                    return brandName;
                }

                /* Adding Getter and Setter methods */
                public void setBrandName(String brandName){
                    this.brandName = brandName;
                    //Update hashMap value..
                    hashMap.put("brandName", brandName);
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

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      PreBookVehicleRepository lowercaseFirstLetterRepository = (PreBookVehicleRepository) getRepository();
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
      PreBookVehicleRepository lowercaseFirstLetterRepository = (PreBookVehicleRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      PreBookVehicleRepository lowercaseFirstLetterRepository = (PreBookVehicleRepository) getRepository();
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
                    private transient PreBookVehicleBrand  preBookVehicleBrand ;
                    private String preBookVehicleBrandId;

                    public String getPreBookVehicleBrandId(){
                         return preBookVehicleBrandId;
                    }

                    public void setPreBookVehicleBrandId(Object preBookVehicleBrandId){
                        if(preBookVehicleBrandId != null){
                          this.preBookVehicleBrandId = preBookVehicleBrandId.toString();
                        }
                    }

                    public PreBookVehicleBrand getPreBookVehicleBrand() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(preBookVehicleBrand == null){
                                        PreBookVehicleRepository preBookVehicleRepository = (PreBookVehicleRepository) getRepository();

                                        RestAdapter restAdapter = preBookVehicleRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          preBookVehicleBrand = getPreBookVehicleBrand__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return preBookVehicleBrand;
                    }

                    public void setPreBookVehicleBrand(PreBookVehicleBrand preBookVehicleBrand) {
                        this.preBookVehicleBrand = preBookVehicleBrand;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setPreBookVehicleBrand(Map<String, Object> preBookVehicleBrand) {
                        //First create a dummy Repo class object for customer.
                        PreBookVehicleBrandRepository preBookVehicleBrandRepository = new PreBookVehicleBrandRepository();
                        PreBookVehicleBrand preBookVehicleBrand1 = preBookVehicleBrandRepository.createObject(preBookVehicleBrand);
                        setPreBookVehicleBrand(preBookVehicleBrand1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setPreBookVehicleBrand(HashMap<String, Object> preBookVehicleBrand) {
                        //First create a dummy Repo class object for customer.
                        PreBookVehicleBrandRepository preBookVehicleBrandRepository = new PreBookVehicleBrandRepository();
                        PreBookVehicleBrand preBookVehicleBrand1 = preBookVehicleBrandRepository.createObject(preBookVehicleBrand);
                        setPreBookVehicleBrand(preBookVehicleBrand1);
                    }

                    //Adding relation method..
                    public void addRelation(PreBookVehicleBrand preBookVehicleBrand) {
                        that.setPreBookVehicleBrand(preBookVehicleBrand);
                    }


                    //Fetch related data from local database if present a preBookVehicleBrandId identifier as property for belongsTo
                    public PreBookVehicleBrand getPreBookVehicleBrand__db(RestAdapter restAdapter){
                      if(preBookVehicleBrandId != null){
                        PreBookVehicleBrandRepository preBookVehicleBrandRepository = restAdapter.createRepository(PreBookVehicleBrandRepository.class);
                            try{
                            PreBookVehicleRepository lowercaseFirstLetterRepository = (PreBookVehicleRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(preBookVehicleBrandRepository.getDb() == null ){
                                                    preBookVehicleBrandRepository.addStorage(context);
                                                }

                                                if(context != null && preBookVehicleBrandRepository.getDb() != null){
                                                    preBookVehicleBrandRepository.addStorage(context);
                                                    PreBookVehicleBrand preBookVehicleBrand = (PreBookVehicleBrand) preBookVehicleBrandRepository.getDb().get__db(preBookVehicleBrandId);
                                                    return preBookVehicleBrand;
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
                                    public void get__preBookVehicleBrand( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<PreBookVehicleBrand> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final PreBookVehicleRepository  preBookVehicleRepo = restAdapter.createRepository(PreBookVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        preBookVehicleRepo.get__preBookVehicleBrand( (String)that.getId(), refresh,  new ObjectCallback<PreBookVehicleBrand> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(PreBookVehicleBrand object) {
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
                    private transient PreBookVehicleModel  preBookVehicleModel ;
                    private String preBookVehicleModelId;

                    public String getPreBookVehicleModelId(){
                         return preBookVehicleModelId;
                    }

                    public void setPreBookVehicleModelId(Object preBookVehicleModelId){
                        if(preBookVehicleModelId != null){
                          this.preBookVehicleModelId = preBookVehicleModelId.toString();
                        }
                    }

                    public PreBookVehicleModel getPreBookVehicleModel() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(preBookVehicleModel == null){
                                        PreBookVehicleRepository preBookVehicleRepository = (PreBookVehicleRepository) getRepository();

                                        RestAdapter restAdapter = preBookVehicleRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          preBookVehicleModel = getPreBookVehicleModel__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return preBookVehicleModel;
                    }

                    public void setPreBookVehicleModel(PreBookVehicleModel preBookVehicleModel) {
                        this.preBookVehicleModel = preBookVehicleModel;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setPreBookVehicleModel(Map<String, Object> preBookVehicleModel) {
                        //First create a dummy Repo class object for customer.
                        PreBookVehicleModelRepository preBookVehicleModelRepository = new PreBookVehicleModelRepository();
                        PreBookVehicleModel preBookVehicleModel1 = preBookVehicleModelRepository.createObject(preBookVehicleModel);
                        setPreBookVehicleModel(preBookVehicleModel1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setPreBookVehicleModel(HashMap<String, Object> preBookVehicleModel) {
                        //First create a dummy Repo class object for customer.
                        PreBookVehicleModelRepository preBookVehicleModelRepository = new PreBookVehicleModelRepository();
                        PreBookVehicleModel preBookVehicleModel1 = preBookVehicleModelRepository.createObject(preBookVehicleModel);
                        setPreBookVehicleModel(preBookVehicleModel1);
                    }

                    //Adding relation method..
                    public void addRelation(PreBookVehicleModel preBookVehicleModel) {
                        that.setPreBookVehicleModel(preBookVehicleModel);
                    }


                    //Fetch related data from local database if present a preBookVehicleModelId identifier as property for belongsTo
                    public PreBookVehicleModel getPreBookVehicleModel__db(RestAdapter restAdapter){
                      if(preBookVehicleModelId != null){
                        PreBookVehicleModelRepository preBookVehicleModelRepository = restAdapter.createRepository(PreBookVehicleModelRepository.class);
                            try{
                            PreBookVehicleRepository lowercaseFirstLetterRepository = (PreBookVehicleRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(preBookVehicleModelRepository.getDb() == null ){
                                                    preBookVehicleModelRepository.addStorage(context);
                                                }

                                                if(context != null && preBookVehicleModelRepository.getDb() != null){
                                                    preBookVehicleModelRepository.addStorage(context);
                                                    PreBookVehicleModel preBookVehicleModel = (PreBookVehicleModel) preBookVehicleModelRepository.getDb().get__db(preBookVehicleModelId);
                                                    return preBookVehicleModel;
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
                                    public void get__preBookVehicleModel( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<PreBookVehicleModel> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final PreBookVehicleRepository  preBookVehicleRepo = restAdapter.createRepository(PreBookVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        preBookVehicleRepo.get__preBookVehicleModel( (String)that.getId(), refresh,  new ObjectCallback<PreBookVehicleModel> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(PreBookVehicleModel object) {
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