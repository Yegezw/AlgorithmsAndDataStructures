package util.helper;

import week10.BubbleSort;
import week10.ShellSort;
import week14.bucketsort.BucketSort;
import week14.lsdsort.LSDSort;
import week14.msdsort.MSDSort;
import week2.InsertionSort;
import week2.SelectionSort;
import week5.MergeSort;
import week5.MergeSortBU;
import week6.QuickSort;
import week6.QuickSortD;
import week6.QuickSortT;
import week9.sort.HeapSort;

public class SortingHelper {

    private SortingHelper() {
    }

    /**
     * 判断当前数组是否已排序
     */
    public static <T extends Comparable<T>> boolean isSorted(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 排序测试
     */
    public static <T extends Comparable<T>> void sortTest(String sortName, T[] arr) {
        long startTime = System.nanoTime();
        switch (sortName) {
            case "SelectionSort":
                SelectionSort.sort1(arr);
                break;
            case "InsertionSort":
                InsertionSort.sort1(arr);
                break;
            case "MergeSort1":
                MergeSort.mergeSort1(arr);
                break;
            case "MergeSort2":
                MergeSort.mergeSort2(arr);
                break;
            case "MergeSort3":
                MergeSort.mergeSort3(arr);
                break;
            case "MergeSort4":
                MergeSort.mergeSort4(arr);
                break;
            case "MergeSortBU":
                MergeSortBU.mergeSortBU(arr);
                break;
            case "QuickSort1":
                QuickSort.quickSort1(arr);
                break;
            case "QuickSort2":
                QuickSort.quickSort2(arr);
                break;
            case "QuickSort3":
                QuickSort.quickSort3(arr);
                break;
            case "QuickSortD":
                QuickSortD.quickSortD(arr);
                break;
            case "QuickSortT":
                QuickSortT.quickSortT(arr);
                break;
            case "HeapSort1":
                HeapSort.sort1(arr);
                break;
            case "HeapSort2":
                HeapSort.sort2(arr);
                break;
            case "BubbleSort1":
                BubbleSort.sort1(arr);
                break;
            case "BubbleSort2":
                BubbleSort.sort2(arr);
                break;
            case "BubbleSort3":
                BubbleSort.sort3(arr);
                break;
            case "ShellSort1":
                ShellSort.sort2(arr);
                break;
            case "ShellSort2":
                ShellSort.sort3(arr);
                break;
            case "LSDSort":
                String[] strArr = (String[]) arr;
                if (strArr.length == 0) throw new IllegalArgumentException("arr can not be empty!");
                LSDSort.sort(strArr, strArr[0].length());
                break;
            case "MSDSort":
                MSDSort.sort((String[]) arr);
                break;
            case "BucketSort1":
                BucketSort.sort1((Integer[]) arr, 200);
                break;
            case "BucketSort2":
                BucketSort.sort2((Integer[]) arr, 100);
                break;
        }
        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        if (!isSorted(arr)) {
            throw new RuntimeException(sortName + " failed!");
        }
        System.out.println(String.format("%s, n = %d : %f s", sortName, arr.length, time));
    }

}
