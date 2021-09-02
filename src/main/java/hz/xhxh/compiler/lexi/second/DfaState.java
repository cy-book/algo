package hz.xhxh.compiler.lexi.second;

public enum DfaState {
    /***********************特殊状态**************************/
    /*初始状态，开始接受程序*/
    Initial,
    /*结束态，完成token解析*/
    End,
    /*错误状态*/
    Error,


    /***********************标识符**************************/

    /*标识符状态*/
    Id,

    /*分别对应着i* , in*, int* , 如果最终是 int 则会转到 Int 状态, 否则是 Id状态 */
    Id_int1, Id_int2, Id_int3,

    /*匹配到int标识符,从Id_int匹配而来*/
    Int,

    /*if else */

    /*对应着 i* , if* */
    Id_if1, Id_if2,
    If,

    /*对应着 e*, el*, els*, else* */
    Id_Else1, Id_Else2, Id_Else3,   Id_Else4,
    Else,


    /***********************表达式中的状态**************************/

    /* 判断语句 */
    /*对应 > , >=*/
    GT, GE,

    /*对应 < , <= */
    LT, LE,

    /* 四则运算 */
    Plugs,      /* 加 */
    Minus,      /* 减 */
    Star,       /* * or 乘 */
    Slash,      /* / or 除 */


    /***********************特殊符号**************************/
    Semicolon,   /* ; */
    LeftParen,   /* ( */
    RightParen,  /* ) */

    /*赋值表达式 = */
    Assignment,


    /***********************常量**************************/

    /*匹配整型字面量*/
    IntLiteral,
}
