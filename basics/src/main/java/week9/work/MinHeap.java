package week9.work;

import week3.DynamicArray;

/**
 * 最小堆: 所有节点都小于等于孩子节点
 */
public class MinHeap<E extends Comparable<E>> {

    private final DynamicArray<E> data;

    public MinHeap() {
        data = new DynamicArray<>();
    }

    public MinHeap(int capacity) {
        data = new DynamicArray<>(capacity);
    }

    public MinHeap(E[] arr) {
        data = new DynamicArray<>(arr);
        for (int i = parent(data.getSize() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int index) {
        while (index > 0 && data.get(index).compareTo(data.get(parent(index))) < 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    public E findMin() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Heap is empty.");
        }
        return data.get(0);
    }

    public E extractMin() {
        E ret = findMin();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int index) {
        while (leftChild(index) < data.getSize()) {
            int smaller = leftChild(index);
            if (smaller + 1 < data.getSize() && data.get(smaller + 1).compareTo(data.get(smaller)) < 0) smaller++;

            if (data.get(index).compareTo(data.get(smaller)) <= 0) break;
            data.swap(index, smaller);
            index = smaller;
        }
    }

    public E replace(E e) {
        E ret = findMin();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
