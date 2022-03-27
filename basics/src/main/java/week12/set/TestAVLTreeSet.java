package week12.set;

import port.Set;
import util.file.Novel;
import week8.set.BSTSet;
import week8.set.LinkedListSet;

import java.util.ArrayList;
import java.util.Collections;

/**
 * word: 傲慢与偏见
 */
public class TestAVLTreeSet {

    private static final ArrayList<String> words = Novel.words1List;

    public static void vs(Set<String> set) {
        long startTime = System.nanoTime();

        for (String word : words) {
            set.add(word);
        }

        long endTime = System.nanoTime();
        System.out.println("Total words: " + words.size());
        System.out.println("Total different words: " + set.getSize());
        double time = (endTime - startTime) / 1000000000.0;
        String simpleName = set.getClass().getSimpleName();
        System.out.println(simpleName + ": " + time + " s");
    }

    /**
     * 链表 Set 添加 String, "有序" 比 "无序" 快好多
     */
    public static void test() {
        LinkedListSet<String> set1 = new LinkedListSet<>();
        LinkedListSet<String> set2 = new LinkedListSet<>();

        System.out.println("无序");
        vs(set1);

        Collections.sort(words);
        System.out.println("有序");
        vs(set2);
    }

    public static void main(String[] args) {
        BSTSet<String> set1 = new BSTSet<>();
        LinkedListSet<String> set2 = new LinkedListSet<>();
        AVLTreeSet<String> set3 = new AVLTreeSet<>();

        //Collections.sort(words); // 顺序添加单词后, BST 将会退化为链表, 而 AVLTree 不会

        vs(set1);
        System.out.println("------------------");
        vs(set2);
        System.out.println("------------------");
        vs(set3);

        test();
    }
}
