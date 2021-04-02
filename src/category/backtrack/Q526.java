package category.backtrack;
// Beautiful Arrangement
public class Q526 {
    // O(n!) O(n)
    private int count;
    public int countArrangement(int n) {
        int[] nums = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            nums[i] = i;
        }
        dfs(nums, 1);
        return count;
    }

    private void dfs(int[] nums, int i) {
        if(i == nums.length) {
            count++;
            return;
        }

        for(int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            if(nums[i] % i == 0 || i % nums[i] == 0) {  // or - either one is fine
                dfs(nums, i + 1);
            }
            swap(nums, i, j);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
