package practice.binary;

public class BinarySearch {

    /**
     * 非递归
     */
    public static <E extends Comparable<E>> int binarySearch(E[] arr, E target) {
        int l = 0;
        int r = arr.length - 1;

        // arr[l, r] 查找 target
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) == 0) return mid;
            if (arr[mid].compareTo(target) > 0) r = mid - 1;
            else l = mid + 1;
        }

        return -1;
    }

    /**
     * 递归
     */
    public static <E extends Comparable<E>> int searchR(E[] arr, E target) {
        return searchR(arr, 0, arr.length - 1, target);
    }

    public static <E extends Comparable<E>> int searchR(E[] arr, int l, int r, E target) {
        if (l > r) return -1;
        int mid = l + (r - l) / 2;
        if (arr[mid].compareTo(target) == 0) return mid;
        if (arr[mid].compareTo(target) > 0) return searchR(arr, l, mid - 1, target);
        else return searchR(arr, l + 1, r, target);
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 3, 4, 8, 9, 11, 26, 29, 30};
        int target = -1;
        System.out.println(binarySearch(arr, target));
        System.out.println(searchR(arr, target));
    }

}
