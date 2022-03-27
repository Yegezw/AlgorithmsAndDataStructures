package week12.map;

import port.Map;
import util.file.Novel;
import week8.map.BSTMap;
import week8.map.LinkedListMap;

import java.util.ArrayList;
import java.util.Collections;

/**
 * word: 傲慢与偏见
 */
public class TestAVLTreeMap {

    private static final ArrayList<String> words = Novel.words1List;

    public static void vs(Map<String, Integer> map) {
        long startTime = System.nanoTime();

        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Total words: " + words.size());
        System.out.println("Total different words: " + map.getSize());
        System.out.println("Frequency of Pride: " + map.get("pride"));
        System.out.println("Frequency of Prejudice: " + map.get("prejudice"));
        double time = (endTime - startTime) / 1000000000.0;
        String simpleName = map.getClass().getSimpleName();
        System.out.println(simpleName + ": " + time + " s");
    }

    /**
     * 链表 Map 添加 String, "有序" 比 "无序" 快好多
     */
    public static void test() {
        LinkedListMap<String, Integer> map1 = new LinkedListMap<>();
        LinkedListMap<String, Integer> map2 = new LinkedListMap<>();

        System.out.println("无序");
        vs(map1);

        Collections.sort(words);
        System.out.println("有序");
        vs(map2);
    }


    public static void main(String[] args) {
        BSTMap<String, Integer> map1 = new BSTMap<>();
        LinkedListMap<String, Integer> map2 = new LinkedListMap<>();
        AVLTreeMap<String, Integer> map3 = new AVLTreeMap<>();

        //Collections.sort(words); // 顺序添加单词后, BST 将会退化为链表, 而 AVLTree 不会

        vs(map1);
        System.out.println("------------------");
        vs(map2);
        System.out.println("------------------");
        vs(map3);

        test();
    }
}
