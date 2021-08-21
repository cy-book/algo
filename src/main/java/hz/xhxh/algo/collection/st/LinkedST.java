package hz.xhxh.algo.collection.st;

import hz.xhxh.algo.collection.bag.Bag;
import hz.xhxh.algo.collection.bag.SimpleBag;
import javafx.scene.text.Font;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
* 采用链表结构来实现符号表，通过遍历链表查找至
* 优点： 插入时方便
* 缺点： 查找需要遍历链表，插入，删除等操作需要查找为前提
* */
public class LinkedST<K,V> implements SymbolTable<K,V>{
    private Node first;
    private int n;

    @Override
    public void put(K k, V v) {
        for(Node cur = first; cur!=null; cur=cur.next){
            if(cur.key.equals(k)) {
                cur.value = v;
                n +=1;
                return;
            }
        }
        first = new Node(k,v,first);
    }

    @Override
    public V get(K k) {
        for(Node cur = first; cur != null; cur = cur.next){
            if(cur.key.equals(k)) return cur.value;
        }
        return null;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean contains(K k) {
        for(Node cur = first; cur != null; cur = cur.next){
            if(cur.key.equals(k)) return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return 0 == n;
    }

    @Override
    public V delete(K k){
        if(null ==first){
            throw new NoSuchElementException();
        }

        if(first.key.equals(k)){
            var v = first.value;
            first = first.next;
            n--;
            return v;
        }

        for (Node cur = first.next, front = first; cur != null; ) {
            if(cur.key.equals(k)){
                var v = cur.value;
                front.next = cur.next;
                n--;
                return v;
            }
            front = cur;
            cur = cur.next;
        }

        return null;
    }

    @Override
    public Iterable<K> keys() {
        Bag<K> keysBag = new SimpleBag<>();
        for(Node cur=first; cur!=null; cur = cur.next){
            keysBag.add(cur.key);
        }

        return keysBag;
    }

    private class Node{
        V value;
        K key;
        Node next;

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String...args) throws URISyntaxException, FileNotFoundException, MalformedURLException {
        SymbolTable<String,Integer> st = new LinkedST<>();
        Scanner scanner = new Scanner(
                new BufferedInputStream(new FileInputStream(
                        new File(Paths.get("fonts.txt").toUri()))),
                StandardCharsets.UTF_8);
        for(int i=0; scanner.hasNext(); i++){
            st.put(scanner.nextLine(),i);
        }
        scanner.close();

        Font.getFamilies().forEach(s -> {
            if(st.contains(s)) st.delete(s);
            else {
                System.out.println("[st not contain]  " + s);
            }
        });

        for(String s :st.keys()){
            System.out.println(s);
        }
    }
}
