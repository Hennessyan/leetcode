package amazon;

import java.util.Arrays;

// Reorder Data in Log Files
public class Q937 {
    // O(m*nlgn) O(mlgn)
    // https://leetcode.com/problems/reorder-data-in-log-files/solution/
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (l1, l2) -> {
            String[] s1 = l1.split(" ", 2); // limit the times of pattern, n - 1 if n > 0, so match once, split to 2 parts
            String[] s2 = l2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(s1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(s2[1].charAt(0));
            if(!isDigit1 && !isDigit2) {
                int val = s1[1].compareTo(s2[1]);
                if(val != 0) {
                    return val;
                }
                return s1[0].compareTo(s2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }
}
