package leetcode;

import java.util.Arrays;

/**
 * 307 - 区域和检索 - 数组可修改: https://leetcode-cn.com/problems/range-sum-query-mutable/
 * <p>通过前缀和解决</p>
 * <p>超出时间限制</p>
 */
public class S307_1_NumArray {

    private final int[] arr;
    private final int[] sum; // sum[i] 代表 arr[0 ... i] 的和

    public S307_1_NumArray(int[] nums) {
        arr = Arrays.copyOf(nums, nums.length);
        sum = new int[nums.length];

        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
    }

    public void update(int index, int val) {
        int diff = val - arr[index];
        arr[index] = val;
        for (int i = index; i < sum.length; i++) {
            sum[i] += diff;
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0) return sum[right];
        return sum[right] - sum[left - 1];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        S307_1_NumArray x = new S307_1_NumArray(nums);

        System.out.println(x.sumRange(0, 2)); // 1

        x.update(1, 2);

        System.out.println(x.sumRange(0, 2)); // 3
        System.out.println(x.sumRange(3, 5)); // -4
        System.out.println(x.sumRange(0, 5)); // -1
    }
}
