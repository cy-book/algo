package hz.xhxh.algo.calculate;

import org.junit.Test;

import java.util.Arrays;

public class InfixToPostfixTest {
    @Test
    public void testInfixToPostfix(){
        var fix = new InfixToPostfix();
        var expressions = Arrays.asList(
                "1+1",
                "5*2+1",
                "1*4+8",
                "8/2+2*3"
        );
        expressions.forEach(e->{
            fix.parse(e);
            System.out.format("infix: %s, postfix %s, value %d%n",fix.getInfixExp(),fix.getPostfixExp(),fix.getValue(e));
        });
    }
}
