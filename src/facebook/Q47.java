package facebook;

import java.util.*;

// Permutations II
public class Q47 {
    // O(n!*n) O(n!*n)
    // method1: HashMap
    private Map<Integer, Integer> counter;
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        counter = new HashMap<>();
        for(int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        helper(res, new LinkedList<>(), nums.length);
        return res;
    }

    private void helper(List<List<Integer>> res, LinkedList<Integer> list, int length) {
        if(list.size() == length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();

            if(count == 0) {
                continue;
            }
            list.add(num);
            counter.put(num, count - 1);
            helper(res, list, length);
            list.removeLast();
            counter.put(num, count);
        }
    }

    // method2: visited array
    private boolean[] visited;
    public List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        int len = nums.length;
        visited = new boolean[len];

        backtrack(res, new LinkedList<>(), nums);
        return res;
    }

    private void backtrack(List<List<Integer>> res, LinkedList<Integer> list, int[] nums) {
        if(list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            list.add(nums[i]);
            backtrack(res, list, nums);
            list.removeLast();
            visited[i] = false;
        }
    }
}
