package challenge.september;

import java.util.HashSet;
import java.util.Set;

// Repeated Substring Pattern
// Q1044(H) Longest Duplicate Substring
public class Q459 {

    public static void main(String[] args) {
        Q459 q = new Q459();
        System.out.println(q.repeatedSubstringPattern("abab")); // true
        System.out.println(q.repeatedSubstringPattern("aba"));  // false
    }

    // O(n^2) O(n)
    // patternpatternpatternpattern
    // pattern1pattern2pattern1pattern2
    public boolean repeatedSubstringPattern1(String s) {
        return (s + s).substring(1, 2 * s.length() - 1).contains(s);
    }

    // O(n*sqrt(n)) O(sqrt(n))
    // Rabin-Karp : multiple pattern match
    public boolean repeatedSubstringPattern2(String s) {
        int n = s.length();
        if(n < 2) return false;
        if(n == 2) return s.charAt(0) == s.charAt(1);

        for(int i = (int)Math.sqrt(n); i > 0; i--) {    // i == 1 => "zzz"
            Set<Integer> set = new HashSet<>();
            if(n % i == 0) {
                set.add(i);
                if(i != 1) {
                    set.add(n / i);
                }
                for(int l : set) {
                    String t = s.substring(0, l);
                    int firstHash = t.hashCode();
                    int curHash = firstHash;
                    int start = l;
                    while(start != n && curHash == firstHash) {
                        curHash = s.substring(start, start + l).hashCode();
                        start += l;
                    }
                    if(start == n && curHash == firstHash) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // https://leetcode.com/problems/repeated-substring-pattern/solution/
    // KMP : single pattern match
    // O(n) O(n)
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] dp = new int[n];
        for(int i = 1; i < n; i++) {
            int j = dp[i - 1];
            while(j > 0 && s.charAt(j) != s.charAt(i)) {
                j = dp[j - 1];
            }
            if(s.charAt(j) == s.charAt(i)) {
                j++;
            }
            dp[i] = j;
        }
        int l = dp[n - 1];
        return l != 0 && n % (n - l) == 0;
    }
}
// a b c a b c
// 0 0 0 1 2 3