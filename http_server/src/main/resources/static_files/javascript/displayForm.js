require(["dijit/form/Button",
		"dijit/form/TextBox",
		"dojo/dom",
		"dojo/dom-style",
		"dojo/dom-construct",
		"dojo/dom-class",
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
		domClass,
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

	var getFormUrl = document.URL+"/getform"

	var editorHidden = false;
	var expandButton = registry.byId("expandButton");

	var editorParent = dom.byId("editorParent");
	var demoParent = dom.byId("demoParent");
	var resultParent = dom.byId("resultParent");

	var editor = ace.edit("editor");
	editor.setTheme("ace/theme/dawn");
	editor.getSession().setTabSize(2);
	editor.getSession().setUseWrapMode(true);
	editor.setShowPrintMargin(false);

	var contentPane = new ContentPane({
			content:"This is a contentpane"
		}).placeAt("renderWindow");		

	contentPane.set("href", getFormUrl);
	contentPane.startup();

	on(expandButton,"click", function() {
		console.log("EXPANDING " + editorHidden);
		if(editorHidden) {
			expandButton.set('label', "Expand");
			editorHidden = !editorHidden;

			domClass.toggle(editorParent,"hiddenelem");

			domClass.toggle(demoParent,'col-md-12');
			domClass.toggle(demoParent,'col-md-6');

			//domClass.toggle(resultParent, 'col-md-offset-6');
		} else {
			expandButton.set('label',"Show Editor");
			editorHidden = !editorHidden;

			domClass.toggle(demoParent,'col-md-12');
			domClass.toggle(demoParent,'col-md-6');
			domClass.toggle(editorParent,"hiddenelem");

			//domClass.toggle(resultParent, 'col-md-offset-6');
		}
	});

	on(renderForm, "click", function() {
		console.log("posting data");
		lastViewerScrollPos = $("#renderWindow").scrollTop();

		renderForm.set('label', ' <i class="fa fa-spinner fa-spin"></i> Saving and rendering form ');
		//renderForm.setDisabled(true);

		//errorFrame.innerHTML = "";
		errorFrameLower.innerHTML = "";
		//domStyle.set("error-message", "visibility", "hidden");
		domClass.add("error-message-lower","hiddenelem");

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
				domClass.toggle("error-message-lower","hiddenelem");

			}else {
				/*updating the div with the new form*/
				//var renderedForm = jsonData.data;
				//renderWindow.innerHTML = renderedForm;
				var cont = "<div id=formcol data-dojo-type='dijit/form/Form' data-dojo-id='formcol'>"+jsonData.data+"</div>"

				/* Destroying and creating a new contentpane*/
				contentPane.destroyRecursive();
				contentPane = new ContentPane({
					"content":cont}).placeAt("renderWindow");
				contentPane.startup();

				$("#renderWindow").scrollTop(lastViewerScrollPos);
			}
		})
	})
});
})