package leetcode;

import java.util.Stack;

/**
 * 20 - 有效的括号: https://leetcode-cn.com/problems/valid-parentheses/
 */
public class S20_ValidParentheses {

    /**
     * 给定一个只包括 '('、')'、'{'、'}'、'['、']'的字符串 s, 判断字符串是否有效
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false; // 注意

                Character topChar = stack.pop();
                if (c == ')' && topChar != '(') return false;
                if (c == '}' && topChar != '{') return false;
                if (c == ']' && topChar != '[') return false;
            }
        }

        return stack.isEmpty();                    // 注意
    }

    public static void main(String[] args) {
        System.out.println(new S20_ValidParentheses().isValid("(("));
        System.out.println(new S20_ValidParentheses().isValid("((){}"));
        System.out.println(new S20_ValidParentheses().isValid("{()}[]"));
    }

}
