package amazon;
//
public class Q189 {

    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }
    private void reverse(int[] nums, int l, int r) {
        while(l < r) {
            int tmp = nums[l];
            nums[l++] = nums[r];
            nums[r--] = tmp;
        }
    }

    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        for(int i = 0; i < k; i++) {
            int pre = nums[n - 1];
            for(int j = n - 2; j >= 0; j--) {
                nums[j + 1] = nums[j];
            }
            nums[0] = pre;
        }
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] tmp = new int[n];
        for(int i = 0; i < n; i++) {
            tmp[(i + k) % n] = nums[i];
        }
        for(int i = 0; i < n; i++) {
            nums[i] = tmp[i];
        }
    }
}
