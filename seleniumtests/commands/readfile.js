exports.command = function(file, callback) {
  var self = this;
  var fs = require('fs');
  
  try {
    var data = fs.readFileSync(file);
    console.log("data");
  } catch (err) {
    console.log(err);
    throw "Unable to open file: " + file;
  }
    
  this.execute(
    function(data) { // execute application specific code
      App.resizePicture(data);
      return true;
    }, 
    
    [imageData], // arguments array to be passed
    
    function(result) {
      if (typeof callback === "function") {
        callback.call(self, result);
      }
    }
  );
  
  return "data"
  //return this; // allows the command to be chained.
};
