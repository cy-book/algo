package hz.xhxh.algo.graph.udgraph;

public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph g){
        this.marked = new boolean[g.V()];
        for(int s=0; s<g.V(); s++){
            if(!marked[s]) dfs(g,s,s);
        }
    }

    /*深度优先遍历整个图，通过一些标志判断图中是否带有环
    * 首先，当一个节点 v 被访问两次时候，第二次访问是通过
    * 节点 w
    * 如果访问节点 w 是通过节点 v ,那么因为无向图两个顶点
    * 直接有路径，不能认定是连通图。
    * 如果 访问节点 w 不是通过节点 v,  而是另一个节点 ，
    * 可以判断， 节点v被访问了两次， 两次访问是通过两条
    * 不同的路径，说明开始节点有两条路径到达v，是有环图。
    *
    * @param g 要判断是否有环的图
    * @param v 当前要访问的节点 v
    * @param u 上一个访问的节点 u, 即节点v的上一级
    * */
    private void dfs(Graph g, int v, int u){
        marked[v] = true;
        for(int w : g.adj(v)){
            if(!marked[w]) dfs(g,w,v);
            else if(w != u) hasCycle = true;
        }
    }

    /*
    * @return 如果图中有环，则返回true。
    * */
    public boolean hasCycle(){
        return hasCycle;
    }
}
