package hz.xhxh.algo.collection;


import hz.xhxh.algo.collection.stack.SimpleStack;
import hz.xhxh.algo.collection.stack.Stack;
import org.junit.Test;

public class StackTest {
    @Test
    public void testStack(){
          var stack = getStack();
          System.out.println("test add element: [1..20]");
          for(int i=0; i<20; i++ ){
              stack.push(i);
          }
          System.out.println(stack);
          System.out.println("test pop method");
          while(!stack.isEmpty()){
              System.out.format("%d\t",stack.pop());
          }
          System.out.println("\nnow stack is empty ");
          System.out.println("loop operate");
        System.out.println("test add element: [1..20]");
        for(int i=0; i<20; i++ ){
            stack.push(i);
        }
        System.out.println(stack);
        System.out.println("test pop method");
        while(!stack.isEmpty()){
            System.out.format("%d\t",stack.pop());
        }

    }

    public Stack<Integer> getStack(){
        return new SimpleStack<>();
    }
}
