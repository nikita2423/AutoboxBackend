## dataImport plugin for Snaphy


###Plugin to import data from excel into database

###This plugin is exposed on  `/dataImport` route

###Please copy the` dataImport` folder to `common/settings/` after plugin installed.

### TO Install a npm module use `npm install moduleName --prefix ../../../ --save` and then save the module in package.json of plugin file.

#Doc
1. Copy the `dataImport` folder present inside `dataImport` to `settings`.  
2. Create a property with name `worksheet` and start adding property by Sheet name.
 ```
 "worksheet":{
     "Admin":{
       "structure":[
         {
           "model": "School",
           "properties":[
             {
               "sheetColName": "School_Name",
               "modelProperty":"name",
               "unique": true
             },
             {
               "sheetColName": "School_Address",
               "modelProperty":"address",
               "unique": false
             },
             {
               "sheetColName": "School_Contact",
               "modelProperty":"phoneNumber",
               "unique": false
             }
           ]
         },
         {
           "model": "Admin",
           "properties":[
             {
               "sheetColName": "Admin_Name",
               "modelProperty":"name",
               "unique": true
             },
             {
               "sheetColName": "Admin_Address",
               "modelProperty":"address",
               "unique": false
             },
             {
               "sheetColName": "Admin_Contact",
               "modelProperty":"phoneNumber",
               "unique": false
             },
             {
               "sheetColName": "Admin_EmailId",
               "modelProperty":"email",
               "unique": false
             }
           ],
           "beforeSave":["addSchoolId", "addPassword"]
         },
         {
           "model": "Teacher",
           "properties":[
             {
               "sheetColName": "Teacher_Name",
               "modelProperty":"name",
               "unique": true
             },
             {
               "sheetColName": "Teacher_Address ",
               "modelProperty":"address",
               "unique": false
             },
             {
               "sheetColName": "Teacher_Contact",
               "modelProperty":"phoneNumber",
               "unique": false
             },
             {
               "sheetColName": "Teacher_EmailId",
               "modelProperty":"email",
               "unique": true
             }
           ],
           "beforeSave":["addTeacherBeforeSave"]
         }
       ]
     }
   }
 ```
 
 Here, `Admin` is the name of first sheet. Inside `Admin`
 there is two different models that are going to be affected by this this sheet on save.  
 Create a `structure` property of array value. and in object define its models.  
 
 We have hook for performing `beforeSave` of a data.  
 Just add this model to beforeSave from your plugin.  
 ```
 var dataImport =  helper.loadPlugin("dataImport");
 dataImport.beforeSave.beforeSaveMethod = function(sheetRowObj, callback){
    //Here callback has on argument error on receiving error. the sheet saving stops operation.
    //sheetRowObj is the data of each row which is currently going to be saved.
    shetRowObj has configruration like 
    {
          "MODEL NAME":{
                instance: "MODEL INSTANCE",
                where:{},/where query
                data:{
                    //Data which is going to be saved
                },
                results:{
                    //server data after save
                },
                beforeSave:[],
                config:{
                    rowNumber,
                    parent: complete worksheet parent pointer.
                    which has a configuration like.
                    {
                    	    "SheetName":
                    	    [
                    	        //SheetRow obj
                    	        {
                    				"MODEL NAME":{
                    					instance: "MODEL INSTANCE",
                    					where:{},
                    					data:{
                    						//Data which is going to be saved
                    					},
                    					results:{
                    						//server results
                    					},
                    					beforeSave:[]
                    				}
                    			}
                    		]
                    }
          }
    }
 };
 ```  
 

####Written by Robins Gupta

