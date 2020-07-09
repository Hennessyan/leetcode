package google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Permutations
public class Q46 {

    //  O(n*n!) O(n*n!)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        backtrack(nums, new ArrayList<>(), res);
        return res;
    }
    private void backtrack(int[] nums, List<Integer> list, List<List<Integer>> res) {
        if(list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int num : nums) {
            if(!list.contains(num)) {
                list.add(num);
                backtrack(nums, list, res);
                list.remove(list.size() - 1);
            }
        }
    }

    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> tmp = new ArrayList<>();
        for(int n : nums) {
            tmp.add(n);
        }
        backtrack(tmp, 0, tmp.size(), res);
        return res;
    }
    private void backtrack(List<Integer> tmp, int i, int n, List<List<Integer>> res) {
        if(i == n) {
            res.add(new ArrayList<>(tmp));
        }
        for(int j = i; j < n; j++) {
            Collections.swap(tmp, i, j);
            backtrack(tmp, i + 1, n, res);  //注意这里是i + 1
            Collections.swap(tmp, i, j);
        }
    }

}
