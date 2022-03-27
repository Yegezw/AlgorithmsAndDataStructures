package leetcode;

/**
 * 203 - 移除链表元素: https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class S203_RemoveElements {

    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode(int[] arr) {
            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException("Arr is empty.");
            }
            this.val = arr[0];
            ListNode cur = this;
            for (int i = 1; i < arr.length; i++) {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        @Override
        public String toString() {
            ListNode cur = this;
            StringBuilder sb = new StringBuilder();
            while (cur != null) {
                sb.append(cur.val).append("->");
                cur = cur.next;
            }
            sb.append("NULL");
            return sb.toString();
        }
    }

    /**
     * 不使用 dummyHead
     */
    public static ListNode removeElements1(ListNode head, int val) {
        // 1.先删除头结点
        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) return null;

        // 2.再删除其他节点
        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = prev.next;
            }
            cur = cur.next;
        }

        return head;
    }

    /**
     * 使用 dummyHead
     */
    public static ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1, head);

        ListNode prev = dummyHead;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = prev.next;
            }
            cur = cur.next;
        }
        return dummyHead.next;
    }

    /**
     * 递归解决问题
     */
    public static ListNode removeElements3(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode res = removeElements3(head.next, val);
        if (head.val == val) {
            return res;
        } else {
            head.next = res;
            return head;
        }
    }

    /**
     * 递归解决问题进一步简化
     */
    public static ListNode removeElements4(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements4(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     * <p>递归逻辑的查看(递归调试)</p>
     * <p>刚进函数, 先打印 depthString 和 本次调用意图(包含传入的参数)</p>
     * <p>每次返回前打印 depthString 和 "Return: " + ret</p>
     * <p>每次调用递归函数得到结果后, 打印 depthString 和 结果(包含传入的参数)</p>
     */
    public static ListNode removeElements5(ListNode head, int val, int depth) {
        String depthString = generateDepthString(depth);

        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return null;
        }

        ListNode res = removeElements5(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode ret;
        if (head.val == val) {
            ret =  res;
        } else {
            head.next = res;
            ret = head;
        }

        System.out.print(depthString);
        System.out.println("Return: " + ret);
        return ret;
    }

    private static String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        sb.append(depth).append("-");
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        ListNode h1 = removeElements5(head, 6, 1);
        System.out.println(h1);
    }
}
