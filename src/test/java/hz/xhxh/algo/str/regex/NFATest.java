package hz.xhxh.algo.str.regex;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class NFATest extends TestCase {
    public void testNFA(){
            String regexp = "(" + "(A*B|AC)D" + ")";
            String txt = "AAAABD";
            NFA nfa = new NFA(regexp);
            System.out.println(nfa.recognizes(txt));
    }

}