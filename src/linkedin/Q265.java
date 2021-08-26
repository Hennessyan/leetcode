package linkedin;
// Paint House II
public class Q265 {
    // O(nk^2) O(1) - as use costs
    public int minCostII0(int[][] costs) {

        if (costs.length == 0) return 0;
        int k = costs[0].length;
        int n = costs.length;

        for (int house = 1; house < n; house++) {
            for (int color = 0; color < k; color++) {
                int min = Integer.MAX_VALUE;
                for (int previousColor = 0; previousColor < k; previousColor++) {
                    if (color == previousColor) continue;
                    min = Math.min(min, costs[house - 1][previousColor]);
                }
                costs[house][color] += min;
            }
        }

        // Find the minimum in the last row.
        int min = Integer.MAX_VALUE;
        for (int c : costs[n - 1]) {
            min = Math.min(min, c);
        }
        return min;
    }
    // O(nk) O(1)
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0) return 0;
        int n = costs.length, k = costs[0].length;

        int min1 = 0, min2 = 0, minColor = -1;
        for(int[] cost : costs) {
            int m1 = Integer.MAX_VALUE, m2 = Integer.MAX_VALUE, index = -1;
            for(int i = 0; i < k; i++) {
                int val = cost[i] + (i == minColor ? min2 : min1);
                if(val < m1) {
                    m2 = m1;
                    m1 = val;
                    index = i;
                } else if(val < m2) {
                    m2 = val;
                }
            }
            min1 = m1;
            min2 = m2;
            minColor = index;
        }
        return min1;
    }
}
