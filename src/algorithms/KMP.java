package algorithms;

import java.util.ArrayList;
import java.util.List;

public class KMP {
    // O(m+n) O(m+n)
    public List<Integer> match(String s, String pattern) {
        List<Integer> indexes = new ArrayList<>();
        if(pattern.length() == 0) return indexes;
        int[] next = build(pattern);
        char[] sa = s.toCharArray();
        char[] pa = pattern.toCharArray();
        for(int i = 0, j = 0; i < s.length(); i++) {
            while(j > 0 && sa[i] != pa[j]) {
                j = next[j];
            }
            if(sa[i] == pa[j]) {
                j++;
            }
            if(j == pa.length) {
                indexes.add(i - j + 1);
                j = next[j];
            }
        }
        return indexes;
    }

    public int[] build(String pattern) {
        int n = pattern.length();
        // if pattern is empty, we may return something directly, no need KMP.
        if(n < 1) {
            throw new IllegalArgumentException("no need KMP actually!");
        }
        char[] arr = pattern.toCharArray();
        int[] next = new int[n + 1];

        // -1 0 1 2 3
        //    a b c d
        // next[0] is for index -1 , next[1] for index 0, so both of them are 0
        next[0] = next[1] = 0;

        for(int i = 1, j = 0; i < n; i++) {
            while(j > 0 && arr[i] != arr[j]) {
                j = next[j];
            }
            if(arr[i] == arr[j]) {
                j++;
            }
            next[i + 1] = j;    // j == 0 if no match
        }
        return next;
    }
}
