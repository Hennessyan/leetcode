package amazon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// Next Greater Element I ->  both of unique elements
public class Q496 {
    // O(m+n) O(m+n)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int[] res = new int[len1];
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for(int num : nums2) {
            while(!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        while(!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }
        for(int i = 0; i < len1; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int[] res = new int[len1];
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for(int num : nums2) {
            while(!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
//        while(!stack.isEmpty()) {
//            map.put(stack.pop(), -1);
//        }
        for(int i = 0; i < len1; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
