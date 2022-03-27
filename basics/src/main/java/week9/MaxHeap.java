package week9;

import week3.DynamicArray;

/**
 * 最大堆: 所有节点都大于等于孩子节点
 */
public class MaxHeap<E extends Comparable<E>> {

    private final DynamicArray<E> data;

    public MaxHeap() {
        data = new DynamicArray<>();
    }

    public MaxHeap(int capacity) {
        data = new DynamicArray<>(capacity);
    }

    /**
     * 将任意数组整理成堆的形状: O(n)<p>
     * 最后一个节点的父节点就是最后一个非叶子节点
     */
    public MaxHeap(E[] arr) {
        data = new DynamicArray<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回 index 的父亲索引
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回 index 左孩子的索引
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * 返回 index 右孩子的索引
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 上浮
     */
    private void siftUp(int index) {
        while (index > 0 && data.get(index).compareTo(data.get(parent(index))) > 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     * 提取最大
     */
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    /**
     * 找到最大
     */
    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Heap is empty!");
        }
        return data.get(0);
    }

    /**
     * 下沉
     */
    private void siftDown(int index) {
        while (leftChild(index) < data.getSize()) {
            int bigger = leftChild(index);
            if (bigger + 1 < data.getSize() && data.get(bigger + 1).compareTo(data.get(bigger)) > 0) bigger++;

            if (data.get(index).compareTo(data.get(bigger)) >= 0) break;
            data.swap(index, bigger);
            index = bigger;
        }
    }

    /**
     * 取出一个最大元素, 放入一个新元素
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
