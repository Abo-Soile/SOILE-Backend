
require(["dojo/dom",
		"dojo/dom-construct",
		"dojo/dom-style",
		"dojo/dom-class",
		"dojo/parser", 
		"dijit/form/TextBox",
		"dijit/registry",
		"dojo/on",
		"dojo/dom-form",
		"dojo/request/xhr",
		"dojo/request",
		"dojo/json",
		"dojox/layout/ContentPane",
		"dojox/widget/DialogSimple",
		"dojox/form/Uploader",
		"dojo/ready"],
function(dom,
		construct,
		domStyle,
		domClass,
		parser,
		TextBox,
		registry,
		on,
		domForm,
		xhr,
		request,
		json,
		contentPane,
		Dialog,
		Upload,
		ready) {
	ready(function() {
		parser.parse();

		var uploadUrl = document.URL + "/imageupload"
		var upbutton = registry.byId("uploadButton");

		var uploader = new dojox.form.Uploader({
			label:"Select images",
			multiple: true,
			url:uploadUrl,
		}).placeAt("uploader");
		uploader.startup();

		on(upbutton, "click", function() {
			uploader.upload();
		})

		// dojo.byId("uploader").appendChild(uploader.domNode);

		var submitButton = registry.byId("compileButton");
		var runButton = registry.byId("runButton");
		// var codeBox = registry.byId("code");
		var errorBox = dom.byId("errorbox");

		var compiledCode = "";

		var editor = ace.edit("editor");
		editor.setTheme("ace/theme/dawn");

		on(compileButton, "click", function() {
			console.log("compile");
			
			//var code = {"code":codeBox.get("value")};
			var code = {"code":editor.getValue()};

			xhr.post("", {
				data: json.stringify(code)
			}).then(function(data) {
				data = json.parse(data);
				console.log(data);

				if(data.errors) {
					errorBox.innerHTML = data.errors;
					domClass.remove(errorBox, "hidden");
					console.log("errors");
				}else {
					domClass.add(errorBox,"hidden");
					compiledCode = data.code;
				}

			})
		})

		on(runButton, "click", function() {
			console.log(compiledCode);
			console.log("Executing soile");
			SOILE2.util.eval(compiledCode);
			setTimeout(function() {
				SOILE2.rt.exec_pi();
			}, 1500);
		})
	});
});