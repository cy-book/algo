package hz.xhxh.compiler.lexi.second.token;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class SimpleTokenTest extends TestCase {
    public void testToken(){
        Token token = new SimpleToken();
        token.setText("token");
        token.setType(TokenType.Identifier);
        System.out.println(token);
    }

}