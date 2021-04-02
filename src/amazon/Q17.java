package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Letter Combinations of a Phone Number
public class Q17 {

    public static void main(String[] args) {
        Q17 q = new Q17();
        List<String> res = q.letterCombinations("23");  // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(Arrays.toString(res.toArray()));
    }

    private String[] map = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    // O(4^n) O(n)
    // StringBuilder比String concatenation快,因为后者不停的在创建新的instance.
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        backtrack(res, new StringBuilder(), digits, 0);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder sb, String digits, int index) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        for (char c : map[digits.charAt(index) - '0'].toCharArray()) {
            sb.append(c);
            backtrack(res, sb, digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    // method2 !!!
    public List<String> letterCombinations1(String digits) {
        LinkedList<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        res.add("");
        for (int i = 0; i < digits.length(); i++) {
            while (res.peek().length() == i) {
                String tmp = res.poll();
                for (char c : map[digits.charAt(i) - '0'].toCharArray()) {
                    res.add(tmp + c);
                }
            }
        }
        return res;
    }

    private static final String[] KEYS = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations4(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return list;
        }
        help(digits, "", 0, list);
        return list;
    }

    private void help(String digits, String prefix, int index, List<String> res) {
        if (index == digits.length()) {
            res.add(prefix);
            return;
        }
        for (char c : KEYS[digits.charAt(index) - '0'].toCharArray()) {
            help(digits, prefix + c, index + 1, res);
        }
    }

}
