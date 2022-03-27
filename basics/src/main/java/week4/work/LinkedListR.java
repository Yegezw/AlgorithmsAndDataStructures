package week4.work;

import javafx.util.Pair;

/**
 * <p>所有操作采用递归来完成</p>
 * <p></p>
 * <p>(1)添加操作和删除操作的递归函数的返回值通常需要为 Node</p>
 * <p>(2)有索引的操作要先判断索引, 然后调用递归函数, 同时递归函数的条件通常是 index == 0</p>
 * <p>(3)无索引且不确定的操作(判断元素是否存在、删除所有指定元素), 它的递归函数的条件通常是 node == null</p>
 * <p></p>
 * <p>(1)要注意递归函数的 "宏观语意"</p>
 * <p>(2)如果问题的解需要组合在一起(添加和删除操作), 递归函数就需要返回值 Node</p>
 * <p>(3)如果不需要组合在一起(修改和查看), 就不需要返回 Node</p>
 */
public class LinkedListR<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node node) {
            this.e = e;
            this.next = node;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private int size;

    public LinkedListR() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed, require 0 <= index <= size");
        }
        head = add(head, index, e);
        size++;
    }

    /**
     * 以 node 为头结点的链表, 给它的 index 位置添加元素 e, 并返回新链表的头结点
     */
    private Node add(Node node, int index, E e) {
        if (index == 0) {
            return new Node(e, node);
        }
        node.next = add(node.next, index - 1, e);
        return node;
    }

    public void addFirst(E  e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed, require 0 <= index < size");
        }
        Pair<Node, E> res = remove(head, index);
        head = res.getKey();
        size--;
        return res.getValue();
    }

    /**
     * 以 node 为头结点的链表, 删除它 index 位置的元素, 并返回新链表的头结点和被删除的元素 e
     */
    private Pair<Node, E> remove(Node node, int index) {
        if (index == 0) {
            return new Pair<>(node.next, node.e);
        }
        Pair<Node, E> ret = remove(node.next, index - 1);
        node.next = ret.getKey();
        return new Pair<>(node, ret.getValue());
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        head = removeElement(head, e);
    }

    /**
     * 以 node 为头结点的链表, 删除它中 "所有的" 元素 e, 并返回新链表的头结点
     */
    private Node removeElement(Node node, E e) {
        if (node == null) {
            return null;
        }

        node.next = removeElement(node.next, e); // "所有的" 体现在这里, 它是后序遍历
        if (node.e.equals(e)) {
            node = node.next;
            size--;
        }
        return node;
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed, require 0 <= index < size");
        }
        set(head, index, e);
    }

    /**
     * 以 node 为头结点的链表, 把它 index 位置的元素修改为 e
     */
    private void set(Node node, int index, E e) {
        if (index == 0) {
            node.e = e;
            return;
        }
        set(node.next, index - 1, e);
    }

    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, require 0 <= index < size");
        }
        return get(head, index);
    }

    /**
     * 以 node 为头结点的链表, 获得它 index 位置的元素
     */
    private E get(Node node, int index) {
        if (index == 0) {
            return node.e;
        }
        return get(node.next, index - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public boolean contains(E e) {
        return contains(head, e);
    }

    /**
     * 以 node 为头结点的链表, 查询它中否有元素 e
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.e.equals(e)) {
            return true;
        }
        return contains(node.next, e);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur).append("->");
        }
        sb.append("NULL");
        return sb.toString();
    }

}
