package week9.work;

import util.ArrayGenerator;

/**
 * 基于最小堆实现: 降序
 */
public class HeapSort {

    public static <E extends Comparable<E>> void sort1(E[] arr) {
        MinHeap<E> minHeap = new MinHeap<>();
        for (E e : arr) {
            minHeap.add(e);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = minHeap.extractMin();
        }
    }

    /**
     * 堆排序优化
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        if (arr == null || arr.length <= 1) return;

        int last = arr.length - 1;

        // 把 arr 变成最小堆
        for (int i = (last - 1) / 2; i >= 0; i--) {
            siftDown(arr, i, last);
        }

        while (last >= 1) {
            swap(arr, 0, last--);
            siftDown(arr, 0, last);
        }
    }

    /**
     * 对 arr[0, last] 所形成的最小堆的 index 位置进行调整
     */
    private static <E extends Comparable<E>> void siftDown(E[] arr, int index, int last) {
        while (index * 2 + 1 <= last) {
            int smaller = index * 2 + 1;
            if (smaller + 1 <= last && arr[smaller + 1].compareTo(arr[smaller]) < 0) smaller++;

            if (arr[index].compareTo(arr[smaller]) <= 0) break;
            swap(arr, index, smaller);
            index = smaller;
        }
    }

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    /**
     * 验证数组 arr 是否是降序
     */
    private static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) < 0) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        //sort1(arr);
        sort2(arr);
        System.out.println(isSorted(arr));
    }
}
