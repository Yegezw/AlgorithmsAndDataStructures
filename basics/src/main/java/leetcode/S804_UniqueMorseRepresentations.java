package leetcode;

import java.util.Set;
import java.util.TreeSet;

/**
 * 804 - 唯一摩尔斯密码词: https://leetcode-cn.com/problems/unique-morse-code-words/
 */
public class S804_UniqueMorseRepresentations {

    /**
     * a ~ z: 97 ~ 122<br>
     * codes[word.charAt(i) - 'a']<br>
     * codes[word.charAt(i) - 97]
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> set = new TreeSet<>();

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                sb.append(codes[word.charAt(i) - 'a']);
            }
            set.add(sb.toString());
            sb.delete(0, sb.length());
        }

        return set.size();
    }
}
