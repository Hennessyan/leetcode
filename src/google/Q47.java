package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Permutations II
public class Q47 {

    // O(n*n!) O(n*n!)
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        backtrack(nums, new ArrayList<>(), res, new boolean[nums.length]);
        return res;
    }
    private void backtrack(int[] nums, List<Integer> list, List<List<Integer>> res, boolean[] visited) {
        if(nums.length == list.size()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i-1])) {
                continue;
            }
            visited[i] = true;
            list.add(nums[i]);
            backtrack(nums, list, res, visited);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

}
