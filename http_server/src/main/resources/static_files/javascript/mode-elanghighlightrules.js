/*
http://ace.c9.io/tool/mode_creator.html
Based on ABAP syntax highlighter

see 
http://plnkr.co/edit/MuzIWp?p=preview
 */

 window.define = window.define || ace.define;

define("ace/mode/elanghighlightrules", ["require","exports","module","ace/lib/oop","ace/mode/text_highlight_rules"],function(require, exports, module) {
"use strict";

var oop = require("ace/lib/oop");
var TextHighlightRules = require("ace/mode/text_highlight_rules").TextHighlightRules;

var ElangHighlightRules = function() {

    console.log("HIGHLIGHTNING")

    var keywordMapper = this.createKeywordMapper({
        "variable.language": "wait starttimer hidemsg",
        "keyword": 
            "function end intermezzo-phase interaction-phase "+ 
            "if then else enterphase leavephase beforeiteration afteriteration "+
            "transition intermezzo-phase iteration while do",
        "constant.language": 
            "TRUE FALSE NULL SPACE",
        "support.type": 
            "gvar var val",
        "keyword.operator":
            "plus minus muliply divide" 
    }, "text", true, " ");

    var varkeywords = "var|gvar|val"
    var compoundKeywords = "aaassdasda"


    var func = "function|intermezzo-phase|interaction-phase"
    
     
    this.$rules = {
        "start" : [
            {token : "string", regex : '"', next  : "string"},
            {token : "doc.comment", regex : "#.*$"},
            {token : "keyword.operator", regex: /\W[\-+\%=<>*]\W|\*\*|[~:,\.&$]|->*?|=>/},
            {token : "paren.lparen", regex : "[\\[({]"},
            {token : "paren.rparen", regex : "[\\])}]"},
            {token : "support.function", regex: func},
            {token : "constant.numeric", regex: "[+-]?\\d+\\b"},
            {token : "keyword", regex : compoundKeywords}, 
            {token : "variable.parameter", regex : varkeywords}, 
            {token : keywordMapper, regex : "\\b\\w+\\b"},
            {caseInsensitive: true}
        ],
        "string" : [
            {token : "constant.language.escape",   regex : '""'},
            {token : "string", regex : '"',     next  : "start"},
            {defaultToken : "string"}
        ]
    }
};
oop.inherits(ElangHighlightRules, TextHighlightRules);

console.log("EXPORTED")
exports.ElangHighlightRules = ElangHighlightRules;
});