package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// Connecting Cities With Minimum Cost
// todo: compare with Q261
public class Q1135 {
    // both of them are greedy algorithm.
    // Prime's algorithm: O(ElgE) O(E+V)
    // connections -> edge list
    public int minimumCost1(int n, int[][] connections) {
        // check cycle !!!!
        if(connections.length < n - 1) return -1;
        int[] visited = new int[n + 1];
        List<int[]>[] graph = new List[n + 1];
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] c : connections) {
            graph[c[0]].add(new int[]{c[1], c[2]});
            graph[c[1]].add(new int[]{c[0], c[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // start from arbitrary vertex is fine:
        pq.offer(new int[]{1, 0});
        int sum = 0, count = 0;
        while(!pq.isEmpty()) {
            int[] u = pq.poll();
            if(visited[u[0]] == 1) continue;
            visited[u[0]] = 1;
            count++;
            sum += u[1];
            for(int[] v : graph[u[0]]) {
                if(visited[v[0]] == 0) {
                    pq.offer(v);
                }
            }
        }
        return count == n ? sum : -1;   // may has corner case of Q261.
    }
    // Kruskal's algorithm : O(ElgE + E) O(V)
    // E - length of connections, V - N
    public int minimumCost(int N, int[][] connections) {
        DisjointSet disjointset = new DisjointSet(N);
        // Sort connections based on their weights (in increasing order)
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        // Keep track of total edges added in the MST
        int total = 0;
        // Keep track of the total cost of adding all those edges
        int cost = 0;
        for (int i = 0; i < connections.length; ++i) {
            int a = connections[i][0];
            int b = connections[i][1];
            // Do not add the edge from a to b if it is already connected
            if (disjointset.isInSameGroup(a, b)) continue;
            // If a and b are not connected, take union
            disjointset.Union(a, b);
            // increment cost
            cost += connections[i][2];
            // increment number of edges added in the MST
            total++;
        }
        // If all N nodes are connected, the MST will have a total of N - 1 edges
        if (total == N - 1) {
            return cost;
        } else {
            return -1;
        }
    }
    class DisjointSet {
        private int[] weights; // Used to store weights of each nodes
        private int[] parents;

        public void Union(int a, int b) {
            int rootA = Find(a);
            int rootB = Find(b);
            // If both a and b have same root, i.e. they both belong to the same set, hence we don't need to take union
            if (rootA == rootB) return;

            // Weighted union
            if (this.weights[rootA] > this.weights[rootB]) {
                // In case rootA is having more weight
                // 1. Make rootA as the parent of rootB
                // 2. Increment the weight of rootA by rootB's weight
                this.parents[rootB] = rootA;
                this.weights[rootA] += this.weights[rootB];
            } else {
                // Otherwise
                // 1. Make rootB as the parent of rootA
                // 2. Increment the weight of rootB by rootA's weight
                this.parents[rootA] = rootB;
                this.weights[rootB] += this.weights[rootA];
            }
        }

        public int Find(int a) {
            // Traverse all the way to the top (root) going through the parent nodes
            while (a != this.parents[a]) {
                // Path compression
                // a's grandparent is now a's parent
                this.parents[a] = this.parents[parents[a]];
                a = this.parents[a];
            }
            return a;
        }

        public boolean isInSameGroup(int a, int b) {
            // Return true if both a and b belong to the same set, otherwise return false
            return Find(a) == Find(b);
        }

        // Initialize weight for each node to be 1
        public DisjointSet(int N) {
            this.parents = new int[N + 1];
            this.weights = new int[N + 1];
            // Set the initial parent node to itself
            for (int i = 1; i <= N; ++i) {
                this.parents[i] = i;
                this.weights[i] = 1;
            }
        }
    }
}
