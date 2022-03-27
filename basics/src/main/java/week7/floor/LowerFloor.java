package week7.floor;

public class LowerFloor {

    /**
     * <p>没有 target, 返回 < target 的最右边的索引</p>
     * <p>有 target, 返回 target 最小索引</p>
     */
    public static <E extends Comparable<E>> int lowerFloor(E[] arr, E target) {
        int lower = lower(arr, target);
        if (lower + 1 <= arr.length - 1 && arr[lower + 1].compareTo(target) == 0) return lower + 1;
        return lower;
    }

    public static <E extends Comparable<E>> int lower(E[] arr, E target) {
        int l = -1;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (arr[mid].compareTo(target) < 0) l = mid;
            else r = mid - 1;
        }

        return l;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        System.out.println(lowerFloor(arr, 2));
        System.out.println(lowerFloor(arr, 3));
    }
}
