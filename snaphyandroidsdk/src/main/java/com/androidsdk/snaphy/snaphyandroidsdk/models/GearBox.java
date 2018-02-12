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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.GearBoxRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.BrandRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CarModelRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.FuelRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class GearBox extends Model {


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

    private GearBox that ;

    public GearBox (){
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

            
            
        
    
        
            

            
                private String gearboxNumber;
                /* Adding Getter and Setter methods */
                public String getGearboxNumber(){
                    return gearboxNumber;
                }

                /* Adding Getter and Setter methods */
                public void setGearboxNumber(String gearboxNumber){
                    this.gearboxNumber = gearboxNumber;
                    //Update hashMap value..
                    hashMap.put("gearboxNumber", gearboxNumber);
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
      GearBoxRepository lowercaseFirstLetterRepository = (GearBoxRepository) getRepository();
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
      GearBoxRepository lowercaseFirstLetterRepository = (GearBoxRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      GearBoxRepository lowercaseFirstLetterRepository = (GearBoxRepository) getRepository();
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
                    private transient Brand  brand ;
                    private String brandId;

                    public String getBrandId(){
                         return brandId;
                    }

                    public void setBrandId(Object brandId){
                        if(brandId != null){
                          this.brandId = brandId.toString();
                        }
                    }

                    public Brand getBrand() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(brand == null){
                                        GearBoxRepository gearBoxRepository = (GearBoxRepository) getRepository();

                                        RestAdapter restAdapter = gearBoxRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          brand = getBrand__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return brand;
                    }

                    public void setBrand(Brand brand) {
                        this.brand = brand;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setBrand(Map<String, Object> brand) {
                        //First create a dummy Repo class object for customer.
                        BrandRepository brandRepository = new BrandRepository();
                        Brand brand1 = brandRepository.createObject(brand);
                        setBrand(brand1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setBrand(HashMap<String, Object> brand) {
                        //First create a dummy Repo class object for customer.
                        BrandRepository brandRepository = new BrandRepository();
                        Brand brand1 = brandRepository.createObject(brand);
                        setBrand(brand1);
                    }

                    //Adding relation method..
                    public void addRelation(Brand brand) {
                        that.setBrand(brand);
                    }


                    //Fetch related data from local database if present a brandId identifier as property for belongsTo
                    public Brand getBrand__db(RestAdapter restAdapter){
                      if(brandId != null){
                        BrandRepository brandRepository = restAdapter.createRepository(BrandRepository.class);
                            try{
                            GearBoxRepository lowercaseFirstLetterRepository = (GearBoxRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(brandRepository.getDb() == null ){
                                                    brandRepository.addStorage(context);
                                                }

                                                if(context != null && brandRepository.getDb() != null){
                                                    brandRepository.addStorage(context);
                                                    Brand brand = (Brand) brandRepository.getDb().get__db(brandId);
                                                    return brand;
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
                                    public void get__brand( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Brand> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final GearBoxRepository  gearBoxRepo = restAdapter.createRepository(GearBoxRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        gearBoxRepo.get__brand( (String)that.getId(), refresh,  new ObjectCallback<Brand> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Brand object) {
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
                    private transient CarModel  carModel ;
                    private String carModelId;

                    public String getCarModelId(){
                         return carModelId;
                    }

                    public void setCarModelId(Object carModelId){
                        if(carModelId != null){
                          this.carModelId = carModelId.toString();
                        }
                    }

                    public CarModel getCarModel() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(carModel == null){
                                        GearBoxRepository gearBoxRepository = (GearBoxRepository) getRepository();

                                        RestAdapter restAdapter = gearBoxRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          carModel = getCarModel__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return carModel;
                    }

                    public void setCarModel(CarModel carModel) {
                        this.carModel = carModel;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setCarModel(Map<String, Object> carModel) {
                        //First create a dummy Repo class object for customer.
                        CarModelRepository carModelRepository = new CarModelRepository();
                        CarModel carModel1 = carModelRepository.createObject(carModel);
                        setCarModel(carModel1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setCarModel(HashMap<String, Object> carModel) {
                        //First create a dummy Repo class object for customer.
                        CarModelRepository carModelRepository = new CarModelRepository();
                        CarModel carModel1 = carModelRepository.createObject(carModel);
                        setCarModel(carModel1);
                    }

                    //Adding relation method..
                    public void addRelation(CarModel carModel) {
                        that.setCarModel(carModel);
                    }


                    //Fetch related data from local database if present a carModelId identifier as property for belongsTo
                    public CarModel getCarModel__db(RestAdapter restAdapter){
                      if(carModelId != null){
                        CarModelRepository carModelRepository = restAdapter.createRepository(CarModelRepository.class);
                            try{
                            GearBoxRepository lowercaseFirstLetterRepository = (GearBoxRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(carModelRepository.getDb() == null ){
                                                    carModelRepository.addStorage(context);
                                                }

                                                if(context != null && carModelRepository.getDb() != null){
                                                    carModelRepository.addStorage(context);
                                                    CarModel carModel = (CarModel) carModelRepository.getDb().get__db(carModelId);
                                                    return carModel;
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
                                    public void get__carModel( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<CarModel> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final GearBoxRepository  gearBoxRepo = restAdapter.createRepository(GearBoxRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        gearBoxRepo.get__carModel( (String)that.getId(), refresh,  new ObjectCallback<CarModel> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(CarModel object) {
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
                    private transient Fuel  fuel ;
                    private String fuelId;

                    public String getFuelId(){
                         return fuelId;
                    }

                    public void setFuelId(Object fuelId){
                        if(fuelId != null){
                          this.fuelId = fuelId.toString();
                        }
                    }

                    public Fuel getFuel() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(fuel == null){
                                        GearBoxRepository gearBoxRepository = (GearBoxRepository) getRepository();

                                        RestAdapter restAdapter = gearBoxRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          fuel = getFuel__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return fuel;
                    }

                    public void setFuel(Fuel fuel) {
                        this.fuel = fuel;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setFuel(Map<String, Object> fuel) {
                        //First create a dummy Repo class object for customer.
                        FuelRepository fuelRepository = new FuelRepository();
                        Fuel fuel1 = fuelRepository.createObject(fuel);
                        setFuel(fuel1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setFuel(HashMap<String, Object> fuel) {
                        //First create a dummy Repo class object for customer.
                        FuelRepository fuelRepository = new FuelRepository();
                        Fuel fuel1 = fuelRepository.createObject(fuel);
                        setFuel(fuel1);
                    }

                    //Adding relation method..
                    public void addRelation(Fuel fuel) {
                        that.setFuel(fuel);
                    }


                    //Fetch related data from local database if present a fuelId identifier as property for belongsTo
                    public Fuel getFuel__db(RestAdapter restAdapter){
                      if(fuelId != null){
                        FuelRepository fuelRepository = restAdapter.createRepository(FuelRepository.class);
                            try{
                            GearBoxRepository lowercaseFirstLetterRepository = (GearBoxRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(fuelRepository.getDb() == null ){
                                                    fuelRepository.addStorage(context);
                                                }

                                                if(context != null && fuelRepository.getDb() != null){
                                                    fuelRepository.addStorage(context);
                                                    Fuel fuel = (Fuel) fuelRepository.getDb().get__db(fuelId);
                                                    return fuel;
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
                                    public void get__fuel( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Fuel> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final GearBoxRepository  gearBoxRepo = restAdapter.createRepository(GearBoxRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        gearBoxRepo.get__fuel( (String)that.getId(), refresh,  new ObjectCallback<Fuel> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Fuel object) {
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
