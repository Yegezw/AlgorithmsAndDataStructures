package leetcode;

/**
 * 1147 - 段式回文: https://leetcode-cn.com/problems/longest-chunked-palindrome-decomposition/
 */
public class S1147_2_LongestDecomposition {

    private final long MOD = (long) (1e9 + 7);
    private long[] pow26;

    public int longestDecomposition(String text) {
        pow26 = new long[text.length()];
        pow26[0] = 1;
        for (int i = 1; i < pow26.length; i++) {
            pow26[i] = (pow26[i - 1] * 26) % MOD;
        }

        return solve(text, 0, text.length() - 1);
    }

    private int solve(String s, int left, int right) {
        if (left > right) return 0;

        long prevHash = 0;
        long postHash = 0;

        // s[left, i] == s[j, right] ? 注意, 有可能存在哈希冲突
        for (int i = left, j = right; i < j; i++, j--) {
            prevHash = (prevHash * 26 + (s.charAt(i) - 'a')) % MOD;
            postHash = ((s.charAt(j) - 'a') * pow26[right - j] + postHash) % MOD;
            if (prevHash == postHash && equal(s, left, i, j, right)) {
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
