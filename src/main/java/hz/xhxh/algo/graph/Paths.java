package hz.xhxh.algo.graph;

/*
* 给定一个图和图中一个点
* 可以求出与这个点与和这个点连通的点的路径
* */
public interface Paths {
    /*
    * 判断特殊点与点v直接是否存在路径
    * */
    boolean hasPathTo(int v);

    /*
    * 返回特殊点与点v之间的路径的点的迭代
    * */
    Iterable<Integer> pathTo(int v);
}
