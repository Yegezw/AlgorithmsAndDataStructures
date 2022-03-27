package practice.sort;

import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Random;

public class QuickSort {

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    private static <E extends Comparable<E>> void insertionSort(E[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            E k = arr[i];
            int j;
            for (j = i; j - 1 >= l && arr[j - 1].compareTo(k) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = k;
        }
    }

    /**
     * 单路快排
     */
    public static <E extends Comparable<E>> void quickSort1(E[] arr) {
        Random random = new Random();
        process1(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void process1(E[] arr, int l, int r, Random random) {
        if (r - l <= 15) {
            insertionSort(arr, l, r);
            return;
        }
        int p = partition1(arr, l, r, random);
        process1(arr, l, p - 1, random);
        process1(arr, p + 1, r, random);
    }

    private static <E extends Comparable<E>> int partition1(E[] arr, int l, int r, Random random) {
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);
        E v = arr[l];

        int j = l;
        // [l + 1, j] < v, [j, i - 1] >= v
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) {
                swap(arr, i, ++j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 双路快排
     */
    public static <E extends Comparable<E>> void quickSort2(E[] arr) {
        Random random = new Random();
        process2(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void process2(E[] arr, int l, int r, Random random) {
        if (r - l <= 15) {
            insertionSort(arr, l, r);
            return;
        }
        int p = partition2(arr, l, r, random);
        process2(arr, l, p - 1, random);
        process2(arr, p + 1, r, random);
    }

    private static <E extends Comparable<E>> int partition2(E[] arr, int l, int r, Random random) {
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);
        E v = arr[l];

        int p1 = l + 1;
        int p2 = r;
        // arr[l + 1, p1] < v, arr[p2, r] > v
        while (true) {
            while (p1 <= p2 && arr[p1].compareTo(v) < 0) p1++; // arr[p1] >= v
            while (p1 <= p2 && arr[p2].compareTo(v) > 0) p2--; // arr[p2] <= v

            if (p1 >= p2) break;

            swap(arr, p1, p2);
            p1++;
            p2--;
        }
        swap(arr, l, p2);
        return p2;
    }

    /**
     * 三路快排
     */
    public static <E extends Comparable<E>> void quickSort3(E[] arr) {
        Random random = new Random();
        process3(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void process3(E[] arr, int l, int r, Random random) {
        if (r - l <= 15) {
            insertionSort(arr, l, r);
            return;
        }
        int[] res = partition3(arr, l, r, random);
        process3(arr, l, res[0], random);
        process3(arr, res[1], r, random);
    }

    private static <E extends Comparable<E>> int[] partition3(E[] arr, int l, int r, Random random) {
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);
        E v = arr[l];

        int p1 = l;
        int p2 = r + 1;
        // arr[l + 1, p1] < v, arr[p2, r] > v, arr[p1 + 1, i - 1] == v
        int i = l + 1;
        while (i < p2) {
            if (arr[i].compareTo(v) < 0) {
                swap(arr, ++p1, i++);
            } else if (arr[i].compareTo(v) > 0) {
                swap(arr, --p2, i);
            } else {
                i++;
            }
        }
        swap(arr, l, p1);
        return new int[] {p1 - 1, p2};
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);

        //quickSort1(arr);
        //System.out.println(SortingHelper.isSorted(arr));

        //quickSort2(arr);
        //System.out.println(SortingHelper.isSorted(arr));

        quickSort3(arr);
        System.out.println(SortingHelper.isSorted(arr));
    }
}
