package amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// Employee Free Time
public class Q759 {
    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start,int _end) {
            start = _start;
            end = _end;
        }
    }
    // method1: O(2clg2c) O(c)
    public List<Interval> employeeFreeTime1(List<List<Interval>> schedule) {
        List<int[]> events = new ArrayList<>();
        for(List<Interval> intervals : schedule) {
            for(Interval interval : intervals) {
                events.add(new int[]{interval.start, 1});
                events.add(new int[]{interval.end, -1});
            }
        }
        Collections.sort(events, (e1, e2) -> e1[0] != e2[0] ? e1[0] - e2[0] : e2[1] - e1[1]);
        List<Interval> res = new ArrayList<>();
        int prev = -1, balance = 0;
        for(int[] event : events) {
            if(prev != -1 && balance == 0) {
                res.add(new Interval(prev, event[0]));
            }
            balance += event[1];
            prev = event[0];
        }
        return res;
    }
    // method2: O(clgn) O(n) - n - # of employees, c - # of intervals
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<Job> pq = new PriorityQueue<>((j1, j2) ->
                schedule.get(j1.e).get(j1.i).start - schedule.get(j2.e).get(j2.i).start);
        List<Interval> res = new ArrayList<>();
        int start = Integer.MAX_VALUE;
        for(int i = 0; i < schedule.size(); i++) {
            start = Math.min(start, schedule.get(i).get(0).start);
            pq.offer(new Job(i, 0));
        }

        while(!pq.isEmpty()) {
            Job job = pq.poll();
            Interval interval = schedule.get(job.e).get(job.i);
            int tmp_start = interval.start;
            if(start < tmp_start) {
                res.add(new Interval(start, tmp_start));
            }
            start = Math.max(start, interval.end);
            if(++job.i < schedule.get(job.e).size()) {
                pq.offer(job);
            }
        }
        return res;
    }
    class Job {
        int e, i;
        public Job(int e, int i) {
            this.e = e; // index of employee
            this.i = i; // index of employee work interval
        }
    }
}
