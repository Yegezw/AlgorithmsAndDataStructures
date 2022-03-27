package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225 - 用队列实现栈: https://leetcode-cn.com/problems/implement-stack-using-queues/
 * <p>最后进来的元素在队尾, 从队列倒出 size - 1 个元素, 重新入队</p>
 * <p>push(int x): O(1)</p>
 * <p>pop(): O(n)</p>
 * <p>top(): O(n)</p>
 * <p>empty(): O(1)</p>
 */
public class S225_1_MyStack {

    private Queue<Integer> q;

    public S225_1_MyStack() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        q.add(x);
    }

    public int pop() {
        Queue<Integer> k = new LinkedList<>();
        while (q.size() > 1) {
            k.add(q.remove());
        }
        int ret = q.remove();
        q = k;
        return ret;
    }

    public int top() {
        int ret = pop();
        push(ret);
        return ret;
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
