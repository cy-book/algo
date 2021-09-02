package hz.xhxh.compiler.lexi.first;

import hz.xhxh.compiler.lexi.token.TokenType;

import java.io.*;

public class SimpleToken implements Token,Cloneable ,Serializable{
    private TokenType type;

    private String text;

    private DfaState current = DfaState.Initial;

    @Override
    public TokenType getType() {
        return type;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setType(TokenType type) {
        this.type = type;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean isEnd() {
        return current == DfaState.End;
    }

    @Override
    public void update(DfaState state){
        current = state;
    }

    @Override
    public DfaState state() {
        return current;
    }

    public Token clone() throws CloneNotSupportedException{
        Token clone = (Token) super.clone();
        try {
            var bout = new ByteArrayOutputStream();
            try (var out = new ObjectOutputStream(bout);) {
                out.writeObject(this);
            }
            try(var bin = new ByteArrayInputStream(bout.toByteArray())){
                var in = new ObjectInputStream(bin);
                return (Token)in.readObject();
            }

        }catch (IOException | ClassNotFoundException e){
            var e2 = new CloneNotSupportedException();
            e2.initCause(e);
            throw e2;
        }
    }

}
