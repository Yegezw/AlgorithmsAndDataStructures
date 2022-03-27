package week7.floor;

public class UpperFloor {

    /**
     * <p>没有 target, 返回 < target 的最右边的索引</p>
     * <p>有 target, 返回 target 最大索引</p>
     * <p><= target 的最右边的索引</p>
     */
    public static <E extends Comparable<E>> int upperFloor(E[] arr, E target) {
        int l = -1;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (arr[mid].compareTo(target) <= 0) l = mid;
            else r = mid - 1;
        }

        return l;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        System.out.println(upperFloor(arr, 2));
        System.out.println(upperFloor(arr, 3));
    }

}
