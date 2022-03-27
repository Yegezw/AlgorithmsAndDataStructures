package week11.unionFind;

import port.UF;

import java.util.Random;

public class UnionFindTest {

    /**
     * 对 uf 进行 m 次合并和查询操作
     */
    private static void testUF(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        String simpleName = uf.getClass().getSimpleName();
        System.out.println(simpleName + ": " + time + " s");
    }

    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000;

        UnionFind1 uf1 = new UnionFind1(size);
        UnionFind2 uf2 = new UnionFind2(size);
        UnionFind3 uf3 = new UnionFind3(size); // size 优化
        UnionFind4 uf4 = new UnionFind4(size); // rank 优化
        UnionFind5 uf5 = new UnionFind5(size); // 路径压缩 1
        UnionFind6 uf6 = new UnionFind6(size); // 路径压缩 2

        //testUF(uf1, m);
        //testUF(uf2, m);
        testUF(uf3, m);
        testUF(uf4, m);
        testUF(uf5, m);
        testUF(uf6, m);
    }
}
