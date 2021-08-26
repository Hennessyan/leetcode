package amazon;

import java.util.HashMap;
import java.util.Map;

// Word Pattern
public class Q290 {
    // O(n) O(n)
    public boolean wordPattern(String pattern, String s) {
        String[] array = s.trim().split(" ");
        int len = array.length;
        if(pattern.length() != len) return false;
        Map<Character, String> m1 = new HashMap<>();
        Map<String, Character> m2 = new HashMap<>();

        for(int i = 0; i < len; i++) {
            char c = pattern.charAt(i);
            if(!m1.containsKey(c)) {
                if(m2.containsKey(array[i])) {
                    return false;
                }
                m1.put(c, array[i]);
                m2.put(array[i], c);
            } else if(!m1.get(c).equals(array[i])) {
                return false;
            }
        }
        return true;
    }
}
