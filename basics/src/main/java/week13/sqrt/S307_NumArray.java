package week13.sqrt;

import java.util.Arrays;

/**
 * 307 - 区域和检索 - 数组可修改: https://leetcode-cn.com/problems/range-sum-query-mutable/
 * <p>通过 sqrt 分解解决</p>
 */
public class S307_NumArray {

    private int[] data;
    private int[] blocks;
    private int N;  // 元素总数
    private int B;  // 每组元素个数
    private int Bn; // 组数

    public S307_NumArray(int[] nums) {
        N = nums.length;
        if (N == 0) return;
        data = Arrays.copyOf(nums, N);

        B = (int) Math.sqrt(N);
        Bn = N / B + (N % B == 0 ? 0 : 1);

        blocks = new int[Bn];
        for (int i = 0; i < nums.length; i++) {
            blocks[i / B] += nums[i];
        }
    }

    public void update(int index, int val) {
        if (index < 0 || index >= N) return;
        int diff = val - data[index];
        data[index] = val;
        blocks[index / B] += diff;
    }

    public int sumRange(int l, int r) {
        if (l < 0 || l >= N || r < 0 || r >= N || l > r) {
            return 0;
        }

        int sum = 0;
        int bstart = l / B;
        int bend = r / B;

        if (Math.abs(bstart - bend) <= 1) {
            for (int i = l; i <= r; i++) {
                sum += data[i];
            }
        } else {
            // bstart 组
            for (int i = l; i < (bstart + 1) * B; i++) {
                sum += data[i];
            }
            // [bstart + 1 ... bend - 1] 组
            for (int i = bstart + 1; i < bend; i++) {
                sum += blocks[i];
            }
            // bend 组
            for (int i = bend * B; i <= r; i++) {
                sum += data[i];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        S307_NumArray x = new S307_NumArray(nums);

        System.out.println(x.sumRange(0, 2)); // 1

        x.update(1, 2);

        System.out.println(x.sumRange(0, 2)); // 3
        System.out.println(x.sumRange(3, 5)); // -4
        System.out.println(x.sumRange(0, 5)); // -1
    }
}
