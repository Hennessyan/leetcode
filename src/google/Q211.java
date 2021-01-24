package google;

import java.util.HashMap;
import java.util.Map;

// Design Add and Search Words Data Structure
public class Q211 {
    // addWord - O(m) O(m) m -> length of word
    // search - O(n*26^m) O(m) m -> number of dots
    // O(n) O(1) if no dot
    class WordDictionary {

        TrieNode root;
        /** Initialize your data structure here. */
        public WordDictionary() {
            root = new TrieNode();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            TrieNode node = root;
            for(char c : word.toCharArray()){
                if(!node.containsKey(c)){
                    node.put(c, new TrieNode());
                }
                node = node.get(c);
            }
            node.setEnd();
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return search(word, root);
        }

        private boolean search(String word, TrieNode root){
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(c == '.'){			//需要放到node.containsKey(c)这个判断的上边,否则会导致indexOutOfBound
                    boolean tmp = false;
                    for(char ch = 'a'; ch <= 'z'; ch++){
                        if(node.get(ch) != null){
                            tmp |= search(word.substring(i + 1), node.get(ch));
                        }
                    }
                    return tmp;
                }else if(node.containsKey(c)){
                    node = node.get(c);
                }else{
                    return false;
                }
            }
            return node.isEnd();
        }

        class TrieNode{
            private TrieNode[] list;
            private final int R = 26;
            private boolean isEnd;

            public TrieNode(){
                list = new TrieNode[R];
            }
            public boolean containsKey(char c){
                return list[c - 'a'] != null;
            }
            public void put(char c, TrieNode node){
                list[c - 'a'] = node;
            }
            public TrieNode get(char c){
                return list[c - 'a'];
            }
            public boolean isEnd(){
                return isEnd;
            }
            public void setEnd(){
                isEnd = true;
            }
        }
    }

    // method2
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap();
        boolean word = false;
        public TrieNode() {}
    }

    class WordDictionary1 {
        TrieNode trie;

        /** Initialize your data structure here. */
        public WordDictionary1() {
            trie = new TrieNode();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            TrieNode node = trie;

            for (char ch : word.toCharArray()) {
                if (!node.children.containsKey(ch)) {
                    node.children.put(ch, new TrieNode());
                }
                node = node.children.get(ch);
            }
            node.word = true;
        }

        /** Returns if the word is in the node. */
        public boolean searchInNode(String word, TrieNode node) {
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                if (!node.children.containsKey(ch)) {
                    // if the current character is '.'
                    // check all possible nodes at this level
                    if (ch == '.') {
                        for (char x : node.children.keySet()) {
                            TrieNode child = node.children.get(x);
                            if (searchInNode(word.substring(i + 1), child)) {
                                return true;
                            }
                        }
                    }
                    // if no nodes lead to answer
                    // or the current character != '.'
                    return false;
                } else {
                    // if the character is found
                    // go down to the next level in trie
                    node = node.children.get(ch);
                }
            }
            return node.word;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return searchInNode(word, trie);
        }
    }
}
