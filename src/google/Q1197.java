package google;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// Minimum Knight Moves
public class Q1197 {
    // edge of square will be max(2x, 2y) => O((max(x,y)^2) O((max(x,y)^2)
    private int[][] dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1},
            {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
    public int minKnightMoves(int x, int y) {
        // set in java is not efficient, will TLE
        boolean[][] seen = new boolean[605][605];   // will visit 302, so scope is 302 * 2 + 1
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        seen[0][0] = true;
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] tmp = queue.poll();
                if(tmp[0] == x && tmp[1] == y) {
                    return step;
                }
                for(int[] d : dirs) {
                    int[] next = new int[]{tmp[0] + d[0], tmp[1] + d[1]};
                    if(!seen[next[0] + 302][next[1] + 302]) {
                        seen[next[0] + 302][next[1] + 302] = true;
                        queue.add(next);
                    }
                }
            }
            step++;
        }
        return step;
    }
    // bidirectional -> O(0.5 * (max(x,y)^2) O((max(x,y)^2)
    public int minKnightMoves1(int x, int y) {
        // set in java is not efficient, will TLE
        Queue<int[]> q1 = new LinkedList<>();
        Queue<int[]> q2 = new LinkedList<>();
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        q1.add(new int[]{0,0,0});
        q2.add(new int[]{x,y,0});
        m1.put(0, 0);
        m2.put(x * 1000 + y, 0);
        while(true) {
            int[] origin = q1.poll();
            int k1 = origin[0] * 1000 + origin[1];
            if(m2.containsKey(k1)) {
                return origin[2] + m2.get(k1);
            }

            int[] dest = q2.poll();
            int k2 = dest[0] * 1000 + dest[1];
            if(m1.containsKey(k2)) {
                return dest[2] + m1.get(k2);
            }

            for(int[] d : dirs) {
                int[] next1 = new int[]{origin[0] + d[0], origin[1] + d[1], origin[2] + 1};
                int n1k = next1[0] * 1000 + next1[1];
                if(!m1.containsKey(n1k)) {
                    m1.put(n1k, origin[2] + 1);
                    q1.add(next1);
                }
                int[] next2 = new int[]{dest[0] + d[0], dest[1] + d[1], dest[2] + 1};
                int n2k = next2[0] * 1000 + next2[1];
                if(!m2.containsKey(n2k)) {
                    m2.put(n2k, dest[2] + 1);
                    q2.add(next2);
                }
            }
        }
    }
    // dfs - consider only first quadrant: O(xy) O(xy)
    private Map<Integer, Integer> memo = new HashMap<>();
    public int minKnightMoves2(int x, int y) {
        return dfs(Math.abs(x), Math.abs(y));
    }
    private int dfs(int x, int y) {
        int key = x * 1000 + y;
        if(memo.containsKey(key)) {
            return memo.get(key);
        }
        if (x + y == 0) {
            return 0;
        } else if (x + y == 2) {    // two step can arrive [0,0] from [x,y] where x + y == 2
            return 2;
        } else {
            int ret = Math.min(dfs(Math.abs(x - 1), Math.abs(y - 2)),   // only [-1,-2] and [-2,-1] are needed
                    dfs(Math.abs(x - 2), Math.abs(y - 1))) + 1;
            memo.put(key, ret);
            return ret;
        }
    }
}
