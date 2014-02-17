require(["dijit/form/Button", 
			"dojo/dom",
			"dojo/dom-style",
			"dojo/request", 
			"dijit/registry",
			"dojo/parser",
			"dojo/on", 
			"dojo/json",
			"dojox/layout/ContentPane",
			"dojo/ready"], 
		function(Button, 
			dom, 
			domStyle,
			request, 
			registry,
			parser,
			on, 
			json,
			ContentPane,
			ready){ 
		ready(function() {

		var markup = dom.byId("markup");
		markup = registry.byId("markup");
		var renderForm = dom.byId("renderform");
		var renderWindow = dom.byId("renderWindow");

		var errorFrame = dom.byId("error-message");

		var contentPane = new ContentPane({
				content:"This is a contentpane"
			},"contPane");			
		on(renderForm, "click", function() {
			console.log("posting data");

			errorFrame.innerHTML = "";
			domStyle.set("error-message", "visibility", "hidden");

			request.post("",{
				data: markup.get("value")

			}).then(function(reply){
				/*Parsing json*/
				jsonData = json.parse(reply);
				if(jsonData.hasOwnProperty('error')) {
					errorFrame.innerHTML = jsonData.error;
					domStyle.set("error-message","visibility","visible");
				}else {
					/*updating the div with the new form*/
					var renderedForm = jsonData.data;
					renderWindow.innerHTML = renderedForm;

					//parser.parse();
					contentPane.set("content",jsonData.data);
					/*Createing or updating the contentpane*/
				}
			})
		})
	});
})