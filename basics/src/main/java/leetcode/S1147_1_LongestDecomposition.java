package leetcode;

/**
 * 1147 - 段式回文: https://leetcode-cn.com/problems/longest-chunked-palindrome-decomposition/
 */
public class S1147_1_LongestDecomposition {

    public int longestDecomposition(String text) {
        return solve(text, 0, text.length() - 1);
    }

    private int solve(String s, int left, int right) {
        if (left > right) return 0;

        for (int i = left, j = right; i < j; i++, j--) {
            // s[left, i] == s[j, right] ? 需要注意, 有可能存在哈希冲突
            if (equal(s, left, i, j, right)) {
                return 2 + solve(s, i + 1, j - 1);
            }
        }

        return 1;
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
