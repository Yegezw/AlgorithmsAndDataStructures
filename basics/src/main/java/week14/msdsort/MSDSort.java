package week14.msdsort;

/**
 * RadixSort
 * <p>Most Significant Digit 字符串排序算法</p>
 * <p>String[] arr 中, 每个字符串的长度可以不相等</p>
 */
public class MSDSort {

    private MSDSort() {
    }

    /**
     * O(W * n), W 是最长的字符串长度
     */
    public static void sort(String[] arr) {
        String[] temp = new String[arr.length];
        sort(arr, 0, arr.length - 1, 0, temp);
    }

    /**
     * 根据 r 位置的字符, 对 arr[left, right] 进行 MSD 基数排序
     */
    private static void sort(String[] arr, int left, int right, int r, String[] temp) {
        if (left >= right) return;

        // 字符范围 [0, 256) + 空
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

        // index[0...R + 1] 的最后一个区间无效
        // 需要遍历 index[0...R] 中所有的区间
        for (int i = 0; i + 1 <= R; i++) {
            sort(arr, left + index[i], left + index[i + 1] - 1, r + 1, temp);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"BCA", "CBAA", "AC", "BADFE", "ABC", "CBA"};
        sort(arr);
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
