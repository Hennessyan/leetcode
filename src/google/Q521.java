package google;
// Longest Uncommon Subsequence I
public class Q521 {
    // O(min(a,b)) O(1)
    public int findLUSlength(String a, String b) {
        if(a.equals(b)) return -1;
        return Math.max(a.length(), b.length());
    }
}
