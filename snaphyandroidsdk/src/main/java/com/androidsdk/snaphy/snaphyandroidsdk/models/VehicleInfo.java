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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.VehicleInfoRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.ColorRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.BrandRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CarModelRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.TrimRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.GearBoxRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.FuelRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class VehicleInfo extends Model {


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

    private VehicleInfo that ;

    public VehicleInfo (){
        that = this;
    }

    
        
            

            
                private String vehicleModel;
                /* Adding Getter and Setter methods */
                public String getVehicleModel(){
                    return vehicleModel;
                }

                /* Adding Getter and Setter methods */
                public void setVehicleModel(String vehicleModel){
                    this.vehicleModel = vehicleModel;
                    //Update hashMap value..
                    hashMap.put("vehicleModel", vehicleModel);
                }

            
            
        
    
        
            

            
                private String vehicleType;
                /* Adding Getter and Setter methods */
                public String getVehicleType(){
                    return vehicleType;
                }

                /* Adding Getter and Setter methods */
                public void setVehicleType(String vehicleType){
                    this.vehicleType = vehicleType;
                    //Update hashMap value..
                    hashMap.put("vehicleType", vehicleType);
                }

            
            
        
    
        
            

            
                private String fuelType;
                /* Adding Getter and Setter methods */
                public String getFuelType(){
                    return fuelType;
                }

                /* Adding Getter and Setter methods */
                public void setFuelType(String fuelType){
                    this.fuelType = fuelType;
                    //Update hashMap value..
                    hashMap.put("fuelType", fuelType);
                }

            
            
        
    
        
            

            
                private String vehicleTrim;
                /* Adding Getter and Setter methods */
                public String getVehicleTrim(){
                    return vehicleTrim;
                }

                /* Adding Getter and Setter methods */
                public void setVehicleTrim(String vehicleTrim){
                    this.vehicleTrim = vehicleTrim;
                    //Update hashMap value..
                    hashMap.put("vehicleTrim", vehicleTrim);
                }

            
            
        
    
        
            

            
                private String vehicleGearbox;
                /* Adding Getter and Setter methods */
                public String getVehicleGearbox(){
                    return vehicleGearbox;
                }

                /* Adding Getter and Setter methods */
                public void setVehicleGearbox(String vehicleGearbox){
                    this.vehicleGearbox = vehicleGearbox;
                    //Update hashMap value..
                    hashMap.put("vehicleGearbox", vehicleGearbox);
                }

            
            
        
    
        
            

            
                private String vehicleColor;
                /* Adding Getter and Setter methods */
                public String getVehicleColor(){
                    return vehicleColor;
                }

                /* Adding Getter and Setter methods */
                public void setVehicleColor(String vehicleColor){
                    this.vehicleColor = vehicleColor;
                    //Update hashMap value..
                    hashMap.put("vehicleColor", vehicleColor);
                }

            
            
        
    
        
            

            
                private String vehicleBrand;
                /* Adding Getter and Setter methods */
                public String getVehicleBrand(){
                    return vehicleBrand;
                }

                /* Adding Getter and Setter methods */
                public void setVehicleBrand(String vehicleBrand){
                    this.vehicleBrand = vehicleBrand;
                    //Update hashMap value..
                    hashMap.put("vehicleBrand", vehicleBrand);
                }

            
            
        
    
        
            

            
                private String quoteType;
                /* Adding Getter and Setter methods */
                public String getQuoteType(){
                    return quoteType;
                }

                /* Adding Getter and Setter methods */
                public void setQuoteType(String quoteType){
                    this.quoteType = quoteType;
                    //Update hashMap value..
                    hashMap.put("quoteType", quoteType);
                }

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      VehicleInfoRepository lowercaseFirstLetterRepository = (VehicleInfoRepository) getRepository();
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
      VehicleInfoRepository lowercaseFirstLetterRepository = (VehicleInfoRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      VehicleInfoRepository lowercaseFirstLetterRepository = (VehicleInfoRepository) getRepository();
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
                    private transient Color  color ;
                    private String colorId;

                    public String getColorId(){
                         return colorId;
                    }

                    public void setColorId(Object colorId){
                        if(colorId != null){
                          this.colorId = colorId.toString();
                        }
                    }

                    public Color getColor() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(color == null){
                                        VehicleInfoRepository vehicleInfoRepository = (VehicleInfoRepository) getRepository();

                                        RestAdapter restAdapter = vehicleInfoRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          color = getColor__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return color;
                    }

                    public void setColor(Color color) {
                        this.color = color;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setColor(Map<String, Object> color) {
                        //First create a dummy Repo class object for customer.
                        ColorRepository colorRepository = new ColorRepository();
                        Color color1 = colorRepository.createObject(color);
                        setColor(color1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setColor(HashMap<String, Object> color) {
                        //First create a dummy Repo class object for customer.
                        ColorRepository colorRepository = new ColorRepository();
                        Color color1 = colorRepository.createObject(color);
                        setColor(color1);
                    }

                    //Adding relation method..
                    public void addRelation(Color color) {
                        that.setColor(color);
                    }


                    //Fetch related data from local database if present a colorId identifier as property for belongsTo
                    public Color getColor__db(RestAdapter restAdapter){
                      if(colorId != null){
                        ColorRepository colorRepository = restAdapter.createRepository(ColorRepository.class);
                            try{
                            VehicleInfoRepository lowercaseFirstLetterRepository = (VehicleInfoRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(colorRepository.getDb() == null ){
                                                    colorRepository.addStorage(context);
                                                }

                                                if(context != null && colorRepository.getDb() != null){
                                                    colorRepository.addStorage(context);
                                                    Color color = (Color) colorRepository.getDb().get__db(colorId);
                                                    return color;
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
                                    public void get__color( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Color> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final VehicleInfoRepository  vehicleInfoRepo = restAdapter.createRepository(VehicleInfoRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        vehicleInfoRepo.get__color( (String)that.getId(), refresh,  new ObjectCallback<Color> (){
                                            

                                            
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
                                        VehicleInfoRepository vehicleInfoRepository = (VehicleInfoRepository) getRepository();

                                        RestAdapter restAdapter = vehicleInfoRepository.getRestAdapter();
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
                            VehicleInfoRepository lowercaseFirstLetterRepository = (VehicleInfoRepository) getRepository();
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
                                        final VehicleInfoRepository  vehicleInfoRepo = restAdapter.createRepository(VehicleInfoRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        vehicleInfoRepo.get__brand( (String)that.getId(), refresh,  new ObjectCallback<Brand> (){
                                            

                                            
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
                                        VehicleInfoRepository vehicleInfoRepository = (VehicleInfoRepository) getRepository();

                                        RestAdapter restAdapter = vehicleInfoRepository.getRestAdapter();
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
                            VehicleInfoRepository lowercaseFirstLetterRepository = (VehicleInfoRepository) getRepository();
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
                                        final VehicleInfoRepository  vehicleInfoRepo = restAdapter.createRepository(VehicleInfoRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        vehicleInfoRepo.get__carModel( (String)that.getId(), refresh,  new ObjectCallback<CarModel> (){
                                            

                                            
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
                                        VehicleInfoRepository vehicleInfoRepository = (VehicleInfoRepository) getRepository();

                                        RestAdapter restAdapter = vehicleInfoRepository.getRestAdapter();
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
                            VehicleInfoRepository lowercaseFirstLetterRepository = (VehicleInfoRepository) getRepository();
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
                                        final VehicleInfoRepository  vehicleInfoRepo = restAdapter.createRepository(VehicleInfoRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        vehicleInfoRepo.get__trim( (String)that.getId(), refresh,  new ObjectCallback<Trim> (){
                                            

                                            
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
                                        VehicleInfoRepository vehicleInfoRepository = (VehicleInfoRepository) getRepository();

                                        RestAdapter restAdapter = vehicleInfoRepository.getRestAdapter();
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
                            VehicleInfoRepository lowercaseFirstLetterRepository = (VehicleInfoRepository) getRepository();
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
                                        final VehicleInfoRepository  vehicleInfoRepo = restAdapter.createRepository(VehicleInfoRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        vehicleInfoRepo.get__gearBox( (String)that.getId(), refresh,  new ObjectCallback<GearBox> (){
                                            

                                            
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
                                        VehicleInfoRepository vehicleInfoRepository = (VehicleInfoRepository) getRepository();

                                        RestAdapter restAdapter = vehicleInfoRepository.getRestAdapter();
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
                            VehicleInfoRepository lowercaseFirstLetterRepository = (VehicleInfoRepository) getRepository();
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
                                        final VehicleInfoRepository  vehicleInfoRepo = restAdapter.createRepository(VehicleInfoRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        vehicleInfoRepo.get__fuel( (String)that.getId(), refresh,  new ObjectCallback<Fuel> (){
                                            

                                            
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
                    private transient Customer  customer ;
                    private String customerId;

                    public String getCustomerId(){
                         return customerId;
                    }

                    public void setCustomerId(Object customerId){
                        if(customerId != null){
                          this.customerId = customerId.toString();
                        }
                    }

                    public Customer getCustomer() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(customer == null){
                                        VehicleInfoRepository vehicleInfoRepository = (VehicleInfoRepository) getRepository();

                                        RestAdapter restAdapter = vehicleInfoRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          customer = getCustomer__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return customer;
                    }

                    public void setCustomer(Customer customer) {
                        this.customer = customer;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setCustomer(Map<String, Object> customer) {
                        //First create a dummy Repo class object for customer.
                        CustomerRepository customerRepository = new CustomerRepository();
                        Customer customer1 = customerRepository.createObject(customer);
                        setCustomer(customer1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setCustomer(HashMap<String, Object> customer) {
                        //First create a dummy Repo class object for customer.
                        CustomerRepository customerRepository = new CustomerRepository();
                        Customer customer1 = customerRepository.createObject(customer);
                        setCustomer(customer1);
                    }

                    //Adding relation method..
                    public void addRelation(Customer customer) {
                        that.setCustomer(customer);
                    }


                    //Fetch related data from local database if present a customerId identifier as property for belongsTo
                    public Customer getCustomer__db(RestAdapter restAdapter){
                      if(customerId != null){
                        CustomerRepository customerRepository = restAdapter.createRepository(CustomerRepository.class);
                            try{
                            VehicleInfoRepository lowercaseFirstLetterRepository = (VehicleInfoRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(customerRepository.getDb() == null ){
                                                    customerRepository.addStorage(context);
                                                }

                                                if(context != null && customerRepository.getDb() != null){
                                                    customerRepository.addStorage(context);
                                                    Customer customer = (Customer) customerRepository.getDb().get__db(customerId);
                                                    return customer;
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
                                    public void get__customer( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Customer> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final VehicleInfoRepository  vehicleInfoRepo = restAdapter.createRepository(VehicleInfoRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        vehicleInfoRepo.get__customer( (String)that.getId(), refresh,  new ObjectCallback<Customer> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Customer object) {
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
