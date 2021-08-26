package google;

import java.util.*;

// Longest String Chain
public class Q1048 {
    // O(nlgn + nl^2) O(nl)
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, (w1, w2) -> w1.length() - w2.length());

        int ans = 0;
        for(String word : words) {

            int length = 1;
            for(int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                sb.deleteCharAt(i);
                String pre = sb.toString();     // creating pre takes O(l)
                length = Math.max(length, dp.getOrDefault(pre, 0) + 1);
            }
            dp.put(word, length);
            ans = Math.max(ans, length);
        }
        return ans;
    }
    // O(nl^2) O(nl) - faster than method1
    public int longestStrChain1(String[] words) {
        Map<String, Integer> memo = new HashMap<>();
        Set<String> set = new HashSet<>();
        Collections.addAll(set, words);

        int ans = 0;
        for(String word : words) {
            ans = Math.max(ans, dfs(memo, set, word));
        }
        return ans;
    }
    private int dfs(Map<String, Integer> memo, Set<String> set, String word) {
        if(memo.containsKey(word)) {
            return memo.get(word);
        }

        int max = 1, len = word.length();
        StringBuilder sb = new StringBuilder(word);
        for(int i = 0; i < len; i++) {
            sb.deleteCharAt(i);
            String tmp = sb.toString();
            if(set.contains(tmp)) {
                max = Math.max(max, 1 + dfs(memo, set, tmp));
            }
            sb.insert(i, word.charAt(i));
        }
        memo.put(word, max);
        return max;
    }
}
