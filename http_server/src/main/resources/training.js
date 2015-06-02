var CustomMatcher = require('router')
var console = require('vertx/console');

var customMatcher = new CustomMatcher();


/*
Architectural ideas. 

Tränings experimentet sparar:
  pre komponenter
  post komponenter
  training komponenter
  dataumintervall
  
Per användare sparas: 
  Var i testen en person er: pre - träning - post?
  Data för varje fas, samma som i ett vanligt experiment
  Datum intervall för nästa träning

Urlar:
  /training       -  allmän information samt status för var i testet användaren e.
  /training/pre   -  pre
  /training/post  -  post
  /training/task  -  träningsuppgift
  
  Skippa juttun med faser, istället visas bara rätt experiment/form
  kan ju ändu int navigera mellan olika juttun så, och soile sköter 
  ändå om allt redirectande.

  såå flöde /training -> training/pre -> /training/task (repeat) -> training/post -> /training
*/


//Handles saving of posted data from tests
function handleResultData(data, datatype, callback) {

}

//Admin view, show list of training experiments
customMatcher.get("/training", function(request) {

})

//Create a new training task 
customMatcher.post("/training", function(request) {

})

//View  training experiment
customMatcher.get("/training/:id", function(request) {

})

//Save data to the experiment
customMatcher.post("/training/:id", function(request) {

})

//Pre test
customMatcher.get("/training/:id/pre", function(request) {

})

//Post test
customMatcher.get("/training/:id/post", function(request) {

})

//Repeated training task 
customMatcher.get("/training/:id/task", function(request) {

})

//JSON structure
/*
{
  pre:[],
  post[],
  training[]
}
*/

customMatcher.get("/training/:id/json", function(request) {

})