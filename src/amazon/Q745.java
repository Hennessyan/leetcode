package amazon;
// Prefix and Suffix Search
public class Q745 {
    // O(nk^2 + qk) O(nk^2)
    class WordFilter {
        TrieNode root;
        public WordFilter(String[] words) {
            root = new TrieNode();
            for(int i = 0; i < words.length; i++) {
                String word = words[i] + '{';
                int len = word.length();
                for(int j = 0; j < len; j++) {
                    TrieNode node = root;
                    node.w = i;
                    for(int k = j; k < 2 * len - 1; k++) {
                        int index = word.charAt(k % len) - 'a';
                        if(node.list[index] == null) {
                            node.list[index] = new TrieNode();
                        }
                        node = node.list[index];
                        node.w = i;
                    }
                }
            }
        }
        // qk - q is number of query, k is max length of word
        public int f(String prefix, String suffix) {
            TrieNode node = root;
            for(char c : (suffix + '{' + prefix).toCharArray()) {
                if(node.list[c - 'a'] == null) {
                    return -1;
                }
                node = node.list[c - 'a'];
            }
            return node.w;
        }
        class TrieNode {
            TrieNode[] list;
            int w;
            public TrieNode() {
                list = new TrieNode[27];    // '{' is after z
                w = 0;
            }
        }
    }
    // method2 : same TC & SC
    // record char pair: "apple" [a,e] [p,l] ... [a,none] [none, e] ...
//    class WordFilter {
//        TrieNode trie;
//        public WordFilter(String[] words) {
//            trie = new TrieNode();
//            int wt = 0;
//            for (String word: words) {
//                TrieNode cur = trie;
//                cur.weight = wt;
//                int L = word.length();
//                char[] chars = word.toCharArray();
//                for (int i = 0; i < L; ++i) {
//
//                    TrieNode tmp = cur;
//                    for (int j = i; j < L; ++j) {             // record single char key per level as another one is none
//                        int code = (chars[j] - '`') * 27;
//                        if (tmp.children.get(code) == null)
//                            tmp.children.put(code, new TrieNode());
//                        tmp = tmp.children.get(code);
//                        tmp.weight = wt;
//                    }
//
//                    tmp = cur;
//                    for (int k = L - 1 - i; k >= 0; --k) {    // record single char key per level as another one is none
//                        int code = (chars[k] - '`');
//                        if (tmp.children.get(code) == null)
//                            tmp.children.put(code, new TrieNode());
//                        tmp = tmp.children.get(code);
//                        tmp.weight = wt;
//                    }
//
//                    int code = (chars[i] - '`') * 27 + (chars[L - 1 - i] - '`');
//                    if (cur.children.get(code) == null)
//                        cur.children.put(code, new TrieNode());
//                    cur = cur.children.get(code);
//                    cur.weight = wt;
//
//                }
//                wt++;
//            }
//        }
//
//        public int f(String prefix, String suffix) {
//            TrieNode cur = trie;
//            int i = 0, j = suffix.length() - 1;
//            while (i < prefix.length() || j >= 0) {
//                char c1 = i < prefix.length() ? prefix.charAt(i) : '`';
//                char c2 = j >= 0 ? suffix.charAt(j) : '`';
//                int code = (c1 - '`') * 27 + (c2 - '`');
//                cur = cur.children.get(code);
//                if (cur == null) return -1;
//                i++; j--;
//            }
//            return cur.weight;
//        }
//    }
//
//    class TrieNode {
//        Map<Integer, TrieNode> children;
//        int weight;
//        public TrieNode() {
//            children = new HashMap();
//            weight = 0;
//        }
//    }
}
