package leetcode;

import java.util.TreeMap;

/**
 * 211 - 添加与搜索单词 - 数据结构设计
 * <p>https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/</p>
 * <p>模式匹配: 每个 '.' 都可以表示任何一个字母</p>
 */
public class S211_WordDictionary {

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

    public S211_WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)) cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        if (!cur.isWord) cur.isWord = true;
    }

    public boolean search(String word) {
        return match(root, word, 0);
    }

    /**
     * 在以 node 为根节点的 Trie 中查找 word[index ... n - 1]
     * <p>
     * node 中存放的是已经查询过的, 未查询的在 node 的下面
     */
    private boolean match(Node node, String word, int index) {
        if (index == word.length()) return node.isWord;

        char c = word.charAt(index);

        if (c != '.') {
            if (!node.next.containsKey(c)) return false;
            return match(node.next.get(c), word, index + 1);
        } else {
            for (char nextChar : node.next.keySet()) {
                if (match(node.next.get(nextChar), word, index + 1)) return true;
            }
            return false;
        }
    }

}
