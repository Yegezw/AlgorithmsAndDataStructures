package leetcode;

/**
 * 303 - 区域和检索 - 数组不可变: https://leetcode-cn.com/problems/range-sum-query-immutable/
 * <p>通过线段树解决</p>
 */
public class S303_1_NumArray {

    private final int[] tree;
    private final int[] data;

    public S303_1_NumArray(int[] nums) {
        data = nums;
        tree = new int[nums.length * 4];
        buildSegmentTree(0, 0, nums.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int mid = l + (r - l) / 2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        buildSegmentTree(leftChild, l, mid);
        buildSegmentTree(rightChild, mid + 1, r);

        tree[treeIndex] = tree[leftChild] + tree[rightChild];
    }

    public int sumRange(int left, int right) {
        // [0, data.length - 1], left <= right
        if (left < 0 || left >= data.length || right < 0 || right >= data.length || left > right) {
            throw new IllegalArgumentException("index is illegal.");
        }
        return query(0, 0, data.length - 1, left, right);
    }

    private int query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (r == queryR && l == queryL) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        if (queryL >= mid + 1) {
            return query(rightChild, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftChild, l, mid, queryL, queryR);
        } else {
            int leftRes = query(leftChild, l, mid, queryL, mid);
            int rightRes = query(rightChild, mid + 1, r, mid + 1, queryR);
            return leftRes + rightRes;
        }
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }
}
