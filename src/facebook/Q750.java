package facebook;

import java.util.*;

// Number Of Corner Rectangles
public class Q750 {
    // method1 : O(r*c^2) O(c*c)
    public int countCornerRectangles0(int[][] grid) {
        int total = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int m = grid.length, n = grid[0].length;
        for(int r = 0; r < m; r ++) {
            for(int c = 0; c < n; c++) {
                if(grid[r][c] == 1) {
                    for(int c1 = c + 1; c1 < n; c1++) {
                        if(grid[r][c1] == 1) {
                            int key = c * 200 + c1;
                            int val = map.getOrDefault(key, 0);
                            total += val;
                            map.put(key, val + 1);
                        }
                    }
                }
            }
        }
        return total;
    }
    // O(n*sqrt(n) + r*c) O(n+c^2)
    public int countCornerRectangles(int[][] grid) {
        List<List<Integer>> lists = new ArrayList<>();
        int N = 0;
        for(int r = 0; r < grid.length; r++) {  // O(R * C)
            List<Integer> list = new ArrayList<>();
            for(int c = 0 ; c < grid[0].length; c++) {
                if(grid[r][c] == 1) {
                    list.add(c);
                    N++;
                }
            }
            lists.add(list);
        }

        N = (int) Math.sqrt(N);
        int total = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < lists.size(); i++) {
            List<Integer> tmp = lists.get(i);
            int m = tmp.size();
            if(m >= N) {        // O(sqrtn)
                Set<Integer> set = new HashSet<>(tmp);
                for(int j = 0; j < lists.size(); j++) { //需要遍历整个lists.  O(n)
                    int t = 0;
                    if(j <= i && lists.get(j).size() >= N) {    //排除之前与当前位置（计算过的>= SQRT(N)）的行避免重复计算.
                        continue;
                    }
                    for(int val : lists.get(j)) {
                        if(set.contains(val)) {
                            t++;
                        }
                    }
                    total += t * (t - 1) / 2;
                }
            } else {
                for(int j = 0; j < m; j++) {
                    for(int k = j + 1; k < m; k++) {
                        int key = tmp.get(j) * 200 + tmp.get(k);
                        int val = map.getOrDefault(key, 0);
                        total += val;
                        map.put(key, val + 1);
                    }
                }
            }
        }
        return total;
    }
}
