package leetcode;

import java.util.Stack;

/**
 * 232 - 用栈实现队列: https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * <p>需要保证最先进来的元素在栈顶</p>
 * <p>push(int x): O(n)</p>
 * <p>pop(): O(1)</p>
 * <p>top(): O(1)</p>
 * <p>empty(): O(1)</p>
 */
public class S232_1_MyQueue {

    private Stack<Integer> stack;

    public S232_1_MyQueue() {
        stack = new Stack<>();
    }

    public void push(int x) {
        Stack<Integer> k = new Stack<>();
        while (!stack.isEmpty()) {
            k.push(stack.pop());
        }
        k.push(x);
        while (!k.isEmpty()) {
            stack.push(k.pop());
        }
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
