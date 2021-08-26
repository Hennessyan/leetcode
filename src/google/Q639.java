package google;
// Decode Ways II
// Q91
public class Q639 {
    // DP : O(n) O(n)
    public int numDecodings(String s) {
        int M = (int) (1e9 + 7);
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
        for(int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if(c == '*') {
                dp[i + 1] = 9 * dp[i] % M;  // need mode here !!!
                if(s.charAt(i - 1) == '1') {
                    dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % M;
                } else if(s.charAt(i - 1) == '2') {
                    dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;
                } else if(s.charAt(i - 1) == '*') {
                    dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;
                }
            } else {
                dp[i + 1] = c == '0' ? 0 : dp[i];
                if(s.charAt(i - 1) == '1') {
                    dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                } else if(s.charAt(i - 1) == '2' && c <= '6') {
                    dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                } else if(s.charAt(i - 1) == '*') {
                    dp[i + 1] = (dp[i + 1] + (c <= '6' ? 2 : 1) * dp[i - 1]) % M;
                }
            }
        }
        return (int) dp[n];
    }
    // O(n) O(1)
    int M = 1000000007;
    public int numDecodings1(String s) {
        long first = 1, second = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            long temp = second;
            if (s.charAt(i) == '*') {
                second = 9 * second % M;
                if (s.charAt(i - 1) == '1')
                    second = (second + 9 * first) % M;
                else if (s.charAt(i - 1) == '2')
                    second = (second + 6 * first) % M;
                else if (s.charAt(i - 1) == '*')
                    second = (second + 15 * first) % M;
            } else {
                second = s.charAt(i) != '0' ? second : 0;
                if (s.charAt(i - 1) == '1')
                    second = (second + first) % M;
                else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                    second = (second + first) % M;
                else if (s.charAt(i - 1) == '*')
                    second = (second + (s.charAt(i) <= '6' ? 2 : 1) * first) % M;
            }
            first = temp;
        }
        return (int) second;
    }
}
