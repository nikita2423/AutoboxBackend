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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.BindAddressRepository;

//Now import repository of related models..

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CityRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.AreaRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.DealerRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.ShowroomRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class BindAddress extends Model {


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

    private BindAddress that ;

    public BindAddress (){
        that = this;
    }

    
        
            

            
                private String type;
                /* Adding Getter and Setter methods */
                public String getType(){
                    return type;
                }

                /* Adding Getter and Setter methods */
                public void setType(String type){
                    this.type = type;
                    //Update hashMap value..
                    hashMap.put("type", type);
                }

            
            
        
    
        
            

            
                private String address;
                /* Adding Getter and Setter methods */
                public String getAddress(){
                    return address;
                }

                /* Adding Getter and Setter methods */
                public void setAddress(String address){
                    this.address = address;
                    //Update hashMap value..
                    hashMap.put("address", address);
                }

            
            
        
    
        
            

            
                private Map<String, Object> latlong = new HashMap();
                /* Adding Getter and Setter methods */
                public Map<String, Object> getLatlong(){
                    return latlong;
                }
                /* Adding Getter and Setter methods */
                public double getLatlongLatitide(){
                    if(latlong != null){
                        return (Double)latlong.get("lat");
                    }else{
                        return 0;
                    }
                }

                /* Adding Getter and Setter methods */
                public double getLatlongLongitude(){
                    if(latlong != null){
                        return (Double)latlong.get("lng");
                    }else{
                        return 0;
                    }

                }

                /* Adding Getter and Setter methods */
                public void setLatlong(Map<String, Object> latlong){
                    this.latlong.putAll(latlong);
                    //Update Map value..
                    hashMap.put("latlong", latlong);
                }

                /* Adding Getter and Setter methods */
                public void setLatlong(double lat, double lng){
                    this.latlong.put("lat", lat);
                    this.latlong.put("lng", lng);
                    //Update Map value..
                    hashMap.put("latlong", latlong);
                }

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      BindAddressRepository lowercaseFirstLetterRepository = (BindAddressRepository) getRepository();
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
      BindAddressRepository lowercaseFirstLetterRepository = (BindAddressRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      BindAddressRepository lowercaseFirstLetterRepository = (BindAddressRepository) getRepository();
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
                    private transient City  city ;
                    private String cityId;

                    public String getCityId(){
                         return cityId;
                    }

                    public void setCityId(Object cityId){
                        if(cityId != null){
                          this.cityId = cityId.toString();
                        }
                    }

                    public City getCity() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(city == null){
                                        BindAddressRepository bindAddressRepository = (BindAddressRepository) getRepository();

                                        RestAdapter restAdapter = bindAddressRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          city = getCity__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return city;
                    }

                    public void setCity(City city) {
                        this.city = city;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setCity(Map<String, Object> city) {
                        //First create a dummy Repo class object for customer.
                        CityRepository cityRepository = new CityRepository();
                        City city1 = cityRepository.createObject(city);
                        setCity(city1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setCity(HashMap<String, Object> city) {
                        //First create a dummy Repo class object for customer.
                        CityRepository cityRepository = new CityRepository();
                        City city1 = cityRepository.createObject(city);
                        setCity(city1);
                    }

                    //Adding relation method..
                    public void addRelation(City city) {
                        that.setCity(city);
                    }


                    //Fetch related data from local database if present a cityId identifier as property for belongsTo
                    public City getCity__db(RestAdapter restAdapter){
                      if(cityId != null){
                        CityRepository cityRepository = restAdapter.createRepository(CityRepository.class);
                            try{
                            BindAddressRepository lowercaseFirstLetterRepository = (BindAddressRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(cityRepository.getDb() == null ){
                                                    cityRepository.addStorage(context);
                                                }

                                                if(context != null && cityRepository.getDb() != null){
                                                    cityRepository.addStorage(context);
                                                    City city = (City) cityRepository.getDb().get__db(cityId);
                                                    return city;
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
                                    public void get__city( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<City> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final BindAddressRepository  bindAddressRepo = restAdapter.createRepository(BindAddressRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        bindAddressRepo.get__city( (String)that.getId(), refresh,  new ObjectCallback<City> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(City object) {
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
                    private transient Area  area ;
                    private String areaId;

                    public String getAreaId(){
                         return areaId;
                    }

                    public void setAreaId(Object areaId){
                        if(areaId != null){
                          this.areaId = areaId.toString();
                        }
                    }

                    public Area getArea() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(area == null){
                                        BindAddressRepository bindAddressRepository = (BindAddressRepository) getRepository();

                                        RestAdapter restAdapter = bindAddressRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          area = getArea__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return area;
                    }

                    public void setArea(Area area) {
                        this.area = area;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setArea(Map<String, Object> area) {
                        //First create a dummy Repo class object for customer.
                        AreaRepository areaRepository = new AreaRepository();
                        Area area1 = areaRepository.createObject(area);
                        setArea(area1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setArea(HashMap<String, Object> area) {
                        //First create a dummy Repo class object for customer.
                        AreaRepository areaRepository = new AreaRepository();
                        Area area1 = areaRepository.createObject(area);
                        setArea(area1);
                    }

                    //Adding relation method..
                    public void addRelation(Area area) {
                        that.setArea(area);
                    }


                    //Fetch related data from local database if present a areaId identifier as property for belongsTo
                    public Area getArea__db(RestAdapter restAdapter){
                      if(areaId != null){
                        AreaRepository areaRepository = restAdapter.createRepository(AreaRepository.class);
                            try{
                            BindAddressRepository lowercaseFirstLetterRepository = (BindAddressRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(areaRepository.getDb() == null ){
                                                    areaRepository.addStorage(context);
                                                }

                                                if(context != null && areaRepository.getDb() != null){
                                                    areaRepository.addStorage(context);
                                                    Area area = (Area) areaRepository.getDb().get__db(areaId);
                                                    return area;
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
                                    public void get__area( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Area> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final BindAddressRepository  bindAddressRepo = restAdapter.createRepository(BindAddressRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        bindAddressRepo.get__area( (String)that.getId(), refresh,  new ObjectCallback<Area> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Area object) {
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
                                        BindAddressRepository bindAddressRepository = (BindAddressRepository) getRepository();

                                        RestAdapter restAdapter = bindAddressRepository.getRestAdapter();
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
                            BindAddressRepository lowercaseFirstLetterRepository = (BindAddressRepository) getRepository();
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
                                        final BindAddressRepository  bindAddressRepo = restAdapter.createRepository(BindAddressRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        bindAddressRepo.get__dealer( (String)that.getId(), refresh,  new ObjectCallback<Dealer> (){
                                            

                                            
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
                                 
                            
                         
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                 
                 
             
          
    
        
        
                
                    //Define belongsTo relation method here..
                    private transient Showroom  showroom ;
                    private String showroomId;

                    public String getShowroomId(){
                         return showroomId;
                    }

                    public void setShowroomId(Object showroomId){
                        if(showroomId != null){
                          this.showroomId = showroomId.toString();
                        }
                    }

                    public Showroom getShowroom() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(showroom == null){
                                        BindAddressRepository bindAddressRepository = (BindAddressRepository) getRepository();

                                        RestAdapter restAdapter = bindAddressRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          showroom = getShowroom__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return showroom;
                    }

                    public void setShowroom(Showroom showroom) {
                        this.showroom = showroom;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setShowroom(Map<String, Object> showroom) {
                        //First create a dummy Repo class object for customer.
                        ShowroomRepository showroomRepository = new ShowroomRepository();
                        Showroom showroom1 = showroomRepository.createObject(showroom);
                        setShowroom(showroom1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setShowroom(HashMap<String, Object> showroom) {
                        //First create a dummy Repo class object for customer.
                        ShowroomRepository showroomRepository = new ShowroomRepository();
                        Showroom showroom1 = showroomRepository.createObject(showroom);
                        setShowroom(showroom1);
                    }

                    //Adding relation method..
                    public void addRelation(Showroom showroom) {
                        that.setShowroom(showroom);
                    }


                    //Fetch related data from local database if present a showroomId identifier as property for belongsTo
                    public Showroom getShowroom__db(RestAdapter restAdapter){
                      if(showroomId != null){
                        ShowroomRepository showroomRepository = restAdapter.createRepository(ShowroomRepository.class);
                            try{
                            BindAddressRepository lowercaseFirstLetterRepository = (BindAddressRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(showroomRepository.getDb() == null ){
                                                    showroomRepository.addStorage(context);
                                                }

                                                if(context != null && showroomRepository.getDb() != null){
                                                    showroomRepository.addStorage(context);
                                                    Showroom showroom = (Showroom) showroomRepository.getDb().get__db(showroomId);
                                                    return showroom;
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
                                    public void get__showroom( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Showroom> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final BindAddressRepository  bindAddressRepo = restAdapter.createRepository(BindAddressRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        bindAddressRepo.get__showroom( (String)that.getId(), refresh,  new ObjectCallback<Showroom> (){
                                            

                                            
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
                                 
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                 
                 
             
          
      

}