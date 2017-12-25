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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.RoleRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.RoleMappingRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Role extends Model {


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

    private Role that ;

    public Role (){
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

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      RoleRepository lowercaseFirstLetterRepository = (RoleRepository) getRepository();
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
      RoleRepository lowercaseFirstLetterRepository = (RoleRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      RoleRepository lowercaseFirstLetterRepository = (RoleRepository) getRepository();
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
                    private transient DataList<RoleMapping>  principals ;

                    public DataList< RoleMapping > getPrincipals() {
                        //Check for pure case of hasMany
                                                    //TODO: Modify foreign key name..
                          try{
                            RoleMappingRepository roleMappingRepository = (RoleMappingRepository) getRepository();

                            if(that.getId() != null && roleMappingRepository.getDb() != null){

                                 //Fetch locally from db
                                 //principals = getPrincipals__db(restAdapter);
                                 // Getting single cont
                                 principals = roleMappingRepository.getDb().getAll__db("roleId", that.getId().toString());

                                 //lowercaseFirstLetter(modelName)
                            }
                          }catch(Exception e){
                                //Ignore
                          }
                                                return principals;
                    }

                    public void setPrincipals(DataList<RoleMapping> principals) {
                        boolean hashType = false;
                        DataList<HashMap<String, Object>> hashMaps = new DataList<>();
                        for(Object o: principals){
                            if(o.getClass().equals(HashMap.class)){
                                hashType = true;
                                HashMap<String, Object> dataObj = (HashMap<String, Object>)o;
                                hashMaps.add(dataObj);
                            }
                        }

                        if(hashType){
                            setPrincipals1(hashMaps);
                        }else{
                            this.principals = principals;
                            //TODO: Warning move this to new thread
                            for(RoleMapping data: principals){
                              try{
                                data.save__db();
                              } catch (NoSuchMethodError e) {
                                // ignore
                              }
                            }
                        }
                    }

                /*    //Adding related model automatically in case of include statement from server.. Adding 1 for removing same name error..
                    public void setPrincipals1(List<Map<String, Object>> principals) {
                        //First create a dummy Repo class object for ..
                        RoleMappingRepository principalsRepository = new RoleMappingRepository();
                        List<RoleMapping> result = new ArrayList<>();
                        for (Map<String, Object> obj : principals) {
                            //Also add relation to child type for two way communication..
                            RoleMapping obj1 = principalsRepository.createObject(obj);
                            result.add(obj1);

                        }
                        setPrincipals(result);

                    }

                */

                    //Adding related model automatically in case of include statement from server.. Adding 1 for removing same name error..
                    public void setPrincipals1(DataList<HashMap<String, Object>> principals) {
                        //First create a dummy Repo class object for ..
                        RoleMappingRepository principalsRepository = new RoleMappingRepository();
                        DataList<RoleMapping> result = new DataList<>();
                        for (HashMap<String, Object> obj : principals) {
                            //Also add relation to child type for two way communication..
                            RoleMapping obj1 = principalsRepository.createObject(obj);
                            result.add(obj1);

                        }
                        setPrincipals(result);

                    }


                    //Adding relation method..
                    //Add a dummy class Name object to seperate data..
                    public void addRelation(DataList<RoleMapping> principals, RoleMapping dummyClassInstance) {
                        that.setPrincipals(principals);

                    }

                    //Adding relation method..
                    //This will add a new data to the list relation object..
                    public void addRelation(RoleMapping principals) {
                        try{
                            try{

                                  //Save to database..
                                  principals.save__db();
                            }catch (NoSuchMethodError e) {
                              // ignore
                            }
                            that.getPrincipals().add(principals);
                        }catch(Exception e){
                            DataList< RoleMapping> principals1 = new DataList();
                            //Now add this item to list..
                            principals1.add(principals);
                            //Now set data....
                            that.setPrincipals(principals1);
                        }
                    }




                    
                        //Implement logic for pure hasMany methods here....

                    
                
                







                    //Now add instance methods to fetch the related belongsTo Model..
                    

                    

                                    //Write the method here..
                                    public void findById__principals( String fk,  RestAdapter restAdapter, final ObjectCallback<RoleMapping> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final RoleRepository  roleRepo = restAdapter.createRepository(RoleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        roleRepo.findById__principals( (String)that.getId(), fk,  new ObjectCallback<RoleMapping> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(RoleMapping object) {
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
                                    public void destroyById__principals( String fk,  RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final RoleRepository  roleRepo = restAdapter.createRepository(RoleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        roleRepo.destroyById__principals( (String)that.getId(), fk,  new VoidCallback (){
                                            
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
                                    public void updateById__principals( String fk,  RoleMapping data,  RestAdapter restAdapter, final ObjectCallback<RoleMapping> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final RoleRepository  roleRepo = restAdapter.createRepository(RoleRepository.class);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        



                                        roleRepo.updateById__principals( (String)that.getId(), fk, data.convertMap(),  new ObjectCallback<RoleMapping> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(RoleMapping object) {
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
                                    public void get__principals( Map<String,  ? extends Object> filter,  RestAdapter restAdapter, final DataListCallback<RoleMapping> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final RoleRepository  roleRepo = restAdapter.createRepository(RoleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        roleRepo.get__principals( (String)that.getId(), filter,  new DataListCallback<RoleMapping> (){
                                            

                                            


                                            
                                                @Override
                                                
                                                    public void onSuccess(DataList<RoleMapping> object) {
                                                        if(object != null){
                                                            //now add relation to this recipe.
                                                            RoleMapping obj = new RoleMapping();
                                                            addRelation(object, obj);
                                                            //Disabling two way communication for cyclic error..
                                                            /*for (RoleMapping obj : object) {
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
                                    public void create__principals( RoleMapping data,  RestAdapter restAdapter, final ObjectCallback<RoleMapping> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final RoleRepository  roleRepo = restAdapter.createRepository(RoleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        roleRepo.create__principals( (String)that.getId(), data.convertMap(),  new ObjectCallback<RoleMapping> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(RoleMapping object) {
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
                                    public void delete__principals( RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final RoleRepository  roleRepo = restAdapter.createRepository(RoleRepository.class);
                                        
                                        



                                        roleRepo.delete__principals( (String)that.getId(),  new VoidCallback (){
                                            
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
                                    public void count__principals( Map<String,  ? extends Object> where,  RestAdapter restAdapter, final ObjectCallback<JSONObject>  callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final RoleRepository  roleRepo = restAdapter.createRepository(RoleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        roleRepo.count__principals( (String)that.getId(), where,  new ObjectCallback<JSONObject>(){
                                            

                                            
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
