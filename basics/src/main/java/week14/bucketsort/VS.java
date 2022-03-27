package week14.bucketsort;

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

        System.out.println("开始");
        SortingHelper.sortTest("BucketSort1", arr1);
        SortingHelper.sortTest("BucketSort2", arr2);
        SortingHelper.sortTest("QuickSortD", arr3);
        SortingHelper.sortTest("MergeSort4", arr4);
    }
}
