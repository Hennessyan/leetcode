package challenge.june;
// Is Subsequence
// Similar: Q792 Q1055
public class Q392 {

    public static void main(String[] args) {
        Q392 q = new Q392();
        System.out.println(q.isSubsequence("ace", "abcde"));
        System.out.println(q.isSubsequence("axc", "ahbgdc"));
    }

    // O(m+n) O(1)
    public boolean isSubsequence(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        int i = 0, j = 0;
        while(i < sLen && j < tLen) {
            if(s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == sLen;
    }

    // O(mn) O(mn)
    public boolean isSubsequence1(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        int[][] dp = new int[tLen + 1][sLen + 1];
        if(sLen == 0) {
            return true;
        }
        for(int i = 1; i <= tLen; i++) {
            for(int j = 1; j <= sLen; j++) {
                if(s.charAt(j - 1) == t.charAt(i - 1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
            if(dp[i][sLen] == sLen) {
                return true;
            }
        }
        return false;
    }
}
