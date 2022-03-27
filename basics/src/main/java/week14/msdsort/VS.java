package week14.msdsort;

import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Arrays;

public class VS {

    public static void main(String[] args) {
        int n = 100000;
        int bound = 20;
         //int bound = 2;

        String[] arr1 = ArrayGenerator.generateRandomStringArray(n, bound);
        String[] arr2 = Arrays.copyOf(arr1, n);

        SortingHelper.sortTest("QuickSortD", arr1);
        SortingHelper.sortTest("MSDSort", arr2);
    }
}
