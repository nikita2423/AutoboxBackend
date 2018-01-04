package com.androidsdk.snaphy.snaphyandroidsdk.repository;



import com.google.common.collect.ImmutableMap;
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
import com.androidsdk.snaphy.snaphyandroidsdk.list.Util;

import com.strongloop.android.remoting.adapters.Adapter;
import com.strongloop.android.remoting.adapters.RestContract;
import com.strongloop.android.remoting.adapters.RestContractItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Method;
import android.util.Log;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;


//Replaced by Custom ModelRepository method
//import com.strongloop.android.loopback.ModelRepository;



import org.json.JSONArray;
import org.json.JSONObject;


//Import its models too.
import com.androidsdk.snaphy.snaphyandroidsdk.models.AdminEmail;
import android.content.Context;
import com.androidsdk.snaphy.snaphyandroidsdk.db.AdminEmailDb;

//Now import model of related models..





public class AdminEmailRepository extends ModelRepository<AdminEmail> {


    private Context context;
    private String METADATA_DATABASE_NAME_KEY = "snaphy.database.name";
    private static String DATABASE_NAME;

    public AdminEmailRepository(){
        super("AdminEmail", null, AdminEmail.class);

    }


    public Context getContext(){
        return context;
    }


    







    public AdminEmailDb getDb() {
      return adminEmailDb;
    }

    public void setAdminEmailDb(AdminEmailDb adminEmailDb) {
      this.adminEmailDb = adminEmailDb;
    }

    private AdminEmailDb adminEmailDb;



    //Flag to check either to store data locally or not..
    private boolean STORE_LOCALLY = true;

    public boolean isSTORE_LOCALLY() {
      return STORE_LOCALLY;
    }


    public void  persistData(boolean persist){
      STORE_LOCALLY = persist;
    }



    public void reset__db(){
      if(isSTORE_LOCALLY()){
          getDb().reset__db();
      }
    }



    public void addStorage(Context context){
         try{
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            DATABASE_NAME = (String) ai.metaData.get(METADATA_DATABASE_NAME_KEY);
         }
         catch (Exception e){
            Log.e("Snaphy", e.toString());
         }
         setAdminEmailDb(new AdminEmailDb(context, DATABASE_NAME, getRestAdapter()));
         //allow data storage locally..
         persistData(true);
         this.context = context;
    }


