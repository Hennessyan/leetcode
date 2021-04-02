package google;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// Design Phone Directory
public class Q379 {

    class PhoneDirectory {
        Queue<Integer> numberPool;
        Set<Integer> numberSet;
        int num;
        int max;
        /** Initialize your data structure here
         @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
        public PhoneDirectory(int maxNumbers) {
            numberPool = new LinkedList<>();
            numberSet = new HashSet<>();;
            num = 0;
            max = maxNumbers;
        }

        /** Provide a number which is not assigned to anyone.
         @return - Return an available number. Return -1 if none is available. */
        public int get() {
            if(!numberPool.isEmpty()) {
                int val = numberPool.poll();
                numberSet.add(val);
                return val;
            }
            if(num >= max) {
                return -1;
            }
            numberSet.add(num);
            return num++;
        }

        /** Check if a number is available or not. */
        public boolean check(int number) {
            return !numberSet.contains(number);
        }

        /** Recycle or release a number. */
        public void release(int number) {
            if(!numberSet.contains(number)) {
                return;
            }
            numberPool.add(number);
            numberSet.remove(number);
        }
    }

    // https://leetcode.com/problems/design-phone-directory/discuss/956186/Java-solution-using-set-only
}
