package challenge.june;

import java.util.HashSet;
import java.util.Set;

// Longest Duplicate Substring
public class Q1044 {

    public static void main(String[] args) {
        Q1044 q = new Q1044();
        System.out.println(q.longestDupSubstring("banana"));    // "ana"
        System.out.println(q.longestDupSubstring("abcd"));      // ""
    }
    //Rabin-Karp algorithm
    // O(nlgn)
    // SC - 理想状况下重复使用MEMORY -> O(n).
    private static final long MOD = (long)Math.pow(2, 32);
    public String longestDupSubstring(String S) {
        int n = S.length();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = (int)S.charAt(i) - (int)'a';
        }
        int l = 1, r = n;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(findDup(arr, m) != -1) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        int start = findDup(arr, l - 1);        // start = 1, l = 1 if S = "abcd"
        return S.substring(start, start + l - 1);
    }
    //把一些固定重复使用的local variable当作参数传入更好一些.
    private int findDup(int[] arr, int len) {
        int n = arr.length;
        // Can't use Integer.MAX_VALUE as MOD
        Set<Long> set = new HashSet<>();
        long value = 0l;
        long base = 1l;
        for(int i = 0; i < len; i++) {
            value = (value * 26 + arr[i]) % MOD;
            base  = (base * 26) % MOD;
        }
        set.add(value);
        for(int i = 1; i < n - len + 1; i++) {
            // rolling hash - O(1)
            //  h = (h - s.charAt(i-1)*pow + MOD) % MOD;   -->  maybe negative value too large that plus a MOD cannot make it positive
            //  h = (h - s.charAt(i-1)*pow%MOD) % MOD;  --> can be negative
            // correct version
            value = (value * 26 - base * arr[i - 1] % MOD + MOD) % MOD;
            value = (value + arr[i + len - 1]) % MOD;
            if(set.contains(value)) {
                return i;
            }
            set.add(value);
        }
        return -1;
    }
}
