package week12.set;

import port.Set;
import week12.avltree.AVLTree;

//import practice.tree.AVLTree; // 测试 practice 的代码是否正确

public class AVLTreeSet<E extends Comparable<E>> implements Set<E> {

    private final AVLTree<E, Object> avlTree;

    public AVLTreeSet() {
        avlTree = new AVLTree<>();
    }

    @Override
    public void add(E e) {
        avlTree.add(e, null);
    }

    @Override
    public void remove(E e) {
        avlTree.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return avlTree.contains(e);
    }

    @Override
    public int getSize() {
        return avlTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avlTree.isEmpty();
    }
}
