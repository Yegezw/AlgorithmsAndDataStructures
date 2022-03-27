package practice.tree;

/**
 * BSTree 保持 "黑平衡" 的二叉树
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }

    private Node root;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取 key 所在的节点
     */
    private Node getNode(Node node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) return node;
        if (key.compareTo(node.key) < 0) return getNode(node.left, key);
        return getNode(node.right, key);
    }

    /**
     * 返回最小 key 所在节点
     */
    private Node minimum(Node node) {
        if (node.left == null) return node;
        return minimum(node.left);
    }

    /**
     * 判断 node 是否为红色
     */
    private boolean isRed(Node node) {
        if (node == null) return BLACK;
        return node.color;
    }

    /**
     * 颜色翻转
     */
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = node.right.color = BLACK;
    }

    /**
     * 对 node 左旋转, 返回新的根节点 x
     */
    private Node leftRotate(Node node) {
        Node x = node.right;

        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 对 node 右旋转, 返回新的根节点 x
     */
    private Node rightRotate(Node node) {
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 添加
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK; // 保持根节点为黑色节点
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        // 维护红黑树
        if (isRed(node.right) && !isRed(node.left)) node = leftRotate(node);     // 左旋转
        if (isRed(node.left) && isRed(node.left.left)) node = rightRotate(node); // 右旋转
        if (isRed(node.left) && isRed(node.right)) flipColors(node);             // 颜色翻转

        return node;
    }

    /**
     * 删除
     */
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        return retNode;
    }

    /**
     * 修改
     */
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = newValue;
    }

    /**
     * 查看
     */
    public V get(K key) {
        Node node = getNode(root, key);
        return node != null ? node.value : null;
    }

    public boolean contains(K key) {
        Node node = getNode(root, key);
        return node != null;
    }

}
