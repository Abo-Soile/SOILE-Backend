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
		"dojox/form/uploader/FileList",
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
		FileList,
		ready) {
	ready(function() {
		parser.parse();

		var baseTable = "<thead><tr><th>Timestamp</th><th>Message</th></tr></thead>"

		var uploadUrl = document.URL + "/imageupload"
		var upbutton = registry.byId("uploadButton");

		var uploader = new dojox.form.Uploader({
			label:"Select images to upload",
			multiple: true,
			url:uploadUrl,
			uploadOnSelect: true
		}).placeAt("uploader");
		uploader.startup();

		on(uploader,"Complete",function(uploadedFiles){ 
			console.log("Upload Completed");
			buildImageList();
		}); 

		// on(upbutton, "click", function() {
		// 	uploader.upload();
		// })

		// dojo.byId("uploader").appendChild(uploader.domNode);

		var submitButton = registry.byId("compileButton");
		var runButton = registry.byId("runButton");
		// var codeBox = registry.byId("code");
		var errorBox = dom.byId("errorbox");
		var logger = document.getElementById('log');

		var soileStartTime = 0;

		var compiledCode = "";

		var editor = ace.edit("editor");
		editor.setTheme("ace/theme/dawn");

		function end(data) {
			console.log("it's over");
			console.log(data);
		}

		var logfunc = function (message) {
			var row = logger.insertRow(1);
			var timestamp = Date.now() - soileStartTime;

			var timeCell = row.insertCell(0);
			timeCell.innerHTML = timestamp/1000 + " s  ";
			var messageCell = row.insertCell(1);
		    if (typeof message == 'object') {
		    	var msg = (JSON && JSON.stringify ? JSON.stringify(message) : message);
		    	messageCell.innerHTML = msg;
		        
		    } else {
		        messageCell.innerHTML = message;
		    }

		}

		var buildImageList = function () {
			var imageList = dom.byId("imagelist");

			//construct.destry(imageList)
			imageList.innerHTML = "";
			xhr.get(window.location.href+"/imagelist").then(function(data) {
				var imageJson = JSON.parse(data);
				console.log("Updating filelist");
				console.log(imageJson);

				for(var i = 0; i<imageJson.length; i++) {

					var name = imageJson[i].name;
					var humanName = name.substring(0, name.lastIndexOf("."));
					var url = "/"+imageJson[i].url
					var li = construct.create("li", null,imageList,"last");

					console.log("insering " + name);
					var insertButton = new dijit.form.Button({
						label:"Use",
						// id:"insert_"+imageJson[i].name,
						onClick:function() {
							var str = "var"+name+" <- imagefile(" + url+ ") \n"
							editor.insert(str);
						}
					});

					var deleteButton = new dijit.form.Button({
						label:"",
						iconClass:'dijitCommonIcon dijitIconDelete',
						onClick: function() {
							console.log("deleteing image");
							construct.destroy(li);
						}
					})
					console.log(insertButton.domNode);
					construct.place("<img src="+url+">", li)
					construct.place("<span class='imgname'>" + humanName + "</span>", li);
					construct.place(insertButton.domNode, li);
					construct.place(deleteButton.domNode, li);

					console.log("insert complete");
				}
			});
		}

		buildImageList();

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
			logger.innerHTML = baseTable;

			soileStartTime = Date.now();

			console.log(compiledCode);
			console.log("Executing soile");
			SOILE2.util.eval(compiledCode);
			SOILE2.util.setEndFunction(end);
			SOILE2.util.setLogFunction(logfunc);
			SOILE2.util.resetData();
			setTimeout(function() {
				SOILE2.rt.exec_pi();
			}, 1500);
		})
	});
});