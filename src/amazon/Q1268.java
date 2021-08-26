package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Search Suggestions System
public class Q1268 {
    // O(M + m^2) O(M)
    // -> M : sum of word length in products
    //    m : length of searchWord, build immutable string takes O(m^2)
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();

        Trie trie = new Trie();
        for(String p : products) {
            trie.insert(p);
        }

        String prefix = "";
        List<String> list;
        for(char c : searchWord.toCharArray()) {
            prefix += c;
            list = new ArrayList<>();
            trie.searchPrefix(prefix, list);
            res.add(new ArrayList<>(list));
        }
        return res;
    }
    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }
        public void insert(String product) {
            TrieNode node = root;
            for(char c : product.toCharArray()) {
                if(node.list[c - 'a'] == null) {
                    node.list[c - 'a'] = new TrieNode();
                }
                node = node.list[c - 'a'];
            }
            node.product = product;
        }
        public void searchPrefix(String prefix, List<String> list) {
            TrieNode node = root;
            for(char c : prefix.toCharArray()) {
                if(node.list[c - 'a'] == null) return;
                node = node.list[c - 'a'];
            }
            dfs(node, list);
        }
        private void dfs(TrieNode node, List<String> list) {
            if(list.size() == 3) {
                return;
            }
            if(node.product != null) list.add(node.product);

            for(int c = 0; c < 26; c++) {
                if(node.list[c] != null) {
                    dfs(node.list[c], list);
                }
            }
        }
    }
    class TrieNode {
        TrieNode[] list;
        String product;

        public TrieNode() {
            list = new TrieNode[26];
            product = null;
        }
    }
    // O(nlgn) O(lgn) - space spent for sort algorithm.
    // ignore the space required for output as it does not affect the algorithm's space complexity
    // n : size of products, m : length of searchWord.
    public List<List<String>> suggestedProducts1(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        Arrays.sort(products);
        int len = searchWord.length();
        /**
         * L86 - L88 is necessary for below test case:
         * Input: products = ["havana"], searchWord = "tatiana"
         * Output: [[],[],[],[],[],[],[]]
         */
        for(int i = 0; i < len; i++) {              // O(m)
            res.add(new ArrayList<>());
        }

        int l = 0, r = products.length - 1;         // n == m based on the constraints.
        for(int i = 0; i < len && l <= r; i++) {    // O(n) here, but much smaller than MlgM
            char c = searchWord.charAt(i);
            while(l <= r && (products[l].length() <= i || products[l].charAt(i) != c)) {
                l++;
            }
            while(l <= r && (products[r].length() <= i || products[r].charAt(i) != c)) {
                r--;
            }
            for(int k = l; k < l + 3 && k <= r; k++) {
                res.get(i).add(products[k]);
            }

        }
        return res;
    }

    // Equivalent code for lower_bound in Java
    int lower_bound(String[] products, int start, String word) {
        int i = start, j = products.length, mid;
        while (i < j) {
            mid = (i + j) / 2;
            if (products[mid].compareTo(word) >= 0)
                j = mid;
            else
                i = mid + 1;
        }
        return i;
    }
    // O(nlgn + mlgn + m^2) O(lgn)
    // Assume string compare in sort algorightm is O(1)
    // n : size of products, m - length of searchWord
    public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>();
        int start = 0, bsStart = 0, n = products.length;
        String prefix = new String();
        for (char c : searchWord.toCharArray()) {
            prefix += c;

            // Get the starting index of word starting with `prefix`.
            start = lower_bound(products, bsStart, prefix);

            // Add empty vector to result.
            result.add(new ArrayList<>());

            // Add the words with the same prefix to the result.
            // Loop runs until `i` reaches the end of input or 3 times or till the
            // prefix is same for `products[i]` Whichever comes first.
            for (int i = start; i < Math.min(start + 3, n); i++) {
                if (products[i].length() < prefix.length() || !products[i].substring(0, prefix.length()).equals(prefix))
                    break;
                result.get(result.size() - 1).add(products[i]);
            }

            // Reduce the size of elements to binary search on since we know
            bsStart = start;
        }
        return result;
    }
}
