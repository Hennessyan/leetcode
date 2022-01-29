package doordash;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Throttle {
    /*
    1s - 3
    10s - 20
    60s - 60
     */
    public static void main(String[] args) {
        int[] reqs = {1,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7, 7,7,7,11,11,11,11};
        System.out.println(throttle(reqs));
        System.out.println(throttle2(reqs));
    }

    public static int throttle(int[] reqs) {
        int ans = 0;
        for(int i = 0; i < reqs.length; i++) {
            if(i > 2 && (reqs[i] - reqs[i - 3]) < 1) {
                System.out.println("here1: " + i);
                ans++;
            } else if(i > 19 && reqs[i] - reqs[i - 20] < 10) {
                System.out.println("here2: " + i);
                ans++;
            } else if(i > 59 && reqs[i] - reqs[i - 60] < 60) {
                ans++;
            }
        }
        return ans;
    }

    static int ans;
    public static int throttle2(int[] arr) {
        Deque<Integer> oneSecQueue = new LinkedList<>();    // 3
        Deque<Integer> tenSecQueue = new LinkedList<>();    // 20
        Deque<Integer> oneMinQueue = new LinkedList<>();    // 60
        ans = 0;
        int one = 1, ten = 10, sixty = 60;
        for(int i = 0; i < arr.length; i++) {
            int val = arr[i];
            boolean a = helper(oneSecQueue, one, 3, val, i);
            boolean b = helper(tenSecQueue, ten, 20, val, i);
            boolean c = helper(oneMinQueue, sixty, 60, val, i);
            if(a || b || c) ans++;
        }
        return ans;
    }
    private static boolean helper(Deque<Integer> queue, int threshold, int size, int req, int i) {
        while(!queue.isEmpty() && (req - queue.getFirst()) >= threshold) {
            queue.removeFirst();
        }
        if(queue.size() < size) {
            queue.addLast(req);
            return false;
        } else {
            System.out.println(i + " " + threshold + " " + size + " " + req);
            return true;
        }
    }
}
