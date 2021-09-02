package hz.xhxh.compiler.lexi.first;

public enum DfaState {
    /*初始状态，开始接受程序*/
    Initial,
    /*结束态，完成token解析*/
    End,
    /*错误状态*/
    Error,

    /*标识符状态*/
    Id,

    /*分别对应着i* , in*, int* , 如果最终是 int 则会转到 Int 状态, 否则是 Id状态 */
    Id_int1, Id_int2, Id_int3,

    /*对应 > , >=*/
    GT, GE,

    /*赋值表达式 = */
    Assignment,

    /*匹配到int标识符,从Id_int匹配而来*/
    Int,

    /*匹配整型字面量*/
    IntLiteral,


}
