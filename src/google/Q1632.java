package google;
// Rank Transform of a Matrix

import java.util.*;

public class Q1632 {
    // 1. calculate rowMax and colMax takes O(m+n) each round => pre-calculate by using array to record them.
    // 2. wrong answer:
    // 2.a the minimal rank is not always the maximum of the max of other ranks in the same row and col plus one => (same value).
    // 2.b we may need to adjust the previous rank we set before !!! (the reason why below method is not correct.)
    public int[][] matrixRankTransform0(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] rank = new int[m][n];

        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                list.add(new int[]{i, j});
            }
        }
        Collections.sort(list, (l1, l2) -> (matrix[l1[0]][l1[1]] - matrix[l2[0]][l2[1]]));

        int[] p = null;
        for(int i = 0; i < m * n; i++) {
            int[] q = list.get(i);
            if(p != null && matrix[p[0]][p[1]] == matrix[q[0]][q[1]]) {
                rank[q[0]][q[1]] = rank[p[0]][p[1]];
            } else {
                int rmax = findRowRank(rank, q[0]);
                int lmax = findColRank(rank, q[1]);
                rank[q[0]][q[1]] = Math.max(rmax, lmax) + 1;
            }
            p = q;
        }
        return rank;
    }
    private int findRowRank(int[][] rank, int r) {
        int max = Integer.MIN_VALUE;
        for(int val : rank[r]) {
            max = Math.max(max, val);
        }
        return max;
    }
    private int findColRank(int[][] rank, int c) {
        int max = Integer.MIN_VALUE;
        for(int r = 0; r < rank.length; r++) {
            max = Math.max(max, rank[r][c]);
        }
        return max;
    }

    /******************* correct solutions ************************/
    // instead of connecting point to point ((mn)^2), we can connect row to col, and col to row (mn).
    // save graphRow[i] and graphCol[j] together? -> avoid 0 used for both row and col -> col => -col-1 => ~col
    // complement <====> ~
    // BFS / DFS : O(mnglgmn) O(mn)
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Map<Integer, List<Integer>> graph;
        Map<Integer, Map<Integer, List<Integer>>> graphs = new HashMap<>();
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(!graphs.containsKey(matrix[r][c])) {
                    graphs.put(matrix[r][c], new HashMap<>());
                }
                graph = graphs.get(matrix[r][c]);
                if(!graph.containsKey(r)) {
                    graph.put(r, new ArrayList<>());
                }
                if(!graph.containsKey(~c)) {
                    graph.put(~c, new ArrayList<>());
                }
                graph.get(r).add(~c);
                graph.get(~c).add(r);
            }
        }

        TreeMap<Integer, List<List<int[]>>> val2Indexes = new TreeMap<>();
        int[][] seen = new int[m][n];
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(seen[r][c] == 1) continue;
                seen[r][c] = 1;
                graph = graphs.get(matrix[r][c]);
                Set<Integer> rowCols = new HashSet<>();
                rowCols.add(r);
                rowCols.add(~c);
                Queue<Integer> queue = new LinkedList<>();
                queue.add(r);
                queue.add(~c);
                while(!queue.isEmpty()) {
                    int u = queue.poll();
                    for(int v : graph.get(u)) {
                        if(rowCols.add(v)) {
                            queue.add(v);
                        }
                    }
                }

                List<int[]> list = new ArrayList<>();
                // avoid adding points twice!
                Set<Integer> dups = new HashSet<>();

                for(int rowcol : rowCols) {
                    for(int k : graph.get(rowcol)) {
                        if(k >= 0) {
                            if(dups.add(k * 501 + (~rowcol))) {
                                seen[k][~rowcol] = 1;
                                list.add(new int[]{k, ~rowcol});
                            }
                        } else {
                            if(dups.add(rowcol * 501 + (~k))) {
                                seen[rowcol][~k] = 1;
                                list.add(new int[]{rowcol, ~k});
                            }
                        }
                    }
                }

                if(!val2Indexes.containsKey(matrix[r][c])) {
                    val2Indexes.put(matrix[r][c], new ArrayList<>());
                }
                val2Indexes.get(matrix[r][c]).add(new ArrayList<>(list));
            }
        }

        int[][] ans = new int[m][n];
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        // can't use for(List<int[]> points : val2Indexes.values()) directly
        // because val2Indexes.values() is Collections(List<List<int[]>>) !
        for(int key : val2Indexes.keySet()) {
            for(List<int[]> points : val2Indexes.get(key)) {
                int rank = 1;
                for(int[] p : points) {
                    rank = Math.max(rank, rowMax[p[0]] + 1);
                    rank = Math.max(rank, colMax[p[1]] + 1);
                }

                for(int[] p : points) {
                    ans[p[0]][p[1]] = rank;
                    rowMax[p[0]] = Math.max(rowMax[p[0]], rank);
                    colMax[p[1]] = Math.max(colMax[p[1]], rank);
                }
            }
        }

        return ans;
    }

    public int[][] matrixRankTransform1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Map<Integer, List<Integer>> graph;
        Map<Integer, Map<Integer, List<Integer>>> graphs = new HashMap<>();
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(!graphs.containsKey(matrix[r][c])) {
                    graphs.put(matrix[r][c], new HashMap<>());
                }
                graph = graphs.get(matrix[r][c]);
                if(!graph.containsKey(r)) {
                    graph.put(r, new ArrayList<>());
                }
                if(!graph.containsKey(~c)) {
                    graph.put(~c, new ArrayList<>());
                }
                graph.get(r).add(~c);
                graph.get(~c).add(r);
            }
        }

        TreeMap<Integer, List<List<int[]>>> val2Indexes = new TreeMap<>();
        int[][] seen = new int[m][n];
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(seen[r][c] == 1) continue;
                seen[r][c] = 1;
                graph = graphs.get(matrix[r][c]);
                Set<Integer> rowCols = new HashSet<>();
                dfs(graph, rowCols, r);
                // no need to write dfs(graph, rowCols, c) because we will get c related loop in the dfs!.

                List<int[]> list = new ArrayList<>();
                // avoid adding points twice!
                Set<Integer> dups = new HashSet<>();

                for(int rowcol : rowCols) {
                    for(int k : graph.get(rowcol)) {
                        if(k >= 0) {
                            if(dups.add(k * 501 + (~rowcol))) {
                                seen[k][~rowcol] = 1;
                                list.add(new int[]{k, ~rowcol});
                            }
                        } else {
                            if(dups.add(rowcol * 501 + (~k))) {
                                seen[rowcol][~k] = 1;
                                list.add(new int[]{rowcol, ~k});
                            }
                        }
                    }
                }

                if(!val2Indexes.containsKey(matrix[r][c])) {
                    val2Indexes.put(matrix[r][c], new ArrayList<>());
                }
                val2Indexes.get(matrix[r][c]).add(new ArrayList<>(list));
            }
        }

        int[][] ans = new int[m][n];
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        for(int key : val2Indexes.keySet()) {
            for(List<int[]> points : val2Indexes.get(key)) {
                int rank = 1;
                for(int[] p : points) {
                    rank = Math.max(rank, rowMax[p[0]] + 1);
                    rank = Math.max(rank, colMax[p[1]] + 1);
                }

                for(int[] p : points) {
                    ans[p[0]][p[1]] = rank;
                    rowMax[p[0]] = Math.max(rowMax[p[0]], rank);
                    colMax[p[1]] = Math.max(colMax[p[1]], rank);
                }
            }
        }

        return ans;
    }
    private void dfs(Map<Integer, List<Integer>> graph, Set<Integer> rowCols, int u) {
        rowCols.add(u);
        for(int v : graph.get(u)) {
            if(!rowCols.contains(v)) {
                dfs(graph, rowCols, v);
            }
        }
    }
    // Union Find : does not use union by rank here. O(mnlgmn) O(mn)
    public int[][] matrixRankTransform2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Map<Integer, Map<Integer, Integer>> UFs = new HashMap<>();
        Map<Integer, Integer> uf;
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(!UFs.containsKey(matrix[r][c])) {
                    UFs.put(matrix[r][c], new HashMap<>());
                }
                uf = UFs.get(matrix[r][c]);
                union(uf, r, ~c);
            }
        }
        TreeMap<Integer, Map<Integer, List<int[]>>> val2Index = new TreeMap<>();
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                uf = UFs.get(matrix[r][c]);
                if(!val2Index.containsKey(matrix[r][c])) {
                    val2Index.put(matrix[r][c], new HashMap<>());
                }
                Map<Integer, List<int[]>> indexes = val2Index.get(matrix[r][c]);
                int f = find(uf, r);
                if(!indexes.containsKey(f)) {
                    indexes.put(f, new ArrayList<>());
                }
                indexes.get(f).add(new int[]{r, c});
            }
        }

        int[][] ans = new int[m][n];
        int[] rMax = new int[m];
        int[] cMax = new int[n];
        for(int key : val2Index.keySet()) {
            for(List<int[]> points: val2Index.get(key).values()) {
                int rank = 1;
                for(int[] p : points) {
                    rank = Math.max(rank, Math.max(rMax[p[0]], cMax[p[1]]) + 1);
                }

                for(int[] p : points) {
                    ans[p[0]][p[1]] = rank;
                    rMax[p[0]] = Math.max(rMax[p[0]], rank);
                    cMax[p[1]] = Math.max(cMax[p[1]], rank);
                }
            }
        }

        return ans;
    }
    private void union(Map<Integer, Integer> uf, int u, int v) {
        if(!uf.containsKey(u)) {
            uf.put(u, u);
        }
        if(!uf.containsKey(v)) {
            uf.put(v, v);
        }
        int p1 = find(uf, u);
        int p2 = find(uf, v);
        if(p1 != p2) {
            uf.put(p2, p1);
        }
    }
    private int find(Map<Integer, Integer> uf, int u) {
        if(u != uf.get(u)) {
            uf.put(u, find(uf, uf.get(u)));
        }
        return uf.get(u);
    }
}
