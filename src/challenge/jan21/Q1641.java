package challenge.jan21;

import java.util.Arrays;

// Count Sorted Vowel Strings
public class Q1641 {


    // https://leetcode.com/problems/count-sorted-vowel-strings/solution/
    // O(n^5) O(n)
    public int countVowelStrings0(int n) {
        return countVowelStringUtil0(n, 1);
    }
    int countVowelStringUtil0(int n, int vowels) {
        if (n == 0)
            return 1;
        int result = 0;
        for (int i = vowels; i <= 5; i++) {
            result += countVowelStringUtil0(n - 1, i);
        }
        return result;
    }
    // O(n) O(n) - the total number of nodes in the recursion tree would be n⋅5,
    // count[n][vols] = count[n-1][vols] + count[n][vols-1]
    public int countVowelStrings1(int n) {
        int memo[][] = new int[n + 1][6];
        return countVowelStringUtil1(n, 5, memo);
    }

    int countVowelStringUtil1(int n, int vowels, int memo[][]) {
        if (n == 1)
            return vowels;
        if (vowels == 1)
            return 1;
        if (memo[n][vowels] != 0)
            return memo[n][vowels];
        int res = countVowelStringUtil1(n - 1, vowels, memo) +
                countVowelStringUtil1(n, vowels - 1, memo);
        memo[n][vowels] = res;
        return res;
    }
    // DP : O(n) O(n)
    public int countVowelStrings2(int n) {
        int[][] dp = new int[n + 1][6];
        for (int vowels = 1; vowels <= 5; vowels++)
            dp[1][vowels] = vowels;
        for (int nValue = 2; nValue <= n; nValue++) {
            dp[nValue][1] = 1;
            for (int vowels = 2; vowels <= 5; vowels++) {
                dp[nValue][vowels] = dp[nValue][vowels - 1] + dp[nValue - 1][vowels];
            }
        }
        return dp[n][5];
    }
    // DP : O(n) O(1) - use this method
    public int countVowelStrings(int n) {
        if(n == 0) {
            return 0;
        }
        int[] dp = new int[5];
        Arrays.fill(dp, 1);
        while(--n > 0) {
            for(int i = 1; i <= 4; i++) {
                dp[i] = dp[i] + dp[i - 1];
            }
        }
        int sum = 0;
        for(int val : dp) {
            sum += val;
        }
        return sum;
    }
}
