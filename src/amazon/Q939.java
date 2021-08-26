package amazon;

import java.util.*;
//  Minimum Area Rectangle
public class Q939 {
    // O(n^2) O(n)
    public int minAreaRect(int[][] points) {
        Map<Integer, List<Integer>> treemap = new TreeMap<>();
        for(int[] p : points) {
            treemap.computeIfAbsent(p[0], x -> new ArrayList<>()).add(p[1]);
        }

        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> indexMap = new HashMap<>();
        for(int x : treemap.keySet()) {
            List<Integer> list = treemap.get(x);
            Collections.sort(list);
            for(int i = 0; i < list.size() - 1; i++) {
                for(int j = i +1; j < list.size(); j++) {
                    int y1 = list.get(i), y2 = list.get(j);
                    int index = y1 * 40001 + y2;
                    if(indexMap.containsKey(index)) {
                        System.out.println(x + " " + indexMap.get(index));
                        min = Math.min(min, (y2 - y1) * (x - indexMap.get(index)));
                    }
                    indexMap.put(index, x);
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    // count by diagonal
    public int minAreaRect1(int[][] points) {
        Set<Integer> set = new HashSet<>();
        for(int[] p : points) {
            set.add(p[0] * 40001 + p[1]);
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < points.length - 1; i++) {
            for(int j = i + 1; j < points.length; j++) {
                int[] p1 = points[i], p2 = points[j];
                if(p1[0] != p2[0] && p1[1] != p2[1]) {  // must contain this condition.
                    if(set.contains(p1[0] * 40001 + p2[1]) && set.contains(p2[0] * 40001 + p1[1])) {
                        min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
