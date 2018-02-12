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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.DealerVehicleRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.BrandRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CarModelRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.FuelRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.GearBoxRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.TrimRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.DealerRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class DealerVehicle extends Model {


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

    private DealerVehicle that ;

    public DealerVehicle (){
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

            
            
        
    
        
            

            
                private String serialNumber;
                /* Adding Getter and Setter methods */
                public String getSerialNumber(){
                    return serialNumber;
                }

                /* Adding Getter and Setter methods */
                public void setSerialNumber(String serialNumber){
                    this.serialNumber = serialNumber;
                    //Update hashMap value..
                    hashMap.put("serialNumber", serialNumber);
                }

            
            
        
    
        
            

            
                private String registerNumber;
                /* Adding Getter and Setter methods */
                public String getRegisterNumber(){
                    return registerNumber;
                }

                /* Adding Getter and Setter methods */
                public void setRegisterNumber(String registerNumber){
                    this.registerNumber = registerNumber;
                    //Update hashMap value..
                    hashMap.put("registerNumber", registerNumber);
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

            
            
        
    
        
            

            
                private String fuelName;
                /* Adding Getter and Setter methods */
                public String getFuelName(){
                    return fuelName;
                }

                /* Adding Getter and Setter methods */
                public void setFuelName(String fuelName){
                    this.fuelName = fuelName;
                    //Update hashMap value..
                    hashMap.put("fuelName", fuelName);
                }

            
            
        
    
        
            

            
                private String gearboxName;
                /* Adding Getter and Setter methods */
                public String getGearboxName(){
                    return gearboxName;
                }

                /* Adding Getter and Setter methods */
                public void setGearboxName(String gearboxName){
                    this.gearboxName = gearboxName;
                    //Update hashMap value..
                    hashMap.put("gearboxName", gearboxName);
                }

            
            
        
    
        
            

            
                private String trimName;
                /* Adding Getter and Setter methods */
                public String getTrimName(){
                    return trimName;
                }

                /* Adding Getter and Setter methods */
                public void setTrimName(String trimName){
                    this.trimName = trimName;
                    //Update hashMap value..
                    hashMap.put("trimName", trimName);
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
      DealerVehicleRepository lowercaseFirstLetterRepository = (DealerVehicleRepository) getRepository();
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
      DealerVehicleRepository lowercaseFirstLetterRepository = (DealerVehicleRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      DealerVehicleRepository lowercaseFirstLetterRepository = (DealerVehicleRepository) getRepository();
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
                                        DealerVehicleRepository dealerVehicleRepository = (DealerVehicleRepository) getRepository();

                                        RestAdapter restAdapter = dealerVehicleRepository.getRestAdapter();
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
                            DealerVehicleRepository lowercaseFirstLetterRepository = (DealerVehicleRepository) getRepository();
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
                                        final DealerVehicleRepository  dealerVehicleRepo = restAdapter.createRepository(DealerVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        dealerVehicleRepo.get__brand( (String)that.getId(), refresh,  new ObjectCallback<Brand> (){
                                            

                                            
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
                                        DealerVehicleRepository dealerVehicleRepository = (DealerVehicleRepository) getRepository();

                                        RestAdapter restAdapter = dealerVehicleRepository.getRestAdapter();
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
                            DealerVehicleRepository lowercaseFirstLetterRepository = (DealerVehicleRepository) getRepository();
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
                                        final DealerVehicleRepository  dealerVehicleRepo = restAdapter.createRepository(DealerVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        dealerVehicleRepo.get__carModel( (String)that.getId(), refresh,  new ObjectCallback<CarModel> (){
                                            

                                            
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
                                        DealerVehicleRepository dealerVehicleRepository = (DealerVehicleRepository) getRepository();

                                        RestAdapter restAdapter = dealerVehicleRepository.getRestAdapter();
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
                            DealerVehicleRepository lowercaseFirstLetterRepository = (DealerVehicleRepository) getRepository();
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
                                        final DealerVehicleRepository  dealerVehicleRepo = restAdapter.createRepository(DealerVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        dealerVehicleRepo.get__fuel( (String)that.getId(), refresh,  new ObjectCallback<Fuel> (){
                                            

                                            
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
                                        DealerVehicleRepository dealerVehicleRepository = (DealerVehicleRepository) getRepository();

                                        RestAdapter restAdapter = dealerVehicleRepository.getRestAdapter();
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
                            DealerVehicleRepository lowercaseFirstLetterRepository = (DealerVehicleRepository) getRepository();
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
                                        final DealerVehicleRepository  dealerVehicleRepo = restAdapter.createRepository(DealerVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        dealerVehicleRepo.get__gearBox( (String)that.getId(), refresh,  new ObjectCallback<GearBox> (){
                                            

                                            
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
                                        DealerVehicleRepository dealerVehicleRepository = (DealerVehicleRepository) getRepository();

                                        RestAdapter restAdapter = dealerVehicleRepository.getRestAdapter();
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
                            DealerVehicleRepository lowercaseFirstLetterRepository = (DealerVehicleRepository) getRepository();
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
                                        final DealerVehicleRepository  dealerVehicleRepo = restAdapter.createRepository(DealerVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        dealerVehicleRepo.get__trim( (String)that.getId(), refresh,  new ObjectCallback<Trim> (){
                                            

                                            
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
                                        DealerVehicleRepository dealerVehicleRepository = (DealerVehicleRepository) getRepository();

                                        RestAdapter restAdapter = dealerVehicleRepository.getRestAdapter();
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
                            DealerVehicleRepository lowercaseFirstLetterRepository = (DealerVehicleRepository) getRepository();
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
                                        final DealerVehicleRepository  dealerVehicleRepo = restAdapter.createRepository(DealerVehicleRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        dealerVehicleRepo.get__dealer( (String)that.getId(), refresh,  new ObjectCallback<Dealer> (){
                                            

                                            
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
                                 
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                 
                 
             
          
      

}
