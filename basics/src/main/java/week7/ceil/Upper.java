package week7.ceil;

public class Upper {

    /**
     * > target 的最左边的索引
     */
    public static <E extends Comparable<E>> int upper(E[] arr, E target) {
        int l = 0;
        int r = arr.length;

        // 在 arr[l, r] 的范围内搜索大于 target 的最小值的索引
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) > 0) r = mid; // 已经 arr[mid] > target 了, 继续往左找最小索引
            else l = mid + 1;
        }

        return l;
    }

    public static <E extends Comparable<E>> int upperR(E[] arr, E target) {
        return upperR(arr, 0, arr.length, target);
    }

    /**
     * 在 arr[l, r] 的范围内搜索大于 target 的最小值的索引
     */
    private static <E extends Comparable<E>> int upperR(E[] arr, int l, int r, E target) {
        if (l == r) return r;
        int mid = l + (r - l) / 2;
        if (arr[mid].compareTo(target) > 0) {
            return upperR(arr, l, mid, target);
        }
        return upperR(arr, mid + 1, r, target);
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 9};
        int search = upper(arr, 5);
        System.out.println(search);
    }

}
