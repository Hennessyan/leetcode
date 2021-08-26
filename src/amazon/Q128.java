package amazon;

import java.util.*;

// Longest Consecutive Sequence
public class Q128 {

    //https://www.youtube.com/watch?v=rc2QdQ7U78I
    /*method1 hashmap*/
    //O(N) O(N)
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            if(map.containsKey(num)){
                continue;
            }
            int l = map.containsKey(num - 1) ? map.get(num - 1) : 0;
            int r = map.containsKey(num + 1) ? map.get(num + 1) : 0;
            if(l > 0 && r > 0){
                map.put(num, l + r + 1);
                map.put(num - l, l + r + 1);
                map.put(num + r, l + r + 1);
            }else if(l > 0){
                map.put(num, l + 1);
                map.put(num - l, l + 1);
            }else if(r > 0){
                map.put(num, r + 1);
                map.put(num + r, r + 1);
            }else{
                map.put(num, 1);
            }
        }
        int ans = 0;
        for(int key : map.keySet()){
            ans = Math.max(ans, map.get(key));
        }
        return ans;
    }
    /*method2*/
    //concise than method1
    public int longestConsecutive1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for(int num : nums){
            if(map.containsKey(num)){		//注意需要这个避免重复的值改变value的值而导致结果过大
                continue;
            }
            int l = map.containsKey(num - 1) ? map.get(num - 1) : 0;
            int r = map.containsKey(num + 1) ? map.get(num + 1) : 0;
            int len = l + r + 1;
            map.put(num, len);
            map.put(num - l, len);
            map.put(num + r, len);
            ans = Math.max(ans, len);
        }
        return ans;
    }

    /*method3 hashset*/
    //O(N) O(N)
    //每个元素只访问两次,所以时间复杂度仍然是O(N)
    public int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        int ans = 0;
        for(int num : nums){
            if(!set.contains(num - 1)){
                int len = 0;
                while(set.contains(num++)){
                    ++len;
                }
                ans = Math.max(ans, len);
            }
        }
        return ans;
    }
    // O(nlgn) O(lgn)
    public int longestConsecutive4(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                if (nums[i] == nums[i-1]+1) {
                    currentStreak += 1;
                }
                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }
}
