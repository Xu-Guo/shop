
;(function() {
    "use strict";

    return {
        capitalize: _.mixin({
            'capitalize': function(string) {
                return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
            }
        }),

        fillArray: _.mixin({
            'fillArray': function(val, arrLength) {
                var arr = [];
                for(var i = 0; i < arrLength; i++){
                    arr[i] = val;
                }
                return arr;
            }
        }),

        isNonEmptyString: _.mixin({
           'isNonEmptyString': function(str) {
               return !_.isEmpty(str) && _.isString(str);
           }
        }),

        isStringInt: _.mixin({
            'isStringInt': function(str) {
                var num = Number(str);
                return !_.isNaN(num) &&  num % 1 === 0;
            }
        }),

        /**
         * Checks if an object has a 'direct' or 'nested' property specified by the parts.
         * If properties have '.' in name escape '.' using '\\.' when specifying in parts.
         *
         * For eg: var a = {test:{'test.1':{}}}
         * _.hasNested(a, 'test.test\\.1') -> true
         * _.hasNested(a, 'test') -> true
         * _.hasNested(a, 'test1.test2') -> false
         */
        hasNested: _.mixin({
            'hasNested': function (obj, parts) {
                if(!_.isObject(obj)) {
                    return false;
                }
                var o = _.cloneDeep(obj);
                // do not split on dot preceded by \ '\.' this is to take care of cases where property name has dot in it
                parts = parts.replace(/\\\./g, '\uffff').split('.').map(function(s) {
                    return s.replace(/\uffff/g, '.');
                });
                while (parts.length > 0 && o != null && !_.isUndefined(o[parts[0]])) {
                    o = o [parts.shift()];
                }
                return parts.length == 0;
            }
        })

    };

})();

