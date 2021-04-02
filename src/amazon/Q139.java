package amazon;

import java.util.*;

// Word Break
public class Q139 {
    // brute force : O(2^n) O(n) - TLE
    public boolean wordBreak1(String s, List<String> wordDict) {
        return dfs(s, new HashSet<>(wordDict), 0);
    }
    private boolean dfs(String s, Set<String> set, int i) {
        if(i == s.length()) {
            return true;
        }
        for(int j = i + 1; j <= s.length(); j++) {
            if(set.contains(s.substring(i, j)) && dfs(s, set, j)) {
                return true;
            }
        }
        return false;
    }
    // recursion with memorization (top-down) : O(n^3) O(n)
    public boolean wordBreak2(String s, List<String> wordDict) {
        return wordBreak(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }
    private boolean wordBreak(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && wordBreak(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }
    // BFS : O(n^3) O(n)
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (!visited[start]) {
                visited[start] = true;
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // DP : O(n^3) O(n) (bottom-up)
    public boolean wordBreak4(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