    public RestContract createContract() {
    RestContract contract = super.createContract();
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sendMail", "POST"), "adminEmail.sendMail");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/successfulRegistrationForCustomer", "POST"), "adminEmail.successfulRegistrationForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onCompletePurchaseForCustomer", "POST"), "adminEmail.onCompletePurchaseForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onOldTradeCarAdded", "POST"), "adminEmail.onOldTradeCarAdded");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/quoteGeneratedForDealer", "POST"), "adminEmail.quoteGeneratedForDealer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/serviceBookingWorkshop", "POST"), "adminEmail.serviceBookingWorkshop");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sosMedical", "POST"), "adminEmail.sosMedical");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/orderingGpsTracker", "POST"), "adminEmail.orderingGpsTracker");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sellvehicle", "POST"), "adminEmail.sellvehicle");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/buyBattery", "POST"), "adminEmail.buyBattery");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sendMail", "POST"), "adminEmail.sendMail");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/successfulRegistrationForCustomer", "POST"), "adminEmail.successfulRegistrationForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onCompletePurchaseForCustomer", "POST"), "adminEmail.onCompletePurchaseForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onOldTradeCarAdded", "POST"), "adminEmail.onOldTradeCarAdded");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/quoteGeneratedForDealer", "POST"), "adminEmail.quoteGeneratedForDealer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/serviceBookingWorkshop", "POST"), "adminEmail.serviceBookingWorkshop");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sosMedical", "POST"), "adminEmail.sosMedical");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/orderingGpsTracker", "POST"), "adminEmail.orderingGpsTracker");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sellvehicle", "POST"), "adminEmail.sellvehicle");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/buyBattery", "POST"), "adminEmail.buyBattery");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sendMail", "POST"), "adminEmail.sendMail");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/successfulRegistrationForCustomer", "POST"), "adminEmail.successfulRegistrationForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onCompletePurchaseForCustomer", "POST"), "adminEmail.onCompletePurchaseForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onOldTradeCarAdded", "POST"), "adminEmail.onOldTradeCarAdded");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/quoteGeneratedForDealer", "POST"), "adminEmail.quoteGeneratedForDealer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/serviceBookingWorkshop", "POST"), "adminEmail.serviceBookingWorkshop");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sosMedical", "POST"), "adminEmail.sosMedical");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/orderingGpsTracker", "POST"), "adminEmail.orderingGpsTracker");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sellvehicle", "POST"), "adminEmail.sellvehicle");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/buyBattery", "POST"), "adminEmail.buyBattery");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sendMail", "POST"), "adminEmail.sendMail");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/successfulRegistrationForCustomer", "POST"), "adminEmail.successfulRegistrationForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onCompletePurchaseForCustomer", "POST"), "adminEmail.onCompletePurchaseForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onOldTradeCarAdded", "POST"), "adminEmail.onOldTradeCarAdded");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/quoteGeneratedForDealer", "POST"), "adminEmail.quoteGeneratedForDealer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/serviceBookingWorkshop", "POST"), "adminEmail.serviceBookingWorkshop");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sosMedical", "POST"), "adminEmail.sosMedical");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/orderingGpsTracker", "POST"), "adminEmail.orderingGpsTracker");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sellvehicle", "POST"), "adminEmail.sellvehicle");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/buyBattery", "POST"), "adminEmail.buyBattery");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sendMail", "POST"), "adminEmail.sendMail");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/successfulRegistrationForCustomer", "POST"), "adminEmail.successfulRegistrationForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onCompletePurchaseForCustomer", "POST"), "adminEmail.onCompletePurchaseForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onOldTradeCarAdded", "POST"), "adminEmail.onOldTradeCarAdded");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/quoteGeneratedForDealer", "POST"), "adminEmail.quoteGeneratedForDealer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/serviceBookingWorkshop", "POST"), "adminEmail.serviceBookingWorkshop");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sosMedical", "POST"), "adminEmail.sosMedical");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/orderingGpsTracker", "POST"), "adminEmail.orderingGpsTracker");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sellvehicle", "POST"), "adminEmail.sellvehicle");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/buyBattery", "POST"), "adminEmail.buyBattery");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sendMail", "POST"), "adminEmail.sendMail");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/successfulRegistrationForCustomer", "POST"), "adminEmail.successfulRegistrationForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onCompletePurchaseForCustomer", "POST"), "adminEmail.onCompletePurchaseForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onOldTradeCarAdded", "POST"), "adminEmail.onOldTradeCarAdded");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/quoteGeneratedForDealer", "POST"), "adminEmail.quoteGeneratedForDealer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/serviceBookingWorkshop", "POST"), "adminEmail.serviceBookingWorkshop");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sosMedical", "POST"), "adminEmail.sosMedical");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/orderingGpsTracker", "POST"), "adminEmail.orderingGpsTracker");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sellvehicle", "POST"), "adminEmail.sellvehicle");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/buyBattery", "POST"), "adminEmail.buyBattery");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/getSchema", "POST"), "adminEmail.getSchema");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/getAbsoluteSchema", "POST"), "adminEmail.getAbsoluteSchema");
    

    
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sendMail", "POST"), "adminEmail.sendMail");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/successfulRegistrationForCustomer", "POST"), "adminEmail.successfulRegistrationForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onCompletePurchaseForCustomer", "POST"), "adminEmail.onCompletePurchaseForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onOldTradeCarAdded", "POST"), "adminEmail.onOldTradeCarAdded");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/quoteGeneratedForDealer", "POST"), "adminEmail.quoteGeneratedForDealer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/serviceBookingWorkshop", "POST"), "adminEmail.serviceBookingWorkshop");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sosMedical", "POST"), "adminEmail.sosMedical");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/orderingGpsTracker", "POST"), "adminEmail.orderingGpsTracker");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sellvehicle", "POST"), "adminEmail.sellvehicle");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/buyBattery", "POST"), "adminEmail.buyBattery");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/getDetailSchema", "POST"), "adminEmail.getDetailSchema");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/getModelRelationSchema", "POST"), "adminEmail.getModelRelationSchema");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sendMail", "POST"), "adminEmail.sendMail");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/successfulRegistrationForCustomer", "POST"), "adminEmail.successfulRegistrationForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onCompletePurchaseForCustomer", "POST"), "adminEmail.onCompletePurchaseForCustomer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/onOldTradeCarAdded", "POST"), "adminEmail.onOldTradeCarAdded");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/quoteGeneratedForDealer", "POST"), "adminEmail.quoteGeneratedForDealer");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/serviceBookingWorkshop", "POST"), "adminEmail.serviceBookingWorkshop");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sosMedical", "POST"), "adminEmail.sosMedical");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/orderingGpsTracker", "POST"), "adminEmail.orderingGpsTracker");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/sellvehicle", "POST"), "adminEmail.sellvehicle");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/buyBattery", "POST"), "adminEmail.buyBattery");
    

    
    

    

    
    contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/getMailSchema", "POST"), "adminEmail.getMailSchema");
    

    
    
    return contract;
    }



