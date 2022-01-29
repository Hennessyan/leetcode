package linkedin;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// Exclusive Time of Functions
public class Q636 {
    // O(m) O(m) - without sorting.
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] times = new int[n];
        int m = logs.size();
        Log[] arr = new Log[m];
        for(int i = 0; i < m; i++) {
            arr[i] = convert(logs.get(i));
        }
        Arrays.sort(arr);   // ask if needed.
        Deque<int[]> stack = new ArrayDeque<>();    // [id, time, idle_time]
        for(int i = 0; i < m; i++) {
            if(arr[i].status == -1) {   // push if it's start log.
                stack.push(arr[i].toArray());
            } else {
                int[] peek = stack.pop();
                int cost = peek[2] + arr[i].time - peek[1] + 1;
                times[peek[0]] += cost;
                if(!stack.isEmpty()) {
                    stack.peek()[2] -= arr[i].time - peek[1] + 1;   // don't reuse previous idle time
                }
            }
        }
        return times;

    }
    private Log convert(String s) {
        String[] strs = s.split(":");
        return new Log(Integer.parseInt(strs[0]), strs[1].equals("start") ? -1 : 1, Integer.parseInt(strs[2]));
    }
    class Log implements Comparable<Log> {
        int id;
        int status; // start -> -1, end = 1
        int time;
        public Log(int id, int status, int time) {
            this.id = id;
            this.status = status;
            this.time = time;
        }

        @Override
        public int compareTo(Log that) {
            int cmp = this.time - that.time;
            if(cmp == 0) {
                return this.status - that.status;
            }
            return cmp;
        }
        public int[] toArray() {
            return new int[]{this.id, this.time, 0};
        }
    }

     // better
//    public int[] exclusiveTime(int n, List<String> logs) {
//        Deque<Log> stack = new ArrayDeque<>();
//        int[] result = new int[n];
//        for (String content : logs) {
//            Log log = new Log(content);
//            if (log.isStart) {
//                stack.push(log);
//            } else {
//                Log top = stack.pop();
//                result[top.id] += (log.time - top.time + 1);
//                if (!stack.isEmpty()) {
//                    result[stack.peek().id] -= (log.time - top.time + 1);
//                }
//            }
//        }
//
//        return result;
//    }
//
//    public static class Log {
//        public int id;
//        public boolean isStart;
//        public int time;
//
//        public Log(String content) {
//            String[] strs = content.split(":");
//            id = Integer.valueOf(strs[0]);
//            isStart = strs[1].equals("start");
//            time = Integer.valueOf(strs[2]);
//        }
//    }

}
