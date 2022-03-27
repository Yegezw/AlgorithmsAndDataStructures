package practice.sort;

import util.ArrayGenerator;
import util.helper.SortingHelper;

public class ShellSort {

    private ShellSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        int h = arr.length / 2;
        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                E k = arr[i];
                int j;
                for (j = i; j - h >= 0 && k.compareTo(arr[j - h]) < 0; j -= h) {
                    arr[j] = arr[j - h];
                }

                arr[j] = k;
            }

            h /= 2;
        }
    }

    public static void main(String[] args) {
        int n = 100000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        sort(arr);
        System.out.println(SortingHelper.isSorted(arr));
    }
}
