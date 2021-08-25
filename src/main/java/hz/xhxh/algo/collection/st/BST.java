package hz.xhxh.algo.collection.st;

import hz.xhxh.algo.collection.queue.Queue;
import hz.xhxh.algo.collection.queue.SimpleQueue;

public class BST<Key extends Comparable<Key>, Value> implements ComparableSymbolTable<Key, Value> {
    private Node root;

    public BST() {
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key k, Value v) {
        /*
         * 注意的是，当x为空节点时，返回一个新的节点，
         * 否则只是遍历以x为根节点的树，更新涉及到的节点。
         * 只有在k不存在于树中时，x.N的值才会发生变化。
         * */
        if (null == x) return new Node(k, v, 1);
        int cmp = k.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, k, v);
        else if (cmp > 0) x.right = put(x.right, k, v);
        else x.val = v;
        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key k) {
        /*
         * 思考如何使用非递归的方法实现查找并实现
         * */
        if (null == x) return null;
        int cmp = k.compareTo(x.key);
        if (cmp < 0) return get(x.left, k);
        else if (cmp > 0) return get(x.right, k);
        else return x.val;
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key k) {
        if (x == null) return null;
        int cmp = k.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, k);
        else if (cmp > 0) x.right = delete(x.right, k);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            Node tmp = x;
            x = min(x.right);
            x.right = deleteMin(tmp.right);
//            x.right = delete(x.right,x.key);
            x.left = tmp.left;
        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    @Override
    public boolean contains(Key key) {
        return contains(root, key);
    }

    private boolean contains(Node x, Key k) {
        if (null == x) return false;
        int cmp = k.compareTo(x.key);
        if (cmp > 0) return contains(x.right, k);
        else if (cmp < 0) return contains(x.left, k);
        else return true;
    }

    @Override
    public boolean isEmpty() {
        return null == root;
    }

    @Override
    public Key min() {
        if (null == root) throw new IllegalArgumentException("树为空");
        return min(root).key;
    }

    private Node min(Node x) {
        if (null == x) throw new IllegalArgumentException("x子树为空");
        if (null == x.left) return x;
        else return min(x.left);
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (null == x) throw new IllegalArgumentException("x子树为空");
        if (null == x.right) return x;
        else return max(x.right);
    }

    @Override
    public Key floor(Key key) {
        return floor(root, key);
    }

    private Key floor(Node x, Key k) {
        /*
         * floor获得小于等于key的最大键
         * 如果给定键小于当前节点，那么小于等于它的键一定在左子树当中
         * 如果给定键大于当前节点，那么小于等于它的键可能存在右子树中或者为当前节点
         *   应该先判断右子树
         * */
        if (null == x) return null;
        int cmp = k.compareTo(x.key);
        if (cmp < 0) return floor(x.left, k);
        else if(cmp == 0)return x.key;
        else  /*(cmp > 0) */{
            Key t = floor(x.right, k);
            if (null == t) return x.key;
            else return t;
        }
    }

    @Override
    public Key ceiling(Key key) {
        return ceiling(root,key);
    }

    private Key ceiling(Node x, Key k) {
        /*
         * ceiling 获得大于等于key的最小键
         * 如果给定键大于当前节点，那么小于等于它的键一定在右子树当中
         * 如果给定键小于当前节点，那么小于等于它的键可能在左子树当中或者为当前节点
         *   先判断左子树
         * */

        if (null == x) return null;
        int cmp = k.compareTo(x.key);

        if (cmp > 0) return ceiling(x.right, k);
        else if (cmp == 0) return x.key;
        else /* (cmp < 0) */{
            Key t = ceiling(x.left, k);
            if (null == t) return x.key;
            else return t;
        }
    }

    @Override
    public int rank(Key key) {
        return rank(root,key);
    }

    private int rank(Node x, Key k){
        if(null == x) return 0;
        int cmp = k.compareTo(x.key);
        if(cmp < 0) return rank(x.left,k);
        else if(cmp > 0) return 1 + size(x.left) + rank(x.right,k) ;
        else return size(x.left);
    }

    @Override
    public Key select(int k) {
        if(k<0 || k >= size(root)) throw new IllegalArgumentException("所查找位置超出树的大小");
        return select(root,k);
    }

    private Key select(Node x, int k){
        if(null == x) return null;
        int num_left = size(x.left);
        if(k == num_left) return x.key;
        else if(k < num_left) return select(x.left,k);
        else return select(x.right,k-1 -num_left);
    }

    @Override
    public void deleteMin() {
        if (null == root) throw new IllegalArgumentException("树为空，如法删除最小值");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (null == x) throw new IllegalArgumentException("x子树为空");

        if (null == x.left) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void deleteMax() {
        if (null == root) throw new IllegalArgumentException("树为空，无法删除最大值");
        /*
        * 至关重要的赋值，这样才可以删除节点在树中的引用
        * */
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (null == x) throw new IllegalArgumentException("x子树为空");

        if (null == x.right) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(),max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        if(null == lo) throw new IllegalArgumentException("不存在范围为空的集合");
        if(null == hi) throw new IllegalArgumentException("不存在范围为空的集合");
        SimpleQueue<Key> queue = new SimpleQueue<>();
        keys(root,queue,lo,hi);

        return queue;
    }

    private void keys(Node x, Queue<Key> queue , Key lo, Key hi){
        /*
        * 这段代码要区别与遍历二叉树的代码，并不是完全的遍历所有的树
        * 先要判断要找的区间最小值是否比当前值小，若小才需要去遍历左子树
        * 判断
        * */
        if(null == x) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        if(cmplo < 0) keys(x.left,queue,lo,hi);
        if(cmplo <=0 && cmphi >=0) queue.enqueue(x.key);
        if(cmphi >0) keys(x.right,queue,lo,hi);
    }

    private class Node {
        private Node left, right;
        private final Key key;
        private Value val;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
}
