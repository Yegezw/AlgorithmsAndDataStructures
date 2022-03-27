package practice.binary;

public class Floor {

    /**
     * < target 的最大索引
     */
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

    /**
     * < target 的最大索引
     * <p>
     * = target 的最大索引
     * <p>
     * <= target 的最大索引
     */
    public static <E extends Comparable<E>> int upperFloor(E[] arr, E target) {
        int l = -1;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (arr[mid].compareTo(target) <= 0) l = mid;
            else r = mid - 1;
        }

        return  l;
    }

    /**
     * < target 的最大索引
     * <br>
     * = target 的最小索引
     */
    public static <E extends Comparable<E>> int lowerFloor(E[] arr, E target) {
        int lower = lower(arr, target);
        if (lower + 1 < arr.length && arr[lower + 1].compareTo(target) == 0) return lower + 1;
        return lower;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        System.out.println(lower(arr, 3));

        System.out.println(upperFloor(arr, 3));
        System.out.println(upperFloor(arr, 2));

        System.out.println(lowerFloor(arr, 3));
        System.out.println(lowerFloor(arr, 2));
    }

}
