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
import com.androidsdk.snaphy.snaphyandroidsdk.repository.CustomerRepository;

//Now import repository of related models..

    
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.FacebookAccessTokenRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CityRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.CountryRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.WorkshopRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.BusRepository;
            

        
    

    
            import com.androidsdk.snaphy.snaphyandroidsdk.repository.PlanTypeRepository;
            

        
    


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Customer extends User {


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

    private Customer that ;

    public Customer (){
        that = this;
    }

    
        
            

            
                private String firstName;
                /* Adding Getter and Setter methods */
                public String getFirstName(){
                    return firstName;
                }

                /* Adding Getter and Setter methods */
                public void setFirstName(String firstName){
                    this.firstName = firstName;
                    //Update hashMap value..
                    hashMap.put("firstName", firstName);
                }

            
            
        
    
        
            

            
                private String lastName;
                /* Adding Getter and Setter methods */
                public String getLastName(){
                    return lastName;
                }

                /* Adding Getter and Setter methods */
                public void setLastName(String lastName){
                    this.lastName = lastName;
                    //Update hashMap value..
                    hashMap.put("lastName", lastName);
                }

            
            
        
    
        
            

            
                private String registerStatus;
                /* Adding Getter and Setter methods */
                public String getRegisterStatus(){
                    return registerStatus;
                }

                /* Adding Getter and Setter methods */
                public void setRegisterStatus(String registerStatus){
                    this.registerStatus = registerStatus;
                    //Update hashMap value..
                    hashMap.put("registerStatus", registerStatus);
                }

            
            
        
    
        
            

            
                private boolean isInstalled;
                /* Adding Getter and Setter methods */
                public boolean getIsInstalled(){
                    return isInstalled;
                }

                /* Adding Getter and Setter methods */
                public void setIsInstalled(boolean isInstalled){
                    this.isInstalled = isInstalled;
                    //Update hashMap value..
                    hashMap.put("isInstalled", isInstalled);
                }

            
            
        
    
        
            

            
                private boolean isContactSynced;
                /* Adding Getter and Setter methods */
                public boolean getIsContactSynced(){
                    return isContactSynced;
                }

                /* Adding Getter and Setter methods */
                public void setIsContactSynced(boolean isContactSynced){
                    this.isContactSynced = isContactSynced;
                    //Update hashMap value..
                    hashMap.put("isContactSynced", isContactSynced);
                }

            
            
        
    
        
            

            
                private double shareAppCount;
                /* Adding Getter and Setter methods */
                public double getShareAppCount(){
                    return shareAppCount;
                }

                /* Adding Getter and Setter methods */
                public void setShareAppCount(double shareAppCount){
                    this.shareAppCount = shareAppCount;
                    //Update hashMap value..
                    hashMap.put("shareAppCount", shareAppCount);
                }

            
            
        
    
        
            

            
                private double earnedPoints;
                /* Adding Getter and Setter methods */
                public double getEarnedPoints(){
                    return earnedPoints;
                }

                /* Adding Getter and Setter methods */
                public void setEarnedPoints(double earnedPoints){
                    this.earnedPoints = earnedPoints;
                    //Update hashMap value..
                    hashMap.put("earnedPoints", earnedPoints);
                }

            
            
        
    
        
            

            
                private String cityName;
                /* Adding Getter and Setter methods */
                public String getCityName(){
                    return cityName;
                }

                /* Adding Getter and Setter methods */
                public void setCityName(String cityName){
                    this.cityName = cityName;
                    //Update hashMap value..
                    hashMap.put("cityName", cityName);
                }

            
            
        
    
        
            

            
                private String countryName;
                /* Adding Getter and Setter methods */
                public String getCountryName(){
                    return countryName;
                }

                /* Adding Getter and Setter methods */
                public void setCountryName(String countryName){
                    this.countryName = countryName;
                    //Update hashMap value..
                    hashMap.put("countryName", countryName);
                }

            
            
        
    
        
            

            
                private String serviceCenter;
                /* Adding Getter and Setter methods */
                public String getServiceCenter(){
                    return serviceCenter;
                }

                /* Adding Getter and Setter methods */
                public void setServiceCenter(String serviceCenter){
                    this.serviceCenter = serviceCenter;
                    //Update hashMap value..
                    hashMap.put("serviceCenter", serviceCenter);
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

            
            
        
    
        
            

            
                private String phoneNumber;
                /* Adding Getter and Setter methods */
                public String getPhoneNumber(){
                    return phoneNumber;
                }

                /* Adding Getter and Setter methods */
                public void setPhoneNumber(String phoneNumber){
                    this.phoneNumber = phoneNumber;
                    //Update hashMap value..
                    hashMap.put("phoneNumber", phoneNumber);
                }

            
            
        
    
        
            

            
                private String email;
                /* Adding Getter and Setter methods */
                public String getEmail(){
                    return email;
                }

                /* Adding Getter and Setter methods */
                public void setEmail(String email){
                    this.email = email;
                    //Update hashMap value..
                    hashMap.put("email", email);
                }

            
            
        
    
        
            

            
                private String username;
                /* Adding Getter and Setter methods */
                public String getUsername(){
                    return username;
                }

                /* Adding Getter and Setter methods */
                public void setUsername(String username){
                    this.username = username;
                    //Update hashMap value..
                    hashMap.put("username", username);
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

            
            
        
    
        
            

            
                private String registrationId;
                /* Adding Getter and Setter methods */
                public String getRegistrationId(){
                    return registrationId;
                }

                /* Adding Getter and Setter methods */
                public void setRegistrationId(String registrationId){
                    this.registrationId = registrationId;
                    //Update hashMap value..
                    hashMap.put("registrationId", registrationId);
                }

            
            
        
    
        
            

            
                private Map<String, Object> profilePic;
                /* Adding Getter and Setter methods */
                public Map<String, Object> getProfilePic(){
                    return profilePic;
                }

                /* Adding Getter and Setter methods */
                public void setProfilePic(Map<String, Object> profilePic){
                    this.profilePic = profilePic;
                    //Update Map value..
                    hashMap.put("profilePic", profilePic);
                }

            
            
        
    
        
            

            
                private String referralCode;
                /* Adding Getter and Setter methods */
                public String getReferralCode(){
                    return referralCode;
                }

                /* Adding Getter and Setter methods */
                public void setReferralCode(String referralCode){
                    this.referralCode = referralCode;
                    //Update hashMap value..
                    hashMap.put("referralCode", referralCode);
                }

            
            
        
    
        
            

            
                private String locationUrl;
                /* Adding Getter and Setter methods */
                public String getLocationUrl(){
                    return locationUrl;
                }

                /* Adding Getter and Setter methods */
                public void setLocationUrl(String locationUrl){
                    this.locationUrl = locationUrl;
                    //Update hashMap value..
                    hashMap.put("locationUrl", locationUrl);
                }

            
            
        
    
        
            

            
                private double referralCount;
                /* Adding Getter and Setter methods */
                public double getReferralCount(){
                    return referralCount;
                }

                /* Adding Getter and Setter methods */
                public void setReferralCount(double referralCount){
                    this.referralCount = referralCount;
                    //Update hashMap value..
                    hashMap.put("referralCount", referralCount);
                }

            
            
        
    
        
            

            
                private String sosStatus;
                /* Adding Getter and Setter methods */
                public String getSosStatus(){
                    return sosStatus;
                }

                /* Adding Getter and Setter methods */
                public void setSosStatus(String sosStatus){
                    this.sosStatus = sosStatus;
                    //Update hashMap value..
                    hashMap.put("sosStatus", sosStatus);
                }

            
            
        
    
        
            

            
                private double vehicleAdded;
                /* Adding Getter and Setter methods */
                public double getVehicleAdded(){
                    return vehicleAdded;
                }

                /* Adding Getter and Setter methods */
                public void setVehicleAdded(double vehicleAdded){
                    this.vehicleAdded = vehicleAdded;
                    //Update hashMap value..
                    hashMap.put("vehicleAdded", vehicleAdded);
                }

            
            
        
    
        
            

            
                private Map<String, Object> notificationSettings;
                /* Adding Getter and Setter methods */
                public Map<String, Object> getNotificationSettings(){
                    return notificationSettings;
                }

                /* Adding Getter and Setter methods */
                public void setNotificationSettings(Map<String, Object> notificationSettings){
                    this.notificationSettings = notificationSettings;
                    //Update Map value..
                    hashMap.put("notificationSettings", notificationSettings);
                }

            
            
        
    
        
            

            
                private Map<String, Object> gpsTrackerNotification;
                /* Adding Getter and Setter methods */
                public Map<String, Object> getGpsTrackerNotification(){
                    return gpsTrackerNotification;
                }

                /* Adding Getter and Setter methods */
                public void setGpsTrackerNotification(Map<String, Object> gpsTrackerNotification){
                    this.gpsTrackerNotification = gpsTrackerNotification;
                    //Update Map value..
                    hashMap.put("gpsTrackerNotification", gpsTrackerNotification);
                }

            
            
        
    
        
            

            
                private Map<String, Object> driverLicenceProof;
                /* Adding Getter and Setter methods */
                public Map<String, Object> getDriverLicenceProof(){
                    return driverLicenceProof;
                }

                /* Adding Getter and Setter methods */
                public void setDriverLicenceProof(Map<String, Object> driverLicenceProof){
                    this.driverLicenceProof = driverLicenceProof;
                    //Update Map value..
                    hashMap.put("driverLicenceProof", driverLicenceProof);
                }

            
            
        
    
        
            

            
                private String bloodGroup;
                /* Adding Getter and Setter methods */
                public String getBloodGroup(){
                    return bloodGroup;
                }

                /* Adding Getter and Setter methods */
                public void setBloodGroup(String bloodGroup){
                    this.bloodGroup = bloodGroup;
                    //Update hashMap value..
                    hashMap.put("bloodGroup", bloodGroup);
                }

            
            
        
    
        
            

            
                private String googleRefreshToken;
                /* Adding Getter and Setter methods */
                public String getGoogleRefreshToken(){
                    return googleRefreshToken;
                }

                /* Adding Getter and Setter methods */
                public void setGoogleRefreshToken(String googleRefreshToken){
                    this.googleRefreshToken = googleRefreshToken;
                    //Update hashMap value..
                    hashMap.put("googleRefreshToken", googleRefreshToken);
                }

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    
        
            

            
            
        
    


    //------------------------------------Database Method---------------------------------------------------


    public void save(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      //Save to database..
      save__db();
      //Also save to database..
      super.save(callback);
    }

    public void destroy(final com.strongloop.android.loopback.callbacks.VoidCallback callback){
      CustomerRepository lowercaseFirstLetterRepository = (CustomerRepository) getRepository();
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
      CustomerRepository lowercaseFirstLetterRepository = (CustomerRepository) getRepository();

      if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
        if(id != null && lowercaseFirstLetterRepository.getDb() != null){
          lowercaseFirstLetterRepository.getDb().upsert__db(id, this);
        }
      }
    }


    public void delete__db(){
      CustomerRepository lowercaseFirstLetterRepository = (CustomerRepository) getRepository();
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
                    private transient DataList<FacebookAccessToken>  facebookAccessToken ;

                    public DataList< FacebookAccessToken > getFacebookAccessToken() {
                        //Check for pure case of hasMany
                                                    //TODO: Modify foreign key name..
                          try{
                            FacebookAccessTokenRepository facebookAccessTokenRepository = (FacebookAccessTokenRepository) getRepository();

                            if(that.getId() != null && facebookAccessTokenRepository.getDb() != null){

                                 //Fetch locally from db
                                 //facebookAccessToken = getFacebookAccessToken__db(restAdapter);
                                 // Getting single cont
                                 facebookAccessToken = facebookAccessTokenRepository.getDb().getAll__db("customerId", that.getId().toString());

                                 //lowercaseFirstLetter(modelName)
                            }
                          }catch(Exception e){
                                //Ignore
                          }
                                                return facebookAccessToken;
                    }

                    public void setFacebookAccessToken(DataList<FacebookAccessToken> facebookAccessToken) {
                        boolean hashType = false;
                        DataList<HashMap<String, Object>> hashMaps = new DataList<>();
                        for(Object o: facebookAccessToken){
                            if(o.getClass().equals(HashMap.class)){
                                hashType = true;
                                HashMap<String, Object> dataObj = (HashMap<String, Object>)o;
                                hashMaps.add(dataObj);
                            }
                        }

                        if(hashType){
                            setFacebookAccessToken1(hashMaps);
                        }else{
                            this.facebookAccessToken = facebookAccessToken;
                            //TODO: Warning move this to new thread
                            for(FacebookAccessToken data: facebookAccessToken){
                              try{
                                data.save__db();
                              } catch (NoSuchMethodError e) {
                                // ignore
                              }
                            }
                        }
                    }

                /*    //Adding related model automatically in case of include statement from server.. Adding 1 for removing same name error..
                    public void setFacebookAccessToken1(List<Map<String, Object>> facebookAccessToken) {
                        //First create a dummy Repo class object for ..
                        FacebookAccessTokenRepository facebookAccessTokenRepository = new FacebookAccessTokenRepository();
                        List<FacebookAccessToken> result = new ArrayList<>();
                        for (Map<String, Object> obj : facebookAccessToken) {
                            //Also add relation to child type for two way communication..
                            FacebookAccessToken obj1 = facebookAccessTokenRepository.createObject(obj);
                            result.add(obj1);

                        }
                        setFacebookAccessToken(result);

                    }

                */

                    //Adding related model automatically in case of include statement from server.. Adding 1 for removing same name error..
                    public void setFacebookAccessToken1(DataList<HashMap<String, Object>> facebookAccessToken) {
                        //First create a dummy Repo class object for ..
                        FacebookAccessTokenRepository facebookAccessTokenRepository = new FacebookAccessTokenRepository();
                        DataList<FacebookAccessToken> result = new DataList<>();
                        for (HashMap<String, Object> obj : facebookAccessToken) {
                            //Also add relation to child type for two way communication..
                            FacebookAccessToken obj1 = facebookAccessTokenRepository.createObject(obj);
                            result.add(obj1);

                        }
                        setFacebookAccessToken(result);

                    }


                    //Adding relation method..
                    //Add a dummy class Name object to seperate data..
                    public void addRelation(DataList<FacebookAccessToken> facebookAccessToken, FacebookAccessToken dummyClassInstance) {
                        that.setFacebookAccessToken(facebookAccessToken);

                    }

                    //Adding relation method..
                    //This will add a new data to the list relation object..
                    public void addRelation(FacebookAccessToken facebookAccessToken) {
                        try{
                            try{

                                  //Save to database..
                                  facebookAccessToken.save__db();
                            }catch (NoSuchMethodError e) {
                              // ignore
                            }
                            that.getFacebookAccessToken().add(facebookAccessToken);
                        }catch(Exception e){
                            DataList< FacebookAccessToken> facebookAccessToken1 = new DataList();
                            //Now add this item to list..
                            facebookAccessToken1.add(facebookAccessToken);
                            //Now set data....
                            that.setFacebookAccessToken(facebookAccessToken1);
                        }
                    }




                    
                        //Implement logic for pure hasMany methods here....

                    
                
                







                    //Now add instance methods to fetch the related belongsTo Model..
                    

                     
                            
                         
                            
                         
                            
                        

                                    //Write the method here..
                                    public void findById__facebookAccessToken( String fk,  RestAdapter restAdapter, final ObjectCallback<FacebookAccessToken> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CustomerRepository  customerRepo = restAdapter.createRepository(CustomerRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        customerRepo.findById__facebookAccessToken( (String)that.getId(), fk,  new ObjectCallback<FacebookAccessToken> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(FacebookAccessToken object) {
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
                                    public void destroyById__facebookAccessToken( String fk,  RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CustomerRepository  customerRepo = restAdapter.createRepository(CustomerRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        customerRepo.destroyById__facebookAccessToken( (String)that.getId(), fk,  new VoidCallback (){
                                            
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
                                    public void updateById__facebookAccessToken( String fk,  FacebookAccessToken data,  RestAdapter restAdapter, final ObjectCallback<FacebookAccessToken> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CustomerRepository  customerRepo = restAdapter.createRepository(CustomerRepository.class);
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        



                                        customerRepo.updateById__facebookAccessToken( (String)that.getId(), fk, data.convertMap(),  new ObjectCallback<FacebookAccessToken> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(FacebookAccessToken object) {
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
                                    public void get__facebookAccessToken( Map<String,  ? extends Object> filter,  RestAdapter restAdapter, final DataListCallback<FacebookAccessToken> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CustomerRepository  customerRepo = restAdapter.createRepository(CustomerRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        customerRepo.get__facebookAccessToken( (String)that.getId(), filter,  new DataListCallback<FacebookAccessToken> (){
                                            

                                            


                                            
                                                @Override
                                                
                                                    public void onSuccess(DataList<FacebookAccessToken> object) {
                                                        if(object != null){
                                                            //now add relation to this recipe.
                                                            FacebookAccessToken obj = new FacebookAccessToken();
                                                            addRelation(object, obj);
                                                            //Disabling two way communication for cyclic error..
                                                            /*for (FacebookAccessToken obj : object) {
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
                                    public void create__facebookAccessToken( FacebookAccessToken data,  RestAdapter restAdapter, final ObjectCallback<FacebookAccessToken> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CustomerRepository  customerRepo = restAdapter.createRepository(CustomerRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        customerRepo.create__facebookAccessToken( (String)that.getId(), data.convertMap(),  new ObjectCallback<FacebookAccessToken> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(FacebookAccessToken object) {
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
                                    public void delete__facebookAccessToken( RestAdapter restAdapter, final VoidCallback callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CustomerRepository  customerRepo = restAdapter.createRepository(CustomerRepository.class);
                                        
                                        



                                        customerRepo.delete__facebookAccessToken( (String)that.getId(),  new VoidCallback (){
                                            
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
                                    public void count__facebookAccessToken( Map<String,  ? extends Object> where,  RestAdapter restAdapter, final ObjectCallback<JSONObject>  callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CustomerRepository  customerRepo = restAdapter.createRepository(CustomerRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        customerRepo.count__facebookAccessToken( (String)that.getId(), where,  new ObjectCallback<JSONObject>(){
                                            

                                            
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
                                        CustomerRepository customerRepository = (CustomerRepository) getRepository();

                                        RestAdapter restAdapter = customerRepository.getRestAdapter();
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
                            CustomerRepository lowercaseFirstLetterRepository = (CustomerRepository) getRepository();
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
                                        final CustomerRepository  customerRepo = restAdapter.createRepository(CustomerRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        customerRepo.get__city( (String)that.getId(), refresh,  new ObjectCallback<City> (){
                                            

                                            
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
                    private transient Country  country ;
                    private String countryId;

                    public String getCountryId(){
                         return countryId;
                    }

                    public void setCountryId(Object countryId){
                        if(countryId != null){
                          this.countryId = countryId.toString();
                        }
                    }

                    public Country getCountry() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(country == null){
                                        CustomerRepository customerRepository = (CustomerRepository) getRepository();

                                        RestAdapter restAdapter = customerRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          country = getCountry__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return country;
                    }

                    public void setCountry(Country country) {
                        this.country = country;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setCountry(Map<String, Object> country) {
                        //First create a dummy Repo class object for customer.
                        CountryRepository countryRepository = new CountryRepository();
                        Country country1 = countryRepository.createObject(country);
                        setCountry(country1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setCountry(HashMap<String, Object> country) {
                        //First create a dummy Repo class object for customer.
                        CountryRepository countryRepository = new CountryRepository();
                        Country country1 = countryRepository.createObject(country);
                        setCountry(country1);
                    }

                    //Adding relation method..
                    public void addRelation(Country country) {
                        that.setCountry(country);
                    }


                    //Fetch related data from local database if present a countryId identifier as property for belongsTo
                    public Country getCountry__db(RestAdapter restAdapter){
                      if(countryId != null){
                        CountryRepository countryRepository = restAdapter.createRepository(CountryRepository.class);
                            try{
                            CustomerRepository lowercaseFirstLetterRepository = (CustomerRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(countryRepository.getDb() == null ){
                                                    countryRepository.addStorage(context);
                                                }

                                                if(context != null && countryRepository.getDb() != null){
                                                    countryRepository.addStorage(context);
                                                    Country country = (Country) countryRepository.getDb().get__db(countryId);
                                                    return country;
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
                                    public void get__country( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Country> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CustomerRepository  customerRepo = restAdapter.createRepository(CustomerRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        customerRepo.get__country( (String)that.getId(), refresh,  new ObjectCallback<Country> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Country object) {
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
                    private transient Workshop  workshop ;
                    private String workshopId;

                    public String getWorkshopId(){
                         return workshopId;
                    }

                    public void setWorkshopId(Object workshopId){
                        if(workshopId != null){
                          this.workshopId = workshopId.toString();
                        }
                    }

                    public Workshop getWorkshop() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(workshop == null){
                                        CustomerRepository customerRepository = (CustomerRepository) getRepository();

                                        RestAdapter restAdapter = customerRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          workshop = getWorkshop__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return workshop;
                    }

                    public void setWorkshop(Workshop workshop) {
                        this.workshop = workshop;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setWorkshop(Map<String, Object> workshop) {
                        //First create a dummy Repo class object for customer.
                        WorkshopRepository workshopRepository = new WorkshopRepository();
                        Workshop workshop1 = workshopRepository.createObject(workshop);
                        setWorkshop(workshop1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setWorkshop(HashMap<String, Object> workshop) {
                        //First create a dummy Repo class object for customer.
                        WorkshopRepository workshopRepository = new WorkshopRepository();
                        Workshop workshop1 = workshopRepository.createObject(workshop);
                        setWorkshop(workshop1);
                    }

                    //Adding relation method..
                    public void addRelation(Workshop workshop) {
                        that.setWorkshop(workshop);
                    }


                    //Fetch related data from local database if present a workshopId identifier as property for belongsTo
                    public Workshop getWorkshop__db(RestAdapter restAdapter){
                      if(workshopId != null){
                        WorkshopRepository workshopRepository = restAdapter.createRepository(WorkshopRepository.class);
                            try{
                            CustomerRepository lowercaseFirstLetterRepository = (CustomerRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(workshopRepository.getDb() == null ){
                                                    workshopRepository.addStorage(context);
                                                }

                                                if(context != null && workshopRepository.getDb() != null){
                                                    workshopRepository.addStorage(context);
                                                    Workshop workshop = (Workshop) workshopRepository.getDb().get__db(workshopId);
                                                    return workshop;
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
                                    public void get__workshop( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Workshop> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CustomerRepository  customerRepo = restAdapter.createRepository(CustomerRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        customerRepo.get__workshop( (String)that.getId(), refresh,  new ObjectCallback<Workshop> (){
                                            

                                            
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
                                 
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                         
                            
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    

                

                 
                 
             
          
    
        
        
                
                    //Define belongsTo relation method here..
                    private transient Bus  bus ;
                    private String busId;

                    public String getBusId(){
                         return busId;
                    }

                    public void setBusId(Object busId){
                        if(busId != null){
                          this.busId = busId.toString();
                        }
                    }

                    public Bus getBus() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(bus == null){
                                        CustomerRepository customerRepository = (CustomerRepository) getRepository();

                                        RestAdapter restAdapter = customerRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          bus = getBus__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return bus;
                    }

                    public void setBus(Bus bus) {
                        this.bus = bus;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setBus(Map<String, Object> bus) {
                        //First create a dummy Repo class object for customer.
                        BusRepository busRepository = new BusRepository();
                        Bus bus1 = busRepository.createObject(bus);
                        setBus(bus1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setBus(HashMap<String, Object> bus) {
                        //First create a dummy Repo class object for customer.
                        BusRepository busRepository = new BusRepository();
                        Bus bus1 = busRepository.createObject(bus);
                        setBus(bus1);
                    }

                    //Adding relation method..
                    public void addRelation(Bus bus) {
                        that.setBus(bus);
                    }


                    //Fetch related data from local database if present a busId identifier as property for belongsTo
                    public Bus getBus__db(RestAdapter restAdapter){
                      if(busId != null){
                        BusRepository busRepository = restAdapter.createRepository(BusRepository.class);
                            try{
                            CustomerRepository lowercaseFirstLetterRepository = (CustomerRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(busRepository.getDb() == null ){
                                                    busRepository.addStorage(context);
                                                }

                                                if(context != null && busRepository.getDb() != null){
                                                    busRepository.addStorage(context);
                                                    Bus bus = (Bus) busRepository.getDb().get__db(busId);
                                                    return bus;
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
                                    public void get__bus( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<Bus> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CustomerRepository  customerRepo = restAdapter.createRepository(CustomerRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        customerRepo.get__bus( (String)that.getId(), refresh,  new ObjectCallback<Bus> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(Bus object) {
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
                    private transient PlanType  planType ;
                    private String planTypeId;

                    public String getPlanTypeId(){
                         return planTypeId;
                    }

                    public void setPlanTypeId(Object planTypeId){
                        if(planTypeId != null){
                          this.planTypeId = planTypeId.toString();
                        }
                    }

                    public PlanType getPlanType() {
                        try{
                          //Adding database method for fetching from relation if not present..
                                      if(planType == null){
                                        CustomerRepository customerRepository = (CustomerRepository) getRepository();

                                        RestAdapter restAdapter = customerRepository.getRestAdapter();
                                        if(restAdapter != null){
                                          //Fetch locally from db
                                          planType = getPlanType__db(restAdapter);
                                        }
                                      }
                        }catch(Exception e){
                          //Ignore
                        }

                        return planType;
                    }

                    public void setPlanType(PlanType planType) {
                        this.planType = planType;
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setPlanType(Map<String, Object> planType) {
                        //First create a dummy Repo class object for customer.
                        PlanTypeRepository planTypeRepository = new PlanTypeRepository();
                        PlanType planType1 = planTypeRepository.createObject(planType);
                        setPlanType(planType1);
                    }

                    //Adding related model automatically in case of include statement from server..
                    public void setPlanType(HashMap<String, Object> planType) {
                        //First create a dummy Repo class object for customer.
                        PlanTypeRepository planTypeRepository = new PlanTypeRepository();
                        PlanType planType1 = planTypeRepository.createObject(planType);
                        setPlanType(planType1);
                    }

                    //Adding relation method..
                    public void addRelation(PlanType planType) {
                        that.setPlanType(planType);
                    }


                    //Fetch related data from local database if present a planTypeId identifier as property for belongsTo
                    public PlanType getPlanType__db(RestAdapter restAdapter){
                      if(planTypeId != null){
                        PlanTypeRepository planTypeRepository = restAdapter.createRepository(PlanTypeRepository.class);
                            try{
                            CustomerRepository lowercaseFirstLetterRepository = (CustomerRepository) getRepository();
                                          if(lowercaseFirstLetterRepository.isSTORE_LOCALLY()){
                                                Context context = lowercaseFirstLetterRepository.getContext();
                                                if(planTypeRepository.getDb() == null ){
                                                    planTypeRepository.addStorage(context);
                                                }

                                                if(context != null && planTypeRepository.getDb() != null){
                                                    planTypeRepository.addStorage(context);
                                                    PlanType planType = (PlanType) planTypeRepository.getDb().get__db(planTypeId);
                                                    return planType;
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
                                    public void get__planType( Boolean refresh,  RestAdapter restAdapter, final ObjectCallback<PlanType> callback) {
                                        //Call the onBefore callback method..
                                        callback.onBefore();

                                        //Define methods here..
                                        final CustomerRepository  customerRepo = restAdapter.createRepository(CustomerRepository.class);
                                        
                                        
                                        
                                        
                                        



                                        customerRepo.get__planType( (String)that.getId(), refresh,  new ObjectCallback<PlanType> (){
                                            

                                            
                                                @Override
                                                
                                                    public void onSuccess(PlanType object) {
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
