package week13.work;

//import practice.other.SQRTDecomposition;

public class TestSQRT {

    public static void test() {
        Integer[] arr = {1, 2, 3, 4};

        SQRTDecomposition<Integer> sumSQRT = new SQRTDecomposition<>(arr, Integer::sum);
        SQRTDecomposition<Integer> maxSQRT = new SQRTDecomposition<>(arr, Math::max);
        SQRTDecomposition<Integer> minSQRT = new SQRTDecomposition<>(arr, Math::min);
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SQRTDecomposition<Integer> sumSQRT = new SQRTDecomposition<>(nums, (a, b) -> a + b);

        System.out.println(sumSQRT.query(0, 2)); // 1

        sumSQRT.update(1, 2);

        System.out.println(sumSQRT.query(0, 2)); // 3
        System.out.println(sumSQRT.query(3, 5)); // -4
        System.out.println(sumSQRT.query(0, 5)); // -1
    }
}
