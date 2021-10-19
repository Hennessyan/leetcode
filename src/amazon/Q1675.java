package amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// Minimize Deviation in Array
public class Q1675 {

    // even can divide by 2 => even can decrease => even can't increase
    // odd can multiple by 2 => odd can increase => odd can't decrease

    // m - maximum num in nums
    // n - length of nums
    // k = nlgm - frequency
    // lgn - sort time

    // O(k * lgn) O(n + k)
    // Q632(H) Smallest Range Covering Elements from K Lists

    public int minimumDeviation(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = null;
        int max = 0, n = nums.length, minDev = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            list = new ArrayList<>();
            if(nums[i] % 2 == 0) {
                list.add(nums[i]);
                while(nums[i] % 2 == 0) {
                    nums[i] /= 2;
                    list.add(nums[i]);
                }
                Collections.sort(list);
            } else {
                list.add(nums[i]);
                list.add(nums[i] * 2);
            }
            lists.add(list);
            max = Math.max(max, lists.get(i).get(0));
        }
        int[] index = new int[n];
        // order is important, must poll min.
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) ->
                lists.get(a).get(index[a]) - lists.get(b).get(index[b]));
        for(int i = 0; i < n; i++) {
            pq.offer(i);
        }
        while(!pq.isEmpty()) {
            int min = pq.poll();
            List<Integer> tmp = lists.get(min);
            minDev = Math.min(minDev, max - tmp.get(index[min]));
            if(++index[min] == tmp.size()) {
                break;
            }
            pq.offer(min);
            max = Math.max(max, tmp.get(index[min]));
        }
        return minDev;
    }

    // O(n * lgm * lgn) O(n)
    public int minimumDeviation1(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        int min = Integer.MAX_VALUE, minDev = min;
        // as we get all max num first, must poll max.
        for(int num : nums) {
            if(num % 2 == 1) {
                num *= 2;
            }
            min = Math.min(min, num);
            pq.offer(num);
        }

        while(!pq.isEmpty()) {
            int max = pq.poll();
            minDev = Math.min(minDev, max - min);
            if(max % 2 == 0) {
                max /= 2;
                min = Math.min(min, max);
                pq.offer(max);
            } else {
                break;  // not max diff if we go to next round.
            }
        }
        return minDev;
    }
}
