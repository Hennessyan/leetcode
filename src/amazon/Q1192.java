package amazon;

import javafx.util.Pair;

import java.util.*;

// Critical Connections in a Network
public class Q1192 {
    // O(V+E) O(V+E) => O(E) O(E)
    Map<Integer, List<Integer>> graph;
    Map<Integer, Integer> rank;
    Set<Pair<Integer, Integer>> critical;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        buildGraph(connections);

        dfs(0, 0);
        List<List<Integer>> res = new ArrayList<>();
        for(Pair<Integer, Integer> pair : critical) {
            res.add(Arrays.asList(pair.getKey(), pair.getValue()));
        }
        return res;
    }
    private void buildGraph(List<List<Integer>> connections) {
        this.graph = new HashMap<>();
        this.rank = new HashMap<>();
        this.critical = new HashSet<>();

        for(List<Integer> edge : connections) {
            int u = edge.get(0), v = edge.get(1);
            graph.computeIfAbsent(u, x -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, x -> new ArrayList<>()).add(u);
            critical.add(new Pair(Math.min(u,v), Math.max(u,v)));
        }
    }
    private int dfs(int u, int curRank) {
        if(rank.containsKey(u)) {
            return rank.get(u);
        }
        rank.put(u, curRank);

        int minRank = curRank;
        if(graph.containsKey(u)) {
            for(int v : graph.get(u)) {
                // Skip the parent.
                Integer neighRank = rank.get(v);
                if(neighRank != null && neighRank == curRank - 1) {
                    continue;
                }

                int nextRank = dfs(v, curRank + 1);
                // 0 -> 1 -> 2 -> 0
                // <= curRank rather than minRank, otherwise will skip discard edges.
                if(nextRank <= curRank) {           //  <=  we need to compare value here, so can't set rank value as -1 by default.
                    critical.remove(new Pair(Math.min(u, v), Math.max(u, v)));
                    minRank = Math.min(minRank, nextRank);
                }

            }
        }
        return minRank;
    }
    // = in L52 is used to remove 0-1 edge.
    // 4
    // [[0,1],[1,2],[2,0],[1,3]]
}
