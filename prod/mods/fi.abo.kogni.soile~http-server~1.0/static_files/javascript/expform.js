require(["dojo/dom",
		"dojo/parser", 
		"dijit/form/TextBox",
		"dijit/registry",
		"dojo/on",
		"dojo/dom-form",
		"dojo/request/xhr",
		"dojo/ready"],
function(dom,
		parser,
		TextBox,
		registry,
		on,
		domForm,
		xhr,
		ready) {
	ready(function() {
		parser.parse();

		var submitButton = dom.byId("submit");
		var form = dom.byId("expForm");

		on(submitButton, "click", function() {
			if(true) {
				console.log("Valid");
				var jsform = domForm.toJson("expForm");

				console.log(jsform);

				xhr.post(
					"",{
						data:jsform
					}).then(function(data){
					console.log(data);
				});
			}
			console.log("END");
		});
	});
});

