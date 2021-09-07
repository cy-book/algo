package hz.xhxh.compiler.lexi.second;

public class Utilities {
    public static boolean isAlpha(char ch){
        return (ch <= 'z' && ch >= 'a') || (ch <= 'Z' && ch >= 'A');
    }

    public static boolean isDigit(char ch){
        return (ch <='9' && ch >= '0');
    }

    public static boolean isJudge(char ch){
        return '=' == ch || '<' == ch || ch == '>';
    }

    public static boolean isOperator(char ch){
        return '+' == ch || '-' == ch || '*' == ch || '/' == ch;
    }

    public static boolean isSpecial(char ch){
        switch (ch){
            case '(':
            case ')':
            case ';':
                return true;
            default:
                return false;
        }
    }

    public static boolean isBlank(char ch){
        return ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n';
    }
}
