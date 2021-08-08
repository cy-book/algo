package hz.xhxh.algo.sort;

import java.util.Arrays;

public interface Sort<T extends Comparable<T>> {
    void sort(T[] array);

    default void exchange(T[] array, int i, int j){
        T tmp  = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    default boolean less(T a, T b){
        return a.compareTo(b) < 0 ;
    }

    default boolean isSorted(T[] array){
        for(int i=0; i<array.length -1; i++){
            if(less(array[i+1],array[i])) return false;
        }
        return true;
    }

    default void sort(int[] array){
        Arrays.sort(array);
    }

    default void sort(long[] array){
        Arrays.sort(array);
    }

    default void sort(byte[] array){
        Arrays.sort(array);
    }

    default void sort(double[] array){
        Arrays.sort(array);
    }

    default void sort(float[] array){
        Arrays.sort(array);
    }

    default void sort(char[] array){
        Arrays.sort(array);
    }

    default boolean isSorted(int [] a){
        for(int i=0; i<a.length - 1; i++ ){
            if(a[i+1] < a[i]) return false;
        }
        return true;
    }

    default boolean isSorted(long [] a){
        for(int i=0; i<a.length - 1; i++ ){
            if(a[i+1] < a[i]) return false;
        }
        return true;
    }

    default boolean isSorted(char [] a){
        for(int i=0; i<a.length - 1; i++ ){
            if(a[i+1] < a[i]) return false;
        }
        return true;
    }

    default boolean isSorted(byte [] a){
        for(int i=0; i<a.length - 1; i++ ){
            if(a[i+1] < a[i]) return false;
        }
        return true;
    }

    default boolean isSorted(double [] a){
        for(int i=0; i<a.length - 1; i++ ){
            if(a[i+1] < a[i]) return false;
        }
        return true;
    }

    default boolean isSorted(float [] a){
        for(int i=0; i<a.length - 1; i++ ){
            if(a[i+1] < a[i]) return false;
        }
        return true;
    }
}
