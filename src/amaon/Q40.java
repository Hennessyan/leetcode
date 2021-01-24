package amaon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Combination Sum II
public class Q40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        backtrack(res, new LinkedList<>(), candidates, target, 0);
        return res;
    }
    // O(2^n) O(n)
    private void backtrack(List<List<Integer>> res, LinkedList<Integer> list, int[] candidates, int target, int start) {
        if(target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i < candidates.length; i++) {
            if(i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            int remainder = target - candidates[i];
            if(remainder < 0) {
                break;
            }
            list.add(candidates[i]);
            backtrack(res, list, candidates, remainder, i + 1);
            list.removeLast();
        }
    }

}
