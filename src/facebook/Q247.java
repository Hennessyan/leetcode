package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Strobogrammatic Number II
public class Q247 {

    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        if(n % 2 == 0) {
            dfs("", n, res);
        } else {
            dfs("0", n, res);
            dfs("1", n, res);
            dfs("8", n, res);
        }
        return res;
    }
    private void dfs(String prefix, int n, List<String> res) {
        int len = prefix.length();
        if(len == n) {
            res.add(prefix);
            return;
        }
        if(len + 2 != n) {
            dfs('0' + prefix + '0', n, res);
        }
        dfs('1' + prefix + '1', n, res);
        dfs('8' + prefix + '8', n, res);
        dfs('6' + prefix + '9', n, res);
        dfs('9' + prefix + '6', n, res);
    }

    public List<String> findStrobogrammatic1(int n) {
        List<String> res = n % 2 == 0 ? Arrays.asList("") : Arrays.asList("0", "1", "8");
        for(int i = n % 2 == 0 ? 0 : 1; i < n; i += 2) {
            List<String> tmp = new ArrayList<>();
            for(String s : res) {
                if(i + 2 < n) {
                    tmp.add('0' + s + '0');
                }
                tmp.add('1' + s + '1');
                tmp.add('8' + s + '8');
                tmp.add('6' + s + '9');
                tmp.add('9' + s + '6');
            }
            res = tmp;
        }
        return res;
    }
}
