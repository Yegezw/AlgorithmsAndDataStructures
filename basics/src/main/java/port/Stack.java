package port;

public interface Stack<E> {

    /**
     * 入栈
     * @param e 元素
     */
    void push(E e);

    /**
     * 出栈
     * @return 栈顶元素
     */
    E pop();

    /**
     * 查看栈顶元素
     * @return 栈顶元素
     */
    E peek();

    /**
     * 查看栈大小
     * @return 栈大小
     */
    int getSize();

    /**
     * 判断栈是否为空
     * @return 栈是否为空
     */
    boolean isEmpty();
}
