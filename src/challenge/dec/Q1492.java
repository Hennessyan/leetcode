package challenge.dec;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// The kth Factor of n
public class Q1492 {

    public static void main(String[] args) {
        Q1492 q = new Q1492();
        System.out.println(q.kthFactor(1, 1));  // 1
    }
    // brute force : O(n) O(1)
    public int kthFactor0(int n, int k) {
        for(int i = 1; i < n / 2 + 1; i++) {
            if(n % i == 0) {
                if(--k == 0) {
                    return i;
                }
            }
        }
        return k == 1 ? n : -1;
    }
    // max-heap: O(sqrt(n) * lgn) O(min(sqrt(n), k)
    private PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    public int kthFactor1(int n, int k) {
        for(int x = 1; x <= (int) (Math.sqrt(n)); x++) {
            if(n % x == 0) {
                addQueue(x, k);
                if(x != n / x) {
                    addQueue(n / x, k);
                }
            }
        }
        return heap.size() == k ? heap.peek() : -1;
    }
    private void addQueue(int val, int k) {
        heap.add(val);
        if(heap.size() > k) {
            heap.poll();
        }
    }

    // O(sqrt(n)) O(min(sqrt(n), k)
    public int kthFactor(int n, int k) {
        List<Integer> divisors = new ArrayList();
        int sqrtN = (int) Math.sqrt(n);
        for (int x = 1; x < sqrtN + 1; ++x) {
            if (n % x == 0) {
                --k;
                divisors.add(x);
                if (k == 0) {
                    return x;
                }
            }
        }

        // If n is a perfect square
        // we have to skip the duplicate
        // in the divisor list
        if (sqrtN * sqrtN == n) {       // 1, 2, (2), 4
            ++k;
        }

        int nDiv = divisors.size(); // half of refactor number
        return (k <= nDiv) ? n / divisors.get(nDiv - k) : -1;
    }

    // 1,  2,    4,   5,  8,  10,   20,  25,
    // 40, 50, 100, 125, 200, 250, 500, 1000

}
