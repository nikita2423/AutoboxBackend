'use strict';

angular.module($snaphy.getModuleName())

    //PubSub factory method defined here..
    .factory('PubSub', ["Database", function(Database){

        /**
         * Subscribe model to subscribe the data..
         * @param model {string} name of model
         * @param where {{}} where query of model
         * @param callback {Function} callback method with arguments (err, value)
         */
        var subscribe = function(model, where, callback){
            where = where || {};
            //Name of the room
            var room;
            //Name of the namespace
            var namespace;
            //Name of the namespace socket..
            var socket;
            if(model){
                //Now save the model..
                var ModelDatabase = Database.loadDb(model);
                if(ModelDatabase){
                    if(ModelDatabase.subscribe){
                        ModelDatabase.subscribe({}, {
                            where: where
                        }, function(){
                            var options = getDetails(model, where);
                            room = options.room;
                            namespace = options.namespace;
                            //Now connect to namespace..
                            socket = io(namespace);
                            //Now join to room..
                            joinRoom(socket, room);
                            var callbackObject = createCallbackObject(socket, options);
                            callback(null, callbackObject);
                        }, function(error){
                            console.error(error);
                            callback(error, null)
                        });
                    }
                }
            }
        };


        /**
         * Create an callback object which contains all the method for listening to changes..
         * @param socket {{}} namespace socket.
         * @param options {{room: String, namespace: String}} Options object
         */
        var createCallbackObject = function(socket, options){
            return {
                room: options.room,
                namespace: options.namespace,
                socket: socket,
                /**
                 * Leave the connected room of namespace..
                 */
                leave: function(){
                    if(this.socket){
                        this.socket.emit('leave', this.room);
                    }
                },
                /**
                 * Triggers when a new data has been added..
                 * @param callback(data) callback function with arguments of data which has been added
                 */
                onDataAdded: function(callback){
                    //Now subscribe to namespace with POST method
                    this.socket.on('POST', callback);
                },
                /**
                 * Triggers when a data has been updated..
                 * @param callback(data) callback function with arguments of data which has been updated
                 */
                onDataUpdated: function(callback){
                    //Now subscribe to namespace with PUT method
                    this.socket.on('PUT', callback);
                },
                /**
                 * Triggers when a data has been deleted..
                 * @param callback(data) callback function with arguments of data which has been deleted
                 */
                onDataDeleted: function(callback){
                    //Now subscribe to namespace with DELETE method
                    this.socket.on('DELETE', callback);
                }
            };
        };

        /**
         * Join room of a namespace
         * @param socket {{}} namespace socket.
         * @param room {string} name of the room
         */
        var joinRoom = function(socket, room){
            if(room){
                socket.emit('create', room);
            }
        };


        
        /**
         * Get the namespace name and room name..
         * @param model {string} name of model
         * @param where {{}} where query of model
         */
        var getDetails = function(model, where){
            var options = {};
            var namespace = "/" + model;
            var room = "/";
            for(var key in where){
                if(where.hasOwnProperty(key)){
                    //Add key to namespace..
                    namespace =  namespace + "/" + key;
                    room = room + where[key] + "/";
                }
            }
            options.room = room;
            options.namespace = namespace;
            return options;
        };


        return {
            subscribe: subscribe
        };
    }]);

