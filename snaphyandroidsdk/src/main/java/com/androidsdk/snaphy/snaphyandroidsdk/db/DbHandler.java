package com.androidsdk.snaphy.snaphyandroidsdk.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by robins on 16/2/17.
 */



public class DbHandler extends SQLiteOpenHelper {
        private static DbHandler sInstance;
        // Database Version
        private static final int DATABASE_VERSION = 1;
        public static synchronized DbHandler getInstance(Context context, String DATABASE_NAME){
            // Use the application context, which will ensure that you don't accidentally leak an Activity's context.
            if (sInstance == null) {
                sInstance = new DbHandler(context.getApplicationContext(), DATABASE_NAME);
            }
            return sInstance;
        }


        // Creating Tables
        @Override
        public void onCreate(SQLiteDatabase db) {
            new Thread(new Runnable() {
              @Override
              public void run() {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_RoleMapping_TABLE_3 = "CREATE TABLE IF NOT EXISTS `RoleMapping` (  `id` TEXT PRIMARY KEY, `principalType` TEXT, `principalId` TEXT, `roleId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_RoleMapping_TABLE_3);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Role_TABLE_4 = "CREATE TABLE IF NOT EXISTS `Role` (  `id` TEXT PRIMARY KEY, `name` TEXT, `description` TEXT, `created` TEXT, `modified` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Role_TABLE_4);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_CompanyInfo_TABLE_5 = "CREATE TABLE IF NOT EXISTS `CompanyInfo` (  `type` TEXT, `html` TEXT, `edited` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_CompanyInfo_TABLE_5);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Employee_TABLE_6 = "CREATE TABLE IF NOT EXISTS `Employee` (  `username` TEXT, `firstName` TEXT, `lastName` TEXT, `added` TEXT, `updated` TEXT, `email` TEXT, `password` TEXT, `realm` TEXT, `credentials` TEXT, `challenges` TEXT, `emailVerified` TEXT, `verificationToken` TEXT, `status` TEXT, `created` TEXT, `lastUpdated` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Employee_TABLE_6);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_FacebookAccessToken_TABLE_7 = "CREATE TABLE IF NOT EXISTS `FacebookAccessToken` (  `FbUserId` TEXT, `token` TEXT, `expires` TEXT, `userId` TEXT, `type` TEXT, `customerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_FacebookAccessToken_TABLE_7);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Customer_TABLE_8 = "CREATE TABLE IF NOT EXISTS `Customer` (  `firstName` TEXT, `lastName` TEXT, `registerStatus` TEXT, `isInstalled` NUMBER, `isContactSynced` NUMBER, `shareAppCount` NUMBER, `earnedPoints` NUMBER, `cityName` TEXT, `countryName` TEXT, `serviceCenter` TEXT, `status` TEXT, `phoneNumber` TEXT, `email` TEXT, `username` TEXT, `added` TEXT, `updated` TEXT, `registrationId` TEXT, `profilePic` TEXT, `referralCode` TEXT, `locationUrl` TEXT, `referralCount` NUMBER, `sosStatus` TEXT, `vehicleAdded` NUMBER, `notificationSettings` TEXT, `gpsTrackerNotification` TEXT, `driverLicenceProof` TEXT, `bloodGroup` TEXT, `googleRefreshToken` TEXT, `realm` TEXT, `password` TEXT, `credentials` TEXT, `challenges` TEXT, `emailVerified` TEXT, `verificationToken` TEXT, `created` TEXT, `lastUpdated` TEXT, `id` TEXT PRIMARY KEY, `countryId` TEXT, `cityId` TEXT, `workshopId` TEXT, `busId` TEXT, `planTypeId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Customer_TABLE_8);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_AmazonImage_TABLE_9 = "CREATE TABLE IF NOT EXISTS `AmazonImage` (  `name` TEXT, `container` TEXT, `type` TEXT, `url` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_AmazonImage_TABLE_9);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_adminEmail_TABLE_10 = "CREATE TABLE IF NOT EXISTS `adminEmail` (  `to` TEXT, `from` TEXT, `subject` TEXT, `text` TEXT, `html` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_adminEmail_TABLE_10);


                                                                                                                                                                            
                            

                            
                            String CREATE_container_TABLE_12 = "CREATE TABLE IF NOT EXISTS `container` (  `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_container_TABLE_12);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_application_TABLE_14 = "CREATE TABLE IF NOT EXISTS `application` (  `id` TEXT PRIMARY KEY, `realm` TEXT, `name` TEXT, `description` TEXT, `icon` TEXT, `owner` TEXT, `collaborators` TEXT, `email` TEXT, `emailVerified` TEXT, `url` TEXT, `callbackUrls` TEXT, `permissions` TEXT, `clientKey` TEXT, `javaScriptKey` TEXT, `restApiKey` TEXT, `windowsKey` TEXT, `masterKey` TEXT, `pushSettings` TEXT, `authenticationEnabled` TEXT, `anonymousAllowed` TEXT, `authenticationSchemes` TEXT, `status` TEXT, `created` TEXT, `modified` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_application_TABLE_14);


                                                                                                                                                                            
                            

                            
                            String CREATE_ImportData_TABLE_16 = "CREATE TABLE IF NOT EXISTS `ImportData` (  `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_ImportData_TABLE_16);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_installation_TABLE_17 = "CREATE TABLE IF NOT EXISTS `installation` (  `status` TEXT, `appId` TEXT, `appVersion` TEXT, `badge` TEXT, `created` TEXT, `deviceToken` TEXT, `deviceType` TEXT, `modified` TEXT, `subscriptions` TEXT, `timeZone` TEXT, `userId` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_installation_TABLE_17);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_notification_TABLE_19 = "CREATE TABLE IF NOT EXISTS `notification` (  `alert` TEXT, `badge` TEXT, `category` TEXT, `collapseKey` TEXT, `contentAvailable` TEXT, `created` TEXT, `delayWhileIdle` TEXT, `deviceToken` TEXT, `deviceType` TEXT, `expirationInterval` TEXT, `expirationTime` TEXT, `modified` TEXT, `scheduledTime` TEXT, `sound` TEXT, `status` TEXT, `urlArgs` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_notification_TABLE_19);


                                                                                                                                                                            
                            

                            
                            String CREATE_push_TABLE_21 = "CREATE TABLE IF NOT EXISTS `push` (  `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_push_TABLE_21);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Country_TABLE_23 = "CREATE TABLE IF NOT EXISTS `Country` (  `name` TEXT, `status` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Country_TABLE_23);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_City_TABLE_24 = "CREATE TABLE IF NOT EXISTS `City` (  `name` TEXT, `status` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_City_TABLE_24);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Brand_TABLE_25 = "CREATE TABLE IF NOT EXISTS `Brand` (  `name` TEXT, `status` TEXT, `trending` NUMBER, `brandNumber` TEXT, `image` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Brand_TABLE_25);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_CarModel_TABLE_26 = "CREATE TABLE IF NOT EXISTS `CarModel` (  `name` TEXT, `status` TEXT, `image` TEXT, `quoteImage` TEXT, `modelNumber` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `brandId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_CarModel_TABLE_26);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Fuel_TABLE_27 = "CREATE TABLE IF NOT EXISTS `Fuel` (  `name` TEXT, `status` TEXT, `fuelNumber` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `brandId` TEXT, `carModelId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Fuel_TABLE_27);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Trim_TABLE_28 = "CREATE TABLE IF NOT EXISTS `Trim` (  `name` TEXT, `status` TEXT, `trimNumber` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `brandId` TEXT, `carModelId` TEXT, `fuelId` TEXT, `gearBoxId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Trim_TABLE_28);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_GearBox_TABLE_29 = "CREATE TABLE IF NOT EXISTS `GearBox` (  `name` TEXT, `status` TEXT, `gearboxNumber` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `brandId` TEXT, `carModelId` TEXT, `fuelId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_GearBox_TABLE_29);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Color_TABLE_30 = "CREATE TABLE IF NOT EXISTS `Color` (  `name` TEXT, `colorCode` TEXT, `status` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Color_TABLE_30);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Car_TABLE_31 = "CREATE TABLE IF NOT EXISTS `Car` (  `name` TEXT, `status` TEXT, `carNumber` TEXT, `added` TEXT, `brandName` TEXT, `carModelName` TEXT, `fuelName` TEXT, `gearBoxName` TEXT, `variantName` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `brandId` TEXT, `carModelId` TEXT, `trimId` TEXT, `fuelId` TEXT, `gearBoxId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Car_TABLE_31);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_BreakdownCategory_TABLE_32 = "CREATE TABLE IF NOT EXISTS `BreakdownCategory` (  `name` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_BreakdownCategory_TABLE_32);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Breakdown_TABLE_33 = "CREATE TABLE IF NOT EXISTS `Breakdown` (  `name` TEXT, `contactNumber` TEXT, `latlong` TEXT, `breakdownNumber` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `brandId` TEXT, `breakdownCategoryId` TEXT, `cityId` TEXT, `areaId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Breakdown_TABLE_33);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_EmergencyCategory_TABLE_34 = "CREATE TABLE IF NOT EXISTS `EmergencyCategory` (  `name` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_EmergencyCategory_TABLE_34);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Emergency_TABLE_35 = "CREATE TABLE IF NOT EXISTS `Emergency` (  `name` TEXT, `contactNumber` TEXT, `latlong` TEXT, `emergencyNumber` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `cityId` TEXT, `emergencyCategoryId` TEXT, `areaId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Emergency_TABLE_35);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_VehicleDetail_TABLE_36 = "CREATE TABLE IF NOT EXISTS `VehicleDetail` (  `registrationNumber` TEXT, `registeredName` TEXT, `showroomName` TEXT, `workshopName` TEXT, `status` TEXT, `added` TEXT, `updated` TEXT, `vehicleType` TEXT, `vehicleInsuranceProof` TEXT, `vehicleRegistrationProof` TEXT, `pucProof` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `workshopId` TEXT, `showroomId` TEXT, `vehicleInfoId` TEXT, `insuranceId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_VehicleDetail_TABLE_36);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_CustomerMessage_TABLE_37 = "CREATE TABLE IF NOT EXISTS `CustomerMessage` (  `firstName` TEXT, `lastName` TEXT, `replyStatus` TEXT, `type` TEXT, `added` TEXT, `time` TEXT, `status` TEXT, `mobileNumber` TEXT, `message` TEXT, `subject` TEXT, `userType` TEXT, `replyMessage` TEXT, `readStatus` NUMBER, `updated` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `dealerId` TEXT, `customerQuoteId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_CustomerMessage_TABLE_37);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Workshop_TABLE_38 = "CREATE TABLE IF NOT EXISTS `Workshop` (  `dealershipName` TEXT, `status` TEXT, `consultantName` TEXT, `contactNumber` TEXT, `email` TEXT, `latlong` TEXT, `address` TEXT, `landline` TEXT, `image` TEXT, `timings` TEXT, `managerImage` TEXT, `workshopNumber` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `brandId` TEXT, `dealerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Workshop_TABLE_38);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Showroom_TABLE_39 = "CREATE TABLE IF NOT EXISTS `Showroom` (  `name` TEXT, `salesConsultantName` TEXT, `contactNumber` TEXT, `email` TEXT, `latlong` TEXT, `status` TEXT, `generalManagerEmail` TEXT, `address` TEXT, `timings` TEXT, `landline` TEXT, `showroomNumber` TEXT, `image` TEXT, `managerImage` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `brandId` TEXT, `dealerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Showroom_TABLE_39);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Dealer_TABLE_40 = "CREATE TABLE IF NOT EXISTS `Dealer` (  `firstName` TEXT, `lastName` TEXT, `password` TEXT, `email` TEXT, `status` TEXT, `contact` TEXT, `noOfVehicleSold` NUMBER, `dealerNumber` TEXT, `added` TEXT, `updated` TEXT, `realm` TEXT, `username` TEXT, `credentials` TEXT, `challenges` TEXT, `emailVerified` TEXT, `verificationToken` TEXT, `created` TEXT, `lastUpdated` TEXT, `id` TEXT PRIMARY KEY, `cityId` TEXT, `brandId` TEXT, `showroomId` TEXT, `workshopId` TEXT, `areaId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Dealer_TABLE_40);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_ServiceBooking_TABLE_41 = "CREATE TABLE IF NOT EXISTS `ServiceBooking` (  `serviceDate` TEXT, `comments` TEXT, `vehiclePickup` TEXT, `bookingNumber` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `workshopId` TEXT, `customerId` TEXT, `vehicleDetailId` TEXT, `serviceTypeId` TEXT, `vehicleInfoId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_ServiceBooking_TABLE_41);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_CustomerQuote_TABLE_42 = "CREATE TABLE IF NOT EXISTS `CustomerQuote` (  `quoteType` TEXT, `ownershipType` TEXT, `isFinance` TEXT, `isInsurance` TEXT, `isOldVehicleTrade` TEXT, `soldViaAutobox` TEXT, `isSoldViaAutobox` NUMBER, `onRoadPrice` TEXT, `purchaseStatus` TEXT, `purchaseDate` TEXT, `gpsTracker` TEXT, `dashCamera` TEXT, `testDrive` TEXT, `quoteNumber` TEXT, `currentBrandId` TEXT, `miles` NUMBER, `oldTradeVariantName` TEXT, `added` TEXT, `updated` TEXT, `status` TEXT, `id` TEXT PRIMARY KEY, `brandId` TEXT, `carModelId` TEXT, `customerId` TEXT, `dealerId` TEXT, `cityId` TEXT, `trimId` TEXT, `vehicleInfoId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_CustomerQuote_TABLE_42);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_QuoteReply_TABLE_43 = "CREATE TABLE IF NOT EXISTS `QuoteReply` (  `exShowroomPrice` NUMBER, `exchangeBonus` NUMBER, `insurance` NUMBER, `specialDiscount` NUMBER, `rtoRegistration` TEXT, `loyaltyBonus` NUMBER, `miscCharges` NUMBER, `gst` NUMBER, `roadPrice` NUMBER, `distance` NUMBER, `isRead` NUMBER, `replyNumber` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `customerQuoteId` TEXT, `dealerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_QuoteReply_TABLE_43);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Feedback_TABLE_44 = "CREATE TABLE IF NOT EXISTS `Feedback` (  `subject` TEXT, `message` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `dealerId` TEXT, `showroomId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Feedback_TABLE_44);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_ServiceType_TABLE_45 = "CREATE TABLE IF NOT EXISTS `ServiceType` (  `name` TEXT, `status` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_ServiceType_TABLE_45);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Area_TABLE_46 = "CREATE TABLE IF NOT EXISTS `Area` (  `name` TEXT, `pincode` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `cityId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Area_TABLE_46);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_OldTradeCar_TABLE_47 = "CREATE TABLE IF NOT EXISTS `OldTradeCar` (  `registeredCity` TEXT, `added` TEXT, `updated` TEXT, `miles` NUMBER, `variantName` TEXT, `id` TEXT PRIMARY KEY, `cityId` TEXT, `customerId` TEXT, `carModelId` TEXT, `brandId` TEXT, `trimId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_OldTradeCar_TABLE_47);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Offer_TABLE_48 = "CREATE TABLE IF NOT EXISTS `Offer` (  `title` TEXT, `description` TEXT, `status` TEXT, `expiredOn` TEXT, `added` TEXT, `updated` TEXT, `isRead` NUMBER, `isRemoved` NUMBER, `id` TEXT PRIMARY KEY, `cityId` TEXT, `areaId` TEXT, `brandId` TEXT, `dealerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Offer_TABLE_48);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Bank_TABLE_49 = "CREATE TABLE IF NOT EXISTS `Bank` (  `name` TEXT, `branchName` TEXT, `ifsCode` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `areaId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Bank_TABLE_49);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_ServiceHistory_TABLE_50 = "CREATE TABLE IF NOT EXISTS `ServiceHistory` (  `dateOfBooking` TEXT, `mileageCompleted` NUMBER, `charges` NUMBER, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `serviceTypeId` TEXT, `workshopId` TEXT, `vehicleDetailId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_ServiceHistory_TABLE_50);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_VehicleInfo_TABLE_51 = "CREATE TABLE IF NOT EXISTS `VehicleInfo` (  `vehicleModel` TEXT, `vehicleType` TEXT, `fuelType` TEXT, `vehicleTrim` TEXT, `vehicleGearbox` TEXT, `vehicleColor` TEXT, `vehicleBrand` TEXT, `quoteType` TEXT, `id` TEXT PRIMARY KEY, `colorId` TEXT, `brandId` TEXT, `carModelId` TEXT, `trimId` TEXT, `gearBoxId` TEXT, `fuelId` TEXT, `customerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_VehicleInfo_TABLE_51);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Sos_TABLE_52 = "CREATE TABLE IF NOT EXISTS `Sos` (  `contact1` TEXT, `contact2` TEXT, `contact3` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Sos_TABLE_52);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_CurrentVersion_TABLE_53 = "CREATE TABLE IF NOT EXISTS `CurrentVersion` (  `versionCode` NUMBER, `versionName` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_CurrentVersion_TABLE_53);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Faq_TABLE_54 = "CREATE TABLE IF NOT EXISTS `Faq` (  `question` TEXT, `answer` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Faq_TABLE_54);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_SoldViaAutobox_TABLE_55 = "CREATE TABLE IF NOT EXISTS `SoldViaAutobox` (  `type` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `vehicleInfoId` TEXT, `dealerId` TEXT, `customerId` TEXT, `customerQuoteId` TEXT, `vehicleDetailId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_SoldViaAutobox_TABLE_55);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_SnaphyBase_TABLE_56 = "CREATE TABLE IF NOT EXISTS `SnaphyBase` (  `is_deleted` NUMBER, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_SnaphyBase_TABLE_56);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_SnaphyNotification_TABLE_57 = "CREATE TABLE IF NOT EXISTS `SnaphyNotification` (  `title` TEXT, `description` TEXT, `onClick` TEXT, `options` TEXT, `added` TEXT, `updated` TEXT, `status` TEXT, `is_deleted` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_SnaphyNotification_TABLE_57);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_SnaphyAcl_TABLE_59 = "CREATE TABLE IF NOT EXISTS `SnaphyAcl` (  `added` TEXT, `updated` TEXT, `model` TEXT, `read` TEXT, `create` TEXT, `edit` TEXT, `delete` TEXT, `role` TEXT, `is_deleted` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_SnaphyAcl_TABLE_59);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_SnaphyAclProp_TABLE_60 = "CREATE TABLE IF NOT EXISTS `SnaphyAclProp` (  `name` TEXT, `read` TEXT, `write` TEXT, `is_deleted` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `snaphyAclId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_SnaphyAclProp_TABLE_60);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_SnaphyAclRelation_TABLE_61 = "CREATE TABLE IF NOT EXISTS `SnaphyAclRelation` (  `relation` TEXT, `execute` TEXT, `is_deleted` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `snaphyAclId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_SnaphyAclRelation_TABLE_61);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_OfferQuery_TABLE_62 = "CREATE TABLE IF NOT EXISTS `OfferQuery` (  `subject` TEXT, `message` TEXT, `queryType` TEXT, `customerContact` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `dealerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_OfferQuery_TABLE_62);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Insurance_TABLE_63 = "CREATE TABLE IF NOT EXISTS `Insurance` (  `insuranceProvider` TEXT, `policyEndDate` TEXT, `insurancePlanType` TEXT, `policyNumber` TEXT, `id` TEXT PRIMARY KEY, `vehicleInfoId` TEXT, `customerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Insurance_TABLE_63);


                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_DashCamera_TABLE_64 = "CREATE TABLE IF NOT EXISTS `DashCamera` (  `quantity` NUMBER, `deliveryDate` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_DashCamera_TABLE_64);


                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_GpsTracker_TABLE_65 = "CREATE TABLE IF NOT EXISTS `GpsTracker` (  `quantity` NUMBER, `deliveryDate` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_GpsTracker_TABLE_65);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_CustomerOffer_TABLE_66 = "CREATE TABLE IF NOT EXISTS `CustomerOffer` (  `readStatus` NUMBER, `removeStatus` NUMBER, `status` TEXT, `expiredOn` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `offerId` TEXT, `customerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_CustomerOffer_TABLE_66);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_DealerRating_TABLE_67 = "CREATE TABLE IF NOT EXISTS `DealerRating` (  `dealerName` TEXT, `customerName` TEXT, `rating` NUMBER, `id` TEXT PRIMARY KEY, `customerId` TEXT, `dealerId` TEXT, `showroomId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_DealerRating_TABLE_67);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_GpsPacketData_TABLE_68 = "CREATE TABLE IF NOT EXISTS `GpsPacketData` (  `clientId` TEXT, `deviceIMEI` TEXT, `eventCode` NUMBER, `isStoredPacket` NUMBER, `eventType` TEXT, `latitude` TEXT, `longitude` TEXT, `latlng` TEXT, `eventDate` TEXT, `gpsStatus` TEXT, `gmsSignal` NUMBER, `speed` NUMBER, `accumulatedDistance` TEXT, `courseInDegree` NUMBER, `satelliteConnected` NUMBER, `hdop` NUMBER, `voltageEquivalent` NUMBER, `digitalInput1` TEXT, `caseStatus` TEXT, `isOverSpeedStarted` NUMBER, `isOverSpeedEnded` NUMBER, `immobilizerVoilationAlert` NUMBER, `batteryStatus` TEXT, `digitalInput2` TEXT, `ignitionStatus` TEXT, `internalBatteryLowAlert` NUMBER, `anglePollingBit` NUMBER, `digitalOutput1Status` TEXT, `isHarshAccelerationDetected` NUMBER, `isHarshBrakingDetected` NUMBER, `externalBatteryVoltage` TEXT, `internalBatteryVoltage` NUMBER, `gpsPacketId` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_GpsPacketData_TABLE_68);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_GpsTrackerInfo_TABLE_69 = "CREATE TABLE IF NOT EXISTS `GpsTrackerInfo` (  `deviceIMEI` TEXT, `gpsTrackerSimNumber` TEXT, `gpsPassword` TEXT, `registrationNumber` TEXT, `serialNumber` TEXT, `modelName` TEXT, `status` TEXT, `added` TEXT, `updated` TEXT, `gpsTrackerNotification` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `vehicleDetailId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_GpsTrackerInfo_TABLE_69);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Chauffeur_TABLE_70 = "CREATE TABLE IF NOT EXISTS `Chauffeur` (  `chauffeurContact` TEXT, `name` TEXT, `status` TEXT, `message` TEXT, `driverId` TEXT, `ownerName` TEXT, `ownerContact` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `vehicleDetailId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Chauffeur_TABLE_70);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_DealerVehicle_TABLE_71 = "CREATE TABLE IF NOT EXISTS `DealerVehicle` (  `deviceIMEI` TEXT, `serialNumber` TEXT, `registerNumber` TEXT, `brandName` TEXT, `modelName` TEXT, `fuelName` TEXT, `gearboxName` TEXT, `trimName` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `brandId` TEXT, `carModelId` TEXT, `fuelId` TEXT, `gearBoxId` TEXT, `trimId` TEXT, `dealerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_DealerVehicle_TABLE_71);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_TrackDealerVehicle_TABLE_72 = "CREATE TABLE IF NOT EXISTS `TrackDealerVehicle` (  `deviceIMEI` TEXT, `clientId` TEXT, `latitude` NUMBER, `longitude` NUMBER, `latlng` TEXT, `id` TEXT PRIMARY KEY, `dealerId` TEXT, `dealerVehicleId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_TrackDealerVehicle_TABLE_72);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_School_TABLE_73 = "CREATE TABLE IF NOT EXISTS `School` (  `name` TEXT, `email` TEXT, `username` TEXT, `password` TEXT, `added` TEXT, `updated` TEXT, `realm` TEXT, `credentials` TEXT, `challenges` TEXT, `emailVerified` TEXT, `verificationToken` TEXT, `status` TEXT, `created` TEXT, `lastUpdated` TEXT, `id` TEXT PRIMARY KEY, `areaId` TEXT, `cityId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_School_TABLE_73);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Bus_TABLE_74 = "CREATE TABLE IF NOT EXISTS `Bus` (  `busNumber` TEXT, `deviceIMEI` TEXT, `gpsCode` TEXT, `registrationNumber` TEXT, `driverName` TEXT, `driverContact` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `schoolId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Bus_TABLE_74);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_TrackBus_TABLE_75 = "CREATE TABLE IF NOT EXISTS `TrackBus` (  `gpsCode` TEXT, `homeLocation` TEXT, `vicinity` NUMBER, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `busModelId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_TrackBus_TABLE_75);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_BusModel_TABLE_76 = "CREATE TABLE IF NOT EXISTS `BusModel` (  `busNumber` TEXT, `deviceIMEI` TEXT, `gpsCode` TEXT, `registrationNumber` TEXT, `driverName` TEXT, `driverContact` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `schoolId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_BusModel_TABLE_76);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_TrackBusVehicle_TABLE_77 = "CREATE TABLE IF NOT EXISTS `TrackBusVehicle` (  `gpsCode` TEXT, `homeLocation` TEXT, `homeAddress` TEXT, `gpsBusNotification` TEXT, `status` TEXT, `vicinity` NUMBER, `deviceIMEI` TEXT, `added` TEXT, `updated` TEXT, `busNotification` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `busModelId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_TrackBusVehicle_TABLE_77);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_GpsNotification_TABLE_78 = "CREATE TABLE IF NOT EXISTS `GpsNotification` (  `message` TEXT, `deviceIMEI` TEXT, `status` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_GpsNotification_TABLE_78);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_PlanType_TABLE_79 = "CREATE TABLE IF NOT EXISTS `PlanType` (  `name` TEXT, `amount` NUMBER, `chauffeurCount` NUMBER, `vehicleTrackCount` NUMBER, `schoolBusTrackCount` NUMBER, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_PlanType_TABLE_79);


                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_OrderGpsTracker_TABLE_80 = "CREATE TABLE IF NOT EXISTS `OrderGpsTracker` (  `quantity` NUMBER, `id` TEXT PRIMARY KEY, `dealerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_OrderGpsTracker_TABLE_80);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_SellVehicle_TABLE_81 = "CREATE TABLE IF NOT EXISTS `SellVehicle` (  `registrationNumber` TEXT, `kilometers` NUMBER, `yearOfPurchase` NUMBER, `trimData` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `vehicleInfoId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_SellVehicle_TABLE_81);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_Battery_TABLE_82 = "CREATE TABLE IF NOT EXISTS `Battery` (  `batteryNumber` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `vehicleInfoId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_Battery_TABLE_82);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_BindAddress_TABLE_83 = "CREATE TABLE IF NOT EXISTS `BindAddress` (  `type` TEXT, `address` TEXT, `latlong` TEXT, `id` TEXT PRIMARY KEY, `cityId` TEXT, `areaId` TEXT, `dealerId` TEXT, `showroomId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_BindAddress_TABLE_83);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_CustomerContactList_TABLE_84 = "CREATE TABLE IF NOT EXISTS `CustomerContactList` (  `contactNumber` TEXT, `name` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_CustomerContactList_TABLE_84);


                                                                                                                                                                            
                            

                            
                            String CREATE_SharedAppContact_TABLE_85 = "CREATE TABLE IF NOT EXISTS `SharedAppContact` (  `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_SharedAppContact_TABLE_85);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_CustomerContact_TABLE_86 = "CREATE TABLE IF NOT EXISTS `CustomerContact` (  `name` TEXT, `contactNumber` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_CustomerContact_TABLE_86);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_NightLock_TABLE_87 = "CREATE TABLE IF NOT EXISTS `NightLock` (  `deviceIMEI` TEXT, `modelName` TEXT, `customerName` TEXT, `status` TEXT, `timings` TEXT, `days` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `vehicleDetailId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_NightLock_TABLE_87);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_GeoFenceVehicle_TABLE_88 = "CREATE TABLE IF NOT EXISTS `GeoFenceVehicle` (  `deviceIMEI` TEXT, `kilometers` TEXT, `modelName` TEXT, `registerNumber` TEXT, `status` TEXT, `homeLocation` TEXT, `homeLocationAddress` TEXT, `added` TEXT, `updated` TEXT, `id` TEXT PRIMARY KEY, `customerId` TEXT, `vehicleDetailId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_GeoFenceVehicle_TABLE_88);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_ReferralCode_TABLE_89 = "CREATE TABLE IF NOT EXISTS `ReferralCode` (  `code` TEXT, `codeCount` NUMBER, `id` TEXT PRIMARY KEY, `customerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_ReferralCode_TABLE_89);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_PreBookVehicle_TABLE_90 = "CREATE TABLE IF NOT EXISTS `PreBookVehicle` (  `brandName` TEXT, `modelName` TEXT, `added` TEXT, `updated` TEXT, `status` TEXT, `id` TEXT PRIMARY KEY, `preBookVehicleBrandId` TEXT, `preBookVehicleModelId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_PreBookVehicle_TABLE_90);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_PreBookVehicleBrand_TABLE_91 = "CREATE TABLE IF NOT EXISTS `PreBookVehicleBrand` (  `name` TEXT, `added` TEXT, `updated` TEXT, `status` TEXT, `image` TEXT, `id` TEXT PRIMARY KEY, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_PreBookVehicleBrand_TABLE_91);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_PreBookVehicleModel_TABLE_92 = "CREATE TABLE IF NOT EXISTS `PreBookVehicleModel` (  `name` TEXT, `status` TEXT, `image` TEXT, `id` TEXT PRIMARY KEY, `preBookVehicleBrandId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_PreBookVehicleModel_TABLE_92);


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                            

                            
                            String CREATE_PreVehicleBooking_TABLE_93 = "CREATE TABLE IF NOT EXISTS `PreVehicleBooking` (  `brandName` TEXT, `modelName` TEXT, `id` TEXT PRIMARY KEY, `preBookVehicleModelId` TEXT, `customerId` TEXT, _DATA_UPDATED NUMBER )";
                            db.execSQL(CREATE_PreVehicleBooking_TABLE_93);


                
              }
          }).start();
        }

