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
		//Preventing scroll on arrowkeys 37-40 and navigation on backspace 8
		document.addEventListener("keydown", function (e) {
			if([37,38,39,40,8].indexOf(e.keyCode) > -1){
				e.preventDefault();
				// Do whatever else you want with the keydown event (i.e. your navigation).
			}
		}, false);
		//console.log(window.testJS);

		function startFunc() {
			console.log("Starting!!!");
		}

		function end(expdata) {
			console.log("Test over");
			console.log(expdata);

			var d = {};
			d = {};
			d.exp = expdata;
			d.duration = SOILE2.testDuration;

			//Send data xhr,
			xhr.post(document.URL, {data:JSON.stringify(d)}).then(
				function(response) {
					console.log(response);

					//Navigate to next phase
					var url = document.URL;
		            
		            currentPhase = parseInt(url.substr(url.lastIndexOf("/")+1));
		            url = url.slice(0, url.lastIndexOf("/")+1);
		            window.location.href = url+(currentPhase+1);
				})

		}

		var jsonUrl = document.URL + "/json";

		xhr.get(jsonUrl).then(function(data) {
			SOILE2.util.enableLoadScreen();

			SOILE2.util.setStartFunction(startFunc)
			SOILE2.util.eval(data);

			SOILE2.util.setEndFunction(end);

			SOILE2.start();
			//setTimeout(function() {
			//	SOILE2.rt.exec_pi();
			//}, 1500);
		})

	});
})