package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Combination Sum
public class Q39 {
    // O(n^(t/m + 1)) O(t/m)
    // t - target value, m - minimal value among cs
    // n - nary tree, height: t/m + 1
    public List<List<Integer>> combinationSum(int[] cs, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(cs == null || cs.length == 0) {
            return res;
        }
        backtrack(res, new ArrayList<>(), cs, target, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> list, int[] cs, int target, int start) {
        if(target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        if(target < 0) {
            return;
        }
        for(int i = start; i < cs.length; i++) {
            list.add(cs[i]);
            backtrack(res, list, cs, target - cs[i], i);    // i, can reuse candidate
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, 0, new ArrayList<>(), res, target);
        return res;
    }
    private void backtrack(int[] candidates, int start, List<Integer> list,
                           List<List<Integer>> res, int target) {
        if(target == 0) {
            res.add(new ArrayList<>(list));
            return;
        } // else if(target < 0) return;
        for(int i = start; i < candidates.length; i++) {
            if(target - candidates[i] < 0) return;
            list.add(candidates[i]);
            backtrack(candidates, i, list, res, target - candidates[i]);
            list.remove(list.size() - 1);
        }
    }

    // DP : https://leetcode.com/problems/combination-sum/solution/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>>[] dp = new List[target + 1];
        for (int i = 0; i <= target; i++)
            dp[i] = new ArrayList<>();
        dp[0].add(new ArrayList<>());
        for (int candidate: candidates) {
            for (int j = candidate; j <= target; j++) {
                for (List<Integer> comb: dp[j - candidate]) {
                    List<Integer> newComb = new ArrayList(comb);
                    newComb.add(candidate);
                    dp[j].add(newComb);
                }
            }
        }
        return dp[target];
    }
}