        // Upgrading database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `User`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `AccessToken`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `ACL`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `RoleMapping`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Role`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `CompanyInfo`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Employee`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `FacebookAccessToken`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Customer`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `AmazonImage`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `adminEmail`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `AdminEmail`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `container`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Container`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `application`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Application`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `ImportData`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `installation`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Installation`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `notification`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Notification`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `push`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Push`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Country`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `City`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Brand`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `CarModel`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Fuel`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Trim`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `GearBox`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Color`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Car`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `BreakdownCategory`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Breakdown`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `EmergencyCategory`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Emergency`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `VehicleDetail`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `CustomerMessage`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Workshop`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Showroom`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Dealer`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `ServiceBooking`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `CustomerQuote`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `QuoteReply`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Feedback`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `ServiceType`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Area`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `OldTradeCar`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Offer`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Bank`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `ServiceHistory`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `VehicleInfo`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Sos`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `CurrentVersion`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Faq`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `SoldViaAutobox`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `SnaphyBase`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `SnaphyNotification`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `SnaphyUser`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `SnaphyAcl`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `SnaphyAclProp`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `SnaphyAclRelation`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `OfferQuery`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Insurance`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `DashCamera`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `GpsTracker`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `CustomerOffer`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `DealerRating`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `GpsPacketData`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `GpsTrackerInfo`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Chauffeur`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `DealerVehicle`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `TrackDealerVehicle`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `School`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Bus`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `TrackBus`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `BusModel`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `TrackBusVehicle`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `GpsNotification`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `PlanType`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `OrderGpsTracker`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `SellVehicle`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `Battery`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `BindAddress`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `CustomerContactList`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `SharedAppContact`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `CustomerContact`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `NightLock`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `GeoFenceVehicle`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `ReferralCode`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `PreBookVehicle`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `PreBookVehicleBrand`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `PreBookVehicleModel`");
            
                // Drop older table if existed
                db.execSQL("DROP TABLE IF EXISTS `PreVehicleBooking`");
            

            // Create tables again
            onCreate(db);
        }


        private DbHandler(Context context, String DATABASE_NAME) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            SQLiteDatabase db = this.getWritableDatabase();
            onCreate(db);
        }
}
