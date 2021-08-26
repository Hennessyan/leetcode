package amazon;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Maximum Product of Splitted Binary Tree
public class Q1339 {
    // method1: one pass O(n) O(n)
    public int maxProduct1(TreeNode root) {
        List<Integer> sums = new ArrayList<>();
        long total = dfs(root, sums);
        long max = 0;
        for(long val : sums) {
            max = Math.max(max, val * (total - val));
        }
        return (int) (max % (1e9 + 7));
    }
    private int dfs(TreeNode root, List<Integer> sums) {
        if(root == null) {
            return 0;
        }
        int sum = root.val + dfs(root.left, sums) + dfs(root.right, sums);
        sums.add(sum);
        return sum;
    }
    // if we can only use 32 bit Integer, then we needs modular exponentiation.
    public int maxProduct2(TreeNode root) {
        List<Integer> sums = new ArrayList<>();
        int total = dfs(root, sums);
        int half = 0, diff = Integer.MAX_VALUE;
        for(int val : sums) {
            int tmp = Math.abs(total - val * 2);
            if(tmp < diff) {
                diff = tmp;
                half = val;
            }
        }
        return modularMultiplication(half, total - half, (int)(1e9+7));
    }
    // modular exponentiation
    private int modularMultiplication(int a, int b, int mod) {
        int product = 0;
        int val = a;
        while(b > 0) {
            int bit = b % 2;
            b >>= 1;
            if(bit == 1) {
                product += val;
                product %= mod;
            }
            val <<= 1;
            val %= mod;
        }
        return product;
    }


    // method3: two passes
    Map<TreeNode, Long> sumMap;
    long max = 0;
    public int maxProduct(TreeNode root) {
        if(root == null) {
            return 0;
        }
        sumMap = new HashMap<>();
        dfs(root);
        long sum = sumMap.get(root);
        dfs(root, sum);
        return (int) (max % (1e9+7));
    }
    private long dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }
        long val = root.val + dfs(root.left) + dfs(root.right);
        sumMap.put(root, val);
        return val;
    }
    private void dfs(TreeNode root, long sum) {
        if(root == null) {
            return;
        }
        if(root.left != null) {
            long a = sumMap.get(root.left);
            long b = sum - a;

            max = Math.max(max, a * b);
            dfs(root.left, sum);
        }
        if(root.right != null) {
            long a = sumMap.get(root.right);
            long b = sum - a;

            max = Math.max(max, a * b);
            dfs(root.right, sum);
        }
    }
}
