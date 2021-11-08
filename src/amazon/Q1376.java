package amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Time Needed to Inform All Employees
public class Q1376 {
    // O(n) O(n)
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < n; i++) {
            graph.computeIfAbsent(manager[i], x -> new ArrayList<>()).add(i);
        }
        return dfs(graph, informTime, headID);
    }
    private int dfs(Map<Integer, List<Integer>> graph, int[] time, int index) {
        int ans = 0;
        if(graph.containsKey(index)) {
            for(int next : graph.get(index)) {
                ans = Math.max(ans, dfs(graph, time, next));
            }
        }
        return time[index] + ans;
    }
    // method2 : O(n) O(n)
    public int numOfMinutes1(int n, int headID, int[] manager, int[] informTime) {
        int res = 0;
        for(int i = 0; i < n; i++) {
            res = Math.max(res, dfs(i, manager, informTime));
        }
        return res;
    }
    private int dfs(int i, int[] manager, int[] time) {
        if(manager[i] != -1) {
            time[i] += dfs(manager[i], manager, time);
            manager[i] = -1;
        }
        return time[i];
    }
}
