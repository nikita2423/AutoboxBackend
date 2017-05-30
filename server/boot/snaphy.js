const SETTINGS  = require("../../common/settings/conf");
const path = require("path");
const join = path.join;

module.exports = function(server) {
  const chalk = require('chalk');
  const loopback = require('loopback');
  const helper   = require(__dirname + '/../../common/helper')(server);
  const config   = require(__dirname + '/../config.json');
  const STATIC_PATH = '/static';

  const {
    PLUGIN_PRIORITY,
    DESCRIPTION,
    ANGULAR_MODULE,
    NAME,
    AUTHOR,
    VERSION
  } = SETTINGS(server);


  //Now setting up the static files..
  server.use(join(config.adminApiRoot, STATIC_PATH), loopback.static(__dirname + '/../../.views/static'));
  // set the view engine to ejs
  server.set('view engine', 'ejs');


  //Adding properties to an object..
  const concatObject = function(targetObj, containerObj){
    for (let property in targetObj) {
        if (targetObj.hasOwnProperty(property)) {
            // do stuff
            //Add its property and its values..
            containerObj[property] = targetObj[property];
        }
    }
    return containerObj;
  };



  //Load the required plugins script and styles in the memory..
  /**
   * Loads plugins accorsing to priority list..
   * @param data
   * @param state active state name
   * @returns {*}
     */
  const loadPluginsData = function(data, state){
    //get the list of plugins..
    const pluginList = helper.getDirectories(__dirname + '/../../common/plugins');
    //object to check the list of plugin which has been loaded already..
    const done = {};
    //first load the plugins according to priority list..
    if(PLUGIN_PRIORITY){
      for(let i=0; i< PLUGIN_PRIORITY.length; i++){
          let pluginName = PLUGIN_PRIORITY[i];
        //Only run if not already processed..
        if(!done[pluginName]){
          //Add to done list..
          done[pluginName] = true;
          loadPluginToState(pluginName, data, state);
        }
      }
    }

    for(let i=0; i< pluginList.length; i++){
      let pluginName = pluginList[i];
      //Only run if not already processed..
      if(!done[pluginName]){
          //Add to done list..
          done[pluginName] = true;
          loadPluginToState(pluginName, data, state);
      }
    }//for loop

    return data;
  };

    /**
     * Load plugin to state wise..load if state is permitted else unload..it..
      */
  const loadPluginToState = function(pluginName, data, state){
        //Get the settings of the plugin..
        const {confPath} = helper.getSettingPath(pluginName);
        if(confPath){
            const pluginSettings = helper.readPackageJsonFile(confPath);
            if(pluginSettings.activate){
                if(pluginSettings.load){
                    if(pluginSettings.load[state]){
                        loadPlugin(data, pluginName);
                    }
                }else{
                    loadPlugin(data, pluginName);
                }
            }
        }

  };


  /**
   * Load the plugin configuration and static file..
   * @param data
   * @param pluginName
   */
  const loadPlugin = function(data, pluginName){
    //Get the settings of the plugin..
    const {confPath, databasePath, staticPath} = helper.getSettingPath(pluginName);
    if(confPath){
      const pluginSettings = helper.readPackageJsonFile(confPath);
      if(pluginSettings.activate){
        if(staticPath){
          const pluginStaticFiles = helper.readPackageJsonFile(staticPath);
          if(pluginStaticFiles){
            if (pluginStaticFiles.css) {
              data.pluginStyles = concatObject(pluginStaticFiles.css, data.pluginStyles);
            }

            if (pluginStaticFiles.js) {
              data.pluginScripts = concatObject(pluginStaticFiles.js, data.pluginScripts);
            }

            //Load module dependencies..
            if(pluginStaticFiles.moduleDependencies){
              data.moduleDependencies = concatObject(pluginStaticFiles.moduleDependencies, data.moduleDependencies);
            }

            //Load module dependencies..
            if(pluginStaticFiles.settings){
              const adminPanelSettings = pluginStaticFiles.settings;
              for(let i=0; i<adminPanelSettings.length; i++){
                let setting = adminPanelSettings[i];
                if(setting){
                  data.clientSettings.push(setting);
                }
              }
            }
            //Now add hooks..
            if(pluginStaticFiles.bodystructure){
              const {asidebarHook, sidebarHook, headerHook, footerHook} = pluginStaticFiles.bodystructure;
              if(asidebarHook){
                  for(let i=0; i< asidebarHook.length; i++){
                      let hook = asidebarHook[i];
                      if(hook){
                          data.asidebarHook.push(hook);
                      }
                  }
              }

              if(sidebarHook){
                  for(let i=0; i< sidebarHook.length; i++){
                      let hook = sidebarHook[i];
                      if(hook){
                          data.sidebarHook.push(hook);
                      }
                  }
              }

              if(headerHook){
                  for(let i=0; i< headerHook.length; i++){
                      let hook = headerHook[i];
                      if(hook){
                          data.headerHook.push(hook);
                      }
                  }
              }
              if(footerHook){
                  for(let i=0; i< footerHook.length; i++){
                      let hook = footerHook[i];
                      if(hook){
                          data.footerHook.push(hook);
                      }
                  }
              }
            }

          }//if static file
        } //if staticPath

        if(databasePath){
          const pluginDatabases = helper.readPackageJsonFile(databasePath);
          data.databaseObj = getDatabaseObjFormat(pluginName, pluginDatabases, data.databaseObj);
        }
      }//if activate
    }
  };





  /**
   * Format the database object
   * {                                          {
   *   databases: {                                pluginName: {databaseName: databaseMappedName}
   *     databaseName: DatabaseMappedName   ==>
   *   }                                        }
   * }
   */
  var getDatabaseObjFormat = function(pluginName, oldDatabaseObj, targetObjDatabase){
      targetObjDatabase[pluginName] = oldDatabaseObj;
      return targetObjDatabase;
  };


  //Loads the title, desc of the app given in the package.json file.
  var loadAppData = function(data){
    data.title = NAME;
    data.description = DESCRIPTION;
    data.author = AUTHOR;
    data.module = ANGULAR_MODULE;
    data.version = VERSION;
    return data;
  };


    //Changing the view folder
    server.set('views', __dirname + '/../../.views');


    var apiRoot = config.adminApiRoot === '/' ? STATIC_PATH : join(config.adminApiRoot, STATIC_PATH);


    const fetchTemplateSettings = function(data){
        const setting = helper.getTemplateSettings();
        if(setting){
            data.templateSettings = setting;
        }

        return data;
    };

    var initData = function(){
        //Read the main package file..
        var data = {
            title: '',
            author: '',
            description: '',
            //By default the state name is root.
            state: 'root',
            pluginStyles:{},
            pluginScripts: {},
            moduleDependencies:{},
            asidebarHook:[],
            sidebarHook:[],
            headerHook:[],
            footerHook:[],
            //For mapping the defined database in the plugins..
            databaseObj:{},
            staticRoute: apiRoot,
            clientSettings: [],
            templateSettings:{}
        };
        return data;
    };


    /**
     * Add additional routes with a new route and new page with same index.js configuration..but clean web page..with no hooks..present..
     */
  var addAdditionalRoutes = function(){

      var data = fetchTemplateSettings({});
      if(data.templateSettings.routes){
          data.templateSettings.routes.forEach(function(route){
              if(route){
                  if(route.activate){
                    if(route.state){
                        //replace data state..with new state..
                        data.state = route.state;
                        //Now load the route..
                        server.get(route.routeExposure, function(req, res) {
                            var data = initData();
                            data = loadPluginsData(data, route.state);
                            data = loadAppData(data);
                            data = fetchTemplateSettings(data);
                            data.state = route.state;
                            //remove route prop from data..for safe..
                            delete data.templateSettings.route;

                            res.render('index', data);
                        });

                        server.once('started', function() {
                            console.log("Explore " +  chalk.red(route.state) + " at " + chalk.cyan("http://" +  config.host + ':' + config.port + route.routeExposure));
                        });
                    }
                  }
              }
          });
      }



  };

  addAdditionalRoutes();

 server.once('started', function() {
    console.log("Explore admin console at " + chalk.cyan("http://" +  config.host + ':' + config.port + config.adminApiRoot));
 });


};
