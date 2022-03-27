package week8.work;

public class TestBSTNR {
    public static void main(String[] args) {
        BSTNR<Integer> bst = new BSTNR<>();
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

        bst.preOrder();
        System.out.println(bst.contains(9));
    }
}
