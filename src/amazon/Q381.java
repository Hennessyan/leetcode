package amazon;

import java.util.*;

// Insert Delete GetRandom O(1) - Duplicates allowed
public class Q381 {

    class RandomizedCollection {

        List<Integer> list;
        Map<Integer, Set<Integer>> index;   // value use LinkedList is better.
        Random random;
        public RandomizedCollection() {
            list = new ArrayList<>();
            index = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            index.computeIfAbsent(val, x -> new HashSet<>()).add(list.size());
            list.add(val);
            return index.get(val).size() == 1;
        }

        public boolean remove(int val) {
            if(!index.containsKey(val)) return false;
            Set<Integer> set = index.get(val);
            Iterator<Integer> it = set.iterator();
            Integer p = it.next();
            set.remove(p);
            if(set.size() == 0) index.remove(val);
            if(p != list.size() - 1) {
                int last = list.get(list.size() - 1);
                Set<Integer> tmp = index.get(last);
                tmp.remove(list.size() - 1);
                tmp.add(p);
                list.set(p, last);
            }
            list.remove(list.size() - 1);
            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }
}
