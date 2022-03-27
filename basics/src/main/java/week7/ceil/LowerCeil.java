package week7.ceil;

public class LowerCeil {

    /**
     * <p>没有 target, 返回 > target 的最小值的索引</p>
     * <p>有 target, 返回 target 的最小索引</p>
     * <p>>= target 最左边的索引</p>
     */
    public static <E extends Comparable<E>> int lowerCeil(E[] arr, E target) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) >= 0) r = mid; // 已经 arr[mid] >= target 了, 继续往左找最小索引
            else l = mid + 1;
        }

        return l;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        System.out.println(lowerCeil(arr, 0));
    }
}
