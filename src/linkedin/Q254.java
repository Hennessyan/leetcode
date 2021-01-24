package linkedin;

import java.util.ArrayList;
import java.util.List;

// Factor Combinations
//
public class Q254 {

    public static void main(String[] args) {
        Q254 q = new Q254();
        List<List<Integer>> res = q.getFactors(32);
    }
    // https://stackoverflow.com/questions/45662736/how-to-find-the-time-complexity-of-dfsbacktracking-algorithms
    // https://www.jiuzhang.com/qa/5224/

    // 一个数N有log(N)个factors，然后我们相当于在做factors的combination，所以是2的log(N)次方，所以是N各解，每个解的生成复杂度是log(N)所以总体是NlogN。
    // O(nlgn)
    // SC - LESS THAN O(nlgn), depth is lgn
    // consider next factor always greater or equal to previous factor.
    // 32 -> if we choose 16 as first factor, we can't use 2. We should choose next factor >= 16.
    /*
    [2, 2, 2, 2, 2],
    [2, 2, 2, 4]
    [2, 2, 8],
    [2, 4, 4],
    [2, 16],
    [4, 8]
     */
    public List<List<Integer>> getFactors1(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 4) {
            return res;
        }
        dfs1(n, 2, new ArrayList<>(), res);
        return res;
    }

    private void dfs1(int n, int index, List<Integer> list, List<List<Integer>> res) {
        if (n == 1) {
            if (list.size() > 1) {
                res.add(new ArrayList<>(list));
            }
            return;
        }
        for (int i = index; i <= n; i++) {  // should be <=
            if (n % i == 0) {
                list.add(i);
                dfs1(n / i, i, list, res);
                list.remove(list.size() - 1);
            }
        }
    }
    // better method

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 4) {
            return res;
        }
        dfs(n, 2, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int n, int index, List<Integer> list, List<List<Integer>> res) {
        if (list.size() != 0) { //使用8 -> 2*2*2的例子来理解为什么在这里要提前把n加入到list,然后到res中.
            list.add(n);
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
        }
        for (int i = index; i <= Math.sqrt(n); i++) {  //如果当前factor是SQRT(n)了,后边的又肯定大于它,自然不会存在乘积等于n的情况.
            if (n % i == 0) {                          // or i <= n / i
                list.add(i);
                dfs(n / i, i, list, res);
                list.remove(list.size() - 1);
            }
        }
    }
    /*
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res =  new ArrayList<>();
        if (n <= 1) return res;
        getFactors(res, new ArrayList<>(), n, 2);
        return res;
    }

    private void getFactors(List<List<Integer>> res, List<Integer> list, int n, int pos) {
        for (int i = pos; i <= Math.sqrt(n); i++) {
            if (n % i == 0 && n / i >= i) {
                list.add(i);
                list.add(n / i);
                res.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
                getFactors(res, list, n / i, i);
                list.remove(list.size() - 1);
            }
        }
    }
    */
}
