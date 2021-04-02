package microsoft;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Letter Case Permutation
public class Q784 {
    // O(2^n*n) O(2^n*n) extra n for string construct
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        if(S == null || S.length() == 0) {
            return res;
        }
        char[] chs = new char[S.length()];
        backtrack(S, 0, chs, res);
        return res;
    }
    private void backtrack(String s, int i, char[] chs, List<String> res) {
        if(i == s.length()) {
            res.add(new String(chs));
            return;
        }
        char c = s.charAt(i);
        if(Character.isLetter(c)) {
            chs[i] = Character.toLowerCase(c);
            backtrack(s, i + 1, chs, res);
            chs[i] = Character.toUpperCase(c);
            backtrack(s, i + 1, chs, res);
        } else {
            chs[i] = c;
            backtrack(s, i + 1, chs, res);
        }
    }
    // O(2^n*n) O(2^n)
    public List<String> letterCasePermutation2(String S) {
        List<StringBuilder> list = new LinkedList<>();
        List<String> res = new LinkedList<>();
        list.add(new StringBuilder());

        for(char c : S.toCharArray()) {
            int n = list.size();
            if(Character.isLetter(c)) {
                for(int j = 0; j < n; j++) {
                    list.add(new StringBuilder(list.get(j)));
                    list.get(j).append(Character.toLowerCase(c));
                    list.get(j + n).append(Character.toUpperCase(c));
                }
            } else {
                for(int j = 0; j < n; j++) {
                    list.get(j).append(c);
                }
            }
        }
        for(StringBuilder sb : list) {
            res.add(sb.toString());
        }
        return res;
    }
}
