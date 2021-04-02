package challenge.feb21;

import java.util.*;

// Longest Word in Dictionary
public class Q720 {
    // O(nxlgn + nx^2) O(nx^2)
    public String longestWord(String[] words) {
        Set<String> wordSet = new HashSet<>();
        for(String word : words) {
            wordSet.add(word);
        }
        Arrays.sort(words, (a, b) -> a.length() == b.length() ?
                a.compareTo(b) : b.length() - a.length());
        for(String word : words) {
            boolean found = true;
            for(int k = 1; k < word.length(); k++) {
                if(!wordSet.contains(word.substring(0, k))) {   // O(nx^2)
                    found = false;
                    break;
                }
            }
            if(found) {
                return word;
            }
        }
        return "";
    }
    // O(nx) O(nx)
    public String longestWord1(String[] words) {
        Trie trie = new Trie();
        int index = 0;
        for(String word : words) {
            trie.insert(word, ++index);
        }
        trie.words = words;
        return trie.dfs();
    }
    class TrieNode {
        Map<Character, TrieNode> children;
        int end = 0;
        public TrieNode() {
            children = new HashMap<>();
        }
    }
    class Trie {
        TrieNode root;
        String[] words;
        public Trie() {
            root = new TrieNode();
        }
        public void insert(String word, int index) {
            TrieNode cur = root;
            for(char ch : word.toCharArray()) {
                cur.children.putIfAbsent(ch, new TrieNode());
                cur = cur.children.get(ch);
            }
            cur.end = index;
        }
        public String dfs() {
            String longest = "";
            TrieNode cur = root;
            Stack<TrieNode> stack = new Stack<>();
            stack.push(cur);
            while(!stack.isEmpty()) {
                cur = stack.pop();
                if(cur.end > 0 || cur == root) {
                    if(cur != root) {
                        String w = words[cur.end - 1];
                        if(w.length() > longest.length() ||
                                (w.length() == longest.length() && w.compareTo(longest) < 0)) {
                            longest = w;
                        }
                    }
                    for(TrieNode node : cur.children.values()) {
                        stack.push(node);
                    }
                }
            }
            return longest;
        }
    }
}
