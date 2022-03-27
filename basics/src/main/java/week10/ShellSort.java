package week10;

import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Arrays;

public class ShellSort {

    private ShellSort() {
    }

    public static <E extends Comparable<E>> void sort1(E[] arr) {
        int h = arr.length / 2; // h 代表子数组的间隔
        while (h >= 1) {
            // start 从 arr[0 ... h - 1] 是每一个子数组起始的元素; 对以 start 为起始, 间隔为 h 的数组进行插入排序
            for (int start = 0; start < h; start++) {
                // 对 arr[start, start + h, start + 2h, start + 3h ...] 进行插入排序
                for (int i = start + h; i < arr.length; i += h) {
                    E k = arr[i];
                    int j;
                    for (j = i; j - h >= start && k.compareTo(arr[j - h]) < 0; j -= h) {
                        arr[j] = arr[j - h];
                    }
                    arr[j] = k;
                }
            }

            h /= 2;
        }
    }

    /**
     * 代码优化
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        int h = arr.length / 2;
        while (h >= 1) {
            // 对 arr[h, n - 1] 进行插入排序
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

    /**
     * 性能优化: 新的步长序列
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        int h = 1;
        while (h < arr.length) h = h * 3 + 1;
        // 1, 4, 13, 40 ...
        while (h >= 1) {
            // 对 arr[h, n) 进行插入排序
            for (int i = h; i < arr.length; i++) {
                E k = arr[i];
                int j;
                for (j = i; j - h >= 0 && k.compareTo(arr[j - h]) < 0; j -= h) {
                    arr[j] = arr[j - h];
                }
                arr[j] = k;
            }

            h /= 3;
        }
    }

    public static void main(String[] args) {
        int n = 5000000;

        Integer[] arr1 = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        SortingHelper.sortTest("ShellSort1", arr1);
        SortingHelper.sortTest("ShellSort2", arr2);
    }
}
