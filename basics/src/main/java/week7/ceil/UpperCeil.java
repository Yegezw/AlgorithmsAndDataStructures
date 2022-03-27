package week7.ceil;

public class UpperCeil {

    /**
     * <p>没有 target, 返回 > target 的最小值的索引</p>
     * <p>有 target, 返回 target 的最大索引</p>
     */
    public static <E extends Comparable<E>> int upperCeil(E[] arr, E target) {
        int upper = upper(arr, target);
        if (upper - 1 >= 0 && arr[upper - 1].compareTo(target) == 0) {
            return upper - 1;
        }
        return upper;
    }

    public static <E extends Comparable<E>> int upper(E[] arr, E target) {
        int l = 0;
        int r = arr.length;

        // 在 arr[l, r] 的范围内搜索大于 target 的最小值的索引
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) > 0) r = mid;
            else l = mid + 1;
        }

        return l;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        System.out.println(upperCeil(arr, 3));
    }

}
