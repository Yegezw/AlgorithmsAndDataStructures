package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 164 - 最大间距: https://leetcode-cn.com/problems/maximum-gap/
 */
public class S164_MaximumGap {

    public static int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        if (nums.length == 2) {
            int res = nums[1] - nums[0];
            Arrays.sort(nums);
            return res;
        }

        int minV = Integer.MAX_VALUE;
        int maxV = Integer.MIN_VALUE;
        for (int num : nums) {
            minV = Math.min(minV, num);
            maxV = Math.max(maxV, num);
        }

        int B = maxV - minV + 1;
        int[] cnt = new int[B];
        for (int num : nums) {
            cnt[num - minV]++;
        }

        int[] index = new int[B + 1];
        for (int i = 0; i < B; i++) {
            index[i + 1] = index[i] + cnt[i];
        }

        int[] temp = new int[nums.length];
        for (int num : nums) {
            int p = index[num - minV];
            temp[p] = num;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < B; i++) {
            if (cnt[i] != 0) list.add(i);
        }
        int res = 0;
        for (int i = 1; i < list.size(); i++) {
            res = Math.max(res, list.get(i) - list.get(i - 1));
        }

        System.arraycopy(temp, 0, nums, 0, temp.length);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {100, 3, 2, 1};
        System.out.println(maximumGap(nums));
        System.out.println(Arrays.toString(nums));
    }
}
