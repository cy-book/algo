package hz.xhxh.algo.sort;

public class HeapSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] array) {
        heapSort(array);
    }

    private void heapSort(T[] arr) {
        /*
         * 排序分两步
         * 一、构造堆有序的二叉树
         * 二、不停的删除最大值（将其放到数组末段，同时减小方法调用时传递的数组大小)
         * */

        int N = arr.length;
        for (int k = N / 2; k >= 0; k--) {
            sink(arr, k, N);
        }

        while (N > 0) {
            exchange(arr, 0, --N);
            sink(arr, 0, N);
        }
    }

    /*
     * 这里的sink()实现与SimplePQ中的sink()有一点区别，
     * 这里使用了数组的每一个位置（没有空出arr[0]),所以有：
     *       parent(k) = (k-1)/2
     *       child_left(k) = (k+1) * 2 - 1;
     *       child_right(k) = (k+1) * 2;
     * */
    private void sink(T[] arr, int k, int N) {
        while (k * 2 < N) {
            int j = k * 2;
            if (j + 1 < N && less(arr[j], arr[j + 1])) j++;
            if (!less(arr[k], arr[j])) break;
            exchange(arr, k, j);
            k = j;
        }
    }
}
