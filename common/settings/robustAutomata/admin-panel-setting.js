/**
 * Created by robins on 2/12/15.
 */
'use strict';
/*jslint browser: true*/
/*$snaphy*/
//This is the setting file of the plugin..TO be configured according to the user needs..
var settings = {
  "defaultTemplate": true,
  "tablePanelId": "#automataWidget",
  "routePrefixName" : "data",
  loadDatabases: [
      "Employee",
      "Customer",
      "Area",
      "Bank",
      "Brand",
      "Breakdown",
      "BreakdownCategory",
      "Car",
      "CarModel",
      "City",
      "Color",
      "Country",
      "CurrentVersion",
      "CustomerMessage",
      "CustomerQuote",
      "Dealer",
      "Emergency",
      "EmergencyCategory",
      "Faq",
      "Feedback",
      "Fuel",
      "GearBox",
      "Offer",
      "OldTradeCar",
      "QuoteReply",
      "ServiceBooking",
      "ServiceHistory",
      "ServiceType",
      "Showroom",
      "SoldViaAutobox",
      "Sos",
      "Trim",
      "VehicleDetail",
      "VehicleInfo",
      "Workshop"
  ]
};


//Now adding settings to the main index file..
$snaphy.addSettings('robustAutomata', settings);
