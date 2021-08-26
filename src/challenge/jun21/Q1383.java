package challenge.jun21;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// Maximum Performance of a Team
// Q857 Q215
public class Q1383 {
    // O(nlgn+nlgk) O(n+k)
    // Performance depends on the sum of speed and efficiency, we need to "fix" one variable and go through them based on another one.
    // 我们无法在固定speed的同时来控制efficiency. 因为speed由多人决定,没办法固定它. 但是efficiency可以固定,并且使得speed成为唯一变化的决定因素.
    //
    // Set minimum efficiency, we can try to add as many engineers as possible to get local optimal result =>
    // choose engineer who has highest speed =>
    // Use reverse order can make sure always get minimum efficiency easily (L28) =>
    // When we traverse the list, new efficiency is always "fixed" variable, then we just need to consider how to choose the speed =>
    // Then the answer is obvious => choose and remove minimum speed !
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(new Pair(speed[i], efficiency[i]));
        }

        Collections.sort(list, (l1, l2) -> l2.getValue() - l1.getValue());
        long perf = 0l, sum = 0l;
        long modulo = (long) (1e9 + 7);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Pair<Integer, Integer> p : list) {
            int s = p.getKey();
            int e = p.getValue();
            if(pq.size() == k) {
                sum -= pq.poll();
            }
            sum += s;
            perf = Math.max(perf, sum * e);
            pq.offer(s);
        }
        return (int) (perf % modulo);
    }
}
