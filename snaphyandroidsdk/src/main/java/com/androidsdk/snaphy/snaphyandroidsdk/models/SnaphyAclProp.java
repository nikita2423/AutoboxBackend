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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.SnaphyAclPropRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.SnaphyAclRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class SnaphyAclProp extends Model {


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

    private SnaphyAclProp that ;

    public SnaphyAclProp (){
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

            
            
        
    
        
            

            
                private String write;
                /* Adding Getter and Setter methods */
                public String getWrite(){
                    return write;
                }

                /* Adding Getter and Setter methods */
                public void setWrite(String write){
                    this.write = write;
                    //Update hashMap value..
                    hashMap.put("write", write);
                }

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      SnaphyAclPropRepository lowercaseFirstLetterRepository = (SnaphyAclPropRepository) getRepository();
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
      SnaphyAclPropRepository lowercaseFirstLetterRepository = (SnaphyAclPropRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      SnaphyAclPropRepository lowercaseFirstLetterRepository = (SnaphyAclPropRepository) getRepository();
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
                    private transient SnaphyAcl  snaphyAcl ;
                    private String snaphyAclId;

                    public String getSnaphyAclId(){
                         return snaphyAclId;
                    }

                    public void setSnaphyAclId(Object snaphyAclId){
                        if(snaphyAclId != null){
                          this.snaphyAclId = snaphyAclId.toString();
                        }
                    }

                    public SnaphyAcl getSnaphyAcl() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(snaphyAcl == null){
                                        SnaphyAclPropRepository snaphyAclPropRepository = (SnaphyAclPropRepository) getRepository();

                                        RestAdapter restAdapter = snaphyAclPropRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          snaphyAcl = getSnaphyAcl__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return snaphyAcl;
                    }

                    public void setSnaphyAcl(SnaphyAcl snaphyAcl) {
                        this.snaphyAcl = snaphyAcl;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setSnaphyAcl(Map<String, Object> snaphyAcl) {
                        //First create a dummy Repo class object for customer.
                        SnaphyAclRepository snaphyAclRepository = new SnaphyAclRepository();
                        SnaphyAcl snaphyAcl1 = snaphyAclRepository.createObject(snaphyAcl);
                        setSnaphyAcl(snaphyAcl1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setSnaphyAcl(HashMap<String, Object> snaphyAcl) {
                        //First create a dummy Repo class object for customer.
                        SnaphyAclRepository snaphyAclRepository = new SnaphyAclRepository();
                        SnaphyAcl snaphyAcl1 = snaphyAclRepository.createObject(snaphyAcl);
                        setSnaphyAcl(snaphyAcl1);
                    }

                    //Adding relation method..
                    public void addRelation(SnaphyAcl snaphyAcl) {
                        that.setSnaphyAcl(snaphyAcl);
                    }


                    //Fetch related data from local database if present a snaphyAclId identifier as property for belongsTo
                    public SnaphyAcl getSnaphyAcl__db(RestAdapter restAdapter){
                      if(snaphyAclId != null){
                        SnaphyAclRepository snaphyAclRepository = restAdapter.createRepository(SnaphyAclRepository.class);
                            try{
                            SnaphyAclPropRepository lowercaseFirstLetterRepository = (SnaphyAclPropRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(snaphyAclRepository.getDb() == null ){
                                                    snaphyAclRepository.addStorage(context);
                                                }

                                                if(context != null && snaphyAclRepository.getDb() != null){
                                                    snaphyAclRepository.addStorage(context);
                                                    SnaphyAcl snaphyAcl = (SnaphyAcl) snaphyAclRepository.getDb().get__db(snaphyAclId);
                                                    return snaphyAcl;
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
                                    public void get__snaphyAcl( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<SnaphyAcl> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final SnaphyAclPropRepository  snaphyAclPropRepo = restAdapter.createRepository(SnaphyAclPropRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        snaphyAclPropRepo.get__snaphyAcl( (String)that.getId(), refresh,  new ObjectCallback<SnaphyAcl> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(SnaphyAcl object) {
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
