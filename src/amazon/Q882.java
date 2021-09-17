package amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// Reachable Nodes In Subdivided Graph
public class Q882 {
    // dijkstra's algorithm: O(elgn) O(e)
    // can't use bfs as we hope reach as much nodes as possible.
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for(int[] e : edges) {
            graph.computeIfAbsent(e[0], x -> new HashMap<>()).put(e[1], e[2]);
            graph.computeIfAbsent(e[1], x -> new HashMap<>()).put(e[0], e[2]);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{0, 0});  // (node, dist)

        Map<Integer, Integer> dist = new HashMap<>();
        dist.put(0, 0);
        Map<Integer, Integer> used = new HashMap<>();   // u * n + v = key

        int ans = 0;
        while(!pq.isEmpty()) {
            int[] tmp = pq.poll();
            int u = tmp[0], d = tmp[1];
            if(d > dist.getOrDefault(u, 0)) {   // calculate if == as first time visit.
                continue;
            }
            ans++;
            if(graph.containsKey(u)) {
                for(int v : graph.get(u).keySet()) {
                    int w = graph.get(u).get(v);
                    int min = Math.min(w, maxMoves - d);
                    used.put(u * n + v, min);

                    int d2 = d + w + 1;
                    // impossible to have d2 less than dist of v, used it to compare with maxMoves + 1 / avoid revisiting parent node
                    if(d2 < dist.getOrDefault(v, maxMoves + 1)) {
                        dist.put(v, d2);
                        pq.offer(new int[]{v, d2});
                    }
                }
            }
        }

        for(int[] e : edges) {
            // choose min of dist of two node, and used distance from two directions for two nodes
            ans += Math.min(e[2], used.getOrDefault(e[0] * n + e[1], 0) + used.getOrDefault(e[1] * n + e[0], 0));
        }
        return ans;
    }
}
