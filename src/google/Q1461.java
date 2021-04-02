package google;

import java.util.HashSet;
import java.util.Set;

// Check If a String Contains All Binary Codes of Size K
public class Q1461 {
    // O(nk) O(nk)
    public boolean hasAllCodes1(String s, int k) {
        int need = 1 << k;
        Set<String> set = new HashSet<>();
        for(int i = k; i <= s.length(); i++) {
            String tmp = s.substring(i - k, i);
            if(set.add(tmp)) {
                if(--need == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    // O(N) O(2^k)
    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k, allOne = need - 1;
        boolean[] dp = new boolean[need];
        int val = 0;
        for(int i = 0; i < s.length(); i++) {
            val = ((val << 1) & allOne) | (s.charAt(i) - '0');
            if(i >= k - 1 && !dp[val]) {
                dp[val] = true;
                if(--need == 0) {
                    return true;
                }
            }
        }
        return false;
    }

}
