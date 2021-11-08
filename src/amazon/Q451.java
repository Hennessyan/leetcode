package amazon;

import java.util.*;

// Sort Characters By Frequency
public class Q451 {

    public static void main(String[] args) {
        Q451 q = new Q451();
        System.out.println(q.frequencySort("Aabb"));
    }

    //不能这样做,因为"loveleetcode" => "eeeelolovtcd".应该是"eeeeoollvtdc".
//    public String frequencySort(String s) {
//        Map<Character, Integer> map = new HashMap<>();
//        List<Character> list = new LinkedList<>();
//        char[] chs = s.toCharArray();
//        for(char c : chs) {
//            map.put(c, map.getOrDefault(c, 0) + 1);
//            list.add(c);
//        }
//        Collections.sort(list, (a, b) -> map.get(b) - map.get(a));
//        StringBuilder sb = new StringBuilder();
//        for(char c : list) {
//            sb.append(c);
//        }
//        return sb.toString();
//    }
    //O(nlgn) O(n)
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character> list = new LinkedList<>(map.keySet());
        Collections.sort(list, (a, b) -> map.get(b) - map.get(a));
        StringBuilder sb = new StringBuilder();
        for(char c : list) {
            for(int i = 0; i < map.get(c); i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    // use bucket to avoid sort
    public String frequencySort1(String s) {

        if (s == null || s.isEmpty()) return s;

        // Count up the occurances.
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        int maximumFrequency = Collections.max(counts.values());

        // Make the list of buckets and apply bucket sort.
        List<List<Character>> buckets = new ArrayList<>();
        for (int i = 0; i <= maximumFrequency; i++) {
            buckets.add(new ArrayList<Character>());
        }
        for (Character key : counts.keySet()) {
            int freq = counts.get(key);
            buckets.get(freq).add(key);
        }

        // Build up the string.
        StringBuilder sb = new StringBuilder();
        for (int i = buckets.size() - 1; i >= 1; i--) {
            for (Character c : buckets.get(i)) {
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
