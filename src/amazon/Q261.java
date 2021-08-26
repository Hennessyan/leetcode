package amazon;

import java.util.*;

// Graph Valid Tree
// 查看LEETCODE的SOLUTION -> 当graph有N-1个EDGE的情况下我们不用担心会有CYCLE!!!
// 但是我们还要检查是否所有的点都被访问 (only one valid tree.)
public class Q261 {
    // BFS
    public boolean validTree(int n, int[][] edges) {
        boolean[] seen = new boolean[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] e : edges) {
            graph.computeIfAbsent(e[0], x -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], x -> new ArrayList<>()).add(e[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        seen[0] = true;
        int total = 1;

        while(!queue.isEmpty()) {
            Integer u = queue.poll();   //graph的value如果使用set的话就不用Integer来避免remove的时候的问题.
            if(graph.containsKey(u)) {
                for(int v : graph.get(u)) {
                    if(seen[v]) {
                        return false;
                    }
                    queue.add(v);
                    total++;
                    seen[v] = true;
                    graph.get(v).remove(u); // expensive operation.
                }
            }
        }
        return total == n;
    }
    // DFS1
    public boolean validTree1(int n, int[][] edges) {
        boolean[] seen = new boolean[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] e : edges) {
            graph.computeIfAbsent(e[0], x -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], x -> new ArrayList<>()).add(e[0]);
        }
        seen[0] = true;
        if(!dfs(graph, seen, 0)) {
            return false;
        }
        for(int i = 1; i < n; i++) {
            if(!seen[i]) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] seen, Integer u) {
        if(graph.containsKey(u)) {
            for(int v : graph.get(u)) {
                if(seen[v]) {
                    return false;
                }
                seen[v] = true;
                graph.get(v).remove(u);
                if(!dfs(graph,seen,v)) {
                    return false;
                }
            }
        }
        return true;
    }
    // DFS2 : O(V+E) O(V+E), better than DFS1 because no remove operation!
    public boolean validTree2(int n,int[][] edges) {
        boolean[] seen = new boolean[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] e : edges) {
            graph.computeIfAbsent(e[0], x -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], x -> new ArrayList<>()).add(e[0]);
        }
        if(!dfs(graph, seen, 0, -1)) {
            return false;
        }
        for(int i = 1; i < n; i++) {    // we can use set rather than boolean array, then just check if set.size() == n.
            if(!seen[i]) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] seen, int u, int parent) {
        if(seen[u]) {
            return false;
        }
        seen[u] = true;
        if(graph.containsKey(u)) {
            for(int v : graph.get(u)) {
                if(v == parent) {
                    continue;
                }
                if(!dfs(graph, seen, v, u)) {
                    return false;
                }
            }
        }
        return true;
    }
    // 这道题使用UF的方式比较不一样！！！
    public boolean validTree3(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for(int[] e : edges) {
            int x = uf.find(e[0]);
            int y = uf.find(e[1]);
            if(x == y) {
                return false;
            }
            uf.union(x, y);
        }
        return n - 1 == edges.length;   //不需要设置并查看COUNT！！
    }
    class UnionFind {
        int[] parent;
        int[] rank;
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public void union(int r1, int r2) {
            // int r1 = find(i1);
            // int r2 = find(i2);
            if(r1 != r2) {
                if(rank[r1] < rank[r2]) {
                    parent[r1] = r2;
                } else if(rank[r1] > rank[r2]) {
                    parent[r2] = r1;
                } else {
                    parent[r2] = r1;
                    rank[r1]++;
                }
            }
        }
        public int find(int i) {
            while(parent[i] != i) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return parent[i];
        }
    }

    public boolean validTree4(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        Map<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        parent.put(0, -1);

        while(!queue.isEmpty()) {
            Integer u = queue.poll();

            for(int v : graph[u]) {
                if(parent.get(u) == v) {
                    continue;
                }
                if(parent.containsKey(v)) {
                    return false;
                }
                parent.put(v, u);
                queue.add(v);
            }
        }
        return parent.size() == n;
    }
    // For the graph to be a valid tree, it must have exactly n - 1 edges.
    // Any less, and it can't possibly be fully connected.
    // Any more, and it has to contain cycles.
    // Additionally, if the graph is fully connected and contains exactly n - 1 edges,
    // it can't possibly contain a cycle, and therefore must be a tree!
    Set<Integer> seen;
    List<List<Integer>> graph;
    public boolean validTree5(int n, int[][] edges) {
        if(n - 1 != edges.length) {
            return false;
        }
        graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        seen = new HashSet<>();
        seen.add(0);
        dfs(0);
        return seen.size() == n;
    }
    private void dfs(int u) {
        for(int v : graph.get(u)) {
            if(seen.add(v)) {
                dfs(v);
            }
        }
    }
}
