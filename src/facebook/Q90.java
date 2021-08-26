package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Subsets II
public class Q90 {
    // O(N*2^N) O(N*2^N) / O(N) - stack, if do not consider result space.
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null) return res;
        if(nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }
    private void backtrack(int[] nums, int start, List<Integer> list, List<List<Integer>> res) {
        res.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            backtrack(nums, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
    // O(N*2^N) O(lgn) - sort
    public List<List<Integer>> subsetsWithDup2(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int start, end = 0;
        for(int i = 0; i < nums.length; i++) {
            start = (i > 0 && nums[i] == nums[i - 1]) ? end : 0;
            end = res.size();
            for(int j = start; j < end; j++) {
                List<Integer> list = new ArrayList<>(res.get(j));
                list.add(nums[i]);
                res.add(list);
            }
        }
        return res;
    }
}
