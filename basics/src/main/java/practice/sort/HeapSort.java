package practice.sort;

import practice.other.MaxHeap;
import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Arrays;

public class HeapSort {

    private HeapSort() {
    }

    public static <E extends Comparable<E>> void sort1(E[] arr) {
        MaxHeap<E> heap = new MaxHeap<>(arr);

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = heap.extractMax();
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] arr) {
        int last = arr.length - 1;
        for (int i = (last - 1) / 2; i >= 0; i--) {
            siftDown(arr, i, last);
        }
        while (last >= 1) {
            swap(arr, 0, last--);
            siftDown(arr, 0, last);
        }
    }

    public static <E extends Comparable<E>> void siftDown(E[] arr, int index, int last) {
        while (index * 2 + 1 <= last) {
            int bigger = index * 2 + 1;
            if (bigger + 1 <= last && arr[bigger + 1].compareTo(arr[bigger]) > 0) bigger++;

            if (arr[index].compareTo(arr[bigger]) >= 0) return;
            swap(arr, index, bigger);
            index = bigger;
        }
    }

    public static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        sort1(arr1);
        sort2(arr2);

        System.out.println(SortingHelper.isSorted(arr1));
        System.out.println(SortingHelper.isSorted(arr2));
    }
}
