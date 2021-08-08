package hz.xhxh.algo.collection.bag;

import java.util.*;

public class LinkedBag<Item> implements Collection<Item>, Bag<Item> {
    private Node<Item> first;
    private int n;
    private long modCount = 0;

    private static class Node<Item>{
        private Item value ;
        private Node<Item> next;
    }

    public LinkedBag(){
        this.first = null;
        this.n = 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean  isEmpty() {
        return this.first == null;
    }

    @Override
    public boolean contains(Object o) {
        LinkedBag.Node<Item> x ;
        if(o == null){
            for(x = this.first; x.next != null; x = x.next){
                if(null == x.value){
                    return true;
                }
            }
        }else {
            for(x = this.first; x != null; x = x.next){
                if(o.equals(x.value)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[n];
        Iterator<Item> it = iterator();
        for(int i=0; it.hasNext(); arr[i++] = it.next());

        return arr;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Item item) {
        Node<Item> oldFirst = this.first;

        this.first = new Node<Item>();
        this.first.value = item;
        this.first.next = oldFirst;

        n++;
        modCount ++;

        return true ;
    }

    @Override
    public boolean remove(Object o) {
        //throw new UnsupportedOperationException("bag collection is not support remove element");
        if(null==first){
            throw new NoSuchElementException();
        }

        if(first.value.equals(o)){
            first = first.next;
            modCount ++;
            n --;
            return true;
        }

        var cur = first.next;
        var front = first;
        while (cur != null) {
            if (cur.value.equals(o)) {
                front.next = cur.next;
                modCount ++;
                n --;
                return true;
            }
            front = cur;
            cur = cur.next;

        }
        return false;
    }
    /*
    first = first.next
    cur.front.next = cur.next

    temp =
     */

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends Item> collection) {
        for (Item item : collection) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        removeIf(collection::contains);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        LinkedBag.Node<Item> x ;
        deleteAll(first);
        first = null;
    }

    @Override
    public String toString(){
        var cur = first;
        StringBuilder builder = new StringBuilder("[");
        while (cur !=null){
            builder.append(cur.value.toString()).append(", ");
            cur = cur.next;
        }
        builder.delete(builder.length()-2,builder.length());
        builder.append("]");
        return builder.toString();
    }

    private void deleteAll(LinkedBag.Node<Item> item){
        if(item.next != null) {
            deleteAll(item.next);
        }
        item.value = null;
        item.next = null;
        modCount ++;
        n --;
    }



    private class LinkedIterator implements Iterator<Item>{
        private Node<Item> current;
        private Node<Item> front ;
        private final long expectedModCount;
        private boolean isRemove = false;

        public LinkedIterator(){
            this.current = new Node<>();
            this.current.value = null;
            this.current.next = first;

            this.front = new Node<>();
            this.front.value = null;
            this.front.next = current;

            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            checkForModCount();
            return null != current.next;
        }

        /*
        if call next()
        return next element value it is current.next.value
        after front.next.value equals return value
         */
        @Override
        public Item next() {
            checkForModCount();
            isRemove = false;
            if (null == current.next) {
                throw new NullPointerException();
            } else {
                var v = current.next.value;
                front = current;
                current = current.next;
                return v;
            }
        }

        /*
        now will remove element is current element
        it not throws nullPointException because current.next != null
        after current.next == oldCurrent.next
         */
        @Override
        public void remove(){
            if(isRemove){
                throw new NoSuchElementException("当前元素已经删除");
            }
            //throw new UnsupportedOperationException("Bag can't delete element");
            checkForModCount();
            //delete node ,no point direct current
            front.next = current.next;
            current = front;
            n --;
        }

        public void checkForModCount(){
            if( expectedModCount != modCount){
                throw new ConcurrentModificationException();
            }
        }
    }
}
