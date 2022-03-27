package week5;

import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Arrays;

/**
 * 归并排序: O(N*logN)
 * <p>优化 1: merge 条件</p>
 * <p>优化 2: 数据量小的时候(16)采用插入排序</p>
 * <p>优化 3: 避免频繁的在内存中开辟空间</p>
 */
public class MyMergeSort {

    private MyMergeSort() {
    }

    /**
     * 递归
     */
    public static <E extends Comparable<E>> void mergeSort(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        process(arr, 0, arr.length - 1, temp);
    }

    private static <E extends Comparable<E>> void process(E[] arr, int l, int r, E[] temp) {
        if (r - l <= 15) {
            insertionSort(arr, l, r);
            return;
        }
        int mid = l + (r - l) / 2;
        process(arr, l, mid, temp);
        process(arr, mid + 1, r, temp);
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r, temp);
        }
    }

    private static <E extends Comparable<E>> void insertionSort(E[] arr, int l, int r) {
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
     * 合并两个有序数组 arr[l, mid]、arr[mid + 1, r], 使得 arr[l, r] 整体有序
     */
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
        System.arraycopy(arr, l, temp, l, r - l + 1);
        int p1 = l;
        int p2 = mid + 1;
        int i = l;
        while (p1 <= mid && p2 <= r) arr[i++] = (temp[p1].compareTo(temp[p2]) <= 0) ? temp[p1++] : temp[p2++];
        while (p1 <= mid) arr[i++] = temp[p1++];
        while (p2 <= r) arr[i++] = temp[p2++];
    }

    /**
     * 非递归, 自底向上的归并排序
     */
    public static <E extends Comparable<E>> void mergeSortBU(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;
        for (int sz = 1; sz < n; sz += sz) {
            // 合并 arr[i, i + sz - 1] 和 arr[i + sz, i + sz + sz - 1]
            for (int i = 0; i + sz <= n - 1; i += 2 * sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1), temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 100000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        mergeSort(arr1);
        mergeSortBU(arr2);

        System.out.println(SortingHelper.isSorted(arr1));
        System.out.println(SortingHelper.isSorted(arr2));
    }

}
