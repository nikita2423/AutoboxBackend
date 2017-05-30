# multilogin plugin for Snaphy


###A plugin for multi vendores login

###This plugin is exposed on  `/multilogin` route


##RoadMap
1. Add support for facebook, google and instagram login  for web too.


##Docs
Currently multilogin support method for android and ios for login of `google`, `facebook` and `instagram`
1. In settings/multilogin `database.json`  
    - Create a model and name its value in `FacebookAccessToken` it will  hold the value of data. with following required property.  
```
        "FbUserId": {
           "type": "string",
           "id": true,
           "required": true
         },
         "token": {
           "type": "string",
           "required": true
         },
         "expires": {
           "type": "date",
           "required": false
         },
         "userId": {
           "type": "string",
           "required": true
         },
         "type": {
           "type": "string",
           "required": true
         }
 ```
     
     
     - Create a `User` model and add this model name to `database.json` `User` property. to assosiate user with multilogin support.
     - In `User` model add `ACL` for given method to make public methods for google, instagram and facebook login.
2. In settings/multilogin `conf.json`
  
  ```
  "config":{
      "google":{
        "login":{
          "mobile":{
            "method": "loginWithGoogle", //Name of method assosiated with google login
            "enable": true
          },
          "web":{
            "enable": false
          }
        }
      },
      "facebook":{
        "login":{
          "mobile":{
            "method": "loginWithFb",
            "enable": true
          },
          "web":{
            "enable": false
          }
        }
      },
      "instagram":{
        "login":{
          "mobile":{
            "method": "loginWithInstagram",
            "enable": true
          },
          "web":{
            "enable": false
          }
        }
      }
    },
  ```







####Written by Robins Gupta

