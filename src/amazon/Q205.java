package amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Isomorphic Strings
// Q290
public class Q205 {
    // method1 : O(n) O(1)
    // check Q290
    public boolean isIsomorphic(String s, String t) {
        int[] smap = new int[256];
        int[] tmap = new int[256];
        Arrays.fill(smap, -1);
        Arrays.fill(tmap, -1);
        for(int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if(smap[sc] == -1 && tmap[tc] == -1) {
                smap[sc] = tc;
                tmap[tc] = sc;
            }
            if(smap[sc] != tc || tmap[tc] != sc) {
                return false;
            }
        }
        return true;
    }
    public boolean isIsomorphic1(String s, String t) {
        Map<Character, Character> sm = new HashMap<>();
        Map<Character, Character> tm = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if(!sm.containsKey(c1)) {
                if(tm.containsKey(c2)) return false;
                sm.put(c1, c2);
                tm.put(c2, c1);
            } else if(sm.get(c1) != c2) return false;
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        Map<Character, Character> smap = new HashMap<>();
        Map<Character, Character> tmap = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            smap.putIfAbsent(sc, tc);
            tmap.putIfAbsent(tc, sc);
            if(smap.get(sc) != tc || tmap.get(tc) != sc) {
                return false;
            }
        }
        return true;
    }
    // method3 : O(n) O(n)
    public boolean isIsomorphic3(String s, String t) {
        return transform(s).equals(transform(t));
    }
    private String transform(String s) {
        int[] map = new int[256];
        Arrays.fill(map, -1);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map[c] == -1) {
                map[c] = i;
            }
            sb.append(map[c]);
        }
        return sb.toString();
    }
}
