'use strict';
module.exports = function( server, databaseObj, helper, packageObj) {

	const socket = require('socket.io');


	/**
	 * Here server is the main app object
	 * databaseObj is the mapped database from the package.json file
	 * helper object contains all the helpers methods.
	 * packegeObj contains the packageObj file of your plugin. 
	 */

	/**
	 * Initialize the plugin at time of server start.
	 * init method should never have any argument
	 * It is a constructor and is populated once the server starts.
	 * @return {[type]} [description]
	 */
	const init = function(){
		startSocketServer();
		trackChanges();
	};

	//Start the socket servr..
	const startSocketServer = function(){
		//Run this after starting of the server...
		server.once('started', function() {
			server.io = socket(server.start);
			//listenToStatic(server.io);
            //Add this to server..
			server.pubsub = pubsub;
		});

        //Define the pub sub and subscribe method..
        const pubsub = require("./pubsub")(server, databaseObj, helper, packageObj);
	};

	/**
	 * Listen to static events defined in conf.json file
	 * @param socket socket.io object.
     */
	const listenToStatic = function(socket){
		if(packageObj.onStart){
			if(packageObj.onStart.connection){
				var obj = packageObj.onStart.connection;
				//On connection established..
				socket.on('connection', function(socket){
					if(obj.message){
						console.info(obj.message);
					}

					if(packageObj.onStart.disconnect){
						socket.on('disconnect', function(socket){
							if(packageObj.onStart.disconnect.message){
								console.info(packageObj.onStart.disconnect.message);
							}
						});
					}
				});

			}
		}
	};


	/**
	 * Publish any changes made in the server..at real time to client..
	 * @param options {collection: string, method: string, modelId: MongoDb Object, data: {object|array} } Accepts an option with collection name , method like POST, PUT, DELETE and modelId
     */
	const publish = function(options){
		if(server.io){
			var socket = server.io;
			if (options) {
				var collection = options.collection;
				var method = options.method;
				var data = options.data;
				var modelId = options.modelId;
				if (method === 'POST') {
					let name = '/' + collection + '/' + method;
					socket.emit(name, data);
				}
				else {
					let name = '/' + collection + '/' + modelId + '/' + method;
					socket.emit(name, data);
				}
			} else {
				throw 'Error: Option must be an object type';
			}

		}else{
			console.error("Socket object `server.io` not present. User this method after server start.");
		}

	};



	/**
	 * Auto listen for any changes in the model defined in the `conf.json` by property `listen` and publish it accordingly.
	 */
	const trackChanges = function(){
		const {listen} = packageObj;
		if(listen){
			listen.forEach(function(collectionObj){
				const {collection} = collectionObj;
				if(collection){
                    //Add PUT and POST changes
                    onChanges(collection);
                    //Add onDelete changes..
                    onDelete(collection);
				}
			});
		}

	};



    /**
     * Broadcast message to subscribers of respective namespace ==> rooms
     * @param instance {{}} MOdel instance of loopback
     * @param modelName {string} name of model
     * @param method {string} name of method
     */
    const broadcast = function(instance, modelName, method){
        if(!method){
            return console.error("Socket: Method name not present.");
        }
        if(server.pubsub){

            const collection = server.pubsub.findOrCreate(modelName);
            if(collection){
                if(collection.namespaces){
                    for(let key in collection.namespaces){
                        if(collection.namespaces.hasOwnProperty(key)){
                            const namespace = collection.namespaces[key];
                            if(namespace.fields){
                                //set room tag
                                let room = "/";
                                //set found flag == true
                                let found = true;
                                if(namespace.fields.length){
                                    for(var i=0; i< namespace.fields.length; i++){
                                        const field = namespace.fields[i];
                                        if(!instance[field]){
                                            found = false;
                                            break;
                                        }
                                        room = room + instance[field] + "/";
                                    }
                                }

                                if(found){
                                    //If there is a room is present of given name..
                                    if(namespace.rooms[room]){
                                        if(packageObj.debug){
                                            console.log(`Socket: Broadcasting its subscriber of room: ${room}, namespace: ${namespace.name} and method: ${method}`);
                                        }
                                        //Broadcast the message..to its subscribers
                                        namespace.socket.to(room).emit(method.toUpperCase(), instance);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    };



	/**
	 * Listen for onCreate and on Update of data and publish changes to the client subscribers.
	 * @param collection {string} name of collection
     */
	const onChanges = function(collection){
		let model = server.models[collection];
		if(model){
			if(packageObj.debug){
				console.info("\nSocket: Tracking change PUSH and PUT for collection: " + collection );
			}
			
			model.observe("after save", function(ctx, next){
				/*
				OLD METHOD..
				if(method === "POST"){
					if(ctx.isNewInstance){
						if(packageObj.debug){
							console.info("Socket: Publishing event for collection: " + collection);
						}
						publish({
							collection: collection,
							data: ctx.instance,
							method: method
						});
					}
				}else{
					if(method === "PUT"){
						if(packageObj.debug){
							console.info("Socket: Publishing event for collection: " + collection);
						}
						publish({
							collection: collection,
							data: ctx.instance,
							method: method,
							modelId: ctx.instance.id
						});
					}
				}*/

                if(ctx.isNewInstance){
                    if(ctx.instance){
                        //Broadcast..new data
                        process.nextTick(function(){
                           broadcast(ctx.instance, collection, packageObj.methods.POST);
                        });
                    }
                }else{
                    if(ctx.instance){
                        //Broadcast..new data
                        process.nextTick(function(){
                            broadcast(ctx.instance, collection, packageObj.methods.PUT);
                        });
                    }
                }
				//Call the next middleware..
				next();
			});
		}
	};



	/**
	 * Listen for on delete of the model and publish reports to the client subscribers.
	 * @param collection {string} name of collection
	 */
	const onDelete = function(collection){
		let model = server.models[collection];
		if(model){
			if(packageObj.debug){
				console.info("Socket: Tracking DELETE for collection: " + collection );
			}

			model.observe('before delete', function(ctx, next){
				/*
				OLD METHOD..
				if(ctx.where){
					if(ctx.where.id){
						if(packageObj.debug){
							console.info("Socket: Publishing DELETE event for collection: " + collection + " id: " + ctx.where.id );
						}
						publish({
							collection: collection,
							method: 'DELETE',
							modelId: ctx.where.id
						});
					}
				}*/

                if(ctx.where){
                    if(ctx.where.id){
                        model.findById(ctx.where.id, {}, function(err, instance){
                           if(err){
                               console.error("Socket: Error fetching data using where query in delete event type", err);
                           }else{
                               if(instance ){
                                    //Now sending object to next hook..
                                   ctx.hookState.instance = instance;
                               }
                           }
                           //Call the next middleware..
                           next();
                        });
                    }else{
                        //Call the next middleware..
                        next();
                    }
                }else{
                    //Call the next middleware..
                    next();
                }
			});

            //Middle ware of after delete..
            model.observe('after delete', function(ctx, next){
                //If instance is present from before delete middleware the broadcase the message of delete..
                if(ctx.hookState.instance){
                    //Broadcast..new data
                    process.nextTick(function(){
                        broadcast(ctx.hookState.instance, collection, packageObj.methods.DELETE);
                    });
                }
                //Call the next middleware..
                next();
            });
		}
	};


	//return all the methods that you wish to provide user to extend this plugin.
	return {
		init: init,
		publish: publish
	}
}; //module.exports
