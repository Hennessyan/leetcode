package apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

// Shortest Distance to a Character
public class Q821 {

    public static void main(String[] args) {
        Q821 q = new Q821();
        int[] res = q.shortestToChar("loveleetcode", 'e');
        System.out.println(Arrays.toString(res));
    }
    // O(n) O(n)
    public int[] shortestToChar1(String s, char c) {
        int n = s.length();
        int[] res = new int[n];
        char[] chs = s.toCharArray();
        List<Integer> indexC = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(chs[i] == c) {
                indexC.add(i);
            }
        }
        int j = 0, index = 0;
        while(j <= indexC.get(index)) {
            res[j] = indexC.get(index) - j;
            j++;
        }
        index++;
        while(index < indexC.size()) {
            int l = indexC.get(index - 1);
            int r = indexC.get(index);
            int m = l + (r - l) / 2;
            while(j <= m) {
                res[j] = j - l;
                j++;
            }
            while(j > m && j <= r) {
                res[j] = r - j;
                j++;
            }
            index++;
        }
        while(j < n) {
            res[j] = j - indexC.get(index - 1);
            j++;
        }
        return res;
    }

    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] res = new int[n];
        int pre = -10000;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == c) {
                pre = i;
            }
            res[i] = i - pre;
        }
        pre = Integer.MAX_VALUE / 2;    // should not use 10000 here !!!
        for(int i = n - 1; i >= 0; i--) {
            if(s.charAt(i) == c) {
                pre = i;
            }
            res[i] = Math.min(res[i], pre - i);
        }
        return res;
    }
}
