package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Missing Ranges
public class Q163 {

    public static void main(String[] args) {
        Q163 q = new Q163();
        int[] nums = {0,1,3,50,75};
        List<String> res = q.findMissingRanges(nums, 0, 99);    // ["2","4->49","51->74","76->99"]
    }

    public List<String> findMissingRanges0(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return Arrays.asList(format(lower, upper));
        }
        int len = nums.length;
        if(lower < nums[0]) {
            list.add(format(lower, nums[0] - 1));
        }
        for(int i = 1; i < len; i++) {
            if(nums[i - 1] + 1 < nums[i]) {
                list.add(format(nums[i - 1] + 1, nums[i] - 1));
            }
        }
        if(nums[len - 1] < upper) {
            list.add(format(nums[len - 1] + 1, upper));
        }
        return list;
    }

//    private String format(int lower, int upper) {
//        return lower + (lower < upper ?  "->" + upper : "");
//    }
    // StringBuilder much faster than string concatenation.
    private String format(int lower, int upper) {
        StringBuilder sb = new StringBuilder();
        sb.append(lower);
        if(lower < upper) {
            sb.append("->").append(upper);
        }
        return sb.toString();
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return Arrays.asList(format(lower, upper));
        }
        for(int num : nums) {
            if(lower < num) {
                list.add(format(lower, num - 1));
            }
            // if(num == upper) return list;
            lower = num + 1;
        }
        if(lower <= upper) list.add(format(lower, upper));
        return list;
    }
}
