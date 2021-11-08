package google;

import java.util.*;

// Optimize Water Distribution in a Village
// Q1584 Q1135
public class Q1168 {
    // will not return -1 as virtual edge always exist (-1 ~ well itself)

    // prime's algorithm : O((m+n)lg(m+n)) O(m+n)
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        List<List<int[]>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < n; i++) {
            int[] tmp = new int[]{i + 1, wells[i]};
            pq.offer(tmp);
            graph.get(0).add(tmp);
        }
        for(int[] p : pipes) {
            graph.get(p[0]).add(new int[]{p[1], p[2]});
            graph.get(p[1]).add(new int[]{p[0], p[2]});
        }

        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        int total = 0;

        while(seen.size() <= n) {
            int[] u = pq.poll();
            if(!seen.add(u[0])) {
                continue;
            }
            total += u[1];
            for(int[] v : graph.get(u[0])) {
                if(!seen.contains(v[0])) {
                    pq.offer(v);
                }
            }
        }

        return total;
    }
    // kruskal's algorithm :
    public int minCostToSupplyWater1(int n, int[] wells, int[][] pipes) {
        List<int[]> pairs = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            pairs.add(new int[]{0, i + 1, wells[i]});
        }
        for(int[] p : pipes) {
            pairs.add(p);
        }
        Collections.sort(pairs, (p1, p2) -> p1[2] - p2[2]);
        int total = 0, count = 0;
        UnionFind uf = new UnionFind(n);

        for(int[] p : pairs) {
            if(uf.union(p[0], p[1])) {
                total += p[2];
                if(++count == n) break;
            }
        }
        return total;
    }
    class UnionFind {
        int[] parents;
        int[] rank;
        public UnionFind(int n) {
            parents = new int[n + 1];
            rank = new int[n + 1];
            for(int i = 0; i <= n; i++) {
                parents[i] = i;
            }
        }
        public boolean union(int i, int j) {
            int p1 = find(i);
            int p2 = find(j);
            if(p1 == p2) return false;
            if(rank[p1] < rank[p2]) {
                parents[p1] = p2;
            } else if(rank[p1] > rank[p2]) {
                parents[p2] = p1;
            } else {
                parents[p2] = p1;
                rank[p1]++;
            }
            return true;
        }

        public int find(int i) {
            if(i != parents[i]) {
                parents[i] = find(parents[i]);
            }
            return parents[i];
        }
    }

}
