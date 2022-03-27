package practice.sort;

import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Arrays;

public class MergeSort {

    private MergeSort() {
    }

    /**
     * 非递归, 自底向上的归并排序
     */
    public static <E extends Comparable<E>> void mergeSortBU(E[] arr) {
        int n = arr.length;
        E[] temp = Arrays.copyOf(arr, n);

        // arr[0, n - 1] 中所有的 arr[i, i + 15]
        for (int i = 0; i < n; i += 16) {
            insertionSort(arr, i, Math.min(i + 15, n - 1));
        }

        for (int sz = 16; sz < n; sz += sz) {
            // arr[i, i + sz - 1], arr[i + sz, i + sz + sz - 1]
            for (int i = 0; i + sz < n; i += 2 * sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1), temp);
                }
            }
        }
    }

    /**
     * 递归
     */
    public static <E extends Comparable<E>> void mergeSort(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        process(arr, 0, arr.length - 1, temp);
    }

    private static <E extends Comparable<E>> void process(E[] arr, int l, int r, E[] temp) {
        //if (l >= r) return;
        if (r - l <= 15) {
            insertionSort(arr, l, r);
            return;
        }
        int mid = l + (r - l) / 2;
        process(arr, l, mid, temp);
        process(arr, mid + 1, r, temp);
        if (arr[mid].compareTo(arr[mid + 1]) > 0) merge(arr, l, mid, r, temp);
    }

    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
        System.arraycopy(arr, l, temp, l, r - l + 1);
        // arr[l, mid], arr[mid + 1, r]
        int p1 = l;
        int p2 = mid + 1;
        int i = l;
        while (p1 <= mid && p2 <= r) arr[i++] = temp[p1].compareTo(temp[p2]) <= 0 ? temp[p1++] : temp[p2++];
        while (p1 <= mid) arr[i++] = temp[p1++];
        while (p2 <= r) arr[i++] = temp[p2++];
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

    public static void main(String[] args) {
        Integer[] arr = ArrayGenerator.generateRandomArray(100000, 100000);
        mergeSortBU(arr);
        System.out.println(SortingHelper.isSorted(arr));
    }
}
