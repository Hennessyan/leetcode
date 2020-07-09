package challenge.june;

import java.util.Arrays;

// Two City Scheduling
public class Q1029 {

    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (c0, c1) -> (c0[0] - c0[1] - c1[0] + c1[1]));
        int res = 0, n = costs.length / 2;
        for(int i = 0; i < n; i++) {
            res += costs[i][0] + costs[i + n][1];
        }
        return res;
    }
}
