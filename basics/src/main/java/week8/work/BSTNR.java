package week8.work;

import java.util.Stack;

/**
 * Binary Search Tree Not Recursion
 */
public class BSTNR<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTNR() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
            return;
        }

        Node p = root;
        while (true) {
            if (e.equals(p.e)) return;

            if (e.compareTo(p.e) < 0) {
                if (p.left == null) {
                    p.left = new Node(e);
                    size++;
                    return;
                }
                p = p.left;
            }

            if (e.compareTo(p.e) > 0) {
                if (p.right == null) {
                    p.right = new Node(e);
                    size++;
                    return;
                }
                p = p.right;
            }
        }
    }

    public boolean contains(E e) {
        Node cur = root;
        while (cur != null) {
            if (e.compareTo(cur.e) == 0) return true;
            if (e.compareTo(cur.e) < 0) cur = cur.left;
            else cur = cur.right;
        }
        return false;
    }

    public void preOrder() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
    }

}
