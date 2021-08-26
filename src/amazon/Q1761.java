package amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Minimum Degree of a Connected Trio in a Graph
public class Q1761 {
    // O(V^3) O(E)
    // O(n*d*d) n -> V in worst case, d - most connection for node, which is n in worst case => n^3 => v^3.
    public int minTrioDegree(int n, int[][] edges) {
        List<Set<Integer>> graph = new ArrayList<>();   // use hashmap is better
        for(int i = 0; i <= n; i++) {
            graph.add(new HashSet<>());
        }
        for(int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            for(int j : graph.get(i)) {
                if(j <= i) continue;
                for(int k : graph.get(j)) {
                    if(k <= j) continue;
                    if(graph.get(k).contains(i)) {
                        min = Math.min(min, graph.get(i).size() + graph.get(j).size() + graph.get(k).size() - 6);
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
