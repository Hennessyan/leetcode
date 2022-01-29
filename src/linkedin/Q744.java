package linkedin;
// Find Smallest Letter Greater Than Target
public class Q744 {
    // O(n) O(1)
    public char nextGreatestLetter(char[] letters, char target) {
        for (char c: letters)
            if (c > target) return c;
        return letters[0];
    }
    // O(lgn) O(1)
    public char nextGreatestLetter1(char[] letters, char target) {
        int n = letters.length, lo = 0, hi = n;
        while(lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if(letters[mi] <= target) {
                lo = mi + 1;
            } else {
                hi = mi;
            }
        }
        return letters[lo % n];
    }
}
