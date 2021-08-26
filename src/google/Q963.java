package google;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Minimum Area Rectangle II
public class Q963 {
    // O(n^2) O(n^2)
    public double minAreaFreeRect(int[][] points) {
        int N = points.length;
        Point[] A = new Point[N];
        for (int i = 0; i < N; ++i)
            A[i] = new Point(points[i][0], points[i][1]);

        Map<Integer, Map<Point, List<Point>>> seen = new HashMap();
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j) {
                // center is twice actual to keep integer precision
                Point center = new Point(A[i].x + A[j].x, A[i].y + A[j].y);

                int r2 = (A[i].x - A[j].x) * (A[i].x - A[j].x);
                r2 += (A[i].y - A[j].y) * (A[i].y - A[j].y);
                if (!seen.containsKey(r2))
                    seen.put(r2, new HashMap<Point, List<Point>>());
                if (!seen.get(r2).containsKey(center))
                    seen.get(r2).put(center, new ArrayList<Point>());
                seen.get(r2).get(center).add(A[i]);
            }

        double ans = Double.MAX_VALUE;
            // even each classification is log(N), we will have n / logn * logn * logn => O(nlogn)
        for (Map<Point, List<Point>> info: seen.values()) {
            for (Point center: info.keySet()) {  // center is twice actual
                List<Point> candidates = info.get(center);
                int clen = candidates.size();
                for (int i = 0; i < clen; ++i)
                    for (int j = i+1; j < clen; ++j) {
                        Point P = candidates.get(i);
                        Point Q = candidates.get(j);
                        Point Q2 = new Point(center);
                        Q2.translate(-Q.x, -Q.y);
                        double area = P.distance(Q) * P.distance(Q2);
                        if (area < ans)
                            ans = area;
                    }
            }
        }

        return ans < Double.MAX_VALUE ? ans : 0;
    }

    public double minAreaFreeRect1(int[][] points) {
        int n = points.length;
        Point[] A = new Point[n];
        for(int i = 0; i < n; i++) {
            A[i] = new Point(points[i][0], points[i][1]);
        }
        Map<Integer, Map<Point, List<Point>>> map = new HashMap<>();

        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {    // no duplicates be recorded, as array is distinct, and we travere array with order.
                int len = (A[i].x - A[j].x) * (A[i].x - A[j].x) + (A[i].y - A[j].y) * (A[i].y - A[j].y);
                Point center = new Point(A[i].x + A[j].x, A[i].y + A[j].y);
                map.putIfAbsent(len, new HashMap<>());
                Map<Point, List<Point>> tmp = map.get(len);
                tmp.computeIfAbsent(center, x -> new ArrayList<>()).add(A[i]);  // will only record part of points
            }
        }

        double min = Double.MAX_VALUE;
        for(Map<Point, List<Point>> tmp : map.values()) {
            for(Point center : tmp.keySet()) {
                List<Point> list = tmp.get(center);
                for(int i = 0; i < list.size() - 1; i++) {
                    for(int j = i + 1; j < list.size(); j++) {
                        Point p = list.get(i);
                        Point q = list.get(j);
                        Point t = new Point(center);
                        t.translate(-q.x, -q.y);
                        min = Math.min(min, p.distance(q) * p .distance(t));
                    }
                }
            }
        }
        return min == Double.MAX_VALUE ? 0 : min;
    }
}
