'use strict';

angular.module($snaphy.getModuleName())
//Define your services here..
     .factory('HelperService', ['$state', 'LoginServices', '$q', '$timeout', 'Database', 'DetailViewResource', 'SnaphyTemplate',
         function($state, LoginServices, $q, $timeout, Database, DetailViewResource, SnaphyTemplate)
         {
             var initialize = function(){
                 return $q(function(resolve, reject){
                     if(settings.get()){
                         if(settings.get().config){
                             if(settings.get().config.employee){
                                 LoginServices.addUserDetail.get()
                                     .then(function(userObj){
                                         settings.get().config.employee = userObj;
                                         settings.get().config.isUserLoaded = true;
                                     })
                                     .then(function(employee){
                                         resolve(settings.get().config.employee);
                                     })
                                     .catch(function(error){
                                         settings.get().config.isUserLoaded = false;
                                         reject(error);
                                     });
                             } else{
                                 settings.get().config.isUserLoaded = true;
                                 resolve(settings.get().config.employee);
                             }
                         } else{
                             reject(new Error("Settings Object not present"));
                         }
                     } else{
                         reject(new Error("Settings Object not present"));
                     }
                 });
             };
         }
     ])