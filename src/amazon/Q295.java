package amazon;

import java.util.Collections;
import java.util.PriorityQueue;

// Find Median from Data Stream
public class Q295 {
    // O(5lgn) O(n)
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
}
