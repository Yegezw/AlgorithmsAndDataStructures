package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 215 - 数组中的第 K 个最大元素: https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * <p>求数组升序排序好后, 从右往左数第 K 个元素</p>
 * <p>即从左往右数第 length - K 个元素</p>
 */
public class S215_2_FindKthLargest {

    public int findKthLargest1(int[] arr, int k) {
        int[] temp = Arrays.copyOf(arr, k); // 用 temp 来存放最大的 K 个数

        // 使 temp 变成最小堆
        for (int i = (k - 2) / 2; i >= 0; i--) {
            siftDown(temp, i);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] > temp[0]) {
                temp[0] = arr[i];
                siftDown(temp, 0);
            }
        }

        return temp[0];
    }

    /**
     * 维护最小堆
     */
    private void siftDown(int[] arr, int index) {
        while (index * 2 + 1 < arr.length) {
            int smaller = index * 2 + 1;
            if (smaller + 1 < arr.length && arr[smaller + 1] < arr[smaller]) smaller++;

            if (arr[index] <= arr[smaller]) break;
            swap(arr, index, smaller);
            index = smaller;
        }
    }

    private void swap(int[] arr, int a, int b) {
        int k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    /**
     * 使用 java 自带的优先队列解决(默认为最小堆)
     */
    public int findKthLargest2(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (!pq.isEmpty() && arr[i] > pq.peek()) {
                pq.remove();
                pq.add(arr[i]);
            }
        }

        return pq.peek();
    }

    public static void main(String[] args) {
        S215_2_FindKthLargest x = new S215_2_FindKthLargest();
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6}; // k = 4, res = 4
        System.out.println(x.findKthLargest1(arr, 4));
        System.out.println(x.findKthLargest2(arr, 4));
    }
}
