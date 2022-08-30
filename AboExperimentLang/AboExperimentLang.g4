grammar AboExperimentLang;

file: experimentdoc? globalDefs? phasesDef? transitionDef? EOF ;

/* Experiment documentation. */
experimentdoc : DOCQUOTE .*? DOCQUOTE ;

globalDefs : ( gvarDef | valDef | functionDef )+ ;

gvarDef : 'gvar' ID '<-' gvarValue ';' ;

valDef : 'val' ID '<-' valValue ';' ;

functionDef : 'function' ID '(' functionDefParams? ')' functionBody 'end' ;

functionDefParams : functionDefParam (',' functionDefParam)* ;

functionDefParam : ID ;

functionBody : block ;

block : stmt+ ;

gvarValue : jsValue
          | functioncall
          | mapValue
          | ID
          ;

valValue : jsValue
         | mapValue
         ;

expr : '(' expr ')'
       
     // Keep '!' expression above 'arithmOperator' and 'booleanOperator'.
     | '!' expr
     | expr arithmOperator expr 
     | expr booleanOperator expr
     | expr '=' expr
     | functioncall 
     | ID
     | jsValue
     ;

jsValue
      : STRING
      | NUMBER
      | object  
      | array
      | booleanValue
      | 'null'
      ;

functioncall : ID '(' functioncallParams? ')' ;

functioncallParams : expr (',' expr)* ;

object : '{' pair (',' pair)* '}'
       | '{' '}' // empty object
       ;

array : '[' jsValue (',' jsValue)* ']'
      | '[' ']' // empty array
      ;

pair:   ID ':' expr ;

booleanValue : 'true' | 'false' ;

mapValue : 'Map' '[' mapValueDef (',' mapValueDef)* ',' mapValueDefault ']' ;

mapValueDef : STRING '->' jsValue
            | NUMBER '->' jsValue
            ;

mapValueDefault : '?' '->' jsValue ;

arithmOperator : '+'
               | '-' 
               | '*'
               | '/'
               ;

booleanOperator : 'and' 
                | 'or'
                ;

stmt : (stmtReturn | stmtAssign | functioncall) ';' 
     | stmtIf
     | ';' // Empty statement.
     ;

stmtAssign : ID '<-' expr ;

stmtReturn : 'return' expr ;

stmtIf : 'if' '(' expr ')' block ('else' block)? 'end'
       ;

phasesDef : ;

transitionDef : ;

DOCQUOTE : '"""' ;

ID : ID_LETTER ( ID_LETTER | DIGIT | SCAND )* ;

/*
NL : '\r'? '\n' ;
*/

NUMBER : '-'? DIGIT+ ;

STRING :  '"' (ESC | ~["\\])* '"' ;

WS : [ \r\t\u000C\n]+ -> channel(HIDDEN) ;

fragment ESC :   '\\' ["\\] ;
fragment ID_LETTER : 'a' .. 'z' | 'A' .. 'Z' | '_' ;
fragment DIGIT : '0' .. '9' ;

/* Scandinavian characters (åÅöÖäÄ). */
fragment SCAND : '\u00C4' | '\u00C5' | '\u00D6' | '\u00E4' | '\u00E5' | '\u00F6' ;
