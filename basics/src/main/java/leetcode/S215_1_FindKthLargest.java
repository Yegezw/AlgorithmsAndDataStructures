package leetcode;

import java.util.Random;

/**
 * 215 - 数组中的第 K 个最大元素: https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * <p>求数组升序排序好后, 从右往左数第 K 个元素</p>
 * <p>即从左往右数第 length - K 个元素</p>
 */
public class S215_1_FindKthLargest {

    public int findKthLargest(int[] arr, int k) {
        Random random = new Random();
        return process(arr, 0, arr.length - 1, random, arr.length - k);
    }

    private int process(int[] arr, int l, int r, Random random, int k) {
        int p = partition2(arr, l, r, random);
        if (k == p) return arr[p];
        if (k < p) return process(arr, l, p - 1, random, k);
        return process(arr, p + 1, r, random, k);
    }

    private int partition2(int[] arr, int l, int r, Random random) {
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, l, p);
        int v = arr[l];

        // [l + 1, p1] < v, [p2, r] > v
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
        S215_1_FindKthLargest x = new S215_1_FindKthLargest();
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6}; // k = 4, res = 4
        System.out.println(x.findKthLargest(arr, 4));
    }

}
