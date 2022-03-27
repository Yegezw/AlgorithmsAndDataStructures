package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225 - 用队列实现栈: https://leetcode-cn.com/problems/implement-stack-using-queues/
 * <p>最后进来的元素在队尾, 从队列倒出 size - 1 个元素, 重新入队</p>
 * <p>随时用 top 记录最后进来的元素</p>
 * <p>push(int x): O(1)</p>
 * <p>pop(): O(n)</p>
 * <p>top(): O(1)</p>
 * <p>empty(): O(1)</p>
 */
public class S225_2_MyStack {

    private Queue<Integer> q;
    private int top; // 需要保证 top 是最后进来的元素

    public S225_2_MyStack() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        q.add(x);
        top = x;
    }

    public int pop() {
        Queue<Integer> k = new LinkedList<>();
        while (q.size() > 1) {
            top = q.remove();
            k.add(top);
        }
        int ret = q.remove();
        q = k;
        return ret;
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
