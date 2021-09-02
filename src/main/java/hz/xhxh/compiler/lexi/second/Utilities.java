package hz.xhxh.compiler.lexi.second;

public class Utilities {
    public static boolean isAlpha(char ch){
        return (ch <= 'z' && ch >= 'a') || (ch <= 'Z' && ch >= 'A');
    }

    public static boolean isDigit(char ch){
        return (ch <='9' && ch >= '0');
    }

    public static boolean isGT(char ch){
        return ch == '>';
    }

    public static boolean isBlank(char ch){
        return ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n';
    }
}
