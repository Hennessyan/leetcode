package google;

import java.util.TreeMap;

// My Calendar I
public class Q729 {
    // O(nlgn) O(n)
    class MyCalendar {
        TreeMap<Integer, Integer> treemap;
        public MyCalendar() {
            treemap = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer floor = treemap.floorKey(start);
            Integer ceil = treemap.ceilingKey(start);
            if((floor == null || treemap.get(floor) <= start) && (ceil == null || ceil >= end)) {
                treemap.put(start, end);
                return true;
            }
            return false;
        }
    }
    // O(n^2) O(n)
//    class MyCalendar {
//        List<int[]> calendar;
//
//        MyCalendar() {
//            calendar = new ArrayList();
//        }
//
//        public boolean book(int start, int end) {
//            for (int[] iv: calendar) {
//                if (iv[0] < end && start < iv[1]) return false;       //注意学习这个判断条件
//            }
//            calendar.add(new int[]{start, end});
//            return true;
//        }
//    }
}
