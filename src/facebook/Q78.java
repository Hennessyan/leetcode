package facebook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Subsets
public class Q78 {
    // O(N*2^N) O(N*2^N)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        backtrack(res, new LinkedList<>(), 0, nums);
        return res;
    }

    private void backtrack(List<List<Integer>> res, LinkedList<Integer> list, int start, int[] nums) {
        res.add(new ArrayList<>(list));

        for(int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            backtrack(res, list, i + 1, nums);
            list.removeLast();
        }
    }

    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        int len = nums.length;

        for(int i = 0; i < (1 << len); i++) {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < len; j++) {
                if((i & (1 << j)) > 0) {
                    list.add(nums[j]);
                }
            }
            res.add(list);
        }
        return res;
    }

}
