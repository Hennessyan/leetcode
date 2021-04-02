package amazon;

import java.util.Stack;

// Largest Rectangle in Histogram
public class Q84 {

    //brute force : O(n^3) O(1) - 忽略这个方法,不好.
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        for(int i = 0; i < heights.length; i++) {
            for(int j = i; j < heights.length; j++) {
                int h = Integer.MAX_VALUE;
                for(int k = i; k <= j; k++) {
                    h = Math.min(h, heights[k]);
                }
                max = Math.max(max, h * (j - i + 1));
            }
        }
        return max;
    }
    //better brute force : O(n^2) O(1)
    public int largestRectangleArea1(int[] heights) {
        int max = 0;
        for(int i = 0; i < heights.length; i++) {
            int h = Integer.MAX_VALUE;	//注意这里h不是0
            for(int j = i; j < heights.length; j++) {
                h = Math.min(h, heights[j]);
                max = Math.max(max, h * (j - i + 1));
            }
        }
        return max;
    }
    //Divide and Conquer :
    //TC : O(nlgn) -> O(n^2) - worst case : array is sorted.
    //SC : O(lgn) -> O(n)	recursion
    public int largestRectangleArea2(int[] heights) {
        return help(heights, 0, heights.length - 1);
    }
    private int help(int[] heights, int i, int j) {
        if(i > j) {
            return 0;
        }
        int minIndex = i;
        for(int k = i; k <= j; k++) {
            if(heights[minIndex] > heights[k]) {
                minIndex = k;
            }
        }
        return Math.max(heights[minIndex] * (j - i + 1), Math.max(help(heights, i, minIndex - 1), help(heights, minIndex + 1, j)));
    }
    //Stack : O(n) O(n)
    public int largestRectangleArea3(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;	//注意初始化为0
        for(int i = 0; i < heights.length; i++) {
            while(stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
                max = Math.max(max, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while(stack.peek() != -1) {
            max = Math.max(max, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return max;
    }
}
