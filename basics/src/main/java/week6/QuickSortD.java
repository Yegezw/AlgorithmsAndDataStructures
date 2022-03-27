package week6;

import util.ArrayGenerator;
import util.helper.SortingHelper;
import week2.InsertionSort;

import java.util.Random;

public class QuickSortD {

    private QuickSortD() {
    }

    public static <E extends Comparable<E>> void quickSortD(E[] arr) {
        Random random = new Random();
        processD(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void processD(E[] arr, int l, int r, Random random) {
        if (r - l <= 15) {
            InsertionSort.sort1(arr, l, r);
            return;
        }
        int p = partitionD(arr, l, r, random);
        processD(arr, l, p - 1, random);
        processD(arr, p + 1, r, random);
    }

    private static <E extends Comparable<E>> int partitionD(E[] arr, int l, int r, Random random) {
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);
        E v = arr[l];
        int p1 = l + 1;
        int p2 = r;

        while (true) {
            while (p1 <= p2 && arr[p1].compareTo(v) < 0) p1++; // 出循环时: arr[p1] >= v
            while (p1 <= p2 && arr[p2].compareTo(v) > 0) p2--; // 出循环时: arr[p2] <= v

            if (p1 >= p2) break;

            swap(arr, p1, p2);
            p1++;
            p2--;
        }
        swap(arr, l, p2);
        return p2;
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

        SortingHelper.sortTest("QuickSortD", arr1); // 随机数据
        SortingHelper.sortTest("QuickSortD", arr2); // 有序数据
        SortingHelper.sortTest("QuickSortD", arr3); // 相同数据
    }
}
