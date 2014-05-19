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

			//might be better to "destroy" the object.
			imageList.innerHTML = "";

			xhr.get(window.location.href+"/imagelist").then(function(data) {
				var imageJson = JSON.parse(data);
				console.log("Updating filelist");
				console.log(imageJson);

				for(var i = 0; i<imageJson.length; i++) {
					buildListElement(imageJson[i], imageList);
				}
			});
		}

		// Building buttons and inserting elemt into image list
		function buildListElement(image, imageList) {

			var name = image.name;
			var humanName = name.substring(0, name.lastIndexOf("."));
			var url = "/"+image.url 	//Relative
			var absoluteUrl = window.location.origin + url
			var li = construct.create("li", null,imageList,"last");

			var insertButton = new dijit.form.Button({
				label:"Use",
				onClick:function() {
					var str = "var "+ humanName +' <- imagefile("'+ absoluteUrl+ '") \n'
					editor.insert(str);
				}
			});

			var deleteButton = new dijit.form.Button({
				label:"",
				iconClass:'dijitCommonIcon dijitIconDelete',
				onClick: function() {
					var url = window.location.href + "/imageupload/"+name 
					var xhrArgs = {
					    url: url,
					    handleAs: "text",
					}
					var deferred = dojo.xhrDelete(xhrArgs);

					deferred.then(function(data) {
						console.log("deleteing image " + data);
						construct.destroy(li);
					},
					function(error) {
						console.log(error);
					});
				}
			})

			construct.place("<img src="+url+">", li)
			construct.place("<span class='imgname'>" + humanName + "</span>", li);
			construct.place(insertButton.domNode, li);
			construct.place(deleteButton.domNode, li);
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

var mousePos = document.getElementById("mouseposition")

var mouseMove = function (e){
	var displayRect = display.getBoundingClientRect()
    x=e.clientX - displayRect.left + 0.5;
    y=e.clientY - displayRect.bottom + displayRect.height;
    cursor="Mouse Position: Top " + y + " Left: " + x ;
    mousePos.innerHTML=cursor
}

function stopTracking(){
    mousePos.innerHTML="";
}

var display = document.getElementById("display")
var displayRect = display.getBoundingClientRect()

//display.onmouseover = mousePos;
// display.onmousemove = function(e) {
// 	console.log(e);
// }

display.onmousemove = mouseMove;
display.onmouseout = stopTracking;