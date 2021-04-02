package amazon;

import java.util.ArrayList;
import java.util.List;

// Insert Interval
public class Q57 {
    // O(n) O(n)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0, len = intervals.length;
        List<int[]> list = new ArrayList<>();
        while(i < len && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i++]);
        }
        while(i < len && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        list.add(newInterval);
        while(i < len) {
            list.add(intervals[i++]);
        }
        return list.toArray(new int[list.size()][]);
    }

    /*method2*/
    //TC & SC: O(n)
    /*public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> list = new ArrayList<>();
        if(intervals == null || intervals.size() == 0){
            if(newInterval != null){
                list.add(newInterval);
            }
            return list;
        }
        //根据start的值来决定插入的位置
        int j = 0;
        while(j < intervals.size() && intervals.get(j).start < newInterval.start){
            j++;
        }
        intervals.add(j, newInterval);
        //merge, same as Q56
        Interval cur = intervals.get(0);
        for(int i = 1; i < intervals.size(); i++){
            Interval tmp = intervals.get(i);
            if(cur.end >= tmp.start){
                cur.end = Math.max(cur.end, tmp.end);
            }else{
                list.add(cur);
                cur = tmp;
            }
        }
        list.add(cur);
        return list;
    }*/
}
