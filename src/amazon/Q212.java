package amazon;

import java.util.ArrayList;
import java.util.List;

// Word Search II
public class Q212 {
    // O(mn*4*3^(k-1)), O(numOfWords * k)
    int m, n;
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    public List<String> findWords(char[][] board, String[] words) {
        List<String> list = new ArrayList<>();
        m = board.length;
        n = board[0].length;
        Trie trie = new Trie();
        for(String w : words) {
            trie.insert(w);
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dfs(board, i, j, trie.root, list);
            }
        }
        return list;
    }
    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> list) {
        char c = board[i][j];
        if(c == '#' || !node.containsKey(c)) {
            return;
        }
        node = node.get(c);
        String end = node.getEnd();
        if(end != null) {
            list.add(end);
            node.setEnd(null);
        }
        board[i][j] = '#';
        for(int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];
            if(x >= 0 && x < m && y >= 0 && y < n) {
                dfs(board, x, y, node, list);
            }
        }
        board[i][j] = c;
    }
    class Trie {
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        public void insert(String word) {
            TrieNode node = root;
            for(char c : word.toCharArray()) {
                if(!node.containsKey(c)) {
                    node.put(c, new TrieNode());
                }
                node = node.get(c);
            }
            node.setEnd(word);
        }
    }
    class TrieNode {
        private static final int R = 26;
        private TrieNode[] list;
        private String end;
        public TrieNode() {
            list = new TrieNode[R];
        }
        public boolean containsKey(char c) {
            return list[c - 'a'] != null;
        }
        public TrieNode get(char c) {
            return list[c - 'a'];
        }
        public void put(char c , TrieNode node) {
            list[c - 'a'] = node;
        }
        public String getEnd() {
            return end;
        }
        public void setEnd(String s) {
            end = s;
        }
    }
}
