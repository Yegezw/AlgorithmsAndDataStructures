package week4;

import util.ArrayGenerator;
import week5.MyMergeSort;

/**
 * 测试链表添加有序元素和无序元素的快慢, 这里添加的元素类型是 Integer
 * <p>此外还有 String</p>
 * <p>week12.map.TestAVLTreeMap.test()</p>
 * <p>week12.set.TestAVLTreeSet.test()</p>
 */
public class TestAddTime {

    public static void testAdd(LinkedList<Integer> list, Integer[] arr) {
        long startTime = System.nanoTime();

        for (Integer num : arr) {
            if (!list.contains(num)) {
                list.addFirst(num);
            }
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        String simpleName = list.getClass().getSimpleName();
        System.out.println(simpleName + ": " + time + " s");
    }

    public static void testTime(int n) {
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();

        System.out.println("无序");
        testAdd(list1, arr);

        MyMergeSort.mergeSort(arr);
        System.out.println("有序");
        testAdd(list2, arr);
    }

    public static void main(String[] args) {
        testTime(100000);
    }
}
