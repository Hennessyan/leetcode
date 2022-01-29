package doordash;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// K-Similar Strings
public class Q854 {
    // O(n^3) O(n^3)
    public int kSimilarity(String s1, String s2) {
        if(s1.equals(s2)) return 0;
        HashSet<String> seen = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s1);
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String s = queue.poll();
                if(s2.equals(s)) return level;              // O(n)
                for(String next : getNeighbors(s, s2)) {    // O(n^2)
                    if(seen.add(next)) {
                        queue.add(next);
                    }
                }
            }
            level++;
        }
        return level;
    }
    private Set<String> getNeighbors(String s1, String s2) {
        Set<String> set = new HashSet<>();
        char[] chs = s1.toCharArray();
        int i = 0, n = s1.length();
        while(i < n && s1.charAt(i) == s2.charAt(i)) i++;
        for(int j = i + 1; j < n; j++) {
            if(s1.charAt(j) == s2.charAt(i) && s1.charAt(j) != s2.charAt(j)) {
                swap(chs, i, j);
                if(s1.charAt(j) == s2.charAt(j)) {
                    Set<String> tmp = new HashSet<>();
                    tmp.add(new String(chs));
                    return tmp;     // same as Set.of(new String(chs));
                }
                else set.add(new String(chs));
                swap(chs, i, j);
            }
        }
        return set;
    }
    private void swap(char[] arr, int i, int j) {

        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
