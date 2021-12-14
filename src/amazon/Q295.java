package amazon;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

// Find Median from Data Stream
public class Q295 {
    // idea1 : sort whole list
    // idea2 : binary search to find insert position, then insert it (O(n+lgn) O(n)) - TLE
//    class MedianFinder {
//
//        List<Integer> list;
//        /** initialize your data structure here. */
//        public MedianFinder() {
//            list = new LinkedList<>();
//        }
//
//        public void addNum(int num) {
//            if(list.isEmpty()) {
//                list.add(0, num);
//            } else {
//                int index = Collections.binarySearch(list, num);
//                if(index < 0) {
//                    index = -(index + 1);
//                }
//                list.add(index, num);
//            }
//        }
//
//        public double findMedian() {
//            int n = list.size();
//            return n % 2 == 1 ? (double) list.get(n / 2) : (list.get(n / 2) + list.get(n / 2 - 1)) * 0.5;
//        }
//    }

    // idea3 : O(5lgn) O(n)
    class MedianFinder {

        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;
        /** initialize your data structure here. */
        public MedianFinder() {
            minHeap = new PriorityQueue<>();    // save bigger half
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());    // save smaller half
        }

        public void addNum(int num) {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
            if(maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            return maxHeap.size() > minHeap.size() ? (double) maxHeap.peek() : (minHeap.peek() + maxHeap.peek()) * 0.5;
        }
    }
    // follow-ups
    // https://leetcode.com/problems/find-median-from-data-stream/discuss/275207/Solutions-to-follow-ups
}
