package facebook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Combinations
public class Q77 {
    // O(C(n,k) * k)
    // O(C(n,k) * k) - if consider result storage
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(n < k) {
            return res;
        }
        backtrack(res, new LinkedList<>(), 1, n, k);
        return res;
    }

    private void backtrack(List<List<Integer>> res, LinkedList<Integer> list, int start, int n, int k) {
        if(list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i <= n; i++) {
            list.add(i);
            backtrack(res, list, i + 1, n, k);
            list.removeLast();
        }
    }
    // method2
    public List<List<Integer>> combine2(int n, int k) {
        // init first combination
        LinkedList<Integer> nums = new LinkedList<Integer>();
        for(int i = 1; i < k + 1; ++i)
            nums.add(i);
        nums.add(n + 1);    //用来跳出L41的WHILE LOOP 在得到（3，4）的组合之后

        List<List<Integer>> output = new ArrayList<List<Integer>>();
        int j = 0;
        while (j < k) {
            // add current combination
            output.add(new LinkedList(nums.subList(0, k)));
            // increase first nums[j] by one
            // if nums[j] + 1 != nums[j + 1]
            j = 0;
            while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1)) {
                nums.set(j, j++ + 1);
            }
            nums.set(j, nums.get(j) + 1);
        }
        return output;
    }
}
