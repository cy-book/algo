package hz.xhxh.algo.sort;

public class QuickSort<T extends Comparable<T>> implements Sort<T>{
    @Override
    public void sort(T[] array) {
        sort(array,0, array.length -1);
    }

    private void sort(T[] a,int lo, int hi){
        if(lo >= hi) return;
        int size = partition(a,lo,hi);
        sort(a,lo,size-1);
        sort(a,size+1,hi);
    }

    private int partition(T[] a,int lo,int hi){
        T v = a[lo];
        int l = lo;
        int h = hi+1;

        while(l < h){
            while(less(a[++l],v)) if(l==hi) break;
            while(less(v,a[--h])) ;

            if(l > h) break;

            exchange(a,l,h);
        }
        exchange(a,lo,h);

        return h;
    }
}
