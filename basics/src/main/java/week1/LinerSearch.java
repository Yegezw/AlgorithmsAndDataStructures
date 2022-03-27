package week1;

import util.ArrayGenerator;

/**
 * 线性查找法: O(n)
 */
public class LinerSearch {

    private LinerSearch() {
    }

    public static <T> int search(T[] data, T target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] dataSize = {1000000, 10000000};

        for (int n : dataSize) {
            Integer[] data = ArrayGenerator.generateOrderArray(n);

            long startTime = System.nanoTime();
            for (int k = 0; k < 100; k++) {
                search(data, n);
            }
            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("n = " + n + ", 100 runs: " + time + " s");
        }
    }
}
