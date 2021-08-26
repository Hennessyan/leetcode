package amazon;

import java.util.Arrays;

// Candy
public class Q135 {
    //https://leetcode.com/problems/candy/solution/
    //Two arrays : O(N) O(N)
    public int candy(int[] ratings) {
        int sum = 0;
        int[] left2right = new int[ratings.length];
        int[] right2left = new int[ratings.length];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }
        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }
    //One Array : O(N) O(N)
    public int candy1(int[] ratings) {
        int len = ratings.length;
        int[] candidates = new int[len];
        Arrays.fill(candidates, 1);
        for(int i = 1; i< len; i++) {
            if(ratings[i] > ratings[i - 1]) {
                candidates[i] = candidates[i - 1] + 1;
            }
        }
        int res = candidates[len - 1];
        for(int i = len - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) {
                candidates[i] = Math.max(candidates[i], candidates[i + 1] + 1);
            }
            res += candidates[i];
        }
        return res;
    }
    //single pass : O(n) O(1)
    public int count(int n) {
        return (n * (n + 1)) / 2;
    }
    public int candy2(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }
        int candies = 0;
        int up = 0;
        int down = 0;
        int old_slope = 0;
        for (int i = 1; i < ratings.length; i++) {
            int new_slope = (ratings[i] > ratings[i - 1]) ? 1 : (ratings[i] < ratings[i - 1] ? -1 : 0);
            if ((old_slope > 0 && new_slope == 0) || (old_slope < 0 && new_slope >= 0)) {
                candies += count(up) + count(down) + Math.max(up, down);	//每个peak都少加了1,因为跟之前的valley的重复使用抵消了
                up = 0;		//pt10的少加1跟pt8在mountain a-b和d-e的重复使用相抵消了
                down = 0;
            }
            if (new_slope > 0)
                up++;
            if (new_slope < 0)
                down++;
            if (new_slope == 0)
                candies++;

            old_slope = new_slope;
        }
        candies += count(up) + count(down) + Math.max(up, down) + 1;	//但是第一个mountain的peak没有之前的valley重复使用抵消,	所以要加1
        return candies;
    }
}
