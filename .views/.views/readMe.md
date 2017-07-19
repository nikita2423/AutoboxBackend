#Snaphy Template 

1) Angular Module Factory methods provided.
    a. Database -> Contains all the database injected.
    b. TemplateSettings -> Contains all the template settings.
    
2) TemplateSettings object is provided by to ways
    1) TemplateSettings service. Plugin can manipulate value to direclty interact with main template plugin.
    2) TemplateSettings internal ejs value to interract directly with index.ejs files.
    
###TemplateSettings    
1) header -> header of the template
2) main -> Interacting with the main template of the plugin.
3) For adding more ejs based routes through backend..

```
 "routes":[{
    "routeExposure": "/brand",
    "state": "brand/",
    "activate": true
  }]
```
To write some plugin only for a specific brand add below lines in conf.json file of that plugin. 
```
"load":{
    "root": true,
    "brand": false
  },
```

Here, root and are two different states  serving data.
    
    