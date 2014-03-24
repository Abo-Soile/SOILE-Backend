grammar TypeSpec;

@header {
package fi.abo.kogni.soile2.qmarkup.typespec;
}

spec: command+ ;

command: 'command' commandname body ;

commandname: ID ;
body: '{' def ( ',' def )* '}' ;

object: '{' def ( ',' def )* '}' ;

def: field ':' type ;

field: ID ;

type
   : simple_type
   | compound_type
   ;

simple_type
          : integer_type
          | float_type
          | string_type
          | boolean_type	
          ;

compound_type
            : array_type
            | repeat_type
            | object_type
            ;
           
integer_type: TYPE_INTEGER  ( '(' INTEGER ')' )? ;
float_type: TYPE_FLOAT  ( '(' FLOAT ')' )? ;
string_type:  TYPE_STRING   ( '(' STRING ')' )? ;
boolean_type: TYPE_BOOLEAN  ( '(' BOOLEAN ')' )? ;
array_type: '[' type (',' type)* ']' ;
repeat_type: '[' type '*' ']' ;
object_type: object ;

TYPE_INTEGER: 'Integer' ;
TYPE_FLOAT: 'Float' ;
TYPE_STRING: 'String' ;
TYPE_BOOLEAN: 'Boolean' ;
BOOLEAN: 'true' | 'false' ;
INTEGER: '-'? INT ;
FLOAT: DIGIT+ '.' DIGIT* | DIGIT+  ;
//FLOAT: STRING  ;
STRING: '"' [a-zA-Z0-9 _]* '"' ;
REQUIRED: 'required' ;
ID: [a-zA-Z_] [a-zA-Z0-9_]* ;

fragment INT: '0' | [1-9] [0-9]* ;
fragment DIGIT: [0-9] ;

WS: [ \t\n\r]+ -> skip ;

