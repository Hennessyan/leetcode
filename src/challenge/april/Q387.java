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
    // check this one-pass method : sliding window
    public int firstUniqChar2(String s) {
        int[] count = new int[26];
        int ans = -1, n = s.length(), j = 0, cur = -1;
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            if(count[index]++ == 0) {
                if(ans == -1) {
                    ans = i;
                    cur = index;
                }
            } else if(cur == index) {
                ans = -1;
                cur = -1;
                while(j <= i) {
                    int head = s.charAt(j++) - 'a';
                    if(count[head] == 1) {
                        ans = j - 1;
                        cur = head;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
