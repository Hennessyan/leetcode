package amazon;

import java.util.HashMap;
import java.util.Map;

// Design File System
public class Q1166 {
    // O(T) O(T) - # of nodes
    class FileSystem {

        TrieNode root;
        public FileSystem() {
            root = new TrieNode();
        }

        public boolean createPath(String path, int value) {
            TrieNode node = root;
            // if(path.equals("") || path.equals("/")) {
            //     return false;
            // }
            String[] d = path.split("/");
            for(int i = 1; i < d.length - 1; i++) {
                if(!node.list.containsKey(d[i])) {
                    return false;
                }
                node = node.list.get(d[i]);
            }
            if(node.list.containsKey(d[d.length - 1])) {
                return false;
            }
            TrieNode tmp = new TrieNode();
            tmp.val = value;
            node.list.put(d[d.length - 1], tmp);
            return true;
        }

        public int get(String path) {
            TrieNode node = root;
            // if(path.equals("") || path.equals("/")) {
            //     return -1;
            // }
            String[] d = path.split("/");
            for(int i = 1; i < d.length; i++) {
                if(!node.list.containsKey(d[i])) {
                    return -1;
                }
                node = node.list.get(d[i]);
            }
            return node.val;
        }
        class TrieNode {
            int val;
            Map<String, TrieNode> list;
            public TrieNode() {
                this.val = 0;
                list = new HashMap<>();
            }
        }
    }
}
