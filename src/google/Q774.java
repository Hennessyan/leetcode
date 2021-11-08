package google;

import java.util.PriorityQueue;

// Minimize Max Distance to Gas Station
public class Q774 {
    // TLE : O(n + klgn) O(n)
    public double minmaxGasDist(int[] stations, int k) {
        if(stations == null || stations.length == 0) return 0.0;
        int n = stations.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return 1.0 * b[0] / b[1] < 1.0 * a[0] / a[1] ? -1 : 1;
        });
        // just between stations, hence no need stations[0] - 0
        for(int i = 1; i < n; i++) {
            pq.offer(new int[]{stations[i] - stations[i - 1], 1});
        }
        while(k-- > 0) {
            int[] tmp = pq.poll();
            tmp[1]++;
            pq.offer(tmp);
        }
        int[] peek = pq.peek();
        return 1.0 * peek[0] / peek[1];
    }
    // O(nlgw) O(1) w = 1e8 / 1e-6 = 1e14
    public double minmaxGasDist1(int[] stations, int k) {
        double lo = 0, hi = 1e8;
        while(hi - lo > 1e-6) {
            double mi = (lo + hi) / 2.0;
            if(possible(mi, stations, k)) {
                hi = mi;
            } else {
                lo = mi;
            }
        }
        return lo;
    }
    private boolean possible(double mi, int[] stations, int k) {
        int used = 0;
        for(int i = 1; i < stations.length; i++) {
            // used += (int) ((stations[i] - stations[i - 1]) / mi);
            used += (int) Math.ceil((stations[i] - stations[i - 1]) / mi) - 1;
        }
        return used <= k;
    }
}
