package challenge.may;

import java.util.HashSet;
import java.util.Set;

// Jewels and Stones
public class Q771 {

    public static void main(String[] args) {
        Q771 q = new Q771();
        String J = "aA", S = "aAAbbbb";
        System.out.println(q.numJewelsInStones(J, S));
    }

    // O(J.length + S.length) O(1 - if constant length)
    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        for(char c : J.toCharArray()) {
            set.add(c);
        }
        int total = 0;
        for(char c : S.toCharArray()) {
            if(set.contains(c)) {
                total++;
            }
        }
        return total;
    }
}
