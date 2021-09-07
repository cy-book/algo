package hz.xhxh.compiler.lexi.second.token;


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
     * @param str
     * 更新token的文本值
     * */
    void append(String str);

    void append(char ch);

    /*
    * @param str 从token中剪去最后一个字节
    * */
    void delete();
}
