package week6;

import util.ArrayGenerator;
import util.helper.SortingHelper;
import week2.InsertionSort;

import java.util.Arrays;
import java.util.Random;

public class QuickSortT {

    private QuickSortT() {
    }

    public static <E extends Comparable<E>> void quickSortT(E[] arr) {
        Random random = new Random();
        processT(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void processT(E[] arr, int l, int r, Random random) {
        if (r - l <= 15) {
            InsertionSort.sort1(arr, l, r);
            return;
        }
        int[] res = partitionT(arr, l, r, random);
        processT(arr, l, res[0], random);
        processT(arr, res[1], r, random);
    }

    private static <E extends Comparable<E>> int[] partitionT(E[] arr, int l, int r, Random random) {
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);
        E v = arr[l];
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        // [l + 1, lt] < v、[lt + 1, i - 1] == v、[gt, r] > v
        while (i < gt) {
            if (arr[i].compareTo(v) < 0) {
                swap(arr, ++lt, i++);
            } else if (arr[i].compareTo(v) > 0) {
                swap(arr, --gt, i);
            } else {
                i++;
            }
        }
        swap(arr, l, lt);
        // [l + 1, lt - 1] < v、[lt, gt - 1] = v、[gt, r] > v
        return new int[]{lt - 1, gt};
    }

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    public static void main(String[] args) {
        int n = 1000000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = ArrayGenerator.generateOrderArray(n);
        Integer[] arr3 = ArrayGenerator.generateRandomArray(n, 1);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr5 = Arrays.copyOf(arr1, arr2.length);
        Integer[] arr6 = Arrays.copyOf(arr1, arr3.length);

        SortingHelper.sortTest("QuickSortT", arr1); // 随机数据
        SortingHelper.sortTest("QuickSortT", arr2); // 有序数据
        SortingHelper.sortTest("QuickSortT", arr3); // 相同数据

        System.out.println("-----------------------------");

        SortingHelper.sortTest("MergeSort4", arr4); // 随机数据
        SortingHelper.sortTest("MergeSort4", arr5); // 有序数据
        SortingHelper.sortTest("MergeSort4", arr6); // 相同数据

    }
}
