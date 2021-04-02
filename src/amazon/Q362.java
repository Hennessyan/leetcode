package amazon;

import java.util.LinkedList;
import java.util.Queue;

// Design Hit Counter
public class Q362 {

    class HitCounter {
        private Queue<Integer> hits;

        /**
         * Initialize your data structure here.
         */
        public HitCounter() {
            this.hits = new LinkedList<Integer>();
        }

        /**
         * Record a hit.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            this.hits.add(timestamp);
        }

        /**
         * Return the number of hits in the past 5 minutes.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public int getHits(int timestamp) {
            while (!this.hits.isEmpty()) {
                int diff = timestamp - this.hits.peek();
                if (diff >= 300) this.hits.remove();
                else break;
            }
            return this.hits.size();
        }
    }
    // better than method1 as we don't record duplicates timestamps.
//    class HitCounter {
//
//        int total = 0;
//        Deque<int[]> deque;
//        /** Initialize your data structure here. */
//        public HitCounter() {
//            deque = new LinkedList<>();
//        }
//
//        /** Record a hit.
//         @param timestamp - The current timestamp (in seconds granularity). */
//        public void hit(int timestamp) {
//            if(deque.isEmpty() || deque.getLast()[0] != timestamp) {
//                deque.add(new int[]{timestamp, 1});
//            } else {
//                int[] tmp = deque.removeLast();
//                tmp[1]++;
//                deque.add(tmp);
//            }
//            this.total++;
//        }
//
//        /** Return the number of hits in the past 5 minutes.
//         @param timestamp - The current timestamp (in seconds granularity). */
//        public int getHits(int timestamp) {
//            while(!deque.isEmpty() && timestamp - deque.getFirst()[0] >= 300) {
//                total -= deque.removeFirst()[1];
//            }
//            return total;
//        }
//    }

}
