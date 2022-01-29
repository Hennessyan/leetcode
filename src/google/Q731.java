package google;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// My Calendar II
public class Q731 {
    // O(n^2) O(n)

    class MyCalendarTwo {
        TreeMap<Integer, Integer> delta;

        public MyCalendarTwo() {
            delta = new TreeMap();
        }

        public boolean book(int start, int end) {
            delta.put(start, delta.getOrDefault(start, 0) + 1);
            delta.put(end, delta.getOrDefault(end, 0) - 1);

            int active = 0;
            for (int d: delta.values()) {
                active += d;
                if (active >= 3) {
                    delta.put(start, delta.get(start) - 1);
                    delta.put(end, delta.get(end) + 1);
                    if (delta.get(start) == 0)
                        delta.remove(start);
                    return false;
                }
            }
            return true;
        }
    }

    class MyCalendarTwo2 {
        TreeMap<Integer, Integer> delta;

        public MyCalendarTwo2() {
            delta = new TreeMap();
        }

        public boolean book(int start, int end) {
            delta.put(start, delta.getOrDefault(start, 0) + 1);
            delta.put(end, delta.getOrDefault(end, 0) - 1);

            int active = 0;
            for (Map.Entry<Integer, Integer> entry : delta.entrySet()) {
                active += entry.getValue();
                if (active >= 3) {
                    delta.put(start, delta.get(start) - 1);
                    delta.put(end, delta.get(end) + 1);
                    if (delta.get(start) == 0)
                        delta.remove(start);
                    return false;
                }
                if(entry.getKey() >= end) break; // pruning
            }
            return true;
        }
    }
    // brute force
    class MyCalendarTwo1 {
        List<int[]> f_calendar;
        List<int[]> s_calendar;
        public MyCalendarTwo1() {
            f_calendar = new ArrayList<>();
            s_calendar = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for(int[] p : s_calendar) {
                if(p[1] > start && p[0] < end) {
                    return false;
                }
            }

            for(int[] p : f_calendar) {
                if(p[1] > start && p[0] < end) {
                    s_calendar.add(new int[]{Math.max(start, p[0]), Math.min(end, p[1])});
                }
            }
            f_calendar.add(new int[]{start, end});
            return true;
        }
    }
}
