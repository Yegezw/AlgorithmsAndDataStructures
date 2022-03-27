package week12.rbTree;

/**
 * 红黑树
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
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回最小 key 所在节点
     */
    private Node minimum(Node node) {
        if (node.left == null) return node;
        return minimum(node.left);
    }

    /**
     * 获取 key 所在的节点
     */
    private Node getNode(K key) {
        return getNode(root, key);
    }

    private Node getNode(Node node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) return node;
        if (key.compareTo(node.key) < 0) return getNode(node.left, key);
        return getNode(node.right, key);
    }

    /**
     * 判断是否为红色
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
    //   node                     x
    //  /   \     左旋转         /  \
    // T1    x   --------->   node  T3
    //      / \              /   \
    //     T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;

        // 左旋转
        node.right = x.left;
        x.left = node;

        // 调整颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 对 node 右旋转, 返回新的根节点 x
     */
    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->    y  node
    //  / \                      /   \
    // y  T1                   T1    T2
    private Node rightRotate(Node node) {
        Node x = node.left;

        // 右旋转
        node.left = x.right;
        x.right = node;

        // 调整颜色
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
            return new Node(key, value); // 默认插入红色节点
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
        Node node = getNode(key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
            }
        }
    }

    /**
     * 修改
     */
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        } else {
            node.value = newValue;
        }
    }

    /**
     * 查看
     */
    public V get(K key) {
        Node node = getNode(key);
        return node != null ? node.value : null;
    }

    public boolean contains(K key) {
        Node node = getNode(key);
        return node != null;
    }

}
