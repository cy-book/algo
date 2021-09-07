package hz.xhxh.compiler.lexi.second.token;

public enum TokenType {
    Init,               /*未定义的token*/
    Error,
    BLANK,

    EQ,                 /* == */
    GE,                 /* >= */
    GT,                 /* > */
    LE,                 /* <= */
    LT,                 /* < */

    Plus,
    Minus,
    Star,
    Slash,

    Assignment,         /* = */

    Semicolon,          /* ; */
    LeftParen,          /* ( */
    RightParen,         /* ) */

    Int,                /*int 关键字*/
    Identifier,         /*标识符*/
    IntLiteral,         /*整型字面量*/
    StringLiteral,      /*字符串字面量*/
}
