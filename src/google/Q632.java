package google;

import java.util.List;
import java.util.PriorityQueue;

// Smallest Range Covering Elements from K Lists
public class Q632 {

    //Pointers : TLE O(total*m) O(m)
    public int[] smallestRange0(List<List<Integer>> nums) {
        int m = nums.size(), n = nums.get(0).size();
        int[] next = new int[m];
        int minx = 0, miny = Integer.MAX_VALUE;
        boolean flag = true;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n && flag; j++) {
                int min_i = 0, max_i = 0;
                for(int k = 0; k < m; k++) {
                    if(nums.get(min_i).get(next[min_i]) > nums.get(k).get(next[k])) {
                        min_i = k;
                    }
                    if(nums.get(max_i).get(next[max_i]) < nums.get(k).get(next[k])) {
                        max_i = k;
                    }
                }
                if(miny - minx > nums.get(max_i).get(next[max_i]) - nums.get(min_i).get(next[min_i])) {
                    miny = nums.get(max_i).get(next[max_i]);
                    minx = nums.get(min_i).get(next[min_i]);
                }
                next[min_i]++;
                if(next[min_i] == nums.get(min_i).size()) {
                    flag = false;
                }
            }
        }
        return new int[]{minx, miny};
    }
    // O(total*lgm) O(m)
    public int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        int[] index = new int[n];
        int rmax = Integer.MAX_VALUE, rmin = 0;
        int max = Integer.MIN_VALUE;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> nums.get(a).get(index[a]) - nums.get(b).get(index[b]));
        for(int i = 0; i < n; i++) {
            max = Math.max(max, nums.get(i).get(0));
            pq.offer(i);
        }

        while(!pq.isEmpty()) {
            int i = pq.poll();
            int min = nums.get(i).get(index[i]);
            if(max - min < rmax - rmin) {
                rmax = max;
                rmin = min;
            }
            if(++index[i] == nums.get(i).size()) {
                break;
            }
            int val = nums.get(i).get(index[i]);
            max = Math.max(max, val);
            pq.offer(i);
        }
        return new int[]{rmin, rmax};
    }

    //O(total*lgm) O(m)
    public int[] smallestRange2(List<List<Integer>> nums) {	//每行的元素数不相等,因此不能用m*n
        int[] next = new int[nums.size()];
        int minx = 0, miny = Integer.MAX_VALUE;
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> (nums.get(i1).get(next[i1]) - nums.get(i2).get(next[i2])));
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.size(); i++) {
            pq.offer(i);
            max = Math.max(max, nums.get(i).get(0));
        }
        boolean flag = true;
        for(int i = 0; i < nums.size() && flag; i++) {				//不能用m
            for(int j = 0; j < nums.get(i).size() && flag; j++) {	//不能用n
                int min = pq.poll();
                if(miny - minx > max - nums.get(min).get(next[min])) {
                    miny = max;
                    minx = nums.get(min).get(next[min]);
                }
                next[min]++;
                if(next[min] == nums.get(min).size()) {
                    flag = false;
                    break;
                }
                pq.offer(min);
                max = Math.max(max, nums.get(min).get(next[min]));
            }
        }
        return new int[]{minx, miny};
    }
}
