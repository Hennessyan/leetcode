package amazon;

import java.util.Arrays;

// Orderly Queue
public class Q899 {
    // list all possible result of 2-kick, will find we can get all permutations =>
    // swap 2 adjacent chars => can get all permutation
    // 2-kick move 2th char to the end => swap 2 adjacent chars => all permutations are possible
    // 1-kick move 1th char to the end

    // a b c d 2-kick
    // a c d b 1
    // c d b a 1
    // d b a c 2
    // d a c b 1
    // a c b d
    // O(n^2) O(n)
    public String orderlyQueue(String s, int k) {
        if(k == 1) {
            // O(n^2)
            String res = s;
            int n = s.length();
            for(int i = 1; i < n; i++) {
                String tmp = s.substring(i) + s.substring(0, i);
                if(tmp.compareTo(res) < 0) {
                    res = tmp;
                }
            }
            return res;
        } else {
            // O(nlgn)
            // if k >= 2, we can generate all permutations based on 2-kick and 1-kick
            // hence we can find the result directly.
            char[] chs = s.toCharArray();
            Arrays.sort(chs);
            return String.valueOf(chs);
        }
    }

}
