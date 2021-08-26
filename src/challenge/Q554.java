package challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Brick Wall
public class Q554 {
    // method1 - brute force O(n*m) O(m) - n is width of wall, m is wall.size()
    public int leastBricks(List <List< Integer >> wall) {
        int[] pos = new int[wall.size()];
        int c = 0, sum = 0, res = Integer.MAX_VALUE;
        for (int el: wall.get(0))
            sum += el;
        while (sum != 0) {
            int count = 0;
            for (int i = 0; i < wall.size(); i++) {
                List < Integer > row = wall.get(i);
                if (row.get(pos[i]) != 0)
                    count++;
                else
                    pos[i]++;
                row.set(pos[i], row.get(pos[i]) - 1);
            }
            sum--;
            res = Math.min(res, count);
        }
        return res;
    }
    // O(nm) O(m)
    public int leastBricks1(List<List<Integer>> wall) {
        int n = wall.size();
        int[] p = new int[n];
        int sum = 0;
        for(int val : wall.get(0)) {
            sum += val;
        }
        int res = Integer.MAX_VALUE;
        while(sum > 0) {
            int min = Integer.MAX_VALUE, count = 0;
            for(int i = 0; i < n; i++) {
                List<Integer> row = wall.get(i);
                if(row.get(p[i]) > 0) {
                    count++;
                } else {
                    p[i]++;
                }
                min = Math.min(min, row.get(p[i])); // outside of if-else condition, because p[i] might be new element based on L44.
            }
            for(int i = 0; i < n; i++) {
                List<Integer> row = wall.get(i);
                row.set(p[i], row.get(p[i]) - min);
            }
            sum -= min;
            res = Math.min(res, count);
        }
        return res;
    }
    // O(total bricks) O(width of wall)
    public int leastBricks2(List<List<Integer>> wall) {
        int n = wall.size();
        Map<Integer, Integer> map = new HashMap<>();
        for(List<Integer> row : wall) {
            int sum = 0;
            for(int i = 0; i < row.size() - 1; i++) {   // don't calculate last one as it's end of wall.
                sum += row.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        int max = 0;
        for(int count : map.values()) {
            max = Math.max(max, count);
        }
        return n - max;
    }
}
