package hz.xhxh.algo.sort;

public class InsertSort<T extends Comparable<T>> implements Sort<T>{
    @Override
    public void sort(T[] array) {
        insertSort(array);
    }

    private void insertSort(T[] a){
        int len = a.length;
        int size = 0;
        T tmp = null;
        for(int i=1; i<len; i++){
            size = i;
            tmp = a[i];
            for(int j=i -1; j >= 0; j--){
                if(less(tmp,a[j])) {
                    a[size] = a[size -1];
                    size --;
                }else break;
            }
            a[size] = tmp;
        }
    }
}
