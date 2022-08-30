grammar DemoLangV001;

script: globalDefs? iteration EOF ;

iteration : 'iteration' block 'end' ;

globalDefs : ( gvarDef | functionDef )+ ;

gvarDef : 'gvar' identifier '<-' expr ;

functionDef : 'function' identifier '(' functionDefParams? ')' functionBody 'end' ;

functionDefParams : functionDefParam (functionDefParam)* ;

functionDefParam : ID ;

functionBody : block ;

block : stmt+ ;

expr : functionCall                 #ExprFcall
     | identifier '[' expr ']'      #ExprIndexing
     | identifier '.' identifier    #ExprPropref
     | identifier                   #ExprId
     | literal                      #ExprLiteral
     ;

identifier : ID ;

literal
      : STRING          #StringLiteral
      | NUMBER          #NumberLiteral
      | object          #ObjectLiteral
      | array           #ArrayLiteral
      | booleanValue    #BooleanLiteral
      | 'null'          #NullLiteral
      ;

functionCall : functionIdentifier '(' functionCallParams? ')' ;

functionIdentifier : '1+'       #SpecialFID
                   | '1-'       #SpecialFID
                   | '+'        #SpecialFID
                   | '-'        #SpecialFID
                   | '*'        #SpecialFID
                   | '/'        #SpecialFID
                   | '<'        #SpecialFID
                   | '>'        #SpecialFID
                   | '='        #SpecialFID
                   | '!'        #SpecialFID
                   | ID         #RegularFID
                   ;

functionCallParams : expr (expr)* ;

object : '{' pair (',' pair)* '}'
       | '{' '}' // empty object
       ;

array : '[' literal (literal)* ']'
      | '[' ']' // empty array
      ;

pair : identifier ':' expr ;

booleanValue : 'true' | 'false' ;

stmt : stmtReturn 
     | stmtContinue
     | stmtBreak
     | stmtVarDef 
     | stmtAssign 
     | stmtIf
     | stmtWhile
     | functionCall
     ;

stmtVarDef : 'var' identifier '<-' expr ;

stmtAssign : identifier '<-' expr ;

stmtContinue : 'continue' ;

stmtBreak : 'break' ;

stmtReturn : 'return' expr ;

stmtIf : 'if' expr 'then' block ('else' block)? 'end' ;

stmtWhile : 'while' expr 'do' block 'end' ;

// Special Function Names (SFN).
SFN_ONEPLUS : '1+' ;
SFN_ONEMINUS : '1-' ;
SFN_PLUS : '+' ;
SFN_MINUS : '-' ;
SFN_MULTIPLY : '*' ;
SFN_DIVIDE : '/' ;
SFN_LESSTHAN : '<' ;
SFN_GREATERTHAN : '>' ;
SFN_EQUAL : '=' ;
SFN_NEGATE : '!' ;

// Tokens.

T_ARROWLEFT : '<-' ;
T_ARROWRIGHT : '->' ;
T_BRACELEFT : '{' ;
T_BRACERIGHT : '}' ;
T_BRACKETLEFT : '[' ;
T_BRACKETRIGHT : ']' ;
T_BREAK : 'break' ;
T_COLON : ':' ;
T_COMMA : ',' ;
T_CONTINUE : 'continue' ;
T_DO : 'do' ;
T_DOT : '.' ;
T_ELSE : 'else' ;
T_END : 'end' ;
T_FALSE : 'false' ;
T_FUNCTION : 'function' ;
T_FVAR : 'fvar' ;
T_GVAR : 'gvar' ;
T_IF : 'if' ;
T_ITERATION : 'iteration' ;
T_MAP : 'Map' ;
//T_MINUS : '-';
T_NULL : 'null' ;
T_PARENLEFT : '(' ;
T_PARENRIGHT : ')' ;
T_QMARK : '?' ;
T_RETURN : 'return' ;
T_THEN : 'then' ;
T_TRUE : 'true' ;
T_VAL : 'val' ;
T_VAR : 'var' ;
T_WHILE : 'while' ;

ID : ID_LETTER ( ID_CHAR | DIGIT )* ;

NUMBER : '-'? DIGIT+ ;

STRING :  '"' (ESC | ~["\\])* '"' ;

WS : [ \r\t\u000C\n]+ -> channel(HIDDEN) ;

fragment ESC :   '\\' ["\\] ;
fragment ID_CHAR : ID_LETTER | '_' ;
fragment ID_LETTER : 'a' .. 'z' | 'A' .. 'Z' | '\u00C4' | '\u00C5' | '\u00D6' | '\u00E4' | '\u00E5' | '\u00F6' ;
fragment DIGIT : '0' .. '9' ;
