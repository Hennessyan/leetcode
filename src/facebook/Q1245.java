package facebook;

import java.util.LinkedList;
import java.util.List;

// Tree Diameter
public class Q1245 {
    // O(n) O(h)
    int ans;
    public int treeDiameter(int[][] edges) {
        if(edges == null || edges.length == 0) {
            return 0;
        }
        ans = 0;
        int n = edges.length;
        List<Integer>[] list = new List[n + 1];
        for(int i = 0; i <= n; i++) {
            list[i] = new LinkedList<>();
        }
        for(int[] edge : edges) {
            list[edge[0]].add(edge[1]);
            list[edge[1]].add(edge[0]);
        }
        dfs(list, 0, -1);   //choose arbitrary node as start node (root)
        return ans;
    }
    // can't remove node from list, loop iterator - (int v : list[u]) will change unexpectedly
    private int dfs(List<Integer>[] list, int i, int parent) {
        int m1 = 0, m2 = 0;
        for(int v : list[i]) {
            if(v == parent) {
                continue;
            }
            int val = dfs(list, v, i);
            if(val > m1) {
                m2 = m1;
                m1 = val;
            } else if(val > m2) {
                m2 = val;
            }
            ans = Math.max(ans, m1 + m2);
        }
        return m1 + 1;
    }
}
