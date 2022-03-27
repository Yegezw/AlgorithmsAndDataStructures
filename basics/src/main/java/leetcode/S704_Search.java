package leetcode;

/**
 * 704 - 二分查找: https://leetcode-cn.com/problems/binary-search/
 */
public class S704_Search {

    /**
     * 递归
     */
    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private int search(int[] nums, int l, int r, int target) {
        if (l > r) return -1;

        int mid = l + (r - l) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] < target) {
            return search(nums, mid + 1, r, target);
        }
        return search(nums, l, mid - 1, target);
    }

    /**
     * 非递归
     */
    public int search2(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        // 在 arr[l, r] 的范围中查找 target
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return - 1;
    }

    /**
     * 非递归
     */
    public int search3(int[] arr, int target) {
        int l = 0;
        int r = arr.length;

        // 在 arr[l, r) 的范围中查找 target
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) return mid;

            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }

        return -1;
    }

}
