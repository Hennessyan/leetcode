package challenge.june;

import java.util.LinkedList;
import java.util.Queue;

// Surrounded Regions
public class Q130 {

    // BFS : O(mn) O(min(m,n))
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void solve2(char[][] board) {
        if(board == null || board.length == 0) {
            return;
        }
        int m = board.length, n = board[0].length;

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') {
                queue.add(i * n);
            }
            if(board[i][n - 1] == 'O') {
                queue.add(i * n + n - 1);
            }
        }
        for(int j = 1; j < n - 1; j ++) {
            if(board[0][j] == 'O') {
                queue.add(j);
            }
            if(board[m - 1][j] == 'O') {
                queue.add((m - 1) * n + j);
            }
        }
        while(!queue.isEmpty()) {
            int tmp = queue.poll();
            int x = tmp / n;
            int y = tmp % n;
            if(board[x][y] != 'O') continue;
            board[x][y] = 'I';
            for(int[] d : dirs) {
                int x1 = d[0] + x;
                int y1 = d[1] + y;
                if(x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && board[x1][y1] == 'O') {
                    queue.add(x1 * n + y1);
                }
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == 'I') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    // DFS O(mn) O(mn)
    int m, n;
    public void solve1(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        this.m = board.length;
        this.n = board[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if((i == 0 || j == 0 || i == m - 1 || j == n - 1) && board[i][j] == 'O') {
                    board[i][j] = 'I';
                    dfs(board, i, j);
                }
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == 'I') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    private void dfs(char[][] board, int i, int j) {
        for(int[] d : dirs) {
            int x = d[0] + i;
            int y = d[1] + j;
            if(x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                board[x][y] = 'I';
                dfs(board, x, y);
            }
        }
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        this.m = board.length;
        this.n = board[0].length;
        int sum = m * n;
        DSU dsu = new DSU(sum);
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(board[r][c] == 'O') {
                    if(r == 0 || c == 0 || r == m - 1 || c == n - 1) {
                        dsu.union(m*n, r * n + c);
                    }else {
                        if(r - 1 >= 0 && board[r-1][c] == 'O') dsu.union(r * n + c, (r - 1) * n + c);
                        if(r + 1 < m && board[r+1][c] == 'O') dsu.union(r * n + c, (r + 1) * n + c);
                        if(c - 1 >= 0 && board[r][c-1] == 'O') dsu.union(r * n + c, r * n + c - 1);
                        if(c + 1 < n && board[r][c+1] == 'O') dsu.union(r * n + c, r * n + c + 1);
                    }
                }
            }
        }
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(dsu.find(r * n + c) != dsu.find(m * n)) {
                    board[r][c] = 'X';
                }
            }
        }
    }
    class DSU {
        int[] parent;
        int[] rank;
        public DSU(int num) {
            parent = new int[num + 1];
            rank = new int[num + 1];
            for(int i = 1; i < num + 1; i++) {
                parent[i] = i;
            }
        }
        public int find(int n) {
            if(n != parent[n]) {
                parent[n] = find(parent[n]);
            }
            return parent[n];
        }

//        int find(int node) {
//            while(parent[node] != node) {
//                parent[node] = parent[parent[node]];
//                node = parent[node];
//            }
//
//            return node;
//        }

        public void union(int n1, int n2) {
            int r1 = find(n1);
            int r2 = find(n2);
            if(r1 != r2) {
                if(rank[r1] > rank[r2]) {
                    parent[r2] = r1;
                }else if(rank[r1] < rank[r2]) {
                    parent[r1] = r2;
                } else {
                    parent[r2] = r1;
                    rank[r1]++;
                }
            }
        }
    }
}
