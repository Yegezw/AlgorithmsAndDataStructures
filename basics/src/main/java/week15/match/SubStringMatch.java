package week15.match;

import util.file.Novel;
import util.helper.SubStringMatchHelper;

public class SubStringMatch {

    private SubStringMatch() {
    }

    /**
     * 暴力搜索 O(|s| * |t|)
     */
    public static int bruteforce(String s, String t) {
        if (s.length() < t.length()) return -1;

        // s[i, i + t.length - 1] == t ?
        for (int i = 0; i + t.length() - 1 < s.length(); i++) {
            int j;
            for (j = 0; j < t.length(); j++) {
                if (s.charAt(i + j) != t.charAt(j)) break;
            }
            if (j == t.length()) return i;
        }

        return -1;
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

        SubStringMatchHelper.matchTest("bruteforce", s, t);
    }

    public static void main(String[] args) {
        String s = Novel.words1String;
        String t = "china";
        SubStringMatchHelper.matchTest("bruteforce", s, t);
        SubStringMatchHelper.matchTest("bruteforce", s, "zyx");

        testWorstCase();
    }
}
