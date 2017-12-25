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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.PlanTypeRepository;

//Now import repository of related models..


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class PlanType extends Model {


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

    private PlanType that ;

    public PlanType (){
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

            
            
        
    
        
            

            
                private double amount;
                /* Adding Getter and Setter methods */
                public double getAmount(){
                    return amount;
                }

                /* Adding Getter and Setter methods */
                public void setAmount(double amount){
                    this.amount = amount;
                    //Update hashMap value..
                    hashMap.put("amount", amount);
                }

            
            
        
    
        
            

            
                private double chauffeurCount;
                /* Adding Getter and Setter methods */
                public double getChauffeurCount(){
                    return chauffeurCount;
                }

                /* Adding Getter and Setter methods */
                public void setChauffeurCount(double chauffeurCount){
                    this.chauffeurCount = chauffeurCount;
                    //Update hashMap value..
                    hashMap.put("chauffeurCount", chauffeurCount);
                }

            
            
        
    
        
            

            
                private double vehicleTrackCount;
                /* Adding Getter and Setter methods */
                public double getVehicleTrackCount(){
                    return vehicleTrackCount;
                }

                /* Adding Getter and Setter methods */
                public void setVehicleTrackCount(double vehicleTrackCount){
                    this.vehicleTrackCount = vehicleTrackCount;
                    //Update hashMap value..
                    hashMap.put("vehicleTrackCount", vehicleTrackCount);
                }

            
            
        
    
        
            

            
                private double schoolBusTrackCount;
                /* Adding Getter and Setter methods */
                public double getSchoolBusTrackCount(){
                    return schoolBusTrackCount;
                }

                /* Adding Getter and Setter methods */
                public void setSchoolBusTrackCount(double schoolBusTrackCount){
                    this.schoolBusTrackCount = schoolBusTrackCount;
                    //Update hashMap value..
                    hashMap.put("schoolBusTrackCount", schoolBusTrackCount);
                }

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      PlanTypeRepository lowercaseFirstLetterRepository = (PlanTypeRepository) getRepository();
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
      PlanTypeRepository lowercaseFirstLetterRepository = (PlanTypeRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      PlanTypeRepository lowercaseFirstLetterRepository = (PlanTypeRepository) getRepository();
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
      

}
