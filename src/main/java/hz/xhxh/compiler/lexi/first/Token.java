package hz.xhxh.compiler.lexi.first;

import hz.xhxh.compiler.lexi.token.TokenType;

public interface Token {
    /*
    * @return token的类型
    * */
    TokenType getType();

    /*
    * @return token的文本值
    * */
    String getText();

    /*
    * @param type
    * 更新token的类型
    * */
    void setType(TokenType type);

    /*
    * @param text
    * 更新token的文本值
    * */
    void setText(String text);

    /*
    * @return 如果已经完成对当前token的解析，则返回true。否则返回false。
    * */
    boolean isEnd();

    /*
    * @param state
    * 更新当前dfa 的状态，即token被解析到什么地步了
    * */
    void update(DfaState state);

    DfaState state();

}
