package practice.sort;

import util.ArrayGenerator;
import util.helper.SortingHelper;

public class MSDSort {

    private MSDSort() {
    }

    public static void sort(String[] arr) {
        String[] temp = new String[arr.length];
        sort(arr, 0, arr.length - 1, 0, temp);
    }

    private static void sort(String[] arr, int left, int right, int r, String[] temp) {
        if (left >= right) return;

        // 字符范围 [0, 255] + 空
        int R = 256;
        int[] cnt = new int[R + 1];
        int[] index = new int[R + 2];

        for (int i = left; i <= right; i++) {
            cnt[r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1)]++;
        }

        for (int i = 0; i < R + 1; i++) {
            index[i + 1] = index[i] + cnt[i];
        }

        for (int i = left; i <= right; i++) {
            int p = r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1);
            temp[left + index[p]] = arr[i];
            index[p]++;
        }

        System.arraycopy(temp, left, arr, left, right - left + 1);

        // index[0, R + 1] 中的 index[0, R], 因为 index[R, R + 1] 所表示的区间无效
        for (int i = 0; i + 1 <= R; i++) {
            sort(arr, left + index[i], left + index[i + 1] - 1, r + 1, temp);
        }
    }

    public static void main(String[] args) {
        int n = 10000;
        int bound = 20;
        String[] arr = ArrayGenerator.generateRandomStringArray(n, bound);

        sort(arr);

        System.out.println(SortingHelper.isSorted(arr));
    }
}
