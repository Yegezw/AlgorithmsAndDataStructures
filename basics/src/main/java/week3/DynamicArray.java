package week3;

import java.util.Arrays;

public class DynamicArray<E> {

    private E[] data;
    private int size;

    /**
     * 构造函数
     * @param capacity 容量
     */
    public DynamicArray(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 默认容量为 10
     */
    public DynamicArray() {
        this(10);
    }

    /**
     * 复制给定数组
     */
    public DynamicArray(E[] arr) {
        data = Arrays.copyOf(arr, arr.length);
        size = arr.length;
    }

    /**
     * 获取数组中的元素个数
     * @return 数组中的元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     * @return 数组容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     * @return 数组是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向后追加一个元素
     * @param e 需要追加的元素
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 向前追加一个元素
     * @param e 需要追加的元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在指定位置插入元素
     * @param index 指定位置
     * @param e     需要插入的元素
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed, require 0 <= index <= size");
        }
        if (size == data.length) {
            resize(2 * data.length);
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = e;
        size++;
    }

    /**
     * 获取指定位置的数据
     * @param index 指定位置
     * @return 指定位置的数据
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, require 0 <= index < size");
        }
        return data[index];
    }

    /**
     * 查看首元素
     * @return 首元素
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 查看尾元素
     * @return 尾元素
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改指定位置的数据
     * @param index 指定位置
     * @param e     修改后的数据
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed, require 0 <= index < size");
        }
        data[index] = e;
    }

    /**
     * 查找是否存在指定元素
     * @param e 需要查找的元素
     * @return 是否存在
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) return true;
        }
        return false;
    }

    /**
     * 查找指定元素的位置
     * @param e 需要查找的元素
     * @return 元素的位置
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) return i;
        }
        return -1;
    }

    /**
     * 删除指定位置的元素并将其返回
     * @param index 指定位置
     * @return 指定位置的元素
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed, require 0 <= index < size");
        }
        E res = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        data[size] = null;
        if (size == data.length / 4 && (data.length / 2) != 0) {
            resize(data.length / 2);
        }
        return res;
    }

    /**
     * 删除首元素
     * @return 首元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除尾元素
     * @return 尾元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 如果存指定元素存在就删除(仅第一次出现的会被删除)
     * @param e 需要删除的元素
     * @return 是否删除成功
     */
    public boolean removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 内部数组扩容
     * @param newCapacity 新的容量
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    /**
     * 交换索引为 a、b 位置的元素
     */
    public void swap(int a, int b) {
        if (a < 0 || a >= size || b < 0 || b >= size) {
            throw new IllegalArgumentException("Swap failed, require 0 <= index < size");
        }
        E k = data[a];
        data[a] = data[b];
        data[b] = k;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("DynamicArray: size = %d, capacity = %d\n", size, data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
