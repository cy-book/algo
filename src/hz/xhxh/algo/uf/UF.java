package hz.xhxh.algo.uf;

public class UF {
    private int[] parent;
    private byte[] rank;
    private int count;

    public UF(int n){
        if(n < 0) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new byte[n];
    }
}
