package google;

import java.util.HashMap;
import java.util.Map;

// Campus Bikes II
public class Q1066 {
    //backtracking : O(mn) O(mn)
    public int assignBikes(int[][] workers, int[][] bikes) {
        Map<Integer, Integer> memo = new HashMap<>();
        return dfs(workers, 0, bikes, 0, memo);
    }
    private int dfs(int[][] workers, int i, int[][] bikes, int occupy, Map<Integer, Integer> memo) {
        if(i == workers.length) {
            return 0;
        }
        if(memo.containsKey(occupy)) {
            return memo.get(occupy);
        }
        int min = Integer.MAX_VALUE;
        for(int j = 0; j < bikes.length; j++) {
            if((1 & (occupy >> j)) == 0) {
                occupy ^= 1 << j;
                min = Math.min(min, dist(workers[i], bikes[j]) + dfs(workers, i + 1, bikes, occupy, memo));
                occupy ^= 1 << j;
                // min = Math.min(min, dist(workers[i], bikes[j]) + dfs(workers, i + 1, bikes, occupy | (1 << j), memo));
            }
        }
        memo.put(occupy, min);
        return min;
    }
    private int dist(int[] w, int[] b) {
        return Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1]);
    }

    //O(mn*lgmn) O(mn) 复习一下就好,面试直接用method1
    //https://leetcode.com/problems/campus-bikes-ii/discuss/308753/Java-Priority-Queue
}
