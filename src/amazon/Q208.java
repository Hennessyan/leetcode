package amazon;
// Implement Trie (Prefix Tree)
public class Q208 {

    class Trie {
        TrieNode root;
        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode node = root;
            for(char c : word.toCharArray()) {
                if(!node.containsKey(c)) {
                    node.put(c, new TrieNode());
                }
                node = node.get(c);
            }
            node.setEnd();
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode res = searchPrefix(word);
            return res != null && res.getEnd();
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode res = searchPrefix(prefix);
            return res != null;
        }

        private TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for(char c : word.toCharArray()) {
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
        private static final int R = 26;
        private boolean end;
        public TrieNode() {
            list = new TrieNode[R];
            end = false;
        }
        public boolean containsKey(char c) {
            return list[c - 'a'] != null;
        }
        public void put(char c, TrieNode node) {
            list[c - 'a'] = node;
        }
        public TrieNode get(char c) {
            return list[c - 'a'];
        }
        public boolean getEnd() {
            return end;
        }
        public void setEnd() {
            end = true;
        }
    }

}
