package practice.sort;

import util.ArrayGenerator;
import util.helper.SortingHelper;

import java.util.Arrays;

public class LSDSort {

    private LSDSort() {
    }

    public static void sort(String[] arr, int W) {
        for (String s : arr) {
            if (s.length() != W) {
                throw new IllegalArgumentException("All string length must be same!");
            }
        }

        // 字符串范围 [0, 255]
        int R = 256;
        int[] cnt = new int[R];
        int[] index = new int[R + 1];
        String[] temp = new String[arr.length];

        for (int r = W - 1; r >= 0; r--) {
            Arrays.fill(cnt, 0);
            for (String s : arr) {
                cnt[s.charAt(r)]++;
            }

            for (int i = 0; i < R; i++) {
                index[i + 1] = index[i] + cnt[i];
            }

            for (String s : arr) {
                char c = s.charAt(r);
                temp[index[c]] = s;
                index[c]++;
            }

            System.arraycopy(temp, 0, arr, 0, temp.length);
        }
    }

    public static void main(String[] args) {
        int n = 10000;
        int W = 20;
        String[] arr = ArrayGenerator.generateRandomSameLengthStringArray(n, W);

        sort(arr, W);

        System.out.println(SortingHelper.isSorted(arr));
    }
}
