package week5;

import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Arrays;

public class VS {
    public static void main(String[] args) {
        int n = 100000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest("InsertionSort", arr1);
        SortingHelper.sortTest("SelectionSort", arr2);
        SortingHelper.sortTest("MergeSort4", arr3);
        SortingHelper.sortTest("MergeSortBU", arr4);
    }
}
