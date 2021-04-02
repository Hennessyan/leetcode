package amazon;

import java.util.Arrays;
import java.util.PriorityQueue;

// K Closest Points to Origin
public class Q973 {
    // O(n^2) in worst case, O(n) in average
    // O(n)
    public int[][] kClosest(int[][] points, int K) {
        int len = points.length;
        long[] tmp = new long[len];
        for(int i = 0; i < len; i++) {
            tmp[i] = convert(points[i], i);
        }
        sort(tmp, 0, len - 1, K);
        int[][] res=  new int[K][2];
        for(int i = 0; i < K; i++) {
            res[i] = points[(int)tmp[i]];
        }
        return res;
    }
    private long convert(int[] p, int i) {
        return (long) (p[0] * p[0] + p[1] * p[1]) << 32 | i;
    }
    private void sort(long[] arr, int l, int r, int k) {
        if(l >= r) {
            return;
        }
        int pivot = partition(arr, l, r);
        if(pivot < k) {
            sort(arr, pivot + 1, r, k);
        }else if(pivot > k) {
            sort(arr, l, pivot - 1, k);
        }
    }
    private int partition(long[] arr, int l, int r) {
        int index = (int) Math.random() * (r - l + 1) + l;
        swap(arr, l, index);
        long val = arr[l];
        while(l < r) {
            while(l < r && arr[r] >= val) {
                r--;
            }
            arr[l] = arr[r];
            while(l < r && arr[l] <= val) {
                l++;
            }
            arr[r] = arr[l];
            // r--;
        }
        arr[l] = val;
        return l;
    }
    private void swap(long[] arr, int l, int r) {
        long t = arr[l];
        arr[l] = arr[r];
        arr[r] = t;
    }
    // O(nlgn) O(n)
    public int[][] kClosest1(int[][] points, int K) {
        int N = points.length;
        int[] dists = new int[N];
        for (int i = 0; i < N; ++i)
            dists[i] = dist(points[i]);

        Arrays.sort(dists);
        int distK = dists[K-1];

        int[][] ans = new int[K][2];
        int t = 0;
        for (int i = 0; i < N; ++i)
            if (dist(points[i]) <= distK)
                ans[t++] = points[i];
        return ans;
    }

    public int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
    // O(nlgk) O(k)
    public int[][] kClosest2(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> dist(b) - dist(a));
        for(int[] point : points) {
            pq.offer(point);
            if(pq.size() > K) {
                pq.poll();
            }
        }
        int[][] res = new int[K][];
        int i = 0;
        while(!pq.isEmpty()) {
            res[i++] = pq.poll();
        }
        return res;

    }

}
