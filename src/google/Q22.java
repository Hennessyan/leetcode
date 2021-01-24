package google;

import java.util.ArrayList;
import java.util.List;

// Generate Parentheses
public class Q22 {
    // the number of result: nth catalan number => Catalan(n) = (2n)!/((n+1)!*n!)
    // TC / SC : O(n*Catalan(n)) => n * (4^n/(n*sqrt(n))) => 4^n / sqrt(n)
    public List<String> generateParenthesis0(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", n, n);
        return res;
    }
    private void backtrack(List<String> res, String str, int l, int r) {
        if(r == 0) {
            res.add(str);
            return;
        }
        if(l > 0) {
            backtrack(res, str + "(", l - 1, r);
        }
        if(l < r) {
            backtrack(res, str + ")", l, r - 1);
        }
    }
    // brute force - backtrack
    // O(2^(2n) * n)
    // O(2^(2n) * n) - naively, each combination can be result
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generateAll(new char[2 * n], 0, list);
        return list;
    }
    private void generateAll(char[] current, int pos, List<String> list) {
        if(pos == current.length) {
            if(isValid(current)) {
                list.add(new String(current));
            }
            return;
        }
        current[pos] = '(';
        generateAll(current, pos + 1, list);
        current[pos] = ')';
        generateAll(current, pos + 1, list);
    }
    private boolean isValid(char[] chs) {
        int count = 0;
        for(char c : chs) {
            if(c == '(') {
                count++;
            } else {
                count--;
            }
            if(count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}
