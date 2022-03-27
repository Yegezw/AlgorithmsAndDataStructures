package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 187 - 重复的 DNA 序列: https://leetcode-cn.com/problems/repeated-dna-sequences/
 */
public class S187_1_FindRepeatedDnaSequences {

    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        // s[i, i + 9]
        for (int i = 0; i + 9 < s.length(); i++) {
            String curStr = s.substring(i, i + 10);
            if (seen.contains(curStr)) {
                res.add(curStr);
            } else {
                seen.add(curStr);
            }
        }

        return new LinkedList<>(res);
    }
}
