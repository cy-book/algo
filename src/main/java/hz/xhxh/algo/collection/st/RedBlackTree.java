package hz.xhxh.algo.collection.st;

import hz.xhxh.algo.collection.queue.Queue;
import hz.xhxh.algo.collection.queue.SimplePQ;

public class RedBlackTree<K extends Comparable<K>, V> implements ST<K, V> {
    private Node root;

    enum Color {
        RED, BLACK
    }

    private class Node {
        private final K key;
        private V val;

        private Node right, left;
        int N;

        Color color;

        public Node(K key, V val, int N, Color color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }


    @Override
    public void put(K k, V v) {
        root = put(root, k, v);
        root.color = Color.BLACK;
    }

    private Node put(Node h, K k, V v) {
        if (null == h) return new Node(k, v, 1, Color.RED);

        int cmp = k.compareTo(h.key);

        if (cmp < 0) h.left = put(h.left, k, v);
        else if (cmp > 0) h.right = put(h.right, k, v);
        else h.val = v;

        /*当h是一个2-结点，此时向它插入了一个新的结点
         * 如果插入的位置为左结点，那么h仍然是一个正常的节点
         * 如果插入的位置为右结点，那么h有一条红色的右链接，此时需要对h进行左旋转
         * */
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);

        /*首先说明，这里h的左链接不会为空
         *   当插入的节点为h的右结点时，那么在上一步的左转换中h成为了一个有左结点的结点
         *   h不会没有子节点。
         * 这里的情况是，h会有一个红色左节点，且左结点的左结点依旧是红色的
         *
         * 产生的原因是：(h和它的左结点构成了一个3-结点)
         * 新插入的结点在树的最下面，是一个红色结点，递归调用返回，而被插入结点的
         * 结点本来是一个红色结点，这一步就要消除两个连续的红色结点。
         *
         * 注意：不会出现h.left.right.color == Color.RED 的情况，因为这种情况在递归中被消除。
         * */
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);

        /*产生这种结果的可能有两个
         * （1） 直接向一个3-结点插入一个比两个值都大的值的结点， 产生一个4-节点
         *       中间节点指向两个红色节点
         * （2） 上一步 对两个连续红色左结点使用右旋转产生了这样的结果
         *
         *  这时候需要拆解4-结点，让两个红色节点变成黑色节点，中间节点变成红色节点
         *   这里有一个问题： 转换两个红色节点的颜色会让树的高度增加，如果只是局部增加会造成
         *       树的不平衡（只有在根部增加树的高度才会保证树一定会平衡）。
         *      将中间结点变成红色节点即将中间节点向上传递，确保树的高度不在这个地方增加
         * */
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;

        return h;
    }

    @Override
    public V get(K k) {
        return get(root, k);
    }

    public V get(Node x, K k) {
        if (null == x) return null;
        int cmp = k.compareTo(x.key);
        if (cmp < 0) return get(x.left, k);
        else if (cmp > 0) return get(x.right, k);
        else return x.val;
    }

    @Override
    public void delete(K k) {

    }

    @Override
    public int size() {
        return size(root);
    }

    /*
     * @param x 要计算总结点数的子树根节点
     *
     * @return 返回以当前结点为根节点的树的结点总数
     *
     * 红黑树的size(x)的计算结果相当于不区分红黑结点的二叉树的size(x)
     * */
    private int size(Node x) {
        if (null == x) return 0;
        return x.N;
    }

    @Override
    public boolean contains(K k) {
        if (null == k) return false;
        return null != get(k);
    }

    @Override
    public boolean isEmpty() {
        return null == root;
    }

    @Override
    public Iterable<K> keys() {
        if (null == root) return new SimplePQ<K>(0);

        Queue<K> queue = new SimplePQ<>(size());
        keys(root, queue);

        return queue;
    }

    private void keys(Node x, Queue<K> queue) {
        if (null == x) return;
        if (null != x.left) keys(x.left, queue);
        queue.enqueue(x.key);
        if (null != x.right) keys(x.right, queue);
    }

    /*
     * 假设有一条红色的右链接需要被转化为左链接，这个方法接受
     * 一条指向红黑树中的某个结点的链接作为参数。假设被指向的
     * 节点右链接是红色的，这个方法会对树进行必要的调整，并反
     * 会一个指向包含同一组键的子树且其左链接为红色的结点的链
     * 接。
     *
     * @param h 红黑树的某个结点的链接，它的右链接为红色的
     *
     * @return 指向与h包含同一组键的子树且其左链接为红色结点的链接
     * */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = Color.RED;

        x.N = h.N;
        h.N = size(h.right) + size(h.left) + 1;

        return x;
    }

    /*
     * @param h 红黑树的某个结点的链接，它的左链接为红色
     *
     * @return 指向与h包含同一组键的子树且其右链接为红色结点的链接
     * */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = Color.RED;

        x.N = h.N;
        h.N = size(h.right) + size(h.left) + 1;

        return x;
    }

    /*
     * @param x 树的一个结点的链接
     *
     * @return 返回这个结点是否为红色结点，红色结点返回ture,黑色结点返回false
     *
     * 由于一个结点只会有一个父节点，所以这个结点的Color指的是父结点指向它的链接的颜色
     * */
    private boolean isRed(Node x) {
        if (null == x) return false;
        return x.color == Color.RED;
    }

    private void flipColors(Node x) {
        x.color = Color.RED;
        x.left.color = Color.BLACK;
        x.right.color = Color.BLACK;
    }
}
