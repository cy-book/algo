package hz.xhxh.compiler.lexi.token;

public interface TokenReader {
    /*
    * @return 从token流中读取一个token,并取出。 如果流为空，则返回null
    * */
    Token read();

    /*
    * @return 从token流中读取一个token,不取出。 如果流为空，则返回null.
    * */
    Token peek();

    /*
    * token流回退一步。恢复原来的token
    * */
    void unread();

    /*
    * @return 返回当前token流所读取的位置
    * */
    int getPosition();

    /*
    * @param position
    * 设置token流当前读取的位置
    * */
    void setPosition(int position);
}
