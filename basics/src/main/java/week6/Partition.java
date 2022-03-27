package week6;

import java.util.Random;

public class Partition {

    /**
     * 单路快排
     */
    private static <E extends Comparable<E>> int partition1(E[] arr, int l, int r, Random random) {
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);
        E v = arr[l];

        int j = l;
        // arr[l + 1, j] < v, arr[j + 1, i - 1] >= v
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) swap(arr, ++j, i);
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 双路快排
     */
    private static <E extends Comparable<E>> int partition2(E[] arr, int l, int r, Random random) {
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);
        E v = arr[l];

        // arr[l + 1, p1] < v, arr[p2, r] > v
        int p1 = l + 1;
        int p2 = r;
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
    private static <E extends Comparable<E>> int[] partition3(E[] arr, int l, int r, Random random) {
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);
        E v = arr[l];

        // arr[l + 1, lt] < v, arr[lt + 1, i - 1] = v, arr[gt, r] > v
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        while (i < gt) {
            if (arr[i].compareTo(v) < 0) swap(arr, ++lt, i++);
            else if (arr[i].compareTo(v) > 0) swap(arr, --gt, i);
            else i++;
        }
        swap(arr, l, lt);
        return new int[]{lt - 1, gt};
    }

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

}
