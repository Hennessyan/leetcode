package amazon;

import java.util.*;

// Course Schedule IV
// method1 - Q1334
// method2 - Q851
public class Q1462 {

    // Floyd–Warshall Algorithm : O(n^3) O(n^2)
    public List<Boolean> checkIfPrerequisite(int n, int[][] ps, int[][] qs) {
        boolean[][] dp = new boolean[n][n];
        for(int[] p : ps) {
            dp[p[0]][p[1]] = true;
        }
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    dp[i][j] = dp[i][j] || dp[i][k] && dp[k][j];
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for(int[] q : qs) {
            res.add(dp[q[0]][q[1]]);
        }
        return res;
    }
    // record the relationship between parent-child is not easy, but we can record child-parent relationship.
    // => dense graph ? O(n^2) O(n^2)
    public List<Boolean> checkIfPrerequisite1(int n, int[][] ps, int[][] qs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Set<Integer>> parent = new HashMap<>();
        int[] indegree = new int[n];
        for(int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
            parent.put(i, new HashSet<>());
        }
        for(int[] p : ps) {
            indegree[p[1]]++;
            graph.get(p[0]).add(p[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        while(!queue.isEmpty()) {
            int u = queue.poll();
            for(int v : graph.get(u)) {
                parent.get(v).add(u);
                parent.get(v).addAll(parent.get(u));
                if(--indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for(int[] q : qs) {
            Set<Integer> set = parent.get(q[1]);
            res.add(set.contains(q[0]));
        }
        return res;
    }
}
