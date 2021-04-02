package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Minimum Window Subsequence
public class Q727 {
    // O(st) O(s)
    public String minWindow(String S, String T) {
        int len = S.length();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[][] dp = new int[len][26];  // record the position of character in and after current row

        // reverse order to record the position
        for(int i = len - 1; i>= 0; i--) {
            char c = S.charAt(i);
            last[c - 'a'] = i;
            for(int j = 0; j < 26; j++) {
                dp[i][j] = last[j];
            }
        }
        // all possible results, record possible start point first
        List<int[]> windows = new ArrayList<>();
        for(int i = 0; i < len; i++) {
            if(S.charAt(i) == T.charAt(0)) {
                windows.add(new int[]{i, i});
            }
        }
        int[] ans = {-1, len};
        for(int j = 1; j < T.length(); j++) {
            char c = T.charAt(j);
            for(int[] w : windows) {
                if(w[1] + 1 < len && dp[w[1] + 1][c - 'a'] >= 0) {
                    w[1] = dp[w[1] + 1][c - 'a'];
                } else {
                    w[0] = w[1] = -1;   // no possible result later as we record position in reverse order
                    break;
                }
            }
        }
        for(int[] w : windows) {
            if(w[0] == -1) {
                break;
            } else if(w[1] - w[0] < ans[1] - ans[0]) {
                ans = w;
            }
        }
        return ans[0] == -1 ? "" : S.substring(ans[0], ans[1] + 1);
    }

    //https://www.youtube.com/watch?v=W2DvQcDPD9A
    //O(ST) O(1) ??
    public String minWindow1(String S, String T) {
        int j = 0, minLen = S.length() + 1;
        String res = "";
        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == T.charAt(j)) {
                j++;
                if(j >= T.length()) {
                    int end = i + 1;
                    j--;
                    while(j >= 0) {	//当找到尾后，要倒着给前找头在哪,不能直接选择第一次相等时的起点为头,因为存在此情况: [a,b,a,b,c] [a,b,c]
                        if(S.charAt(i) == T.charAt(j)) {
                            j--;
                        }
                        i--;
                    }
                    i++;		//[a,b,x,x,x,c,a,b,c] [a,b,c]
                    j++;		//第一次的时候i,j分别变回为0,但是for循环的i++,使得i从1开始.
                    if(end - i < minLen) {
                        minLen = end - i;
                        res = S.substring(i, end);
                    }
                }
            }
        }
        return res;
    }
}
