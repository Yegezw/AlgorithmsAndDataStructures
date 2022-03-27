package practice.binary;

public class Ceil {

    /**
     * > target 最小索引
     */
    public static <E extends Comparable<E>> int upper(E[] arr, E target) {
        // 保证 arr[l, r] 中肯定有解
        int l = 0;
        int r = arr.length;
        // 最左边
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) > 0) r = mid;
            else l = mid + 1;
        }

        return l;
    }

    /**
     * > target 最小索引
     * <p>
     * = target 最大索引
     */
    public static <E extends Comparable<E>> int upperCeil(E[] arr, E target) {
        int upper = upper(arr, target);
        if (upper - 1 >= 0 && arr[upper - 1].compareTo(target) == 0) return upper - 1;
        return upper;
    }

    /**
     * > target 最小索引
     * <p>
     * = target 最小索引
     * <p>
     * >= target 最小索引
     */
    public static <E extends Comparable<E>> int lowerCeil(E[] arr, E target) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) >= 0) r = mid;
            else l = mid + 1;
        }

        return l;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 3, 3, 5, 5};
        System.out.println(upper(arr, 3));

        System.out.println(upperCeil(arr, 3));
        System.out.println(upperCeil(arr, 2));

        System.out.println(lowerCeil(arr, 3));
        System.out.println(lowerCeil(arr, 2));
    }
}
