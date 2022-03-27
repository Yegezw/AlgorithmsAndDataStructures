package practice.sort;

import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {

    private BucketSort() {
    }

    /**
     * 类似 MSDSort
     */
    public static void sort1(Integer[] arr, int B) {
        if (B <= 1) {
            throw new IllegalArgumentException("B must be > 1");
        }
        Integer[] temp = new Integer[arr.length];
        sort1(arr, 0, arr.length - 1, B, temp);
    }

    private static void sort1(Integer[] arr, int left, int right, int B, Integer[] temp) {
        if (left >= right) return;

        int minV = Integer.MAX_VALUE;
        int maxV = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            minV = Math.min(minV, arr[i]);
            maxV = Math.max(maxV, arr[i]);
        }
        if (minV == maxV) return;

        int d = (maxV - minV + 1) / B + ((maxV - minV + 1) % B == 0 ? 0 : 1);
        int[] cnt = new int[B];
        int[] index = new int[B + 1];

        for (int i = left; i <= right; i++) {
            cnt[(arr[i] - minV) / d]++;
        }

        for (int i = 0; i < B; i++) {
            index[i + 1] = index[i] + cnt[i];
        }

        for (int i = left; i <= right; i++) {
            int p = (arr[i] - minV) / d;
            temp[left + index[p]] = arr[i];
            index[p]++;
        }

        System.arraycopy(temp, left, arr, left, right - left + 1);

        // index[0, B] 中的 index[0, B - 1]
        sort1(arr, left, left + index[0] - 1, B, temp);
        for (int i = 0; i + 1 <= B - 1; i++) {
            sort1(arr, left + index[i], left + index[i + 1] - 1, B, temp);
        }
    }

    /**
     * 桶排序 2
     */
    public static void sort2(Integer[] arr, int c) {
        if (c <= 1) {
            throw new IllegalArgumentException("c must be > 1");
        }

        int minV = Integer.MAX_VALUE;
        int maxV = Integer.MIN_VALUE;
        for (int num : arr) {
            minV = Math.min(minV, num);
            maxV = Math.max(maxV, num);
        }
        if (minV == maxV) return;

        int B = (maxV - minV + 1) / c + ((maxV - minV + 1) % c == 0 ? 0 : 1);
        LinkedList<Integer>[] buckets = new LinkedList[B];
        for (int i = 0; i < B; i++) {
            buckets[i] = new LinkedList<>();
        }

        for (int num : arr) {
            buckets[(num - minV) / c].add(num);
        }

        for (int i = 0; i < B; i++) {
            Collections.sort(buckets[i]);
        }

        int index = 0;
        for (int i = 0; i < B; i++) {
            for (int num : buckets[i]) {
                arr[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        int n = 10000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);

        //sort1(arr, 200);
        sort2(arr, 100);

        System.out.println(SortingHelper.isSorted(arr));
    }
}
