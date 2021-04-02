package amazon;

import java.util.PriorityQueue;

// The K Weakest Rows in a Matrix
public class Q1337 {
    // O(mlgn + mlgnk) => O(mlg(nk)) O(m + k)
    public int[] kWeakestRows(int[][] mat, int k) {
        if(mat == null || mat.length == 0) {
            return new int[0];
        }
        int m = mat.length;
        int[] score = new int[m];
        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1, (a, b) ->
                score[b] - score[a] == 0 ? b - a : score[b] - score[a]);
        for(int i = 0; i < m; i++) {
            score[i] = count(mat[i]);
            pq.add(i);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        int[] res = new int[k];
        while(k > 0) {
            res[--k] = pq.poll();
        }
        return res;
    }

    private int count(int[] row) {
        int l = 0, r = row.length - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(row[m] == 1) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l;
    }


    private int binarySearch(int[] row) {
        int low = 0;
        int high = row.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (row[mid] == 1) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public int[] kWeakestRows1(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        // Create a Priority Queue that measures firstly on strength and then indexes.
        PriorityQueue<int[]> pq = new  PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            else return b[0] - a[0];
        });

        // Add strength/index pairs to the pq. Whenever length > k, remove the largest.
        for (int i = 0; i < m; i++) {
            int strength = binarySearch(mat[i]);
            int[] entry = new int[]{strength, i};
            pq.add(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Pull the indexes out of the priority queue.
        int[] indexes = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            int[] pair = pq.poll();
            indexes[i] = pair[1];
        }

        return indexes;
    }
    // method2 : vertical scan - O(mn) O(1)
    public int[] kWeakestRows2(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[] res = new int[k];
        int index = 0;
        for(int c = 0; c < n && index < k; c++) {           // index < k
            for(int r = 0; r < m && index < k; r++) {       // index < k
                // find first zero
                if(mat[r][c] == 0 && (c == 0 || mat[r][c - 1] == 1)) {
                    res[index++] = r;
                }
            }
        }
        int r = 0;
        while(index < k) {
            if(mat[r][n - 1] == 1) {
                res[index++] = r;
            }
            r++;
        }
        return res;
    }
}
