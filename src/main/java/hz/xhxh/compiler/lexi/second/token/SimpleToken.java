package hz.xhxh.compiler.lexi.second.token;

public class SimpleToken implements Token{
    private TokenType type;
    private final StringBuilder text;

    public SimpleToken(){
        this.text = new StringBuilder();
        this.type = TokenType.Init;
    }

    @Override
    public TokenType getType() {
        return type;
    }

    @Override
    public String getText() {
        return text.toString();
    }

    @Override
    public void setType(TokenType type) {
        this.type = type;
    }

    @Override
    public void append(String ch) {
        text.append(ch);
    }
    @Override
    public void append(char ch){
        text.append(ch);
    }

    @Override
    public void delete() {
        text.deleteCharAt(text.length()-1);
    }

    @Override
    public String toString(){
        return text + "\t\t" + type;
    }
}
