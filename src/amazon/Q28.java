package amazon;

import java.util.Arrays;

//  Implement strStr()
public class Q28 {
    // what should we return if needle is empty? => 0
    // O(m*n) O(1)
    public int strStr(String haystack, String needle) {
        int j = 0, m = haystack.length(), n = needle.length();
        for(int i = 0; i < m - n + 1; i++) {
            for(j = 0; j < n; j++) {
                if(haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if(j == n) return i;
        }
        return -1;
    }
    // KMP : O(m+n) O(m+n)
    public int strStr1(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        if(n == 0) return 0;
        char[] a1 = haystack.toCharArray();
        char[] a2 = needle.toCharArray();
        int[] next = build(needle, n, a2);
        for(int i = 0, j = 0; i < m; i++) {
            while(j > 0 && a1[i] != a2[j]) {
                j = next[j];
            }
            if(a1[i] == a2[j]) {
                j++;
            }
            if(j == n) {
                return i - j + 1;
            }
        }
        return -1;
    }
    private int[] build(String needle, int n, char[] chs) {
        int[] next = new int[n + 1];
        next[0] = next[1] = 0;
        for(int i = 1, j = 0; i < n; i++) {
            while(j > 0 && chs[i] != chs[j]) {
                j = next[j];
            }
            if(chs[i] == chs[j]) {
                j++;
            }
            next[i + 1] = j;
        }
        return next;
    }
    // Q459 - 0-based index
    public int strStr2(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        if(n == 0) return 0;
        char[] a1 = haystack.toCharArray();
        char[] a2 = needle.toCharArray();
        int[] next = build(a2, n);
        System.out.println(Arrays.toString(next));
        for(int i = 0, j = 0; i < m; i++) {
            while(j > 0 && a1[i] != a2[j]) {
                j = next[j - 1];
            }
            if(a1[i] == a2[j]) {
                j++;
            }
            if(j == n) {
                return i - j + 1;
            }
        }
        return -1;
    }
    public int[] build(char[] chs, int n) {
        int[] next = new int[n];
        for(int i = 1; i < n; i++) {
            int j = next[i - 1];
            while(j > 0 && chs[i] != chs[j]) {
                j = next[j - 1];
            }
            if(chs[i] == chs[j]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
