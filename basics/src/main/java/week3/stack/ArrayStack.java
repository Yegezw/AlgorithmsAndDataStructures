package week3.stack;

import port.Stack;
import week3.DynamicArray;

public class ArrayStack<E> implements Stack<E> {

    private final DynamicArray<E> array;

    public ArrayStack(int capacity) {
        array = new DynamicArray<E>(capacity);
    }

    public ArrayStack() {
        array = new DynamicArray<E>();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 查看栈的容量
     *
     * @return 容量
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        for (int i = 0; i < getSize(); i++) {
            sb.append(array.get(i));
            if (i < getSize() - 1) {
                sb.append(", ");
            }
        }
        sb.append("] top");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
