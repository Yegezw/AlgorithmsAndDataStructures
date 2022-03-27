package leetcode;

import java.util.Arrays;

/**
 * 875 - 爱吃香蕉的珂珂: https://leetcode-cn.com/problems/koko-eating-bananas/
 */
public class S875_MinEatingSpeed {

    /**
     * piles = [3,6,7,11], H = 8
     * <br>
     * 输出: 4
     */
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = Arrays.stream(piles).max().getAsInt();

        // arr[l, r] 代表吃香蕉的速度
        // 二分搜索 eatingTime(k) <= H 最大的 eatingTime 对应 k
        while (l < r) {
            int mid = l + (r - l) / 2;
            int curH = eatingTime(piles, mid);
            if (curH <= h) r = mid;
            else l = mid + 1;
        }

        return l;
    }

    private int eatingTime(int[] piles, int k) {
        int res = 0;
        for (int pile : piles) {
            res += pile / k + (pile % k > 0 ? 1 : 0);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] piles = {30, 11, 23, 4, 20}; // 30
        System.out.println(new S875_MinEatingSpeed().minEatingSpeed(piles, 5));
    }
}
