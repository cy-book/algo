package hz.xhxh.compiler.lexi.token;

public enum TokenType {
    EQ,                 /* == */
    GE,                 /* >= */
    GT,                 /* > */
    LE,                 /* <= */
    LT,                 /* < */

    Assignment,         /* = */

    Semicolon,          /* ; */
    LeftParen,          /* ( */
    RightParen,         /* ) */

    Int,                /*int 关键字*/
    Identifier,         /*标识符*/
    IntLiteral,         /*整型字面量*/
    StringLiteral,      /*字符串字面量*/
}
