package hz.xhxh.algo.uf;

public class SimpleUF implements UF{
    private final int[] id;
    private int count;

    public SimpleUF(int n){
        this.count = n;
        this.id = new int[n];
        for(int i=0; i<n; i++){
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if(pID == qID) return;

        for(int i=0; i<id.length; i++){
            if(id[i] == pID) id[i] = qID;
        }
        count --;

    }

    @Override
    public int find(int p) {
        assert  p < id.length;
        return id[p];
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }
}
