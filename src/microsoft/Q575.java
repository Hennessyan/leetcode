package microsoft;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Distribute Candies
public class Q575 {

    // In an interview, you should always check whether or not the interviewer minds you overwriting the input.
    // Be ready to explain the pros and cons of doing so if asked!
    // O(nlgn) O(n) - space for timsort
    public int distributeCandies(int[] candyType) {
        Arrays.sort(candyType);
        int len = candyType.length;
        int diffType = 1;
        for(int i = 1; i < len; i++) {
            if(candyType[i] != candyType[i - 1]) {
                diffType++;
            }
        }
        return Math.min(diffType, len / 2);
    }

    // O(n) O(n)
    public int distributeCandies1(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        for(int c : candyType) {
            set.add(c);
        }
        return Math.min(set.size(), candyType.length / 2);
    }
}
