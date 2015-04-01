var SOILE2;

if (SOILE2 !== undefined){
  throw new Error('SOILE2 already defined!');
}

SOILE2 = (function(){
  'use strict';

  var soile2 = {};
  var bin = {};        // builtin
  var rt = {};         // runtime
  var defs = {};       // definitions (gvars; vals; functions)
  var util = {};       // miscellaneous utility functions

  var endFunc = null;  // function run when the program ends.
  var logFunc = null;  // function used for logging
  
  soile2.defs = defs;
  soile2.rt = rt;
  soile2.bin = bin;
  soile2.util = util;

  bin.onmouseclick = function(id, conf){

    //The args call basically converts a functions arguments object to
    //a real array so that we can expect it to behave like an array.
    var args = Array.prototype.slice.call(arguments);
    var _resettimer, _inputid, _id, action;

    if (_.isString(id)) {
      _id = soile2.util.getid(id);
      if (_.isObject(conf)) {
        if (_.isNumber(conf.inputid)) {
          _inputid = conf.inputid.toString(10);
        }else {
          _inputid = id;
        }
        //_inputid = (_.isString(conf['inputid'])) ? conf['inputid'] : id;
        _resettimer = (_.isBoolean(conf['resettimer'])) ? conf['resettimer'] : false;
        if (_.isFunction(conf.action)) {
          action = conf.action;
          jQuery(_id).click(function(e){
            if (_resettimer) {
              // TODO Reset timer here.
            }
            console.log('mouse clicked. ' + _inputid);
            action.call({}, _inputid);
          });
        }
      } else if (soile2.rt.truthvalue(conf) === false) {
        jQuery(_id).unbind('click');
      }
    }
  };

  // bin.onkeyclick = function(key, conf) {
  //   var args = Array.prototype.slice.call(arguments);
  //   var _resettimer, _inputid, _key, action

  //   if(_.isString(key)) {

  //   }
  // }
  
  bin.onkeypress = function(key, func) {
    var keycode = soile2.rt.kbd.keycode(key);
    if(func) {
      rt.keyhandler.add(keycode, func)
      }
    else {
      rt.keyhandler.remove(keycode);
    }
  };

  bin.onanykey = function(func, ignore) {
    if(func) {
      if(ignore) {
        rt.keyhandler.addAny(func, ignore);
      } else {
        rt.keyhandler.addAny(func);
      }
    }else {
      rt.keyhandler.removeAny();
    }
  }

  bin.getlastkey = function(active) {
    if (typeof active === 'undefined') {
      return rt.keyhandler.lastActiveKey();
    }else {
      return rt.keyhandler.lastKey();
    }
  }

  bin.resumeonkey = function(key) {
    if (key) {
      console.log("Adding keyfunction " + key);
      rt.keyhandler.resume(key);
    }else {
      console.log("No key specified");
      rt.keyhandler.resume();
    }
  };

  /*
   * Copy data -- numbers, strings, arrays or objects but NO
   * functions.
   */
  bin.copydata = function(data){
    return soile2.util.copyobject(data);
  };
  
  /*
   * Stimuli are basically implemented as id strings (DOM id's),
   * so copying a stimulus means copying the DOM element 
   * identified by the ID string.
   */
  bin.copystimulus = function(stim){
    var id;
    var newId, newStim;
    
    if (soile2.util.is_string(stim)){
      id = soile2.util.getid(stim);
    }
    
    if (soile2.util.is_string(id)){
      // http://api.jquery.com/clone/
      // http://stackoverflow.com/questions/1226029/jquery-clone-id
      newId = soile2.rt.uniqueid('id');
      newStim = jQuery(id).clone().attr('id', newId);
      newStim.appendTo(soile2.util.getid("display"));
      return newId;
    }
    return null;
  };

  /*
  The message is a static object that is displayed starting from the top left 
  corner that is used to easily display text, for example instructions.
  */
  bin.emptymsg = function(){
    var id = soile2.util.getid('message');
    jQuery(id).text('');
  };
  
  bin.hidemsg = function(){
    var id = soile2.util.getid('message');
    jQuery(id).css('display', 'none');
    jQuery(id).text('');
  };
  
  bin.showmsg = function(msg){
    var id = soile2.util.getid('message');
    jQuery(id).css('display', 'block');
    jQuery(id).css('left', '50px');
    jQuery(id).css('top', '50px');
    jQuery(id).text(msg);
  };
  /*
  bin.helptext = function(msg){
    if (typeof message == 'object') {
      msg = (JSON && JSON.stringify ? JSON.stringify(msg) : msg);
    } else {
      msg = String(msg);
    }
    if(msg.length > 0) {
        if(logFunc) {
          logFunc(msg);
        } else {
          console.log(msg);
        }
      }
  };*/

  bin.helptext = function() {
    var args = _.toArray(arguments);
    var msg = rt.mergeToString(args);
    if(msg.length > 0) {
      if(logFunc) {
        logFunc(msg)
      } else {
        console.log(msg)
      }
    }
  }

  bin.msgbox = function(msg, _size){
    var id = soile2.rt.uniqueid(); 
    var size;

    if(!_size && !_.isNumber(_size)) {
      size = 20;
    }else {
      size = _size;
    }

    var props = {
      "id": id,
      "class": "hiddenelem",
      "style": "font-size:"+size+"px",
      "text": msg
      //"src": url
    };
    jQuery("<p/>", props).appendTo(soile2.util.getid("display"));
    soile2.rt.dyn.add(id);
    return id;
  };

  bin.rectangle = function(width, height, _borderWidth, _colour) {
    var id = soile2.rt.uniqueid();
    var borderWidth;

    if(!_borderWidth) {
      borderWidth = 2;
    }else{
      borderWidth = _borderWidth;
    }

    var styleSize = "width:"+width+"px; " + "height:"+height+"px;";
    var styleBorder = "border:"+borderWidth+"px solid black;"; 
    var colourStyle = "";
    
    if(_colour !== undefined) {   
      colourStyle = "background-color:" + _colour + ";";
    }

    var props = {
      "id": id,
      "class": "hiddenelem",
      "style": styleSize + " " + styleBorder + " " + colourStyle
      //"src": url
    };
    
    jQuery("<div/>", props).appendTo(soile2.util.getid("display"));
    soile2.rt.dyn.add(id);
    return id;
  }

  bin.not = function(arg){
    var args = Array.prototype.slice.call(arguments);
    if (args.length == 0){
      return undefined;
    }
    return ! (soile2.rt.truthvalue(args[0]));
  };

  bin.and = function(){
    var args = Array.prototype.slice.call(arguments);
    if (args.length == 0){
      return undefined;
    }
    return _.reduce(_.map(args, soile2.rt.truthvalue), function(cumul, elem){
      return cumul && elem;
    }, true);
  };

  bin.or = function(){
    var args = Array.prototype.slice.call(arguments);
    if (args.length == 0){
      return undefined;
    }
    return _.some(_.map(args, soile2.rt.truthvalue));
  };

  bin.divide = function(x, y){
    var x = Number(x)
    var y = Number(y)

    //if (_.isNumber(x) && _.isNumber(y)){
    if (util.can_be_number(x) && util.can_be_number(y)){
      if (y === 0) {
        return Number.NaN;
      }
      return (x / y);
    }
    return undefined;
  };

  bin.eq = function(x, y){

    if (_.isArray(x) && _.isArray(y)) {
      var equalArr = _.isEqual(x.map(String), y.map(String))
      if (equalArr) {
        return true
      }
    }

    if (_.isArray(x) ) {
      if (x.join("") == y) {
        return true;
      }
    }

    if(_.isArray(y)) {
      if(y.join("") == x) {
        return true;
      }
    }

    if (_.isObject(x) || _.isObject(y)) {
      return _.isEqual(x, y);
    }
    else {
      return x == y;
    }
  };
  
  bin.equals = bin.eq;

  bin.gt = function(x, y){
    if (_.isNumber(x) && _.isNumber(y)){
      return (x > y);
    }
    else {
      return x > y;
    }
    return false;
  };
  
  bin.greaterthan = bin.gt;

  bin.hide = function(id){
    id = soile2.util.getid(id);
    jQuery(id).addClass("hiddenelem");
  };
  
  bin.show = function(id){
    var args = Array.prototype.slice.call(arguments);
    var id, pos;
    
    if (args.length == 2){
      id = soile2.util.getid(args[0]);
      pos = args[1];
    }
    else if (args.length == 1){
      id = soile2.util.getid(args[0]);
    }
    
    if (typeof id !== 'undefined'){
      if (jQuery(id).length > 0){
        jQuery(id).removeClass("hiddenelem");

        if (typeof pos !== 'undefined'){
          soile2.bin.position(id, pos);
          return;
        }
      }
    }
  };
  
  bin.imagefile = function(url){
    var id = soile2.rt.uniqueid();
    var props = {
      "id": id,
      "class": "hiddenelem",
      "src": url
    };
    jQuery('<img />', props).appendTo(soile2.util.getid("display"));
    soile2.rt.dyn.add(id);
    return id;
  };
  
  /*
   * Convert an integer representing minutes to milliseconds.
   */
  bin.minutes = function(m){
    if (soile2.util.is_number(m)){
      return (m*60*1000);
    }
    return 0;
  };
  
  /*
   * Convert an integer representing seconds to milliseconds.
   */
  bin.seconds = function(m){
    if (soile2.util.is_number(m)){
      return (m*1000);
    }
    return 0;
  };

  bin.resume = function() {
    console.log("Running resume");
    rt.schd.cancel();
    rt.schd.resume();
  }
  
  bin.position = function(){
    var arr = Array.prototype.slice.call(arguments);
    var id, top, left, args, pos;

    //console.log(arr);

    if (arr.length < 2){
      return;
    }
    args = arr.splice(1);
    id = soile2.util.getid(arr[0]);

    if (args.length == 1){
      if (typeof args[0] === 'object'
          &&
          args[0].hasOwnProperty('top')
          &&
          args[0].hasOwnProperty('left')) {
            
        top = args[0].top;
        left = args[0].left;
        
      }
    }
    else if (args.length > 1){
      left = args[0];
      top = args[1];
    }

    if (args[0] === "center") {
      var imgWidth, imgHeight;
      imgWidth = jQuery(id).outerWidth();
      imgHeight = jQuery(id).outerHeight();

      var posLeft = (jQuery("#display").width()/2) - (imgWidth/2);
      var posTop = (jQuery("#display").height()/2) - (imgHeight/2);

      left = posLeft;
      top = posTop;

      //console.log("width: " + imgWidth + " height " + imgHeight);
      //soile2.bin.position(id, {"top":posTop, "left":posLeft})
    }
    
    if (soile2.util.is_number(top) && soile2.util.is_number(left)){
      // http://stackoverflow.com/questions/4724794/how-do-i-give-a-jquery-element-absolute-positioning-on-a-page
      jQuery(id).css({
        position:  'absolute',
        top: soile2.util.to_integer(top),
        left: soile2.util.to_integer(left)
      });
    }
  };

  bin.lt = function(x, y){
    if (_.isNumber(x) && _.isNumber(y)){
      return (x < y);
    }
    else {
      return x < y;
    }
    return false;
  };
  
  bin.lessthan = bin.lt;

  bin.minus = function(){
    //var nums = _.filter(_.toArray(arguments), _.isNumber);
    var nums = _.filter(_.toArray(arguments), util.can_be_number);
    if (nums.length == 0){
      return undefined;
    }
    if (nums.length == 1){
      return nums[0];
    }
    return (parseFloat(nums[0]) + _.reduce(nums.slice(1), function(memo, num){
      return memo - parseFloat(num);
    }, 0));
  };

  bin.multiply = function(){
    //var nums = _.filter(_.toArray(arguments), _.isNumber);
    var nums = _.filter(_.toArray(arguments), util.can_be_number);
    if (_.isEmpty(nums)){
      return undefined;
    }
    if (nums.length == 1){
      return nums[0];
    }
    return _.reduce(nums, function(memo, num){
      return memo * parseFloat(num);
    }, 1);
  };

  bin.plus = function(){
    //var nums = _.filter(_.toArray(arguments), _.isNumber);
    var nums = _.filter(_.toArray(arguments), util.can_be_number);
    if (_.isEmpty(nums)){
      return undefined;
    }
    if (nums.length == 1){
      return nums[0];
    }
    return _.reduce(nums, function(memo, num){
      return memo + parseFloat(num);
    }, 0);
  };

  bin.modulo = function(num1, num2) {
    var mod = num1 % num2;
    return mod
  };

  bin.round = function(num, op) {
    if(op==="floor") {
      return Math.floor(num)
    }
    if(op==="ceil") {
      return Math.ceil(num)
    }
    return Math.round(num);
  }

  /*
  Datahandling
  */
  bin.storesingle = function(field, value) {
    soile2.rt.dataHandler.storeSingle(field, value);
  }

  bin.storerow =function(field, value) {
    soile2.rt.dataHandler.storeRow(field,value)
  };

  bin.newrow = function() {
    soile2.rt.dataHandler.newRow();
  };

  /*Statistics*/
  bin.average = function(field) {
    return soile2.rt.dataHandler.average(field);
  }

  bin.count = function(field, value) {
    var res;
    if(typeof value !== 'undefined') {
      res = soile2.rt.dataHandler.countValue(field, value);
    } else {
      res = soile2.rt.dataHandler.count(field);
    }
    return res;
  }

  bin.outliers = function(field, multiplier, standarddeviation, externalAverage) {
    return soile2.rt.dataHandler.outlier(field, multiplier, standarddeviation, externalAverage);
  }

  bin.median = function(field, value) {
    return soile2.rt.dataHandler.median(field);
  }

  bin.standarddeviation = function(field) {
    return soile2.rt.dataHandler.standarddeviation(field);
  }

  /*
  Functions related to time and timing
  */

  bin.recordts = function(){
    return soile2.rt.timestamp();
  };

  bin.starttimer = function() {
    console.log("STARTING TIMER");
    soile2.rt.timer.start();
  };

  bin.elapsedtime = function() {
    console.log(soile2.rt.timer.elapsedTime());
    return soile2.rt.timer.elapsedTime();
  };

  bin.stimulus = function(){
    return soile2.rt.stimuli.get();
  };
  
  bin.setstimuli = function(arr){
    soile2.rt.stimuli.set(arr);
  };

  bin.addstimuli = function(stim) {
    soile2.rt.stimuli.add(stim);
  } 

  bin.emptystimuli = function() {
    soile2.rt.stimuli.empty();
  }

  bin.shufflestimuli = function(count) {
    if(typeof count === 'undefined'){
      console.log("Shuffling");
      soile2.rt.stimuli.shuffle();
    }
    else {
      console.log("Shuffling subset");
      soile2.rt.stimuli.subset(count);
    }
  };

  bin.pickstimulisubset= function(count) {
    soile2.rt.stimuli.subset(count)
  }
  
  bin.length = function(o){
    if(_.isNumber(o)) {
      o = o.toString();
    }

    if (_.isArray(o) || _.isString(o)){
      return o.length;
    }
    return -1;
  };
  
  bin.kbdkey = function(name){
    if (_.isString(name)){
      return soile2.rt.kbd.keycode(name);
    }
    return undefined;
  };

  bin.tag = function(){
    return {};
  };

  bin.randominteger = function(min, max) {
    if(_.isNumber(min) && _.isNumber(max)) {
      return Math.floor(rt.random.get()*(max-min+1)+min)
    } 
    else {
      return Math.floor(rt.random.get(1,1000000));
    }
  }

  bin.randomnumber = function(min, max) {
    if(_.isNumber(min) && _.isNumber(max)) {
      return rt.random.get()*(max-min+1)+min;
    }
    else {
      return rt.random.get(1, 1000000);
    }
  }

  bin.seedrandom = function(seed) {
    rt.random.seed(seed);
  }
  
  bin.timeout = function(dur){
    soile2.rt.schd.suspend(dur);
  };

  bin.append = function(str1, str2) {
    //console.log("str1: " + str1 + " " + typeof str1 + " ||str2 " + str2 + " " + typeof str2)
    if(typeof str1 === "undefined") {
      var temp = []
      temp.push(str2);
      return temp;
      return([].push(str2));
    }

    if(typeof str1 !== "object") {
      //console.log("Appending strings " + str1 + str2);       
      var result = ""
      if(!_.isUndefined(str1) && !_.isUndefined(str2)) {
        return str1.toString() + str2.toString();
      }
      else{
        if(str1) {
          return str1.toString();
        }

        if (str2) {
          return str2.toString();
        }
      }
    }

    if(typeof str1 === "object") {
      str1.push(str2);
      return str1;
    }
  }

  bin.elementatindex = function(value, index) {
    var index = parseInt(index)
    if(typeof value != 'undefined' && _.isNumber(index)) {
      if(index>value.length) {
        return false;
      }
      if(value instanceof Array) {
        return value[index];
      }
      else {
        return value.toString()[index];
      }
    }
  }
  /*
  ---------------------------------------------
  */
  // For dynamically added elements. (For instance, a call to 'imagefile' adds an element.)
  rt.dyn = (function(){
    var ids = [];
    
    return {
      'add': function(id){
        ids.push(id);
      },
      'clear': function(){
        while (ids.length > 0){
          jQuery(soile2.util.getid(ids.pop())).remove();
        }
      }
    };
  })();
  
  rt.finalize_defs = function(){
    soile2.rt.seal(soile2.defs.gvars);
    soile2.rt.freeze(soile2.defs.vals);
    soile2.rt.freeze(soile2.defs.fns);
  };
  
  rt.freeze = (function(){
    if (Object.freeze !== undefined || typeof Object.freeze === 'function') {
      return function(obj){
        return Object.freeze(obj);
      };
    }
    return function(obj){
      return obj;
    };
  })();
  
  rt.truthvalue = function(value){
    // http://stackoverflow.com/questions/7615214/in-javascript-why-is-0-equal-to-false-but-not-false-by-itself
    // http://javascriptweblog.wordpress.com/2011/02/07/truth-equality-and-javascript/
    
    if (_.isBoolean(value)) {
      return value;
    }
    if (_.isNull(value) || _.isUndefined(value)){
      return false;
    }
    return true;
  };
  
  rt.milliseconds = function(num){
    if (_.isNumber(num)){
      return Math.abs(num);
    }
    if (_.isString(num)){
      return Math.abs(parseInt(num, 10));
    }
    return 0;
  };
  
  rt.undefvar = function(name){
    var vars = soile2.defs.vars;
    
    if (vars.hasOwnProperty(name)){
      delete vars[name];
    }
  };

  /* Builds a string out of multiple arguments */
  rt.mergeToString = function(arr) {
    var msg = _.reduce(arr, function(memo, num) {
      if(_.isArray(num) || _.isObject(num)) {
        num = JSON.stringify(num);
      } else {
        num = String(num);
      }
      return memo.concat(num);
    }, "");

    return msg;
  }
  
  rt.kbd = (function(){
    var name2keycode = soile2.rt.freeze({
      "backspace": 8,
      "tab":       9,
      "enter":     13,
      "shift":     16,
      "ctrl":      17,
      "alt":       18,
      "capslock":  20,
      "escape":    27,
      "pageup":    33,
      "pagedown":  34,
      "end":       35,
      "home":      36,
      "insert":    45,
      "delete":    46,
      "left":      37,
      "up":        38,
      "right":     39,
      "down":      40,
      "0": 48,
      "1": 49,
      "2": 50,
      "3": 51,
      "4": 52,
      "5": 53,
      "6": 54,
      "7": 55,
      "8": 56,
      "9": 57,
      "a": 65,
      "b": 66,
      "c": 67,
      "d": 68,
      "e": 69,
      "f": 70,
      "g": 71,
      "h": 72,
      "i": 73,
      "j": 74,
      "k": 75,
      "l": 76,
      "m": 77,
      "n": 78,
      "o": 79,
      "p": 80,
      "q": 81,
      "r": 82,
      "s": 83,
      "t": 84,
      "u": 85,
      "v": 86,
      "w": 87,
      "x": 88,
      "y": 89,
      "z": 90
    });
    
    var keycode2name = soile2.rt.freeze({
      37: "left",
      38: "up",
      39: "right",
      40: "down",
      48: "0",
      49: "1",
      50: "2",
      51: "3",
      52: "4",
      53: "5",
      54: "6",
      55: "7",
      56: "8",
      57: "9",
      65: "a",
      66: "b",
      67: "c",
      68: "d",
      69: "e",
      70: "f",
      71: "g",
      72: "h",
      73: "i",
      74: "j",
      75: "k",
      76: "l",
      77: "m",
      78: "n",
      79: "o",
      80: "p",
      81: "q",
      82: "r",
      83: "s",
      84: "t",
      85: "u",
      86: "v",
      87: "w",
      88: "x",
      89: "y",
      90: "z",
    });

    var lookupf = function(key){
      if (this.hasOwnProperty(key) === true){
        return this[key];
      }
      return null;
    };
    
    return {
      'name': function(keyCode){
        return lookupf.call(keycode2name, keyCode);
      },
      
      'keycode': function(keyName){
        return lookupf.call(name2keycode, keyName);
      }
    };
    
  })();

  // The keyhandler captures all keystrokes and executes a callback
  // function if a key is bound to one.
  rt.keyhandler = (function() {
    var keyfunctions = {}
    var anykeyfunctions = []
    var lastKey = ""
    var lastActiveKey = ""

    // Special ignore case
    // Nothing ignored
    var anything = function(keyCode) {
      return true;
    }

    // Special ignore case
    // Checks if keyCode is a letter
    var lettersOnly = function(keyCode) {
      if(keyCode<48||keyCode>90) {
        return false;
      }
      else {
        return true;
      }
    }

    var keyFunction = function(e) {
      lastKey = soile2.rt.kbd.name(e.keyCode);
      //console.log(e.keyCode);
      if(keyfunctions[e.keyCode]) {
        lastActiveKey = e.keyCode;
        keyfunctions[e.keyCode].call();
      }

      //console.log(anykeyfunctions);
      for(var i = 0; i<anykeyfunctions.length; i++) {
        var key = soile2.rt.kbd.name(e.keyCode)

        //console.log(anykeyfunctions[i].ignoreFunc(e.keyCode));
        //Check if the click key is ignored before calling
        // the bound function
        if(anykeyfunctions[i].ignoreFunc(e.keyCode)) {
          lastActiveKey = e.keyCode;
          anykeyfunctions[i].func.call("key");
        }
      }
    }

    document.onkeydown = keyFunction;

    return {
      'add': function(keycode, func) {
        keyfunctions[keycode] = func;
      },
      'remove': function(keycode, func) {
        keyfunctions[keycode] = null;
      },
      'reset': function() {
        keyfunctions = {};
        anykeyfunctions = [];
        lastKey = ""
        lastActiveKey = ""
        // document.onkeydown = null;
      },
      'resume': function(key) {
        var ignoreFunc = anything;
        if(key) {
          var keycode = soile2.rt.kbd.keycode(key);

          var onkey = function() {
            soile2.bin.resume();
            keyfunctions[keycode] = null;
          }
          keyfunctions[keycode] = onkey;
        } else {
          var onkey = null
          onkey = function(key) {
            soile2.bin.resume()
            var index = anykeyfunctions.indexOf(onkey);
            anykeyfunctions.splice(index, 1);
          }

          anykeyfunctions.push({"func":onkey, "ignoreFunc":ignoreFunc});
        }
      },

      //
      'addAny': function(func, ignore) {
        var ignoreFunc = anything;

        if (ignore === "onlyletters"){
          ignoreFunc = lettersOnly;
        } 
        // Ignore case, specific letters
        if(ignore instanceof Array) {
          //Converting keynames to keycodes berfore use
          for(var i in ignore) {
            ignore[i] = soile2.rt.kbd.keycode(ignore[i]);
          }
          console.log(ignore);

          var ignoreFunc = function(keyCode) {
            if (ignore.indexOf(keyCode) === -1) {
              return true;
            }
            else {
              return false;
            }
          }
        }

        anykeyfunctions.push({"func":func,"ignoreFunc":ignoreFunc});
      },
      'removeAny': function() {
        anykeyfunctions = [];
      },
      'lastKey': function() {
        return lastKey;
      },
      'lastActiveKey': function() {
        var key = soile2.rt.kbd.name(lastActiveKey);
        lastActiveKey = ""
        return key;
      }
    }
  })();

  /*
  Methods for storing testdata.
  Single non repeating values should be stored with store single,
  these will simply be sent and stored with the other summarty values. 

  Repeating values, eg data from iterations in a test are stored, should be stored
  in a row as a key value pair. Summary operations can be performed on row data, where a
  field will be included if it exists or fills a certain criteria. The results are stored
  using key:values and are sent to the backend together with the single values.

  Row values should also be stored in the backend if further analysis is neeeded.  
  */
  rt.dataHandler = (function() {
    var data;
    var currentRow;

    var _iterateRows = function(f) {
      var len = data.rows.length;
      for(var i =0; i<len;i++) {
        f(data.rows[i]);
      }
    }

    /* Returns an arroy with data from the current field
       if field is an array do nothign and return it.
    */
    var _fieldToArray = function(field) {
      if(field instanceof Array) {
        return field;
      }
      var arr = [];
      _iterateRows(function(row){
        if(row.hasOwnProperty(field)) {
          arr.push(row[field])
        }
      });
      return arr;
    }

    var _setData = function() {
      data = {};
      currentRow = 0;

      data.single = {};
      data.rows = [];
      data.rows.push({});
    }

    var _average = function(array) {
      if(!array instanceof Array) {
        return 0; 
      }
      var sum =  array.reduce(function(sum, value) {
        return sum + value
      }, 0)

      var avg = sum/array.length;
      return avg;
    }

    /* Using population standard deviation */
    var _standardDeviation = function(array) {
        var avg = _average(array);

        //Calcualting squarediffs
        var squareDiffs = array.map(function(value) {
          var diff = value - avg;
          var sqr = diff * diff;
          return sqr;
        })

        var standardDev = Math.sqrt(_average(squareDiffs));

        return standardDev;
    }

    var _median = function(array) {
      var median = 0;

      array.sort( function(a,b) {return a - b;} );
      var half = Math.floor(array.length/2);

      if(array.length % 2)
          median = array[half];
      else
          median = (array[half-1] + array[half]) / 2.0;

      return median;
    }

    _setData();

    return {
      //Storage methods
      'storeSingle': function(field, value) {
        data.single[field] = value;
      },
      'storeRow': function(field, value) {
        data.rows[currentRow][field] = value;
      },
      'newRow': function() {
        currentRow += 1;
        data.rows.push({});
      },

      //Aggregation methods
      'average': function(field) {
        var noOfvalues = 0;
        var sum = 0;

        return _average(_fieldToArray(field));

       /* _iterateRows(function(row) {
          if(row.hasOwnProperty(field)&& util.is_number(row[field])) {
            sum += row[field];
            noOfvalues += 1;
          }
        });
        console.log("average is " + sum/noOfvalues);
        data.single["average_"+field] = sum/noOfvalues;*/
      },
      'count': function(field) {
        var count = 0;
        var arr = _fieldToArray(field)

        /*_.each(arr, function(row) {
          if(row.hasOwnProperty(field)) {
            count += 1;
          }
        });*/
        console.log(arr);
        console.log(arr.length);

        return arr.length
        
       /* _iterateRows(function(row) {
          if(row.hasOwnProperty(field)) {
            count += 1;
          }
        });
        data.single["count_"+field] = count;*/
      },
      'countValue': function(field, value) {
        var count = 0;
        var arr = _fieldToArray(field)

        _.each(arr, function(val) {
          if(val === value) {
            count += 1;
          }
        });

        return count;

        /*_iterateRows(function(row) {
          if(row.hasOwnProperty(field) && row[field]===value) {
            count += 1;
          }
        });

        data.single["count_"+field+"_"+value] = count;*/
      },
      'median': function(field) {

        var values = _fieldToArray(field); 
        var median; 

        /*_iterateRows(function(row) {
          if(row.hasOwnProperty(field) && util.is_number(row[field])) {
            values.push(row[field]);
          }
        })*/

        median = _median(values);
        return median;

        //data.single["median_"+field] = median;
      },
      'standarddeviation':function(field) {
        var values = _fieldToArray(field);

        var standardDev = _standardDeviation(values);

        //data.single["standarddeviation_"+field] = standardDev;
        return standardDev;
      },

      //Finds values that are within a certain range from the standarddeviation times x
      'outlier':function(field, multiplier, standardd, externalAverage) {
        var arr = _fieldToArray(field);
        var sd = standardd;

        if (sd === undefined) {
          sd = _standardDeviation(arr);
        }
        if (multiplier === undefined) {
          multiplier = 1; 
        }

        var limit = sd * multiplier;
        var av = _average(arr);
        console.log(externalAverage);
        if(externalAverage !== undefined) {
          av = externalAverage;
          console.log("external averagfe: "+av);
        }
        console.log("Limit: " + limit + " av: " + av );

        var upperLimit = av + limit;
        var lowerLimit = av - limit;

        var reminder = [];

        console.log("Upper: " + upperLimit + " Lower: " + lowerLimit);

        //TODO Do somthing proper with this
        _iterateRows(function(row) {
          if((row.hasOwnProperty(field) && util.is_number(row[field])) &&
              (row[field] < upperLimit && row[field] > lowerLimit)) {
            //if(Math.abs(row[field]*multiplier))
            //console.log("Outlier value: " + row[field])
            row[field+"_outlier"] = row[field];
          }
        })

        for(var i =0; i<arr.length;i++) {
          if(util.is_number(arr[i]) &&
              (arr[i] < upperLimit && arr[i] > lowerLimit)) {
            reminder.push(arr[i])
          }
        }
        console.log("Reminder: "+reminder);
        return reminder;

      },

      //Getters and setters
      'getData': function() {
        return(data);
      },
      //Return data to initial state, used when debugging tests
      'reset': function() {
        _setData();
      }

    }
  })();

  // Seedable randomfunction based on Math.sin. appears to produce sufficienlty
  // random numbers for this use. 
  // http://stackoverflow.com/questions/521295/javascript-random-seeds
  rt.random = (function() {
    var _seed = Math.random();

    var _randomFunction = function() {
      var x = Math.sin(_seed++) * 10000;
      return x - Math.floor(x);
    }

    return {
      'seed': function(seed) {
        _seed = seed;
      },
      'get': function() {
        return _randomFunction();
      }
    }
  })();

  // Simple single timer function.
  rt.timer = (function() {
    var startTime = null;

    return {
      'start': function() {
        startTime = soile2.rt.timestamp();
      },
      'elapsedTime': function() {
        if(startTime) {
          var elapsed = soile2.rt.timestamp() - startTime
          return elapsed
        }else {return 0}
      }
    }

  })();
  
  rt.stimuli = (function(){
    var _stimuli = [];

    var currentInteration = true;

    //Stimuli for the  current iteration
    var _iterationStimuli = null;

    //Hasmore is called at the end of an iteration to check if there's
    //still any stimulus left, _hasMore should only be called once per iteration
    var _hasmore = function(){
      _iterationStimuli = null;

      //console.log("Running hasmore " + _stimuli.length)

      return _stimuli.length > 0;
    };

    return {
      'hasmore': _hasmore,
      /*
        Get stimuli returns the current stimuli in the current iteration. Calling 
        getstimuli multiple times in an iteration will return the same result so 
        it's not necessary to store the stimuli in another variable
      */
      'get': function(){
        //if (_stimuli.length > 0) {
         if (_iterationStimuli === null) {
            //console.log("Popping stimuli");
            _iterationStimuli = _stimuli.pop();
         }
         //console.log("Returning stimuli " + _iterationStimuli);
         return _iterationStimuli;

        //}
        //return null;
      },
      'set': function(arr){
        if (_.isArray(arr) && arr.length > 0) {
          
          //Setting current stimuli to 0
          _iterationStimuli = null;

          _stimuli = soile2.bin.copydata(arr);
          _stimuli.reverse();
        }
      },
      'add':function(stim) {
        if(_.isArray(stim)) {
          _stimuli = _stimuli.concat(stim);
        }else{
          _stimuli.push(stim);
        }
      },
      //Shuffle stimuli with Fisher-Yates Shuffle
      'shuffle':function(count) {
        if (_stimuli.length > 0) {
          var counter = _stimuli.length;
          var temp, index;
          while(counter > 0) {
            index = Math.floor(Math.random()*counter);

            counter--;

            temp = _stimuli[counter];
            _stimuli[counter] = _stimuli[index];
            _stimuli[index] = temp;
          }
        }
      },
      //Randomly remove stimuli until x are left
      'subset':function(stimuliCount) {
        this.shuffle();
        if(stimuliCount < _stimuli.length) {
          var removeCount = _stimuli.length - stimuliCount
          for (var i = 0; i < (removeCount); i++) {
            _stimuli.pop(0);
            //console.log("Popping stimuli " + i);
          };
        }
      },
      'empty' : function() {
        _stimuli = [];
      }
    };
  })();
  
  rt.clear_vars = function(){
    var __vars = soile2.defs.vars;
    for (var p in __vars){
      if (__vars.hasOwnProperty(p) === true){
        delete __vars[p];
      }
    }
  };
  
  rt.reset_defs = function(){
    var reset = function(obj, name){
      if (obj.hasOwnProperty(name)){
        delete obj[name];
      }
      obj[name] = {};
    };
    
    reset(soile2.defs, 'gvars');
    reset(soile2.defs, 'vals');
    reset(soile2.defs, 'fns');
    reset(soile2.defs, 'vars');
  };
  
  rt.opcodes = (function(){
    return soile2.rt.freeze({
      Eoc: -1,
      Assign: 0,
      Fcall: 1,
      If: 2,
      While: 3,
      Goto: 4,
      Wait: 5,
      Def: 6,
      Undef: 7,
      Suspend: 8
    });
  })();
  
  // Get next "program instruction."
  rt.get_pi = undefined;
  
  // Reset "program instructions."
  rt.reset_piarray = function(){
    soile2.rt.get_pi = function(idx){
      return null;
    };
  };
  
  /*
   * Save "program instructions." Note that we are 
   * assigning a FUNCTION, not an array. The 
   * "program instruction" array is really a 
   * function which closes over an array 
   * (this is for encapsulation).
   */
  rt.set_piarray = function(piafunc){
    soile2.rt.get_pi = piafunc;
  };
  
  rt.pi_opcode = function(pi){
    if (pi === undefined || pi === null){
      return -1;
    }
    if (typeof pi !== "object" || pi.hasOwnProperty("opcode") === false){
      return -1;
    }
    return pi.opcode;
  };
  
  rt.pi_index = (function(){
    var index = 0;
    
    return {
      'get': function(){
        var current = index;
        index += 1;
        return current;
      },
      'set': function(i){
        index = i;
      }
    };
  })();
  
  /*
   * Execute a program instruction (or several program instructions).
   */
  rt.exec_pi = function(){
    var scheduler = soile2.rt.schd;
    var opcodes = soile2.rt.opcodes;
    var dowait = false;
    var waitfor = 0;
    var pi, opcode, idx;
    
    while (true){
      idx = soile2.rt.pi_index.get();
      pi = soile2.rt.get_pi(idx);
      opcode = soile2.rt.pi_opcode(pi);

      //console.log("Current index: " + idx + "  Instruction: " + JSON.stringify(pi) + " Params: " + pi.params);

      if (opcode < 0){
        // TODO
        rt.finish();
        
        // Program is over, remove listeners send data and navigate to next view
        break;
      }
      if (opcode == opcodes.Assign){
        pi.host[pi.name] = (pi.value)();
        continue;
      }
      else if (opcode == opcodes.Fcall){
        (pi.host[pi.name]).apply(pi.host, pi.params());
        continue;
      }
      else if (opcode == opcodes.If){
        if (pi.cond.call({}) === false){
          soile2.rt.pi_index.set(pi.jmp);
        }
        continue;
      }
      else if (opcode == opcodes.While){
        if ((pi.cond)() === false){
          soile2.rt.pi_index.set(pi.jmp);
        }
        continue;
      }
      else if (opcode == opcodes.Goto){
        soile2.rt.pi_index.set(pi.jmp);
        continue;
      }
      else if (opcode == opcodes.Def){

        pi['func'].call({}, []);
        continue;
      }
      else if (opcode == opcodes.Undef){
        soile2.rt.clear_vars();
        continue;
      }
      else if (opcode == opcodes.Suspend){
        scheduler.suspend();
        return;
      }

      if (opcode == opcodes.Wait){
        dowait = true;
        waitfor = pi.ms();
        break;
      }
    }
    
    if (dowait){
      scheduler.wait(waitfor);
      return;
    }
  };
  
  // The scheduler.
  rt.schd = (function(){
    // The "unit" of the delay times; i.e. the delay time is some multiple of this.
    var MINDELAY = 5;

    // How long the execution will be suspended. ('dur' stands for duration.)
    var suspend_dur = -1;
    
    var due_ts;
    
    // This will be the integer returned from a call to setTimeout.
    var settimeout_id;
    
    var msleft = function(ts){
      return ts - soile2.rt.timestamp();  
      return Math.abs(ts - soile2.rt.timestamp());
    };
    
    var keep_waiting = function(ts){
      return (msleft(ts) > MINDELAY);
    };
    
    var compute_delay = function(ts){
      var delay = msleft(ts);
      //console.log("Delay = " + delay)
      if (delay < 10){
        return MINDELAY;
      }
      if (delay < 50){
        return MINDELAY * 2;
      }
      if (delay < 100){
        return MINDELAY * 5;
      }
      if (delay < 500){
        return MINDELAY * 10;
      }
      if (delay < 1000){
        return MINDELAY * 15;
      }
      return MINDELAY * 20;
    };
    
    var schedule = function(delay){
      settimeout_id = setTimeout(scheduled, delay);
    };
    
    var scheduled = function(){
      settimeout_id = undefined;
      if (keep_waiting(due_ts) === true){
        schedule(compute_delay(due_ts));
        return;
      }
      resume();
    };
    
    var cancel = function(){
      if (settimeout_id) {
        clearTimeout(settimeout_id);
        settimeout_id = undefined;
        due_ts = -1;
      }
    };
    
    var resume = function(){
      var args = Array.prototype.slice.call(arguments);
      if (args.length > 0) {
        if (soile2.util.is_number(args[0])) {
          soile2.rt.pi_index.set(args[0]);
        }
      }
      soile2.rt.exec_pi();
    };
    
    var suspend = function(){
      var args = Array.prototype.slice.call(arguments);
      
      /*
       * When the 'suspend' method is called with a number as a parameter,
       * that will set the duration of suspension WITHOUT suspending the 
       * execution of the program flow.
       * 
       * The researcher can control the length of the suspension with the
       * built-in method soile2.bin.timeout (which corresponds to the 
       * 'timeout' built-in function in the domain specific language.
       */
      if (args.length > 0){
        if (soile2.util.is_number(args[0])){
          suspend_dur = Math.abs(args[0]);
        }
        return;
      }
      
      /*
       * If no suspension duration has been set previously, just stop the
       * execution of the program; otherwise, set a timeout.
       */
      cancel();
      if (suspend_dur > 0){
        settimeout_id = setTimeout(function(){
          resume();
        }, suspend_dur);
        suspend_dur = -1;
      }
    };
    
    var wait = function(ms){
      due_ts = soile2.rt.future_timestamp(ms);
      schedule(compute_delay(due_ts));
    };
    
    return {
      'cancel': cancel,
      'resume': resume,
      'suspend': suspend,
      'wait': wait,
    };
  })();

  rt.finish = function() {
    console.log("Test over");

    soile2.rt.keyhandler.reset();      // Removing all keyhandlers
    $(document).add('*').off(); // Removing all clickhandlers

    // soile2.rt.dataHandler.storeSingle("field", 123456);
    // soile2.rt.dataHandler.storeRow("a", 5);
    // soile2.rt.dataHandler.storeRow("t", true);
    // soile2.rt.dataHandler.newRow();
    // soile2.rt.dataHandler.storeRow("a",20);
    // soile2.rt.dataHandler.storeRow("b",555);
    // soile2.rt.dataHandler.storeRow("c",9999);
    // soile2.rt.dataHandler.storeRow("t", false);
    // soile2.rt.dataHandler.newRow();
    // soile2.rt.dataHandler.storeRow("b", 1000);
    // soile2.rt.dataHandler.storeRow("a", 5);
    // soile2.rt.dataHandler.storeRow("t", true);

    // soile2.rt.dataHandler.average("a");
    // soile2.rt.dataHandler.average("b");

    // soile2.rt.dataHandler.count("a");
    // soile2.rt.dataHandler.count("c");

    // soile2.rt.dataHandler.countValue("a", 5);
    // soile2.rt.dataHandler.countValue("t", true);
    // soile2.rt.dataHandler.countValue("t", false);

    var d = soile2.rt.dataHandler.getData();

    console.log("The end");
    endFunc(soile2.rt.dataHandler.getData());
  }
  
  rt.seal = (function(){
    if (Object.seal !== undefined || typeof Object.seal === 'function') {
      return function(obj){
        return Object.seal(obj);
      };
    }
    return function(obj){
      return obj;
    };
  })();
  
  rt.future_timestamp = function(ms) {
    return (soile2.rt.timestamp() + ms);
  };
  
  rt.timestamp = (function(){
    // http://stackoverflow.com/questions/221294/how-do-you-get-a-timestamp-in-javascript
    if (Date.now !== undefined && typeof Date.now == 'function') {
      return function(){
        return Date.now();
      };
    }
    return function(){
      return (new Date()).getTime();
    };
  })();

  rt.uniqueid = (function(){
    var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var prefix = 'id';
    var default_len = 16;
    
    return function(){
      var args = Array.prototype.slice.call(arguments);
      var len = default_len;
      var idx = 0;
      var id = prefix;
      var i = 0;

      while (i < args.length){
        if (_.isNumber(args[i]) && Math.abs(args[i]) > 0) {
          len = Math.abs(args[i]);
        }
        if (_.isString(args[i])) {
          id = args[i];
        }
        i += 1;
      }

      while (len > 0){
        idx = Math.floor(Math.random() * chars.length);
        id += chars.substring(idx, idx + 1);
        len -= 1;
      }
      return id;
    };
  })();

  rt.phases = (function(){
    return {
      'intermezzo': function(){

      },
      'information': function(idx){

      }
    };
  })();

  // High-level utility functions.
  
  util.copyobject = function(oldObject){
    // https://github.com/douglascrockford/JSON-js
    // http://bestiejs.github.io/json3/
    
    /*
     * We copy an object, or an array, by first serializing 
     * it into a JSON string, and then de-serializing it.
     * It may not be the most efficient way, but it is 
     * conceptually simple and clear.
     * 
     * We assume that the objects we copy do not contain
     * functions. They would be lost in the 
     * serializing process.
     */
    return JSON.parse(JSON.stringify(oldObject));
  };
  
  util.eval = function(code){
    jQuery.globalEval(code);
  };
  
  util.setEndFunction = function(f) {
    endFunc = f;
  }

  util.setLogFunction = function(f) {
    logFunc = f;
  }

  //Resets collected data, should be when a test is rerun when
  //debugging
  util.resetData = function() {
    soile2.rt.dataHandler.reset();
    soile2.rt.keyhandler.reset();
  }

  util.getid = function(s) {
    if (typeof s === 'string') {
      if (s.charAt(0) === '#') {
        return s;
      }
      return '#'.concat(s);
    }
    return s;
  };
  
  // Return true if an object has an id property.
  util.hasobjid = function(elem){
    return (_.isObject(elem) && (_.has(elem, '_id') || _.has(elem, 'id')));
  };
  
  util.is_boolean = function(o){
    return _.isBoolean(o);
  };

  util.is_number = function(i){
    // http://stackoverflow.com/questions/1019515/javascript-test-for-an-integer
    //return ! isNaN(parseInt(i, 10));
    return _.isNumber(i);
  };

  util.can_be_number = function (i) {
    var n = parseFloat(i)
    return _.isNumber(n);
  };

  util.is_string = function(s){
    return _.isString(s);
  };

  util.to_integer = function(i){
    return parseInt(i, 10);
  };

  soile2.bin = soile2.rt.seal(bin);
  
  return soile2;
})();