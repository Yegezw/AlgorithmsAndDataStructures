package week12.map;

import port.Map;
import week12.avltree.AVLTree;

//import practice.tree.AVLTree; // 测试 practice 的代码是否正确

public class AVLTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private final AVLTree<K, V> avlTree;

    public AVLTreeMap() {
        this.avlTree = new AVLTree<>();
    }

    @Override
    public void add(K key, V value) {
        avlTree.add(key, value);
    }

    @Override
    public V remove(K key) {
        return avlTree.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return avlTree.contains(key);
    }

    @Override
    public V get(K key) {
        return avlTree.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        avlTree.set(key, newValue);
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
