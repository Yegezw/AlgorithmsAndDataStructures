package week12.rbTree;

import week12.avltree.AVLTree;
import week8.map.BSTMap;

//import practice.tree.RBTree; // 测试 practice 的代码是否正确

import java.util.ArrayList;
import java.util.Random;

public class VS {

    public static void testBSTMap(ArrayList<Integer> testData) {
        long startTime = System.nanoTime();

        BSTMap<Integer, Integer> map = new BSTMap<>();
        for (Integer data : testData) {
            map.add(data, null);
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BSTMap: " + time + " s");
    }

    public static void testAVLTree(ArrayList<Integer> testData) {
        long startTime = System.nanoTime();

        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        for (Integer data : testData) {
            avlTree.add(data, null);
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVLTree: " + time + " s");
    }

    public static void testRBTree(ArrayList<Integer> testData) {
        long startTime = System.nanoTime();

        RBTree<Integer, Integer> rbTree = new RBTree<>();
        for (Integer data : testData) {
            rbTree.add(data, null);
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBTree: " + time + " s");
    }

    public static void main(String[] args) {
        int n = 20000000;

        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            //testData.add(random.nextInt(Integer.MAX_VALUE));
            testData.add(i);
        }

        //testBSTMap(testData);
        testAVLTree(testData);
        testRBTree(testData);
    }
}
