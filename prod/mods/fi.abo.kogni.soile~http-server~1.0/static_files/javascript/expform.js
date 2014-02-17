require(["dojo/dom",
		"dojo/parser", 
		"dijit/form/TextBox",
		"dijit/registry",
		"dojo/on",
		"dojo/ready"],
function(dom,
		parser,
		TextBox,
		registry,
		on,
		ready) {
	ready(function() {
		parser.parse();

		var submitButton = dom.byId("submit");
		var form = dom.byId("expForm");


		on(submitButton, "click", function(){
			if(form.validate()) {
				console.log("Valid");
			}
		})

	});
});