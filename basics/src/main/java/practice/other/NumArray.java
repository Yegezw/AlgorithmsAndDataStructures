package practice.other;

import java.util.Arrays;

/**
 * SQRT 分解
 */
public class NumArray {

    private int[] data;
    private int[] blocks;
    private int N;
    private int B;
    private int Bn;

    public NumArray(int[] arr) {
        N = arr.length;
        data = Arrays.copyOf(arr, N);

        B = (int) Math.sqrt(N);
        Bn = N / B + (N % B == 0 ? 0 : 1);

        blocks = new int[Bn];
        for (int i = 0; i < N; i++) {
            blocks[i / B] += data[i];
        }
    }

    public int sumRange(int l, int r) {
        if (l < 0 || l >= N || r < 0 || r >= N || l > r) {
            throw new IllegalArgumentException("index is error");
        }

        int bstart = l / B;
        int bend = r / B;

        int res = 0;
        if (bend - bstart <= 1) {
            for (int i = l; i <= r; i++) {
                res += data[i];
            }
            return res;
        }

        for (int i = l; i < (bstart + 1) * B; i++) {
            res += data[i];
        }
        for (int i = bstart + 1; i < bend; i++) {
            res += blocks[i];
        }
        for (int i = bend * B; i <= r; i++) {
            res += data[i];
        }
        return res;
    }

    public void update(int index, int value) {
        if (index < 0 || index >= N) {
            throw new IllegalArgumentException("index is error");
        }

        if (data[index] == value) return;

        data[index] = value;

        int b = index / B;
        blocks[b] = data[b * B];
        for (int i = b * B + 1; i < Math.min((b + 1) * B, N); i++) {
            blocks[b] += data[i];
        }
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray x = new NumArray(nums);

        System.out.println(x.sumRange(0, 2)); // 1

        x.update(1, 2);

        System.out.println(x.sumRange(0, 2)); // 3
        System.out.println(x.sumRange(3, 5)); // -4
        System.out.println(x.sumRange(0, 5)); // -1
    }
}
