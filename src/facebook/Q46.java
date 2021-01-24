package facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Permutations
public class Q46 {
    // O(n!*n) O(n!*n)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        for(int num : nums) {
            list.add(num);
        }
        backtrack(res, 0, list.size(), list);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int i, int size, List<Integer> list) {
        if(i == size) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int j = i; j < size; j++) {
            Collections.swap(list, i, j);
            backtrack(res, i + 1, size, list);
            Collections.swap(list, i, j);
        }
    }
}
