package hz.xhxh.algo.graph.udgraph;

import hz.xhxh.algo.collection.st.ArrayST;
import hz.xhxh.algo.collection.st.ComparableST;
public class SimpleSymbolGraph implements SymbolGraph{
    private ComparableST<String, Integer> st;
    private Graph g ;
    private String[] keys;

    public SimpleSymbolGraph(String[] argv){
        int N = argv.length;
        this.g = new AdjListGraph(N);
        this.st = new ArrayST<>();
        this.keys = new String[N];

        System.arraycopy(argv,0,keys,0,N);
        for(int i=0 ;i<N; i++){
            st.put(argv[i],i);
        }
    }

    public SimpleSymbolGraph(Iterable<String> ite){
        this.st = new ArrayST<>();
        int count = 0;
        for(String v : ite){
            if(!st.contains(v)) st.put(v,count++);
        }
        this.g = new AdjListGraph(count);
        this.keys = new String[count];
        for(int i=0; i<count; i++){
            keys[i] = st.select(i);
        }
    }

    @Override
    public int index(String key) {
        return st.get(key);
    }

    @Override
    public boolean contains(String key) {
        return st.contains(key);
    }

    @Override
    public String name(int v) {
        return keys[v];
    }

    @Override
    public void addEdge(String v, String w) {
        g.addEdge(st.get(v),st.get(w));
    }

    @Override
    public Graph G() {
        return g;
    }
}
