package week9;

//import practice.other.MaxHeap;

import java.util.Arrays;
import java.util.Random;

public class TestMaxHeap {

    private static boolean verify(MaxHeap<Integer> maxHeap) {
        int[] arr = new int[maxHeap.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]) {
                return false;
            }
        }
        return true;
    }

    private static void testHeap(Integer[] arr, boolean isHeapify) {
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(arr);
        } else {
            maxHeap = new MaxHeap<>();
            for (Integer i : arr) {
                maxHeap.add(i);
            }
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        if (!verify(maxHeap)) {
            throw new RuntimeException("Error!");
        }
        System.out.println("time: " + time + " s");
    }

    /**
     * <p>比较两种不同的建堆方式</p>
     * <p>方式一: 对每个元素进行 siftUp</p>
     * <p>方式二: 从最后一个非叶子节点开始, 倒着进行 siftDown</p>
     */
    public static void main(String[] args) {
        int n = 1000000;

        Integer[] arr1 = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr1[i] = (random.nextInt(Integer.MAX_VALUE));
        }
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        testHeap(arr1, false); // O(n * logn)
        testHeap(arr2, true);  // O(n)
    }
}
