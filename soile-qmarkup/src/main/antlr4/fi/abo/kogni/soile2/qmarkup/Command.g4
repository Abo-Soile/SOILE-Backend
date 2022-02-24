grammar Command;

command: object;

object: '{' pair (',' pair)* '}' ;

pair: name ':' value ;

name
   : string		# QString	// Quoted string.
   | NQSTRING		# NQString	// Non-quoted string.
   ;

value
    : string		# VString
    | integer		# VInteger
    | object		# VObject
    | array		# VArray
    | bool		# VBool
    ;

array: '[' value (',' value)* ']' ;

string: STRING ;

integer
      : ZERO
      | NONZERO 
      ;

bool
   : 'true' 
   | 'false' 
   ;

ZERO: '0' ;
NONZERO: '-'? [1-9] [0-9]* ; 

// Non-quoted string
NQSTRING : [a-zA-Z_] [a-zA-Z0-9_]* ;

STRING: '"' (ESC | .)*? '"' ;
fragment ESC: '\\"' | '\\\\' ;

WS  :   [ \t\n\r]+ -> skip ;
