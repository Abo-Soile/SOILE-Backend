require(["dijit/form/Button",
		"dijit/form/TextBox",
		"dojo/dom",
		"dojo/dom-style",
		"dojo/dom-construct",
		"dojo/request", 
		"dijit/registry",
		"dojo/on", 
		"dojo/json",
		"dojo/parser",
		"dojox/layout/ContentPane",
		"dojo/ready"], 
	function(Button,
		TestBox,
		dom, 
		domStyle,
		domConstruct,
		request, 
		registry,
		on, 
		json,
		parser,
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
		}).placeAt("renderWindow");		
	contentPane.set("href", document.URL+"/getform");

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
				//var renderedForm = jsonData.data;
				//renderWindow.innerHTML = renderedForm;


				/* Destroying and creating a new contentpane*/
				contentPane.destroyRecursive();
				contentPane = new ContentPane({
					"content":jsonData.data}).placeAt("renderWindow");
			}
		})
	})
});
})