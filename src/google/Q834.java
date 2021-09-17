package google;

import java.util.*;

// Sum of Distances in Tree
public class Q834 {
    // O(n) O(n)
    // sum[x] = x@X + y@Y + #Y
    // sum[y] = x@X + y@Y + #X
    // sum[x] - sum[y] = #Y - #X
    // assume we split tree as two parts: X and Y
    // x@X - sum of distance from x to its subnodes in X part
    // #X - number of node in X part
    int N;
    int[] count, ans;
    List<Set<Integer>> graph;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        this.N = N;
        this.count = new int[N];
        this.ans = new int[N];
        this.graph = new ArrayList<>();
        Arrays.fill(count, 1);
        for(int i = 0; i < N; i++) {
            this.graph.add(new HashSet<>());
        }
        for(int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        dfs(0, -1); // postorder
        dfs2(0, -1);// preorder
        return ans;
    }
    // ans[root] will be correct answer - ans[0] here because of L30.
    private void dfs(int cur, int parent) {
        for(int child : graph.get(cur)) {
            if(child != parent) {
                dfs(child, cur);
                count[cur] += count[child];
                ans[cur] += ans[child] + count[child];
            }
        }
    }
    private void dfs2(int cur, int parent) {
        for(int child : graph.get(cur)) {
            if(child != parent) {
                // compared with ans[cur], ans[child] will deduct the whole child part (#x) -> count[child]
                // then add the another part (#y) -> n - count[child]
                ans[child] = ans[cur] + N - count[child] - count[child];
                dfs2(child, cur);
            }
        }
    }
}
