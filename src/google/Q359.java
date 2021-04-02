package google;

import java.util.HashMap;
import java.util.Map;

// Logger Rate Limiter
public class Q359 {

    //O(n) O(n)
    class Logger {

        private Map<String, Integer> map;

        /** Initialize your data structure here. */
        public Logger() {
            map = new HashMap<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            if (map.containsKey(message) && (timestamp - map.get(message)) < 10) {
                return false;
            }
            map.put(message, timestamp);
            return true;
        }
    }
    // O(1) O(n)
    //https://leetcode.com/problems/logger-rate-limiter/discuss/83284/A-solution-that-only-keeps-part-of-the-messages
//Should use this solution in the interview.
//Timestamp increase, so we can avoid using PriorityQueue.
//Load of while can be amortized in each operation.
//public class Logger {
//    Queue<Tuple> q = new ArrayDeque<>();
//    Set<String> dict = new HashSet<>();
//
//    public Logger() {}
//
//    public boolean shouldPrintMessage(int timestamp, String message) {
//        while (!q.isEmpty() && q.peek().t <= timestamp - 10) {
//            Tuple next = q.poll();
//            dict.remove(next.msg);
//        }
//        if (!dict.contains(message)) {
//            q.offer(new Tuple(timestamp, message));
//            dict.add(message);
//            return true;
//        }
//        return false;
//    }
//    private static class Tuple {
//        int t;
//        String msg;
//        public Tuple(int t, String msg) {
//            this.t = t;
//            this.msg = msg;
//        }
//    }
//}
}
