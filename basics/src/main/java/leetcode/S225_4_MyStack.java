package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225 - 用队列实现栈: https://leetcode-cn.com/problems/implement-stack-using-queues/
 * <p>需要保证最后进来的元素在队首</p>
 * <p>push(int x): O(n)</p>
 * <p>pop(): O(1)</p>
 * <p>top(): O(1)</p>
 * <p>empty(): O(1)</p>
 */
public class S225_4_MyStack {

    private Queue<Integer> q;

    public S225_4_MyStack() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        q.add(x);
        int size = q.size();
        while (size > 1) {
            q.add(q.remove());
            size--;
        }
    }

    public int pop() {
        return q.remove();
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
