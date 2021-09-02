package hz.xhxh.compiler.lexi.first;

import hz.xhxh.algo.collection.queue.Queue;
import hz.xhxh.algo.collection.queue.SimpleQueue;
import static hz.xhxh.compiler.lexi.first.Utilities.*;


public class SimpleLexer {
    public static void main(String[] args) {
        var lexer = new SimpleLexer();
        String program1 = "int age = 20 ";
        String program2 = "10 > 5;";
        String program3 = "age >= 14;";

        var q = lexer.parseProgram(program1);
        while (!q.isEmpty()){
            var t = q.dequeue();
            System.out.println(t.getText() + " " + t.getType());
        }
//        q = lexer.parseProgram(program2);
//        while (!q.isEmpty()){
//            var t = q.dequeue();
//            System.out.println(t.getText() + " " + t.getType());
//        }
//        q = lexer.parseProgram(program3);
//        while (!q.isEmpty()){
//            var t = q.dequeue();
//            System.out.println(t.getText() + " " + t.getType());
//        }
    }

    public Queue<Token> parseProgram(String program)  {
        var pro = program.trim();
        Queue<Token>  tokenQueue = new SimpleQueue<>();
        char ch;
        SimpleToken token = new SimpleToken();
        StringBuilder tokenText = new StringBuilder();

        for(int i=0; i<pro.length(); i++){
            ch = pro.charAt(i);

            switch (token.state()){
                case Initial:
                    initToken(token,ch);
                    tokenText.delete(0,tokenText.length()).append(ch);
                    break;
                case Id:
                    if(isDigit(ch) || isAlpha(ch)){
                        tokenText.append(ch);
                    }else if(isBlank(ch)){
                        token.update(DfaState.End);
                    }else {
                        token.update(DfaState.Error);
                    }
                    break;
                case GT:
                    if(ch == '='){
                        token.update(DfaState.End);
                        tokenText.append(ch);
                        token.setType(TokenType.GE);
                    }else {
                        token.update(DfaState.End);
                        i--;
                    }
                    break;
                case Assignment:
                    token.update(DfaState.End);
                    break;
                case Id_int1:
                    if(ch == 'n'){
                        token.update(DfaState.Id_int2);
                        tokenText.append(ch);
                    }else {
                        token.update(DfaState.Id);
                        tokenText.append(ch);
                    }
                    break;
                case Id_int2:
                    if(ch == 't'){
                        token.update(DfaState.Id_int3);
                        tokenText.append(ch);
                    }else {
                        token.update(DfaState.Id);
                        tokenText.append(ch);
                    }
                    break;
                case Id_int3:
                    if(isBlank(ch)){
                        token.update(DfaState.End);
                        token.setType(TokenType.Int);
                    }else {
                        token.update(DfaState.Id);
                        i --;
                    }
                    break;
                case IntLiteral:
                    if(isDigit(ch)){
                        tokenText.append(ch);
                    }else {
                        token.update(DfaState.End);
                        i --;
                    }
                    break;
                case End:
                    token.setText(tokenText.toString());
                    try {
                        tokenQueue.enqueue(token.clone());
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    token.update(DfaState.Initial);
                    if(!isBlank(ch)) i--;
                    //else if(isBlank(pro.charAt(i+1))) i++;

                    break;
                case Error:
                    errorToken(String.format("'%s'",tokenText.toString()));
                    System.exit(1);
            }
        }
        if(token.state() == DfaState.Error){
            errorToken(tokenText.toString());
            System.exit(1);
        }

        if(tokenText.length() >0 ) {
            token.setText(tokenText.toString());
            try {
                tokenQueue.enqueue(token.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            token.update(DfaState.Initial);
        }

        return tokenQueue;
    }


    private void initToken(SimpleToken token, char ch){
        if(isAlpha(ch)){
            if(ch == 'i'){
                token.update(DfaState.Id_int1);
            }else {
                token.update(DfaState.Id);
            }
            token.setType(TokenType.Identifier);
        }else if(isDigit(ch)){
            token.update(DfaState.IntLiteral);
            token.setType(TokenType.IntLiteral);
        }else if(isGT(ch)){
            token.update(DfaState.GT);
            token.setType(TokenType.GT);
        }else if(ch == '='){
            token.update(DfaState.Assignment);
            token.setType(TokenType.Assignment);
        }
        else {
            errorToken("init error of character '" + ch +"'" );
            token.update(DfaState.Error);
        }
    }

    private void errorToken(String msg){
        throw new IllegalArgumentException("token having error: " + msg);
    }
}

/*总结
* 在以下表达式中
* 1.   int age = 20;
* 2.   int;
*
* 1中有以下细节
*  "int " "age "
*   标识符 是以 空格判断结束的，这给解析带来了一些便利，当解析到空
*   格时，就可以确定结束了，同时在处理end时候，如果有下一个，就在
*   处理end时将指针回退到当前位置，在下一次访问语句时处理这个被占
*   用的字符。
*
*  "=" "<" "<="
*   这些运算符的结束并不是用空格判断的，所以判断其是end需要占用一个
*   处理字符的机会，处理其end需要占用一个处理字符的机会,这时就要确
*   定，指针需要回退的距离，如果和上例一样以空格结束，则回退一格就
*   行，否则需要回退两格。
*
* "20;"
*   读取整型常量 或者是 标识符 是没有确定状态的，在上述实现程序中，
*   利用了一个end标识符 标记是否结束， 代价是使用下一个位置执行的
*   机会去处理end状态。 20; 中， 分号（单字符类型）在最后（没有下
*   一个位置，无法回退）且前面是整型常量（没有确定终止状态）， 会
*   造成无法对 分号进行解析。
*
* 解决方案： 在这种 没有确定结束状态的类型遇到终止时，将要解析的语
*   句的指针提前回退，使处理end不会占用处理下一个token（尤其时单字
*   符token）的机会.
*
* 2中有以下细节
*  "int;"
*   这是一个不合法的语句，int后面跟字符，却不能构成标识符。
*
*
*
*
*
*
* */