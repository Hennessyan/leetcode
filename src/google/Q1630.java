package google;

import java.util.*;

// Arithmetic Subarrays
public class Q1630 {
    // brute force : O(n * m * mlgm) O(n + m)
    public List<Boolean> checkArithmeticSubarrays1(int[] nums, int[] l, int[] r) {
        int m = nums.length, n = l.length;
        List<Boolean> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = l[i]; j <= r[i]; j++) {
                list.add(nums[j]);
            }
            if(list.size() == 1) {
                res.add(true);
            } else {
                Collections.sort(list);
                int diff = list.get(1) - list.get(0);
                for(int k = 2; k < list.size(); k++) {
                    if(list.get(k) - list.get(k - 1) != diff) {
                        res.add(false);
                        break;
                    }
                }
                if(res.size() < i + 1) {
                    res.add(true);
                }
            }
            list.clear();
        }
        return res;
    }
    // O(n * m) O(n + m)
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int m = nums.length, n = l.length;
        List<Boolean> res = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for(int j = l[i]; j <= r[i]; j++) {
                set.add(nums[j]);
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
            }
            if(set.size() == 1) {
                res.add(true);
            } else {
                if((max - min) % (r[i] - l[i]) != 0) {
                    res.add(false);
                } else {
                    int d = (max - min) / (r[i] - l[i]);
                    int val = min;
                    while(val <= max) {
                        if(!set.contains(val)) {
                            res.add(false);
                            break;
                        }
                        val += d;
                    }
                    if(res.size() == i) {
                        res.add(true);
                    }
                }
            }
        }
        return res;
    }
    // fastest!
    public List<Boolean> checkArithmeticSubarrays2(int[] nums, int[] l, int[] r) {
        Boolean[] checks = new Boolean[l.length];
        for(int i =0 ;i< l.length; i++)
            checks[i] = check(nums, l[i], r[i]);

        return Arrays.asList(checks);
    }


    private Boolean check(int[] nums, int l, int r){
        int length =  r - l + 1;
        int max = nums[l];
        int min = nums[l];

        for(int i = l; i<= r; i++){
            max = Math.max(nums[i], max);
            min = Math.min(nums[i], min);
        }


        //If all numbers are same
        if (max == min) return Boolean.TRUE;

        // if not evenly spaced
        if ((max - min) % (length - 1 ) != 0) return Boolean.FALSE;

        int d = (max-min) / (length -1);

        boolean [] used = new boolean[ r-l+1];
        for(int i=l; i<=r; i++){
            int num = nums[i] - min;
            if ( num %d != 0 || used[num/d]) return Boolean.FALSE;
            used[num/d] = true;
        }

        return Boolean.TRUE;
    }
}
