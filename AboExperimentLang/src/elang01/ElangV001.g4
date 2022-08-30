grammar ElangV001;

file: toplevelStmt* iteration EOF ;

toplevelStmt : ( gvarDef | valDef | functionDef ) ;

gvarDef : 'gvar' identifier '<-' expr ;

valDef : 'val' identifier '<-' expr ;

functionDef : 'function' identifier '(' functionDefParams? ')' functionBody 'end' ;

functionDefParams : functionDefParam (functionDefParam)* ;

functionDefParam : ID ;

functionBody : fvarDefs? varDefs? block ;

fvarDefs : fvarDef+ ;

fvarDef : 'fvar' identifier '<-' expr ;

varDefs : varDef+ ;

varDef : 'var' identifier '<-' expr ;

block : stmt+ ;

expr : expr '.' ID                  #ExprPropref
     | functionCall                 #ExprFcall
     | identifier '[' expr ']'      #ExprIndexing
     | identifier                   #ExprId
     | literal                      #ExprLiteral
     ;

identifier : ID ;

literal : string              #StringLiteral
        | number              #NumberLiteral
        | object              #ObjectLiteral
        | array               #ArrayLiteral
        | 'true'              #BooleanLiteral
        | 'false'             #BooleanLiteral
        | 'null'              #NullLiteral
        ;

functionCall : functionIdentifier '(' functionCallParams? ')' ;

functionIdentifier : identifier ;

functionCallParams : expr ( expr )* ;

object : '{' pair+ '}'
       | '{' '}' // empty object
       ;

array : '[' expr+ ']'
      | '[' ']' // empty array
      ;

pair : ID ':' expr ;

stmt : stmtReturn           #ReturnStatement
     | stmtContinue         #ContinueStatement
     | stmtBreak            #BreakStatement
     | stmtAssign           #AssignStatement
     | stmtIfElse           #IfElseStatement
     | stmtIf               #IfStatement
     | stmtWhile            #WhileStatement
     | functionCall         #FunctionCallStatement
     ;

stmtAssign : identifier '<-' expr ;

stmtContinue : 'continue' ;

stmtBreak : 'break' ;

stmtReturn : 'return' expr ;

stmtIfElse : 'if' expr 'then' block 'else' block 'end' ;

stmtIf : 'if' expr 'then' block 'end' ;

stmtWhile : 'while' expr 'do' block 'end' ;

iteration : T_ITERATION iterationBody T_END ;

iterationBody : varDefs? block ;

number : NUMBER ;

string : STRING ;

DOCQUOTE : T_QUOTE T_QUOTE T_QUOTE ;

// Special Function Names (SFN).
SFN_PLUS : '+' ;
SFN_MINUS : '-' ;
SFN_MULTIPLY : '*' ;
SFN_DIVIDE : '/' ;
SFN_LESSTHAN : '<' ;
SFN_GREATERTHAN : '>' ;
SFN_EQUAL : '=' ;
SFN_NEGATE : '!' ;

// Tokens.

T_TRANNAMEREPEAT : '-"-' ;          // Repeat the source transition name.
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
T_HASH : '#' ;
T_IF : 'if' ;
T_INITTRANS : '|->' ;
T_ITERATION : 'iteration';
T_NULL : 'null' ;
T_PARENLEFT : '(' ;
T_PARENRIGHT : ')' ;
T_QMARK : '?' ;
T_QUOTE : '"' ;
T_RETURN : 'return' ;
T_THEN : 'then' ;
T_TRANSITION : 'transition' ;
T_TRUE : 'true' ;
T_VAL : 'val' ;
T_VAR : 'var' ;
T_WHILE : 'while' ;

ID : ID_LETTER ( ID_CHAR | DIGIT )* ;

NUMBER : '-'? DIGIT+ ;

STRING :  '"' (ESC | ~["\\])* '"' ;

COMMENT : T_HASH .*? NL -> skip ;

WS : [ \r\t\u000C\n]+ -> channel(HIDDEN) ;

NL : '\r'? '\n' ;

fragment ESC :   '\\' ["\\] ;
fragment ID_CHAR : ID_LETTER | '_' ;
fragment ID_LETTER : 'a' .. 'z' | 'A' .. 'Z' ;
fragment DIGIT : '0' .. '9' ;
