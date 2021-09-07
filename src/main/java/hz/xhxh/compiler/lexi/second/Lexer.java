package hz.xhxh.compiler.lexi.second;

import hz.xhxh.algo.collection.queue.Queue;
import hz.xhxh.algo.collection.queue.SimpleQueue;
import hz.xhxh.algo.collection.st.BST;
import hz.xhxh.algo.collection.st.ST;
import hz.xhxh.compiler.lexi.second.token.SimpleToken;
import hz.xhxh.compiler.lexi.second.token.Token;
import hz.xhxh.compiler.lexi.second.token.TokenType;

import static hz.xhxh.compiler.lexi.second.Utilities.*;

/*词法分析器
 * 解析出 标识符、运算符、括号
 * 约定 ： token之间都以空格分割
 * */
public class Lexer {

    private static class Node{
        private Token token;

        public void append(char ch){
            token.append(ch);
        }
    }

    public Lexer() {
    }

    private Queue<Token> tokenize(String code) {
        return tokenize(code.trim().toCharArray());
    }

    private Queue<Token> tokenize(char[] code) {
        Queue<Token> tokenQueue = new SimpleQueue<>();
        Node node = new Node();
        int len = code.length;
        DfaState state = DfaState.Initial;
        for (char ch : code) {
            switch (state) {
                case Initial:
                    state = initToken(ch, node);
                    break;
                case Blank:
                    endToken(state, node, tokenQueue);
                    state = DfaState.Initial;
                    break;
                case Id: //id的token必须以空白字符结尾
                    if (isAlpha(ch) || isDigit(ch)) {
                        node.append(ch);
                    } else {
                        state = DfaState.Error;
                    }
                    break;
                case GT: //gt的token的结尾可以是任何字符
                    if ('=' == ch) {
                        state = DfaState.GE;
                    } else {
                        endToken(state, node, tokenQueue);
                        state = initToken(ch, node);
                    }
                    break;
                case LT:
                    if ('=' == ch) {
                        state = DfaState.LE;
                    } else {
                        endToken(state, node, tokenQueue);
                        state = initToken(ch, node);
                    }
                    break;
                case Assignment:
                    if ('=' == ch) {
                        state = DfaState.EQ;
                    } else {
                        endToken(state, node, tokenQueue);
                        state = initToken(ch, node);
                    }
                    break;

                //终结符
                case GE:
                case LE:
                case EQ:
                case Plus:
                case Minus:
                case Star:
                case Slash:
                case Semicolon:
                case LeftParen:
                case RightParen:
                    endToken(state, node, tokenQueue);
                    state = initToken(ch, node);
                    break;
                case IntLiteral:
                    if (isDigit(ch)) {
                        node.token.append(ch);
                    } else {
                        endToken(state, node, tokenQueue);
                        state = initToken(ch, node);
                    }
                case Id_int1:
                case Id_int2:
                case Id_int3:

            }
        }
        return tokenQueue;
    }

    private TokenType typeMap(DfaState state){
        return TokenType.Init;
    }


    private void endToken(DfaState state,Node x, Queue<Token> tokenQueue){
        TokenType type = typeMap(state);
        if(type != TokenType.Error) {
            if(type != TokenType.BLANK) {
                x.token.setType(type);
                tokenQueue.enqueue(x.token);
            }
        }
    }

    /*对于给定的字符串，确定有限自动机的初始状态
     * 初始化token的类型
     * */
    private DfaState initToken(char ch,Node x) {
        DfaState newState;
        Token token = new SimpleToken();

        if (isAlpha(ch)) {
            newState = initAlpha(ch,token);
        } else if (isDigit(ch)) {
            newState = initDight(ch,token);
        } else if (isOperator(ch)) {
            newState = initOperator(ch,token);
        } else if (isJudge(ch)) {
            newState = initJudge(ch,token);
        } else if (isSpecial(ch)) {
            newState = initSpecial(ch,token);
        } else if (isBlank(ch)) {
            newState = initBlank();
        } else {
            newState = DfaState.Error;
        }
        x.token = token;

        return newState;

    }

    private DfaState initAlpha(char ch,Token token) {
        DfaState state = DfaState.Initial;
        if ('i' == ch) {
            state = DfaState.Id_int1;
        } else {
            state = DfaState.Id;
        }
        token.append(ch);

        return state;
    }

    private DfaState initDight(char ch,Token token) {
        DfaState state = DfaState.IntLiteral;
        token.append(ch);

        return state;
    }

    private DfaState initOperator(char ch,Token token) {
        DfaState state = DfaState.Initial;
        switch (ch) {
            case '+':
                state = DfaState.Plus;
                break;
            case '-':
                state = DfaState.Minus;
                break;
            case '*':
                state = DfaState.Star;
                break;
            case '/':
                state = DfaState.Slash;
                break;
        }

        token.append(ch);

        return state;
    }

    private DfaState initJudge(char ch,Token token) {
        DfaState state = DfaState.Initial;
        switch (ch) {
            case '>':
                state = DfaState.GT;
                break;
            case '<':
                state = DfaState.LT;
                break;
            case '=':
                state = DfaState.Assignment;
                break;
        }
        token.append(ch);

        return state;
    }

    private DfaState initSpecial(char ch,Token token) {
        DfaState state = DfaState.Initial;
        switch (ch) {
            case '(':
                state = DfaState.LeftParen;
                break;
            case ')':
                state = DfaState.RightParen;
                break;
            case ';':
                state = DfaState.Semicolon;
                break;
        }

        token.append(ch);

        return state;
    }

    private DfaState initBlank() {
        return DfaState.Blank;
    }

    private void tokenError(char ch) {
        throw new IllegalStateException("token start char: " + ch);
    }

}
