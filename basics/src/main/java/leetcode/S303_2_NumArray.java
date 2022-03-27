package leetcode;

/**
 * 303 - 区域和检索 - 数组不可变: https://leetcode-cn.com/problems/range-sum-query-immutable/
 * <p>通过前缀和解决</p>
 */
public class S303_2_NumArray {

    private final int[] sum; // sum[i] 代表 arr[0 ... i] 的和

    public S303_2_NumArray(int[] nums) {
        sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0) return sum[right];
        return sum[right] - sum[left - 1];
    }
}
