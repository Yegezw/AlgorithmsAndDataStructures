package port;

public interface Queue<E> {

    /**
     * 入队
     * @param e 元素
     */
    void enqueue(E e);

    /**
     * 出队
     * @return 队首元素
     */
    E dequeue();

    /**
     * 查看队首元素
     * @return 队首元素
     */
    E getFront();

    /**
     * 查看队列大小
     * @return 队列大小
     */
    int getSize();

    /**
     * 判断队列是否为空
     * @return 队列是否为空
     */
    boolean isEmpty();
}
