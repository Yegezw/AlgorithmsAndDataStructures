package practice.sort;

import util.ArrayGenerator;
import util.helper.SortingHelper;

public class BubbleSort {

    private BubbleSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int end = arr.length - 1; end >= 1;) {
            int nextEnd = 0;
            for (int i = 0; i + 1 <= end; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    nextEnd = i;
                }
            }
            end = nextEnd;
        }
    }

    public static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    public static void main(String[] args) {
        int n = 10000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        sort(arr);
        System.out.println(SortingHelper.isSorted(arr));
    }
}
