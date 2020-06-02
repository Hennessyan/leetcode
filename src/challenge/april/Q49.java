package challenge.april;

import java.util.*;

// Group Anagrams (相同字母异序词)
public class Q49 {

    public static void main(String[] args) {
        Q49 q = new Q49();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = q.groupAnagrams(strs);
        for(List<String> list : res) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
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
