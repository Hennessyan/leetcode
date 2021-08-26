package linkedin;
// Paint House
// Q265
public class Q256 {
    // method1 : O(n) O(1)
    public int minCost(int[][] costs) {
        int red = 0, blue = 0, green = 0;
        for(int[] cost : costs) {
            int r1 = Math.min(blue, green) + cost[0];
            int b1 = Math.min(red, green) + cost[1];
            int g1 = Math.min(red, blue) + cost[2];
            red = r1;
            blue = b1;
            green = g1;
        }
        return Math.min(red, Math.min(blue, green));
    }
    // seems better than method1, but don't overwrite origin array.
    public int minCos1t(int[][] costs) {

        for (int n = costs.length - 2; n >= 0; n--) {
            // Total cost of painting the nth house red.
            costs[n][0] += Math.min(costs[n + 1][1], costs[n + 1][2]);
            // Total cost of painting the nth house green.
            costs[n][1] += Math.min(costs[n + 1][0], costs[n + 1][2]);
            // Total cost of painting the nth house blue.
            costs[n][2] += Math.min(costs[n + 1][0], costs[n + 1][1]);
        }

        if (costs.length == 0) return 0;

        return Math.min(Math.min(costs[0][0], costs[0][1]), costs[0][2]);
    }
}
