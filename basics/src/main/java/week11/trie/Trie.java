package week11.trie;

import java.util.TreeMap;

/**
 * 字典树: 非递归实现
 * <p>查询的时间复杂度为: O(w), w 为 word 的长度! 跟 Trie 中存储的 word 数量无关</p>
 */
public class Trie {

    private static class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private final Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 向 Trie 中添加一个新的单词 word
     */
    public void add(String word) {
        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)) cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询 Trie 中是否包含单词 word
     */
    public boolean contains(String word) {
        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)) return false;
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }

    /**
     * 查询 Trie 中是否有以 prefix 为前缀的单词
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!cur.next.containsKey(c)) return false;
            cur = cur.next.get(c);
        }

        return true;
    }

}
