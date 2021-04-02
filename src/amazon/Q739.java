package amazon;

import java.util.ArrayDeque;
import java.util.Deque;

// Daily Temperatures
public class Q739 {
    // O(n) O(n)
    public int[] dailyTemperatures(int[] T) {
        if(T == null || T.length == 0) {
            return T;
        }
        int len = T.length;
        int[] res = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < len; i++) {
            while(!stack.isEmpty() && T[stack.peek()]  < T[i]) {
                int j = stack.pop();
                res[j] = i - j;
            }
            stack.push(i);
        }
        return res;
    }

    public int[] dailyTemperatures1(int[] T) {
        int[] ans = new int[T.length];
        Deque<Integer> stack = new ArrayDeque();
        for (int i = T.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }
}
