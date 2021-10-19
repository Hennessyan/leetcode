package amazon;

import java.util.*;

// Queens That Can Attack the King
public class Q1222 {

    int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> qs = new HashSet<>();
//         boolean[][] seen = new boolean[8][8];
        for(int[] queen : queens) {
            qs.add(queen[0] * 8 + queen[1]);
//            seen[queen[0]][queen[1]] = true;
        }
        for(int i = 0; i < 8; i++) {
            dfs(qs, king[0], king[1], dirs[i], res);
        }
        return res;
    }
    private void dfs(Set<Integer> qs, int r, int c, int[] d, List<List<Integer>> res) {
        int x = r + d[0], y = c + d[1];
        if(x < 0 || x == 8 || y < 0 || y == 8) return;
        if(qs.contains(x * 8 + y)) {
            res.add(Arrays.asList(x, y));
            return;
        }
        dfs(qs, x, y, d, res);
    }
}
