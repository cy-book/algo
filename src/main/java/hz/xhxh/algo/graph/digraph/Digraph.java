package hz.xhxh.algo.graph.digraph;

import hz.xhxh.algo.graph.udgraph.Graph;

public interface Digraph extends Graph {
    /*此方法将有向图的边全部反转，得到当前有向图的
    * 逆有向图。
    *
    * @return 返回当前有向图的翻转图
    * */
    Digraph reverse();
}
