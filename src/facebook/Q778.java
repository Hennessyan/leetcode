package facebook;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

// Swim in Rising Water
public class Q778 {
    // heap : O(n^2lgn^2) O(n^2) => lgn^2 == 2lgn
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) ->
                grid[a / n][a % n] - grid[b / n][b % n]);
        Set<Integer> seen = new HashSet<>();
        int time = 0;
        pq.offer(0);
        seen.add(0);
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        while(!pq.isEmpty()) {
            int tmp = pq.poll();
            int r = tmp / n, c = tmp % n;
            time = Math.max(time, grid[r][c]);
            if(r == n - 1 && c == n - 1) {
                break;
            }
            for(int i = 0; i < 4; i++) {
                int x = r + dr[i];
                int y = c + dc[i];
                if(x >= 0 && x < n && y >= 0 && y < n && seen.add(x * n + y)) {
                    pq.offer(x * n + y);
                }
            }
        }
        return time;
    }
    // binary search : O(n^2 * lgn) O(n^2)
    public int swimInWater1(int[][] grid) {
        int N = grid.length;
        int lo = grid[0][0], hi = N * N;    // lo is grid[0][0] rather than 0, because -> [[3,2],[0,1]]
        while (lo < hi) {           // lgn
            int mi = lo + (hi - lo) / 2;
            if (!possible(mi, grid)) {
                lo = mi + 1;
            } else {
                hi = mi;
            }
        }
        return lo;
    }

    public boolean possible(int T, int[][] grid) {
        int N = grid.length;
        Set<Integer> seen = new HashSet();
        seen.add(0);
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        Stack<Integer> stack = new Stack();
        stack.add(0);

        while (!stack.empty()) {
            int k = stack.pop();
            int r = k / N, c = k % N;
            if (r == N-1 && c == N-1) return true;

            for (int i = 0; i < 4; ++i) {
                int cr = r + dr[i], cc = c + dc[i];
                int ck = cr * N + cc;
                if (0 <= cr && cr < N && 0 <= cc && cc < N
                        && !seen.contains(ck) && grid[cr][cc] <= T) {
                    stack.add(ck);
                    seen.add(ck);
                }
            }
        }

        return false;
    }
    // disjoint-set : O(n^2) O(n^2)
    // this way can be used for minimal spanning tree
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    int n;
    int[][] grid;
    public int swimInWater2(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        int m = n * n;
        UnionFind uf = new UnionFind(m);
        // sort sets based on weight
        int[] map = new int[m];
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                map[grid[r][c]] = r * n + c;
            }
        }
        // merge sets if possible
        for(int t = 0; t < m; t++) {
            int tmp = map[t];
            int r = tmp / n, c = tmp % n;

            for(int[] d : dirs) {
                int x = r + d[0];
                int y = c + d[1];
                if(isValid(x, y, t)) {
                    uf.union(tmp, x * n + y);
                }
            }

            if(uf.connected(0, m - 1)) return t;
        }
        return -1;
    }
    private boolean isValid(int x, int y, int t) {
        return x >= 0 && x < n && y >= 0 && y < n && grid[x][y] < t;
    }
    class UnionFind {
        int[] parent;
        int[] rank;
        public UnionFind(int m) {
            parent = new int[m];
            rank = new int[m];
            for(int i = 0; i < m; i++) {
                parent[i] = i;
            }
        }
        public int find(int n) {
            // if(n != parent[n]) {
            //     parent[n] = find(parent[n]);
            // }
            // return parent[n];
            while(n != parent[n]) {
                parent[n] = parent[parent[n]];
                n = parent[n];
            }
            return n;
        }
        public void union(int i1, int i2) {
            int r1 = find(i1);
            int r2 = find(i2);
            if(r1 != r2) {
                if(rank[r1] < rank[r2]) {
                    parent[r1] = r2;
                } else if(rank[r1] > rank[r2]) {
                    parent[r2] = r1;
                } else {
                    parent[r1] = r2;
                    rank[r2]++;
                }
            }
        }
        public boolean connected(int i1, int i2) {
            return find(i1) == find(i2);
        }
    }
}
