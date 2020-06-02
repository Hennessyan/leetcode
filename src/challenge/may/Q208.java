package challenge.may;

// Implement Trie
public class Q208 {

}

class Trie {

    TrieNode root;
    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
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

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = searchWord(word);
        return node != null && node.getEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchWord(prefix);
        return node != null;
    }

    private TrieNode searchWord(String word) {
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
    private static final int R = 26;
    private boolean isEnd;
    private TrieNode[] list;

    public TrieNode() {
        list = new TrieNode[R];
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
    public void setEnd() {
        isEnd = true;
    }
    public boolean getEnd() {
        return isEnd;
    }
}