package amazon;

import java.util.*;

// Maximum Profit in Job Scheduling
// Q300
public class Q1235 {
    // O(nlgn) O(n)
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[] memo = new int[50001];
        int n = startTime.length;
        // comparator needs T, so Integer ranther than int.
        Integer[] index = new Integer[n];
        for(int i = 0; i < n; i++) {
            index[i] = i;
        }
        Arrays.sort(index, (i1, i2) -> endTime[i1] - endTime[i2]);
        // sort endTime, traverse from i = 0.
        // vice verse => sort startTime, traverse from i = n - 1.
        for(int i = 0; i < n; i++) {
            // sort endTime, then we need to binary search startTime from endTime
            // vice verse => sort startTime, then binary search endTime from startTime.
            int pre = findPreValidJob(index, endTime, startTime[index[i]]);

            int curProfit = profit[index[i]];
            if(pre != -1) {
                curProfit += memo[pre + 1]; // here is pre + 1 as memo is 1-index based.
            }
            memo[i + 1] = Math.max(memo[i], curProfit);
        }
        return memo[n];
    }
    private int findPreValidJob(Integer[] index, int[] end, int s) {
        int l = 0, r = index.length - 1, res = -1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(end[index[m]] <= s) {
                res = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res;
    }

    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        List<List<Integer>> jobs = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            jobs.add(Arrays.asList(startTime[i], endTime[i], profit[i]));
        }
        //jobs.sort(Comparator.comparingInt(job -> job.get(0)));
        Collections.sort(jobs, (j1, j2) -> j1.get(0) - j2.get(0));
        return findProfit(jobs);
    }
    private int findProfit(List<List<Integer>> jobs) {
        int n = jobs.size(), max = 0;
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((l1, l2) -> l1.get(0) - l2.get(0));

        for(int i = 0; i < n; i++) {
            List<Integer> job = jobs.get(i);
            int s = job.get(0), e = job.get(1), p = job.get(2);
            while(!pq.isEmpty() && pq.peek().get(0) <= s) {
                max = Math.max(max, pq.poll().get(1));
            }
            pq.offer(Arrays.asList(e, p + max));
        }
        while(!pq.isEmpty()) {
            max = Math.max(max, pq.poll().get(1));
        }
        return max;
    }


    // sort startTime & memo is 0-index based.
    int memo[] = new int[50001];
    private int findNextJob(int[] startTime, int lastEndingTime) {
        int start = 0, end = startTime.length - 1, nextIndex = startTime.length;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (startTime[mid] >= lastEndingTime) {
                nextIndex = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return nextIndex;
    }
    private int findMaxProfit(List<List<Integer>> jobs, int[] startTime) {
        int length = startTime.length;

        for (int position = length - 1; position >= 0; position--) {
            int currProfit = 0;

            int nextIndex = findNextJob(startTime, jobs.get(position).get(1));

            if (nextIndex != length) {  // find nextIndex
                currProfit = jobs.get(position).get(2) + memo[nextIndex];
            } else {                    // does not find nextIndex
                currProfit = jobs.get(position).get(2);
            }

            if (position == length - 1) {
                memo[position] = currProfit;
            } else {
                memo[position] = Math.max(currProfit, memo[position + 1]);
            }
        }

        return memo[0];
    }

    public int jobScheduling1(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();

        int length = profit.length;
        for (int i = 0; i < length; i++) {
            ArrayList<Integer> currJob = new ArrayList<>();
            currJob.add(startTime[i]);
            currJob.add(endTime[i]);
            currJob.add(profit[i]);

            jobs.add(currJob);
        }

        jobs.sort(Comparator.comparingInt(a -> a.get(0)));

        for (int i = 0; i < length; i++) {
            startTime[i] = jobs.get(i).get(0);
        }

        return findMaxProfit(jobs, startTime);
    }
}
