package amazon;

import java.util.*;

// Top K Frequent Elements
public class Q347 {
    // O(n) O(n)
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> fre = new HashMap<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int max = 0;
        for(int num : nums) {
            fre.put(num, fre.getOrDefault(num, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : fre.entrySet()) {
            max = Math.max(max, entry.getValue());
            map.computeIfAbsent(entry.getValue(), x -> new ArrayList<>()).add(entry.getKey());
        }
        for(int i = max; i >= 0 && k > 0; i--) {
            if(map.containsKey(i)) {
                for(int val : map.get(i)) {
                    if(k - 1 >= 0) {
                        res[--k] = val;
                    } else {
                        break;
                    }
                }
            }
        }
        return res;
    }
    // O(nlgk) O(n+k)
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> fre = new HashMap<>();
        for(int num : nums) {
            fre.put(num, fre.getOrDefault(num, 0) + 1);
        }
        int[] res = new int[k];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> fre.get(a) - fre.get(b));
        for(int key : fre.keySet()) {
            pq.offer(key);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        while(!pq.isEmpty()) {
            res[--k] = pq.poll();
        }
        return res;
    }
    // quick select : T(n) = T(n/2) + n => O(n) in average
    Random random = new Random();
    Map<Integer, Integer> fre = new HashMap<>();
    public int[] topKFrequent2(int[] nums, int k) {
        for(int num : nums) {
            fre.put(num, fre.getOrDefault(num, 0) + 1);
        }
        int size = fre.size(), i = 0;
        int[] unique = new int[size];
        for(int key : fre.keySet()) {
            unique[i++] = key;
        }
        quickselect(unique, 0, size - 1, size - k);
        return Arrays.copyOfRange(unique, size - k, size);
    }
    private void quickselect(int[] arr, int l, int r, int target) {
        if(l == r) {
            return;
        }
        int pivot = partition(arr, l, r);
        if(pivot == target) return;
        else if(pivot > target) {
            quickselect(arr, l, pivot - 1, target);
        } else {
            quickselect(arr, pivot + 1, r, target);
        }
    }
    private int partition(int[] arr, int l, int r) {
        int k = random.nextInt(r - l + 1) + l;
        swap(arr, l, k);
        int m = arr[l];
        int val = fre.get(m);
        while(l < r) {
            while(l < r && fre.get(arr[r]) >= val) {
                r--;
            }
            arr[l] = arr[r];
            while(l < r && fre.get(arr[l]) < val) {
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = m;
        return l;
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
