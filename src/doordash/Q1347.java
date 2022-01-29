package doordash;
// Minimum Number of Steps to Make Two Strings Anagram
public class Q1347 {
    // O(n) O(1)
    public int minSteps(String s, String t) {
        int n = s.length(), ans = 0;
        int[] count = new int[26];
        for(int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for(int c : count) {
            if(c > 0) ans += c;
        }
        return ans;
    }
}
