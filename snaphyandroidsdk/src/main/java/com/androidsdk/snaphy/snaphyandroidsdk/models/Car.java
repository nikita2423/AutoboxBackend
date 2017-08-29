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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.CarRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.BrandRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CarModelRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.FuelRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.TrimRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.GearBoxRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.ColorRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Car extends Model {


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

    private Car that ;

    public Car (){
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

            
            
        
    
        
            

            
                private String carNumber;
                /* Adding Getter and Setter methods */
                public String getCarNumber(){
                    return carNumber;
                }

                /* Adding Getter and Setter methods */
                public void setCarNumber(String carNumber){
                    this.carNumber = carNumber;
                    //Update hashMap value..
                    hashMap.put("carNumber", carNumber);
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
      CarRepository lowercaseFirstLetterRepository = (CarRepository) getRepository();
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
      CarRepository lowercaseFirstLetterRepository = (CarRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      CarRepository lowercaseFirstLetterRepository = (CarRepository) getRepository();
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
                                        CarRepository carRepository = (CarRepository) getRepository();

                                        RestAdapter restAdapter = carRepository.getRestAdapter();
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
                            CarRepository lowercaseFirstLetterRepository = (CarRepository) getRepository();
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
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        carRepo.get__brand( (String)that.getId(), refresh,  new ObjectCallback<Brand> (){
                                            

                                            
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
                                        CarRepository carRepository = (CarRepository) getRepository();

                                        RestAdapter restAdapter = carRepository.getRestAdapter();
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
                            CarRepository lowercaseFirstLetterRepository = (CarRepository) getRepository();
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
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        carRepo.get__carModel( (String)that.getId(), refresh,  new ObjectCallback<CarModel> (){
                                            

                                            
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
                                        CarRepository carRepository = (CarRepository) getRepository();

                                        RestAdapter restAdapter = carRepository.getRestAdapter();
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
                            CarRepository lowercaseFirstLetterRepository = (CarRepository) getRepository();
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
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        carRepo.get__fuel( (String)that.getId(), refresh,  new ObjectCallback<Fuel> (){
                                            

                                            
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
                                 
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                 
                 
             
          
    
        
        
                
                    //Define belongsTo relation method here..
                    private transient Trim  trim ;
                    private String trimId;

                    public String getTrimId(){
                         return trimId;
                    }

                    public void setTrimId(Object trimId){
                        if(trimId != null){
                          this.trimId = trimId.toString();
                        }
                    }

                    public Trim getTrim() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(trim == null){
                                        CarRepository carRepository = (CarRepository) getRepository();

                                        RestAdapter restAdapter = carRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          trim = getTrim__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return trim;
                    }

                    public void setTrim(Trim trim) {
                        this.trim = trim;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setTrim(Map<String, Object> trim) {
                        //First create a dummy Repo class object for customer.
                        TrimRepository trimRepository = new TrimRepository();
                        Trim trim1 = trimRepository.createObject(trim);
                        setTrim(trim1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setTrim(HashMap<String, Object> trim) {
                        //First create a dummy Repo class object for customer.
                        TrimRepository trimRepository = new TrimRepository();
                        Trim trim1 = trimRepository.createObject(trim);
                        setTrim(trim1);
                    }

                    //Adding relation method..
                    public void addRelation(Trim trim) {
                        that.setTrim(trim);
                    }


                    //Fetch related data from local database if present a trimId identifier as property for belongsTo
                    public Trim getTrim__db(RestAdapter restAdapter){
                      if(trimId != null){
                        TrimRepository trimRepository = restAdapter.createRepository(TrimRepository.class);
                            try{
                            CarRepository lowercaseFirstLetterRepository = (CarRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(trimRepository.getDb() == null ){
                                                    trimRepository.addStorage(context);
                                                }

                                                if(context != null && trimRepository.getDb() != null){
                                                    trimRepository.addStorage(context);
                                                    Trim trim = (Trim) trimRepository.getDb().get__db(trimId);
                                                    return trim;
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
                                    public void get__trim( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Trim> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        carRepo.get__trim( (String)that.getId(), refresh,  new ObjectCallback<Trim> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Trim object) {
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
                    private transient GearBox  gearBox ;
                    private String gearBoxId;

                    public String getGearBoxId(){
                         return gearBoxId;
                    }

                    public void setGearBoxId(Object gearBoxId){
                        if(gearBoxId != null){
                          this.gearBoxId = gearBoxId.toString();
                        }
                    }

                    public GearBox getGearBox() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(gearBox == null){
                                        CarRepository carRepository = (CarRepository) getRepository();

                                        RestAdapter restAdapter = carRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          gearBox = getGearBox__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return gearBox;
                    }

                    public void setGearBox(GearBox gearBox) {
                        this.gearBox = gearBox;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setGearBox(Map<String, Object> gearBox) {
                        //First create a dummy Repo class object for customer.
                        GearBoxRepository gearBoxRepository = new GearBoxRepository();
                        GearBox gearBox1 = gearBoxRepository.createObject(gearBox);
                        setGearBox(gearBox1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setGearBox(HashMap<String, Object> gearBox) {
                        //First create a dummy Repo class object for customer.
                        GearBoxRepository gearBoxRepository = new GearBoxRepository();
                        GearBox gearBox1 = gearBoxRepository.createObject(gearBox);
                        setGearBox(gearBox1);
                    }

                    //Adding relation method..
                    public void addRelation(GearBox gearBox) {
                        that.setGearBox(gearBox);
                    }


                    //Fetch related data from local database if present a gearBoxId identifier as property for belongsTo
                    public GearBox getGearBox__db(RestAdapter restAdapter){
                      if(gearBoxId != null){
                        GearBoxRepository gearBoxRepository = restAdapter.createRepository(GearBoxRepository.class);
                            try{
                            CarRepository lowercaseFirstLetterRepository = (CarRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(gearBoxRepository.getDb() == null ){
                                                    gearBoxRepository.addStorage(context);
                                                }

                                                if(context != null && gearBoxRepository.getDb() != null){
                                                    gearBoxRepository.addStorage(context);
                                                    GearBox gearBox = (GearBox) gearBoxRepository.getDb().get__db(gearBoxId);
                                                    return gearBox;
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
                                    public void get__gearBox( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<GearBox> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        carRepo.get__gearBox( (String)that.getId(), refresh,  new ObjectCallback<GearBox> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(GearBox object) {
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
                                 
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                 
                 
             
          
    
        
        
                

                
                
                    //TODO ADD BACKWARD COMPATIBILITY FOR hasManyThrough relationship..warning backward compatibility may leads to cyclic error..
                    //Define belongsTo relation method here..
                    private transient DataList<Color>  colors ;

                    public DataList<Color> getColors() {
                        return colors;
                    }


                    public void setColors(DataList<Color> colors) {
                        boolean hashType = false;
                        DataList<HashMap<String, Object>> hashMaps = new DataList<>();
                        for(Object o: colors){
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
                            setColors1(hashMaps);
                        }else{
                            this.colors = colors;
                        }
                    }

                    /*

                    //Adding related model automatically in case of include statement from server..
                    public void setColors1(List<Map<String, Object>> colors) {
                        //First create a dummy Repo class object for ..
                        ColorRepository colorsRepository = new ColorRepository();
                        List<Color> result = new ArrayList<>();
                        for (Map<String, Object> obj : colors) {
                            //Also add relation to child type for two way communication..
                            Color obj1 = colorsRepository.createObject(obj);
                            result.add(obj1);
                        }
                        setColors(result);
                    }

                    */

                    //Adding related model automatically in case of include statement from server..
                    public void setColors1(DataList<HashMap<String, Object>> colors) {
                        //First create a dummy Repo class object for ..
                        ColorRepository colorsRepository = new ColorRepository();
                        DataList<Color> result = new DataList<>();
                        for (HashMap<String, Object> obj : colors) {
                            //Also add relation to child type for two way communication..
                            Color obj1 = colorsRepository.createObject(obj);
                            result.add(obj1);
                        }
                        setColors(result);
                    }


                    //Adding relation method..
                    public void addRelation(DataList<Color> colors, Color dummyClassInstance) {
                        that.setColors(colors);
                    }


                    //Adding relation method..
                    //This will add a new data to the list relation object..
                    public void addRelation(Color colors) {
                        try{
                            that.getColors().add(colors);
                        }catch(Exception e){
                            DataList< Color> colors1 = new DataList();
                            //Now add this item to list..
                            colors1.add(colors);
                            //Now set data....
                            that.setColors(colors1);
                        }
                    }

                







                    //Now add instance methods to fetch the related belongsTo Model..
                    

                     
                            
                         
                            
                         
                            
                         
                            
                         
                            
                        

                                    //Write the method here..
                                    public void findById__colors( String fk,  RestAdapter restAdapter, final ObjectCallback<Color> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        carRepo.findById__colors( (String)that.getId(), fk,  new ObjectCallback<Color> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Color object) {
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
                                    public void destroyById__colors( String fk,  RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        carRepo.destroyById__colors( (String)that.getId(), fk,  new VoidCallback (){
                                            
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
                                    public void updateById__colors( String fk,  Color data,  RestAdapter restAdapter, final ObjectCallback<Color> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        



                                        carRepo.updateById__colors( (String)that.getId(), fk, data.convertMap(),  new ObjectCallback<Color> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Color object) {
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
                                    public void link__colors( String fk,  RestAdapter restAdapter, final ObjectCallback<Color> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        



                                        carRepo.link__colors( (String)that.getId(), fk,  new ObjectCallback<Color> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Color object) {
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
                                    public void unlink__colors( String fk,  RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        carRepo.unlink__colors( (String)that.getId(), fk,  new VoidCallback (){
                                            
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
                                    public void exists__colors( String fk,  RestAdapter restAdapter, final ObjectCallback<JSONObject>  callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        carRepo.exists__colors( (String)that.getId(), fk,  new ObjectCallback<JSONObject>(){
                                            

                                            
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
                                    public void get__colors( Map<String,  ? extends Object> filter,  RestAdapter restAdapter, final DataListCallback<Color> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        carRepo.get__colors( (String)that.getId(), filter,  new DataListCallback<Color> (){
                                            

                                            


                                            
                                                @Override
                                                
                                                    public void onSuccess(DataList<Color> object) {
                                                        if(object != null){
                                                            //now add relation to this recipe.
                                                            Color obj = new Color();
                                                            addRelation(object, obj);
                                                            //Disabling two way communication for cyclic error..
                                                            /*for (Color obj : object) {
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
                                    public void create__colors( Color data,  RestAdapter restAdapter, final ObjectCallback<Color> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        carRepo.create__colors( (String)that.getId(), data.convertMap(),  new ObjectCallback<Color> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Color object) {
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
                                    public void delete__colors( RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        



                                        carRepo.delete__colors( (String)that.getId(),  new VoidCallback (){
                                            
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
                                    public void count__colors( Map<String,  ? extends Object> where,  RestAdapter restAdapter, final ObjectCallback<JSONObject>  callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CarRepository  carRepo = restAdapter.createRepository(CarRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        carRepo.count__colors( (String)that.getId(), where,  new ObjectCallback<JSONObject>(){
                                            

                                            
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
