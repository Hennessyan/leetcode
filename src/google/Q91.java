package google;
// Decode Ways
public class Q91 {
    // O(n) O(n)
    public int numDecodings1(String s) {
        int n = s.length();
        if(n == 0 || s.charAt(0) == '0') return 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            char pre = s.charAt(i - 2);
            char cur = s.charAt(i - 1);
            if(cur == '0') {
                if(pre == '1' || pre == '2') {
                    dp[i] += dp[i - 2];
                } else {
                    return 0;
                }
            } else {
                if(cur >= '1' && cur <= '9') {
                    dp[i] += dp[i - 1];
                    if(pre == '1') {
                        dp[i] += dp[i - 2];
                    }
                }
                if(cur >= '1' && cur <= '6') {
                    if(pre == '2') dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
    }

    public int numDecodings2(String s) {
        if(s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            int cur = s.charAt(i - 1) - '0';
            if(cur > 0 && cur <= 9) {
                dp[i] += dp[i - 1];
            }
            if(i > 1) {
                int pre = s.charAt(i - 2) - '0';
                pre = pre * 10 + cur;
                if(pre >= 10 && pre <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
    }
    // O(n) O(1)
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int twoBack = 1;
        int oneBack = 1;
        for (int i = 1; i < n; i++) {
            int current = 0;
            if (s.charAt(i) != '0') {
                current = oneBack;
            }
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigit >= 10 && twoDigit <= 26) {
                current += twoBack;
            }

            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }
}
