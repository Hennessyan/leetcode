package amazon;

import java.util.LinkedList;
import java.util.Queue;

// Number of Provinces
public class Q547 {
    // BFS O(n^2) O(n)
    public int findCircleNum(int[][] isConnected) {
        int count = 0;
        int n = isConnected.length;
        for(int i = 0; i < n; i++) {
            if(isConnected[i][i] == 1) {
                count++;
                isConnected[i][i] = 0;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                while(!queue.isEmpty()) {
                    int u = queue.poll();
                    for(int v = 0; v < n; v++) {
                        if(isConnected[u][v] == 1 && isConnected[v][v] == 1) {
                            isConnected[v][v] = 0;
                            queue.add(v);
                        }
                    }
                }
            }
        }
        return count;
    }
    // DFS
    public int findCircleNum1(int[][] isConnected) {
        int count = 0;
        int n = isConnected.length;
        int[] visited = new int[n];
        for(int i = 0; i < n; i++) {
            if(visited[i] == 0) {
                count++;
                visited[i] = 1;
                dfs(isConnected, visited, i);
            }
        }
        return count;
    }
    private void dfs(int[][] isConnected, int[] visited, int i) {
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(isConnected, visited, j);
            }
        }
    }
    // union find : O(n^2) O(n)
    // 上两种方法通过VISITED来减少重复检测,但是需要扫描每行的每一个元素
    // 若使用L59-60这样,不能加VISITED,不然会忽略掉一些组合
    // 1 0 0 1
    // 0 1 1 0
    // 0 1 1 1
    // 1 0 1 1
    public int findCircleNum2(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count;
    }
    class UnionFind {
        int[] parent;
        int[] rank;
        int count;
        public UnionFind(int total) {
            parent = new int[total];
            rank = new int[total];
            count = total;
            for(int i = 0; i < total; i++) {
                parent[i] = i;
            }
        }
        public void union(int i, int j){	// union with rank
            int r1 = find(i);
            int r2 = find(j);
            if(r1 != r2){
                if(rank[r1] > rank[r2]){
                    parent[r2] = r1;
                }else if(rank[r1] < rank[r2]){
                    parent[r1] = r2;
                }else{
                    parent[r2] = r1;
                    rank[r1]++;
                }
                count--;//注意这个要放在if条件里边
            }
        }
        public int find(int n) {
            if(parent[n] != n) {
                parent[n] = find(parent[n]);
            }
            return parent[n];
        }
    }
}

