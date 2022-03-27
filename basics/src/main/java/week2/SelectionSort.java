package week2;

import util.ArrayGenerator;
import util.helper.SortingHelper;

/**
 * 选择排序法: O(n^2)
 */
public class SelectionSort {

    private SelectionSort() {
    }

    /**
     * 正着排
     */
    public static <T extends Comparable<T>> void sort1(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 倒着排
     */
    public static <T extends Comparable<T>> void sort2(T[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int maxIndex = i;
            for (int j = i - 1; j > -1; j--) {
                if (arr[j].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            swap(arr, i, maxIndex);
        }
    }

    private static <T> void swap(T[] arr, int m, int n) {
        if (m == n) return;
        T k = arr[m];
        arr[m] = arr[n];
        arr[n] = k;
    }

    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};

        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest("SelectionSort", arr);
        }
    }

}
