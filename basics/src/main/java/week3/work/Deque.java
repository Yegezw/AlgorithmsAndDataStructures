package week3.work;

/**
 * 双端队列: 使用 size, 不浪费一个空间
 * <p>队列为空: size == 0</p>
 * <p>队列为满: size == data.length</p>
 * <p>get: (1)是否为空</p>
 * <p>remove: (1)是否为空 (2)移除元素并修改成员变量 (3)动态缩容</p>
 * <p>add: (1)动态扩容 (2)添加元素并修改成员变量</p>
 */
public class Deque<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public Deque() {
        this(10);
    }

    public Deque(int capacity) {
        data = (E[]) new Object[capacity];
        front = tail = size = 0;
    }

    public void addFront(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        front = (front == 0 ? data.length - 1 : front - 1);
        data[front] = e;
        size++;
    }

    public void addLast(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    public E removeFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("RemoveFront failed, Deque is empty");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("RemoveLast failed, Deque is empty");
        }
        tail = (tail == 0 ? data.length - 1 : tail - 1);
        E ret = data[tail];
        data[tail] = null;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("GetFront failed, Deque is empty");
        }
        return data[front];
    }

    public E getLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("GetLast failed, Deque is empty");
        }
        int index = (tail == 0 ? data.length - 1 : tail - 1);
        return data[index];
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Deque: size = %d, capacity = %d\n", getSize(), getCapacity()));
        sb.append("front [");
        for (int i = 0; i < size; i++) {
            sb.append(data[(i + front) % data.length]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        // 在下面的双端队列的测试中, 偶数从队尾加入、奇数从队首加入
        Deque<Integer> dq = new Deque<>();
        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) dq.addLast(i);
            else dq.addFront(i);
            System.out.println(dq);
        }

        System.out.println();

        // 之后, 我们依次从队首和队尾轮流删除元素
        for (int i = 0; !dq.isEmpty(); i++) {
            if (i % 2 == 0) dq.removeFront();
            else dq.removeLast();
            System.out.println(dq);
        }
    }
}
