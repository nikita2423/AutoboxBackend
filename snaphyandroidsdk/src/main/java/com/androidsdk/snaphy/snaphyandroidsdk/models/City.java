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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.CityRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.ShowroomRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.WorkshopRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class City extends Model {


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

    private City that ;

    public City (){
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

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      CityRepository lowercaseFirstLetterRepository = (CityRepository) getRepository();
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
      CityRepository lowercaseFirstLetterRepository = (CityRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      CityRepository lowercaseFirstLetterRepository = (CityRepository) getRepository();
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
    
        
        
                

                
                
                    //TODO ADD BACKWARD COMPATIBILITY FOR hasManyThrough relationship..warning backward compatibility may leads to cyclic error..
                    //Define belongsTo relation method here..
                    private transient DataList<Showroom>  showrooms ;

                    public DataList<Showroom> getShowrooms() {
                        return showrooms;
                    }


                    public void setShowrooms(DataList<Showroom> showrooms) {
                        boolean hashType = false;
                        DataList<HashMap<String, Object>> hashMaps = new DataList<>();
                        for(Object o: showrooms){
                            if(o.getClass().equals(HashMap.class)){
                                hashType = true;
                                HashMap<String, Object> dataObj = (HashMap<String, Object>)o;
                                hashMaps.add(dataObj);
                            }else if(o.getClass().equals(HashMap.class)){
                                hashType = true;
                                HashMap<String, Object> dataObj = (HashMap<String, Object>)o;
                                hashMaps.add(dataObj);
                            }
                        }

                        if(hashType){
                            setShowrooms1(hashMaps);
                        }else{
                            this.showrooms = showrooms;
                        }
                    }

                    /*

                    //Adding related model automatically in case of include statement from server..
                    public void setShowrooms1(List<Map<String, Object>> showrooms) {
                        //First create a dummy Repo class object for ..
                        ShowroomRepository showroomsRepository = new ShowroomRepository();
                        List<Showroom> result = new ArrayList<>();
                        for (Map<String, Object> obj : showrooms) {
                            //Also add relation to child type for two way communication..
                            Showroom obj1 = showroomsRepository.createObject(obj);
                            result.add(obj1);
                        }
                        setShowrooms(result);
                    }

                    */

                    //Adding related model automatically in case of include statement from server..
                    public void setShowrooms1(DataList<HashMap<String, Object>> showrooms) {
                        //First create a dummy Repo class object for ..
                        ShowroomRepository showroomsRepository = new ShowroomRepository();
                        DataList<Showroom> result = new DataList<>();
                        for (HashMap<String, Object> obj : showrooms) {
                            //Also add relation to child type for two way communication..
                            Showroom obj1 = showroomsRepository.createObject(obj);
                            result.add(obj1);
                        }
                        setShowrooms(result);
                    }


                    //Adding relation method..
                    public void addRelation(DataList<Showroom> showrooms, Showroom dummyClassInstance) {
                        that.setShowrooms(showrooms);
                    }


                    //Adding relation method..
                    //This will add a new data to the list relation object..
                    public void addRelation(Showroom showrooms) {
                        try{
                            that.getShowrooms().add(showrooms);
                        }catch(Exception e){
                            DataList< Showroom> showrooms1 = new DataList();
                            //Now add this item to list..
                            showrooms1.add(showrooms);
                            //Now set data....
                            that.setShowrooms(showrooms1);
                        }
                    }

                







                    //Now add instance methods to fetch the related belongsTo Model..
                    

                     
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                        

                                    //Write the method here..
                                    public void findById__showrooms( String fk,  RestAdapter restAdapter, final ObjectCallback<Showroom> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.findById__showrooms( (String)that.getId(), fk,  new ObjectCallback<Showroom> (){
                                            

                                            
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
                                 
                            
                        

                                    //Write the method here..
                                    public void destroyById__showrooms( String fk,  RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.destroyById__showrooms( (String)that.getId(), fk,  new VoidCallback (){
                                            
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
                                    public void updateById__showrooms( String fk,  Showroom data,  RestAdapter restAdapter, final ObjectCallback<Showroom> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.updateById__showrooms( (String)that.getId(), fk, data.convertMap(),  new ObjectCallback<Showroom> (){
                                            

                                            
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
                                 
                            
                        

                                    //Write the method here..
                                    public void link__showrooms( String fk,  RestAdapter restAdapter, final ObjectCallback<Showroom> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.link__showrooms( (String)that.getId(), fk,  new ObjectCallback<Showroom> (){
                                            

                                            
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
                                 
                            
                        

                                    //Write the method here..
                                    public void unlink__showrooms( String fk,  RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.unlink__showrooms( (String)that.getId(), fk,  new VoidCallback (){
                                            
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
                                    public void exists__showrooms( String fk,  RestAdapter restAdapter, final ObjectCallback<JSONObject>  callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.exists__showrooms( (String)that.getId(), fk,  new ObjectCallback<JSONObject>(){
                                            

                                            
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
                                 
                            
                         
                            
                         
                            
                         
                            
                         
                            
                        

                                    //Write the method here..
                                    public void get__showrooms( Map<String,  ? extends Object> filter,  RestAdapter restAdapter, final DataListCallback<Showroom> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.get__showrooms( (String)that.getId(), filter,  new DataListCallback<Showroom> (){
                                            

                                            


                                            
                                                @Override
                                                
                                                    public void onSuccess(DataList<Showroom> object) {
                                                        if(object != null){
                                                            //now add relation to this recipe.
                                                            Showroom obj = new Showroom();
                                                            addRelation(object, obj);
                                                            //Disabling two way communication for cyclic error..
                                                            /*for (Showroom obj : object) {
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
                                    public void create__showrooms( Showroom data,  RestAdapter restAdapter, final ObjectCallback<Showroom> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.create__showrooms( (String)that.getId(), data.convertMap(),  new ObjectCallback<Showroom> (){
                                            

                                            
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
                                 
                            
                        

                                    //Write the method here..
                                    public void delete__showrooms( RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        



                                        cityRepo.delete__showrooms( (String)that.getId(),  new VoidCallback (){
                                            
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
                                    public void count__showrooms( Map<String,  ? extends Object> where,  RestAdapter restAdapter, final ObjectCallback<JSONObject>  callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.count__showrooms( (String)that.getId(), where,  new ObjectCallback<JSONObject>(){
                                            

                                            
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
                                 
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                 
                
                    //Define hasAndBelongsToMany..

                 
             
          
    
        
        
                

                
                
                    //TODO ADD BACKWARD COMPATIBILITY FOR hasManyThrough relationship..warning backward compatibility may leads to cyclic error..
                    //Define belongsTo relation method here..
                    private transient DataList<Workshop>  workshops ;

                    public DataList<Workshop> getWorkshops() {
                        return workshops;
                    }


                    public void setWorkshops(DataList<Workshop> workshops) {
                        boolean hashType = false;
                        DataList<HashMap<String, Object>> hashMaps = new DataList<>();
                        for(Object o: workshops){
                            if(o.getClass().equals(HashMap.class)){
                                hashType = true;
                                HashMap<String, Object> dataObj = (HashMap<String, Object>)o;
                                hashMaps.add(dataObj);
                            }else if(o.getClass().equals(HashMap.class)){
                                hashType = true;
                                HashMap<String, Object> dataObj = (HashMap<String, Object>)o;
                                hashMaps.add(dataObj);
                            }
                        }

                        if(hashType){
                            setWorkshops1(hashMaps);
                        }else{
                            this.workshops = workshops;
                        }
                    }

                    /*

                    //Adding related model automatically in case of include statement from server..
                    public void setWorkshops1(List<Map<String, Object>> workshops) {
                        //First create a dummy Repo class object for ..
                        WorkshopRepository workshopsRepository = new WorkshopRepository();
                        List<Workshop> result = new ArrayList<>();
                        for (Map<String, Object> obj : workshops) {
                            //Also add relation to child type for two way communication..
                            Workshop obj1 = workshopsRepository.createObject(obj);
                            result.add(obj1);
                        }
                        setWorkshops(result);
                    }

                    */

                    //Adding related model automatically in case of include statement from server..
                    public void setWorkshops1(DataList<HashMap<String, Object>> workshops) {
                        //First create a dummy Repo class object for ..
                        WorkshopRepository workshopsRepository = new WorkshopRepository();
                        DataList<Workshop> result = new DataList<>();
                        for (HashMap<String, Object> obj : workshops) {
                            //Also add relation to child type for two way communication..
                            Workshop obj1 = workshopsRepository.createObject(obj);
                            result.add(obj1);
                        }
                        setWorkshops(result);
                    }


                    //Adding relation method..
                    public void addRelation(DataList<Workshop> workshops, Workshop dummyClassInstance) {
                        that.setWorkshops(workshops);
                    }


                    //Adding relation method..
                    //This will add a new data to the list relation object..
                    public void addRelation(Workshop workshops) {
                        try{
                            that.getWorkshops().add(workshops);
                        }catch(Exception e){
                            DataList< Workshop> workshops1 = new DataList();
                            //Now add this item to list..
                            workshops1.add(workshops);
                            //Now set data....
                            that.setWorkshops(workshops1);
                        }
                    }

                







                    //Now add instance methods to fetch the related belongsTo Model..
                    

                    

                                    //Write the method here..
                                    public void findById__workshops( String fk,  RestAdapter restAdapter, final ObjectCallback<Workshop> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.findById__workshops( (String)that.getId(), fk,  new ObjectCallback<Workshop> (){
                                            

                                            
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
                                 
                            
                        

                                    //Write the method here..
                                    public void destroyById__workshops( String fk,  RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.destroyById__workshops( (String)that.getId(), fk,  new VoidCallback (){
                                            
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
                                    public void updateById__workshops( String fk,  Workshop data,  RestAdapter restAdapter, final ObjectCallback<Workshop> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.updateById__workshops( (String)that.getId(), fk, data.convertMap(),  new ObjectCallback<Workshop> (){
                                            

                                            
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
                                 
                            
                        

                                    //Write the method here..
                                    public void link__workshops( String fk,  RestAdapter restAdapter, final ObjectCallback<Workshop> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.link__workshops( (String)that.getId(), fk,  new ObjectCallback<Workshop> (){
                                            

                                            
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
                                 
                            
                        

                                    //Write the method here..
                                    public void unlink__workshops( String fk,  RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.unlink__workshops( (String)that.getId(), fk,  new VoidCallback (){
                                            
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
                                    public void exists__workshops( String fk,  RestAdapter restAdapter, final ObjectCallback<JSONObject>  callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.exists__workshops( (String)that.getId(), fk,  new ObjectCallback<JSONObject>(){
                                            

                                            
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
                                 
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                        

                                    //Write the method here..
                                    public void get__workshops( Map<String,  ? extends Object> filter,  RestAdapter restAdapter, final DataListCallback<Workshop> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.get__workshops( (String)that.getId(), filter,  new DataListCallback<Workshop> (){
                                            

                                            


                                            
                                                @Override
                                                
                                                    public void onSuccess(DataList<Workshop> object) {
                                                        if(object != null){
                                                            //now add relation to this recipe.
                                                            Workshop obj = new Workshop();
                                                            addRelation(object, obj);
                                                            //Disabling two way communication for cyclic error..
                                                            /*for (Workshop obj : object) {
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
                                    public void create__workshops( Workshop data,  RestAdapter restAdapter, final ObjectCallback<Workshop> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.create__workshops( (String)that.getId(), data.convertMap(),  new ObjectCallback<Workshop> (){
                                            

                                            
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
                                 
                            
                        

                                    //Write the method here..
                                    public void delete__workshops( RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        



                                        cityRepo.delete__workshops( (String)that.getId(),  new VoidCallback (){
                                            
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
                                    public void count__workshops( Map<String,  ? extends Object> where,  RestAdapter restAdapter, final ObjectCallback<JSONObject>  callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CityRepository  cityRepo = restAdapter.createRepository(CityRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        cityRepo.count__workshops( (String)that.getId(), where,  new ObjectCallback<JSONObject>(){
                                            

                                            
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
                                 
                            
                         
                            
                         
                            
                         
                            
                         
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                 
                
                    //Define hasAndBelongsToMany..

                 
             
          
      

}
