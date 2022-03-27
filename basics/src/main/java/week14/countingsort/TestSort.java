package week14.countingsort;

import util.pojo.Student;

import java.util.Arrays;
import java.util.Random;

/**
 * 计数排序的稳定性
 */
public class TestSort {

    /**
     * O(n + R)
     */
    public static void sort(Student[] arr, int R) {
        // 分数的取值范围是 [0, R)
        int[] cnt = new int[R];

        // O(n)
        for (Student student : arr) {
            cnt[student.getScore()]++;
        }

        // O(R)
        int[] index = new int[R + 1];
        for (int i = 0; i < R; i++) {
            index[i + 1] = index[i] + cnt[i];
        }

        // O(n)
        Student[] temp = new Student[arr.length];
        for (Student student : arr) {
            int score = student.getScore();
            temp[index[score]] = student;
            index[score]++;
        }

        // O(n)
        System.arraycopy(temp, 0, arr, 0, temp.length);
    }

    /**
     * 测试 1, 分数 [0, 3), R = 3
     */
    public static void test1() {
        Student[] students
                = {new Student("A", 2), new Student("B", 1),
                new Student("C", 0), new Student("D", 1),
                new Student("E", 2), new Student("F", 0)};
        sort(students, 3);
        System.out.println(Arrays.toString(students));
    }

    /**
     * 测试 2, 分数 [0, 101),  R = 101
     */
    public static void test2() {
        int n = 26 * 26 * 26 * 26;
        Student[] students = new Student[n];
        Random random = new Random();

        int i = 0;
        for (char c1 = 'a'; c1 <= 'z'; c1++)
            for (char c2 = 'a'; c2 <= 'z'; c2++)
                for (char c3 = 'a'; c3 <= 'z'; c3++)
                    for (char c4 = 'a'; c4 <= 'z'; c4++)
                        students[i++] = new Student("" + c1 + c2 + c3 + c4, random.nextInt(101));

        sort(students, 101);
        isSorted(students);
    }

    /**
     * 验证排序结果是否正确
     */
    public static void isSorted(Student[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            if (arr[i - 1].getScore() > arr[i].getScore()) {
                throw new RuntimeException("Sort failed!");
            }

            if (arr[i - 1].getScore() == arr[i].getScore()) {
                if (arr[i - 1].getName().compareTo(arr[i].getName()) > 0) {
                    throw new RuntimeException("Non-Stable counting sort!");
                }
            }
        }
    }

    public static void main(String[] args) {
        test1();

        test2();
    }
}
