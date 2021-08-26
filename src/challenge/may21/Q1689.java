package challenge.may21;
// Partitioning Into Minimum Number Of Deci-Binary Numbers
public class Q1689 {
    // O(n) O(n)
    public int minPartitions(String n) {
        if(n == null || n.length() == 0) {
            return 0;
        }
        int max = 0;
        for(char c : n.toCharArray()) { // O(n) space for array
            max = Math.max(max, c - '0');
        }
        return max;
    }
}
