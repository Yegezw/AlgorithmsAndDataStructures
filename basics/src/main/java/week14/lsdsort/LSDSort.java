package week14.lsdsort;

import java.util.Arrays;

/**
 * RadixSort
 * <p>Least Significant Digit 字符串排序算法</p>
 * <p>String[] arr 中, 每个字符串的长度应该相等</p>
 */
public class LSDSort {

    private LSDSort() {
    }

    /**
     * O(W * (n + R)) => O(n)
     */
    public static void sort(String[] arr, int W) {
        for (String s : arr) {
            if (s.length() != W) {
                throw new RuntimeException("All Strings' length must be the same.");
            }
        }

        // 字符范围 [0, 256)
        int R = 256;
        int[] cnt = new int[R];
        int[] index = new int[R + 1];
        String[] temp = new String[arr.length];

        for (int r = W - 1; r >= 0; r--) {
            // 基于第 r 位字符进行计数排序
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
        String[] arr = {"BCA", "CAB", "ACB", "BAC", "ABC", "CBA"};
        LSDSort.sort(arr, 3);
        for (String s : arr) {
            System.out.println(s);
        }
    }

}
