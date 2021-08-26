package google;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

// Jump Game VI
public class Q1696 {
    // similar to Q239
    // method1: O(n) O(n)
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        if(n * k == 0) return 0;

        int[] score = new int[n];
        score[0] = nums[0];
        Deque<Integer> queue = new LinkedList<>();
        queue.add(0);

        // int min = Math.min(n - 1, k);
        for(int i = 1; i < n; i++) {
            score[i] = nums[i] + score[queue.peek()];
            shift(queue, i, score, k);
            queue.add(i);
        }
        return score[n - 1];

    }
    private void shift(Deque<Integer> queue, int i, int[] score, int k) {
        if(!queue.isEmpty() && i - queue.peek() == k) {
            queue.removeFirst();
        }
        while(!queue.isEmpty() && score[queue.getLast()] <= score[i]) {
            queue.removeLast();
        }
    }
    // save space for method1: O(n) O(k)
    public int maxResult01(int[] nums, int k) {
        int n = nums.length, score = nums[0];
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{score, 0});

        for(int i = 1; i < n; i++) {
            if(dq.peekFirst() != null && dq.peekFirst()[1] < i - k) {
                dq.removeFirst();
            }
            score = dq.peekFirst()[0] + nums[i];
            while(dq.peekLast() != null && dq.peekLast()[0] <= score) {
                dq.removeLast();
            }
            dq.offer(new int[]{score, i});
        }
        return score;
    }


    // method2: O(nlgn) O(n) - if n == k in worst case
    public int maxResult1(int[] nums, int k) {
        int n = nums.length;
        int[] score = new int[n];
        score[0] = nums[0];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        priorityQueue.add(new int[] { nums[0], 0 });
        for (int i = 1; i < n; i++) {
            // pop the old index
            while (priorityQueue.peek()[1] < i - k) {   // while rather than if, different with method1
                priorityQueue.remove();
            }
            score[i] = nums[i] + priorityQueue.peek()[0];
            priorityQueue.add(new int[] { score[i], i });
        }
        return score[n - 1];
    }
    // improvement of method2, but still O(n) space in worst case
    public int maxResult11(int[] nums, int k) {
        int n = nums.length;
        int score = nums[0];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        priorityQueue.add(new int[] { nums[0], 0 });
        for (int i = 1; i < n; i++) {
            // pop the old index
            while (priorityQueue.peek()[1] < i - k) {
                priorityQueue.remove();
            }
            score = nums[i] + priorityQueue.peek()[0];
            priorityQueue.add(new int[] { score, i });
        }
        return score;
    }

    //segment tree : O(nlgn) O(n)
    public int maxResult2(int[] nums, int k) {
        int n = nums.length;
        int[] tree = new int[2 * n];
        update(0, nums[0], tree, n);
        for (int i = 1; i < n; i++) {
            int maxi = query(Math.max(0, i - k), i, tree, n);
            update(i, maxi + nums[i], tree, n);
        }
        return tree[2 * n - 1];
    }

    // implement Segment Tree
    private void update(int index, int value, int[] tree, int n) {
        index += n;
        tree[index] = value;
        for (index >>= 1; index > 0; index >>= 1) {
            tree[index] = Math.max(tree[index << 1], tree[(index << 1) + 1]);
        }
    }

    private int query(int left, int right, int[] tree, int n) {
        int result = Integer.MIN_VALUE;
        for (left += n, right += n; left < right; left >>= 1, right >>= 1) {
            if ((left & 1) == 1) {
                result = Math.max(result, tree[left++]);
            }
            if ((right & 1) == 1) {
                result = Math.max(result, tree[--right]);
            }
        }
        return result;
    }
}
