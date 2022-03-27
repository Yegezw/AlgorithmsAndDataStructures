package week2;

import util.ArrayGenerator;
import util.helper.SortingHelper;

/**
 * 插入排序法: O(n^2)
 */
public class InsertionSort {

    private InsertionSort() {
    }

    /**
     * 正着排
     */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            E k = arr[i];
            int j;
            for (j = i; j - 1 >= 0 && k.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = k;
        }
    }

    public static <E extends Comparable<E>> void sort1(E[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            E k = arr[i];
            int j;
            for (j = i; j - 1 >= l && k.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = k;
        }
    }

    /**
     * 倒着排
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = arr.length - 2; i >= 0; i--) {
            E k = arr[i];
            int j;
            for (j = i; j + 1 < arr.length && k.compareTo(arr[j + 1]) > 0; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = k;
        }
    }

    /**
     * 交换
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j - 1 >= 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    /**
     * 交换
     */
    public static <E extends Comparable<E>> void sort4(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j - 1 >= 0; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 每次交换都是 3 次操作
     */
    private static <E> void swap(E[] arr, int m, int n) {
        E k = arr[m];
        arr[m] = arr[n];
        arr[n] = k;
    }

    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};

        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest("InsertionSort", arr);
        }
    }
}
