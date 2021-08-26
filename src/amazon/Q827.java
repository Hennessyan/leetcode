package amazon;

import java.util.*;

// Making A Large Island
public class Q827 {
    // O(n^2) O(n^2)
    public int largestIsland(int[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        int max = 1;
        Queue<int[]> queue = new LinkedList<>();
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(grid[r][c] == 1) {
                    if(r > 0 && grid[r - 1][c] == 1) uf.union(r* n + c, (r - 1) * n + c);
                    if(c > 0 && grid[r][c - 1] == 1) uf.union(r* n + c, r * n + c - 1);
                    if(r + 1 < m && grid[r + 1][c] == 1) uf.union(r* n + c, (r + 1) * n + c);
                    if(c + 1 < n && grid[r][c + 1] == 1) uf.union(r* n + c, r * n + c + 1);
                }
            }
        }
        for(int i = 0; i < m * n; i++) {

            if(uf.count[i] == 0) {
                queue.add(new int[]{i / n, i % n});
            } else {
                max = Math.max(max, uf.count[i]);
            }
        }

        while(!queue.isEmpty()) {
            int[] t = queue.poll();
            int sum = 1;
            Set<Integer> set = new HashSet<>();
            for(int[] d : dirs) {
                int x = d[0] + t[0];
                int y = d[1] + t[1];
                if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                    if(set.add(uf.find(x * n + y))) {
                        sum += uf.getCount(x * n + y);
                    }
                }
            }
            max = Math.max(max, sum);
        }
        return max;

    }
    class UnionFind {
        int[] parent;
        int[] rank;
        int[] count;
        public UnionFind(int[][] grid) {
            int m = grid.length, n = grid[0].length, total = m * n;
            parent = new int[total];
            rank = new int[total];
            count = new int[total];
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    int tmp = r * n + c;
                    if(grid[r][c] == 1) {
                        count[tmp] = 1;
                    }
                    parent[tmp] = tmp;
                }
            }
        }
        public int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }
        public void union(int i1, int i2) {
            int r1 = find(i1);
            int r2 = find(i2);
            if(r1 != r2) {
                if(rank[r1] > rank[r2]) {
                    parent[r2] = r1;
                    count[r1] += count[r2];
                } else if(rank[r1] < rank[r2]) {
                    parent[r1] = r2;
                    count[r2] += count[r1];
                } else {
                    parent[r2] = r1;
                    rank[r1]++;
                    count[r1] += count[r2];
                }
            }
        }
        public int getCount(int i) {
            return count[find(i)];
        }
    }
    // O(n^4) O(n^2) - TLE
    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};

    public int largestIsland1(int[][] grid) {
        int N = grid.length;

        int ans = 0;
        boolean hasZero = false;
        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c)
                if (grid[r][c] == 0) {
                    hasZero = true;
                    grid[r][c] = 1;
                    ans = Math.max(ans, check(grid, r, c));
                    grid[r][c] = 0;
                }

        return hasZero ? ans : N*N;
    }

    public int check(int[][] grid, int r0, int c0) {
        int N = grid.length;
        Stack<Integer> stack = new Stack();
        Set<Integer> seen = new HashSet();
        stack.push(r0 * N + c0);
        seen.add(r0 * N + c0);

        while (!stack.isEmpty()) {
            int code = stack.pop();
            int r = code / N, c = code % N;
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k], nc = c + dc[k];
                if (!seen.contains(nr * N + nc) && 0 <= nr && nr < N &&
                        0 <= nc && nc < N && grid[nr][nc] == 1) {
                    stack.push(nr * N + nc);
                    seen.add(nr * N + nc);
                }
            }
        }

        return seen.size();
    }
}
