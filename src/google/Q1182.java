package google;

import java.util.*;

// Shortest Distance to Target Color
public class Q1182 {
    // O(qlgn + n) O(n) - q : length of queries, n : length of colors
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        int n = colors.length;
        for(int i = 0; i < n; i++) {
            map.computeIfAbsent(colors[i], x -> new TreeSet<>()).add(i);
        }

        for(int[] query : queries) {
            if(!map.containsKey(query[1])) {
                res.add(-1);
                continue;
            }
            TreeSet<Integer> set = map.get(query[1]);
            int dist = n;

            Integer floor = set.floor(query[0]);
            Integer ceiling = set.ceiling(query[0]);

            if(floor != null) {
                dist = Math.min(dist, query[0] - floor);
            }
            if(ceiling != null) {
                dist = Math.min(dist, ceiling - query[0]);
            }
            res.add(dist);
        }
        return res;
    }
    // O(q + n) O(n)
    public List<Integer> shortestDistanceColor1(int[] colors, int[][] queries) {
        List<Integer> res = new ArrayList<>();
        int n = colors.length;

        int[] rightMost = new int[3];
        int[] leftMost = new int[3];
        int[][] dist = new int[3][n];
        Arrays.fill(leftMost, n - 1);
        for(int[] row : dist) {
            Arrays.fill(row, -1);
        }
        // forward
        for(int i = 0; i < n; i++) {
            int color = colors[i] - 1;
            for(int j = rightMost[color]; j <= i; j++) {
                dist[color][j] = i - j;
            }
            rightMost[color] = i + 1;
        }
        // backward
        for(int i = n - 1; i >= 0; i--) {
            int color = colors[i] - 1;
            for(int j = leftMost[color]; j >= i; j--) {
                if(dist[color][j] == - 1 || dist[color][j] > j - i) {
                    dist[color][j] = j - i;
                }
            }
            leftMost[color] = i - 1;
        }

        for(int[] query : queries) {
            res.add(dist[query[1] - 1][query[0]]);
        }
        return res;
    }
}
