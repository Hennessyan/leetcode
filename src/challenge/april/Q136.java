package challenge.april;
// Single Number
public class Q136 {

    public static void main(String[] args) {
        Q136 q = new Q136();
        int[] nums = {2, 2, 1};
        System.out.println(q.singleNumber(nums));
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        for(int num : nums) {
            res ^= num;
        }
        return res;
    }
}
