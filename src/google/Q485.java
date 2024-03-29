package google;
// Max Consecutive Ones
public class Q485 {

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, count = 0;
        for(int num : nums) {
            if(num == 1) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        max = Math.max(max, count);
        return max;
    }
}
