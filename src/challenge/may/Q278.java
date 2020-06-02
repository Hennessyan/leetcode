package challenge.may;

// First Bad Version
public class Q278 {

    public static void main(String[] args) {
        Q278 q = new Q278();
        System.out.println(q.firstBadVersion(10));
    }
    // O(lgn) O(1)
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (isBadVersion(m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private boolean isBadVersion(int version) {
        return version >= 4;
    }
}
