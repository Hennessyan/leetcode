package google;

import java.util.*;

// Beautiful Arrangement II
public class Q667 {

    public static void main(String[] args) {
        Q667 q = new Q667();
        int[] res = q.constructArray(2, 1);
    }
    // method1 : O(n) O(n)
    // if k = n - 1, then we need : [1, n-1, 2, n-2, 3, n-3 ...]

    //            1 2 3 4 5 6 7 8
    // n=8,k=3 => 1 2 3 4 5 8 6 7
    // n=8,k=4 => 1 2 3 4 8 5 7 6
    public int[] constructArray(int n, int k) {
        int[] nums = new int[n];
        int i = 0;
        for(int val = 1; val < n - k; val++) {
            nums[i++] = val;
        }
        for(int j = 0; j <= k; j++) {       // <=
            nums[i++] = j % 2 == 0 ? (n - k + j / 2) : (n - j / 2);
        }
        return nums;
    }


    // 1 <= k < n <= 10^4 - LTE
    // method2 : O(n!) O(n) - TLE
    public int[] constructArray2(int n, int k) {
        int[] nums = new int[n];
        for(int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }
        int[] res = new int[n];
        List<List<Integer>> lists = new ArrayList<>();
        permutations(lists, nums, 0);
        for(List<Integer> list : lists) {
            if(beautiful(list, k)) {
                int i = 0;
                for(int v : list) {
                    res[i++] = v;
                }
                break;
            }
        }
        return res;
    }
    private void permutations(List<List<Integer>> lists, int[] nums, int i) {
        if(i == nums.length) {
            List<Integer> list = new ArrayList<>();
            for(int num : nums) {
                list.add(num);
            }
            lists.add(list);
            return;
        }
        for(int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            permutations(lists, nums, i + 1);
            swap(nums, i, j);
        }
    }
    private boolean beautiful(List<Integer> list, int k) {
        Set<Integer> seen = new HashSet<>();
        for(int i = 1; i < list.size(); i++) {
            seen.add(Math.abs(list.get(i) - list.get(i - 1)));
            if(seen.size() > k) {
                return false;
            }
        }
        return seen.size() == k;
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }




    // method3
    private int[] res;
    private Map<Integer, Integer> count;
    // TLC
    public int[] constructArray3(int n, int k) {
        int[] arr = new int[n];
        count  = new HashMap<>();
        dfs(arr, 0, n, 0, k);
        return res;
    }
    private void dfs(int[] arr, int start, int n, int visited, int k) {
        if(start >= n) {        // return if start >= n
            if(count.size() == k) {
                res = arr.clone();
            }
            return;
        }
        for(int i = 1; i <= n; i++) {
            if((visited & (1 << (i-1))) > 0) {  // > 0
                continue;
            }
            visited ^= 1 << (i - 1);
            arr[start] = i;
            if(start > 0) {
                int val = Math.abs(arr[start] - arr[start - 1]);
                int tmp = count.getOrDefault(val, 0);
                count.put(val, tmp + 1);
                dfs(arr, start + 1, n, visited, k);
                if(tmp == 0) {
                    count.remove(val);      // must remove entry if 0
                }
            } else {
                dfs(arr, start + 1, n, visited, k);
            }
            visited ^= 1 << (i - 1);
        }
    }
}
