package amazon;

import java.util.*;

// Insert Delete GetRandom O(1)
public class Q380 {

    class RandomizedSet {
        List<Integer> list;
        Map<Integer, Integer> map;
        Random random;
        /** Initialize your data structure here. */
        public RandomizedSet() {
            random = new Random();
            list = new ArrayList<>();
            map = new HashMap<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(!map.containsKey(val)) {
                list.add(val);
                map.put(val, list.size() - 1);
                return true;
            }
            return false;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(map.containsKey(val)) {
                int index = map.get(val);
                if(index != list.size() - 1) {
                    int last = list.get(list.size() - 1);
                    list.set(index, last);
                    map.put(last, index);
                }
                list.remove(list.size() - 1);
                map.remove(val);
                return true;
            }
            return false;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int i = random.nextInt(list.size());
            return list.get(i);
        }
    }
}
