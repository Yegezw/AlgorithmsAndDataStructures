package leetcode;

import java.util.TreeMap;

/**
 * 677 - 键值映射: https://leetcode-cn.com/problems/map-sum-pairs/
 */
public class S677_MapSum {

    private static class Node {
        public int val;
        public TreeMap<Character, Node> next;

        public Node(int val) {
            this.val = val;
            next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private final Node root;

    public S677_MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;

        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!cur.next.containsKey(c)) cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        cur.val = val;
    }

    public int sum(String prefix) {
        Node cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!cur.next.containsKey(c)) return 0;
            cur = cur.next.get(c);
        }

        return sum(cur);
    }

    private int sum(Node node) {
        int sum = node.val;
        if (node.next.isEmpty()) return sum;

        for (char c : node.next.keySet()) {
            sum += sum(node.next.get(c));
        }

        return sum;
    }
}
