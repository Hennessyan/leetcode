package amazon;

import java.util.*;

// All Paths From Source to Target
public class Q797 {
    // O(n*2^n) O(n*2^n) -> ({2^i,(0 <= i <= n - 2)}   => 2^(n-1) - 1 possible solutions)
    // DFS
    Map<Integer, List<List<Integer>>> memo;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        memo = new HashMap<>();
        return dfs(graph, 0, n - 1);
    }
    private List<List<Integer>> dfs(int[][] graph, int u, int end) {
        if(memo.containsKey(u)) {
            return memo.get(u);
        }
        List<List<Integer>> res = new ArrayList<>();
        if(u == end) {
            res.add(Arrays.asList(end));
            memo.put(end, res);
            return res;
        }
        for(int v : graph[u]) {
            for(List<Integer> tmp : dfs(graph, v, end)) {
                List<Integer> list = new ArrayList<>();
                list.add(u);
                list.addAll(tmp);
                res.add(list);
            }
        }
        memo.put(u, res);
        return res;
    }
    // BFS
    public List<List<Integer>> allPathsSourceTarget1(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> res = new ArrayList<>();
        backtrack(graph, 0, n - 1, new LinkedList<>(), res);
        return res;
    }
    private void backtrack(int[][] graph, int u, int end, LinkedList<Integer> list, List<List<Integer>> res) {
        list.add(u);
        if(u == end) {
            res.add(new ArrayList<>(list));
        }
        for(int v : graph[u]) {
            backtrack(graph, v, end, list, res);
        }
        list.removeLast();
    }
}
