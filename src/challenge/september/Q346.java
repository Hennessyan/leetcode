package challenge.september;

import java.util.ArrayDeque;
import java.util.Deque;

// Moving Average from Data Stream
public class Q346 {
    // O(1) - amortize to 1
    // O(size)
    /*
    class MovingAverage {
        int[] arr;
        long total;
        int index, len;
        public MovingAverage(int size) {
            arr = new int[size];
            total = 0;
            index = 0;
            len = 0;
        }

        public double next(int val) {
            if(len < arr.length) {
                len++;
            }
            total -= arr[index];
            total += val;
            arr[index] = val;
            index = (index + 1) % arr.length;
            return (double) total / len;
        }
    }
    */
    // Deques support thread-safe, memory efficient appends and pops from either side of
    // the deque with approximately the same O(1) performance in either direction.
    class MovingAverage {
        int size;
        long total;
        Deque<Integer> deque;
        public MovingAverage(int size) {
            deque = new ArrayDeque<>();
            total = 0;
            this.size = size;
        }

        public double next(int val) {
            if(deque.size() == this.size) {
                total -= deque.poll();  //head
            }
            total += val;
            deque.offer(val);   // tail
            return (double) total / deque.size();
        }
    }
}
