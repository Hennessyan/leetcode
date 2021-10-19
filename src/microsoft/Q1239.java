package microsoft;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Maximum Length of a Concatenated String with Unique Characters
public class Q1239 {

    public int maxLength(List<String> arr) {
        if(arr == null || arr.size() == 0) {
            return 0;
        }
        return maxLength(arr, 0, "");
    }
    private int maxLength(List<String> arr, int start, String res) {
        int best = res.length();
        Set<Character> set = new HashSet<>();
        for(char c : res.toCharArray()) {
            if(!set.add(c)) {
                return 0;
            }
        }
        for(int i = start; i < arr.size(); i++) {
            best = Math.max(best, maxLength(arr, i + 1, res + arr.get(i)));
        }
        return best;
    }
    // O(2^n) O(n)
    public int maxLength1(List<String> arr) {
        if(arr == null || arr.size() == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for(String str : arr) {
            strToInteger(set, str);
        }
        int[] optArr = new int[set.size()];
        int i = 0;
        for(int tmp : set) {
            optArr[i++] = tmp;
        }
        return maxLength(optArr, 0, 0);
    }
    private int maxLength(int[] arr, int start, int res) {
        int curValue = res & ((1 << 26) - 1);
        int curLen = res >> 26;
        int best = curLen;
        for(int i = start; i < arr.length; i++) {
            int next = arr[i];
            int nextValue = next & ((1 << 26) - 1);
            if((curValue & nextValue) > 0) {
                continue;
            }
            int len = (next >> 26) + curLen;
            nextValue |= curValue;
            best = Math.max(best, maxLength(arr, i + 1, nextValue + (len << 26)));
        }
        return best;
    }

    private void strToInteger(Set<Integer> set, String str) {
        int value = 0;
        for(char c : str.toCharArray()) {
            int mask = 1 << (c - 'a');
            if((value & mask) > 0) {
                return;
            }
            value += mask;
        }
        set.add(value + (str.length() << 26));
    }
}
