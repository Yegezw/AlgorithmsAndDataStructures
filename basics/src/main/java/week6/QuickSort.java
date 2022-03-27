package week6;

import util.ArrayGenerator;
import util.helper.SortingHelper;
import week2.InsertionSort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序: O(N*logN)
 * <p>优化 1: 数据量小的时候(16)采用插入排序</p>
 * <p>优化 2: 随机化</p>
 */
public class QuickSort {

    private QuickSort() {
    }

    public static <E extends Comparable<E>> void quickSort1(E[] arr) {
        process1(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void process1(E[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition1(arr, l, r);
        process1(arr, l, p - 1);
        process1(arr, p + 1, r);
    }

    public static <E extends Comparable<E>> void quickSort2(E[] arr) {
        process2(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void process2(E[] arr, int l, int r) {
        if (r - l <= 15) {
            InsertionSort.sort1(arr, l, r);
            return;
        }
        int p = partition1(arr, l, r);
        process2(arr, l, p - 1);
        process2(arr, p + 1, r);
    }

    private static <E extends Comparable<E>> int partition1(E[] arr, int l, int r) {
        E v = arr[l];
        int j = l;
        // [l + 1, j] < v, [j + 1, i - 1] >= v
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) {
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, j, l);
        return j;
    }

    public static <E extends Comparable<E>> void quickSort3(E[] arr) {
        Random random = new Random();
        process3(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void process3(E[] arr, int l, int r, Random random) {
        if (r - l <= 15) {
            InsertionSort.sort1(arr, l, r);
            return;
        }
        int p = partition2(arr, l, r, random);
        process3(arr, l, p - 1, random);
        process3(arr, p + 1, r, random);
    }

    private static <E extends Comparable<E>> int partition2(E[] arr, int l, int r, Random random) {
        int p = random.nextInt(r - l + 1) + l; // 取随机点为标定点
        //p = l + (r - l) / 2; // 取中间点为标定点
        swap(arr, l, p);
        E v = arr[l];
        int j = l;
        // [l + 1, j] < v, [j + 1, i - 1] >= v
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) {
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, j, l);
        return j;
    }

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    public static void main(String[] args) {
        int n = 100000;

        System.out.println("Random");
        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        SortingHelper.sortTest("QuickSort1", arr1); // 普通
        SortingHelper.sortTest("QuickSort2", arr2); // 数据量小的时候(16)采用插入排序
        SortingHelper.sortTest("QuickSort3", arr3); // 添加随机化

        System.out.println("-----------------------------------");

        System.out.println("Order");
        Integer[] arr4 = ArrayGenerator.generateOrderArray(n);
        Integer[] arr5 = Arrays.copyOf(arr3, arr3.length);
        //SortingHelper.sortTest("QuickSort2", arr4); // StackOverflowError
        SortingHelper.sortTest("QuickSort3", arr4);
        SortingHelper.sortTest("MergeSort4", arr5);

        System.out.println("-----------------------------------");

        System.out.println("Special: 针对以中间点为标定点的快速排序");
        Integer[] arr6 = ArrayGenerator.generateSpecialArray(n);
        SortingHelper.sortTest("QuickSort3", arr6);

        System.out.println("-----------------------------------");

        System.out.println("Special: 针对以随机点为标定点的快速排序");
        Integer[] arr7 = ArrayGenerator.generateRandomArray(n, 1);
        SortingHelper.sortTest("QuickSort3", arr7);
    }

}
