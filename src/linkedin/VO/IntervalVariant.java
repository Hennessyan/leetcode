package linkedin.VO;

import java.util.ArrayList;
import java.util.List;

public class IntervalVariant {

    public static void main(String[] args) {
        IntervalVariant q = new IntervalVariant();
        System.out.println(q.insert(new int[]{4, 8}));
    }

    List<int[]> intervals;
    public IntervalVariant() {
        intervals = new ArrayList<>();
        intervals.add(new int[]{1,2});
        intervals.add(new int[]{3,5});
        intervals.add(new int[]{6,7});
        intervals.add(new int[]{8,10});
        intervals.add(new int[]{12,16});
    }

    public int insert(int[] newInterval) {  // [4,8]
        List<int[]> tmp = new ArrayList<>();
        int i = 0, n = intervals.size(), overlap = 0;
        while(i < n && intervals.get(i)[1] < newInterval[0]) {
            tmp.add(intervals.get(i++));
        }
        while(i < n && newInterval[1] >= intervals.get(i)[0]) {
            int[] it = intervals.get(i++);
            overlap += Math.min(newInterval[1], it[1]) - Math.max(newInterval[0], it[0]) + 1;
            newInterval[0] = Math.min(newInterval[0], it[0]);
            newInterval[1] = Math.max(newInterval[1], it[1]);
        }
        tmp.add(newInterval);
        while(i < n) {
            tmp.add(intervals.get(i++));
        }
        intervals = tmp;
        System.out.println("------");
        for(int[] val : intervals)
            System.out.println(val[0] + " " + val[1]);
        System.out.println("------");
        return overlap;
    }

}
