package week15.match;

import util.file.Novel;
import util.helper.SubStringMatchHelper;

public class RabinKarp {

    /**
     * Rabin-Karp 算法, 滚动哈希思想, O(n)
     */
    public static int rabinKarp(String s, String t) {
        if (t.length() == 0) {
            throw new IllegalArgumentException("t's length must be > 1");
        }
        if (s.length() < t.length()) return -1;

        int B = 256;
        long MOD = (long) (1e9 + 7);
        long pow = 1;
        for (int i = 0; i < t.length() - 1; i++) {
            pow = pow * B % MOD;
        }

        long tHash = 0;
        for (int i = 0; i < t.length(); i++) {
            tHash = (tHash * B + t.charAt(i)) % MOD;
        }

        // hash(s[0, t.length - 2])
        long cHash = 0;
        for (int i = 0; i < t.length() - 1; i++) {
            cHash = (cHash * B + s.charAt(i)) % MOD;
        }

        // hash(s[i - t.length + 1, i])
        for (int i = t.length() - 1; i < s.length(); i++) {
            cHash = (cHash * B + s.charAt(i)) % MOD;
            if (cHash == tHash && equal(s, i - t.length() + 1, i, t)) {
                return i - t.length() + 1;
            }
            cHash = (cHash - s.charAt(i - t.length() + 1) * pow % MOD + MOD) % MOD;
        }

        return -1;
    }

    public static boolean equal(String s, int l, int r, String t) {
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(i + l) != t.charAt(i)) return false;
        }
        return true;
    }

    /**
     * 暴力搜索最坏的情况
     */
    public static void testWorstCase() {
        int n = 1000000;
        int m = 10000;

        String s; // aaa...aaa...
        String t; // aaa...b

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('a');
        }
        s = sb.toString();

        sb.delete(0, sb.length());
        for (int i = 0; i < m - 1; i++) {
            sb.append('a');
        }
        sb.append('b');
        t = sb.toString();

        SubStringMatchHelper.matchTest("rabinKarp", s, t);
    }

    public static void main(String[] args) {
        String s = Novel.words1String;
        String t = "china";
        SubStringMatchHelper.matchTest("rabinKarp", s, t);
        SubStringMatchHelper.matchTest("rabinKarp", s, "zyx");

        testWorstCase();
    }
}
