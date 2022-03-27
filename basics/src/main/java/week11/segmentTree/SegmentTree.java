package week11.segmentTree;

import port.Merger;

import java.util.Arrays;

/**
 * 线段树: 融合基于 Merger 接口的 merge(E a, E b) 方法
 * <p>(treeIndex, l, r) 三者是绑定在一起的</p>
 * <p>tree[treeIndex] 代表 arr[l ... r] 的融合结果</p>
 * <p>后序遍历思想</p>
 */
public class SegmentTree<E> {

    private final E[] data;
    private final E[] tree;
    private final Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = Arrays.copyOf(arr, arr.length);
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 在 treeIndex 的位置创建表示区间 [l ... r] 的线段树
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        buildSegmentTree(leftTreeIndex, l, mid);        // 左边 [l, mid]
        buildSegmentTree(rightTreeIndex, mid + 1, r); // 右边 [mid + 1, r]

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 返回 [queryL ... queryR] 的值
     */
    public E query(int queryL, int queryR) {
        // [0, data.length - 1], queryL <= queryR
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("index is illegal.");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以 treeIndex 为根的线段树中 [l ... r] 的范围里, 搜索区间 [queryL ... queryR] 的值
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        } else {
            E leftRes = query(leftTreeIndex, l, mid, queryL, mid);
            E rightRes = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftRes, rightRes);
        }
    }

    /**
     * 基于 equals 判断是否真的进行了更新操作
     */
    public void set(int index, E value) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Set failed, require 0 <= index < length");
        }
        if (data[index].equals(value)) return;

        data[index] = value;
        set(0, 0, data.length - 1, index, value);
    }

    /**
     * 在以 treeIndex 为根的线段树中更新 index 的值为 value
     */
    private void set(int treeIndex, int l, int r, int index, E value) {
        if (l == r) {
            tree[treeIndex] = value;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        if (index <= mid) {
            set(leftChild, l, mid, index, value);
        } else {
            set(rightChild, mid + 1, r, index, value);
        }

        tree[treeIndex] = merger.merge(tree[leftChild], tree[rightChild]);
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Get failed, require 0 <= index < length");
        }
        return data[index];
    }

    public int getSize() {
        return data.length;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    @Override
    public String toString() {
        return Arrays.toString(tree);
    }
}
