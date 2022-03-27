package week14.countingsort;

import java.util.Arrays;

public class SortColors {

    /**
     * 计数排序 1
     */
    public static void sortColors1(int[] nums) {
        int cnt[] = new int[3];
        for (int num : nums) {
            cnt[num]++;
        }

        // 0 [0, cnt[0])
        // 1 [cnt[0], cnt[0] + cnt[1])
        // 2 [cnt[0] + cnt[1], cnt[0] + cnt[1] + cnt[2])
        for (int i = 0; i < cnt[0]; i++) {
            nums[i] = 0;
        }
        for (int i = cnt[0]; i < cnt[0] + cnt[1]; i++) {
            nums[i] = 1;
        }
        for (int i = cnt[0] + cnt[1]; i < cnt[0] + cnt[1] + cnt[2]; i++) {
            nums[i] = 2;
        }

        int j = 0;
        for (int i = 0; i < cnt.length; i++) {
            while (cnt[i] != 0) {
                nums[j] = i;
                j++;
                cnt[i]--;
            }
        }
    }

    /**
     * 计数排序 2
     */
    public static void sortColors2(int[] nums) {
        // 处理元素取值范围是 [0, R) 的计数排序
        int R = 3;

        int[] cnt = new int[R];
        for (int num : nums) {
            cnt[num]++;
        }

        // index[] 是 cnt[] 的前缀和
        int[] index = new int[R + 1];
        for (int i = 0; i < R; i++) {
            index[i + 1] = index[i] + cnt[i];
        }

        for (int i = 0; i + 1 < index.length; i++) {
            // [index[i], index[i + 1]) 的值为 i
            for (int j = index[i]; j < index[i + 1]; j++) {
                nums[j] = i;
            }
        }
    }

    /**
     * 计数排序 n
     */
    public static void sortColorsn(int[] nums) {
        int cnt[] = new int[3];
        for (int num : nums) {
            cnt[num]++;
        }

        int j = 0;
        for (int i = 0; i < cnt.length; i++) {
            while (cnt[i] != 0) {
                nums[j++] = i;
                cnt[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors1(nums);
        System.out.println(Arrays.toString(nums));
    }
}
