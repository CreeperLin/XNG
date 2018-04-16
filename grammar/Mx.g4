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
    |   key='for' '('  init=varDecl? ';' cond=expression? ';' step=exprList? ')' body=statement
    |   key='while' '(' cond=expression ')' body=statement
    |   key='return' ret=expression? ';'
    |   key='continue' ';'
    |   key='break' ';'
    |   expression ';'
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
    |   <assoc=right> op=('+'|'-'|'++'|'--') expression
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
    :   type (arrayInit|classInit)
    ;

arrayInit
    :   LB expression RB (LB expression RB)* (LB RB)*
    ;

classInit
    :   '(' exprList? ')'
    ;
//basic
//
//varDecls
//    :   type varDecl (',' varDecl)*
//    ;

varDecl
    :   type Identifier ('=' expression)?
    ;

type
    : primType (LB RB)*
    | classType (LB RB)*
    ;

classType
    : Identifier ('.' Identifier)*
    ;

primType
    :   'bool'
    |   'int'
    |   'string'
    ;

literal
    :   boolLiteral
    |   intLiteral
    |   strLiteral
    |   nullLiteral
    ;

nullLiteral
    :   'null'
    ;

boolLiteral
    :   'true'
    |   'false'
    ;

intLiteral
    :   DecimalLiteral
    ;

strLiteral
    :   '"'(EscSeq|~('\\'|'"'))*'"'
    ;
//LEXER

DecimalLiteral
    : '0'|('-'?[1-9][0-9]*) ;

Identifier
    : [a-zA-Z]([a-zA-Z0-9_])* ;

EscSeq
    : '\\'('t'|'n'|'"'|'\\'|'\'') ;

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
WS : (' '|'\t'|'\n')+ -> skip ;