package hz.xhxh.algo.collection.stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;

public class SimpleStack<T> implements Stack<T>{
    private static final int MIN_SIZE = 2;

    private T[] stack;

    private int top;
    public SimpleStack()
    {
        stack = (T[])new Object[MIN_SIZE];
        top = -1;
    }

    @Override
    public synchronized T pop()
    {
        if(top <0){
            throw new EmptyStackException();
        }
        var value =  stack[top--];
        if(top+1 < stack.length/4 && stack.length/2 >MIN_SIZE )resize(stack.length/2);
        return value;
    }
    @Override
    public synchronized T peek()
    {
        if(top < 0 ){
            throw new EmptyStackException();
        }
        return stack[top];
    }

    @Override
    public synchronized void push(T t)
    {
        if(top+1 == stack.length) resize(stack.length *2);
        stack[++top] = t;
    }

    @Override
    public boolean isEmpty()
    {
        return -1 == top;
    }

    @Override
    public int size(){
        return top + 1;
    }

    private void resize(int capacity){
        assert capacity >= top+1;

        T[] copy = (T[]) new Object[capacity];
        System.arraycopy(stack,0,copy,0,top+1);
        stack = copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T>{
        private int t;

        public StackIterator(){
            this.t = SimpleStack.this.top;
        }

        @Override
        public boolean hasNext() {
            return t >= 0;
        }

        @Override
        public T next() {
            return stack[t--];
        }
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("[");
        for(int i=0; i<=top; i++){
            builder.append(stack[i]).append(", ");
        }
        builder.delete(builder.length()-2,builder.length());
        builder.append("]");
        return builder.toString();
    }
}
