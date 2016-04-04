var vertx = require('vertx');
var console = require('vertx/console');

var babyparser = require("libs/babyparse"); 

function jsonRowDataToCsv(json, groupby) {

  var users = {};
  var command = "single";
  var headers = {};
  var idField = groupby;

  console.log("jsonlen = " + json.length);

  for (var i = 0; i < json.length; i++) {
    var item = json[i];
    var id = item[idField];

    if (typeof users[id] === "undefined") {
      users[id] = {};
      console.log("new user");
    } 

    var data = null;

    if (typeof item.data !== "undefined") {
      if (command === "single") {
        data = item.data.single;
      }

      // No single or raw object -> form
      if (typeof item.data.single === "undefined") {
        data = item.data;
      }
    }


    users[id]["timestamp_" + item.phase] = item.timestamp;
    headers["timestamp_" + item.phase] = "";

    for (var datapoint in data) {
      var dataheader = datapoint + " phase" +item.phase;
      users[id][dataheader] = data[datapoint];
      headers[dataheader] = "";

      console.log(datapoint + " --- " + data[datapoint]);
    }
  }

  headers[groupby] = "";

  console.log(JSON.stringify(users));

  var resArr = [];

  for (var id in users) {
    users[id][groupby] = id;
    resArr.push(users[id]);
  } 

  resArr.unshift(headers);

  var csv = babyparser.unparse(resArr, {"delimiter":";"});

  return csv;
}

function jsonMatrixDataToCsv(json, groupby) {

  var data = [];
  var sep =";";

  var csvData = "";

  for (var ik = 0; ik < json.length; ik++) {
    if (typeof json[ik].data.rows !== "undefined") {
      data.push(json[ik]);
    }
  }

  for (var i = 0; i < data.length; i++) {
    var element = data[i];
    var keys = {};

    csvData += groupby+ ": " + element[groupby] + sep + "\n";
    //console.log("RawData number of rows: " + element.data.rows.length);
    var rowCount = element.data.rows.length;
    for (var j = 0; j < element.data.rows.length; j++) {
      var row = element.data.rows[j];
      for (var rkey in row) {
        if (keys.hasOwnProperty(rkey)) {
          keys[rkey][j] = JSON.stringify(row[rkey]);
        }else {
          keys[rkey] = [];
          keys[rkey][j] = JSON.stringify(row[rkey]);
        }
      }
    }

    var csv = "";
    var lastK = "";
    
    //Building headers
    for(var k in keys) {
      csv += k + sep;
      lastK = k;
    }

    //console.log(csv + "\n");
    csv += "\n";
    
    /*
      Skriver ut resultatet till csv:n
    */
    for (var ij = 0; ij < rowCount; ij++) {
      for(var k in keys) {
        if ( keys[k][ij] !== undefined) {
          csv += keys[k][ij] + sep;
          
        } else{
          csv += sep;
        }
      }
      csv += "\n"; 
    }

    csvData += csv;

  }
  return csvData;
}
function jsonMatrixToCsvSorted(json, groupby) {
  var data = json;
  var sep =";";

  var csvData = "";

  var keys = {};

  keys.userid = [];

  var totalRows = 0;

  var rowcounter = 0;
  
  for (var i = 0; i < data.length; i++) {
    var element = data[i];

    //csvData += "userID: " + sep +  element.userid + sep + "\n";
    //console.log("RawData number of rows: " + element.data.rows.length);
    var rowCount = element.data.rows.length;
    for (var j = 0; j < element.data.rows.length; j++) {
      var row = element.data.rows[j];
      for (var rkey in row) {
        if (keys.hasOwnProperty(rkey)) {
          //keys[rkey][j] = JSON.stringify(row[rkey]);
          keys[rkey][rowcounter] = JSON.stringify(row[rkey]);
        }else {
          keys[rkey] = [];
          //keys[rkey][j] = JSON.stringify(row[rkey]);
          keys[rkey][rowcounter] = JSON.stringify(row[rkey]);
        }
        //keys.userid[j] = element.userid;
        keys.userid[rowcounter] = element.userid;

      }
        rowcounter += 1;
    }
    totalRows += rowCount;
  }

  totalRows = rowcounter;

  var csv = "";
  var lastK = "";
  
  //Building headers
  for(var k in keys) {
    csv += k + sep;
    lastK = k;
  }

  //console.log(csv + "\n");
  csv += "\n";
  
  /*
    Skriver ut resultatet till csv:n
  */
  //for (var ij = 0; ij < keys[lastK].length; ij++) {
  for (var ij = 0; ij < totalRows; ij++) {
    for(var k in keys) {
      if ( keys[k][ij] !== undefined) {
        csv += keys[k][ij] + sep;
        
      } else{
        csv += sep;
      }
    }
    csv += "\n"; 
  }

  csvData += csv;

  return csvData;
}

var csvUtils = (function() {
  return {
    'jsonRowDataToCsv':jsonRowDataToCsv,
    'jsonMatrixDataToCsv':jsonMatrixDataToCsv,
    'jsonMatrixToCsvSorted':jsonMatrixToCsvSorted
  };
});

module.exports = csvUtils();