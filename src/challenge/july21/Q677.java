package challenge.july21;

import java.util.HashMap;
import java.util.Map;

// Map Sum Pairs
public class Q677 {
    // SC - total size of input
    class MapSum {

        Map<String, Integer> map;
        Map<String, Integer> score;
        /** Initialize your data structure here. */
        public MapSum() {
            map = new HashMap<>();
            score=  new HashMap<>();
        }
        // O(K^2) -> loop is k, build the prefix is k => K * K
        public void insert(String key, int val) {
            int delta = val - map.getOrDefault(key, 0);
            map.put(key, val);
            String prefix = "";
            for(char c : key.toCharArray()) {
                prefix += c;
                score.put(prefix, score.getOrDefault(prefix, 0) + delta);
            }
        }

        public int sum(String prefix) {
            return score.getOrDefault(prefix, 0);
        }
    }

    class MapSum1 {

        Map<String, Integer> map;
        TrieNode root;
        /** Initialize your data structure here. */
        public MapSum1() {
            map = new HashMap<>();
            root = new TrieNode();
        }
        // O(K)
        public void insert(String key, int val) {
            int delta = val - map.getOrDefault(key, 0);
            map.put(key, val);
            TrieNode node = root;
            // root.score += delta;     // not necessary.
            for(char c : key.toCharArray()) {
                if(!node.map.containsKey(c)) {
                    node.map.put(c, new TrieNode());
                }
                node = node.map.get(c);
                node.score += delta;
            }
        }
        // O(K)
        public int sum(String prefix) {
            TrieNode node = root;
            for(char c : prefix.toCharArray()) {
                node = node.map.get(c);
                if(node == null) return 0;
            }
            return node.score;
        }

        class TrieNode {
            Map<Character, TrieNode> map;
            int score;
            public TrieNode() {
                map = new HashMap<>();
                score = 0;
            }
        }
    }

//    class MapSum2 {
//
//        TrieNode root;
//        Map<String, Integer> map;
//        /** Initialize your data structure here. */
//        public MapSum2() {
//            map = new HashMap<>();
//            root = new TrieNode();
//        }
//
//        public void insert(String key, int val) {
//            int delta = val - map.getOrDefault(key, 0);
//            map.put(key, val);
//            TrieNode node = root;
//            for(char c : key.toCharArray()) {
//                if(node.children[c - 'a'] == null) {
//                    node.children[c - 'a'] = new TrieNode();
//                }
//                node = node.children[c - 'a'];
//                node.score += delta;
//            }
//        }
//
//        public int sum(String prefix) {
//            TrieNode node = root;
//            for(char c : prefix.toCharArray()) {
//                node = node.children[c - 'a'];
//                if(node == null) return 0;
//            }
//            return node.score;
//        }
//
//        class TrieNode {
//            TrieNode[] children;
//            int score;
//            public TrieNode() {
//                children = new TrieNode[26];
//                score = 0;
//            }
//        }
//    }

}
