package amazon;

import java.util.ArrayDeque;
import java.util.Deque;

// Trapping Rain Water
public class Q42 {

    // DP : O(N) O(N)
    public int trap1(int[] height) {
        if(height == null || height.length < 3) {
            return 0;
        }
        int n = height.length;
        int[] l = new int[n], r = new int[n];
        l[0] = height[0];
        r[n - 1] = height[n - 1];
        for(int i = 1; i < n; i++) {
            l[i] = Math.max(height[i], l[i - 1]);
            r[n - i - 1] = Math.max(height[n - i - 1], r[n - i]);
        }
        int ans = 0;
        for(int i = 1; i < n - 1; i++) {
            ans += Math.min(l[i], r[i]) - height[i];
        }
        return ans;
    }
    // stack : O(N) O(N)
    public int trap2(int[] height) {
        if(height == null || height.length < 3) {
            return 0;
        }
        int n = height.length;
        int ans = 0, i = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        while(i < n) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int cur = stack.pop();
                if(stack.isEmpty()) {
                    break;
                }
                int width = i - stack.peek() - 1;
                ans += width * (Math.min(height[i], height[stack.peek()]) - height[cur]);
            }
            stack.push(i++);
        }
        return ans;
    }

    //O(n)	O(1)		one path
    public int trap3(int[] height) {
        int maxL = 0, maxR = 0;
        int l = 0, r = height.length - 1;
        int ans = 0;
        while(l < r){
            if(height[l] < height[r]){
                if(height[l] >= maxL){
                    maxL = height[l];
                }else{
                    ans += maxL - height[l];
                }
                l++;
            }else{
                if(height[r] >= maxR){
                    maxR = height[r];
                }else{
                    ans += maxR - height[r];
                }
                r--;
            }
        }
        return ans;
    }

}
