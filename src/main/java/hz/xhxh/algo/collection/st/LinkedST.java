package hz.xhxh.algo.collection.st;

import hz.xhxh.algo.collection.bag.Bag;
import hz.xhxh.algo.collection.bag.SimpleBag;
import java.util.NoSuchElementException;

/*
 * 采用链表结构来实现符号表，通过遍历链表查找至
 * 优点： 插入时方便
 * 缺点： 查找需要遍历链表，插入，删除等操作需要查找为前提
 * */
public class LinkedST<K, V> implements ST<K, V> {
    private Node first;
    private int n;

    @Override
    public void put(K k, V v) {
        for (Node cur = first; cur != null; cur = cur.next) {
            if (cur.key.equals(k)) {
                cur.value = v;
                n += 1;
                return;
            }
        }
        first = new Node(k, v, first);
    }

    @Override
    public V get(K k) {
        for (Node cur = first; cur != null; cur = cur.next) {
            if (cur.key.equals(k)) return cur.value;
        }
        return null;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean contains(K k) {
        for (Node cur = first; cur != null; cur = cur.next) {
            if (cur.key.equals(k)) return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return 0 == n;
    }

    @Override
    public void delete(K k) {
        if (null == first) {
            throw new NoSuchElementException();
        }

        if (first.key.equals(k)) {
            first = first.next;
            n--;
            return ;
        }

        for (Node cur = first.next, front = first; cur != null; ) {
            if (cur.key.equals(k)) {
                front.next = cur.next;
                n--;return ;
            }
            front = cur;
            cur = cur.next;
        }
    }

    @Override
    public Iterable<K> keys() {
        Bag<K> keysBag = new SimpleBag<>();
        for (Node cur = first; cur != null; cur = cur.next) {
            keysBag.add(cur.key);
        }

        return keysBag;
    }

    private class Node {
        private V value;
        private final K key;
        private Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


}
