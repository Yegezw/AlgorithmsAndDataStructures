package leetcode;

/**
 * 1392 - 最长快乐前缀: https://leetcode-cn.com/problems/longest-happy-prefix/
 */
public class S1392_1_LongestPrefix {

    public String longestPrefix(String s) {

        // s[0, len - 1] == s[s.length - len, s.length - 1] ?
        for (int len = s.length() - 1; len >= 1; len--) {
            if (equal(s, 0, len - 1, s.length() - len, s.length() - 1)) {
                return s.substring(0, len);
            }
        }

        return "";
    }

    private boolean equal(String s, int l1, int r1, int l2, int r2) {
        while (l1 <= r1 && l2 <= r2) {
            if (s.charAt(l1) != s.charAt(l2)) return false;
            l1++;
            l2++;
        }
        return true;
    }
}
