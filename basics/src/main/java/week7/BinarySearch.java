package week7;

public class BinarySearch {

    private BinarySearch() {
    }

    public static <E extends Comparable<E>> int search(E[] arr, E target) {
        int l = 0;
        int r = arr.length - 1;

        // 在 arr[l, r] 的范围中查找 target
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) == 0) {
                return mid;
            } else if (arr[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public static <E extends Comparable<E>> int searchR(E[] arr, E target) {
        return searchR(arr, 0, arr.length - 1, target);
    }

    private static <E extends Comparable<E>> int searchR(E[] arr, int l, int r, E target) {
        // 在 arr[l, r] 的范围中查找 target
        if (l > r) return -1;

        int mid = l + (r - l) / 2;
        if (arr[mid].compareTo(target) == 0) {
            return mid;
        }
        if (arr[mid].compareTo(target) < 0) {
            return searchR(arr, mid + 1, r, target);
        }
        return searchR(arr, l, mid - 1, target);
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 3, 5, 7, 9};
        int search = search(arr, 9);
        System.out.println(search);
    }

}
