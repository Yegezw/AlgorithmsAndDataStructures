package week13.work;

import port.Merger;

import java.util.Arrays;

/**
 * 通用 SQRT 分解
 * <p>注意 merge 的元素可能为 null</p>
 * <p>注意并不是每一组的元素个数都为 B(注意更新操作)</p>
 * <p>N / B, 第几组</p>
 * <p>N % B, 第几组的第几个</p>
 */
public class SQRTDecomposition<E> {

    private E[] data;
    private E[] blocks;
    private int N;
    private int B;
    private int Bn;
    private Merger<E> merger;

    public SQRTDecomposition(E[] arr, Merger<E> merger) {
        this.N = arr.length;
        this.data = Arrays.copyOf(arr, N);
        this.B = (int) Math.sqrt(N);
        this.Bn = (N / B) + (N % B == 0 ? 0 : 1);
        this.merger = merger;
        blocks = (E[]) new Object[Bn];
        buildBlocks();
    }

    private void buildBlocks() {
        for (int i = 0; i < N; i++) {
            if (i % B == 0) blocks[i / B] = data[i];
            else blocks[i / B] = merger.merge(blocks[i / B], data[i]);
        }
    }

    public E query(int l, int r) {
        if (l < 0 || l >= N || r < 0 || r >= N || l > r) {
            return null;
        }

        int bstart = l / B;
        int bend = r / B;

        E res = data[l];
        if (Math.abs(bstart - bend) <= 1) {
            for (int i = l + 1; i <= r; i++) {
                res = merger.merge(res, data[i]);
            }
        } else {
            for (int i = l + 1; i < (bstart + 1) * B; i++) {
                res = merger.merge(res, data[i]);
            }
            for (int i = bstart + 1; i < bend; i++) {
                res = merger.merge(res, blocks[i]);
            }
            for (int i = bend * B; i <= r; i++) {
                res = merger.merge(res, data[i]);
            }
        }

        return res;
    }

    public void update(int index, E val) {
        if (index < 0 || index >= N) return;

        int b = index / B;
        data[index] = val;

        blocks[b] = data[b * B];
        for (int i = b * B + 1; i < Math.min((b + 1) * B, N); i++) {
            blocks[b] = merger.merge(blocks[b], data[i]);
        }
    }
}
