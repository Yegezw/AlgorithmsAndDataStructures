package week3;

import port.Queue;
import week3.work.LoopQueue3;

public class Test {
    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueue3<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            // 每 3 个执行一次
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
                System.out.println("----------------");
            }
        }

    }
}
