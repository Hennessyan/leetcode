package amazon;
// Longest Palindromic Substring
public class Q5 {

    // O(n^2) O(n^2)
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        int len = s.length(), start = 0, max = 1;
        boolean[][] dp = new boolean[len][len];

        for(int l = 1; l <= len; l++) {
            for(int i = 0; i < len - l + 1; i++) {
                int j = i + l - 1;
                if(s.charAt(i) == s.charAt(j) && (l <= 3 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    if(l > max) {
                        max = l;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + max);
    }

    /*同下一方法*/
    int start = 0, end = 0, maxLen = 0;
    public String longestPalindrome2(String s) {
        if(s == null || s.length() < 2){
            return s;
        }
        for(int i = 0; i < s.length(); i++){
            expandAroundCenter1(s, i, i);
            expandAroundCenter1(s, i, i+1);
        }
        return s.substring(start, end);
    }
    private void expandAroundCenter1(String s, int l, int r){
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        int len = r - l - 1;
        if(len > maxLen){
            maxLen = len;
            start = l + 1;
            end = r;
        }
    }
    /*Approach #4 (Expand Around Center) [Accepted]*/
    //Time complexity :O(n^2) Since expanding a palindrome around its center could take O(n) time, the overall complexity is O(n^2)
    //Space complexity:O(1)
    public String longestPalindrome1(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        int start = 0, end = 0;
        for(int i = 0; i < s.length(); i++){
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);
            int len = Math.max(len1, len2);
            if(len > end - start){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    private int expandAroundCenter(String s, int left, int right){
        int l = left, r = right;
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return r - l - 1;		//注意这里是r-l-1 不是 r-l+1
    }
}
