define(["dojo/_base/declare", "dojo/_base/lang", "dijit/form/SimpleTextarea", "dijit/form/ValidationTextBox"],
  function(declare, lang, SimpleTextarea, ValidationTextBox) {

    return declare('dijit.form.ValidationTextArea', [SimpleTextarea, ValidationTextBox], {
      constructor: function(params){
        this.constraints = {};
        this.baseClass += ' dijitValidationTextArea';
      },    
      templateString: "<textarea ${!nameAttrSetting} data-dojo-attach-point='focusNode,containerNode,textbox' autocomplete='off'></textarea>"
    })
  })