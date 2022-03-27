package week8.map;

import port.Map;
import util.file.Novel;

import java.util.ArrayList;

/**
 * word: 傲慢与偏见
 */
public class TestBSTMap {

    private static final ArrayList<String> words = Novel.words1List;

    public static void testBSTMap() {
        System.out.println("Pride and Prejudice");
        System.out.println("Total words: " + words.size());

        BSTMap<String, Integer> map = new BSTMap<>();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }

        System.out.println("Total different words: " + map.getSize());
        System.out.println("Frequency of Pride: " + map.get("pride"));
        System.out.println("Frequency of Prejudice: " + map.get("prejudice"));
    }

    public static void testLinkedListMap() {
        System.out.println("Pride and Prejudice");
        System.out.println("Total words: " + words.size());
        LinkedListMap<String, Integer> map = new LinkedListMap<>();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }
        System.out.println("Total different words: " + map.getSize());
        System.out.println("Frequency of Pride: " + map.get("pride"));
        System.out.println("Frequency of Prejudice: " + map.get("prejudice"));
    }

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

    public static void main(String[] args) {
        //testLinkedListMap();
        //testBSTMap();

        BSTMap<String, Integer> map1 = new BSTMap<>();
        LinkedListMap<String, Integer> map2 = new LinkedListMap<>();
        vs(map1);
        System.out.println("------------------");
        vs(map2);
    }
}
