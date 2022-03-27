package week7.work;

import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Arrays;

public class MergeSort {

    public static <E extends Comparable<E>> void mergeSort(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        process(arr, 0, arr.length, temp);
    }

    /**
     * 对 arr[l, r) 进行归并排序
     */
    public static <E extends Comparable<E>> void process(E[] arr, int l, int r, E[] temp) {
        if (l >= r - 1) return;

        int mid = l + (r - l) / 2;
        process(arr, l, mid, temp); // arr[l, mid) 有序
        process(arr, mid, r, temp); // arr[mid, r) 有序
        merge(arr, l, mid, r, temp);
    }

    /**
     * 合并 arr[l, mid) 和 arr[mid, r), 使得 arr[l, r) 整体有序
     */
    public static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
        System.arraycopy(arr, l, temp, l, r - l);
        int p1 = l;
        int p2 = mid;
        int i = l;
        while (p1 < mid && p2 < r) arr[i++] = (temp[p1].compareTo(temp[p2]) <= 0) ? temp[p1++] : temp[p2++];
        while (p1 < mid) arr[i++] = temp[p1++];
        while (p2 < r) arr[i++] = temp[p2++];
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);

        mergeSort(arr);
        System.out.println(SortingHelper.isSorted(arr));
    }

}
