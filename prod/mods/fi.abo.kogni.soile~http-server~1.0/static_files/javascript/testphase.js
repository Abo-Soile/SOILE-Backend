require([
	"dojo/request/xhr",
	"dojo/json",
	"dojo/ready"
	],
function(
	xhr,
	JSON,
	ready
	) {
	ready(function() {
		console.log("Ready");

		//Preventing scroll on arrowkeys 37-40 and navigation on backspace 8
		document.addEventListener("keydown", function (e) {
			if([37,38,39,40,8].indexOf(e.keyCode) > -1){
				e.preventDefault();
				// Do whatever else you want with the keydown event (i.e. your navigation).
			}
		}, false);
		//console.log(window.testJS);

		function end(expdata) {
			console.log("Test over");
			console.log(expdata);

			//Send data xhr,
			xhr.post("", {data:JSON.stringify(expdata)}).then(
				function(response) {
					console.log(response);

					//Navigate to next phase
					var url = document.URL;
		            
		            currentPhase = parseInt(url.substr(url.lastIndexOf("/")+1));
		            url = url.slice(0, url.lastIndexOf("/")+1);
		            window.location.href = url+(currentPhase+1);
				})

		}

		var jsonUrl = document.URL + "/json"

		xhr.get(jsonUrl).then(function(data) {
			SOILE2.util.eval(data);
			SOILE2.util.setEndFunction(end);

			setTimeout(function() {
				SOILE2.rt.exec_pi();
			}, 1500);
		})

	});
})