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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.BreakdownCategoryRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.BreakdownRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class BreakdownCategory extends Model {


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

    private BreakdownCategory that ;

    public BreakdownCategory (){
        that = this;
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
      BreakdownCategoryRepository lowercaseFirstLetterRepository = (BreakdownCategoryRepository) getRepository();
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
      BreakdownCategoryRepository lowercaseFirstLetterRepository = (BreakdownCategoryRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      BreakdownCategoryRepository lowercaseFirstLetterRepository = (BreakdownCategoryRepository) getRepository();
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
    
        
        
                

                
                    
                    //Define hasMany relation method here..
                    private transient DataList<Breakdown>  breakdowns ;

                    public DataList< Breakdown > getBreakdowns() {
                        //Check for pure case of hasMany
                                                    //TODO: Modify foreign key name..
                          try{
                            BreakdownRepository breakdownRepository = (BreakdownRepository) getRepository();

                            if(that.getId() != null && breakdownRepository.getDb() != null){

                                 //Fetch locally from db
                                 //breakdowns = getBreakdowns__db(restAdapter);
                                 // Getting single cont
                                 breakdowns = breakdownRepository.getDb().getAll__db("breakdownCategoryId", that.getId().toString());

                                 //lowercaseFirstLetter(modelName)
                            }
                          }catch(Exception e){
                                //Ignore
                          }
                                                return breakdowns;
                    }

                    public void setBreakdowns(DataList<Breakdown> breakdowns) {
                        boolean hashType = false;
                        DataList<HashMap<String, Object>> hashMaps = new DataList<>();
                        for(Object o: breakdowns){
                            if(o.getClass().equals(HashMap.class)){
                                hashType = true;
                                HashMap<String, Object> dataObj = (HashMap<String, Object>)o;
                                hashMaps.add(dataObj);
                            }
                        }

                        if(hashType){
                            setBreakdowns1(hashMaps);
                        }else{
                            this.breakdowns = breakdowns;
                            //TODO: Warning move this to new thread
                            for(Breakdown data: breakdowns){
                              try{
                                data.save__db();
                              } catch (NoSuchMethodError e) {
                                // ignore
                              }
                            }
                        }
                    }

                /*    //Adding related model automatically in case of include statement from server.. Adding 1 for removing same name error..
                    public void setBreakdowns1(List<Map<String, Object>> breakdowns) {
                        //First create a dummy Repo class object for ..
                        BreakdownRepository breakdownsRepository = new BreakdownRepository();
                        List<Breakdown> result = new ArrayList<>();
                        for (Map<String, Object> obj : breakdowns) {
                            //Also add relation to child type for two way communication..
                            Breakdown obj1 = breakdownsRepository.createObject(obj);
                            result.add(obj1);

                        }
                        setBreakdowns(result);

                    }

                */

                    //Adding related model automatically in case of include statement from server.. Adding 1 for removing same name error..
                    public void setBreakdowns1(DataList<HashMap<String, Object>> breakdowns) {
                        //First create a dummy Repo class object for ..
                        BreakdownRepository breakdownsRepository = new BreakdownRepository();
                        DataList<Breakdown> result = new DataList<>();
                        for (HashMap<String, Object> obj : breakdowns) {
                            //Also add relation to child type for two way communication..
                            Breakdown obj1 = breakdownsRepository.createObject(obj);
                            result.add(obj1);

                        }
                        setBreakdowns(result);

                    }


                    //Adding relation method..
                    //Add a dummy class Name object to seperate data..
                    public void addRelation(DataList<Breakdown> breakdowns, Breakdown dummyClassInstance) {
                        that.setBreakdowns(breakdowns);

                    }

                    //Adding relation method..
                    //This will add a new data to the list relation object..
                    public void addRelation(Breakdown breakdowns) {
                        try{
                            try{

                                  //Save to database..
                                  breakdowns.save__db();
                            }catch (NoSuchMethodError e) {
                              // ignore
                            }
                            that.getBreakdowns().add(breakdowns);
                        }catch(Exception e){
                            DataList< Breakdown> breakdowns1 = new DataList();
                            //Now add this item to list..
                            breakdowns1.add(breakdowns);
                            //Now set data....
                            that.setBreakdowns(breakdowns1);
                        }
                    }




                    
                        //Implement logic for pure hasMany methods here....

                    
                
                







                    //Now add instance methods to fetch the related belongsTo Model..
                    

                    

                                    //Write the method here..
                                    public void findById__breakdowns( String fk,  RestAdapter restAdapter, final ObjectCallback<Breakdown> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final BreakdownCategoryRepository  breakdownCategoryRepo = restAdapter.createRepository(BreakdownCategoryRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        breakdownCategoryRepo.findById__breakdowns( (String)that.getId(), fk,  new ObjectCallback<Breakdown> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Breakdown object) {
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
                                 
                            
                        

                                    //Write the method here..
                                    public void destroyById__breakdowns( String fk,  RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final BreakdownCategoryRepository  breakdownCategoryRepo = restAdapter.createRepository(BreakdownCategoryRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        breakdownCategoryRepo.destroyById__breakdowns( (String)that.getId(), fk,  new VoidCallback (){
                                            
                                                @Override
                                                public void onSuccess() {
                                                    callback.onSuccess();
                                                    //Calling the finally..callback
                                                    callback.onFinally();
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
                                 
                            
                        

                                    //Write the method here..
                                    public void updateById__breakdowns( String fk,  Breakdown data,  RestAdapter restAdapter, final ObjectCallback<Breakdown> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final BreakdownCategoryRepository  breakdownCategoryRepo = restAdapter.createRepository(BreakdownCategoryRepository.class);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        



                                        breakdownCategoryRepo.updateById__breakdowns( (String)that.getId(), fk, data.convertMap(),  new ObjectCallback<Breakdown> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Breakdown object) {
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
                                 
                            
                        

                                    //Write the method here..
                                    public void get__breakdowns( Map<String,  ? extends Object> filter,  RestAdapter restAdapter, final DataListCallback<Breakdown> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final BreakdownCategoryRepository  breakdownCategoryRepo = restAdapter.createRepository(BreakdownCategoryRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        breakdownCategoryRepo.get__breakdowns( (String)that.getId(), filter,  new DataListCallback<Breakdown> (){
                                            

                                            


                                            
                                                @Override
                                                
                                                    public void onSuccess(DataList<Breakdown> object) {
                                                        if(object != null){
                                                            //now add relation to this recipe.
                                                            Breakdown obj = new Breakdown();
                                                            addRelation(object, obj);
                                                            //Disabling two way communication for cyclic error..
                                                            /*for (Breakdown obj : object) {
                                                                //Also add relation to child type for two way communication..
                                                                obj.addRelation(that);
                                                            }*/

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
                                 
                            
                        

                                    //Write the method here..
                                    public void create__breakdowns( Breakdown data,  RestAdapter restAdapter, final ObjectCallback<Breakdown> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final BreakdownCategoryRepository  breakdownCategoryRepo = restAdapter.createRepository(BreakdownCategoryRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        breakdownCategoryRepo.create__breakdowns( (String)that.getId(), data.convertMap(),  new ObjectCallback<Breakdown> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Breakdown object) {
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
                                 
                            
                        

                                    //Write the method here..
                                    public void delete__breakdowns( RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final BreakdownCategoryRepository  breakdownCategoryRepo = restAdapter.createRepository(BreakdownCategoryRepository.class);
                                        
                                        



                                        breakdownCategoryRepo.delete__breakdowns( (String)that.getId(),  new VoidCallback (){
                                            
                                                @Override
                                                public void onSuccess() {
                                                    callback.onSuccess();
                                                    //Calling the finally..callback
                                                    callback.onFinally();
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
                                 
                            
                        

                                    //Write the method here..
                                    public void count__breakdowns( Map<String,  ? extends Object> where,  RestAdapter restAdapter, final ObjectCallback<JSONObject>  callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final BreakdownCategoryRepository  breakdownCategoryRepo = restAdapter.createRepository(BreakdownCategoryRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        breakdownCategoryRepo.count__breakdowns( (String)that.getId(), where,  new ObjectCallback<JSONObject>(){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(JSONObject object) {
                                                        callback.onSuccess(object);
                                                        //Calling the finally..callback
                                                        callback.onFinally();
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
                                 
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                
                    //Define hasMany, hasManyThrough method here..

                 
                 
             
          
      

}
