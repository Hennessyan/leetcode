package google;

import java.util.*;

// Alien Dictionary
public class Q269 {
    // O(C + U + min(U^2, N)) O(U+min(U ^ 2,N))
    // C - total number of chars
    // U - unique chars
    // N - length of words
    // O(V+E) : V == U, E == min(N, U ^ 2), SC -> O(1) as U is fixed by 26.
    // bfs
    public String alienOrder(String[] words) {
        if(words == null || words.length == 0) {    //["abcd"] => "abcd"
            return "";
        }
        int[] indegree = new int[26];
        int len = words.length;
        Map<Character, Set<Character>> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for(String word : words) {
            for(char c : word.toCharArray()) {
                set.add(c);
            }
        }

        for(int i = 0; i < len - 1; i++) {
            char[] ch1 = words[i].toCharArray();
            char[] ch2 = words[i + 1].toCharArray();
            if (ch1.length > ch2.length && words[i].startsWith(words[i + 1])) { // ["abc", "ab"] => ""
                return "";
            }
            for(int j = 0; j < Math.min(ch1.length, ch2.length); j++) {
                if(ch1[j] != ch2[j]) {
                    if(map.computeIfAbsent(ch1[j], x -> new HashSet<>()).add(ch2[j])) { //注意这里
                        indegree[ch2[j] - 'a']++;
                    }
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for(char c : set) {
            if(indegree[c - 'a'] == 0) {
                queue.add(c);
            }
        }

        while(!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            if(map.containsKey(c)) {
                for(char v : map.get(c)) {
                    if(--indegree[v - 'a'] == 0) {
                        queue.add(v);
                    }
                }
            }
        }
        if(sb.length() != set.size()) { // 必须有.
            return "";
        }
        return sb.toString();
    }
    // dfs
    Map<Character, Set<Character>> map;
    int[] status;
    StringBuilder sb;
    public String alienOrder1(String[] words) {
        if(words == null || words.length == 0) {
            return "";
        }
        status = new int[26];
        map = new HashMap<>();
        sb = new StringBuilder();
        for(String word : words) {
            for(char c : word.toCharArray()) {
                map.put(c, new HashSet<>());    // add all chars as key to avoid ["z", "z"] does not execute dfs.
            }
        }
        for(int i = 1; i < words.length; i++) {
            String w1 = words[i - 1];
            String w2 = words[i];
            if(w1.length() > w2.length() && w1.startsWith(w2)) {    // ["abc","ab"] should return ""
                return "";
            }
            for(int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                char a = w1.charAt(j);
                char b = w2.charAt(j);
                if(a != b) {
                    map.get(b).add(a);   // pay attention the order here
                    break;
                }
            }
        }
        for(char key : map.keySet()) {
            if(!dfs(key)) {
                return "";
            }
        }
//        if(sb.length() != map.size()) {   // do not need this condition because of l77-81
//            return "";
//        }
        return sb.toString();
    }
    private boolean dfs(char c) {
        if(status[c - 'a'] == 2) {
            return true;
        }
        if(status[c - 'a'] == 1) {
            return false;
        }
        if(map.containsKey(c)) {
            status[c - 'a'] = 1;
            for(char v : map.get(c)) {
                if(!dfs(v)) {
                    return false;
                }
            }
        }
        sb.append(c);
        status[c - 'a'] = 2;
        return true;
    }
}
