package amazon;
// Count Substrings with Only One Distinct Letter
public class Q1180 {
    // O(n) O(1)
    public int countLetters0(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int res = 0;
        int cur = 1;
        for (int i = 1; i < n; i ++) {
            if (cs[i-1] == cs[i]) {
                cur ++;
            } else {
                res += cur * (cur + 1) / 2;
                cur = 1;
            }
        }
        res += cur * (cur + 1) / 2;
        return res;
    }

    public int countLetters(String s) {
        int total = 0, n = s.length(), num = 0;
        for(int l = 0, r = 0; r <= n; r++) {
            if(r == n || s.charAt(r) != s.charAt(l)) {
                num = r - l;
                total += num * (num + 1) / 2;
                l = r;
            }
        }
        return total;
    }
    // dp[i] : number of combination from 0 to i
    // aa - dp[0] = 1, dp[1] = 2 => dp[0] + dp[1] = 3
    public int countLetters1(String S) {
        int total = 1, count = 1;
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == S.charAt(i-1)) {
                count++;
            } else {
                count = 1;
            }
            total += count;
        }
        return total;
    }
}
