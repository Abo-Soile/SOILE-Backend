var SOILE2;

if (SOILE2 !== undefined){
  throw new Error('SOILE2 already defined!');
}

SOILE2 = (function(){
  'use strict';

  var soile2 = {};
  var bin = {},        // builtin
      rt = {},         // runtime
      sys = {},        // system
      gen = {},        // generated functions
      defs = {},       // definitions (gvars, vals, functions)
      util = {};       // miscellaneous utility functions
  
  bin.hide = function(id){
      id = soile2.util.getid(id);
      $(id).css('display', 'none');
  };
  
  bin.setstimuli = function(arr){
    if (jQuery.isArray(arr)) {
      soile2.rt.stimuli = soile2.util.copyobject(arr);
      
      /*
       * We will reverse the array so that we can 
       * pop the elements starting from the end.
       */
      soile2.rt.stimuli.reverse();
    }
  };
  
  bin.stimulus = undefined;
  
  bin.show = function(id){
      id = soile2.util.getid(id);
      $(id).css('display', 'block');
  };
  
  // Function environments are part of the runtime.
  rt.fenvs = {};
  
  rt.definefenv = function(fname, env, init){
    var fenvs = this.fenvs;
    
    fenvs[fname] = {
      /*
       * 'env' is the object which contains fvar and var definitions.
       */
      'env': env,
      
      /*
       * 'init' is the function which is called before the 
       * user-defined function (fname). 
       */
      'init': init
    };
  };
  
  rt.finalize_defs = function(){
    this.freeze(soile2.defs.gvars);
    this.freeze(soile2.defs.vals);
    this.freeze(soile2.defs.fns);
    this.freeze(soile2.defs.gen);
    this.freeze(soile2.rt.fenvs);
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
  
  rt.futuretimestamp = function(ms) {
    return (this.timestamp() + ms);
  };
  
  rt.getfenv = function(name){
    var fenvs = this.fenvs;
    var fenv;
    
    if (fenvs.hasOwnProperty(name)){
      fenv = fenvs[name];
      return fenv.init(fenv.env);
    }
    return undefined;
  };
  
  rt.defvar = function(name, value){
    soile2.defs.vars[name] = value;
  };
  
  rt.truthvalue = function(value){
    // http://stackoverflow.com/questions/7615214/in-javascript-why-is-0-equal-to-false-but-not-false-by-itself
    // http://javascriptweblog.wordpress.com/2011/02/07/truth-equality-and-javascript/
    
    if (value === false || value === null || value === undefined){
      return false;
    }
    return true;
  };
  
  rt.milliseconds = function(num){
    if (typeof num === 'number'){
      return Math.abs(num);
    }
    if (typeof num === 'string'){
      return Math.abs(parseInt(num, 10));
    }
    return 0;
  };
  
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
  
  rt.undefvar = function(name){
    var vars = soile2.defs.vars;
    
    if (vars.hasOwnProperty(name)){
      delete vars[name];
    }
  };
  
  rt.kbdlookup = function(){};
  
  rt.stimuli = [];
  
  rt.has_next_stimulus = function(){
    return (soile2.rt.stimuli.length > 0);
  };
  
  rt.next_stimulus = function(){
    soile2.bin.stimulus = soile2.rt.stimuli.pop();
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
    reset(soile2.defs, 'gen');
    reset(soile2.rt, 'fenvs');
  };
  
  rt.scheduler = (function() {
    var _obj = {
      add: _schdadd,
      delay: 25,
      running: false,
      start: _schdstart,
      stop: _schdstop,
      tasks: [],
      timeoutID: null
    };

    function _scheduledf() {
      _scheduledf1.call(_obj);
    }

    function _scheduledf1() {
      var now = rt.timestamp();
      var tasks = this.tasks;
      var task;

      if (tasks.length > 0) {
        while (tasks.length > 0) {
          task = tasks[0];
          if (task.due < now + 5) {
            task = tasks.shift();
            task.func.apply(task.context, task.params);
            if (task.repeat === true) {
              this.add(task.func, task.context, task.params, task.delay, task.repeat);
            }
          } else {
            return;
          }
        }
      }
      if (tasks.length === 0) {
        this.stop();
      }
    }

    function _schdcompare(a, b) {
      var _cmp = function(x, y) {
        if (x < y) { return -1; }
        if (y < x) { return 1; }
      };
      
      if (a.due !== b.due) {
        return _cmp(a.due, b.due);
      }
      return _cmp(a.index, b.index);
    }

    function _schdadd(func, ctx, params, delay, repeat) {
      var tasks = this.tasks;
      var i;
      
      delay = Math.max(this.delay, delay);
      tasks.push({
        func: func,
        context: ctx,
        params: params,
        delay: delay,
        due: rt.futuretimestamp(delay),
        repeat: repeat,
        index: -1   // This is needed to keep the sorting stable.
      });
      for (i = 0; i < tasks.length; ++i) {
        tasks[i].index = i;
      }
      tasks.sort(_schdcompare);
      if (! this.running) {
        this.start();
      }
    }

    function _schdstart() {
      if (! this.running) {
        this.running = true;
        this.timeoutID = setInterval(_scheduledf, this.delay);
        _scheduledf();
      }
    }

    function _schdstop() {
      if (this.running) {
        this.running = false;
        clearInterval(this.timeoutID);
        this.timeoutID = null;
      }
    }
    return _obj;
  })();
  
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

  // High-level utility functions.
  
  util.copyobject = function(oldObject){
    // https://github.com/douglascrockford/JSON-js
    // http://bestiejs.github.io/json3/
    
    /*
     * We copy an object, or an array, by first serializing 
     * it into a JSON string, and then de-serializing it.
     * It may not be the most efficient way, but it is 
     * conceptually relatively simple and clear.
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
  
  util.getid = function(s) {
    if (typeof s === 'string') {
      if (s.charAt(0) === '#') {
        return s;
      }
      return '#'.concat(s);
    }
    return s;
  };

  soile2.defs = defs;
  soile2.rt = rt;
  soile2.bin = bin;
  soile2.gen = gen;
  soile2.sys = sys;
  soile2.util = util;
  
  return soile2;
})();
