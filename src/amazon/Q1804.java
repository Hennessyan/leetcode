package amazon;
// Implement Trie (Prefix Tree) II
public class Q1804 {

    class Trie {

        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for(char c : word.toCharArray()) {
                if(node.list[c - 'a'] == null) {
                    node.list[c - 'a'] = new TrieNode();
                }
                node = node.list[c - 'a'];
                node.precount++;
            }
            node.count++;
            node.isEnd = true;
        }

        public int countWordsEqualTo(String word) {
            TrieNode node = root;
            for(char c : word.toCharArray()) {
                if(node.list[c - 'a'] == null) {
                    return 0;
                }
                node = node.list[c - 'a'];
            }
            return node.count;
        }

        public int countWordsStartingWith(String prefix) {
            TrieNode node = root;
            for(char c : prefix.toCharArray()) {
                if(node.list[c - 'a'] == null) {
                    return 0;
                }
                node = node.list[c - 'a'];
            }
            return node.precount;
        }

        public void erase(String word) {
            TrieNode node = root;
            for(char c : word.toCharArray()) {
                node = node.list[c - 'a'];
                node.precount--;
            }
            if(--node.count == 0) {
                node.isEnd = false;
            }
        }

        class TrieNode {
            TrieNode[] list;
            int count;
            int precount;
            boolean isEnd;  // not necessary.
            public TrieNode() {
                list = new TrieNode[26];
                count = 0;
                precount = 0;
            }
        }
    }
}
