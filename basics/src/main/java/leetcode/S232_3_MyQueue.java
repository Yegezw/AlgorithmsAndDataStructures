package leetcode;

import java.util.Stack;

/**
 * 232 - 用栈实现队列: https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * <p>需要保证最先进来的元素在栈顶</p>
 * <p>push(int x): O(1)</p>
 * <p>pop(): O(1) ~ O(n)</p>
 * <p>top(): O(1)</p>
 * <p>empty(): O(1)</p>
 */
public class S232_3_MyQueue {

    private Stack<Integer> stack1; // 用来添加元素
    private Stack<Integer> stack2; // 用来缓存部分已经排好的元素
    private int front; // 需要保证 front 是 stack1 中最先进来的元素

    public S232_3_MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        if (stack1.isEmpty()) {
            front = x;
        }
        stack1.push(x);
    }

    public int pop() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }

        while (stack1.size() > 1) {
            stack2.push(stack1.pop());
        }

        return stack1.pop();
    }

    public int peek() {
        if (!stack2.isEmpty()) {
            return stack2.peek();
        }

        if (stack1.isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }

        return front;
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

}
