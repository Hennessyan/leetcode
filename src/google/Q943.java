package google;

import java.util.Arrays;

// Find the Shortest Superstring
public class Q943 {
    public static void main(String[] args) {
        Q943 q = new Q943();
        String[] A = {"catg","ctaagt","gcta","ttca","atgcatc"};
        System.out.println(q.shortestSuperstring(A));   // "gctaagttcatgcatc"
    }
    // 2^n vs n!
    // 2^n => n可以做到20
    // n! => n可以到10, 11 / 12就很勉强,需要PRE-PROCESSING & PRUNING.
    // https://www.youtube.com/watch?v=eG99FDBeuJo

    // DFS : O(n!) O(n^2)
    private int n;
    private int[][] g;
    private String[] a;
    private int best_len;
    private int[] path;
    private int[] best_path;
    public String shortestSuperstring0(String[] A) {
        n = A.length;
        g = new int[n][n];  // cost - diff part in suffix of A[j] after comparing A[i] and A[j]
        a = A;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }
                g[i][j] = getCost(A[i], A[j]);
            }
        }
        best_len = Integer.MAX_VALUE;
        path = new int[n];

        dfs(0, 0, 0);
        StringBuilder res = new StringBuilder();
        res.append(A[best_path[0]]);
        for(int k = 1; k < n; k++) {
            int i = best_path[k-1];
            int j = best_path[k];
            res.append(A[j].substring(A[j].length() - g[i][j]));
        }
        return res.toString();
    }

    private void dfs(int visit, int deep, int cur_len) {
        if(cur_len > best_len) {
            return;
        }
        if(deep == n) {
            best_len = cur_len;
            best_path = path.clone();
            return;
        }

        for(int i = 0; i < n; i++) {
            if((visit & (1 << i)) == 0) {
                path[deep] = i;
                dfs(visit | (1 << i),
                        deep + 1,
                        cur_len + (deep == 0 ? a[i].length() : g[path[deep-1]][i]));
            }
        }
    }

    private int getCost(String a, String b) {
        int alen = a.length(), blen = b.length(), len = blen;
        for(int i = 1; i <= Math.min(alen, blen); i++) {
            if(a.substring(alen - i).equals(b.substring(0, i))) {
                len = blen - i;
            }
        }
        return len;
    }
    // DP : O(2^n * n^2) O(n * 2^n)
    public String shortestSuperstring(String[] A) {
        n = A.length;
        g = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }
                g[i][j] = getCost(A[i], A[j]);
            }
        }

        int[][] dp = new int[1 << n][n]; // dp[i][j] - i represent path by binary, j - last word is j
        int[][] parent = new int[1 << n][n];
        for(int[] tmp : dp) {
            Arrays.fill(tmp, Integer.MAX_VALUE / 2);
        }
        for(int[] tmp : parent) {
            Arrays.fill(tmp, -1);
        }
        for(int i = 0; i < n; i++) {
            dp[1 << i][i] = A[i].length();
        }

        for(int s = 1; s < (1 << n); s++) { // start from 1
            for(int j = 0; j < n; j++) {
                if((s & (1 << j)) == 0) {
                    continue;
                }
                int pre = s & ~(1 << j);
                for(int i = 0; i < n; i++) {
                    if(dp[pre][i] + g[i][j] < dp[s][j]) {
                        dp[s][j] = dp[pre][i] + g[i][j];
                        parent[s][j] = i;
                    }
                }
            }
        }

        int j = -1, diff = Integer.MAX_VALUE, s = (1 << n) - 1;
        for(int k = 0; k < n; k++) {
            if(diff > dp[s][k]) {
                diff = dp[s][k];
                j = k;
            }
        }

        StringBuilder res = new StringBuilder();
        while(s > 0) {
            int i = parent[s][j];
            if(i == -1) {
                res.insert(0, A[j]);
            } else {
                res.insert(0, A[j].substring(A[j].length() - g[i][j]));
            }
            s &= ~(1 << j);
            j = i;
        }
        return res.toString();
    }
}