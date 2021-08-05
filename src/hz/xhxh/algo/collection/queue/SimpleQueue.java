package hz.xhxh.algo.collection.queue;

import java.util.NoSuchElementException;

public class SimpleQueue<T> implements Queue<T>{
    private static final int MIN_SIZE = 2;

    private T[] queue ;
    private int front ;  // point first element , queue must have element this point is effective
    private int rear;   // point first empty position

    public SimpleQueue(){
        this.queue = (T[]) new Object[MIN_SIZE];
        front = 0;
        rear  = 0;
    }

    @Override
    public synchronized void enqueue(T item) {
        if((rear -front + queue.length) %queue.length == queue.length -1) resize(queue.length * 2);
        queue[(rear++)% queue.length] = item;
    }

    @Override
    public synchronized T dequeue() {
        if(isEmpty()){
            throw new NoSuchElementException("queue is empty");
        }
        if((rear -front + queue.length) % queue.length <= queue.length/4 && queue.length/2 >= MIN_SIZE) resize(queue.length /2);
        return queue[(front++) % queue.length ];
    }

    @Override
    //if rear and front point one same position the queue is empty
    public boolean isEmpty() {
        return (rear -front + queue.length ) % queue.length == 0;
    }

    @Override
    public int size() {
        return (rear - front + queue.length) % queue.length;
    }

    private void resize(int capacity){
        assert  capacity > (rear - front +queue.length) % queue.length;

        //System.out.format("resize : %d to %d , front is %d, rear is %d, length is %d%n",queue.length,capacity,front,rear, size());
        T[] copy = (T[]) new Object[capacity];
        int n = size();
        for(int i=0; i<size(); i++){
            copy[i] = queue[(front+i)% queue.length];
        }
        queue = copy;
        front = 0;
        rear = n;
    }
}
