const SETTINGS  = require("../../common/settings/conf");
const path = require("path");
const fs   = require("fs");
const join = path.join;

module.exports = function(server) {
  const chalk           = require('chalk');
  const loopback        = require('loopback');
  const Promise         = require('bluebird');
  const helper          = require(__dirname + '/../../common/helper')(server);
  const config          = require(__dirname + '/../config.json');
  const STATIC_PATH     = '/static';
  const CLIENT_DIR_NAME = "client";

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
   * Loads plugins according to priority list..
   * @param req
   * @param data
   * @param state active state name
   * @returns {*}
     */
  const loadPluginsData = function(req, data, state){
      return new Promise(function (resolve, reject) {
          //get the list of plugins..
          const pluginList = helper.getDirectories(__dirname + '/../../common/plugins');
          //object to check the list of plugin which has been loaded already..
          const done = {};
          const promiseList1 = [];
          const promiseList2 = [];
          //first load the plugins according to priority list..
          if(PLUGIN_PRIORITY){
              for(let i=0; i< PLUGIN_PRIORITY.length; i++){
                  let pluginName = PLUGIN_PRIORITY[i];
                  //Only run if not already processed..
                  if(!done[pluginName]){
                      //Add to done list..
                      done[pluginName] = true;
                      promiseList1.push(new Promise(function (resolve, reject) {
                          //console.log("\nProcessing Plugin> ", pluginName);
                          loadPluginToState(req, pluginName, data, state)
                              .then(function () {
                                  //console.log("Done Plugin> ", pluginName);
                                  resolve();
                              })
                              .catch(function (error) {
                                  //console.log("Error Plugin> ", pluginName);
                                  reject(error);
                              })
                      }));
                  }
              }
          }

          for(let i=0; i< pluginList.length; i++){
              let pluginName = pluginList[i];
              //Only run if not already processed..
              if(!done[pluginName]){
                  //Add to done list..
                  done[pluginName] = true;
                  //promiseList.push(loadPluginToState(req, pluginName, data, state));
                  promiseList2.push(new Promise(function (resolve, reject) {
                      //console.log("\nProcessing Plugin> ", pluginName);
                      loadPluginToState(req, pluginName, data, state)
                          .then(function () {
                              //console.log("Done Plugin> ", pluginName);
                              resolve();
                          })
                          .catch(function (error) {
                              //console.log("Error Plugin> ", pluginName);
                              reject(error);
                          })
                  }));
              }
          }//for loop

          Promise.each(promiseList1, function () {})
              .then(function () {
                  return Promise.each(promiseList2, function () {});
              })
              .then(function () {
                  resolve(data);
              })
              .catch(function (error) {
                    console.log(error);
                    reject(error);
              });
      });
  };



    /**
     * Load plugin to state wise..load if state is permitted else unload..it..
     */
  const loadPluginToState = function(req, pluginName, data, state){
        return new Promise(function (resolve, reject) {
            //Get the settings of the plugin..
            const {confPath} = helper.getSettingPath(pluginName);
            if(confPath){
                const pluginSettings = helper.readPackageJsonFile(confPath);
                if(pluginSettings.activate){
                    if(pluginSettings.load){
                        if(pluginSettings.load[state]){
                            loadPlugin(req, data, pluginName)
                                .then(function () {
                                    return loadPluginsStaticData(req, pluginName, data, state);
                                })
                                .then(function () {
                                    resolve();
                                })
                                .catch(function (error) {
                                    reject(error);
                                });
                        }else{
                            resolve();
                        }
                    }else{
                        loadPlugin(req, data, pluginName)
                            .then(function () {
                                return loadPluginsStaticData(req, pluginName, data, state);
                            })
                            .then(function () {
                                resolve();
                            })
                            .catch(function (error) {
                                reject(error);
                            });
                    }
                }else{
                    resolve();
                }
            }else{
                resolve();
            }
        });
  };



    /**
     * Get the roles of the current logged in users..
     * @param req
     */
    const getRoles = function(req){
        return new Promise(function (resolve, reject) {
            if(req){
                if(req.accessToken){
                    if(req.accessToken.userId){
                        Role = server.models.Role;
                        RoleMapping = server.models.RoleMapping;
                        //bad documentation loopback..
                        //http://stackoverflow.com/questions/28194961/is-it-possible-to-get-the-current-user-s-roles-accessible-in-a-remote-method-in
                        //https://github.com/strongloop/loopback/issues/332
                        var context;
                        try {
                            context = {
                                principalType: RoleMapping.USER,
                                principalId: req.accessToken.userId
                            };
                        } catch (err) {
                            console.error("Error >> User not logged in. ");
                            context = {
                                principalType: RoleMapping.USER,
                                principalId: null
                            };
                        }

                        Role.getRoles(context, function(err, roles) {
                            if(err){
                                reject(err);
                            }else{
                                resolve(roles);
                            }
                        });
                    }else{
                        resolve();
                        //reject(new Error("Request is required"));
                    }
                }else{
                    resolve();
                    //reject(new Error("Request is required"));
                }
            }else{
                resolve();
                //reject(new Error("Request is required"));
            }
        });
    };

    /**
     * Load AsideBar to html data..
     * @param hookObj
     * @param roles
     * @param pluginName
     * @param dataArr
     */
    const loadHook = function (hookObj, roles, pluginName, dataArr) {
        return new Promise(function (resolve, reject) {
            /**
             * Breaking Changes Inserted on Plugins Update in August 20th 2017.
             */
            if(hookObj.path){
                //Check of acl is defined or not..
                let allow = true;
                if(hookObj.acl){
                    if(hookObj.acl.reject){
                        if(hookObj.acl.reject.length){
                            if(roles){
                                for(let i=0; i<roles.length;i++){
                                    const role = roles[i];
                                    const isFound = hookObj.acl.reject.indexOf(role);
                                    if(isFound !== -1){
                                        //Reject role is present thus reject this group..
                                        allow = false;
                                    }

                                    //If any allow is present then allow and break..
                                    if(hookObj.acl.allow){
                                        if(hookObj.acl.allow.length){
                                            const isFound = hookObj.acl.allow.indexOf(role);
                                            if(isFound !== -1){
                                                //Reject role is present thus reject this group..
                                                allow = true;
                                                break;
                                                //Break since priority of allow is higher than reject..
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if(allow){
                    //Add html data to data list..
                    //data.asidebarHook.push(hookObj);
                    //First read file..
                    const pluginPath =  helper.getPluginRootDir(pluginName);
                    if(pluginPath){
                        //Add Client folder to path..
                        const clientPath = path.join(pluginPath, CLIENT_DIR_NAME);
                        //Now add hook path..
                        const hookPath = path.join(clientPath, hookObj.path);
                        //check if path present or not..
                        if(fs.existsSync(hookPath)){
                            fs.readFile(hookPath, "utf8", function(err, data) {
                                if (err) return reject(err);
                                //console.log("Reading Hook data", data);
                                dataArr.push(data);
                                resolve();
                            });
                        }else{
                            reject("Hook Path >" + hookPath + " not found");
                        }
                    }else {
                        reject("Plugin >" + pluginPath + " not found");
                    }
                }else{
                    //Hook not allowed here...
                    resolve();
                }
            }else{
                reject("Path property not found in hook object in Plugin: " + pluginName +" ", hookObj, "for Plugin >", pluginName);
            }
        });
    };



    /**
     * Load hooks to plugins.
     * @param req
     * @param pluginStaticFiles
     * @param pluginName
     * @param data
     */
    const loadHooks = function (req, pluginStaticFiles, pluginName, data) {
        return new Promise(function (resolve, reject) {
            if(pluginStaticFiles.bodystructure){
                const {asidebarHook, sidebarHook, headerHook, footerHook} = pluginStaticFiles.bodystructure;
                let roleList;
                getRoles(req)
                    .then(function (roles) {
                        roleList = roles;
                        const promiseList = [];
                        if(asidebarHook){
                            for(let i=0; i< asidebarHook.length; i++){
                                let hookObj = asidebarHook[i];
                                if(hookObj){
                                    promiseList.push(loadHook(hookObj, roleList, pluginName, data.asidebarHook));
                                }
                            }
                        }
                        //Wait to add all aside bar..
                        return Promise.all(promiseList);
                    })
                    .then(function () {
                        const promiseList = [];
                        //Load sidebar hook..
                        if(sidebarHook){
                            for(let i=0; i< sidebarHook.length; i++){
                                let hookObj = sidebarHook[i];
                                if(hookObj){
                                    promiseList.push(loadHook(hookObj, roleList, pluginName, data.sidebarHook));
                                }
                            }
                        }
                        //Wait to add all aside bar..
                        return Promise.all(promiseList);
                    })
                    .then(function () {
                        const promiseList = [];
                        //Load sidebar hook..
                        if(headerHook){
                            for(let i=0; i< headerHook.length; i++){
                                let hookObj = headerHook[i];
                                if(hookObj){
                                    promiseList.push(loadHook(hookObj, roleList, pluginName, data.headerHook));
                                }
                            }
                        }
                        //Wait to add all aside bar..
                        return Promise.all(promiseList);
                    })
                    .then(function () {
                        const promiseList = [];
                        //Load sidebar hook..
                        if(footerHook){
                            for(let i=0; i< footerHook.length; i++){
                                let hookObj = footerHook[i];
                                if(hookObj){
                                    promiseList.push(loadHook(hookObj, roleList, pluginName, data.footerHook));
                                }
                            }
                        }
                        //Wait to add all aside bar..
                        return Promise.all(promiseList);
                    })
                    .then(function () {
                        resolve();
                    })
                    .catch(function (error) {
                        reject(error);
                    });
            }else{
                resolve();
            }
        });
    };





    /**
   * Load the plugin configuration and static file..
   * @param req
   * @param data
   * @param pluginName
   */
  const loadPlugin = function(req, data, pluginName){
      return new Promise(function (resolve, reject) {
          //Get the settings of the plugin..
          const {confPath, databasePath, staticPath} = helper.getSettingPath(pluginName);
          if(confPath){
              const pluginSettings = helper.readPackageJsonFile(confPath);
              if(pluginSettings.activate){
                  if(databasePath){
                      const pluginDatabases = helper.readPackageJsonFile(databasePath);
                      data.databaseObj = getDatabaseObjFormat(pluginName, pluginDatabases, data.databaseObj);
                  }

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

                          /*----------------------------ADDING HOOKS-----------------------------*/
                          //Now add hooks..
                          /*EDIT => From 20th August 2017. We don't add path and add html code instead. */
                          loadHooks(req, pluginStaticFiles, pluginName, data)
                              .then(function () {
                                  //console.log("DONE>", pluginName);
                                  resolve();
                              })
                              .catch(function (error) {
                                  reject(error);
                              });
                      }else{
                          resolve();
                      }
                  }else{
                      resolve();
                  }
              }else{
                  resolve();
              }
          }else{
              resolve();
          }
      });
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
            ///Static data to be stored as window object..
            staticData:{},
            //For mapping the defined database in the plugins..
            databaseObj:{},
            staticRoute: apiRoot,
            clientSettings: [],
            templateSettings:{}
        };
        return data;
    };


    /**
     * Load plugins global data. and store it in as window object.
     * @param req
     * @param pluginName
     * @param data
     * @param state
     */
    const loadPluginsStaticData = function(req, pluginName, data, state){
        return new Promise(function (resolve, reject) {
            const pluginData = helper.loadPlugin(pluginName);
            if(pluginData){
                if(pluginData.getStaticData){
                    data.staticData = data.staticData || {};
                    pluginData.getStaticData(req, state, data.staticData)
                        .then(function (staticData) {
                            resolve();
                        })
                        .catch(function (error) {
                            console.error("Got error fetching plugin static data.");
                            console.error(error);
                            reject(error);
                        });
                }else{
                    resolve();
                }
            }else{
                resolve();
            }
        });
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
                            data = loadAppData(data);
                            data = fetchTemplateSettings(data);
                            data.state = route.state;
                            //remove route prop from data..for safe..
                            delete data.templateSettings.route;
                            loadPluginsData(req, data, route.state)
                                .then(function () {
                                    res.render('index', data);
                                })
                                .catch(function (error) {
                                    console.log(error);
                                    res.status(500).send(error);
                                });

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
