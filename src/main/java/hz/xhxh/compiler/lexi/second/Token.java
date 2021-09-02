package hz.xhxh.compiler.lexi.second;

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
}
