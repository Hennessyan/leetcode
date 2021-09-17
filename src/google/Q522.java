package google;
// Longest Uncommon Subsequence II
import java.util.Arrays;

public class Q522 {

    // O(x*n^2) O(1)
    // x - length of str, n - length of strs.
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (s1, s2) -> s2.length() - s1.length());
        // check if strs[i] is subsequence of others.
        for(int i = 0, j; i < strs.length; i++) {
            for(j = 0; j < strs.length; j++) {
                if(i == j) continue;
                if(isSubSequence(strs[i], strs[j])) {
                    break;
                }
            }
            if(j == strs.length) return strs[i].length();
        }
        return -1;
    }
    // check if s1 is subsequence of s2.
    private boolean isSubSequence(String s1, String s2) {
        int j = 0;
        for(int i = 0; i < s2.length() && j < s1.length(); i++) {
            if(s2.charAt(i) == s1.charAt(j)) {
                j++;
            }
        }
        return j == s1.length();
    }
    // O(x*n^2) O(1)
    public int findLUSlength1(String[] strs) {
        int max = -1;
        for(int i = 0, j; i < strs.length; i++) {
            if(strs[i].length() > max) {
                for (j = 0; j < strs.length; j++) {
                    if (i == j) continue;
                    if (isSubSequence(strs[i], strs[j])) {
                        break;
                    }
                }
                if (j == strs.length) max = Math.max(max, strs[i].length());
            }
        }
        return max;
    }
}
