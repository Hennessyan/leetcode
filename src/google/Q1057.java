package google;

import java.util.*;

// Campus Bikes
public class Q1057 {

    public static void main(String[] args) {
        Q1057 q = new Q1057();
        int[][] workers = {{0, 0}, {2, 1}};
        int[][] bikes = {{1, 2}, {3, 3}};
        int[] res = q.assignBikes(workers, bikes);
        System.out.println(Arrays.toString(res));
    }
    //bucket sort : good idea
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int wl = workers.length, bl = bikes.length;
        int[] warray = new int[wl];
        int[] barray = new int[bl];
        Arrays.fill(warray, -1);
        Arrays.fill(barray, -1);
        Map<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 0; i < wl; i++) {
            for(int j = 0; j < bl; j++) {
                int d = dist(workers[i], bikes[j]);
                min = Math.min(min, d);
                max = Math.max(max, d);
                map.computeIfAbsent(d, x -> new LinkedList<>()).add(new int[]{i, j});
            }
        }
        for(int j = min, i = 0; j <= max && i < wl; j++) {
            if(map.containsKey(j)) {
                for(int[] pair : map.get(j)) {
                    if(warray[pair[0]] == -1 && barray[pair[1]] == -1) {
                        warray[pair[0]] = pair[1];
                        barray[pair[1]] = pair[0];
                        i++;
                    }
                }
            }
        }
        return warray;
    }

    private int dist(int[] w, int[] b) {
        return Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1]);
    }

    //O(mn) O(m+n) 如果不考虑dists的大小的话是m+n
    public int[] assignBikes1(int[][] workers, int[][] bikes) {
        // Notice that the Manhattan distance is between 0 and 2000,
        // which means we can sort easily without even using priority queue
        int w = workers.length, b = bikes.length;
        int[] wo = new int[w], bi = new int[b];
        List<int[]>[] dists = new List[2001];	//如果不知道workers/bikes的范围,就要用map来存，但是需要注意之后要对key进行sort然后求value;
        Arrays.fill(wo, -1);					//不能直接map.values()，因为map是不按大小顺序进行保存key-value的.
        Arrays.fill(bi, -1);
        for (int i = 0; i < w; i++) {				//两个for循环就决定了woker跟bike的index顺序是从小到大
            for (int j = 0; j < b; j++) {
                int[] worker = workers[i];
                int[] bike = bikes[j];
                int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                if (dists[dist] == null) {			//如果没有对应的dist，可以不new ArrayList，从而节约空间和时间.
                    dists[dist] = new ArrayList<int[]>();
                }
                dists[dist].add(new int[]{i, j});	//dists决定了distance的从小到大
            }
        }
        int assigned = 0;							//加快了循环结束
        for (int i = 0; i <= 2000 && assigned < w; i++) {
            if (dists[i] == null) continue;
            for (int[] pair : dists[i]) {
                if (wo[pair[0]] == -1 && bi[pair[1]] == -1) {
                    wo[pair[0]] = pair[1];
                    bi[pair[1]] = pair[0];
                    assigned++;
                }
            }
        }
        return wo;
    }
    //heap : O(mn*lgmn) O(mn)
    public int[] assignBikes2(int[][] workers, int[][] bikes) {
        int w = workers.length;
        Set<Integer> sw = new HashSet<>();
        Set<Integer> sb = new HashSet<>();
//        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] a, int[] b) {
//                return a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0];
//            }
//        });
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0]);
        for(int i = 0; i < w; i++) {
            for(int j = 0; j < bikes.length; j++) {
                int d = dist(workers[i], bikes[j]);
                pq.offer(new int[]{d, i, j});
            }
        }
        int i = 0;
        int[] res = new int[w];
        while(i < w) {
            int[] t = pq.poll();
            if(!sw.contains(t[1]) && !sb.contains(t[2])) {
                res[t[1]] = t[2];
                sw.add(t[1]);
                sb.add(t[2]);
                i++;
            }
        }
        return res;
    }
}
