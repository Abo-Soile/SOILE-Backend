
require(["dijit/form/HorizontalSlider", 
         "dijit/form/HorizontalRuleLabels", 
         "dijit/form/HorizontalRule", 
         "dijit/form/Button", 
         "dijit/form/Select",
         "dijit/form/FilteringSelect",
         "dijit/form/ComboBox",
         "dijit/form/CheckBox",
         "dijit/form/RadioButton",
         "dijit/form/NumberSpinner",
         "dijit/form/TextBox",
         "dojo/dom",
         "dojo/dom-construct", 
         "dojo/aspect", 
         "dijit/registry", 
         "dojo/on",
         "dojo/parser",
         "dojo/query",
         "dojo/cookie",
         "dojo/json",
         "dojo/request/xhr",
         "dojo/ready"],
  function(HorizontalSlider, 
           HorizontalRuleLabels, 
           HorizontalRule, 
           Button, 
           Select,
           FilteringSelect,
           ComboBox,
           CheckBox,
           RadioButton,
           NumberSpinner,
           TextBox,
           dom,
           domConstruct, 
           aspect, 
           registry, 
           on,
           parser,
           query,
           cookie,
           JSON,
           xhr,
           ready) {

  ready(function() {
    parser.parse();

    var qdata = {};
    var testdata = {};
    var is_checked = function(id) {
      var widget = registry.byId(id);
      return (widget.get('checked') === true ? true : false);
    };

    //deprecated
    var widget_value = function(id) {
      return registry.byId(id).get('value');
    };
    var validate_widget = function(id) {
      return (registry.byId(id).validate() === true ? true : false);
    };
    var nonempty_text_widget = function(id) {
      var text = widget_value(id);
      return (text.length > 0);
    };
    var set_select_value = function(id, value, default_value) {
      return (is_checked(id) === true ? value : default_value);
    };

    //Deprecated
    var text_widget_value = function(id, maxlen) {
      var text = widget_value(id);
      if (maxlen > text.length) {
        return text;
      }
      return text.substring(0, maxlen);
    };

    var save_value = function(data, params, column){
      var id = params[0];
      qdata[column] = registry.byId(params[0]).get('value');
    };

    var save_textwidget_value = function(data, params, column) {
      var id = params[0];
      var maxlen = params[1];

      var text = registry.byId(id).get('value');
      console.log(text);
      if (maxlen > text.length) {
        text = text;
      }
      else {
        text = text.substring(0, maxlen);
      }
      qdata[column] = text;
    }

    var save_first_checked = function(data,
                                      ids, 
                                      values,
                                      column_name,
                                      default_value){
      var len = Math.min(ids.length, values.length);
      var i = 0;
      var use_default = true;
      var id, value;
      
      while (i < len){
        id = ids[i];
        if (is_checked(id)){
          use_default = false;
          value = values[i];
          break;
        }
        i += 1;
      }
      if (use_default){
        value = default_value;
      }
      qdata[column_name] = value;
    };

    var save_all_checked = function(data,
                                    ids, 
                                    values,
                                    column_names,
                                    default_value){
      var len = Math.min(ids.length, values.length, column_names.length);
      var i = 0;
      var id, value, column;

      while (i < len){
        id = ids[i];
        column = column_names[i];
        if (is_checked(id)){
          value = values[i];
        } else {
          value = default_value;
        }
        qdata[column] = value;
        i += 1;
      }
    };

    var show_saved_data = function(data){
      //Emptying div before rendering
      domConstruct.empty("formdata");
      
      for (var key in qdata){
        if (qdata.hasOwnProperty(key)){

          domConstruct.create('dt', {innerHTML: key}, 'formdata');
          domConstruct.create('dd', {innerHTML: qdata[key]}, 'formdata');
        }
      }
    };

    var send_questionnaire_data = function(data){
      console.log(JSON.stringify(data));

    }
    var loadData = function() {
      var funcArray = window.funcArray;
      qdata = {}

      for(var i = 0; i<funcArray.length; i++) {
        console.log(funcArray[i]);
        switch(funcArray[i].fun) {
          case "save_all_checked":
            // console.log("save_all_checked " + i);
            save_all_checked.apply(undefined, funcArray[i].params);
            break;
          case "save_first_checked":
            // console.log("save_first_checked " + i);
            save_first_checked.apply(undefined, funcArray[i].params);
            break;
          case "save_value":
            // console.log("save_value " + i);
            save_value.apply(undefined, funcArray[i].params);
            break;
          case "save_textwidget_value":
            // console.log("save_textwidget_value " + i);
            save_textwidget_value.apply(undefined, funcArray[i].params);
            break;
        }
      }
      return qdata;
    }
    if (dom.byId('showData')) {
      on(dom.byId('showData'), "click", function() {
        var domContainer = dom.byId("formcol");
        var widgets = registry.findWidgets(domContainer);

        var d = loadData();

        show_saved_data(d);

        //console.log("Widgets:");
        //console.log(widgets);

      });
    };
    if (dom.byId("submitButton")) {
      on(dom.byId("submitButton"), "click", function() { 
        console.log("submitbutton");

        var formdata = loadData();
        send_questionnaire_data(formdata);

        xhr.post("",{data:JSON.stringify(formdata)}).then(function(response) {
          console.log(response);
          currentPhase = parseInt(document.URL.slice(76));
          
          window.location.href = document.URL.slice(0,76)+(currentPhase+1);
          //window.location.assign("../");
        });

      });
    };
  });
});