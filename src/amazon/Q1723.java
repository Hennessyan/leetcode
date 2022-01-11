package amazon;

import java.util.*;

// Find Minimum Time to Finish All Jobs
// Q698
public class Q1723 {

    int ans;
    public int minimumTimeRequired(int[] jobs, int k) {
        List<Integer> list = new ArrayList<>(); // can't use Arrays.asList() directly.
        for(int job : jobs) {
            list.add(job);
        }
        Collections.sort(list, (a, b) -> b - a);
        int[] count = new int[k];
        ans = Integer.MAX_VALUE;
        backtrack(list, 0, count);
        return ans;
    }
    private void backtrack(List<Integer> list, int i, int[] count) {
        if(i == list.size()) {
            ans = Math.min(ans, getMax(count));
            return;
        }
        Set<Integer> seen = new HashSet<>();
        for(int j = 0; j < count.length; j++) {
            // worker[] {10, 5, 5, 5, 5} => no need to add job to worker has same value already
            if(seen.contains(count[j])) continue;
            if(count[j] + list.get(i) >= ans) continue;
            seen.add(count[j]); // must before adding new job value (before L31)
            count[j] += list.get(i);
            backtrack(list, i + 1, count);
            count[j] -= list.get(i);
        }
    }
    private int getMax(int[] count) {
        int max = Integer.MIN_VALUE;
        for(int c : count) {
            max = Math.max(max, c);
        }
        return max;
    }
}
