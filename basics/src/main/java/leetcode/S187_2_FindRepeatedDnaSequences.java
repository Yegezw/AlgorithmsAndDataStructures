package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 187 - 重复的 DNA 序列: https://leetcode-cn.com/problems/repeated-dna-sequences/
 */
public class S187_2_FindRepeatedDnaSequences {

    /**
     * 10 进制
     */
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) return new ArrayList<>();

        HashSet<Long> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();

        int[] map = new int[256];
        map['A'] = 1;
        map['C'] = 2;
        map['G'] = 3;
        map['T'] = 4;

        long hash = 0;
        long ten9 = (long) 1e9;

        // hash(s[0, 8])
        for (int i = 0; i < 9; i++) {
            hash = hash * 10 + map[s.charAt(i)];
        }

        // hash(s[i - 9, i])
        for (int i = 9; i < s.length(); i++) {
            hash = hash * 10 + map[s.charAt(i)];
            if (seen.contains(hash)) {
                res.add(s.substring(i - 9, i + 1));
            } else {
                seen.add(hash);
            }
            hash -= map[s.charAt(i - 9)] * ten9;
        }

        return new ArrayList<>(res);
    }

    /**
     * 4 进制
     */
    public List<String> findRepeatedDnaSequences2(String s) {
        if (s.length() < 10) return new ArrayList<>();

        int[] map = new int[256];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;

        HashSet<Long> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();

        long hash = 0;
        long four9 = (long) Math.pow(4, 9);

        // hash(s[0, 8])
        for (int i = 0; i < 9; i++) {
            hash = hash * 4 + map[s.charAt(i)];
        }

        // hash(s[i - 9, i])
        for (int i = 9; i < s.length(); i++) {
            hash = hash * 4 + map[s.charAt(i)];
            if (seen.contains(hash)) {
                res.add(s.substring(i - 9, i + 1));
            } else {
                seen.add(hash);
            }
            hash -= map[s.charAt(i - 9)] * four9;
        }

        return new ArrayList<>(res);
    }
}
