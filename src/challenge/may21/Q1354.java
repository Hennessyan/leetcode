package challenge.may21;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

// Construct Target Array With Multiple Sums
public class Q1354 {
    // O(n + klgn) O(n) - TLE
    // k - maximum value of target -> [1, 10000000000]
    public boolean isPossible1(int[] target) {
        if(target.length == 1) {
            return target[0] == 1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int totalSum = Arrays.stream(target).sum();
        for(int num : target) {
            pq.offer(num);  // 1 <= num <= 1e9
        }
        while(pq.element() > 1) {   // throw exception if queue is empty, but peek() not.
            int largest = pq.remove();
            int x = largest - (totalSum - largest);
            if(x < 1) return false;
            pq.offer(x);
            totalSum -= largest - x;
        }
        return true;
    }
    // O(n + lgk * lgn) O(n)
    // k - maximum value of target, totalSum in first round is almost but less than 2*k
    // each round we deduct k, because the rest can't add up to more than k => lgK steps we need.
    public boolean isPossible(int[] target) {

        // Handle the n = 1 case.
        if (target.length == 1) {
            return target[0] == 1;
        }

        int totalSum = 0;   //Arrays.stream(target).sum();

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int num : target) {
            totalSum += num;
            pq.add(num);
        }

        while (pq.element() > 1) {
            int largest = pq.remove();
            int rest = totalSum - largest;
            // this will only occur if n = 2
            if(rest == 1) {
                return true;
            }
            int x = largest % rest;
            if(x == 0 || x == largest) {    // largest always greater than half totalSum => largest > rest
                return false;
            }
            pq.offer(x);
            totalSum -= largest - x;
        }

        return true;
    }
}
