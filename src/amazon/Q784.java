package amazon;

import java.util.ArrayList;
import java.util.List;

// Letter Case Permutation
public class Q784 {
    // O(n*2^n) O(n*2^n)
    public List<String> letterCasePermutation0(String S) {
        List<String> list = new ArrayList<>();
        List<StringBuilder> tmp = new ArrayList<>();
        tmp.add(new StringBuilder());

        for(char c : S.toCharArray()) {
            int n = tmp.size();
            if(Character.isLetter(c)) {
                for(int i = 0; i < n; i++) {
                    tmp.add(new StringBuilder(tmp.get(i)));
                    tmp.get(i).append(Character.toLowerCase(c));
                    tmp.get(i+n).append(Character.toUpperCase(c));
                }
            } else {
                for(int i = 0; i < n; i++) {
                    tmp.get(i).append(c);
                }
            }
        }

        for(StringBuilder sb : tmp) {
            list.add(sb.toString());
        }
        return list;
    }

    public List<String> letterCasePermutation1(String S) {
        List<String> list = new ArrayList<>();
        char[] chs = S.toCharArray();
        int letters = 0;
        for(char c : chs) {
            if(Character.isLetter(c)) {
                letters++;
            }
        }

        for(int i = 0; i < (1 << letters); i++) {
            StringBuilder sb = new StringBuilder();
            int j = letters - 1;    //注意是letters-1 不过从0开始也行.因为只是要bit manipulation来决定0/1.
            for(char c : chs) {
                if(Character.isLetter(c)) {
                    if((i & (1 << j--)) == 0) {
                        sb.append(Character.toLowerCase(c));
                    } else {
                        sb.append(Character.toUpperCase(c));
                    }
                } else {
                    sb.append(c);
                }
            }
            list.add(sb.toString());
        }
        return list;
    }

    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        char[] chs = new char[S.length()];
        backtrack(res, chs, 0, S);
        return res;
    }

    private void backtrack(List<String> res, char[] chs, int start, String s) {
        if(start == s.length()) {
            res.add(new String(chs));
            return;
        }

        char c = s.charAt(start);
        if(Character.isDigit(c)) {
            chs[start] = c;
            backtrack(res, chs, start + 1, s);
        } else {
            chs[start] = Character.toLowerCase(c);
            backtrack(res, chs, start + 1, s);
            chs[start] = Character.toUpperCase(c);
            backtrack(res, chs, start + 1, s);
        }
    }
}
