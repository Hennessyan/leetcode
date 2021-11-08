package microsoft;
// Single Number
public class Q136 {

    public static void main(String[] args) {
        Q136 q = new Q136();
        int[] nums = {4,1,2,1,2};
        System.out.println(q.singleNumber(nums));   // 4
    }
    // xor
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int n : nums) {
            res ^= n;
        }
        return res;
    }
}
