package week4.recursion;

public class Recursion {

    /**
     * 求 n 的阶乘
     */
    public static int factorial(int n) {
        if (n == 1) {
            return n;
        }
        return n * factorial(n - 1);
    }

    /**
     * 数组求和
     */
    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int l) {
        if (l == arr.length) {
            return 0;
        }
        return arr[l] + sum(arr, l + 1);
    }


    public static void main(String[] args) {
        System.out.println(factorial(5));

        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(sum(arr));
    }
}
