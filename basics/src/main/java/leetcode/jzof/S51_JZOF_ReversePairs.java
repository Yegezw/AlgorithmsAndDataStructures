package leetcode.jzof;

/**
 * 剑指 Offer 51 - 数组中的逆序对: https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class S51_JZOF_ReversePairs {

    /**
     * 暴力解法
     */
    public static int reversePairs1(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 归并解法
     */
    public static int reversePairs2(int[] nums) {
        int[] temp = new int[nums.length];
        return process(nums, 0, nums.length - 1, temp);
    }

    public static int process(int[] arr, int l, int r, int[] temp) {
        if (l >= r) return 0;
        int mid = l + (r - l) / 2;
        int left = process(arr, l, mid, temp);
        int right = process(arr, mid + 1, r, temp);
        if (arr[mid] > arr[mid + 1]) {
            return left + right + merge(arr, l, mid, r, temp);
        }
        return left + right;
    }

    public static int merge(int[] arr, int l, int mid, int r, int[] temp) {
        int res = 0;
        System.arraycopy(arr, l, temp, l, r - l + 1);
        int p1 = l;
        int p2 = mid + 1;
        int i = l;
        while (p1 <= mid && p2 <= r) {
            if (temp[p1] <= temp[p2]) {
                arr[i++] = temp[p1++];
            } else {
                //输出逆序数对
                //for (int j = p1; j <= mid; j++) {
                //    System.out.println(String.format("[%d, %d]", arr[j], temp[p2]));
                //}
                res += mid - p1 + 1; // 后面区间的元素归并上来时, 和前面区间剩余元素形成逆序数对
                arr[i++] = temp[p2++];
            }
        }
        while (p1 <= mid) arr[i++] = temp[p1++];
        while (p2 <= r) arr[i++] = temp[p2++];
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {7, 5, 6, 4};
        System.out.println(reversePairs1(nums));
        System.out.println(reversePairs2(nums));
    }
}
