package hz.xhxh.algo.calculate;

import hz.xhxh.algo.collection.stack.Stack;
import hz.xhxh.algo.collection.stack.SimpleStack;

public class InfixToPostfix {
    private String infixExp;
    private String postfixExp;
    private int value;

    public InfixToPostfix() {
    }

//    public ValueOfExpression parseExpression(String exp)
//    {
//        parse(exp);
//
//        return new ValueOfExpression(infixExp,postfixExp,value);
//    }

    public int getValue(String exp) {
        return value;
    }

    public String getInfixExp() {
        return infixExp;
    }

    public String getPostfixExp() {
        return postfixExp;
    }

    public void parse(String exp) {
        infixExp = exp;
        postfixExp = parseInfix();
        value = solution();

    }

    private int solution() {
        char[] postfix = postfixExp.toCharArray();
        int len = postfix.length;
        Stack<Integer> stack = new SimpleStack<Integer>();

        char op;
        for (int i = 0; i < len; i++) {
            op = postfix[i];
            if (isOperand(op)) {
                stack.push(getIntger(postfix, i));
            } else if (isOperator(op)) {
                int numb = stack.pop();
                int nump = stack.pop();

                switch (op) {
                    case '+':
                        stack.push(nump + numb);
                        break;
                    case '-':
                        stack.push(nump - numb);
                        break;
                    case '*':
                        stack.push(nump * numb);
                        break;
                    case '/':
                        stack.push(nump / numb);
                        break;
                    default:
                }
            }
        }

        return stack.pop();

    }

    private int getIntger(char[] exp, int index) {
        return exp[index] - '0';
    }

    private String parseInfix() {
        int len = infixExp.length();
        char[] expre = infixExp.toCharArray();
        StringBuffer postfix = new StringBuffer();
        SimpleStack<Character> stack = new SimpleStack<>();

        for (int i = 0; i < len; i++) {
            if (isOperand(expre[i])) {
                postfix.append(expre[i]);
            } else if (isOperator(expre[i])) {
                if (expre[i] == '(') stack.push(expre[i]);
                else if (stack.isEmpty() || curPrec(expre[i]) > topPrec(stack.peek())) {
                    stack.push(expre[i]);
                } else {
                    while (!stack.isEmpty() && curPrec(expre[i]) < topPrec(stack.peek())) {
                        postfix.append(stack.pop());
                    }
                    stack.push(expre[i]);
                }
            } else if (expre[i] == ')') {
                while (stack.peek() != '(') {
                    postfix.append(stack.pop());

                    if (stack.isEmpty()) {
                        System.out.printf("Wrong inpu\n");
                        System.exit(1);
                    }
                }

                stack.pop();

            }

        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                System.out.printf("Wrong inpu\n");
                System.exit(1);
            }
            postfix.append(stack.pop());
        }

        System.out.println();

        return postfix.toString();

    }

    private int topPrec(char op) {
        switch (op) {
            case '+':
            case '-':
                return 2;
            case '*':
            case '/':
                return 4;
            case '(':
                return 0;
        }
        return 0;
    }

    private int curPrec(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 3;
            case ')':
                return 100;
        }
        return 0;
    }

    private boolean isOperator(char op) {
        switch (op) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '(':
                return true;
        }
        return false;
    }

    private boolean isOperand(char op) {
        if (op <= '9' && op >= '0') return true;
        return false;

    }

}
