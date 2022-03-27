package leetcode;

import java.util.Arrays;

/**
 * 1011 - 在 D 天内送达包裹的能力
 * <p>https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/</p>
 */
public class S1011_ShipWithinDays {

    public int shipWithinDays(int[] weights, int days) {
        int l = Arrays.stream(weights).max().getAsInt();
        int r = Arrays.stream(weights).sum();

        // arr[l, r] 代表运载能力
        // 二分搜索 shippingDays(k) <= D 最大的 shippingDays 对应 k
        while (l < r) {
            int mid = l + (r - l) / 2;
            int curDay = shippingDays(weights, mid);
            if (curDay <= days) r = mid;
            else l = mid + 1;
        }

        return l;
    }

    private int shippingDays(int[] weights, int k) {
        int cur = 0;
        int res = 0;
        for (int weight : weights) {
            if (cur + weight <= k) cur += weight;
            else {
                res++;
                cur = weight;
            }
        }
        res++; // 注意
        return res;
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 1, 1};
        System.out.println(new S1011_ShipWithinDays().shipWithinDays(weights, 4));
    }

}
