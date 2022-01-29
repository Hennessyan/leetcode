package challenge.june;

import java.util.*;

// Cheapest Flights Within K Stops
public class Q787 {
    // SC not consider memo


    // DFS : O(E*K) O(E + n*K)
    // TC => V*K (recursion) * V (cost in each recursion)
    Map<Integer, Integer> memo;
    List<int[]>[] graph;
    boolean[] seen;
    int ans = Integer.MAX_VALUE, K, dst;
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] f : flights) {
            graph[f[0]].add(new int[]{f[1], f[2]}); // [neighbor, cost]
        }
        memo = new HashMap<>();
        seen = new boolean[n];
        this.K = K;
        this.dst = dst;
        dfs(src, 0, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    private void dfs(int u, int cost, int k) {
        if(seen[u]) return;
        seen[u] = true;
        for(int[] nei : graph[u]) {
            if(k == K && nei[0] != dst) continue;
            int key = (k + 1) * 1000 + nei[0];
            int nCost = cost + nei[1];
            if(nCost < memo.getOrDefault(key, ans)) {
                memo.put(key, nCost);
                if(nei[0] == dst) {
                    if(ans > nCost) {
                        ans = nCost;
                    }
                    continue;
                }
                dfs(nei[0], nCost, k + 1);
            }
        }
        seen[u] = false;
    }

    //BFS O(E*K) O(E + n*K)
    public int findCheapestPrice0(int n, int[][] flights, int src, int dst, int K) {
        List<int[]>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] f : flights) {
            graph[f[0]].add(new int[]{f[1], f[2]}); // [neighbor, cost]
        }
        Map<Integer, Integer> memo = new HashMap<>();
        int INF = Integer.MAX_VALUE, ans = INF, k = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, 0});

        while(!queue.isEmpty() && k < K + 1) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int u = cur[0], cost = cur[1];

                for(int[] nei : graph[u]) {
                    if(k == K && nei[0] != dst) continue;
                    if(cost + nei[1] < memo.getOrDefault((k + 1) * 1000 + nei[0], ans)) {
                        memo.put((k + 1) * 1000 + nei[0], cost + nei[1]);
                        if(nei[0] == dst) {
                            if(cost + nei[1] < ans) {
                                ans = cost + nei[1];
                            }
                            continue;
                        }
                        queue.add(new int[]{nei[0], cost + nei[1]});
                    }
                }
            }

            k++;
        }
        return ans == INF ? -1 : ans;
    }

    // Dijkstra's Algorithm
    // O(knlgkn) O(E + n*K) ?
    // TC can be O(ElgE) actually.
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
