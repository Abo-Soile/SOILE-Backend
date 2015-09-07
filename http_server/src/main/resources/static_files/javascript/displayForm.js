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

	//var markup = dom.byId("markup");
	//markup = registry.byId("markup");
	var renderForm = registry.byId("renderform");
	var renderWindow = dom.byId("renderWindow");

	var errorFrame = dom.byId("error-message");
	var errorFrameLower = dom.byId("error-message-lower")

	var lastViewerScrollPos = 0;

	var editor = ace.edit("editor");
	editor.setTheme("ace/theme/dawn");
	editor.getSession().setTabSize(2);
	editor.getSession().setUseWrapMode(true);
	editor.setShowPrintMargin(false);

	var contentPane = new ContentPane({
			content:"This is a contentpane"
		}).placeAt("renderWindow");		
	contentPane.set("href", document.URL+"/getform");

	on(renderForm, "click", function() {
		console.log("posting data");
		lastViewerScrollPos = $("#renderWindow").scrollTop();

		renderForm.set('label', ' <i class="fa fa-spinner fa-spin"></i> Saving and rendering form ');
		//renderForm.setDisabled(true);

		//errorFrame.innerHTML = "";
		errorFrameLower.innerHTML = "";
		//domStyle.set("error-message", "visibility", "hidden");
		domStyle.set("error-message-lower","visibility","hidden");

		request.post(document.URL,{
			//data: markup.get("value")
			data: editor.getValue()

		}).then(function(reply){
			/*Parsing json*/
			renderForm.set('label', 'Update Form');
			renderForm.setDisabled(false);

			jsonData = json.parse(reply);
			if(jsonData.hasOwnProperty('error')) {
				//errorFrame.innerHTML = jsonData.error;
				errorFrameLower.innerHTML = jsonData.error;
				//domStyle.set("error-message","visibility","visible");
				domStyle.set("error-message-lower","visibility","visible");
			}else {
				/*updating the div with the new form*/
				//var renderedForm = jsonData.data;
				//renderWindow.innerHTML = renderedForm;
				var cont = '<div id=formcol data-dojo-type="dijit/form/Form">'+jsonData.data+"</div>"

				/* Destroying and creating a new contentpane*/
				contentPane.destroyRecursive();
				contentPane = new ContentPane({
					"content":cont}).placeAt("renderWindow");

				$("#renderWindow").scrollTop(lastViewerScrollPos);
			}
		})
	})
});
})