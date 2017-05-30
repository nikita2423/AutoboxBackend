'use strict';

angular.module($snaphy.getModuleName())
//Define your services here..
    .factory('SnaphyCache', ["localStorageService", function(localStorageService) {
        //Store cache..data..
        //default cache..
        var storeCache = $snaphy.loadSettings('cache', "storeCache") != undefined ? $snaphy.loadSettings('cache', "storeCache")  : true;

        /**
         * Store cache data.to localstorage..
         * @param key
         * @param val
         * @returns {*}
         */
        function submit(key, val) {
            if(storeCache){
                return localStorageService.set(key, val);
            }
            return null;
        }

        //Clear all data..
        function clearAll() {
            return localStorageService.clearAll();
        }

        function removeItem(key) {
            return localStorageService.remove(key);
        }

        //Get item
        function getItem(key) {
            if (storeCache) {
                return localStorageService.get(key);

            }
            return null;
        }

        return {
            save: submit,
            clearAll: clearAll,
            removeItem: removeItem,
            getItem: getItem
        };
    }]);
