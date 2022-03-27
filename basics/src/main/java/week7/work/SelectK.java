package week7.work;

import util.ArrayGenerator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * <p>第 K 大的元素</p>
 * <p>升序后, 从右往左数第 K 个元素</p>
 * <p>升序后, 从左往右数第 length - K 个元素</p>
 */
public class SelectK {

    /**
     * 非递归
     */
    public static <E extends Comparable<E>> E selectK(E[] arr, int k) {
        Random random = new Random();
        k = arr.length - k;

        int l = 0;
        int r = arr.length - 1;

        // 在 arr[l, r] 的范围中查找 K 索引的元素
        while (l <= r) {
            int p = partition2(arr, l, r, random);
            if (k == p) return arr[p];

            if (k < p) {
                r = p - 1;
            } else {
                l = p + 1;
            }
        }

        throw new RuntimeException("No Solution");
    }

    /**
     * 递归
     */
    public static <E extends Comparable<E>> E selectKR(E[] arr, int k) {
        Random random = new Random();
        return processKR(arr, 0, arr.length - 1, arr.length - k, random);
    }

    public static <E extends Comparable<E>> E processKR(E[] arr, int l, int r, int k, Random random) {
        int p = partition2(arr, l, r, random);
        if (k == p) return arr[p];
        if (k < p) return processKR(arr, l, p - 1, k, random);
        return processKR(arr, p + 1, r, k, random);
    }

    public static <E extends Comparable<E>> int partition2(E[] arr, int l, int r, Random random) {
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

    private static <E> void swap(E[] arr, int a, int b) {
        E k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Integer[] arr = ArrayGenerator.generateRandomArray(10, 100);
            System.out.println(selectKR(arr, 3));
            System.out.println(selectK(arr, 3));

            Arrays.sort(arr, Comparator.comparingInt(a -> a));
            System.out.println(Arrays.toString(arr));
        }
    }
}
