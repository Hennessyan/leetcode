package challenge.aug21;
// Range Addition II
public class Q598 {
    // O(n) O(1)
    public int maxCount(int m, int n, int[][] ops) {
        for(int[] op : ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }
}
