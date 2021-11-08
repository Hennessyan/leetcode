package facebook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Palindrome Partitioning
// Q267
public class Q131 {

    // O(n*2^n) O(n)
    // method0
    public List<List<String>> partition0(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        dfs(0, result, new ArrayList<String>(), s);
        return result;
    }

    void dfs(int start, List<List<String>> result, List<String> currentList, String s) {
        if (start >= s.length()) result.add(new ArrayList<String>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                // add current substring in the currentList
                currentList.add(s.substring(start, end + 1));
                dfs(end + 1, result, currentList, s);
                // backtrack and remove the current substring from currentList
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) return false;
        }
        return true;
    }
    // O(n*2^n) O(n^2)
    // method1
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if(s == null || s.length() == 0) {
            return res;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        for(int i = 0; i < len; i++) {
            for(int j = 0; j <= i; j++) {
                if(s.charAt(j) == s.charAt(i) && (i - j <= 2 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                }
            }
        }
        backtrack(res, new LinkedList<>(), 0, s, dp);
        return res;
    }

    private void backtrack(List<List<String>> res, LinkedList<String> list, int start, String s, boolean[][] dp) {
        if(start == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i < s.length(); i++) {
            if(dp[start][i]) {
                list.add(s.substring(start, i + 1));
                backtrack(res, list, i + 1, s, dp);
                list.removeLast();
            }
        }
    }
    // method2
    public List<List<String>> partition1(String s) {
        List<List<String>> res = new ArrayList<>();
        if(s == null || s.length() == 0) {
            return res;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        dfs1(res, new LinkedList<>(), 0, s, dp);
        return res;
    }
    private void dfs1(List<List<String>> res, LinkedList<String> curList, int start, String s, boolean[][] dp) {
        if(start == s.length()) {
            res.add(new ArrayList<>(curList));
            return;
        }
        for(int end = start; end < s.length(); end++) {
            if(s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start+1][end-1])) {
                dp[start][end] = true;
                curList.add(s.substring(start, end + 1));
                dfs1(res,curList, end + 1, s, dp);
                curList.removeLast();
            }
        }
    }
}
