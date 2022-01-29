package doordash;
// Shortest Palindrome
public class Q214 {

    // brute-force : O(n^2) O(n) - TLE
    public String shortestPalindrome1(String s) {
        String rev_s = reverse(s);
        int len = s.length();
        for(int i = 0; i <= len; i++) {
            if(s.substring(0, len - i).equals(rev_s.substring(i))) {
                return rev_s.substring(0, i) + s;
            }
        }
        return "";
    }
    private String reverse(String s) {
        char[] chs = s.toCharArray();
        int l = 0, r = chs.length - 1;
        while(l < r) {
            char tmp = chs[l];
            chs[l++] = chs[r];
            chs[r--] = tmp;
        }
        return new String(chs);
    }
    // O(n^2) O(n)
    // best case => palindrome
    // worst case => aababababab => T(n) = T(n-2) + O(n) = O(n) + O(n-2) + ... + O(1) => O(n^2)
    public String shortestPalindrome(String s) {
        int n = s.length(), i = 0;
        for(int j = n - 1; j >= 0; j--) {
            if(s.charAt(i) == s.charAt(j)) {
                i++;
            }
        }
        if(i == n) return s;
        String revs = reverse(s.substring(i));
        return revs + shortestPalindrome(s.substring(0, i)) + s.substring(i);
    }
    // KMP : O(n) O(n)
    public String shortestPalindrome2(String s) {
        int n = s.length(), i = 0;
        String revs = reverse(s);
        String ns = s + '*' + revs;
        int[] next = getNext(ns);
        int len = ns.length();
        return revs.substring(0, n - next[len]) + s;
    }


    private int[] getNext(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        int[] next = new int[n + 1];
        next[0] = next[1] = 0;
        for(int i = 1, j = 0; i < n; i++) {
            while(j != 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j];
            }
            if(s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i + 1] = j;
        }
        return next;
    }

}
