package amazon;
// Remove Palindromic Subsequences
public class Q1332 {
    // we are trying to remove subsequences, and just contain only a and b
    // if s is not palindrome, we can remove all a, then all b, which is 2 steps.
    // O(n) O(n)
    public int removePalindromeSub(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        String reverseStr = new StringBuilder(s).reverse().toString();
        if(s.equals(reverseStr)) {
            return 1;
        }
        return 2;
    }
    // O(n) O(1)
    public int removePalindromeSub1(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        if (isPalindrome(s)) {
            return 1;
        }
        return 2;
    }

    private boolean isPalindrome(String s) {
        int lo = 0;
        int hi = s.length() - 1;
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) {
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }
}
