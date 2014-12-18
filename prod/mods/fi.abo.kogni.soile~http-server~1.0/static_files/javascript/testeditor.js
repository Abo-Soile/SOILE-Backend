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

		var baseTable = "<thead><tr><th>Time</th><th>Message</th></tr></thead>"

		var uploadUrl = document.URL + "/imageupload"
		var upbutton = registry.byId("uploadButton");

		var uploader = new dojox.form.Uploader({
			label:"Select images to upload <i class='fa fa-cloud-upload'></i> ",
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
		runButton.setDisabled(true);

		// var codeBox = registry.byId("code");
		var errorBox = dom.byId("errorbox");
		var logger = document.getElementById('log');
		var datatable = document.getElementById('dataTable');
		var rawtable = document.getElementById('rawTable');

		var soileStartTime = 0;

		var compiledCode = "";

		var editor = ace.edit("editor");
		editor.setTheme("ace/theme/dawn");
		editor.getSession().setTabSize(2);
		editor.getSession().setUseWrapMode(true);
		editor.setShowPrintMargin(false);

		var testName = dom.byId('testName');
		var editNameUrl = document.URL + "/editname"

		on(testName, "click", function(evt) {
			console.log("Clicked header");
			testNameForm =  new dijit.form.TextBox({
					value:testName.innerHTML,
					onChange: function(value){
						console.log(value +" ---- ");
						xhr.post(editNameUrl, {
							data: json.stringify({"name":value})
						}).then(function(res) {
							console.log(res);
						});
					}
				});
			console.log(testNameForm.domNode);
			construct.place(testNameForm.domNode, testName,"replace");
			domStyle.set(testNameForm.domNode, "font-size", "26px");

		})


		function end(data) {
			console.log("it's over");
			console.log(data);

		/*	 This part pretty much generate a bucket for all distinct fields
			 in the result object. These buckets are then filled sequentially
			 so that all fields are filled int the right order and missing values
			 are replaced with a -*/
			var set = new Object();

			for (var i = 0; i<data.rows.length; i++) {
				for(j in data.rows[i]) {
					set[j] = {valid:true, data:[]};
				}
			};

			console.log(set)

			for (var i = 0; i<data.rows.length; i++) {
				for(s in set) {
					if ( data.rows[i].hasOwnProperty(s) ) {
						set[s].data.push(data.rows[i][s])
					}else {
						set[s].data.push("-");
					}
				}
			};

			var rowCount = data.rows.length;
			var table = "<thead><tr>"

			for(col in set) {
				table += "<th>"+col+"</th>"
			}
			table += "</tr></thead><tbody>"
			for (var i = 0; i<rowCount; i++) {
				table += "<tr>"
				for(col in set) {
					table += "<td>"+JSON.stringify(set[col].data[i])+"</td>";
				}
				table += "</tr>"
			};
			table += "</tbody>"
			
			rawtable.innerHTML = table;

			var aggregateHead = "<thead><tr>";
			var aggregateBody = "<tbody><tr>";

			//for(var i=0; i < data.single.length; i++) {
			for(key in data.single) {
				aggregateHead += "<th>" + key + "</th>"
				aggregateBody += "<td>" + data.single[key] + "</td>"
			}

			aggregateHead += "</tr></thead>"
			aggregateBody += "</tr></tbody>"

			datatable.innerHTML = aggregateHead + aggregateBody;

			logfunc("Program exited successfully");
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
			//var absoluteUrl = window.location.origin + url
			var absoluteUrl = url;
			var li = construct.create("li", null,imageList,"last");

			var insertButton = new dijit.form.Button({
				label:"Use",
				onClick:function() {
					var str = "var "+ humanName +' <- imagefile("'+ absoluteUrl+ '") \n'
					editor.insert(str);
				}
			});

			var deleteButton = new dijit.form.Button({
				label:"<i class='fa fa-times'></i>",
				/*iconClass:'dijitCommonIcon dijitIconDelete',*/
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
			runButton.setDisabled(true);
			submitButton.set("label","<i class='fa fa-spinner fa-spin'></i> Compiling...");
			
			//var code = {"code":codeBox.get("value")};
			var code = {"code":editor.getValue()};

			xhr.post(document.URL, {
				data: json.stringify(code)
			}).then(function(data) {
				console.log("Then")
				data = json.parse(data);
				submitButton.set("label","Save&Compile</i>");

				if(data.errors) {
					var err = "";

					for(var i=0;i<data.errors.length; i++) {
						if(i===0) {
							err += "<p><i class='fa fa-exclamation-triangle'></i> " + 
									data.errors[i] + "</p>";
						}else {
							err += "<p>" + data.errors[i] + "</p>"
						}
					}
					errorBox.innerHTML = err;
					domClass.remove(errorBox, "hidden");
					console.log("errors");
				}else {
					domClass.add(errorBox,"hidden");
					compiledCode = data.code;

					runButton.setDisabled(false);

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

//Showing mouse coordinates when hovering over the test display
var mousePos = document.getElementById("mouseposition")

var mouseMove = function (e){
	var displayRect = display.getBoundingClientRect()
    x=e.clientX - displayRect.left + 0.5;
    y=e.clientY - displayRect.bottom + displayRect.height;
    cursor="Mouse Position: Top " + y.toFixed(0) + " Left: " + x.toFixed(0) ;
    mousePos.innerHTML=cursor
}

function stopTracking(){
    mousePos.innerHTML="";
}

var display = document.getElementById("display")
var displayRect = display.getBoundingClientRect()

display.onmousemove = mouseMove;
display.onmouseout = stopTracking;


document.addEventListener("keydown", function (e) {
  if([37,38,39,40].indexOf(e.keyCode) > -1){
    e.preventDefault();
    // Do whatever else you want with the keydown event (i.e. your navigation).
  }
}, false);