package hz.xhxh.algo.sort;

public class MergeSort<T extends Comparable<T>> implements Sort<T>{
    private T[] aux;

    @Override
    public void sort(T[] array) {
        aux =(T[]) new Comparable[array.length];
        sort(array,0, array.length-1);
    }

    private void merge(T[] a,int lo,int mid, int hi){
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        int l = lo;
        int h = mid + 1;
        for(int k=lo; k<=hi; k++){
            if(l > mid) a[k] = aux[h++];
            else if(h > hi) a[k] = aux[l++];
            else if(less(aux[h],aux[l])) a[k] = aux[h++];
            else a[k] = aux[l++];
        }
    }

    private void sort(T[] a,int lo, int hi){
        if(hi <= lo) return;
        int mid = lo + (hi -lo) /2;
        sort(a,lo,mid);
        sort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }

}
