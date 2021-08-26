package amazon;

import java.util.*;

// Find K Closest Elements
// next : Q719(H)
public class Q658 {
    // O(nlgk + klgk) O(k)
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (
                Math.abs(arr[a] - x) - Math.abs(arr[b] - x) == 0 ? arr[b] - arr[a] : Math.abs(arr[b] - x) - Math.abs(arr[a] - x)
        )); // can't use Math.abs(arr[b] - x) - Math.abs(arr[a] - x) only
        for(int i = 0; i < arr.length; i++) {
            pq.offer(i);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        while(!pq.isEmpty()) {
            list.add(arr[pq.poll()]);
        }
        Collections.sort(list);
        return list;
    }
    // O(nlgn + klgk) O(k)
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        for(int val : arr) {
            list.add(val);
        }
        Collections.sort(list, (a,b) -> Math.abs(a - x) - Math.abs(b - x));
        list = list.subList(0, k);
        Collections.sort(list);
        return list;
    }
    // Binary Search To Find The Left Bound : O(lgk + k) O(k)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new LinkedList<>();
        int l = 0, r = arr.length;
        r -= k;
        while(l < r) {
            int mid = l + (r - l) / 2;
            if(arr[mid] >= x) {
                r = mid;
            }else {
                if(x - arr[mid] <= arr[mid + k] - x) {
                    r = mid;
                }else {
                    l = mid + 1;
                }
            }
        }
        for(int i = 0; i < k; i++) {
            list.add(arr[i + l]);
        }
        return list;
    }
}
