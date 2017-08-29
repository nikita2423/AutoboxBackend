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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.SnaphyAclRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.SnaphyAclPropRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.SnaphyAclRelationRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class SnaphyAcl extends Model {


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

    private SnaphyAcl that ;

    public SnaphyAcl (){
        that = this;
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

            
            
        
    
        
            

            
                private String model;
                /* Adding Getter and Setter methods */
                public String getModel(){
                    return model;
                }

                /* Adding Getter and Setter methods */
                public void setModel(String model){
                    this.model = model;
                    //Update hashMap value..
                    hashMap.put("model", model);
                }

            
            
        
    
        
            

            
                private String read;
                /* Adding Getter and Setter methods */
                public String getRead(){
                    return read;
                }

                /* Adding Getter and Setter methods */
                public void setRead(String read){
                    this.read = read;
                    //Update hashMap value..
                    hashMap.put("read", read);
                }

            
            
        
    
        
            

            
                private String create;
                /* Adding Getter and Setter methods */
                public String getCreate(){
                    return create;
                }

                /* Adding Getter and Setter methods */
                public void setCreate(String create){
                    this.create = create;
                    //Update hashMap value..
                    hashMap.put("create", create);
                }

            
            
        
    
        
            

            
                private String edit;
                /* Adding Getter and Setter methods */
                public String getEdit(){
                    return edit;
                }

                /* Adding Getter and Setter methods */
                public void setEdit(String edit){
                    this.edit = edit;
                    //Update hashMap value..
                    hashMap.put("edit", edit);
                }

            
            
        
    
        
            

            
                private String delete;
                /* Adding Getter and Setter methods */
                public String getDelete(){
                    return delete;
                }

                /* Adding Getter and Setter methods */
                public void setDelete(String delete){
                    this.delete = delete;
                    //Update hashMap value..
                    hashMap.put("delete", delete);
                }

            
            
        
    
        
            

            
                private String role;
                /* Adding Getter and Setter methods */
                public String getRole(){
                    return role;
                }

                /* Adding Getter and Setter methods */
                public void setRole(String role){
                    this.role = role;
                    //Update hashMap value..
                    hashMap.put("role", role);
                }

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      SnaphyAclRepository lowercaseFirstLetterRepository = (SnaphyAclRepository) getRepository();
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
      SnaphyAclRepository lowercaseFirstLetterRepository = (SnaphyAclRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      SnaphyAclRepository lowercaseFirstLetterRepository = (SnaphyAclRepository) getRepository();
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
                    private transient DataList<SnaphyAclProp>  snaphyAclProps ;

                    public DataList< SnaphyAclProp > getSnaphyAclProps() {
                        //Check for pure case of hasMany
                                                    //TODO: Modify foreign key name..
                          try{
                            SnaphyAclPropRepository snaphyAclPropRepository = (SnaphyAclPropRepository) getRepository();

                            if(that.getId() != null && snaphyAclPropRepository.getDb() != null){

                                 //Fetch locally from db
                                 //snaphyAclProps = getSnaphyAclProps__db(restAdapter);
                                 // Getting single cont
                                 snaphyAclProps = snaphyAclPropRepository.getDb().getAll__db("snaphyAclId", that.getId().toString());

                                 //lowercaseFirstLetter(modelName)
                            }
                          }catch(Exception e){
                                //Ignore
                          }
                                                return snaphyAclProps;
                    }

                    public void setSnaphyAclProps(DataList<SnaphyAclProp> snaphyAclProps) {
                        boolean hashType = false;
                        DataList<HashMap<String, Object>> hashMaps = new DataList<>();
                        for(Object o: snaphyAclProps){
                            if(o.getClass().equals(HashMap.class)){
                                hashType = true;
                                HashMap<String, Object> dataObj = (HashMap<String, Object>)o;
                                hashMaps.add(dataObj);
                            }
                        }

                        if(hashType){
                            setSnaphyAclProps1(hashMaps);
                        }else{
                            this.snaphyAclProps = snaphyAclProps;
                            //TODO: Warning move this to new thread
                            for(SnaphyAclProp data: snaphyAclProps){
                              try{
                                data.save__db();
                              } catch (NoSuchMethodError e) {
                                // ignore
                              }
                            }
                        }
                    }

                /*    //Adding related model automatically in case of include statement from server.. Adding 1 for removing same name error..
                    public void setSnaphyAclProps1(List<Map<String, Object>> snaphyAclProps) {
                        //First create a dummy Repo class object for ..
                        SnaphyAclPropRepository snaphyAclPropsRepository = new SnaphyAclPropRepository();
                        List<SnaphyAclProp> result = new ArrayList<>();
                        for (Map<String, Object> obj : snaphyAclProps) {
                            //Also add relation to child type for two way communication..
                            SnaphyAclProp obj1 = snaphyAclPropsRepository.createObject(obj);
                            result.add(obj1);

                        }
                        setSnaphyAclProps(result);

                    }

                */

                    //Adding related model automatically in case of include statement from server.. Adding 1 for removing same name error..
                    public void setSnaphyAclProps1(DataList<HashMap<String, Object>> snaphyAclProps) {
                        //First create a dummy Repo class object for ..
                        SnaphyAclPropRepository snaphyAclPropsRepository = new SnaphyAclPropRepository();
                        DataList<SnaphyAclProp> result = new DataList<>();
                        for (HashMap<String, Object> obj : snaphyAclProps) {
                            //Also add relation to child type for two way communication..
                            SnaphyAclProp obj1 = snaphyAclPropsRepository.createObject(obj);
                            result.add(obj1);

                        }
                        setSnaphyAclProps(result);

                    }


                    //Adding relation method..
                    //Add a dummy class Name object to seperate data..
                    public void addRelation(DataList<SnaphyAclProp> snaphyAclProps, SnaphyAclProp dummyClassInstance) {
                        that.setSnaphyAclProps(snaphyAclProps);

                    }

                    //Adding relation method..
                    //This will add a new data to the list relation object..
                    public void addRelation(SnaphyAclProp snaphyAclProps) {
                        try{
                            try{

                                  //Save to database..
                                  snaphyAclProps.save__db();
                            }catch (NoSuchMethodError e) {
                              // ignore
                            }
                            that.getSnaphyAclProps().add(snaphyAclProps);
                        }catch(Exception e){
                            DataList< SnaphyAclProp> snaphyAclProps1 = new DataList();
                            //Now add this item to list..
                            snaphyAclProps1.add(snaphyAclProps);
                            //Now set data....
                            that.setSnaphyAclProps(snaphyAclProps1);
                        }
                    }




                    
                        //Implement logic for pure hasMany methods here....

                    
                
                







                    //Now add instance methods to fetch the related belongsTo Model..
                    

                    

                                    //Write the method here..
                                    public void findById__snaphyAclProps( String fk,  RestAdapter restAdapter, final ObjectCallback<SnaphyAclProp> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclRepo.findById__snaphyAclProps( (String)that.getId(), fk,  new ObjectCallback<SnaphyAclProp> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(SnaphyAclProp object) {
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
                                    public void destroyById__snaphyAclProps( String fk,  RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclRepo.destroyById__snaphyAclProps( (String)that.getId(), fk,  new VoidCallback (){
                                            
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
                                    public void updateById__snaphyAclProps( String fk,  SnaphyAclProp data,  RestAdapter restAdapter, final ObjectCallback<SnaphyAclProp> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclRepo.updateById__snaphyAclProps( (String)that.getId(), fk, data.convertMap(),  new ObjectCallback<SnaphyAclProp> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(SnaphyAclProp object) {
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
                                    public void get__snaphyAclProps( Map<String,  ? extends Object> filter,  RestAdapter restAdapter, final DataListCallback<SnaphyAclProp> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclRepo.get__snaphyAclProps( (String)that.getId(), filter,  new DataListCallback<SnaphyAclProp> (){
                                            

                                            


                                            
                                                @Override
                                                
                                                    public void onSuccess(DataList<SnaphyAclProp> object) {
                                                        if(object != null){
                                                            //now add relation to this recipe.
                                                            SnaphyAclProp obj = new SnaphyAclProp();
                                                            addRelation(object, obj);
                                                            //Disabling two way communication for cyclic error..
                                                            /*for (SnaphyAclProp obj : object) {
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
                                    public void create__snaphyAclProps( SnaphyAclProp data,  RestAdapter restAdapter, final ObjectCallback<SnaphyAclProp> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclRepo.create__snaphyAclProps( (String)that.getId(), data.convertMap(),  new ObjectCallback<SnaphyAclProp> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(SnaphyAclProp object) {
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
                                    public void delete__snaphyAclProps( RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        



                                        snaphyAclRepo.delete__snaphyAclProps( (String)that.getId(),  new VoidCallback (){
                                            
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
                                    public void count__snaphyAclProps( Map<String,  ? extends Object> where,  RestAdapter restAdapter, final ObjectCallback<JSONObject>  callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclRepo.count__snaphyAclProps( (String)that.getId(), where,  new ObjectCallback<JSONObject>(){
                                            

                                            
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

                 
                 
             
          
    
        
        
                

                
                    
                    //Define hasMany relation method here..
                    private transient DataList<SnaphyAclRelation>  snaphyAclRelations ;

                    public DataList< SnaphyAclRelation > getSnaphyAclRelations() {
                        //Check for pure case of hasMany
                                                    //TODO: Modify foreign key name..
                          try{
                            SnaphyAclRelationRepository snaphyAclRelationRepository = (SnaphyAclRelationRepository) getRepository();

                            if(that.getId() != null && snaphyAclRelationRepository.getDb() != null){

                                 //Fetch locally from db
                                 //snaphyAclRelations = getSnaphyAclRelations__db(restAdapter);
                                 // Getting single cont
                                 snaphyAclRelations = snaphyAclRelationRepository.getDb().getAll__db("snaphyAclId", that.getId().toString());

                                 //lowercaseFirstLetter(modelName)
                            }
                          }catch(Exception e){
                                //Ignore
                          }
                                                return snaphyAclRelations;
                    }

                    public void setSnaphyAclRelations(DataList<SnaphyAclRelation> snaphyAclRelations) {
                        boolean hashType = false;
                        DataList<HashMap<String, Object>> hashMaps = new DataList<>();
                        for(Object o: snaphyAclRelations){
                            if(o.getClass().equals(HashMap.class)){
                                hashType = true;
                                HashMap<String, Object> dataObj = (HashMap<String, Object>)o;
                                hashMaps.add(dataObj);
                            }
                        }

                        if(hashType){
                            setSnaphyAclRelations1(hashMaps);
                        }else{
                            this.snaphyAclRelations = snaphyAclRelations;
                            //TODO: Warning move this to new thread
                            for(SnaphyAclRelation data: snaphyAclRelations){
                              try{
                                data.save__db();
                              } catch (NoSuchMethodError e) {
                                // ignore
                              }
                            }
                        }
                    }

                /*    //Adding related model automatically in case of include statement from server.. Adding 1 for removing same name error..
                    public void setSnaphyAclRelations1(List<Map<String, Object>> snaphyAclRelations) {
                        //First create a dummy Repo class object for ..
                        SnaphyAclRelationRepository snaphyAclRelationsRepository = new SnaphyAclRelationRepository();
                        List<SnaphyAclRelation> result = new ArrayList<>();
                        for (Map<String, Object> obj : snaphyAclRelations) {
                            //Also add relation to child type for two way communication..
                            SnaphyAclRelation obj1 = snaphyAclRelationsRepository.createObject(obj);
                            result.add(obj1);

                        }
                        setSnaphyAclRelations(result);

                    }

                */

                    //Adding related model automatically in case of include statement from server.. Adding 1 for removing same name error..
                    public void setSnaphyAclRelations1(DataList<HashMap<String, Object>> snaphyAclRelations) {
                        //First create a dummy Repo class object for ..
                        SnaphyAclRelationRepository snaphyAclRelationsRepository = new SnaphyAclRelationRepository();
                        DataList<SnaphyAclRelation> result = new DataList<>();
                        for (HashMap<String, Object> obj : snaphyAclRelations) {
                            //Also add relation to child type for two way communication..
                            SnaphyAclRelation obj1 = snaphyAclRelationsRepository.createObject(obj);
                            result.add(obj1);

                        }
                        setSnaphyAclRelations(result);

                    }


                    //Adding relation method..
                    //Add a dummy class Name object to seperate data..
                    public void addRelation(DataList<SnaphyAclRelation> snaphyAclRelations, SnaphyAclRelation dummyClassInstance) {
                        that.setSnaphyAclRelations(snaphyAclRelations);

                    }

                    //Adding relation method..
                    //This will add a new data to the list relation object..
                    public void addRelation(SnaphyAclRelation snaphyAclRelations) {
                        try{
                            try{

                                  //Save to database..
                                  snaphyAclRelations.save__db();
                            }catch (NoSuchMethodError e) {
                              // ignore
                            }
                            that.getSnaphyAclRelations().add(snaphyAclRelations);
                        }catch(Exception e){
                            DataList< SnaphyAclRelation> snaphyAclRelations1 = new DataList();
                            //Now add this item to list..
                            snaphyAclRelations1.add(snaphyAclRelations);
                            //Now set data....
                            that.setSnaphyAclRelations(snaphyAclRelations1);
                        }
                    }




                    
                        //Implement logic for pure hasMany methods here....

                    
                
                







                    //Now add instance methods to fetch the related belongsTo Model..
                    

                     
                            
                         
                            
                         
                            
                        

                                    //Write the method here..
                                    public void findById__snaphyAclRelations( String fk,  RestAdapter restAdapter, final ObjectCallback<SnaphyAclRelation> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclRepo.findById__snaphyAclRelations( (String)that.getId(), fk,  new ObjectCallback<SnaphyAclRelation> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(SnaphyAclRelation object) {
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
                                    public void destroyById__snaphyAclRelations( String fk,  RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclRepo.destroyById__snaphyAclRelations( (String)that.getId(), fk,  new VoidCallback (){
                                            
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
                                    public void updateById__snaphyAclRelations( String fk,  SnaphyAclRelation data,  RestAdapter restAdapter, final ObjectCallback<SnaphyAclRelation> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclRepo.updateById__snaphyAclRelations( (String)that.getId(), fk, data.convertMap(),  new ObjectCallback<SnaphyAclRelation> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(SnaphyAclRelation object) {
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
                                    public void get__snaphyAclRelations( Map<String,  ? extends Object> filter,  RestAdapter restAdapter, final DataListCallback<SnaphyAclRelation> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclRepo.get__snaphyAclRelations( (String)that.getId(), filter,  new DataListCallback<SnaphyAclRelation> (){
                                            

                                            


                                            
                                                @Override
                                                
                                                    public void onSuccess(DataList<SnaphyAclRelation> object) {
                                                        if(object != null){
                                                            //now add relation to this recipe.
                                                            SnaphyAclRelation obj = new SnaphyAclRelation();
                                                            addRelation(object, obj);
                                                            //Disabling two way communication for cyclic error..
                                                            /*for (SnaphyAclRelation obj : object) {
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
                                    public void create__snaphyAclRelations( SnaphyAclRelation data,  RestAdapter restAdapter, final ObjectCallback<SnaphyAclRelation> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclRepo.create__snaphyAclRelations( (String)that.getId(), data.convertMap(),  new ObjectCallback<SnaphyAclRelation> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(SnaphyAclRelation object) {
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
                                    public void delete__snaphyAclRelations( RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        



                                        snaphyAclRepo.delete__snaphyAclRelations( (String)that.getId(),  new VoidCallback (){
                                            
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
                                    public void count__snaphyAclRelations( Map<String,  ? extends Object> where,  RestAdapter restAdapter, final ObjectCallback<JSONObject>  callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclRepository  snaphyAclRepo = restAdapter.createRepository(SnaphyAclRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclRepo.count__snaphyAclRelations( (String)that.getId(), where,  new ObjectCallback<JSONObject>(){
                                            

                                            
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
