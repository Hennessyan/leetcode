package amazon;

import java.util.*;

// Remove Sub-Folders from the Filesystem
public class Q1233 {

    public List<String> removeSubfolders(String[] folder) {
        List<String> res = new ArrayList<>();
        if(folder.length == 0) return res;
        Arrays.sort(folder);
        TrieNode root = new TrieNode();
        for(String path : folder) {
            TrieNode node = root;
            boolean notSub = true;
            for(String p : path.split("/")) {
                if(!node.map.containsKey(p)) {
                    if(node.isEnd) {
                        notSub = false;
                        break;
                    }
                    node.map.put(p, new TrieNode());
                }
                node = node.map.get(p);
            }
            if(notSub) {
                node.isEnd = true;
                res.add(path);
            }
        }

        return res;
    }
    class TrieNode {
        Map<String, TrieNode> map;
        boolean isEnd;
        public TrieNode() {
            map = new HashMap<>();
            isEnd = false;
        }
    }

//    class TrieNode {
//        TrieNode[] list = new TrieNode[27];
//        int index = -1;
//    }
//    public List<String> removeSubfolders(String[] folder) {
//        List<String> res = new ArrayList<>();
//
//        List<Integer>[] folders = new ArrayList[101];
//        for(int i = 0; i < 101; i++) {
//            folders[i] = new ArrayList<>();
//        }
//        for(int i = 0; i < folder.length; i++) {
//            folders[folder[i].length()].add(i);
//        }
//        TrieNode root = new TrieNode();
//        for(List<Integer> tmp : folders) {
//            for(int i : tmp) {
//                String path = folder[i];
//                TrieNode node = root;
//                boolean notSub = true;
//                for(char c : path.toCharArray()) {
//                    if(c == '/' && node.index != -1) {
//                        notSub = false;
//                        break;
//                    }
//                    int ti = c == '/' ? 26 : c - 'a';
//                    if(node.list[ti] == null) {
//                        node.list[ti] = new TrieNode();
//                    }
//                    node = node.list[ti];
//                }
//                if(notSub) {
//                    node.index = 0;
//                    res.add(path);
//                }
//            }
//        }
//        return res;
//    }
}
