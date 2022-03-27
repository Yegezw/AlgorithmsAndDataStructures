package week12.avltree;

import util.file.Novel;
import week8.map.BSTMap;

//import practice.tree.AVLTree; // 测试 practice 的代码是否正确

import java.util.ArrayList;
import java.util.Collections;

/**
 * word: 傲慢与偏见
 */
public class TestAVLTree {

    private static final ArrayList<String> words = Novel.words1List;

    public static void testBSTMap() {
        long startTime = System.nanoTime();

        BSTMap<String, Integer> map = new BSTMap<>();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("Total different words: " + map.getSize());
        System.out.println("Frequency of Pride: " + map.get("pride"));
        System.out.println("Frequency of Prejudice: " + map.get("prejudice"));
        System.out.println("BSTMap: " + time + " s");
    }

    public static void testAVLTree() {
        long startTime = System.nanoTime();

        AVLTree<String, Integer> avlTree = new AVLTree<>();
        for (String word : words) {
            if (avlTree.contains(word)) {
                avlTree.set(word, avlTree.get(word) + 1);
            } else {
                avlTree.add(word, 1);
            }
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("Total different words: " + avlTree.getSize());
        System.out.println("Frequency of Pride: " + avlTree.get("pride"));
        System.out.println("Frequency of Prejudice: " + avlTree.get("prejudice"));
        System.out.println("AVLTree: " + time + " s");

        for (String word : words) {
            avlTree.remove(word);
            if (!avlTree.isBST() || !avlTree.isBalanced()) {
                throw new RuntimeException("Error");
            }
        }

        System.out.println("is BST: " + avlTree.isBST());
        System.out.println("is Balanced: " + avlTree.isBalanced());
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        System.out.println("Total words: " + words.size());
        System.out.println();

        Collections.sort(words); // 顺序添加单词后, BST 将会退化为链表, 而 AVLTree 不会

        //testBSTMap();
        System.out.println();
        testAVLTree();
    }
}
