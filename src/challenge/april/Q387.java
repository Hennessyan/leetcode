package challenge.april;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// First Unique Character in a String
public class Q387 {

    public static void main(String[] args) {
        Q387 q = new Q387();
        System.out.println(q.firstUniqChar("loveleetcode"));    //2
    }

    public int firstUniqChar(String s) {
        int[] count = new int[26];
        for(char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for(int i = 0; i < s.length(); i++) {
            if(count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                list.remove(map.get(c));
            }else {
                list.add(i);
                map.put(c, i);
            }
        }
        return list.size() == 0 ? -1 : list.get(0);
    }
}
