package challenge.june;

import java.util.*;

// Perfect Squares
public class Q279 {

    public static void main(String[] args) {
        Q279 q = new Q279();
//        System.out.println(q.numSquares(12));   // 3  -> 4 + 4 + 4
//        System.out.println(q.numSquares(13));   // 2  -> 4 + 9
        System.out.println(q.numSquares(18));
    }
    // O(n*sqrt(n)) O(n)
    public int numSquares0(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= n; i++) {
            int val = (int) Math.sqrt(i);
            if(val * val == i) {
                dp[i] = 1;
                continue;
            }
            for(int j = 1; j <= val; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    // method2 : O(sqrt(n) ^ h) O(sqrt(n))  h is deep of n-ary tree, can't exceed 4
    Set<Integer> square_nums = new HashSet<Integer>();
    protected boolean is_divided_by(int n, int count) {
        if (count == 1) {
            return square_nums.contains(n);
        }

        for (Integer square : square_nums) {
            if (is_divided_by(n - square, count - 1)) {
                return true;
            }
        }
        return false;
    }

    public int numSquares1(int n) {
        this.square_nums.clear();

        for (int i = 1; i * i <= n; ++i) {
            this.square_nums.add(i * i);
        }

        int count = 1;
        for (; count <= n; ++count) {
            if (is_divided_by(n, count))
                return count;
        }
        return count;
    }
    // method3 : O(sqrt(n) ^ h) O(sqrt(n) ^ h) - last level of n-ary tree
    public int numSquares(int n) {

        List<Integer> square_nums = new ArrayList<Integer>();   //这里不能用SET,因为两层FOR LOOP中需要从小到大对V进行比较
        for (int i = 1; i * i <= n; ++i) {                      //如果是SET,可能上来就遇到一个很大的数,然后整层比较被BREAK掉从而错过正确答案
            square_nums.add(i * i);                             //比如: 18 SET - [16, 1, 4, 9] LIST - [1，4，9，16】
        }
        System.out.println(square_nums.size());
        Set<Integer> queue = new HashSet<Integer>();
        queue.add(n);

        int level = 0;
        while (queue.size() > 0) {
            level += 1;
            Set<Integer> next_queue = new HashSet<Integer>();

            for (Integer remainder : queue) {
                for (Integer square : square_nums) {
                    System.out.println(remainder + " " + square);
                    if (remainder.equals(square)) {
                        return level;
                    } else if (remainder < square) {
                        break;
                    } else {
                        next_queue.add(remainder - square);
                    }
                }
            }
            queue = next_queue;
        }
        return level;
    }
}
