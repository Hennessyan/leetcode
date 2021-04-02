package microsoft;
// Wildcard Matching
public class Q44 {

    public boolean isMatch(String s, String p) {
        if(s == null || p == null) {
            return false;
        }
        int slen = s.length(), plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[0][0] = true;
        for(int i = 1; i <= plen; i++) {
            if(p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i-1];
            }
        }
        for(int i = 1; i <= slen; i++) {
            for(int j = 1; j <= plen; j++) {
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                }else if(p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
            }
        }
        return dp[slen][plen];
    }

    public boolean isMatch1(String s, String p) {
        int sp = 0, pp = 0;
        int start = -1, match = 0;
        //while(sp < s.length() && pp < p.length()) {   // wrong because of -> "aa" "a"
        while(sp < s.length()) {
            if(pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            } else if(pp < p.length() && p.charAt(pp) == '*') {
                start = pp;
                match = sp;
                pp++;
            } else if(start != -1) {
                // has pattern match, check start from 0 match.
                // Backtrack: check the situation
                // when '*' matches one more character
                pp = start + 1;
                match++;
                sp = match;
            } else {    // not match
                return false;
            }
        }
        while(pp < p.length() && p.charAt(pp) == '*') {
            pp++;
        }
        return pp == p.length();
    }
}
