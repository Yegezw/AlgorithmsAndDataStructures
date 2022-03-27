package leetcode.jzof;


import week9.queue.PriorityQueue;

import java.util.Arrays;
import java.util.Collections;

/**
 * 剑指 Offer 40 - 最小的 K 个数: https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * <p>求数组升序排序好后, 从左往右数共 K 个元素</p>
 */
public class S40_JZOF_2_GetLeastNumbers {

    /**
     * 基于优先队列(最大堆)解决
     */
    public int[] getLeastNumbers1(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.enqueue(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (!pq.isEmpty() && arr[i] < pq.getFront()) {
                pq.dequeue();
                pq.enqueue(arr[i]);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.dequeue();
        }
        return res;
    }

    /**
     * 使用 java 自带的优先队列解决(默认为最小堆)
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        java.util.PriorityQueue<Integer> pq = new java.util.PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (!pq.isEmpty() && arr[i] < pq.peek()) {
                pq.remove();
                pq.add(arr[i]);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = pq.remove();
        }
        return res;
    }

    public static void main(String[] args) {
        S40_JZOF_2_GetLeastNumbers x = new S40_JZOF_2_GetLeastNumbers();
        int[] arr = {3, 2, 1};
        System.out.println(Arrays.toString(x.getLeastNumbers1(arr, 2)));
        System.out.println(Arrays.toString(x.getLeastNumbers2(arr, 2)));
    }
}
