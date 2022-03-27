package week7.work;

public class BinarySearch2 {

    /**
     * 用 >= target 最小索引的思路实现二分查找法
     */
    public static <E extends Comparable<E>> int search(E[] arr, E target) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) >= 0) r = mid;
            else l = mid + 1;
        }

        // l 是 >= target 的最小值索引, 注意 l 的取值范围是 [0...arr.length]
        // 如果 data[l] == target, 则返回 l, 否则返回 -1
        if (l < arr.length && arr[l].compareTo(target) == 0) return l;
        else return -1;
    }
}
