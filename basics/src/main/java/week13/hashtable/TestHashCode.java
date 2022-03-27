package week13.hashtable;

import util.pojo.Student2;

import java.util.HashMap;
import java.util.HashSet;

public class TestHashCode {

    public static void test() {
        int a = 42;
        System.out.println(((Integer) a).hashCode());

        int b = -42;
        System.out.println(((Integer) b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double) c).hashCode());

        String d = "imooc";
        System.out.println(d.hashCode());

        Student2 stu = new Student2(3, 2, "bobo", "Liu");
        System.out.println(stu.hashCode());
    }

    public static void testHashSet() {
        Student2 stu = new Student2(3, 2, "bobo", "Liu");

        HashSet<Student2> set = new HashSet<>();
        set.add(stu);
    }

    public static void testHashMap() {
        Student2 stu = new Student2(3, 2, "bobo", "Liu");

        HashMap<Student2, Integer> scores = new HashMap<>();
        scores.put(stu, 100);
    }

    public static void main(String[] args) {
        test();
        testHashSet();
        testHashMap();
    }
}
