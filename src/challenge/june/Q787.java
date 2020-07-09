package challenge.june;

import java.util.*;

// Cheapest Flights Within K Stops
public class Q787 {
    // SC not consider memo


    // DFS : O(n^(K+1)) O(E + V + K + 1)
    Map<Integer, List<int[]>> graph;
    Map<Integer, Integer> memo;
    boolean[] visited;
    int res;
    int K;
    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int K) {
        graph = new HashMap<>();
        memo = new HashMap<>();
        this.K = K;
        visited = new boolean[n];
        res = Integer.MAX_VALUE;
        for (int[] f : flights) {
            graph.computeIfAbsent(f[0], x -> new ArrayList<>()).add(new int[]{f[1], f[2]});
        }
        dfs(src, dst, 0, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void dfs(int src, int dst, int cost, int k) {
        if (k > this.K + 1 || visited[src]) {
            return;
        }
        if (src == dst) {
            res = Math.min(res, cost);
            return;
        }
        visited[src] = true;
        if (graph.containsKey(src)) {
            for (int[] vTmp : graph.get(src)) {
                int v0 = vTmp[0], v1 = cost + vTmp[1];
                if (v1 < res && memo.getOrDefault(vTmp[0] + (k + 1) * 1000, Integer.MAX_VALUE) > v1) {
                    memo.put(vTmp[0] + (k + 1) * 1000, v1);
                    dfs(v0, dst, v1, k + 1);
                }
            }
        }
        visited[src] = false;
    }

    //BFS O(n^(K+1)) O(n^(K+1))
    public int findCheapestPrice0(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Map<Integer, Integer> memo = new HashMap<>();
        int res = Integer.MAX_VALUE;
        for(int[] f : flights) {
            graph.computeIfAbsent(f[0], x -> new ArrayList<>()).add(new int[]{f[1], f[2]});
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, 0, 0});
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] u = queue.poll();
                if(u[0] == dst) {
                    res = Math.min(res, u[1]);
                    continue;
                }
                if(graph.containsKey(u[0])) {
                    for(int[] vTmp : graph.get(u[0])) {
                        int v0 = vTmp[0], v1 = u[1] + vTmp[1];
                        if(v1 < res && v1 < memo.getOrDefault((u[2] + 1) * 1000 + v0, Integer.MAX_VALUE)) {
                            memo.put((u[2] + 1) * 1000 + v0, v1);
                            queue.add(new int[]{v0, v1, u[2] + 1});
                        }
                    }
                }
            }
            if(K-- < 0) {
                break;
            }
        }
        return res == Integer.MAX_VALUE ?  -1 : res;
    }

    // Dijkstra's Algorithm
    // O(E*lgE) O(E)   E = V^2
    // TC of offer() / poll() is O(lgn) !!!
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Map<Integer, Integer> memo = new HashMap<>();
        for(int[] f : flights) {
            graph.computeIfAbsent(f[0], x -> new ArrayList<>()).add(new int[]{f[1], f[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[]{src, 0, 0});
        while(!pq.isEmpty()) {
            int[] uTmp = pq.remove();
            int u = uTmp[0], cost = uTmp[1], k = uTmp[2];
            if(u == dst) {
                return cost;
            }
            if(k == K + 1) {
                continue;
            }
            if(graph.containsKey(u)) {
                for(int[] vTmp : graph.get(u)) {
                    int ncost = cost + vTmp[1];
                    if(ncost < memo.getOrDefault((k + 1) * 1000 + vTmp[0], Integer.MAX_VALUE)) {
                        memo.put((k + 1) * 1000 + vTmp[0], ncost);
                        pq.offer(new int[]{vTmp[0], ncost, k + 1});
                    }
                }
            }
        }

        return -1;
    }

    //DP
    // O(K*E)  => O(k * |flights|) / O(k*n^2)
    // O(n)
    public int findCheapestPrice4(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[2][n];
        int inf = (int) 1e9;
        Arrays.fill(dp[0], inf);
        Arrays.fill(dp[1], inf);
        dp[0][src] = 0;
        dp[1][src] = 0;
        for(int k = 0; k <= K; k++) {	//因为这里并不是node数,不要把这个当作bellman-ford,所以下边也不是一维数组.(单纯看作是一个dp)
            for(int[] f : flights) {
                dp[k&1][f[1]] = Math.min(dp[k&1][f[1]], dp[~k&1][f[0]] + f[2]);	//注意dp[k&1][f[1]]不是k-1
            }
        }
        return dp[K&1][dst] < inf ? dp[K&1][dst] : -1;
    }
}
