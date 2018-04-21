grammar Mx;

//PARSER
//entry point
compilationUnit
    :   (classDecl|funcDecl|varDeclStat)*
        EOF
    ;

//class&function

classDecl
    :   'class' Identifier '{' classBodyDecl* '}'
    ;

classBodyDecl
    :   varDeclStat
    |   funcDecl
    |   constructorDecl
    ;

constructorDecl
    :   Identifier '(' paramDecl? ')' block
    ;

funcDecl
    :   (type|'void') Identifier '(' paramDecl? ')' block
    ;

paramDecl
    :   varDecl (',' varDecl)*
    ;

//statement

block
    :   '{' blockStat* '}'
    ;

blockStat
    :   block
    |   statement
    |   varDeclStat
    ;

statement
    :   block
    |   key='if' '(' cond=expression ')' body=statement (iselse='else' elsebody=statement)?
    |   key='for' '(' init=expression? ';' cond=expression? ';' step=exprList? ')' body=statement
    |   key='while' '(' cond=expression ')' body=statement
    |   key='return' ret=expression? ';'
    |   key='continue' ';'
    |   key='break' ';'
    |   expr=expression ';'
//    |   ';'
    ;

varDeclStat
    :   varDecl ';'
    ;

//expression

exprList
    :   expression (',' expression)*
    ;

expression
    :   primary
    |   expression op=('++'|'--')
    |   expression op='.' expression
    |   expression op='[' expression ']'
    |   expression op='(' exprList? ')'
    |   <assoc=right> op=('++'|'--') expression
    |   <assoc=right> op=('+'|'-') expression
    |   <assoc=right> op=('~'|'!') expression
    |   <assoc=right> op='new' creator
    |   expression op=('*'|'/'|'%') expression
    |   expression op=('+'|'-') expression
    |   expression op=('<<'|'>>') expression
    |   expression op=('<='|'>='|'<'|'>'|'=='|'!=') expression
    |   expression op='&' expression
    |   expression op='^' expression
    |   expression op='|' expression
    |   expression op='&&' expression
    |   expression op='||' expression
    |   <assoc=right> expression op='=' expression
//    |   expression op='?' expression ':' expression
    ;

primary
    :   Identifier
    |   literal
    |   isthis='this'
    |   '(' expression ')'
    ;

creator
    :   type (arrayInit|classInit?)
    ;

arrayInit
    :   LB expression RB (LB expression RB)* (LB RB)*
    ;

classInit
    :   '(' exprList? ')'
    ;

//basic
varDecl
    :   type Identifier ('=' expression)?
    ;

type
    : primType (LB RB)*
    | classType (LB RB)*
    ;

classType
    : Identifier
    ;

primType
    :   'bool'
    |   'int'
    |   'string'
    ;

literal
    :   BoolLiteral
    |   IntLiteral
    |   StrLiteral
    |   NullLiteral
    ;

NullLiteral
    :   'null'
    ;

BoolLiteral
    :   'true'
    |   'false'
    ;

IntLiteral
    :   DecimalLiteral
    ;

StrLiteral
    :   '"' ((EscSeq|~('"'|'\\'))+)? '"'
    ;

DecimalLiteral
    : '0'|([1-9][0-9]*) ;

Identifier
    : [a-zA-Z]([a-zA-Z0-9_])* ;

fragment
EscSeq
    : '\\'('n'|'"'|'\\') ;

LB
    : '[' ;

RB
    : ']' ;

LP
    : '(' ;

RP
    : ')' ;

LBB
    : '{' ;

RBB
    : '}' ;

COMMENT : '//'.*?'\r'?'\n' -> skip ;
NEWLINE : ('\r'?'\n')+ -> skip ;
WS : (' '|'\t'|'\n')+ -> channel(HIDDEN);