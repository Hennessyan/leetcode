package amazon;
// Design Search  Autocomplete System

import java.util.*;

public class Q642 {
    // We can optimize our storage by storing only references of the terminal nodes rather than storing the entire phrase
    // O(nl) O(nl) - update() takes constant time, n is length of sentences, l is length of sentence.
    class AutocompleteSystem {

        TrieNode root;
        TrieNode cur;
        StringBuilder sb;
        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new TrieNode();
            cur = root;
            sb = new StringBuilder();
            for(int i = 0; i < sentences.length; i++) {
                add(sentences[i], times[i]);
            }
        }

        public List<String> input(char c) {
            List<String> res = new ArrayList<>();
            if(c == '#') {
                add(sb.toString(), 1);
                sb = new StringBuilder();
                cur = root;
                return res;
            }

            sb.append(c);
            // avoid NullPointerException
            if(cur != null) {
                cur = cur.children[c];
            }
            // avoid NullPointerException
            if(cur != null) {
                for(TrieNode node : cur.hots) {
                    res.add(node.s);
                }
            }
            return res;
        }

        private void add(String s, int time) {
            TrieNode node = root;
            List<TrieNode> visited = new LinkedList<>();
            for(char c : s.toCharArray()) {
                if(node.children[c] == null) {
                    node.children[c] = new TrieNode();
                }
                node = node.children[c];
                visited.add(node);
            }
            node.s = s;
            // add extra time
            node.times += time;

            for(TrieNode v : visited) {
                v.update(node);
            }
        }


        class TrieNode implements Comparable<TrieNode>{
            TrieNode[] children;
            LinkedList<TrieNode> hots;    // don't need use extra data structure to keep times for sorting purpose.
            String s;
            int times;

            public TrieNode() {
                children = new TrieNode[128];   // 26 lower-case characters + ' '
                s = null;
                times = 0;
                hots = new LinkedList<>();
            }

            public int compareTo(TrieNode that) {
                if(this.times == that.times) {
                    return this.s.compareTo(that.s);
                }
                return that.times - this.times;
            }

            public void update(TrieNode node) {
                // check if duplicates
                if(!hots.contains(node)) {
                    hots.add(node);
                }
                Collections.sort(hots);

                if(hots.size() > 3) {
                    hots.removeLast();
                }
            }

        }
    }

    class AutocompleteSystem1 {
        TrieNode root, cur;
        StringBuilder sb;
        Map<String, Integer> fre;
        public AutocompleteSystem1(String[] sentences, int[] times) {
            root = new TrieNode();
            cur = root;
            sb = new StringBuilder();
            fre = new HashMap<>();

            for(int i = 0; i < sentences.length; i++) {
                fre.put(sentences[i], times[i]);
                add(sentences[i]);
            }

        }

        public List<String> input(char c) {
            if(c == '#') {
                String s = sb.toString();
                fre.put(s, fre.getOrDefault(s, 0) + 1);
                add(s);
                sb = new StringBuilder();
                cur = root;
                return new ArrayList<>();
            }
            sb.append(c);
            if(cur != null) {
                cur = cur.children[c == ' ' ? 26 : c - 'a'];
            }
            // don't return original hots directly => user can modify it !
            return cur == null ? new ArrayList<>() : new ArrayList<>(cur.hots);
        }

        private void add(String s) {
            TrieNode tmp = root;
            for(char c : s.toCharArray()) {
                int i = c == ' ' ? 26 : c - 'a';
                if(tmp.children[i] == null) {
                    tmp.children[i] = new TrieNode();
                }
                tmp = tmp.children[i];

                update(tmp, s);
            }
            tmp.isEnd = true;
        }

        private void update(TrieNode node, String s) {
            List<String> hots = node.hots;
            hots.remove(s);

            for(int i = 0; i < 3; i++) {
                if(i == hots.size()) {
                    hots.add(s);
                    return;  // can't exceed 3 in this case, return directly.
                }
                String pre = hots.get(i);
                int pre_fre = fre.get(pre);
                int cur_fre = fre.get(s);
                if(cur_fre > pre_fre || (cur_fre == pre_fre && s.compareTo(pre) < 0)) {
                    hots.add(i, s);
                    break;
                }
            }
            // at most 3 in hots
            if(hots.size() > 3) hots.remove(3);
        }

        class TrieNode {
            TrieNode[] children;
            List<String> hots;
            boolean isEnd;
            public TrieNode() {
                children = new TrieNode[27];
                hots = new ArrayList<>();
                isEnd = false;
            }
        }
    }
}
