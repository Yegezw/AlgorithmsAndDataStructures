package leetcode;

import java.util.Stack;

/**
 * 232 - 用栈实现队列: https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * <p>需要保证最先进来的元素在栈顶</p>
 * <p>push(int x): O(1)</p>
 * <p>pop(): O(n)</p>
 * <p>top(): O(1)</p>
 * <p>empty(): O(1)</p>
 */
public class S232_2_MyQueue {

    private Stack<Integer> stack;
    private int front; // 需要保证 front 是最先进来的元素

    public S232_2_MyQueue() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (empty()) {
            front = x;
        }
        stack.push(x);
    }

    public int pop() {
        Stack<Integer> k = new Stack<>();
        while (stack.size() > 1) {
            front = stack.pop();
            k.push(front);
        }
        int ret = stack.pop();
        while (!k.isEmpty()) {
            stack.push(k.pop());
        }
        return ret;
    }

    public int peek() {
        if (empty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return front;
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
