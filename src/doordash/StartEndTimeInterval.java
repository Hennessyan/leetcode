package doordash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartEndTimeInterval {

    class Time {
        int day = 0;
        int hours = 0;
        int mins = 0;
        boolean am = false;
        public Time(int day, int hours, int mins, boolean am) {
            this.day = day;
            this.hours = hours;
            this.mins = mins;
            this.am = am;
        }

        public void add(int min) {
            hours += (mins + min) / 60;
            mins = (mins + min) % 60;
            if(hours == 12) {
                am = !am;
                hours = 0;
                if(am) {
                    day = (day + 1) % 7;
                }
            }
        }

        public int getNumeric() {
            int addHour = hours;
            if(format.equals("24 HOUR")) {
                addHour = am ? hours : hours + 12;
            }
            return (day * 100 + addHour) * 100 + mins;
        }

        public boolean equals(Time that) {
            return this.day == that.day && this.hours == that.hours &&
                    this.mins == that.mins && Boolean.compare(this.am, that.am) == 0;
        }
    }

    public static void main(String[] args) {
        StartEndTimeInterval q = new StartEndTimeInterval();

        // check the 12:10 pm or 00:10 pm
        List<Integer> res = q.getIntervals("mon 11:00 am", "mon 00:10 pm");
        for(int num : res) {
            System.out.println(num);
        }
    }

    Map<String, Integer> map = new HashMap<>();
    String format;
    public List<Integer> getIntervals(String start, String end) {
        List<Integer> res = new ArrayList<>();
        initialMap();
        format = "24 HOUR";
        Time s = convert(start);
        Time e = convert(end);
        while(!s.equals(e)) {
            res.add(s.getNumeric());
            s.add(5);
        }
        res.add(e.getNumeric());
        return res;
    }

    private Time convert(String str) {
        String[] strs = str.split(" ");
        String[] tmp = strs[1].split(":");
        return new Time(map.get(strs[0]), Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), strs[2].equals("am"));
    }

    private void initialMap() {
        map.put("mon", 1);
        map.put("tue", 2);
        map.put("wed", 3);
        map.put("thu", 4);
        map.put("fri", 5);
        map.put("sat", 6);
        map.put("sun", 7);
    }
}
