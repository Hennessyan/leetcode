package facebook;

import java.util.HashMap;
import java.util.Map;

// Path Sum IV
// Q742, Q272
public class Q666 {
    // O(n) O(n)
    Map<Integer, Integer> tree;
    int sum;
    public int pathSum(int[] nums) {
        tree = new HashMap<>();
        sum = 0;
        for(int num : nums) {
            tree.put(num / 10, num % 10);
        }
        dfs(nums[0] / 10, 0);
        return sum;
    }
    private void dfs(int key, int total) {
        if(tree.containsKey(key)) {
            total += tree.get(key);
            int d = key / 10, i = key % 10;
            int l = (d + 1) * 10 + 2 * i - 1;
            int r = l + 1;
            if(!tree.containsKey(l) && !tree.containsKey(r)) {
                sum += total;
            } else {
                dfs(l, total);
                dfs(r, total);
            }
        }
    }
}
