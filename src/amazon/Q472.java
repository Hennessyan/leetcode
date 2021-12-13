package amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Concatenated Words
public class Q472 {
    // O(m * n^3) O(mn)
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(String w : words) {
            set.add(w);
        }
        if(set.size() <= 1) {
            return res;
        }
        for(String w : words) {
            if(w.length() != 0) {   // corner case!
                set.remove(w);
                if(canBreak(set, w)) {
                    res.add(w);
                }
                set.add(w);
            }
        }
        return res;
    }
    private boolean canBreak(Set<String> set, String word) {
        int n = word.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j]) {
                    String tmp = word.substring(j, i);
                    if(set.contains(tmp)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[n];
    }

    // Trie : O(m*n^2) O(mn)
    public List<String> findAllConcatenatedWordsInADict1(String[] words) {
        TrieNode root = new TrieNode();
        //put each word in the Trie
        for(String word : words) {
            insertWord(root, word);
        }
        List<String> result = new ArrayList<>();
        //check each word
        for (String word : words) {
            if (isConcatenated(word, root)){
                result.add(word);
            }
        }
        return result;
    }
    private boolean isConcatenated(String word, TrieNode root) {
        int[] dp = new int[word.length() + 1];
        dp[word.length()] = 0;
        for (int i = word.length() - 1; i >= 0; i--) {
            TrieNode cur = root;
            dp[i] = -1;
            //check word substring from right to left
            for (int j = i; j < word.length(); j++) {
                cur = cur.children[word.charAt(j) - 'a'];
                if (cur == null) {
                    break;
                }
                //if prefix does not exist, no need to check further
                if (cur.isWord && dp[j + 1] != -1) {
                    dp[i] = dp[j + 1] + 1;
                    if (dp[i] >= 2) {   // Can't use dp[i] > 2 here !
                        break;
                    }
                    //once found it can be composed by two words, break
                }
            }
        }
        return dp[0] >= 2;
    }
    private void insertWord(TrieNode root, String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            TrieNode next = cur.children[c - 'a'];
            if (next == null) {
                next = new TrieNode();
                cur.children[c - 'a'] = next;
            }
            cur = next;
        }
        cur.isWord = true;
    }
    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }
}
