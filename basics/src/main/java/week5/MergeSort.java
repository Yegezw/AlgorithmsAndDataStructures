package week5;

import util.ArrayGenerator;
import util.helper.SortingHelper;
import week2.InsertionSort;

import java.util.Arrays;

/**
 * 归并排序: O(N*logN)
 * <p>优化 1: merge 条件</p>
 * <p>优化 2: 数据量小的时候(16)采用插入排序</p>
 * <p>优化 3: 避免频繁的在内存中开辟空间</p>
 */
public class MergeSort {

    private MergeSort() {
    }

    public static <E extends Comparable<E>> void mergeSort1(E[] arr) {
        process1(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void process1(E[] arr, int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l) / 2;
        process1(arr, l, mid);
        process1(arr, mid + 1, r);
        merge1(arr, l, mid, r);
    }

    public static <E extends Comparable<E>> void mergeSort2(E[] arr) {
        process2(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void process2(E[] arr, int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l) / 2;
        process2(arr, l, mid);
        process2(arr, mid + 1, r);
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge1(arr, l, mid, r);
        }
    }

    public static <E extends Comparable<E>> void mergeSort3(E[] arr) {
        process3(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void process3(E[] arr, int l, int r) {
        if (r - l <= 15) {
            InsertionSort.sort1(arr, l, r);
            return;
        }
        int mid = l + (r - l) / 2;
        process3(arr, l, mid);
        process3(arr, mid + 1, r);
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge1(arr, l, mid, r);
        }
    }

    /**
     * 合并两个有序数组 arr[l, mid]、arr[mid + 1, r], 使得 arr[l, r] 整体有序
     */
    private static <E extends Comparable<E>> void merge1(E[] arr, int l, int mid, int r) {
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);
        int p1 = 0;
        int p2 = mid + 1 - l;
        int i = l;
        while (p1 <= mid - l && p2 <= r - l) {
            arr[i++] = (temp[p1].compareTo(temp[p2]) <= 0) ? temp[p1++] : temp[p2++];
        }
        while (p1 <= mid - l) arr[i++] = temp[p1++];
        while (p2 <= r - l) arr[i++] = temp[p2++];
    }

    public static <E extends Comparable<E>> void mergeSort4(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        process4(arr, 0, arr.length - 1, temp);
    }

    private static <E extends Comparable<E>> void process4(E[] arr, int l, int r, E[] temp) {
        if (r - l <= 15) {
            InsertionSort.sort1(arr, l, r);
            return;
        }
        int mid = l + (r - l) / 2;
        process4(arr, l, mid, temp);
        process4(arr, mid + 1, r, temp);
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge2(arr, l, mid, r, temp);
        }
    }

    /**
     * 合并两个有序数组 arr[l, mid]、arr[mid + 1, r], 使得 arr[l, r] 整体有序
     */
    private static <E extends Comparable<E>> void merge2(E[] arr, int l, int mid, int r, E[] temp) {
        System.arraycopy(arr, l, temp, l, r - l + 1);
        int p1 = l;
        int p2 = mid + 1;
        int i = l;
        while (p1 <= mid && p2 <= r) {
            arr[i++] = (temp[p1].compareTo(temp[p2]) <= 0) ? temp[p1++] : temp[p2++];
        }
        while (p1 <= mid) arr[i++] = temp[p1++];
        while (p2 <= r) arr[i++] = temp[p2++];
    }

    public static void main(String[] args) {
        int n = 1000000;

        //Integer[] arr1 = ArrayGenerator.generateOrderArray(n);
        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest("MergeSort1", arr1); // 普通
        SortingHelper.sortTest("MergeSort2", arr2); // 优化 merge 条件
        SortingHelper.sortTest("MergeSort3", arr3); // 数据量小的时候(16)采用插入排序
        SortingHelper.sortTest("MergeSort4", arr4); // 避免频繁的在内存中开辟空间
    }

}
