package week8;

import java.util.ArrayList;
import java.util.Random;

public class TestBST {

    public static void test() {
        BST<Integer> bst = new BST<>();
        int[] arr = {5, 3, 6, 8, 4, 2};
        for (int i : arr) {
            bst.add(i);
        }
        /////////////////
        //      5      //
        //    /   \    //
        //   3     6   //
        //  / \     \  //
        // 2   4     8 //
        /////////////////
        bst.preOrder();   // 5 3 2 4 6 8
        bst.inOrder();    // 2 3 4 5 6 8
        bst.postOrder();  // 2 4 3 8 6 5
        bst.levelOrder(); // 5 3 6 2 4 8

        bst.remove(3);
        bst.levelOrder(); // 5 4 6 2 8
    }

    public static void testBST() {
        BST<Integer> bst = new BST<>();
        Random random = new Random();
        testRemoveMin(bst, random);
        testRemoveMax(bst, random);
    }

    public static void testRemoveMin(BST<Integer> bst, Random random) {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            nums.add(bst.removeMin());
        }
        System.out.println(nums);

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) > nums.get(i)) {
                throw new IllegalArgumentException("removeMin Error");
            }
        }
        System.out.println("removeMin test completed");
    }

    public static void testRemoveMax(BST<Integer> bst, Random random) {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            nums.add(bst.removeMax());
        }
        System.out.println(nums);

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) < nums.get(i)) {
                throw new IllegalArgumentException("removeMax Error");
            }
        }
        System.out.println("removeMax test completed");
    }

    public static void main(String[] args) {
        test();
        testBST();
    }
}
