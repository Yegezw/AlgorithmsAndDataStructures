package week7.floor;

public class Lower {

    /**
     * < target 的最右边的索引
     */
    public static <E extends Comparable<E>> int lower(E[] arr, E target) {
        int l = -1;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l + 1) / 2;               // mid 上取整
            if (arr[mid].compareTo(target) < 0) l = mid; // 已经 arr[mid] < target 了, 继续往右找最大索引
            else r = mid - 1;
        }
        return l;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        System.out.println(lower(arr, 2));
    }
}
