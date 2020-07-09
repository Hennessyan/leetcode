package google;

import java.util.*;

// Cheapest Flights Within K Stops
public class Q787 {

    // DFS : O(n^(K+1)) O(E + V + K + 1)
    Map<Integer, List<int[]>> graph;
    boolean[] visited;
    int price = Integer.MAX_VALUE;

    public int findCheapestPrice0(int n, int[][] flights, int src, int dst, int K) {
        graph = new HashMap<>();
        visited = new boolean[n];
        for (int[] f : flights) {
            graph.computeIfAbsent(f[0], x -> new LinkedList<>()).add(new int[]{f[1], f[2]});
        }
        dfs(src, dst, 0, 0, K);
        return price == Integer.MAX_VALUE ? -1 : price;
    }

    private void dfs(int src, int dst, int val, int k, int K) {
        if (visited[src]) {
            return;
        }
        if (src == dst) {
            price = Math.min(price, val);
            return;
        }
        if (k == K + 1) {
            return;
        }
        if (graph.containsKey(src)) {
            visited[src] = true;
            for (int[] tmp : graph.get(src)) {
                int v = tmp[0], p = tmp[1] + val;
                if (p < price) {
                    dfs(v, dst, p, k + 1, K);
                }
            }
            visited[src] = false;
        }
    }

    // BFS : O(n^(K+1)) O(n^(K+1))
    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        int price = Integer.MAX_VALUE;
        for (int[] f : flights) {
            graph.computeIfAbsent(f[0], x -> new LinkedList<>()).add(new int[]{f[1], f[2]});
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, 0});

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] u = queue.poll();
                if (u[0] == dst) {
                    price = Math.min(price, u[1]);
                    continue;
                }
                if (graph.containsKey(u[0])) {
                    for (int[] v : graph.get(u[0])) {
                        int val = v[1] + u[1];
                        if (val < price) {
                            queue.add(new int[]{v[0], val});
                        }
                    }
                }
            }
            if (K-- < 0) {
                break;
            }
        }
        return price == Integer.MAX_VALUE ? -1 : price;
    }

    // Dijkstra's Algorithm :
    // O(E*lgE) O(E)   E = V^2
    // TC of offer() / poll() is O(lgn) !!!
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] f : flights) {
            graph.computeIfAbsent(f[0], x -> new LinkedList<>()).add(new int[]{f[1], f[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{src, 0, 0});
        while (!pq.isEmpty()) {
            int[] u = pq.poll();
            if (u[0] == dst) {
                return u[1];
            }
            if (u[2] == K + 1) {
                continue;
            }
            if (graph.containsKey(u[0])) {
                for (int[] v : graph.get(u[0])) {
                    pq.offer(new int[]{v[0], v[1] + u[1], u[2] + 1});
                }
            }
        }
        return -1;
    }
    // Bellman-ford
    // O(K*E)  => O(k * |flights|) / O(k*n^2)
    // O(n)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[2][n];
        int inf = (int)(1e9);
        Arrays.fill(dp[0], inf);
        Arrays.fill(dp[1], inf);
        dp[0][src] = 0;
        dp[1][src] = 0;
        for(int k = 0; k <= K; k++) {
            for(int[] v : flights) {
                dp[k & 1][v[1]] = Math.min(dp[k & 1][v[1]], dp[~k & 1][v[0]] + v[2]);
            }
        }
        return dp[K&1][dst] < inf ? dp[K&1][dst] : -1;
    }
}
