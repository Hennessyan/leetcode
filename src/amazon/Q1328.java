package amazon;
// Break a Palindrome
public class Q1328 {
    // O(n) O(n)
    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if(n == 1) return "";
        int l = 0, r = n - 1;
        char[] chs = palindrome.toCharArray();
        while(l < r) {
            if(chs[l] != 'a') {
                chs[l] = 'a';
                return String.valueOf(chs);
            }
            l++;
            r--;
        }
        chs[n - 1]++;   // chs[n-1] = 'b';
        return String.valueOf(chs);
    }
}
