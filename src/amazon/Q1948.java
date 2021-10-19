package amazon;

import java.util.*;

// Delete Duplicate Folders in System
public class Q1948 {

    Map<String, Integer> freMap = new HashMap<>();
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        List<List<String>> res = new ArrayList<>();
        TrieNode root = new TrieNode("/");
        for(List<String> path : paths) {    // n * m * lgx (x nodes in one level, treemap sort)
            insert(root, path);
        }
        root.getKey();  // Generate key (hashcode) first !
        LinkedList<String> list = new LinkedList<>();
        for(TrieNode node : root.map.values()) {
            deleting(node, list, res);
        }
        return res;
    }
    // backtracking
    private void deleting(TrieNode node, LinkedList<String> list, List<List<String>> res) {
        if(freMap.getOrDefault(node.key, 0) > 1) {
            return;
        }
        list.add(node.name);
        res.add(new ArrayList<>(list));
        for(TrieNode child : node.map.values()) {
            deleting(child, list, res);
        }
        list.removeLast();
    }
    private void insert(TrieNode node, List<String> path) {
        for(String p : path) {
            if(!node.map.containsKey(p)) {
                node.map.put(p, new TrieNode(p));
            }
            node = node.map.get(p);
        }
    }
    class TrieNode {
        Map<String, TrieNode> map;  // assume deep of TrieNode is m
        String name;                // assume the length of name is l
        String key;                 // SC : l*m*m -> last name will repeat in the key m times
        public TrieNode(String name) {
            this.map = new TreeMap<>();
            this.name = name;
            this.key = null;
        }
        public String getKey() {
            if(key != null) return key;
            if(map.size() == 0) return "";
            StringBuilder sb = new StringBuilder();
            for(TrieNode child : map.values()) {
                sb.append("|").append(child.name + child.getKey()).append("|");
            }
            this.key = sb.toString();
            freMap.put(this.key, freMap.getOrDefault(this.key, 0) + 1);
            return this.key;
        }
    }
}
