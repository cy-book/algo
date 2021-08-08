package hz.xhxh.algo.sort;

public class SelectSort<T extends Comparable<T>> implements Sort<T> {
    @Override
    public void sort(T[] array) {
        selectSort(array);
    }

    private void selectSort(T[] a) {
        int len = a.length;
        int min = 0;
        for (int i = 0; i < len - 1; i++) {
            min = i;

            for (int j = i + 1; j < len; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exchange(a, i, min);
        }
    }
}
