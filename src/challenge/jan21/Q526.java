package challenge.jan21;
// Beautiful Arrangement
public class Q526 {
    // O(n!) - less than n!, because of "beautiful arrangement check"
    // O(n)
    private int count;
    public int countArrangement0(int n) {
        int[] nums = new int[n];
        for(int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }
        count = 0;
        backtrack(nums, 0);
        return count;
    }

    private void backtrack(int[] nums, int i) {
        if(i == nums.length) {
            count++;
            return;
        }

        for(int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            if(nums[i] % (i + 1) == 0 || (i + 1) % nums[i] == 0) {
                backtrack(nums, i + 1);
            }
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    // O(n!*n)
    // O(n)
    private boolean[] visited;
    public int countArrangement(int n) {
        visited = new boolean[n + 1];
        count = 0;
        dfs(1, n);
        return count;
    }
    private void dfs(int i, int n) {
        if(i > n) {
            count++;
            return;
        }
        for(int j = 1; j <= n; j++) {
            if(!visited[j] && (j % i == 0 || i % j == 0)) {
                visited[j] = true;
                dfs(i + 1, n);
                visited[j] = false;
            }
        }
    }
}
