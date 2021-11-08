package amazon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Combination Sum III
public class Q216 {
    // O(C(9,K)*K)  - last step deep copy takes O(K), whole loop takes C(9,K) = 9! / (K!*(9 - K)!)
    // O(K)
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if(k == 0 || n == 0) {
            return res;
        }
        backtrack(res, new LinkedList<>(), 0, k, n);
        return res;
    }

    private void backtrack(List<List<Integer>> res, LinkedList<Integer> list, int val, int k, int n) {
        if(k == list.size()) {
            if(n == 0) {
                res.add(new ArrayList<>(list));
            }
            return;
        }
        if(n < 0) return;
        for(int i = val + 1; i <= 9; i++) {
            if(n >= i) {
                list.add(i);
                backtrack(res, list, i, k, n - i);
                list.removeLast();
            }
        }
    }

}
