package leetcode.jzof;

import java.util.Arrays;
import java.util.Random;

/**
 * 剑指 Offer 40 - 最小的 K 个数: https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * <p>求数组升序排序好后, 从左往右数共 K 个元素</p>
 */
public class S40_JZOF_1_GetLeastNumbers {

    /**
     * 基于快排 2 解决
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) return new int[0];
        Random random = new Random();
        return process(arr, 0, arr.length - 1, random, k - 1);
    }

    private int[] process(int[] arr, int l, int r, Random random, int k) {
        int p = partition2(arr, l, r, random);
        if (k == p) {
            return Arrays.copyOf(arr, k + 1);
        }
        if (k < p) return process(arr, l, p - 1, random, k);
        return process(arr, p + 1, r, random, k);
    }

    private int partition2(int[] arr, int l, int r, Random random) {
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);
        int v = arr[l];

        // arr[l + 1, p1] < v, arr[p2, r] > v
        int p1 = l + 1;
        int p2 = r;
        while (true) {
            while (p1 <= p2 && arr[p1] < v) p1++; // arr[p1] >= v
            while (p1 <= p2 && arr[p2] > v) p2--; // arr[p2] <= v

            if (p1 >= p2) break;

            swap(arr, p1, p2);
            p1++;
            p2--;
        }
        swap(arr, l, p2);
        return p2;
    }

    private void swap(int[] arr, int a, int b) {
        if (a == b) return;
        int k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    public static void main(String[] args) {
        S40_JZOF_1_GetLeastNumbers x = new S40_JZOF_1_GetLeastNumbers();
        int[] arr = {3, 2, 1};
        System.out.println(Arrays.toString(x.getLeastNumbers(arr, 2)));
    }

}
