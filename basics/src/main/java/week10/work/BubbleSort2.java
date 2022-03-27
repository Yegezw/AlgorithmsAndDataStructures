package week10.work;

import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Arrays;

/**
 * 倒着冒泡
 */
public class BubbleSort2 {

    private BubbleSort2() {
    }

    /**
     * 冒泡排序
     */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        for (int start = 0; start <= arr.length - 2; start++) {
            // 通过冒泡在 arr[start] 放上合适的元素
            for (int i = arr.length - 1; i - 1 >= start; i--) {
                if (arr[i].compareTo(arr[i - 1]) < 0) {
                    swap(arr, i, i - 1);
                }
            }
        }
    }

    /**
     * 冒泡排序优化 1
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int start = 0; start <= arr.length - 2; start++) {
            // 通过冒泡在 arr[start] 放上合适的元素
            boolean isSwapped = false;
            for (int i = arr.length - 1; i - 1 >= start; i--) {
                if (arr[i].compareTo(arr[i - 1]) < 0) {
                    swap(arr, i, i - 1);
                    isSwapped = true;
                }
            }

            if (!isSwapped) return;
        }
    }

    /**
     * 冒泡排序优化 2
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        for (int start = 0; start <= arr.length - 2;) {
            // 通过冒泡在 arr[start] 放上合适的元素
            int nextStart = arr.length - 1; // 如果一次都没 swap, 就应该退出
            for (int i = arr.length - 1; i - 1 >= start; i--) {
                if (arr[i].compareTo(arr[i - 1]) < 0) {
                    swap(arr, i, i - 1);
                    nextStart = i;
                }
            }
            start = nextStart;
        }
    }

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    public static void main(String[] args) {
        int n = 10000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        System.out.print("Random Array: ");
        sort1(arr1);
        sort2(arr2);
        sort3(arr3);
        if (SortingHelper.isSorted(arr1) && SortingHelper.isSorted(arr1) && SortingHelper.isSorted(arr1)) {
            System.out.println("yes");
        }

        arr1 = ArrayGenerator.generateOrderArray(n);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        System.out.print("Order Array: ");
        sort1(arr1);
        sort2(arr2);
        sort3(arr3);
        if (SortingHelper.isSorted(arr1) && SortingHelper.isSorted(arr1) && SortingHelper.isSorted(arr1)) {
            System.out.println("yes");
        }
    }

}
