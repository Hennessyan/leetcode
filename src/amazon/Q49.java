package amazon;

import java.util.*;

// Group Anagrams
public class Q49 {

    // O(nk) O(nk)
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            int[] count = new int[26];
            for(char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            map.computeIfAbsent(generateKey(count), x -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
    private String generateKey(int[] count) {
        StringBuilder sb = new StringBuilder();
        for(int c : count) {
            sb.append(c + "#");
        }
        return sb.toString();
    }

    // O(nklgk) O(nk)
    public List<List<String>> groupAnagrams1(String[] strs) {
        if(strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            map.computeIfAbsent(String.valueOf(chs), x -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
