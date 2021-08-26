package amazon;
// Count Binary Substrings
public class Q696 {
    // O(n) O(n)
    public int countBinarySubstrings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] group = new int[len];
        group[0] = 1;
        int i = 0;
        for(int j = 1; j < len; j++) {
            if(s.charAt(j - 1) != s.charAt(j)) {
                group[++i] = 1;
            } else {
                group[i]++;
            }
        }
        int total = 0;
        for(int j = 1; j <= i; j++) {
            total += Math.min(group[j-1],group[j]);
        }
        return total;
    }
    // O(n) O(1)
    public int countBinarySubstrings1(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length(), pre = 0, cur = 1;
        int total = 0;
        for(int j = 1; j < len; j++) {
            if(s.charAt(j - 1) != s.charAt(j)) {
                total += Math.min(pre, cur);
                pre = cur;
                cur = 1;
            } else {
                cur++;
            }
        }

        return total + Math.min(pre, cur);
    }
}
