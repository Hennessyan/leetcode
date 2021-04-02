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
}
