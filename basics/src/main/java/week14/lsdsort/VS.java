package week14.lsdsort;

import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Arrays;

public class VS {

    public static void main(String[] args) {
        int n = 100000;
        int W = 200;
        // int W = 2;

        String[] arr1 = ArrayGenerator.generateRandomSameLengthStringArray(n, W);
        String[] arr2 = Arrays.copyOf(arr1, n);

        SortingHelper.sortTest("QuickSortD", arr1);
        SortingHelper.sortTest("LSDSort", arr2);
    }
}
