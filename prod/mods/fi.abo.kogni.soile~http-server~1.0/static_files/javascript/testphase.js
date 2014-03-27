require([
	"dojo/request/xhr",
	"dojo/ready"
	],
function(
	xhr,
	ready
	) {
	ready(function() {
		console.log("Ready");
		//console.log(window.testJS);

		var jsonUrl = document.URL + "/json"

		xhr.get(jsonUrl).then(function(data) {
			SOILE2.util.eval(data);

			setTimeout(function() {
				SOILE2.rt.exec_pi();
			}, 1500);
		})

	});
})