require(["dojo/dom",
		"dojo/dom-construct",
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
		"dojo/ready"],
function(dom,
		construct,
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
		ready) {
	ready(function() {
		parser.parse();

		var submitButton = dom.byId("submit");
		var newForm = dom.byId("newform")

		var form = dom.byId("expForm");

		var name = registry.byId("name");
		var description = registry.byId("description");
		var startDate = registry.byId("startDate");
		var endDate = registry.byId("endDate");

		var componentList = dom.byId("componentlist")

		var dialog = "";
		//var contentpane = new ContentPane("").placeAt("contentpane");

		on(newForm, "click", function() {
			xhr.post("addform",
				"").then(function(data){
					var data = json.parse(data);
					console.log(data);
					//contentpane.setHref("/questionnaire/render/"+data.id);

					var dialog = new Dialog({
						"title":"titlfs",
						"content":"<iframe id='formframe' src=/questionnaire/mongo/"+data.id +"></iframe>",
						"executeScripts":"true"

						});
					// dialog.show();

					createComponentRow(data.id, {"dialog":dialog});

					//li.innerHtml+=editbutton.domNode;
					console.log(li);
					console.log("creating list");
				    // var dialog = new Dialog({
				    // 	"title":"titlfs",
				    // 	"href":"/questionnaire/mongo/"+data.id,
				    // 	"executeScripts":"true"
				    // });
				    // dialog.show();
				    // console.log("showing dialogs") 
				})
			
		});

		on(submitButton, "click", function() {
			var isValid = true;

			sDate = new Date(startDate.get("value"));
			eDate = new Date(endDate.get("value"));

			startDate.get("value");

			console.log(eDate.toString())

			if(isValid) {
				console.log("Valid");
				var resp= {};
				resp.name = name.get("value");
				resp.description = description.get("value");
				resp.startDate = sDate.toISOString();
				resp.endDate = eDate.toISOString();

				console.log(resp);
				var jsform = domForm.toJson("expForm");

				console.log(jsform);
				xhr.post(
					"",{
						data:json.stringify(resp)
					}).then(function(data){
					respData = json.parse(data);
					if(respData.status=="ok") {
						console.log("Navigating");
						window.location.replace("");
						
					}

				});
			}
		});

		xhr.get("json").then(function(data) {
			var jsonData = json.parse(data);
			components = jsonData.components;
			console.log(components);
			for(var i =0;i<components.length;i++) {
				console.log("adding " + components[i].id);
				createComponentRow(components[i].id, 
								{name:components[i].name});
			}
		})

		//ID must be a valid component id
		//valid opts {name:name, dialog:<dialogobject>}
		function createComponentRow(id, opts) {

			var name = "";
			if(opts.name !== undefined) {
				name = opts.name;
			}else {
				name = "Unamed Form";
			}

			// if(opts.dialog === undefined) {
			// 	dialog = new dojox.widget.DialogSimple({
			// 			"title":"titlfs",
			// 			"content":"<iframe id='formframe' src=/questionnaire/mongo/"+id +"></iframe>",
			// 			"executeScripts":"true"
			// 			});
			//}
			var componentList = dom.byId("componentlist")

			var li = construct.create("li", null,componentlist,"last");
					
			var nameBox = new dijit.form.TextBox({
				id:"name:"+id,
				value:name,
				onChange: function(value){
					console.log(value +" ---- " + id);
					xhr.post("editformname", {
						data: json.stringify({"id":id, "name":value})
					}).then(function(res) {
						console.log(res);
					});
				}
			});

			var editButton = new dijit.form.Button({
			 	label:"Edit",
			 	id:"edit:"+id,
				onClick: function(){
					console.log("edit " + id);

					var dialog = new Dialog({
						"title":"titlfs",
						"content":"<iframe width='100%' height='600px'  id='formframe' src=/questionnaire/mongo/"+id +"></iframe>",
						"executeScripts":"true",
						"style":"width: 90%; height:650px;"
						});
					dialog.show();
				}});

			var deleteButton = new dijit.form.Button({
			 	label:"Delete",
			 	id:"delete:"+id,
				onClick: function(){
					console.log("delete " + id);
					construct.destroy(li);
				}});

			var newWindowButton = new dijit.form.Button({
				label:"Edit in new window",
				onClick: function() {
					window.open("/questionnaire/mongo/"+id)
				}
			})

			construct.place(nameBox.domNode, li);
			construct.place(editButton.domNode, li);
			construct.place(deleteButton.domNode,li)
			construct.place(newWindowButton.domNode, li);

		}
	});
});


