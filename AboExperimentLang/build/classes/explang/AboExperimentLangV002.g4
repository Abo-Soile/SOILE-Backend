grammar AboExperimentLangV002;

file: experimentdoc? globalDefs? phaseDefs? transitionDefs? EOF ;

/* Experiment documentation. */
experimentdoc : DOCQUOTE .*? DOCQUOTE ;

globalDefs : ( gvarDef | valDef | functionDef )+ ;

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

expr : expr '.' identifier          #ExprPropref
     | functionCall                 #ExprFcall
     | identifier '[' expr ']'      #ExprIndexing
     | identifier                   #ExprId
     | literal                      #ExprLiteral
     ;

identifier : ID ;

literal
      : string              #StringLiteral
      | number              #NumberLiteral
      | object              #ObjectLiteral
      | array               #ArrayLiteral
      | 'true'              #BooleanLiteral
      | 'false'             #BooleanLiteral
      | 'null'              #NullLiteral
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

functionCallParams : expr ( expr )* ;

object : '{' pair+ '}'
       | '{' '}' // empty object
       ;

array : '[' expr+ ']'
      | '[' ']' // empty array
      ;

pair : identifier ':' expr ;

stmt : stmtReturn 
     | stmtContinue
     | stmtBreak
     | stmtAssign 
     | stmtIf
     | stmtWhile
     | functionCall
     ;

stmtAssign : identifier '<-' expr ;

stmtContinue : 'continue' ;

stmtBreak : 'break' ;

stmtReturn : 'return' expr ;

stmtIf : 'if' expr 'then' block ('else' block)? 'end' ;

stmtWhile : 'while' expr 'do' block 'end' ;

phaseDefs : ;

transitionDefs : T_TRANSITION transitions T_END ;

transitions : firstTransition ',' otherTransitions ;

firstTransition : T_INITTRANS identifier ;

otherTransitions : transition (',' transition)* ;

transition : transitionSrc '->' transitionTo ('if' transitionCond)? ;

transitionSrc : T_TRANNAMEREPEAT    #TransitionSrcRepeat
              | ID                  #TransitionSrcId
              ;

transitionTo : ID ;

transitionCond : '(' functionCall ')'       #TransitionCondFcall
               | '(' identifier ')'         #TransitionCondVar
               ;

number : NUMBER ;

string : STRING ;

DOCQUOTE : T_QUOTE T_QUOTE T_QUOTE ;

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
fragment ID_LETTER : 'a' .. 'z' | 'A' .. 'Z' | '\u00C4' | '\u00C5' | '\u00D6' | '\u00E4' | '\u00E5' | '\u00F6' ;
fragment DIGIT : '0' .. '9' ;
