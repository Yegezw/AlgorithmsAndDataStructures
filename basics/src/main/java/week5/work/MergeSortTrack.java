package week5.work;

import java.util.Arrays;

/**
 * 归并排序: O(N*logN)
 */
public class MergeSortTrack {

    private MergeSortTrack() {
    }

    public static <E extends Comparable<E>> void mergeSort(E[] arr) {
        process(arr, 0, arr.length - 1, 1);
    }

    private static <E extends Comparable<E>> void process(E[] arr, int l, int r, int depth) {
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("排序数组 " + printArr(arr, l, r) + String.format(" l = %d, r = %d", l, r));

        if (l >= r) {
            System.out.print(depthString);
            System.out.println("返回");
            return;
        }

        int mid = l + (r - l) / 2;
        process(arr, l, mid, depth + 1);
        System.out.print(depthString);
        System.out.println("左边数组 " + printArr(arr, l, mid) + String.format(" l = %d, r = %d", l, mid));

        process(arr, mid + 1, r, depth + 1);
        System.out.print(depthString);
        System.out.println("右边数组 " + printArr(arr, mid + 1, r) + String.format(" l = %d, r = %d", mid + 1, r));


        merge(arr, l, mid, r);
        System.out.print(depthString);
        System.out.println("归并数组 " + printArr(arr, l, r) + String.format(" l = %d, mid = %d, r = %d", l, mid, r));
    }

    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);
        int p1 = 0;
        int p2 = mid + 1 - l;
        int i = l;
        while (p1 <= mid - l && p2 <= r - l) {
            arr[i++] = (temp[p1].compareTo(temp[p2]) <= 0) ? temp[p1++] : temp[p2++];
        }
        while (p1 <= mid - l) arr[i++] = temp[p1++];
        while (p2 <= r - l) arr[i++] = temp[p2++];
    }

    /**
     * 生成深度字符串
     */
    private static String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        sb.append(depth).append("-");
        return sb.toString();
    }

    /**
     * 输出 arr[start, end]
     */
    private static <E> String printArr(E[] arr, int start, int end) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = start; i <= end; i++) {
            sb.append(arr[i]);
            if (i != end) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 3, 7, 2, 8, 9, 4, 6, 5};
        System.out.println("排序前: " + Arrays.toString(arr));

        mergeSort(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
    }
}
