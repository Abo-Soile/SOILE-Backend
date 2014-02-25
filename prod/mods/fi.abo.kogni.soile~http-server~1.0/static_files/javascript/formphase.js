
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
           ready) {

  ready(function() {
    parser.parse();

    on(dom.byId('submitButton'), "click", function() {
      window.r = registry;

      var domContainer = dom.byId("formcol");
      var widgets = registry.findWidgets(domContainer);

      console.log("Widgets:");
      console.log(widgets);
      
    });
  });
});
