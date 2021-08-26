package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Palindrome Pairs
public class Q336 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    // O(nkk) O(nnk)
    // n - num of words, k - longest length of word
    // SC : O(nnk) -> in worst case, trie takes nk nodes, each node contains the list with size n.
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if(words == null || words.length == 0) {
            return res;
        }
        Trie trie = new Trie();
        int len = words.length;
        for(int i = 0; i < len; i++) {
            trie.insert(words[i], i);
        }
        for(int i = 0; i < len; i++) {
            trie.search(words[i], i, res);
        }
        return res;
    }

    class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        public void insert(String word, int index) {
            TrieNode node = root;
            for(int i = word.length() - 1; i >= 0; i--) {
                char c = word.charAt(i);
                if(!node.containsKey(c)) {
                    node.put(c, new TrieNode());
                }
                if(isPalin(word, 0, i)) {
                    node.addIndex(index);
                }
                node = node.get(c);
            }
            node.setEndIndex(index);
            node.addIndex(index);	//最后也要加 针对["abc", "cba"]
        }
        public void search(String word, int index, List<List<Integer>> res) {
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(node.getEndIndex() != -1 && node.getEndIndex() != index && isPalin(word, i, word.length() - 1)) {	//i~len-1
                    res.add(new ArrayList<>(Arrays.asList(index, node.getEndIndex())));	//这个if要在containskey的前头,针对["a",""]
                }
                if(!node.containsKey(c)) {
                    return;
                }
                node = node.get(c);
            }
            for(int id : node.getPalin()) {
                if(id != index) {
                    res.add(new ArrayList<>(Arrays.asList(index, id)));
                }
            }
        }
        public boolean isPalin(String s, int l, int r) {
            while(l <= r && s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            }
            return l >= r;
        }
    }
    class TrieNode {
        private TrieNode[] list;
        private final int r = 26;
        private int isEndForIndex;
        private List<Integer> palins;
        public TrieNode() {
            list = new TrieNode[r];
            isEndForIndex = -1;
            palins = new ArrayList<>();
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
        public int getEndIndex() {
            return isEndForIndex;
        }
        public void setEndIndex(int index) {
            this.isEndForIndex = index;
        }
        public List<Integer> getPalin() {
            return palins;
        }
        public void addIndex(int index) {
            this.palins.add(index);
        }
    }
/*  hash table O(nkk) O((n+k)^2)
    // n^2 + nk + k^2 => (n+k)^2
    private List<String> allValidPrefixes(String word) {
        List<String> validPrefixes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (isPalindromeBetween(word, i, word.length() - 1)) {
                validPrefixes.add(word.substring(0, i));
            }
        }
        return validPrefixes;
    }

    private List<String> allValidSuffixes(String word) {
        List<String> validSuffixes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (isPalindromeBetween(word, 0, i)) {
                validSuffixes.add(word.substring(i + 1, word.length()));
            }
        }
        return validSuffixes;
    }

    // Is the prefix ending at i a palindrome?
    private boolean isPalindromeBetween(String word, int front, int back) {
        while (front < back) {
            if (word.charAt(front) != word.charAt(back)) return false;
            front++;
            back--;
        }
        return true;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        // Build a word -> original index mapping for efficient lookup.
        Map<String, Integer> wordSet = new HashMap<>();
        for (int i = 0; i < words.length; i++) {    // nk
            wordSet.put(words[i], i);
        }

        // Make a list to put all the palindrome pairs we find in.
        List<List<Integer>> solution = new ArrayList<>();

        for (String word : wordSet.keySet()) {

            int currentWordIndex = wordSet.get(word);
            String reversedWord = new StringBuilder(word).reverse().toString();

            // Build solutions of case #1. This word will be word 1.
            if (wordSet.containsKey(reversedWord)
                    && wordSet.get(reversedWord) != currentWordIndex) {
                solution.add(Arrays.asList(currentWordIndex, wordSet.get(reversedWord)));
            }

            // Build solutions of case #2. This word will be word 2.
            for (String suffix : allValidSuffixes(word)) {  // k^2 -> temp list, not save, so it's k^2 rather than nk^2
                String reversedSuffix = new StringBuilder(suffix).reverse().toString();
                if (wordSet.containsKey(reversedSuffix)) {
                    solution.add(Arrays.asList(wordSet.get(reversedSuffix), currentWordIndex));
                }
            }

            // Build solutions of case #3. This word will be word 1.
            for (String prefix : allValidPrefixes(word)) {
                String reversedPrefix = new StringBuilder(prefix).reverse().toString();
                if (wordSet.containsKey(reversedPrefix)) {
                    solution.add(Arrays.asList(currentWordIndex, wordSet.get(reversedPrefix))); // result is n^2
                }
            }
        }
        return solution;
    }
 */
}
