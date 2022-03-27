package week2;

import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Arrays;

public class VS {
    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};

        for (int n : dataSize) {
            System.out.println("Random Array:");
            Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
            SortingHelper.sortTest("InsertionSort", arr1);
            SortingHelper.sortTest("SelectionSort", arr2);

            System.out.println("Order Array:");
            Integer[] arr3 = ArrayGenerator.generateOrderArray(n);
            Integer[] arr4 = Arrays.copyOf(arr3, arr3.length);
            SortingHelper.sortTest("InsertionSort", arr3);
            SortingHelper.sortTest("SelectionSort", arr4);

            System.out.println("-------------------------------------");
        }
    }
}
