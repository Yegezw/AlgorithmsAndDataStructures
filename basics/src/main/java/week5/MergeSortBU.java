package week5;

import util.ArrayGenerator;
import util.helper.SortingHelper;
import week2.InsertionSort;

import java.util.Arrays;

public class MergeSortBU {

    private MergeSortBU() {
    }

    /**
     * 非递归, 自底向上的归并排序
     */
    public static <E extends Comparable<E>> void mergeSortBU(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;

        // 对 arr[0, n - 1] 中所有的 arr[i, i + 15], 使用插入排序法
        for (int i = 0; i < n; i += 16) {
            InsertionSort.sort1(arr, i, Math.min(i + 15, n - 1));
        }

        // 遍历合并的区间长度(把 2 个区间合并成 1 个区间)
        for (int sz = 16; sz < n; sz += sz) {
            // 遍历合并的两个区间的起始位置 i
            // 合并 arr[i, i + sz - 1] 和 arr[i + sz, Math.min(i + sz + sz - 1, n - 1)]
            for (int i = 0; i + sz < n; i += 2 * sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1), temp);
                }
            }
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

    public static void main(String[] args) {
        int n = 100000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("MergeSortBU", arr);
    }

}
