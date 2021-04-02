package category.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Word Squares
public class Q425 {

    //O(n^len) O(n * len / len * len) => O(n*len) ???
    //n - num of words, len - len of word

    // https://leetcode.com/problems/word-squares/solution/
    // O(n * 26^l * l)
    // O(n*l + n*l/2) => O(n*l)
    private int len;
    public List<List<String>> wordSquares(String[] words) {
        len = words[0].length();
        List<List<String>> res = new ArrayList<>();
        Trie trie = new Trie();
        for(String word : words) {
            trie.insertWord(word);
        }
        backtrack(trie, words, new ArrayList<>(), res, 0);
        return res;
    }
    private void backtrack(Trie trie, String[] words, List<String> list, List<List<String>> res,  int i) {
        if(i == len) {
            res.add(new ArrayList<>(list));
            return;
        }
        String prefix = "";
        if(i > 0) {
            for(int j = 0; j < i; j++) {
                prefix += list.get(j).charAt(i);
            }
        }
        List<String> next = i == 0 ? Arrays.asList(words) : trie.getWords(prefix);
        for(String n : next) {
            list.add(n);
            backtrack(trie, words, list, res, i + 1);
            list.remove(list.size() - 1);
        }
    }
    class Trie {
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        public void insertWord(String word) {
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(!node.containsKey(c)) {
                    node.put(c, new TrieNode());
                }
                node = node.get(c);
                node.children.add(word);	//建trie的时候直接存prefix后的结果,省了遍历.
            }
//            node.setEnd(word);
        }
        public List<String> getWords(String prefix) {
            List<String> res = new ArrayList<>();
            TrieNode node = searchPrefix(prefix);
            if(node == null) {
                return res;
            }
            return node.children;
//            Stack<TrieNode> stack = new Stack<>();
//            stack.push(node);
//            while(!stack.isEmpty()) {
//                TrieNode tmp = stack.pop();
//                if(tmp.isEnd()) {
//                    res.add(tmp.getEnd());
//                    continue;
//                }
//                for(char c = 'a'; c <= 'z'; c++) {
//                    if(tmp.containsKey(c)) {
//                        stack.push(tmp.get(c));
//                    }
//                }
//            }
//            return res;
        }
        private TrieNode searchPrefix(String prefix) {
            TrieNode node = root;
            for(char c : prefix.toCharArray()) {
                if(!node.containsKey(c)) {
                    return null;
                }
                node = node.get(c);
            }
            return node;
        }
    }
    class TrieNode {
        private TrieNode[] list;
        List<String> children;
        private static final int R = 26;
        String endWord;
        public TrieNode() {
            list = new TrieNode[R];
            endWord = null;
        }
        public boolean containsKey(char c) {
            return list[c - 'a'] != null;
        }
        public TrieNode get(char c) {
            return list[c - 'a'];
        }
        public void put(char c, TrieNode node) {
            list[c - 'a'] = node;
        }
//        public boolean isEnd() {
//            return this.endWord != null;
//        }
//        public String getEnd() {
//            return this.endWord;
//        }
//        public void setEnd(String word) {
//            this.endWord = word;
//        }
    }
}
