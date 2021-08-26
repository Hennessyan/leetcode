package amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Matchsticks to Square
public class Q473 {

    //DFS : O(4^n) O(n)
    List<Integer> nums;
    int[] sum;
    int edge;
    public boolean makesquare(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        sum = new int[4];
        int total = 0;
        for(int n : nums) {
            total += n;
        }
        edge = total / 4;
        if(edge * 4 != total) {	//最先检查是否有可能构成square
            return false;
        }
        this.nums = new ArrayList<>();
        for(int n : nums) {
            this.nums.add(n);
        }
        Collections.sort(this.nums, Collections.reverseOrder()); 	//[8,4,4,4] 从大到小可以更快的发现错误结果从而终止.
        return dfs(0);
    }
    private boolean dfs(int index) {
        if(index == this.nums.size()) {
            return sum[0] == sum[1] && sum[1] == sum[2] && sum[2] == sum[3];
        }
        int val = this.nums.get(index);
        for(int i = 0; i < 4; i++) {
            if(sum[i] + val <= edge) {
                sum[i] += val;
                if(dfs(index + 1)) {
                    return true;
                }
                sum[i] -= val;
            }
        }
        return false;
    }

    //DP (memo)	:	O(N*2^N) O(N + 2^N)	<= 4 * 2^N unique bit masks are possible
//    int L;
//    Map<Pair<Integer, Integer>, Boolean> memo;
//    public boolean makesquare(int[] nums) {
//        if(nums == null || nums.length == 0) {
//            return false;
//        }
//        int total = 0;
//        for(int n : nums) {
//            total += n;
//        }
//        edge = total / 4;
//        if(edge * 4 != total) {
//            return false;
//        }
//        this.nums = nums;
//        memo = new HashMap<>();
//        L = nums.length;
//        return help((1 << L) - 1, 0);
//    }
//    private boolean help(int mask, int numOfComplete) {
//        int total = 0;
//        for(int i = L - 1; i >= 0; i--) {
//            if((mask & (1 << i)) == 0) {
//                total += this.nums[L - i - 1];
//            }
//        }
//        Pair<Integer, Integer> key = new Pair(mask, numOfComplete);	//不能使用int[],否则Memory Limit Exceeded  必须在numOfComplete++之前
//
//        if(total > 0 && total % edge == 0) {	//total是所有边的总和,因此不能用total==edge来判断
//            numOfComplete++;
//        }
//        if(numOfComplete == 3) {				//因为已经知道4*edge是总和,发现3个以后,最后一个一定存在,所以直接返回true
//            return true;
//        }
//        if(memo.containsKey(key)) {
//            return memo.get(key);
//        }

//        int c = total / edge;					//同上,total是总和,所以得计算rem是多少.
//        // Remaining vlength in the current partially formed side.
//        int rem = edge * (c + 1) - total;

//        boolean ans = false;
//        for(int i = L - 1; i >= 0; i--) {
//            if (this.nums[L - 1 - i] <= rem && (mask&(1 << i)) > 0) {	//注意： (mask&(1 << i)) > 0不是==0
//                if(help(mask ^ (1 << i), numOfComplete)) {
//                    ans = true;
//                    break;
//                }
//            }
//        }
//        memo.put(key, ans);
//        return ans;
//    }
}
