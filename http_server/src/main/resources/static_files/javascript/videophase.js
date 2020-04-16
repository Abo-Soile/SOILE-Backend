require([
  "dojo/request/xhr",
  "dojo/json",
  "levenshtein",
  "dojo/ready"
  ],
function(
  xhr,
  JSON,
  levenshtein,
  ready
  ) {
  ready(function() {

    //Preventing scroll on arrowkeys 37-40 and navigation on backspace 8
    document.addEventListener("keydown", function (e) {
      if([37,38,39,40,8,32].indexOf(e.keyCode) > -1){
        //console.log(e);
        if(e.target.tagName == "INPUT" || e.target.type == "text" || e.target.tagName == "TEXTAREA") {
          return
        }

        e.preventDefault();
      }
    }, false);


    function sendData(d) {

      //Send data xhr,
      xhr.post(document.URL, {timeout:10000,data:JSON.stringify(d)}).then(
        function(response) {

          if (typeof response !== 'undefined') {
            response = JSON.parse(response);
          }

          if(response.redirect) {
            console.log("JSON_REDIRECTING");
            window.location.replace(response.redirect);
          }

          else {
            //Navigate to next phase
            var url = document.URL;
            var currentPhase = parseInt(url.substr(url.lastIndexOf("/")+1));
            url = url.slice(0, url.lastIndexOf("/")+1);

            if(!isNaN(currentPhase)) {
              console.log("Redirecting " + isNaN(currentPhase));
              window.location.href = url+(currentPhase+1);
            }else {
              location.reload();
            }
          }
        }, function(error) {
          console.log("Sending data failed, retrying...");
          setTimeout(function() {
            console.log("...resending");
            sendData(d);
          }, 1000);
        });
    }
  });
});