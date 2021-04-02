package challenge.jan21;
// Check If All 1's Are at Least Length K Places Away
public class Q1437 {
    // O(n) O(1)
    public boolean kLengthApart(int[] nums, int k) {
        int i = 0, n = nums.length;
        while(i < n) {
            if(nums[i] == 1) {
                i = i + 1;
                int c = 0;
                while(i < n && nums[i] == 0) {
                    c++;
                    i++;
                }
                if(i == n) {
                    return true;
                }
                if(c < k) {
                    return false;
                }
            } else {
                i++;
            }
        }
        return true;
    }
    public boolean kLengthApart1(int[] nums, int k) {
        int count = k;  // avoid wrong answer if nums[0] == 1
        for(int num : nums) {
            if(num == 1) {
                if(count < k) {
                    return false;
                }
                count = 0;
            } else {
                count++;
            }
        }
        return true;
    }
    // works if len of nums <= 32
    public boolean kLengthApart3(int[] nums, int k) {
        // convert binary array into int
        int x = 0;
        for (int num : nums) {
            x = (x << 1) | num;
        }

        // base case
        if (x == 0 || k == 0) {
            return true;
        }

        // remove trailing zeros
        while ((x & 1) == 0) {
            x = x >> 1;
        }

        while (x != 1) {
            // remove trailing 1-bit
            x = x >> 1;

            // count trailing zeros
            int count = 0;
            while ((x & 1) == 0) {
                x = x >> 1;
                ++count;
            }

            // number of zeros in-between 1-bits
            // should be greater than or equal to k
            if (count < k) {
                return false;
            }
        }
        return true;
    }
}
