package week11.segmentTree;

public class TestSegmentTree {
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> tree = new SegmentTree<>(nums, (a, b) -> a + b);

        System.out.println(tree.query(0, 2)); // 1

        tree.set(1, 2);

        System.out.println(tree.query(0, 2)); // 3
        System.out.println(tree.query(3, 5)); // -4
        System.out.println(tree.query(0, 5)); // -1
    }
}
