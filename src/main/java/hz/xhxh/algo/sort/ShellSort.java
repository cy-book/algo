package hz.xhxh.algo.sort;

public class ShellSort<T extends Comparable<T>> implements Sort<T>{
    @Override
    public void sort(T[] array){
        shellSort(array);
    }

    private void shellSort(T[] a){
        int len = a.length;
        int h = 1;
        while(h < len/3) h = h*3 +1;

        while(h >=1 ){
            for(int i=h; i <len; i++){
                for(int j=i; j>=h; j-=h){
                    if(less(a[j], a[j-h])) exchange(a,j,j-h);
                    else break;
                }
            }

            h /= 3;
        }
    }
}

