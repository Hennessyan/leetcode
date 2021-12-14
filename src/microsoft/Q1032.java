package microsoft;

import java.util.Deque;
import java.util.LinkedList;

// Stream of Characters
public class Q1032 {

    class StreamChecker {

        TrieNode root;
        Deque<Character> deque;
        // O(NM) O(NM) - M longest length of word
        public StreamChecker(String[] words) {
            root = new TrieNode();
            deque = new LinkedList<>();
            for(String word : words) {
                TrieNode node = root;
                for(int i = word.length() - 1; i >= 0; i--) {
                    char c = word.charAt(i);
                    if(node.list[c - 'a'] == null) {
                        node.list[c - 'a'] = new TrieNode();
                    }
                    node = node.list[c - 'a'];
                }
                node.isWord = true;
            }
        }
        // O(M) O(M)
        public boolean query(char letter) {
            deque.addFirst(letter);
            TrieNode node = root;
            for(char c : deque) {
                if(node.list[c - 'a'] == null) return false;
                node = node.list[c - 'a'];
                if(node.isWord) return true;
            }
            return false;
        }

        class TrieNode {
            TrieNode[] list;
            boolean isWord;
            public TrieNode() {
                list = new TrieNode[26];
                isWord = false;
            }
        }
    }
}