//override getNameForRestUrlMethod
    public String  getNameForRestUrl() {
        
            //call super method instead..
            return super.getNameForRestUrl();
        
    }



    




    
        
            //Method sendMail definition
            public void sendMail(  DataList<String> to,  String subject,  String html, final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                
                        hashMapObject.put("to", to);
                
                        hashMapObject.put("subject", subject);
                
                        hashMapObject.put("html", html);
                

                


                
                    
                    invokeStaticMethod("sendMail", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method sendMail definition ends here..

            

        
    
        
            //Method successfulRegistrationForCustomer definition
            public void successfulRegistrationForCustomer(  DataList<String> to,  String subject,  Map<String,  ? extends Object> templateOptions, final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                
                        hashMapObject.put("to", to);
                
                        hashMapObject.put("subject", subject);
                
                        hashMapObject.put("templateOptions", templateOptions);
                

                


                
                    
                    invokeStaticMethod("successfulRegistrationForCustomer", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method successfulRegistrationForCustomer definition ends here..

            

        
    
        
            //Method onCompletePurchaseForCustomer definition
            public void onCompletePurchaseForCustomer(  DataList<String> to,  String subject,  Map<String,  ? extends Object> templateOptions, final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                
                        hashMapObject.put("to", to);
                
                        hashMapObject.put("subject", subject);
                
                        hashMapObject.put("templateOptions", templateOptions);
                

                


                
                    
                    invokeStaticMethod("onCompletePurchaseForCustomer", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method onCompletePurchaseForCustomer definition ends here..

            

        
    
        
            //Method onOldTradeCarAdded definition
            public void onOldTradeCarAdded(  DataList<String> to,  String subject,  Map<String,  ? extends Object> templateOptions, final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                
                        hashMapObject.put("to", to);
                
                        hashMapObject.put("subject", subject);
                
                        hashMapObject.put("templateOptions", templateOptions);
                

                


                
                    
                    invokeStaticMethod("onOldTradeCarAdded", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method onOldTradeCarAdded definition ends here..

            

        
    
        
            //Method quoteGeneratedForDealer definition
            public void quoteGeneratedForDealer(  DataList<String> to,  String subject,  Map<String,  ? extends Object> templateOptions, final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                
                        hashMapObject.put("to", to);
                
                        hashMapObject.put("subject", subject);
                
                        hashMapObject.put("templateOptions", templateOptions);
                

                


                
                    
                    invokeStaticMethod("quoteGeneratedForDealer", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method quoteGeneratedForDealer definition ends here..

            

        
    
        
            //Method serviceBookingWorkshop definition
            public void serviceBookingWorkshop(  DataList<String> to,  String subject,  Map<String,  ? extends Object> templateOptions, final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                
                        hashMapObject.put("to", to);
                
                        hashMapObject.put("subject", subject);
                
                        hashMapObject.put("templateOptions", templateOptions);
                

                


                
                    
                    invokeStaticMethod("serviceBookingWorkshop", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method serviceBookingWorkshop definition ends here..

            

        
    
        
            //Method sosMedical definition
            public void sosMedical(  DataList<String> to,  String subject,  Map<String,  ? extends Object> templateOptions, final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                
                        hashMapObject.put("to", to);
                
                        hashMapObject.put("subject", subject);
                
                        hashMapObject.put("templateOptions", templateOptions);
                

                


                
                    
                    invokeStaticMethod("sosMedical", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method sosMedical definition ends here..

            

        
    
        
            //Method orderingGpsTracker definition
            public void orderingGpsTracker(  DataList<String> to,  String subject,  Map<String,  ? extends Object> templateOptions, final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                
                        hashMapObject.put("to", to);
                
                        hashMapObject.put("subject", subject);
                
                        hashMapObject.put("templateOptions", templateOptions);
                

                


                
                    
                    invokeStaticMethod("orderingGpsTracker", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method orderingGpsTracker definition ends here..

            

        
    
        
            //Method sellvehicle definition
            public void sellvehicle(  DataList<String> to,  String subject,  Map<String,  ? extends Object> templateOptions, final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                
                        hashMapObject.put("to", to);
                
                        hashMapObject.put("subject", subject);
                
                        hashMapObject.put("templateOptions", templateOptions);
                

                


                
                    
                    invokeStaticMethod("sellvehicle", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method sellvehicle definition ends here..

            

        
    
        
            //Method buyBattery definition
            public void buyBattery(  DataList<String> to,  String subject,  Map<String,  ? extends Object> templateOptions, final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                
                        hashMapObject.put("to", to);
                
                        hashMapObject.put("subject", subject);
                
                        hashMapObject.put("templateOptions", templateOptions);
                

                


                
                    
                    invokeStaticMethod("buyBattery", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method buyBattery definition ends here..

            

        
    
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
            //Method getSchema definition
            public void getSchema( final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                

                


                
                    
                    invokeStaticMethod("getSchema", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method getSchema definition ends here..

            

        
    
        
            //Method getAbsoluteSchema definition
            public void getAbsoluteSchema( final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                

                


                
                    
                    invokeStaticMethod("getAbsoluteSchema", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method getAbsoluteSchema definition ends here..

            

        
    
        
    
        
        
        
        
        
        
        
        
        
        
        
            //Method getDetailSchema definition
            public void getDetailSchema( final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                

                


                
                    
                    invokeStaticMethod("getDetailSchema", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method getDetailSchema definition ends here..

            

        
    
        
            //Method getModelRelationSchema definition
            public void getModelRelationSchema( final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                

                


                
                    
                    invokeStaticMethod("getModelRelationSchema", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method getModelRelationSchema definition ends here..

            

        
    
        
        
        
        
        
        
        
        
        
        
        
            //Method getMailSchema definition
            public void getMailSchema( final ObjectCallback<JSONObject>  callback ){

                /**
                Call the onBefore event
                */
                callback.onBefore();


                //Definging hashMap for data conversion
                Map<String, Object> hashMapObject = new HashMap<>();
                //Now add the arguments...
                

                


                
                    
                    invokeStaticMethod("getMailSchema", hashMapObject, new Adapter.JsonObjectCallback() {
                    
                    
                        @Override
                        public void onError(Throwable t) {
                            callback.onError(t);
                            //Call the finally method..
                            callback.onFinally();
                        }

                        @Override
                        public void onSuccess(JSONObject response) {
                            
                                callback.onSuccess(response);
                            
                            //Call the finally method..
                            callback.onFinally();
                        }
                    });
                

                

            }//Method getMailSchema definition ends here..

            

        
    



}
